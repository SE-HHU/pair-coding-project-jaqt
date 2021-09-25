package SimpleCalculate;

import java.io.FileWriter;
import java.util.*;

public class CalculateSystem
{
    static int numRange;
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入题目个数：");
        int exesNumber = in.nextInt();
        System.out.println("请输入表达式中出现的最大数值：");
        numRange = in.nextInt();
        String[] exercises = new String[exesNumber];//生成存放题目的二维数组
        for (int i = 0; i < exesNumber; i++)//循环生成题目
        {
            //这里有一个检查方法，检查不通过，题目销毁，
            if (StackCalculate.getResult(exercises[i] = createExes()).equals("-1") )
            {
                i--;
            }
        }
        writeEA(exercises);//写入题目和答案
    }
    //产生一个范围内的整数
    static String getNumber()
    {
        return String.valueOf((int)(Math.random() * (numRange)) + 1);
    }
    //随机生成1到3个运算符个数
    static int getSignNumber()
    {
        return (int)(Math.random() * 3) + 1;
    }
    //用1，2，3，4来代表"+""-""*""/"
    static String getSign()
    {
        return switch ((int) (Math.random() * 4) + 1) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> "";
        };
    }
    //是否带括号；
    static boolean hasBrackets()
    {
        return !new Random().nextBoolean();
    }

    //生成一道题目
    static String createExes()
    {
        //生成一道不带括号的题目;
        LinkedList<String> list = new LinkedList<>();
        int signNumber = getSignNumber();
        list.add(getNumber());
        for(int i =0;i<signNumber;i++)
        {
                list.add(getSign());
                list.add(getNumber());
        }
        //在不带括号的基础上加入括号；
        if (signNumber >= 2&&hasBrackets())//添加括号
        {
            if(signNumber == 2)
            {
                if(!new Random().nextBoolean())
                {
                    list.add(0,"(");
                    list.add(4,")");
                }
                else
                {
                    list.add(2,"(");
                    list.add(6,")");
                }
            }
            if(signNumber == 3)
            {
                switch ((int) (Math.random() * 6) + 1) {
                    case 1 -> {
                        list.add(0, "(");
                        list.add(4, ")");
                    }
                    case 2 -> {
                        list.add(2, "(");
                        list.add(6, ")");
                    }
                    case 3 -> {
                        list.add(4, "(");
                        list.add(8, ")");
                    }
                    case 4 -> {
                        list.add(0, "(");
                        list.add(4, ")");
                        list.add(6, "(");
                        list.add(10, ")");
                    }
                    case 5 -> {
                        list.add(0, "(");
                        list.add(6, ")");
                    }
                    case 6 -> {
                        list.add(2, "(");
                        list.add(8, ")");
                    }
                }
            }

        }
        ListIterator<String> it = list.listIterator();
        StringBuilder in = new StringBuilder();
        while (it.hasNext())
        {
            in.append(it.next());
        }
        return in.toString();
    }

    //将问题按顺序写入Exercises.txt文件中
    static void writeEA(String[] exercises) throws Exception
    {
        FileWriter Exercises = new FileWriter("Exercises.txt");
        FileWriter Answers = new FileWriter("Answers.txt");
        for(int i = 0;i < exercises.length;i++)
        {
            Exercises.write((i+1) + "."+ exercises[i] +"="+"\n");
            String result = StackCalculate.getResult(exercises[i]);
            Answers.write((i+1) + "." + result+"\n");
        }
        Answers.close();
        Exercises.close();
    }
}
