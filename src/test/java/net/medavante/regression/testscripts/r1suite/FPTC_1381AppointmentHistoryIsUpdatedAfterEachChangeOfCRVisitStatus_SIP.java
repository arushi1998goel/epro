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

public class FPTC_1381AppointmentHistoryIsUpdatedAfterEachChangeOfCRVisitStatus_SIP extends BaseTest {

	protected String studyName, subjectName = "SUBJ2169" + generateRandomString(4),
			crScheduledVisitName, crRequestedVisitName, crCancelledVisitName, crRescheduledVisitName,
			requestedBeforeScheduledVisitName, recalledCrVisitName, updatedCrVisitName, hourForAppointment = "03",
			minuteForAppointment = "30", timeMarker = "PM", hourForAppointment1 = "05", minuteForAppointment1 = "30",
			timeMarker1 = "PM", commentTxt = "Test" + generateRandomString(3), dateTimeForScheduling,
			dateTimeForReScheduling;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1381AppointmentHistoryIsUpdatedAfterEachChangeOfCRVisitStatus_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("studyNameTestCase2227");
		crScheduledVisitName = prop.getProperty("auto_CrScheduled_Visit");
		
		/*Creation Of Subject*/
        reportLog("For Configuring the PreRequisit Subject Creation");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		/*Successfully Created*/
	
		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		reportLog("2:Navigate To The Central Rating Area");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("Setting Up The Prerequisit #3.3");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);
		subjectDetailPage.clickOnVisitRow(crScheduledVisitName);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "03:30pm");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1381 Test Case Name:CR Appointment Details:
	 * Appointment History is updated after each change of CR Visit status
	 * 
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1381_CR Appointment Details: Appointment History is updated after each change of CR Visit status", groups = {
			"R1" })

	public void FPTC_1381_verifyCrAppointmentDetailsAppointmentHistoryIsUpdatedAfterEachChangeOfCrVisitStatus() {

		dateTimeForScheduling = centralRatingAppointmentPage.getDateTimeForSchedulingHistory("scheduledDate");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("8.5:After Setting Up The Pre-Requisite#3.3 Click On Visit History Model Window Icon");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();

		reportLog("8.6: Verify Visit History Model Window");
		centralRatingAppointmentPage.verifyModelWindowDisplayed();

		reportLog("8.7:Appointment Details Displayed Fo Scheduled Appointment");
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("9: Check that Record Displayed For Appointment History");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician15Name, dateTimeForScheduling, "", "", "Scheduled");

		reportLog("9.1 Close Model WIndow");
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();
		dateTimeForScheduling = centralRatingAppointmentPage.getDateTimeForSchedulingHistory("scheduledDate");

		reportLog("Configuring the Pre-Requist PR#3.7 And Change the Scheduled Visit To Requested");
		centralRatingAppointmentPage.clickOnPreviouslyScheduleClinicianLink();

		reportLog("pr#3.7:Click On Cancel Appointment");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("pr#3.7:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("pr#3.7:Select Reason For Cancel Appointment");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);

		reportLog("pr#3.7:Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("pr#3.7:Set Appointment Date/Time");
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("pr#3.7:Click On Save");
		dateTimeForScheduling = centralRatingAppointmentPage.getDateTimeForSchedulingHistory("scheduledDate");
		centralRatingAppointmentPage.clickOnSave();
		centralRatingAppointmentPage.verifyVisitStatus("Requested");

		reportLog("16:After Setting PreRequisit pr#3.7 Click On Visit History Model Window Icon");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();

		reportLog("16.1: Verify Visit History Model Window");
		centralRatingAppointmentPage.verifyModelWindowDisplayed();

		reportLog("16.2:Apointment Details Displayed For Requested Was Scheduled");
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("17: Check that Record Displayed For Requested Was Scheduled  Appointment History");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation("", dateTimeForScheduling, "", "",
				"Requested");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician15Name, dateTimeForScheduling,
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt, "Cancelled");

		reportLog("17.1 Close Model WIndow");
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();

		reportLog("10:After setting pr#3.4 Appointment Details Of Requested Visit");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("11:Check that the records are displayed Appointment History:requested");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation("", dateTimeForScheduling, "", "",
				"Requested");
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();

		reportLog("pr#3.1:Setting PreRequisit For pr#3.1");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "03:30pm");

		reportLog("pr#3.1:Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("pr#3.1:Set Appointment Date/Time For ReScheduling");
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);
		dateTimeForReScheduling = centralRatingAppointmentPage.getDateTimeForSchedulingHistory("scheduledDate");

		reportLog("pr#3.1:Pick Clinican for ReScheduling");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "05:30pm");

		reportLog("pr#3.1:Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("3: After Setting PreRequisit pr#3.1 Click On Visit History Model Window Icon");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();

		reportLog("3.1: Verify Visit History Model Window");
		centralRatingAppointmentPage.verifyModelWindowDisplayed();

		reportLog("4:Apointment Details Displayed For ReScheduled Visit");
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("5:Check that the records are displayed Appointment History:ReScheuled");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician15Name, dateTimeForReScheduling, "", "", "Scheduled");
		
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician15Name, dateTimeForScheduling,
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt, "Cancelled");

		reportLog("pr#3.2:Setting The PreRequisit For pr#3.2");
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();

		reportLog("pr#3.2:Click On Cancel Appointment");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("pr#3.2:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("pr#3.2:Select Reason For Cancel Appointment");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);

		reportLog("pr#3.2:Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("6:After Setting Up The Pre-requisite Find an Appointment Detail Pr#3.2 - Appointments that was Cancelled ");
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("7:Check that the records are displayed Appointment History:Cancelled");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician15Name, dateTimeForReScheduling,
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt, "Cancelled");

		reportLog("pr#3.6:Setting the PreRequisit For pr#3.6");
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();

		reportLog("pr#3.6:Set Appointment Date/Time For Scheduling");
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);

		reportLog("pr#3.6:Pick Clinican for ReScheduling");
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "05:30pm");

		reportLog("pr#3.6:Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("pr#3.6:Verify Appointment Page");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("pr#3.6:Fill Subject alias Field");
		centralRatingAppointmentPage.fillSubjectAliasField();

		reportLog("pr#3.6:Click On Initiate Appointment");
		centralRatingAppointmentPage.clickOnInitiateAppointment();

		reportLog("pr#3.6:Confirm Initiation");
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("pr#3.6:Visit Status Change To Initiates");
		centralRatingAppointmentPage.verifyVisitStatus("Initiated");

		reportLog("pr#3.6:Click On Recall Button");
		centralRatingAppointmentPage.clickOnRecall();

		reportLog("pr#3.6:Confirm Recall ConFirmation");
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("pr#3.6:Visit Status Change To Scheduled");
		centralRatingAppointmentPage.verifyVisitStatus("Scheduled");

		reportLog("14.1:After Setting PreRequisit pr#3.6 Click On Visit History Model Window Icon");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();

		reportLog("14.2:Verify Visit History Model Window");
		centralRatingAppointmentPage.verifyModelWindowDisplayed();

		reportLog("14.3:Apointment Details Displayed For Recalled Visit");
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("15:Check that the records are displayed Appointment History:");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician15Name, dateTimeForReScheduling, "", "", "Scheduled");
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();

		reportLog("pr#3.5:Setting the PreRequisit For pr#3.5");
		centralRatingAppointmentPage.verifyAppointmentPage();
		centralRatingAppointmentPage.clickOnPreviouslyScheduleClinicianLink();

		reportLog("pr#3.5:Click On Available Time Slot for New Clinician");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician16Name, "05:30pm");

		reportLog("pr#3.5:Click On Update Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		
		reportLog("pr#3.5:confirm Confirmation Of PopUp");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();
		centralRatingAppointmentPage.verifyVisitStatus("Scheduled");

		reportLog("pr#3.5:Click on Visit Model Window");
		centralRatingAppointmentPage.clickOnVisitHistoryModelWindow();

		reportLog("pr#3.5:Verify Visit History Model Window");
		centralRatingAppointmentPage.verifyModelWindowDisplayed();

		reportLog("12:Apointment Details Displayed For Updated Visit");
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("13:Check that the records are displayed Appointment History:Updated");
		centralRatingAppointmentPage.checkAppointmentHistoryContainsInformation(
				Constants.clinician16Name, dateTimeForReScheduling, "", "", "Scheduled");
		centralRatingAppointmentPage.clickOnModelWindowVisitHisotryCloseButton();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
