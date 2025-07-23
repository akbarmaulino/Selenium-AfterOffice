package selenium;

import java.time.Duration;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
// import org.junit.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FailedLogin{
    public WebDriver driver;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Starting the browser...");
        System.setProperty("webdriver.chrome.driver", "C:/Users/Yantrisnandra Akbar/Downloads/Selenium-AfterOffice/resources/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void fillEmail() {
        System.out.println("Running the test...");
        // WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        WebElement inputUsername = driver.findElement(By.cssSelector("input#inputUsername"));
        inputUsername.sendKeys("Test@Test.com");
    }

    @Test (dependsOnMethods = "fillEmail")
    public void fillPassword() {
        System.out.println("Running the test...");
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name ='inputPassword']"));
        inputPassword.sendKeys("Test1234");
    }

    @Test (dependsOnMethods = "fillPassword")
    public void clickSubmit() {
        System.out.println("Running the test...");
        WebElement inputSubmit = driver.findElement(By.xpath("//button[contains(@class,'signInBtn') and text()='Sign In']"));
        inputSubmit.click();
    }

    @Test (dependsOnMethods = "clickSubmit")
    public void verifyAllert() {
        System.out.println("Running the test...");
        WebElement alertMessage = driver.findElement(By.xpath("//form[@class='form']//child::p[@class='error']"));
        String message = alertMessage.getText();
        System.out.println("Alert message: " + message);
        assert message.equals("* Incorrect username or password");
    }

    @AfterSuite
    public void closeBrowser() {
        System.out.println("Closing the browser...");
        if (driver != null) {
            driver.quit(); 
        }
    }

}