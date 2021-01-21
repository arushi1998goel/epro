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

public class FPTC_3327_UserWithTheRightClaimCanManageTabletFormSettings_SIP extends BaseTest {

	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3327_UserWithTheRightClaimCanManageTabletFormSettings_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		Properties prop = Configuration.readTestData("RegressionTestData");
		userWithoutRightClaim = prop.getProperty("UserCantConfigureStudyFormTabletSettings");
		studyName=prop.getProperty("tabletFormSettingStudy");
		System.setProperty("className", getClass().getSimpleName());
		

	}

	/**
	 * =========================================================================
	 * Test Case Id : FP-TC-3327 Test Case Name : In portal - user with the
	 * right claim can manage tablet form settings
	 * 
	 * =========================================================================
	 * 
	 */

	@Test(description = "FP-TC-3327 --In portal - user with the right claim can manage tablet form settings", groups = {
			"R1" })
	public void FPTC_3327_verifyUserWIthOutRightClaimsCantManagaeTabletFormSetting() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2:Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.1:Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("2.2:Select Study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("2.3:Navigate To Forms Tab");
		adminstrationFormsPage = adminstrationStudiesPage.navigateToStudyFormsTab();

		reportLog("2.4:Verify Form Page loaded properly");
		adminstrationFormsPage.verifyFormPageIsDisplayed();

		reportLog("3:Select ClinRo Form");
		adminstrationFormsPage.selectClinRoForm(Constants.ClinRO_Form_Label);

		reportLog("3.1:Verify Tablet Form Settings Icon Present And  By Default Icon Is Enabled");
		adminstrationFormsPage.verifyTabletFormSettingIconPresentAndEnabled();

		reportLog("4:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("4.1:Verify Tablet Form Setting Options");
		adminstrationFormsPage.verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus();

		reportLog("4.2:Verify Tablet Form Setting ClinRo Only  Options");
		adminstrationFormsPage.verifyTabletFormSettingsClinRoAssessmentOnlyOptions();

		reportLog("4.3:Verify Tablet Form Setting Hidden  Options Not Visible");
		adminstrationFormsPage.verifyTabletFormSettingsForHiddenOptions();

		reportLog("4.4:Verify Save And Cancel Options");
		adminstrationFormsPage.verifySaveButtonDisbledAndCancelEnabled();

		reportLog("5:Verify after selection of Cancel button on Pop Up,Study Form view should visible");
		adminstrationFormsPage.verifyStudyFormViewPageDisplayedAfterClickOnCancelBTNOnTabletSettingPopUP();

		// ============ For Pro form ===================================//
		reportLog("6:Select Pro Form And Repeating Steps For Pro Form");
		adminstrationFormsPage.selectProForm(Constants.Pro_Form_Label);

		reportLog("6.1:Verify Tablet Form Settings Icon Present And  By Default Icon Is Enabled");
		adminstrationFormsPage.verifyTabletFormSettingIconPresentAndEnabled();

		reportLog("6.2:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("6.3:Verify Tablet Form Setting Options");
		adminstrationFormsPage.verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus();

		reportLog("6.4:Verify Take Break And I'm Done Options");
		adminstrationFormsPage.verifyTakeBreakAndDone();

		reportLog("6.5:Verify Save And Cancel Options");
		adminstrationFormsPage.verifySaveButtonDisbledAndCancelEnabled();

		reportLog("6.6:Verify after selection of Cancel button on Pop Up,Study Form view should visible");
		adminstrationFormsPage.verifyStudyFormViewPageDisplayedAfterClickOnCancelBTNOnTabletSettingPopUP();

		// =========== For ObsRO form ===================================//

		reportLog("7:Select Pro Form And Repeating Steps For Pro Form");
		adminstrationFormsPage.selectObsRoForm(Constants.ObsRo_Form_Label);
		;

		reportLog("7.1:Verify Tablet Form Settings Icon Present And  By Default Icon Is Enabled");
		adminstrationFormsPage.verifyTabletFormSettingIconPresentAndEnabled();

		reportLog("7.2:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("7.3:Verify Tablet Form Setting Options");
		adminstrationFormsPage.verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus();

		reportLog("7.4:Verify Take Break And I'm Done Options");
		adminstrationFormsPage.verifyTakeBreakAndDone();

		reportLog("7.5:Verify Save And Cancel Options");
		adminstrationFormsPage.verifySaveButtonDisbledAndCancelEnabled();

		reportLog("7.6:Verify after selection of Cancel button on Pop Up,Study Form view should visible");
		adminstrationFormsPage.verifyStudyFormViewPageDisplayedAfterClickOnCancelBTNOnTabletSettingPopUP();

		reportLog("7.7:Verify Form Page loaded properly");
		adminstrationFormsPage.verifyFormPageIsDisplayed();

		reportLog("8: Logout application");
		loginPage.logoutApplication();

		reportLog("8.1: Verify user is logout");
		loginPage.verifyUserLogout();


		// ============== Verification of above scenarios with
		// canConfigureStudyFormTabletSettings and canManageStudies exists
		// ======//

		reportLog("10:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("11:Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("11.1:Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("11.2:Select Study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("11.3:Navigate To Forms Tab");
		adminstrationFormsPage = adminstrationStudiesPage.navigateToStudyFormsTab();

		reportLog("12:Select ClinRo Form");
		adminstrationFormsPage.selectClinRoForm(Constants.ClinRO_Form_Label);

		reportLog("12.1:Verify Tablet Form Settings Icon Present And  By Default Icon Is Enabled");
		adminstrationFormsPage.verifyTabletFormSettingIconPresentAndEnabled();

		reportLog("13:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("13.1:Verify Tablet Form Setting Options");
		adminstrationFormsPage.verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus();

		reportLog("13.2:Verify Tablet Form Setting ClinRo Only  Options");
		adminstrationFormsPage.verifyTabletFormSettingsClinRoAssessmentOnlyOptions();

		reportLog("14:Verify Save And Cancel Options");
		adminstrationFormsPage.verifySaveButtonDisbledAndCancelEnabled();

		reportLog("15:Verify after selection of Cancel button on Pop Up,Study Form view should visible");
		adminstrationFormsPage.verifyStudyFormViewPageDisplayedAfterClickOnCancelBTNOnTabletSettingPopUP();

		// ============ For Pro form ===================================//
		reportLog("16:Select Pro Form And Repeating Steps For Pro Form");
		adminstrationFormsPage.selectProForm(Constants.Pro_Form_Label);

		reportLog("16.1:Verify Tablet Form Settings Icon Present And  By Default Icon Is Enabled");
		adminstrationFormsPage.verifyTabletFormSettingIconPresentAndEnabled();

		reportLog("16.2:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("16.3:Verify Tablet Form Setting Options");
		adminstrationFormsPage.verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus();

		reportLog("16.4:Verify Take Break And I'm Done Options");
		adminstrationFormsPage.verifyTakeBreakAndDone();

		reportLog("16.5:Verify Save And Cancel Options");
		adminstrationFormsPage.verifySaveButtonDisbledAndCancelEnabled();

		reportLog("16.6:Verify after selection of Cancel button on Pop Up,Study Form view should visible");
		adminstrationFormsPage.verifyStudyFormViewPageDisplayedAfterClickOnCancelBTNOnTabletSettingPopUP();

		// =========== For ObsRO form ===================================//

		reportLog("17:Select Pro Form And Repeating Steps For Pro Form");
		adminstrationFormsPage.selectObsRoForm(Constants.ObsRo_Form_Label);

		reportLog("17.1:Verify Form Page loaded properly");
		adminstrationFormsPage.verifyFormPageIsDisplayed();

		reportLog("17.2:Verify Tablet Form Settings Icon Present And  By Default Icon Is Enabled");
		adminstrationFormsPage.verifyTabletFormSettingIconPresentAndEnabled();

		reportLog("17.3:Click On Tablet Form Settings");
		adminstrationFormsPage.clickOnTabletFormSettings();

		reportLog("17.4:Verify Tablet Form Setting Options");
		adminstrationFormsPage.verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus();

		reportLog("17.5:Verify Take Break And I'm Done Options");
		adminstrationFormsPage.verifyTakeBreakAndDone();

		reportLog("17.6:Verify Save And Cancel Options");
		adminstrationFormsPage.verifySaveButtonDisbledAndCancelEnabled();

		reportLog("17.7:Verify after selection of Cancel button on Pop Up,Study Form view should visible");
		adminstrationFormsPage.verifyStudyFormViewPageDisplayedAfterClickOnCancelBTNOnTabletSettingPopUP();

		reportLog("18: Logout application");
		loginPage.logoutApplication();

		reportLog("18.1: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
