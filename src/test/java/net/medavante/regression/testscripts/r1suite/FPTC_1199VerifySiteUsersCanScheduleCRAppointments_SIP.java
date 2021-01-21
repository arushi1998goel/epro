package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1199VerifySiteUsersCanScheduleCRAppointments_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1199VerifySiteUsersCanScheduleCRAppointments_SIP(String browser) {
		super(browser);
	}

	private String studyName, visitName;

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("Auto_CR_Visit1");

		reportLog(
				"1.1: Login to Site Portal with site user with the claim 'canManageSelfServeCRAppointments' and verify user login");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2: Navigate to the Study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Click on " + studyName + " from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName, AT_PRODSiteCoordinatorUserName);

		reportLog("1.5: Select an action to add Subject by selecting the site");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("1.6: Enter " + screeningNum + " Screening# number");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("1.7: Select language from the configured language list");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("1.8: select an action to save and verify subject detail page is display with " + screeningNum
				+ " screening num");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

	}

	/**
	 * =========================================================================
	 * Test Case Id: FPTC_1199 Test Case Name: Site Users can schedule CR
	 * Appointments
	 * =========================================================================
	 * 
	 * @throws Exception
	 *
	 */

	@Test(description = "FPTC_1199_Site Users can schedule CR Appointments", groups = { "R1" })

	public void FPTC_1199_verifySiteUsersCanScheduleCRAppointments() throws Exception {

		reportLog("2.1: Select the visit");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("2.2: Verify Option to add the visit is visible");
		subjectDetailPage.verifyAddVisitIconIsDisplayed();

		reportLog("3.1: Select the add visit option");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("3.2: Verify A Scheduling calendar control pops up is displayed");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("3.3: Verify The selected date = Current Date + Lead time(i.e. 0) ");
		subjectDetailPage.verifyTodayDateIsSelected();

		reportLog("3.4: Verify All the dates earlier than the selected date are disabled");
		subjectDetailPage.verifyPastDateIsDisabledFromSelectedDate();

		reportLog(
				"Step 4 - Step 6: Select visit appointment date and time, Click on Search, Select a time slot and Schedule an appointment and confirm.");
		String selectedTime = subjectDetailPage.selectAppointmentDateAndTime(clinicianTimeToBeSelect);
		String VisitAppointmentDate = subjectDetailPage.getappointmentDateFromAppointmentConfirmationPopUp();

		reportLog("6.2: Verify A confirmation message is displayed on the modal:");
		subjectDetailPage.verifyCRAppointmentScheduledMessageIsDisplayed(
				"Thank you!" + "\n" + "Your appointment has been scheduled for ", selectedTime);

		reportLog("7.1: Close Schedule Appointment control by clicking X button at the top right side.");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("7.2: Verify Reschedule & Cancel controls are displayed for the visit");
		subjectDetailPage.verifyRescheduleAndCancelBtnIsDisplayedForSelectedVisit(visitName);

		reportLog(
				"7.3: Verify Scheduled date/time is displayed for the visit following the format: dd-Mmm-YYYY hh:mm am/pm");
		subjectDetailPage.verifyVisitStatus(visitName,
				"Scheduled" + "\n" + VisitAppointmentDate + " " + selectedTime.toLowerCase());

		reportLog("7.4: Verify Assigned and Completed counts are displayed for that visit.");
		subjectDetailPage.VerifyAssignedAndCompletedCountIsDisplayedForSelectedVisit(visitName);

		reportLog("8.1: Click on Reschedule control.");
		subjectDetailPage.clickOnScheduledVisit();

		reportLog("8.2: Verify Appointment screen is displayed.");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog(
				"9.1: Repeat Step#4, Select available Time Slot differs from previously selected (Step 5) and Confirm with reason");
		String VisitAppointmentTime = subjectDetailPage.reScheduledVisitAppointmentTime();
		
		reportLog("11.1: Close Schedule Appointment control by clicking X button at the top right side");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("11.2: Verify Reschedule & Cancel controls are displayed for " + visitName + " visit");
		subjectDetailPage.verifyRescheduleAndCancelBtnIsDisplayedForSelectedVisit(visitName);

		reportLog(
				"11.3: Verify Scheduled date/time is displayed for the visit following the format: dd-Mmm-YYYY hh:mm am/pm");
		subjectDetailPage.verifyVisitStatus(visitName,
				"Scheduled" + "\n" + VisitAppointmentDate + " " + VisitAppointmentTime.toLowerCase());

		reportLog("11.4: Verify Assigned and Completed counts are displayed for that visit.");
		subjectDetailPage.VerifyAssignedAndCompletedCountIsDisplayedForSelectedVisit(visitName);

		reportLog("12.1: Click on Cancel Appointment");
		subjectDetailPage.clickOnCancelVisitIcon();

		reportLog("12.2: Verify Confirmation message is displayed with Reason");
		subjectDetailPage.verifyConfirmPopUpMessageIsDisplayed(Constants.Cancel_Appointment_Message);

		reportLog("13.1: Enter Reason in text box and confirm.");
		subjectDetailPage.inputReasonToCancelTheAppointment("Reason" + generateRandomString(10));

		reportLog("14.1: Click on yes button to cancel the visit.");
		subjectDetailPage.clickOnYesBtnToCancelTheVisit();

		reportLog("14.2: Verify Confirmation dialog is displayed and Click Ok in the confirmation modal.");
		subjectDetailPage.verifyConfirmationDialogIsDisplayedAndClickOnOkBTN();

		reportLog("14.3: Verify Appointment is cancelled for CR visit");
		subjectDetailPage.verifyVisitStatus(visitName, "Cancelled");

		reportLog("14.4: Verify Add visit option is enabled again for that visit");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.verifyAddVisitIconIsDisplayed();

		reportLog("14.5: Verify  Assigned count is decreased");
		subjectDetailPage.verifyAssignedVisitCount(visitName, "0");

		reportLog("15.1: Logout from the application");
		loginPage.logoutApplication();

		reportLog("15.2: Verify User is logout");
		loginPage.verifyUserLogout();

		reportLog("16.1: Login to Site Portal as the user without the claim canManageSelfServeCRAppointments");
		dashBoardPage = loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);

		reportLog("16.2: navigate to the Study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("16.2: Click on " + studyName + " from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();

		reportLog("16.3: Click on " + screeningNum + " from subject list");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		reportLog("16.4: Verify Subject details page is displayed with " + screeningNum + " subject Number");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("17.1: Click on " + visitName + " visit row");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("17.2: Verify The control to add CR appointments is not displayed for the logged in user");
		subjectDetailPage.verifyAddVisitIconIsNotDisplayed();

		reportLog("17.3: Logout from the application");
		loginPage.logoutApplication();

		reportLog("17.4: Verify User is logout");
		loginPage.verifyUserLogout();
	}
}
