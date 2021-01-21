package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.pages.studynavigator.VisitDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1470_UserWithoutClaimCantMoveVisit_SIP extends BaseTest {

	private String studyName,subjectName, crvisitName, noncrvisitName; 	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1470_UserWithoutClaimCantMoveVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");
		subjectName = properties.getProperty("SubjectName1470_1");
		crvisitName = properties.getProperty("VisitSecond2144");
		noncrvisitName = properties.getProperty("VisitFirst2144");
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1470 Test Case Name: Verify that CR/Non-CR Visit cannot be moved by a User without an appropriate claim
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-1470_Verify that CR/Non-CR Visit cannot be moved by a User without an appropriate claim", groups = { })
	public void FPTC_1470_VerifyUserWithoutClaimCantMoveVisit() throws Exception {
		
		reportLog("1: Login to portal as a user from Pr.#1");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteUser, AT_Password);
		//dashBoardPage = loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2: Navigate to Visit list for the study identified in the Pr.#2");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();

		reportLog("2.2: Click on 'Completed' filter");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.Complete_Status); 

		reportLog("2.3: Visit list is opened displaying visits in status as Completed");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(noncrvisitName);		

		reportLog("2.4: Navigate to Visit Detail screen identified in the Pr.#2");
		visitDetaiLPage=studyNavigatorDashBoardPage.selectByFirstCell(VisitDetailsPage.class);

		reportLog("2.5: Visit Detail screen is opened");
		visitDetaiLPage.verifyVisitPageIsDisplayed();
		
		reportLog("2.6: User cannot change Visit");
		visitDetaiLPage.verifyActionButtonIsNotDisplayed();
		
		reportLog("3: Navigate to Visit listing screen and select Visit from Pr.#3");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.Complete_Status);
		
		reportLog("3.1: Visit list is opened displaying visits in status as Completed");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(crvisitName);		

		reportLog("3.2: Navigate to Visit Detail screen identified in the Pr.#2");
		studyNavigatorDashBoardPage.selectByFirstCell(VisitDetailsPage.class);

		reportLog("3.3: Visit Detail screen is opened");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("3.4: 'Action' option isn't displayed");
		visitDetaiLPage.verifyActionButtonIsNotDisplayed();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
				
		
	}

}
