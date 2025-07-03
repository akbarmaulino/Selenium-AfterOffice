package testng.demoQA;


import org.testng.annotations.Test;
import testng.data_provider.DataProvide;

public class TestDataProvider {
    @Test(dataProvider = "data-provider", dataProviderClass = DataProvide.class)
    public void testMethod(int id, String data) {
        System.out.println("Data received: " + data + " with ID: " + id);
    }
    
}
