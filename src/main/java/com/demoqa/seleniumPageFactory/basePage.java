package com.demoqa.seleniumPageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;


public abstract class basePage {
    public WebDriver driver;
    public Wait<WebDriver> wait;
    
    public basePage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }
}
