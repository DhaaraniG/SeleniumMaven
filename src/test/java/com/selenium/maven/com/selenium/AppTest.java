package com.selenium.maven.com.selenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.utitlity.DriverUtility;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	public static WebDriver webdriver;
	
	@Test
	public static void createNewViewT11() throws InterruptedException{
		webdriver=DriverUtility.launch("firefox");
		webdriver=DriverUtility.login(webdriver);
		
		WebElement accounttab=webdriver.findElement(By.xpath(".//*[@id='Account_Tab']/a"));
		DriverUtility.clickEvent(accounttab, "Accounts");
		
		
		WebElement createview=webdriver.findElement(By.xpath(".//*[@id='filter_element']/div/span/span[2]/a[2]"));
		DriverUtility.clickEvent(createview, "create new view");
		
		Thread.sleep(5000);
		try {
			WebElement closeWe=webdriver.findElement(By.id("tryLexDialogX"));
			DriverUtility.clickEvent(closeWe, "Close");
		}catch(Exception e) {
			
		}
		DriverUtility.clickEvent(createview, "create new view");
		DriverUtility.checkAppTitle(webdriver, "Create New View", "Create new view");
		WebElement viewName=webdriver.findElement(By.xpath(".//*[@id='fname']"));
		DriverUtility.enterText(viewName, "sampleView127", "view Name");
		
		WebElement uniquename=webdriver.findElement(By.xpath(".//*[@id='devname']"));
		DriverUtility.enterText(uniquename, "sampleView127", "Unique Name");
		
		WebElement save=webdriver.findElement(By.xpath(".//*[@id='editPage']/div[1]/table/tbody/tr/td[2]/input[1]"));
		DriverUtility.clickEvent(save, "Save Button");
		
		WebDriverWait wait=new WebDriverWait(webdriver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='bodyTable']")));
		WebElement selectwe=webdriver.findElement(By.xpath(".//*[@id='bodyTable']//select[contains(@id,'listSelect')]"));
		DriverUtility.checkAppTitle(webdriver, "Accounts", "Accounts");
		Select select=new Select(selectwe);
		Assert.assertEquals("sampleView127",select.getFirstSelectedOption().getText());
		
		Thread.sleep(5000);
		webdriver.close();
		
	}
	
	@Test
	public static void createAccountT10()  throws InterruptedException{
		webdriver=DriverUtility.launch("firefox");
		webdriver=DriverUtility.login(webdriver);
		
		WebElement accounttab=webdriver.findElement(By.xpath(".//*[@id='Account_Tab']/a"));
		DriverUtility.clickEvent(accounttab, "Accounts");
		WebElement accountNew=webdriver.findElement(By.xpath(".//*[@id='hotlist']/table/tbody/tr/td[2]/input"));
		
		DriverUtility.checkAppTitle(webdriver, "Accounts", "Acounts page");
		DriverUtility.clickEvent(accountNew, "Account New");
		Thread.sleep(5000);
		
		try {
			WebElement closeWe=webdriver.findElement(By.id("tryLexDialogX"));
			DriverUtility.clickEvent(closeWe, "Close");
		}catch(Exception e) {
			
		}
		DriverUtility.clickEvent(accountNew, "Account New");
		
		WebDriverWait wait=new WebDriverWait(webdriver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='acc2']")));
		WebElement accountName=webdriver.findElement(By.xpath(".//*[@id='acc2']"));
		DriverUtility.enterText(accountName, "Dhaarani", "Account Name");
		
		WebElement save=webdriver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]"));
		DriverUtility.clickEvent(save, "Save Button");
		
		Thread.sleep(5000);
		
		DriverUtility.checkAppTitle(webdriver, "Account: Dhaarani", "Account title");
		
		Thread.sleep(5000);
		webdriver.close();
	}
	
	@Test
	public static void logoutUsermenuT09() throws InterruptedException {
		webdriver=DriverUtility.launch("firefox");
		webdriver=DriverUtility.login(webdriver);
		List<String> actualMenu=new ArrayList<String>();
		String[] options=new String[]{"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		List<String> expectedMenu=new ArrayList<String>(Arrays.asList(options));
		WebElement usericon=webdriver.findElement(By.xpath(".//*[@id='userNav-arrow']"));
		DriverUtility.clickEvent(usericon, "User Menu");
		List<WebElement> menu=webdriver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		for(WebElement element:menu) {
			actualMenu.add(element.getText().trim());
		}
		if(actualMenu.equals(expectedMenu)) {
			System.out.println("Sucess: User Menu is verified");
		}
		else {
			System.out.println("Fail: User Menu is not verified");
		}
		WebElement logoutwe=webdriver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[5]"));
		DriverUtility.clickEvent(logoutwe, "Logout");
		Thread.sleep(5000);
		DriverUtility.checkAppTitle(webdriver, "Login", "Login page");
		
		Thread.sleep(5000);
		webdriver.close();	
		
	}
	
	@Test
	public static void testCase6() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Mohan\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		webdriver = new FirefoxDriver();
		webdriver.get("https://login.salesforce.com");
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement weusername = webdriver.findElement(By.xpath(".//*[@id='username']"));
		DriverUtility.enterText(weusername, "gnana@gmail.com", "Username");

		// gets password web element and sends appropriate values
		WebElement wePass = webdriver.findElement(By.xpath(".//*[@id='password']"));
		DriverUtility.enterText(wePass, "Almond@123", "Password");

		// Clicks remeber me check box
		WebElement rememberwe = webdriver.findElement(By.xpath(".//*[@id='rememberUn']"));
		DriverUtility.selectCheckBox(rememberwe, "Remember Me Check Box");

		WebElement weLogin = webdriver.findElement(By.xpath(".//*[@id='Login']"));
		DriverUtility.clickEvent(weLogin, "Login button");

		WebDriverWait wait = new WebDriverWait(webdriver, 20);

		DriverUtility.waitUntillVisible(wait, ".//*[@id='userNavButton']", "User Icon ");
		WebElement menuitem = webdriver.findElement(By.xpath(".//*[@id='userNavButton']"));
		DriverUtility.clickEvent(menuitem, "User Icon");

		WebElement profile = webdriver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[1]"));
		DriverUtility.clickEvent(profile, "Profile Link");

		WebElement arrowwe = DriverUtility.waitUntillClickable(wait, ".//*[@id='moderatorMutton']", "Arrow");
		DriverUtility.clickEvent(arrowwe, "Arrow");

		WebElement editProfile = webdriver
				.findElement(By.xpath(".//*[@id='chatterTab']/div[1]/div/div[1]/div[1]/div/ul/li[2]/a"));
		DriverUtility.clickEvent(editProfile, "Edit Profile Link");

		DriverUtility.waitUntillVisible(wait, ".//*[@id='aboutMeContentId']", "Iframe");

		WebElement iframe = webdriver.findElement(By.xpath(".//*[@id='aboutMeContentId']"));
		webdriver.switchTo().frame(iframe);

		WebElement lastnamewe = webdriver.findElement(By.xpath(".//*[@id='lastName']"));
		DriverUtility.enterText(lastnamewe, "G", "Lastname");

		WebElement savewe = webdriver.findElement(By.xpath(".//*[@id='TabPanel']/div/div[2]/form/div/input[1]"));
		DriverUtility.clickEvent(savewe, "Save All");
		

		webdriver.switchTo().defaultContent();

		DriverUtility.waitUntillVisible(wait, ".//*[@id='tailBreadcrumbNode']", "User Name");
		WebElement usernamebreadcrumb = webdriver.findElement(By.xpath(".//*[@id='tailBreadcrumbNode']"));

		DriverUtility.validateText(usernamebreadcrumb, "Dhaarani G ", "User Breadcrumb");

		Thread.sleep(5000);
		WebElement postwe= DriverUtility.waitUntillClickable(wait, ".//*[@id='publisherAttachTextPost']", "Post Link");
		DriverUtility.clickEvent(postwe, "Post Link");
		
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='cke_wysiwyg_frame cke_reset']")));

		WebElement postframe = webdriver.findElement(By.xpath(".//*[@class='cke_wysiwyg_frame cke_reset']"));
		webdriver.switchTo().frame(postframe);

		DriverUtility.waitUntillVisible(wait, "html/body", "post");

		WebElement post = webdriver.findElement(By.xpath("html/body"));
		DriverUtility.enterText(post, "sample post", "post Text");

		webdriver.switchTo().defaultContent();

		WebElement sharewe = webdriver.findElement(By.xpath(".//*[@id='publishersharebutton']"));
		DriverUtility.clickEvent(sharewe, "share button");
		

		List<WebElement> posttext = webdriver.findElements(By.xpath("//*[@class='feeditemtext cxfeeditemtext']"));
		System.out.println(posttext);
		DriverUtility.validateText(posttext.get(0), "sample post", "Post Text");

		WebElement filewe = webdriver.findElement(By.xpath(".//*[@id='publisherAttachContentPost']/span[1]"));
		DriverUtility.clickEvent(filewe, "File Link");

		WebElement computerwe = webdriver.findElement(By.xpath(".//*[@id='chatterUploadFileAction']"));
		DriverUtility.clickEvent(computerwe, "UPLOAD fILE");

		WebElement browsewe = webdriver.findElement(By.xpath(".//*[@id='chatterFile']"));
		DriverUtility.enterText(browsewe, "C:\\Users\\Mohan\\Downloads\\DhaaraniCover.pdf", "Browse button");
		
		
		DriverUtility.clickEvent(sharewe, "share button");
		Thread.sleep(5000);
		
		Actions action=new Actions(webdriver);
		action.moveToElement(webdriver.findElement(By.xpath("//*[text()='Moderator']"))).perform();

		WebElement photowe = webdriver.findElement(By.xpath(".//*[@id='uploadLink']"));
		DriverUtility.clickEvent(photowe, "photo link");
		
		Thread.sleep(5000);
		
		DriverUtility.waitUntillVisible(wait, ".//*[@id='uploadPhotoContentId']","Photo frame");
		WebElement photoframe=webdriver.findElement(By.xpath(".//*[@id='uploadPhotoContentId']"));
		webdriver.switchTo().frame(photoframe);

		WebElement photobrowsewe = DriverUtility.waitUntillClickable(wait,
				".//*[@id='j_id0:uploadFileForm:uploadInputFile']", "Photo browse");
		DriverUtility.enterText(photobrowsewe, "C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg",
				"Photo browse");
		
		DriverUtility.waitUntillVisible(wait, ".//*[@id='j_id0:uploadFileForm:uploadBtn']","Save Button");
		WebElement photosavewe = webdriver.findElement(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadBtn']"));
		DriverUtility.clickEvent(photosavewe, "Photo save");
		
		DriverUtility.waitUntillVisible(wait, ".//*[@id='j_id0:j_id7:save']","Save Button");
		WebElement savefinalwe=webdriver.findElement(By.xpath(".//*[@id='j_id0:j_id7:save']"));
		DriverUtility.clickEvent(savefinalwe, "save File");
		
		Thread.sleep(5000);	
		
		webdriver.switchTo().defaultContent();
		
		Thread.sleep(5000);
		webdriver.close();	
	}
	
	@Test
	public static void navigateToSDFC() throws InterruptedException
	{
		WebDriver webdriver=DriverUtility.launch("firefox");

		WebElement weusername=webdriver.findElement(By.xpath(".//*[@id='username']"));
		DriverUtility.enterText(weusername, "gnana@gmail.com", "User name");
	
		WebElement wePass=webdriver.findElement(By.xpath(".//*[@id='password']"));
		DriverUtility.clear(wePass, "Password");
		
		WebElement weLogin=webdriver.findElement(By.xpath(".//*[@id='Login']"));
		DriverUtility.clickEvent(weLogin,"Login Button");
		
		WebDriverWait wait=new WebDriverWait(webdriver, 10);
		DriverUtility.waitUntillVisible(wait,".//*[@id='error']","Error tag");
		
		WebElement errorwe=webdriver.findElement(By.xpath(".//*[@id='error']"));		
		DriverUtility.validateText(errorwe, "Please enter your password.", "Error  ");
		webdriver.quit();	
		
	}
	
	@Test
	public static void loginToSDFC() throws InterruptedException
	{
		WebDriver webdriver=DriverUtility.launch("firefox");
		webdriver=DriverUtility.login(webdriver);
		webdriver.quit();
	}
	
	@Test
	public void checkRememberMe() throws InterruptedException {
		WebDriver webdriver=DriverUtility.launch("firefox");
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
		
		
		WebDriverWait wait=new WebDriverWait(webdriver, 10);
		DriverUtility.waitUntillVisible(wait, ".//*[@id='userNavButton']","User Field");
		
		WebElement menuitem=webdriver.findElement(By.xpath(".//*[@id='userNavButton']"));
		DriverUtility.clickEvent(menuitem, "usericon");
		WebElement logout=webdriver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[5]"));
		
		DriverUtility.clickEvent(logout, "Logout Link");
		
		
		WebElement usercheckwe=DriverUtility.waitUntillClickable(wait, ".//*[@id='idcard-identity']","User Name");
		DriverUtility.validateText(usercheckwe, "gnana@gmail.com", "User name");
		
		webdriver.quit();
	}
	
	@Test
	public static void validateLoginErrorMessage() throws InterruptedException {
		WebDriver webdriver=DriverUtility.launch("firefox");
		WebElement weusername = webdriver.findElement(By.xpath(".//*[@id='username']"));
		DriverUtility.enterText(weusername, "123", "User name");
		
		WebElement wePass = webdriver.findElement(By.xpath(".//*[@id='password']"));
		DriverUtility.enterText(wePass, "22131", "Password");
	
		WebElement weLogin = webdriver.findElement(By.xpath(".//*[@id='Login']"));
		DriverUtility.clickEvent(weLogin, "Login Button");
		
		WebDriverWait wait = new WebDriverWait(webdriver, 10);
		WebElement errorid=DriverUtility.waitUntillClickable(wait, ".//*[@id='error']", "Error");
		DriverUtility.validateText(errorid, "Please check your username and password. If you still can't log in, contact your Salesforce administrator.", "Error message");

		webdriver.quit();
	}
	
	@Test
	public static void userMenuDropDown() throws InterruptedException {
		WebDriver webdriver=DriverUtility.launch("firefox");
		webdriver=DriverUtility.login(webdriver);		
		WebDriverWait wait=new WebDriverWait(webdriver, 10);
		
		DriverUtility.waitUntillVisible(wait, ".//*[@id='userNavButton']","User Icon ");
		WebElement menuitem=webdriver.findElement(By.xpath(".//*[@id='userNavButton']"));
		DriverUtility.clickEvent(menuitem, "User Icon");
		
		try {
		WebElement profile=webdriver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[1]")); 
		WebElement settings=webdriver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[2]"));
		WebElement developerConsole=webdriver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[3]"));
		WebElement switchwe=webdriver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[4]"));
		WebElement logout=webdriver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[5]"));
		
		DriverUtility.validateText(profile, "My Profile"," profile");
		
		DriverUtility.validateText(settings,"My Settings", "Settings");
		
		DriverUtility.validateText(developerConsole, "Developer Console"," Developer console");
		
		DriverUtility.validateText(switchwe,"Switch to Lightning Experience","Light Experience BUtton");
		
		DriverUtility.validateText(logout, "Logout", "Logout");
		
		
		}catch(NoSuchElementException e) {
			System.out.println("Fail: Test Failed");
		}		
		
		webdriver.quit();
	}
	

	
}
