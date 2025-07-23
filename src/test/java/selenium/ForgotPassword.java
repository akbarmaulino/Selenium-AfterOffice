package selenium;

import java.time.Duration;
import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
// import org.junit.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ForgotPassword{
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
    public void clickForgotPassword() {
        WebElement linkForgotPassword = driver.findElement(
                By.xpath("//div[@class='forgot-pwd-container']//child::a[contains(text(), 'Forgot your password?')]"));
        linkForgotPassword.click();
         
    }

    @Test(dependsOnMethods = "clickForgotPassword")
    public void fillAllFields()   {
        List<WebElement> fields = driver.findElements(
                By.xpath("//input[@placeholder='Phone Number']//preceding-sibling::input"));
        for (WebElement webElement : fields) {
            if (webElement.getAttribute("placeholder").equals("Name")) {
                webElement.sendKeys("Test");
            }
            if (webElement.getAttribute("placeholder").equals("Email")) {
                webElement.sendKeys("test@test.com");
            }
        }
    }

    @Test(dependsOnMethods = "fillAllFields")
    public void clickButton()   {

        List<WebElement> buttonResetLogins = driver
                .findElements(By.xpath("//div[@class='forgot-pwd-btn-conainer']//descendant::button"));
        buttonResetLogins.get(1).click();
    }

    @Test(dependsOnMethods = "clickButton")
    public void getPasswordAndTryLogin() throws InterruptedException   {
         
        WebElement textInfoMsg = driver
                .findElement(By.xpath("//div[@id='container']//following::p[@class='infoMsg']"));
        System.out.println(textInfoMsg.getText());
        String password = extractPassword(textInfoMsg.getText());

        // back to login
         
        List<WebElement> buttonResetLogins = driver
                .findElements(By.xpath("//div[@class='forgot-pwd-btn-conainer']//descendant::button"));
        buttonResetLogins.get(0).click();

        // login
         
        WebElement inputUsername = driver.findElement(By.cssSelector("input#inputUsername"));
        inputUsername.sendKeys("test@test.com");

         
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='inputPassword']"));
        inputPassword.sendKeys(password);

         
        WebElement buttonSignIn = driver
                .findElement(By.xpath("//button[contains(@class,'signInBtn') and contains(text(), 'Sign In')]"));
        buttonSignIn.click();

        // login-container
         Thread.sleep(2000); // Wait for the login to process
        List<WebElement> loginContainer = driver.findElements(By.xpath("//div[@class='login-container']//child::*"));
        for (WebElement webElement : loginContainer) {
            System.out.println(webElement.getText());
        }
    }

    public String extractPassword(String infoText) {
        String targetString = "";

        // Define the known parts around the target string
        String prefix = "'";
        String suffix = "'";

        // Find the starting and ending indices
        int startIndex = infoText.indexOf(prefix) + prefix.length();
        int endIndex = infoText.indexOf(suffix, startIndex); // Start searching for suffix from startIndex

        // Check if both prefix and suffix were found
        if (startIndex != -1 && endIndex != -1) {
            targetString = infoText.substring(startIndex, endIndex);
        }

        return targetString;
    }


    @AfterSuite
    public void closeBrowser() {
        System.out.println("Closing the browser...");
        if (driver != null) {
            driver.quit(); 
        }
    }

}