package seleniumPageFactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import com.demoqa.seleniumPageFactory.basePage;
import seleniumPageFactory.objectRepository.checkoutObject;

public class checkoutPages extends basePage {

    public checkoutObject checkoutObject;

    public checkoutPages(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.checkoutObject = new checkoutObject(webDriver);
    }

    public void enterCVV() {
        wait.until(d -> checkoutObject.cvvInput.isDisplayed());
        checkoutObject.cvvInput.sendKeys("123");
    }

    public void enterNameCard() {
        wait.until(d -> checkoutObject.nameOnCard.isDisplayed());
        checkoutObject.nameOnCard.sendKeys("andre");
    }

    public void enterCountry() {
        wait.until(d -> checkoutObject.selectCountry.isDisplayed());
        checkoutObject.selectCountry.sendKeys("indonesia");
    }

    public void clickRecomendationCountry() {
        wait.until(d -> checkoutObject.sectionRecomendationCountry.isDisplayed());
        checkoutObject.sectionRecomendationCountry.click();
    }

    public void clickPlaceOrder() {
        wait.until(d -> checkoutObject.buttonPlaceOrder.isDisplayed());
        checkoutObject.buttonPlaceOrder.click();
    }
}
