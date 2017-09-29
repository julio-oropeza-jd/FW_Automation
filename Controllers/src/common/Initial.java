package common;

import enums.XMlEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

/*
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/

public class Initial {
	WebDriver driver;

	public Initial() {
	}

	public WebDriver getDriver(String browser) {
		try {
			if (browser.equals("Chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.setBinary(getValueFromConfig(XMlEnum.GoogleExe));
				System.setProperty("webdriver.chrome.driver", getValueFromConfig(XMlEnum.GoogleBinary));

				driver = new ChromeDriver(options);

			}else if (browser.equals("Firefox")) {
				//System.setProperty("webdriver.firefox.marionette", getValueFromConfig(XMlEnum.FireFoxBinary));
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				FirefoxOptions options = new FirefoxOptions();

				options.addPreference("log", "{level: trace}");

				capabilities.setCapability("marionette", true);
				capabilities.setCapability("moz:firefoxOptions", options);

				System.setProperty("webdriver.gecko.driver", getValueFromConfig(XMlEnum.FireFoxBinary));

				driver = new FirefoxDriver(capabilities);
				//driver = new FirefoxDriver();

			}else if (browser.equals("Edge")) {
				System.setProperty("webdriver.edge.driver", getValueFromConfig(XMlEnum.IEBinary));

				driver = new EdgeDriver();
			}

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				return driver;

		} catch (Exception e) {

			return null; // need to change for a exception

		}

	}


	public String getValueFromConfig(XMlEnum Value) throws Exception {
		File fXmlFile = new File("./Controllers/src/resource/Config.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("config");
		Node nNode = nList.item(0);
		Element eElement = (Element) nNode;
		String content = eElement.getElementsByTagName(Value.toString()).item(0).getTextContent();
		if (content.isEmpty())
			throw new Exception("Cannot find the value in the resource file Config");

		return content;

	}







/*
	public Object[] readParameterFile(String userFlag) throws IOException {
		// userFlag = value to look for in the first column into the xlsx file

		Object[] tempHeader = null;
		try {
			tempHeader = readExcel(getValueFromConfig(XMlEnum.ParameterFile), "Profiles", userFlag);

		} catch (IOException e) {
			throw new IOException("Cannot find the Profile Header Configuration File");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempHeader;

	}


	// read an excel file looking for a row with a number defined by idRowGet in
	// the first cell
	// return an array
	public Object[] readExcel(String fileName, String sheetName, String idRowGet) throws IOException {

		// Create an object of File class to open xlsx file
		File file = new File(fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		// Find the file extension by splitting file name in substring and
		// getting only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class
			guru99Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class
			guru99Workbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name
		org.apache.poi.ss.usermodel.Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);

		// Find number of rows in excel file
		int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();

		// Object[] tempHeader = {null,null,null,null};
		Object[] tempHeader = new Object[100];

		// Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount + 1; i++) {

			Row row = guru99Sheet.getRow(i);

			// Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {

				// Print Excel data in console

				System.out.print(row.getCell(j).getStringCellValue());

				if (row.getCell(0).getStringCellValue().compareTo(idRowGet) == 0 && j > 0) {
					tempHeader[j - 1] = row.getCell(j).getStringCellValue();
				}
			}

			System.out.println();

		}

		return tempHeader;

	}
	*/
}

