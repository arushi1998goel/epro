package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1539_MoveAssessmentWithAPaperTranscription_SIP extends BaseTest {

	protected String subject_CompletedWithPaperTranscriptionSource = "SUBJ_" + generateRandomString(5);

	protected String subject_CompletedWithOutPaperTranscriptionSource = "SUBJ_" + generateRandomString(5);
	protected String subject_NotCompleted_1 = "SUBJ_" + generateRandomString(5);
	protected String subject_NotCompleted_2 = "SUBJ_" + generateRandomString(5);

	private String visitName, visitName4, moveToAssesment;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1539_MoveAssessmentWithAPaperTranscription_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
		visitName = properties.getProperty("visitName");
		visitName4 = properties.getProperty("visitName4");
		moveToAssesment = properties.getProperty("moveToAssesment");
 
		reportLog(
				"Creating a subject for the Study Site (Pr#3), 3.1. Assessment_1 with a paper transcription with source");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subject_CompletedWithPaperTranscriptionSource);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.uncheckNotAdministeredCheckBox();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.clickOnPaperTrnscriptionCheckBox();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.verifyPaperTranscriptionCheckBoxFlagSet();
		assessmentDetailPage.selectSourceOrAttachementOption("Attachments");
		assessmentDetailPage.clickOnPaperSourceIcon();
		assessmentDetailPage.closeOnFailedassessment();
		assessmentDetailPage.uploadAttachementFile(Constants.exeFilePath, Constants.PDFFile2ToUpload,
				Constants.windowTitle);
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);

		reportLog(
				"Creating a subject for the Study Site (Pr#3), 3.2. Assessment_2 without a paper transcription with source");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subject_CompletedWithOutPaperTranscriptionSource);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName4);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.uncheckNotAdministeredCheckBox();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.clickOnPaperTrnscriptionCheckBox();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.closeOnFailedassessment();
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);

		reportLog("Creating a subject for the Study Site (Pr#4), 4.1. Assessment_1 Not Completed Non_Cr");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subject_NotCompleted_1);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);

		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		reportLog("Creating a subject for the Study Site (Pr#4), 4.2. Assessment_2 Not Completed Non_Cr");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subject_NotCompleted_2);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName4);
		loginPage.logoutApplication();
	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1539 Test Case Name: Move Assessment with a paper
	 * transcription ===========================================
	 * 
	 * 
	 */

	@Test
	public void FPTC_1539_verifyMoveAssessmentWithAPaperTranscription_SIP() throws InterruptedException, Exception {

		reportLog("1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("1.1:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("1.2 Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormName, visitName, subject_CompletedWithPaperTranscriptionSource);

		reportLog("1.3 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("2 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("2.1 Check that Visit PR#4 is displayed in Move To Assessment drop-down list ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_NotCompleted_1);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitName);

		reportLog("2.2 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(visitName);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("2.3 Click on Confirm yes button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("2.4 : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("2.5 Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();

		reportLog("2.6  Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();

		reportLog(
				"2.6Check that the Assessment PR#4 got a paper transcription with source and all appropriate changes");
		assessmentDetailPage.verifyPaperTranscriptionCheckBoxFlagSet();

		reportLog("2.7 Click on Attachement link");
		assessmentDetailPage.selectSourceOrAttachementOption("Attachments");

		reportLog("Verify Pdf is Present");
		assessmentDetailPage.verifyPaperSourceAttachementIsPresent();

		reportLog("3.1:navigate to Dashboard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("3.2:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("3.3 Search visit1 and subject in assessment page");
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormName, visitName4,
				subject_CompletedWithOutPaperTranscriptionSource);

		reportLog("3.4 Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(moveToAssesment);

		reportLog("3.5 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("3.6 Check that Visit PR#4 is displayed in Move To Assessment drop-down list ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject_NotCompleted_2);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitName4);

		reportLog("3.7 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(visitName);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("3.8 Click on Confirm yes button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("3.9 : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("3.10 Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();

		reportLog("3.11  Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();

		reportLog("3.12 Check that the Assessment PR#4 got a paper transcription with source and all appropriate changes");
		assessmentDetailPage.verifyPaperTranscriptionCheckBoxFlagSet();

		reportLog("3.13 Click on Attachement link");
		assessmentDetailPage.selectSourceOrAttachementOption("Attachments");

		reportLog("3.14 Verify Pdf is Present");
		assessmentDetailPage.verifyPaperSourceAttachementIsNotPresent();

		reportLog("3.15:Logout application");
		loginPage.logoutApplication();

		reportLog("3.16:Verify user is logout");
		loginPage.verifyUserLogout();
	}
}
