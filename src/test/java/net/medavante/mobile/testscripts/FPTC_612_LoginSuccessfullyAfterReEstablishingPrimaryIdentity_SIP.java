package net.medavante.mobile.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;

public class FPTC_612_LoginSuccessfullyAfterReEstablishingPrimaryIdentity_SIP extends BaseTest {

	@BeforeClass
	public void executionOn() {
		exceutionOn = "Mobile";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_612_LoginSuccessfullyAfterReEstablishingPrimaryIdentity_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-612 Test Case Name:To verify that user could login in
	 * successfully after re-establishing the primary identity.
	 * 
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-612_To verify that user could login in successfully after re-establishing the primary identity.", groups = {
			"Mobile" })
	public void FPTC_612_verifyUserCouldLoginSuccessfullyAfterReEstablishingPrimaryIdentity() throws Exception {

		reportLog("1: MobileView As participant, open the application.");
		mobileLoginPage = androidSetUp();

		reportLog(
				"2.1: MobileView Sign-In page shall not be displayed with the following message Incorrect, you have 'N' attempts left");
		mobileLoginPage.verifyTextDoesNotAppearOnScreen("you have 'N' attempts left");

		reportLog("2.2: MobileView PIN edit box shall be displayed. ");
		mobileLoginPage.verifySignInScreenWithPINEditBox();

		reportLog("2.3: MobileView Submit button shall be displayed in disabled state.");
		mobileLoginPage.verifySubmitButtonIsDisabled();

		reportLog("2.4: MobileView I forgot the PIN link shall be displayed.");
		mobileLoginPage.verifyForgetPINLinkDisplay();

		reportLog("2.5: MobileView Avatar and participant version label shall be displayed.");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();

		reportLog("3: MobileView Make an invalid pin attempt");
		mobileLoginPage.enterPINCode("5555");
		mobileLoginPage.clickOnSubmitButton();

		reportLog("3.1: MobileView 'Reset PIN' page shall be displayed with instruction message.");
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage);

		reportLog("3.2: MobileView click on 'I forgot the PIN' link.");
		mobileLoginPage.clickOnForgetPINLink();

		reportLog(
				"3.3: MobileView secret questions with blank edit boxes shall be displayed as per the study config settings.");
		mobileLoginPage.verifyQuestionText("What color was your first car?");
		mobileLoginPage.verifyAnswerCodeDisplay();

		reportLog("3.4: MobileView Next button shall be displayed in disabled state.");
		mobileLoginPage.verifyNextButtonIsDisabled();

		reportLog("4: MobileView Enter valid answers for the secret questions and click on Next button");
		mobileLoginPage.enterAnAnswer("black");
		mobileLoginPage.clickOnNextButton();

		reportLog("4.1: MobileView Create Identity screen shall be displayed with instructional message.");
		mobileLoginPage.verifyInstructionMessageText(createIdentityInstruction);

		reportLog("4.2: MobileView PIN and confirm PIN editboxes shall be displayed");
		mobileLoginPage.verifyPinAndConfirmPINEditBoxesAreDisplay();

		reportLog("4.3: MobileView Next button shall be displayed.");
		mobileLoginPage.verifyNextButtonIsDisabled();

		reportLog("5: MobileView Re-establish primary identity with Enter PIN and confirm Pin values");
		mobileLoginPage.enterPINCode("6161");
		mobileLoginPage.enterConfirmPINCode("6161");

		reportLog("5.1: MobileView Next button shall be displayed in enabled state");
		mobileLoginPage.verifyContinueButtonIsEnabled();

		reportLog("6: MobileView Click on Continue button.");
		mobileLoginPage.clickOnContinueButton();

		reportLog("6.1: MobileView Sign in page shall be displayed with instruction message");
		mobileLoginPage.verifySignInScreenDisplayed();
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage);

		reportLog("6.2: MobileView PIN edit box shall be displayed");
		mobileLoginPage.verifySignInScreenWithPINEditBox();

		reportLog("6.3: MobileView Submit button shall be displayed in disabled state");
		mobileLoginPage.verifySubmitButtonIsDisabled();

		reportLog("6.4: MobileView I forgot the PIN link shall be displayed.");
		mobileLoginPage.verifyForgetPINLinkDisplay();

		reportLog("7: MobileView Enter valid pin and then click on submit button.");
		dashborad = mobileLoginPage.loginUser("6161");

		reportLog(
				"7.1: MobileView Participant logged in to the application successfully and Home page shall be displayed.");
		dashborad.verifyUserLogin();

	}

}
