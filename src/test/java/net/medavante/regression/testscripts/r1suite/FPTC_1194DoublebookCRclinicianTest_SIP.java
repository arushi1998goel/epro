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
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;


public class FPTC_1194DoublebookCRclinicianTest_SIP extends BaseTest {

	protected String studyName, userNameCentralRatingClaim, crVisitName1,
			subjectNameForScheduleVisit = "Subject1384" + generateRandomString(5),
			subjectName = "Subject1384" + generateRandomString(3), notesText = generateRandomString(3),
			hourForAppointment = "05", minuteForAppointment = "30", timeMarker = "AM",
			time = hourForAppointment + ":" + minuteForAppointment,
			timeToSelect = hourForAppointment + ":" + minuteForAppointment + timeMarker;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1194DoublebookCRclinicianTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("studyWithCentralRating");
		crVisitName1 = properties.getProperty("CRVisit");

		/*Creating Subject For Configuring Pre-requisite*/
		reportLog("Creating Subject For Configuring Pre-requisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectNameForScheduleVisit);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		/*Subject Created Successfully*/

		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectNameForScheduleVisit);
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, timeToSelect);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1194
	 * Test Case Name:Doublebook CR clinician
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1194_Doublebook CR clinician", groups = { "R1" })

	public void FPTC_1194_verifyDoublebookCRClinician() {

		reportLog("1:Navigate Back To CentralRatingAssesment Listing Page");
		
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("1.1:Click On Find Subject Icon");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("1.2:Find Subject");
		subjectDetailPage = centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(studyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("2: Click To Schedule The Visit");
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();

		reportLog("2.1: Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("3: Set Appointment  Date ");
		centralRatingAppointmentPage.setAppointmentDate();

		reportLog("3.1:Set Appointment Time ");
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("3.2:Verify Appointment Date And Time Has been Set");
		centralRatingAppointmentPage.verifyAppointmentDateTimeSet(time);

		reportLog("4:Select To View Qualified Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("4.1:Select Booked Clinician");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, timeToSelect);

		reportLog("4.2 Click On Schedule Appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("5:Verify Confirmation Meassage");
		String middleMessageText = centralRatingAppointmentPage.getMiddleMessage();
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(
				ApplicationVerificationMessage.doubleBookingCentralRatingFirstMessage);

		reportLog("5:Confirm Confirmation Message");
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("6:Logout application");
		loginPage.logoutApplication();

		reportLog("6:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
