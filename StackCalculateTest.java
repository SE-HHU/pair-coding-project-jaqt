package SimpleCalculate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StackCalculateTest {
    private StackCalculate a;
    private StackCalculate b;
    @BeforeEach
    void init()
    {
        a = new StackCalculate();
        b = new StackCalculate(100);
    }
    @Test
    void toPostfix() throws Exception
    {
        assertEquals("20 3 - ",a.toPostfix("20-3"));
        assertEquals("1 2 + ",a.toPostfix("1+2"));
        assertEquals("18 2 × ",a.toPostfix("18×2"));
        assertEquals("1 2 + 3 / ",a.toPostfix("(1+2)/3"));
        assertEquals("1 2 3 / + ",a.toPostfix("1+2/3"));
        assertEquals("1 2 3 4 - × + ",a.toPostfix("1+2×(3-4)"));
        assertEquals("1 4 3 - 5 × + 8 + ",a.toPostfix("1+((4-3)×5)+8"));
    }
    @Test
    void getResult()
    {
        //与toPostfix一致
        assertEquals("17",b.getResult("20-3"));
        assertEquals("3",b.getResult("1+2"));
        assertEquals("36",b.getResult("18×2"));
        assertEquals("1",b.getResult("(1+2)/3"));
        assertEquals("14",b.getResult("1+((4-3)×5)+8"));
        //个别用例测试
        assertEquals("-1",b.getResult("1/0"));//除数为0
        assertEquals("2/3",b.getResult("2/3"));//分数测试
        assertEquals("5/6",b.getResult("1/2+1/3"));//分数测试
        assertEquals("-1",b.getResult("2-3"));//下界测试
        assertEquals("0",b.getResult("3-3"));//下界测试
        assertEquals("-1",b.getResult("100+1"));//上界测试
        assertEquals("100",b.getResult("99+1"));//上界测试
        assertEquals("-1",b.getResult("1+2/3"));//假分数测试
        assertEquals("9",b.getResult("18/2"));//约分测试
//        assertEquals("-1",b.getResult("(14/56*46)-31"));
        assertEquals("0",b.getResult("1-(4-3)-0"));
    }

}