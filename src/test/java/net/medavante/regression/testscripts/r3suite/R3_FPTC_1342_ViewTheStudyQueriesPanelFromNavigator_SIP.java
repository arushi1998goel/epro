/**
 *@author Siddharth
* @date 29/11/2019
* =========================================================================
*  Test Case Id: FP-TC-1342 || Test Case Name: View the study queries panel from navigator
	 * pre-qualification :  At least one Study having more than one form-based query is available
	 * At least 1 User is a Study person with 'canManageQueries' claim	
* ========================================================================= 
*/
package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1342_ViewTheStudyQueriesPanelFromNavigator_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1342_ViewTheStudyQueriesPanelFromNavigator_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");

	/***
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1342 Test Case Name: Show the ability to view the Study Queries panel from Navigator
	 *
	 *
	 *
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 *
	 *
	 */
	}
	

	@Test(description = "FP-TC-1342 --View the study queries panel from navigator", groups = { "R3" })
	public void R3_FPTC_1342_verifyStudyQueriesPanelFromNavigator() throws Exception {

		reportLog("1.1:Login in to application as the user of Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:- Go to a Study Navigator -> Study Pr#1");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.verifyOptionToSelectStudyAndSiteIsDisplayed();
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("1.3:- Queries control is available");
		studyNavigatorDashBoardPage.isQueriesButtonEnabled();

		reportLog(
				"1.4:- The control showing the total number of queries for the Study, or Study and Site, given the current context and applied filters defined in the queries side panel");
		studyNavigatorDashBoardPage.verifyQueryCountOnButtonAssociatedWithFilters();
		

//		reportLog("2.1: Select Queries control");
//		studyNavigatorDashBoardPage.selectQueriesControl();
		
		reportLog("2.2:- Queries side panel is expanded");
		studyNavigatorDashBoardPage.verifyQuerySidePanel();
		
		reportLog("2.3: Side panel provides details on query items");
		studyNavigatorDashBoardPage.verifySidePanelProvidesDetailsOnQueryItems();

		reportLog("2.4: The queries that are included in the list are controlled by the context and filters applied");
		studyNavigatorDashBoardPage.verifySortByContextFilter();
		studyNavigatorDashBoardPage.verifySortByAgeFilter();

		reportLog(
				"2.5:- A label showing the total number of queries for the Study, or Study and Site, given the current context and applied filters");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();

		reportLog("2.6:-  A control to refresh the query list display");
		studyNavigatorDashBoardPage.verifyRefreshControlIsPresentOnQuerypanel();

		reportLog("2.7:- A control to return the query side panel to collapsed mode");
		studyNavigatorDashBoardPage.verifyCollapseControlOnQueryPanel();

		reportLog("3.1:- Select the refresh control");
		studyNavigatorDashBoardPage.verifyRefreshControlSelectionOnQueryPanel();

		reportLog("3.2:- The list of query items is refreshed");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();

		reportLog("4.1:- Select the collapse control");
		studyNavigatorDashBoardPage.verifyCollapseControlSelection();

		reportLog("4.2:- The query side panel is returned to collapsed mode	");
		studyNavigatorDashBoardPage.verifyQuerypanelCollapseMode();

		reportLog(" Logout application");
		loginPage.logoutApplication();

		reportLog(" Verify user is logout");
		loginPage.verifyUserLogout();
	}
	}


