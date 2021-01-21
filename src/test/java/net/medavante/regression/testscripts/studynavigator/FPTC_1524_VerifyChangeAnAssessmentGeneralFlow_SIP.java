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

public class FPTC_1524_VerifyChangeAnAssessmentGeneralFlow_SIP extends BaseTest {

	protected String subjectName1 = "SUBJ_1" + generateRandomString(5);
	protected String subjectName2 = "SUBJ_2" + generateRandomString(5);
	private String completedNonCRVisit1, notcompeletedNonCRVisit2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1524_VerifyChangeAnAssessmentGeneralFlow_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyMoveVisit");
		completedNonCRVisit1 = properties.getProperty("Auto_NonCr_Visit1");
		notcompeletedNonCRVisit2 = properties.getProperty("Auto_NonCr_Visit2");

		reportLog("Creating a subject from user and configure Non-CR Visit_1 for it");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName1);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("At least one completed Non-CR Visit_1 exists for the Subject_1 with one completed Assessment");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		reportLog("Creating an other subject and configure 1 Non-CR visit2 for it");

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName2);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Select non cr visit2 and verify that Assessment is not assigned");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(notcompeletedNonCRVisit2);
		subjectDetailPage.clickOnAddVisitIcon();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("Completed Assessment");
		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,
				Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);
		studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog13List1FormName);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.navigateBackToDashBoard();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1524 Test Case Name: Change an Assessment - General
	 * flow
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1524_Verify Change an Assessment - General flow ", groups = {})
	public void FPTC_1524_VerifyChangeAnAssessmentGeneralFlow() throws InterruptedException {

		reportLog("1.: Log in to portal as a user from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("1.1: Navigate to the Assessment Listing screen and select the Assessment from PR#4 ");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit1,
				subjectName1);

		reportLog(
				"1.2: Check that the Destination Visit has received all corresponding data from the source Assessment:");
		reportLog("Assessments detail screen");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("1.3: Action option is displayed for the Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("1.4: Select the action control to change the Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.verifyMoveAssessmentPopUpWindowIsDisplayed();

		reportLog("1.5: Close the Change Assessment modal");
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2: Select the action control to change the Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("2.1: Check that the Assessment from PR#4 is displayed in the ‘Change From’ block");
		reportLog("3: Select the Assessment from PR#5 in the 'Select Subject' drop-down list in the ‘Change To’ block.");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedNonCRVisit2);

		reportLog("4: Select to cancel/close the modal");
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("4.1: Check that the Assessment wasn't changed");
		assessmentDetailPage.verifyAssessmentDetails("Subject", subjectName1);
		assessmentDetailPage.verifyAssessmentDetails("Visit", completedNonCRVisit1);

		reportLog("5: Select the action control to change the Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.verifyMoveAssessmentPopUpWindowIsDisplayed();

		reportLog("5.1: Close the modal with the \"X\" control button ");
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("5.2: Check that the Assessment wasn't changed");
		assessmentDetailPage.verifyAssessmentDetails("Subject", subjectName1);
		assessmentDetailPage.verifyAssessmentDetails("Visit", completedNonCRVisit1);

		reportLog("6: Select the action control to change the Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("6.1: Select to confirm the Assessment changing ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedNonCRVisit2);
		assessmentDetailPage.changeVisitDropDown(notcompeletedNonCRVisit2);

		reportLog("6.2: Close the 'Reason for change' modal with the 'X' control button ");
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("6.3: Check that the Assessment wasn't changed");
		assessmentDetailPage.verifyAssessmentDetails("Subject", subjectName1);
		assessmentDetailPage.verifyAssessmentDetails("Visit", completedNonCRVisit1);

		reportLog("7: Select the action control to change the Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("7.1: Select to confirm the Assessment changing ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);

		reportLog("7.2: Select 'Cancel' control button");
		assessmentDetailPage.clickOnCancelButtonOfChangeAssesment();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("7.3: Check that the Assessment wasn't changed");
		assessmentDetailPage.verifyAssessmentDetails("Subject", subjectName1);
		assessmentDetailPage.verifyAssessmentDetails("Visit", completedNonCRVisit1);

		reportLog("8: Select to confirm the Assessment changing");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);
		assessmentDetailPage.changeVisitDropDown(notcompeletedNonCRVisit2);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("8.1: Check that the E-signature window appears with 2 Reasons to Change:");
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonForChangeValuesList);

		reportLog("9: Select the first reason from the dropdown list");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);

		reportLog("9.1: Select the second reason from the dropdown list");
		assessmentDetailPage.selectReasonForChangeOption(Constants.Other_Reason_For_Change);

		reportLog("9.2: Fill in the 'Comment required' field");
		assessmentDetailPage.inputReasonComment(Constants.Other_Reason_For_Change);

		reportLog("9.3: Fill in the Username and the Password fields");
		reportLog("9.4: Select action to confirm the E-signature");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog(
				"9.5: 'The Assessment has been changed successfully' message displayed for the successful operation of changing an Assessment");
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("10: Open the Visit for an assessment from PR#4");
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,
				Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("11: Open the Visit for an Assessment from the PR#5");
		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(notcompeletedNonCRVisit2, subjectName2);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog(
				"11.1: Check that the destination Subject Visit Start Date is the date of the Assessment that was started 1st for that visit.");
		assessmentDetailPage.verifyAssessmentDetails("Started", currentDate().toUpperCase());

		reportLog("12: Check that the Destination Subject Visit Assessments contains:");
		assessmentDetailPage.verifyAssessmentDetails("Assessment", Constants.Assesment_AdasCog13List1FormName);

		reportLog("13: Check that the Destination Subject Visit status is:");
		assessmentDetailPage.verifyAssessmentDetails("Visit", notcompeletedNonCRVisit2);

		reportLog(
				"14: Check that the Destination Subject Status and its History is updated to have the latest status in order of Visit completion by system change and not manual change");
		reportLog("15: Check that the PDF cover page, footer and the Audit History contains the relevant changes made");
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyFooterDataOnEachPage(subjectName2);
		assessmentDetailPage.verifyPdfSignaturePageCompletedAndSignedByInformation(Constants.ATAssignedRater_11);
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.ATAssignedRater_11);
		assessmentDetailPage.clickOnCancelButton();

		reportLog("16: Logout application");
		loginPage.logoutApplication();

		reportLog("16.1: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
