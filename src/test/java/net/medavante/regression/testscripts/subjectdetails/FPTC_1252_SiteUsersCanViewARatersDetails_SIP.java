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

public class FPTC_1252_SiteUsersCanViewARatersDetails_SIP extends BaseTest {

	private String studyNameNonCR, visit_Pending, visit_Complete, visit_Editing;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1252_SiteUsersCanViewARatersDetails_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyNameNonCR = properties.getProperty("AutomationStudyNameNonCR");
		visit_Pending = properties.getProperty("Auto_Visit_ClinROForms");
		visit_Complete = properties.getProperty("Auto_Visit1781");
		visit_Editing = properties.getProperty("Visit_Screened_1682NonCR");
		screeningNum = properties.getProperty("SubjectName1791");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1252 Test Case Name: Subject Details - Site users
	 * can view a rater's details
	 * =========================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1252_Site users can view a rater's details", groups = { "" })
	public void FPTC_1252_SiteUsersCanViewARatersDetails() throws InterruptedException {

		reportLog("1.2: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.4: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.5: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.RaterName_21);

		reportLog("2.1: Select subject");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		reportLog("2.2: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		// ==================================================
		/**
		 * Incorrect TC-3 and TC-4
		 */
		// ==================================================

		reportLog("5.1.1: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("5.1.2: Select visit row with 'Complete' state");
		subjectDetailPage.clickOnVisitRow(visit_Complete);

		reportLog("5.1.3: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("5.1.4: Verify rater's name appears as hyperlinked next to form thumbnail");
		subjectDetailPage.verifyRatersHyprlinkDisplayed(Constants.RaterName_22);

		reportLog("5.1.5: Select rater's name appears as hyperlinked next to form thumbnail");
		ratersDetailsPage = subjectDetailPage.clickOnRatersLink(Constants.RaterName_22);

		reportLog("5.1.6: Verify Rater's details page is visible");
		ratersDetailsPage.verifyRaterDetailPageDisplayed();

		reportLog("5.1.7: Verify Rater's name under details");
		ratersDetailsPage.verifyRaterName(Constants.RaterName_22);

		reportLog("5.1.8: Navigate to Subject details page");
		ratersDetailsPage.navigateBack();

		reportLog("5.2.1: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("5.2.2: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("5.2.3: Select visit row with 'Editing' state");
		subjectDetailPage.clickOnVisitRow(visit_Editing);

		reportLog("5.2.4: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("5.2.5: Verify rater's name appears as hyperlinked next to form thumbnail");
		subjectDetailPage.verifyRatersHyprlinkDisplayed(Constants.RaterName_22);

		reportLog("5.2.6: Select rater's name appears as hyperlinked next to form thumbnail");
		subjectDetailPage.clickOnRatersLink(Constants.RaterName_22);

		reportLog("5.2.7: Verify Rater's details page is visible");
		ratersDetailsPage.verifyRaterDetailPageDisplayed();

		reportLog("5.2.8: Verify Rater's name under details");
		ratersDetailsPage.verifyRaterName(Constants.RaterName_22);

		reportLog("6.1: Navigate to Subject details page");
		ratersDetailsPage.navigateBack();

		reportLog("6.2: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("6.3: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("6.4: Select visit row with 'Pending' state");
		subjectDetailPage.clickOnVisitRow(visit_Pending);

		reportLog("6.5: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("6.6: Select form for assessments are listed as: " + Constants.ClinRO_Form_Label);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);

		reportLog("6.7: Verify Assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("6.8: Verify rater's name appears as hyperlinked");
		assessmentDetailPage.verifyRaterNameDisplayedasHyperlink(Constants.RaterName_22);

		reportLog("7.1: Navigate to dashboard");
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("7.2: Navigate to study dashboard");
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("7.3: Select visit listing");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("7.4: Open Visit Details page for Subject");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit_Complete, screeningNum);

		reportLog("7.5: Verify Visit Details Page is opened");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("7.6: Verify rater's name appears as hyperlinked next to form thumbnail");
		visitDetaiLPage.verifyRatersLinkDisplayed(Constants.RaterName_22);

		reportLog("User Logout the application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}
}
