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

public class FPTC_950_AuthenticationResetPINObserver_SIP extends BaseTest {

	private String subjectName = "AutoEpro" + generateRandomString(5), observerRelation1;
	
	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_950_AuthenticationResetPINObserver_SIP(String driver) {
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

		reportLog("Get the Registration Code From The Observer");
		
		subjectDetailPage.configureObsreverForMobile(observerRelation1, SuperAdminUN, SuperAdminPW);
		subjectDetailPage.clickOnMobileObserverQrIcon();
		registrationCodeObserver = subjectDetailPage.getRegistrationCodeofObserver();
		subjectDetailPage.clickOnObserverRegistrationPopUpCloseButton();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("1.1: MobileView As a Participant logged into the application");
		mobileLoginPage = androidSetUp();

		reportLog(
				"1.2: MobileView Application launch and verify Register screen with instruction message,register the subject");
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.clickOnEnterTheCode();
		mobileLoginPage.enterTheRegistrationCode(registrationCodeObserver);
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
	 * Test Case Id: FP-TC-950 Test Case Name: To verify authentication
	 * functionality for user to gain access to the application.
	 * 
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-950_To verify authentication functionality for user to gain access to the application.", groups = {
			"Mobile" })
	public void FPTC_950_AuthenticationResetPINObserver() throws Exception {

		reportLog("1: MobileView Launch the application as an observer");
		reportLog("1.1: MobileView Verify Sign-In screen display");
		mobileLoginPage.verifySignInScreenDisplayed();

		reportLog("2.1: MobileView Sign-In screen is displayed with instruction message.");
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage);

		reportLog("3: MobileView In the Sign-In page, click to 'I forgot the PIN'");
		mobileLoginPage.clickOnForgetPINLink();

		reportLog("3.1: MobileView RESET PIN Screen shall be displayed with an instruction message");
		mobileLoginPage.verifyInstructionMessageText(resetPINInstructionMessage);

		reportLog("3.2: MobileView Upon providing CORRECT answer to Security question");
		mobileLoginPage.enterAnAnswer(Choose_QuestionAnswer);

		reportLog("3.3: MobileView Tapping to 'Next' Button");
		mobileLoginPage.clickOnNextButton();

		reportLog("3.4: MobileView User is navigated to CREATE IDENTITY Screen.");
		mobileLoginPage.verifyInstructionMessageTextOnCreateIdentity(createIdentityInstruction);
		mobileLoginPage.verifyCreateIdentityScreenWithPINAndEditPIN();

		reportLog("4: MobileView Enter valid PIN and matching Confirm PIN details and then click to 'Next' Button.");
		mobileLoginPage.enterPINCode(resetMobilePin);
		mobileLoginPage.enterConfirmPINCode(resetMobilePin);
		mobileLoginPage.clickOnNextButton();

		reportLog("4.1: MobileView Sign-In page shall be displayed with an instruction message.");
		mobileLoginPage.verifyInstructionMessageText(signInScreenInstructionMessage);

		reportLog("4.2: MobileView PIN edit box shall be displayed");
		mobileLoginPage.verifySignInScreenWithPINEditBox();

		reportLog("4.3: MobileView Submit button shall be displayed in disabled state.");
		mobileLoginPage.verifySubmitButtonIsDisabled();

		reportLog("4.4: MobileView I forgot the PIN link shall be displayed.");
		mobileLoginPage.verifyForgetPINLinkDisplay();

		reportLog("4.5: MobileView Avatar and Observer version shall be displayed.");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();

		reportLog("5: MobileView Enter valid new PIN and then click to Submit Button.");
		dashborad = mobileLoginPage.loginUser(resetMobilePin);

		reportLog("5.1: MobileView Home page shall be displayed for the Observer.");
		dashborad.verifyUserLogin();
		
		reportLog("5.2: MobileView Exit from the application");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		dashborad.clickOnExitApplication();
		clickOnConnectAppIcon();
		
	}

	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) throws InterruptedException {
		if(ITestResult.SUCCESS==result.getStatus()) {
			
			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			//studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
			
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
