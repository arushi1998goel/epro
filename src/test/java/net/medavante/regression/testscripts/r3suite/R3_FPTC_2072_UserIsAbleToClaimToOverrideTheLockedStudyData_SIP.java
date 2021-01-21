
/**
 *@author 
* @date 12/12/2019
* =========================================================================
*  Test Case Id: FP-TC-2072 || Test Case Name: Claim to override the locked Study data
	 * pre-qualification :  At least 1 User with a claim to Unlock Studies without a claim to Lock a Study exists.
	 * At least 1 User with a claim to override the locked Study data and with a claim to manage Studies exists.
	 * At least 1 active Locked Study with at least one Site exists.	
* ========================================================================= 
*/
package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.LoginPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationScaleActivationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCliniciansPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCountriesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCustomPage;
import net.medavante.portal.pages.administration.AdministrationStudiesFormsPage;
import net.medavante.portal.pages.administration.AdministrationStudiesGeneralPage;
import net.medavante.portal.pages.administration.AdministrationStudiesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesPeoplePage;
import net.medavante.portal.pages.administration.AdministrationStudiesSitesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSurveyPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSurveyTrackingPage;
import net.medavante.portal.pages.administration.AdministrationStudiesVisitsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_2072_UserIsAbleToClaimToOverrideTheLockedStudyData_SIP extends BaseTest

{
	private String studySite, studyfacility, sitePeople, people1Role, studyLanguage,rater,raterSite,textContent,site,email;

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_2072_UserIsAbleToClaimToOverrideTheLockedStudyData_SIP(String browser) {
		super(browser);

	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2072");
		studySite = properties.getProperty("StudySite2072");
		studyfacility = properties.getProperty("StudyFacility2072");
		sitePeople = properties.getProperty("StudySitePeople");
		people1Role = properties.getProperty("StudyPeopleRole");
		studyLanguage = properties.getProperty("StudyLanguage");
		rater=properties.getProperty("StudyRater2072");
		raterSite=properties.getProperty("StudyRaterSite2072");
		textContent=properties.getProperty("StudyTextcontent2072");
		site=properties.getProperty("StudySite2072_1");
		email=properties.getProperty("StudyEmail2072"); 
		
	}

	@Test(description = "FP-TC-2072 --User is able to Claim to override the locked Study data")
	public void R3_FPTC_2072_ClaimToOverrideTheLockedStudyData() {

		reportLog("2.0 Login to Portal as the user of Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.0.1 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1.1 Navigate to Administration");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.1.2 Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("2.1.2.1 Search " + studyName + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("2.2: General tab displayed for a Study from the PR#3");
		adminstrationStudiesPage.verifyAdministrationStudiesGeneralPageIsOpen(studyName);
		
		
		/*************************************-----------------------------********
		 ***************************************------------------------------------- */
		 
		reportLog("2.3: The Lock icon and a text \"Study Configuration is locked\"");
		reportLog("This Functionality Is n't present in UI");
		
		/*************************************-----------------------------********
		 ***************************************------------------------------------- */
		
		reportLog("2.4: The Lock icon displayed in the Study row on the Study list.");
		adminstrationStudyGeneralPage=adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabGeneral,AdministrationStudiesGeneralPage.class);	
		adminstrationStudyGeneralPage.verifyStudyLockIsdisplayed(studyName);
		

		reportLog("2.5 Navigating to language SubTab");
		adminstrationStudyGeneralPage.navigateToSubTabs(Constants.SubTabsLanguages, AdministrationStudiesPage.class);

		reportLog("2.5.0 Verifying Languages Tab Edit Button Functionality");
		adminCountriesPage = adminstrationStudyGeneralPage.editAdministrationSubTabs(
				AdministrationStudiesCountriesPage.class, Constants.editIcon, Constants.isDisabled);

		reportLog("2.5.1 Navigating to Country SubTab");
		adminCountriesPage.navigateToSubTabs(Constants.SubTabCountry, AdministrationStudiesCountriesPage.class);

		reportLog("2.5.1.0 Verifying Countries Tab Edit Button Functionality");
		adminstrationFormsPage = adminCountriesPage.editAdministrationSubTabs(AdministrationStudiesFormsPage.class,
				Constants.addIcon, Constants.isDisabled);

		reportLog("2.5.2 Navigating To Forms SubTab");
		adminstrationFormsPage.navigateToSubTabs(Constants.SubTabForms, AdministrationStudiesFormsPage.class);

		reportLog("2.5.2.1 Verifying Forms subTab Edit Functionality");
		adminVisitsPage = adminstrationFormsPage.editAdministrationSubTabs(AdministrationStudiesVisitsPage.class,
				Constants.editIcon, Constants.isDisabled);

		reportLog("2.5.6 Navigating To Visits SubTab");
		adminVisitsPage.navigateToSubTabs(Constants.SubTabVisits, AdministrationStudiesFormsPage.class);

		reportLog("2.5.6.1 Verifying Visits subTab Edit Functionality");
		adminstrationStudiesCustomPage = adminVisitsPage.editAdministrationSubTabs(
				AdministrationStudiesCustomPage.class, Constants.addIcon, Constants.isDisabled);

		reportLog("2.5.7 Navigating To Custom SubTab");
		adminstrationStudiesCustomPage.navigateToSubTabs(Constants.SubTabCustom, AdministrationStudiesCustomPage.class);

		reportLog("2.5.7.1 Verifying Custom subTab Edit Functionality");
		adminStudiesSurveyPage = adminstrationFormsPage.editAdministrationSubTabs(AdministrationStudiesSurveyPage.class,
				Constants.SaveIcon, Constants.isDisabled);

		reportLog("2.5.8 Navigating To Survey SubTab");
		adminStudiesSurveyPage.navigateToSubTabs(Constants.SubTabSurvey, AdministrationStudiesSurveyPage.class);

		reportLog("2.5.8.1 Verifying Survey SubTab Edit functionality");
		adminPeoplePage = adminStudiesSurveyPage.editAdministrationSubTabs(AdministrationStudiesPeoplePage.class,
				Constants.surveyAddButton, Constants.isDisabled);

		reportLog("3.0 Navigating to People SubTab");
		adminPeoplePage.navigateToSubTabs(Constants.Peopletext, AdministrationStudiesPeoplePage.class);

		reportLog("3.1 Adding Person");
		adminPeoplePage.addPeople(Constants.ATAssignedRater_18, Constants.PeopleRole);

		reportLog("3.2 Manage and Remove Persons");
		administrationStudiesCliniciansPage = adminPeoplePage.removePerson(AdministrationStudiesCliniciansPage.class,
				Constants.ATAssignedRater_18,Constants.Actionaccept);

		reportLog("4.0 Navigating to clinicians SubTab");
		administrationStudiesCliniciansPage.navigateToSubTabs(Constants.SubTabClinicians,
				AdministrationStudiesCliniciansPage.class);

		reportLog("4.1  Adding Clinician");
		administrationStudiesCliniciansPage.addClinician(Constants.clinician14Name, Constants.clinicianRole);

		reportLog("4.1  Manage and Remove Persons");
		adminSitesPage = administrationStudiesCliniciansPage.removeClinician(AdministrationStudiesSitesPage.class,Constants.clinician14Name, Constants.Actionaccept);
				
		reportLog("5.0 Navigating to Sites SubTab");
		adminSitesPage.navigateToSubTabs(Constants.SubTabSites, AdministrationStudiesSitesPage.class);

		reportLog("5.1 Adding Sites, Facilities, People, Languages, conduct Closeout activities");
		adminSitesPage.AddNewSite(studySite, studyfacility, sitePeople, people1Role, studyLanguage);
		
		reportLog("5.2  Manage and Removing Sites, Facilities, People, Languages, conduct Closeout activities");
		adminScaleActivationPage=adminSitesPage.removeSite(AdministrationScaleActivationPage.class,studySite,Constants.Actionaccept,sitePeople);
		
		reportLog("6.0 Navigating to Scale Activation SubTab");
		adminScaleActivationPage.navigateToSubTabs(Constants.SubTabScaleactivation, AdministrationScaleActivationPage.class);
		
		reportLog("6.1 User is able to manage Scale Activation");
		adminScaleActivationPage.deactivateRater(rater, raterSite);
		adminSurveyTrackingPage=adminScaleActivationPage.activateRater(AdministrationStudiesSurveyTrackingPage.class,rater, raterSite);
		
		reportLog("7.0 Navigating to Survey Tracking SubTabs");
		adminSurveyTrackingPage.navigateToSubTabs(Constants.SubTabSurveyTracking, AdministrationStudiesSurveyTrackingPage.class);
		
		reportLog("7.1 Adding Email");
		adminSurveyTrackingPage.addEmail(site,email, textContent,Constants.Actionaccept);
		
		reportLog("7.2 Sending Survey");
		adminSurveyTrackingPage.sendingSurvey(Constants.Actionaccept);
		
		reportLog("7.3 Export To SCV");
		adminstrationStudyGeneralPage=adminSurveyTrackingPage.exportToCsv(AdministrationStudiesGeneralPage.class);
		
		reportLog("LogOut Application");
		loginPage.logoutApplication();
		
		reportLog("Verify LogOut ");
		loginPage.verifyUserLogout();
		
		reportLog("8.0 : Log in to Portal with PR#1");
		dashBoardPage=loginPage.loginInApplication(AT_PRODAdminViewOnly, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("8.0 Unlock the System Studies");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudyGeneralPage.navigateToSubTabs(Constants.SubTabGeneral,AdministrationStudiesGeneralPage.class);	
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationOrganizationPage=adminstrationStudyGeneralPage.lockunlockStudy(AdministrationOrganizationPage.class, Constants.Actionaccept,Constants.unlock);
		
		reportLog("8.1: System unlocks the Study configuration. Lock icon disappears from the Study row in the Study list.");
		adminstrationStudyGeneralPage.refreshPage();
		adminstrationStudyGeneralPage.verifystudyLockIsNOtdisplayed(studyName);
		
		reportLog("LogOut Application");
		loginPage.logoutApplication();
		
		reportLog("Verify LogOut ");
		loginPage.verifyUserLogout();
		
		reportLog("9.1 Login to Portal as the user of Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("9.1.1 Navigate to Studies");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudyGeneralPage.navigateToSubTabs(Constants.SubTabGeneral,AdministrationStudiesGeneralPage.class);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
        
		reportLog("9.1.2 Search " + studyName + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("9.2 Navigating to language SubTab");
		adminstrationStudyGeneralPage.navigateToSubTabs(Constants.SubTabsLanguages, AdministrationStudiesPage.class);

		reportLog("9.3 Verifying Languages Tab Edit Button Functionality");
		adminCountriesPage = adminstrationStudyGeneralPage.editAdministrationSubTabs(
				AdministrationStudiesCountriesPage.class, Constants.editIcon, Constants.isEnabled);

		reportLog("9.4 Navigating to Country SubTab");
		adminCountriesPage.navigateToSubTabs(Constants.SubTabCountry, AdministrationStudiesCountriesPage.class);

		reportLog("9.5 Verifying Countries Tab Edit Button Functionality");
		adminstrationFormsPage = adminCountriesPage.editAdministrationSubTabs(AdministrationStudiesFormsPage.class,
				Constants.addIcon, Constants.isEnabled);

		reportLog("9.6 Navigating To Forms SubTab");
		adminstrationFormsPage.navigateToSubTabs(Constants.SubTabForms, AdministrationStudiesFormsPage.class);

		reportLog("9.7 Verifying Forms subTab Edit Functionality");
		adminVisitsPage = adminstrationFormsPage.editAdministrationSubTabs(AdministrationStudiesVisitsPage.class,
				Constants.editIcon, Constants.isEnabled);

		reportLog("9.8 Navigating To Visits SubTab");
		adminVisitsPage.navigateToSubTabs(Constants.SubTabVisits, AdministrationStudiesFormsPage.class);

		reportLog("9.9 Verifying Visits subTab Edit Functionality");
		adminstrationStudiesCustomPage = adminVisitsPage.editAdministrationSubTabs(
				AdministrationStudiesCustomPage.class, Constants.addIcon, Constants.isEnabled);

		reportLog("9.10 Navigating To Custom SubTab");
		adminstrationStudiesCustomPage.navigateToSubTabs(Constants.SubTabCustom, AdministrationStudiesCustomPage.class);

		reportLog("9.11 Verifying Custom subTab Edit Functionality");
		adminStudiesSurveyPage = adminstrationFormsPage.editAdministrationSubTabs(AdministrationStudiesSurveyPage.class,
				Constants.SaveIcon, Constants.isEnabled);

		reportLog("9.12 Navigating To Survey SubTab");
		adminStudiesSurveyPage.navigateToSubTabs(Constants.SubTabSurvey, AdministrationStudiesSurveyPage.class);

		reportLog("9.13 Verifying Survey SubTab Edit functionality");
		adminstrationStudiesPage = adminStudiesSurveyPage.editAdministrationSubTabs(AdministrationStudiesPage.class,
				Constants.surveyAddButton, Constants.isEnabled);
		
		reportLog("Unlocked study is locked again.");
		adminstrationStudyGeneralPage=adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabGeneral,AdministrationStudiesGeneralPage.class);		
		loginPage=adminstrationStudyGeneralPage.lockunlockStudy(LoginPage.class, Constants.Actionaccept,"Lock");
		loginPage.refreshPage();
		
		reportLog("LogOut Application");
		loginPage.logoutApplication();
		
		reportLog("Verify LogOut ");
		loginPage.verifyUserLogout();
	}
}
