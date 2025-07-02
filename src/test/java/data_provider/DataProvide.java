package data_provider;

import org.testng.annotations.DataProvider;

public class DataProvide {
    @DataProvider(name = "data-provider") // buat list dataset yang bakalan digunakan
    // DataProvider ini akan mengembalikan array dua dimensi
    public Object[][] dataProviderMethod() {
        return new Object[][] {
            { 1234, "Check" },
            { 4567, "Uncheck" },
            { 8901, "Check" }
        };
    }
}
