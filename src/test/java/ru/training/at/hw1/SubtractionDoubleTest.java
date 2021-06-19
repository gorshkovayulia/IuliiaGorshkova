package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubtractionDoubleTest {
    @DataProvider
    public Object[][] dataProviderForSubtraction() {
        return new Object[][]{
                {15.0, 3.1, 12.0},
                {0.0, 0.0, 0.0},
                {-10.0, -5.0, -5.0},
                {1.1, 0.5, 0.6},
                {-100.1, 5.8, -105.9}};
    }

    @Test(dataProvider = "dataProviderForSubtraction",
            groups = {TagsForTests.ADD_AND_SUB_TESTS, TagsForTests.ALL_TESTS})
    public void divideDoubleTest(double num1, double num2, double expected) {
        Calculator calculator = new Calculator();
        double actual = calculator.sub(num1, num2);
        Assert.assertEquals(actual, expected, 0.1);
    }
}
