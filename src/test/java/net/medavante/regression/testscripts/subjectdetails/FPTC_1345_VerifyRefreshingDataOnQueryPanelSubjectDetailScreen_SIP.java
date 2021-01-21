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


public class FPTC_1345_VerifyRefreshingDataOnQueryPanelSubjectDetailScreen_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5),firstQuery = "Query1_" + generateRandomString(5),
	secondQuery = "Query2_" + generateRandomString(5), thirdQuery = "Query3_" + generateRandomString(5),
	firstScoreQuery = "ScoreQuery1_" + generateRandomString(5), secondScoreQuery = "ScoreQuery2_" + generateRandomString(5),
	completedNonCRVisit, notCompletedNonCRVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1345_VerifyRefreshingDataOnQueryPanelSubjectDetailScreen_SIP(String browser) {
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

		reportLog("At least 3 Queries created for Subject");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		subjectDetailPage.addNewQuery(firstQuery);
		subjectDetailPage.addNewQuery(secondQuery);
		subjectDetailPage.addNewQuery(thirdQuery);		
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
		
		dashBoardPage=loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit);

		reportLog("Click on Assessment link");
	    studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
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
	 * Test Case Id: FP-TC-1345 Test Case Name: Verify that query screen isn't re populated every time User selects a query which doesn't match the screen opened in the background.
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1345_Verify Refreshing Data On Query Panel Subject Detail Screen", groups = {})                                                                
	public void FPTC_1345_VerifyRefreshingDataOnQueryPanelSubjectDetailScreen() throws InterruptedException {

		reportLog("1: Log in as a User with claim identified in the Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2.1: Navigate to Subject Detail screen");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSubject(subjectName);
		
		reportLog("2.1: Subject Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("3: Open Queries side panel identified in the Pr.#1 and Pr.#2");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();
		
		reportLog("3.1: List of Queries displayed for the selected Subject");
		subjectDetailPage.verifyPresenceOfQueryBySubject(subjectName);
		
		reportLog("4.1: Select any Query for current Subject from Pr#1");
		subjectDetailPage.selectQuery(firstQuery);
		
		reportLog("Selected Query is displayed");
		//subjectDetailPage.selectQuery(firstQuery);
		
		reportLog("4.2: Check that navigation between Queries aren't refreshed background screen");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("Background screen isn't refreshed");
		subjectDetailPage.verifyBackgroundScreenIsNotRefreshed();
		
		reportLog("5: Select Score Query from Pr#2");		
		reportLog("Selected Query is displayed");
		subjectDetailPage.selectQuery(secondScoreQuery);
				
		reportLog("5.1: Check that navigation between Queries are refreshed background screen");
		subjectDetailPage.verifyBackgroundScreenIsRefreshed();
		
		reportLog("6: Close the Query panel");
		subjectDetailPage.clickOnQueriesCollpaseIcon();
		
		reportLog("7: Logout application");
		loginPage.logoutApplication();

		reportLog("7.1: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
