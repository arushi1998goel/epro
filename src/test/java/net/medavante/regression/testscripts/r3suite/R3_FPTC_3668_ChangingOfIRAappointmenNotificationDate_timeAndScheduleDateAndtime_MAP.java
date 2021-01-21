/**
 *@author 
* @date 17/03/2020
* =========================================================================
     *  Test Case Id: FP-TC-3668 || Test Case Name: Changing of IR appointment Notification date/time and Scheduled date/time

     * pre-qualification :   At least one Study with at least one configured visit with IR assessment exists for the test
* At least one Subject for the Study Pr#1 exists
* At least one User who is able to schedule IR visit for Pr#2 exists.
* At least one Clinician who is able to conduct IR assessment in the Study Pr#1 exists
* ========================================================================= 
*/
package net.medavante.regression.testscripts.r3suite;

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

public class R3_FPTC_3668_ChangingOfIRAappointmenNotificationDate_timeAndScheduleDateAndtime_MAP extends BaseTest
{
	protected String studyName,crVisitName,subjectName = "SUBJ3668" + generateRandomString(4),
			hourForAppointment = "07", minuteForAppointment = "30", timeMarker = "PM";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3668_ChangingOfIRAappointmenNotificationDate_timeAndScheduleDateAndtime_MAP(String browser) {
		super(browser);
	}
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("studyNameTestCase2227");
		crVisitName = properties.getProperty("visitName");
		
//		/* Creating Subject For Configuring Pre-requisite */
//		reportLog("1:Login To Application And Setting Up The Pre-Requisite");
//		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
//		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
//				Constants.NavigateText, Constants.StudyText);
//		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
//		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
//		subjectDetailPage.verifyNewSubjectDetailPage();
//		loginPage.logoutApplication();	

		
	}
	@Test(description = "FP-TC-3668 --Changing of IR appointment Notification date/time and Scheduled date/time", groups = { "R3" })
	public void R3_3668_ChangingOfIRAappointmenNotificationDate_timeAndScheduleDateAndtime() {
		reportLog("2.1: Log in to the Site Portal as the User in Pr#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("2.2: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.1:  Navigate to the Subject Details screen of Pr#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSubject("SUBJ3668viyi");
		
		reportLog("3.2:Select an Action to initiate  Visit");
		subjectDetailPage.clickOnVisitRow(crVisitName);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		
		reportLog("3.3: Appointment screen is displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();
		
		reportLog("3.4: verify Notification date is present with current date");
		centralRatingAppointmentPage.verifyNotiFicationDateIsCurrentDate();
		
		reportLog("3.5: verify schedule date is empty");
		centralRatingAppointmentPage.scheduleDateFieldIsEmpty();
		
		reportLog("4.1: change notification time");
		centralRatingAppointmentPage.clickonNotificationtimepicker();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		String expecteddateTime = centralRatingAppointmentPage.GetNotiFicationFieldTime();
		
		
		reportLog("4.2: Notification Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyNotificationFieldReflectsAccordinglyDateTime(expecteddateTime);
				
		reportLog("5.1: Change Notification date and time to the past");
		centralRatingAppointmentPage.setNotificationPastDate();
		centralRatingAppointmentPage.changeNotificationTime();
		String PastDatetime=centralRatingAppointmentPage.GetNotiFicationFieldTime();
		
		reportLog("5.2: Notification Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyNotificationFieldReflectsAccordinglyDateTime(PastDatetime);

		reportLog("6.1: Change Notification date and time to the current date and time");
		centralRatingAppointmentPage.setNotificationCurrentDate();
		centralRatingAppointmentPage.changeNotificationTime();
		String CurrentDatetime=centralRatingAppointmentPage.GetNotiFicationFieldTime();

		reportLog("6.2: Notification Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyNotificationFieldReflectsAccordinglyDateTime(CurrentDatetime);
		
		reportLog("7.1: Change Notification date and time to the future");
		centralRatingAppointmentPage.setNotificationFutureDate();
		centralRatingAppointmentPage.changeNotificationTime();
		String FutureDatetime=centralRatingAppointmentPage.GetNotiFicationFieldTime();
		
		reportLog("7.2: Notification Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyNotificationFieldReflectsAccordinglyDateTime(FutureDatetime);
		
		reportLog("8.1: Select an action to clear Notification date and time");
		centralRatingAppointmentPage.clearNotoficationDateTime();
		
        reportLog("8.2: Change Notification date and time to the current date and time");
		centralRatingAppointmentPage.setNotificationCurrentDate();
		centralRatingAppointmentPage.changeNotificationTime();
		String CurrentDatetime_1=centralRatingAppointmentPage.GetNotiFicationFieldTime();

		reportLog("8.3: Notification Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyNotificationFieldReflectsAccordinglyDateTime(CurrentDatetime_1);

		reportLog("9.1: Set Scheduled date and time to the current date and time");
		centralRatingAppointmentPage.setScheduleCurrentDate();
		String schedulefieldCurrentdateAndTime = centralRatingAppointmentPage.GetScheduledFieldDateTimeValue();
		
		reportLog("9.2: Scheduled Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyScheduledFieldReflectsAccordinglyDateTime(schedulefieldCurrentdateAndTime);
				
		reportLog("10.1: Change Scheduled time");
		centralRatingAppointmentPage.clickOnScheduledFieldtimePicker();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		String schedulefielddateAndTime = centralRatingAppointmentPage.GetScheduledFieldDateTimeValue();
		
		reportLog("10.2: Scheduled Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyScheduledFieldReflectsAccordinglyDateTime(schedulefielddateAndTime);

		reportLog("11.1: Change Scheduled date and time to the past");
		centralRatingAppointmentPage.setSchedulePastDate();
		centralRatingAppointmentPage.changeScheduleTime();
		String scheduldfieldPastdateAndTime = centralRatingAppointmentPage.GetScheduledFieldDateTimeValue();
		
		reportLog("11.2: Scheduled Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyScheduledFieldReflectsAccordinglyDateTime(scheduldfieldPastdateAndTime);

		reportLog("12.1: Change Scheduled date and time to the current date and time");
		centralRatingAppointmentPage.setScheduleCurrentDate();
		String scheduldfieldCurrentdateAndTime = centralRatingAppointmentPage.GetScheduledFieldDateTimeValue();

		reportLog("12.2: Scheduled Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyScheduledFieldReflectsAccordinglyDateTime(scheduldfieldCurrentdateAndTime);

		reportLog("13.1: Change Scheduled date and time to the future");
		centralRatingAppointmentPage.setScheduleFutureDate();
		centralRatingAppointmentPage.changeScheduleTime();
		String scheduldfieldFuturedateAndTime = centralRatingAppointmentPage.GetScheduledFieldDateTimeValue();
		
		reportLog("13.2: Scheduled Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyScheduledFieldReflectsAccordinglyDateTime(scheduldfieldFuturedateAndTime);

		reportLog("14.1: Select an action to clear Scheduled date and time");
		centralRatingAppointmentPage.clearScheduleDateTime();
		
		reportLog("14.2: Change Scheduled date and time to the current date and time");
		centralRatingAppointmentPage.setScheduleCurrentDate();
		String scheduldfieldCurrentdateAndTime_1 = centralRatingAppointmentPage.GetScheduledFieldDateTimeValue();

		reportLog("14.3: Scheduled Date/Time picker field reflects the corresponding date and time");
		centralRatingAppointmentPage.verifyScheduledFieldReflectsAccordinglyDateTime(scheduldfieldCurrentdateAndTime_1);
		
		reportLog("15.0:Select an action to Pick One");
		centralRatingAppointmentPage.clickOnPickClinician();
		
		reportLog("15.1: Select the time slot for the Clinician Pr#4");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "03:30pm");
		
		reportLog("15.2: Fill all required fields, schedule and initiate the appointment");

		
		
			
		
		
		
		
	}

	}


