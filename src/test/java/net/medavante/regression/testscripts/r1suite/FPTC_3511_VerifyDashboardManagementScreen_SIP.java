package net.medavante.regression.testscripts.r1suite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;


public class FPTC_3511_VerifyDashboardManagementScreen_SIP extends BaseTest {
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3511_VerifyDashboardManagementScreen_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3511 Test Case Name: Dashboard Management screen: Add Dashboard - General flow
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-3511--Dashboard Management screen: Add Dashboard - General flow", groups = { "R1" })
	public void FPTC_3511_verifyDashboardManagementScreenAddDashboardGeneralFlow() {
		
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

		reportLog("3.1: Select control to add new Dashboard");
		adminstrationSystemPage.clickOnAddDashBoard();

		reportLog("3.2: Verify Pop-up window to add Dashboard information is displayed");
		adminstrationSystemPage.verifyAddDashBoardPopIsDisplayed();

		reportLog("4.1: Select Dashboard from Item type");
		adminstrationSystemPage.selectItemTypeRadioButton(Constants.ItemType_Dashboard);
		
		reportLog("4.2: Verify Dashboard is selected ");
		//adminstrationSystemPage.verifyItemTypeRadioButtonIsSelected(Constants.ItemType_Dashboard);
		
		reportLog("5.3:  Leave all fields blank and check-boxes are not marked and Verify Validation message is displayed for display,System name and access checkBox");
		adminstrationSystemPage.verifyAllRequiredFieldsValidationMessagesAreDisplayed();

		reportLog("5.4: click On save button to add the dashboard");
		adminstrationSystemPage.verifySaveButtonIsDisabled();
		
		reportLog("5.5: Verify Add DashBoard Pop up is open");
		adminstrationSystemPage.verifyAddDashBoardPopIsDisplayed();

		reportLog("5.5: Verify Add DashBoard Pop up is open");
		adminstrationSystemPage.verifyAddDashBoardPopIsDisplayed();

		reportLog("6.1: Select Item type radio button");
		adminstrationSystemPage.selectItemTypeRadioButton(Constants.ItemType_Dashboard);
		
		reportLog("6.2: Input "+ displayName+" dashboard name in Add Dashboard pop-up");
		adminstrationSystemPage.inputDashBoardDisplayName(displayName);

		reportLog("6.3: Input "+systemName+" system name in Add Dashboard pop-up");
		adminstrationSystemPage.inputDashBoardSystemName(systemName);

		reportLog("6.4: Select internal checkbox at the Add Dashboard pop-up");
		adminstrationSystemPage.selectInternalCheckBox();
		
		reportLog("6.5: Select control to cancel changes");
		adminstrationSystemPage.clickOnCancelBtn();
		
		reportLog("6.6: Verify Add Dashboard pop-up is closed");
		adminstrationSystemPage.verifyAdministrationSystemDashBoardManagmentPageIsOpen();

		reportLog("6.7: Verify "+displayName+" Dashboard isn't added");
		adminstrationSystemPage.verifyDashBoardIsNotAdded(displayName);

		reportLog("7.1: Select control to add new Dashboard");
		adminstrationSystemPage.clickOnAddDashBoard();
		
		reportLog("7.2: Verify Pop-up window to add Dashboard information is displayed");
		adminstrationSystemPage.verifyAddDashBoardPopIsDisplayed();
		
		reportLog("8.1: Select Item type");
		adminstrationSystemPage.selectItemTypeRadioButton(Constants.ItemType_Dashboard);

		reportLog("8.1: Input "+displayName+" dashboard name in Add Dashboard pop-up");
		adminstrationSystemPage.inputDashBoardDisplayName(displayName);

		reportLog("8.2: Input "+systemName+" system name in Add Dashboard pop-up");
		adminstrationSystemPage.inputDashBoardSystemName(systemName);

		reportLog("8.3: Select internal checkbox at the Add Dashboard pop-up");
		adminstrationSystemPage.selectInternalCheckBox();

		reportLog("8.4: Select control to close pop-up");
		adminstrationSystemPage.closeAddNewDashBoardPopUP();
		
		reportLog("8.5: Verify Add Dashboard pop-up is closed");
		adminstrationSystemPage.verifyAdministrationSystemDashBoardManagmentPageIsOpen();

		reportLog("8.6: Verify "+displayName+" Dashboard isn't added");
		adminstrationSystemPage.verifyDashBoardIsNotAdded(displayName);

		reportLog("9.1: Select control to add new Dashboard");
		adminstrationSystemPage.clickOnAddDashBoard();
		
		reportLog("9.2: Verify Pop-up window to add Dashboard information is displayed");
		adminstrationSystemPage.verifyAddDashBoardPopIsDisplayed();

		reportLog("10.1: Input "+displayName+" dashboard name in Add Dashboard pop-up");
		adminstrationSystemPage.inputDashBoardDisplayName(displayName);

		reportLog("10.2: Input "+systemName+" system name in Add Dashboard pop-up");
		adminstrationSystemPage.inputDashBoardSystemName(systemName);

		reportLog("10.3: Select internal checkbox at the Add Dashboard pop-up");
		adminstrationSystemPage.selectInternalCheckBox();
		
		reportLog("10.4: Select the item type check box");
		adminstrationSystemPage.selectItemTypeRadioButton(Constants.ItemType_Dashboard);

		reportLog("10.5: Select control to save Dashboard");
		adminstrationSystemPage.clickOnSaveBtn();

		reportLog("10.6: Verify "+displayName+" Dashboard is displayed as a new row on Dashboard Management screen");
		adminstrationSystemPage.verifyDashBoardIsAdded(displayName);

		reportLog("11.1: Logout application");
		loginPage.logoutApplication();

		reportLog("11.2 Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
