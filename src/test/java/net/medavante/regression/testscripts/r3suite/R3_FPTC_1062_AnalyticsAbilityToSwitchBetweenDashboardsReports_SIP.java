/**
 *@author 
* @date 23/07/2020
* ================================================================================================================
*  Test Case Id: FP-TC-1062 || Test Case Name: Analytics - Ability to switch between Dashboards/Reports- V6
	 * pre-qualification :1. At least one Study and Site are configured for the test
                          2. At least one User who has access to Study in Pr#1 exists
                          3. At least two Reports are configured for Study in Pr#1
                          3.1 Report with Template 1
                          3.2 Report with Template 2
                          4. At least two Dashboards are configured for Study in Pr#1
                          4.1 Dashboard with Template 3
                          4.2 Dashboard with Template 4
                          4. The Role of User in Pr#2 is added to view Reports/Dashboards in Pr#3 and Pr#4

* ==================================================================================================================
*/package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1062_AnalyticsAbilityToSwitchBetweenDashboardsReports_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1062_AnalyticsAbilityToSwitchBetweenDashboardsReports_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study1089");
	}
	
	@Test(description="FPTC-1062---Analytics - Ability to switch between Dashboards/Reports- V6")
	public void R3_FPTC_1062_AnalyticsAbilityToSwitchBetweenDashboardsReports()
	{
		reportLog("2.0: Log in to the Portal as User from Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to Analytics");
		analyticsPage=dashBoardPage.navigateToAnalytics();
		
		reportLog("3.1: Select the Study from Pr#1");
		analyticsPage.selectStudy(studyName);
		
		reportLog("3.2: Two Reports Pr#3 are displayed for User Pr#2 in Reports section");
		analyticsPage.verifyRespectiveReportsDisplayed(2);
		
		reportLog("3.3: Two Dashboards Pr#4 are displayed for User Pr#2 in Dashboards section");
		analyticsPage.verifyRespectiveDashboardsDisplayed(2);
		
		reportLog("3.4: The first Report Pr#3.1 is preselected");
		analyticsPage.verifyReportIsSelected(Constants.Report_1);
		
		reportLog("3.5: Preselected Report is used the Template from Pr#3.1 and reflects the corresponding Data");
		analyticsPage.verifyRespectiveUsedTemplateIsDisplayed(Constants.Report_1);
		analyticsPage.verifyCorrespondingReportDataReflects();
		
		reportLog("4.0: Select the second Report Pr#3.2");
		analyticsPage.selectReport(Constants.Report_2);
		
		reportLog("4.1: Report is displayed on the page as added template Pr#3.2");
		analyticsPage.verifyRespectiveUsedTemplateIsDisplayed(Constants.Report_2);

		reportLog("4.3: Report reflects the corresponding Data");
		analyticsPage.verifyCorrespondingReportDataReflects();

		reportLog("5.0: Select the first Dashboard in the Dashboards section Pr#4.1");
		analyticsPage.selectDashboardFromList(Constants.Dashboard_1);
		
		reportLog("5.1: Dashboard is displayed on the page as added template Pr#4.1");
		analyticsPage.verifyRespectiveUsedTemplateIsDisplayed(Constants.Dashboard_1);
		
		reportLog("5.2: Dashboard reflects the corresponding Data");
		
		reportLog("6.0: Select the first Dashboard in the Dashboards section Pr#4.2	");
		analyticsPage.selectDashboardFromList(Constants.Dashboard_2);
		
		reportLog("6.1: Dashboard is displayed on the page as added template Pr#4.2");
		analyticsPage.verifyRespectiveUsedTemplateIsDisplayed(Constants.Dashboard_2);
		
		reportLog("6.2: Dashboard reflects the corresponding Data");
		
		reportLog("LogOut Application");
		loginPage.logoutApplication();
		
		reportLog("Verify LogOut ");
		loginPage.verifyUserLogout();

		
	}

}
