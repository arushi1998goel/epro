package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_3322FormsTypeConfigurationTest_SIP extends BaseTest {
	
	protected String studyName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_3322FormsTypeConfigurationTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("studyForEnrolledStatus");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3322 Test Case Name:Forms type configuration
	 * ====================================================================================================================
	 */

	 @Test(description = "FP-TC-3322_ VerifyFormsTypeConfiguration", groups = { "R2" })
	  public void FPTC_3322_VerifyFormsTypeConfiguration() {

	   reportLog("1:Login in to application");
	   dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

	   reportLog("1.1:Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

	   reportLog("1.2:Navigate To Studies");
	   adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

	   reportLog("1.3:Select Study");
	   adminstrationStudiesPage.searchAndClickOnStudy(studyName);

	   reportLog("1.4:Navigate To Forms Tab");
	   adminstrationFormsPage = adminstrationStudiesPage
	     .navigateToStudyFormsTab();

	   reportLog("1.5 List of forms selected for study is displayed in view mode");
	   adminstrationFormsPage.verifyFormsConfiguredInStudyPresent();

	   reportLog("1.6 Edit Control Is Available");
	   adminstrationFormsPage.verifyFormsPage();

	   reportLog("2:Click On Edit");
	   adminstrationFormsPage.clickOnEditForm();

	   reportLog("2.1:List of all forms available on forms library is displayed in edit mode");
	   adminstrationFormsPage
	     .verifyAllFormsPresentInEditableModeAndClinRoTypeIsFirstBlindOptionPresentWithSaveCancel("ClinRO","Mobile");

	   reportLog("3:Click on Type dropdown of first available form and select 'PRO' type");
	   adminstrationFormsPage.clickOnDropDownAndSelectFormType(Constants.Assesment_AdasCog14List1FormName_QA, Constants.Pro_Form_Label);

	   reportLog("3.1:Verify 'PRO' label is displayed on Type dropdown. Blind option is disabled.");
	   adminstrationFormsPage.verifyTypeDropDownOptionAndBlindOptionDisabled(Constants.Assesment_AdasCog14List1FormName_QA, Constants.Pro_Form_Label);

	   reportLog("4:Click on Type drop-down on next available form and select 'ObsRO' type");
	   adminstrationFormsPage.clickOnDropDownAndSelectFormType(Constants.Assesment_AdasCog14List2FormName_QA, Constants.ObsRo_Form_Label);

	   reportLog("4.1:Verify 'ObsRO' label is displayed on Type dropdown. Blind option is disabled.");
	   adminstrationFormsPage.verifyTypeDropDownOptionAndBlindOptionDisabled(Constants.Assesment_AdasCog14List2FormName_QA, Constants.ObsRo_Form_Label);

	   reportLog("5:Click on Type dropdown of first available form and select 'Mobile' type");
	   adminstrationFormsPage.clickOnDropDownAndSelectFormType(Constants.Mobile_VP, Constants.Mobile_FormName);

	   reportLog("5.1 Verify 'Mobile' label is displayed on Type dropdown. Blind option is disabled.");
	   adminstrationFormsPage.verifyTypeDropDownOptionAndBlindOptionDisabled(Constants.Mobile_VP, Constants.Mobile_FormName);

	   reportLog("6:Click on Type drop-down on next available form and select 'Event' type");
	   adminstrationFormsPage.clickOnDropDownAndSelectFormType(Constants.VForm, Constants.Event_FormName);

	   reportLog("6.1 Verify 'Event' label is displayed on Type dropdown. Blind option is disabled.");
	   adminstrationFormsPage.verifyTypeDropDownOptionAndBlindOptionDisabled(Constants.VForm, Constants.Event_FormName);
	    
	   reportLog("7:Click On save Button");
	   adminstrationFormsPage.clickOnSaveScaleButton();
	   
	   reportLog("7.1:List of forms selected for study is displayed in view mode. Selected form types in step #3 Is displayed as labels in Form Type column ");
	   adminstrationFormsPage.verifyFormTypeAfterSavingFormType(Constants.Assesment_AdasCog14List1FormName_QA, Constants.Pro_Form_Label);
	   
	   reportLog("7.2: List of forms selected for study is displayed in view mode. Selected form types in step #4 Is displayed as labels in Form Type column ");
	   adminstrationFormsPage.verifyFormTypeAfterSavingFormType(Constants.Assesment_AdasCog14List2FormName_QA, Constants.ObsRo_Form_Label);
	   
	   reportLog("7.3: List of forms selected for study is displayed in view mode. Selected form types in step #5 Is displayed as labels in Form Type column ");
	   adminstrationFormsPage.verifyFormTypeAfterSavingFormType(Constants.Mobile_VP, Constants.Mobile_FormName);
	   
	   reportLog("7.4: List of forms selected for study is displayed in view mode. Selected form types in step #6 Is displayed as labels in Form Type column ");
	   adminstrationFormsPage.verifyFormTypeAfterSavingFormType(Constants.VForm, Constants.Event_FormName);
	   
	   reportLog("Logout application");
	   loginPage.logoutApplication();

	   reportLog("Verify user is logout");
	   loginPage.verifyUserLogout(); 
	 }
}
