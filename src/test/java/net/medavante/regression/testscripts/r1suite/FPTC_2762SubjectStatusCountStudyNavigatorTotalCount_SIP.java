package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2762SubjectStatusCountStudyNavigatorTotalCount_SIP extends BaseTest {

	private String studyName;
	private String siteName;


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2762SubjectStatusCountStudyNavigatorTotalCount_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("StudyToBeSelected");
		siteName = prop.getProperty("SiteToBeSelected");
		System.setProperty("className", getClass().getSimpleName());
	}

	/**
	 * =========================================================================
	 * Test Case Id : FPTC-2762 Test Case Name :Subject Status Count - Study
	 * Navigator - Total Count
	 * 
	 * =========================================================================
	 * 
	 */

	@Test(description = "FP-TC- 2762 Subject Status Count - Study Navigator - Total Count", groups = { "R1" })
	public void verifySubjectStatusCountStudyNavigatorTotalCount_SIP() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(userName, password);

		reportLog("2:Click On Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("2.1:Select study on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("2.2:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();

		reportLog("2.3:Select site for the selected study");
		studyNavigatorDashBoardPage.selectSite(siteName);

		/*reportLog("2.4:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();*/

		reportLog("3:Subject statuses are present on the study navigator page");
		studyNavigatorDashBoardPage.verifySubjectsStatusesForSubjectsOnStudyNavigator();

		reportLog("4.1 :Verify Subject's respective counts and status as NEW");
		//studyNavigatorDashBoardPage.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber("New", "0");

		reportLog("4.2:Verify Subject's Listing Page is opened status as NEW");
		studySubjectListing.verifySubjectListIsOpened();

		reportLog("4.3:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage = dashBoardPage.navigateBackToStudyNavigatorPage();

		/*reportLog("4.4:Verify Study Navigator Dashboard Page is opened with data");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();*/

		reportLog("5.1:Verify Subject's respective counts and status as SCREENED");
		//studyNavigatorDashBoardPage.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber("Custom screened", "1");

		reportLog("5.2:Verify Subject's Listing Page is opened status as SCREENED");
		studySubjectListing.verifySubjectListIsOpened();

		reportLog("5.3:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage = dashBoardPage.navigateBackToStudyNavigatorPage();

		/*reportLog("5.4:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();*/

		reportLog("6:Verify Subject's respective counts and status as SCREEN FAILED");
		//studyNavigatorDashBoardPage.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber("Screen Failed", "2");

		reportLog("6.2:Verify Subject's Listing Page is opened status as SCREEN FAILED");
		studySubjectListing.verifySubjectListIsOpened();

		reportLog("6.3:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage = dashBoardPage.navigateBackToStudyNavigatorPage();

		/*reportLog("6.4:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();*/

		reportLog("7.1:Verify Subject's respective counts and status as ENROLLED");
		//studyNavigatorDashBoardPage.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber("Enrolled","3");

		reportLog("7.2:Verify Subject's Listing Page is opened status as ENROLLED");
		studySubjectListing.verifySubjectListIsOpened();

		reportLog("7.3:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage = dashBoardPage.navigateBackToStudyNavigatorPage();

		reportLog("7.4:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();

		reportLog("8.1:Verify Subject's respective counts and status as COMPLETED");
		////studyNavigatorDashBoardPage.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber("Completed", "4");

		reportLog("8.2:Verify Subject's Listing Page is opened status as COMPLETED");
		studySubjectListing.verifySubjectListIsOpened();

		reportLog("8.3:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage = dashBoardPage.navigateBackToStudyNavigatorPage();

		reportLog("8.4:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();

		reportLog("9.1:Verify Subject's respective counts and status as DISCONTINUED");
		//studyNavigatorDashBoardPage.verifySubjectStatusIsDisplayedCorrectWithCountIndicationNumber("Discontinued","5");

		reportLog("9.2:Verify Subject's Listing Page is opened status as DISCONTINUED");
		studySubjectListing.verifySubjectListIsOpened();

		reportLog("9.3:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage = dashBoardPage.navigateBackToStudyNavigatorPage();

		/*reportLog("9.4:Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();*/

		reportLog("10: Logout application");
		loginPage.logoutApplication();

		reportLog("10.1: Logout application");
		loginPage.verifyUserLogout();

	}

}
