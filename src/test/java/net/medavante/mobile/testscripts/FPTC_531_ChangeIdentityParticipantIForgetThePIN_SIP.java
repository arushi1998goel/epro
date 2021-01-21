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

public class FPTC_531_ChangeIdentityParticipantIForgetThePIN_SIP extends BaseTest {

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_531_ChangeIdentityParticipantIForgetThePIN_SIP(String driver) {
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
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("1.1: MobileView As a Participant logged into the application");
		mobileLoginPage = androidSetUp();

		reportLog(
				"1.2: MobileView Application launch and verify Register screen with instruction message,register the subject");
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.clickOnEnterTheCode();
		mobileLoginPage.enterTheRegistrationCode(registrationCode);
		mobileLoginPage.clickOnSubmitButton();
		mobileLoginPage.clickOnContinueButton();
		mobileLoginPage.verifyTermsAndConditionPageIsDisplay(termsAndCondtionInstructionMessage);
		mobileLoginPage.clickOnAcceptBtn();

		mobileLoginPage.verifyInstructionMessageText(createIdentityInstructionMessage);
		mobileLoginPage.enterPINCode(Mobile_Pin);
		mobileLoginPage.enterConfirmPINCode(Mobile_Pin);

		mobileLoginPage.clickOnNextButton();

		mobileLoginPage.verifyInstructionMessageText(createIdentityQuestionMessage);
		mobileLoginPage.verifyChooseAQuestionDisplay();

		mobileLoginPage.chooseAQuestion(Choose_QuestionPin);
		mobileLoginPage.enterAnAnswer(Choose_QuestionAnswer);
		mobileLoginPage.clickOnNextButton();
		mobileLoginPage.clickOnContinueButton();
		
		

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-531 Test Case Name:To verify that a participant can
	 * change their identity if they forgot their PIN.
	 * 
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-531_Device Registration ", groups = { "Mobile" })
	public void FPTC_531_verifyThatParticipantCanChangeTheirIdentityIfTheyForgetTheirPIN() throws Exception {
		
		reportLog("2:MobileView As a participant,launch the application.");
		mobileLoginPage.verifySignInScreenDisplayed();
				
		reportLog("2.1:MobileView Sign In screen shall be displayed with the instruction message.");
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage);
		
		reportLog("2.2:MobileView PIN edit box shall be displayed.");
		mobileLoginPage.verifySignInScreenWithPINEditBox();
		
		reportLog("2.3:MobileView Submit button shall be displayed in disabled state");
		mobileLoginPage.verifySubmitButtonIsDisabled();
		
		reportLog("2.4:MobileView I forgot the PIN link shall be displayed.");
		mobileLoginPage.verifyForgetPINLinkDisplay();
		
		reportLog("3: MobileView Participant donot remember the pin value.");
		reportLog("3.1: MobileView Click on 'I forgot the PIN' link.");
		mobileLoginPage.clickOnForgetPINLink();

		reportLog("3.2: MobileView Reset Pin page shall be displayed with instruction message ");
		mobileLoginPage.verifyInstructionMessageText(resetPINInstructionMessage);

		reportLog("3.3: MobileView security questions shall be displayed.");
		mobileLoginPage.verifyTextOnScreen(Choose_QuestionPin);
		
		reportLog("3.4: MobileView Next button shall be displayed in disabled state ");
		mobileLoginPage.verifyNextButtonIsDisabled();

		reportLog("3.5: MobileView Avatar and participant version label shall be displayed");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();

		reportLog("4: MobileView Enter valid answer(s) to the security question(s).");
		mobileLoginPage.enterAnAnswer(Choose_QuestionAnswer);

		reportLog("4.1: MobileView Next button shall be displayed in enabled state.");
		mobileLoginPage.verifyNextButtonIsEnabled();

		reportLog("5: MobileView Click on 'Next' button.");
		mobileLoginPage.clickOnNextButton();

		reportLog("5.1: MobileView 'Create Identity' screen shall be displayed with instruction message ");
		mobileLoginPage.verifyTextOnScreen("Create Identity");
		mobileLoginPage.verifyInstructionMessageText(createIdentityInstructionMessage);

		reportLog("5.2: MobileView Pin and Confirm pin edit boxes shall be displayed. ");
		mobileLoginPage.verifyPinAndConfirmPINEditBoxesAreDisplay();

		reportLog("5.3: MobileView Next button shall be displayed in disabled state.");
		mobileLoginPage.verifyNextButtonIsDisabled();

		reportLog("6: MobileView Enter pin and confirm pin details ");
		mobileLoginPage.enterPINCode(resetMobilePin);
		mobileLoginPage.enterConfirmPINCode(resetMobilePin);

		reportLog("6.1:MobileView then click on next button.");
		mobileLoginPage.clickOnNextButton();

		reportLog("6.2: MobileView Sign in page shall be displayed with the instructional message ");
		mobileLoginPage.verifySignInScreenWithPINEditBox();
		mobileLoginPage.verifySignInScreenDisplayed();
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage);

		reportLog("6.3: MobileView PIN edit box shall be displayed.");
		mobileLoginPage.verifySignInScreenWithPINEditBox();

		reportLog("6.4: MobileView submit button shall be displayed in disabled state");
		mobileLoginPage.verifySubmitButtonIsDisabled();

		reportLog("6.5: MobileView I forgot the PIN link shall be displayed ");
		mobileLoginPage.verifyForgetPINLinkDisplay();

		reportLog("7:MobileView In the Sign in page, enter valid Pin number and click on submit button.");
		mobileLoginPage.enterPINCode(resetMobilePin);
		mobileLoginPage.clickOnSubmitButton();
		
		reportLog("7.1:MobileView Signin shall be successful and Home page shall be displayed.");
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
