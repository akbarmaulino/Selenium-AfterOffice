package selenium;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class E2EWebsite {
    public WebDriver driver;
    public WebDriverWait wait;
    public static String email = "simanjuntakalbert57@gmail.com";
    public static String password = "XBf@rWNvByn!#K8";
    public static String titleProduct = "ZARA COAT 3";
    public static String priceProduct = "$ 31500";

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Starting the browser...");
        System.setProperty("webdriver.chrome.driver", "C://Users//Akbar//Downloads//Selenium-AfterOffice//resources//chromedriver-win64//chromedriver-win64//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void LoginWebsite() throws InterruptedException {
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='userEmail']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='userPassword']"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login']"));

        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();

        Thread.sleep(2000); // Wait for login to complete

    }

    @Test(dependsOnMethods = "LoginWebsite")
    public void searchAndViewProduct() throws InterruptedException {
        By searchBoxLocator = By.xpath("//section[@id='sidebar']//descendant::input[@name='search']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxLocator));
        WebElement searchBox = driver.findElement(searchBoxLocator);
        searchBox.sendKeys("ZARA COAT 3");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000); // Wait for search results to load
        By viewButtonLocator = By.xpath("//section[@id='products']//descendant::button[contains(text(), 'View')]");
        wait.until(ExpectedConditions.elementToBeClickable(viewButtonLocator));
        WebElement buttonView = driver.findElement(viewButtonLocator);
        buttonView.click();

        Thread.sleep(2000); // Optional, bisa diganti dengan wait sampai detail muncul
    }


    @Test(dependsOnMethods = "searchAndViewProduct")
    public void verifyProductDetails() throws InterruptedException {
        WebElement productName = driver.findElement(By.xpath("//h2[contains(text(), '" + titleProduct + "')]"));
        WebElement productPrice = driver.findElement(By.xpath("//h3[contains(text(), '" + priceProduct + "')]"));

        wait.until(ExpectedConditions.visibilityOf(productName));
        wait.until(ExpectedConditions.visibilityOf(productPrice));

        assert productName.getText().equals(titleProduct) : "Product name does not match!";
        assert productPrice.getText().equals(priceProduct) : "Product price does not match!";

        boolean elementTitleisPresent = wait.until(d -> productName.isDisplayed());
        boolean elementPriceisPresent = wait.until(d -> productPrice.isDisplayed());
    
        Assert.assertTrue(elementPriceisPresent, "Product price element is not present!");
        Assert.assertTrue(elementTitleisPresent, "Product title element is not present!");
        Thread.sleep(2000); 
    }

    @Test(dependsOnMethods = "verifyProductDetails")
    public void addToCartAndCheckout() throws InterruptedException {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(text(), 'Add to Cart')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();

        Thread.sleep(2000);

        // WebElement cartButton = driver.findElement(By.xpath("(//button[@class = 'btn btn-custom'])[3]"));
        WebElement cartButton = driver.findElement(By.xpath("//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]"));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        

        Thread.sleep(2000); 

        // WebElement labelChart = driver.findElement(By.xpath("(//button[@class = 'btn btn-custom'])[3]//descendant::label[contains(text(), 1)]"));
        WebElement labelChart = driver.findElement(By.xpath("//button[contains(text(), 'Cart') and not(contains(text(), 'Add to Cart'))]//child::label"));
        
        wait.until(ExpectedConditions.visibilityOf(labelChart));
        if(labelChart.getText().equals("1")) {
            cartButton.click();
            System.out.println("Product successfully added to cart.");
        } else {
            System.out.println("Product not added to cart.");
        }
        Thread.sleep(2000); 
    }

    @Test(dependsOnMethods = "addToCartAndCheckout")
    public void proceedToCheckout() throws InterruptedException {
        WebElement buyNowButton = driver.findElement(By.xpath("//button[contains(text(), 'Checkout')]"));
        wait.until(ExpectedConditions.visibilityOf(buyNowButton));
        buyNowButton.click();
        Thread.sleep(2000); 
    }

    @Test(dependsOnMethods = "proceedToCheckout")
    public void verifyCheckoutDetails() throws InterruptedException {
        Thread.sleep(2000); // Wait for the checkout page to load
        // WebElement creditCardNumberField = driver.findElement(By.xpath("//div[contains(text(), 'Credit Card Number')]/following-sibling::input"));
        WebElement cvvCodeField = driver.findElement(By.xpath("//div[contains(text(), 'CVV Code ')]/following-sibling::input"));
        WebElement nameOnCardField = driver.findElement(By.xpath("//div[contains(text(), 'Name on Card')]/following-sibling::input"));
        WebElement countryField = driver.findElement(By.xpath("//input[contains(@placeholder,  'Select Country')]"));
        WebElement placeOrderButton = driver.findElement(By.xpath("//a[contains(text(), 'Place Order')]"));
        // wait.until(ExpectedConditions.visibilityOf(creditCardNumberField));
        // creditCardNumberField.sendKeys("1234 5678 9012 3456");
        wait.until(ExpectedConditions.visibilityOf(cvvCodeField));
        cvvCodeField.sendKeys("123");
        wait.until(ExpectedConditions.visibilityOf(nameOnCardField));
        nameOnCardField.sendKeys("Albert Simanjuntak");
        wait.until(ExpectedConditions.visibilityOf(countryField));
        countryField.sendKeys("Indonesia");
        Thread.sleep(2000); // Wait for the dropdown to populate
        countryField.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "verifyCheckoutDetails")
    public void verifyOrderConfirmation() throws InterruptedException {
        WebElement titleThanks = driver.findElement(By.xpath("//*[contains(text(), 'Thankyou for the order')]"));
        wait.until(ExpectedConditions.visibilityOf(titleThanks));
        assert titleThanks.isDisplayed() : "Order confirmation message is not displayed!";
        System.out.println("Order placed successfully!");
        Thread.sleep(2000);
    }




    @AfterSuite
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
