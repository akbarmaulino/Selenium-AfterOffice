package seleniumPageFactory.cucumber.definitions;

import java.time.Duration;

import io.cucumber.java.en.Then;

public class generalDefinition {
    @Then("Open {string}")
    public void openUrl(String url) {
        hook.driver.get(url);
        hook.driver.manage().window().maximize();
        hook.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Then("Delay for {int} seconds")
    public void doCheckoutFlow(int sec) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(sec));
    }
}
