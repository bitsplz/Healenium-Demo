package TestProject.Tests;

import TestProject.PageObjects.HomePage;
import TestProject.PageObjects.UserAuth;
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.UtilsConfig;

import java.util.Properties;

public class HomePageTests {
    //<//div[@class='inventory_list']/div[1]>
    //<//select[@class='product_sort_container']/option[1]>
    WebDriver delegate;
    SelfHealingDriver driver;
    UserAuth authAction;
    HomePage homePage;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\mujtaba.afzal\\Documents\\ChromeDriver\\chromedriver.exe");
        delegate = new ChromeDriver();
        //create Self-healing driver
        driver = SelfHealingDriver.create(delegate);
        Properties properties = UtilsConfig.getProperties("SiteData");
        driver.get(properties.getProperty("URL"));
        authAction = new UserAuth(driver);
        authAction.login();
        homePage = new HomePage(driver);
    }
    @Test(description = "Verify the count of products on home page should be 6")
    public void verifyProductCount(){
        System.out.println("Product Count: "+homePage.productCount());
        Assert.assertEquals(homePage.productCount(), 6);
    }
    @Test(description = "Verify filter can be applied")
    public void applyFilter(){
        homePage.getFilter().click();
        homePage.applyFilter("lohi");
        Assert.assertEquals(homePage.getFilterValue(),"Price (low to high)");
    }
    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
