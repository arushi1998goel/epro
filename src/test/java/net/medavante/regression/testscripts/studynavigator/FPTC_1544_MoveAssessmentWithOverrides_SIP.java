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

public class FPTC_1544_MoveAssessmentWithOverrides_SIP extends BaseTest {
	
	protected String subjectName1="SUBJ_1" + generateRandomString(5),subjectName2 = "SUBJ_2" + generateRandomString(5);
	private String completedNonCRVisit, notcompeletedNonCRVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1544_MoveAssessmentWithOverrides_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		//subjectName1 = properties.getProperty("Subject_NonCRWithOverrides");
		completedNonCRVisit = properties.getProperty("VisitCompletedNonCRWithOverrides");
		notcompeletedNonCRVisit = properties.getProperty("VisitNotCompletedNonCRWithOverrides");		
		
		reportLog("At least 1 completed Non-CR Assessment with overrides exists");	
		
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("Select visit and verify that Assessment is not assigned");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		
		
		reportLog("At least 1 NOT completed Non-CR Assessment with No/Empty Subject Visit status exists");
		
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName2);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
				
		/*studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName2);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		*/
		reportLog("Select visit and verify that Assessment is not assigned");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(notcompeletedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);
		//studyNavigatorDashBoardPage.searchAssessment(Constants.Assesment_AdasCog14List2FormNameSecondType);

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
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		reportLog("At least 1 NOT completed Non-CR Assessment with No/Empty Subject Visit status exists");
		
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1544 Test Case Name: Verify that a User is able to move Non-CR  Assessment with overrides
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1544_Verify that a User is able to move Non-CR  Assessment with overrides.", groups = {})
	public void FPTC_1544_VerifythatUserIsAbleToMoveNonCRAssessmentWithOverrides() throws InterruptedException {

		reportLog("1.1: Login to the Portal with PR#1 -> Open the Study Dashboard -> Select a Study and a Site from PR#2 ");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("1.2: Navigate to the Assessment Listing screen and select the Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit, subjectName1);
		
		reportLog("1.3: Visit Assessment screen from PR#3 is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		/*Scores needed for this study on stg due to this commenting this code for stg env*/
		
	/*	assessmentDetailPage.verifyScoreResponseValues("Date1", "Rater not available");
		assessmentDetailPage.verifyScoreResponseValues("Dropdown1", "Rater not available");
		assessmentDetailPage.verifyScoreResponseValues("Time1", "Rater not available");
		assessmentDetailPage.verifyScoreResponseValues("SingleChoice1", "Rater not available");*/
				
		reportLog("1.4: 'Action' option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();
				
		reportLog("2.1: Select action to moving an Assessment");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName2);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
				
		reportLog("2.2: Check that Assessment PR#4 is displayed in 'Move To' Assessment drop-down list ");
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedNonCRVisit);
		
		reportLog("2.3: Move Assessment PR#3 to Assessment PR#4 ");
		assessmentDetailPage.changeVisitDropDown(notcompeletedNonCRVisit);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		
		reportLog("2.4: Save changes "); 
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);		
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();	
		
		reportLog("2.5: Navigate to the Assessment Listing screen and select the Assessment from PR#4");
		
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(notcompeletedNonCRVisit, subjectName2);
		
		reportLog("2.6: Check that the Assessment PR#4 got all the appropriate changes with the PDF with overrides");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		/*Scores needed for this study on stg due to this commenting this code for stg env*/
		
		/*assessmentDetailPage.verifyScoreResponseValues("Date1", "Rater not available");
		assessmentDetailPage.verifyScoreResponseValues("Dropdown1", "Rater not available");
		assessmentDetailPage.verifyScoreResponseValues("Time1", "Rater not available");
		assessmentDetailPage.verifyScoreResponseValues("SingleChoice1", "Rater not available");*/
		
		reportLog("2.7: Move Assessment PR#4 to Assessment PR#3");
		
		assessmentDetailPage.verifyActionOptionIsDisplayed();
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName1);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(completedNonCRVisit);
		assessmentDetailPage.changeVisitDropDown(completedNonCRVisit);	
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);		
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();	
		assessmentDetailPage.navigateBackToDashBoard();		
		
		reportLog(" Logout application");
		loginPage.logoutApplication();

		reportLog(" Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
