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
    void checkCorrectness(String path1,String path2,String path3) throws Exception
    {
        FileWriter file3;
        File file1 =new File(path1);
        File file2 =new File(path2);
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
        file3 = new FileWriter(path3, true);
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
    void checkRepeat (String path1,String path2,String path3) throws Exception
    {
        FileWriter file3;
        file3 = new FileWriter(path3, true);
        File file1 =new File(path1);
        File file2 =new File(path2);
        InputStreamReader read1 = new InputStreamReader(new FileInputStream(file1));//题目文件
        BufferedReader f1=new BufferedReader(read1);
        InputStreamReader read2 = new InputStreamReader(new FileInputStream(file2));//答案文件
        BufferedReader f2=new BufferedReader(read2);


        String line1;
        String line2;

        int m=1;int p=1;
        int i=0;
        int repeat=0;

        String [][] line3 = new String [10000][];//存放答案
        String [][] line4 = new String [10000][];//存放题目文件后缀表达式的各个元素
        String [] line5 = new String [10000];//存放所有题目，用于打印重复题目
        int [] ll=new int[10000];//存放重复题目的题号
        StackCalculate a = new StackCalculate(numRange);
        while((line1=f1.readLine())!=null) {
            line4[p]=a.toPostfix(line1).split(" ");
            line5[p]=line1;
            p++;
        }

        while ((line2=f2.readLine())!=null){
            line3[m]=a.toPostfix(line2).split(" ");
            m++;
        }

        for(int n=1;n<m;n++)
        {
            for(int k=n+1;k<m;k++)
            {

                if(line3[k][1].equals(line3[n][1]))//第n题和第k题答案相同时  判断后缀表达式的元素是否相同；
                {
                    if(line4[k].length==line4[n].length)
                    {
                        int u=0;
                        // System.out.println("u的值为"+u);
                        for(int q=1;q<line4[n].length;q++)
                        {

                            for(int c=1;c<line4[k].length;c++)
                            {
                                if(line4[n][q].equals(line4[k][c])) {
                                    u++;
                                    break;
                                }
                            }
                        }
                        if(u==line4[n].length-1)
                        {
                            repeat++;
                            ll[i]=n;
                            ll[i+1]=k;
                            i+=2;
                        }
                    }
                }
            }
        }
        file3.write("repeat:"+repeat);
        file3.write("\n");
        file3.write("RepeatDetail:");
        file3.write("\n");
        int ee=0;//ee为重复题目的题号-1
        for(int e=0;e<repeat;e++)
        {
            file3.write("("+(e+1)+")"+" "+line5[ll[ee]]+" Repeat"+" "+line5[ll[ee+1]]);
            ee+=2;
            file3.write("\n");
        }
        file3.flush();
        file3.close();
        read1.close();
        read2.close();
    }

}
