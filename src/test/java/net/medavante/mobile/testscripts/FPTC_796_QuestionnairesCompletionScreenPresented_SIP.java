package net.medavante.mobile.testscripts;

import java.util.Properties;


import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;

import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_796_QuestionnairesCompletionScreenPresented_SIP extends BaseTest {

	private String subjectName = "AutoEpro" + generateRandomString(5), questionnairesValue;

	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_796_QuestionnairesCompletionScreenPresented_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("EPROQuestionnairesStudy");
		visitName = properties.getProperty("EPROMandatoryVisit");

		reportLog("Go to Portal Side to Create Subject and complete visit for displaying questionnaires");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		System.out.println(subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Get the Registration Code From The Subject");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();

		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitName);
		subjectDetailPage.clickOnInitiateVisitIcon();

		reportLog("Completion of first mandatory visit");
		subjectDetailPage.selectRaterForCalenderVisitDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label,
				Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		/* Subject Created Successfully */
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-796 Test Case Name: Questionnaires Completion Screen
	 * Presented
	 * ====================================================================================================================
	 * 
	 * 
	 */

	@Test(description = "FP-TC-796_QuestionnairesCompletionScreenPresented", groups = { "Mobile" })

	public void FPTC_796_verifyQuestionnairesCompletionScreenPresented() throws Exception {

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

		reportLog(
				"2.1: MobileView Not Started Questionnaires Home Page Pending Questionnaires fields shall display the right value");
		questionnairesValue = dashborad.getQuestionnairesValue();
		dashborad.verifyHomeDashbaordValues("Pending", questionnairesValue);

		reportLog("2.2: MobileView Questionnaires Tab Alert shall display the right value");
		dashborad.verifyQuestionnairesTabAlertShowingCorrectValue(questionnairesValue);

		reportLog("3.1:MobileView Tap to Questionnaires Tab");
		questionnairesPage = dashborad.clickOnQuestionnairesTab();

		reportLog(
				"3.2:MobileView Questionnaires List shall be displayed for user listing all configured pending questionnaires.");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog("4.1:MobileView Tap to Quest.1");
		questionnairesPage.selectQuestionForms(Constants.MForm);

		reportLog("4.2:MobileView View Questionnaire Start Page ");
		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);

		reportLog("4.3:MobileView View the progress bar which is displayed based on configuration ");
		questionnairesPage.clickOnStartQuestion();
		questionnairesPage.verifyProgressBarShowing();

		reportLog("4.4:MobileView Answer all questions ");
		questionnairesPage.answerAllTextBoxField(Constants.QuestionnairesAnswer);

		reportLog(
				"4.5:MobileView View Questionnaire Completion Page with its details:Questionnaire Name , Completion Message");
		questionnairesPage.verifyCompletionSucessMessage(Constants.Questionnaires_CompletionMessage);

		reportLog("4.6:MobileView verify Continue Button and tab");
		questionnairesPage.verifyContinueButtonDisplayed();
		questionnairesPage.clickOnContinue();

		reportLog("4.7:MobileView The user shall be able to navigate back to Questionnaire List by tapping to the Continue Button.	");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog("5.1:MobileView Tap to Quest.2");
		questionnairesPage.selectQuestionForms(Constants.Mat_Mob);

		reportLog("5.2:MobileView Start Page is Not displayed ");
		questionnairesPage.verifyQuestionStartingPageWithDescriptionIsNotShowing();

		reportLog("5.3:MobileView First Question of Questionnaire is displayed ");
		questionnairesPage.verifyQuestionCanBeAnswered();

		reportLog("5.4:MobileView  View the progress bar which is displayed based on configuration :progress bar is displayed in Design 3 - empty");
		questionnairesPage.verifyProgressBarNotShowing();

		reportLog("5.5:MobileView  Answer all questions ");
		questionnairesPage.answerAllCheckbox();

		reportLog(
				"5.6:MobileView View Questionnaire Completion Page with its details:Questionnaire Name , Completion Message");
		questionnairesPage.verifyCompletionSucessMessage(Constants.Questionnaires_CompletionMessage);

		reportLog("5.7:MobileView verify Continue Button and tab");
		questionnairesPage.verifyContinueButtonDisplayed();
		questionnairesPage.clickOnContinue();

		reportLog(
				"5.9:MobileView The user shall be able to navigate back to Questionnaire List by tapping to the Continue Button.	");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog("MobileView Exit Application");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();
		sideMenu.verifyApplicationExit();
		clickOnConnectAppIcon();

		
	}

	@AfterMethod
	public void updateSubjectValueInPropertiesFile() throws InterruptedException {
		{

			Configuration.updatePropertyTestData("RegressionTestData", "RegistrationCodeQuestionnaires",
					registrationCode);
			Configuration.updatePropertyTestData("RegressionTestData", "SubjectNameQuesionnaires", subjectName);

		}
	}
}
