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

public class FPTC_2764_SubjectStatusCountStatusChangeTotalCount_SIP extends BaseTest {

	private String  visitScreened, visitEnrolled, visitCompleted, visitScreenedFailed,
			visitDiscontinue, subjectNew, subjectScreened = "Auto2065Screened"+generateRandomAlphanumericString(5),
			subjectScreenFailed = "Auto2065ScreenedFailed"+generateRandomAlphanumericString(5),
			subjectEnrolled = "Auto2065Enrolled"+generateRandomAlphanumericString(5),
			subjectCompleted = "Auto2065Completed"+generateRandomAlphanumericString(5),
			subjectDiscontinued = "Auto2065SubjectDisContinued"+generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2764_SubjectStatusCountStatusChangeTotalCount_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("tabletFormSettingStudy");
		subjectNew = properties.getProperty("SubjectNew2065");
		visitScreened = properties.getProperty("Auto_ScreenedStatusVisit");
		visitEnrolled = properties.getProperty("Auto_EnrolledStatusVisit");
		visitCompleted = properties.getProperty("Auto_CompletedStatusVisit");
		visitScreenedFailed = properties.getProperty("Auto_CompletedStatusVisit");
		visitDiscontinue = properties.getProperty("Auto_DiscontinuesStatusVisit");
		
		/*--------------Configuring Screened Subject Status-------------*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectScreened);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitScreened,SuperAdminUN, SuperAdminPW);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		/*--------------Configuring Enrolled Subject Status------------*/
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectEnrolled);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitEnrolled,SuperAdminUN, SuperAdminPW);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		/*--------------Configuring Complete Subject Status-------------*/
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectCompleted);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitCompleted,SuperAdminUN, SuperAdminPW);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		/*--------------Configuring Screened Failed Subject Status-------------*/
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectScreenFailed);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitScreenedFailed,SuperAdminUN, SuperAdminPW);	
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		/*--------------Configuring Discontinue Subject Status-------------*/
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectDiscontinued);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();
		subjectDetailPage.selectUnscheduledVisit(visitDiscontinue);
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-2764 Test Case Name:Subject Status Count - Status
	 * change - Total count
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-2764_Subject Status Count - Status change - Total count", groups = { "R1" })
	public void FPTC_2764_verifySubjectStatusCountStatusChangeTotalCount() {

		reportLog("1.1:Log in to Portal as User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2:Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog(
				"1.3:Navigate to Subject Detail screen Pr#1.1 - Check that 'New' status couldn't be changed manually");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		int subjectsTotalCount = studyNavigatorDashBoardPage.getTotalcountOfSubjects();
		studyNavigatorDashBoardPage.searchSubject(subjectNew);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectNew);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("1.4: 'New' Subject status disabled for manually change");
		subjectDetailPage.verifySubjectStatusEditButtonDisabled();
		subjectDetailPage.clickOnCancelBtn();
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("1.5:Navigate to Study Navigator and check total/subjects by status count");
		studyNavigatorDashBoardPage.verifySubjectsTotalCount(subjectsTotalCount);
		int screenedCount = studyNavigatorDashBoardPage
				.getSubjectStatusFilterCountByStatusName(Constants.SubjectStatus_Screened);
		int screenedFailedCount = studyNavigatorDashBoardPage
				.getSubjectStatusFilterCountByStatusName(Constants.Status_ScreenFailed);

		reportLog("2.1:Navigate to Subject Detail screen Pr#1.2 and manually change Subject’s status to Pr#1.3 ");
		studyNavigatorDashBoardPage.searchSubject(subjectScreened);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectScreened);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinator, AT_Password);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("2.2:Navigate to Study Navigator and check total/subjects by status count");
		studyNavigatorDashBoardPage.verifySubjectsTotalCount(subjectsTotalCount);

		reportLog("2.3:Filter with subject’s previous status has 1 less total count");
		studyNavigatorDashBoardPage.verifySubjectStatusHasOneLessCount(Constants.SubjectStatus_Screened, screenedCount);

		reportLog("2.4:Filter with new subject’s status has 1 more");
		studyNavigatorDashBoardPage.verifySubjectStatusHasOneMoreCount(Constants.Status_ScreenFailed,
				screenedFailedCount);
		int screened_FailedCount = studyNavigatorDashBoardPage
				.getSubjectStatusFilterCountByStatusName(Constants.Status_ScreenFailed);
		int enrolledCount = studyNavigatorDashBoardPage
				.getSubjectStatusFilterCountByStatusName(Constants.SubjectStatus_Enrolled);


		reportLog("3.1:Navigate to Subject Detail screen Pr#1.3 and manually change Subject’s status to Pr#1.4");
		studyNavigatorDashBoardPage.searchSubject(subjectScreenFailed);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectScreenFailed);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Enrolled);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinator, AT_Password);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("3.2:Navigate to Study Navigator and check total/subjects by status count");
		studyNavigatorDashBoardPage.verifySubjectsTotalCount(subjectsTotalCount);

		reportLog("3.3:Filter with subject’s previous status has 1 less total count");
		studyNavigatorDashBoardPage.verifySubjectStatusHasOneLessCount(Constants.Status_ScreenFailed,
				screened_FailedCount);

		reportLog("3.4:Filter with new subject’s status has 1 more");
		studyNavigatorDashBoardPage.verifySubjectStatusHasOneMoreCount(Constants.SubjectStatus_Enrolled, enrolledCount);
		int enrolledPreviousCount = studyNavigatorDashBoardPage
				.getSubjectStatusFilterCountByStatusName(Constants.SubjectStatus_Enrolled);
		int completeCount = studyNavigatorDashBoardPage
				.getSubjectStatusFilterCountByStatusName(Constants.SubjectStatus_Completed);

		reportLog("4.1:Navigate to Subject Detail screen Pr#1.4 and manually change Subject’s status to Pr#1.5");
		studyNavigatorDashBoardPage.searchSubject(subjectEnrolled);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectEnrolled);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Completed);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinator, AT_Password);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("4.2:Navigate to Study Navigator and check total/subjects by status count");
		studyNavigatorDashBoardPage.verifySubjectsTotalCount(subjectsTotalCount);


		reportLog("4.3:Filter with subject’s previous status has 1 less total count");
		studyNavigatorDashBoardPage.verifySubjectStatusHasOneLessCount(Constants.SubjectStatus_Enrolled,
				enrolledPreviousCount);

		reportLog("4.4:Filter with new subject’s status has 1 more");
		studyNavigatorDashBoardPage.verifySubjectStatusHasOneMoreCount(Constants.SubjectStatus_Completed,
				completeCount);
		int completeCountPrevious = studyNavigatorDashBoardPage
				.getSubjectStatusFilterCountByStatusName(Constants.SubjectStatus_Completed);
		int discontinuedCount = studyNavigatorDashBoardPage
				.getSubjectStatusFilterCountByStatusName(Constants.SubjectStatus_Discontinued);

		reportLog("5.1:Navigate to Subject Detail screen Pr#1.5 and manually change Subject’s status to Pr#1.6");
		studyNavigatorDashBoardPage.searchSubject(subjectCompleted);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectCompleted);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Discontinued);
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinator, AT_Password);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("5.2:Navigate to Study Navigator and check total/subjects by status count");
		studyNavigatorDashBoardPage.verifySubjectsTotalCount(subjectsTotalCount);
	

		reportLog("5.3:Filter with subject’s previous status has 1 less total count");
		studyNavigatorDashBoardPage.verifySubjectStatusHasOneLessCount(Constants.SubjectStatus_Completed,
				completeCountPrevious);

		reportLog("5.4:Filter with new subject’s status has 1 more");
		studyNavigatorDashBoardPage.verifySubjectStatusHasOneMoreCount(Constants.SubjectStatus_Discontinued,
				discontinuedCount);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}
}