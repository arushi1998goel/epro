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

public class FPTC_1150_SubjectDetailsViewAssessments_SIP extends BaseTest {

	private String studyNameNonCR, visitName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1150_SubjectDetailsViewAssessments_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());

		Properties properties = Configuration.readTestData("RegressionTestData");
		studyNameNonCR = properties.getProperty("AutomationStudyNameNonCR");
		visitName = properties.getProperty("Auto_Visit_VisitMltplForms");
		screeningNum = properties.getProperty("SubjectName1547");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1150 Test Case Name: Subject Details - View
	 * assessments
	 * =========================================================================
	 */

	@Test(description = "FP-TC-1150_Subject Details - View assessments", groups = { "" })
	public void FPTC_1150_SubjectDetailsViewAssessments() {

		reportLog("1.2: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.4: Navigate to study dashboard");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.5: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ATAssignedRater_10);

		reportLog("1.6: Select subject");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		reportLog("1.7: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("1.8: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("1.9: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("1.10: Verify Forms for assessments are listed as: " + Constants.Pro_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);

		reportLog("1.11: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("1.12: Verify Forms for assessments are listed as: " + Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);

		reportLog("1.13: Logout application");
		loginPage.logoutApplication();

		reportLog("1.14: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

	}
}
