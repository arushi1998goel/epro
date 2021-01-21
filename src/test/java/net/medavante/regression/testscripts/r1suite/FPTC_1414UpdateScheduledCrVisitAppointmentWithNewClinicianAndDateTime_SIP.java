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

public class FPTC_1414UpdateScheduledCrVisitAppointmentWithNewClinicianAndDateTime_SIP extends BaseTest {

	protected String crStudyName, userNameWithClaim, subjectName = "Automation_SUB" + generateRandomString(6),
			notesText = "Automation_Notes_"+generateRandomString(10), commentTxt = "Auto_Comment_" + generateRandomString(6), crVisitName1;
	String popUpMessageForreSchedulingStart = "You have selected to move this appointment to a different date/time:";
	String popUpMessageForreSchedulingEndText = ". Are you sure you want to continue?";
	String UpdatePopUpMessage = "You have selected to move this appointment to a different Clinician. Are you sure you want to continue?";
	
	
	String hourForAppointment1 = "03", minuteForAppointment1 = "15", timeMarker = "pm",
	timeToSelect1 = hourForAppointment1 + ":" + minuteForAppointment1 + timeMarker;
	
	String	hourForAppointment2 = "02", minuteForAppointment2 = "15",
	 timeToSelect2 = hourForAppointment2 + ":" + minuteForAppointment2 + timeMarker;
			
	String hourForAppointment3 = "04", minuteForAppointment3 = "00",
			timeToSelect3 = hourForAppointment3 + ":" + minuteForAppointment3 + timeMarker;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1414UpdateScheduledCrVisitAppointmentWithNewClinicianAndDateTime_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		crStudyName = prop.getProperty("studyName");
		crVisitName1 = prop.getProperty("visitName1");
		
		reportLog("Login To the apllication and Create Subject For Study");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(crStudyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		
		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1:Click on Central Rating Tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.2 Setting Up The Pre-Requisite For Updating The Visit And Find Subject");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("1.3:Select study And Site and SubjectName");
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(crStudyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);
		
		reportLog("1.4:Click On Visit Row For Scheuling The Visit");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician14Name,timeToSelect1);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1414 Test Case Name: Update scheduled CR visit
	 * appointment with new clinician and date/time
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1414_Update scheduled CR visit appointment with new clinician and date/time", groups = {
			"R1" })

	public void FPTC_1414_verifyUpdateScheduledCrVisitAppointmentWithNewClinicianAnddatetime() {

		reportLog("1.5: After Setting Up The PreRequisite Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(crStudyName, Constants.ATAssignedRater_10);
		centralRatingAppointmentPage.clickOnClinicanLink();

		reportLog("2:Change Scheduled Date Of Appointment");
		centralRatingAppointmentPage.scheduleAppointmentForNextDate();

		reportLog("2.1:Change Scheduled Time Of Appointment");
		centralRatingAppointmentPage.setStartedTime(hourForAppointment2, minuteForAppointment2, timeMarker);

		reportLog("2.2:Click On Pick Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("2.3: Click On Available Time Slot for New Clinician");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician14Name,timeToSelect2);

		reportLog("3: Deselect The Clinician");
		centralRatingAppointmentPage.deselectClinician();

		reportLog("3.1: Clinician Is Removed");
		centralRatingAppointmentPage.verifyOptionToPickClinicianShown();

		reportLog("3.2: Click On Available Time Slot for New Clinician");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician14Name,timeToSelect2);

		reportLog("4: Click On Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("4.1:Verify Rescheduling Message");
		String middleMessageReschedulingText = centralRatingAppointmentPage.getMiddleMessage();
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(popUpMessageForreSchedulingStart);

		reportLog("4.2:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("4.3:Verify Reason Change Values And Reason Comment Present");
		centralRatingAppointmentPage.verifyOptionToSelectAndReasonCommentIsAvailable();

		reportLog("4.4:Select Reason From DropDown");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(Constants.Cancel_Appointment_Scheduling_Adjusment_Reason,
				commentTxt);

		reportLog("5: Cancel Confirmation");
		centralRatingAppointmentPage.cancelConfimartionForCancelAppointment();

		reportLog("5.1:Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(crStudyName, Constants.ATAssignedRater_10);

		reportLog("6:Navigate Back to Subject Detail Page");
		centralRatingAssesmentListingPage = centralRatingAppointmentPage.navigateToListingPage();

		reportLog("6.1: Confirm Confirmation");
		subjectDetailPage = centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("6.6 Click On Visit Row");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		subjectDetailPage.clickOnSchedulerIcon();

		reportLog("6.7:Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(crStudyName, Constants.ATAssignedRater_10);

		reportLog("6.8:Click On Pick Clinician");
		centralRatingAppointmentPage.clickOnPreviouslyScheduleClinicianLink();

		reportLog("6.9: Click On Available Time Slot for New Clinician");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",CentralRatingModuleConstants.clinician15Name,timeToSelect1);

		reportLog("6.10:Click On Update Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("6.11:Verify Update Confirmation Appointment message");
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(UpdatePopUpMessage);

		reportLog("6.12:confirm Confirmation Of PopUp");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
