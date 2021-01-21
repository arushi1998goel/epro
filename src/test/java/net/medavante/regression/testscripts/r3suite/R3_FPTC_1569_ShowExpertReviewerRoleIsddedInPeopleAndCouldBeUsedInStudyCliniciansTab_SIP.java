/**
 * author: siddharth
 * created on:12/12/2019
 * Objective:-
 * Show that Expert Reviewer role was added to People 
 * Show that Expert Reviewers could be added using Study Clinicians tab
 */
package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationPeoplePage;
import net.medavante.portal.pages.administration.AdministrationStudiesPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1569_ShowExpertReviewerRoleIsddedInPeopleAndCouldBeUsedInStudyCliniciansTab_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1569_ShowExpertReviewerRoleIsddedInPeopleAndCouldBeUsedInStudyCliniciansTab_SIP(String browser) {
		super(browser);
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void getData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("");
		personName = prop.getProperty("Person15569");
	}

	@Test(description = "ShowExpertReviewerRoleIsddedInPeopleAndCouldBeUsedInStudyCliniciansTab", groups = { "R3" })
	public void R3_FPTC_1569_ShowExpertReviewerRoleIsddedInPeopleAndCouldBeUsedInStudyCliniciansTab() {

		reportLog("1.1	Log in to Portal as user Pr #1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog(" 1.2 User successfully logged in ");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog(" 2.1	Select navigate tab  to  ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.2: Navigate to people tab ");
		adminstrationPeoplePage = adminstrationOrganizationPage.navigateToTab(Constants.Peopletext,
				AdministrationPeoplePage.class);

		reportLog("2.3 Find and select the person");
		adminstrationPeoplePage.searchPerson(personName);

		reportLog("2.4 The following Person types are displayed:- Investigator ,MedAvante Clinician ,Expert Reviewer");
		adminstrationPeoplePage.verifyPersonTypePresent(Constants.personTypeInvestigator,
				Constants.personTypeMedAvanteClinicians, Constants.personTypeExpertReviewer);

		reportLog("3.1 Select action to activate edit mode on General tab");
		adminstrationPeoplePage.clickToEditTheGeneralTab();

		reportLog("3.2 Edit mode is activated");
		adminstrationPeoplePage.verifyEditModeIsActivated(Constants.isEnabled);

		reportLog("4.1 Set Person Type to 'Expert Reviewer' ");
		adminstrationPeoplePage.selectPersonType(Constants.personTypeExpertReviewer);

		reportLog("4.2 Click On save icon ");
		adminstrationPeoplePage.clickOnSaveIcon();

		reportLog("4.3  Person Type for User Pr.#3 is set as 'Expert Reviewer' Changes are saved");
		adminstrationPeoplePage.verifyPersonTypeIsSetTo(Constants.personTypeExpertReviewer);

		reportLog("5.1 Navigate to Study Pr.#1 -> Clinician -> ");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToTab(Constants.Studies,
				AdministrationStudiesPage.class);

		reportLog("5.2 Select action to Add Clinician -> ");
		administrationStudiesCliniciansPage = adminstrationStudiesPage.navigateToStudyCliniciansTab();

		reportLog("5.3 Check if User Pr.#3 (Expert Reviewer) is displayed in the drop-down list");
		administrationStudiesCliniciansPage.clickToAddClinicianIcon()
				.verifyClinicianIsDisplyedInDropDown(personName, Constants.isEnabled)
				.clickToCancelClinicianSuggestion();

		reportLog("6.1 Navigate back to Administration -> People ");
		adminstrationPeoplePage = adminstrationOrganizationPage.navigateToTab(Constants.Peopletext,
				AdministrationPeoplePage.class);

		reportLog("6.2 Find and select user Pr.#3");
		adminstrationPeoplePage.searchPerson(personName);

		reportLog("6.3 People's general tab with corresponding information is displayed");
		adminstrationPeoplePage.verifyPersonTypePresent(Constants.personTypeInvestigator,
				Constants.personTypeMedAvanteClinicians, Constants.personTypeExpertReviewer);

		reportLog("7.1 Select action to activate edit mode on General tab");
		adminstrationPeoplePage.clickToEditTheGeneralTab();

		reportLog("7.2 Edit mode is activated");
		adminstrationPeoplePage.verifyEditModeIsActivated(Constants.isEnabled);

		reportLog("8.1 Unset Person Type from 'Expert Reviewer'");
		adminstrationPeoplePage.deselectPersonType(Constants.personTypeExpertReviewer);

		reportLog("8.2 Save changes ");
		adminstrationPeoplePage.clickOnSaveIcon();

		reportLog("8.3 Person Type for User Pr.#3 isn't set as 'Expert Reviewer'");
		adminstrationPeoplePage.verifyPersonTypeIsSetTo(Constants.personTypeExpertReviewer);

		reportLog("9.1 Navigate to Study Pr.#1 ");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToTab(Constants.Studies,
				AdministrationStudiesPage.class);

		reportLog("9.2 Navigate to Clinician tab  ");
		administrationStudiesCliniciansPage = adminstrationStudiesPage.navigateToStudyCliniciansTab();

		reportLog("9.3 Select action to Add Clinician ");
		administrationStudiesCliniciansPage.clickToAddClinicianIcon();

		reportLog("9.4 Verify that User Pr.#3 isn't displayed in the drop-down list");
		administrationStudiesCliniciansPage.verifyClinicianIsDisplyedInDropDown(personName, Constants.isDisabled)
				.clickToCancelClinicianSuggestion();

		reportLog("10.1 Logout from the application");
		loginPage = loginPage.logoutApplication();

		reportLog("10.2 Verify user is successfully logout from the application");
		loginPage.verifyUserLogout();
	}

}
