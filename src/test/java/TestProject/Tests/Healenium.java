package TestProject.Tests;

import TestProject.PageObjects.HealeniumObjects;
import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.annotation.DisableHealing;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.UtilsConfig;

import java.util.Properties;

public class Healenium {
    WebDriver delegate;
    SelfHealingDriver driver;
    Properties properties;
    HealeniumObjects pageObjects;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\mujtaba.afzal\\Documents\\ChromeDriver\\chromedriver.exe");
        delegate = new ChromeDriver();
        //create Self-healing driver
        driver = SelfHealingDriver.create(delegate);
        properties = UtilsConfig.getProperties("SiteData");
        driver.get(properties.getProperty("HLM_URL"));
        pageObjects = new HealeniumObjects(driver);
    }
    @BeforeMethod
    public void beforeEach(){
        driver.get(properties.getProperty("HLM_URL"));
    }
    @Test(description = "Change in id")
    public void testChangeId(){
        if(pageObjects.getByID()!=null){
            System.out.println("Case 1: Element Found");
        }
        pageObjects.getSubmitButton().click();
        if(pageObjects.getByID()!=null){
            System.out.println("Case 1: Element Found After Healing");
        }
    }
    @Test(description = "Change in ClassName")
    public void testChangeClassName(){
        if(pageObjects.getByClassName()!=null){
            System.out.println("Case 2: Element Found");
        }
        pageObjects.getSubmitButton().click();
        if(pageObjects.getByClassName()!=null){
            System.out.println("Case 2: Element Found After Healing");
        }
    }

    @Test(description = "Change in Name")
    public void testChangeName(){
        if(pageObjects.getByName()!=null){
            System.out.println("Case 3: Element Found");
        }
        pageObjects.getSubmitButton().click();
        if(pageObjects.getByName()!=null){
            System.out.println("Case 3: Element Found After Healing");
        }
    }

    @Test(description = "Change in Link Text")
    public void testChangeLinkText(){
        if(pageObjects.getByLinkText()!=null){
            System.out.println("Case 4: Element Found");
        }
        pageObjects.getSubmitButton().click();
        if(pageObjects.getByLinkText()!=null){
            System.out.println("Case 4: Element Found After Healing");
        }
    }

    @Test(description = "Change in Tag Name")
    public void testChangeTagName(){
        if(pageObjects.getByTagName()!=null){
            System.out.println("Case 5: Element Found");
        }
        pageObjects.getSubmitButton().click();
        if(pageObjects.getByTagName()!=null){
            System.out.println("Case 5: Element Found After Healing");
        }
    }

    @Test(description = "Change in xpath")
    public void testChangeXpath(){
        if(pageObjects.getByXpath("//*[@id='change:name']")!=null){
            System.out.println("Case 6: Element Found");
        }
        pageObjects.getSubmitButton().click();
        if(pageObjects.getByXpath("//*[@id='change:name']")!=null){
            System.out.println("Case 6: Element Found After Healing");
        }
    }

    @Test(description = "XPath Contains")
    public void testXpathContains(){
        if(pageObjects.getByXpath("//input[contains(@class, 'test')]")!=null){
            System.out.println("Case 7: Element Found");
        }
        pageObjects.getSubmitButton().click();
        if(pageObjects.getByXpath("//input[contains(@class, 'test')]")!=null){
            System.out.println("Case 7: Element Found After Healing");
        }
    }

    @Test(description = "XPath Ancestor::")
    public void testXPathAncestor(){
        if(pageObjects.getByXpath("(//*[starts-with(@class, 'test')]/ancestor::div[@class='healenium-form validate-form']//input)[1]")!=null){
            System.out.println("Case 8: Element Found");
        }
        pageObjects.getSubmitButton().click();
        if(pageObjects.getByXpath("(//*[starts-with(@class, 'test')]/ancestor::div[@class='healenium-form validate-form']//input)[1]")!=null){
            System.out.println("Case 8: Element Found After Healing");
        }
    }

    @Test(description = "XPath Ancestor::")
    //@DisableHealing
    public void testCheckBoxes(){
        if(pageObjects.clickFirstCheckbox()){
            Assert.assertFalse(pageObjects.getByCssSelectorCheckbox(1).isSelected(),"Case 9: Element Found");
        }
        pageObjects.getSubmitButtonCheckbox().click();
        if(pageObjects.clickFirstCheckbox()){
            Assert.assertTrue(pageObjects.getByCssSelectorCheckbox(1).isSelected(), "Case 9: Element Found After Healing");
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
