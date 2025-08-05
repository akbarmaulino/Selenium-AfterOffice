package seleniumPageFactory.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

    public class cartObject {
        @FindBy(xpath = "//button[contains(text(), 'Checkout')]")
        public By buyNowButton;

}