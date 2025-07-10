package cucumber.runner;

import org.testng.annotations.AfterSuite;

import cucumber.helper.GenerateReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber/features/Employee.feature",
    glue = "cucumber.definitions",
    plugin = {
        "pretty",
        "json:target/jsonReports/cucumber.json",  // HARUS ini
        "html:target/cucumber-reports.html"
    },
    monochrome = true
)

public class cucumberRunner extends AbstractTestNGCucumberTests {
    
    @AfterSuite
    public void afterSuite() {
        GenerateReport.generateReport();
    }

     
}
