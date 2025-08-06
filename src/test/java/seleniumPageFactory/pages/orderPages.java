package seleniumPageFactory.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import seleniumPageFactory.objectRepository.orderObject;
import com.demoqa.seleniumPageFactory.basePage;

public class orderPages extends basePage {
    public orderObject orderObject;

    public orderPages(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.orderObject = new orderObject(webDriver);
    }

    public void verifyOrderCreated(String productName) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(2));

        // Boolean elementIsPresentProductName = wait.until(d -> orderObject.titleProductDynamic(productName).isDisplayed());
        // Assert.assertTrue(elementIsPresentProductName, "element title product not present");

        Boolean elementIsPresentThanks = wait.until(d -> orderObject.titleThanks.isDisplayed());
        Assert.assertTrue(elementIsPresentThanks, "element thanks not present");
    }
}
