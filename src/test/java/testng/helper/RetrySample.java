package testng.helper;

import org.testng.ITestResult;
import org.testng.IRetryAnalyzer;

public class RetrySample implements IRetryAnalyzer {
    //Private itu cuman bisa dipake didalam class ini aja
    private int count = 0;
    //static itu artinya bisa dipanggil kalo bentuknya public tanpa harus membuat object dari class ini
    //final itu artinya nilai ini tidak bisa diubah lagi
    private static final int MAX_RETRY_COUNT = 5;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && count < MAX_RETRY_COUNT) {
            count++;
            System.out.println("Retrying test: " + result.getName() + " for the " + count + " time.");
            return true; // Retry the test
        }
        return false; // Do not retry
    }
}
