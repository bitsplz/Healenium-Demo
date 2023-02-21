package TestProject.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By pageHeading = new By.ByXPath("//span[contains(text(),'Products')]");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public String getPageHeading() {
        return driver.findElement(pageHeading).getText();
    }
}
