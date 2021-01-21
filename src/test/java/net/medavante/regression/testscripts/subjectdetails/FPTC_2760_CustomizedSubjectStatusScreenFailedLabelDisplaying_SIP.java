package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_2760_CustomizedSubjectStatusScreenFailedLabelDisplaying_SIP extends BaseTest {

	private String studyName, visitCompleted, screenFailedCustomStatus = "Auto2063Custom",
			subjectScreenFailed = "Auto2065ScreenFailed" + generateRandomAlphanumericString(5), subjectCr;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2760_CustomizedSubjectStatusScreenFailedLabelDisplaying_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("tabletFormSettingStudy");
		visitCompleted = properties.getProperty("Auto_CompletedStatusVisit");
		subjectCr = properties.getProperty("subjectCr2063");

		/*--------------Configuring Completed Subject Status-------------*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		reportLog("Configuring ScreenFailed Custom Status");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.editSubjectAlias(Constants.SubjectStatus_ScreenFailed, screenFailedCustomStatus);

		/*--------------Configuring ScreenFailed  Subject Status-------------*/
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectScreenFailed);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitCompleted, SuperAdminUN, SuperAdminPW);
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();
		subjectDetailPage.clickOnSubjectDetailsEditIcon();
		subjectDetailPage.selectStatus(screenFailedCustomStatus);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();

	}

	/**
	 * =======================================================================================================
	 * Test Case Id: SFB-TC-2063 Test Case Name:Customized Subject Status,
	 * “Screen Failed” - Label displaying
	 * ========================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-2760_Verify Customized Subject Status, “Screen Failed” - Label displaying ", groups = {
			"" })
	public void FPTC_2760_verifyCustomizedSubjectStatusScreenFailedLabelDisplaying() {

		reportLog("1.1:	Log in to Portal as a User from PR#1");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("1.2:	Home page displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigate to a Subjects Listing view for the Study identified in PR#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("2.2:Status labels are displayed in the Status column for each Subject");
		studyNavigatorDashBoardPage.verifySubjectStatusinSubjectListing(subjectScreenFailed, screenFailedCustomStatus);

		reportLog("3.1:	Open Subjects Details screen for a Subject identified in the PR #4");
		studyNavigatorDashBoardPage.searchSubject(subjectScreenFailed);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectScreenFailed);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.2: Status label with customized “Screen Failed” Subject status is displayed");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, screenFailedCustomStatus);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("4.1:	Open Visit Details screen for a Subject identified in the PR#4");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitCompleted,
				subjectScreenFailed);

		reportLog("4.2:Status label with customized “Screen Failed” Subject status is displayed");
		visitDetaiLPage.verifyVisitDetailsUnderSubjectSection(Constants.StudyDashBoard_columnName_Status,
				screenFailedCustomStatus);
		loginPage.logoutApplication();

		reportLog(
				"5.1:	Log in as a User identified in the PR #2 and navigate to Central Ratings for the Study identified in Pr#4");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("5.2:Status labels are displayed in the Subject Status column for each CR Visit");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();
		centralRatingAssesmentListingPage.sortBySubjectName(subjectCr);
		centralRatingAssesmentListingPage.searchByVisitStatus(CentralRatingModuleConstants.completeStatus);
		centralRatingAssesmentListingPage.verifyRowValueAccordingToColumnValue(
				CentralRatingModuleConstants.subjectStatus, Constants.SubjectStatus_ScreenFailed);

		reportLog("6.1:	Click on the Subject Status filter on the CR Assessments Listing view");
		centralRatingAssesmentListingPage.clickOnSubjectStatusFilterDropDown();

		reportLog("6.2:Screen FaileD Subject status is displayed among the Subject status list");
		centralRatingAssesmentListingPage.verifySubjectStatusPresentInList(Constants.SubjectStatus_ScreenFailed);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

	@AfterMethod
	public void clearSubjectAlias(ITestResult result) {
		if (ITestResult.SUCCESS == result.getStatus()) {
			reportLog("Configuring ScreenFailed Custom Status");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
					AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
			adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
			adminstrationStudiesPage.searchAndClickOnStudy(studyName);
			adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
			adminstrationStudiesCustomPage.clearSubjectAlias(Constants.SubjectStatus_ScreenFailed);
			loginPage.logoutApplication();
		}
	}

}
