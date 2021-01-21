/**
 *@author 
* @date 01/07/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-4449   
*  Test Case Name: Add Site Rater - Facilities/Studies validation - Active sites- V2
*  pre-qualification :1. At least 3 Studies and the same Facility exist for the test:
                      1.1 Study_1 and Study_2 with On-boarding configured by MA-PP and Sites.
                      1.2 Study_3 with On-boarding configured by MA-PP only.
                      2. Site Manager with the claim to manage Site Raters, assigned to Study_1 Pr. #1 exists.
                      3. Site Manager with the claim to manage Site Raters, assigned to all Studies Pr. #1 exists.
                      4. MA Admin User with the claim to manage Site Raters exists.
* ======================================================================================================================================= 
*/package net.medavante.regression.testscripts.r4suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_4449_AddSiteRaterFacilitiesStudiesValidationActiveSites_SIP extends BaseTest {

	private String study,study2,study3;
	
    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_4449_AddSiteRaterFacilitiesStudiesValidationActiveSites_SIP(String browser) {
		super(browser);
	}
    
    @BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study=properties.getProperty("Study1089");
		study2 = properties.getProperty("Study2180");
		study3 = properties.getProperty("StudyConfiguredWithBirth");

		}
    
    @Test(description="FP-TC-4449--Add Site Rater - Facilities/Studies validation - Active sites- V2")
    public void R4_FPTC_4449_AddSiteRaterFacilitiesStudiesValidationActiveSites() throws Exception
    {
    	
    	reportLog("2.0: Log in to the Site Portal as User Pr. #2");
		dashBoardPage = loginPage.loginInApplication(SiteManager_2, VU_AT_Password);

    	reportLog("2.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();
    	
    	reportLog("2.2: Navigate to University grid -> Study_1 Pr. #1 and all Sites");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
					Constants.NavigateText, Constants.SubTabUniversity);

		reportLog("2.3: Site Staff Listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

		reportLog("3.0: Select an option to Add single Rater");
		ratersDetailsPage.selectAddSiteControl();

		reportLog("3.1: Add Site Staff dialog is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsDisplayed();

		reportLog("3.2: Fill in Primary Email, First and Last Name");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(Constants.primaryEmail,Constants.firstName,
				Constants.lastName);
		
		reportLog("3.3: Fill in Primary Email, First and Last Name are populated");
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_Email,Constants.primaryEmail);
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_FirstName,Constants.firstName);
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_LastName,Constants.lastName);
  
		reportLog("3.4: Select Facility Pr. #1 (if enabled)");
		ratersDetailsPage.selectFacility(Constants.FacilityName,Constants.Add_SiteRater_PopUp);

		reportLog("3.5: Facility Pr. #1 is selected");
		ratersDetailsPage.verifyAssignedFacilityIsDisplayedInFacilityField(Constants.FacilityName);
		
		reportLog("4.0: Select Study drop-down");
		ratersDetailsPage.selectStudyDropDown();
		
		reportLog("4.1: Study_1 is displayed in the list of available");
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(study, Constants.Add_SiteRater_PopUp);

		reportLog("4.2: Study_2 and Study_3 are not displayed in the list of available");
		ratersDetailsPage.verifyStudyIsNotDisplayed(study2);
		ratersDetailsPage.verifyStudyIsNotDisplayed(study3);

		reportLog("5.0: Log out from the Portal");
		ratersDetailsPage.clickOnCancelButton();
        ratersDetailsPage.clickOnActionCloseDialogOption();
        
        reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		reportLog("5.1: Log in to the Site Portal as User Pr. #3");
		dashBoardPage = loginPage.loginInApplication(SiteManager_1, VU_AT_Password);

		reportLog("5.2: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("5.3: Navigate to University grid -> Study_1 Pr. #1 and all Sites");
		//dashBoardPage.selectStudyWithAllSites(study2);
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
        ratersDetailsPage.selectStudyWithAllSites(study2);
        
		reportLog("5.4: Site Staff Listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

		reportLog("6.0: Select an option to Add single Rater");
		ratersDetailsPage.selectAddSiteControl();

		reportLog("6.1: Add Site Staff dialog is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsDisplayed();

		reportLog("6.2: Fill in Primary Email, First and Last Name");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(Constants.primaryEmail,Constants.firstName,
				Constants.lastName);
		
		reportLog("6.3: Fill in Primary Email, First and Last Name are populated");
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_Email,Constants.primaryEmail);
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_FirstName,Constants.firstName);
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_LastName,Constants.lastName);
  
		reportLog("6.4: Select Facility Pr. #1 (if enabled)");
		ratersDetailsPage.selectFacility(Constants.FacilityName,Constants.Add_SiteRater_PopUp);

		reportLog("6.5: Facility Pr. #1 is selected");
		ratersDetailsPage.verifyAssignedFacilityIsDisplayedInFacilityField(Constants.FacilityName);

		reportLog("7.0: Select Study drop-down");
		ratersDetailsPage.selectStudyDropDown();
	
		reportLog("7.1: Study_1 and Study_2 are displayed in the list of available");
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(study, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(study2, Constants.Add_SiteRater_PopUp);
		
		reportLog("7.2: Study_3 is not displayed in the list of available");
		ratersDetailsPage.verifyStudyIsNotDisplayed(study3);

		reportLog("8.0: Log out from the Portal");
		ratersDetailsPage.clickOnCancelButton();
        ratersDetailsPage.clickOnActionCloseDialogOption();
        
        reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();

		reportLog("8.1: Log in to the MA Portal as User Pr. #4");
		dashBoardPage = loginPage.maLogin(AT_ProdAdminOps, AT_Password);

		reportLog("8.2: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("8.3: Navigate to University grid -> Study_1 Pr. #1 and all Sites");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
        ratersDetailsPage.selectStudyWithAllSites(study);

		reportLog("8.4: Raters Listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

		reportLog("9.0: Select an option to Add single Rater");
		ratersDetailsPage.selectAddSiteControl();

		reportLog("9.1: Add Site Staff dialog is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsDisplayed();

		reportLog("9.2: Fill in Primary Email, First and Last Name");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(Constants.primaryEmail,Constants.firstName,
				Constants.lastName);
		
		reportLog("9.3: Fill in Primary Email, First and Last Name are populated");
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_Email,Constants.primaryEmail);
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_FirstName,Constants.firstName);
        ratersDetailsPage.verifyEnteredValueInRespectiveField(Constants.Field_LastName,Constants.lastName);
  
		reportLog("9.4: Select Facility Pr. #1 (if enabled)");
		ratersDetailsPage.selectFacility(Constants.FacilityName,Constants.Add_SiteRater_PopUp);

		reportLog("9.5: Facility Pr. #1 is selected");
		ratersDetailsPage.verifyAssignedFacilityIsDisplayedInFacilityField(Constants.FacilityName);

		reportLog("10.0: Select Study drop-down");
		ratersDetailsPage.selectStudyDropDown();

		reportLog("10.1: All Studies Pr. #1 are displayed in the list of available");
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(study, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(study2, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(study3, Constants.Add_SiteRater_PopUp);

		reportLog("Log out from the Portal");
		ratersDetailsPage.clickOnCancelButton();
        ratersDetailsPage.clickOnActionCloseDialogOption();
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();

    }

}
 