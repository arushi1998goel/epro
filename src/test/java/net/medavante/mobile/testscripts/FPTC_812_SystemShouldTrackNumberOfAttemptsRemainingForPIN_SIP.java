package net.medavante.mobile.testscripts;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;

public class FPTC_812_SystemShouldTrackNumberOfAttemptsRemainingForPIN_SIP extends BaseTest{
	
	
	
	@BeforeClass
	public void executionOn(){
		exceutionOn="Mobile"; 
	}
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_812_SystemShouldTrackNumberOfAttemptsRemainingForPIN_SIP(String driver) {
		super(driver);
	}
		
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-812  Test Case Name: System should track the number of attempts remaining for PIN. 
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-812_System should track the number of attempts remaining for PIN.", groups = { "Mobile" })
	public void FPTC_951_AuthenticationPINExpirationParticipant() throws Exception {
		
		reportLog("1: MobileView In the sign in page, enter invalid pin value click on submit button.");
		mobileLoginPage=androidSetUp(); 
		mobileLoginPage.enterPINCode("4141");
		mobileLoginPage.clickOnSubmitButton();
		
		reportLog("2.1: MobileView In Sign-in page, Error message shall be displayed for first invalid pin attempt.");
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage + "INCORRECT,YOU HAVE");
		mobileLoginPage.verifyCrossMarkButtonDisplayed();
		
		reportLog("3: MobileView In the second attempt -Provide invalid pin value and click on submit.");
		mobileLoginPage.enterPINCode("4141");
		mobileLoginPage.clickOnSubmitButton();
		
		reportLog("3.1: MobileView In Sign-in page, Error message shall be displayed for second invalid pin attempt.");
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage + "INCORRECT,YOU HAVE");
		mobileLoginPage.verifyCrossMarkButtonDisplayed();
		
		reportLog("4: MobileView Close the application. Reopen the application.");
		mobileLoginPage.closeApp();
		mobileLoginPage.reopenApp();
				
		reportLog("4.1: MobileView Sign in page shall be displayed.");
		mobileLoginPage.verifySignInScreenDisplayed();		
		
		reportLog("5: MobileView In the third attempt-Provide invalid pin value and click on submit.");
		mobileLoginPage.enterPINCode("4141");
		mobileLoginPage.clickOnSubmitButton();
		
		reportLog("5.1: MobileView In Sign-in page, Error message shall be displayed for third invalid pin attempt system should track the number of attempts remaining for PIN.");
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage + "INCORRECT,YOU HAVE");
		mobileLoginPage.verifyCrossMarkButtonDisplayed();
		
		reportLog("6: MobileView In the fourth attempt -provide valid pin attempt.");
		dashborad = mobileLoginPage.loginUser("1414");		
		
		reportLog("6.1: MobileView Home page shall be displayed.");
		dashborad.verifyHomePageDisplay();
		
		reportLog("7: MobileView Click on Exit application option.");
		mobileLoginPage.exitApplication();
		mobileLoginPage.reopenApp();		
		
		reportLog("7.1: MobileView Signin page shall be displayed.");
		mobileLoginPage.verifySignInScreenDisplayed();
		
		reportLog("8: MobileView This time, enter invalid pin value and check the behavior.");
		mobileLoginPage.enterPINCode("4141");
		mobileLoginPage.clickOnSubmitButton();
		
		reportLog("8.1: MobileView In Sign-in page, Error message shall be displayed for first invalid pin attempt.");
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage + "INCORRECT,YOU HAVE");
		mobileLoginPage.verifyCrossMarkButtonDisplayed();
		mobileLoginPage.closeApp();
				
	}
	
	
	
	
}
