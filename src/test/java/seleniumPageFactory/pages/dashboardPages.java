package seleniumPageFactory.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import seleniumPageFactory.objectRepository.dashboardObject;
import com.demoqa.seleniumPageFactory.basePage;

public class dashboardPages extends basePage {
    public dashboardObject dashboardObject;

    public dashboardPages(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.dashboardObject = new dashboardObject(webDriver);
    }

    public void doSearch(String value) {
        wait.until(d -> dashboardObject.inputSearch.isDisplayed());
        dashboardObject.inputSearch.sendKeys(value);
        dashboardObject.inputSearch.sendKeys(Keys.ENTER);
    }

    public void clickButtonViewProduct() {
        wait.until(d -> dashboardObject.buttonViewProduct.isDisplayed());
        dashboardObject.buttonViewProduct.click();
    }
}
