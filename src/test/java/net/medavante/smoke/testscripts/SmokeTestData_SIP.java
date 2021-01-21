package net.medavante.smoke.testscripts;
import java.util.Properties;


import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class SmokeTestData_SIP extends BaseTest {

	private String orgName, abbrevation, orgType, studyName, studyPhase, studyLanguage, studyScale, studyVisitName,
			visitAnalyticalType, studySite, studyfacility, studyCountry, sitePeople, siteCount, people1Role;
			
	private Properties properties;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public SmokeTestData_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}

	@BeforeClass
	public void updateStudyNameInProp() throws Exception {
		Configuration.updatePropertyTestData("TestDataFile", "AutomationOrgName",
				"01_Automation_Org_" + generateRandomString(10));
		Configuration.updatePropertyTestData("TestDataFile", "AutomationStudyName",
				"Automation_Study_" + generateRandomString(10));
		Configuration.updatePropertyTestData("TestDataFile", "AutomationAbbrevation",
				"Automation_Abbr_" + generateRandomString(10));
		properties = Configuration.readTestData("TestDataFile");
		//orgName = properties.getProperty("AutomationOrgName");
		orgName=properties.getProperty("OrganizationExisting");
		abbrevation = properties.getProperty("AutomationAbbrevation");
		orgType = properties.getProperty("AutomationOrgType");
		studyName = properties.getProperty("AutomationStudyName");
		studyPhase = properties.getProperty("AutomationStudyPhase");
		studyLanguage = properties.getProperty("AutomationStudyLanguage");
		siteCount = properties.getProperty("AutomationSiteCount");
		studyScale = properties.getProperty("AutomationScale");
		visitAnalyticalType = properties.getProperty("AutomationVisitType");
		studyCountry = properties.getProperty("AutomationStudyCountry");
		studySite = properties.getProperty("AutomationSite");
		studyfacility = properties.getProperty("AutomationFacility");
		studyVisitName = properties.getProperty("AutomationVisitName");
		sitePeople = properties.getProperty("AutomationSitePeople1");
		people1Role = properties.getProperty("AutomationSitePeople1Role");

		System.setProperty("className", getClass().getSimpleName());
	}

	/** Create Organization For Smoke Test Case **/

	@Test(description = "Creating New Organization",enabled=false)
	public void createNewOrg() {
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("Select Administration option.");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		
		reportLog("Add organization");
		adminstrationOrganizationPage.createOrganization(orgName, abbrevation, orgType, generateRandomString(10));

		reportLog("Search " + orgName + " from the organization records.");
		adminstrationOrganizationPage.organizationSearch(orgName, orgType);

		reportLog("Verify " + orgName + " is selected and displayed.");
		adminstrationOrganizationPage.verifySelectedOrgIsDisplayed(orgName);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}

	/** Create new study For Smoke Test Case **/
	@Test(description = "Creating New study")
	public void createNewStudy() {
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("Select Administration option.");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		
		reportLog("Navigate to study tab");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		
		reportLog("Click on add new study button and set up new study");
		adminstrationStudiesPage.createStudy(studyName, abbrevation, studyPhase, orgName);

		reportLog("Navigate to Language tab");
		adminLanguagePage = adminstrationStudiesPage.navigateToStudyLanguageTab();

		reportLog("Select " + studyLanguage + " language from the options.");
		adminLanguagePage.selectLanguage(studyLanguage);

		reportLog("Navigate to Countries tab");
		adminCountriesPage = adminstrationStudiesPage.navigateToStudyCountriesTab();

		reportLog("Select " + studyCountry + " " + siteCount + " " + studyLanguage
				+ " country, Site Count and language from the options.");
		adminCountriesPage.selectCountry(studyCountry, siteCount, studyLanguage);

		reportLog("Navigate to Forms tab");
		adminstrationFormsPage = adminstrationStudiesPage.navigateToStudyFormsTab();

		reportLog("Select " + studyScale + " Scale from the list of scale.");
		adminstrationFormsPage.addStudyScale(studyScale);

		reportLog("Navigate to People tab");
		adminPeoplePage = adminstrationStudiesPage.navigateToStudyPeopleTab();
		adminPeoplePage.addPeople(Constants.SiteCordinatorUser, "MedAvante User Type 2");

		reportLog("Navigate to Visits tab");
		adminVisitsPage = adminstrationStudiesPage.navigateToStudyVisitsTab();

		reportLog("Configured visit for " + studyName + " study.");
		adminVisitsPage.addStudyVisit(studyVisitName + generateRandomString(1), visitAnalyticalType, studyScale);

		reportLog("Navigate to Sites tab");
		adminSitesPage = adminstrationStudiesPage.navigateToStudySitesTab();

		reportLog("Configured site for " + studyName + " study.");
		adminSitesPage.enterSitesData(studySite, studyfacility, sitePeople, people1Role, studyLanguage);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}

	}


