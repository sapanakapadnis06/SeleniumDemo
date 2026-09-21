package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

    }


    @Test
    public void LoginTest(){

        LoginPage loginPage = new LoginPage( driver );
        loginPage.login("standard_user","secret_sauce");

    }

    @Test
    public void FailedLoginTest(){
        LoginPage loginPage = new LoginPage( driver );
        loginPage.login("abcd","xyz");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated( By.cssSelector("[data-test ='error']")));
        String textError = errorMessage.getText();

        Assert.assertTrue(textError.contains("Username and password do not match"));

    }

    @Test
    public void dropdownTest(){


        LoginPage loginPage = new LoginPage( driver );
        loginPage.login("standard_user","secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container")));

        Select selectDropdown = new Select(dropdown);
        selectDropdown.selectByVisibleText("Price (low to high)");

    }

    @Test
    public void assertTest(){

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
