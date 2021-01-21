package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1350_VerifyRetainingOfActionsOnQueryPanelAssessmentDetailScreen_SIP extends BaseTest {

	//protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String firstQuery = "Query1_" + generateRandomString(5);
	protected String secondQuery = "Query2_" + generateRandomString(5);
	protected String firstScoreQuery = "ScoreQuery1_" + generateRandomString(5);
	protected String secondScoreQuery = "ScoreQuery2_" + generateRandomString(5);
	protected String subjectName = "SUBJ_rblmv";
	protected String completedNonCRVisit1, completedNonCRVisit2;


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1350_VerifyRetainingOfActionsOnQueryPanelAssessmentDetailScreen_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		completedNonCRVisit1 = properties.getProperty("PaperClinRoVisit1");
		completedNonCRVisit2 = properties.getProperty("PaperClinRoVisit2");

	/*	reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(superAdminUN, superAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		// studyDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("At least one completed Visit with ClinRO Assessment has queries at visit level.");
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);

		subjectDetailPage.clickOnVisitRow(completedNonCRVisit2);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);

		reportLog("At least 5 Queries created for different Assessments (two Assessments exist)");

		subjectDetailPage.navigateToHomePage();
		dashBoardPage.verifyMedavantePortalPage();

		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("At least 2 score Queries created");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewQuery(firstScoreQuery);
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("At least 2 score Queries created");

		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewQuery(secondScoreQuery);

		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
*/	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1350 Test Case Name: Retaining of actions on Query panel: Assessment Detail screen
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1350_Retaining of actions on Query panel: Assessment Detail screen", groups = {})
	public void FPTC_1350_VerifyRetainingOfActionsOnQueryPanelAssessmentDetailScreen() throws InterruptedException {

		reportLog("1: Log in as a User with claim identified in the Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.1: User logged in successfully");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2: Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.ATSiteAssignedRater_10);

		reportLog("2: Navigate to first Visit Detail screen identified in the Pr.#1");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("2.1 : Navigate to Assessment Detail screen Pr.#1");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		
		reportLog("2.2: Assessment Detail screen is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.1:  Clicks on Quer icon");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();

		reportLog("3.2: Check that panel display queries for first Visit Detail screen");
		assessmentDetailPage.verifyPresenceOfQueryBySubject(subjectName);

		reportLog("3.3: Select the first query");
		assessmentDetailPage.selectQuery(firstQuery);
		//assessmentDetailPage.selectQuery("ScoreQuery1_dslyk");
		
		reportLog("4.1: Types reply to the first Visit Query");
		assessmentDetailPage.replyQuery("Reply 1");

		reportLog("4.2: Close the query panel");
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("5.1: Reopen the Query panel");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		
		reportLog("5.2: Check that background still on the first Subject Detail screen");
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assessment_CAM_Paper_Test);

		reportLog("5.3: Check that replying text which was typing in Step #4 is displayed");
		assessmentDetailPage.verifyCloseQueryButtonDisplayed();

		reportLog("6: Navigate back to first Visit Detail screen (Pr.#1) and open Query panel");
		assessmentDetailPage.clickOnQueriesIcon();

		reportLog("Queries for first Subject are displayed");
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assessment_CAM_Paper_Test);

		reportLog("7: Check that Open queries filter is selected");
		assessmentDetailPage.selectCheckboxQueryOption("All");

		reportLog("All open Queries for that study site are displayed");
		assessmentDetailPage.verifyListOfAllQueriesDisplayed();

		reportLog("8.1: Selects second Visit Query (Pr.#2)");
		assessmentDetailPage.selectQuery(secondQuery);
		
		reportLog("8.2: Check that Background will automatically navigate to second Visit Detail screen");
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assessment_CAM_Paper_Test);

		reportLog("8.3: Types reply to the second visit queries ");
		assessmentDetailPage.replyQuery("Reply 2");

		reportLog("8.4: Closes query panel");
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("9.1: Reopen the Query panel");
		assessmentDetailPage.clickOnQueriesIcon();

		reportLog("9.2: Check that User able to see second Visit Query they were working on");
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assessment_CAM_Paper_Test);
		assessmentDetailPage.selectQuery(secondQuery);

		reportLog("9.3: Check that User able to see the text they were typing in");
		assessmentDetailPage.verifyCloseQueryButtonDisplayed();
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("10: Navigate back to first Visit Detail screen (Pr.#1) and open Query panel");
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		studyNavigatorDashBoardPage.selectStudy(studyName);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("10.1: Queries for the first visit are displayed");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assessment_CAM_Paper_Test);

		reportLog("11: Types reply to the first Visit Query and close the panel");
		assessmentDetailPage.selectQuery(firstQuery);
		assessmentDetailPage.verifyCloseQueryButtonDisplayed();
		assessmentDetailPage.replyQuery("Reply 3");

		reportLog("11.1: Close the query panel");
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("12: Navigates to second Visit Detail screen (Pr.#2) and reopen Query panel");
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		studyNavigatorDashBoardPage.selectStudy(studyName);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit2);
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("12.1: User able to see Queries for second Visit");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assessment_CAM_Paper_Test);

		reportLog("12.2: Check that User will see Queries for the second Visit and last activity will be lost");
		assessmentDetailPage.selectQuery(secondQuery);
		assessmentDetailPage.verifyCloseQueryButtonDisplayed();

		reportLog("Close the query panel");
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
