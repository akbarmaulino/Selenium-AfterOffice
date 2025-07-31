package selenium;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ChooseColoumn {
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

    @Test(dependsOnMethods = "selectPassengers")
    public void selectCurrency() throws InterruptedException {
        System.out.println("Selecting currency...");
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='ctl00_mainContent_DropDownListCurrency']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("AED");
        Assert.assertEquals("AED", select.getFirstSelectedOption().getText());
        Thread.sleep(3000);
        select.selectByIndex(1);
        Assert.assertEquals("INR", select.getFirstSelectedOption().getText());
        Thread.sleep(3000);
    }
    
    @Test(dependsOnMethods = "selectTravelDate")
    public void selectPassengers() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Selecting passengers...");
        WebElement dropdownPassengers = driver.findElement(By.xpath("//div[@id='divpaxinfo']"));
        dropdownPassengers.click();
        Thread.sleep(1000);
        WebElement incrementAdult = driver.findElement(By.xpath("//span[@id='hrefIncAdt']"));
        incrementAdult.click();
        Thread.sleep(1000);
        WebElement incrementChild = driver.findElement(By.xpath("//span[@id='hrefIncChd']"));
        incrementChild.click();
        Thread.sleep(1000);
        WebElement incrementInfant = driver.findElement(By.xpath("//span[@id='hrefIncInf']"));
        incrementInfant.click();
        Thread.sleep(1000);
        WebElement btn = driver.findElement(By.xpath("//input[@id='btnclosepaxoption']"));
        btn.click();
        Thread.sleep(1000);
    }


    @Test
    public void selectDepatureCity() {
        System.out.println("Selecting departure city...");
        WebElement formInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_mainContent_ddl_originStation1_CTXT")));
        formInput.click();
        formInput.sendKeys("am");
        WebElement valueDepature = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Ahmedabad')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", valueDepature);
    }

    @Test(dependsOnMethods = "selectDepatureCity")
    public void selectArrivalCity() throws InterruptedException {
        System.out.println("Selecting arrival city...");
        WebElement formInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")));
        formInput.click();
        formInput.sendKeys("go");
        WebElement valueArrival = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Goa')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", valueArrival);
    }


    @Test(dependsOnMethods = "selectArrivalCity")
    public void selectTravelDate() throws InterruptedException {
        WebElement datePicker = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_view_date1']"));
        datePicker.click();
        Thread.sleep(1000);
        WebElement dates = driver.findElement(By.xpath("//table[contains(@class, 'ui-datepicker-calendar')]//descendant::td[@data-month='4' and @data-year='2019']//child::a[text()='10']"));
        dates.click();
        Thread.sleep(1000);
        

    }

    @AfterSuite
    public void closeBrowser() {
        System.out.println("Closing the browser...");
        if (driver != null) {
            driver.quit(); 
        }
    }
    
}
