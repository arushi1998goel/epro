package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_3346_ConfigureATemporaryIDMaskingForUsingTheAutoGenerateTemporaryID_SIP extends BaseTest {

	protected String MaskForTC8 = "#" + generateRandomString(49);
	protected String MaskForTC7 = "#" + generateRandomString(50);
	private String MaskForTC6 = "SN";
	private String MaskForTC5 = "###";
	private String MaskForTC4 = "SN-###-##-TMP";
	private String MaskForTC3 = "SN-###-TMP";
	private String MaskForTC2 = "SN-####";
	private String MaskForTC1 = "###-SN";
	private String TempID;
	private String sizeToBeVerify;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3346_ConfigureATemporaryIDMaskingForUsingTheAutoGenerateTemporaryID_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
		TempID = properties.getProperty("TempID");
		sizeToBeVerify = properties.getProperty("sizeToBeVerify");

	}

	/**
	 * =======================================================================================================
	 * Test Case Id: FP-TC-3346 Test Case Name: Configure a Temporary ID masking
	 * for using the Auto-generate Temporary ID
	 * 
	 * ========================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-3346_Configure a Temporary ID masking for using the Auto-generate Temporary ID ", groups = {
			"" })
	public void FPTC_3346_VerifyTheAbilityToConfigureAMaskForTheTemporaryIDFieldUsingTheAutoGenerateTemporaryID() {

		reportLog("1.1 Log in to Portal as a User from PR#1 go to Administration -> Studies ");
		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);
		AdministrationOrganizationPage adminstrationOrganizationPage = dashBoardPage.navigateToAdministration();

		reportLog("1.2 Open a Study from PR#2 -> Custom tab -> Edit mode");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy("2180");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("1.3Verify Custom tab in the edit mode for a Study from PR#2 displayed");
		adminstrationStudiesCustomPage.verifyCustomTabInTheEditMode();

		reportLog("2.1 Select Temporary ID option -> Select Auto-generate Temporary ID option");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();
		adminstrationStudiesCustomPage.checkAutoGenerateCheckBox();

		reportLog("2.2Temporary ID and \"Auto-generate Temporary ID\" option options selected. ");
		adminstrationStudiesCustomPage.verifyTemporaryIDOptionsSelected();

		reportLog("2.3Verify Temporary ID field becomes required and marked in red.");
		adminstrationStudiesCustomPage.verifyTempIDFieldRequiredAndMarked("red");

		reportLog(
				"3.1 Select the Temporary ID field and  Verify  hint describing the masking format displayed The field is active.");
		adminstrationStudiesCustomPage.clickOnTemporaryIDFieldAndVerifyHintForMaskingFormatDisplayed();

		reportLog(
				"4.1Set the Temporary ID in format: SN-### (where SN - site number, # - set of digits) and Save changes");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC2);

		reportLog("4.2: click on save button to apply the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("4.3: Verify Edit mode is hidden");
		adminstrationStudiesCustomPage.verifyEditModeIsHidden();

		reportLog("4.4 Verify Temporary ID \"SN-###\" format displayed. ");
		adminstrationStudiesCustomPage.verifyTempIDFormatDisplayed();

		reportLog("4.5Verify Temporary ID and Auto-generate Temporary ID options selected.");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxChecked();
		adminstrationStudiesCustomPage.verifyAutoGenerateTemporaryIdCheckBoxIsChecked();

		reportLog(
				"5.1 Open Home page -> Study dashboard -> Select a Study from PR#2 -> Subjects Select action to \"Add Subject\" option");
		adminstrationStudiesCustomPage.navigateToHome();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);

		reportLog("5.2New Subject page displayed for a Study from PR#2");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("6.1Select the Auto-generate Temporary ID icon  Fill in all the required fields  Cancel changes");
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog(
				"7.1 Select action to Add Subject option  Select the Auto-generate Temporary ID icon Fill in all the required fields and Save changes");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.clickonAutogenerateTemporaryIdIcon();
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("7.2 Auto-generated Temporary ID in a format: SN-### displayed.");
		subjectDetailPage.verifyTempID(TempID);
		subjectDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToAdministration();
		adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("8.1 Repeat Step 4 with the format: ###-SN");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC1);

		reportLog("8.2: click on save button to apply the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("8.3: Verify Edit mode is hidden");
		adminstrationStudiesCustomPage.verifyEditModeIsHidden();

		reportLog("8.4 Verify Temporary ID \"SN-###\" format displayed. ");
		adminstrationStudiesCustomPage.verifyTempIDFormatDisplayed();

		reportLog("8.5Verify Temporary ID and Auto-generate Temporary ID options selected.");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxChecked();
		adminstrationStudiesCustomPage.verifyAutoGenerateTemporaryIdCheckBoxIsChecked();

		reportLog(
				"9.1 Select action to Add Subject option  Select the Auto-generate Temporary ID icon Fill in all the required fields and Save changes");
		adminstrationStudiesCustomPage.navigateToHome();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.clickonAutogenerateTemporaryIdIcon();
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("9.2 Auto-generated Temporary ID in a format: SN-### displayed.");
		subjectDetailPage.verifyTempID(TempID);
		subjectDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToAdministration();
		adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.verifyCustomTabInTheEditMode();

		reportLog("10.1 Repeat Step 4 with the format: SN-###-TMP");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC3);

		reportLog("10.2: click on save button to apply the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("10.3: Verify Edit mode is hidden");
		adminstrationStudiesCustomPage.verifyEditModeIsHidden();

		reportLog("10.4 Verify Temporary ID \"SN-###\" format displayed. ");
		adminstrationStudiesCustomPage.verifyTempIDFormatDisplayed();

		reportLog("10.5Verify Temporary ID and Auto-generate Temporary ID options selected.");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxChecked();
		adminstrationStudiesCustomPage.verifyAutoGenerateTemporaryIdCheckBoxIsChecked();
		adminstrationStudiesCustomPage.navigateToHome();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("11.1 Repeat Step 7");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.clickonAutogenerateTemporaryIdIcon();
		studyNavigatorDashBoardPage.selectSubjectLanguage(Constants.subjectLanguage);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("11.2 Auto-generated Temporary ID in a format: SN-###-TMP displayed.");
		subjectDetailPage.verifyTempID(TempID);
		subjectDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToAdministration();
		adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.verifyCustomTabInTheEditMode();

		reportLog("12.1 Repeat Step 4 with the format: SN-###-##-TMP");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC4);

		reportLog("12.2Temporary ID field marked in red. ");
		adminstrationStudiesCustomPage.verifyTempIDFieldRequiredAndMarked(Constants.BackgroundColorCode_Red);

		reportLog("12.3Save control is disabled.");
		adminstrationStudiesCustomPage.verifyCustomTabInTheEditMode();

		reportLog("13.1Repeat Step 4 with the format: ###");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC5);

		reportLog("13.2: click on save button to apply the changes");
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("13.3: Verify Edit mode is hidden");
		adminstrationStudiesCustomPage.verifyEditModeIsHidden();

		reportLog("13.4 Verify Temporary ID \"SN-###\" format displayed. ");
		adminstrationStudiesCustomPage.verifyTempIDFormatDisplayed();

		reportLog("13.5Verify Temporary ID and Auto-generate Temporary ID options selected.");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxChecked();
		adminstrationStudiesCustomPage.verifyAutoGenerateTemporaryIdCheckBoxIsChecked();

		reportLog("14.1 Repeat Step 7");
		adminstrationStudiesCustomPage.navigateToHome();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.clickonAutogenerateTemporaryIdIcon();
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		String TempIdToBeVerified = subjectDetailPage.getTempIDvalue();
		subjectDetailPage.verifyTempIdAsNumber(MaskForTC5, TempIdToBeVerified);

		reportLog("15.1Repeat Step 4 with the format: SN");
		subjectDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToAdministration();
		adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.verifyCustomTabInTheEditMode();
		adminstrationStudiesCustomPage.inputTempID(MaskForTC6);

		reportLog("15.1Temporary ID field marked in red. ");
		adminstrationStudiesCustomPage.verifyTempIDFieldRequiredAndMarked(Constants.BackgroundColorCode_Red);

		reportLog("15.2Repeat Step 4 with the 51 digits with at least 1  #");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC7);
		adminstrationStudiesCustomPage.verifyTextBoxDoesContainsMoreThanMaximumValue(Integer.parseInt(sizeToBeVerify));

		reportLog("16Repeat Step 4 with the 50 digits with at least 1  # ");
		adminstrationStudiesCustomPage.inputTempID(MaskForTC8);
		adminstrationStudiesCustomPage.clickOnSaveBtn();
		adminstrationStudiesCustomPage.verifyEditModeIsHidden();

		reportLog("17 Verify Temporary ID and Auto-generate Temporary ID options selected.");
		adminstrationStudiesCustomPage.verifyTempIDCheckBoxChecked();
		adminstrationStudiesCustomPage.verifyAutoGenerateTemporaryIdCheckBoxIsChecked();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}
}
