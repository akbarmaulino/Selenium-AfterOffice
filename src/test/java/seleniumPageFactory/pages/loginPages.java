package seleniumPageFactory.pages;

import com.demoqa.seleniumPageFactory.basePage;

public class loginPages extends basePage {

    // Locators
    private String usernameField = "input#username";
    private String passwordField = "input#password";
    private String loginButton = "button#login";

    // Methods
    public void enterUsername(String username) {
        findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        findElement(loginButton).click();
    }
    
}
