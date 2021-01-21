package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.analytics.AnalyticsDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1066_VerifyAnalyticsStudyPersonDashboardsView_MAP extends BaseTest{

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1066_VerifyAnalyticsStudyPersonDashboardsView_MAP(String browser) {
		super(browser);
	}
	
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("AutomationStudyName");
		dashBoardName = prop.getProperty("DashBoardName2");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1066
	 * Test Case Name: Analytics - Study Person can view Dashboards on Analytics page
	 * ====================================================================================================================
	 * @throws Exception 
	 */
	@Test(description = "FPTC_1066_VerifyAnalyticsStudyPersonDashboardsView", groups = { "R1" })
	public void FPTC_1066_verifyAnalyticsStudyPersonCanViewDashboardsOnAnalyticsPage() throws Exception {
		
		reportLog("1.1 Log in to MA Portal by user " + AT_PRODProjectManager);
		dashBoardPage =loginPage.loginInApplication(AT_PRODProjectManager, AT_Password);
		
		reportLog("1.2 Verify "+ AT_PRODProjectManager + " has been logged in");
		dashBoardPage.verifyMedavantePortalPage(); 
	
		reportLog("2.0 Navigate to Analytics");
		analyticsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AnalyticsDashBoardPage.class,Constants.AnalyticsText);
		
		reportLog("2.1 Analytical details page is displayed");
		analyticsPage.verifyAnalyticsDashBoardPageIsDisplayed();
		
		reportLog("2.2 Select "+ studyName +" study");
		analyticsPage.selectStudy(studyName);
		
		reportLog("2.3 Select "+ dashBoardName +" dashboard");
		analyticsPage.selectDashBoard(dashBoardName);
		
		reportLog("2.4 Verify " + dashBoardName + " is selected and displayed"); 
		analyticsPage.verifydashBoardIsSelected(dashBoardName);
		
		reportLog(" Verify dashboard added under the study");
		analyticsPage.verifyAnalyticsDashBoardPage();
		
		reportLog("2.5 Logout the application");
	    loginPage.logoutApplication(); 
	    
	    reportLog("2.5 Verify  Logout from the application");
	    loginPage.verifyUserLogout();
	   
	    reportLog("3.1 Log in to Sponsor Portal as User "  + AT_PRODSponsorUserType3);
	    loginPage.sponsorLogin(AT_PRODSponsorUserType3,AT_Password); 
	    
	    reportLog("3.2 Verify "+ AT_PRODSponsorUserType3+ " has been logged in to the system");
		dashBoardPage.verifyMedavantePortalPage(); 
	   
	    reportLog("4.0 Navigate to Analytics tab");
		analyticsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AnalyticsDashBoardPage.class,Constants.AnalyticsText);
		
		reportLog("4.1 Analytical details page is displayed");
		analyticsPage.verifyAnalyticsDashBoardPageIsDisplayed();
		
		reportLog("4.2 Select "+ studyName +" study");
		analyticsPage.selectStudy(studyName);
		
		reportLog("4.3 Select "+ dashBoardName +" dashboard");
		analyticsPage.selectDashBoard(dashBoardName);

		reportLog("4.4 Verify " + dashBoardName + " is selected and displayed"); 
		analyticsPage.verifydashBoardIsSelected(dashBoardName);
		
		reportLog("4.5: Verify dashboard added under the study");
		analyticsPage.verifyAnalyticsDashBoardPage();
		
		reportLog("4.6: Logout from the application");
	    loginPage.logoutApplication(); 
	    
	    reportLog("4.7 Verify "+ AT_PRODSponsorUserType3 + " Logout from the application");
	    loginPage.verifyUserLogout();
		
		
	}

}
