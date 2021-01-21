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
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_813_HomeScreenTopMenuAndSideMenuForParticipantOrObserver_SIP extends BaseTest{
	private String subjectName = "Subject_" + generateRandomString(3);
	
	@BeforeClass
	public void executionOn(){
		exceutionOn="MobileAndWebExecution"; 
	}
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_813_HomeScreenTopMenuAndSideMenuForParticipantOrObserver_SIP(String driver) {
		super(driver);
	}
		
	@BeforeMethod
	public void getTestData() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("EPROQuestionnairesOnlyStudy");
		visitName = properties.getProperty("EPROMandatoryVisit");

		reportLog("Go to Portal Side to Create Subject and complete visit for displaying questionnaires");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		System.out.println(subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitName);
		subjectDetailPage.clickOnInitiateVisitIcon();
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("Get the Registration Code From The Subject");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);

		subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);

		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();
		loginPage.logoutApplication();
		/* Subject Created Successfully */
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-813  Test Case Name: Home Screen - Top Menu and Side Menu for Participant/Observer
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-813_Home Screen - Top Menu and Side Menu for Participant/Observer ", groups = { "Mobile" })
	public void FPTC_813_HomeScreenTopMenuAndSideMenuForParticipantOrObserver() throws Exception {
		
		reportLog("1.1: As a Participant logged into the application");
		mobileLoginPage = androidSetUp();
		

		reportLog(
				"1.2: MobileView Application launch and verify Register screen with instruction message,register the subject");
	    mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.clickOnEnterTheCode();
		mobileLoginPage.enterTheRegistrationCode(registrationCode);
		mobileLoginPage.clickOnSubmitButton();
		mobileLoginPage.clickOnContinueButton();

		reportLog("1.3: MobileView Enter Pin");
		mobileLoginPage.enterPINCode(Mobile_Pin);
		mobileLoginPage.enterConfirmPINCode(Mobile_Pin);
		mobileLoginPage.clickOnNextButton();

		reportLog("1.4: MobileView The number of security questions is displayed in the instruction message.");

		mobileLoginPage.chooseAQuestion(Choose_QuestionPin);
		mobileLoginPage.enterAnAnswer(Choose_QuestionAnswer);
		mobileLoginPage.clickOnNextButton();
		mobileLoginPage.clickOnContinueButton();

		reportLog("1.5: MobileView Login with configured Pin");
		mobileLoginPage.verifySignInScreenDisplayed();
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);
		
		reportLog("2 : MobileView The Questionnaire Tab (List) shall be displayed for the user (questionnaires present as per configuration).");
		dashborad.verifyUserLogin();
		dashborad.verifyTopMenuOptionsQuestionnaireOnlyDiplay();

		reportLog("3.1: MobileView Tap to 'Side Menu' Icon");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		
		reportLog("3.2: MobileView The Menu Option List shall be displayed for the user");
		sideMenu.verifySideMenuOptions();
				
	}
	
	@AfterMethod
	public void deactivateUser(ITestResult result) throws InterruptedException {

		if (ITestResult.SUCCESS == result.getStatus()) {
			
			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
			studyNavigatorDashBoardPage.selectStudy(studyName);
			studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
			studyNavigatorDashBoardPage
					.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
			subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
			subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
		} else {
			Log.error("Deactivation Not needed");
		}
	}
	
	
}
