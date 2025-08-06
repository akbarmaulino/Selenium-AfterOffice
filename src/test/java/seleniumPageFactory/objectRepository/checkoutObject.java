package seleniumPageFactory.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.seleniumPageFactory.baseObject;

public class checkoutObject extends baseObject {

    @FindBy(xpath = "//div[contains(text(), 'CVV Code')]//following-sibling::input")
    public WebElement cvvInput;

    @FindBy(xpath = "//div[contains(text(), 'Name on Card')]//following-sibling::input")
    public WebElement nameOnCard;

    @FindBy(xpath = "//input[contains(@placeholder, 'Select Country')]")
    public WebElement selectCountry;

    @FindBy(xpath = "//a[contains(text(), 'Place Order')]")
    public WebElement buttonPlaceOrder;

    @FindBy(xpath = "//input[contains(@placeholder, 'Select Country')]//following-sibling::section")
    public WebElement sectionRecomendationCountry;

    public checkoutObject(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }


    }
