package net.medavante.regression.testscripts.r1suite;

import java.text.ParseException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1389VerifyCancelRescheduleASelfScheduledCRVisit_SIP extends BaseTest {

	private String studyName, visitName, studyName2 = "01532", subjectName2 = "TestScreen";
	private String initiatedVisit, editingVisit, completedVisit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1389VerifyCancelRescheduleASelfScheduledCRVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("Auto_CR_Visit1");
		initiatedVisit = properties.getProperty("Auto_InitiatedCR_Visit");
		editingVisit = properties.getProperty("Auto_EditingCR_Visit");
		completedVisit = properties.getProperty("Auto_CompletedCR_Visit");

		reportLog("1.1: Login to Site Portal with site user with the claim 'canManageSelfServeCRAppointments'");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.2: Verify User login is successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to Study dashboard page");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select " + studyName + " from the study list");
		studyNavigatorDashBoardPage.selectStudy(studyName, AT_PRODSiteCoordinatorUserName);

		reportLog("1.5: Click on add subject button to create new subject to configure the test prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("1.6: Input " + screeningNum + " Screening num to configure the test prerequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("1.7: Select " + studyLanguage
				+ " language from the configured study language options to configure the test prerequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("1.8: Click on save button to save the subject to configure the test prerequiste");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("1.9: Select " + visitName + " visit to configure the test prerequiste");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("1.10: Select the add visit option to configure the test prerequiste");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("1.11: Verify A Scheduling calendar control pops up is displayed");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("1.12: Scheduled CR visit by selecting time slot");
		subjectDetailPage.selectAppointmentDateAndTime(clinicianTimeToBeSelect1);

		reportLog("1.13: Close Schedule Appointment control by clicking X button at the top right side.");
		subjectDetailPage.closeAppointmentPopup();
	}

	/**
	 * =========================================================================
	 * Test Case Id: FPTC_1389 Test Case Name: Cancel/reschedule a self-scheduled CR
	 * visit
	 * =========================================================================
	 * ===========================================
	 * 
	 * @throws ParseException
	 */

	@Test(description = "FPTC_1389_Cancel/reschedule a self-scheduled CR visit ", groups = { "R1" })

	public void FPTC_1389_verifyCancelRescheduleASelfScheduledCRVisit() throws ParseException {

		reportLog("1.17: Verify Subject Visit details page is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("2.1: Click on " + visitName + " visit row");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("2.2: Verify Reschedule & Cancel controls are displayed for the " + visitName + " Visit");
		subjectDetailPage.verifyRescheduleAndCancelBtnIsDisplayedForSelectedVisit(visitName);

		reportLog("3.1: Click on Reschedule option");
		subjectDetailPage.clickOnScheduledVisit();

		reportLog("3.2: Verify CR Self scheduling Appointment screen is displayed");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("3.3: ReScheduled " + visitName + " appointment time");
		String VisitAppointmentTime = subjectDetailPage.reScheduledVisitAppointmentTime();
		String VisitAppointmentDate = subjectDetailPage.getappointmentDateFromAppointmentConfirmationPopUp();

		reportLog("3.4: Close Schedule Appointment control by clicking X button at the top right side");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("3.5: Verify Reschedule & Cancel controls are displayed for " + visitName + " visit");
		subjectDetailPage.verifyRescheduleAndCancelBtnIsDisplayedForSelectedVisit(visitName);

		reportLog(
				"3.6: Verify Scheduled date/time is displayed for the visit following the format: dd-Mmm-YYYY hh:mm am/pm");
		subjectDetailPage.verifyVisitStatus(visitName,
				"Scheduled" + "\n" + VisitAppointmentDate + " " + VisitAppointmentTime.toLowerCase());

		reportLog("4.1: Logout from application");
		loginPage.logoutApplication();

		reportLog("4.2: Verify User is logout");
		loginPage.verifyUserLogout();

		reportLog(
				"4.3: Login to Site Portal with site user with the claim 'canManageSelfServeCRAppointments' and verify user is login");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("4.4: Navigate to Study dashboard page");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("4.6: Select " + screeningNum + " subject");
		studyNavigatorDashBoardPage.searchSubject(screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum);
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("4.7: Click on " + visitName + " visit");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("4.8: Verify Reschedule and Cancel Button Is Displayed for " + visitName + "visit");
		subjectDetailPage.verifyRescheduleAndCancelBtnIsDisplayedForSelectedVisit(visitName);

		reportLog("5.1: Click on Cancel option");
		subjectDetailPage.clickOnCancelVisitIcon();

		reportLog("5.2: Verify Cancellation confirm window is displayed with " + Constants.Cancel_Appointment_Message
				+ " message");
		subjectDetailPage.verifyConfirmPopUpMessageIsDisplayed(Constants.Cancel_Appointment_Message);

		reportLog(
				"5.3: Verify Cancellation Reason multiple-line input field is displayed and yes and no button is displayed");
		subjectDetailPage.verifyVisitCanceledPopUpFields();

		reportLog("6.1: Enter Reason in text box and confirm.");
		subjectDetailPage.inputReasonToCancelTheAppointment("Reason" + generateRandomString(5));

		reportLog("7.1: Click on yes button to cancel the visit.");
		subjectDetailPage.clickOnYesBtnToCancelTheVisit();

		reportLog("7.2: Verify Confirmation dialog is displayed and Click Ok in the confirmation modal.");
		subjectDetailPage.verifyConfirmationDialogIsDisplayedAndClickOnOkBTN();

		reportLog("7.3: Verify " + visitName + " visit is canceled and updated the visit status to " + cancelledStatus);
		subjectDetailPage.verifyVisitStatus(visitName, cancelledStatus);

		reportLog("8.1: Schedule " + visitName + " visit");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("8.2: Verify A Scheduling calendar control pops up is displayed");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("8.3: Scheduled CR visit by selecting time slot");
		subjectDetailPage.selectAppointmentDateAndTime(clinicianTimeToBeSelect1);

		reportLog("8.4: Close Schedule Appointment control by clicking X button at the top right side.");
		subjectDetailPage.closeAppointmentPopup();
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("8.5: Click on Cancel option");
		subjectDetailPage.clickOnCancelVisitIcon();

		reportLog("8.6: Click on No Button");
		subjectDetailPage.clickOnPopUpNoBtn();

		reportLog("8.7: Verify " + visitName + " visit is not cancelled");
		subjectDetailPage.verifyVisitStatus(visitName, scheduledStatus);

		reportLog("10.1: Back to dashboard Page");
		subjectDetailPage.scrollToTopOfThePage();
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,
				Constants.HomeNavText);

		reportLog("10.2: Navigate to study navigator Page");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("10.3: Select " + studyName2 + " study from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName2, Constants.VTAssignedRater_21);

		reportLog("10.4: Select " + subjectName2 + " Subject from subject list");
		studyNavigatorDashBoardPage.clickOnSubject(subjectName2);
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("10.5: Click on " + initiatedVisit + " visit row");
		subjectDetailPage.clickOnVisitRow(initiatedVisit);

		reportLog("10.6: Verify this visit cant be cancelled or rescheduled");
		subjectDetailPage.verifyVisitCancelledAndRescheduledBtnIsNotDisplayed();

		reportLog("11.1: Back to dashboard Page");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,
				Constants.HomeNavText);

		reportLog("11.2: Navigate to study navigator Page");
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("11.3: Select " + studyName2 + " study from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName2, Constants.VTAssignedRater_21);

		reportLog("11.4: Select " + subjectName2 + " Subject from study list");
		studyNavigatorDashBoardPage.clickOnSubject(subjectName2);
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("11.5: Click on " + editingVisit + " visit row");
		subjectDetailPage.clickOnVisitRow(editingVisit);

		reportLog("11.6: Verify this visit cant be cancelled or rescheduled");
		subjectDetailPage.verifyVisitCancelledAndRescheduledBtnIsNotDisplayed();

		reportLog("12.1: Back to dashboard Page");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,
				Constants.HomeNavText);

		reportLog("12.2: Navigate to study navigator Page");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("12.3: Select " + studyName2 + " study from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName2, Constants.VTAssignedRater_21);

		reportLog("12.4: Select " + subjectName2 + " from Subject list");
		studyNavigatorDashBoardPage.clickOnSubject(subjectName2);
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("12.5: Click on " + completedVisit + " visit");
		subjectDetailPage.clickOnVisitRow(completedVisit);

		reportLog("12.6: Verify this visit cant be cancelled or rescheduled");
		subjectDetailPage.verifyVisitCancelledAndRescheduledBtnIsNotDisplayed();

		reportLog("12.7: Logout from application");
		loginPage.logoutApplication();

		reportLog("12.8: Verify User Logout from application");
		loginPage.verifyUserLogout();
	}

}
