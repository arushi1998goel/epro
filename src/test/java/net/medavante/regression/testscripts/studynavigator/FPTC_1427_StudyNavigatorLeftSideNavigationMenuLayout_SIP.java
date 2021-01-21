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

public class FPTC_1427_StudyNavigatorLeftSideNavigationMenuLayout_SIP extends BaseTest {
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1427_StudyNavigatorLeftSideNavigationMenuLayout_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1427 Test Case Name: Study Navigator - Left side
	 * navigation menu - Layout
	 * =========================================================================
	 * 
	 */

	@Test(description = "FP-TC-1427_Study Navigator - Left side navigation menu - Layout", groups = { "R2" })
	public void FPTC_1427_VerifyStudyNavigatorLeftSideNavigationMenuLayout() {

		reportLog("Login to Site Portal with claims to access Study");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("Select " + studyName + " from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("Verify Study Navigator for " + studyName
				+ " and verify Subjects, Visits, Assessments, Questionnaires, Messages, Medications, Logged Events options is displayed");
		studyNavigatorDashBoardPage.verifyExtendedSidePanelIsDisplayedWithMenuIconAndTiles();

		reportLog("Verify Subjects are selected by default filter block");
		studyNavigatorDashBoardPage.verifySelectedSidePanelFilterName(Constants.SubjectsText);
		
		reportLog("Verify Filter and list blocks are displayed on the page");
		studyNavigatorDashBoardPage.verifyFilterAndListBlockIsDisplayed();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
