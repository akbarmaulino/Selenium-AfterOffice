package com.demoqa.seleniumPageFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class baseTestSuites {
    public WebDriver driver;
    public Wait<WebDriver> wait;



    public void setUp() {
        System.out.println("Initializing the base page...");
        WebDriverManager.chromedriver().setup(); // otomatis download & set driver

        ChromeOptions options = new ChromeOptions();

        String isHeadless = System.getProperty("headless", "false");
        if (isHeadless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
            System.out.println("Running in headless mode.");
        } else {
            System.out.println("Running in non-headless (UI) mode.");
            // driver = new ChromeDriver(); // ← mode non-headless (UI), masih disimpan
        }

        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options); // inisialisasi WebDriver dengan opsi
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    public void setupURL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
