package TestProject.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;
    By pageHeading = new By.ByXPath("//span[contains(text(),'Products')]");
    By products = new By.ByXPath("//div[@class='inventory_list']/div");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public String getPageHeading() {
        return driver.findElement(pageHeading).getText();
    }
    public int productCount(){
        return driver.findElements(products).size();
    }
    public WebElement getFilter(){
        return driver.findElement(By.className("select_container"));
    }
    public void applyFilter(String val){
        driver.findElement(By.xpath("//select[@class='product_sort_container']/option[@value='"+val+"']"));
    }
    public String getFilterValue(){
        return driver.findElement(By.className("active_option")).getText();
    }
}
