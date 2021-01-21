
/**
 *@author 
* @date 04/05/2020
* ===============================================================================================================================
*  Test Case Id: FP-TC-2804 || Test Case Name:Certification card - View completed Training - Save/Print Training Certificate- V5
 
* pre-qualification :     1. At least 2 Studies and Active Sites exist for the test:Study_1 and Study_2
                          2. At least 2 Users associated with both Studies but with different roles:
                          2.1 Site Manager_1 added to the Study_1 with the claim to can View History
                          2.2 Site Manager_2 added to the Study_2 without the claim to can View History
                          2.3 MA Admin_1 added to the Study_1 as MA Person (role: Study Coordinator) with the claim to View History Global
                          2.4 MA Admin_2 added to the Study_2 as MA Person (role: Study Coordinator) without the claim to View History Global
                          2.5. Sponsor_1 associated with Study_1 with the claim to can View History
                          2.6. Sponsor_2 associated with Study_2 without the claim to can View History
                          3. At least 1 Site Rater in 'Complete' status and associated with both Studies exists	
* ================================================================================================================================ 
*/
package net.medavante.regression.testscripts.r4suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_3636_SiteRaterViewHistoryClaimsRestriction_MAP extends BaseTest {

	private String study,studyNew;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_3636_SiteRaterViewHistoryClaimsRestriction_MAP(String browser) {
		super(browser);
	}
	
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("Study1089");
		studyNew = properties.getProperty("Study2180");

	}
	
	
	@Test(description="FP-TC-3636---Site Rater - View History: claims restriction- V15")
	public void R4_FPTC_3636_SiteRaterViewHistoryClaimsRestriction() throws Exception
	{
		reportLog("2.0: Log in to MA-Portal as MA Admin_1");
		dashBoardPage = loginPage.maLogin(SuperAdminUN, SuperAdminPW);

		reportLog("2.0.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Select Navigate -> University");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);

		reportLog("2.1.2: Study_1- All Sites");
		ratersDetailsPage.selectStudyWithAllSites(study);

		reportLog("2.1.3: Navigate To On-boarding grid");
		ratersDetailsPage.navigateToOnBoardingTab();
		
		reportLog("2.2: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingTabDisplayed();
		
		reportLog("3.0: Select row for Site Rater Pr.#3");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.ATAssignedRater_10);
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		
		reportLog("3.1: Site Rater Pr.#3 is selected");
		ratersDetailsPage.verifyCheckBoxOnSiteRaterRowSelected(Constants.ATAssignedRater_10);
		
		reportLog("3.2: Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();
		
		reportLog("4.0: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();
		
		reportLog("4.1: 'View History' option is displayed");
		ratersDetailsPage.verifyViewHistoryLinkDisplayed();
		
		reportLog("5.0: In the Study Selector select Study_2 - All Sites"); 
		ratersDetailsPage.reSelectstudy(study);
		ratersDetailsPage.selectStudyWithAllSites(studyNew);

		reportLog("5.1:  Study selection is applied");
		ratersDetailsPage.verifyStudySelectionIsApplied(studyNew);
		
		reportLog("5.2: Repeat Steps 3-4");
		reportLog("The same as Steps 3-4");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.ATAssignedRater_10);
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		ratersDetailsPage.verifyCheckBoxOnSiteRaterRowSelected(Constants.ATAssignedRater_10);
		ratersDetailsPage.verifyActionControlDropDownButton();
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.verifyViewHistoryLinkDisplayed();
		
		reportLog("6.0: Logout from Portal");
		loginPage.logoutApplication();

		reportLog("6.1: Log in to MA-Portal as MA Admin_2");
		dashBoardPage = loginPage.maLogin(AT_PRODAdminViewOnly, SuperAdminPW);

		reportLog("6.2: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("6.3: Select Navigate -> University -> Study_1- All Sites ->On-boarding grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudyWithAllSites(study);
		ratersDetailsPage.navigateToOnBoardingTab();

		reportLog("6.4: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingTabDisplayed();
		
		reportLog("7.0: Select row for Site Rater Pr.#3" );
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.ATAssignedRater_10);
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		
		reportLog("7.1: Site Rater Pr.#3 is selected");
		ratersDetailsPage.verifyCheckBoxOnSiteRaterRowSelected(Constants.ATAssignedRater_10);

		reportLog("7.2: Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();
		
		reportLog("8.0: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

		reportLog("8.1: 'View History' option is NOT displayed");
        ratersDetailsPage.verifyViewHistoryLinkNotDisplayed();
        
        reportLog("9.0: In the Study Selector select Study_2 - All Sites");
        ratersDetailsPage.reSelectstudy(study);
		ratersDetailsPage.selectStudyWithAllSites(studyNew);
		
        reportLog("9.1: Study selection is applied");
		ratersDetailsPage.verifyStudySelectionIsApplied(studyNew);
		
        reportLog("9.2: Repeat Steps 3-4");
        ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.ATAssignedRater_10);
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		ratersDetailsPage.verifyCheckBoxOnSiteRaterRowSelected(Constants.ATAssignedRater_10);
		ratersDetailsPage.verifyActionControlDropDownButton();
		ratersDetailsPage.selectActionControl();
		
		reportLog("9.3: 'View History' option is NOT displayed");
        ratersDetailsPage.verifyViewHistoryLinkNotDisplayed();
        
        reportLog("logOut from Portal ");
		loginPage.logoutApplication();

        reportLog("10.0: Log in to Site-Portal as Site Manager_1");
		dashBoardPage = loginPage.siteLogin(SuperAdminUN, SuperAdminPW);

        reportLog("10.1:  User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

        reportLog("10.2: Select Navigate -> University -> Study_1- All Sites ->On-boarding grid");
        ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudyWithAllSites(study);
		ratersDetailsPage.navigateToOnBoardingTab();
		
        reportLog("10.3: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingTabDisplayed();

        reportLog("10.4: Repeat Steps 3-4");
		reportLog("The same as Steps 3-4");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.ATAssignedRater_10);
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		ratersDetailsPage.verifyCheckBoxOnSiteRaterRowSelected(Constants.ATAssignedRater_10);
		ratersDetailsPage.verifyActionControlDropDownButton();
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.verifyViewHistoryLinkDisplayed();
		
		reportLog("11.0: Logout from Portal");
		loginPage.logoutApplication();
		
		reportLog("11.1: Log in to Site-Portal as Site Manager_2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODAdminViewOnly, SuperAdminPW);

		reportLog("11.2: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("11.3: Select Navigate -> University -> Study_2 ->On-boarding grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudyWithAllSites(studyNew);
		ratersDetailsPage.navigateToOnBoardingTab();
		
		reportLog("11.4: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingTabDisplayed();
		
		reportLog("11.5: Repeat Steps 3-4");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.ATAssignedRater_10);
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		ratersDetailsPage.verifyCheckBoxOnSiteRaterRowSelected(Constants.ATAssignedRater_10);
		ratersDetailsPage.verifyActionControlDropDownButton();
		ratersDetailsPage.selectActionControl();
		
		reportLog("11.6: View History option is NOT displayed");
        ratersDetailsPage.verifyViewHistoryLinkNotDisplayed();

        reportLog("logOut from Portal ");
		loginPage.logoutApplication();
		
		reportLog("12.0: Log in to Sponsor Portal as Sponsor_1");
		dashBoardPage = loginPage.sponsorLogin(SuperAdminUN, SuperAdminPW);

		reportLog("12.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("12.2: Select Navigate -> University -> Study_1- All Sites ->On-boarding grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudyWithAllSites(study);
		ratersDetailsPage.navigateToOnBoardingTab();
		
		reportLog("12.3: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingTabDisplayed();

		reportLog("12.4: Repeat Steps 3-4");
		reportLog("The same as Steps 3-4");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.ATAssignedRater_10);
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		ratersDetailsPage.verifyCheckBoxOnSiteRaterRowSelected(Constants.ATAssignedRater_10);
		ratersDetailsPage.verifyActionControlDropDownButton();
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.verifyViewHistoryLinkDisplayed();
		
		reportLog("13.0: Logout from Portal");
		loginPage.logoutApplication();

		reportLog("13.1: Log in to Sponsor Portal as Sponsor_2");
		dashBoardPage = loginPage.sponsorLogin(AT_PRODAdminViewOnly, SuperAdminPW);

		reportLog("13.2: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("13.3: Select Navigate -> University -> Study_2- All Sites ->On-boarding grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudyWithAllSites(studyNew);
		ratersDetailsPage.navigateToOnBoardingTab();
		
		reportLog("13.4: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingTabDisplayed();
		
		reportLog("13.5: Repeat Steps 3-4");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.ATAssignedRater_10);
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		ratersDetailsPage.verifyCheckBoxOnSiteRaterRowSelected(Constants.ATAssignedRater_10);
		ratersDetailsPage.verifyActionControlDropDownButton();
		ratersDetailsPage.selectActionControl();
		
		reportLog("13.6: View History option is NOT displayed");
        ratersDetailsPage.verifyViewHistoryLinkNotDisplayed();
        
        reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
	}
}
