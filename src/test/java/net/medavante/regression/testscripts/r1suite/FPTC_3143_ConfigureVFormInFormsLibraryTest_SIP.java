package net.medavante.regression.testscripts.r1suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;


public class FPTC_3143_ConfigureVFormInFormsLibraryTest_SIP extends BaseTest {	
	
	String formName = "DemoForm_" +GenerateRandomNumber(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3143_ConfigureVFormInFormsLibraryTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3143 || Test Case Name: Configure VForm in Forms Library
	 * pre-qualification : At least 1 form of VForm type is available for
	 * configuration At least 1 user with appropriate claim to configure a form,
	 * exists At least 1 study is available
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-3143 --Configure VForm in Forms Library", groups = { "R1" })
	public void FPTC_3143_verifyConfigureVFormInFormsLibrary() throws Exception {

		reportLog("1:Login in to application as the user of Pr#2");
	    dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);

		reportLog("1.1: Navigate to Administration ");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("1.2: Navigate to Forms library");
		adminstrationFormLibraryPage = adminstrationOrganizationPage.navigateFormsLibrary();

		reportLog("2: Select the option to add a new form");
		adminstrationFormLibraryPage.clickOnAddForm();

		reportLog("2.1: Validate required fields");
		adminstrationFormLibraryPage.verifyRequiredFieldsAreRed();

		reportLog("2.2:Form attributes are displayed");
		adminstrationFormLibraryPage.validateFormLablesArePresent(
				new String[] { "Abbreviation", "Description", "Administration Time (HH:MM)", "Parent Scales",
						"Risk Form", "Form Time Point", "Customized Form", "Organization", "Comments" });

		reportLog("3: Add required fields");
		adminstrationFormLibraryPage.verifyRequiredFieldsAreRed();

		reportLog("3.1: Save the form by adding the required fields");
		adminstrationFormLibraryPage.createFormWithRequiredFields(formName, "Test description", "02", "00", "ADBC","Tablet");

		reportLog("3.2: Search the New form");
		adminstrationFormLibraryPage.clickOnSearchForm(formName);

		reportLog("3.3: New form is added and displayed in the list of available forms");
		adminstrationFormLibraryPage.verifySelectedFormDisplayed(formName);

		reportLog("3.4: There is an option to delete the form");
		adminstrationFormLibraryPage.verifyRemoveFormDisplayed();
        
		reportLog("4: Add Version control is available");
		adminstrationFormLibraryPage.valiateHeaderName("Versions");

		reportLog("5. Select to add the version");
		adminstrationFormLibraryPage.clickOnAddVersion();

		reportLog("5.1: Option to specify the form Template Type, is displayed");
		adminstrationFormLibraryPage.validateFormLablesArePresent(
				new String[] { "Form Template Type", "Field Definition", "Paper form", "Description" });

		reportLog("6: Select VForm as the form Template Type and Save");
		adminstrationFormLibraryPage.selectVForm(Constants.VFormType);

		reportLog("6.1: Validate edit icon display after saving version");
		adminstrationFormLibraryPage.verifyEditIconDisplayed();

		reportLog("6.2: Add language control is displayed");
		adminstrationFormLibraryPage.valiateHeaderName(Constants.Languages_HeaderName);

		reportLog("6.3: Click on edit icon");
		adminstrationFormLibraryPage.clickOnEditIcon();

		reportLog("7: Select to add language");
		adminstrationFormLibraryPage.clickOnAddLanguage();

		reportLog("8: List of languages to add to the form version#1 is displayed");
		adminstrationFormLibraryPage.verifyLanguageListDisplayed();

		reportLog("8.1: Add a form language");
		adminstrationFormLibraryPage.selectSearchLanguageOption(Constants.subjectLanguage);

		reportLog("8.2: Language is added to form and Add a subform control is enabled");		
		adminstrationFormLibraryPage.validateFormLablesArePresent(new String[] { Constants.subjectLanguage, "Delete Language" });

		reportLog("10: Select the add subform control");
		adminstrationFormLibraryPage.addSubForm();

		reportLog("9: VForm dorp down menu is dispalyed");
		adminstrationFormLibraryPage.verifyDropDownForForm();

		reportLog("10. Select the VFrom menu and Select a subform");
		adminstrationFormLibraryPage.selectVFromInLanguage();

		reportLog("11. Select the PDF icon for the newly added form");
		adminstrationFormLibraryPage.clickPDFIcon();

		reportLog("12: A message is displayed indicating that the template is being processed");
		adminstrationFormLibraryPage.verifyWarningMessage(Constants.Form_TemplateGenreationErrorMessage);
		
		reportLog("Logout from the application");
		loginPage.logoutApplication();
		
		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
