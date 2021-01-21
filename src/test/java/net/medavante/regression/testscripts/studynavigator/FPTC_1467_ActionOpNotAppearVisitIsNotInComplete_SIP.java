package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1467_ActionOpNotAppearVisitIsNotInComplete_SIP extends BaseTest {
	
	private String studyName, visit1,visit2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1467_ActionOpNotAppearVisitIsNotInComplete_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");
		subjectName = properties.getProperty("SubjectName2142");
		visit1 = properties.getProperty("MoveVisit1");
		visit2 = properties.getProperty("MoveVisit2");		
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1467 Test Case Name: Change Visit - option to change Visit doesn't appear in case when the Visit is not in 'Complete' state 
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1467 _Verify that 'Action' option doesn't appear as the Visit is not in 'Complete' state.", groups = {})
	public void FPTC_1467_VerifyActionOpNotAppearVisitIsNotInComplete() throws InterruptedException {

		reportLog("1: Login to portal as a user from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.2: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("2: Navigate to Visit list for the study identified in the Pr.#2");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();

		reportLog("2.2: Click on 'In progress' filter");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.InProgress_Status);
		
		reportLog("2.3: Navigate to Visit Detail screen identified in the Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit1,subjectName);

		reportLog("2.4: Visit Detail screen is opened");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("3: Check that a control that allows moving a Visit is not displayed");		
		reportLog("3.1: 'Action' option isn't displayed");
		visitDetaiLPage.verifyingActionButtonIsNotDisplayed();		

		reportLog("4: Navigate to Visit listing screen and select Visit from Pr.#3");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("4.1: Click on 'Pending' filter");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.Pending_Status);

		reportLog("4.2: Navigate to Visit listing screen and select Visit from Pr.#3");
		studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit2,subjectName);

		reportLog("4.3: Visit Detail screen is opened");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("5: Check that a control that allows moving a Visit is not displayed");
		reportLog("5.1: 'Action' option isn't displayed");
		visitDetaiLPage.verifyingActionButtonIsNotDisplayed();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
