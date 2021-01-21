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

public class FPTC_783_VerifyApplicationSideMenuStudyInformationOptionDetails_SIP extends BaseTest {
	
	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_783_VerifyApplicationSideMenuStudyInformationOptionDetails_SIP(String driver) {
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
	 * Test Case Id: FP-TC-783 Test Case Name: Verify the application's side menu - study information option details. [Study name and sponsor details are defined] 
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-783_Verify the application's side menu - study information option details. [Study name and sponsor details are defined] ", groups = { "Mobile" })
	public void FPTC_783_VerifyApplicationSideMenuStudyInformationOptionDetails() throws Exception {

		reportLog("2: As a participant, launch the application.");		
		reportLog("2.1: MobileView: Signin page shall be displayed with an instruction message.");
		mobileLoginPage.verifySignInScreenDisplayed();
		
		reportLog("2.2: MobileView: PIN edit box shall be displayed.");
		mobileLoginPage.verifySignInScreenWithPINEditBox();
				
		reportLog("2.3: MobileView: Submit button shall be displayed in disabled state");
		mobileLoginPage.verifySubmitButtonIsDisabled();
		
		reportLog("2.4: I forget the PIN link shall be displayed. ");
		mobileLoginPage.verifyForgetPINLinkDisplay();
		
		reportLog("2.5: Avatar and participant version label shall be displayed.");
		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();
		
		reportLog("3: MobileView Enter valid pin and then click on submit button.");
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);
				
		reportLog("3.1: MobileView The Home Page shall be displayed for the user. Based on the number of already configured:");
		dashborad.verifyHomePageDisplay();

		reportLog("4: MobileView Click on side menu in the top left corner of the home page.");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		
		reportLog("4.2: MobileView Application shall display following options: My Account, Medications, My schedule, Study Information, Contacts, Settings, Help & Tutorials options shall be displayed.");
		sideMenu.verifySideMenuOptions();
		
		reportLog("5: MobileView Click on 'Study Information' option.");
		sideMenu.clickOnStudyInformation();
		
		reportLog("5.2: MobileView Study Information shall be displayed.");
		sideMenu.verifyPageTitle("Study Information");
		
		reportLog("5.3: MobileView Study Name and Sponsor details: Shall be displayed ");
		sideMenu.verifyTextOnScreen(studyName);
		sideMenu.verifyTextOnScreen("16Org");
		
		reportLog("5.4: MobileView Move to message tab");
		sideMenu.clickOnBackIcon();
		
		reportLog("5.5: MobileView Exit Application");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();
		sideMenu.verifyApplicationExit();
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
