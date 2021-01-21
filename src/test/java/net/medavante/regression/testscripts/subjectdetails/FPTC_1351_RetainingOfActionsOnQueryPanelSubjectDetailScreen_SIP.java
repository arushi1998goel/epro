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

public class FPTC_1351_RetainingOfActionsOnQueryPanelSubjectDetailScreen_SIP extends BaseTest {

	protected String subjectName1 = "SUBJ_1" + generateRandomString(5),
	subjectName2 = "SUBJ_2" + generateRandomString(5),firstQueryForSub1 = "QuerySub1_1" + generateRandomString(5),
	secondQueryForSub1 = "QuerySub1_2" + generateRandomString(5), firstQueryForSub2 = "QuerySub2_1" + generateRandomString(5),
	secondQueryForSub2 = "QuerySub2_2" + generateRandomString(5),completedNonCRVisit,notCompletedNonCRVisit;
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1351_RetainingOfActionsOnQueryPanelSubjectDetailScreen_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		completedNonCRVisit = properties.getProperty("PaperClinRoVisit1");
		notCompletedNonCRVisit = properties.getProperty("PaperClinRoVisit2");

		reportLog("Creating first subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("At least one completed Visit with ClinRO Assessment has queries at visit level.");
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		
		reportLog("At least two open Queries for the first Subject exist");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		subjectDetailPage.addNewQuery(firstQueryForSub1);
		subjectDetailPage.addNewQuery(secondQueryForSub1);
		subjectDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("Creating second subject");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName2);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("At least one completed Visit with ClinRO Assessment has queries at visit level.");
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		reportLog("At least two open Queries for the second Subject exist");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		subjectDetailPage.addNewQuery(firstQueryForSub2);
		subjectDetailPage.addNewQuery(secondQueryForSub2);
		subjectDetailPage.clickOnQueriesCollpaseIcon();
		
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	
		}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1351 Test Case Name: Verify that Query panel retains
	 * last search/user action and Verify last Query activity during switching
	 * between different Subjects.
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1351_Retaining of actions on Query panel: Subject Detail screen ", groups = {})
	public void FPTC_1351_RetainingOfActionsOnQueryPanelSubjectDetailScreen() throws InterruptedException {

		reportLog("1: Log in as a user with claim identified in the Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("User logged in successfully");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2.1 : Navigate to first Subject Detail screen identified in the Pr.#1");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSubject(subjectName1);
		
		reportLog("2.2: Subject Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3: Clicks on Query icon");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		
		subjectDetailPage.selectQuery(firstQueryForSub1);
		
		reportLog("3.1: Query panel is displayed Queries for first Subject Detail screen");
		subjectDetailPage.verifyPresenceOfQueryBySubject(subjectName1);

		reportLog("Select the first query");
		subjectDetailPage.selectQuery(firstQueryForSub1);
		
		reportLog("4.1: Types reply to the first Subject Query");
		subjectDetailPage.replyQuery("Reply 1");

		reportLog("4.2: Close the query panel");
		subjectDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("5.1: Reopen the Query panel");
		subjectDetailPage.clickOnQueriesIcon();

		reportLog("5.2: Check that background still on the first Subject Detail screen");
		subjectDetailPage.verifySubjectPageTitle(subjectName1);

		reportLog("5.3: Check that replying text which was typing in Step #4 is displayed");
		subjectDetailPage.verifyCloseQueryButtonDisplayed();
		subjectDetailPage.clickonArrowDownIcon();
		subjectDetailPage.clickOnQueriesCollpaseIcon();
		
		reportLog("6: Navigate back to first Subject Detail screen (Pr.#1) and open Query panel");
		subjectDetailPage.clickOnQueriesIcon();

		reportLog("Queries for first Subject are displayed");
		subjectDetailPage.verifyPresenceOfQueryBySubject(subjectName1);

		reportLog("7: Select Open Queries filters");
		subjectDetailPage.selectCheckboxQueryOption("All Queries");

		reportLog("7.1: All open Queries for that study site are displayed");
		subjectDetailPage.verifyListOfAllQueriesDisplayed();
		
		reportLog("8.1: Selects Query for the second Subject (Pr.#2)");
		subjectDetailPage.selectQuery(secondQueryForSub2);
		
		reportLog("8.2: Check that Background will automatically navigate to the second Subject Detail screen");
		subjectDetailPage.verifySubjectPageTitle(subjectName2);

		reportLog("8.3: Types reply to the second Subject queries ");
		subjectDetailPage.replyQuery("Reply 2");
		subjectDetailPage.selectCheckboxQueryOption("All Queries");

		reportLog("8.4: Closes query panel");
		subjectDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("9.1: Reopen the Query panel");
		subjectDetailPage.clickOnQueriesIcon();

		reportLog("9.2: Check that user able to see Query for the second Subject they were working on");
		subjectDetailPage.verifySubjectPageTitle(subjectName2);
		subjectDetailPage.selectQuery(secondQueryForSub2);
		
		reportLog("9.3: Check that replying text which was typing in");
		subjectDetailPage.verifyCloseQueryButtonDisplayed();
		subjectDetailPage.clickonArrowDownIcon();
		subjectDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("10: Navigate back to first Subject Detail screen (Pr.#1) and open Query panel");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);		
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName1);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName1);
		subjectDetailPage.verifySubjectPageTitle(subjectName1);

		reportLog("10.1: Queries for the first Subject are displayed");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyPresenceOfQueryBySubject(subjectName1);

		reportLog("11: Types reply to the first Subject Query and close the panel");
		subjectDetailPage.selectQuery(firstQueryForSub1);
		subjectDetailPage.verifyCloseQueryButtonDisplayed();
		subjectDetailPage.replyQuery("Reply 3");

		reportLog("11.1: Close the query panel");
		subjectDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("12: Navigates to second Subject Detail screen (Pr.#2) and reopen Query panel");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName2);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName2);
		subjectDetailPage.verifySubjectPageTitle(subjectName2);

		reportLog("12.1: User able to see Queries for second Subject");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyListOfAllQueriesDisplayed();
		subjectDetailPage.verifyPresenceOfQueryBySubject(subjectName2);

		reportLog("12.2: Types reply to the first Subject Query and close the panel");
		subjectDetailPage.selectQuery(secondQueryForSub2);

		reportLog("12.3: Close the query panel");
		subjectDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
