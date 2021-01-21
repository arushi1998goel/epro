/**
 *@author 
* @date 11/03/2020
* =========================================================================
     *  Test Case Id: FP-TC-1896 || Test Case Name: Dashboard Management screen: user claim restrictions
     * pre-qualification : At least one User has access to System tab in Administration and has appropriate claims to view and manage Dashboards (canViewDashboards, canManageDashboards)
     * At least one User has access to System tab in Administration and has appropriate claims to view and manage Dashboards (canViewDashboards, canManageDashboardsSuperuser, canManageDashboards)
     * At least one User has access to System tab in Administration and has appropriate claim to view Dashboards (canViewDashboards)
     * At least one dashboard version that is not in use is configured for Dashboard Management 
     * At least one dashboard version that is in use is configured for Dashboard Management  

* ========================================================================= 
*/
package net.medavante.regression.testscripts.r3suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1896_DashboardManagementScreenUserClaimRestrictions_SIP extends BaseTest{

	@Factory(dataProvider = "Browsers" , dataProviderClass = DataProviders.class)
   public R3_FPTC_1896_DashboardManagementScreenUserClaimRestrictions_SIP(String browser) {
		super(browser);
	}
	
	
	@BeforeMethod
    public void getData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}
	
	
	@Test(description = "FP-TC-1896--Dashboard Management screen: user claim restrictions", groups = { "R3" })
	public void FPTC_1896_DashboardManagementScreenUserClaimRestrictions()
	{
//		reportLog("2.1: Log in to the Portal by user who have access to System tab in Administration and has appropriate claims to view and manage Dashboards");
//		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, SuperAdminPW);
//		
//		reportLog("2.2: Verify User successfully logged in");
//		dashBoardPage.verifyMedavantePortalPage();
//
//		reportLog("3.1: Navigate to Administration");
//		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
//
//		reportLog("3.2: Navigate to System");
//		adminstrationSystemPage = adminstrationOrganizationPage.navigateToSystem();
//		
//		reportLog("3.3: click On DashBoard Management link from left panel option");
//		adminstrationSystemPage.clickOnDashBoardManagementOption();
//				
//		reportLog("3.4: Verify DashBoard Management is open");
//		adminstrationSystemPage.verifyAdministrationSystemDashBoardManagmentPageIsOpen();
//		
//		reportLog("3.5: Select Dashboard from Pr# 4");
//		adminstrationSystemPage.clickOnExpandIconOnAddedDashboard(Constants.Automation_Dashboard_1);
//		
//		reportLog("3.6:  Add Dashboard action is available");
//		adminstrationSystemPage.verifyAddDashboradButtonisDisplayed();
//		
//	    reportLog("3.7: Edit Dashboard action is available");
//		adminstrationSystemPage.moveOnExistingDashboard(Constants.Automation_Dashboard_1);
//		
//		reportLog("3.8: verify Edit Dashboard action is available");
//		adminstrationSystemPage.verifyEditDashboardActionIsDisplayed();
//		
//		reportLog("3.9: verify Delete Dashboard action is available");
//		adminstrationSystemPage.verifyDeleteDashboardActionIsDisplayed();
//		
//		reportLog("3.10: Verify Add Version action is available");
//		adminstrationSystemPage.verifyAddVersionButtonIsDisplayed();
//		
//		reportLog("4.1:  Dashboard Version Pr#4 is displayed");
//		adminstrationSystemPage.VerifyVersionIsDisplayed(Constants.Version_1);
//		
//		reportLog("4.2: Verify Edit Dashboard Version action is available");
//		adminstrationSystemPage.verifyVersionEditButton();
//		
//		reportLog("4.3: Verify Delete Dashboard Version action is available");
//		adminstrationSystemPage.verifyVersionDeleteButton();
//
//		reportLog("5.1: Select Dashboard from Pr# 5");
//		adminstrationSystemPage.clickOnExpandIconOnAddedDashboard(Constants.Automation_Dashboard_2);
//		
//		reportLog("5.2:  Add Dashboard action is available");
//		adminstrationSystemPage.verifyAddDashboradButtonisDisplayed();
//		
//		reportLog("5.3: verify Dashboard actions are available");
//		adminstrationSystemPage.moveOnExistingDashboard(Constants.Automation_Dashboard_2);
//		
//     	reportLog("5.4: verify Edit Dashboard action is available");
// 		adminstrationSystemPage.verifyEditDashboardActionIsDisplayed();
//		
//		reportLog("5.5: verify Delete Dashboard action is available");
//		adminstrationSystemPage.verifyDeleteDashboardActionIsDisplayed();
//		
//		reportLog("5.6: Verify Add Version action is available");
//		adminstrationSystemPage.verifyAddVersionButtonIsDisplayed();
//		
//		reportLog("6.1:  Dashboard Version Pr#4 is displayed");
//		adminstrationSystemPage.VerifyVersionIsDisplayed(Constants.Version_2);
//		
//		reportLog("6.2: Verify Edit Dashboard Version action is available");
//		adminstrationSystemPage.verifyVersionEditButton();
//		
//		reportLog("6.3: Verify Delete Dashboard Version action is available");
//		adminstrationSystemPage.verifyVersionDeleteButton();
//		
//		reportLog("Logout application");
//		loginPage.logoutApplication();
//
//		reportLog("Verify user is logout");
//		loginPage.verifyUserLogout();
		
		reportLog("7.1: Log in to the Portal by user who have access to System tab in Administration and has appropriate claims to view and manage Dashboards");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN , SuperAdminPW);
		
		reportLog("7.2: Verify User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("8.1: Navigate to Administration");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("8.2: Navigate to System");
		adminstrationSystemPage = adminstrationOrganizationPage.navigateToSystem();
		
		reportLog("8.3: click On DashBoard Management link from left panel option");
		adminstrationSystemPage.clickOnDashBoardManagementOption();
		
		reportLog("8.4: Verify DashBoard Management is open");
		adminstrationSystemPage.verifyAdministrationSystemDashBoardManagmentPageIsOpen();
		
		reportLog("8.5: verify Dashboard Pr#5 is displayed");
		adminstrationSystemPage.clickOnExpandIconOnAddedDashboard(Constants.Automation_Dashboard_2);
		
		reportLog("8.6:  Add Dashboard action is available");
		adminstrationSystemPage.verifyAddDashboradButtonisDisplayed();
		
	     reportLog("8.7: verify Dashboard actions are available");
		adminstrationSystemPage.moveOnExistingDashboard(Constants.Automation_Dashboard_2);
		
		reportLog("8.8: verify Edit Dashboard action is available");
		adminstrationSystemPage.verifyEditDashboardActionIsDisplayed();
		
		reportLog("8.9: verify Delete Dashboard action is available");
		adminstrationSystemPage.verifyDeleteDashboardActionIsDisplayed();
		
		reportLog("8.10: Verify Add Version action is available");
		adminstrationSystemPage.verifyAddVersionButtonIsDisplayed();
		
		reportLog("9.1:  Dashboard Version Pr#4 is displayed");
		adminstrationSystemPage.VerifyVersionIsDisplayed(Constants.Version_2);
		
		reportLog("9.2: Verify Edit Dashboard Version action is available");
		adminstrationSystemPage.verifyVersionEditButton();
		
		reportLog("9.3: Verify Delete Dashboard Version action is available");
		adminstrationSystemPage.verifyVersionDeleteButton();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		reportLog("10.1: Log in to the Portal by user who have access to System tab in Administration and has appropriate claims to view and manage Dashboards");
		dashBoardPage = loginPage.loginInApplication(AT_PRODAdminViewOnly, SuperAdminPW);
		
		reportLog("10.2: Verify User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("11.1: Navigate to Administration");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("11.2: Navigate to System");
		adminstrationSystemPage = adminstrationOrganizationPage.navigateToSystem();
		
		reportLog("11.3: click On DashBoard Management link from left panel option");
		adminstrationSystemPage.clickOnDashBoardManagementOption();
				
		reportLog("11.4: Verify DashBoard Management is open");
		adminstrationSystemPage.verifyAdministrationSystemDashBoardManagmentPageIsOpen();
		
		reportLog("11.5: Verify View only action is available");
		
		reportLog("11.6: Select Dashboard from Pr# 4");
		adminstrationSystemPage.clickOnExpandIconOnAddedDashboard(Constants.Automation_Dashboard_1);
		
		reportLog("11.7: verify edit/delete action are not present");
		adminstrationSystemPage.verifyEditDashboardActionIsNotDisplayed();
		adminstrationSystemPage.verifyDeleteDashboardActionIsNotDisplayed();
		
		reportLog("11.8: verify add dashboard action isn't available");
        adminstrationSystemPage.verifyAddDashboradButtonisNotDisplayed();
		
		reportLog("11.9: verify add/Edit/Delete actions aren't available");
        adminstrationSystemPage.verifyAddVersionButtonNotDisplayed();
        adminstrationSystemPage.verifyVersionEditButtonIsNotDisplayed();
        adminstrationSystemPage.verifyVersionDeleteButtonIsNotDisplayed();
        
        reportLog("11.6.1: Select Dashboard from Pr# 5");
		adminstrationSystemPage.clickOnExpandIconOnAddedDashboard(Constants.Automation_Dashboard_2);
		
		reportLog("11.7.1: verify edit/delete action are not present");
		adminstrationSystemPage.verifyEditDashboardActionIsNotDisplayed();
		adminstrationSystemPage.verifyDeleteDashboardActionIsNotDisplayed();
		
		reportLog("11.8.1: verify add dashboard action isn't available");
        adminstrationSystemPage.verifyAddDashboradButtonisNotDisplayed();
		
		reportLog("11.9.1: verify add/Edit/Delete actions aren't available");
        adminstrationSystemPage.verifyAddVersionButtonNotDisplayed();
        adminstrationSystemPage.verifyVersionEditButtonIsNotDisplayed();
        adminstrationSystemPage.verifyVersionDeleteButtonIsNotDisplayed();
        
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
	}
}
		
		