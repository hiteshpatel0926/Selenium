package main.java.framework;

import java.text.*;
import java.util.*;

import org.openqa.selenium.WebElement;

public class Utility {

	public static String getinputvalue(HashMap<String, String> input, String sKey) {

		String sInput = "";
		if (input.get(sKey) == null || input.get(sKey).trim().equals("")) {
			sInput = "";
		} else {
			sInput = input.get(sKey).trim();
		}
		return sInput;

	}

	public static boolean ValidateDateSort() {
		List<WebElement> oTimeStamp = UIDriver.getElements("articletimestamp");
		// data-live-timestamp'
		SimpleDateFormat f = new SimpleDateFormat("MMM dd, yyyy");
		String sDate = "";
		ArrayList<Date> oDates = new ArrayList<>();
		ArrayList<Date> oDates1 = new ArrayList<>();
		for (WebElement oElement : oTimeStamp) {
			String sTimeStamp = oElement.getText();

			if (sTimeStamp.toLowerCase().contains("yesterday") == true) {
				f = new SimpleDateFormat("MMM dd, yyyy");
				sDate = f.format(yesterday());

			}

			else if (sTimeStamp.contains("at") == true) {
				f = new SimpleDateFormat("MMM dd, yyyy");
				sDate = sTimeStamp.split("at")[0].trim();
			}

			else {
				String sAttribute = oElement.getAttribute("data-live-timestamp");
				sDate = sAttribute.split("T")[0];
				f = new SimpleDateFormat("yyyy-MM-dd");
			}

			try {
				oDates1.add(f.parse(sDate));
				oDates.add(f.parse(sDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// oDates.add(sDate);
		}

		Collections.sort(oDates1, Collections.reverseOrder());

		boolean bSort = oDates1.equals(oDates);
		CSVReporter.reportPassFail(bSort, "Validation for Articles Order",
				"Articles should be sorted in descending Date order", "Articles visible in correct order",
				"Article NOT shown in correct order");
		return bSort;

	}

	public static Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static List<String> stringToList(String strValue) {
		String[] strAsArray = strValue.split("\\|");
		List<String> strToList = Arrays.asList(strAsArray);
		return strToList;
	}

	public static LinkedHashMap<String, WebElement> listToMapConversion(List<String> string,
			List<WebElement> webElement) {

		LinkedHashMap<String, WebElement> entryMap = new LinkedHashMap<String, WebElement>();

		Iterator<String> itr1 = string.iterator();
		Iterator<WebElement> itr2 = webElement.iterator();

		while (itr1.hasNext() && itr2.hasNext()) {
			entryMap.put(itr1.next(), itr2.next());
		}
		return entryMap;
	}
	
	public static String randomNumberGenerator() {
		Random random = new Random();


		int x = random.nextInt(99) + 1;
		return Integer.toString(x);
	}
	
	public static Map<String, String> convertTwoListIntoMap(List<String> list1, List<String> list2) {
		Map<String, String> tempMap = new LinkedHashMap<String, String>();
		Iterator<String> i1 = list1.iterator();
		Iterator<String> i2 = list2.iterator();
		while (i1.hasNext() && i2.hasNext()) {
			tempMap.put(i1.next(), i2.next());
		}
		return tempMap;
	}

	public static double calculateTotalForProducts(List<WebElement> priceElements) {
		double sumOfPrices = 0;
		float productPrice;
		for(WebElement priceElement : priceElements) {
			productPrice = Float.valueOf(priceElement.getText().substring(1));
			sumOfPrices += productPrice;
		}
		sumOfPrices = Math.round(sumOfPrices * 100.0) / 100.0;
		return sumOfPrices;
	}
}