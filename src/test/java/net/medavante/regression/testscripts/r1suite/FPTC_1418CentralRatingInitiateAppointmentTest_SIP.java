package net.medavante.regression.testscripts.r1suite;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1418CentralRatingInitiateAppointmentTest_SIP extends BaseTest {
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1418CentralRatingInitiateAppointmentTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1418 Test Case Name:Initiate Appointment
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1418_Initiate Appointment", groups = { "R1" })

	public void FPTC_1418_verifyInitiateAppointment() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1:Click on CentralRating tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.2:Cancel Date From DatePicker");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("1.3:Select Visit By Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(CentralRatingModuleConstants.scheduledStatus);

		reportLog("1.4:Navigate To Appointment Page");
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.clickOnVisitRow();

		reportLog("1.5:Verify Initiate Button Is Disabled");
		centralRatingAppointmentPage.verifyInitiateButtonIsDisabledAfterRemovingSubjectAlias();

		reportLog("2:Fill Subject alias Field");
		centralRatingAppointmentPage.fillSubjectAliasField();

		reportLog("2.1:Verify Initiate Button Is Enabled");
		centralRatingAppointmentPage.verifyInitiateButtonIsEnabled();

		reportLog("3:Click On Initiate Appointment");
		centralRatingAppointmentPage.clickOnInitiateAppointment();

		reportLog("3.1:Verify PopUp Meassage displayed");
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(
				ApplicationVerificationMessage.initiateCentralRatingPopUpMessage);

		reportLog("4:Cancel Confirmation");
		centralRatingAppointmentPage.cancelConfirmation();

		reportLog("4.1:Verify Visit Status Not Changed");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.scheduledStatus);

		reportLog("5:Click On Initiate Appointment");
		centralRatingAppointmentPage.clickOnInitiateAppointment();

		reportLog("5.1: Confirm Initiation");
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("5.2:Visit Status Change To Initiates");
		centralRatingAppointmentPage.verifyVisitStatus(CentralRatingModuleConstants.initiatedStatus);

		reportLog("6:Navigate To Requested Visit");
		centralRatingAppointmentPage.navigateToListingPage();

		reportLog("6.1:Click On Cancel Schedular Date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("6.2:Select Requested Visit");
		centralRatingAssesmentListingPage.searchByVisitStatus(CentralRatingModuleConstants.requestStatus);

		reportLog("6.3:Click On Visit");
		centralRatingAssesmentListingPage.clickOnVisitRow();

		reportLog("6.4:Initiate Button Not Visible");
		centralRatingAppointmentPage.initiateButtonNotVisible();

		reportLog("6.5:Logout application");
		loginPage.logoutApplication();

		reportLog("6.6:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
