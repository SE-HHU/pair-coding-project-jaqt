package SimpleCalculate;

import java.io.FileWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class CalculateSystem
{
    int exesNumber;
    int numRange;
    public CalculateSystem(int exesNumber,int numRange)
    {
        this.exesNumber =exesNumber;
        this.numRange = numRange;
    }
    /*
     * function:       //produceFiles
     * Description:    //调用生成四则运算题目、答案、检查的方法
     * Calls:          //getResult();writeEA();textFile();checkRepeat();
     * Calls By:       //无
     */
    void produceFiles() throws Exception
    {
        String[] exercises = new String[exesNumber];//生成存放题目的二维数组
        StackCalculate a = new StackCalculate(numRange);//创建StackCalculate的一个实例对象
        for (int i = 0; i < exesNumber; i++)//循环生成题目
        {
            if (a.getResult(exercises[i] = createOneExercise(numRange)).equals("-1") )
            {
                i--;
            }
        }
        writeExercises(exercises);//写入题目
        writeAnswers(exercises);//写入答案
        CheckAnswers b = new CheckAnswers(numRange);
        b.checkCorrectness("Exercises.txt","Answers.txt","Grade.txt");
        b.checkRepeat("Exercises.txt","Answers.txt","Grade.txt");
    }
    /*
     * function:       //createOneExercise
     * Description:    //生成一道题目
     * Calls:          //getSignNumber();getSignNumber();getNumber();hasBrackets()
     * Calls By:       //produceFiles();
     */
    String createOneExercise(int numRange)
    {
        //生成一道不带括号的题目;
        LinkedList<String> list = new LinkedList<>();
        int signNumber = getSignNumber();
        list.add(getNumber(numRange));
        for(int i =0;i<signNumber;i++)
        {
            list.add(getSign());
            list.add(getNumber(numRange));
        }
        //在不带括号的基础上加入括号；
        if (signNumber >= 2&&hasBrackets())
        {
            //在链表不同位置加入括号
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
        //将链表中元素遍历，形成字符串
        ListIterator<String> it = list.listIterator();
        StringBuilder in = new StringBuilder();
        while (it.hasNext())
        {
            in.append(it.next());
        }
        return in.toString();
    }
    /*
     * function:       //writeExercises
     * Description:    //将题目写入Exercises.txt中
     * Calls:          //无
     * Calls By:       //produceFiles();
     */
    void writeExercises(String[] exercises) throws Exception
    {
        FileWriter Exercises = new FileWriter("Exercises.txt");
        for(int i = 0;i < exercises.length;i++)
        {
            Exercises.write((i+1) + ":"+ exercises[i] +"="+"\n");
        }
        Exercises.close();
    }
    /*
     * function:       //writeAnswers
     * Description:    //根据题目生成答案，将答案写入Answers.txt中去
     * Calls:          //StackCalculate.getResult();
     * Calls By:       //produceFiles();
     */
    void writeAnswers(String[] exercises) throws Exception
    {
        FileWriter Answers = new FileWriter("Answers.txt");
        StackCalculate a = new StackCalculate(numRange);//生成StackCalculate的一个实例对象
        for(int i = 0;i < exercises.length;i++)
        {
            String result = a.getResult(exercises[i]);
            Answers.write((i+1) + ":" + result+"\n");
            Answers.close();
        }
    }
    //产生一个范围内的整数
    String getNumber(int numRange)
    {
        return String.valueOf((int)(Math.random() * (numRange)) + 1);
    }
    //随机生成1到3个运算符个数
    int getSignNumber()
    {
        return (int)(Math.random() * 3) + 1;
    }
    //用1，2，3，4来代表"+""-""*""/"
    String getSign()
    {
        return switch ((int) (Math.random() * 4) + 1) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> "";
        };
    }
    //随机确定是否有括号；
    boolean hasBrackets()
    {
        return !new Random().nextBoolean();
    }

}
