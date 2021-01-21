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

public class FPTC_1222_StudyNavigatorStudySelectionSaveAction_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1222_StudyNavigatorStudySelectionSaveAction_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1222 || Test Case Name:Study Navigator - Study
	 * selection save action
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1222_StudyNavigatorStudySelectionSaveAction", groups = { "" })
	public void FPTC_1222_StudyNavigatorStudySelectionSaveAction() {

		reportLog("1.1:Using IE browser log in to the Site Portal as User PR#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2:User successfully logged in");
		//dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Study Navigator is displayed ");
		//dashBoardPage.verifyStudyNavigatorTilePresent();

		reportLog("2.2:Navigate to Study Navigator");
		//studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("3.1:Select a Study Pr.#1 from the drop-down list and Verify select button if it is enabled");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("3.2:	Selected Study is displayed");
		studyNavigatorDashBoardPage.verifySelectedStudyName(studyName);

		reportLog("4.1:Refresh the page -> Wait until the page is loaded");
		studyNavigatorDashBoardPage.refreshThePageUntilThePageIsLoaded();

		reportLog("4.2:Study from Steps#3 is selected");
		studyNavigatorDashBoardPage.verifySelectedStudyName(studyName);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
