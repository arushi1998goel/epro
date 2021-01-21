
/**
 *@author 
* @date 20/05/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-4155 || Test Case Name: Add Multiple Site Raters-Action is not available for Site People and Sponsor user- V3
*  pre-qualification : 1. At least 1 Study and Site exists for the test.
                       2. Site user with VU Site People role without claims canManageStudySiteRaters 
                       and canManageSiteRaters,associated with Study Pr. #1 exists.
                       3. Sponsor user with VU Sponsor role without claims canManageStudySiteRaters and 
                       canManageSiteRaters, associated with Study Pr. #1 exists.
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

public class R4_FPTC_4155_AddMultipleRatersActionNotAvailableForSitePeopleAndSponsor_SIP extends BaseTest {

	private String study;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_4155_AddMultipleRatersActionNotAvailableForSitePeopleAndSponsor_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("Study1089");

	}
	
	@Test(description="Add Multiple Site Raters - Action is not available for Site People and Sponsor user- V3")
	public void R4_FPTC_4155_AddMultipleSiteRatersActionIsNotAvailableForSitePeopleAndSponsor() throws Exception
	{
		 
		reportLog("2.0: Log in to the Site Portal as the User Pr. #2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteUser, AT_Password);

		reportLog("2.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to University -> Study and Site Pr. #1 -> Tracking grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudy(study,Constants.ATAssignedRater_10);
		ratersDetailsPage.navigateToTrackingGrid();
		
		reportLog("3.1:  Raters Listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
		
        reportLog("3.2: Bulk Load control is not displayed");
        ratersDetailsPage.bulkUploadOptionNotdisplayed();
        
        reportLog("4.0: Navigate to each grid one by one(On-boarding, Qualification, Certification, Activation)");
		reportLog("Navigate To On- Boarding Grid & verify Listing Screen is Displayed");
        ratersDetailsPage.navigateToOnBoardingTab();
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        ratersDetailsPage.bulkUploadOptionNotdisplayed();
		
		reportLog("Navigate To Qualification Grid & verify Listing Screen is Displayed");
        ratersDetailsPage.navigateToQualificationGrid();
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        ratersDetailsPage.bulkUploadOptionNotdisplayed();

		reportLog("Navigate To Certification Grid & verify Listing Screen is Displayed");
		ratersDetailsPage.navigateToCertificationGrid();
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        ratersDetailsPage.bulkUploadOptionNotdisplayed();

		reportLog("Navigate To Activation Grid & verify Listing Screen is Displayed");
		ratersDetailsPage.navigateToActivationMenu();
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        ratersDetailsPage.bulkUploadOptionNotdisplayed();

        reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		reportLog("5.0: Log in to the Sponsor Portal as the user Pr. #3");
		dashBoardPage = loginPage.sponsorLogin(AT_SponsorUser, AT_SponsorUserPassword);

		reportLog("5.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("6.0: Navigate to University -> Study and Site Pr. #1 -> Tracking grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudy(study,Constants.ATAssignedRater_10);
		ratersDetailsPage.navigateToTrackingGrid();
		
		reportLog("6.1: Raters Listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

		reportLog("6.2: Bulk Load control is not displayed");
        ratersDetailsPage.bulkUploadOptionNotdisplayed();

		reportLog("7.0: Navigate to each grid one by one(On-boarding, Qualification, Certification, Activation)");
		reportLog("Navigate To On- Boarding Grid & verify Listing Screen is Displayed");
        ratersDetailsPage.navigateToOnBoardingTab();
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        ratersDetailsPage.bulkUploadOptionNotdisplayed();

		reportLog("Navigate To Qualification Grid & verify Listing Screen is Displayed");
        ratersDetailsPage.navigateToQualificationGrid();
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        ratersDetailsPage.bulkUploadOptionNotdisplayed();

		reportLog("Navigate To Certification Grid & verify Listing Screen is Displayed");
		ratersDetailsPage.navigateToCertificationGrid();
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        ratersDetailsPage.bulkUploadOptionNotdisplayed();

		reportLog("Navigate To Activation Grid & verify Listing Screen is Displayed");
		ratersDetailsPage.navigateToActivationMenu();
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        ratersDetailsPage.bulkUploadOptionNotdisplayed();

        reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
       }

}
