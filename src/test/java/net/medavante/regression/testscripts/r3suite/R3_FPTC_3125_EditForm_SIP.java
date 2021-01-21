package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3125_EditForm_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3125_EditForm_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
	}
	
	
	
	/***
	 * ====================================================================================================================
	 * Test Case Id: FPTC_3125 Test Case Name: Show that User is able to Edit Form

                                     Show that User is able to Cancel form editing

                        Show that Form Abbreviation with Form Factor is uniqueness 

         Show that Standard Form Administration Time is required (can not be null)

         Show Save constraints
	 *
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 *
	 */
	
	@Test(description = "FP-TC-3125 --EditForm", groups = { "R3" })
    public void FPTC_3125_verifyEditForm() {
		
		reportLog("1.1: Login in to application");
	    dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
	    
	    reportLog("1.2: User is successfully Logged in ");
	    dashBoardPage.verifyMedavantePortalPage();

	    reportLog("1.3: Navigate to study set up");
	    adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
	    
	    reportLog("2.1: Navigate to forms library tab");
	    adminstrationFormLibraryPage= adminstrationOrganizationPage.navigateFormsLibrary();
	    
	    reportLog("2.2: Select the form as PR#1.1");
	    adminstrationFormLibraryPage.clickOnSearchForm(Constants.formMobileAbbreviation1);
	    adminstrationFormLibraryPage.verifySelectedFormDisplayed(Constants.formMobileAbbreviation1);
	    
	    reportLog("2.3: Form Details are shown in view mode");
	    adminstrationFormLibraryPage.verifyAttributesOfFormsLibraryAreInViewMode();
	    
	    reportLog("2.4: Save is disabled");
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();
	    
	    reportLog("3.1: Select an action to edit Form");
	    adminstrationFormLibraryPage.selectActionToEditForm();
	    
	    reportLog("3.2: Form details are in edit mode");
	    adminstrationFormLibraryPage.verifyCancelIconIsEnabled();
	    
	    reportLog("3.3: Save is disabled");
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();
	    
	    reportLog("3.4: Cancel became enabled");
	    adminstrationFormLibraryPage.verifyCancelIconIsEnabled();
	    
	    reportLog("4.1: Update form attributes (Abbreviation with Form Factor must be unique)");
	    adminstrationFormLibraryPage.updateFormAttributes(Constants.updatedAbbreviation);
	    
	    reportLog("4.2: Save become enabled");
	    adminstrationFormLibraryPage.verifySaveButtonIsEnabled();
	    
	    reportLog("4.3: There is no option to clear Standard Form Administration Time value (field is required)");
	    adminstrationFormLibraryPage.verifyNoOptionToClearStandardAdministrationTimeValue(Constants.resetadminTime_hoursToZero, Constants.resetadminTime_minutesToZero);
	    adminstrationFormLibraryPage.resetAdministrationTimeValue(Constants.adminTime_hoursMobile, Constants.adminTime_minutesMobile);
	    
	    reportLog("5.1: Clear a required attribute");
	    adminstrationFormLibraryPage.clearRequiredAttribute();
	    
	    reportLog("5.2: Save became disabled");
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();
	    
	    reportLog("6.1: Specify the cleared value");
	    adminstrationFormLibraryPage.specifyClearedValueIntextBox(Constants.formMobileAbbreviation1);
	    
	    reportLog("6.2: Select an action to save changes");
	    adminstrationFormLibraryPage.selectSave();
	    
	    reportLog("6.3: The changes are saved");
	    adminstrationFormLibraryPage.verifySaveChanges(Constants.updatedAbbreviation);
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();
	        
	    reportLog("7.1: Select an action to edit Form (Pr#1.1)");
	   /* adminstrationFormLibraryPage.clickOnSearchForm(Constants.formMobileAbbreviation2);
	    adminstrationFormLibraryPage.verifySelectedFormDisplayed(Constants.formMobileAbbreviation2);
	   */ 
	    adminstrationFormLibraryPage.selectActionToEditForm();
	    

	    reportLog("7.2: Specify abbreviation and From Factor of another existing form (Pr #1.2)");
	    adminstrationFormLibraryPage.specifyClearedValueIntextBox(Constants.formMobileAbbreviation2);

	    reportLog("7.3: Select an action to save the form");
	    adminstrationFormLibraryPage.selectSave();

	    reportLog("7.4: The changes are not saved");
	    adminstrationFormLibraryPage.verifyrequiredFieldOnlyAcceptUniqueValue();

	    
	    reportLog("7.5: There is an identification that abbreviation must be unique");
	    adminstrationFormLibraryPage.verifyThatAbbreviationMustBeUnique(Constants.toolTipText);
	    
	    reportLog("8.1: Modify form attributes");
	    adminstrationFormLibraryPage.updateFormAttributes(Constants.formMobileAbbreviation1);
	    
	    reportLog("8.2: Select an action to cancel changes");
	    adminstrationFormLibraryPage.selectCancelIcon();
	    
	    reportLog("8.3: Changes are not saved");
	    adminstrationFormLibraryPage.verifyChangedAreNotSaved(Constants.updatedAbbreviation);
	    
	    reportLog("8.4: reset the changed mobile form abbreviation");
	    adminstrationFormLibraryPage.selectActionToEditForm();
	    adminstrationFormLibraryPage.updateFormAttributes(Constants.formMobileAbbreviation1);
	    adminstrationFormLibraryPage.selectSave();
	    
	    reportLog("9: Repeat Step#1 to Step#7 for Pr#2");
	    reportLog(" Navigate to study set up");
	    adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
	    
	    reportLog("9.1:Navigate to Forms library tab and select the Form (Pr #2.1)");
	    adminstrationFormLibraryPage= adminstrationOrganizationPage.navigateFormsLibrary();
	    
	    reportLog("9.2: Select the form as PR#2.1");
	    adminstrationFormLibraryPage.clickOnSearchForm(Constants.formTabletAbbreviation1);
	    adminstrationFormLibraryPage.verifySelectedFormDisplayed(Constants.formTabletAbbreviation1);
	    
	    reportLog("9.3: Form Details are shown in view mode");
	    adminstrationFormLibraryPage.verifyAttributesOfFormsLibraryAreInViewMode();

	    reportLog("9.4: Save is disabled");
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();

	    reportLog("10.1: Select an action to edit Form");
	    adminstrationFormLibraryPage.selectActionToEditForm();

	    reportLog("10.2: Form details are in edit mode");
	    adminstrationFormLibraryPage.verifyCancelIconIsEnabled();

	    reportLog("10.3: Save is disabled");
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();

	    reportLog("10.4: Cancel became enabled");
	    adminstrationFormLibraryPage.verifyCancelIconIsEnabled();

	    reportLog("11.1:  Update form attributes (Abbreviation with Form Factor must be unique)");
	    adminstrationFormLibraryPage.updateFormAttributes(Constants.updatedAbbreviationTablet);

	    reportLog("11.2: Save become enabled");
	    adminstrationFormLibraryPage.verifySaveButtonIsEnabled();

	    reportLog("11.3:  There is no option to clear Standard Form Administration Time value (field is required)");
	    adminstrationFormLibraryPage.verifyNoOptionToClearStandardAdministrationTimeValue(Constants.resetadminTime_hoursToZero, Constants.resetadminTime_minutesToZero);
	    adminstrationFormLibraryPage.resetAdministrationTimeValue(Constants.adminTime_hoursTablet, Constants.adminTime_minutesTablet);
	    
	    reportLog("12.1: Clear a required attribute");
	    adminstrationFormLibraryPage.clearRequiredAttribute();

	    reportLog("12.2: Save became disabled");
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();

	    reportLog("13.1:  Specify the cleared value");
	    adminstrationFormLibraryPage.specifyClearedValueIntextBox(Constants.formTabletAbbreviation1);

	    reportLog("13.2: Select an action to save changes");
	    adminstrationFormLibraryPage.selectSave();

	    reportLog("13.3: The changes are saved");
	    adminstrationFormLibraryPage.verifySaveChanges(Constants.updatedAbbreviationTablet);
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();  

	    reportLog("14.1: Select an action to edit Form (Pr#2.1)");
	    adminstrationFormLibraryPage.selectActionToEditForm();

	    reportLog("14.2: Specify abbreviation and From Factor of another existing form (Pr #2.2)");
	    adminstrationFormLibraryPage.specifyClearedValueIntextBox(Constants.formTabletAbbreviation2);

	    reportLog("14.3: Select an action to save the form");
	    adminstrationFormLibraryPage.selectSave();

	    reportLog("14.4: The changes are not saved");
	    adminstrationFormLibraryPage.verifyrequiredFieldOnlyAcceptUniqueValue();

	    reportLog("14.5: There is an identification that abbreviation must be unique");
	    adminstrationFormLibraryPage.verifyThatAbbreviationMustBeUnique(Constants.toolTipText);

	    reportLog("15.1: Modify form attributes");
	    adminstrationFormLibraryPage.updateFormAttributes(Constants.formTabletAbbreviation1);

	    reportLog("15.2: Select an action to cancel changes");
	    adminstrationFormLibraryPage.selectCancelIcon();
	    adminstrationFormLibraryPage.verifySaveButtonIsDisabled();
	    
	    reportLog("15.3: Changes are not saved");
	    adminstrationFormLibraryPage.verifyChangedAreNotSaved(Constants.updatedAbbreviationTablet);

	    reportLog("15.4: reset the changed tablet form abbreviation");
	    adminstrationFormLibraryPage.selectActionToEditForm();
	    adminstrationFormLibraryPage.updateFormAttributes(Constants.formTabletAbbreviation1);
	    adminstrationFormLibraryPage.selectSave();
	    
	    reportLog(" Logout application");
 		loginPage.logoutApplication();

 		reportLog(" Verify user is logout");
 		loginPage.verifyUserLogout();

	    
	    
	}

}
