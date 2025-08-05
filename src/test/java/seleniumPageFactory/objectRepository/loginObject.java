package seleniumPageFactory.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class loginObject {
    
    @FindBy(xpath = "//input[@id='userEmail']")
    public By emailInput;
    @FindBy(xpath = "//input[@id='userPassword']")
    public By passwordInput;
    @FindBy(xpath = "//input[@id='login']")
    public By loginButton;
}
