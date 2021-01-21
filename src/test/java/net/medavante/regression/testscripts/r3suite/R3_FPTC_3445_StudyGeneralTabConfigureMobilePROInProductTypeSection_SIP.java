/**
 *@author 
* @date 03/03/2020
* =========================================================================
     *  Test Case Id: FP-TC-3445 || Test Case Name: Study General Tab - Configure Mobile PRO in Product Type section
     * pre-qualification :  At least on User who can manage Studies exists
	 * At least one Study is configured for the test without added Product Type
* ========================================================================= 
*/
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

public class R3_FPTC_3445_StudyGeneralTabConfigureMobilePROInProductTypeSection_SIP extends BaseTest {
	private String abbreviation = "Abbre3445", phase = "Auoto_Phase3445" + generateRandomString(3),
			studyName = "Study3445" + generateRandomString(3), studyName1;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3445_StudyGeneralTabConfigureMobilePROInProductTypeSection_SIP(String browser) {
		super(browser);

	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName1 = properties.getProperty("Study3445_1");

	}

	@Test(description = "FP-TC-3445 --Study General Tab - Configure Mobile PRO in Product Type section", groups = {
			"R3" })
	public void R3_FPTC_3445_StudyGeneralTabConfigureMobilePROinProductTypesection() {

		reportLog("2: Log in to Portal as User PR#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3: Navigate to Administration tab -> Studies ");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.1: Navigate to Studies ");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.2: click on add studies button");
		adminstrationStudyGeneralPage = adminstrationStudiesPage.addStudyButtonicon();

		reportLog("3.3: verify general tab ");
		adminstrationStudiesPage.verifyGeneralTabIsDisplayed();

		reportLog("3.4: verify Required Fields are highligted");
		adminstrationStudyGeneralPage.sponserFieldIsHighlighted(Constants.HighlightedBackgroundColor_3);
		adminstrationStudyGeneralPage.requiredFieldsOnGeneralTabArehighLighted(Constants.HighlightedBackgroundColor_2);

		reportLog("3.5: verify Product Type section is available on the tab and no one options are selected");
		adminstrationStudyGeneralPage.verifyProductTypeSectionIsAvailable();
		adminstrationStudyGeneralPage.verifyNoOptionsAreSelectedInProductTypeSection();

		reportLog("4.1: enter data to create study");
		adminstrationStudyGeneralPage.enterDataToCreateStudy(studyName, abbreviation, phase, Constants.Sponsor_3345);

		reportLog("4.2: click on cancel button");
		adminstrationStudyGeneralPage.clickOncancelbutton();

		reportLog("4.3: verify study is not saved");
		adminstrationStudyGeneralPage.VerifyStudyIsNotSaved(studyName);

		reportLog("5.1: Select the Study from Pr#2");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName1);

		reportLog("5.2: Open General tab in Edit mode");
		adminstrationStudiesPage.verifyGeneralTabIsDisplayed();
		adminstrationStudiesPage.editStudy();
		adminstrationStudiesPage.verifyGeneralTabIsInEditMode();

		reportLog("5.3: verify Product Type section is available on the tab and no one options are selected");
		adminstrationStudyGeneralPage.verifyProductTypeSectionIsAvailable();
		adminstrationStudyGeneralPage.verifyNoOptionsAreSelectedInProductTypeSection();

		reportLog("6.1: verify and Select Mobile PRO checkbox");
		adminstrationStudyGeneralPage.selectMobileProCheckBox();

		reportLog("6.2:  Subject checkbox is selected by default and not editable");
		adminstrationStudyGeneralPage.verifySelectedCheckboxisdisable();

		reportLog("6.3: Observer checkbox is not selected");
		adminstrationStudyGeneralPage.verifyObserverCheckBoxIsnotSelected();

		reportLog("7.1: Select on save button");
		adminstrationStudyGeneralPage.clickOnSaveButton();

		reportLog("7.2: Mobile PRO and Subject items are displayed in Product Type section");
		adminstrationStudyGeneralPage.verifyProductTypeInputSection(Constants.productinput1);

		reportLog("7.3: Schedule tab is available for Study in Pr#2");
		adminstrationStudyGeneralPage.verifyScheduleTabIsDisplayed();

		reportLog("7.4: Applications tab is available for Study in Pr#2");
		adminstrationStudyGeneralPage.verifyApplicationTabIsDisplayed();

		reportLog("7.5: Identity tab is available for Study in Pr#3");
		adminstrationStudyGeneralPage.verifyIdentityTabIsDisplayed();

		reportLog("8.1: Open General tab in Edit mode");
		adminstrationStudiesPage.verifyGeneralTabIsDisplayed();
		adminstrationStudiesPage.editStudy();
		adminstrationStudiesPage.verifyGeneralTabIsInEditMode();

		reportLog("8.2: Select observer checkbox in product type section");
		adminstrationStudyGeneralPage.selectObserverCheckbox();

		reportLog("8.3: Select save control");
		adminstrationStudyGeneralPage.clickOnSaveButton();

		reportLog("8.4: Observer item is displayed in Product Type section");
		adminstrationStudyGeneralPage.verifyProductTypeInputSection(Constants.productinput2);

		reportLog("9: unselect all selected check box from product type");
		adminstrationStudiesPage.editStudy();
		adminstrationStudyGeneralPage.unselectallSelectedCheckBox();
		adminstrationStudyGeneralPage.clickOnSaveIcon();

		reportLog(" Logout application");
		loginPage.logoutApplication();

		reportLog(" Verify user is logout");
		loginPage.verifyUserLogout();
	}
}
