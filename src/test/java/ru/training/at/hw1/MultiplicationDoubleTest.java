package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MultiplicationDoubleTest {

    @DataProvider
    public Object[][] dataProviderForMultiplication() {
        return new Object[][]{
                {2.0, 5.1, 10.0},
                {0.0, 0.0, 0.0},
                {-3.2, -8.4, 26.0},
                {1.0, 0.0, 0.0},
                {-10.5, 12.2, -129.0}};
    }

    @Test(dataProvider = "dataProviderForMultiplication",
            groups = {TagsForTests.MULT_AND_DIV_TESTS, TagsForTests.ALL_TESTS})
    public void multiplyDoubleTest(double num1, double num2, double expected) {
        Calculator calculator = new Calculator();
        double actual = calculator.mult(num1, num2);
        Assert.assertEquals(actual, expected);
    }
}

