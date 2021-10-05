package SimpleCalculate;

import java.io.*;

public class CheckAnswers
{
    int numRange;
    public CheckAnswers(int numRange)
    {
        this.numRange = numRange;
    }
    /*
     * function:       //checkCorrectness
     * Description:    //检查答案结果是否正确并写入grades。txt文件
     * Calls:          //无
     * Calls By:       //produceFile()
     */
    void checkCorrectness() throws Exception
    {
        FileWriter file3;
        File file1 =new File("Exercises.txt");
        File file2 =new File("Answers.txt");
        InputStreamReader read1 = new InputStreamReader(new FileInputStream(file1));
        BufferedReader f1=new BufferedReader(read1);
        InputStreamReader read2 = new InputStreamReader(new FileInputStream(file2));
        BufferedReader f2=new BufferedReader(read2);
        int correct=0,wrong=0;
        int i=1;
        int [ ] t=new int [1000];
        int [ ] tt=new int [1000];

        String line;
        String line1;
        StackCalculate a = new StackCalculate(numRange);
        while ((line = f1.readLine())!=null)
        {
            String line2=i+":"+a.getResult(line);
            line1=f2.readLine();
            if(line1.equals(line2))
            {
                t[correct]=i;
                correct++;
            }
            else
            {
                tt[wrong]=i;
                wrong++;
            }
            i++;
        }
        file3 = new FileWriter("Grade.txt", true);
        file3.write("Correct:"+correct+"(");
        for (int p=0;p<correct;p++)
        {
            if(p<correct-1)
            {
                file3.write(t[p]+",");
            }
            else
            {
                file3.write(t[correct-1]+"");
            }
        }
        file3.write(")");

        file3.write("\n");

        file3.write("Wrong:"+wrong+"(");
        for (int p=0;p<wrong;p++)
        {
            if(p<wrong-1)
                file3.write(tt[p]+",");
            else file3.write(tt[p]+"");
        }
        file3.write(")");
        file3.flush();
        file3.close();
        read1.close();
        read2.close();
    }
    /*
     * function:       //checkRepeat
     * Description:    //检查题目是否有重复并写入grades。txt文件
     * Calls:          //无
     * Calls By:       //produceFile()
     */
    void checkRepeat() throws Exception
    {
        FileWriter file3;
        file3 = new FileWriter("Grade.txt", true);
        File file1 =new File("Exercises.txt");//读取题目文件
        File file2 =new File("Answers.txt");//读取答案文件
        InputStreamReader read1 = new InputStreamReader(new FileInputStream(file1));//题目文件
        BufferedReader f1=new BufferedReader(read1);
        InputStreamReader read2 = new InputStreamReader(new FileInputStream(file2));//答案文件
        BufferedReader f2=new BufferedReader(read2);

        int m=1;int p=1;// m,p分别为答案和题目的题号
        int i=0;//i为ll[]参数，看有多少重复的
        int repeat=0;

        String [][] line3 = new String [10000][];//存放答案
        String [][] line4 = new String [10000][];//存放题目文件后缀表达式的各个元素
        String [] line5 = new String [10000];//存放所有题目，用于打印重复题目
        int [] ll=new int[10000];//存放重复题目的题号
        String line1;
        String line2;
        StackCalculate a = new StackCalculate(numRange);
        while((line1=f1.readLine())!=null) {   //line1读取题目文件
            line4[p]=a.toPostfix(line1).split(" ");//将题目数字和符号分开并以后缀表达式的格式一个一个存入line4[p]中
            line5[p]=line1;//将原题目存入line5[p]中，一旦重复，便于打印
            p++;
        }

        while ((line2=f2.readLine())!=null){
            line3[m]=a.toPostfix(line2).split(" ");//存放答案，line3[m][0]为题号，line3[m][1]为答案 如果为分数就是分子位
            m++;
        }

        for(int n=1;n<m;n++) //循环嵌套判断答案是否相等
        {
            for(int k=n+1;k<m;k++)
            {

                if(line3[k][1].equals(line3[n][1]))//第n题和第k题答案或者分子位相同时  判断后缀表达式的元素是否相同；
                {
                    if(line4[k].length==line4[n].length)//判断后缀表达式的长度是否一样，不一样就省去比较，一样在比较
                    {
                        int u=0;
                        for(int q=1;q<line4[n].length;q++)
                        {

                            for(int c=1;c<line4[k].length;c++)
                            {
                                if(line4[n][q].equals(line4[k][c])) {//循环判断是否一个后缀表达式与另外一个后缀表达式有相同元素，如果有，就直接跳出再循环
                                    u++;
                                    break;
                                }
                            }
                        }
                        if(u==line4[n].length-1)//
                        {
                            repeat++;
                            ll[i]=n;//用ll[i]存放题目编号,便于用line5[]打印题目
                            ll[i+1]=k;
                            i+=2;//一次存放两个，存放完i+=2
                        }
                    }
                }
            }
        }
        file3.write("repeat:"+repeat);
        file3.write("\n");
        file3.write("RepeatDetail:");
        file3.write("\n");
        int re=0;//re为重复题目的题号-1
        for(int e=0;e<repeat;e++)
        {
            file3.write("("+(e+1)+")"+" "+line5[ll[re]]+" Repeat"+" "+line5[ll[re+1]]);
            re+=2;
            file3.write("\n");
        }
        file3.flush();
        file3.close();
        read1.close();
        read2.close();
    }

}
