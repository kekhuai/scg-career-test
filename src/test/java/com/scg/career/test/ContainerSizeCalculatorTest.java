package com.scg.career.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ContainerSizeCalculatorTest {

    private ContainerSizeCalculator calculator;

    @Before
    public void setUp() {
        calculator = new ContainerSizeCalculator();
    }

    @Test
    public void emptyArrayShouldReturnZero() {
        int waterWithinContainer = calculator.calculateWaterWithinContainer(new int[] {});
        Assertions.assertEquals(0, waterWithinContainer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullArgumentsShouldThrownException() {
        int waterWithinContainer = calculator.calculateWaterWithinContainer(null);
        Assertions.assertEquals(0, waterWithinContainer);
    }

    @Test
    public void container000ShouldReturnZero() {
        int waterWithinContainer = calculator.calculateWaterWithinContainer(new int[] { 0, 0, 0 });
        Assertions.assertEquals(0, waterWithinContainer);
    }

    @Test
    public void container101ShouldReturn1() {
        int waterWithinContainer = calculator.calculateWaterWithinContainer(new int[] { 1, 0, 1 });
        Assertions.assertEquals(1, waterWithinContainer);
    }

    @Test
    public void container1001ShouldReturn2() {
        int waterWithinContainer = calculator.calculateWaterWithinContainer(new int[] { 1, 0, 0, 1 });
        Assertions.assertEquals(2, waterWithinContainer);
    }

    @Test
    public void container2005113ShouldReturn8() {
        int waterWithinContainer = calculator.calculateWaterWithinContainer(new int[] { 2, 0, 0, 5, 1, 1, 3 });
        Assertions.assertEquals(8, waterWithinContainer);
    }

    @Test
    public void container21012ShouldReturn4() {
        int waterWithinContainer = calculator.calculateWaterWithinContainer(new int[] { 2, 1, 0, 1, 2 });
        Assertions.assertEquals(4, waterWithinContainer);
    }

    @Test
    public void container0102101320102ShouldReturn10() {
        int waterWithinContainer = calculator
                .calculateWaterWithinContainer(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 0, 1, 0, 2 });
        Assertions.assertEquals(10, waterWithinContainer);
    }

}
