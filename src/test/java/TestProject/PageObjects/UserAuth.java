package TestProject.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.UtilsConfig;

import java.util.Properties;

public class UserAuth {
    WebDriver driver;
    Properties properties;
    By username = new By.ById("user-name");
    By password = new By.ByName("password");
    By loginButton = new By.ByClassName("submit-button");
    By hamburgerMenu = new By.ByXPath("//button[@id='react-burger-menu-btn']");
    By logoutButton = new By.ByXPath("//a[@id='logout_sidebar_link']");
    public UserAuth(WebDriver driver){
        this.driver=driver;
        this.properties = UtilsConfig.getProperties("SiteData");
    }
    public void login(){
        driver.findElement(username).sendKeys(properties.getProperty("username"));
        driver.findElement(password).sendKeys(properties.getProperty("password"));
        driver.findElement(loginButton).click();
    }
    public void logout() throws InterruptedException {
        driver.findElement(hamburgerMenu).click();
        Thread.sleep(2000);
        driver.findElement(logoutButton).click();
    }
}
