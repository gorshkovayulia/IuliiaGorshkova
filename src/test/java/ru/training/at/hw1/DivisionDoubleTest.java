package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DivisionDoubleTest {
    @DataProvider
    public Object[][] dataProviderForDivision() {
        return new Object[][]{
                {15.2, 3.3, 4.6},
                {-10.9, -5.4, 2.0},
                {-100, 5.9, -16.9}};
    }

    @Test(dataProvider = "dataProviderForDivision",
            groups = {TagsForTests.MULT_AND_DIV_TESTS, TagsForTests.ALL_TESTS})
    public void divideDoubleTest(double num1, double num2, double expected) {
        Calculator calculator = new Calculator();
        double actual = calculator.div(num1, num2);
        Assert.assertEquals(actual, expected, 0.1);
    }
}
