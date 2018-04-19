package com.utitlity;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DriverUtility {

	/*
	 * Name of the method:"enterText" Description- To enter text inside a input
	 * Arguments: we -> webelement,textvalue -> textvalue to be entered,name- name
	 * of the webelement. created By:Dhaarani Created Date: March 09,2018 This is
	 * CMMI-V level coding
	 */

	public static void enterText(WebElement we, String textValue, String name) {
		if (we.isDisplayed()) {
			we.clear();
			we.sendKeys(textValue);
			System.out.println("Success: " + textValue + " is entered in " + name);
		} else {
			System.out.println("Fail: " + name + " is not available to enter text");
		}
	}

	/*
	 * Name of the method:"clickEvent" Arguments: we -> web element on which above
	 * operation will be perfomed, name- name of the webelement created By:Dhaarani
	 * Created Date: March 09,2018 Modified By: This is CMMI-V level coding
	 */

	public static void clickEvent(WebElement we, String name) {
		if (we.isDisplayed()) {
			if (we.isEnabled()) {
				we.click();
				System.out.println("Sucess: " + name + " is clicked");
			} else {
				System.out.println("Fail: " + name + " is not in the avaialble state");
			}
		} else {
			System.out.println("Fail: " + name + " is not avaialble");
		}
	}

	/*
	 * Name :SelectCheckBox Description: To select the Checkbox Arguments: we->
	 * CheckBox Web Element,Name of the checkbox Created By: Dhaarani, Created Date:
	 * March 09,2018
	 */
	public static void selectCheckBox(WebElement we, String name) {
		if (we.isDisplayed()) {
			if (we.isSelected()) {
				System.out.println("Sucess: " + name + " is already selected");
			} else {
				we.click();
				System.out.println("Sucess: " + name + " is selected");
			}
		} else {
			System.out.println("Fail: " + name + " is not avaialble");
		}
	}

	/*
	 * Name :DeSelectCheckBox Description: To deselect the Checkbox Arguments: we->
	 * CheckBox Web Element,Name of the checkbox Created By: Dhaarani Created Date:
	 * March 09,2018
	 */
	public static void deselectCheckBox(WebElement we, String name) {
		if (we.isDisplayed()) {
			if (we.isSelected()) {
				we.clear();
				System.out.println("Success: " + name + " is deselected");
			} else {
				System.out.println("Success: " + name + " is already  deselected");
			}
		} else {
			System.out.println("Fail :" + name + " is not avaialble");
		}
	}

	/*
	 * Name : ValidateTextBoxContent Description: To check value of an web element
	 * against given value Arguments: WE- Web element whose value has to be checked,
	 * valuetobecompared- web element value to be checked against,name- naem of the
	 * object Created By: Dhaarani. Created Date: March 09,2018
	 */

	public static void validateTextBoxContentValue(WebElement we, String valuetobecompared, String name) {
		if (we.isDisplayed()) {
			String value = we.getAttribute("value");
			if (value.equals(null)) {
				System.out.println("Fail: " + name + " value is null");
			} else {
				if (value.equals(valuetobecompared)) {
					System.out.println("Sucess: " + name + " value is equal");
				} else {
					System.out.println("Fail: " + name + " value is not equal");
				}
			}
		}
	}

	/*
	 * Name: validateText;
	 * Description: To check the value of a web element with getText();
	 * Arguments: we- web element whose values as to be checked;
	 *			  valuetobecomapred-> string to check thecalue against;
	 *			  name- name of the web element;
	 *
	 *created By: Dhaarani ;
	 *Created Date: march 12th, 2018
	 */
	public static void validateText(WebElement we, String valuetobecompared, String name) {
		if (we.isDisplayed()) {
			String value = we.getText();
			if (value.equals(null)) {
				System.out.println("Fail: " + name + " value is null");
			} else {
				Assert.assertEquals(valuetobecompared, value);
				/*if (valuetobecompared.equals(value)) {
					System.out.println("Sucess: " + name + " value is equal");
				} else {
					System.out.println("Fail: " + name + " value is not equal");
				}*/
			}
		}
		
	}
	
	public static void clear(WebElement we,String name) {
		if(we.isDisplayed()) {
			we.clear();
			System.out.println("Success: "+ name+" is cleared");
		}
		else {
			System.out.println("Fail: "+name+" is not availale now");
		}
	}
	
	public static void waitUntillVisible(WebDriverWait wait,String xpath,String name) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		System.out.println("Sucess: wait for "+name+ " to be visible");
	}
	
	public static WebElement waitUntillClickable(WebDriverWait wait,String xpath,String name) {
		WebElement we=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		System.out.println("Success: wait for "+name+" to be clickable");
		return we;
	}
	
	public static void checkAppTitle(WebDriver webDriver,String valueToBeCompared,String name) {
		Assert.assertTrue(webDriver.getTitle().contains(valueToBeCompared));
	}
	
	public static WebDriver launch(String browser) throws InterruptedException {
		WebDriver webdriver=null;
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Mohan\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			webdriver = new FirefoxDriver();
			webdriver.get("https://login.salesforce.com");
			webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Thread.sleep(5000);
			checkAppTitle(webdriver, "Salesforce", "Login page");
		}
		return webdriver;
	}
	
	
	public static WebDriver login(WebDriver webdriver) throws InterruptedException {
		WebElement weusername=webdriver.findElement(By.xpath(".//*[@id='username']"));
		DriverUtility.enterText(weusername, "gnana@gmail.com", "Username");
		
		//gets password web element and sends appropriate values
		WebElement wePass=webdriver.findElement(By.xpath(".//*[@id='password']"));
		DriverUtility.enterText(wePass, "Almond@123", "Password");
		
		//Clicks remeber me check box
		WebElement rememberwe=webdriver.findElement(By.xpath(".//*[@id='rememberUn']"));
		DriverUtility.selectCheckBox(rememberwe, "Remember Me Check Box");
		
		WebElement weLogin=webdriver.findElement(By.xpath(".//*[@id='Login']"));
		DriverUtility.clickEvent(weLogin,"Login button");
		Thread.sleep(5000);
		checkAppTitle(webdriver, "Home Page", "Home Page");
		
		return webdriver;
	}
	
	

}
