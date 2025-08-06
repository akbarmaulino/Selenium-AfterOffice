package seleniumPageFactory.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.seleniumPageFactory.baseObject;

public class orderObject extends baseObject {
    @FindBy(xpath = "//*[contains(text(), 'Thankyou for the order')]")
    public WebElement titleThanks;

    public WebElement titleProductDynamic(String productName) {
        return webDriver.findElement(By.xpath("//h2[contains(text(), '" + productName + "')]"));
    }

    public orderObject(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }


}
