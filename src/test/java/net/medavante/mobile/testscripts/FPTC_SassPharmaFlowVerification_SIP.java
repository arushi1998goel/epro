package net.medavante.mobile.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.selenium.core.BaseTest;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_SassPharmaFlowVerification_SIP extends BaseTest {

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_SassPharmaFlowVerification_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("SassPharmaStudy");
		visitBaseLine = properties.getProperty("SleepDiaryBaseLineVisitFirst");
		visitTreatmentPhase = properties.getProperty("SleepDiaryBaseLineVisitSecond");

		reportLog("Go to Portal Side to Create Subject and Complete visit");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitBaseLine);
		subjectDetailPage.clickOnInitiateVisitIcon();
		subjectDetailPage.setCurrentDate();

		reportLog("Scheduling Treatment Visit After Seven day");
		subjectDetailPage.clickOnCalendarVisitRow(visitTreatmentPhase);
		subjectDetailPage.clickOnInitiateVisitIcon();
		subjectDetailPage.setScheduledDateAfterSevenDays();

		reportLog("Completion of first visit");
		subjectDetailPage.clickOnCalendarVisitRow(visitBaseLine);
		subjectDetailPage.selectRaterForCalenderVisitDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label,
				Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("Get the Registration Code From The Subject");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();
		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Name: Treatment Of Subject who get sleepy while work
	 * 
	 * ====================================================================================================================
	 * 
	 * 
	 */

	@Test(description = "Treatment Of Subject who get sleepy while work", groups = { "Mobile" })
	public void verificationOfSassPharmaFlow() throws Exception {

		/*-------------------------------Participant Steps-------------------------------*/

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
		dashborad.verifyUserLogin();

		reportLog("2:Add Medications for one week");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnMedications();
		sideMenu.clickOnAddIcon();
		sideMenu.fillAllDetailsForMedication();

		reportLog("3.1:MobileView Tap on Questionnaires tab");
		questionnairesPage = dashborad.clickOnQuestionnairesTab();

		reportLog("3.2:Todays Question list is present");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog("3.3:Verfiy questionniares present for all 6 remaining days");
		questionnairesPage.verifyTomorrowQuestionnairesListPresent();
		questionnairesPage.verifyLaterQuestionnairesListPresent();

		reportLog("3.4:Select Todays Form for completing daily dairy");
		questionnairesPage.selectQuestionForms(Constants.NSCLCSAQ);
		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);
		questionnairesPage.clickOnStartQuestion();
		questionnairesPage.answerAllRadioButton();

		reportLog("3.5: Complete the Form");
		questionnairesPage.verifyCompletionSucessMessage(Constants.Questionnaires_CompletionMessage);
		questionnairesPage.verifyContinueButtonDisplayed();
		dashborad=questionnairesPage.clickOnContinue();
		
		reportLog("3.6:Verify the home page");
		dashborad.verifyHomePageDisplay();

		reportLog("4:MobileView Exit Application");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();
		sideMenu.verifyApplicationExit();

	}

}
