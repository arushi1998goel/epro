package net.medavante.regression.testscripts.r3suite;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1899_DashboardManagementScreenAddVersionOption_SIP extends BaseTest {

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
     public R3_FPTC_1899_DashboardManagementScreenAddVersionOption_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}
	
	@Test(description = "FP-TC-1899--Dashboard Management Screen Add Version Option", groups = { "R3" })
	public void R3_FPTC_1899_VerifyDashBoardManagementScreenAddVersionOption() {
		
		reportLog("1.1: Log in to the Portal by user who have access to System tab in Administration and has appropriate claims to view and manage Dashboards");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.2: Verify User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to Administration");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.2: Navigate to System");
		adminstrationSystemPage = adminstrationOrganizationPage.navigateToSystem();

		reportLog("2.3: Verify Dashboard Management screen is displayed");
		adminstrationSystemPage.verifyAdministrationSystemRoleManagmentPageIsOpen();
		
		reportLog("2.4: click On DashBoard Management link from left panel option");
		adminstrationSystemPage.clickOnDashBoardManagementOption();
				
		reportLog("2.5: Verify DashBoard Management is open");
		adminstrationSystemPage.verifyAdministrationSystemDashBoardManagmentPageIsOpen();
		
		reportLog("3.1: Expand Dashboard's row Pr#2");
		adminstrationSystemPage.clickOnExpandIconOnAddedDashboard(Constants.Automation_Dashboard);
		
		reportLog("3.2: View Version information is displayed");
		adminstrationSystemPage.verifyVersionInformationIsDispalyed(Constants.versionName,Constants.Activation,Constants.Deactivation,Constants.noVersionAdded);
		
		reportLog("3.3: Select action to add new Version");
		adminstrationSystemPage.selectAddVersionButton();
		
		reportLog("3.4: Section to add new Version information is displayed");
		adminstrationSystemPage.verifyAddNewVersionPopInformation();
		
		reportLog("3.5: Click on Cancel Control");
		adminstrationSystemPage.cancelAddDashboardPopUp();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		
		
	}


		
		
		
		
	}
		