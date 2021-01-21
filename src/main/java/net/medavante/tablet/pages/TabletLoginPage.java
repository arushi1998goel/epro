package net.medavante.tablet.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.winium.WiniumDriver;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import net.medavante.mobile.core.TabletCoreFunctions;
import net.medavante.portal.utilities.Utilities;

public class TabletLoginPage extends TabletCoreFunctions {

	public TabletLoginPage(WiniumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "txtLogin")
	private WebElement userName;

	@FindBy(id = "txtPass")
	private WebElement userPassword;

	@FindBy(id = "btnLogin")
	private WebElement signInButton;
	
	@FindBy(xpath = "//*[contains(@Name,'Row 1 Column')]")
	private WebElement studyButton;
	
	@FindBy(xpath = "//Table[1]/Custom[1]/DataItem[5]")
	private WebElement searchSubject;
	
	@FindBy(xpath = "//*[contains(@Name='MedAvante.SCN.Win.VisitDetails.ViewModels.FormInfoViewModel']")
	private WebElement subjectButton;
	
	
	@FindBy(className = "WindowsForms10.EDIT.app.0.a40b34_r9_ad1")
	private WebElement seachSubject;

	@FindBy(name = "Row 1")
	private WebElement studyName;

	
	public void verifyCloseButton(String uName) {
		//closeButton.click();
		verifyPageIsDisplayAndCaptureTheScreenShot();
		_normalWait(globalWaitTime);
	}
	
	
	public void enterUserName(String uName) {
		userName.sendKeys(uName);
		verifyPageIsDisplayAndCaptureTheScreenShot();
		_normalWait(globalWaitTime);
	}
	
	

	public void enterPasswordName(String pwd) {
		userPassword.sendKeys(pwd);
		verifyPageIsDisplayAndCaptureTheScreenShot();
		_normalWait(globalWaitTime);
		
	}

	public void clickOnSignInButton() {
		signInButton.click();		
		verifyPageIsDisplayAndCaptureTheScreenShot();
		_normalWait(40000);

	}
	
	public void clickOnStdyButton() {
		studyButton.click();		
		verifyPageIsDisplayAndCaptureTheScreenShot();
		_normalWait(30000);

	}

	public void clickOnVirgilAppIcon() {
		try {
			Screen s = new Screen();
			String imagePath = Utilities.getPath() + "//src//test//resources//webdriver//MedAvanteVirgil.PNG";
			Pattern p = new Pattern(imagePath);
			Match m = s.exists(p);
			if (m != null) {
				Thread.sleep(1000);
				s.click(p);
			} else {
				this.connectToApp();
			}
		} catch (Exception e) {
			Reporter.log("Unable to connect to the mobile ");
		}
	}

	public void connectToApp() throws Exception {
		Robot r = new Robot();
		String appName = "MedAvante Virgil";
		StringSelection stringSelection = new StringSelection(appName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		r.keyPress(KeyEvent.VK_WINDOWS);
		r.keyRelease(KeyEvent.VK_WINDOWS);

		Thread.sleep(1000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);

		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public void clickOnRefresh() {

	}

	public void closeTablet() throws IOException, InterruptedException {
		// driver.quit();
		Process process = Runtime.getRuntime().exec("taskkill /f /IM Winium.Desktop.Driver.exe");
		process.waitFor();
		process.destroy();
		// driver.close();

	}

	public void clickOnStudyName(String study) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("Enter Study Table");
		studyName.click();
		
		
		
		/*boolean flag = false;
		System.out.println("Study Name:" + studyName.getText());
		if (studyName.getText().equalsIgnoreCase(study)) {
			studyName.click();
			flag = true;
		}
		Assert.assertTrue(flag);
	}*/
	}


	public void enterSubject(String subject) {
		seachSubject.sendKeys(subject);
		_normalWait(globalWaitTime);
		//clickOnStdyButton();
		verifyPageIsDisplayAndCaptureTheScreenShot();
		_normalWait(globalWaitTime);
		
	}


	public void clickOnSubject() {
		subjectButton.click();
		verifyPageIsDisplayAndCaptureTheScreenShot();
		_normalWait(globalWaitTime);
	}
	
	
	public void clickOnSearchSubject() {
		searchSubject.click();
		verifyPageIsDisplayAndCaptureTheScreenShot();
		_normalWait(globalWaitTime);
	}
}
