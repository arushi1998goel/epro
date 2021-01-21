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

public class FPTC_1244_AddUnscheduledVisit_SIP extends BaseTest {

	private String  studyName, Subject, visit1, visit2;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1244_AddUnscheduledVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		visit1 = properties.getProperty("Auto_Visit1781");
		visit2 = properties.getProperty("Visit_Discontinued_1682NonCR");
		Subject = properties.getProperty("Subject1127");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1244 Test Case Name: Add unscheduled visit
	 * =========================================================================
	 */

	@Test(description = "FP-TC-1244_Add unscheduled visit ", groups = { "" })
	public void FPTC_1244_AddUnscheduledVisit() throws Exception {

		reportLog("1.1: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: select " + studyName + " study.");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.5:Select Subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				Subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(Subject);

		reportLog("1.6:Verify Subject Details Page");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("1.7: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("2.1: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit1);

		reportLog("2.2: Verify visit status");
		subjectDetailPage.verifyVisitStatus(visit1, Constants.Complete_Status);

		reportLog("2.3: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("2.3: Select Form for assessment");
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);

		reportLog("2.4 Verify Assesment Detail Page Displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.5 Uncheck Not Administered CheckBox");
		assessmentDetailPage.uncheckNotAdministeredCheckBox();

		reportLog("2.6 Confirm Button Appears");
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("2.7:Click On Confirm Button");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("2.8:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("2.9:Select 'Reason for Change', enter username and password and press OK control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("2.10: Verify Assessment receives 'Complete' status ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("2.11: Navigate to Subject Details Page");
		assessmentDetailPage.navigateBack();

		reportLog("2.12:Verify Subject Details Page");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.13: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("2.14: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit1);

		reportLog("2.15: Select edit visit icon");
		subjectDetailPage.clickOnEditVisitIcon();

		reportLog("2.16: Verify Visit Status is Editing ");
		subjectDetailPage.verifyVisitStatus(visit1, Constants.Editing_Status);

		reportLog("3.1: Verify Option to add Unscheduled visits is available");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();

		reportLog("3.2: Select the option to add the unscheduled visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("3.3: verify Unscheduled Visit List is Displayed");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("3.4: verify Editing visit is not displayed in unscheduled visit list");
		subjectDetailPage.verifyVisitNotContainsInUnscheduledVisitList(visit1);

		reportLog("3.5: verify Unscheduled visit is displayed in visit list");
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(visit2);

		reportLog("4.1: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit1);

		reportLog("4.2: Verify Visit Status is Editing ");
		subjectDetailPage.verifyVisitStatus(visit1, Constants.Editing_Status);

		reportLog("4.2: Select Cancel visit icon");
		subjectDetailPage.clickOnCancelVisitIcon();

		reportLog("4.2: Verify Visit Status is Editing ");
		subjectDetailPage.verifyVisitStatus(visit1, Constants.Complete_Status);

		reportLog("5.1: Verify Option to add Unscheduled visits is available");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();

		reportLog("5.2: Select the option to add the unscheduled visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("5.3: verify Unscheduled Visit List is Displayed");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("5.4: verify Unscheduled Visit List contains ");
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(visit1);

		reportLog("5.5: verify Unscheduled Visit List contains ");
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(visit2);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
