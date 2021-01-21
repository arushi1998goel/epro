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

public class FPTC_1344_VerifyRefreshingDataOnQueryPanelSwitchingBetweenAssessmentDetailScreen_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5), firstQuery = "Query1_" + generateRandomString(5),
			secondQuery = "Query2_" + generateRandomString(5),
			firstScoreQuery = "ScoreQuery1_" + generateRandomString(5),
			secondScoreQuery = "ScoreQuery2_" + generateRandomString(5), completedNonCRVisit1, completedNonCRVisit2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1344_VerifyRefreshingDataOnQueryPanelSwitchingBetweenAssessmentDetailScreen_SIP(String browser) {
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
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("At least 2 score Queries created");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewQuery(secondScoreQuery);

		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1344 Test Case Name: Refreshing data on query panel:
	 * Assessment Detail screen
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1344_Refreshing data on query panel: Assessment Detail screen  ", groups = {})
	public void FPTC_1344_VerifyRefreshingDataOnQueryPanelSwitchingBetweenAssessmentDetailScreen()
			throws InterruptedException {

		reportLog("1: Log in as a User with claim identified in the Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1: Navigate to Assessment Detail screen Pr.#1");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1 : Navigate to Assessment Detail screen Pr.#1");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(completedNonCRVisit1);

		reportLog("Click on Assessment link");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();

		reportLog("2.2: Assessment Detail screen is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3: Open Queries side panel identified in the Pr.#1 and Pr.#2");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();

		reportLog("3.1: List of queries displayed for the selected Assessment");
		assessmentDetailPage.verifyListOfAllQueriesDisplayed();

		reportLog("4: Select Query for current Assessment from Pr#1");
		assessmentDetailPage.selectQuery(firstScoreQuery);

		reportLog("4.1.1: Selected Query is displayed");
		assessmentDetailPage.verifyQueryEditButtonDisplay();

		reportLog("4.2: Check that navigation between queries aren't refreshed background screen");
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assesment_AdasCog14List2FormName_QA);

		reportLog("4.2.2: Background screen isn't refreshed");
		assessmentDetailPage.verifyBackgroundScreenIsNotRefreshed();

		reportLog("5.1: Select any Query from Pr#2");
		assessmentDetailPage.selectQuery(secondScoreQuery);

		reportLog("5.1.1: Selected Query is displayed");
		assessmentDetailPage.verifyQueryEditButtonDisplay();

		reportLog("5.2: Check that navigation between score queries aren't refreshed background screen");
		assessmentDetailPage.verifyAssessmentPageTitle(Constants.Assesment_AdasCog14List2FormName_QA);

		reportLog("5.2.2: Background screen isn't refreshed");
		assessmentDetailPage.verifyBackgroundScreenIsNotRefreshed();

		reportLog("6.1: Navigate back to Assessment Detail screen");
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("6.1.1: Assessment Detail screen is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("6.2: Open PDF form and proceed to the next page after the cover page");
		assessmentDetailPage.clickOnPdfImage();

		reportLog("6.2.2: PDF form is opened");
		assessmentDetailPage.verifyPDFFormIsOpen();

		reportLog("7.1: Open Queries side panel");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();

		reportLog("List of queries displayed for the selected Assessment");
		assessmentDetailPage.verifyListOfAllQueriesDisplayed();

		reportLog("7.2: Select Query for current Assessment from Pr#1");
		assessmentDetailPage.selectQuery(firstScoreQuery);

		reportLog("Selected Query is displayed");
		assessmentDetailPage.verifyQueryEditButtonDisplay();

		reportLog("7.3: Check that navigation between queries aren't return PDF back to the cover page");
		assessmentDetailPage.verifyPDFFormIsOpen();
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("8.1: Navigate back to Assessment Detail screen");
		assessmentDetailPage.clickOnCancelButton();

		reportLog("Assessment Detail screen is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("8.2: Open PDF form and proceed to the next page after the cover page");
		assessmentDetailPage.clickOnPdfImage();

		reportLog("PDF form is opened");
		assessmentDetailPage.verifyPDFFormIsOpen();

		reportLog("9.1: Open Queries side panel");
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();

		reportLog("List of queries displayed for the selected  Assessment");
		assessmentDetailPage.verifyListOfAllQueriesDisplayed();

		reportLog("9.2: Select any score Query from Pr#2");
		assessmentDetailPage.selectQuery(secondScoreQuery);

		reportLog("Select any score Query from Pr#2");
		assessmentDetailPage.verifyQueryEditButtonDisplay();

		reportLog("9.3: Check that navigation between queries aren't return PDF back to the cover page");
		assessmentDetailPage.verifyPDFFormIsOpen();
		assessmentDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("Close the PDF");
		assessmentDetailPage.clickOnCancelButton();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
