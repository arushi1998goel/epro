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

public class FPTC_1197RecallCRVisitAssesment_SIP extends BaseTest {

	private String crVisitName1, hour = "01", minute = "45", timeMarker = "PM", clinicianTime = "01:45pm",
			subjectName = "Auto1526" + generateRandomAlphanumericString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1197RecallCRVisitAssesment_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("studyName");
		crVisitName1 = prop.getProperty("visitName1");

		/*
		 * Creating Subject For Configuring Pre-requisite And Initiate
		 * Appointment
		 */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		/* Subject Created Successfully */

		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(crVisitName1);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.scheduleAppointment(hour, minute, timeMarker);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				Constants.clinician14Name, clinicianTime);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();	
		centralRatingAppointmentPage.fillSubjectAliasField();
		centralRatingAppointmentPage.clickOnInitiateAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		loginPage.logoutApplication();
	}

	/**
	 * =========================================================================
	 * Test Case Id: FPTC_1197
	 * Test Case Name:Recall CR Visit Assessment
	 * =========================================================================
	 */

	@Test(description = "FPTC_1197_Recall CR Visit Assessment", groups = { "R1" })

	public void FPTC_1197_verifyRecallCRVisitAssessment() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1:Click on study navigator tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.2:Cancel Date From DatePicker");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("1.3:Select Visit By Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(CentralRatingModuleConstants.initiatedStatus);

		reportLog("1.4:Navigate To Appointment Page");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnVisitRow();

		reportLog("1.5:Verify Recall Button is Enabled");
		centralRatingAppointmentPage.verifyRecallButtonIsEnabled();

		reportLog("2:Click On Recall Button");
		centralRatingAppointmentPage.clickOnRecall();

		reportLog("3:Verify PopUp Message");
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(
				ApplicationVerificationMessage.recallCentralRatingAppointmentMessage);

		reportLog("4:Cancel Confirmation");
		centralRatingAppointmentPage.cancelConfirmation();

		reportLog("4.1:Verify Appointment Page Displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("4.2:Verify Recall Button is Enabled");
		centralRatingAppointmentPage.verifyRecallButtonIsEnabled();

		reportLog("5.1 Click On Recall and Verify Message");
		centralRatingAppointmentPage.verifyMessage(Constants.Recalled_Message);

		reportLog("5.2:Visit Status Change To Scheduled");
		centralRatingAppointmentPage.verifyAppointmentPage();
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.scheduledStatus);

		reportLog("6:Logout application");
		loginPage.logoutApplication();

		reportLog("6.1:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}