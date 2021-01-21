package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class FPTC_1271VerifyCreateSubjectWithAutoGenerationOfTemporaryIDOnPortal_SIP extends BaseTest implements ApplicationVerificationMessage{

	private String studyName, customTempString = generateRandomString(4)+"-", customTempMask="####";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1271VerifyCreateSubjectWithAutoGenerationOfTemporaryIDOnPortal_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		
		reportLog("1.1: Log in to Portal with " + SuperAdminUN);
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.2: Verify User Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2 Navigate to Administration to configure test prerequiste");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("1.3 Navigate to study tab to configure test prerequiste");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("1.4 Search " + studyName + " study to configure test prerequiste");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("1.5 Navigate to custom tab to configure test prerequiste");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog(
				"1.6 In the Study configuration select the Temporary ID autogeneration to configure test prerequiste");
		adminstrationStudiesCustomPage.checkAutoGenerateCheckBox();

		reportLog("1.7 Input Temp Id Format to configure test prerequiste");
		adminstrationStudiesCustomPage.inputTempID(customTempString+customTempMask);

		reportLog("1.8 Click on save button to save the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("1.9 Navigate back to dashboard page by click on home button.");
		adminstrationOrganizationPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		

	}

	/**
	 * =============================================================================
	 * Test Case Id: FPTC_1271 Test Case Name: Create Subject with auto generation
	 * of Temporary ID on Portal
	 * =============================================================================
	 */

	@Test(description = "FPTC_1271 Test Case Name: Create Subject with auto generation of Temporary ID on Portal", groups = {
			"R1" })

	public void FPTC_1271_verifyCreateSubjectWithAutoGenerationOfTemporaryIDOnPortal() {
		reportLog("1.10: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.11: Select " + studyName + " from the study list");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("1.13: Verify Subject Listing Page is displayed");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("1.14: Verify Option to add Subject is available");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("1.15: Select an action to add Subject by selecting the site");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);

		reportLog("1.16: Verify Create new Subject page displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog(
				"1.17: Verify Click to Generate Temporary ID red text displayed on the Temporary ID row with an active icon");
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon();

		reportLog("1.18: Verify Screening# field is required now and editable.");
		studyNavigatorDashBoardPage.verifyScreeningNumIsRequiredAndEditable();

		reportLog("2.1: Select action to Generate Temporary Id control");
		String generatedTempId = studyNavigatorDashBoardPage.generateAutoTemporaryID();

		reportLog("2.2: Verify "+ generatedTempId+" Temporary ID Auto-generated, displayed in the Temporary ID field");
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(generatedTempId);

		reportLog(
				"2.3: Verify Remove Autogenerated Temporary ID control displayed instead of the Generate Temporary Id control ");
		studyNavigatorDashBoardPage.verifyAutoGenerateTemporaryCancelBTNIsDisplay();

		reportLog("2.4: Verify Screening# field is not required now and editable");
		studyNavigatorDashBoardPage.verifyScreeningNumNotRequiredAndEditable();
		
		reportLog("3.1: Verify Configured mask from the Temporary ID displayed on the Custom tab for the Study is same with the auto generated");
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(customTempString);

		reportLog("3.2: Click on cancel button to close the add subject popup");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("3.4 Return to the New Subject Creation page.");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);

		reportLog("4.2 Select the Temporary ID field");
		studyNavigatorDashBoardPage.generateAutoTemporaryID();

		reportLog("4.3 Verify Autogenerated Temporary ID not editable");
		studyNavigatorDashBoardPage.verifyAutoGeneratedTemporaryINPIsNotEditable();

		reportLog("5.1 Select action to Remove Autogenerated Temporary ID control");
		studyNavigatorDashBoardPage.removeAutoGenerateTemporaryID();

		reportLog("5.2 Verify Autogenerated Temporary ID deleted, Click to Generate Temporary ID red text on the Temporary ID row with an active icon displayed");
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon();

		reportLog("5.3 Verify Screening# field is required now");
		studyNavigatorDashBoardPage.verifyScreeningNumIsRequiredAndEditable();

		reportLog("6.1 Fill in the Screening# field");
		studyNavigatorDashBoardPage.inputScreeningNum(generateRandomString(5));

		reportLog(
				"6.2 Verify Click to Generate Temporary ID red text on the Temporary ID row with an icon hidden.Empty not editable Temporary ID field displayed");
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsNotDisplay();

		reportLog("7.1 Clean the Screening# field");
		studyNavigatorDashBoardPage.clearScreeningInp();

		reportLog(
				"7.2 Verify Click to Generate Temporary ID red text displayed on the Temporary ID row with an active icon ");
		studyNavigatorDashBoardPage.verifyClickToGenerateTemporaryIDTXTIsDisplayWithActiveIcon();

		reportLog("7.3 Verify Screening# field is required now and editable.");
		studyNavigatorDashBoardPage.verifyScreeningNumIsRequiredAndEditable();

		reportLog("8.1 	Select action to Generate Temporary Id control");
		String autoGenerateId = studyNavigatorDashBoardPage.generateAutoTemporaryID();

		reportLog("8.2: Verify "+autoGenerateId+" Temporary ID Auto-generated, displayed in the Temporary ID field");
		studyNavigatorDashBoardPage.verifyAutoTemporaryIDIsGenerated(autoGenerateId);

		reportLog(
				"8.3: Verify Remove Autogenerated Temporary ID control displayed instead of the Generate Temporary Id control ");
		studyNavigatorDashBoardPage.verifyAutoGenerateTemporaryCancelBTNIsDisplay();

		reportLog("8.4: Verify Screening# field is not required now and editable");
		studyNavigatorDashBoardPage.verifyScreeningNumNotRequiredAndEditable();

		reportLog("9.1 Select action to Cancel control");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("9.2 Verify Subject Listing is opened");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("10.1 Select action to Add Subject control");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);

		reportLog("10.2 Select action to Generate Temporary Id control");
		String tempNum = studyNavigatorDashBoardPage.generateAutoTemporaryID();
	
		reportLog("10.3 Fill in all required fields");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("10.3 Click on save button");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("10.4 Verify Page refreshes. Created Subject details page displayed. " +tempNum+" Auto-generated Temporary ID displayed.");
		subjectDetailPage.verifyTempIdIsDisplayed(tempNum);

		reportLog("11.1 Navigate back to home page");
		dashBoardPage=subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog(
				"11.2 Navigate to Administration to configure the study to unselect the Temporary ID autogeneration under custom tab");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog(
				"11.3 Navigate to study tab to configure the study to unselect the Temporary ID autogeneration under custom tab");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("11.4 Search " + studyName
				+ " study to configure the study to unselect the Temporary ID autogeneration under custom tab");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog(
				"11.5 Navigate to custom tab to configure the study to unselect the Temporary ID autogeneration under custom tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("11.6 In the Study configuration unselect the Temporary ID autogeneration.");
		adminstrationStudiesCustomPage.uncheckAutoGenerateCheckBox();

		reportLog("11.7 Click on save button to save the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog(
				"11.8 Navigate back to dashboard page by click on home button to create new subject with new study custom configuration");
		adminstrationOrganizationPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("11.9 Navigate to study dashboard page to create new subject with new study custom configuration");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("11.11 Click on add subject button to create new subject with new study custom configuration");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);

		reportLog("11.12 Create a new Subject with the Temporary ID = "+tempNum+" ID of a previously created Subject with autogenerated Temp ID");
		studyNavigatorDashBoardPage.inputTemporaryID(tempNum);

		reportLog("11.13 Select study language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("11.14 Click on save button");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("11.15 Verify Error message with the text displayed: "+SubjectsModuleConstants.newDuplicateTemporaryIdErrorMessage);
		studyNavigatorDashBoardPage.verifyHeaderErrorText(SubjectsModuleConstants.newDuplicateTemporaryIdErrorMessage);

		reportLog("11.16 click on close button of Error message container");
		studyNavigatorDashBoardPage.closeHeaderErrorMessage();

		reportLog("11.17 Verify add subject still open and display");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("11.18 Verify "+studyLanguage+" language still displayed");
		studyNavigatorDashBoardPage.verifyLanguageIsSelected(studyLanguage);

		reportLog("11.19 Click on cancel button to close the add subject popup");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("11.20 Logout from the application");
		loginPage.logoutApplication();

		reportLog("11.21 Verify user logout from the application");
		loginPage.verifyUserLogout();

	}

}
