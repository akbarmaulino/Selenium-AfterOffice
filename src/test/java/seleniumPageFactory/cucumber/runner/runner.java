package seleniumPageFactory.cucumber.runner;

import org.testng.annotations.AfterSuite;
import cucumber.helper.GenerateReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/pageFactory",
    glue = "seleniumPageFactory.cucumber.definitions",
    plugin = {"pretty", "json:target/jsonReports/cucumber.json"},
    monochrome = true
)
public class runner extends AbstractTestNGCucumberTests {
    @AfterSuite
    public void after_suite() {
        GenerateReport.generateReport();
    }
}

