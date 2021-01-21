package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.Constants;

public class FPTC_1460_ChangeVisitWithDiffNumbOfCompletedAssessments_SIP extends BaseTest {

	private String visitNameWithThreeCompletedAssesment, visitNameWithTwoCompletedAssesment,
			visitNameWithThreeNotAssignedAssesment, visitNameWithTwoNotAssignedAssesment,
			subjectName = "Auto2145"+generateRandomString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1460_ChangeVisitWithDiffNumbOfCompletedAssessments_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Test741Study");
		visitNameWithThreeCompletedAssesment = properties.getProperty("VisitThreeClinRoCompleted");
		visitNameWithTwoCompletedAssesment = properties.getProperty("VisitTwoClinRoCompleted");
		visitNameWithThreeNotAssignedAssesment = properties.getProperty("VisitThreeClinRoNotAssigned");
		visitNameWithTwoNotAssignedAssesment = properties.getProperty("VisitTwoClinRoNotAssigned");
		
		reportLog("Creating a subject from user and configure visits for them");
		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		// Completing Three ClinRo Assessment For Configuring Pre-Requisite
		reportLog("Configuring At least one Visit_3 has 3 ClinRo completed assessments");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitNameWithTwoCompletedAssesment);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);
		subjectDetailPage.clickOnVisitRow(visitNameWithThreeCompletedAssesment);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormName,
				visitNameWithThreeCompletedAssesment, subjectName);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List2FormNameSecondType,
				visitNameWithThreeCompletedAssesment, subjectName);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog13List1FormName,
				visitNameWithThreeCompletedAssesment, subjectName);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		// Configuring Two Completed Assessment
		reportLog("Configuring  At least one Visit_1 has 2 ClinRo completed assessments");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List2FormNameSecondType,
				visitNameWithTwoCompletedAssesment, subjectName);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormName,
				visitNameWithTwoCompletedAssesment, subjectName);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

	}

	/**
	 * ===========================================================================================================================================
	 * Test Case Id: FP-TC-1460 Test Case Name:Move Visit -Change Visit -
	 * option to change Visit with different numbers of completed assessments
	 * 
	 * ===========================================================================================================================================
	 */

	@Test(description = "FP-TC-1460_Move Visit -Change Visit - option to change Visit with different numbers of completed assessments", groups = {
			" " })
	public void FPTC_1460_verifyChangeVisitOptionToChangeVisitWithDifferentNumbersOfCompletedAssessments() {

		reportLog("2.2:Navigate to Visit listing screen ");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.3:Select Visit from Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage
				.clickOnVisitByVisitAndSubjectName(visitNameWithTwoCompletedAssesment, subjectName);

		reportLog("2.4:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("3.1:	'Action' option is displayed");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("3.2:Select action to change visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();
		
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName);
		visitDetaiLPage.clickOnChangeToVisitDropDown();

		reportLog("3.3:Visit from Pr.#3 is displayed in ‘Change To’ Visit drop down list");
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(visitNameWithThreeNotAssignedAssesment);

		reportLog("4.1:	Select the Visit Pr.#3 in Change To filter");
		visitDetaiLPage.selectChangeToVisit(visitNameWithThreeNotAssignedAssesment);

		reportLog("4.2:The Visit from Pr.#3 is selected in the Change To filter");
		visitDetaiLPage.verifyChangeToVisitSelected(visitNameWithThreeNotAssignedAssesment);

		reportLog("5.1: Select an action to Save the changes (Control 'Save')");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("5.2: Select an action to Confirm the changes (Control 'Confirm')");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment(); 

		reportLog("5.3:Check if E-signature window appears after the change has been confirmed");
		visitDetaiLPage.verifyReasonForChangePopUpDisplayed();

		reportLog("6.1:Select a reason for change ");
		visitDetaiLPage.selectReasonForChangeOption(Constants.Visit_Move_TO_IncorrectAdministered_Option);

		reportLog("6.2:Enter the credentials ");
		reportLog("6.3:Select control to sign the Reason For Change ('Ok')");
		visitDetaiLPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		visitDetaiLPage.waitSpinnerToBecomeInvisible();

		reportLog("6.4:Visit successfully change ");
		visitDetaiLPage.verifySuccessMessage(ApplicationVerificationMessage.moveVisitSuccessfulMessage);
		visitDetaiLPage.closeSuccessMessage();

		reportLog("6.5:Visit Details screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("7.1:Check if status for change Visit is In Progress )");
		reportLog("7.2:Visit Pr.#3 status is In Progress");
		visitDetaiLPage.verifyVisitDetails(Constants.StudyDashBoard_columnName_Status,Constants.InProgress_Status);
		visitDetaiLPage.navigateToHome();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		
		reportLog("7.3:Check if Subject status wasn't changed");
		reportLog("7.4:Subject status wasn't changed");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(visitNameWithThreeNotAssignedAssesment);
		studyNavigatorDashBoardPage.verifyListShowingStausAccordingToFilterSelection(Constants.StudyDashBoard_columnName_Status, Constants.InProgress_Status);
			
		reportLog("8.1:Open 'Source Subject Status' for Subject Pr#2");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
			
		reportLog("8.2:	Source Visit becomes blank after a successful change. And all Assessments are not assigned to any rater");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitNameWithTwoCompletedAssesment);
		subjectDetailPage.clickOnAddVisitIcon();
		
		reportLog("9: Navigate to the assessment Listing page");
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("10.1:Open corresponding assessments");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormName,
				visitNameWithThreeNotAssignedAssesment, subjectName);
		
		reportLog("10.2:Destination Visit has received all corresponding data from the source Assessment:");
		reportLog("10.3:Assessments detail screen;");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("10.4:Cover page on PDF Assessment;");
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyPdfAssignedRaterIspresent(Constants.ATAssignedRater_10);

		reportLog("10.5:Footer on PDF Assessment;");
		assessmentDetailPage.verifyFooterDataOnEachPage(subjectName);
		
		reportLog("10.6:Signature History on PDF Assessment;");
		assessmentDetailPage.verifyPdfSignaturePageCompletedAndSignedByInformation(Constants.ATAssignedRater_10);

		reportLog("10.7:Audit History on PDF Assessment.");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.ATAssignedRater_10);
		assessmentDetailPage.clickOnCancelButton();
		assessmentDetailPage.navigateBack();

		reportLog("11.1:Navigate to Visit Listing screen and select the Visit from Pr.#4");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitNameWithThreeCompletedAssesment, subjectName);

		reportLog("11.2:Visit Details screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("12.2:'Action' option is displayed");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("12.1:Select action to change visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();
		
		reportLog("13.1: Check if Visit Pr.#5 isn't displayed in ‘Move To’ Visit drop down list");
		reportLog("13.2: Visit from Pr.#5 isn't displayed in ‘Change To’ Visit drop down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.verifySubjectIsNotPresentInChangeToSubjectDropDown(subjectName);
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();
		
		reportLog("13.3: Logout application");
		loginPage.logoutApplication();

		reportLog("13.4: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
