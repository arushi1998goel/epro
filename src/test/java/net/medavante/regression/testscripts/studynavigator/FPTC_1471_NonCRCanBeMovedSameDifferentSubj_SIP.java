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

public class FPTC_1471_NonCRCanBeMovedSameDifferentSubj_SIP extends BaseTest {

	protected String subjectName1 = "SUBJ_1" + generateRandomString(5);
	protected String subjectName2 = "SUBJ_2" + generateRandomString(5);
	private String completedNonCRVisit1, completedNonCRVisit2, notcompeletedCRVisit3;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1471_NonCRCanBeMovedSameDifferentSubj_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyMoveVisit");
		completedNonCRVisit1 = properties.getProperty("Auto_ClinRo");
		completedNonCRVisit2 = properties.getProperty("visitClinRoNotAssigned");
		notcompeletedCRVisit3 = properties.getProperty("Visit_NotCompleted_2195SFBTC");

		reportLog("Creating a subject from user and configure visits for them");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("At least two completed Non-CR Visit_1 exist for the Subject_1");

		subjectDetailPage.clickOnVisitRow(completedNonCRVisit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		subjectDetailPage.clickOnVisitRow(completedNonCRVisit2);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		reportLog("At least one not completed Non-CR Visit_2 exist for the Subject_1");

		subjectDetailPage.clickOnVisitRow(notcompeletedCRVisit3);
		subjectDetailPage.clickOnAddVisitIcon();	
		

		reportLog("At least one not completed Non-CR Visit_1 exist for the Subject_2");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_11);
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName2);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Select visit");
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		loginPage.logoutApplication();

		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

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
		subjectDetailPage.setStartedDate();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
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
		subjectDetailPage.setStartedDate();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1471 Test Case Name: Verify that Non-CR Visit can be
	 * moved to the same and different Subject.
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1471_Verify that Non-CR Visit can be moved to the same and different Subject.", groups = {})
	public void FPTC_1471_VerifyNonCRCanBeMovedSameDifferentSubj() throws InterruptedException {

		reportLog("1.: Log in to portal as a user from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1:  Navigate to Visit Listing screen and select Visit from Pr.#2");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);		
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit1,
				subjectName1);

		reportLog("Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("'Action' option is displayed for Visit");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("2.2:  Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName1);
		visitDetaiLPage.clickOnChangeToVisitDropDown();

		reportLog("2.3: Check that Visit Pr.#3 is displayed in ‘Move To’ Visit drop down list");
		reportLog("2.4: Move Visit Pr.#2 to Visit Pr.#3 for the same Subject");
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedCRVisit3);
		visitDetaiLPage.selectChangeToVisit(notcompeletedCRVisit3);

		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("2.6: Select reason for change");
		visitDetaiLPage.verifyReasonForChangePopUpDisplayed();
		visitDetaiLPage.selectReasonForChangeOption(Constants.Other_Reason_For_Change);
		visitDetaiLPage.inputReasonComment(Constants.Other_Reason_For_Change);
		visitDetaiLPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		visitDetaiLPage.closeSuccessMessage();
		visitDetaiLPage.waitSpinnerToBecomeInvisible();

		reportLog("3.1: Navigate to Visit listing screen");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("Select second Visit from Pr#2 for the Subject_1");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit2,
				subjectName1);

		reportLog("Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("'Action' option is displayed for Visit");
		visitDetaiLPage.verifyActionOptionIsDisplayed();

		reportLog("3.2: Select action to moving visit:");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		reportLog("Change the subject 2");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName2);
		visitDetaiLPage.clickOnChangeToVisitDropDown();

		reportLog("3.3: Check that Visit Pr.#4 is displayed in ‘Move To’ Visit drop down list:");
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(notcompeletedCRVisit3);

		reportLog("Visit from Pr.#4 is displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.selectChangeToVisit(notcompeletedCRVisit3);

		reportLog("3.4: Move second Visit from Pr#2 to Visit Pr.#4:");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("3.5: Save changes");
		visitDetaiLPage.verifyReasonForChangePopUpDisplayed();
		visitDetaiLPage.selectReasonForChangeOption(Constants.Other_Reason_For_Change);
		visitDetaiLPage.inputReasonComment(Constants.Other_Reason_For_Change);
		visitDetaiLPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		visitDetaiLPage.closeSuccessMessage();
		visitDetaiLPage.waitSpinnerToBecomeInvisible();

		reportLog("3.6: Logout application");
		loginPage.logoutApplication();

		reportLog("3.7: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
