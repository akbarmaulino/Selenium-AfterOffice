package seleniumPageFactory.pages;

import com.demoqa.seleniumPageFactory.basePage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import seleniumPageFactory.objectRepository.loginObject;

public class loginPages extends basePage {
    public loginObject loginObject;

    public loginPages(WebDriver webDriver, Wait<WebDriver> wait) {
        super(webDriver, wait);
        this.loginObject = new loginObject(webDriver);
    }

    public void fillEmail(String email) {
        wait.until(d -> loginObject.inputEmail.isDisplayed());
        loginObject.inputEmail.sendKeys(email);
    }

    public void fillPassword(String password) {
        wait.until(d -> loginObject.inputPassword.isDisplayed());
        loginObject.inputPassword.sendKeys(password);
    }

    public void clickLoginButton() {
    wait.until(ExpectedConditions.elementToBeClickable(loginObject.buttonLogin));

        // Scroll ke tombol pakai JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginObject.buttonLogin);

        // Tambahkan delay kecil untuk menghindari animasi/overlay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Klik tombol
        loginObject.buttonLogin.click();
    }
}

