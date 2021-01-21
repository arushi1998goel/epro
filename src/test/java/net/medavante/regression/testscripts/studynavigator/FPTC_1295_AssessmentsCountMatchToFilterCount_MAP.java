package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1295_AssessmentsCountMatchToFilterCount_MAP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1295_AssessmentsCountMatchToFilterCount_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1295 Test Case Name:Assessments list: assessments
	 * count match to filter count
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-1295_Assessments list: assessments count match to filter count ", groups = { "" })
	public void FPTC_1295_verifyAssessmentsListAssessmentsCountMatchToFilterCount() throws Exception {

		reportLog("1.1:Log in to MA Portal as User in Pr#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager, AT_Password);
		
		reportLog("1.2:User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigate to  Study Navigator -> Assessments list");
		//studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("2.2:Assessments list is displayed ");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("2.3:Counter is displayed the number of Assessments");
		studyNavigatorDashBoardPage.verifyCounterCount();

		reportLog("2.4:Table count match filter to count");
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();
		loginPage.logoutApplication();

		reportLog("3.1:	Log in to Sponsor Portal as User in Pr#3");
		loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);

		reportLog("3.2:User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("4.1:Navigate to  Study Navigator -> Assessments list");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		//studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("4.2:Assessments list is displayed ");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("4.3:Counter is displayed the number of Assessments");
		studyNavigatorDashBoardPage.verifyCounterCount();

		reportLog("4.4:Table count match filter to count");
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();
		loginPage.logoutApplication();

		reportLog("5.1:Log in to Site Portal as User in Pr#4");
		loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);

		reportLog("5.2:User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("6.1:Navigate to  Study Navigator -> Assessments list");
		//studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("6.2:Assessments list is displayed ");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("6.3:Counter is displayed the number of Assessments");
		studyNavigatorDashBoardPage.verifyCounterCount();

		reportLog("6.4:Table count match filter to count");
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
