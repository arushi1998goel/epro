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

public class FPTC_525_EstablishIdentityParticipant_SIP extends BaseTest{
	
	@BeforeClass
	public void executionOn(){
		exceutionOn="MobileAndWebExecution"; 
	}
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_525_EstablishIdentityParticipant_SIP(String driver) {
		super(driver);
	}
		
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());	
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		
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
		
		reportLog("1.1: MobileView As a Participant logged into the application");
		mobileLoginPage = androidSetUp();

		reportLog("1.2: MobileView Application launch and verify Register screen with instruction message,register the subject");
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.clickOnEnterTheCode();
		mobileLoginPage.enterTheRegistrationCode(registrationCode);
		mobileLoginPage.clickOnSubmitButton();
		mobileLoginPage.clickOnContinueButton();
		mobileLoginPage.verifyTermsAndConditionPageIsDisplay(termsAndCondtionInstructionMessage);
		mobileLoginPage.clickOnAcceptBtn();				
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-525 Test Case Name:Establish Identity (Participant)

	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-525_Device Registration ", groups = { "Mobile" })
	public void FPTC_525_verifyThatParticipantShouldBeAbleToCreateAnIdentity() throws Exception {
		
		reportLog("2: MobileView Create Identity' screen is displayed with instruction message.");
		mobileLoginPage.verifyTextOnScreen("Create Identity");
		mobileLoginPage.verifyInstructionMessageText(createIdentityInstructionMessage);
		
		reportLog("2.1: MobileView Pin and Confirm Pin edit boxes shall be displayed.");
		mobileLoginPage.verifyPinAndConfirmPINEditBoxesAreDisplay();
		
		reportLog("2.2: MobileView Next button shall be displayed in disabled state");
		mobileLoginPage.verifyNextButtonIsDisabled();
		
		reportLog("2.3: MobileView Avatar and Participant version label shall be displayed");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();
		
		reportLog("3: MobileView Enter different identity. ( pin and non-matching confirm pin details).");
		mobileLoginPage.enterPINCode(Mobile_Pin);
		mobileLoginPage.enterConfirmPINCode(Mobile_PinInCorrect);
		
		reportLog("3.1: MobileView Next button shall be displayed in disabled state");
		mobileLoginPage.verifyNextButtonIsDisabled();
		
		reportLog("3.2: MobileView Displays ‘(PINs do not match).");
		mobileLoginPage.verifyPinDontMatchTextDisplay();
		
		reportLog("4: MobileView Provide matching pin and confirm pin details and check the availability of Next button");
		mobileLoginPage.clearConfirmPINBox();
		mobileLoginPage.enterConfirmPINCode(Mobile_Pin);
						
		reportLog("4.1: MobileView Next button shall be displayed in enabled state.");
		mobileLoginPage.verifyNextButtonIsEnabled();
		
		reportLog("5: MobileView Click on Next button and check the functionality.");
		mobileLoginPage.clickOnNextButton();
		
		reportLog("5.1: MobileView Security Questions and Answers set-up is displayed with instruction message. ");
		mobileLoginPage.verifyInstructionMessageText(createIdentityQuestionMessage);
		mobileLoginPage.verifyChooseAQuestionDisplay();
		
		reportLog("5.2: MobileView The number of security questions is displayed in the instruction message.");
		mobileLoginPage.clickOnChooseAQuestionOption();
		mobileLoginPage.clickOnCancelButton();
		
		reportLog("5.3: MobileView Next button shall be displayed in disabled state.");
		mobileLoginPage.verifyNextButtonIsDisabled();
					
		reportLog("5.4: MobileView Avatar, Participant version labels shall be displayed ");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();
		
		reportLog("6: MobileView Select Security Questions and Provide Answers.");
		mobileLoginPage.chooseAQuestion(Choose_QuestionPin);		
				
		reportLog("6.1: MobileView System accepts the provided Security Questions and Answers Identity. ");
		mobileLoginPage.enterAnAnswer(Choose_QuestionAnswer);
		
		reportLog("6.2: MobileView Next button shall be displayed in enabled state.");
		mobileLoginPage.verifyNextButtonIsEnabled();
		
		reportLog("7: MobileView Click on 'Next' button.");
		mobileLoginPage.clickOnNextButton();
		
		reportLog("7.1: MobileView 'CreateIdentity' page shall be displayed with instruction message. ");
		mobileLoginPage.verifyInstructionMessageText(createIdentityInstruction);
		
		reportLog("7.2: MobileView 'Continue' button shall be displayed in enabled state. Check mark, avatar, 'participant version' label shall be displayed.");
		mobileLoginPage.verifyContinueButtonIsDisplay();
		mobileLoginPage.verifyTextOnScreen(CheckListText);
		mobileLoginPage.verifyTextOnScreen(DigitPinConfirmation);
		mobileLoginPage.verifyTextOnScreen(SecretQuestionText);
		
		reportLog("8: MobileView Click on 'Continue' button.");
		mobileLoginPage.clickOnContinueButton();		
		
		reportLog("8.1: MobileView Login with configured Pin");
		mobileLoginPage.verifySignInScreenDisplayed();
		
		reportLog("9: MobileView Enter the Pin details and check the state of submit button.");
		mobileLoginPage.enterPINCode(Mobile_Pin);
		mobileLoginPage.clickOnSubmitButton();
		
		reportLog("10: MobileView System allows access to the application.");
		mobileLoginPage.verifyUserLogin();
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
