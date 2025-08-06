package seleniumPageFactory.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.seleniumPageFactory.baseObject;

public class cartObject extends baseObject {
    @FindBy(xpath = "//button[contains(text(), 'Checkout')]")
    public WebElement buttonCheckout;

    public cartObject(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
