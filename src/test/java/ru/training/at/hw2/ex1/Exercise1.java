package ru.training.at.hw2.ex1;

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

public class Exercise1 {
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
    public void exerciseOneTest() {

        //  1. Open test site by URL
        webDriver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");

        //  2. Assert Browser title
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
        Assert.assertEquals(actualUser, "ROMAN IOVLEV");

        // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerMenuItems = webDriver.findElements(By.cssSelector(".uui-navigation.nav > li"));
        Assert.assertEquals(headerMenuItems.size(), 4);
        Assert.assertEquals(headerMenuItems.get(0).getText(), "HOME");
        Assert.assertEquals(headerMenuItems.get(1).getText(), "CONTACT FORM");
        Assert.assertEquals(headerMenuItems.get(2).getText(), "SERVICE");
        Assert.assertEquals(headerMenuItems.get(3).getText(), "METALS & COLORS");

        //6. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> benefitIcons = webDriver.findElements(By.cssSelector(".icons-benefit"));
        Assert.assertEquals(benefitIcons.size(), 4);

        //7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> benefitTexts = webDriver.findElements(By.cssSelector(".benefit-txt"));
        Assert.assertEquals(benefitTexts.size(), 4);
        Assert.assertEquals(benefitTexts.get(0).getText(),
                "To include good practices\n" + "and ideas from successful\n" + "EPAM project");
        Assert.assertEquals(benefitTexts.get(1).getText(), "To be flexible and\n" + "customizable");
        Assert.assertEquals(benefitTexts.get(2).getText(), "To be multiplatform");
        Assert.assertEquals(benefitTexts.get(3).getText(),
                "Already have good base\n" + "(about 20 internal and\n"
                        + "some external projects),\n" + "wish to get more…");

        //8. Assert that there is the iframe with “Frame Button” exist
        Assert.assertTrue(webDriver.findElement(By.id("frame")).isDisplayed());

        //9. Switch to the iframe and check that there is “Frame Button” in the iframe
        webDriver.switchTo().frame("frame");
        Assert.assertTrue(webDriver.findElement(By.id("frame-button")).isDisplayed());

        //10. Switch to original window back
        webDriver.switchTo().defaultContent();

        //11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        String [] expectedMenuItems = {"Home", "Contact form", "Service", "Metals & Colors", "Elements packs"};
        int expectedMenuSize = 5;

        Assert.assertTrue(webDriver.findElement(By.name("navigation-sidebar")).isDisplayed());
        List<WebElement> leftMenuItems = webDriver.findElements(By.xpath("//*[@class='sidebar-menu left']/li"));

        Assert.assertEquals(leftMenuItems.size(), expectedMenuSize);
        for (int i = 0; i < leftMenuItems.size(); i++) {
            Assert.assertEquals(leftMenuItems.get(i).getText(), expectedMenuItems[i]);
        }
    }
}
