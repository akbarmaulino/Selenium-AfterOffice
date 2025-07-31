package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumAdvance {
    public WebDriver driver;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Starting the browser...");
        System.setProperty("webdriver.chrome.driver", "C:/Users/Yantrisnandra Akbar/Downloads/Selenium-AfterOffice/resources/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void selectCurrency() throws InterruptedException {
        System.out.println("Selecting currency...");
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='ctl00_mainContent_DropDownListCurrency']"));
        Select select = new Select(dropdown);
        // System.out.println("Selected currency Dropdown Selected Size: " + select.getAllSelectedOptions().size());
        // for(WebElement option : select.getAllSelectedOptions()) {
        //     System.out.println("Currency Dropdown Selected Value: " + option.getText());
        // }
        // for (WebElement option : select.getOptions()) {
        //     System.out.println("Currency Dropdown All Value: " + option.getText());
        // }
        select.selectByVisibleText("AED");
        Assert.assertEquals("AED", select.getFirstSelectedOption().getText());
        Thread.sleep(3000);
        select.selectByIndex(1);
        Assert.assertEquals("INR", select.getFirstSelectedOption().getText());
        Thread.sleep(3000);
    }
    
    @Test
    public void selectPassengers() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Selecting passengers...");
        WebElement dropdownPassengers = driver.findElement(By.xpath("//div[@id='divpaxinfo']"));
        dropdownPassengers.click();
        Thread.sleep(2000);
        List<WebElement> dropdownOptions = driver.findElements(By.xpath("//div[@id='divpaxOptions']//child::div[contains(@class, 'ad-row')]"));
        for(WebElement optionPassenger : dropdownOptions) {
            String idOption = optionPassenger.getAttribute("id");
            if(idOption == null || idOption.isEmpty()) {
                List<WebElement> optionDescendant = driver.findElements(By.xpath("//div[@id='" + idOption + "']//descendant::span[contains(@class, 'pax-add')]"));
                for (int i = 0; i < optionDescendant.size(); i++) {
                    System.out.println(idOption + " ==== " + i + " ---- " + optionDescendant.get(i).getText());
                }
                optionDescendant.get(1).click();
                break;
            } else {
                WebElement btnClose = driver.findElement(By.xpath("//input[@id='btnclosepaxoption']"));
                btnClose.click();
            }
        }

        // System.out.println("Selecting passengers...");

        // // Klik dropdown penumpang
        // WebElement dropdownPassengers = driver.findElement(By.xpath("//div[@id='divpaxinfo']")
        // );
        // dropdownPassengers.click();
        // Thread.sleep(2000);

        // // Ambil semua opsi penumpang
        // List<WebElement> optionPassengers = driver.findElements(
        //     By.xpath("//div[@id='divpaxOptions']//div[contains(@class, 'ad-row')]")
        // );

        // for (WebElement optionPassenger : optionPassengers) {
        //     String idOption = optionPassenger.getAttribute("id");

        //     if (idOption != null && !idOption.isEmpty()) {
        //         // Cari tombol "+" dalam opsi tersebut
        //         List<WebElement> optionDescendant = driver.findElements(
        //             By.xpath("//div[@id='" + idOption + "']//span[contains(@class, 'pax-add')]")
        //         );

        //         // Debug: tampilkan isi tiap tombol
        //         for (int i = 0; i < optionDescendant.size(); i++) {
        //             System.out.println(idOption + " ==== " + i + " ---- " + optionDescendant.get(i).getText());
        //         }

        //         Thread.sleep(1000);

        //         // Klik tombol tambah penumpang ke-2 (index 1)
        //         if (optionDescendant.size() > 1) {
        //             optionDescendant.get(1).click();
        //         }
        //     } else {
        //         // Jika tidak ada ID, tutup opsi
        //         WebElement btnClose = driver.findElement(By.xpath("//input[@id='btnclosepaxoption']"));
        //          btnClose.click();
        //     }
        // }

        // // ==== INPUT ORIGIN (ASAL) ====
        // WebElement fromInput = driver.findElement(
        //     By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']")
        // );
        // fromInput.click();
        // fromInput.sendKeys("am");

        // WebElement selectedValue = driver.findElement(
        //     By.xpath("//div[contains(@class, 'dropdownDiv')]//a[contains(text(), 'AIP')]")
        // );
        // selectedValue.click(); // Tambahkan klik jika memang ingin memilihnya

        // // ==== PILIH TANGGAL KALENDER ====
        // Thread.sleep(2000);

        // WebElement fromInputCalendar = driver.findElement(
        //     By.xpath("//input[@id='ctl00_mainContent_view_date1']")
        // );
        // fromInputCalendar.click();

        // List<WebElement> selectedValueCalendar = driver.findElements(
        //     By.xpath("//table[contains(@class, 'ui-datepicker-calendar')]//td[@data-month='4' and @data-year='2019']//a[text()='5']")
        // );

        // System.out.println("Tanggal ditemukan: " + selectedValueCalendar.size());

        // // Klik tanggal jika tersedia
        // if (!selectedValueCalendar.isEmpty()) {
        //     selectedValueCalendar.get(0).click();
        // }
    }


    @AfterSuite
    public void closeBrowser() {
        System.out.println("Closing the browser...");
        if (driver != null) {
            driver.quit(); 
        }
    }
    
}
