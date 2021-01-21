package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1532_MoveAssesBetweenDiffSubjectScrenumSIP extends BaseTest {
	protected String subjectName1 = "SUBJ_1"+ generateRandomString(5),subjectName2 = "SUBJ_2" + generateRandomString(5);
	private String completedNonCRVisit, notcompeletedNonCRVisit;

	public FPTC_1532_MoveAssesBetweenDiffSubjectScrenumSIP(String browser) {
		super(browser);
	}

	

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		completedNonCRVisit = properties.getProperty("visitForSite1");
		notcompeletedNonCRVisit = properties.getProperty("Auto_Visit_ClinROForms");		
		
		reportLog("Creating subject 1 with completed non CR visit");
		
		dashBoardPage = loginPage.loginInApplication(superAdminUN, superAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();	
		
		reportLog("Select visit and assign rater");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.waitSpinnerToBecomeInvisible();		
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		
		reportLog("Creating subject 2 with not completed non CR visit");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName2);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Select visit and assign rater");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(notcompeletedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.waitSpinnerToBecomeInvisible();		
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		reportLog(" At least 1not completed and not assigned Non-CR Assessments for Subject 2: Screening #234 exist");
		reportLog("At least 1 completed Non-CR Assessments for Subject 1: Screening# 123 exist");

		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		//dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);
		
		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();		
		
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1532 Test Case Name: Verify that Subject's Screening number reflected change in PDF Source Audit History in case when Assessment was moved
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1532_Verify that Subject's Screening number reflected change in PDF Source Audit History in case when Assessment was moved", groups = {})
	public void FPTC_1532_verifyMoveAssesBetweenDiffSubjectScrenumSIP() throws InterruptedException {

		reportLog("1.1: Log in to the Portal with PR#1 -> Open the Study Dashboard -> Select a Study and a Site from PR#2 ");
		loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.2: Navigate to the Assessment Listing screen and select the Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();
		
		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		
		reportLog("1.2.1: Visit Assessment screen from PR#3 is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("'Action' option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();
		
		reportLog("1.3: Select action to moving an Assessment");		
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);
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
		
		reportLog("2.2: Check that changes is reflected in PDF Source Audit History:");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.ATAssignedRater_10);
		
		reportLog("2.2.1: Screening Number with OLD value 123");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(subjectName1);
		
		reportLog("2.2.2: New Value 234");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(subjectName2);
		
		reportLog("2.2.3: Change Reason 'Assessment Transfer'");		
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory("Assessment Transfer");		
	
		reportLog(" Logout application");
		loginPage.logoutApplication();

		reportLog(" Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
