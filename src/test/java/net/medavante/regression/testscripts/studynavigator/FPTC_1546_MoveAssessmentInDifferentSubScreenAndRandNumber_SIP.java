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

public class FPTC_1546_MoveAssessmentInDifferentSubScreenAndRandNumber_SIP extends BaseTest {

	protected String screeningNum1 = "Automation_1" + generateRandomString(4);
	protected String screeningNum4 = "Automation_4" + generateRandomString(4), generatedTempId1, generatedTempId2;
	private String completedNonCRVisit, notcompeletedNonCRVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1546_MoveAssessmentInDifferentSubScreenAndRandNumber_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		completedNonCRVisit = properties.getProperty("visitForSite1");
		notcompeletedNonCRVisit = properties.getProperty("Auto_Visit_ClinROForms");

		reportLog(
				"At least 1 completed Non-CR Assessments for Subject 1: Screening# '123'  and Randomization Number: ‘Empty’ has no value exists");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Select visit and assign rater");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		subjectDetailPage.waitSpinnerToBecomeInvisible();

		reportLog(
				"At least 1 not completed and not assigned Non-CR Assessment for Subject 2: Screening# ‘Empty’ has no value and Randomization Number '256' exists");

		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("Creating subject 2 with not completed non CR visit");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon();

		generatedTempId1 = studyNavigatorDashBoardPage.generateAutoTemporaryID();
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(generatedTempId1);
		studyNavigatorDashBoardPage.verifyAutoGenerateTemporaryCancelBTNIsDisplay();
		studyNavigatorDashBoardPage.verifyScreeningNumNotRequiredAndEditable();
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(generatedTempId1);

		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Select not assigned Non-CR Assessments for Subject 2");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifySubjectPageTitle(generatedTempId1);

		subjectDetailPage.clickOnVisitRow(notcompeletedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.waitSpinnerToBecomeInvisible();

		reportLog(
				"At least 1 completed Non-CR Assessments for Subject 3:  Screening# ‘Empty’ has no value and Randomization Number '256' exists");

		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		reportLog("Creating subject 2 with not completed non CR visit");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon();

		generatedTempId2 = studyNavigatorDashBoardPage.generateAutoTemporaryID();
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(generatedTempId2);
		studyNavigatorDashBoardPage.verifyAutoGenerateTemporaryCancelBTNIsDisplay();
		studyNavigatorDashBoardPage.verifyScreeningNumNotRequiredAndEditable();
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(generatedTempId2);

		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Select Non-CR Assessments for Subject 3");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifySubjectPageTitle(generatedTempId2);

		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		subjectDetailPage.waitSpinnerToBecomeInvisible();

		reportLog(
				"At least 1 not completed and not assigned Non-CR Assessment for Subject 4: Screening# '123'  and Randomization Number: ‘Empty’ has no value exists");

		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum4);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Select not assigned Non-CR Assessments for Subject 4");

		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(notcompeletedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.waitSpinnerToBecomeInvisible();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog(
				" At least 1 completed Non-CR Assessments for Subject 1: Screening# '123'  and Randomization Number: ‘Empty’ has no value exists");

		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit,
				screeningNum1);

		reportLog("Click on Assessment link");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		subjectDetailPage.setStartedDate();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		reportLog(
				"At least 1 completed Non-CR Assessments for Subject 3:  Screening# ‘Empty’ has no value and Randomization Number '256' exists");

		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit, generatedTempId2);

		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		subjectDetailPage.setStartedDate();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * =========================================================================
	 * =========================================== Test Case Id: FP-TC-1546
	 * Test Case Name: Move Assessment between different Subject's Screening and Randomization Numbers 
	 * =========================================================================
	 * ===========================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1546_Verify that Subject's Randomization Number reflected change in PDF Source Audit History in case when Assessment was moved", groups = {})
	public void FPTC_1546_MoveAssessmentBetweenDifferentSubjectScreenAndRandomNum()
			throws InterruptedException {

		reportLog(
				"1.1: Log in to the Portal with PR#1 -> Open the Study Dashboard -> Select a Study and a Site from PR#2 ");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.2: Navigate to the Assessment Listing screen and select the Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit,
				screeningNum1);

		reportLog("1.2.1: Visit Assessment screen from PR#3 is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("'Action' option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("1.3: Select action to moving an Assessment");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(generatedTempId1);
		assessmentDetailPage.clickOnChangeToVisitDropDown();

		reportLog("1.4: Check that Visit PR#4 is displayed in 'Move To' Assessment drop-down list ");
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedNonCRVisit);

		reportLog("1.5: Move Assessment PR#3 to Assessment PR#4 ");
		assessmentDetailPage.changeVisitDropDown(notcompeletedNonCRVisit);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("1.6: Save changes");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();

		reportLog("2.1: Open PDF for Assessment Pr#4");
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyRatersNameOnPDFsTitlePage(Constants.ATAssignedRater_10);

		reportLog("2.2:  Check that two change is reflected in PDF Source Audit History:");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.ATAssignedRater_10);

		reportLog(
				"2.2.1: Screening Number with OLD value and New Value ‘Empty and Change Reason “Assessment Transfer”");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(screeningNum1);

		reportLog(
				"2.2.2: Randomization Number with OLD value ‘Empty’ and New Value ‘256’ and Change Reason “Assessment Transfer”");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(generatedTempId1);
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory("Assessment Transfer");

		reportLog("3.1: Navigate to the Assessment Listing screen and select the Assessment from PR#5");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit, generatedTempId2);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("'Action' option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("3.2: Select action to moving an Assessment");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(screeningNum4);
		assessmentDetailPage.clickOnChangeToVisitDropDown();

		reportLog("3.3: Check that Visit PR#6 is displayed in 'Move To' Assessment drop-down list ");
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedNonCRVisit);

		reportLog("3.4: Move Assessment PR#5 to Assessment PR#6 ");
		assessmentDetailPage.changeVisitDropDown(notcompeletedNonCRVisit);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("3.5: Save changes");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();

		reportLog("4.1: Open PDF for Assessment Pr#4");
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyRatersNameOnPDFsTitlePage(Constants.ATAssignedRater_10);

		reportLog("4.2:  Check that two change is reflected in PDF Source Audit History:");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.ATAssignedRater_10);

		reportLog(
				"4.2.1: Screening Number with OLD value and New Value ‘Empty and Change Reason “Assessment Transfer”");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(screeningNum4);

		reportLog(
				"4.2.2: Randomization Number with OLD value ‘256’ and New Value ‘Empty’ and Change Reason “Assessment Transfer”.");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(generatedTempId2);
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory("Assessment Transfer");

		reportLog(" Logout application");
		loginPage.logoutApplication();

		reportLog(" Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
