package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_4082_StudySelectorStudyNavigatorUniversity_SIP extends BaseTest {
	
	private String studyInactive,studyActiveFirst,studyActiveSecond,inactiveSite;
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
     public FPTC_4082_StudySelectorStudyNavigatorUniversity_SIP(String browser) {
		super(browser);
	}
	
	
	

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyActiveFirst = properties.getProperty("Study1594");
		studyActiveSecond = properties.getProperty("AutomationStudyName");
		studyInactive = properties.getProperty("StudyInactiveState");
		inactiveSite=properties.getProperty("InactiveSite");

	}
	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-4082 || Test Case Name:Study selector: Navigate - University

	 * ====================================================================================================================
	 * @throws Exception 
	 * 
	 *
	 */
	
	@Test(description = "FP-TC-4082_Study selector: Navigate - University", groups = { "" })
    public void FPTC_4082_StudySelectorStudyNavigatorUniversity() throws Exception {
		
		reportLog("1.1: Log in to the Ma-Portal as User PR#1");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectCoordinator, AT_Password);
		
		reportLog("1.2: Select Navigate -> University");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class, Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);
		
		reportLog("1.3: University page is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
		
		reportLog("1.4: Study Selector is available");
		ratersDetailsPage.verifyNavigateButtonIsAvailable();
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class, Constants.NavigateText, Constants.SubTabUniversity);
		
		reportLog("2.1: Select to open Study Selector");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("2.2: The following items are displayed:"); 
		reportLog("Full Study list"); 
		reportLog("All studies option"); 
		reportLog("Search Study option"); 
		reportLog("Not selected by default Hide Inactive Sites option"); 
		reportLog("Full Sites (active) list"); 
		reportLog("Search Site option"); 
		reportLog("Inactive Sites are displayed in the list"); 
		reportLog("All sites option"); 
		reportLog("Select, Cancel, close options");
		ratersDetailsPage.verifyItemssAreDisplayed();
		
		reportLog("3.0: Select any Study from the list");
		ratersDetailsPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("3.1: Study is selected" );
		ratersDetailsPage.verifyStudyIsSelectedOnPopUp();

		reportLog("3.2: Select Cancel control");
		ratersDetailsPage.selectCancelOnStudySelectPopUp();
		
		reportLog("3.3: Modal is closed and Selected study is not applied");
		ratersDetailsPage.verifySelectedStudyIsNotApplied(studyActiveFirst);
		
		reportLog("4.1: Select to open Study Selector again");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("4.2: Select any Study from the list");
		ratersDetailsPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("4.3: Select Inactive Site");
		ratersDetailsPage.selectInActiveSiteFromsiteList(Constants.ChooseSite_ATTester08);
		
		reportLog("4.3: Study is selected");
		ratersDetailsPage.verifyStudyIsSelectedOnPopUp();
		
		reportLog("4.4: Inactive Site is selected");
		ratersDetailsPage.verifyInActiveSiteSiteIsSelected();
		
		reportLog("5.1: Click the Select control");
		ratersDetailsPage.clickOnSelectButtonAfterselectingStudySite();
		
		reportLog("5.2:  Selected Study and Inactive Site is applied");
		ratersDetailsPage.verifySelectedStudyAndSiteIsApplied(studyActiveFirst,Constants.ChooseSite_ATTester08);
		
		reportLog("5.3: Option to view Study Profile is available");
		ratersDetailsPage.verifyOptionToViewStudyProfileDisplayed();
		
		reportLog("5.4: Option to open a modal window with a Study Selector is available");
		ratersDetailsPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("6.1: Select to open Study Selector again");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("6.2: Select Hide Inactive Sites option");
		ratersDetailsPage.selectHideInactiveSitesCheckboxoption();
		
		reportLog("6.3: Inactive Sites are NOT displayed in the list");
		ratersDetailsPage.verifyHideInActivesitesOptionIsSelected();
		
		reportLog("6.4: Select All studies option");
		reportLog("6.5: Select All sites option");
		ratersDetailsPage.selectAllStudiesAndAllsitesOption();
		
		reportLog("6.6: All studies & All Sites option is selected");
		ratersDetailsPage.verifyAllStudyAndAllSiteOptionIsSelected();
		
		reportLog("7.1: Click the Select control");
		ratersDetailsPage.clickOnSelectButtonAfterselectingStudySite();
		
		reportLog("7.2: All studies and All sites are applied");
		ratersDetailsPage.verifySelectedStudyAndSiteIsApplied(Constants.All_Studies,Constants.All_Sites);
		
		reportLog("7.3: Option to view Study Profile is not  available");
		ratersDetailsPage.verifyOptionToViewStudyProfileNotDisplayed();
		
		reportLog("7.4: Option to open a modal window with a Study Selector is available");
		ratersDetailsPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		reportLog("8.1: Log in to the Site-Portal as User PR#2");
		dashBoardPage = loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);
		
		reportLog("8.2: Select Navigate -> University");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class, Constants.NavigateText, Constants.virgilUniversitySubTab);
		ratersDetailsPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);
		
		reportLog("8.3: University page is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
		
		reportLog("8.4: Study Selector is available");
		ratersDetailsPage.verifyNavigateButtonIsAvailable();
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class, Constants.NavigateText, Constants.SubTabUniversity);
		
		reportLog("9.1: Select to open Study Selector");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("9.2: The following items are displayed:1.Study list 2.Site list 3.All sites option 4.Select, Cancel, close options");
		ratersDetailsPage.verifyItemssAreDisplayed();
		
		reportLog("9.2.1: Hide Inactive Sites option is NOT displayed");
		ratersDetailsPage.verifyHideInactiveSiteOptionIsNotdisplayed();
		
		reportLog("10.1: Select any Study from the list");
		ratersDetailsPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("10.2: Study is selected" );
		ratersDetailsPage.verifyStudyIsSelectedOnPopUp();

		reportLog("10.3: Select Cancel control");
		ratersDetailsPage.selectCancelOnStudySelectPopUp();
		
		reportLog("10.4: Modal is closed and Selected study is not applied");
		ratersDetailsPage.verifySelectedStudyIsNotApplied(studyActiveFirst);
		
		reportLog("11.1: Select to open Study Selector again");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("11.2: Select Study from the list. Select any Site andClick the Select control");
		ratersDetailsPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);

		reportLog("11.3: Selected Study and Site are applied");
		ratersDetailsPage.verifySelectedStudyAndSiteIsApplied(studyActiveSecond,Constants.ATAssignedRater_10);

        reportLog("11.4: Option to view Study Profile is available");
		ratersDetailsPage.verifyOptionToViewStudyProfileDisplayed();

		reportLog("11.5: Option to open a modal window with a Study Selector is available");
		ratersDetailsPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("12.1: Select to open Study Selector again");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("12.2: Select any Study from the list");
		ratersDetailsPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("12.3: Select All sites option");
		ratersDetailsPage.selectAllSiteOption();
		
		reportLog("12.4: Study is selected");
		ratersDetailsPage.verifyStudyIsSelectedOnPopUp();
		
		reportLog("12.5: Check that All sites option is selected");
		ratersDetailsPage.verifyAllSiteOptionIsSelected();
		ratersDetailsPage.clickOnSelectButtonAfterselectingStudySite();

		reportLog("12.6: Option to view Study Profile is available");
		ratersDetailsPage.verifyOptionToViewStudyProfileDisplayed();
		
		reportLog("12.7: Option to open a modal window with a Study Selector is available");
		ratersDetailsPage.verifyStudyButtonForModalWindowIsAvailable();
				
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		reportLog("13.1: Log in to the Site-Portal as User PR#3");
		dashBoardPage = loginPage.siteLogin(AT_PRODSponsorUserType3, AT_Password);
		
		reportLog("13.2: Select Navigate -> University");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class, Constants.NavigateText, Constants.virgilUniversitySubTab);
		ratersDetailsPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);
		
		reportLog("13.3: University page is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();
		
		reportLog("13.4: Study Selector is available");
		ratersDetailsPage.verifyNavigateButtonIsAvailable();
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class, Constants.NavigateText, Constants.SubTabUniversity);
		
		reportLog("14.1: Select to open Study Selector");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("14.2: The following items are displayed:1.Study list 2.Site list 3.All sites option 4.Select, Cancel, close options");
		ratersDetailsPage.verifyItemssAreDisplayed();
		
		reportLog("15.1: Select any Study from the list");
		ratersDetailsPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("15.2: Study is selected");
		ratersDetailsPage.verifyStudyIsSelectedOnPopUp();
		
		reportLog("15.3: Select Cancel control");
		ratersDetailsPage.selectCancelOnStudySelectPopUp();
		
		reportLog("15.4: Modal is closed and Selected study is not applied");
		ratersDetailsPage.verifySelectedStudyIsNotApplied(studyActiveFirst);
		
		reportLog("16.1: Select to open Study Selector again");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("16.2: Select any Study from the list");
		ratersDetailsPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("16.3: Select Inactive Site");
		ratersDetailsPage.selectInActiveSiteFromsiteList(Constants.ChooseSite_ATTester08);
		
		reportLog("16.4: Study is selected");
		ratersDetailsPage.verifyStudyIsSelectedOnPopUp();
		
		reportLog("16.5: Inactive Site is selected");
		ratersDetailsPage.verifyInActiveSiteSiteIsSelected();
		
		reportLog("17.1: Click the Select control");
		ratersDetailsPage.clickOnSelectButtonAfterselectingStudySite();
		
		reportLog("17.2:  Selected Study and Inactive Site is applied");
		ratersDetailsPage.verifySelectedStudyAndSiteIsApplied(studyActiveFirst,Constants.ChooseSite_ATTester08);
		
		reportLog("17.3: Option to view Study Profile is available");
		ratersDetailsPage.verifyOptionToViewStudyProfileDisplayed();
		
		reportLog("17.4: Option to open a modal window with a Study Selector is available");
		ratersDetailsPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("18.1: Select to open Study Selector again");
		ratersDetailsPage.selectStudySelectorButton();
		
		reportLog("18.2: Select Hide Inactive Sites option");
		ratersDetailsPage.selectHideInactiveSitesCheckboxoption();
		
		reportLog("18.3: Inactive Sites are NOT displayed in the list");
		ratersDetailsPage.verifyHideInActivesitesOptionIsSelected();
		
		reportLog("18.4: Select another Study from the list. Select any Site andClick the Select control");
		ratersDetailsPage.selectStudyFromList(studyActiveFirst);

		reportLog("18.5: Select All sites option");
		ratersDetailsPage.selectAllSiteOption();
		
		reportLog("18.6: Study is selected");
		ratersDetailsPage.verifyStudyIsSelectedOnPopUp();
		
		reportLog("18.7: Check that All sites option is selected");
		ratersDetailsPage.verifyAllSiteOptionIsSelected();
		
		reportLog("19.1: Click the Select control");
		ratersDetailsPage.clickOnSelectButtonAfterselectingStudySite();
		
		reportLog("19.2:  Selected Study and All Site is applied");
		ratersDetailsPage.verifySelectedStudyAndSiteIsApplied(studyActiveFirst,Constants.All_Sites);
		
        reportLog("19.3: Option to view Study Profile is available");
		ratersDetailsPage.verifyOptionToViewStudyProfileDisplayed();

		reportLog("19.4: Option to open a modal window with a Study Selector is available");
		ratersDetailsPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		
	}

}
