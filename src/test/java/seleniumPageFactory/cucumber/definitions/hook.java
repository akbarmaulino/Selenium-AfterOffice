package seleniumPageFactory.cucumber.definitions;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoqa.constant.Env;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hook {
    public static WebDriver driver;
    public static Wait<WebDriver> wait;

    @Before
    public void beforeScenario() throws IOException {
        System.out.println("beforeScenario");
        Env config = new Env();

        System.setProperty("webdriver.chrome.driver", config.driverPath);


        hook.driver = new ChromeDriver();
        hook.wait = new WebDriverWait(hook.driver, Duration.ofSeconds(5));

        // throw new Error("this sample of error");
    }

    @After
    public void afterScenario() {
        System.out.println("afterScenario");
        if (hook.driver != null) {
            hook.driver.quit();
        }
    }
}