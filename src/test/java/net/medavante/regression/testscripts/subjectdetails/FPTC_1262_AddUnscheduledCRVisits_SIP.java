package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1262_AddUnscheduledCRVisits_SIP extends BaseTest {

	private String siteRater1LoginUN, studyName, Subject, UnscheduledCRvisit_1, UnscheduledCRvisit_2,
			UnschedulednonCRvisit_1, UnschedulednonCRvisit_2, hourForAppointment = "08", minuteForAppointment = "00",
			timeMarker = "AM", time = hourForAppointment + ":" + minuteForAppointment;
	String timeToSelect = hourForAppointment + ":" + minuteForAppointment + timeMarker;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1262_AddUnscheduledCRVisits_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties userCredentials = Configuration.readTestData("userClaimsCredentials");
		siteRater1LoginUN = userCredentials.getProperty("PRODSiteCoordinatorCRSelfSchedule");
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		UnscheduledCRvisit_1 = properties.getProperty("UnscheduledCRvisit_1");
		UnscheduledCRvisit_2 = properties.getProperty("UnscheduledCRvisit_2");
		UnschedulednonCRvisit_1 = properties.getProperty("UnscheduledVisit_1");
		UnschedulednonCRvisit_2 = properties.getProperty("UnscheduledVisit_2");
		Subject = properties.getProperty("Subject1974");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1262 Test Case Name: Add unscheduled CR visits
	 * =========================================================================
	 */

	@Test(description = "FP-TC-1262_Add unscheduled CR visits  ", groups = { "" })
	public void FPTC_1262_AddUnscheduledCRVisits() throws Exception {

		reportLog("1.1: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, AT_Password);

		reportLog("1.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: select " + studyName + " study.");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.VTAssignedRater_21);

		reportLog("1.5:Select Subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				Subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(Subject);

		reportLog("1.6:Verify Subject Details Page");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("1.7: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("1.8: Verify Option to add Unscheduled visits is available");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();

		reportLog("3.1: Select the option to add the unscheduled visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("3.2: verify Unscheduled Visit List is Displayed");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("3.3: verify Unscheduled visit is displayed in visit list");
//		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(UnscheduledCRvisit_1);
//		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(UnscheduledCRvisit_2);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(UnschedulednonCRvisit_1);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(UnschedulednonCRvisit_2);

//		reportLog("4.1: Select CR visit from Unscheduled visit list");
//		centralRatingAppointmentPage = subjectDetailPage.selectCRVisitFromUnscheduledVisitList(UnscheduledCRvisit_1);
//
//		reportLog("4.2: Verify CR Appointment screen is visible");
//		centralRatingAppointmentPage.verifyAppointmentPage();
//
//		reportLog("5.1:Set Appointment  Date ");
//		centralRatingAppointmentPage.setAppointmentDate();
//
//		reportLog("5.2:Set Appointment Time ");
//		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
//
//		reportLog("5.3:Verify Appointment Date And Time Has been Set");
//		centralRatingAppointmentPage.verifyAppointmentDateTimeSet(time);
//
//		reportLog("5.4:Select To View Qualified Clinician");
//		centralRatingAppointmentPage.clickOnPickClinician();
//
//		reportLog("5.5 Verify clinician With Calender Is Displayed");
//		centralRatingAppointmentPage.verifyClinicianWithCalenderIsDisplayed();
//
//		reportLog("5.6:Select Clinician Without Schedule Appointment");
//		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
//				CentralRatingModuleConstants.clinician16Name, timeToSelect);
//
//		reportLog("5.7:Click On Schedule Appointment Button");
//		centralRatingAppointmentPage.clickOnScheduleAppointment();
//
//		reportLog("5.8:Verify Confirmation Pop Up Message");
//		String middleMessageText = centralRatingAppointmentPage.getMiddleMessage();
//		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(
//				ApplicationVerificationMessage.scheduleCentralRatingAssesmentMessage, middleMessageText, "?");
//
//		reportLog("5.9:Confirm Confirmation Of PopUp Message");
//		centralRatingAppointmentPage.confirmationOfPopUpMessage();
//
//		reportLog("5.10:Verify Visit Status");
//		centralRatingAppointmentPage.verifyVisitStatus(Constants.scheduled_Status);
//
//		reportLog("5.11:Verify Visit Status");
//		centralRatingAppointmentPage.navigateToSubjectDetailsPage();

		reportLog("5.12:Verify Subject Details Page");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("6.1: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("6.2: Verify Option to add Unscheduled visits is available");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();

//		reportLog("6.3: Select the option to add the unscheduled visit");
//		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("6.4: verify Unscheduled Visit List is Displayed");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("6.5: verify Unscheduled visit is not displayed in visit list");
		subjectDetailPage.verifyVisitNotContainsInUnscheduledVisitList(UnscheduledCRvisit_1);

		reportLog("7.1.1: verify Unscheduled visit is displayed in visit list");
//		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(UnscheduledCRvisit_2);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(UnschedulednonCRvisit_1);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(UnschedulednonCRvisit_2);

//		reportLog("7.2.1: Select CR visit from Unscheduled visit list");
//		centralRatingAppointmentPage = subjectDetailPage.selectCRVisitFromUnscheduledVisitList(UnscheduledCRvisit_2);
//
//		reportLog("7.2.2: Verify CR Appointment screen is visible");
//		centralRatingAppointmentPage.verifyAppointmentPage();
//
//		reportLog("7.3.1:Set Appointment  Date ");
//		centralRatingAppointmentPage.setAppointmentDate();
//
//		reportLog("7.3.2:Set Appointment Time ");
//		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
//
//		reportLog("7.3.3:Verify Appointment Date And Time Has been Set");
//		centralRatingAppointmentPage.verifyAppointmentDateTimeSet(time);
//
//		reportLog("7.3.4:Select To View Qualified Clinician");
//		centralRatingAppointmentPage.clickOnPickClinician();
//
//		reportLog("7.3.5 Verify clinician With Calender Is Displayed");
//		centralRatingAppointmentPage.verifyClinicianWithCalenderIsDisplayed();
//
//		reportLog("7.3.6:Select Clinician Without Schedule Appointment");
//		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
//				CentralRatingModuleConstants.clinician16Name, timeToSelect);
//
//		reportLog("7.3.7:Click On Schedule Appointment Button");
//		centralRatingAppointmentPage.clickOnScheduleAppointment();
//
//		reportLog("7.3.8:Verify Confirmation Pop Up Message");
//		String middleMessageText1 = centralRatingAppointmentPage.getMiddleMessage();
//		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(
//				ApplicationVerificationMessage.scheduleCentralRatingAssesmentMessage, middleMessageText1, "?");
//
//		reportLog("7.3.9:Confirm Confirmation Of PopUp Message");
//		centralRatingAppointmentPage.confirmationOfPopUpMessage();
//
//		reportLog("7.3.10:Verify Visit Status");
//		centralRatingAppointmentPage.verifyVisitStatus(Constants.scheduled_Status);
//
//		reportLog("7.3.11:Verify Visit Status");
//		centralRatingAppointmentPage.navigateToSubjectDetailsPage();

		reportLog("7.3.12:Verify Subject Details Page");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("7.4.1: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("7.4.2: Verify Option to add Unscheduled visits is available");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();

//		reportLog("7.4.3: Select the option to add the unscheduled visit");
//		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("7.4.4: verify Unscheduled Visit List is Displayed");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("7.4.5: verify Unscheduled visit is not displayed in visit list");
		subjectDetailPage.verifyVisitNotContainsInUnscheduledVisitList(UnscheduledCRvisit_2);
		

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
