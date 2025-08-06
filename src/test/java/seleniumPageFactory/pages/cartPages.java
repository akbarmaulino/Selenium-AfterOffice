package seleniumPageFactory.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        // Tunggu sampai tombol benar-benar bisa diklik
        wait.until(ExpectedConditions.elementToBeClickable(cartObject.buttonCheckout));

        // Scroll ke tombol supaya pasti terlihat
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartObject.buttonCheckout);

        // Delay sedikit untuk menghindari race condition animasi (bisa dihapus kalau yakin aman)
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Klik tombol dengan JavaScript (paksa)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartObject.buttonCheckout);
    }

}
