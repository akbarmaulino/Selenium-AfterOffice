package testng.demoQA;

import org.testng.Assert;
import org.testng.annotations.Test;
// import helper.RetrySample;
public class RetryTest {
    public static int count = 0;

    @Test(retryAnalyzer = testng.helper.RetrySample.class)
    public void sampleTest() {
        // Simulate a failure for demonstration purposes
        // if (Math.random() < 0.5) {
        //     throw new RuntimeException("Simulated failure");
        // }
        // Assert.assertTrue(false, "This test is expected to fail");
        count++;
        System.out.println("Test executed " + count + " times");
        Assert.assertTrue(count == 4, "Test should not be retried more than 2 times");
    }
    
}
