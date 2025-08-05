package seleniumPageFactory.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class checkoutObject {

    @FindBy(xpath = "//div[contains(text(), 'CVV Code ')]/following-sibling::input")
    public By cvvCodeField;
    @FindBy(xpath = "//div[contains(text(), 'Name on Card')]/following-sibling::input")
    public By nameOnCardField;
    @FindBy(xpath = "//input[contains(@placeholder,  'Select Country')]")
    public By countryField;
    @FindBy(xpath = "//a[contains(text(), 'Place Order')]")
    public By placeOrderButton;


    }
