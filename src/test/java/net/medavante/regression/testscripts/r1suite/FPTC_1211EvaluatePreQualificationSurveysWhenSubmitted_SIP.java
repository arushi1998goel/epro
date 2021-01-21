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

public class FPTC_1211EvaluatePreQualificationSurveysWhenSubmitted_SIP extends BaseTest {


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1211EvaluatePreQualificationSurveysWhenSubmitted_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("PreQualificationStudy"); 
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1211 Test Case Name: System will automatically
	 * evaluate pre-qualification surveys when submitted
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1211 --System will automatically evaluate pre-qualification surveys when submitted", groups = {
			"R1" })
	public void FPTC_1211_verifySystemAutomaticallyEvaluatePreQualificationSurveysWhenSubmitted() {

		reportLog("1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);

		reportLog("1.1: Navigate to Administration ");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("1.2: Navigate to studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("1.3:  Click on Pre-Qualification tab");	
		prequalificationPage = adminstrationStudiesPage.navigateToStudySurveyTrackerTab();

		reportLog("1.4:  verify Pre-Qualification tab is displayed");
		prequalificationPage.verifyAdministrationStudiesSurveyTrackerPage();

		reportLog("2.1:  Navigate to the DashBoard page");
		adminstrationOrganizationPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("2.2:  Click on Pre-Qualification option");
		preQualificationDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(PreQualificationDashBoardPage.class, Constants.NavigateText, Constants.QualificationText);

		reportLog("2.3:  change the view mode");
		preQualificationDashBoardPage.changeViewMode();

		reportLog("2.4:   Select defind casestudy");
		preQualificationDashBoardPage.selectStudy(studyName);

		reportLog("2.5:Verify Lables In The Page");
		preQualificationDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		preQualificationDashBoardPage.clickOnRefreshButton();
		preQualificationDashBoardPage.verifyPreQualificationRaterScaleListIsDisplayed();

		reportLog("2.6:Verify Lables In The Page");
		preQualificationDashBoardPage.verifyPreQualificationRaterLabelPresent();

		reportLog("2.7:Verify Rater Column Values");
		preQualificationDashBoardPage.verifyPreQualificationRaterScaleListRaterColumnValues(Constants.RaterName_Test21);

		reportLog("2.8:Verify Site Column Values");
		preQualificationDashBoardPage.verifyPreQualificationRaterScaleListSiteColumnValues(Constants.ATAssignedRater_10);
		

		reportLog("2.9: For the scale that meets Qualification criteria is: Pre-qualified (System & Overall)");
		preQualificationDashBoardPage.verifyDetailsForScale(Constants.AdasCogParentScale,Constants.NotPrequalified);

		reportLog(
				"2.10: For the scale that doesn't meet For Review and Qualification criteria is: Not Pre-qualified (System & Overall)");
		preQualificationDashBoardPage.verifyDetailsForScale(Constants.ADBCParentScale,Constants.Prequalified);
		
		reportLog(
				"2.11: For the scale that doesn't meet Qualification criteria but meets For Review criteria:For Review(System) & not set (Overall)");
		preQualificationDashBoardPage.verifyDetailsForScale(Constants.CDRParentScale,Constants.ForReviewText);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
