package seleniumPageFactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.demoqa.seleniumPageFactory.basePage;
import seleniumPageFactory.objectRepository.cartObject;

public class cartPages extends basePage {
    public cartObject cartObject;

    public cartPages(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.cartObject = new cartObject(webDriver);
    }

    public void clickButtonCheckout() {
        wait.until(d -> cartObject.buttonCheckout.isDisplayed());
        cartObject.buttonCheckout.click();
    }
}
