package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {
        String path = System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx";

        ExcelUtility xlutil = new ExcelUtility(path);

        int row_count = xlutil.getRowCount("Sheet1");
        int col_count = xlutil.getCellCount("Sheet1",1);

        String[][] logindata = new String[row_count][col_count];

        for(int i=1;i<=row_count;i++){
            for(int j=0;j<col_count;j++){
                logindata[i-1][j] = xlutil.getCellData("Sheet1",i,j);
            }
        }
        return logindata;
    }
}
