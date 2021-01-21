/**
 *@author 
* @date 12/05/2020
* ===============================================================================================================================
*  Test Case Id: FP-TC-3635 || Test Case Name: Site Rater - Activation Grid: claims restriction- V10
*  pre-qualification :
1. At least two Studies and Sites (Facility) exist for the test:
1.1. Study 1
1.2. Study 2
2. At least two Users associated with both Studies Pr.#1.1 and Pr.#1.2 but with different roles:
2.1 First User added to the first Study Pr.#1.1 as Site Coordinator with the claim to track Study Raters
2.2 First User added to the second Study Pr.#1.2 as Investigator without the claim to track Study Raters
2.3 Second User added to the first Study Pr.#1.1 as MA Person (role: Project Manager) with the claim to track Study Raters Global
2.4 Second User added to the second Study Pr.#1.2 as MA Person (role: Project Coordinator) without the claim to track Study Raters Global	
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

public class R4_FPTC_3635_SiteRaterActivationGridClaimsRestriction_SIP extends BaseTest {

private String study,studyNew;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_3635_SiteRaterActivationGridClaimsRestriction_SIP(String browser) {
		super(browser);
	}
	
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("Study1089");
		studyNew = properties.getProperty("Study2180");

	}
	
	 @Test(description="FP-TC-3635--Site Rater - Activation Grid: claims restriction- V10")
	 public void R4_FPTC_3635_SiteRaterActivationGridClaimsRestriction()
	 {
		
		reportLog("2.0:  Log in to Portal as First User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODInvestigator, AT_Password);

		reportLog("2.0.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();
	
		reportLog("3.1: Navigate to University dashboard");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
					Constants.NavigateText, Constants.SubTabUniversity);

		reportLog("3.2: Select Study and Site Pr.#1.1");
		ratersDetailsPage.selectStudy(study, Constants.ChooseSite);
		
		reportLog("3.3: Study Navigator Listing screen is displayed");
		
		reportLog("4.0: At the left side panel select Clinicians/Raters item -> Activation menu item");
		ratersDetailsPage.navigateToActivationMenu();
		
		reportLog("4.1: Clinicians/Raters listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
		
		reportLog("4.2: Activation menu item is displayed");
        ratersDetailsPage.verifyActivationMenuIsDisplayed();
		
		reportLog("4.3: Activation Grid is displayed");
		ratersDetailsPage.verifyActivationGridIsDisplayed();
		ratersDetailsPage.navigateToTrackingGrid();

		reportLog("5.0: Select Study and Site Pr.#1.2");
		ratersDetailsPage.reSelectstudy(study);
		ratersDetailsPage.selectStudy(studyNew, Constants.ChooseSite_1);

		reportLog("5.1: Study Navigator Listing screen is displayed");
		reportLog("6.0: At the left side panel select Site Raters item");
		
		reportLog("6.1: Site Raters listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

		reportLog("6.2: Activation menu item isn't displayed");
		ratersDetailsPage.verifyActivationMenuIsNotDisplayed();
		
		reportLog("6.3: Activation Grid isn't displayed");
		ratersDetailsPage.verifyActivationGridIsNotDisplayed();
	
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		reportLog("7.0: Log in to Portal as Second User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);

		reportLog("7.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();


		reportLog("8.0: Navigate to Study Navigator");
		
		reportLog("8.1: Navigate to University dashboard");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
					Constants.NavigateText, Constants.SubTabUniversity);
		
		reportLog("8.2: Select Study and Site Pr.#1.1");
		ratersDetailsPage.selectStudy(studyNew, Constants.ChooseSite);

		reportLog("8.1: Study Navigator Listing screen is displayed");
		
		reportLog("9.0: At the left side panel select Site Raters item -> Activation menu item");
		ratersDetailsPage.navigateToActivationMenu();
		
        reportLog("9.1: Site Raters listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

        reportLog("9.2: Activation menu item is displayed");
        ratersDetailsPage.verifyActivationMenuIsDisplayed();

        reportLog("9.3: Activation Grid is displayed");
		ratersDetailsPage.verifyActivationGridIsDisplayed();

		reportLog("10.0: Select Study and Site Pr.#1.2");
		ratersDetailsPage.reSelectstudy(studyNew);
		ratersDetailsPage.selectStudy(study, Constants.ChooseSite);
		
		reportLog("10.1: Study Navigator Listing screen is displayed");
		
		reportLog("11.0: At the left side panel select Site Raters item -> Activation menu item");
		ratersDetailsPage.navigateToActivationMenu();

        reportLog("11.1: Site Raters listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

        reportLog("11.2: Activation menu item is displayed");
        ratersDetailsPage.verifyActivationMenuIsDisplayed();

        reportLog("11.3: Activation Grid is displayed");
		ratersDetailsPage.verifyActivationGridIsDisplayed();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		
	 }
}
