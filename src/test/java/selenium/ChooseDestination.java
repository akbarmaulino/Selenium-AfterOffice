package selenium;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ChooseDestination {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Starting the browser...");
        System.setProperty("webdriver.chrome.driver", "C:/Users/Yantrisnandra Akbar/Downloads/Selenium-AfterOffice/resources/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void searchFlight() throws InterruptedException {
        System.out.println("Searching for flights...");


        WebElement departureCityDropdown = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
        wait.until(d -> departureCityDropdown.isDisplayed());
        departureCityDropdown.click();
        WebElement departureCityDropdownValue = driver.findElement(By.xpath("//a[contains(text(),'AIP')]"));
        wait.until(d -> departureCityDropdownValue.isDisplayed());
        departureCityDropdownValue.click();
    }



    @Test(dependsOnMethods = "searchFlight")
    public void selectDestinationCity() throws InterruptedException {
        WebElement destinationCityDropdownValue = driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//descendant::a[@value='DEL']"));
        wait.until(d -> destinationCityDropdownValue.isDisplayed());
        destinationCityDropdownValue.click();
    }

    @Test(dependsOnMethods = "selectDestinationCity")
    public void selectDate() throws InterruptedException {
        System.out.println("Selecting Date...");
        WebElement datePicker = driver.findElement(By.xpath("//div[@id='ui-datepicker-div']//descendant::td[@data-month='4']//child::a[text()='8']"));
        wait.until(ExpectedConditions.elementToBeClickable(datePicker));
        datePicker.click();
    }

    @Test(dependsOnMethods = "selectDate")
    public void clickButtonSearch() throws InterruptedException {
        System.out.println("Clicking Search Button...");
        WebElement searchButton = driver.findElement(By.id("ctl00_mainContent_btn_FindFlights"));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    @AfterSuite
    public void closeBrowser() {
        System.out.println("Closing the browser...");
        if (driver != null) {
            driver.quit(); 
        }
    }
    
}
