package net.medavante.mobile.testscripts;

import java.util.Properties;

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

public class FPTC_799_B2BQuestionnairesCompletionScreenNotPresented_SIP extends BaseTest {

	private String questionnairesValue;

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_799_B2BQuestionnairesCompletionScreenNotPresented_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("EPROQuestionnairesStudy");
		visitName = properties.getProperty("EPROMandatoryVisit");
		registrationCode = properties.getProperty("RegistrationCodeQuestionnaires");
		subjectName = properties.getProperty("SubjectNameQuesionnaires");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-799 Test Case Name: B2BQuestionnaires Completion
	 * Screen Not Presented
	 * 
	 * ====================================================================================================================
	 * 
	 * 
	 */

	@Test(description = "FP-TC-799_QuestionnairesCompletionScreenNotPresented", groups = { "Mobile" })
	public void FPTC_799_verifyQuestionnairesCompletionScreenNotPresented() throws Exception {

		reportLog("1.1: As a Participant logged into the application");
		mobileLoginPage = androidSetUp();

		reportLog("1.5: MobileView Login with configured Pin");
		dashborad = mobileLoginPage.scanCodeOrEnterPin(registrationCode, Mobile_Pin);
		dashborad.verifyUserLogin();

		reportLog(
				"2.1:MobileView Not Started Questionnaires Home Page Pending Questionnaires fields shall display the right value");
		questionnairesValue = dashborad.getQuestionnairesValue();
		dashborad.verifyHomeDashbaordValues("Pending", questionnairesValue);

		reportLog("2.2:MobileView Questionnaires Tab Alert shall display the right value");
		dashborad.verifyQuestionnairesTabAlertShowingCorrectValue(questionnairesValue);

		reportLog("3.1:MobileView Tap to Questionnaires Tab");
		questionnairesPage = dashborad.clickOnQuestionnairesTab();

		reportLog(
				"3.2:MobileView Questionnaires List shall be displayed for user listing all configured pending questionnaires.");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog(" MobileView Configure In Progress Question");
		questionnairesPage.selectQuestionForms(Constants.MobileThree);
		questionnairesPage.filltheQuestionContainingCheckBox();
		questionnairesPage.tabOnProgressBarNextButton();
		questionnairesPage.clickOnBackIcon();
		questionnairesPage.clickOnYesOption();

		reportLog("4.1: MobileView Tab To Questionaires In Progress");
		questionnairesPage.selectQuestionForms(Constants.MobileThree);

		reportLog("4.2: MobileView Start page Not Displayed ");
		questionnairesPage.verifyQuestionStartingPageWithDescriptionIsNotShowing();

		reportLog("4.3 : MobileView progress bar is displayed in Design 2");
		questionnairesPage.verifyProgressBarDesign2IsPresent();

		reportLog("4.4 :MobileView  Answer all questions ");
		questionnairesPage.answerAllCheckbox();

		reportLog("4.5 : MobileView Completion page not displayed ");
		questionnairesPage.verifyContinueButtonAndCompletionPageIsNotDisplayed();

		reportLog("5.1: MobileView Tab To Questionaires Not started");
		questionnairesPage.selectQuestionForms(Constants.Mat_Mob1);

		reportLog("5.2:MobileView  First question is displayed ");
		questionnairesPage.verifyQuestionCanBeAnswered();

		reportLog("5.3 : MobileView progress bar is displayed in Design 2");
		questionnairesPage.verifyProgressBarDesign2IsPresent();

		reportLog("5.4 :MobileView  Answer all questions ");
		questionnairesPage.answerAllCheckbox();

		reportLog("5.5 : MobileView Completion page not displayed ");
		questionnairesPage.verifyContinueButtonAndCompletionPageIsNotDisplayed();

		reportLog("5.6: MobileView Then the user shall be able to navigate to either Home Page or Questionnaire List.");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog("MobileView Exit Application");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();
		sideMenu.verifyApplicationExit();
		clickOnConnectAppIcon();

	}

	@AfterMethod()
	public void deactivateUser(ITestResult result) throws InterruptedException {
		if (ITestResult.SUCCESS == result.getStatus()) {
			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
			studyNavigatorDashBoardPage
					.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
			subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
			subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
		}
	}
}