package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1352_VerifyRetainingOfActionsOnQueryPanelVisitDetailScreen_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String firstQuery = "Query1_" + generateRandomString(5);
	protected String secondQuery = "Query2_" + generateRandomString(5);
	
	/*protected String subjectName = "SUBJ_msifq";
	protected String firstQuery = "ScoreQuery1_hqyui";
	protected String secondQuery = "ScoreQuery2_xmccc";*/
	
	protected String firstVisitQuery = "ScoreQuery1_" + generateRandomString(5);
	protected String secondVisitQuery = "ScoreQuery2_" + generateRandomString(5);
	
	protected String completedNonCRVisit1, completedNonCRVisit2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1352_VerifyRetainingOfActionsOnQueryPanelVisitDetailScreen_SIP(String browser) {
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

		subjectDetailPage.navigateToHomePage();
		dashBoardPage.verifyMedavantePortalPage();

		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("Search the subject in assessment page");

		reportLog("Click on Visit link");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit1,
				subjectName);
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("First visit Queries created");
		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();		
		visitDetaiLPage.addNewQuery(firstVisitQuery);

		visitDetaiLPage.navigateToHome();
		dashBoardPage.navigateToStudyNavigator();																												
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("Search the subject in assessment page");

		reportLog("Click on Visit link");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit2,
				subjectName);
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("2nd visit Queries created");
		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();
		visitDetaiLPage.addNewQuery(secondVisitQuery);
		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1352 Test Case Name: Verify that Query panel retains
	 * last search/user action and Verify last Query activity during switching
	 * between different Visits.
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1352_Refreshing data on Query panel: Visit Detail screen", groups = {})
	public void FPTC_1352_VerifyRetainingOfActionsOnQueryPanelVisitDetailScreen() throws InterruptedException {

		reportLog("1: Log in as a User with claim identified in the Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.1: User logged in successfully");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2: Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("2: Navigate to first Visit Detail screen identified in the Pr.#1");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit1,
				subjectName);

		reportLog("2.1: First Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("3.1:  Clicks on Query icon");
		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();
		visitDetaiLPage.selectQuery(firstQuery);

		reportLog("3.2: Check that panel display queries for first Visit Detail screen");
		visitDetaiLPage.verifyPresenceOfQueryBySubject(subjectName);

		reportLog("3.3: Select the first query");
		visitDetaiLPage.selectQuery(firstQuery);
		
		reportLog("4.1: Types reply to the first Visit Query");
		visitDetaiLPage.replyQuery("Reply 1");
		
		reportLog("4.2: Close the query panel");
		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		reportLog("5.1: Reopen the Query panel");
		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();

		reportLog("5.2: Check that background still on the first Subject Detail screen");
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit1);

		reportLog("5.3: Check that replying text which was typing in Step #4 is displayed");
		visitDetaiLPage.verifyCloseQueryButtonDisplayed();
		visitDetaiLPage.clickonArrowDownIcon();
		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		reportLog("6: Navigate back to first Visit Detail screen (Pr.#1) and open Query panel");
		visitDetaiLPage.clickOnQueriesIcon();

		reportLog("6.1: Queries for first Subject are displayed");
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit1);

		reportLog("7: Check that Open queries filter is selected");
		visitDetaiLPage.selectCheckboxQueryOption("All Queries");

		reportLog("7.1: All open Queries for that study site are displayed");
		visitDetaiLPage.verifyListOfAllQueriesDisplayed();	
		
		reportLog("7.2: Unselect the All queries checkbox");
		visitDetaiLPage.selectCheckboxQueryOption("All Queries");
		
		reportLog("8.1: Selects second Visit Query (Pr.#2)");
		visitDetaiLPage.selectQuery(secondQuery);
		
		reportLog("8.2: Check that Background will automatically navigate to second Visit Detail screen");
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit2);

		reportLog("8.3: Types reply to the second visit queries ");
		visitDetaiLPage.replyQuery("Reply 2");

		reportLog("8.4: Closes query panel");
		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		reportLog("9.1: Reopen the Query panel");
		visitDetaiLPage.clickOnQueriesIcon();

		reportLog("9.2: Check that User able to see second Visit Query they were working on");
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit2);
		visitDetaiLPage.clickonArrowDownIcon();
		
		reportLog("9.3: Check that User able to see the text they were typing in");
		visitDetaiLPage.verifyCloseQueryButtonDisplayed();
		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		reportLog("10: Navigate back to first Visit Detail screen (Pr.#1) and open Query panel");
		visitDetaiLPage.navigateToHome();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		studyNavigatorDashBoardPage.selectStudy(studyName);

		studyNavigatorDashBoardPage.navigateToVisitsListing();
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit1, subjectName);
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit1);

		reportLog("10.1: Queries for the first visit are displayed");
		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit1);

		reportLog("11: Types reply to the first Visit Query and close the panel");
		visitDetaiLPage.selectQuery(firstQuery);
		visitDetaiLPage.verifyCloseQueryButtonDisplayed();
		visitDetaiLPage.replyQuery("Reply 3");

		reportLog("11.1: Close the query panel");
		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		reportLog("12: Navigates to second Visit Detail screen (Pr.#2) and reopen Query panel");
		visitDetaiLPage.navigateToHome();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		studyNavigatorDashBoardPage.selectStudy(studyName);

		studyNavigatorDashBoardPage.navigateToVisitsListing();
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit2, subjectName);
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit2);

		reportLog("12.1: User able to see Queries for second Visit");
		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyVisitPageTitle(completedNonCRVisit2);

		reportLog("12.2: Check that User will see Queries for the second Visit and last activity will be lost");
		visitDetaiLPage.selectQuery(secondQuery);
		visitDetaiLPage.verifyCloseQueryButtonDisplayed();

		reportLog("Close the query panel");
		visitDetaiLPage.clickOnQueriesCollpaseIcon();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
