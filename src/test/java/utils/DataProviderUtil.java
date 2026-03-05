package utils;
import org.testng.annotations.DataProvider;

public class DataProviderUtil {
    @DataProvider(name = "csvData")
    public static Object[][] csvData() {
        return CSVUtils.getDataProvider("src/test/resources/testdata/loginData.csv");
    }

    @DataProvider(name = "jsonData")
    public Object[][] jsonData() {
        return JsonUtils.getDataProvider("src/test/resources/testdata/loginData.json");
    }

    @DataProvider(name = "excelData")
    public Object[][] excelData() {
        return ExcelUtils.getDataProvider("Login");
    }
}
