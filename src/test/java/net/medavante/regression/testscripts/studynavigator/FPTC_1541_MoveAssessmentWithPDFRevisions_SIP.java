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

public class FPTC_1541_MoveAssessmentWithPDFRevisions_SIP extends BaseTest {
	
	protected String subjectName1 = "SUBJ_1" + generateRandomString(5);
	protected String subjectName2 = "SUBJ_2" + generateRandomString(5);
	
	private String completedNonCRVisit1, completedNonCRVisit2, notcompeletedCRVisit3;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class) 
	public FPTC_1541_MoveAssessmentWithPDFRevisions_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		completedNonCRVisit1 = properties.getProperty("Auto_ClinRo");
		completedNonCRVisit2 = properties.getProperty("visitClinRoNotAssigned");
		notcompeletedCRVisit3 = properties.getProperty("Auto_Paper_ClinRo_Visit1");
						
		reportLog("At least 2 NOT completed Assessment with No/Empty Subject Visit status exists");
		
		reportLog(" At least 2 completed Non-CR Assessments exists:  3.1. Assessment_1 with 1 PDF revision");
		reportLog(" At least 2 completed Non-CR Assessments exists:  3.2. Assessment_1 with 3 PDF revisions");
		reportLog("Creating a subject from user and configure visits for them");
		
		dashBoardPage = loginPage.loginInApplication(superAdminUN, superAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();			
		
		reportLog("At least two completed Non-CR Visit_1 exist for the Subject_1");
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		subjectDetailPage.clickOnVisitRow(completedNonCRVisit2);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		
		reportLog("At least one not completed Non-CR Visit_1 exist for the Subject_2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName2);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Select visit");
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		reportLog(" At least 2 completed Non-CR Assessments exists:  3.1. Assessment_1 with 1 PDF revision");
		reportLog(" At least 2 completed Non-CR Assessments exists:  3.2. Assessment_1 with 3 PDF revisions");
		
		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);
		studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List2FormNameSecondType);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit2);
		studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List2FormNameSecondType);

		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();		
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1541_ Test Case Name: Verify that a User is able to move an Assessment with PDF revisions
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1541_Verify that a User is able to move an Assessment with PDF revisions", groups = {})
	public void FPTC_1541_VerifythatUserIsAbleToMoveAnssessmentWithPDFRevisions() throws InterruptedException {

		reportLog("1.1: Log in to the Portal with PR#1 -> Open the Study Dashboard -> Select a Study and a Site from PR#2 ");
		dashBoardPage=loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
				
		reportLog("1.2: Navigate to the Assessment Listing screen and select the Assessment from PR#3.1");		
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit1, subjectName1);
		
		reportLog("Visit Assessment screen from PR#3.1 is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("'Action' option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();
		
		reportLog("2.1: Select action to moving an Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		
		reportLog("2.2: Check that Assessment PR#4 is displayed in 'Move To' Assessment drop-down list ");
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(completedNonCRVisit2);		
		
		reportLog("2.3: Move Assessment PR#3.1 to Assessment PR#4");
		assessmentDetailPage.changeVisitDropDown(completedNonCRVisit2);	
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		
		reportLog("2.4: Save changes ");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);		
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();	
		
		reportLog("2.5: Check that the Assessment PR#4 got all appropriate changes and the PDF with 1 revision");
		reportLog("The Assessment PR#4 got all appropriate changes and the PDF with 1 revision");		
		
		reportLog("3.1: Navigate to the Assessment Listing screen and select the Assessment from PR#3.2");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit2, subjectName1);
		
		reportLog("3.2: Select action to moving an Assessment ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
				
		reportLog("3.3: Check that Assessment PR#4 is displayed in 'Move To' Assessment drop-down list ");
		assessmentDetailPage.verifyActionOptionIsDisplayed();
		
		reportLog("3.4: Move Assessment PR#3.2 to Assessment PR#4");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedCRVisit3);	
		assessmentDetailPage.changeVisitDropDown(notcompeletedCRVisit3);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		
		reportLog("3.5: Save changes ");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);		
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();	
		
		reportLog("3.6: Check that the Assessment PR#4 got all appropriate changes and the PDF with 3 revisions");
		
		reportLog(" Logout application");
		loginPage.logoutApplication();

		reportLog(" Verify user is logout");
		loginPage.verifyUserLogout();
	}

}
