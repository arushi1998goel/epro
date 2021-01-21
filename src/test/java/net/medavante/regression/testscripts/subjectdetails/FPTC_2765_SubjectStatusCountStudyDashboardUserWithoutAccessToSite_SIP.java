package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2765_SubjectStatusCountStudyDashboardUserWithoutAccessToSite_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2765_SubjectStatusCountStudyDashboardUserWithoutAccessToSite_SIP(String browser) {
		super(browser);

	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("Study2224New");
	}

	/**
	 * ===================================================================================================
	 * Test Case Id : FP-TC-2765 || Test Case Name :Subject Status Count -
	 * Study Dashboard - User without access to site
	 * ===================================================================================================
	 * 
	 * @throws Exception
	 */
	@Test(description = "FP-TC-2765 : Subject Status Count - Study Dashboard - User without access to site ", groups = {
			"" })
	public void FPTC_2765_SubjectStatusCount_StudyDashboard_UserWithoutAccessToSite() throws Exception {

		reportLog("1.1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSponsorUserType3, AT_Password);

		reportLog("2.1: Navigate to a Subject Detail screen");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("2.2: Select study on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("2.3: Verify " + Constants.All_Sites + " is selected as default");
		studyNavigatorDashBoardPage.verifySelectedSite(Constants.All_Sites);

		reportLog(
				"3.1: Verify Total count of Subjects across 'All Sites' is equal to Sum of Subjects for Rest of the available sites");
		studyNavigatorDashBoardPage
				.verifySubjectsCountAcrossAllSites_EqualsTO_SumOfSubjectsCountAcrossRestOfAvailableSites();

		reportLog("4.1:Logout from the application");
		loginPage.logoutApplication();

		reportLog("4.2:Verify user is logout");
		loginPage.verifyUserLogout();

		reportLog("4.3:Login in to application");
		dashBoardPage = loginPage.siteLogin(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("5.1: Navigate to a Subject Detail screen");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("5.2: Select study on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("5.3: Verify " + Constants.All_Sites + " is selected as default");
		studyNavigatorDashBoardPage.verifySelectedSite(Constants.All_Sites);

		reportLog("6.1: Verify Site - " + Constants.RaterName_20 + " doesn't include under site list");
		studyNavigatorDashBoardPage.verifySiteIsNotIncludeUnderSiteList(Constants.RaterName_20);

		reportLog(
				"6.2: Verify Total count of Subjects across 'All Sites' is equal to Sum of Subjects for Rest of the available sites");
		studyNavigatorDashBoardPage
				.verifySubjectsCountAcrossAllSites_EqualsTO_SumOfSubjectsCountAcrossRestOfAvailableSites();

		reportLog("6.3: Logout from the application");
		loginPage.logoutApplication();

		reportLog("6.4: Verify user is logout");
		loginPage.verifyUserLogout();

	}
}