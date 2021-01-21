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
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1411DoublebookedCRAppointmentsCanceledFromPortalTest_SIP extends BaseTest {

	protected String crStudyName,userNameWithClaim;

	protected String subjectName1 = "SUBJ1_" + generateRandomString(4);
	protected String subjectName2 = "SUBJ2_" + generateRandomString(4);

	protected String crVisitName1, crVisitName2, crVisitName3, crVisitName4;
	protected String reasonCommentTXT = "test" + generateRandomString(3),

	hourForAppointment1 = "08", minuteForAppointment1 = "00", timeMarker1 = "AM",
			time1 = hourForAppointment1 + ":" + minuteForAppointment1;
	String timeToSelect1 = hourForAppointment1 + ":" + minuteForAppointment1 + timeMarker1,
			hourForAppointment2 = "09", minuteForAppointment2 = "45", timeMarker2 = "PM",
			time2 = hourForAppointment1 + ":" + minuteForAppointment1;
	String timeToSelect2 = hourForAppointment2 + ":" + minuteForAppointment2 + timeMarker1;
	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1411DoublebookedCRAppointmentsCanceledFromPortalTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	
		Properties prop = Configuration.readTestData("RegressionTestData");
		crStudyName = prop.getProperty("studyName");
		crVisitName1 = prop.getProperty("visitName1");
		crVisitName2 = prop.getProperty("visitName2");

		crVisitName3 = prop.getProperty("visitReScheduling");
		crVisitName4 = prop.getProperty("visitName4");

		// Creating two subjects

		reportLog("Creating subject 1");
		//dashBoardPage = loginPage.loginInApplication(userName, password);
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
	    studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(crStudyName, Constants.ATAssignedRater_10,subjectName1);
	    subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
	    subjectDetailPage.navigateBack();
	    
	    reportLog("Create subject 2");
	    studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectName2);
	    subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
		loginPage.logoutApplication();
		
		// schedule 2-2 visits with two different subjects
		
		reportLog("Schedule 1 visits with sub1");
		loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(crStudyName, Constants.ATAssignedRater_10);
		subjectDetailPage = centralRatingAssesmentListingPage.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName1);
		subjectDetailPage.clickOnVisitRow(crVisitName1);
	    centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, timeToSelect1);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("Schedule 2 visits with sub1");
		centralRatingAppointmentPage.navigateToListingPage();
		subjectDetailPage.clickOnVisitRow(crVisitName2);
		subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment2, minuteForAppointment2, timeMarker1);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment2, minuteForAppointment2, timeMarker1);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, timeToSelect2);
		
		reportLog("Schedule 3 visits with sub2");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		centralRatingAssesmentListingPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(crStudyName, Constants.ATAssignedRater_10);
		centralRatingAssesmentListingPage.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName2);
		subjectDetailPage.clickOnVisitRow(crVisitName3);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment1, minuteForAppointment1, timeMarker1);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, timeToSelect1);
		
		reportLog("Schedule 4 visits with sub2");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		
		centralRatingAppointmentPage.navigateToListingPage();
		subjectDetailPage.clickOnVisitRow(crVisitName4);
		subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment2, minuteForAppointment2, timeMarker1);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment2, minuteForAppointment2, timeMarker1);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, timeToSelect2);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		loginPage.logoutApplication();
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1411 Test Case Name: Cancel doublebooked CR
	 * Appointments when appointment has been removed from exchange
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1411_Cancel doublebooked CR Appointments when appointment has been removed from exchange ", groups = {
			"R1" })

	public void FPTC_1411_verifyDoublebookedCRAppointmentsCanBeCanceledFromPortal() {

		reportLog("1:Login in to application");
	    dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport,AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to CR appointment page");
	    dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);


		reportLog("2.1: CR assessment page is displayed. ");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("2.2 Applied filter with Visit Status, Subject and Visit name");
		centralRatingAssesmentListingPage.searchByVisitStatus(CentralRatingModuleConstants.scheduledStatus);
		centralRatingAssesmentListingPage.sortByVisitName(crVisitName1);

		reportLog("2.3 Click on applied filter row");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue("Visit Name", crVisitName1);

	    centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnVisitRow();
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("2.4: Option to cancel appointment is visible and enabled");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("3.1: Select the option to cancel");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("3.2: Verify Cancel PopUp Message");
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(ApplicationVerificationMessage.cancelCentralRatingAppointmentMessage);

		reportLog("3.3: Select the reason, provide comment and select the option to proceed");
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, reasonCommentTXT);

		reportLog("3.4: Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("3.5: Visit Status Change To Pending");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.pendingStatus);

		reportLog("3.6: Verify User Remains On the Same Screen");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("4 : Repeat Step#2 and Step#3 for PR#5.2");
		centralRatingAppointmentPage.navigateBack();

		reportLog("4.1 Applied filter with Visit Status, Subject and Visit name");
		centralRatingAssesmentListingPage.searchByVisitStatus(CentralRatingModuleConstants.scheduledStatus);
		centralRatingAssesmentListingPage.sortByVisitName(crVisitName2);

		reportLog("4.2 Click on applied filter row");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue("Visit Name", crVisitName2);

		centralRatingAssesmentListingPage.clickOnVisitRow();
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("4.3: Option to cancel appointment is visible and enabled");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("4.4: Select the option to cancel");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("4.5: Verify Cancel PopUp Message");
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(ApplicationVerificationMessage.cancelCentralRatingAppointmentMessage);

		reportLog("4.6: Select the reason, provide comment and select the option to proceed");
		centralRatingAppointmentPage.clickOnReasonDropDown();
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, reasonCommentTXT);

		reportLog("4.7: Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("4.8: Visit Status Change To Pending");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.pendingStatus);

		reportLog("4.9: Verify User Remains On the Same Screen");
		centralRatingAppointmentPage.verifyAppointmentPage();
		centralRatingAppointmentPage.navigateBack();

		reportLog("5.1: Logout application");
		loginPage.logoutApplication();

		reportLog("5.2: Verify user is logout");
		loginPage.verifyUserLogout();
	}
}