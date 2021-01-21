package net.medavante.regression.testscripts.r4suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_1585_AddSiteStaff_AddAnother_MAP extends BaseTest {

	private String studyName1, studyName2, email1, email2, firstName1, lastName, firstName2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_1585_AddSiteStaff_AddAnother_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName1 = properties.getProperty("AutomationStudyName");
		studyName2 = properties.getProperty("Study1089");
		email1 = "test1" + generateRandomString(3) + "@test.com";
		email2 = "test2" + generateRandomString(3) + "@test.com";
		firstName1 = "Auto" + generateRandomString(3);
		firstName2 = "Auto-" + generateRandomString(3);
		lastName = "Mation" + generateRandomString(3);
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1585 Add Site Staff - add another
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1585 Add Site Staff - add another", groups = { "R4" })
	public void R4_FPTC_1585_AddSiteStaff_AddAnother() throws Exception {

		reportLog("2: Log in to the Ma-Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager, SuperAdminPW);

		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3: Navigate to Navigation -> University -> Study_1 and Site Pr.#1");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.verifyOptionToSelectStudyAndSiteIsDisplayed();
		ratersDetailsPage.selectStudy(studyName1, Constants.ATAssignedRater_10);

		reportLog("3.1: University page is displayed");
		ratersDetailsPage.verifyClinicians_RatersPage();

		reportLog("4: Select Add action to create new Site Staff");
		ratersDetailsPage.selectAddSiteControl();

		reportLog("4.1: Add Site Staff modal window is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindow(Constants.siteRaterWindowTitle);

		reportLog("5: Fill in all required fields: - All required fields aren't highlighted ");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(email1, firstName1, lastName);
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.verifyRequiredFieldsOnAddSitePopUpAreNotHighLighted(Constants.HighlightedBackgroundColor_3);
		ratersDetailsPage.clickOnPopUpTitle(Constants.siteRaterWindowTitle);

		reportLog("5.1: - Add another action is available - Add control is enabled");
		ratersDetailsPage.verifyAddControlIsEnabledOnAddSitePopUp();

		reportLog("5.2: - Cancel control is enabled");
		ratersDetailsPage.verifyCancelControlIsEnabledOnAddSitePopUp();

		reportLog("6: Select Add another action");
		ratersDetailsPage.selectAddAnotherCheckboxOnAddSitePopUp();

		reportLog("6.1: Add another action marked as selected");
		ratersDetailsPage.addAnotherCheckboxIsSelected();

		reportLog("7: Select an action to save changes");
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();

		reportLog("7.1: - Add Site Staff Modal window is opened");
		ratersDetailsPage.verifyAddSiteRaterWindow(Constants.siteRaterWindowTitle);

		reportLog("7.2: - All previous data are cleared");
		ratersDetailsPage.verifyRequiredFieldsOnAddSitePopUpAreHighLighted(Constants.HighlightedBackgroundColor);

		reportLog("8: - New Site Staff from Step 7 is created");
		ratersDetailsPage.clickOnCancelControlOnAddSitePopUp();
		ratersDetailsPage.clickOnYesCloseFormControlOnConfirmationPopUp();
		ratersDetailsPage.verifyNewlyAddedSiteRater(firstName1);

		/* Repeat Steps 4-7 for Study_2 */

		reportLog("9: Navigate to Navigation -> University -> Study_2");
		ratersDetailsPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class, Constants.NavigateText,
				Constants.SubTabUniversity);
		ratersDetailsPage.verifyOptionToSelectStudyAndSiteIsDisplayed();

		ratersDetailsPage.selectStudy(studyName2, Constants.ATAssignedRater_10);

		reportLog("9.1: University page is displayed");
		ratersDetailsPage.verifyClinicians_RatersPage();

		reportLog("9.2: Select Add action to create new Site Rater");
		ratersDetailsPage.selectAddSiteControl();

		reportLog("9.3: Add Site Staff modal window is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindow(Constants.siteRaterWindowTitle);

		reportLog("10: Fill in all required fields: - All required fields aren't highlighted ");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(email2, firstName2, lastName);

		reportLog("10.1: Facilities field is disabled and has preselected Facility_1");
		ratersDetailsPage.verifyFacilityFieldDisabled(Constants.FacilityName);

		reportLog("10.2: - Add another action is available - Add control is enabled");
		ratersDetailsPage.verifyAddControlIsEnabledOnAddSitePopUp();

		reportLog("10.3: - Cancel control is enabled");
		ratersDetailsPage.verifyCancelControlIsEnabledOnAddSitePopUp();

		reportLog("11: Select Add another action");
		ratersDetailsPage.selectAddAnotherCheckboxOnAddSitePopUp();

		reportLog("11.1: Add another action marked as selected");
		ratersDetailsPage.addAnotherCheckboxIsSelected();

		reportLog("12: Select an action to save changes");
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();

		reportLog("12.1: - Add Site Staff Modal window is opened");
		ratersDetailsPage.verifyAddSiteRaterWindow(Constants.siteRaterWindowTitle);

		reportLog("12.2: - All previous data are cleared");
		ratersDetailsPage.verifyRequiredFieldsOnAddSitePopUpAreHighLighted(Constants.HighlightedBackgroundColor);

		reportLog("12.3: - New Site Staff from Step 6 is created");
		ratersDetailsPage.clickOnCancelControlOnAddSitePopUp();
		ratersDetailsPage.clickOnYesCloseFormControlOnConfirmationPopUp();

		ratersDetailsPage.verifyNewlyAddedSiteRater(firstName2);

		reportLog("13: Logout application");
		loginPage.logoutApplication();

		reportLog("13.1: Verify user is logout");
		loginPage.verifyUserLogout();

	}
}