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

public class FPTC_896_QuestionnaireListEnforceAssessmentOrderParticipantObserver_SIP extends BaseTest {

	private String subjectName = "AutoEpro" + generateRandomString(5), observerRelation1;


	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_896_QuestionnaireListEnforceAssessmentOrderParticipantObserver_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyQuestionnairesObserver");
		visitName = properties.getProperty("VisitObserver");
		observerRelation1 = properties.getProperty("Auto_Observer_Relation1");

	     reportLog("Go to Portal Side to Create Subject and complete visit for displaying questionnaires");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		
		System.out.println(subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("Get the Registration Code From The Subject/Observer");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCodeParticipant = subjectDetailPage.getRegistrationCodeOfSubject();		
		System.out.println(registrationCodeParticipant);
		
		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();
		subjectDetailPage.configureObsreverForMobile(observerRelation1, SuperAdminUN, SuperAdminPW);
		subjectDetailPage.clickOnMobileObserverQrIcon();
		registrationCodeObserver = subjectDetailPage.getRegistrationCodeofObserver();
		subjectDetailPage.clickOnObserverRegistrationPopUpCloseButton();
		
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
		/* Subject Created Successfully */

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-896 Test Case Name: Questionnaire list - Enforce
	 * assessment order (Participant/Observer)
	 * 
	 * ====================================================================================================================
	 * 
	 * 
	 */

	@Test(description = "FP-TC-896_Questionnaire list - Enforce assessment order (Participant/Observer)", groups = {
			"Mobile" })
	public void FPTC_896_verifyQuestionnaireListEnforceAssessmentOrderParticipantObserver() throws Exception {

		/*-------------------------------Participant Steps-------------------------------*/

		reportLog("1.1: MobileView Launch the application as a Participant");

		mobileLoginPage = androidSetUp();
	
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.configurationForRegisterTheSubject(registrationCodeParticipant);
		mobileLoginPage.verifySignInScreenDisplayed();
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);
		
		
		reportLog("2:MobileView Application is successfully launched, Home screen is displayed.");
		dashborad.verifyUserLogin();

		reportLog("3.1:MobileView Tap on Questionnaires tab");
		questionnairesPage = dashborad.clickOnQuestionnairesTab();

		reportLog("3.2:MobileView Questionnaires tab is opened with questionnaires list displayed.");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog(
				"4.1:MobileView Questionnaires tab is opened with questionnaires list displayed.User shall be able to see all 3 questionnaires (A,B,C) grouped for Today (No other group displayed): ");
		reportLog(
				"4.2:MobileView Questionnaire A is active,MobileView Questionnaire B is disabled,MobileView Questionnaire C is disabled ");

		questionnairesPage.verifyTheAppearenceOfForm();

		reportLog("4.5:MobileView All questionnaires displayingdue in minute(s) where X is the end time");
		questionnairesPage.verifyDueTimeIsDisplayedForQuestionnaires(Constants.Mat_Mob, Constants.Due_In_Time);
		questionnairesPage.verifyDueTimeIsDisplayedForQuestionnaires(Constants.Mat_Mob1, Constants.Due_In_Time);
		questionnairesPage.verifyDueTimeIsDisplayedForQuestionnaires(Constants.MForm, Constants.Due_In_Time);

		reportLog("4.6:MobileView Verify Pending Icon Present with form");
		questionnairesPage.verifyPendingIconPresentWithQuestions(Constants.Mat_Mob);

		reportLog("5.1:MobileView Tap on Questionnaire A.");
		questionnairesPage.selectQuestionForms(Constants.Mat_Mob);

		reportLog(
				"5.2:MobileView Questionnaire A is opened on Start screen or first question - based on configuration.");
		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);

		reportLog("6:MobileView Complete the questionnaire A and click to Continue Button on Completion Page.");
		questionnairesPage.clickOnStartQuestion();
		questionnairesPage.answerAllCheckbox();
		questionnairesPage.verifyCompletionSucessMessage(Constants.Questionnaires_CompletionMessage);
		questionnairesPage.verifyContinueButtonDisplayed();
		questionnairesPage.clickOnContinue();

		reportLog("6.2:MobileView Verify Completed Icon Present with form");
		questionnairesPage.verifyCompletedIconPresentWithQuestionsAndNotEditable(Constants.Mat_Mob);

		reportLog(
				"7.1:MobileView User shall be able to see the Start Page of Questionnaire B with its all detail:Start Page Start Button");
		questionnairesPage.selectQuestionForms(Constants.Mat_Mob1);
		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);
		questionnairesPage.clickOnStartQuestion();

		reportLog("8.1:MobileView Tap BACK button in upper left corner.");
		questionnairesPage.clickOnBackButtonIconOnQuestionPage();
		questionnairesPage.clickOnYesOption();

		reportLog("8.2:MobileView Questionnaire List shall be displayed.");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog(
				"9.1:MobileView Questionnaire A in disabled status, B in active status (not started),C in disabled status (in the window, but disabled.) ");
		questionnairesPage.verifyTheAppearenceOfForm();

		reportLog("9.2:MobileView Verify Pending Icon Present with form");
		questionnairesPage.verifyPendingIconPresentWithQuestions(Constants.Mat_Mob1);

		reportLog("10.1:MobileView Tap on Questionnaire B.");
		questionnairesPage.selectQuestionForms(Constants.Mat_Mob1);

		reportLog(
				"10.2:MobileView Questionnaire B is opened on Start screen or first question - based on configuration.");
		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);

		reportLog("10.3:MobileView Complete the questionnaire B and click to Continue Button on Completion Page.");
		questionnairesPage.clickOnStartQuestion();
		questionnairesPage.answerAllCheckbox();
		questionnairesPage.verifyCompletionSucessMessage(Constants.Questionnaires_CompletionMessage);
		questionnairesPage.verifyContinueButtonDisplayed();
		questionnairesPage.clickOnContinue();

		reportLog("10.4:MobileView Verify Completed Icon Present with form");
		questionnairesPage.verifyCompletedIconPresentWithQuestionsAndNotEditable(Constants.Mat_Mob1);

		reportLog("10.5:MobileView Verify Pending Icon Present with form");
		questionnairesPage.verifyPendingIconPresentWithQuestions(Constants.MForm);

		reportLog(
				"10.6:MobileView User shall be able to see the Start Page of Questionnaire C with its all detail:Start Page Start Button");
		questionnairesPage.selectQuestionForms(Constants.MForm);
		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);

		reportLog("11.1:MobileView Complete the questionnaire C and click to Continue Button on Completion Page.");
		questionnairesPage.clickOnStartQuestion();
		questionnairesPage.answerAllTextBoxField(Constants.QuestionnairesAnswer);

		reportLog("11.2:MobileView After the questionnaire is completed, application navigates to Questionnaire lis");
		questionnairesPage.verifyCompletionSucessMessage(Constants.Questionnaires_CompletionMessage);
		questionnairesPage.verifyContinueButtonDisplayed();
		questionnairesPage.clickOnContinue();

		reportLog("11.3:MobileView Verify Completed Icon Present with form");
		questionnairesPage.verifyCompletedIconPresentWithQuestionsAndNotEditable(Constants.MForm);

		reportLog("11.4:MobileView Observe the questionnaire list.Questionnaire A,b,c are  in disabled status ");
		questionnairesPage.verifyTheAppearenceOfForm();

		reportLog("MobileView Exit Application");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();
		sideMenu.verifyApplicationExit();
		clickOnConnectAppIcon();

		reportLog("deactivate participant");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
		subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		

		/*-------------------------------Observer Steps-------------------------------*/

		reportLog("12.1 :MobileView Launch with observer ");
		mobileLoginPage = androidSetUp();
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.configurationForRegisterTheSubject(registrationCodeObserver);
		mobileLoginPage.verifySignInScreenDisplayed();
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);

		reportLog("12.2:MobileView Application is successfully launched, Home screen is displayed.");
		dashborad.verifyUserLogin();

		reportLog("13.1:MobileView Tap on Questionnaires tab");
		questionnairesPage = dashborad.clickOnQuestionnairesTab();

		reportLog("13.2:MobileView Questionnaires tab is opened with questionnaires list displayed.");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog(
				"14.1:MobileView Questionnaires tab is opened with questionnaires list displayed.User shall be able to see all 3 questionnaires (D,E,F) grouped for Today (No other group displayed): ");
		reportLog(
				"14.2:MobileView Questionnaire D is active,MobileView Questionnaire E is disabled,MobileView Questionnaire F is disabled ");
		questionnairesPage.verifyTheAppearenceOfForm();

		reportLog("14.3:MobileView All questionnaires displayingdue in minute(s) where X is the end time");
		questionnairesPage.verifyDueTimeIsDisplayedForQuestionnaires(Constants.Mat_Mob, Constants.Due_In_Time);
		questionnairesPage.verifyDueTimeIsDisplayedForQuestionnaires(Constants.Mat_Mob1, Constants.Due_In_Time);
		questionnairesPage.verifyDueTimeIsDisplayedForQuestionnaires(Constants.MForm, Constants.Due_In_Time);

		reportLog("14.4:MobileView Verify Pending Icon Present with form");
		questionnairesPage.verifyPendingIconPresentWithQuestions(Constants.Mat_Mob);

		reportLog("15.1:MobileView Tap on Questionnaire D.");
		questionnairesPage.selectQuestionForms(Constants.Mat_Mob);

		reportLog(
				"15.2:MobileView Questionnaire D is opened on Start screen or first question - based on configuration.");
		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);

		reportLog("16.1:MobileView Complete the questionnaire D and click to Continue Button on Completion Page.");
		questionnairesPage.clickOnStartQuestion();
		questionnairesPage.answerAllCheckbox();
		questionnairesPage.verifyCompletionSucessMessage(Constants.Questionnaires_CompletionMessage);
		questionnairesPage.verifyContinueButtonDisplayed();
		questionnairesPage.clickOnContinue();

		reportLog("16.2:MobileView Verify Completed Icon Present with form");
		questionnairesPage.verifyCompletedIconPresentWithQuestionsAndNotEditable(Constants.Mat_Mob);

		reportLog("16.3:MobileView Verify Pending Icon Present with form");
		questionnairesPage.verifyPendingIconPresentWithQuestions(Constants.Mat_Mob1);

		reportLog(
				"17.1:MobileView User shall be able to see the Start Page of Questionnaire B with its all detail:Start Page Start Button");
		questionnairesPage.selectQuestionForms(Constants.Mat_Mob1);
		questionnairesPage.verifyQuestionStartingPageWithDescription(Constants.QuestionDescription);
		questionnairesPage.clickOnStartQuestion();

		reportLog("18.1:MobileView Tap BACK button in upper left corner.");
		questionnairesPage.clickOnBackButtonIconOnQuestionPage();
		questionnairesPage.clickOnYesOption();

		reportLog("18.2:MobileView Questionnaire List shall be displayed.");
		questionnairesPage.verifyTodayQuestionnairesListPresent();

		reportLog(
				"19.1:MobileView Questionnaire D in disabled status, E in active status (not started),F in disabled status (in the window, but disabled.) ");
		questionnairesPage.verifyTheAppearenceOfForm();

		reportLog("20:MobileView Exit Application");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();
		sideMenu.verifyApplicationExit();
		clickOnConnectAppIcon();

	}


	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) {
		if (ITestResult.SUCCESS == result.getStatus()) {
			
			Configuration.updatePropertyTestData("RegressionTestData", "ObserverRegistrationCode",
					registrationCodeObserver);
			Configuration.updatePropertyTestData("RegressionTestData", "SubjectName959", subjectName);


		} else {
			Log.error(subjectName + " subject is not added for " + studyName + " study.");
		}
	}
}
