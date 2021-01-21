package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1251_SiteUsersShouldNotBeAbleToViewTheRatersDetailsOfAMedAvanteClinician_SIP extends BaseTest {

	private String studyNameNonCR, visit_Scheduled, visit_Complete, visit_Editing;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1251_SiteUsersShouldNotBeAbleToViewTheRatersDetailsOfAMedAvanteClinician_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyNameNonCR = properties.getProperty("StudyCRStatus");
		visit_Scheduled = properties.getProperty("ScheduledVisit");
		visit_Complete = properties.getProperty("completdVisit");
		visit_Editing = properties.getProperty("EditingVisit");
		screeningNum = properties.getProperty("SubjectNameCrStatus");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1251 Test Case Name: Site users should not be able
	 * to view the rater's details of a MedAvante Clinician
	 * =========================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1251_Site users should not be able to view the rater's details of a MedAvante Clinician ", groups = {
			"" })
	public void FPTC_1251_SiteUsersShouldNotBeAbleToViewTheRatersDetailsOfAMedAvanteClinician()
			throws InterruptedException {

		reportLog("1.2: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODAdminViewOnly, AT_Password);

		reportLog("1.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.4: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.5: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ATAssignedRater_10);

		reportLog("2.1: Select subject");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		reportLog("2.2: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("3.1: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("3.2: Select visit row with 'Scheduled' state");
		subjectDetailPage.clickOnVisitRow(visit_Scheduled);

		reportLog("3.3: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("3.4: Verify rater's name appears next to form thumbnail is not a hyperlink");
		subjectDetailPage.verifyRatersNameDisplayedIsNotHyprlink(Constants.clinician15Name);

		reportLog("4.1: Verify rater's name is disabled and can't be selected");
		subjectDetailPage.verifyRatersNotHyprlinkIsDisabled(Constants.clinician15Name);

		reportLog("5.1.1: Select visit row with 'Complete' state");
		subjectDetailPage.clickOnVisitRow(visit_Complete);

		reportLog("5.1.2: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("5.1.3: Verify rater's name appears next to form thumbnail is not a hyperlink");
		subjectDetailPage.verifySubmittedByRatersNameNonHyprlinkDisplayed(Constants.clinician15Name);

		reportLog("5.1.4: Verify rater's name is disabled and can't be selected");
		subjectDetailPage.verifySubmittedByRatersNameNonHyprlinkIsDisabled(Constants.clinician15Name);

		reportLog("5.2.1: Select visit row with 'Editing' state");
		subjectDetailPage.clickOnVisitRow(visit_Editing);

		reportLog("5.2.2: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("5.2.3: Verify rater's name appears next to form thumbnail is not a hyperlink");
		subjectDetailPage.verifySubmittedByRatersNameNonHyprlinkDisplayed(Constants.clinician15Name);

		reportLog("5.2.4: Verify rater's name is disabled and can't be selected");
		subjectDetailPage.verifySubmittedByRatersNameNonHyprlinkIsDisabled(Constants.clinician15Name);

		reportLog("6.1: Select form for assessments are listed as: " + Constants.ClinRO_Form_Label);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);

		reportLog("6.2: Verify Assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("6.3: Verify rater's name appears is not hyperlinked");
		assessmentDetailPage.verifyRaterNameUnderDetailsIsNonHyperlinked(Constants.RaterName_16);

		reportLog("7.1: Navigate to dashboard");
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("7.2: Navigate to study dashboard");
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("7.3: Select visit listing");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("7.4: Open Visit Details page for Subject");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit_Editing, screeningNum);

		reportLog("7.5: Verify Visit Details Page is opened");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("7.6: Verify rater's name appears next to form thumbnail is not a hyperlink");
		visitDetaiLPage.verifySubmittedByRatersNameNonHyprlinkDisplayed(Constants.RaterName_16);

		reportLog("User Logout the application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}
}
