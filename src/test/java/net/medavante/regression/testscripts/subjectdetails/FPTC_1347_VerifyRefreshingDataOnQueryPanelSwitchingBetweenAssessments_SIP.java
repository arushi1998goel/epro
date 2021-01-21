package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1347_VerifyRefreshingDataOnQueryPanelSwitchingBetweenAssessments_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5),
	firstQuery = "Query1_" + generateRandomString(5), secondQuery = "Query2_" + generateRandomString(5),
	firstScoreQuery = "ScoreQuery1_" + generateRandomString(5),	secondScoreQuery = "ScoreQuery2_" + generateRandomString(5);
	String completedNonCRVisit1, completedNonCRVisit2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1347_VerifyRefreshingDataOnQueryPanelSwitchingBetweenAssessments_SIP(String browser) {
		super(browser);	
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		completedNonCRVisit1 = properties.getProperty("PaperClinRoVisit1");
		completedNonCRVisit2 = properties.getProperty("PaperClinRoVisit2");

		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("At least one completed Visit with ClinRO Assessment has queries at visit level.");
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		subjectDetailPage.clickOnVisitRow(completedNonCRVisit2);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		reportLog("At least 5 Queries created for different Assessments (two Assessments exist)");

		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,
				Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("At least 2 score Queries created");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewQuery(firstScoreQuery);
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,
				Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit2);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("Create second score Queries for another assessment");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewQuery(secondScoreQuery);

		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1347 Test Case Name: Refreshing data on Query panel - switching between Assessments
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1347_Refreshing data on Query panel - switching between Assessments ", groups = {})
	public void FPTC_1347_VerifyRefreshingDataOnQueryPanelSwitchingBetweenAssessments() throws InterruptedException {

		reportLog("1: Log in as a User with claim identified in the Pr.#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1: Navigate to Assessment Detail screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1 : Navigate to first Assessment Detail screen identified in the Pr.#1");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();

		reportLog("2.2: Assessment Detail screen is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3: Select All Queries filter");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.selectCheckboxQueryOption("All Queries");

		reportLog("3.1: List of All Queries are displayed");
		assessmentDetailPage.verifyListOfAllQueriesDisplayed();

		reportLog("4: At the Query panel select Query for the second Assessment");
		assessmentDetailPage.selectQuery(secondScoreQuery);

		reportLog("4.2: Background screen navigates to the Assessment selected");
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assesment_AdasCog14List2FormName_QA);

		reportLog("5: Check that Query panel isn't refreshed");
		reportLog("Query panel isn't refreshed");
		assessmentDetailPage.verifyQueryPanelIsNotRefreshed();

		reportLog("5.1: Check that User stays on the Query they have selected");
		assessmentDetailPage.verifyQueryEditButtonDisplay();
		assessmentDetailPage.selectCheckboxQueryOption("All Queries");

		reportLog("5.2: User stays on the Query they have selected");
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
