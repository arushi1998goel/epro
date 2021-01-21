package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1234_LayoutSubmittedFormsWithOverridesAndSkippedQuestions_SIP extends BaseTest {

	private String visit_Pro, visit_Obsro, visit_Clinro, SkippedQuestionCount = "1", RaterName = "1 (test)";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1234_LayoutSubmittedFormsWithOverridesAndSkippedQuestions_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		subjectName = properties.getProperty("SubjectNum818");
		visit_Pro = properties.getProperty("Auto_Visit_ProForms");
		visit_Obsro = properties.getProperty("Auto_Visit_ObsroForms");
		visit_Clinro = properties.getProperty("Visit_Screened_1682NonCR");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1234 Test Case Name:Layout submitted forms with
	 * 'Overrides' and 'Skipped Questions'
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1234_Layout submitted forms with 'Overrides' and 'Skipped Questions'", groups = { "" })

	public void FPTC_1234_LayoutSubmittedFormsWithOverridesAndSkippedQuestions() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("1.3: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("1.4:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("1.5:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("1.6: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("1.7: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_Pro);

		reportLog("1.8: Verify Forms for assessments are listed as: " + Constants.Pro_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);
		
		/* hold due to Tablet App issue*/
		
//		reportLog("1.9: Verify Labels 'Submitted by displayed as: " + Constants.RaterName_21);
//		subjectDetailPage.verifySubmittedByRatersNameNonHyprlinkDisplayed(Constants.RaterName_21);
//
//		reportLog("1.10: Verify 'Skipped Questions' label with number of skipped questions are displayed.");
//		subjectDetailPage.verifyAdditionalDetailsBelowSubmittedDateDisplayedUnderAssessmentDetails(
//				Constants.AdditionalDetailsUnderBelowSubmittedDateUnderAssessmentDetails_SkippedQuestion,
//				SkippedQuestionCount);

		reportLog("2.1:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.2: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("2.3: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_Obsro);

		reportLog("2.4: Verify Forms for assessments are listed as: " + Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);

		reportLog("2.5: Verify Labels 'Submitted by displayed as: " + RaterName);
		subjectDetailPage.verifySubmittedByRatersNameNonHyprlinkDisplayed(Constants.ATAssignedRater_13);

		/*reportLog("2.6: Verify 'Skipped Questions' label with number of skipped questions are displayed.");
		subjectDetailPage.verifyAdditionalDetailsBelowSubmittedDateDisplayedUnderAssessmentDetails(
				Constants.AdditionalDetailsUnderBelowSubmittedDateUnderAssessmentDetails_SkippedQuestion,
				SkippedQuestionCount);*/

		reportLog("3.1:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("3.2: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("3.3: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_Clinro);

		reportLog("3.4: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("3.5: Verify Labels 'Submitted by displayed as: " + Constants.ATAssignedRater_10);
		subjectDetailPage.verifySubmittedByRatersNameNonHyprlinkDisplayed(Constants.ATAssignedRater_10);

		reportLog("3.6: Verify  'Overrides' with number of overrides are displayed.");
		subjectDetailPage.verifyAdditionalDetailsBelowSubmittedDateDisplayedUnderAssessmentDetails(
				Constants.AdditionalDetailsUnderBelowSubmittedDateUnderAssessmentDetails_Override,
				SkippedQuestionCount);

		reportLog("3.7: Logout application");
		loginPage.logoutApplication();

		reportLog("3.8: Verify user is at logout Page");
		loginPage.verifyUserLogout();
	}
}
