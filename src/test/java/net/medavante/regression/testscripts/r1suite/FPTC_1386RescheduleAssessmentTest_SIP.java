package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1386RescheduleAssessmentTest_SIP extends BaseTest {
	protected String crStudyName, subjectName = "SUBJ1435" + generateRandomString(5),
			notesText = generateRandomString(3), commentTxt = "Test" + generateRandomString(3), crVisitName1,
			 crVisitName2, crVisitName3;
	String popUpMessageForreSchedulingStart = "You have selected to move this appointment to a different date/time:";
	String popUpMessageForreSchedulingEndText = ". Are you sure you want to continue?";
	String UpdatePopUpMessage = "You have selected to move this appointment to a different Clinician. Are you sure you want to continue?";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1386RescheduleAssessmentTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		crStudyName = prop.getProperty("studyName");
		crVisitName1 = prop.getProperty("visitName1");
		crVisitName2 = prop.getProperty("visitName2");
		crVisitName3 = prop.getProperty("visitReScheduling");
		
		/*Creating Subject For Configuring Pre-requisite*/
		reportLog("Setting Up The PreRequisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(crStudyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		/*Subject Created Successfully*/
		
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(crStudyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime("09", "45", "AM");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician15Name,"09:45am");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.navigateToListingPage();
		subjectDetailPage.clickOnVisitRow(crVisitName2);
		subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime("09", "30", "AM");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician14Name,"09:30am");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.navigateToListingPage();
		subjectDetailPage.clickOnVisitRow(crVisitName3);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime("11", "00", "AM");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician14Name,"11:00am");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.navigateToSubjectDetailsPage();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1386 Test Case Name: CR Calendar - Reschedule
	 * Assessment
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1386_CRCalendar - Reschedule Assessment", groups = { "R1" })

	public void FPTC_1386_verifyCrCalendarRescheduleAssessment() {

		reportLog("1.1: After setting pre-requisite Search Visit By Visit Status");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("1.2:Click On Visit Row");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnSchedulerIcon();

		reportLog("1.3:Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(crStudyName, Constants.ATAssignedRater_10);

		reportLog("1.4:Verify cancel Appointment Button is Displayed");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("2.1: Currently Schedule Clinician Shown");
		centralRatingAppointmentPage.verifyScheduledClinicanIsShown();

		reportLog("2.2:Change Scheduled Date Of Appointment");
		centralRatingAppointmentPage.scheduleAppointmentForNextDate();

		reportLog("2.3:Change Scheduled Time Of Appointment");
		centralRatingAppointmentPage.setStartedTime("08", "15", "AM");

		reportLog("2.4: Verify Option To Pick Clinician Shown");
		centralRatingAppointmentPage.verifyOptionToPickClinicianShown();

		reportLog("2.5: Verify cancel Appointment Button is Displayed");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("3:Click On Pick Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("3.1:Select Option To Pick Clinician");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician15Name, "08:15am");

		reportLog("3.2:Schedule Appointment For Clinician");
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("3.3:Verify Rescheduling Message");
		String middleMessageReschedulingText = centralRatingAppointmentPage.getMiddleMessage();
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(popUpMessageForreSchedulingStart);

		reportLog("4:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("4.1:Verify Reason Change Values And Reason Comment Present");
		centralRatingAppointmentPage.verifyOptionToSelectAndReasonCommentIsAvailable();

		reportLog("5:Select Reason From DropDown");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(Constants.Cancel_Appointment_Scheduling_Adjusment_Reason,
				commentTxt);

		reportLog("5.1:confirm Confirmation Of Reason For Rescheduling");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("5.2: verify Visit Status");
		centralRatingAppointmentPage.verifyVisitStatus(Constants.scheduled_Status);

		reportLog("6:Navigate Back to listing Page");
		centralRatingAssesmentListingPage = centralRatingAppointmentPage.navigateToListingPage();

		reportLog("6.1:Cancel Date From DatePicker");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("6.2:View Scheduled Visit For Today");
		centralRatingAssesmentListingPage.selectScheduledDateFromToForToday();

		reportLog("6.3:Select Visit By Subject And Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(Constants.scheduled_Status);

		reportLog("6.4 Search Visit By Visit Status");
		centralRatingAssesmentListingPage.sortByVisitName(crVisitName2);
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);

		reportLog("6.5 Click On Visit Row");
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnRowOfVisitByName(crVisitName2);

		reportLog("6.6:Click On Currently Scheduled Clinician");
		centralRatingAppointmentPage.clickOnPreviouslyScheduleClinicianLink();

		reportLog("6:7: Select Same Time And Date For Another Clinician For Updating Appointment");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician15Name ,"09:30am");

		reportLog("6:8: Verify Update Appointment Button is Displayed");
		centralRatingAppointmentPage.verifyUpdateScheduledButtonIsPresentAndEnabled();

		reportLog("6.9: Verify cancel Appointment Button is Displayed");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("6.10: Currently Schedule Clinician Shown");
		centralRatingAppointmentPage.verifyScheduledClinicanIsShown();

		reportLog("7:Click On Update Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("7.1:Verify Update Confirmation Appointment message");
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(UpdatePopUpMessage);

		reportLog("8:confirm Confirmation Of PopUp");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("8.2: verify Visit Status");
		centralRatingAppointmentPage.verifyVisitStatus(Constants.scheduled_Status);

		reportLog("8.3:Navigate Back to CR Listing Page");
		centralRatingAssesmentListingPage = centralRatingAppointmentPage.navigateToListingPage();

		reportLog("8.4:Cancel Date From DatePicker");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("8.5:View Scheduled Visit For Today");
		centralRatingAssesmentListingPage.selectScheduledDateFromToForToday();

		reportLog("8.6:Select Visit By Subject And Status");
		centralRatingAssesmentListingPage.searchByVisitStatus("Scheduled");

		reportLog("8.7: Search Visit By Visit Status");
		centralRatingAssesmentListingPage.sortByVisitName(crVisitName3);
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);

		reportLog("9:Click On Visit Row");
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnRowOfVisitByName(crVisitName3);

		reportLog("9.1:Currently Schedule Clinician Shown");
		centralRatingAppointmentPage.verifyScheduledClinicanIsShown();

		reportLog("9.2:Change Scheduled Date Of Appointment");
		centralRatingAppointmentPage.scheduleAppointmentForNextDate();

		reportLog("9.3:Change Scheduled Time Of Appointment");
		centralRatingAppointmentPage.setStartedTime("05", "30", "PM");

		reportLog("9.4:Verify Option To Pick Clinician Shown");
		centralRatingAppointmentPage.verifyOptionToPickClinicianShown();

		reportLog("9.5:Verify cancel Appointment Button is Displayed");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("10:Click On Pick Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("10.1:Select Option To Pick Clinician");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician", CentralRatingModuleConstants.clinician14Name, "05:30pm");

		reportLog("10.2:Schedule Appointment For Clinician");
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("10.3:Verify Rescheduling Message");
		String middleMessageText = centralRatingAppointmentPage.getMiddleMessage();
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(popUpMessageForreSchedulingStart);

		reportLog("11:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("11.1:Verify Reason Change Values And Reason Comment Present");
		centralRatingAppointmentPage.verifyOptionToSelectAndReasonCommentIsAvailable();

		reportLog("12:Select Reason From DropDown");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(Constants.Cancel_Appointment_Scheduling_Adjusment_Reason,
				commentTxt);

		reportLog("12.1:confirm Confirmation Of Reason For Rescheduling");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("12.2: verify Visit Status");
		centralRatingAppointmentPage.verifyVisitStatus(Constants.scheduled_Status);

		reportLog("12.3:Logout application");
		loginPage.logoutApplication();

		reportLog("12.4:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}