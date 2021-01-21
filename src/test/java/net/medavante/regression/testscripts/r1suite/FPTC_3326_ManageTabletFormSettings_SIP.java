package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_3326_ManageTabletFormSettings_SIP extends BaseTest {


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3326_ManageTabletFormSettings_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("tabletFormSettingStudy");
		System.setProperty("className", getClass().getSimpleName());

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3326 || Test Case Name:Manage tablet form settings
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-3326_manage tablet form settings", groups = { "R1" })
	public void FPTC_3326_verifyUserCanManageTabletFormSettings() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2:Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2:Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("2:Select Study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("2:Navigate To Forms Tab");
		adminstrationFormsPage = adminstrationStudiesPage.navigateToStudyFormsTab();

		reportLog("Configuring PreRequisite");
		adminstrationFormsPage.selectClinRoForm(Constants.ClinRO_Form_Label);
		adminstrationFormsPage.clickOnTabletFormSettings();
		adminstrationFormsPage.deselectAllCheckBoxOptions();
		adminstrationFormsPage.selectProForm(Constants.Pro_Form_Label);
		adminstrationFormsPage.clickOnTabletFormSettings();
		adminstrationFormsPage.deselectAllCheckBoxOptions();

		reportLog("3:Select ClinRo Form");
		adminstrationFormsPage.selectClinRoForm(Constants.ClinRO_Form_Label);

		reportLog("3:Verify Tablet Form Settings Icon Present And  By Default Icon Is Enabled");
		adminstrationFormsPage.verifyTabletFormSettingIconPresentAndEnabled();

		reportLog("4:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("4:Verify Tablet Form Setting Options");
		adminstrationFormsPage.verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus();

		reportLog("4:Verify Tablet Form Setting ClinRo Only  Options");
		adminstrationFormsPage.verifyTabletFormSettingsClinRoAssessmentOnlyOptions();

		reportLog("4:Verify Tablet Form Setting Hidden  Options Not Visible");
		adminstrationFormsPage.verifyTabletFormSettingsForHiddenOptions();

		reportLog("4:Verify Save And Cancel Options");
		adminstrationFormsPage.verifySaveButtonDisbledAndCancelEnabled();

		reportLog("5:Select Checkbox");
		adminstrationFormsPage.selectCheckBox();

		reportLog("5:Verify Checkbox Value Is Selected And Save Buton Enabled");
		adminstrationFormsPage.verifyCheckboxIsSelectedAndSaveEnabled();

		reportLog("6:Save Options");
		adminstrationFormsPage.saveOptions();

		reportLog("6:Verify Study Forms View");
		adminstrationFormsPage.verifyStudyFormsView();

		reportLog("7:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("7:Verify Changes Found");
		adminstrationFormsPage.verifyChangesFound();

		reportLog("8:Revert Changes Of Step 5");
		adminstrationFormsPage.deselectCheckBox();

		reportLog("9:Select Option Turn Off Forward Navigation");
		adminstrationFormsPage.selectCheckBoxForwardNavigation();

		reportLog("9:Verify Checkbox Value Is Selected ");
		adminstrationFormsPage.verifySelectForwardNavigationAndPageSectionSelected();

		reportLog("9:Save Options");
		adminstrationFormsPage.saveOptions();

		reportLog("10:Verify Study Forms View");
		adminstrationFormsPage.verifyStudyFormsView();

		reportLog("11:Select Pro Form And Repeating Steps For Pro Form");
		adminstrationFormsPage.selectProForm(Constants.Pro_Form_Label);

		reportLog("11:Verify Tablet Form Settings Icon Present And  By Default Icon Is Enabled");
		adminstrationFormsPage.verifyTabletFormSettingIconPresentAndEnabled();

		reportLog("11:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("11:Verify Tablet Form Setting Options");
		adminstrationFormsPage.verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus();

		reportLog("11:Verify Take Break And I'm Done Options");
		adminstrationFormsPage.verifyTakeBreakAndDone();

		reportLog("11:Verify Save And Cancel Options");
		adminstrationFormsPage.verifySaveButtonDisbledAndCancelEnabled();

		reportLog("11:Select Checkbox");
		adminstrationFormsPage.selectCheckBox();

		reportLog("11:Verify Checkbox Value Is Selected And Save Buton Enabled");
		adminstrationFormsPage.verifyCheckboxIsSelectedAndSaveEnabled();

		reportLog("11:Save Options");
		adminstrationFormsPage.saveOptions();

		reportLog("11:Verify Study Forms View");
		adminstrationFormsPage.verifyStudyFormsView();

		reportLog("11:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("11:Verify Changes Found");
		adminstrationFormsPage.verifyChangesFound();

		reportLog("11:Revert Changes Of Step 5");
		adminstrationFormsPage.deselectCheckBox();

		reportLog("11:Select Option Turn Off Forward Navigation");
		adminstrationFormsPage.selectCheckBoxForwardNavigation();

		reportLog("11:Verify Checkbox Value Is Selected ");
		adminstrationFormsPage.verifySelectForwardNavigationAndPageSectionSelected();

		reportLog("11:Save Options");
		adminstrationFormsPage.saveOptions();

		reportLog("11:Verify Study Forms View");
		adminstrationFormsPage.verifyStudyFormsView();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}
}