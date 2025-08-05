package seleniumPageFactory.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class orderObject {
    @FindBy(xpath = "//*[contains(text(), 'Thankyou for the order')]")
    public By titleThanks;


}
