package seleniumPageFactory.pages;

import com.demoqa.seleniumPageFactory.basePage;
import org.openqa.selenium.WebDriver;
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
        wait.until(d -> loginObject.buttonLogin.isDisplayed());
        loginObject.buttonLogin.click();
    }
}
