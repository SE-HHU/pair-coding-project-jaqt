package SimpleCalculate;

import java.util.Scanner;

public class CalculateEntrance
{
    /*
     * function:       //主入口main
     * Description:    //启动程序进入主方法，与用户交互，调用生成四则运算题目、答案、检查的方法
     * Calls:          //getResult();writeEA();textFile();checkRepeat();
     * Calls By:       //无
     */
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入题目个数：");
        int exesNumber = in.nextInt();
        System.out.println("请输入表达式中出现的最大数值：");
        int numRange = in.nextInt();
        new CalculateSystem(exesNumber,numRange).produceFiles();
        System.out.println("题目、答案、检查均已生成！");
    }
}
