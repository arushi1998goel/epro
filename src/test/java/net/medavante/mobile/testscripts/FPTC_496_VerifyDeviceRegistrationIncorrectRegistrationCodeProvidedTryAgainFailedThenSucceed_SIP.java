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

public class FPTC_496_VerifyDeviceRegistrationIncorrectRegistrationCodeProvidedTryAgainFailedThenSucceed_SIP extends BaseTest{

	@BeforeClass
	public void executionOn(){
		
		exceutionOn="MobileAndWebExecution"; 
	}
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_496_VerifyDeviceRegistrationIncorrectRegistrationCodeProvidedTryAgainFailedThenSucceed_SIP(String driver) {
		super(driver);
	}
		
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		registrationIncorrectCode = Configuration.readApplicationFile("studyRegistrationIncorretCode");
		registrationIncompleteCode = Configuration.readApplicationFile("studyRegistrationIncompleteCode");
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");

		
		/*PR: Pre-Requisites: [Inputs]: 
			• Correct Registration Code generated
			• Incorrect Registration Code (16 digit) generated
			• Incorrect Registration Code (17 digit) 
			• Terms and Conditions: Configured*/
		
		reportLog("Go to Portal Side to Create Subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Get the Registration Code From The Subject");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
	}
	
	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-496 Test Case Name:Device Registration - Incorrect (QR) Registration Code provided - Correct Text Registration Code provided
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-496_Device Registration - Incorrect Registration Code (Text) provided - Try Again Failed Then Succed  ", groups = {"Mobile" })
	public void FPTC_496_verifyDeviceRegistrationIncorrectRegistrationCodeProvidedTryAgainFailedThenSucceed() throws Exception {
		
		reportLog("1: MobileView Launch ePro application");
		mobileLoginPage=androidSetUp(); 
		
		reportLog("1.1: MobileView Verify Register screen with instruction message");
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		
		reportLog("2: MobileView Verify both registration methods are available.");
		mobileLoginPage.verifyRegisterScreenIsDisplay();
				
		reportLog("3: MobileView Tap 'Enter the code'");
		mobileLoginPage.clickOnEnterTheCode();
		
		reportLog("4: MobileView Enter code screen shall be displayed with instruction message.");
		mobileLoginPage.verifyInstructionMessageText(enterTheCodeScreenInstructionMessage);
				
		reportLog("5: MobileView Enter the Text code (Incorrect 12 digits)");
		mobileLoginPage.enterTheRegistrationCode(registrationIncompleteCode);
		
		reportLog("5.1: MobileView Register screen with instruction message is displayed ");
		mobileLoginPage.verifyInstructionMessageText(enterTheCodeScreenInstructionMessage);
		
		reportLog("5.2: MobileView Verify 16-digit Registered code is display");
		mobileLoginPage.verifyRegistratedCode(registrationIncompleteCode);
		
		reportLog("5.3: MobileView Submit Button is DISABLED");		
		mobileLoginPage.verifySubmitButtonIsDisabled();
		
		reportLog("6: MobileView Try to Enter the Incorrect Text Code (17 digit).");
		mobileLoginPage.enterTheRegistrationCode(registrationIncorrectCode);
		
		reportLog("6.1: MobileView Submit Button is ENABLED ");		
		mobileLoginPage.verifySubmitButtonIsEnabled();
		
		reportLog("7: MobileView Tap Submit Button");
		mobileLoginPage.clickOnSubmitButton();
		
		reportLog("7.1: MobileView Text PIN code is verified by the system and determined not to be correct");
		mobileLoginPage.verifyIncorrectRegistrationCodeErrorMessage();
		
		reportLog("7.2: MobileView Register screen with instruction message");
		mobileLoginPage.verifyInstructionMessageText(enterTheCodeScreenInstructionMessage);
		
		reportLog("7.3: MobileView 'Try Again' button");
		mobileLoginPage.verifyTryAgainButtonDisplayed();
		
		reportLog("7.4: MobileView Fail Indicator -' Cross mark'");
		mobileLoginPage.verifyCrossMarkButtonDisplayed();
		
		reportLog("8: MobileView Tap Try Again button");
		mobileLoginPage.clickOnTryAgainButton();
		
		reportLog("8.1 MobileView Verify registration screen display");
		mobileLoginPage.verifyRegisterScreenForEnterCodeIsDisplay();
		
		reportLog("8.2: MobileView Enter the Correct Text code ");
		mobileLoginPage.enterTheRegistrationCode(registrationCode);
		
		reportLog("8.3: MobileView click Submit button");
		mobileLoginPage.clickOnSubmitButton();
		mobileLoginPage.verifyContinueButtonIsDisplay();
		
		reportLog("9.1: MobileView Register screen with instruction message is displayed ");
		mobileLoginPage.verifyInstructionMessageText(successRegisterInstrucrionMessage);
		
		reportLog("9.2: MobileView Verify Success Indicator - Tick mark");
		mobileLoginPage.verifySuccessIndicatorTickMarkIconIsDisplay();
		
		reportLog("9.3: MobileView Verify Continue button is display");
		mobileLoginPage.verifyContinueButtonIsDisplay();
		
		reportLog("9.4: MobileView click on Continue button ");
		mobileLoginPage.clickOnContinueButton();
		
		reportLog("10: MobileView Verify Terms and Conditions screen with instruction message shall be displayed");
		mobileLoginPage.verifyTermsAndConditionPageIsDisplay(termsAndCondtionInstructionMessage);
		
		reportLog("10.1: MobileView click on Accept button");
		mobileLoginPage.clickOnAcceptBtn();	
		
		reportLog("MobileView User Enter user pin in input field and click on submit button");
		mobileLoginPage.verifyLoginScreenDisplay();
		
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
