package seleniumPageFactory.objectRepository;


import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class dashboardObject {
    @FindBy(xpath = "//section[@id='sidebar']//descendant::input[@name='search']")
    public By searchBox;
    @FindBy(xpath = "//section[@id='products']//descendant::button[contains(text(), 'View')]")
    public By viewButton;

}
