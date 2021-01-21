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

public class FPTC_2790_CustomizedSubjectStatusScreenFailedManuallySetFromCompletedToScreenFailed_SIP extends BaseTest {

	private String studyName, visitCompleted, screenFailedCustomStatus = "Auto2068Custom",
			subjectCompleted = "Auto2065Completed" + generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2790_CustomizedSubjectStatusScreenFailedManuallySetFromCompletedToScreenFailed_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("tabletFormSettingStudy");
		visitCompleted = properties.getProperty("Auto_CompletedStatusVisit");

		/*--------------Configuring Completed Subject Status-------------*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectCompleted);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitCompleted,SuperAdminUN, SuperAdminPW);
		
		reportLog("Configuring ScreenFailed Custom Status");
		subjectDetailPage.navigateToHomePage();
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.editSubjectAlias(Constants.SubjectStatus_ScreenFailed,screenFailedCustomStatus);
		loginPage.logoutApplication();

	}

	/**
	 * =======================================================================================================
	 * Test Case Id: FP-TC-2790 Test Case Name:Verify the ability to manually
	 * set the Subject Status from "Completed" to “Screen Failed”
	 * ========================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-2790_Verify the ability to manually set the Subject Status from Completed to Screen Failed", groups = {
			"" })
	public void FPTC_2790_verifyCustomizedSubjectStatusScreenFailedManuallySetFromCompletedToScreenFailed() {

		reportLog("1.1:	Log in to Portal as a User from PR#1");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("1.2:	Home page displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog(
				"2.1:Navigate to Subject Detail screen and open subject with PR#3, click Edit Subject details control");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchSubject(subjectCompleted);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectCompleted);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.2:	Subject Details screen is in Edit mode");
		subjectDetailPage.verifySubjectOpenedInEditMode();

		reportLog("3.1:Open the Status drop-down,Available Subject Status options displayed with a customized “Screen Failed” status");
		subjectDetailPage.verifySubjectStatusIsPresent(screenFailedCustomStatus);
		
		reportLog("4.1:	Select a customized “Screen Failed” status and cancel changes");
		subjectDetailPage.selectStatus(screenFailedCustomStatus);
		subjectDetailPage.clickOnCancelBtn();

		reportLog("4.2:	No changes applied for the Subject Detail screen");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Completed);

		reportLog("5.1:	Select a customized “Screen Failed” status and save");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(screenFailedCustomStatus);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("5.2:	A customized “Screen Failed” status is saved and displayed in Subject Details");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				screenFailedCustomStatus);

		reportLog("6.1:	Navigate to a Subjects Listing screen");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		
		reportLog("6.2:	A customized “Screen Failed” status is displayed for a Subject identified in the PR#3");
	    studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectCompleted,screenFailedCustomStatus);

		reportLog(
				"7.1:	Navigate to a Subject Detail screen, identified in the PR #3, change the Screen failed status to previous Completed and Save changes");
		studyNavigatorDashBoardPage.searchSubject(subjectCompleted);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectCompleted);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Completed);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);
		
		reportLog("7.2:Previous Completed status is saved and displayed in Subject Details");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Completed);
		
		reportLog("Reverting Custom Status");
		subjectDetailPage.navigateToHomePage();
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.clearCustomSubjectStatus();
		adminstrationStudiesCustomPage.clickOnSaveBtn();


		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}