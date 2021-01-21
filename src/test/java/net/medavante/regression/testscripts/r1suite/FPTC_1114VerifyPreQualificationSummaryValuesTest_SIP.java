package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.preqqualification.PreQualificationDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1114VerifyPreQualificationSummaryValuesTest_SIP extends BaseTest {

	private String studyName;
	private int sentSurvey, respondedSurvey;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1114VerifyPreQualificationSummaryValuesTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("studyName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id:FPTC_1114 Test Case Name: Pre-qualification Summary -	 * Values
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1114--Pre-qualification Summary - Values", groups = { "R1" })
	public void FPTC_1114_verifyPreQualificationSummaryValues() {

		reportLog("1.1 Login to Portal.");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3 Navigate to Administration");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("1.3 Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("1.4 Search " + studyName + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("1.5 Navigate to Pre-qualification tab.");
		prequalificationPage = adminstrationStudiesPage.navigateToStudySurveyTrackerTab();

		reportLog("1.4 Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("1.5 Search " + studyName + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("1.6 Navigate to Pre-qualification tab.");
		prequalificationPage = adminstrationStudiesPage.navigateToStudySurveyTrackerTab();

		reportLog("1.7 Note total count of sent emails for the site");
		sentSurvey = prequalificationPage.getFilterCount(Constants.sentSurveyLabel);

		reportLog("1.8 Note total count of responded emails for the site");
		respondedSurvey = prequalificationPage.getFilterCount(Constants.sentRespondedSurveyLabel);

		reportLog("2.1 Navigate back to Home page.");
		dashBoardPage=adminstrationOrganizationPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("2.2 Navigate to Pre-qualification Summary screen");
		preQualificationDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(PreQualificationDashBoardPage.class, Constants.NavigateText, Constants.QualificationText);

		reportLog("2.3 Select " + studyName + " in Study list ");
		preQualificationDashBoardPage.selectStudy(studyName);

		reportLog("2.4 Verify Summary for study survey results is shown.");
		preQualificationDashBoardPage.verifySummaryChartIsOpened();

		reportLog("2.5 Verify Sent surveys column value for the site");
		preQualificationDashBoardPage.verifySummarySurveysSentCount(sentSurvey);

		reportLog("2.6 Verify Received surveys column value for the site");
		preQualificationDashBoardPage.verifySummarySurveysReceivedCount(respondedSurvey);

		reportLog("2.7 Logout application");
		loginPage.logoutApplication();

		reportLog("2.8: verify user Logout the application");
		loginPage.verifyUserLogout();

	}

}
