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

public class FPTC_1241_DeleteSubjectVisit_SIP extends BaseTest {

	private String studyNameNonCR, visit_MltplForms, visit_ObsroForms, visit_ProForms, visit_ClinROForms;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1241_DeleteSubjectVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyNameNonCR = properties.getProperty("AutomationStudyNameNonCR");
		visit_MltplForms = properties.getProperty("Auto_Visit_VisitMltplForms");
		visit_ObsroForms = properties.getProperty("Auto_Visit_ObsroForms");
		visit_ProForms = properties.getProperty("Auto_Visit_ProForms");
		visit_ClinROForms = properties.getProperty("Auto_Visit_ClinROForms");
		screeningNum = properties.getProperty("SubjectName918");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1241 Test Case Name: Delete subject visit
	 * =========================================================================
	 */

	@Test(description = "FP-TC-1241_Delete subject visit", groups = { "" })
	public void FPTC_1241_DeleteSubjectVisit() {

		reportLog("1.1: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR, Constants.ATAssignedRater_10);

		reportLog("1.5: Select subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum);

		reportLog("1.6: Verify Subject details page is opened");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("1.7: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("2.1: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_ObsroForms);

		reportLog("2.2: Verify Forms for assessments are listed as: " + Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);

		reportLog("2.3: Verify Visit is assigned to Obserever");
		//subjectDetailPage.verifyObserverSelectedValue(Constants.Observer_Subject918);
		//subjectDetailPage.verifyScaleAndScaleTypeConfigured(AT_PRODSiteCoordinatorCRSelfSchedule);

		reportLog("2.4: Verify Delete button is not available");
		subjectDetailPage.verifydeleteVisitIconIsNOtDisplayed();

		reportLog("3.1: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_ClinROForms);

		reportLog("3.2: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("3.3: Verify Visit is assigned to rater");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(AT_PRODSiteCoordinatorUserName);

		reportLog("3.4: Verify Delete button is not available");
		subjectDetailPage.verifydeleteVisitIconIsNOtDisplayed();

		reportLog("4.1: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_ProForms);

		reportLog("4.2: Verify Forms for assessments are listed as: " + Constants.Pro_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);

		reportLog("4.3: Verify Visit status is Complete.");
		subjectDetailPage.verifyVisitStatus(visit_ProForms, Constants.Complete_Status);

		reportLog("4.4: Verify Delete button is not available");
		subjectDetailPage.verifydeleteVisitIconIsNOtDisplayed();

		reportLog("5.1: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_MltplForms);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("5.2: Verify Forms for assessments are listed as: " + Constants.Pro_Form_Label + ", "
				+ Constants.ClinRO_Form_Label + " and " + Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);

		reportLog("5.3: Verify Visits are not assigned to rater");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValueForConfiguredFormType(Constants.ClinRO_Form_Label,
				Constants.notAssignedText);
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValueForConfiguredFormType(Constants.ObsRo_Form_Label,
				Constants.notAssignedText);

		reportLog("5.4: Delete Added Visit");
		subjectDetailPage.deleteAddedVisit();

		reportLog("5.5: Verify add visit icon is available");
		subjectDetailPage.verifyAddVisitIconIsDisplayed();

		reportLog("6.1: Logout application");
		loginPage.logoutApplication();

		reportLog("6.2: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

	}
}
