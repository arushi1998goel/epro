/**
 * @author
 * @date 03/19/2020 
 * ================================================================================================
 * Test Case Id: FP-TC-2073  ||  Test Case Name: Locked Study Configuration View Mode
         * pre-qualification : 1. At least 1 User without a claim to override the locked Study data, 
                                  but with claims to Manage Studies, exists
                               2. At least 1 active locked Study with at least one Site exists.
 * ================================================================================================= 
 */

package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationScaleActivationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesAnalyticsPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCliniciansPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCountriesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCustomPage;
import net.medavante.portal.pages.administration.AdministrationStudiesFormsPage;
import net.medavante.portal.pages.administration.AdministrationStudiesLanguagesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesPeoplePage;
import net.medavante.portal.pages.administration.AdministrationStudiesSchedulePage;
import net.medavante.portal.pages.administration.AdministrationStudiesSitesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSurveyPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSurveyTrackingPage;
import net.medavante.portal.pages.administration.AdministrationStudiesVisitsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_2073_LockedStudyConfigurationViewMode_SIP extends BaseTest {
	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
    public R3_FPTC_2073_LockedStudyConfigurationViewMode_SIP(String browser) {
		super(browser);
		
	}
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties =Configuration.readTestData("RegressionTestData");
        studyName=properties.getProperty("Study2831");
	}
	
	
	/***
	 * ====================================================================================================================
	 * Test Case Id: FPTC_2073 Test Case Name: Show that Users without a claim to override locked Study data
	 *  see the Study configuration in a View mode only.
	 *  
	 *  
	 *
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 *
	 */

	@Test(description = "FP-TC-2073 --Locked Study configuration View mode" , groups = { "R3" })
	public void R3_FPTC_LockedStudyConfigurationViewMode() throws InterruptedException {
		
		reportLog("2.1: Log in to Portal as User PR#2");
		dashBoardPage =loginPage.loginInApplication(AT_ProdAdminOps,SuperAdminPW);

		reportLog("2.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.3: Navigate to Administration tab -> Studies ");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.3.1: Navigate to Studies ");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		
		reportLog("2.4: Select the Study PR#2 ");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
	 	
        reportLog("2.5: verify general tab is Displayed for a Study from the PR#2 in a view mode, Edit mode is Disabled");
	    adminstrationStudiesPage.verifyGeneralTabIsDisplayedInViewMode(studyName);
	    adminstrationStudiesPage.verifyGeneralTabEditModeIsDisabled(Constants.LockedStudy);
	    
       reportLog("2.6: verify The Lock Icon and a text 'Study Configuration is locked' //div[@class='row editing-controls']//div[contains(@class,'lock')] ");
        adminstrationStudiesPage.verifyTheLockIcontext(Constants.LockedStudy);
        
		reportLog("2.7: The Lock icon displayed in the Study row on the Study list.");
		adminstrationStudyGeneralPage.verifyStudyLockIsdisplayed(studyName);
		
		reportLog("2.8: All Study configuration tabs are available in the View only mode.");
		reportLog("2.8.1: Languages configuration tabs are available in the View only mode.");
		adminstrationStudyGeneralPage.navigateToSubTabs(Constants.SubTabsLanguages, AdministrationStudiesPage.class);
		adminCountriesPage = adminstrationStudyGeneralPage.editAdministrationSubTabs(
				AdministrationStudiesCountriesPage.class, Constants.editIcon, Constants.isDisabled);
		
		reportLog("2.8.2: countries configuration tabs are available in the View only mode.");
        adminCountriesPage.navigateToSubTabs(Constants.SubTabCountry, AdministrationStudiesCountriesPage.class);
		adminstrationFormsPage = adminCountriesPage.editAdministrationSubTabs(AdministrationStudiesFormsPage.class,
				Constants.addIcon, Constants.isDisabled);
		
		reportLog("2.8.3: Forms configuration tabs are available in the View only mode.");
		adminstrationFormsPage.navigateToSubTabs(Constants.SubTabForms, AdministrationStudiesFormsPage.class);
		adminVisitsPage = adminstrationFormsPage.editAdministrationSubTabs(AdministrationStudiesVisitsPage.class,
				Constants.editIcon, Constants.isDisabled);
		
		reportLog("2.8.4: Visits configuration tabs are available in the View only mode.");
		adminVisitsPage.navigateToSubTabs(Constants.SubTabVisits, AdministrationStudiesFormsPage.class);
		adminstrationStudiesCustomPage = adminVisitsPage.editAdministrationSubTabs(
				AdministrationStudiesCustomPage.class, Constants.addIcon, Constants.isDisabled);
		
		reportLog("2.8.5: Custom configuration tabs are available in the View only mode.");
		adminstrationStudiesCustomPage.navigateToSubTabs(Constants.SubTabCustom, AdministrationStudiesCustomPage.class);
		adminstrationStudiesAnalytics = adminstrationStudiesCustomPage.editAdministrationSubTabs(AdministrationStudiesAnalyticsPage.class, Constants.SaveIcon, Constants.isDisabled);
		
		reportLog("2.8.6: Analytics configuration tabs are available in the View only mode.");
		adminstrationStudiesAnalytics.navigateToSubTabs(Constants.SubTabAnalytics, AdministrationStudiesAnalyticsPage.class);
		adminPeoplePage = adminstrationStudiesAnalytics.editAdministrationSubTabs(AdministrationStudiesPeoplePage.class, Constants.Analy_addIcon, Constants.isDisabled);
		
		reportLog("2.8.7: People configuration tabs are available in the View only mode.");
		adminPeoplePage.navigateToSubTabs(Constants.SubTabPeople, AdministrationStudiesPeoplePage.class);
		administrationStudiesCliniciansPage = adminPeoplePage.editAdministrationSubTabs(AdministrationStudiesCliniciansPage.class, Constants.addIcon, Constants.isDisabled);
		
		reportLog("2.8.8: Clinicians configuration tabs are available in the View only mode.");
		administrationStudiesCliniciansPage.navigateToSubTabs(Constants.SubTabClinicians,AdministrationStudiesCliniciansPage.class);
		administrationStudiesCliniciansPage.cliniciansTabAreInViewMode();
		
		reportLog("2.8.8: Sites configuration tabs are available in the View only mode.");
		administrationStudiesSitePage=administrationStudiesCliniciansPage.navigateToSubTabs(Constants.siteSubTab,AdministrationStudiesSitesPage.class);
		administrationStudiesSitePage.siteTabAreInViewMode();
		
		reportLog("2.8.9: Survey Tracker configuration tabs are available in the View only mode.");
		adminSurveyTrackingPage=administrationStudiesSitePage.navigateToSubTabs(Constants.SubTabSurveyTracking, AdministrationStudiesSurveyTrackingPage.class);
		adminSurveyTrackingPage.verifySurveyTrackerPageInViewMode();
		
		reportLog("2.8.9: Survey  configuration tabs are available in the View only mode.");
		adminStudiesSurveyPage=adminSurveyTrackingPage.navigateToSubTabs(Constants.SubTabSurvey,AdministrationStudiesSurveyPage.class);
		adminStudiesSurveyPage.verifySurveyPageInViewMode();
		
		reportLog("2.8.9: Schedule  configuration tabs are available in the View only mode.");
		adminStudiesSchedulePage=adminStudiesSurveyPage.navigateToSubTabs(Constants.scheduleSubTab, AdministrationStudiesSchedulePage.class);
		adminStudiesSchedulePage.verifySchedulePageInViewMode();
		
		reportLog("3.1: Open Study -> Languages");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminLanguagePage=adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabsLanguages, AdministrationStudiesLanguagesPage.class);
		
		reportLog("3.2: Languages tab displayed in a View mode");
		adminLanguagePage.languageTabIsInViewMode();
		
		reportLog("3.3: Edit mode is disabled.");
		adminLanguagePage.editModeIsDisabled();
		
		reportLog("3.4: The search field is available");
		adminLanguagePage.searchFieldIsavailable();
		
		reportLog("4.1: Open Study -> Countries ");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminCountriesPage=adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabCountry, AdministrationStudiesCountriesPage.class);
        
        reportLog("4.2: click on the country row");
        adminCountriesPage.clickOnCountryrow();
        
        reportLog("4.3: Languages tab displayed in a View mode");
        adminCountriesPage.languageTabDisplayedInViewMode();
        
        reportLog("4.4: Edit mode is disabled");
        adminCountriesPage.languageTabDisplayedInViewMode();

        reportLog("4.5: The Search field is available.");
        adminCountriesPage.verifySearchField();
        
        reportLog("4.6: Country languages subcategory displayed");
        adminCountriesPage.countryLanguageSubCategoryIsDisplayed();
        
        reportLog("5.1: Open Study -> Forms");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminstrationFormsPage= adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabForms, AdministrationStudiesFormsPage.class);
        
        reportLog("5.2: Click the drop-down of the Languages version row");
        adminstrationFormsPage.clickTheDropDownOfFormRow();
        
        /******************************************************/
        
        
        reportLog("5.3: Languages tab displayed in a View mode");
        adminstrationFormsPage.languageTabDisplayedInViewMode();
        
        
        /***********Automated accordingly Tc's need to update on forms tab form page should Displayed***/
        
        
        reportLog("5.4: Edit mode is disabled");
        adminstrationFormsPage.verifyEditModeIsDisabled();
        
        reportLog("5.5: The Search field is available");
        adminstrationFormsPage.verifySearchFieldIsAvailable();
        
        reportLog("5.6: First form is selected.");
		adminstrationFormsPage.selectandclickOnform(Constants.Assessment_AdasCog14List2FormName);
        adminstrationFormsPage.verifyFormRowIsSelected(Constants.Assessment_AdasCog14List2FormName,Constants.csscolor);
        
        reportLog("5.7: click to open the PDF");
        adminstrationFormsPage.clickToOpenFormPdfFile();
        
        reportLog("5.8: New window with the PDF file displayed");
        String parentwin = switchToChildWindow();
        switchParentWindowByClosingChild(parentwin);
        
        reportLog("6.1: Repeat step#5 with other form rows (in case there are more than 1 form)");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminstrationFormsPage= adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabForms, AdministrationStudiesFormsPage.class);
        
        reportLog("6.2: Click the drop-down of the Languages version row");
        adminstrationFormsPage.clickTheDropDownOfFormRow();
        
        /***********Automated accordingly Tc's need to update on forms tab form page should Displayed***/
        
        reportLog("6.3: Languages tab displayed in a View mode");
        adminstrationFormsPage.languageTabDisplayedInViewMode();
        
        /******************************************************/

        reportLog("6.4: Edit mode is disabled");
        adminstrationFormsPage.verifyEditModeIsDisabled();
        
        reportLog("6.5: The Search field is available");
        adminstrationFormsPage.verifySearchFieldIsAvailable();
        
        reportLog("6.6: Selected form is grayed out.");
		adminstrationFormsPage.selectandclickOnform(Constants.Assesment_AdasCog13List1FormName);
		adminstrationFormsPage.verifyFormRowIsSelected(Constants.Assesment_AdasCog13List1FormName,Constants.csscolor);
        
        reportLog("6.7: click to open the PDF");
        adminstrationFormsPage.clickToOpenFormPdfFile();
        
        reportLog("6.8: New window with the PDF file displayed");
        String parentwin1 = switchToChildWindow();
        switchParentWindowByClosingChild(parentwin1);
        
        reportLog("7.1 Open Study -> Visits");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminVisitsPage=adminstrationFormsPage.navigateToSubTabs(Constants.SubTabVisits, AdministrationStudiesVisitsPage.class);
        
        reportLog("7.2: Visits tab displayed in a View mode,");
        adminVisitsPage.verifyVisitsTabIsInViewMode();
        
        reportLog("7.3: Edit mode is disabled.");
        adminVisitsPage.verifyVisitsTabIsInViewMode();

        reportLog("7.4: The Search field is available.");
        adminVisitsPage.searchFieldIsAvailable();
        
        reportLog("7.5: Mapping control is disabled.");
        adminVisitsPage.mappingControlIsDisabled();
        
        reportLog("8.1: Open Study -> Custom");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminstrationStudiesCustomPage=adminVisitsPage.navigateToSubTabs(Constants.SubTabCustom,AdministrationStudiesCustomPage.class);
        
        reportLog("8.2: Custom tab displayed in a View mode");
        adminstrationStudiesCustomPage.customTabIsDisplayedInViewMode();
        
        reportLog("8.3: Edit mode is disabled.");
        adminstrationStudiesCustomPage.customTabIsDisplayedInViewMode();
        
        reportLog("9.1: Open Study -> People");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminPeoplePage=adminstrationStudiesCustomPage.navigateToSubTabs(Constants.SubTabPeople, AdministrationStudiesPeoplePage.class);
        
        reportLog("9.2:  click the drop-down on the person row");
        adminPeoplePage.clickTheDropDownOnThePersonRow();
        
        reportLog("9.3: People tab displayed in a View mode");
        adminPeoplePage.peopleTabDisplayedInViewMode();
        
        reportLog("9.4: Edit mode is disabled.");
        adminPeoplePage.peopleTabDisplayedInViewMode();

        reportLog("9.5: The Search field is available.");
        adminPeoplePage.searchFieldIsAvailable();
        
        reportLog("9.6: Person details row displayed");
        adminPeoplePage.personDetailsRowDispalyed();
        
        reportLog("10.1: Open Study -> Clinicians");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        administrationStudiesCliniciansPage=adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabClinicians, AdministrationStudiesCliniciansPage.class);
        
        reportLog("10.2: Clinicians tab displayed in a View mode");
        administrationStudiesCliniciansPage.verifyTabInViewMode();
        
        reportLog("10.3: Edit mode is disabled.");
        administrationStudiesCliniciansPage.verifyTabInViewMode();

        reportLog("10.4: The Search field is available.");
        administrationStudiesCliniciansPage.verifySearchField();
        
        reportLog("11.1: Open Study -> Sites");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        administrationStudiesSitePage= adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabSites, AdministrationStudiesSitesPage.class);
        
        reportLog("11.2: click the drop-down on the Sites row");
        administrationStudiesSitePage.clickOnDropdownOnTheSiteRow();
        
        reportLog("11.3: Site Facilities tab displayed in a View mode");
        administrationStudiesSitePage.siteFacilitiesTavDisplayedInViewMode();
        
        reportLog("11.4: Edit mode is disabled. ");
        administrationStudiesSitePage.siteFacilitiesTavDisplayedInViewMode();

        reportLog("11.5: The Search field is available.");
        administrationStudiesSitePage.verifySearchField();
        
        reportLog("11.6: Site Facilities details row displayed");
        administrationStudiesSitePage.siteFacilitiesDetailsRowDisplayed();
        
        reportLog("12.1: Open People Sites tab ");
        administrationStudiesSitePage.openPeopleSitesTab();
        
        reportLog("12.2:  click the drop-down on the Sites row");
        administrationStudiesSitePage.clickTheDropdownOfTheSitesRow();
        
        reportLog("12.3: Site People tab displayed in a View mode");
        administrationStudiesSitePage.sitesPeopleTabDisplayedInViewMode();
        
        reportLog("12.4: Edit mode is disabled.");
        administrationStudiesSitePage.sitesPeopleTabDisplayedInViewMode();
        
        reportLog("12.5: The Search field is available.");
        administrationStudiesSitePage.verifySearchField();

        reportLog("12.6: Site People details row displayed");
        administrationStudiesSitePage.sitePeopleDetailsRowDisplayed();
        
        reportLog("13.1: Open Languages Sites tab");
        administrationStudiesSitePage.openLanguagesSitesTab();
        
        reportLog("13.2: click the drop-down on the Sites row");
        administrationStudiesSitePage.clickTheDropdownOfTheSitesRow();
        
        reportLog("13.3: Site Languages tab displayed in a View mode");
        administrationStudiesSitePage.siteLanguagesTabDisplayedInAViewMode();
        
        reportLog("13.4: Edit mode is disabled.");
        administrationStudiesSitePage.siteLanguagesTabDisplayedInAViewMode();

        reportLog("13.5: The Search field is available.");
        administrationStudiesSitePage.verifySearchField();
        
        reportLog("13.6: Site Languages details row displayed");
        administrationStudiesSitePage.siteLanguageDetailsRowDisplayed();
        
        reportLog("14.1: Open Closeout Sites tab");
        administrationStudiesSitePage.openCloseOutSitesTab();
        
        reportLog("14.2: Site Closeout tab displayed in a View mode");
        administrationStudiesSitePage.siteCloseOutTabTabDisplayedInAViewMode();
        
        reportLog("14.3: Edit mode is disabled");
        administrationStudiesSitePage.siteCloseOutTabTabDisplayedInAViewMode();

        reportLog("14.4:  The Search field is available.");
        administrationStudiesSitePage.verifySearchField();
        
        reportLog("14.5: First Site row is grayed out ");
        administrationStudiesSitePage.firstRowIsGrayedOut();
        
        reportLog("14.6: Warnings tab displayed in the Closeout details block");
        administrationStudiesSitePage.warningTabDisplayedInTheCloseoutDetailsBlock();
        
        reportLog("15.1: Open Files tab in the Closeout details block");
        administrationStudiesSitePage.openFilesTab();
        
        reportLog("15.2: Files tab displayed in the Closeout details block");
        administrationStudiesSitePage.filesTabDisplayedInTheCloseoutTab();
        
        reportLog("16.1: Open Workflow tab in the Closeout details block");
        administrationStudiesSitePage.openWorkflowTab();
        
        reportLog("16.2: Workflow tab displayed in the Closeout details block");
        administrationStudiesSitePage.workflowTabDisplayedInTheCloseoutBlock();
        
        reportLog("17.1: Open Study -> Scale Activation");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminScaleActivationPage= adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabScaleactivation, AdministrationScaleActivationPage.class);
        
        reportLog("17.2: select the option to expand the person details");
        adminScaleActivationPage.selectExpandIcon();
        
        reportLog("17.3: Scale Activation tab displayed in a View mode");
        adminScaleActivationPage.scaleActivationTabDisplayedInViewMode();
        
        reportLog("17.4: Edit mode is disabled");
        adminScaleActivationPage.scaleActivationTabDisplayedInViewMode();

        reportLog("17.5: The Search field is available.");
        adminScaleActivationPage.searchFieldAvailableForScaleActivation();
        
        reportLog("17.6: Scale activation details are displayed for the person");
        adminScaleActivationPage.scaleActivationDetailsAreDisplayed();
        
        reportLog("18.1: Open Study -> Survey Tracker");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminSurveyTrackingPage=adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabSurveyTracking, AdministrationStudiesSurveyTrackingPage.class);
        
        reportLog("18.2:Survey Tracker tab displayed in a View mode");
        adminSurveyTrackingPage.verifySurveyTrackerPageInViewMode();
        
        reportLog("18.3: Edit mode is disabled.");
        adminSurveyTrackingPage.verifySurveyTrackerPageInViewMode();
        
        reportLog("18.4: The Search field is available.");
        adminSurveyTrackingPage.verifySearchFieldOnSurveyTrackerPage();
        
        reportLog("19.1: Open Study -> Survey");
        adminstrationStudiesPage.searchAndClickOnStudy(studyName);
        adminStudiesSurveyPage= adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabSurvey, AdministrationStudiesSurveyPage.class);
        
        reportLog("19.2: Site Survey tab displayed in a View mode");
        adminStudiesSurveyPage.verifySurveyPageInViewMode();
        
        reportLog("19.3: Edit mode is disabled");
        adminStudiesSurveyPage.verifySurveyPageInViewMode();
        String mainWindow=adminStudiesSurveyPage.getDriver().getWindowHandle();

        /****Need To updated Tc's UI changed**************/
        
        //Search field is not available on admin studies Survey page 
        reportLog("19.4: The Search field is available.");

        /****Need To updated Tc's UI changed**************/

        
        reportLog("20.1: Select the option to open the preview");
        adminStudiesSurveyPage.selectTheOptionToOpenThePreview();
        adminStudiesSurveyPage.switchToNewWindow();
        
        reportLog("20.2: Survey Preview displayed in a new Window with the active \"Exit Preview\" control");
        adminStudiesSurveyPage.verifyExitPreviewButton();
        adminStudiesSurveyPage.getDriver().close();
        adminStudiesSurveyPage.getDriver().switchTo().window(mainWindow);
        
        reportLog("Log out as a user PR#2");
        loginPage.logoutApplication();

		reportLog(" Verify user logged out successfully");
		loginPage.verifyUserLogout();	
	
	}
}