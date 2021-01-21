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

public class FPTC_1153_NavigationToSchedulingScreenFromSubjectDetailsAsASiteUser_SIP extends BaseTest {

	private String visitNameWithOutStatus, visitNameWithSkippedStatus, initiatedVisit, completedVisit,
			cancelledVisit, scheduledVisit, inProgressVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1153_NavigationToSchedulingScreenFromSubjectDetailsAsASiteUser_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyCRStatus");
		visitNameWithOutStatus = properties.getProperty("CR_VisitWithNoStatus");
		visitNameWithSkippedStatus = properties.getProperty("CR_SkippedVisit");
		initiatedVisit = properties.getProperty("InitiatedVisit");
		completedVisit = properties.getProperty("CompletedVisit");
		cancelledVisit = properties.getProperty("CanceledVisit");
		scheduledVisit = properties.getProperty("ScheduledVisit");
		inProgressVisit = properties.getProperty("InProgressVisit");
		subjectName = properties.getProperty("SubjectNameCrStatus");
	}

	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1153 Test Case Name:Navigation To Scheduling Screen From Subject Details As A SiteUser
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1153_Verify Navigation ToScheduling ScreenFromSubjectDetailsAsASiteUser", groups = {
			"R1" })
	public void FPTC_1153_verifyNavigationToSchedulinGScreenFromSubjectDetailsAsASiteUser()
			throws InterruptedException {

		reportLog("1.1: Login to Site Portal with site user with the claim 'canManageSelfServeCRAppointments'");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("2.1: Verify User login is successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.3: Select " + studyName + " from the study list");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);


		reportLog("2.5:Select the Subject From the subject list");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.6:	Subject Detail page appears");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.1:	Select the visit of Pr#3");
		subjectDetailPage.clickOnVisitRow(visitNameWithOutStatus);

		reportLog("3.2:Option to add the visit is available");
		subjectDetailPage.verifyAddVisitIconIsDisplayed();

		reportLog("4.1:	Select to add the visit");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("4.2:Self Scheduling calendar is visible");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("5.1:	Select to cancel out the scheduling");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("5.2:Subject Detail page appears");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("6.2:Repeat Step#3 to Step#5 for the visits of Pr#4 And 	Select the visit of Pr#4 ");
		subjectDetailPage.clickOnVisitRow(visitNameWithSkippedStatus);

		reportLog("6.3:Option to add the visit is available");
		subjectDetailPage.verifyAddVisitIconIsDisplayed();

		reportLog("6.4:	Select to add the visit");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("6.5:Self Scheduling calendar is visible");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("6.6:	Select to cancel out the scheduling");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("6.7:Subject Detail page appears");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("6.8:Repeat Step#3 to Step#5 for the visits of Pr#5 and Select the visit of Pr#5");
		subjectDetailPage.clickOnVisitRow(cancelledVisit);

		reportLog("6.10:Select to add the visit");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("6.11:Self Scheduling calendar is visible");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("6.12:Select to cancel out the scheduling");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("6.13:Subject Detail page appears");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("7.1:Select the visit of Pr#6");
		subjectDetailPage.clickOnVisitRow(initiatedVisit);

		reportLog("7.2:No scheduler is visible");
		subjectDetailPage.verifyVisitCancelledAndRescheduledBtnIsNotDisplayed();

		reportLog("8.1:Repeat Step#7 for the visits of Pr#7 Select Visit");
		subjectDetailPage.clickOnVisitRow(completedVisit);

		reportLog("8.2:No scheduler is visible");
		subjectDetailPage.verifyVisitCancelledAndRescheduledBtnIsNotDisplayed();

		reportLog("8.3:Repeat Step#7 for the visits of Pr#9 Select Visit");
		subjectDetailPage.clickOnVisitRow(inProgressVisit);

		reportLog("8.4:No scheduler is visible");
		subjectDetailPage.verifyVisitCancelledAndRescheduledBtnIsNotDisplayed();

		reportLog("9.1:Select the visit of Pr#10");
		subjectDetailPage.clickOnVisitRow(scheduledVisit);

		reportLog("9.2:Option to the Self Scheduling calendar is visible");
		subjectDetailPage.verifySchedularCalenderIconIsVisible();

		reportLog("10.1:Select to the option of scheduler");
		subjectDetailPage.clickOnSchedulerIcon();

		reportLog("10.2:Self Scheduling calendar is visible");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("11.1:Select to cancel out the scheduling");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("11.2:Subject Detail page appears");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("11.3:Logout from the application");
		loginPage.logoutApplication();

		reportLog("11.4:Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
