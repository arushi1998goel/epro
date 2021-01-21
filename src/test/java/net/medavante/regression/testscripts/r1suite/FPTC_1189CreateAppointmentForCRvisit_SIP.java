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

public class FPTC_1189CreateAppointmentForCRvisit_SIP extends BaseTest {

	protected String studyName, visitName, userNameCentralRatingClaim,
			subjectName = "SUBJ1379" + generateRandomString(4), notesText = generateRandomString(3),
			hourForAppointment = "08", minuteForAppointment = "00", timeMarker = "AM",
			time = hourForAppointment + ":" + minuteForAppointment;
	String timeToSelect = hourForAppointment + ":" + minuteForAppointment + timeMarker;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1189CreateAppointmentForCRvisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("studyWithCentralRating");
		visitName = properties.getProperty("CRVisit");
		
		/*Creating Subject For Configuring Pre-requisite*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes(notesText);
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		/*Subject Created Successfully*/
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1189 Test Case Name: Create Appointment For CR Visit
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1189_CreateAppointmentForCRvisit", groups = { "R1" })
	public void FPTC_1189_verifyCreateAppointmentForCrVisit() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();


		reportLog("1.1:Click on study navigator tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.2:Click On Find Subject Icon");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("1.3:Select Study and Site");
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyName, Constants.ATAssignedRater_10);

		reportLog("1.4 Verify Subject List Is Present");
		centralRatingAssesmentListingPage.verifySubjectListDisplayedAfterSelectingStduyAndSiteFromDrpDown();

		reportLog("1.5 Verify   Subject Without Schedule CR Visit  Is Present");
		centralRatingAssesmentListingPage.verifySubjectWithoutScheduleCrVisitPresentInList(subjectName);

		reportLog("2.1:Select Subject From dropDown");
		subjectDetailPage = centralRatingAssesmentListingPage
				.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);

		reportLog("2.2: Verify Configure Visit Is Displayed");
		subjectDetailPage.verifyVisitIsPresentInList();

		reportLog("3:Click To Schedule The  Visit");
		subjectDetailPage.clickOnVisitRow(visitName);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();

		reportLog("3.1:Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("4:Set Notification  Date And Time");
		centralRatingAppointmentPage.setNotificationDate();

		reportLog("4.1:Set Notification Time ");
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("5:Verify Notification Date And Time Has been Set");
		centralRatingAppointmentPage.verifyNotificationDateTimeSet(time);

		reportLog("5.1:Set Appointment  Date ");
		centralRatingAppointmentPage.setAppointmentDate();

		reportLog("5:Set Appointment Time ");
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("5.2:Verify Appointment Date And Time Has been Set");
		centralRatingAppointmentPage.verifyAppointmentDateTimeSet(time);

		reportLog("6:Select To View Qualified Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("6.1 Verify clinician With Calender Is Displayed");
		centralRatingAppointmentPage.verifyClinicianWithCalenderIsDisplayed();

		reportLog("7:Select Clinician Without Schedule Appointment");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, timeToSelect);

		reportLog("7.1:Click On Schedule Appointment Button");
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("7.3:Verify Confirmation Pop Up Message");
		String middleMessageText = centralRatingAppointmentPage.getMiddleMessage();
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(
				ApplicationVerificationMessage.scheduleCentralRatingAssesmentMessage);

		reportLog("8:Confirm Confirmation Of PopUp Message");
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("8.1:Verify Visit Status");
		centralRatingAppointmentPage.verifyVisitStatus(Constants.scheduled_Status);

		reportLog("8.2:Verify User Remains On CR visit Screen");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("8.3:Logout application");
		loginPage.logoutApplication();

		reportLog("8.4:Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
