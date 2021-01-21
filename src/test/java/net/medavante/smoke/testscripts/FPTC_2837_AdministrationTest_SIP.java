package net.medavante.smoke.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2837_AdministrationTest_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2837_AdministrationTest_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("TestDataFile");
		studyName = properties.getProperty("StudyExisit");
		orgName=properties.getProperty("OrganizationExisting");
	}
	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-2837--Verify Organization,People ,Study and Forms
	 * Library Pages
	 * =========================================================================
	 * 
	 */

	@Test(description = "FP-TC-2837--Verify Organization,People ,Study and Forms Library Pages  ", groups = { "smoke" })
	public void FPTC_2837_verifyAdminstrationOrganizationPeopleStudyAndFormsLibraryPages() throws Exception {

		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.1 Verify Home page is displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2. Go to CONFIGURE>>Study Setup>>ORGANIZATIONS");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3 Verify Organization page with Add Organization Icon");
		adminstrationOrganizationPage.verifyOrganizationPageWithAddOrgButton();

		reportLog("4. Select " + orgName + orgType + " from the drop down");
		adminstrationOrganizationPage.organizationSearch(orgName, orgType);

		reportLog("4.1 General,Address,People sub tabs are displayed");
		adminstrationOrganizationPage.verifyAllTabsPresent();

		reportLog("4.2 Click On General Tab");
		adminstrationOrganizationGeneralPage = adminstrationOrganizationPage.navigateToOrganizationGeneralTab();

		reportLog("4.2.1 Verify General Page Open");
		adminstrationOrganizationGeneralPage.verifyGeneralPageIsOpen(orgName);

		reportLog("4.3 Click On Address Tab");
		adminstrationOrganizationAddressPage = adminstrationOrganizationPage.navigateToOrganizationAddressesTab();

		reportLog("4.3.1 Verify Address Page Open");
		adminstrationOrganizationAddressPage.verifyAddressesPageIsOpen();

		reportLog("4.4 Click On People Tab");
		adminstrationOrganizationPeoplePage = adminstrationOrganizationPage.navigateToOrganizationPeopleTab();

		reportLog("4.4.1 Verify People Page Open");
		adminstrationOrganizationPeoplePage.verifyPeoplePageIsOpen();

		reportLog("5. Go to CONFIGURE>>Study Setup>>PEOPLE");
		adminstrationPeoplePage = adminstrationOrganizationPage.navigateToPeople();

		reportLog("6: Verify People page with Add People icon");
		adminstrationPeoplePage.verifyPeoplePageWithAddPeopleIcon();

		reportLog("7. Search Person");
		adminstrationPeoplePage.searchPerson(personName);

		reportLog("7.1 Verify all tabs are present Under People Tab");
		adminstrationPeoplePage.verifyAllTabsPresentUnderPeopleTab();

		reportLog("7.2 Click On General Tab");
		adminstrationPeopleGeneralPage = adminstrationPeoplePage.navigateToPeopleGeneralTab();

		reportLog("7.2.1 Verify General Page Open");
		adminstrationPeopleGeneralPage.verifyAdministrationPeopleGeneralPageIsOpen(personName);

		reportLog("7.3 Click On Contact Information Tab");
		adminstrationPeopleContactInformationPage = adminstrationPeoplePage.navigateToPeopleContactInforrmationTab();

		reportLog("7.3.1 Verify Contact Information Page Open");
		adminstrationPeopleContactInformationPage.verifyContactInformationPageIsOpen();

		reportLog("7.4 Click On Organization Tab");
		adminstrationPeopleOrganizationPage = adminstrationPeoplePage.navigateToPeopleOrganizationsTab();

		reportLog("7.4.1 Verify Organization Page Open");
		adminstrationPeopleOrganizationPage.verifyAdministrationPeopleOrganizationsPageIsOpen();

		reportLog("8. Go to CONFIGURE>>Study Setup>>STUDIES");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("9 Verify studies page with Add Study icon");
		adminstrationStudiesPage.verifyAdminstrationStudiesPageIsOpen();

		reportLog("10. Select a Study and Verify each of the tab");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("10.1 Verify General Page");
		adminstrationStudiesPage.verifyAdministrationStudiesGeneralPageIsOpen(studyName);

		reportLog("10.2 Click On Languages Tab");
		adminLanguagePage = adminstrationStudiesPage.navigateToStudyLanguageTab();

		reportLog("10.2.1 Languges Page Open");
		adminLanguagePage.verifyLangugesPage();

		reportLog("10.3 Click On Countries Tab");
		adminCountriesPage = adminstrationStudiesPage.navigateToStudyCountriesTab();

		reportLog("10.3.1 Countries Page Open");
		adminCountriesPage.verifyCountryPage();

		reportLog("10.4 Click On Forms Tab");
		adminstrationFormsPage = adminstrationStudiesPage.navigateToStudyFormsTab();

		reportLog("10.4.1 Forms Page Open");
		adminstrationFormsPage.verifyFormsPage();

		reportLog("10.5 Click On Visit Tab");
		adminVisitsPage = adminstrationStudiesPage.navigateToStudyVisitsTab();

		reportLog("10.5.1 Visit Page Open");
		adminVisitsPage.verifyVisitsPage();

		reportLog("10.6 Click On Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("10.6.1 Verify Custom Page");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("10.7 Click on Analytics Tab");
		adminstrationStudiesAnalytics = adminstrationStudiesPage.navigateToStudyAnalyticsTab();

		reportLog("10.7.1 Verify Analytics Page");
		adminstrationStudiesAnalytics.verifyAnalyticsPageIsDisplayed();

		reportLog("10.8 Click On People Tab ");
		adminPeoplePage = adminstrationStudiesPage.navigateToStudyPeopleTab();

		reportLog("10.8.1 Verify People Under Studies Page");
		adminPeoplePage.verifyAdministrationStudiesPeoplePage();

		reportLog("10.9 Click On Clinician Tab ");
		administrationStudiesCliniciansPage = adminstrationStudiesPage.navigateToStudyCliniciansTab();

		reportLog("10.9.1 Verify Clinician Page");
		administrationStudiesCliniciansPage.verifyAdministrationStudiesCliniciansPage();

		reportLog("10.10 Click On Sites Tab ");
		adminSitesPage = adminstrationStudiesPage.navigateToStudySitesTab();

		reportLog("10.10.1 Verify Sites Page");
		adminSitesPage.verifyAdministrationStudiesSitesPage();

		reportLog("10.11 Click On Qualification Tab ");
		adminStudQualificationPage = adminstrationStudiesPage.navigateToStudyQualificationsTab();

		reportLog("10.11.1 Verify Qualification Page");
		adminStudQualificationPage.verifyAdministrationStudiesScaleActivationPage();

		reportLog("10.12 Click On Prequalification Tab ");
		prequalificationPage = adminstrationStudiesPage.navigateToStudySurveyTrackerTab();

		reportLog("10.12.1 Verify Prequalification Page");
		prequalificationPage.verifyAdministrationStudiesSurveyTrackerPage();

		reportLog("10.13 Click On Survey Tab ");
		adminStudiesSurveyPage = adminstrationStudiesPage.navigateToStudySurveyTab();

		reportLog("10.13.1 Verify Survey Page");
		adminStudiesSurveyPage.verifyAdministrationStudiesSurveyPage();

		reportLog("10.14 Click On Schedule Tab ");
		adminStudiesSchedulePage = adminstrationStudiesPage.navigateToStudyScheduleTab();

		reportLog("10.14.1 Verify Schedule Page");
		adminStudiesSchedulePage.verifyAdministrationStudiesSchedulePage();
		
		reportLog("10.16 Click On Identity Tab ");
		adminStudiesIdentityPage = adminstrationStudiesPage.navigateToStudyIdentityTab();

		reportLog("10.16.1 Verify Identity Page");
		adminStudiesIdentityPage.verifyAdministrationStudiesIdentityPage();
		
		reportLog("10.17 Click On University Tab ");
		adminStudiesVirgilUniversityPage = adminstrationStudiesPage.navigateToStudyUniversityTab();

		reportLog("10.17.1 Verify University Page");
		adminStudiesVirgilUniversityPage.verifyAdministrationStudiesUniversityPage();	

		reportLog("11: Go to CONFIGURE>>Study Setup>>FORMS LIBRARY");
		adminstrationFormLibraryPage = adminstrationOrganizationPage.navigateFormsLibrary();

		reportLog("12. Verify Forms page with Add Form icon");
		adminstrationFormLibraryPage.verifyFormPageWithAddFormIconDisplay();

		reportLog("13. Select one Form and verify");
		adminstrationFormLibraryPage.clickOnSearchForm(formName);

		reportLog("13.1 Verify " + formName + " is selected and displayed");
		adminstrationFormLibraryPage.verifySelectedFormIsDisplayed(formName);

		reportLog("14. Logout application");
		loginPage.logoutApplication();

		reportLog("14.1 Verify User Logout");
		loginPage.verifyUserLogout();
	}
	
}
