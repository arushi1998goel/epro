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

public class FPTC_3644_ChangeNotificationDateForCRAppointmentsWithRequestedStatus_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5), userNameWithClaim, crStudyName, crVisitName1,
			visitNameSecond, hourForAppointment = "05", minuteForAppointment = "00", timeMarker = "AM",
			hourForAppointment1 = "07", minuteForAppointment1 = "30", timeMarker1 = "PM", usersTimeZone,
			time = hourForAppointment + ":" + minuteForAppointment + timeMarker,
			commentTxt = "Test" + generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3644_ChangeNotificationDateForCRAppointmentsWithRequestedStatus_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		Properties userCredentials = Configuration.readTestData("userClaimsCredentials");
		userNameWithClaim = userCredentials.getProperty("PRODSystemAdministrator");
		crVisitName1 = prop.getProperty("visitName1");
		crStudyName = prop.getProperty("studyName");

		reportLog("Creating a subject from Admin user");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(crStudyName, Constants.ATAssignedRater_10, subjectName);
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		loginPage.logoutApplication();

		reportLog("Update the visit from Schedule to requested mode");
		reportLog("1:Login in to application");
		dashBoardPage=loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(crStudyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.scheduleAppointment(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3644 Test Case Name: Change of Notification date/time
	 * for the CR Appointments with requested status
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-3644_Change of Notification date/time for the CR Appointments with requested status", groups = {
			"R1" })

	public void FPTC_3644_verifyChangeNotificationDateForCRAppointmentsWithRequestedStatus() {

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

		reportLog("2.4 : Select To Requested Visit");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnRequestedVisit();

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

		reportLog("2.8: Pick Clinician with time slot");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("2.9 Verify scheduled time for notificationDate and scheduledDate");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate", updatedTimeForScheduledate);
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate",
				updatedTimeForNotification);

		reportLog("2.10: Go back to subject details screen");
		centralRatingAppointmentPage.navigateBack();
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName);

		reportLog("Case1: Only Notification date-time will change");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnScheduledVisit();

		reportLog("Appointment page display");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("Clear notification date time and then reset it and save it");
		centralRatingAppointmentPage.clearNotoficationDateTime();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		String updatedTimeForNotificationDateCase1 = centralRatingAppointmentPage.getDateTime("notificationDate");
		centralRatingAppointmentPage.clickOnSave();

		reportLog("3.1: Schedule date-time verified");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate",
				updatedTimeForNotificationDateCase1);

		reportLog("Go back to home page and then move to subject detail page");
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("Case 2: Notification and Schedule date-time will update");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnScheduledVisit();
		
		reportLog("Appointment page display");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("Clear date time for both schedule and notification and then reset it and save it");
		centralRatingAppointmentPage.clearDateTime();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.setAppointmentDate();
		
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.clickOnSave();

		reportLog("Fill the reason pop up detail");
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("Go back to home page and then move to subject detail page");
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("Case 3: Notification and Clinician will update");

	//	subjectDetailPage.waitSpinnerToBecomeInvisible();
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnRequestedVisit();
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("Clear notification date time and then reset it and save it");
		centralRatingAppointmentPage.clearNotoficationDateTime();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		String updatedTimeForNotificationDateCase3 = centralRatingAppointmentPage.getDateTime("notificationDate");

		reportLog("Pick the Clinician and confirm the appointment");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate",
				updatedTimeForNotificationDateCase3);

		reportLog("5: Navigate to the CR appointment details screen of another visit of Pr#4");

		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("Case 4: Only Clinician will change");

		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnScheduledVisit();
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("Click on previously schedule clinician link and reschedule the appointment");
		centralRatingAppointmentPage.clickOnPreviouslyScheduleClinicianLink();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time); 
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("Go back to home page and then move to subject detail page");
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("Case 5: Schedule time and Clinician will change");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnScheduledVisit();
		centralRatingAppointmentPage.verifyAppointmentPage();

		centralRatingAppointmentPage.clearScheduleDateTime();
		centralRatingAppointmentPage.scheduleAppointmentForNextDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("Fill the reason pop up detail");
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("Go back to home page and then move to subject detail page");
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(crStudyName, Constants.ATAssignedRater_10, subjectName);

		reportLog("Case 6: Notification, Schedule time and Clinician will update");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnScheduledVisit();
		centralRatingAppointmentPage.verifyAppointmentPage();

		centralRatingAppointmentPage.clearDateTime();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, time);
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("Fill the reason pop up detail");
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}