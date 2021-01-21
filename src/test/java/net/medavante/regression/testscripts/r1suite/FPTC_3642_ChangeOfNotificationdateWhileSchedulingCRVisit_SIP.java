

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

public class FPTC_3642_ChangeOfNotificationdateWhileSchedulingCRVisit_SIP extends BaseTest {

	protected String subjectName = "Subject1819" + generateRandomString(5), studyName, visitName, visitNameSecond,
			hourForAppointment = "01", minuteForAppointment = "30", timeMarker = "PM", hourForAppointment1 = "07",
			minuteForAppointment1 = "00", timeMarker1 = "PM", usersTimeZone, userTimeZoneSecond,
			time = hourForAppointment + minuteForAppointment + timeMarker;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3642_ChangeOfNotificationdateWhileSchedulingCRVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("studyNameTestCase2227");
		visitName = prop.getProperty("visitName3");
		visitNameSecond = prop.getProperty("visitName4");
		
		/*Creation Of Subject*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		/*Created Successfully*/

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3642 || Test Case Name:Change of Notification date
	 * while scheduling a CR Visit
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-3642_Change of Notification date while scheduling a CR Visit ", groups = { "R1" })
	public void FPTC_3642_verifyChangeOfNotificationdateWhileSchedulingCrVisit() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1:Click on Central Rating tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.2:Click On Find Subject Icon");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("1.3:Find Subject");
		subjectDetailPage = centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(studyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("1.4 Visit List Contains The Visit");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visitName);
		subjectDetailPage.verifyVisitAddedIsPresentInList(visitNameSecond);

		reportLog("1.5 Subject detail Page Displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName);
		subjectDetailPage.verifySubjectStudyAndSite(studyName, Constants.ATAssignedRater_10);

		reportLog("2:Select To Add Visit");
		subjectDetailPage.clickOnVisitRow(visitName);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
	
		reportLog("2.1 Verify Notificatiion Time Set As Current Date Time Of Users Time Zone");
		usersTimeZone = dashBoardPage.getSystemTime();
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate", usersTimeZone);

		reportLog("2.2: Schedule Date Time Is Empty");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate", "");

		reportLog("3:Set Notification Date/Time");
		centralRatingAppointmentPage.scheduleNotificationAndAppointmentDateForNextDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		String updatedTime = centralRatingAppointmentPage.getDateTime("notificationDate");

		reportLog("3.1 Verify Notification Time Updated");
		centralRatingAppointmentPage.verifyUpdatedDateTime(usersTimeZone);

		reportLog("3.2 Verify Time Can Be Updated By One minute Intervals");
		centralRatingAppointmentPage.increaseOnemiuteTimeInterval();
		String oneMinuteIncreaseTime = centralRatingAppointmentPage.getDateTime("notificationDate");
		centralRatingAppointmentPage.verifyTimeUpdatedByOneMinuteIntervals(updatedTime, oneMinuteIncreaseTime);
		String afterUpdationTime = centralRatingAppointmentPage.getDateTime("notificationDate");

		reportLog("4:Set Appointment Date/Time");
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("4.1 Pick Clinican");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("4.2 Book Appointment");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "01:30pm");

		reportLog("4.3 Click On Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("4.4 Verify Notification date/time same as step:3");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate", afterUpdationTime);

		reportLog("5:Navigate Back to Subject Detail Page");
		centralRatingAppointmentPage.navigateBack();

		reportLog("5.1 Subject detail Page Displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName);
		subjectDetailPage.verifySubjectStudyAndSite(studyName, Constants.ATAssignedRater_10);

		reportLog("6: Navigate To the CR Appointment Screen Of step:2");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnSchedulerIcon();

		reportLog("6.1 Verify Notification date/time same as step:4");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate", afterUpdationTime);

		reportLog("7:Navigate Back To Subject Details Screen");
		centralRatingAppointmentPage.navigateToListingPage();
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(studyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("7.1: Select to Add Other visit");
		subjectDetailPage.clickOnVisitRow(visitNameSecond);
		subjectDetailPage.clickOnAddVisitIcon();
		userTimeZoneSecond = dashBoardPage.getSystemTime();

		reportLog("7.2: Verify Notificatiion Time Set As Current Date Time Of Users Time Zone");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate", userTimeZoneSecond);

		reportLog("7.3: Schedule Date Time Is Empty");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("scheduledDate", "");

		reportLog("8:Complete Scheduling The Appointment Without Updating NotificationDate/Time");
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);

		reportLog("8.1:Pick Clinican");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("8.2 Book Appointment");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "07:00pm");

		reportLog("8.3 Click On Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("8.4 Verify Appointment Scheduled");
		userTimeZoneSecond=dashBoardPage.getSystemTime();
		centralRatingAppointmentPage.verifyVisitStatus("Scheduled");

		reportLog("8.5 Verify Notificatiion Time reset As Current Date Time Of Users Time Zone");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate", userTimeZoneSecond);

		reportLog("9:Navigate Back To Subject Details Screen");
		centralRatingAppointmentPage.navigateBack();

		reportLog("9.1 Subject detail Page Displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName);
		subjectDetailPage.verifySubjectStudyAndSite(studyName, Constants.ATAssignedRater_10);

		reportLog("10 select visit And Navigate Back to Cr Appointment Screen of step:8");
		subjectDetailPage.clickOnVisitRow(visitNameSecond);
		centralRatingAppointmentPage = subjectDetailPage.clickOnSchedulerIcon();

		reportLog("10.1 Notification Date/Time Same As Step:8");
		centralRatingAppointmentPage.verifyTimeZoneIsSameAsGivenTimeZone("notificationDate", userTimeZoneSecond);

		reportLog("10.2:Logout application");
		loginPage.logoutApplication();

		reportLog("10.3:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
