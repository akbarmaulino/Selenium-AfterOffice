package com.demoqa.seleniumPageFactory;

import org.openqa.selenium.WebDriver;

public abstract class baseObject {
    public WebDriver webDriver;

    public baseObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    
}




