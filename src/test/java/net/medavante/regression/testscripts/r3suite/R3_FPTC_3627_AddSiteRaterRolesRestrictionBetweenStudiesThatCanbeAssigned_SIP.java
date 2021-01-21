package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3627_AddSiteRaterRolesRestrictionBetweenStudiesThatCanbeAssigned_SIP extends BaseTest {

	private String study, studyNew;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3627_AddSiteRaterRolesRestrictionBetweenStudiesThatCanbeAssigned_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void GetTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("PreQualificationStudy");
		studyNew = properties.getProperty("AutomationStudyName");
	}

	@Test(description = "FP-TC-3627 --Add Site Rater - Roles restriction between Studies that can be assigned")
	public void R3_FPTC_3627_AddSiteRaterRolesRestrictionBetweenStudiesThatCanbeAssigned() {

		reportLog("1.0:  Log in to the Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager, AT_Password);
		dashBoardPage.clickToCloseFailedTrainingInfoAlert();

		reportLog("1.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();


		reportLog("2.1: Navigate to Clinician/Raters dashboard");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.clinician_ratersText);

		reportLog("3: Select study");
		ratersDetailsPage.selectStudy(studyNew, Constants.VTAssignedRater_21);

		reportLog("4: Clinicians/Raters Listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

		reportLog("5.1: Verify Site Rater From Pr.#4");
		ratersDetailsPage.applyFilterUnderUnderTrackingTab(Constants.Completed_Status_Rater1,
				Constants.SiteRater_Status);

		reportLog("5.2: Verify Actions control is displayed");
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		ratersDetailsPage.verifyActionControlDropDownButton();

		reportLog("6. Verify Assign to Facility / Study modal window is displayed");
		ratersDetailsPage.clickOnCompletedSiteRaterLink();
		ratersDetailsPage.verifyCompletedSiteRaterPage(Constants.Completed_Status_Rater1);
		ratersDetailsPage.clickOnAssignToFacilityOrStudyButton();
		ratersDetailsPage.verifyAssignToFacilityOrStudyPopUp();

		reportLog("7.1: Select corresponding Facility (if exists)");
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Assign_SiteRater_PopUp);
		//ratersDetailsPage.clickOnFacilityField();

		reportLog("7.2: Verify both Studies from Pr.#1.1 and Pr.#1.2 is visible and available to be selected.");
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(study, Constants.Assign_SiteRater_PopUp);
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(studyNew, Constants.Assign_SiteRater_PopUp);

		reportLog("8.1: Select Study and site on Assign To Facility/study pop up.");
		ratersDetailsPage.selectStudyFromStudyDropDownOptions(study);
		ratersDetailsPage.searchAndSelectSite(Constants.VTAssignedRater_21, Constants.Assign_SiteRater_PopUp);

		reportLog("8.2: Click on save button on Assign To Facility/study pop up.");
		ratersDetailsPage.clickOnSaveButtonOnAssignToFacilityOrStudyPopUp();

		reportLog("8.3: Verify Assign To Facility or study pop up closed after saved.");
		ratersDetailsPage.verifyAssignToFacilityOrStudyPopUpClosed();

		reportLog("9.1: Logout application");
		loginPage.logoutApplication();

		reportLog("9.2: Verify user is Logout from application");
		loginPage.verifyUserLogout();

		reportLog("9.3:  Log in to the Portal as a User Pr.#2" );
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator,AT_Password);
		
		reportLog("9.4: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("10.1: Navigate to Clinician/Raters dashboard");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
					Constants.NavigateText, Constants.clinician_ratersText);

		reportLog("10.2: Select study");
		ratersDetailsPage.selectStudy(study, Constants.VTAssignedRater_21);
		
		reportLog("11: Clinicians/Raters Listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

		reportLog("12.1: Verify Site Rater From Pr.#4");
		ratersDetailsPage.applyFilterUnderUnderTrackingTab(Constants.Completed_Status_Rater1,
				Constants.SiteRater_Status);

		reportLog("12.2: Verify Actions control is displayed");
		ratersDetailsPage.clickOnCheckBoxOnSiteRaterRow();
		ratersDetailsPage.verifyActionControlDropDownButton();

		reportLog("13. Verify Assign to Facility / Study modal window is displayed");
		ratersDetailsPage.clickOnCompletedSiteRaterLink();
		ratersDetailsPage.verifyCompletedSiteRaterPage(Constants.Completed_Status_Rater1);
		ratersDetailsPage.clickOnAssignToFacilityOrStudyButton();
		ratersDetailsPage.verifyAssignToFacilityOrStudyPopUp();
		
		reportLog("14.1: Select corresponding Facility (if exists)");
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Assign_SiteRater_PopUp);

		reportLog("14.2: Verify both Studies from Pr.#1.1 and Pr.#1.2 is visible and available to be selected.");
		ratersDetailsPage.verifyStudiesListDisplayedAndAvailableToselected(studyNew, Constants.Assign_SiteRater_PopUp);
		ratersDetailsPage.verifyStudiesListDisplayedAndNotAvailableToselected(study, Constants.Assign_SiteRater_PopUp);

		reportLog("15.1: Select Study and site on Assign To Facility/study pop up.");
		ratersDetailsPage.selectStudyFromStudyDropDownOptions(studyNew);
		ratersDetailsPage.searchAndSelectSite(Constants.VTAssignedRater_21, Constants.Assign_SiteRater_PopUp);

		reportLog("15.2: Click on save button on Assign To Facility/study pop up.");
		ratersDetailsPage.clickOnSaveButtonOnAssignToFacilityOrStudyPopUp();

		reportLog("15.3: Verify Assign To Facility or study pop up closed after saved.");
		ratersDetailsPage.verifyAssignToFacilityOrStudyPopUpClosed();

		reportLog("16.1: Logout application");
		loginPage.logoutApplication();

		reportLog("16.2: Verify user is Logout from application");
		loginPage.verifyUserLogout();
	}
}
