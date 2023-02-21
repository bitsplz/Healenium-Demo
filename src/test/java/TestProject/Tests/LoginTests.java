package TestProject.Tests;

import TestProject.PageObjects.HomePage;
import TestProject.PageObjects.UserAuth;
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.UtilsConfig;

import java.util.Properties;

public class LoginTests {
    WebDriver delegate;
    SelfHealingDriver driver;
    UserAuth authAction;
    @BeforeSuite
    public void setupBeforeSuite(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\mujtaba.afzal\\Documents\\ChromeDriver\\chromedriver.exe");
        delegate = new ChromeDriver();
        //create Self-healing driver
        driver = SelfHealingDriver.create(delegate);
        Properties properties = UtilsConfig.getProperties("SiteData");
        driver.get(properties.getProperty("URL"));
        authAction = new UserAuth(driver);
    }
    @Test(description = "Case 1: Verify user should Log in Successfully", priority = 1)
    public void login(){
        authAction.login();
        HomePage homePage = new HomePage(driver);
        String login_text = homePage.getPageHeading();
        Assert.assertEquals(login_text, "PRODUCTS");
    }
    @Test(description = "Case 2: Verify user should Log out Successfully", priority = 2)
    public void logout() throws InterruptedException {
        authAction.logout();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/");
    }
    @AfterSuite
    public void setupAfterSuite() {
        driver.quit();
    }
}
