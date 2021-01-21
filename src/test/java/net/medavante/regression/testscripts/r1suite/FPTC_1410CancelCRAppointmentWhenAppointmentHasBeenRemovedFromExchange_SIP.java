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

public class FPTC_1410CancelCRAppointmentWhenAppointmentHasBeenRemovedFromExchange_SIP extends BaseTest {

	protected String subjectName = "Subject19231" + generateRandomString(4),
			subjectNameForScheduleVisit = "Subject19233" + generateRandomString(3),
			SubjectNameForOtherScheduleVisit = "Subject19232" + generateRandomString(5), crScheduledVisitName,
			siteClinicanName, commentTxt = "Test" + generateRandomString(3);
	protected String hourForAppointment = "03", minuteForAppointment = "00", timeMarker = "PM", hours = "08",
			minute = "00", timeMark = "PM", appointment2Hours = "04", appointmentThirdMinute = "00",
			appointmentThirdTimeMarker = "PM", studyName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1410CancelCRAppointmentWhenAppointmentHasBeenRemovedFromExchange_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("studyNameTestCase2227");
		crScheduledVisitName = prop.getProperty("auto_CrScheduled_Visit");
		siteClinicanName = prop.getProperty("clinicianNameTimeZoneSame");
		
		/* Creation Of Subject */
		reportLog("Creation Of Subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectNameForScheduleVisit);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
	
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.navigateBack();

		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(SubjectNameForOtherScheduleVisit);
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		loginPage.logoutApplication();
		/*Successfully Created*/

		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);
		subjectDetailPage.clickOnVisitRow(crScheduledVisitName);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "03:00pm");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectNameForScheduleVisit);
		subjectDetailPage.clickOnVisitRow(crScheduledVisitName);
		subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hours, minute, timeMark);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "08:00pm");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(SubjectNameForOtherScheduleVisit);
		subjectDetailPage.clickOnVisitRow(crScheduledVisitName);
		subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(appointment2Hours, appointmentThirdMinute,
				appointmentThirdTimeMarker);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "04:00pm");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1410 Test Case Name:Cancel CR Appointment when
	 * appointment has been removed from exchange
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1410_Cancel CR Appointment when appointment has been removed from exchange", groups = {
			"R1" })

	public void FPTC_1410_verifyCrAppointmentDetailsAppointmentHistoryIsUpdatedAfterEachChangeOfCrVisitStatus() {

		reportLog("1.1:Navigate To Listing Page");
		centralRatingAppointmentPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.2 :Central Rating List Page");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("1.3 Navigate To the pr#6.1 Cr Appointment Page");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		centralRatingAssesmentListingPage.sortByVisitName(crScheduledVisitName);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnVisitRow();

		reportLog("2:Verify CR Appointment Displayed");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("2.1 Verify Cancel Appointment Visible And Enabled");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("3:Select Option To Cnacel Appointment");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("3.1:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("3.2: Select Reason And Reason Comment");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);

		reportLog("3.3:Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("3.4 Schedule Date Time Is Empty");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate", "");

		reportLog("3.5:Visit Status Change To Pending");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.pendingStatus);

		reportLog("3.6:Verify  Appointment Page");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("4:Repeating Steps For Pr#7.1");
		centralRatingAppointmentPage.navigateToListingPage();

		reportLog(
				"4.1 Search CR Visit has been scheduled for subject  with clinician which Time Zone Is Diffrent From Site Time Zone as #pr7.1");
		centralRatingAssesmentListingPage.sortByVisitName(crScheduledVisitName);
		centralRatingAssesmentListingPage.sortBySubjectName(subjectNameForScheduleVisit);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnVisitRow();

		reportLog("4.2:Veridy CR Appointment Displayed");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("4.3: Verify Cancel Appointment Visible And Enabled");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("4.4:Select Option To Cancel Appointment");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("4.5:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("4.6:Select Reason And Reason Comment");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);

		reportLog("4.7:Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("4.8:Schedule Date Time Is Empty");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate", "");

		reportLog("4.9:Visit Status Change To Pending");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.pendingStatus);

		reportLog("4.10:Verify  Appointment Page");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("5:Repeating Steps For Pr#8.1");
		centralRatingAppointmentPage.navigateToListingPage();

		reportLog("5.1 CR Visit has been scheduled for subject in PR#8");
		centralRatingAssesmentListingPage.sortByVisitName(crScheduledVisitName);
		centralRatingAssesmentListingPage.sortBySubjectName(SubjectNameForOtherScheduleVisit);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnVisitRow();

		reportLog("5.2 Veridy CR Appointment Displayed");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("5.3 Verify Cancel Appointment Visible And Enabled");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("5.4:Select Option To Cancel Appointment");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("5.5:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("5.6: Select Reason And Reason Comment");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(
				Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, commentTxt);

		reportLog("5.7:Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("5.8: Schedule Date Time Is Empty");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate", "");

		reportLog("5.9:Visit Status Change To Pending");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.pendingStatus);

		reportLog("5.10:Verify  Appointment Page");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
