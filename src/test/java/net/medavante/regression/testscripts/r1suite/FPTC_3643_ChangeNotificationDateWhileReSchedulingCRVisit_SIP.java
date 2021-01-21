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

public class FPTC_3643_ChangeNotificationDateWhileReSchedulingCRVisit_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5), userNameWithClaim, crStudyName, crVisitName1,
			visitNameSecond, hourForAppointment = "08", minuteForAppointment = "30", timeMarker = "AM",
			hourForAppointment1 = "11", minuteForAppointment1 = "30", timeMarker1 = "PM", usersTimeZone,
			time = hourForAppointment + ":" + minuteForAppointment + timeMarker,
			time1 = hourForAppointment1 + ":" + minuteForAppointment1 + timeMarker1,
			commentTxt = "Test" + generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3643_ChangeNotificationDateWhileReSchedulingCRVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");

		crStudyName = prop.getProperty("studyName");
		crVisitName1 = prop.getProperty("visitName1");

		reportLog("Creating a subject from Admin user");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(crStudyName, Constants.ATAssignedRater_10,
				subjectName);
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		loginPage.logoutApplication();

		reportLog("Schedule visit for current date");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(crStudyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		loginPage.logoutApplication();
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3643 Test Case Name:Change of Notification date/time for the CR Appointments with requested status
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-3643_Change of Notification date/time for the CR Appointments with requested status", groups = {
			"R1" })

	public void FPTC_3643_verifyChangeNotificationDateWhileReSchedulingCRVisit() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to the CR appointment details screen");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("2.1:Click On Find Subject Icon");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("2.2:Find Subject");
		subjectDetailPage = centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("2.3 Subject detail Page Displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName);
		subjectDetailPage.verifySubjectStudyAndSite(crStudyName,Constants.ATAssignedRater_10);

		reportLog("2.4 : Select To Add Visit");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnScheduledVisit();

		reportLog("2.5 Verify Apoointment Page Displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("2.6  Notification date-time is set to be in future");
		centralRatingAppointmentPage.scheduleNotificationAndAppointmentDateForNextDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		String updatedTimeForNotification = centralRatingAppointmentPage.getDateTime("notificationDate");

		reportLog("2.7: Schedule date-time is set to be in future");
		centralRatingAppointmentPage.scheduleAppointmentForNextDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		String updatedTimeForScheduledate = centralRatingAppointmentPage.getDateTime("scheduledDate");

		reportLog("2.8: Click on save button and give the reason for rescheduling");
		centralRatingAppointmentPage.clickOnSave();
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("2.9: Pick Clinician with time slot");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("2.10 Verify scheduled time for notificationDate and scheduledDate");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate", updatedTimeForScheduledate);
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate",
				updatedTimeForNotification);

		reportLog("2.11: Go back to subject details screen and apply filter for required subjcet");
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("3: Case1: Only Schedule date-time will change");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnScheduledVisit();

		centralRatingAppointmentPage.verifyAppointmentPage();
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();
		centralRatingAppointmentPage.clickOnCancelAppointment();
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		String updatedTimeForScheduledDate = centralRatingAppointmentPage.getDateTime("scheduledDate");

		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("3.1: Scheduled date-time verified");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate", updatedTimeForScheduledDate);

		reportLog("4: Go back to subject details screen");
		centralRatingAppointmentPage.navigateBack();
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("5: Navigate to the CR appointment details screen of Step#2");
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("5.1 Subject detail Page Displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName);
		subjectDetailPage.verifySubjectStudyAndSite(crStudyName, Constants.ATAssignedRater_10);

		reportLog("6: Case2: Notification date-time will change");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnScheduledVisit();
		centralRatingAppointmentPage.verifyAppointmentPage();
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();
		centralRatingAppointmentPage.clickOnCancelAppointment();
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		centralRatingAppointmentPage.scheduleNotificationAndAppointmentDateForNextDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("6: Case3: Schedule Date-Time and time with Clinician will change");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnScheduledVisit();
		centralRatingAppointmentPage.verifyAppointmentPage();
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("6.1: changes the scheduledDate with time");
		centralRatingAppointmentPage.scheduleAppointmentForNextDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);
		String afterUpdatedTimeForScheduledate = centralRatingAppointmentPage.getDateTime("scheduledDate");

		reportLog("6.2:Pick Clinican");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("6.3: Book Appointment");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time1);

		reportLog("6.4 Click On Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("6.5 Verify Appointment Scheduled");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate",
				afterUpdatedTimeForScheduledate);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}