
package net.medavante.smoke.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.analytics.AnalyticsDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2838_AnalyticsDetailsPage_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2838_AnalyticsDetailsPage_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("TestDataFile");
		studyName = properties.getProperty("StudyExisit");
	}

	
	/**
	 * Test Case Id: TC-12 Test Case Name: FP-TC-2838-To Verify Analytics
	 * Details
	 * 
	 */
	@Test(description = "FP-TC-2838-To Verify Analytics Details  ", groups = { "smoke" })
	public void FPTC_2838_VerifyAnalyticsDetailsPage() {

		reportLog("1. Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.1 Home page is displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2. Navigate to Analytics page");
		analyticsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AnalyticsDashBoardPage.class,Constants.AnalyticsText);

		reportLog("2.1 Analytical details page is displayed");
		analyticsPage.verifyAnalyticsDashBoardPageIsDisplayed();

		reportLog("3. Select existing Study from the dropdown");
		analyticsPage.selectStudy(studyName);

		reportLog("4. Verify dashboard added under the study");
		analyticsPage.verifyAnalyticsDashBoardPage();

		reportLog("5. Log Out Application");
		loginPage.logoutApplication();

		reportLog("5.1 Verify User logout");
		loginPage.verifyUserLogout();

	}

}