package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1221_StudyNavigatorSelectStudy_SIP extends BaseTest {
	
	private String studyInactive,studyActiveFirst,studyActiveSecond;
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1221_StudyNavigatorSelectStudy_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyActiveFirst = properties.getProperty("Study1594");
		studyActiveSecond = properties.getProperty("AutomationStudyName");
		studyInactive = properties.getProperty("StudyInactiveState");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1221 || Test Case Name:Study Navigator - Select Study
	 * ====================================================================================================================
	 * 
	 *
	 */

	@Test(description = "FP-TC-1221_Study Navigator - Select Study", groups = { "" })
	public void FPTC_1221_verifyStudyNavigatorSelectStudy() throws Exception {
		
		
		reportLog("1.1: Log in to the MA-Portal as User PR#1");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectCoordinator, AT_Password);
		
		reportLog("1.2: Select Navigate -> Study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);
		
		reportLog("1.3:  Study Navigator is displayed");
		reportLog("1.4: Study Selector is available");
		studyNavigatorDashBoardPage.verifyNavigateButtonIsAvailable();
        studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,Constants.StudyText);
		
		reportLog("2.1: Select to open Study Selector");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("2.2: The following items are displayed:1.Study list 2.Site list 3.All sites option 4.Select, Cancel, close options");
		studyNavigatorDashBoardPage.verifyItemssAreDisplayed();
		
		reportLog("3.1: Select any Study from the list");
		studyNavigatorDashBoardPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("3.2: Select Cancel control");
		studyNavigatorDashBoardPage.selectCancelOnStudySelectPopUp();
		
		reportLog("3.3: Modal is closed and Selected study is not applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsNotApplied(studyActiveFirst);
		
		reportLog("4.1: Select to open Study Selector again");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("4.2: Select any Study from the list");
		studyNavigatorDashBoardPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("4.3: Check that All sites option is selected");
		studyNavigatorDashBoardPage.selectAllSiteOption();
		
		reportLog("4.3: Study is selected");
		studyNavigatorDashBoardPage.verifyStudyIsSelectedOnPopUp();
		
		reportLog("4.4: Check that All sites option is selected");
		studyNavigatorDashBoardPage.verifyAllSiteOptionIsSelected();
		
		reportLog("5.1: Click the Select control");
		studyNavigatorDashBoardPage.clickOnSelectButtonAfterselectingStudySite();
		
		reportLog("5.2:  Selected Study is applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsApplied(studyActiveFirst);
		
		reportLog("5.3: Option to view Study Profile is available");
		studyNavigatorDashBoardPage.verifyOptionToViewStudyProfileDisplayed();
		
		reportLog("5.4: Option to open a modal window with a Study Selector is available");
		studyNavigatorDashBoardPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("6.1: Select to open Study Selector again");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("6.2: Select another Study from the list. Select any Site andClick the Select control");
		studyNavigatorDashBoardPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);

		reportLog("6.3: Selected Study and Site are applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsApplied(studyActiveSecond);

        reportLog("6.4: Option to view Study Profile is available");
		studyNavigatorDashBoardPage.verifyOptionToViewStudyProfileDisplayed();

		reportLog("6.5: Option to open a modal window with a Study Selector is available");
		studyNavigatorDashBoardPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		
		reportLog("7.1: Log in to the Site-Portal as User PR#2");
		dashBoardPage = loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);
		
		reportLog("7.2: Select Navigate -> Study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);
		
		reportLog("7.3:  Study Navigator is displayed");
		reportLog("7.4: Study Selector is available");
		studyNavigatorDashBoardPage.verifyNavigateButtonIsAvailable();
        studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,Constants.StudyText);
		
		reportLog("8.1: Select to open Study Selector");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("8.2: The following items are displayed:1.Study list 2.Site list 3.All sites option 4.Select, Cancel, close options");
		studyNavigatorDashBoardPage.verifyItemssAreDisplayed();
		
		reportLog("9.1: Select any Study from the list");
		studyNavigatorDashBoardPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("9.2: Select Cancel control");
		studyNavigatorDashBoardPage.selectCancelOnStudySelectPopUp();
		
		reportLog("9.3: Modal is closed and Selected study is not applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsNotApplied(studyActiveFirst);
		
		reportLog("10.1: Select to open Study Selector again");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("10.2: Select any Study from the list");
		studyNavigatorDashBoardPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("10.3: Check that All sites option is selected");
		studyNavigatorDashBoardPage.selectAllSiteOption();
		
		reportLog("10.4: Study is selected");
		studyNavigatorDashBoardPage.verifyStudyIsSelectedOnPopUp();
		
		reportLog("10.5: Check that All sites option is selected");
		studyNavigatorDashBoardPage.verifyAllSiteOptionIsSelected();
		
		reportLog("11.1: Click the Select control");
		studyNavigatorDashBoardPage.clickOnSelectButtonAfterselectingStudySite();
		
		reportLog("11.2:  Selected Study is applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsApplied(studyActiveFirst);
		
		reportLog("11.3: Option to view Study Profile is available");
		studyNavigatorDashBoardPage.verifyOptionToViewStudyProfileDisplayed();
		
		reportLog("11.4: Option to open a modal window with a Study Selector is available");
		studyNavigatorDashBoardPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("12.1: Select to open Study Selector again");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("12.2: Select another Study from the list. Select any Site andClick the Select control");
		studyNavigatorDashBoardPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);

		reportLog("12.3: Selected Study and Site are applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsApplied(studyActiveSecond);

        reportLog("12.4: Option to view Study Profile is available");
		studyNavigatorDashBoardPage.verifyOptionToViewStudyProfileDisplayed();

		reportLog("12.5: Option to open a modal window with a Study Selector is available");
		studyNavigatorDashBoardPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		
		
		reportLog("13.1: Log in to the Site-Portal as User PR#2");
		dashBoardPage = loginPage.siteLogin(AT_PRODSponsorUserType3, AT_Password);
		
		reportLog("13.2: Select Navigate -> Study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);
		
		reportLog("13.3:  Study Navigator is displayed");
		reportLog("13.4: Study Selector is available");
		studyNavigatorDashBoardPage.verifyNavigateButtonIsAvailable();
        studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText,Constants.StudyText);
		
		reportLog("14.1: Select to open Study Selector");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("14.2: The following items are displayed:1.Study list 2.Site list 3.All sites option 4.Select, Cancel, close options");
		studyNavigatorDashBoardPage.verifyItemssAreDisplayed();
		
		reportLog("15.1: Select any Study from the list");
		studyNavigatorDashBoardPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("15.2: Select Cancel control");
		studyNavigatorDashBoardPage.selectCancelOnStudySelectPopUp();
		
		reportLog("15.3: Modal is closed and Selected study is not applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsNotApplied(studyActiveFirst);
		
		reportLog("16.1: Select to open Study Selector again");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("16.2: Select any Study from the list");
		studyNavigatorDashBoardPage.selectStudyFromList(studyActiveFirst);
		
		reportLog("16.3: Check that All sites option is selected");
		studyNavigatorDashBoardPage.selectAllSiteOption();
		
		reportLog("16.4: Study is selected");
		studyNavigatorDashBoardPage.verifyStudyIsSelectedOnPopUp();
		
		reportLog("16.5: Check that All sites option is selected");
		studyNavigatorDashBoardPage.verifyAllSiteOptionIsSelected();
		
		reportLog("17.1: Click the Select control");
		studyNavigatorDashBoardPage.clickOnSelectButtonAfterselectingStudySite();
		
		reportLog("17.2:  Selected Study is applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsApplied(studyActiveFirst);
		
		reportLog("17.3: Option to view Study Profile is available");
		studyNavigatorDashBoardPage.verifyOptionToViewStudyProfileDisplayed();
		
		reportLog("17.4: Option to open a modal window with a Study Selector is available");
		studyNavigatorDashBoardPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("18.1: Select to open Study Selector again");
		studyNavigatorDashBoardPage.selectStudySelectorButton();
		
		reportLog("18.2: Select another Study from the list. Select any Site andClick the Select control");
		studyNavigatorDashBoardPage.selectStudy(studyActiveSecond,Constants.ATAssignedRater_10);

		reportLog("18.3: Selected Study and Site are applied");
		studyNavigatorDashBoardPage.verifySelectedStudyIsApplied(studyActiveSecond);

        reportLog("18.4: Option to view Study Profile is available");
		studyNavigatorDashBoardPage.verifyOptionToViewStudyProfileDisplayed();

		reportLog("18.5: Option to open a modal window with a Study Selector is available");
		studyNavigatorDashBoardPage.verifyStudyButtonForModalWindowIsAvailable();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
	}
	
	}