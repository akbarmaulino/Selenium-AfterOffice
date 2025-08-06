package seleniumPageFactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import seleniumPageFactory.objectRepository.orderObject;
import com.demoqa.seleniumPageFactory.basePage;

public class orderPages extends basePage {
    public orderObject orderObject;

    public orderPages(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.orderObject = new orderObject(webDriver);
    }
}
