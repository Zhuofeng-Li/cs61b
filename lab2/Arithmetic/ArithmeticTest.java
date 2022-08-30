package Arithmetic;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArithmeticTest {

    /** Performs a few arbitrary tests to see if the product method is
     * correct */
    @Test
    public void testProduct() {
        /* assertEquals for comparison of ints takes two arguments:
        assertEquals(expected, actual).
        if it is false, then the assertion will be false,
        and this test will fail.
        */
        //常用方法,每个类前面都必须有一个@test昆虫有多个判断相等
        //测试方法必须是非静态的
        assertEquals(30, Arithmetic.product(5, 6));
        assertEquals(-30, Arithmetic.product(5, -6));
        assertEquals(0, Arithmetic.product(0, -6));
    }

    /** Performs a few arbitrary tests to see if the sum method is correct */
    @Test
    public void testSum() {

        assertEquals(11, Arithmetic.sum(5, 6));
        assertEquals(-1, Arithmetic.sum(5, -6));
        assertEquals(-6, Arithmetic.sum(0, -6));
        assertEquals(0, Arithmetic.sum(6, -6));
    }
}
//可以直接点击单元测试错误的地方