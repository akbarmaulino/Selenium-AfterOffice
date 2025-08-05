package seleniumPageFactory.testSuites;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.demoqa.seleniumPageFactory.baseTestSuites;

public class E2ECheckout extends baseTestSuites {

    @BeforeSuite
    public void setUp() {
        super.setUp();
    }
    
    @AfterSuite
    public void tearDown() {
        super.tearDown();
    }
}
