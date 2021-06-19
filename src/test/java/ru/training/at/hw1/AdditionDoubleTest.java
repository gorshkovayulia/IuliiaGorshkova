package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdditionDoubleTest {
    @DataProvider
    public Object[][] dataProviderForAddition() {
        return new Object[][]{
                {15.5, 3.5, 19.0},
                {0.0, 0.0, 0.0},
                {-10.5, -5.5, -16.0},
                {1.0, 0.0, 1.0},
                {-100.1, 5.5, -94.6}};
    }

    @Test(dataProvider = "dataProviderForAddition",
            groups = {TagsForTests.ADD_AND_SUB_TESTS, TagsForTests.ALL_TESTS})
    public void sumDoubleTest(double num1, double num2, double expected) {
        Calculator calculator = new Calculator();
        double actual = calculator.sum(num1, num2);
        Assert.assertEquals(actual, expected, 0.1);
    }
}
