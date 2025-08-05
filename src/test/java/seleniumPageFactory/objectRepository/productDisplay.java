package seleniumPageFactory.objectRepository;


import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;


public class productDisplay {
    public By productTitleDynamic(String ProductName){
        return By.xpath("//h3[contains(text(),'" + ProductName + "')]");
    }

    public By productPriceDynamic(String ProductPrice){
        return By.xpath("//h3[contains(text(),'" + ProductPrice + "')]");
    }

    @FindBy(xpath = "//button[contains(text(), 'Add to Cart')]")
    public By cartButton;
    @FindBy(xpath = "//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]")
    public By cartButtonWithoutAddToCart;
    @FindBy(xpath = "//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]//child::label")
    public By labelCart;

}
