package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{

	@DataProvider(name = "LoginData")
	public String[][] getLoginData() throws IOException
	{

		String path = ".\\testData\\Opencart_LoginData.xlsx";

		ExcelUtils xlutil = new ExcelUtils(path);

		int totalRow = xlutil.getRowCount("Sheet1");
		int totalColumn = xlutil.getCellCount("Sheet1", 1);

		String loginData[][] = new String[totalRow][totalColumn];

		for(int i=1;i<=totalRow;i++)
		{
			for(int j=0;j<totalColumn;j++)
			{
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}

		return loginData;

	}

}
