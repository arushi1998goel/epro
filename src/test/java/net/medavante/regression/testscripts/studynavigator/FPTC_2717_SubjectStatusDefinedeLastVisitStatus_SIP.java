package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2717_SubjectStatusDefinedeLastVisitStatus_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2717_SubjectStatusDefinedeLastVisitStatus_SIP(String browser) {
		super(browser);
	}

	protected String subjectName_2088 = "SUBJ_" + generateRandomString(5);
	private String visit_screened, visit_enrolled, visit_unscheduled, visit_completed;

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
		visit_screened = properties.getProperty("visit_screened");
		visit_enrolled = properties.getProperty("visit_enrolled");
		visit_unscheduled = properties.getProperty("visit_unscheduled");
		visit_completed = properties.getProperty("visit_completed");

		reportLog("Creating a subject for the Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		// StudyDashBoardPage studyNavigatorDashBoardPage =
		// dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName_2088);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Configure the visit as screened and verify the status of Subject according to the visit");
		subjectDetailPage.clickOnVisitRow(visit_screened);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		assessmentDetailPage.setStartedDate();

		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.clickOnSubjectLink();
		assessmentDetailPage.verifySubjectStatusDisplayedAsReadOnlyLabel(Constants.SubjectStatus_Screened);

		reportLog("Configure the visit as enrolled and verify the status of Subject according to the visit");
		subjectDetailPage.clickOnVisitRow(visit_enrolled);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		assessmentDetailPage.setStartedDate();

		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.clickOnSubjectLink();
		assessmentDetailPage.verifySubjectStatusDisplayedAsReadOnlyLabel(Constants.SubjectStatus_Enrolled);

		reportLog("Click on completed status  visit row and verify the subject status as complete");
		subjectDetailPage.clickOnVisitRow(visit_completed);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		assessmentDetailPage.setStartedDate();

		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.clickOnSubjectLink();
		assessmentDetailPage.verifySubjectStatusDisplayedAsReadOnlyLabel(Constants.SubjectStatus_Completed);
		loginPage.logoutApplication();
	}

	/**
	 * ===============================================================================================================================
	 * Test Case Id: FP-TC-2717 || Test Case Name: Subject status should be based on
	 * visit order. The last Unscheduled visit with Discontinued status completion
	 * ===============================================================================================================================
	 * 
	 * 
	 */

	@Test
	public void FPTC_2717_SubjectStatusDefinedeLastVisitStatus() {

		reportLog("1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.reSelectstudy(studyName);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.2navigate to subject List");
		studyNavigatorDashBoardPage.clickOnSubject(subjectName_2088);

		reportLog("   2. Navigate to Subject Details page  and verify Subject Status is Discontinued");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();
		subjectDetailPage.selectUnscheduledVisit(visit_unscheduled);
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		assessmentDetailPage.setStartedDate();

		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.clickOnSubjectLink();
		assessmentDetailPage.verifySubjectStatusDisplayedAsReadOnlyLabel(Constants.Status_Discontinued);

		reportLog("2.1: Logout application");
		loginPage.logoutApplication();

		reportLog("2.2: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

	}
}
