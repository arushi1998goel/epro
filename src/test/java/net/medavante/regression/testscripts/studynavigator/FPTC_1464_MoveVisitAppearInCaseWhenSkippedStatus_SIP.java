package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.Constants;

public class FPTC_1464_MoveVisitAppearInCaseWhenSkippedStatus_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5);
	private String completedNonCRVisit, skippedNonCRVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1464_MoveVisitAppearInCaseWhenSkippedStatus_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyMoveVisit");
		completedNonCRVisit = properties.getProperty("Auto_ClinRo");
		skippedNonCRVisit = properties.getProperty("visitClinRoNotAssigned");

		reportLog("Creating a subject from user and configure visits for them");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("At least one Visit_1 has 1 ClinRo completed assessments");

		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		reportLog(
				"At least one Visit_2 has 1 ClinRo not assigned assessments with skipped status on the Subject Visit Details screen");

		subjectDetailPage.clickOnVisitRow(skippedNonCRVisit);
		loginPage.logoutApplication();

		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit,
				subjectName);
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

		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1464 Test Case Name: Verify that option to change Visit
	 * appear in the case when Skipped status on the Subject Visit details screen
	 * exists.
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1464_Verify that option to move Visit appear in the case when Skipped status on the Subject Visit details screen exists. ", groups = {})
	public void FPTC_1464_VerifyOptionToMoveVisitAppearInCaseWhenSkippedStatusOnTheSubjectVisitDetailsScreenExists()
			throws InterruptedException {

		reportLog("1: Log in to portal as a user from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		// studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("2.1: Navigate to Visit Listing screen and select Visit from Pr.#2");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit,
				subjectName);

		reportLog("Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("'Action' option is displayed for Visit");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("3: Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName);
		visitDetaiLPage.clickOnChangeToVisitDropDown();

		reportLog("3.1: Check that Visit Pr.#3 is displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(skippedNonCRVisit);

		reportLog("4: Select Visit from Pr.#3");
		visitDetaiLPage.selectChangeToVisit(skippedNonCRVisit);

		reportLog("5.1: Select an action to Save the changes (Control 'Save')");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("5.2: Select an action to Confirm the changes (Control 'Confirm')");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("6: Select control to sign the Reasons to Change ('Ok')");
		visitDetaiLPage.verifyReasonForChangePopUpDisplayed();
		visitDetaiLPage.selectReasonForChangeOption(Constants.Visit_Move_TO_IncorrectAdministered_Option);
		visitDetaiLPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		visitDetaiLPage.waitSpinnerToBecomeInvisible();

		reportLog("Validate success message an close the validation");
		visitDetaiLPage.verifySuccessMessage(ApplicationVerificationMessage.moveVisitSuccessfulMessage);
		visitDetaiLPage.closeSuccessMessage();

		reportLog("7: Check that status for moved Visit is Completed");
		visitDetaiLPage.verifyVisitDetails(Constants.StudyDashBoard_columnName_Status, Constants.Complete_Status);

		reportLog("8: Open 'Source Subject Status' for Subject Pr#2");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("Search the subject and move to subject detail page");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog(
				"8.1: Check that Source Visit becomes blank after a successful moving. And all assessments are not assigned to any rater");
		subjectDetailPage.verifyVisitStatus(completedNonCRVisit, Constants.skipped_Status);

		reportLog("9: Open 'Subject Status History' Pr#3");
		subjectDetailPage.clickOnShowHistory();
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog(
				"9.1: Check that 'Subject Status History' is updated and have the latest status in order of visit completion by system change.");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		subjectDetailPage.clickOnCloseHistory();

		reportLog("10. Open corresponding assessments");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(skippedNonCRVisit, subjectName);

		reportLog(
				"10.1: - Check that the Destination Visit has received all corresponding data from the source Assessment:");
		reportLog("10.2:Assessments detail screen");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("10.3: Cover page on PDF Assessment;");
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyPdfAssignedRaterIspresent(Constants.ATAssignedRater_11);

		reportLog("10.4: Footer on PDF Assessment;");
		assessmentDetailPage.verifyFooterDataOnEachPage(subjectName);

		reportLog("10.5: Signature History on PDF Assessment;");
		assessmentDetailPage.verifyPdfSignaturePageCompletedAndSignedByInformation(Constants.ATAssignedRater_11);

		reportLog("10.6: Audit History on PDF Assessment.");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.ATAssignedRater_11);
		assessmentDetailPage.clickOnCancelButton();

		reportLog("10.7: Logout application");
		loginPage.logoutApplication();

		reportLog("10.8: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
