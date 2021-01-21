package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSitesPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1337_VerifyAssessmentsCanBeAssignedToQualifiedRatersOnly_SIP extends BaseTest {	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1337_VerifyAssessmentsCanBeAssignedToQualifiedRatersOnly_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		Properties userCredentials = Configuration.readTestData("userClaimsCredentials");
		userName = userCredentials.getProperty("PRODSiteCoordinator");
		studyName = prop.getProperty("AutomationStudyName");
		visitSubmitted = prop.getProperty("visitClinRoSubmitted");
		visitNotAssigned = prop.getProperty("visitClinRoNotAssigned");
				
		/*------Creating Subject and assigning rater for Pre-Requisite-------------*/
		
		reportLog("Creating Subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
	
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1337 Test Case Name: Show that Assessments can be assigned to qualified raters only
	 *
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1337_Show that Assessments can be assigned to qualified raters only", groups = { "R3" })

	public void R3_FPTC_1337_VerifyAssessmentsCanBeAssignedToQualifiedRatersOnly() throws InterruptedException {

		reportLog("2: Log in to Portal as User PR#2");
		dashBoardPage =loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);

		reportLog("2.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3: Navigate to Administration tab -> Studies -> Study PR#1 ->Sites -> People");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
				
		reportLog("3.1: Verify Organization page with Add Organization Icon");
		adminstrationOrganizationPage.verifyOrganizationPageWithAddOrgButton();

		reportLog("3.2: Go to CONFIGURE>>Study Setup>>STUDIES");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.3: Verify studies page with Add Study icon");
		adminstrationStudiesPage.verifyAdminstrationStudiesPageIsOpen();
		
		reportLog("3.4: Select a Study and Verify each of the tab");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("3.5: Click On Sites Tab ");
		adminSitesPage = adminstrationStudiesPage.navigateToStudySitesTab();

		reportLog("3.6: Verify Sites Page");
		adminSitesPage.verifyAdministrationStudiesSitesPage();
		
		reportLog("Move to  Study PR#1 ->Sites -> People");
		adminSitesPage.clickOnPeopleSitesTab();	
		
		reportLog("Select people tab and Click on Collpased icon");	
		reportLog("3.7: Open settings for User from PR#3");	
		adminSitesPage.selectSitePeople(Constants.ATAssignedRater_10);
				
		reportLog("3.8: Conduct Assessments option is selected");
		adminSitesPage.verifyConductAssessmentsOptionIsSelected();
		
		reportLog("4: Navigate to the Subject PR#4 details page");
		adminSitesPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationStudiesSitesPage.class,Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATSiteAssignedRater_10);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum);	
		
		reportLog("4.1: Subject details page displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("5: Select a Visit PR#4");
		subjectDetailPage.clickOnVisitRow(visitSubmitted);
		
		reportLog("5.1: Visit details displayed");
		subjectDetailPage.clickOnAddVisitIcon();
		
		reportLog("6: Initiate and Assign an Assessment to Rater PR#3");		
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);
		
		reportLog("7: Navigate to the Assessment details page");
		assessmentDetailPage=subjectDetailPage.clickOnBeforeNotAdministeredThumbnailImage();
		
		reportLog("7.1: Assessment details page displayed with assigned Rater PR#3");
		assessmentDetailPage.verifyRaterNameDisplayedasHyperlink(Constants.ATAssignedRater_10);
		
		reportLog("8.1: Navigate back to the Subject PR#4 details page");
		assessmentDetailPage.clickOnSubjectLink();		
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("8.2: Try to assign the Assessment to the Rater PR#5");
		subjectDetailPage.clickOnVisitRow(visitSubmitted);
		
		reportLog("8.3: Rater PR#5 not displayed in the list of available Raters for the Assessment");
		/*Need to create method for not display rater in list */
		subjectDetailPage.verifyRaterNameIsNotDisplayedInRaterDropDown(PRODSiteCoordinator);
		
		reportLog("9: Logout from the application");
		loginPage.logoutApplication();
		
		reportLog("9.1: Verify user is logout");
		loginPage.verifyUserLogout();
		
	}
}
