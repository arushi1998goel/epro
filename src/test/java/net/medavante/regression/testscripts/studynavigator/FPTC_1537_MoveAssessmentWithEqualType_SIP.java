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

public class FPTC_1537_MoveAssessmentWithEqualType_SIP extends BaseTest {
	protected String subject_Completed = "SUBJ_" + generateRandomString(5);
	private String observerAlias = "Auto" + generateRandomString(3);
	private String visitName, visit_PRO, visitName3, moveToAssesment, completedButNotAssignedObserver,
			visitName4, Visit_2176_Pro, Visit_2076_Obsro;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1537_MoveAssessmentWithEqualType_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
		visitName = properties.getProperty("visitName");
		visit_PRO = properties.getProperty("visit_PRO");
		visitName3 = properties.getProperty("visitName3");
		visitName4 = properties.getProperty("visitName4");
		Visit_2176_Pro = properties.getProperty("Visit_2176_Pro");
		Visit_2076_Obsro = properties.getProperty("visitOBSRO");

		moveToAssesment = properties.getProperty("moveToAssesment");
		completedButNotAssignedObserver = properties.getProperty("Auto_Observer_Relation1");
		
		reportLog("Creating a subject for (Pr#1)");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.VTAssignedRater_21, subject_Completed);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();
//		assessmentDetailPage = subjectDetailPage.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		
		reportLog("Navigate to Study Dashboard page from assessment page");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subject_Completed);
		studyNavigatorDashBoardPage.clickOnVisitFilterLink();
		subjectDetailPage.clickOnVisitRow(visit_PRO);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();		
		
		reportLog("Navigate to Study Dashboard page");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subject_Completed);
		studyNavigatorDashBoardPage.clickOnVisitFilterLink();
		subjectDetailPage.clickOnVisitRow(visitName3);
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.clickOnAddObserverBtN();
		subjectDetailPage.inputObserverRelationName(completedButNotAssignedObserver);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();
		subjectDetailPage.navigateBackToDashBoard();

		reportLog("Logout application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1537 Test Case Name: Move Assessment with equal type.
	 * =========================================================================
	 * 
	 * 
	 */

	@Test(description = "FP-TC-1537 Test Case Name: Move Assessment with equal type.", groups = { "" })
	
	public void FPTC_1537_verifyMoveAssessmentWithEqualType_SIP() {

		reportLog("1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
			
		reportLog("1.1:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("1.2 Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormName,
				visitName, subject_Completed);

		reportLog("1.3 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("2 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("2.2 Check that Assessment  from PR#4.2 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_Completed);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visit_PRO);

		reportLog("2.3 Click on close button of change assesment form dialog");
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		
		reportLog("2.4 Navigate to study navigator and select study");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("3.1:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("3.2 Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormName, visitName, subject_Completed);

		reportLog("3.3 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("3.4 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("3.5 Check that Assessment  from PR#4.3 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_Completed);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitName3);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitName4);

		reportLog("4.1 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(visitName4);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("4.2 Click on Confirm button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("4.3 : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("4.4 Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();

		reportLog("4.5  Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();
		
		reportLog("4.6 Navigate to study navigator and select study");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("5.1:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("5.2 Search visit2 and subject in assessment page");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameSecondType, visit_PRO, subject_Completed);

		reportLog("5.3 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("5.4 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("5.5 Check that Assessment  from PR#4.1 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_Completed);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitName);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		
		reportLog("5.6Navigate to study navigator and select study");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("6.1:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("6.2 Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameSecondType, visit_PRO, subject_Completed);

		reportLog("6.3 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("6.4 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("6.5 Check that Assessment  from PR#4.3 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_Completed);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(Visit_2076_Obsro);

		reportLog("6.6 Click on close button of change assesment form dialog");
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		
		reportLog("7.1 Navigate to study navigator and select study");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("7.2: Navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("7.3 Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormNameSecondType, visit_PRO, subject_Completed);

		reportLog("7.4 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("7.5 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("7.6 Check that Assessment  from PR#4.3 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_Completed);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(Visit_2176_Pro);

		reportLog("7.7 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(Visit_2176_Pro);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("7.8 Click on Confirm yes button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("7.9 : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("7.10 Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();

		reportLog("7.11 Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();
		
		reportLog("8.1 Navigate to study navigator and select study");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("8.2: Navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("8.3 Search visit_Obsro and subject in assessment page");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_MMSEFormName, visitName3,
				subject_Completed);

		reportLog("8.4 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("8.5 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("8.6 Check that Assessment  from PR#4.1 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_Completed);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitName4);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
	
		reportLog("9.1 Navigate to study navigator and select study");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("9.2: Navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("9.3 Search visit_Obsro and subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_MMSEFormName, visitName3, subject_Completed);

		reportLog("9.4 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("9.5 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("9.6 Check that Assessment  from PR#4.2 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_Completed);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(Visit_2176_Pro);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		
		reportLog("Navigate to study navigator and select study");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("9.7 Search visit_Obsro and subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_MMSEFormName, visitName3, subject_Completed);

		reportLog("9.8 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("9.9 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("9.10 Check that Assessment  from PR#4.2 NOT displayed in the \"Move To\" Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_Completed);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(Visit_2076_Obsro);

		reportLog("10 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(Visit_2076_Obsro);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("10.1 Click on Confirm yes button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("10.2 : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("10.3 Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();

		reportLog("10.4  Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();

		reportLog("10.5: Logout application");
		loginPage.logoutApplication();

		reportLog("10.6: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
