package seleniumPageFactory.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.seleniumPageFactory.baseObject;

public class loginObject extends baseObject {
    
    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@id='userPassword']")
    public WebElement inputPassword;

    @FindBy(xpath = "//input[@id='login']")
    public WebElement buttonLogin;
    

    public loginObject(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }
}
