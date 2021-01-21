/**************************************************************
*@author siddharth
*@date 16-09-2019
*Test Case Id :- FP-TC-1056
*Test Case Description :- Show that Analytics tile is displayed in case of at least one active Versions
* are associated with the Dashboard for Portal side (siteportal, maportal and sponsor portal) .
*
*****************************************************************/
package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.analytics.AnalyticsDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1056_VerifyView_Analytics_Tile_ActiveOrInactive_Versions_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1056_VerifyView_Analytics_Tile_ActiveOrInactive_Versions_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		dashBoardName = properties.getProperty("DashBoardName2");
	}

	@Test(description = "R3_FPTC__1056_VerifyView_Analytics_Tile_ActiveOrInactive_Versions_SIP", groups = { "R3" })
	public void R3_FPTC_1056_VerifyView_Analytics_Tile_ActiveOrInactive_Versions() {

		try {

			reportLog("1.1 :Login To the application as Site Portal");
			dashBoardPage = loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);

			reportLog("1.2 :Verify site user is logged in");
			dashBoardPage.verifyMedavantePortalPage();

			reportLog("2: Open Home Page");
			dashBoardPage.verifyPageTitle(Constants.HomeNavText);

			reportLog("3.1: Verify Analytics tile is displayed on the home page");
			dashBoardPage.verifyPageTitle(Constants.AnalyticsTile);

			reportLog("3.2: Logout from the application");
			loginPage.logoutApplication();

			reportLog("4.1: Login To Application as MA Portal ");
			dashBoardPage = loginPage.maLogin(AT_PRODProjectManager, AT_Password);

			reportLog("4.2: Verify user is logged in as MA Portal");
			dashBoardPage.verifyMedavantePortalPage();

			reportLog("5.1: Verify user is on Home Page");
			dashBoardPage.verifyPageTitle(Constants.HomeNavText);

			reportLog("5.2: Verify Analytics title is displayed on the home page");
			dashBoardPage.verifyPageTitle(Constants.AnalyticsTile);

			reportLog("5.3: Logout from the application");
			loginPage.logoutApplication();

			reportLog("6.1: Login to the application as Sponsor Portal");
			dashBoardPage = loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);

			reportLog("6.2: Verify User is logged in as Sponsor Portal");
			dashBoardPage.verifyMedavantePortalPage();

			reportLog("7.1: Verify user is on Home Page");
			dashBoardPage.verifyPageTitle(Constants.HomeNavText);

			reportLog("7.2: Verify Analytics tile displayed on Home page");
			dashBoardPage.verifyPageTitle(Constants.AnalyticsTile);

			reportLog("7.3: Logout from the application");
			loginPage.logoutApplication();
			
			reportLog("7.4: Verify user is logout");
			loginPage.verifyUserLogout();

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
