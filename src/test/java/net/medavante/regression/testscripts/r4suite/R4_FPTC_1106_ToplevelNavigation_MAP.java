/**
 *@author 
* @date 22/05/2020
* =========================================================================
     *  Test Case Id: FP-TC-1106 || Test Case Name: Top-level-Navigation
    
* ========================================================================= 
*/
package net.medavante.regression.testscripts.r4suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.pages.formsLibrary.FormManagerPage;
import net.medavante.portal.pages.preqqualification.PreQualificationDashBoardPage;
import net.medavante.portal.pages.traininglibrary.TrainingDetailsPage;
import net.medavante.portal.pages.analytics.AnalyticsDashBoardPage;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_1106_ToplevelNavigation_MAP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_1106_ToplevelNavigation_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void GetTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("studyNameTestCase2227");
	}

	@Test(description = "FP-TC-1106_ToplevelNavigation_MAP", groups = { "R4" })
	public void R4_FPTC_1106_ToplevelNavigation() throws InterruptedException, Exception {
		reportLog("2: Log in to Portal as the User of Pr#1 ");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		//dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Home screen is displayed");
		dashBoardPage.verifyMedavantePortalPage();
		//dashBoardPage.verifySelectedPageScreenIsDisplayed(Constants.h1Tag, Constants.HomePageTitle);

		reportLog("2.2: Top-level navigation is displayed");
		dashBoardPage.verifyTopLevelNavigationIsDisplayed();

		reportLog("2.3: Home item selected by default");
		dashBoardPage.verifyHomeItemIsSelectedByDefault();

		reportLog("2.4: Navigate item is Displayed ");
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.spanTag, Constants.NavigateText);

		reportLog("2.5: Analytics item is Displayed ");
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.AnalyticsText);

		reportLog("2.6: Configure item is Displayed");
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.spanTag, Constants.ConfigureNavText);

		reportLog("2.7: user's dropdown is Displayed ");
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.spanTag, Constants.CurrentUser);

		reportLog("2.8: Quick navigation is Displayed ");
		dashBoardPage.verifyQuickNavigatorIsDisplayed();

		reportLog("3: Select Navigate label in the menu of header area");
		dashBoardPage.clickOnDropdown(Constants.NavigateText);

		reportLog("3.1: Navigate menu expended with: Study, University, Rating, Qualification(Legacy) ");
		dashBoardPage.verifyNavigateDropDownItems(Constants.SubTabstudy);
		dashBoardPage.verifyNavigateDropDownItems(Constants.SubTabUniversity);
		dashBoardPage.verifyNavigateDropDownItems(Constants.SubTabRatings);
		dashBoardPage.verifyNavigateDropDownItems(Constants.SubTabQualification);

		reportLog("4.1: Select Study and study page Displayed");
		studyNavigatorDashBoardPage = dashBoardPage.clickOnNavigateDropDownItems(Constants.SubTabstudy);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyStudyDashBoardPage();

		reportLog("4.2: Select University and page displayed");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		ratersDetailsPage.verifyRaterPageIsDisplayed();

		reportLog("4.3: select Rating and page Displayed");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.verifyRatingPagrIsDisplayed();

		reportLog("4.5: select qualification and qualification page is displayed");
		preQualificationDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				PreQualificationDashBoardPage.class, Constants.NavigateText, Constants.QualificationText);
		preQualificationDashBoardPage.verifySelectedPageIsDisplayed();

		reportLog("5.1: Select Analytics label");
		analyticsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AnalyticsDashBoardPage.class,
				Constants.AnalyticsText);

		reportLog("5.2: Analytics page is displayed");
		analyticsPage.verifyAnalyticsDashBoardPageIsDisplayed();

		reportLog("6: Select Configure label in the menu of header area");
		dashBoardPage.clickOnDropdown(Constants.ConfigureNavText);

		reportLog(
				"6.1: Configure Menu expended with: Study Setup, Forms Library, Training Library, Qualification Library");
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.StudySetupText);
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.FormsLibraryNavText);
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.TrainingLibraryText);
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.QualificationLibraryText);

		reportLog("7.1: select Study Setup and study setup page is displayed ");
		dashBoardPage.clickOnDropdown(Constants.ConfigureNavText);
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationOrganizationPage.verifyAllTabsPresent();

		reportLog("7.2: select Form library and Form library page is displayed");
		formManagerPage = dashBoardPage.selectHorizontalUpperNavMenuItem(FormManagerPage.class,
				Constants.ConfigureNavText, Constants.FormsLibraryNavText);
		formManagerPage.verifyFormManagerPage(Constants.Form_Manager);

		reportLog("7.3: Training library and training library page is displayed ");
		trainingDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(TrainingDetailsPage.class,
				Constants.ConfigureNavText, Constants.TrainingLibraryText);
		trainingDetailsPage.verifyTrainingDetailsPage();

		reportLog("7.4: Select Qualification library and qualification library page is displayed ");
		preQualificationDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				PreQualificationDashBoardPage.class, Constants.ConfigureNavText, Constants.QualificationLibraryText);
		preQualificationDashBoardPage.verifyQualificationLibraryPageIsDisplayed();

		reportLog("8.1: Select Quick navigation in the menu of header area ");
		dashBoardPage.selectQuickNavigator();

		reportLog("8.2: Quick navigator expended with: System Status, About & Help, Privacy Policy");
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.SubTabsystem);
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.SubTababout);
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.SubTabprivacy);

		reportLog("9.1: Select to open Privacy Policy");
		dashBoardPage.clickOnTopLevelNavigatorItem(Constants.aTag, Constants.SubTabprivacy);

		reportLog("9.2: MedAvante Privacy Policy page is displayed in a new tab");
		String parentWindow = switchToChildWindow();
		dashBoardPage.verifySelectedPageScreenIsDisplayed(Constants.h1Tag, Constants.PrivacyPageTitle);

		reportLog("10.1: Navigate back to Home screen");
		switchParentWindow(parentWindow);

		reportLog("10.2: Select to open About & Help");
		dashBoardPage.selectQuickNavigator();
		dashBoardPage.clickOnTopLevelNavigatorItem(Constants.aTag, Constants.SubTababout);

		reportLog("10.3: About page is displayed");
		dashBoardPage.verifySelectedPageScreenIsDisplayed(Constants.h2Tag, Constants.AboutPageTitle);

		reportLog("11.1: Navigate back to Home screen");
		dashBoardPage.navigateBack();

		/***************
		 * System status tab is not present on Test Environment
		 *****************/

		reportLog("11.2: Select to open System Status");
		dashBoardPage.selectQuickNavigator();
		dashBoardPage.clickOnTopLevelNavigatorItem(Constants.aTag, Constants.SubTabsystem);

		reportLog("11.3: System Status page is displayed");
		dashBoardPage.verifySystemStatusTabIsDisplayed();

		reportLog("12.1: Navigate back to Home screen ");
		dashBoardPage.navigateBack();

		reportLog("12.2: Select User's name label in the menu of header area ");
		dashBoardPage.clickOnDropdown(Constants.CurrentUser);

		reportLog("12.3: Menu is expanded with the following label: Log Out");
		dashBoardPage.verifyTopLevelNavigatorItemIsDisplayed(Constants.aTag, Constants.LogOut);

		reportLog("13.1: Select Log Out ");
		dashBoardPage.clickOnTopLevelNavigatorItem(Constants.aTag, Constants.LogOut);

		reportLog("13.2: MedAvante Log in page is displayed");
		loginPage.verifyUserLogout();
	}
}
