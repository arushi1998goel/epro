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

public class R3_FPTC_3124_VerifyFormAdded_SIP extends BaseTest {
	
	String formName;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3124_VerifyFormAdded_SIP(String Browser) {
		super(Browser);
	} 

	@BeforeMethod
	public void getData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop1 = Configuration.readTestData("RegressionTestData");
		studyName = prop1.getProperty("2180");
		formName= "Form"+generateRandomString(3);
	}
	
	@Test
	public void R3_FPTC_3124_VerifyUserIsAbleToAddNewForm() {
		

			reportLog("1.1: Login to the application");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

			reportLog("1.2 :Verify site user is logged in");
			dashBoardPage.verifyMedavantePortalPage();

			reportLog("2.1: Navigate to Forms library");
			adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText ,Constants.StudySetupText);
			adminstrationFormLibraryPage=adminstrationOrganizationPage.navigateFormsLibrary();
			
			reportLog("2.2: Verify An option to add Form is available ");
			adminstrationFormLibraryPage.verifyFormPageWithAddFormIconDisplay();
			
			reportLog("3.1 	Select an action to add a Form");
			adminstrationFormLibraryPage.clickOnAddForm();
			
			reportLog("3.1 : Specify form attributes  and Select an action to save the Form");
			adminstrationFormLibraryPage.createFormWithRequiredFields(formName, "Test description", "02",
					"00","5D-ASC","Tablet");
						
			reportLog("4.1:  Verify New form is added and displayed in the list of available forms");
			adminstrationFormLibraryPage.verifyAddedFormIsDisplayedInTheList(formName);
			
			reportLog("4.2: Verify There is an option to delete the form ");
			adminstrationFormLibraryPage.verifyFormAddedDeleteButtonIsDisplayed(formName);
			
			reportLog("5.1:Select an action to add a Form");
			adminstrationFormLibraryPage.clickOnAddForm();
	
			reportLog("5.2: Specify abbreviation and Form Factor of existing form (Pr #1)");
			adminstrationFormLibraryPage.createFormWithRequiredFields("Formygz", "Test description",  "02",
					"00","ADBC","Tablet");
						
			reportLog("5.3: Specify other form attributes");
			adminstrationFormLibraryPage.verifyOtherAttributes();
			
			reportLog("5.4:Select an action to save the Form");
			adminstrationFormLibraryPage.selectSave();
			
			reportLog("5.5:  Verify New form is not added");
			
			reportLog("5.6 :Verify  There is an identification that Abbreviation with Form Factor must be unique");
			adminstrationFormLibraryPage.verifYAbbreviationAndFormFactorsAreUnique();
			
			reportLog("6.1: Select an action to cancel adding new Form");
			adminstrationFormLibraryPage.selectCancelIcon();
			adminstrationFormLibraryPage.selectYesPopup();

			reportLog("6.2 : Verify New form is not added ");
			adminstrationFormLibraryPage.verifyFormsAreNotAdded();
			
//			reportLog("7.1 Deleting the Created Form");
//			adminstrationFormLibraryPage.deleteTheCreatedForms(formName);
//			adminstrationFormLibraryPage.selectYesPopup();
		
			reportLog("8.1 logout Appliication");
			loginPage=loginPage.logoutApplication();
	}
}
