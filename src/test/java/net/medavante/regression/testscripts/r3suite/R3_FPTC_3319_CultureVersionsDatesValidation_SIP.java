/**
 * @author
 * @date 04/02/2020 
 * =========================================================================
 * Test Case Id: FP-TC-3319 
 * Test Case Name: Subject Details -Culture Versions - Dates validation
 *  * pre-qualification :1. A Study exists with at least one language
                         2. At least one Tablet Study Form exists having more than one culture versions available for a Study language
                         3. At least one Mobile Study Form exists having more than one culture versions available for a Study language
 * ========================================================================= 
 */

package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesFormsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3319_CultureVersionsDatesValidation_SIP extends BaseTest {

	
	private String study;
	
	
	@Factory(dataProvider="Browsers",dataProviderClass=DataProviders.class)
	public R3_FPTC_3319_CultureVersionsDatesValidation_SIP(String browser) {
		super(browser);
	}
	
	
	@BeforeMethod
	public void getTestData() throws Exception
	{
		
		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study=properties.getProperty("Study2180");
	
	}

	
	
	@Test(description="FP-TC-3319 ----Culture Versions - Dates validation")
	public void R3_FPTC_3319_CultureVersionsDatesValidation()
	{
		reportLog(" Log in to the Portal" );
		dashBoardPage=loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			
		reportLog(" Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Navigate to Administration");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog(" Search " + study + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(study);
		
		reportLog("Navigating To Forms SubTab");
		adminstrationFormsPage=adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabForms, AdministrationStudiesFormsPage.class);
		
		reportLog("2.0: Open Forms tab of the Study Pr#1");
		adminstrationFormsPage.selectandclickOnform(Constants.Form1_3319);
		
		reportLog("2.1: Form language version list is displayed");
		adminstrationFormsPage.verifyFormVersionsIsDisplayed();
		
		reportLog("3.0: Click to edit culture versions of the selected Form (Pr#2)");
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form1_3319);
		
		reportLog("3.1: Form language version list is switched to edit mode");
		adminstrationFormsPage.verifyFormLanguageConfigurationIsIoneditMode();
		
		reportLog("4.0: Select one culture version for a Study language, specify End Date only and click to save the change");
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form1_3319, Constants.formVersion);
		adminstrationFormsPage.ConfigureFormVersion(Constants.formVersion);
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		
		reportLog("4.1: Saving is canceled and the selected culture version is identified with validation error mark which has corresponding description in a Form of a hint");
		adminstrationFormsPage.verifyValidationErrorMark(Constants.formVersion);
		
		reportLog("5.0: Select Start Date greater than End Date for the culture version selected in step # 2 and click to save the change");
		adminstrationFormsPage.selectStartDateGraterThanEndDate(Constants.formVersion);
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		
		reportLog("5.1: Saving is canceled and the selected culture version is identified with validation error mark which has corresponding description in a Form of a hint");
		adminstrationFormsPage.verifyValidationErrorMark(Constants.formVersion);

		reportLog("6.0: Select Valid Start and End Date Save the Changes");
		adminstrationFormsPage.clickOnCancelFormLanguageVersionConfigurationTab();
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form1_3319);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form1_3319, Constants.formVersion);
		adminstrationFormsPage.selectValidStartAndEndDate(Constants.formVersion);
		
		reportLog("6.1: Changes Saved");
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		adminstrationFormsPage.verifySavedFormChangesListed(Constants.Form1_3319,Constants.formVersion);
		
		reportLog("7.0: Edit and Select more than one culture version for a Study language Select Start/End dates for the selected culture versions to intersect with the previous "
		+ "selected culture version");
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form1_3319);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form1_3319, Constants.formVersion_1);
		adminstrationFormsPage.selectValidStartAndEndDate(Constants.formVersion_1);
		
		reportLog("7.1: Click to save the changes");
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		
		reportLog("7.2: Saving is canceled and intersected dates are identified with validation error mark which has corresponding description in a Form of a hint");
		adminstrationFormsPage.verifyValidationErrorMark(Constants.formVersion_1);
	
	    reportLog("8.0: Edit and modify the affected Start and End dates to have valid dates without intersecting.Save the changes");
	    adminstrationFormsPage.clickOnCancelFormLanguageVersionConfigurationTab();
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form1_3319);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form1_3319, Constants.formVersion_1);
		adminstrationFormsPage.selectValidStartAndEndfutureDate(Constants.formVersion_1);
	    
	    reportLog("8.1: Changes saved successfully and listed.");
	    adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
	    adminstrationFormsPage.verifySavedFormChangesListed(Constants.Form1_3319, Constants.formVersion_1);
	    
	    reportLog("Repeat Step#2 to Step#7 for Pr#3");
	    reportLog("Same as Step#2 to Step#7");
	    reportLog(" Open Forms tab of the Study Pr#1");
		adminstrationFormsPage.selectandclickOnform(Constants.Form2_3319);
		
		reportLog(" Form language version list is displayed");
		adminstrationFormsPage.verifyFormVersionsIsDisplayed();
		
		reportLog(" Click to edit culture versions of the selected Form (Pr#2)");
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form2_3319);
		
		reportLog("Form language version list is switched to edit mode");
		adminstrationFormsPage.verifyFormLanguageConfigurationIsIoneditMode();
		
		reportLog(" Select one culture version for a Study language, specify End Date only and click to save the change");
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form2_3319, Constants.formVersion);
		adminstrationFormsPage.ConfigureFormVersion(Constants.formVersion);
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		
		reportLog("Saving is canceled and the selected culture version is identified with validation error mark which has corresponding description in a Form of a hint");
		adminstrationFormsPage.verifyValidationErrorMark(Constants.formVersion);
		
		reportLog("Select Start Date greater than End Date for the culture version selected in step # 2 and click to save the change");
		adminstrationFormsPage.selectStartDateGraterThanEndDate(Constants.formVersion);
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		
		reportLog(" Saving is canceled and the selected culture version is identified with validation error mark which has corresponding description in a Form of a hint");
		adminstrationFormsPage.verifyValidationErrorMark(Constants.formVersion);

		reportLog("Select Valid Start and End Date Save the Changes");
		adminstrationFormsPage.clickOnCancelFormLanguageVersionConfigurationTab();
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form2_3319);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form2_3319,Constants.formVersion);
		adminstrationFormsPage.selectValidStartAndEndDate(Constants.formVersion);
	
		
		reportLog(" Changes Saved");
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		adminstrationFormsPage.verifySavedFormChangesListed(Constants.Form2_3319, Constants.formVersion);
		

		reportLog(" Edit and Select more than one culture version for a Study language Select Start/End dates for the selected culture versions to intersect with the previous "
		+ "selected culture version");
		reportLog("Click to save the changes");
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form2_3319);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form2_3319, Constants.formVersion_1);
		adminstrationFormsPage.selectValidStartAndEndDate(Constants.formVersion_1);
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		
		
		reportLog(" Saving is canceled and intersected dates are identified with validation error mark which has corresponding description in a Form of a hint");
		adminstrationFormsPage.verifyValidationErrorMark(Constants.formVersion_1);
	
	    reportLog(" Edit and modify the affected Start and End dates to have valid dates without intersecting.Save the changes");
	    adminstrationFormsPage.clickOnCancelFormLanguageVersionConfigurationTab();
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form2_3319);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form2_3319, Constants.formVersion_1);
		adminstrationFormsPage.selectValidStartAndEndfutureDate(Constants.formVersion_1);
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
	    
	    reportLog(" Changes saved successfully and listed.");
	    adminstrationFormsPage.verifySavedFormChangesListed(Constants.Form2_3319, Constants.formVersion_1);
	
	
	    reportLog("Reset the changes");
	    reportLog("For Form "  +  Constants.Form2_3319  +":-----Mobile Study Form ");
		adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form2_3319);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form2_3319, Constants.formVersion);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form2_3319, Constants.formVersion_1);
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();

	
		reportLog("Reset the changes");
	    reportLog("For Form"  + Constants.formTabletAbbreviation2  +":-----Tablet Study Form ");
	    adminstrationFormsPage.selectandclickOnform(Constants.Form1_3319);
	    adminstrationFormsPage.clickOneditformLanguageVersion(Constants.Form1_3319);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form1_3319, Constants.formVersion);
		adminstrationFormsPage.SelectFormversionToConfiguration(Constants.Form1_3319, Constants.formVersion_1);
		adminstrationFormsPage.clickOnSaveFormLanguagesVersionConfigurationTab();
		adminstrationFormsPage.verifyFormVersionsIsDisplayed();
		
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
	
	}
	
}
