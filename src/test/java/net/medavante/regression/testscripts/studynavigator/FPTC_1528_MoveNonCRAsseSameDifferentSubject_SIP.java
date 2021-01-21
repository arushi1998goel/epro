package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1528_MoveNonCRAsseSameDifferentSubject_SIP extends BaseTest {

	private String VisitFirstCompleted, visitSecondCompleted, visitThirdCompleted, visitNonCrAssessmentSubject1,
			visitNonCrAssessmentSubject2, visitAssignedSubject,
			subjectName1 = "Auto2173" + generateRandomAlphanumericString(4)
			, subjectName2 = "AutoTest2173" + generateRandomAlphanumericString(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1528_MoveNonCRAsseSameDifferentSubject_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		VisitFirstCompleted = properties.getProperty("visitClinRoSubmitted");
		visitSecondCompleted = properties.getProperty("VisitAutomaticSubmission");
		visitThirdCompleted = properties.getProperty("visitClinRoNotAssigned");
		visitAssignedSubject = properties.getProperty("VisitAssigned2173");
		visitNonCrAssessmentSubject1 = properties.getProperty("Auto_Paper_ClinRo_Visit1");
		visitNonCrAssessmentSubject2 = properties.getProperty("VisitAutomaticSubmission");

		/*----------------------- Creating Subject 1 and Configuring Three Not Completed Assessment---------------------- */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(VisitFirstCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnVisitRow(visitSecondCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnVisitRow(visitThirdCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnVisitRow(visitAssignedSubject);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		
		/*-------------------Completing First Assessment---------------------------*/
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List2FormNameSecondType, VisitFirstCompleted, subjectName1);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		
		/*-------------------Completing Second Assessment---------------------------*/
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage=studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormName,
				visitSecondCompleted, subjectName1);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		
		/*-------------------Completing Third Assessment---------------------------*/
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		
		/*----------------Creating Second Subject Second For At least 1 Non-CR Assessment-2------------------*/
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,subjectName2);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		
	}

	/**
	 * ==========================================================================================================================
	 * Test Case Id: FPTC_1528 Test Case Name:Move Non CR Assessment For The
	 * Same Different Subject
	 * 
	 * ==========================================================================================================================
	 */

	@Test(description = "FP-TC-1528_Move Non-CR Assessment for the same/different Subject", groups = { " " })

	public void FPTC_1528_verifyMoveNonCRAsseSameDifferentSubject() {

		reportLog("1.1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User from PR#1 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3:-> Open the Study Dashboard -> Select a Study and a Site from PR#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.4:Dashboard for the PR#2 displayed");
		studyNavigatorDashBoardPage.verifyStudyDashBoardPage();

		reportLog("1.5:Navigate to the Assessment Listing screen and select the Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitFirstCompleted, subjectName1);

		reportLog("1.6:Visit Assessment screen from PR#3 is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("1.7:Action option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("1.8:Select action to moving an Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("1.9:Check that Visit PR#4 is displayed in Move To Assessment drop-down list");
		reportLog("1.10:Assessment from PR#4 is displayed in Move To Assessment drop-down list  ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName1);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitNonCrAssessmentSubject1);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		
		reportLog("1.11:Move Assessment PR#3 to Assessment PR#4 for the same Subject Save changes");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.changeVisitDropDown(visitNonCrAssessmentSubject1);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.12:The Assessment was moved");
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		assessmentDetailPage.navigateBackToDashBoard();
		
		reportLog("2.1:Navigate to the Assessment Listing screen and select the 2nd Assessment from PR#3 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage=studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormName,
				visitSecondCompleted, subjectName1);
		
		reportLog("2.2:Visit Assessment screen from PR#3 is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.4:Action option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("2.5:Select action to moving an Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("2.6:Check that Visit PR#5 is displayed in   Move To Assessment drop-down list  ");
		reportLog("2.7 :Assessment from PR#5 is displayed in Move To Assessment drop-down list ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitNonCrAssessmentSubject2);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("2.8:Move Assessment PR#3 to Assessment PR#5 for the same Subject Save changes");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.changeVisitDropDown(visitNonCrAssessmentSubject2);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		
		reportLog("2.9:The Assessment was moved");
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		assessmentDetailPage.navigateBackToDashBoard();
		
		reportLog("3.1:Navigate to the Assessment Listing screen and select the 3d Assessment from PR#3");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage=studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List2FormNameSecondType, visitThirdCompleted, subjectName1);

		reportLog("3.2:Assessment detail screen is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3:Action option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("3.4:Select action to moving an Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("3.5:Check that Visit PR#6 NOT displayed in Move To Assessment drop-down list");
		reportLog("3.6:Assessment from PR#6 NOT displayed in Move To Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName1);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitAssignedSubject);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		
		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
