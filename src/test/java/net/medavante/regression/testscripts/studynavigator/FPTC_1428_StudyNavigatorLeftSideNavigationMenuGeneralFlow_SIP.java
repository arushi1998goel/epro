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

public class FPTC_1428_StudyNavigatorLeftSideNavigationMenuGeneralFlow_SIP extends BaseTest{

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1428_StudyNavigatorLeftSideNavigationMenuGeneralFlow_SIP(String browser) {
		super(browser);
	}
	
	private String studyName;

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1428 Test Case Name: Study Navigator - Left side navigation menu - General flow

	 * =========================================================================
	 * 
	 */
	
	@Test(description = "FP-TC-1428_Study Navigator - Left side navigation menu - General flow", groups = { "R2" })
	public void FPTC_1428_VerifyStudyNavigatorLeftSideNavigationMenuGeneralFlow() {

		reportLog("Login to Site Portal with claims to access Study");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("Select " + studyName + " from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("Verify Study Navigator for " + studyName + " is displayed");
		studyNavigatorDashBoardPage.verifySelectedStudyName(studyName);
		
		reportLog("Verify Study Navigator for " + studyName +" is displayed in extended view");
		studyNavigatorDashBoardPage.verifyExtendedSidePanelIsDisplayed();
		
		reportLog("Select to collapse the Left side panel");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		
		reportLog("Verify Left side panel is collapsed");
		studyNavigatorDashBoardPage.verifyExtendedSidePanelIsNotDisplayed();
		
		reportLog("Verify Menu icons are displayed without menu titles");
		studyNavigatorDashBoardPage.verifyExtendedSidePanelIsDisplayedWithOutMenuTitles();
		
		reportLog("Select to expand the Left side panel");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		
		reportLog("Verify Left side panel is expanded");
		studyNavigatorDashBoardPage.verifyExtendedSidePanelIsDisplayed();
		
		reportLog("Verify Menu icons are displayed with the menu titles");
		studyNavigatorDashBoardPage.verifyExtendedSidePanelIsDisplayedWithMenuTitles(); 
		
		reportLog("Select all menu items one by one and verify Each selected menu item is displayed its filter and list blocks");
		studyNavigatorDashBoardPage.selectSidePanelFilterOneByOneAndVerifyFilterItemAndListBlock();
		
		reportLog("Refresh the page and verify Study Navigator is displayed and Menu selection is saved with the last select menu item before refresh is displayed");
		studyNavigatorDashBoardPage.verifyLastSelectedStudyAndFilterIsSelectedAfterRefreshThePage(studyName);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		
	}
}
