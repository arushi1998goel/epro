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

public class FPTC_1224_VerifyAutomaticOpeningOfStudySite_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1224_VerifyAutomaticOpeningOfStudySite_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyWithSites");
	}

	/**
	 * =================================================================================
	 * Test Case Id: FP-TC-1224 Test Case Name:Study Navigator - Automatic opening of Study/Site Selection modal window
	 * ==================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1224_Study Navigator - Automatic opening of Study/Site Selection modal window", groups = { "" })
	public void FPTC_1224_VerifyAutomaticOpeningOfStudySiteSelectionModalWindow() {

		reportLog("1.1Login to Site Portal with claims to access Study");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.1:Study/Site Selection modal window is displayed ");
		studyNavigatorDashBoardPage.verifyOptionToSelectStudyAndSiteIsDisplayed();
		
		reportLog("2.2:Click on Study dropdown and 	More than one Study is displayed");
		studyNavigatorDashBoardPage.verifyMoreThanOneStudyShowingInStudyList();
		
		reportLog("2.3:There is no option to close modal window");
		studyNavigatorDashBoardPage.verifyNoOptionToCloseModalWindow();
		
		reportLog("3.1:Select " + studyName + " from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("3.2:	Only 'Active' sites (Pr #3.1) are displayed in Site list");
		studyNavigatorDashBoardPage.verifyActivesitePresentInList(Constants.RaterName_21);
		studyNavigatorDashBoardPage.verifyActivesitePresentInList(Constants.ATAssignedRater_10);
		
		reportLog("3.3:Select Study and site and the action to confirm selection ");
		studyNavigatorDashBoardPage.clickOnSelectButtonAfterselectingStudySite();
		
		reportLog("3.4:Logout application");
		loginPage.logoutApplication();

		reportLog("3.5:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
