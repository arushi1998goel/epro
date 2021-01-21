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

public class FPTC_1348_VerifyRefreshingDataOnQueryPanelSwitchingBetweenSubjects_SIP extends BaseTest {

	protected String subjectName1 = "SUBJ_1" + generateRandomString(5),	subjectName2 = "SUBJ_2" + generateRandomString(5),
	firstQuery = "Query1_" + generateRandomString(5), secondQuery = "Query2_" + generateRandomString(5),
	completedNonCRVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1348_VerifyRefreshingDataOnQueryPanelSwitchingBetweenSubjects_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		completedNonCRVisit = properties.getProperty("PaperClinRoVisit1");
		
		reportLog("Created two Subjects");
		
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
		
		reportLog("Create second subject");		
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName2);
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("At least one completed Visit with ClinRO Assessment has queries at visit level.");		
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		
		reportLog("At least 5 Queries created for different Visits (two Visits exist)");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,
				Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName1);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("At least 2 Queries created");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewQuery(firstQuery);
		assessmentDetailPage.clickOnQueriesCollpaseIcon();		

		reportLog("Second Queries created");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,
				Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName2);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("Second score Queries created");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewQuery(secondQuery);
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1348 Test Case Name: Verify that switching between different Subjects didn't reload the Query panel.
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1348_Refreshing data on Query panel - switching between Subjects", groups = {})
	public void FPTC_1348_VerifyRefreshingDataOnQueryPanelSwitchingBetweenSubjects() throws InterruptedException {

		reportLog("1: Log in as a User with claim identified in the Pr.#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("1.1: Navigate to Assessment Detail screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1 : Navigate to first Subject Detail screen identified in the Pr.#1");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName1);		
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName1);
		
		reportLog("2.2: Subject Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
				
		reportLog("3: Select All Queries filter");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		subjectDetailPage.selectCheckboxQueryOption("All Queries");
		
		reportLog("3.1: List of All Queries are displayed");
		subjectDetailPage.verifyListOfAllQueriesDisplayed();				
		
		reportLog("4: At the Query panel select Query for the second Assessment");
		subjectDetailPage.selectQuery(secondQuery);
		
		reportLog("4.2: Background screen navigates to the Assessment selected");
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assesment_AdasCog14List2FormName_QA);
				
		reportLog("5: Check that Query panel isn't refreshed");		
		reportLog("Query panel isn't refreshed");
		subjectDetailPage.verifyQueryPanelIsNotRefreshed();
				
		reportLog("5.1: Check that User stays on the Query they have selected");
		subjectDetailPage.verifyQueryEditButtonDisplay();
		subjectDetailPage.selectCheckboxQueryOption("All Queries");
		
		reportLog("5.2: User stays on the Query they have selected");
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		subjectDetailPage.clickOnQueriesCollpaseIcon();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
