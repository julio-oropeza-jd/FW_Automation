package actions;

import enums.commonEnum;
import objectRepository.LoginPage;
import objectRepository.XFormDashBoard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class logActions{
	objectRepository.LoginPage login;
	objectRepository.XFormDashBoard XFormDashBoard;
	WebDriver driver;
	
	public logActions(WebDriver driver) {
		this.driver=driver;
		login = new LoginPage();
		XFormDashBoard = new XFormDashBoard();
		
	}

	public void login(Object[] initParameters) throws Exception{

		String sPassword = initParameters[0].toString();
		String sEmail = initParameters[1].toString();

		driver.findElement(login.username).sendKeys(sEmail);
		driver.findElement(login.password).sendKeys(sPassword);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		//this need to be set in a specific function in commons 
		wait.until(ExpectedConditions.elementToBeClickable(login.signIn));
			   
		driver.findElement(login.signIn).click();		
		/*
		WebElement element = driver.findElement(By.xpath("//*[@id='cred_sign_in_button']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);*/

		boolean rightURL = wait.until(ExpectedConditions.urlContains(commonEnum.pagesURLs.XFormDashBoardUrl.toString()));

		if(!rightURL)
			throw new Exception("Page was not loaded");
	}
	//public void addUser
}
