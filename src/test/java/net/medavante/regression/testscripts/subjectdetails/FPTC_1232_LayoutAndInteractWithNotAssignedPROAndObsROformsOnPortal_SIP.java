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

public class FPTC_1232_LayoutAndInteractWithNotAssignedPROAndObsROformsOnPortal_SIP extends BaseTest {

	private String  Subject1, Subject2, visit_MltplForms;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1232_LayoutAndInteractWithNotAssignedPROAndObsROformsOnPortal_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		visit_MltplForms = properties.getProperty("Auto_Visit_VisitMltplForms");
		Subject1 = properties.getProperty("Subject806_1");
		Subject2 = properties.getProperty("Subject806_2");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1232 Test Case Name: Layout and interact with not
	 * assigned PRO and ObsRO forms on Portal
	 * =========================================================================
	 * 
	 * @throws Exception
	 * 
	 */

	@Test(description = "FP-TC-1232_Layout and interact with not assigned PRO and ObsRO forms on Portal", groups = {
			"" })
	public void FPTC_1232_LayoutAndInteractWithNotAssignedPROAndObsROformsOnPortal() throws Exception {

		reportLog("1.1: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: select " + studyName + " study.");
		 studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.5:Select Subject #1: " + Subject1);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				Subject1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(Subject1);

		reportLog("1.6:Verify Subject Details Page");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("1.7: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("1.8: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_MltplForms);

		reportLog("1.9: Verify Forms for assessments are listed as: " + Constants.Pro_Form_Label + " and "
				+ Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);

		reportLog("1.10: Verify Visits are assigned to rater :" + Constants.Observer1_Subject806);
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValueForConfiguredFormType(Constants.ObsRo_Form_Label,
				Constants.Observer1_Subject806);

		reportLog("1.11: Verify assessment doesn't display any assignment information");
		subjectDetailPage.verifyRaterAssignmentInfoNotAvailableForConfiguredFormType(Constants.Pro_Form_Label);

		reportLog("2.1: Navigate to study dashboard");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1:Select Subject #2: " + Subject2);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				Subject2);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(Subject2);

		reportLog("2.2:Verify Subject Details Page");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.3: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("2.4: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_MltplForms);

		reportLog("2.5: Verify Forms for assessments are listed as: " + Constants.Pro_Form_Label + " and "
				+ Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);

		reportLog("2.6: Verify assessment '" + Constants.ObsRo_Form_Label + "' is not assigned to rater ");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValueForConfiguredFormType(Constants.ObsRo_Form_Label,
				Constants.notAssignedText);

		reportLog("2.7: Verify assessment doesn't display any assignment information");
		subjectDetailPage.verifyRaterAssignmentInfoNotAvailableForConfiguredFormType(Constants.Pro_Form_Label);

		reportLog("3.1: Verify Raters are available for assessment: " + Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyRaterAvailableUnderDropdownForConfiguredFormType(Constants.ObsRo_Form_Label,
				Constants.Observer1_Subject806);
		subjectDetailPage.verifyRaterAvailableUnderDropdownForConfiguredFormType(Constants.ObsRo_Form_Label,
				Constants.Observer2_Subject806);

		reportLog("4.1: Assign observer to :" + Constants.ObsRo_Form_Label);
		subjectDetailPage.selectRaterFromDropDownForMultipleConfiguredFormType(Constants.ObsRo_Form_Label,
				Constants.Observer2_Subject806);

		reportLog("5.1: Click on '" + Constants.ObsRo_Form_Label + "' form thumbnail");
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ObsRo_Form_Label);

		reportLog("5.2: Verify Assesment Detail Page Displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("6.1: Navigate back to subject details page");
		assessmentDetailPage.navigateBack();

		reportLog("6.2: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("6.3: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_MltplForms);

		reportLog("6.4: Verify Form for assessment is displayed as : " + Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);

		reportLog("6.5: Remove assignment from 'ObsRO' assessment");
		subjectDetailPage.selectRaterFromDropDownForMultipleConfiguredFormType(Constants.ObsRo_Form_Label,
				Constants.notAssignedText);

		reportLog("7.1: Click on '" + Constants.ObsRo_Form_Label + "' form thumbnail");
		subjectDetailPage.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ObsRo_Form_Label);

		reportLog("7.2: Verify PDF template for form is opened in a new window");
		String ParrentWin = switchToChildWindow();
		subjectDetailPage.verifyFormOpenInPDFtemplate();
		switchParentWindowByClosingChild(ParrentWin);

		reportLog("7.3: Verify Visit list is displayed after reverting on parent window.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("8.1: Click on '" + Constants.Pro_Form_Label + "' form thumbnail");
		subjectDetailPage.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.Pro_Form_Label);

		reportLog("8.2: Verify PDF template for form is opened in a new window");
		switchToChildWindow();
		subjectDetailPage.verifyFormOpenInPDFtemplate();
		switchParentWindowByClosingChild(ParrentWin);

		reportLog("8.3: Verify subject Detail page is displayed after reverting on parent window.");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("8.4: Logout application");
		loginPage.logoutApplication();

		reportLog("8.5: Verify user is Logout from application");
		loginPage.verifyUserLogout();

	}
}
