package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1135CentralRatingAssessmentslistSelfScheduleCRVisitAppointment_SIP extends BaseTest {

	protected String studyName, userNameWithClaim, subjectName = "Subject2227" + generateRandomString(4), crVisitName,
			crVisitNameCancelledHistory, hourForAppointment = "01", minuteForAppointment = "00", timeMarker = "PM",
			minuteForAppointment1 = "00", hourForAppointment1 = "08", timeMarker1 = "PM",
			commentTxt = "Test" + generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1135CentralRatingAssessmentslistSelfScheduleCRVisitAppointment_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("studyNameTestCase2227");
		crVisitName = prop.getProperty("visitName");
		crVisitNameCancelledHistory = prop.getProperty("crVisitNameCancelled");

		/* Creating Subject For Configuring Pre-requisite */
		reportLog("1:Login To Application And Setting Up The Pre-Requisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		/* Subject Created Successfully */
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);
		subjectDetailPage.clickOnVisitRow(crVisitName);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		
		reportLog("Pick up clinitian for this time");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "01:00pm");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.navigateBack();
		subjectDetailPage.clickOnVisitRow(crVisitNameCancelledHistory);
		subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);
		
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "08:00pm");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.navigateBack();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1135 Test Case Name:Central Rating Assessments list:
	 * Self Schedule CR Visit Appointment
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1135_Central Rating Assessments list: Self Schedule CR Visit Appointment ", groups = {
			"R1" })

	public void FPTC_1135_verifyCentralRatingAssesmentListSelfScheduleCrVisitAppointment() {

		reportLog("2.1 After Setting Up The Pre-Requisite Verify  Subject Detail Page Displayed");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("3:Click To ReSchedule The Visit");
		subjectDetailPage.clickOnVisitRow(crVisitName);
		centralRatingAppointmentPage = subjectDetailPage.clickOnSchedulerIcon();

		reportLog("3.1:Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("3.2:Set Appointment  Date ");
		centralRatingAppointmentPage.setAppointmentDate();

		reportLog("3.3:Set Appointment Time ");
		centralRatingAppointmentPage.setStartedTime("03", "30", "PM");

		reportLog("3.4:Select To View Qualified Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("3.5:Select Clinician Available Time");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "03:30pm");

		reportLog("3.6 Click On Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		String dateAndTime = centralRatingAppointmentPage.getDateTimeForSchedulingHistory("scheduledDate");

		reportLog("3.7:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("3.8:Select Reason From DropDown");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);

		reportLog("3.9:confirm Confirmation Of Reason For Rescheduling");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("3.10: verify Visit Status");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.scheduledStatus);

		reportLog("4:Navigate Back to Subject Detail Page");
		dashBoardPage = centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,
				Constants.HomeNavText);

		reportLog("4.1 Navigate To Central Rating Listing page");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("5: Verify Central Rating Listing Page Displayed");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("6:Search rescheduled CrVisit");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("6.1:View Scheduled Visit For Today");
		centralRatingAssesmentListingPage.selectScheduledDateFromToForToday();

		reportLog("6.2:Select Visit By Subject And Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(CentralRatingModuleConstants.scheduledStatus);

		reportLog("6.3: Search Visit By Visit Status");
		centralRatingAssesmentListingPage.sortByVisitName(crVisitName);
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);

		reportLog("6.4:Click On Visit Row");
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnRowOfVisitByName(crVisitName);

		reportLog("6.5:Verify Rescheduled Cr Visit Displayed");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("7: Click On Visit History Model Window Icon");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();

		reportLog("7.1: Verify Visit History Model Window");
		centralRatingAppointmentPage.verifyModelWindowDisplayed();

		reportLog("8:Check Appointment History Contains Information");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician15Name, dateAndTime, "", "",
				CentralRatingModuleConstants.scheduledStatus);
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();

		reportLog("9:Navigate To Home Page For Repeating Steps For Cancelled Appointment");
		centralRatingAppointmentPage.navigateToListingPage();

		reportLog("9.1:Cancel Date For Filling Up the Current Date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("9.2:View Scheduled Visit For Today");
		centralRatingAssesmentListingPage.selectScheduledDateFromToForToday();

		reportLog("9.3:Select Scheduled Visit Status From DropDown");
		centralRatingAssesmentListingPage.searchByVisitStatus(CentralRatingModuleConstants.scheduledStatus);
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		centralRatingAssesmentListingPage.sortByVisitName(crVisitNameCancelledHistory);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage
				.clickOnRowOfVisitByName(crVisitNameCancelledHistory);

		reportLog("9.4:Verify Appointment Page Displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();
		String dateAndTime1 = centralRatingAppointmentPage.getDateTimeForSchedulingHistory("scheduledDate");

		reportLog("9.5:Click On Cancel Appointment");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("9.6:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("9.7:Select Reason For Cancel Appointment");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);

		reportLog("9.8:Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("9.9:Visit Status Change To Pending");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.pendingStatus);

		reportLog("9.10: Click On Visit History Model Window Icon");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();

		reportLog("9.11: Verify Visit History Model Window");
		centralRatingAppointmentPage.verifyModelWindowDisplayed();

		reportLog("9.12:Check Appointment History Contains Information");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician15Name, dateAndTime1,
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt, "Cancelled");
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();

		reportLog("9.13:Logout application");
		loginPage.logoutApplication();

		reportLog("9.14:Verify user is logout");
		loginPage.verifyUserLogout();

	}

}