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

public class FPTC_1346_VerifyRefreshingDataOnQueryPanelVisitDetailScreen_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5), firstQuery = "Query1_" + generateRandomString(5),
	secondQuery = "Query2_" + generateRandomString(5), firstScoreQuery = "ScoreQuery1_" + generateRandomString(5),
	secondScoreQuery = "ScoreQuery2_" + generateRandomString(5),completedNonCRVisit, notCompletedNonCRVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1346_VerifyRefreshingDataOnQueryPanelVisitDetailScreen_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		completedNonCRVisit = properties.getProperty("PaperClinRoVisit1");
		notCompletedNonCRVisit = properties.getProperty("PaperClinRoVisit2");

		reportLog("Creating a subject from user and configure studies accordingly");
		
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("At least one completed Visit with ClinRO Assessment has queries at visit level.");
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);

		reportLog("At least 10 Queries created for Visit");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		
		subjectDetailPage.addNewQuery(firstQuery);
		subjectDetailPage.addNewQuery(secondQuery);
		
		subjectDetailPage.verifyPresenceOfQueryBySubject(subjectName);
		subjectDetailPage.clickOnQueriesCollpaseIcon();				
		
		reportLog("Logout from application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("complete the assessment");
		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);
		
		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("At least 2 score Queries created");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewScoreQuery(firstScoreQuery,"Score","Trial 1 - Temple");	
		assessmentDetailPage.addNewScoreQuery(secondScoreQuery,"Score","Trial 1 - Temple");
		
		reportLog("Click on Query collpase icon");
		assessmentDetailPage.clickOnQueriesCollpaseIcon();	
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1346 Test Case Name: Verify that Query screen isn't repopulated every time User selects a Query which doesn't match the screen opened in the background.
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1346_Refreshing data on Query panel: Visit Detail screen", groups = {})
	public void FPTC_1346_VerifyRefreshingDataOnQueryPanelVisitDetailScreen() throws InterruptedException {

		reportLog("1: Log in as a User with claim identified in the Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("1.2: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2: Navigate to Visit Detail screen");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(completedNonCRVisit, subjectName);
		
		reportLog("2.1: Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("3: Open Queries side panel identified in the Pr.#1 and Pr.#2");
		visitDetaiLPage.clickOnQueriesIcon();
		visitDetaiLPage.verifyQueriesDetailsPanelIsOpened();
		
		reportLog("3.1: List of Queries displayed for the selected Subject");
		visitDetaiLPage.verifyPresenceOfQueryBySubject(subjectName);
		
		reportLog("4.1: Select any Query for current Subject from Pr#1");
		reportLog("Selected Query is displayed");
		visitDetaiLPage.selectQuery(firstQuery);
		
		reportLog("4.2: Check that navigation between Queries aren't refreshed background screen");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("Background screen isn't refreshed");
		visitDetaiLPage.verifyBackgroundScreenIsNotRefreshed();
		
		reportLog("5: Select any Query for current Subject from Pr#1");		
		reportLog("Selected Query is displayed");
		visitDetaiLPage.selectQuery(firstScoreQuery);
				
		reportLog("5.1: Check that navigation between Queries are refreshed background screen");
		visitDetaiLPage.verifyBackgroundScreenIsRefreshed();		
		visitDetaiLPage.clickOnQueriesCollpaseIcon();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
