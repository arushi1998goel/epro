package net.medavante.mobile.testscripts;

import java.util.Properties;

import org.jfree.util.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_486_VerifyIncorrectQRRegistrationCodeProvidedCorrectTextRegistrationCode_SIP
		extends BaseTest {

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_486_VerifyIncorrectQRRegistrationCodeProvidedCorrectTextRegistrationCode_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");

		
		  reportLog("Go to Portal Side to Create Subject");
		  
		  dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		  studyNavigatorDashBoardPage =
		  dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
		  Constants.NavigateText, Constants.StudyText);
		  studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,
		  Constants.ATAssignedRater_10, screeningNum); subjectDetailPage =
		  studyNavigatorDashBoardPage.clickOnSaveBTN();
		  subjectDetailPage.verifyNewSubjectDetailPage();
		  
		  reportLog("Get the Registration Code From The Subject");
		  
		  subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		  registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		  
		  subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();
		  
		  loginPage.logoutApplication(); loginPage.verifyUserLogout();
		 
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-486 Test Case Name:Device Registration - Incorrect
	 * (QR) Registration Code provided - Correct Text Registration Code provided
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-486_Device Registration - Incorrect (QR) Registration Code provided - Correct Text Registration Code provided ", groups = {
			"Mobile" })
	public void FPTC_486_VerifyIncorrectQRRegistrationCodeProvidedCorrectTextRegistrationCodeProvided()

			throws Exception {

		reportLog("MobileView Launch the application");
		/*mobileLoginPage=runConnectAndConnectToDevice();
		mobileLoginPage.tapTopToBottom();*/

		mobileLoginPage = androidSetUp();
		//mobileLoginPage.clickToMinimizeConnect();

		reportLog("MobileView Application launch and verify Register screen with instruction message ");
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);

		reportLog("MobileView Verify both registration methods are available.");
		mobileLoginPage.verifyRegisterScreenIsDisplay();

		reportLog("MobileView Tap Scan the code");
		mobileLoginPage.clickOnScanTheCode();

		reportLog("MobileView Verify Scan code screen shall be displayed with instruction message");
		mobileLoginPage.verifyScanTheCodePage(scanCodeScreenInstructionMessage);

		reportLog("MobileView Click on back button to go on back screen");
		mobileLoginPage.clickOnBackButton();

		reportLog("MobileView Verify both registration methods are available.");
		mobileLoginPage.verifyRegisterScreenIsDisplay();

		reportLog("MobileView Click on enter the code button");
		mobileLoginPage.clickOnEnterTheCode();

		reportLog("MobileView Verify Enter the code screen shall be displayed with instruction message.");
		mobileLoginPage.verifyInstructionMessageText(enterTheCodeScreenInstructionMessage);

		reportLog("MobileView Enter the Text code");
		mobileLoginPage.enterTheRegistrationCode(registrationCode);

		reportLog("MobileView click Submit button");
		mobileLoginPage.clickOnSubmitButton();
		mobileLoginPage.verifyContinueButtonDisplay();

		reportLog("MobileView Verify Register screen with instruction message");
		mobileLoginPage.verifyInstructionMessageText(successRegisterInstrucrionMessage);

		reportLog("MobileView Verify Success Indicator - Tick mark");
		mobileLoginPage.verifySuccessIndicatorTickMarkIconIsDisplay();

		reportLog("MobileView Verify Continue button is display");
		mobileLoginPage.verifyContinueButtonIsDisplay();

		reportLog("MobileView click on Continue button ");
		mobileLoginPage.clickOnContinueButton();

		reportLog("MobileView Verify Terms and Conditions screen with instruction message shall be displayed");
		mobileLoginPage.verifyTermsAndConditionPageIsDisplay(termsAndCondtionInstructionMessage);

		reportLog("MobileView click on Accept button");
		mobileLoginPage.clickOnAcceptBtn();

		reportLog("MobileView User Enter user pin in input field and click on submit button");
		mobileLoginPage.verifyInstructionMessageText(createIdentityInstructionMessage);
		mobileLoginPage.enterPINCode(Mobile_Pin);
		mobileLoginPage.enterConfirmPINCode(Mobile_Pin);

		reportLog("MobileView click on Next Button");
		mobileLoginPage.clickOnNextButton();

		reportLog("MobileView Verify create Identity questions and question lsit");
		mobileLoginPage.verifyInstructionMessageText(createIdentityQuestionMessage);
		mobileLoginPage.verifyChooseAQuestionDisplay();

		reportLog("MobileView choose question and respective answer");
		mobileLoginPage.chooseAQuestion(Choose_QuestionPin);
		mobileLoginPage.enterAnAnswer(Choose_QuestionAnswer);

		reportLog("MobileView click on Next button and then Continue one");
		mobileLoginPage.clickOnNextButton();
		mobileLoginPage.clickOnContinueButton();

		reportLog("MobileView verify Sign In screen display");
		mobileLoginPage.verifySignInScreenDisplayed();

		reportLog("MobileView Enter the PIN and click on submit button");
		mobileLoginPage.enterPINCode(Mobile_Pin);
		mobileLoginPage.clickOnSubmitButton();

		reportLog("MobileView Verify User is login");
		mobileLoginPage.verifyUserLogin();

		reportLog("MobileView Click on exit icon and close the application");
		mobileLoginPage.exitApplication();
		clickOnConnectAppIcon();
			
		
	}
	
	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) throws InterruptedException {
		if(ITestResult.SUCCESS==result.getStatus()) {
			
			reportLog("Deactivate Subject");
			
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage
					.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, screeningNum);
			subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
			subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
			loginPage.verifyUserLogout();
			
		}else {
			Log.error(subjectName + " subject is not added for " + studyName+ " study.");
		}

	}

}
