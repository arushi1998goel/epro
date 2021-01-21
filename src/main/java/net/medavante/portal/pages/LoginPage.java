package net.medavante.portal.pages;

import org.jfree.util.Log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.selenium.core.Configuration;

public class LoginPage extends BasePage {

	private String applicationUrl, setEnvironment;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "WebLogin_UserName")
	private WebElement userInput;

	@FindBy(id = "Password")
	private WebElement passwordInput;

	@FindBy(xpath = "//h1[text()='MedAvante-ProPhase']")
	private WebElement proPhaseText;

	@FindBy(xpath = "//*[@id='WebLogin_Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//header//i[@class='burger-menu']")
	private WebElement menu;

	@FindBy(xpath = "//div[@class='menu open']//a[contains(text(),'Log out')]")
	private WebElement logout;

	@FindBy(css = "a[title='Log out']")
	private WebElement logoutIcon;

	@FindBy(xpath = "//div[@class='error-container']//div[@class='close-button-white']")
	private WebElement headerErrorContainerCloseBTN;

	@FindBy(xpath = "//a[contains(text(),'Log Out')]")
	private WebElement logoutButton;

	@FindBy(xpath = "//div[@class='name']//a[@class='dropdown-toggle dropdown-toggle-caret']")
	private WebElement userNameDropDownForLogout;

	/**
	 * Function: Login application.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */

	public MedAvantePortalPage loginInApplication(String userName, String password) {
		inputText(userInput, userName);
		inputText(passwordInput, password);
		waitAndClick(loginButton);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
		waitUntillFinishProcessSpinnerDisable();
		completeAllAssetsInTraining(userName, password);
		return PageFactory.initElements(driver, MedAvantePortalPage.class);
	}

	public MedAvantePortalPage maLogin(String userName, String password) throws Exception {
		setEnvironment = Configuration.readApplicationFile("SetEnvironment");
		if (setEnvironment.equalsIgnoreCase("stg")) {
			applicationUrl = Configuration.readApplicationFile("maStgURL");
		} else if (setEnvironment.equalsIgnoreCase("test")) {
			applicationUrl = Configuration.readApplicationFile("maTestURL");
		} else if (setEnvironment.equalsIgnoreCase("qa")) {
			applicationUrl = Configuration.readApplicationFile("maQaURL");
		} 
		else {
			Log.error("Please enter correct URL");
		}
		driver.navigate().to(applicationUrl);
		inputText(userInput, userName);
		inputText(passwordInput, password);
		clickOn(loginButton);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
		waitUntillFinishProcessSpinnerDisable();
		completeAllAssetsInTraining(userName, password);
				return PageFactory.initElements(driver, MedAvantePortalPage.class);
	}

	public MedAvantePortalPage sponsorLogin(String userName, String password) throws Exception {
		setEnvironment = Configuration.readApplicationFile("SetEnvironment");
		if (setEnvironment.equalsIgnoreCase("stg")) {
			applicationUrl = Configuration.readApplicationFile("sponsorStgURL");
		} else if (setEnvironment.equalsIgnoreCase("test")) {
			applicationUrl = Configuration.readApplicationFile("sponsorTestURL");
		} else if (setEnvironment.equalsIgnoreCase("qa")) {
			applicationUrl = Configuration.readApplicationFile("sponserQaURL");
		} 
		else {
			Log.error("Please enter correct URL");
		}
		driver.navigate().to(applicationUrl);
		inputText(userInput, userName);
		inputText(passwordInput, password);
		clickOn(loginButton);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
		waitUntillFinishProcessSpinnerDisable();
		//completeAllAssetsInTraining(userName, password);
		return PageFactory.initElements(driver, MedAvantePortalPage.class);
	}

	public MedAvantePortalPage siteLogin(String userName, String password) throws Exception {
		setEnvironment = Configuration.readApplicationFile("SetEnvironment");
		if (setEnvironment.equalsIgnoreCase("stg")) {
			applicationUrl = Configuration.readApplicationFile("siteStgURL");
		} else if (setEnvironment.equalsIgnoreCase("test")) {
			applicationUrl = Configuration.readApplicationFile("siteTestURL");
		} else if (setEnvironment.equalsIgnoreCase("qa")) {
			applicationUrl = Configuration.readApplicationFile("siteQaURL");
		}

		else {
			Log.error("Please enter correct URL");
		}
		driver.navigate().to(applicationUrl);
		inputText(userInput, userName);
		inputText(passwordInput, password);
		clickOn(loginButton);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
		waitUntillFinishProcessSpinnerDisable();
		//completeAllAssetsInTraining(userName, password);
		return PageFactory.initElements(driver, MedAvantePortalPage.class);
	}

	/**
	 * Function: Logout application
	 * 
	 * @return
	 */
	public LoginPage logoutApplication() {
		try {
			scrollToTopOfThePage();
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (isElementDisplayed(userNameDropDownForLogout)) {
			waitForElement(userNameDropDownForLogout);
			javascriptButtonClick(userNameDropDownForLogout);
			javascriptButtonClick(logoutButton);
		} else if (isElementDisplayed(logoutIcon)) {
			javascriptButtonClick(logoutIcon);
		}
		return PageFactory.initElements(driver, LoginPage.class);
	}

	/**
	 * Function: Logout application
	 * 
	 * @return
	 */
	public LoginPage logoutApplicationWithDropDown() {
		scrollToTopOfThePage();
		javascripctHilightingElement(menu);
		waitAndClick(menu);
		waitAndClick(logout);
		return PageFactory.initElements(driver, LoginPage.class);

	}

	/* Verify user LogOut */
	public void verifyUserLogout() {
		_normalWait(1000);
		boolean flag = false;
		for (int i = 0; i <= 1; i++) {
			if (isElementDisplayed(userInput)) {
				flag = true;
				break;
			} else {
				refreshPage();
			continue;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
		//driver.close();
	}

	
}
