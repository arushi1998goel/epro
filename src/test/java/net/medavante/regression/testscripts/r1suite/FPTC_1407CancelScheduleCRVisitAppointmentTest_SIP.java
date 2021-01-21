package net.medavante.regression.testscripts.r1suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.Constants;

public class FPTC_1407CancelScheduleCRVisitAppointmentTest_SIP extends BaseTest {


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1407CancelScheduleCRVisitAppointmentTest_SIP(String browser) {
		super(browser);

	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: SFB-TC-1407
	 * Test Case Name:Cancel Scheduled CR Visit
	 * Appointment
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1407CancelScheduleCRVisitAppointmentTest_SIP_Cancel Scheduled CR Visit Appointment", groups = { "R1" })
	public void FPTC_1407CancelScheduleCRVisitAppointmentTest_SIP_verifyCancelScheduledCRVisitAppointment() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();


		reportLog("1.1:Click on study navigator tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("2:Verify List Of Visits Displayed");
		centralRatingAssesmentListingPage.loadCompletePage();

		reportLog("2.1:Cancel Date For Filling Up the Current Date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("2.2:View Scheduled Visit For Today");
		centralRatingAssesmentListingPage.selectScheduledDateFromToForToday();

		reportLog("2.3:Select Scheduled Visit Status From DropDown");
		centralRatingAssesmentListingPage.searchByVisitStatus(Constants.scheduled_Status);

		reportLog("2.4: Click On Visit  Schedule  For Today");
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnVisitRow();

		reportLog("2.5:Verify Appointment Page Displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("2.6:Verify Cancel Button Present And Enabled");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("3:Click On Cancel Appointment");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("4:Verify Cancel PopUp Message");
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(
				ApplicationVerificationMessage.cancelCentralRatingAppointmentMessage);

		reportLog("4.1:Cancel Confirmation");
		centralRatingAppointmentPage.cancelConfimartionForCancelAppointment();

		reportLog("4.2:Verify Appointment Page Displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("4.3:Verify Cancel Button Present And Enabled");
		centralRatingAppointmentPage.verifyCancelButtonIsPresentAndEnabled();

		reportLog("5:Click On Cancel Appointment");
		centralRatingAppointmentPage.clickOnCancelAppointment();

		reportLog("5.1:Click on Reason DropDown");
		centralRatingAppointmentPage.clickOnReasonDropDown();

		reportLog("5.2:Select Reason For Cancel Appointment");
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(Constants.Cancel_Appointment_Scheduling_Adjusment_Reason,
				"Test" + generateRandomString(3));

		reportLog("5.3:Confirm Confirmation");
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();

		reportLog("5.4:Visit Status Change To Pending");
		centralRatingAppointmentPage.verifyVisitStatus(Constants.Pending_Status);

		reportLog("5.5:Verify User Remains On the Same Screen");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("6:Logout application");
		loginPage.logoutApplication();

		reportLog("6.1:Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
