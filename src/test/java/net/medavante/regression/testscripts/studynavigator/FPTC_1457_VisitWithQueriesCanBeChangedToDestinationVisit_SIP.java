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

public class FPTC_1457_VisitWithQueriesCanBeChangedToDestinationVisit_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String completedNonCRVisit, notCompletedNonCRVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1457_VisitWithQueriesCanBeChangedToDestinationVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyMoveVisit");
		completedNonCRVisit = properties.getProperty("visitForRenameStatus");
		notCompletedNonCRVisit = properties.getProperty("Auto_NonCr_Visit1");

		reportLog("Creating a subject from user and configure studies accordingly");
		
		reportLog("1: Login to portal as a user with the claims");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
	
		
		reportLog("At least one completed Visit with ClinRO Assessment has queries at visit level.");

		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		reportLog("Add query for this visit");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		subjectDetailPage.addNewQuery("New Query");
		
		subjectDetailPage.verifyPresenceOfQueryBySubject(subjectName);
		subjectDetailPage.clickOnQueriesCollpaseIcon();		

		reportLog("At least one Visit has one ClinRO not assigned assessment.");
		subjectDetailPage.clickOnVisitRow(notCompletedNonCRVisit);
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();

	}

	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1457 Test Case Name: Verify that Visit with queries can
	 * be changed to destination Visit
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1457_Verify that Visit with queries can be changed to destination Visit", groups = {})
	public void FPTC_1457_VerifyVisitWithQueriesCanBeChangedToDestinationVisit() throws InterruptedException {

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("2: Navigate to Visit listing screen and select Visit from Pr.#2");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit,
				subjectName);

		reportLog("2.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();
		visitDetaiLPage.verifyVisitPageIsDisplayed();
		
		reportLog("3: 'Action' option is displayed");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("3.1: Select action to change visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();
		
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName);
		visitDetaiLPage.clickOnChangeToVisitDropDown();
		
		reportLog("3.2: Visit from Pr.#3 is displayed in ‘Change To’ Visit drop down list");
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(notCompletedNonCRVisit);

		reportLog("4: Select Visit from Pr.#3");
		visitDetaiLPage.selectChangeToVisit(notCompletedNonCRVisit);
		
		reportLog("5.1: Select an action to Save the changes (Control 'Save')");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("5.2: Select an action to Confirm the changes (Control 'Confirm')");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("5.3: E-signature window appears after the change has been confirmed");
		visitDetaiLPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("5.4: Select control to apply the Reasons to Change");
		visitDetaiLPage.selectReasonForChangeOption(Constants.Visit_Move_TO_IncorrectAdministered_Option);
		
		reportLog("6: Select control to sign the Reason for Change ('Ok')");
		visitDetaiLPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		visitDetaiLPage.waitSpinnerToBecomeInvisible();

		reportLog("6.1: Visit is changed successfully and Visit detail screen is displayed");
		visitDetaiLPage.verifySuccessMessage("The visit has been changed successfully");
		visitDetaiLPage.closeSuccessMessage();
				
		reportLog("7: Check that status for changed Visit and Status for changed Visit is Completed");
		visitDetaiLPage.verifyVisitDetails(Constants.StudyDashBoard_columnName_Status,Constants.Complete_Status);

		reportLog("8: Open 'Source Subject Status' for Subject Pr#2");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		reportLog("8.1: Source Visit becomes blank after successful change. And all assessments are not assigned to any rater");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);
		
		reportLog("9: Open 'Subject Status History' Pr#3");
		subjectDetailPage.clickOnShowHistory();
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog("9.1: 'Subject Status History' is updated and has the latest status in order of visit completion by system change");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		subjectDetailPage.clickOnCloseHistory();

		reportLog("10: Open corresponding assessments and Destination Visit has received all corresponding data from the source Assessment:");
		subjectDetailPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(notCompletedNonCRVisit,subjectName);
		
		reportLog("10.1: Assessments detail screen;");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("10.2: Cover page on PDF Assessment;");
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyPdfAssignedRaterIspresent(Constants.ATAssignedRater_11);
		
		reportLog("10.3: Footer on PDF Assessment;");
		assessmentDetailPage.verifyFooterDataOnEachPage(subjectName);
		
		reportLog("10.4: Signature History on PDF Assessment;");
		assessmentDetailPage.verifyPdfSignaturePageCompletedAndSignedByInformation(Constants.ATAssignedRater_11);
		
		reportLog("10.5: Audit History on PDF Assessment.");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.ATAssignedRater_11);
		assessmentDetailPage.clickOnCancelButton();
		
		reportLog("11: Check that all the visit level queries are changed to destination visit");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.verifyPresenceOfQueryBySubject(subjectName);
		assessmentDetailPage.clickOnQueriesCollpaseIcon();		
		
		reportLog("12: Logout application");
		loginPage.logoutApplication();

		reportLog("12.1: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
