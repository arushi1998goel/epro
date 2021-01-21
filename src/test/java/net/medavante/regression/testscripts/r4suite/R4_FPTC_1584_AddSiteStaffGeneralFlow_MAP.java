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

public class R4_FPTC_1584_AddSiteStaffGeneralFlow_MAP extends BaseTest {

	private String email1, email2, firstName, lastName, name, siteRaterFName = "SiteRater" + generateRandomString(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_1584_AddSiteStaffGeneralFlow_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		email1 = "test1" + generateRandomString(3) + "@test.com";
		email2 = "test2" + generateRandomString(3) + "@test.com";
		firstName = "Auto" + generateRandomString(3);
		lastName = "Mation" + generateRandomString(3);
	}

	/***
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1584 Test Case Name: Show that User with
	 * CanManageSiteRater claim is able to add Site Rater to the system and
	 * assign to the Study
	 * 
	 * 
	 *
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 *
	 */

	@Test(description = "FP-TC-1584 --Add site Staff_General flow", groups = { "R4" })
	public void R4_FPTC_1584_verifyAddSiteStaffGeneralFlow() throws Exception {

		reportLog("2.1:Log in to the MA-Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager, SuperAdminPW);

		reportLog("2.2: User is successfully Logged in ");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.1: Navigate to Study Navigator ->University -> Study, Site Pr.#1");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.verifyOptionToSelectStudyAndSiteIsDisplayed();
		ratersDetailsPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("3.2: User listing screen is displayed");
		ratersDetailsPage.verifyClinicians_RatersPage();

		reportLog("4.1: Select Add action to create new Site Rater");
		ratersDetailsPage.selectAddSiteControl();

		reportLog("4.2: A modal window for adding a new Site staff is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindow(Constants.siteRaterWindowTitle);

		reportLog("5.1: Leave all required fields empty and required field should be highlighted");
		ratersDetailsPage.verifyRequiredFieldsOnAddSitePopUpAreHighLighted(Constants.HighlightedBackgroundColor);

		reportLog("5.2: Leave all required fields empty and add control should be disabled");
		ratersDetailsPage.verifyAddButtonIsDisabledOnAddSitePopUp();

		reportLog("5.3: Leave all required fields empty and Cancel control should be enabled");
		ratersDetailsPage.verifyCancelControlIsEnabledOnAddSitePopUp();

		reportLog("6.1: Fill in all required fields");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(email1, siteRaterFName, lastName);
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_SiteRater_PopUp);
		
		reportLog("6.2: All required fields aren't highlighted");
		ratersDetailsPage.verifyRequiredFieldsOnAddSitePopUpAreNotHighLighted(Constants.HighlightedBackgroundColor_3);
		
		reportLog("6.3: Add control is enabled");
		ratersDetailsPage.verifyAddControlIsEnabledOnAddSitePopUp();

		reportLog("6.4: Cancel control is enabled");
		ratersDetailsPage.verifyCancelControlIsEnabledOnAddSitePopUp();

		reportLog("7.1: Select action to cancel changes");
		ratersDetailsPage.clickOnAddSiteRaterWindowTitle();
		ratersDetailsPage.clickOnCancelControlOnAddSitePopUp();

		reportLog("7.2: Confirm canceling action using confirmation pop-up");
		ratersDetailsPage.clickOnYesCloseFormControlOnConfirmationPopUp();

		reportLog("7.3: Modal window is closed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsNotDisplayed();

		reportLog("7.4: New Site Staff isn't created");
		ratersDetailsPage.verifySiteRaterIsNotCreated(siteRaterFName + " " + lastName);
		ratersDetailsPage.verifyRaterFilterIsCleared();

		reportLog("8.1: Select Add action to create new Site Staff");
		ratersDetailsPage.selectAddSiteControl();

		reportLog("8.2: A modal window for adding a new Site Staff is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsDisplayed();

		reportLog("9.1: Fill in all required fields");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(email1, siteRaterFName, lastName);
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_SiteRater_PopUp);
		
		reportLog("9.2: All required fields aren't highlighted");
		ratersDetailsPage.verifyRequiredFieldsOnAddSitePopUpAreNotHighLighted(Constants.HighlightedBackgroundColor_3);
		
		reportLog("9.3: Add control is enabled");
		ratersDetailsPage.verifyAddControlIsEnabledOnAddSitePopUp();

		reportLog("9.4: Cancel control is enabled");
		ratersDetailsPage.verifyCancelControlIsEnabledOnAddSitePopUp();

		reportLog("10.1: Select an action to close window");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(email1, siteRaterFName, lastName);
		ratersDetailsPage.clickOnTinyCrossButtonOnAddSiteRaterWindow();

		reportLog("10.2: Confirm closing action using confirmation pop-up");
		ratersDetailsPage.clickOnYesCloseFormControlOnConfirmationPopUp();

		reportLog("10.3: Modal window is closed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsNotDisplayed();

		reportLog("10.4: New Site Staff isn't created");
		ratersDetailsPage.verifySiteRaterIsNotCreated(siteRaterFName + " " + lastName);
		ratersDetailsPage.verifyRaterFilterIsCleared();

		reportLog("11.1: Select an add action to create new Site Staff");
		ratersDetailsPage.selectAddSiteControl();

		reportLog("11.2: A modal window for adding a new Site Staff is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsDisplayed();

		reportLog("12.1: Fill in all required fields");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(email1, siteRaterFName, lastName);
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_SiteRater_PopUp);
		
		reportLog("12.2: All required fields aren't highlighted");
		ratersDetailsPage.verifyRequiredFieldsOnAddSitePopUpAreNotHighLighted(Constants.HighlightedBackgroundColor_3);
		
		reportLog("12.3: Add control is enabled");
		ratersDetailsPage.verifyAddControlIsEnabledOnAddSitePopUp();

		reportLog("12.4: Cancel control is enabled");
		ratersDetailsPage.verifyCancelControlIsEnabledOnAddSitePopUp();

		reportLog("13.1: Select an action to save changes");
		ratersDetailsPage.clickOnAddSiteRaterWindowTitle();
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();

		reportLog("13.2: Modal window is closed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsNotDisplayed();

		reportLog("13.3: New Site Staff is created");
		ratersDetailsPage.selectAddSiteControl();
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(email2, firstName, lastName);
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectStudyFromStudyDropDownOptions(studyName);
		ratersDetailsPage.searchAndSelectSite(Constants.ATAssignedRater_10, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectUserRoleOnAddStaffPopUp();
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();
		ratersDetailsPage.verifyNewlyAddedSiteRater(firstName);

		reportLog("14.1: Navigate to View History of created Site Staff");
		name = ratersDetailsPage.navigateToViewHistoryOfCreatedSiteRater();
		
		reportLog("14.2: View History of created Site Staff is displayed");
		ratersDetailsPage.verifyViewHistoryOfCreatedSiteRaterDisplayed(name);

		reportLog(
				 "14.3: - Appropriate information is displayed (Add to Facility/ Add to Study/Add to Site if were added)");
		ratersDetailsPage.verifyApproiateInformation();
		ratersDetailsPage.selectSystemRoleAndRoleGroupColumns();
		
		reportLog("14.4:  Add to System record: Date , Performed by - User name Pr#2, Action - Add to System");
		ratersDetailsPage.addToSystemRecordInfo(Constants.User_Name, Constants.Add_To_System);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Add_To_System);
		
		reportLog("14.5: On-boarding Status Change record: Date, Performed by - System, Action - On-boarding Status Change, Status - Invited");
		ratersDetailsPage.addToSystemRecordInfo(Constants.Performed_By, Constants.Onboarding_Status);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Onboarding_Status);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.ActionStatus);

		reportLog("14.6: Add to Facility record: Date, Performed by - User name Pr#2, Action - Add to Facility, Facility - {facility name}");
		ratersDetailsPage.addToSystemRecordInfo(Constants.User_Name, Constants.Add_To_Facility);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Add_To_Facility);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.FacilityName);
		
		reportLog("14.7: Add to Study record: Date, Performed by - User name Pr#2, Action - Add to Study, Facility - {facility name}, Study - Study name Pr#1");
		ratersDetailsPage.addToSystemRecordInfo(Constants.User_Name, Constants.Add_To_Study);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Add_To_Study);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.FacilityName);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Study_Name);
		
		reportLog("14.8: Add to Site record: Date, Performed by - User name Pr#2, Action - Add to Site, Facility - {facility name}, Study - Study name Pr#1, Site - Site name Pr#1, System Role - System role name Pr#1, Role Group - Role group name Pr#1");
		ratersDetailsPage.addToSystemRecordInfo(Constants.User_Name, Constants.Add_To_Site);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Add_To_Site);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.FacilityName);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Study_Name);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Site_Name);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.System_Role);
		ratersDetailsPage.verifyAppropriateInfoDisplayed(Constants.Role_Group);
		
        reportLog("15: Logout application");
		loginPage.logoutApplication();

		reportLog("15.1: Verify user is logout");
		loginPage.verifyUserLogout();
	
	}

}
