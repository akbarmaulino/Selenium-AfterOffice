package seleniumPageFactory.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.demoqa.seleniumPageFactory.basePage;

import seleniumPageFactory.objectRepository.productDisplay;

public class productDisplayPage extends basePage {
    public productDisplay productDisplayObject;

    public productDisplayPage(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.productDisplayObject = new productDisplay(webDriver);
    }

    public void verifyDataProduct(String productName) {
        Boolean elementIsPresent = wait.until(d -> productDisplayObject.titleProductDynamic(productName).isDisplayed());
        Assert.assertTrue(elementIsPresent, "element title product not present");
    }

    public void clickATCButton() throws InterruptedException {
        wait.until(d -> productDisplayObject.buttonAddToCart.isDisplayed());
        productDisplayObject.buttonAddToCart.click();

        Thread.sleep(3000);

        wait.until(d -> productDisplayObject.labelCart.isDisplayed());
        if (productDisplayObject.labelCart.getText().equals("1")) {
            wait.until(d -> productDisplayObject.buttonOpenCartPage.isDisplayed());
            productDisplayObject.buttonOpenCartPage.click();
        } else {
            System.out.println("labelCart => " + productDisplayObject.labelCart);
            Assert.assertTrue(false, "cart label not increment");
        }
    }
}
