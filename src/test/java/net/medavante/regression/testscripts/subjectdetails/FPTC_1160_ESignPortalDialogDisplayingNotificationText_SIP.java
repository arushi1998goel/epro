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

public class FPTC_1160_ESignPortalDialogDisplayingNotificationText_SIP extends BaseTest {

	private String studyNameNonCR, visit_ClinROForms;
	private String subjectName1 = "Auto_Screening_" + generateRandomString(3) + getRandomInteger(0, 9);
	private String subjectName2 = "Auto_Screening_" + generateRandomString(3) + getRandomInteger(0, 9);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1160_ESignPortalDialogDisplayingNotificationText_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());

		Properties properties = Configuration.readTestData("RegressionTestData");
		studyNameNonCR = properties.getProperty("AutomationStudyNameNonCR");
		visit_ClinROForms = properties.getProperty("Auto_Visit_ClinROForms");

		reportLog("PR1: Creating Subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyNameNonCR, Constants.ATAssignedRater_10,subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("PR2: Schedule a visit and assign a rater");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_ClinROForms);
		subjectDetailPage.clickOnAddVisitIcon();
		
		reportLog("Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		subjectDetailPage.selectRaterFromDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label,
				AT_PRODSiteCoordinatorUserName);

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1160 Test Case Name: E-Sign Portal Dialog.
	 * Displaying notification text
	 * =========================================================================
	 */

	@Test(description = "FP-TC-1160_E-Sign Portal Dialog. Displaying notification text ", groups = { "" })
	public void FPTC_1160_ESignPortalDialogDisplayingNotificationText() {

		reportLog("1.1: Login to Site Portal");
		// dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator,
		// AT_Password);
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("1.4: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR, Constants.ATAssignedRater_10);

		reportLog("1.5: Select subject");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName1);

		reportLog("1.6: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName1);

		reportLog("2.1: Verify Subject Edit Btn is enable");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("2.2: Click on Subject details edit icon");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("2.3: Update Screening no in Edit subject modal window");
		subjectDetailPage.clearScreeningInp();
		subjectDetailPage.inputScreeningNumber(subjectName2);

		reportLog("2.4: Click Edit subject Save Button");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("2.5 Verify reason for change popup appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("2.6 Verify Reason For Change Authentication Text is displayed");
		subjectDetailPage.verifyReasonForChangeAuthenticationText(Constants.ReasonForChange_AuthenticationMessage);

		reportLog("3.1 Select " + Constants.Subject_Reason_For_Change + " Reason for change");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);

		reportLog("3.2 complete e-sign and save");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("3.3 Verify New changes are applied to the Subject name");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName2);
		subjectDetailPage.clickOnVisitRow(visit_ClinROForms);

		reportLog("4.1: Verify Forms for assessments are listed as: " + Constants.ClinRO_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);

		reportLog("4.2 Verify Assesment Detail Page Displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("4.3 Verify checkBox For Not Administered is present");
		assessmentDetailPage.verifyCheckBoxIsPresent();

		reportLog("4.4 Set CheckBox As Not Administered");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();

		reportLog("4.5 Verify CheckBox Is Set");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();

		reportLog("4.6 Confirm Button Appears");
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("4.7:Click On Confirm Button");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("4.8 Confirmation Window Appears");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog("4.9:Press Yes Control From Confirmation");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("4.10:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("4.11 Verify Reason For Change Authentication Text is displayed");
		assessmentDetailPage.verifyReasonForChangeAuthenticationText(Constants.ReasonForChange_AuthenticationMessage);

		reportLog("5.1:Select 'Reason for Change', enter username and password and press OK control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog(
				"5.2: Verify Assessment receives 'Complete' status  Assessment form thumbnail changed accordingly to 'Not administered' option");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("5.3  Verify CheckBox Span Text Changed As Not Administered");
		assessmentDetailPage.verifyAssesmentCheckBoxChangedAccordinglyNotAdministered();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
