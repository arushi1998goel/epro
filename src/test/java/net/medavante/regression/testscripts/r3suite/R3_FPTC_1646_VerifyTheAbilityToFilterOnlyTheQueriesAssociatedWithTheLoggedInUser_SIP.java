/**
* @author Siddharth
* @date 13/09/2019
* =========================================================================
* Test Case Id: FP-TC-1646 
* Test Case Name: Show the ability to filter only the Queries associated with the logged in user
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

public class R3_FPTC_1646_VerifyTheAbilityToFilterOnlyTheQueriesAssociatedWithTheLoggedInUser_SIP extends BaseTest{
	
	
	@Factory(dataProvider = "Browsers",dataProviderClass = DataProviders.class)
	public R3_FPTC_1646_VerifyTheAbilityToFilterOnlyTheQueriesAssociatedWithTheLoggedInUser_SIP(String browser) {
		super(browser);
	}
	
	
	@BeforeMethod
	public void getData() throws Exception{
		System.setProperty("className",getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName=properties.getProperty("AutomationStudyName");
	}
	
	
	@Test(description = "R3_FPTC_1646: Show the ability to filter only the Queries associated with the logged in user", groups = {"R3"})
	public void R3_FPTC_1646_VerifyTheAbilityToFilterOnlyTheQueriesAssociatedWithTheLoggedInUser() throws InterruptedException{
		
		reportLog("1.1: Log in to Portal as User PR");
		dashBoardPage =loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);

		reportLog("1.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("3.1: Click On Query side Panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToOpen();
		
		
		reportLog("3.2: Queries side panel is opened with list of Queries based on default filters");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		//TODO :: Closed checkbox is not selected by default Need to verify whether we have to select it manually .
		
		reportLog("4.1: Select Mine CheckBox");
		studyNavigatorDashBoardPage.selectFiltersOnQueriesTab(Constants.MineCheckBox);
		
		//TODO:: Need to look the verification method of filtered query
		reportLog("4.2: The Open,Responded ,Closed queries associated with the user are filtered");
		studyNavigatorDashBoardPage.verifyFiltersAreSelected(Constants.OpenQueryCheckBox,Constants.RespondedCheckBox);
		
		reportLog("5.1: Verify queries counter");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		reportLog("5.2: Counter value is changed accordingly to number of queries filtered");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		reportLog("5.3: click on queries side panel to close queries panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToClose();
		
		reportLog("6.1: Navigate to the subjects screen ");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		reportLog("6.2:  open queries panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToOpen();
		
		reportLog("6.3: Mine check box is selected queries are filtered accordingly");
		studyNavigatorDashBoardPage.verifyFiltersAreSelected(Constants.MineCheckBox);
		
		reportLog("6.4: Query counter accurately reflects the counts based on filtered queries");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		reportLog("6.5: click on queries side panel to close queries panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToClose();
		
		reportLog("7.1 Navigate to the visits screen ");
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		
		reportLog("7.2 open queries panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToOpen();
		
		reportLog("7.3: Mine check box is selected and queries are filtered accordingly");
		studyNavigatorDashBoardPage.verifyFiltersAreSelected(Constants.MineCheckBox);
		
		reportLog("7.3: Query counter accurately reflects the counts based on filtered queries");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		reportLog("7.4: click on queries side panel to close queries panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToClose();
		
		reportLog("8.1: Navigate to the assesments screen and open queries panel");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("8.2 Click on queries button");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToOpen();
		
		reportLog("8.2: Mine check box is selected and queries are filtered accordingly");
		studyNavigatorDashBoardPage.verifyFiltersAreSelected(Constants.MineCheckBox);
		
		reportLog("8.3: Query counter accurately reflects the counts based on filtered queries");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		reportLog("8.4: click on queries side panel to close queries panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToClose();
		
		reportLog("9.1: Logout the application");
		loginPage.logoutApplication();
		
		reportLog("9.2: Login to the application");
		loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		
		reportLog("9.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("9.3: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("9.4: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("9.5: Click On Query side Panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToOpen();
		
		reportLog("9.6: Mine checkBox is selected and queries are filtered accordingly");
		studyNavigatorDashBoardPage.verifyFiltersAreSelected(Constants.MineCheckBox);
		
		reportLog("9.5: click on queries side panel to close queries panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToClose();
		
		reportLog("9.6: Logout the application");
		loginPage.logoutApplication();
		
	}
	
	

}
