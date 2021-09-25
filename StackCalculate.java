package SimpleCalculate;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackCalculate
{
    //将一般式转化成后缀式
    static String toPostfix(String str)throws Exception
    {
        Stack<String> postfix = new Stack<>();
        StringBuilder postFix = new StringBuilder();
        Pattern p = Pattern.compile("(?<!\\d)-?\\d+(\\.\\d+)?|[+\\-*/()]");//这个正则为匹配表达式中的数字或运算符
        Matcher m = p.matcher(str);
        while(m.find())
        {
            String c = m.group();
            if (c.equals("("))//遇到左括号，直接压栈
            {
                postfix.push(c);
                continue;
            }
            if (c.equals(")"))//遇到右括号，弹栈输出直到弹出左括号（左括号不输出）
            {
                while (!postfix.peek().equals("(")) {
                    postFix.append(postfix.peek()+" ");
                    postfix.pop();
                }
                postfix.pop();
                continue;
            }
            if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
                if (!postfix.empty() && getPriority(postfix.peek()) >= getPriority(c)) {
                    while (!postfix.empty() && getPriority(postfix.peek()) >= getPriority(c)) {
                        postFix.append(postfix.peek()+" ");
                        postfix.pop();
                    }
                }
                postfix.push(c);
            } else
                postFix.append(c+" ");
        }
        while (!postfix.empty())
        {
            postFix.append(postfix.peek()+" ");
            postfix.pop();
        }
        return postFix.toString();
    }
    //获得优先级
    static int getPriority(String ch)throws Exception
    {
        if (ch.equals("+") || ch.equals("-"))
            return 1;
        if (ch.equals("*") || ch.equals("/"))
            return 2;
        if (ch.equals("("))
            return 0;
        throw new Exception("illegal operator!");
    }
    //基本运算
    static int calculate(int a,int b,String ch)
    {
        if (ch.equals("+"))
            return a+b;
        if (ch.equals("-"))
            return a-b;
        if (ch.equals("*"))
            return a*b;
        return 0;
    }
    //分数的运算
    static int[] calculate(int a1,int a2,int b1,int b2,String ch)
    {
        Fraction a = new Fraction(a1, a2);
        Fraction b = new Fraction(b1, b2);
        if (ch.equals("+"))
            return a.add(b).getNumber();
        if (ch.equals("-"))
            return a.sub(b).getNumber();
        if (ch.equals("*"))
            return a.multiply(b).getNumber();
        if (ch.equals("/"))
            return a.divide(b).getNumber();
        return new int[]{0,0};
    }
   //获得结果
    static String getResult(String str)
    {
        //变成后缀表达式
        try {
            str = toPostfix(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(str.contains("/0"))
            return "-1";
        String[] str0 = str.split(" ");
        if (!str.contains("/")) //不含除号的基本运算
        {
            Stack<Integer> stack = new Stack<>();
            for (String c : str0)
            {
                if (c.equals("+") || c.equals("-") || c.equals("*"))
                {
                    int b = stack.peek();
                    stack.pop();
                    int a = stack.peek();
                    stack.pop();
                    stack.push(calculate(a, b, c));
                    //在这里作出调整，对于每一个c和calculate(a,b,c)检查
                    //在这里作出调整，对于每一个c和calculate(a,b,c)检查
                    //在范围之外
                    if (a < 0 || a > CalculateSystem.numRange || b < 0 || b > CalculateSystem.numRange)
                        return "-1";
                } else
                    stack.push(Integer.parseInt(c));
            }
            if (stack.peek()<0||stack.peek()>CalculateSystem.numRange)
                return "-1";
            return Integer.toString(stack.peek());
        }
        else //带除号转换成分数运算
        {
            Stack<int []> stack = new Stack<>();
            for(String c:str0)
            {
                if (c.equals("+") || c.equals("-") || c.equals("*")||c.equals("/")) {
                    int[] b = stack.peek();
                    stack.pop();
                    int[] a = stack.peek();
                    stack.pop();
                    if (a[0] < 0 || a[0] > CalculateSystem.numRange || a[1] < 0 || a[1] > CalculateSystem.numRange
                        ||b[0] <= 0 || b[0] > CalculateSystem.numRange||b[1] <= 0 || b[1] > CalculateSystem.numRange
                        ||a[0]>a[1]&&a[1]!=1||b[0]>b[1]&&b[1]!=1)
                        return "-1";
                    stack.push(calculate(a[0], a[1], b[0],b[1],c));
                } else
                    stack.push(new int[]{Integer.parseInt(c), 1});
            }
            //继续对结论进行考察
            if (stack.peek()[0] < 0 || stack.peek()[0] > CalculateSystem.numRange ||stack.peek()[0]>stack.peek()[1]
            ||stack.peek()[1] < 0 || stack.peek()[1] > CalculateSystem.numRange )
                return "-1";
            if (stack.peek()[0] == stack.peek()[1])
                return "1";
            else if (stack.peek()[1] == 1)
                return Integer.toString(stack.peek()[0]);
            return stack.peek()[0]+"/"+stack.peek()[1];
        }
    }

}
class Fraction   //分数计算
{

    private int Numerator; // 分子
    private int Denominator; // 分母

    public Fraction(int numerator, int denominator)
    {
        this.Numerator = numerator;
        if (denominator == 0)
        {
            throw new ArithmeticException("分母不能为零");
        } else
        {
            this.Denominator = denominator;
        }
        change();
    }

    public int[] getNumber()
    {
        return new int[]{getNumerator(),getDenominator()};
    }
    public int getNumerator()
    {
        return Numerator;
    }

    public int getDenominator()
    {
        return Denominator;
    }

    private Fraction change()
    {
        int gcd = this.gcd(this.Numerator, this.Denominator);
        this.Numerator /= gcd;
        this.Denominator /= gcd;
        return this;
    }

    /**
     * 最大公因数
     *
     */
    private int gcd(int a, int b)
    {
        int mod = a % b;
        if (mod == 0)
        {
            return b;
        } else
        {
            return gcd(b, mod);
        }
    }

    /**
     * 四则运算
     */
    public Fraction add(Fraction second)
    {
        return new Fraction(this.Numerator * second.Denominator + second.Numerator * this.Denominator,
                this.Denominator * second.Denominator);
    }

    public Fraction sub(Fraction second)
    {
        return new Fraction(this.Numerator * second.Denominator - second.Numerator * this.Denominator,
                this.Denominator * second.Denominator);
    }

    public Fraction multiply(Fraction second)
    {
        return new Fraction(this.Numerator*second.Numerator,
                this.Denominator * second.Denominator);
    }

    public Fraction divide(Fraction second)
    {
        return new Fraction(this.Numerator*second.Denominator,
                this.Denominator * second.Numerator);
    }
}

