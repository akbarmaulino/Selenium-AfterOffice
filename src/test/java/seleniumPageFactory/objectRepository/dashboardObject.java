package seleniumPageFactory.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.demoqa.seleniumPageFactory.baseObject;

public class dashboardObject extends baseObject {
    @FindBy(xpath = "//section[@id='sidebar']//descendant::input[@name='search']")
    public WebElement inputSearch;

    @FindBy(xpath = "//section[@id='products']//descendant::button[contains(text(), 'View')]")
    public WebElement buttonViewProduct;

    public dashboardObject(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

}
