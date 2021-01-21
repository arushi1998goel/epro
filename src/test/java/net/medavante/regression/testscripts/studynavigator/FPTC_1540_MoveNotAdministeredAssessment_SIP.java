package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1540_MoveNotAdministeredAssessment_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String subject_NotAdministered_NonCR = "SUBJ_" + generateRandomString(4);

	protected String subjectName1 = "test7";
	private String visitName1, visitName, moveToAssesment, changeassesment, optionToBeVerify;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1540_MoveNotAdministeredAssessment_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2180");
		visitName = properties.getProperty("visitName4");
		visitName1 = properties.getProperty("visitName");
		optionToBeVerify = properties.getProperty("changeassesment");

		reportLog("Creating a subject for the Study Site (Pr#1),");

		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.RaterName_21);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("Creating a subject for (Pr#3");
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.RaterName_21);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputScreeningNum(subject_NotAdministered_NonCR);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);

		reportLog("Click on Assessment link");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
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
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		loginPage.logoutApplication();

	}
	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1540 Test Case Name: Move Not Administered Assessment 
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1540 MoveNotAdministeredAssessment_SIP", groups = { "" })
	public void FPTC_1540_VerifyMoveNotAdministeredAssessment_SIP() {

		reportLog("1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		//dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.RaterName_21);

		reportLog("1.1 Search visit1 and subject in assessment page");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subject_NotAdministered_NonCR);

		reportLog("1.2: Subject details page is visible");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("1.3: Select Form for assessment");
		assessmentDetailPage = subjectDetailPage
				.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);

		reportLog("1.4:Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(optionToBeVerify);

		reportLog("2.1 Select option of change assesment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("2.2 Select the Subject from dropdown in change assesment section");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName);

		reportLog("2.3 Select the visit from dropdown in change assesment section");
		assessmentDetailPage.changeVisitDropDown(visitName);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("2.4 Click on Confirm yes button");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("2.5 : Enter all details for reason for change");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("2.6 Verify the assesment has been changed");
		assessmentDetailPage.verifyAssesmentChanged();
		
		reportLog("2.7  Click on close icon");
		assessmentDetailPage.closeAssesmentSuccessMessage();
		
		reportLog("3.1:Logout application");
		loginPage.logoutApplication();

		reportLog("3.2:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
