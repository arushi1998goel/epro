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

public class FPTC_1349_VerifyRefreshingDataOnQueryPanelSwitchingBetweenVisits_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5), firstQuery = "Query1_" + generateRandomString(5),
			secondQuery = "Query2_" + generateRandomString(5),
			firstScoreQuery = "ScoreQuery1_" + generateRandomString(5),
			secondScoreQuery = "ScoreQuery2_" + generateRandomString(5), completedNonCRVisit1, completedNonCRVisit2;


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1349_VerifyRefreshingDataOnQueryPanelSwitchingBetweenVisits_SIP(String browser) {
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

		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("Search the subject in Visit page");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit1,
				subjectName);
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("First Score Queries created");

		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();
		visitDetaiLPage.addNewQuery(firstScoreQuery);

		visitDetaiLPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("Search the subject in assessment page");

		reportLog("Click on Visit link");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit2,
				subjectName);
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("2nd Score Queries created");

		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();
		visitDetaiLPage.addNewQuery(secondScoreQuery);

		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1349 Test Case Name: Verify that switching between Visits didn't reload the Query panel.
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1349_Refreshing data on Query panel - switching between Visits ", groups = {})
	public void FPTC_1349_VerifyRefreshingDataOnQueryPanelSwitchingBetweenVisits() throws InterruptedException {

		reportLog("1: Log in as a User with claim identified in the Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2: Navigate to Visit Detail screen");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit1,
				subjectName);

		reportLog("2.1: Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("2.2: Open Queries side panel identified in the Pr.#1 and Pr.#2");
		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();

		reportLog("3: Select All Queries filter");
		visitDetaiLPage.selectCheckboxQueryOption("All Queries");

		reportLog("3.1: List of Queries displayed for the selected Subject");
		visitDetaiLPage.verifyListOfAllQueriesDisplayed();

		reportLog("4.1: At the Query panel select Query for the second Visit");
		visitDetaiLPage.selectQuery(secondScoreQuery);
		visitDetaiLPage.verifyPresenceOfQueryBySubject(subjectName);

		reportLog("4.2: Background screen navigates to the the Visit selected");
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit1);

		reportLog("5: Check that Query panel isn't refreshed");
		reportLog("Query panel isn't refreshed");
		visitDetaiLPage.verifyQueryPanelIsNotRefreshed();

		reportLog("5.1: Check that User stays on the Query they have selected");
		visitDetaiLPage.verifyQueryEditButtonDisplay();
		assessmentDetailPage.selectCheckboxQueryOption("All Queries");

		reportLog("5.2: User stays on the Query they have selected");
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();
		visitDetaiLPage.selectCheckboxQueryOption("All Queries");
		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}

}
