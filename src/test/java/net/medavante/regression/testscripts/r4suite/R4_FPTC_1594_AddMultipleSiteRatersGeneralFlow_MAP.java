/**
 *@author 
* @date 27/01/2020
* =========================================================================
*  Test Case Id: FP-TC-1594 || Test Case Name: Add Multiple Site Raters - General Flow
	 * pre-qualification :  At least one Study and Site exists for the test.
	 * User without claim to view and manage Site Raters exists.
	 * User with the claim to view and manage Site Raters exists.
	 * At least one .xlsx spreadsheet with 2 site raters data without validation errors exists  Make sure the raters have unique data First/Last Name and mail	
* ========================================================================= 
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

public class R4_FPTC_1594_AddMultipleSiteRatersGeneralFlow_MAP extends BaseTest
{

	private String study;
	
	@Factory(dataProvider= "Browsers",dataProviderClass= DataProviders.class)
	public R4_FPTC_1594_AddMultipleSiteRatersGeneralFlow_MAP(String browser) {
		super(browser);
	}
		
	
	@BeforeMethod
	public void getTestData() throws Exception{
		
		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study=properties.getProperty("AutomationStudyName");
	}
	
	@Test(description="FP-TC-1594 ---Add Multiple Site Raters General Flow ")
	public void R3_FPTC_1594_AddMultipleSiteRatersGeneralFlow() throws InterruptedException, Exception
	{
		reportLog("2.0:  Log in to the Portal as a User Pr.#2" );
		dashBoardPage=loginPage.loginInApplication(AT_PRODAdminViewOnly, AT_Password);
			
		reportLog("2.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

	    reportLog("2.2: Navigate to University dashboard");
	    ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);

		reportLog("2.2.1: Select study");
		ratersDetailsPage.selectStudy(study, Constants.ATAssignedRater_10);
		
		reportLog("2.3: Clinicians/Raters Listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
	
		reportLog("2.4: Bulk Load option is not displayed");
		ratersDetailsPage.bulkUploadOptionNotdisplayed();
	
		reportLog(" LogOut Application");
		loginPage.logoutApplication();
	
		reportLog("3.0: Log in to the Portal as a User Pr.#3");
		dashBoardPage=loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("3.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.2: Navigate to University grid -> Study and Site Pr.#1");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
	
		reportLog("3.2.1: Select study" );
		ratersDetailsPage.selectStudy(study, Constants.ATAssignedRater_10);	
        
        reportLog("3.3:  Clinicians/Raters Listing screen is displayed");
        ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
        
         reportLog("3.4: Bulk Load option is displayed" );
        ratersDetailsPage.bulkUploadOptionDisplayed();
        
        reportLog("4.0: Select action to add Multiple Site Raters (Bulk Load -> Upload File option)");
        ratersDetailsPage.selectActionToAddMultipleSiteRaters();
        
        reportLog("4.1: modal window to Add Multiple Site Raters is displayed");
        ratersDetailsPage.verifyAddMultipleSiteRatersWindowDisplayed();
        
        reportLog("4.2: Control Upload File is displayed and active");
        ratersDetailsPage.verifyControlUploadFileIsDisplayedandActive();
        
        reportLog("4.3:  Next control is disabled");
        ratersDetailsPage.verifyNextcontrolDisabled();
        
        reportLog("4.4: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
	
        reportLog("5.0: Do not upload any files and try proceed to next step");
        ratersDetailsPage.clickOnNextButton();
        
        reportLog("5.1: Modal window isn't closed");
        ratersDetailsPage.verifyAddMultipleSiteRatersWindowDisplayed();
        
        reportLog("5.2:  Next control is disabled");
        ratersDetailsPage.verifyNextcontrolDisabled();
        
        reportLog("5.3: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("5.4: New Site Raters aren't created");
        ratersDetailsPage.verifyModalwindowWithsauccessMessageIsNotDisplayed();
        
        reportLog("6.0: Upload file from Pr.#4");
        ratersDetailsPage.uploadFile("FP-TC-1594.xlsx");
        
        reportLog("6.1: Next control is enabled");
        ratersDetailsPage.verifyNextControlEnabled();
        
        reportLog("6.2: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("6.3: File Pr. #4 name is displayed" );
        ratersDetailsPage.VerifyUploadFileIsDisplayed();
        
        reportLog("6.4:  Option to delete file is displayed");
        ratersDetailsPage.verifyRemoveFileoptionDisplayed();
        
        reportLog("7.0: Delete uploaded file");
        ratersDetailsPage.clickOnRemoveFileOptionBtn();
        
        reportLog("7.1: Next control is disabled");
        ratersDetailsPage.verifyNextcontrolDisabled();
        
        reportLog("7.2: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("8.0: Select action to cancel changes");
        ratersDetailsPage.clickOnCancelButton();
        
        reportLog("8.1: Modal window is closed");
        ratersDetailsPage.VerifyaddMultipleSiteraterswindowIsNotDisplayed();
        
        reportLog("8.2: New Site Raters aren't created");
        ratersDetailsPage.verifyModalwindowWithsauccessMessageIsNotDisplayed();
        
        reportLog("9.0: Select action to add Multiple Raters (Bulk Load -> Upload File option)");
        ratersDetailsPage.selectActionToAddMultipleSiteRaters();
        
        reportLog("9.1: A modal window for adding Multiple Site Raters is displayed");
        ratersDetailsPage.verifyAddMultipleSiteRatersWindowDisplayed();
        
        reportLog("9.2: Control for file uploading is displayed and active");
        ratersDetailsPage.verifyControlUploadFileIsDisplayedandActive();
        
        reportLog("9.3: Next control is disabled");
        ratersDetailsPage.verifyNextcontrolDisabled();
        
        reportLog("9.4: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("10.0: Upload file from Pr.#4");
        ratersDetailsPage.uploadFile("FP-TC-1594.xlsx");
        
        reportLog("10.1: Next control is enabled");
        ratersDetailsPage.verifyNextControlEnabled();
        
        reportLog("10.2: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("10.3: File Pr. #4 name is displayed" );
        ratersDetailsPage.VerifyUploadFileIsDisplayed();
        
        reportLog("10.4:  Option to delete file is displayed");
        ratersDetailsPage.verifyRemoveFileoptionDisplayed();
        
        reportLog("11.0: Select action to close changes");
        ratersDetailsPage.clickOnCancelButton();
        
        reportLog("11.1: Modal window with the confirmation message 'The rater(s) will not be added! Are you sure?' is displayed");
        ratersDetailsPage.verifyConfirmationMessageIsdisplayed();
        
        reportLog("11.2: Select Yes, close dialog option");
        ratersDetailsPage.clickOnActionCloseDialogOption();
        
        reportLog("11.3: Modal window is closed");
        ratersDetailsPage.VerifyaddMultipleSiteraterswindowIsNotDisplayed();
        
        reportLog("11.4: New Site Raters aren't created");
        ratersDetailsPage.verifyModalwindowWithsauccessMessageIsNotDisplayed();
        
        reportLog("12.0: Select action to add Multiple Raters (Bulk Load -> Upload File option)");
        ratersDetailsPage.selectActionToAddMultipleSiteRaters();
        
        reportLog("12.1: A modal window for adding Multiple Site Raters is displayed");
        ratersDetailsPage.verifyAddMultipleSiteRatersWindowDisplayed();
        
        reportLog("12.2: Upload file from Pr.#3");
        ratersDetailsPage.uploadFile("FP-TC-1594.xlsx");
        
        reportLog("12.3: File Pr. #3 name is displayed");
        ratersDetailsPage.VerifyUploadFileIsDisplayed();
        
        reportLog("12.4: Option to delete file is displayed");
        ratersDetailsPage.verifyRemoveFileoptionDisplayed();
        
        reportLog("12.5:  Next control is enabled");
        ratersDetailsPage.verifyNextControlEnabled();
        
        reportLog("12.6: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("13.0: Select an action to proceed to the Next step");
        ratersDetailsPage.clickOnNextButton();
        
        reportLog("13.1:- Preview data from the file is displayed in grid with following columns:"); 
        reportLog("-- Primary Email "); 
        reportLog("-- Additional Emails"); 
        reportLog("-- First Name"); 
        reportLog("-- Last Name"); 
        reportLog("-- Phone"); 
        reportLog("-- Facilities" ); 
        reportLog("-- Studies");
        ratersDetailsPage.VerifyUploadedDataDisplayedInGrid();
        		
        
        reportLog("13.2:'Review each rater information one by one before adding' option is displayed" );
        ratersDetailsPage.verifyReviewEachRaterInformationOptionDisplayed();
        
        reportLog("13.3: Next control is enabled");
        ratersDetailsPage.verifyNextControlEnabled();
        
        reportLog("13.4: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("14.0: Activate 'Review each rater information one by one before adding' option -> "
        		+ "Select an action to proceed to the Next step");
        ratersDetailsPage.activateReviewEachraterInformationoption();
        ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_MultipleSiteRater_PopUp);
        ratersDetailsPage.addSiteforRaterinBulkUpload(Constants.ATAssignedRater_10);
        ratersDetailsPage.clickOnNextButton();
        ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_MultipleSiteRater_PopUp);

        reportLog("14.1: Site Rater information (without validation errors) is displayed in corresponding modal window");
        ratersDetailsPage.verifySiteRaterInformationWithoutValidationErrorsDisplayed(
        		Constants.FilledrequiredColor,Constants.FilledrequiredColor_2,Constants.FilledrequiredColor_2,
        		Constants.FilledrequiredColor_3);
        
        reportLog("14.2: Next control is enabled" );
        ratersDetailsPage.verifyNextControlEnabled();
        
        reportLog("14.3: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("14.4: Back control is enabled");
        ratersDetailsPage.verifyBackControlIsEnabled();        
        
        reportLog("15.0: Select Back action");
        ratersDetailsPage.clickOnBackButton();
        ratersDetailsPage.clickOnBackButton();

        reportLog("15.1: User redirected to the previous step");
        ratersDetailsPage.verifyAddMultipleSiteRatersWindowDisplayed();
        
        reportLog("15.2:  Preview data from the file is displayed");
        ratersDetailsPage.VerifyUploadedDataDisplayedInGrid();
        
        reportLog("15.3: 'Review each rater information one by one before adding' option is displayed");
        ratersDetailsPage.verifyReviewEachRaterInformationOptionDisplayed();
        
        reportLog("15.4:  Next control is enabled");
        ratersDetailsPage.verifyNextControlEnabled();
        
        reportLog("15.5: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("16.0: Select an action to proceed to the Next step");
        ratersDetailsPage.activateReviewEachraterInformationoption();
        ratersDetailsPage.addSiteforRaterinBulkUpload(Constants.ATAssignedRater_10);
        
        reportLog("16.1: Site Rater information is displayed in corresponding modal window");
        ratersDetailsPage.verifyAddMultipleSiteRatersWindowDisplayed();
        
        reportLog("16.2: Delete all existing Facilities for Rater");
        ratersDetailsPage.deleteExistingFacilitiesForRater();
          
        reportLog("16.3:  Facilities field is highlighted as required and empty");
        reportLog("Validation message 'At least one Facility should be selected' is displayed on selecting the information icon");
        ratersDetailsPage.verifyFacilityFieldHighlightedWithErrorMessage();
        
        reportLog("16.4: Studies field is highlighted as incorrect");
        reportLog("Validation message 'Provided Study is incorrect' is displayed is displayed on selecting the information icon");
        ratersDetailsPage.verifyStudyfieldGighlightedWithWarning(Constants.StudyFieldHighlightedColor);
        
        reportLog("16.5: Next control is disabled");
        ratersDetailsPage.verifyNextcontrolDisabled();
        
        reportLog("16.6: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("16.7: Back control is enabled");
        ratersDetailsPage.verifyBackControlIsEnabled();
        
        reportLog("17.0: Input corresponding Facility Pr.#1 into Facilities field");
        ratersDetailsPage.clickOnBackButton();
        ratersDetailsPage.activateReviewEachraterInformationoption();
        ratersDetailsPage.addSiteforRaterinBulkUpload(Constants.ATAssignedRater_10);
        
        reportLog("17.1: Studies and Facilities fields aren't highlighted");
        reportLog("Validation messages are not displayed");
        ratersDetailsPage.verifyFacilityFieldNotHighlighted(Constants.FilledrequiredColor_1);
        ratersDetailsPage.verifyFieldsNotHighlightedProceedToSaveRaters();
        ratersDetailsPage.addSiteforRaterinBulkUpload(Constants.ATAssignedRater_10);
        
        reportLog("17.2: Next control is enabled");
        ratersDetailsPage.verifyNextControlEnabled();
        
        reportLog("17.3: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("17.4: Back control is enabled");
        ratersDetailsPage.verifyBackControlIsEnabled();  
        
        reportLog("17.0: Select an action to proceed to the Next step for all Site raters");
        ratersDetailsPage.clickOnNextButton();
        
        reportLog("18.1: Modal window with message 'Success! 2 Site Raters are ready to be added.is displayed (according to Pr. #3)");
        ratersDetailsPage.verifyModalwindowWithsauccessMessageIsDisplayed();
        
        reportLog("18.2:  Finish control is enabled");
        ratersDetailsPage.verifyFinishcontrolIsEnabled();
        
        reportLog("18.3: Cancel control is enabled");
        ratersDetailsPage.verifyCancelbuttonEnabled();
        
        reportLog("19.0: Select an action to finish");
        ratersDetailsPage.clickOnFinishButton();
        
        reportLog("19.1: The modal window closed");
        ratersDetailsPage.verifyModalwindowWithsauccessMessageIsNotDisplayed();
        
        reportLog("Restoring PreRequisite");
        ratersDetailsPage.searchSiteRater("Auto Raters");
        ratersDetailsPage.deleteraterInformation("Auto Raters",SuperAdminUN, SuperAdminPW);
        ratersDetailsPage.verifyDeleteRaterWindowIsNotdisplayed();
        ratersDetailsPage.reSelectstudy(study);
        ratersDetailsPage.selectStudy("16Org - 2180", Constants.ATAssignedRater_10);
        ratersDetailsPage.searchSiteRater("AutoTest Raters");
        ratersDetailsPage.deleteraterInformation("AutoTest Raters",SuperAdminUN, SuperAdminPW);
        ratersDetailsPage.verifyDeleteRaterWindowIsNotdisplayed();
        
        reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
        
	}
}
