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

public class FPTC_1220_SelectStudyForDisplay_SIP extends BaseTest {

	private String studyForSitePerson, studyForSponsorPerson, studyForMedAvantePerson;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1220_SelectStudyForDisplay_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyForSitePerson = properties.getProperty("StudyOnlyForSitePerson");
		studyForSponsorPerson = properties.getProperty("StudyOnlyForSponsorPerson");
		studyForMedAvantePerson = properties.getProperty("StudyOnlyForMedAvantePerson");
		

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1220 Test Case Name: Select Study For Display
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1220_Select Study For Display  ", groups = { "" })

	public void FPTC_1220_SelectStudyForDisplay() throws Exception {

		reportLog("1.1: Log in to the Site Portal");
		dashBoardPage = loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Verify Option to select a study is available");
		studyNavigatorDashBoardPage.verifyOptionToSelectStudyAndSiteIsDisplayed();

		reportLog("2.1: Select study and site");
		studyNavigatorDashBoardPage.selectStudy(studyForSitePerson,Constants.ATAssignedRater_10);

		reportLog("2.2: Verify Option to view Study Profile is available");
		studyNavigatorDashBoardPage.verifyOptionToViewStudyProfileDisplayed();

		reportLog("2.3: Logout application");
		loginPage.logoutApplication();

		reportLog("2.4: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("3.1: Log in to the Sponsor Portal");
		dashBoardPage = loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("3.3: Verify  Study " + studyForSponsorPerson + " is displayed in the list.");
		studyNavigatorDashBoardPage.verifyStudyDisplayedUnderSelectStudyList(studyForSponsorPerson);
		studyNavigatorDashBoardPage.selectStudy(studyForSponsorPerson,Constants.VTAssignedRater_21);
	
		reportLog("3.7: Logout application");
		loginPage.logoutApplication();

		reportLog("3.8: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("4.1: Log in to the MedAvante Portal");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectManager, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();		

		reportLog("4.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("4.3: Verify  Study " + studyForMedAvantePerson + " is displayed in the list.");
		studyNavigatorDashBoardPage.verifyStudyDisplayedUnderSelectStudyList(studyForMedAvantePerson);
		studyNavigatorDashBoardPage.selectStudy(studyForMedAvantePerson,Constants.ATAssignedRater_10);

		reportLog("4.7: Logout application");
		loginPage.logoutApplication();

		reportLog("4.8: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
