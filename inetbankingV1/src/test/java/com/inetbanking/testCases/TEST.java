package com.inetbanking.testCases;

import java.io.IOException;

import com.inetbanking.utilities.XLUtils;

public class TEST {

	public static void main(String[] args) throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData2.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				
				System.out.println(logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j));
			}

					
		}

	}
}
