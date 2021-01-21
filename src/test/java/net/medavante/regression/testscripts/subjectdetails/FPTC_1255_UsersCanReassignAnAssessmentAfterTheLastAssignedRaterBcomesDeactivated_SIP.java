package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1255_UsersCanReassignAnAssessmentAfterTheLastAssignedRaterBcomesDeactivated_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1255_UsersCanReassignAnAssessmentAfterTheLastAssignedRaterBcomesDeactivated_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("visitClinRoSubmitted");

		/* Creating Subject For Configuring Pre-requisite */
		reportLog("1:Login To Application And Setting Up The Pre-Requisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, AT_PRODSiteCoordinatorUserName,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1255 || Test Case Name:UsersCanRe-assignAnAssessmentAfterTheLastAssignedRaterBcomesDeactivated
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1255_Users can re-assign an assessment after the last assigned rater becomes deactivated", groups = { "" })
	public void FPTC_1255_UsersCanReassignAnAssessmentAfterTheLastAssignedRaterBcomesDeactivated() {

		reportLog("1.1 :Login to the portal as the Rater#1 of Pr#1");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1 : Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.2 : select " + studyName + " study.");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("2.3: Subject details page of Pr#3 is visible");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum);
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("3.1: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("2.3: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("2.4: Click on add visit icon");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("3.1: select the Not Assigned drop down");
		subjectDetailPage.clickOnAssignRaterDropDown();

		reportLog("3.2: Verify Me is the first in the assignment list of raters for the logged in user");
		subjectDetailPage.verifyLoginUserDisplayedAtFirstPositionInScaleRaterList();

		reportLog("3.3: Verify Name of the Other Rater is displayed in rater drop down list");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(AT_PRODSiteUserName);

		reportLog("4.1:	Assign the assessment to Me");
		subjectDetailPage.selectRaterFromDropDown("Me");

		reportLog("4.2:	Assessment is assigned to the current rater");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue("Me");
		loginPage.logoutApplication();

		reportLog("5.1:Login to admin portal as the user of Pr#7 ");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("5.2:	 Login is successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("5.3:	 Navigate to study of Pr#2");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		
		reportLog("5.4:	 Study is selected");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.verifyAdminstrationStudiesPageIsOpen();

		reportLog("Search Study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("6.1:	 From Sites tab deactivate the Rater#1 of step#4 from the study site");
		administrationStudiesSitePage = adminstrationStudiesPage.navigateToStudySitesTab();
		administrationStudiesSitePage.verifyAdministrationStudiesSitesPage();
		administrationStudiesSitePage.deactivateTheRater(Constants.ATAssignedRater_10, Constants.ATAssignedRater_10);

		reportLog("Configuring PreRequisite Activate rater");
		administrationStudiesSitePage.addPeopleRater(Constants.ATAssignedRater_10, Constants.ATAssignedRater_10);

		reportLog("6.3: Logout application");
		loginPage.logoutApplication();

	}
	
	

}
