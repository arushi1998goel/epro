package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_3345_ConfigureAutoGenerationOfTempIDForAStudy_MAP extends BaseTest {

	private String studyWithoutTempIDConfigured, studyWithTempIDConfigured;
	private String MaskForTC1 = "######";
	private String MaskForTC2 = "SN-####";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3345_ConfigureAutoGenerationOfTempIDForAStudy_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyWithoutTempIDConfigured = properties.getProperty("AutomationStudyNameCR");
		studyWithTempIDConfigured = properties.getProperty("AutoStudyWithMasking");

		reportLog("Creating PR#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);
		
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		
		
		
//		adminstrationOrganizationPage = dashBoardPage.navigateToAdministration();
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyWithoutTempIDConfigured);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.verifyCustomPage();
		adminstrationStudiesCustomPage.uncheckTemporaryIdCheckBox();
		adminstrationStudiesCustomPage.clearTempID();
		adminstrationStudiesCustomPage.clickOnSaveBtn();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-3345 || Test Case Name: Configure Auto generation of Temp
	 * ID for a Study
	 * ========================================================================= *
	 */

	@Test(description = "FP-TC-3345_Configure Auto generation of Temp ID for a Study ", groups = { "" })
	public void FPTC_3345_ConfigureAutoGenerationOfTempIDForAStudy() throws InterruptedException {

		reportLog("1.1: Login in to application to verify TC#1");
		loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("1.2: Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		
//		dashBoardPage.navigateToAdministration();

		reportLog("1.3: Navigate To Studies");
		adminstrationOrganizationPage.navigateToStudies();

		reportLog("1.4: Search study name in the search input");
		adminstrationStudiesPage.searchAndClickOnStudy(studyWithoutTempIDConfigured);

		reportLog("1.5: Navigate To Studies Custom Tab");
		adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("1.6: Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("2.1: Select Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("2.2: Verify Auto-generate Temporary ID option appeared");
		adminstrationStudiesCustomPage.verifyAutoGenerateCheckBoxAppeared();

		reportLog("3.1: Unselect Temporary ID option");
		adminstrationStudiesCustomPage.uncheckTemporaryIdCheckBox();

		reportLog("3.2: Verify Auto-generate Temporary ID option is hidden");
		adminstrationStudiesCustomPage.verifyAutoGenerateCheckBoxNotAppeared();

		reportLog("4.1: Select Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("4.2: Click on cancel button");
		adminstrationStudiesCustomPage.clickOnCancelBtn();

		reportLog("4.3: Verify Edit mode is hidden");
		adminstrationStudiesCustomPage.verifyEditModeIsHidden();

		reportLog("4.4: Verify Temporary Id Check Box is not checked ");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxNotChecked();

		reportLog("5.1: Select Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("5.2: Select Auto-generate Temporary ID option");
		adminstrationStudiesCustomPage.checkAutoGenerateCheckBox();

		reportLog("5.3: Select Auto-generate Temporary ID option");
		adminstrationStudiesCustomPage.verifyTempIDFieldRequiredAndMarked(Constants.BackgroundColorCode_Red);

		reportLog("6.1: Click on cancel button");
		adminstrationStudiesCustomPage.clickOnCancelBtn();

		reportLog("6.2: Verify Edit mode is hidden");
		adminstrationStudiesCustomPage.verifyEditModeIsHidden();

		reportLog("6.3: Verify Temporary Id Check Box is not checked ");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxNotChecked();

		reportLog("7.1: Select Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("7.2: Select Auto-generate Temporary ID option");
		adminstrationStudiesCustomPage.checkAutoGenerateCheckBox();

		reportLog("7.3: Unselect Temporary ID option");
		adminstrationStudiesCustomPage.uncheckTemporaryIdCheckBox();

		reportLog("7.4: Verify Temporary ID field is not required");
		adminstrationStudiesCustomPage.verifyTempIDFieldNotRequired();

		reportLog("8.1: Select Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("8.2: click on the Temporary ID field And Verify hint for the Temporary ID masking format displayed");
		adminstrationStudiesCustomPage.clickOnTemporaryIDFieldAndVerifyHintForMaskingFormatDisplayed();

		reportLog("9.1: Enter the Temporary ID Format");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC1);

		reportLog("9.2: Click on cancel button");
		adminstrationStudiesCustomPage.clickOnCancelBtn();

		reportLog("9.3: Verify Edit mode is hidden");
		adminstrationStudiesCustomPage.verifyEditModeIsHidden();

		reportLog("9.4: Verify Temporary Id Check Box is not checked ");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxNotChecked();

		reportLog("10.1: Select Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("10.2: Select Auto-generate Temporary ID option");
		adminstrationStudiesCustomPage.checkAutoGenerateCheckBox();

		reportLog("10.3: Enter the Temporary ID Format");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC1);

		reportLog("10.4: Navigate To Studies General Tab");
		adminstrationStudiesPage.navigateToStudyGeneralTab();

		reportLog("10.5: Verify confirmation Modal PopUp displayed ");
		adminstrationStudiesCustomPage.verifyConfirmationModalPopUpDisplayed();

		reportLog("10.6: Select No on confirmation Modal PopUp");
		adminstrationStudiesCustomPage.selectNoOnConfirmationModal();

		reportLog("10.7: Verify Custom tab in the edit mode");
		adminstrationStudiesCustomPage.verifyCustomTabInEditMode();

		reportLog("11.1: Navigate To Studies sites Tab");
		adminstrationStudyGeneralPage = adminstrationStudiesPage.navigateToStudyGeneralTab();

		reportLog("11.2: Verify confirmation Modal PopUp displayed ");
		adminstrationStudiesCustomPage.verifyConfirmationModalPopUpDisplayed();

		reportLog("11.3: Select yes on confirmation Modal PopUp");
		adminstrationStudiesCustomPage.selectYesOnConfirmationModal();

		reportLog("11.4: Verify Studies Sites Page is displayed");
		adminstrationStudyGeneralPage.verifyAdministrationStudiesGeneralPageIsOpen(studyWithoutTempIDConfigured);

		reportLog("11.5: Navigate To Studies Custom Tab");
		adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("11.6: Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("11.7: Verify Temporary Id Check Box is not checked ");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxNotChecked();

		reportLog("12.1: Select Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("12.2: Select Auto-generate Temporary ID option");
		adminstrationStudiesCustomPage.checkAutoGenerateCheckBox();

		reportLog("12.3: Enter the Temporary ID Format");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC1);

		reportLog("12.4: click on save button to apply the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("12.5: Verify Temporary Id Check Box is checked ");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxChecked();

		reportLog("12.6:  Verify auto Generate Temporary Id Check Box is checked");
		adminstrationStudiesCustomPage.verifyAutoGenerateTemporaryIdCheckBoxIsChecked();

		reportLog("12.17: Logout application");
		loginPage.logoutApplication();

		reportLog("12.18: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("13.1: Login in to application to verify TC#1");
		loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("13.2: Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
//		dashBoardPage.navigateToAdministration();

		reportLog("13.3: Navigate To Studies");
		adminstrationOrganizationPage.navigateToStudies();

		reportLog("13.4: Search study name in the search input");
		adminstrationStudiesPage.searchAndClickOnStudy(studyWithTempIDConfigured);

		reportLog("13.5: Navigate To Studies Custom Tab");
		adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("13.6: Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("14.1: Select Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("14.2: Select Auto-generate Temporary ID option");
		adminstrationStudiesCustomPage.checkAutoGenerateCheckBox();

		reportLog("14.3: Enter the Temporary ID Format");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC2);

		reportLog("14.4: click on save button to apply the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("14.5: Verify Temporary Id Check Box is checked ");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxChecked();

		reportLog("14.6:  Verify auto Generate Temporary Id Check Box is checked");
		adminstrationStudiesCustomPage.verifyAutoGenerateTemporaryIdCheckBoxIsChecked();

		reportLog("14.7: Logout application");
		loginPage.logoutApplication();

		reportLog("14.9: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}