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
import net.medavante.portal.utilities.Constants;

public class FPTC_1455_ChangeVisitGeneralFlow_SIP extends BaseTest {

	protected String subjectName1 = "SUBJ_1" + generateRandomString(5);
	protected String subjectName2 = "SUBJ_2" + generateRandomString(5);
	private String completedNonCRVisit1, notcompeletedCRVisit2;

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1455_ChangeVisitGeneralFlow_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		completedNonCRVisit1 = properties.getProperty("VisitToSubject1");
		notcompeletedCRVisit2 = properties.getProperty("VisitNotAssigned");

		reportLog("Creating a subject from user and configure visits for them");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("At least one completed Non-IR Visit_1 exists for the Subject_1 with one completed Assessment");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(completedNonCRVisit1);
		subjectDetailPage.clickOnInitiateVisitIcon();
		subjectDetailPage.verifyAssesmentNotAssignedForCalenderVisit();
		subjectDetailPage.selectAssigneeDropDownOption(Constants.ATAssignedRater_10);

		reportLog(
				"At least one not completed Non-IR Visit_1 exists for the Subject_2 and at least 1 Assessment is not assigned ");

		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName2);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Select visit and verify that Assessment is not assigned");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(notcompeletedCRVisit2);
		subjectDetailPage.clickOnInitiateVisitIcon();
		subjectDetailPage.verifyAssesmentNotAssignedForCalenderVisit();


		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, completedNonCRVisit1, subjectName1);

		reportLog("Click on Assessment link to compelete ");		
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1455 Test Case Name: Change Visit - General flow 
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1455_Change Visit - General flow.", groups = {})
	public void FPTC_1455_VerifyUserWithAnAppropriateClaimCanChangeAVisit() throws InterruptedException {

		reportLog("1.: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1:  Navigate to Visit Listing screen and select Visit from Pr.#2");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit1,
				subjectName1);

		reportLog("Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitPageIsDisplayed();
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit1);

		reportLog("'Action' option is displayed for Visit");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("2.2:  Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName1);
		visitDetaiLPage.clickOnChangeToVisitDropDown();

		reportLog("2.3: Check that Visit Pr.#3 is displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedCRVisit2);

		reportLog("3: Move Visit Pr.#2 to Visit Pr.#3 for the same Subject");
		visitDetaiLPage.selectChangeToVisit(notcompeletedCRVisit2);

		reportLog("4: Select an action to not apply changes (Control 'Cancel')");
		visitDetaiLPage.clickOnCancelButtonInMoveToVisitConfirmation();

		reportLog("4.1: Visit isn't changed");
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit1);

		reportLog("5: Select action to change visit 'Action' option is displayed for Visit");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("6.1: Change to Visit Pr.#3");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();
		
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName1);
		visitDetaiLPage.clickOnChangeToVisitDropDown();

		reportLog("6.2: Check that Visit Pr.#3 is displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedCRVisit2);

		reportLog("6.3: Move Visit Pr.#2 to Visit Pr.#3 for the same Subject");
		visitDetaiLPage.selectChangeToVisit(notcompeletedCRVisit2);
		
		reportLog("7.1: Select an action to Save the changes (Control 'Save')");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("7.2: Select an action to Confirm the changes (Control 'Confirm')");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("7.3: E-signature window appears with 2 Reasons to Change:");
		visitDetaiLPage.verifyReasonForChangePopUpDisplayed();

		reportLog("7.4: E-signature window appears with 2 Reasons to Change: 'Incorrect Visit Administered' and 'Other'");
		visitDetaiLPage.verifyReasonForChangeOptions(Constants.Visit_Move_TO_IncorrectAdministered_Option);
		visitDetaiLPage.verifyReasonForChangeOptions(Constants.Other_Reason_For_Change);

		reportLog("8: Select control to cancel E-Signature (Reason For Change)");
		visitDetaiLPage.clickOnReasonForChangeCancelBTN();

		reportLog("8.2: E-Signature is closed ");
		visitDetaiLPage.verifyReasonForChangePopUpIsNotDisplayed();

		reportLog("8.3:  Changes isn't applied and previous 'Change Visit' window is displayed");
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		reportLog("9.1: Select an action to Save the changes (Control 'Save')");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("9.2: Select an action to Confirm the changes (Control 'Confirm')");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("9.3: E-signature window appears with 2 Reasons to Change:");
		visitDetaiLPage.verifyReasonForChangePopUpDisplayed();

		reportLog(
				"9.4: E-signature window appears with 2 Reasons to Change: 'Incorrect Visit Administered' and 'Other'");
		visitDetaiLPage.verifyReasonForChangeOptions(Constants.Visit_Move_TO_IncorrectAdministered_Option);
		visitDetaiLPage.verifyReasonForChangeOptions(Constants.Other_Reason_For_Change);

		reportLog("10: Select control to apply the Reasons to Change");
		visitDetaiLPage.selectReasonForChangeOption(Constants.Visit_Move_TO_IncorrectAdministered_Option);

		reportLog("10.1: Reason to change is applied");
		visitDetaiLPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		visitDetaiLPage.waitSpinnerToBecomeInvisible();

		reportLog("Close the success message");
		visitDetaiLPage.closeSuccessMessage();

		reportLog("11: Open 'Source Subject Status' for Subject Pr#2");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("11.1: Search the subject and move to subject detail page");

		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName1);
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog(
				"11.2: Check that Source Visit becomes blank after a successful moving. And all assessments are not assigned to any rater");
		subjectDetailPage.verifyCalendarVisitStatus(completedNonCRVisit1, Constants.None_Status);

		reportLog("12: Open 'Subject Status History' Pr#2");
		subjectDetailPage.clickOnShowHistory();
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog(
				"12.1: Source Visit becomes blank after a successful changing and all assessments are not assigned to any rater");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		subjectDetailPage.clickOnCloseHistory();

		reportLog("13: Open 'Source Subject Status' for Subject Pr#3");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("13.1: Search the subject and move to subject detail page");
		studyNavigatorDashBoardPage.searchSubject(subjectName2);
		studyNavigatorDashBoardPage.clickOnFirstCell();

		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("14: Open 'Subject Status History' Pr#3");
		subjectDetailPage.clickOnShowHistory();
		subjectDetailPage.verifyHistoryPopUpDisplayed();

		reportLog(
				"14.1: Source Visit becomes blank after a successful changing and all assessments are not assigned to any rater");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		subjectDetailPage.clickOnCloseHistory();

		reportLog("15: Open corresponding assessments");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(notcompeletedCRVisit2,
				subjectName1);

		reportLog(
				"15.1: Check that the Destination Visit has received all corresponding data from the source Assessment:");
		reportLog("Assessments detail screen");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("15.2: Cover page on PDF Assessment;");
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyPdfAssignedRaterIspresent(Constants.ATAssignedRater_10);

		reportLog("15.3: Footer on PDF Assessment;");
		assessmentDetailPage.verifyFooterDataOnEachPage(studyName);

		reportLog("15.4: Signature History on PDF Assessment;");
		assessmentDetailPage.verifyPdfSignaturePageCompletedAndSignedByInformation(Constants.ATAssignedRater_10);

		reportLog("15.5: Audit History on PDF Assessment.");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.ATAssignedRater_10);
		assessmentDetailPage.clickOnCancelButton();

		reportLog("16: Logout application");
		loginPage.logoutApplication();

		reportLog("17: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
