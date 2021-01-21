/*
 *@author Gurpreet
* @date 25/11/2019
* =========================================================================
* Test Case Id: FP-TC-1559 
* Test Case Name: Show that User with appropriate claim is able to create new Query
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

public class R3_FPTC_1559_ShowUserWithAppropriateClaimIsAbleToCreateNewQuery_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1559_ShowUserWithAppropriateClaimIsAbleToCreateNewQuery_SIP(String Browser) {
		super(Browser);
	}
	
	@BeforeMethod
	public void getData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop1 = Configuration.readTestData("RegressionTestData");
		studyName = prop1.getProperty("AutomationStudyNameNonCR");
		visitName1 = prop1.getProperty("CompletedAssesmentName_1559");
		queryName="TestQuery"+generateRandomString(5);
	}

	@Test(description = "Show that User with appropriate claim is able to create new Query", groups = { "R3" })
	public void R3_FPTC_1559_showUserWithAppropriateClaimIsAbleToCreateNewQuery() {
		reportLog("1.1	Log in to Portal as user Pr #1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog(" 1.2 User successfully logged in ");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog(
				" 2.1 Select To Navigate and tab and select study tab");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog(
				" 2.1	Select study name and site rater ");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog(" 2.2 List of available completed assessments is displayed ");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog(" 3.1	Select any completed assessment (Pr #2) ");
		studyNavigatorDashBoardPage.selectSubjectStatusFromFilter(Constants.Complete_Status);

		reportLog(" 3.2 Assessment details page of the selected assessment is displayed ");
		studyNavigatorDashBoardPage.clickOnSearchedSubject(visitName1);

		reportLog(" 4.1	Open Queries menu and click on Add Query control ");
		studyNavigatorDashBoardPage.clickOnQueriesIconAfterReachingToSubjectPage();
		studyNavigatorDashBoardPage.clickOnAddNewQuery();

		reportLog(" 4.2 New Query item is displayed. ");
		studyNavigatorDashBoardPage.verifyNewQueryItemIsOpened();

		reportLog(" 4.3 Create control is disabled. ");
		studyNavigatorDashBoardPage.verifyCreateButtonIsDisabled(Constants.isDisabled);

		reportLog(" 4.4 Cancel control is enabled. ");
		studyNavigatorDashBoardPage.verifyCancelButtonIsEnabled();

		reportLog(" 5.1	Enter text in the Textbox ");
		studyNavigatorDashBoardPage.EnterTextInNewQueryTextBox(queryName);

		reportLog(" 5.2 Create control becomes enabled ");
		studyNavigatorDashBoardPage.verifyCreateButtonIsDisabled(Constants.isEnabled);

		reportLog(" 6.1	Click on Create control ");
		studyNavigatorDashBoardPage.clickOnCreateButton();

		reportLog(" 6.2 New query is created ");
		studyNavigatorDashBoardPage.VerifyNewQueryIsCreated(queryName);

		reportLog("7.1 Delete the query");
		studyNavigatorDashBoardPage.deleteNewlyCreatedQuery(queryName);

		reportLog("7.2 Logout from the application");
		loginPage = loginPage.logoutApplication();

		reportLog("7.3 Verify User Logout");
		loginPage.verifyUserLogout();
	}

}
