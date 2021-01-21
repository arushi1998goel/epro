
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

public class FPTC_2746_SubjectStatusPossibilityToChangeStatusAfterVisitCompletion_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2746_SubjectStatusPossibilityToChangeStatusAfterVisitCompletion_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("Test741Study");
		newVisitName = prop.getProperty("visitForRenameNewStatus");
		screenedVisitName = prop.getProperty("visitForRenameScreenedStatus");
		enrolledVisitName = prop.getProperty("visitForRenameEnrolledStatus");
		completedVisitName = prop.getProperty("visitForRenameCompletedStatus");
		discontinuedVisitName = prop.getProperty("visitForRenamediscontinuedStatus");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-2746 || Test Case Name: Subject Status - Possibility
	 * to change status after visit completion
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-2717_SubjectStatusShouldbeBasedOnVisitOrder")

	public void FPTC_2746_verifySubjectStatusPossibilityToChangeStatusAfterVisitCompletion() {

		reportLog("Create Subject for setting up the Pre-Requisite");

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:Homepage is displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.4:Create subject New");
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("1.5: Subject Status has New Status and it is NOT able to change");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_New);
		subjectDetailPage.clickOnSubjectEdtingIcon();

	    subjectDetailPage.verifySubjectStatusDropDownDisable();
		subjectDetailPage.clickOnCancelBtn();
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("2.1:Create subject with Screened");
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectWithScreennedStatusName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("2.2:Completing Screened Visit");
		subjectDetailPage.completingVisitByMarkAsNotCompleting(screenedVisitName, SuperAdminUN, SuperAdminPW);

		reportLog("2.3: Visit is successfully completed");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Screened);
		subjectDetailPage.verifyVisitStatus(screenedVisitName, Constants.Complete_Status);
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("3.1: Change Subject Status to Enrolled");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Enrolled);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);

		reportLog("3.2:Subject Status has Enrolled Status and Visit Status is Completed");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Enrolled);
		subjectDetailPage.verifyVisitStatus(screenedVisitName, Constants.Complete_Status);

		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("4.1:Create subject with Enrolled Status");
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectWithEnrolledStatusName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("4.2:Completing Enrolled Visit");
		subjectDetailPage.completingVisitByMarkAsNotCompleting(enrolledVisitName, SuperAdminUN, SuperAdminPW);

		reportLog("4.3:Navigate to Subject Details screen with PR#3.4 and change Subject Status to Completed");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		subjectDetailPage.selectStatus(Constants.SubjectStatus_Completed);
		subjectDetailPage.clickOnSaveBtn();

		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);

		reportLog("4.4: Subject Status has Completed Status and Visit Status is Completed");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Completed);
		subjectDetailPage.verifyVisitStatus(enrolledVisitName, Constants.Complete_Status);

		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("5.1:Create subject with Completed Status");
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectCompletedStatusName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("5.2:Completing  Completed Visit");
		subjectDetailPage.completingVisitByMarkAsNotCompleting(completedVisitName, SuperAdminUN, SuperAdminPW);

		reportLog("5.3:Navigate to Subject Details screen with PR#3.5 and change Subject Status to Screened");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Screened);
		subjectDetailPage.clickOnSaveBtn();

		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);

		reportLog("5.4: Subject Status has Screened Status and Visit Status is Completed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Screened);
		subjectDetailPage.verifyVisitStatus(completedVisitName, Constants.Complete_Status);
		
		reportLog("6.1: Edit mode and expand the drop-down with statuses on the Portal and Screened, Screen Failed, Enrolled and Discontinued statuses are shown on the Portal");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.verifySubjectStatusesArePresentInStatusChangeDropDownList(Constants.Subjects_FilterListUnderSubjectStatusWithoutNew);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		
		reportLog("8.1: Create subject screenFailed status");
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectScreenFailedName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("8.2:Completing  Completed Visit");
		subjectDetailPage.completingVisitByMarkAsNotCompleting(completedVisitName, SuperAdminUN, SuperAdminPW);

		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnSaveBtn();

		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);

		reportLog("8.3: Subject status is Screen Failed and Visit status is Completed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.verifyVisitStatus(completedVisitName, Constants.Complete_Status);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("9.1: Create subject discontinued status");
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectDisconutinedStatusName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Completing unscheduled Visit");
		subjectDetailPage.clickOnVisitRow(completedVisitName);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();
		subjectDetailPage.selectUnscheduledVisit(discontinuedVisitName);
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setCurrentDate();		
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.waitForProcessingVisitToBeCompleted();

		reportLog("9.2: Subject Status has Discontinued Status and Visit Status is Completed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_Discontinued);
		subjectDetailPage.verifyVisitStatus(discontinuedVisitName, Constants.Complete_Status);

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
