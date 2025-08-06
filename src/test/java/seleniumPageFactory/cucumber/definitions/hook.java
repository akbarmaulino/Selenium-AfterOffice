package seleniumPageFactory.cucumber.definitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demoqa.constant.Env;


import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hook {
    public static WebDriver driver;
    public static Wait<WebDriver> wait;

    Logger hookLogger = LoggerFactory.getLogger(hook.class);

    @Before
    public void beforeScenario() throws IOException {
         System.out.println("beforeScenario");

        String env = System.getProperty("env") == null ? "staging" : System.getProperty("env");
        System.out.println("env: " + env);
        if (!env.equals("staging") && !env.equals("production")) {
            env = "staging";
        }

        String currentWorkingDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory = " + currentWorkingDirectory);
        FileInputStream fileInputStream = new FileInputStream(
            currentWorkingDirectory + "/src/test/resources/pageFactory/" + env + ".properties");

        System.getProperties().load(fileInputStream);
        System.out.println(System.getProperty("browser"));
        System.out.println(System.getProperty("env"));
        System.out.println(System.getProperty("suiteXml"));

        if (System.getProperty("browser").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + Env.driverPath);
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new"); // Gunakan new headless mode
            options.addArguments("--window-size=1920,1080"); // Viewport besar supaya elemen tidak ketutup
            options.addArguments("--disable-gpu"); // Aman untuk CI server
            options.addArguments("--no-sandbox"); // Aman untuk container
            options.addArguments("--disable-dev-shm-usage"); // Aman untuk container
            hook.driver = new ChromeDriver(options);


        } else {
            //
        }

        // hook.driver = new ChromeDriver();
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