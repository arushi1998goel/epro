package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.Constants;

public class FPTC_1474_VisitChangedToSkippedNullVisit_SIP extends BaseTest {
	private String visitCompleted, visitOutDatedFormName, subjectName = "Auto2236" + generateRandomString(4),
			visitForSkiiping;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1474_VisitChangedToSkippedNullVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Test741Study");
		visitCompleted = properties.getProperty("VisitCompleted");
		visitOutDatedFormName = properties.getProperty("VisitOutDatedForm");
		visitForSkiiping = properties.getProperty("Visit1678ThreeForms");

		/*-----------------Creating Subject For Configuring Pre-Requisite---------------*/
		reportLog("1:Login To Application And Setting Up The Pre-Requisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		/*------------------Subject Created Successfully----------------------------*/

		/*-----------------Completed ClinRo Visit With Latest Form Version-------------*/
		subjectDetailPage.clickOnVisitRow(visitCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);
		subjectDetailPage.clickOnVisitRow(visitForSkiiping);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitCompleted, subjectName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();

	}

	/**
	 * ===================================================================================================================
	 * Test Case Id: FP-TC-1474 Test Case Name: Change Visit - Visit can be
	 * changed to Skipped/Null Visit when Study Form has one actual and one out
	 * of date Version
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1474_Change Visit - Visit can be changed to Skipped/Null Visit when Study Form has one actual and one out of date Version", groups = {
			"" })
	public void FPTC_1474_VerifyVisitChangedToSkippedNullVisit() {
		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("2.2:	Navigate to Visit Listing screen");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.3:	select Visit from Pr.#3");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitCompleted, subjectName);

		reportLog("2.4:Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("2.5:Action' option is displayed  ");
		visitDetaiLPage.verifyActionButtonIsDisplayed();

		reportLog("3.1:Select action to moving a Visit ");
		visitDetaiLPage.selectActionToMoveVisit();

		reportLog("3.2:Select the Visit from Pr.#4 in Move To Filter");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName);
		visitDetaiLPage.clickOnChangeToVisitDropDown();
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(visitOutDatedFormName);
		visitDetaiLPage.selectChangeToVisit(visitOutDatedFormName);

		reportLog("3.4:Visit from Pr.#4 is selected");
		visitDetaiLPage.verifyChangeToVisitSelected(visitOutDatedFormName);

		reportLog("4.1:Select an action to Save the changes (Control Save) ");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("4.2:Select an action to Confirm the changes (Control Confirm) ");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();

		reportLog("4.3:Check that Reason for Change window appears after the moving has been confirmed");
		reportLog("4.4:Reason for Change window appears after the moving has been confirmed");
		visitDetaiLPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("5.1:Select the Reason from drop-down list");
		visitDetaiLPage.selectReasonForChangeOption(Constants.Visit_Move_TO_IncorrectAdministered_Option);

		reportLog("5.2:Enter the credentials ");
		visitDetaiLPage.inputReasonComment(generateRandomString(2));

		reportLog("5.3:Select control to sign the Reasons for Change ('Ok')");
		visitDetaiLPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("5.4:Visit successfully moved ");
		visitDetaiLPage.verifySuccessMessage(ApplicationVerificationMessage.moveVisitSuccessfulMessage);
		visitDetaiLPage.closeSuccessMessage();

		reportLog("5.5:Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("5.6:Logout from the application");
		loginPage.logoutApplication();

		reportLog("5.7:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
