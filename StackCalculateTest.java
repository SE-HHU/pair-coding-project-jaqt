package SimpleCalculate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StackCalculateTest {

    @Test
    void toPostfix()
    {
        try
        {
            //注意在后缀表达式最后是有一个空格字符的
            Assertions.assertEquals("1 2 + 3 + ",StackCalculate.toPostfix("1+2+3"));
            Assertions.assertEquals("1 2 3 + / 4 + ",StackCalculate.toPostfix("1/(2+3)+4"));
            Assertions.assertEquals("430 50 + 222 + ",StackCalculate.toPostfix("430+50+222"));
            Assertions.assertEquals("430 50 222 + / 7 + ",StackCalculate.toPostfix("430/(50+222)+7"));
            Assertions.assertEquals("22 11 * 23 5 * - ",StackCalculate.toPostfix("22*11-23*5"));
            Assertions.assertEquals("5 3 - 1 - ",StackCalculate.toPostfix("5-3-1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void getResult()
    {
        try {
            Assertions.assertEquals("",StackCalculate.toPostfix("1+2+3"));
            Assertions.assertEquals("",StackCalculate.toPostfix("1/(2+3)+4"));
            Assertions.assertEquals("",StackCalculate.toPostfix("430+50+222"));
            Assertions.assertEquals("",StackCalculate.toPostfix("430/(50+222)+7"));
            Assertions.assertEquals("",StackCalculate.toPostfix("22*11-23*5"));
            Assertions.assertEquals("",StackCalculate.toPostfix("5-3-1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}