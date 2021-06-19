package ru.training.at.hw2.ex2;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise2 {
    private WebDriver webDriver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Iuliia_Gorshkova\\Downloads\\chromedriver_win32\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        if (webDriver != null) {
            webDriver.close();
        }
    }

    @Test
    public void exerciseTwoTest() {

        //  1. Open test site by URL
        webDriver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");

        //  2. Assert browser title
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(webDriver.getTitle(), "Home Page");

        //  3. Perform login
        WebElement webElement = webDriver.findElement(By.id("user-icon"));
        webElement.click();
        webDriver.findElement(By.id("name")).sendKeys("Roman");
        webDriver.findElement(By.id("password")).sendKeys("Jdi1234");
        webElement = webDriver.findElement(By.id("login-button"));
        webElement.click();

        // 4. Assert Username is loggined
        String actualUser = webDriver.findElement(By.id("user-name")).getText();
        String expectedUser = "ROMAN IOVLEV";
        Assert.assertEquals(actualUser, expectedUser);

        //5. Open through the header menu Service -> Different Elements Page
        List<WebElement> headerMenu =
                webDriver.findElements(By.cssSelector("ul.uui-navigation.nav.navbar-nav.m-l8 > li"));
        headerMenu.get(2).click();
        webElement = webDriver.findElement(By.linkText("DIFFERENT ELEMENTS"));
        webElement.click();

        // *8*. Check interface on Different elements page, it contains all needed elements
        List<WebElement> checkBoxes = webDriver.findElements(By.xpath("//input[@type='checkbox']"));
        Assert.assertEquals(checkBoxes.size(), 4);
        List<WebElement> radioButtons = webDriver.findElements(By.xpath("//input[@type='radio']"));
        Assert.assertEquals(radioButtons.size(), 4);
        List<WebElement> dropDown = webDriver.findElements(By.xpath("//select"));
        Assert.assertEquals(dropDown.size(), 1);
        List<WebElement> buttons = webDriver.findElements(By.xpath("//input[@type='button']"));
        Assert.assertEquals(buttons.size(), 1);

        //6. Select checkboxes
        checkBoxes.get(0).click();
        Assert.assertTrue(checkBoxes.get(0).isSelected());
        checkBoxes.get(2).click();
        Assert.assertTrue(checkBoxes.get(2).isSelected());

        //7. Select radio
        radioButtons.get(3).click();
        Assert.assertTrue(radioButtons.get(3).isSelected());

        //8. Select in dropdown
        webDriver.findElement(By.xpath("//option[contains(text(), 'Yellow')]")).click();


        //9. Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        Assert.assertTrue(webDriver
                .findElement(By.xpath("//li[contains(text(), 'Wind: condition changed to true')]")).isDisplayed());
        Assert.assertTrue(webDriver
                .findElement(By.xpath("//li[contains(text(), 'Water: condition changed to true')]")).isDisplayed());

        // Assert that
        // for radiobutton there is a log row and value is corresponded to the status of radiobutton.
        Assert.assertTrue(webDriver
                .findElement(By.xpath("//li[contains(text(), 'metal: value changed to  Selen')]")).isDisplayed());

        // Assert that
        // for dropdown there is a log row and value is corresponded to the selected value.
        Assert.assertTrue(webDriver
                .findElement(By.xpath("//li[contains(text(), 'Colors: value changed to Yellow')]")).isDisplayed());
    }
}
