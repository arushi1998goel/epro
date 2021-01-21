package net.medavante.mobile.testscripts;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;

public class FPTC_951_AuthenticationPINExpirationParticipant_SIP extends BaseTest{
	
	@BeforeClass
	public void executionOn(){
		exceutionOn="MobileAndWebExecution"; 
	}
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_951_AuthenticationPINExpirationParticipant_SIP(String driver) {
		super(driver);
	}
		
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-951 Test Case Name: To verify authentication functionality for user to gain access to the application.

	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-951_To verify authentication functionality for user to gain access to the application.", groups = { "Mobile" })
	public void FPTC_951_AuthenticationPINExpirationParticipant() throws Exception {
		
		reportLog("1: MobileView Launch the application as a participant");
		mobileLoginPage=androidSetUp(); 
		
	/*	reportLog("1.1: MobileView Verify Sign-In screen display");
		mobileLoginPage.verifySignInScreenDisplayed();
		
		reportLog("1.2: MobileView Click on forget PIN link");
		mobileLoginPage.clickOnForgetPINLink();*/
		
		reportLog("2.1:MobileView RESET PIN Screen shall be displayed with instruction message that 'To reset the PIN for your account you need to answer the following questions. PIN HAS EXPIRED");
		mobileLoginPage.verifyInstructionMessageText(ResetPINInstruction);
		
		reportLog("2.2: MobileView Security questions shall be displayed (as per configuration).");
		mobileLoginPage.verifyQuestionText("What color was your first car?");	
		mobileLoginPage.verifyAnswerCodeDisplay();		
		
		reportLog("2.3: MobileView Next button shall be displayed in disabled state ");
		mobileLoginPage.verifyNextButtonIsDisabled();
		
		reportLog("2.4: MobileView Avatar and Participant version label shall be displayed");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();
		
		reportLog("3: MobileView Enter valid answer to Security Question and then click to 'Next' Button.");
		mobileLoginPage.enterAnAnswer("black");
		mobileLoginPage.clickOnNextButton();
		
		reportLog("3.1: MobileView CREATE IDENTITY Screen shall be displayed with an instruction message.");
		mobileLoginPage.verifyInstructionMessageText(createIdentityInstruction);
		
		reportLog("3.2: MobileView PIN edit box shall be displayed");
		mobileLoginPage.verifyPinAndConfirmPINEditBoxesAreDisplay();
		
		reportLog("3.3: MobileView Confirm PIN edit box shall be displayed");
		mobileLoginPage.verifyPinAndConfirmPINEditBoxesAreDisplay();
		
		reportLog("3.4: MobileView Next button shall be displayed in disabled state.");
		mobileLoginPage.verifyNextButtonIsDisabled();
		
		reportLog("3.4: MobileView Avatar and Participant version shall be displayed.");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();
		
		reportLog("4: MobileView Enter valid PIN and matching Confirm PIN details and then click to 'Next' Button.");
		mobileLoginPage.enterPINCode(resetMobilePin);
		mobileLoginPage.enterConfirmPINCode(resetMobilePin);
		mobileLoginPage.clickOnNextButton();
		
		reportLog("4.1: MobileView Sign-In screen shall be displayed with an instruction message");
		mobileLoginPage.verifySignInScreenDisplayed();	
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage);		
		
		reportLog("4.2: MobileView PIN edit box shall be displayed");
		mobileLoginPage.verifySignInScreenWithPINEditBox();
		
		reportLog("4.3:  MobileView Submit button shall be displayed in disabled state");
		mobileLoginPage.verifySubmitButtonIsDisabled();
		
		reportLog("4.4: MobileView I forgot the PIN link shall be displayed.");
		mobileLoginPage.verifyForgetPINLinkDisplay();
		
		reportLog("4.5: MobileView Avatar and Participant version shall be displayed.");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();
		
		reportLog("5: MobileView Enter valid new PIN and then click to Submit Button.");
		dashborad=mobileLoginPage.loginUser(resetMobilePin);
		
		reportLog("5.1: MobileView Home page shall be displayed for the Participant.");
		dashborad.verifyUserLogin();
	}
	
	
	
	
}
