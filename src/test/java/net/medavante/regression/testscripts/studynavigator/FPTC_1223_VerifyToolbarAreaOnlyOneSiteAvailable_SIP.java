package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;

public class FPTC_1223_VerifyToolbarAreaOnlyOneSiteAvailable_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1223_VerifyToolbarAreaOnlyOneSiteAvailable_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
	}

	/**
	 * =================================================================================
	 * Test Case Id: FP-TC-1223 Test Case Name:Toolbar Area: only one site available 
	 * ==================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1223_Toolbar Area Only One site Available", groups = { "" })
	public void FPTC_1223_VerifyToolbarAreaOnlyOneSiteAvailable() {

		reportLog("1.1:Login into Site Portal as user from #2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:Go to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("1.3:Study dropdown is displayed with the Study");
		studyNavigatorDashBoardPage.verifyStudyDisplayedUnderSelectStudyList(studyName);

		reportLog("1.4:choose Study from #1");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("1.5: Site user having access to selected by default");
		studyNavigatorDashBoardPage.verifySiteDropDownShowingByDefaultSiteSelected(AT_PRODSiteCoordinatorUserName);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
