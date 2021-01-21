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

public class FPTC_1527_MoveAssessWithDifferentFormsLang_SIP extends BaseTest {

	private String VisitCompleted, visitNotAssigned, subjectName = "Auto2188" + generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1527_MoveAssessWithDifferentFormsLang_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		VisitCompleted = properties.getProperty("visitClinRoSubmitted");
		visitNotAssigned = properties.getProperty("VisitRussianFormLangguage");

		/*-------------------- Creating Subject and Configuring Not Completed Assessment--------------------- */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		/* Subject Created Successfully */

		/*-----------------Completing Assessment-------------------------------*/
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(VisitCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);
		
		assessmentDetailPage = subjectDetailPage.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		loginPage.logoutApplication();

	}

	/**
	 * ===================================================================================================================
	 * Test Case Id: FP-TC-1527 Test Case Name: Move Assessment with different
	 * form's language
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1527 Move Assessment with different form's language", groups = { "" })
	public void FPTC_1527_VerifyMoveAssessWithDifferentFormsLang() {

		reportLog("1.1:Log in to the Portal as a User from Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User from PR#1 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3:Open the Study Navigator-> Select a Study and a Site from PR#2 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2.1: Navigate to the Assessment Listing screen and select the  Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitCompleted, subjectName);

		reportLog("2.2:Visit Assessment screen from PR#3 is displayed  ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.1:Action option is displayed for the Assessment ");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("3.2:Select - Action to Change Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("3.3:Check that Visit Pr.#4 isn't displayed in Move To Visit drop-down list ");
		reportLog("3.4:Assessment from PR#4 isn't displayed in Move To Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitNotAssigned);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
