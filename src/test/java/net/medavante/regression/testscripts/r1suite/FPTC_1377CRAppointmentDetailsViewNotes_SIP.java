package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1377CRAppointmentDetailsViewNotes_SIP extends BaseTest implements CentralRatingModuleConstants {
	private String userSysAdmin;
	private String studyNameToSort;
	private String prodSiteUser;
	private String studyToSortingForSiteUser;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1377CRAppointmentDetailsViewNotes_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		Properties prop = Configuration.readTestData("RegressionTestData");
		userSysAdmin = prop.getProperty("userSystemAdministrator");
		studyNameToSort = prop.getProperty("studyToBeSelected");
		studyToSortingForSiteUser = prop.getProperty("studyForProdSiteUser");
		System.setProperty("className", getClass().getSimpleName());
		Properties userCred = Configuration.readTestData("userClaimsCredentials");
		prodSiteUser = userCred.getProperty("PRODSiteUser");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FPTC_1377 Test Case Name: CR Appointment Details View
	 * Notes
	 * =========================================================================
	 */

	@Test(description = "FPTC_1377 CR Appointment Details View Notes", groups = { "R1" })
	public void FPTC_1377_verifyCRAppointmentDetailsViewNotes() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(userSysAdmin, password);

		reportLog("2:Navigate to Central Ratings module");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("2.1:Verify Central Listing Screen is displayed");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("2.2:Verify List is sorted on the basis of study name and 'Requested' status");
		centralRatingAssesmentListingPage.verifySortedListDisplayedForSelectedVisitStatus(studyNameToSort, requestStatus);

		reportLog("2.3:Select visit which is in 'Requested' status");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(visitStatus, requestStatus);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.navigateToCentralRatingAppointmentPage();

		reportLog("2.4:Verify Appointment detail screen is displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("3:Verify Modal WIndow opened after clicking on Visit History Button");
		centralRatingAppointmentPage.verifyModalWindowOpenedWithNote();

		reportLog("4:Check that view notes indicate the date, time and person, which created particular note");
		centralRatingAppointmentPage.verifyDeatilsDisplayedOnNote();

		reportLog("5:Verify the most recent action is displayed on the top of the notes list");
		centralRatingAppointmentPage.verifyModalWindowOpenedWithRecentHistory();

		reportLog("6:Verify Add new note action is available, add new notice action is displayed");
		centralRatingAppointmentPage.verifyPresenceOfAddNoteButtonOnModalWindow();

		reportLog("7:Verify Tool tip 'Add Note' is displayed");
		centralRatingAppointmentPage.verifyTooTipTextOfAddNoteButton();

		// ** Verify Note's detail for Scheduled subject status *//*

		reportLog("8:Verify Central Listing Screen is displayed");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("8.1:Verify List is sorted on the basis of study name and 'Scheduled' status");
		centralRatingAssesmentListingPage.verifySortedListDisplayedForSelectedVisitStatus(studyNameToSort, scheduledStatus);

		reportLog("8.2:Select visit which is in 'Scheduled' status");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(visitStatus, scheduledStatus);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.navigateToCentralRatingAppointmentPage();

		reportLog("8.3:Verify Appointment detail screen is displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("9:Verify Modal WIndow opened after clicking on Visit History Button");
		centralRatingAppointmentPage.verifyModalWindowOpenedWithNote();

		reportLog("10:Check that view notes indicate the date, time and person, which created particular note");
		centralRatingAppointmentPage.verifyDeatilsDisplayedOnNote();

		reportLog("11:Verify the most recent action is displayed on the top of the notes list");
		centralRatingAppointmentPage.verifyModalWindowOpenedWithRecentHistory();

		reportLog("12:Verify Add new note action is available, add new notice action is displayed");
		centralRatingAppointmentPage.verifyPresenceOfAddNoteButtonOnModalWindow();

		reportLog("13:Verify Tool tip 'Add Note' is displayed");
		centralRatingAppointmentPage.verifyTooTipTextOfAddNoteButton();

		// ** Verify Note's detail for Pending/Cancelled subject status *//*

		reportLog("14:Verify Central Listing Screen is displayed");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("14.1:Verify List is sorted on the basis of study name and 'Pending/Cancelled' status");
		centralRatingAssesmentListingPage.verifySortedListDisplayedForSelectedVisitStatus(studyNameToSort, cancelledStatus);

		reportLog("14.2:Select visit which is in 'Pending/Cancelled' status");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(visitStatus, cancelledStatus);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.navigateToCentralRatingAppointmentPage();

		reportLog("14.3:Verify Appointment detail screen is displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("15:Verify Modal WIndow opened after clicking on Visit History Button");
		centralRatingAppointmentPage.verifyModalWindowOpenedWithNote();

		reportLog("16:Check that view notes indicate the date, time and person, which created particular note");
		centralRatingAppointmentPage.verifyDeatilsDisplayedOnNote();

		reportLog("17:Verify the most recent action is displayed on the top of the notes list");
		centralRatingAppointmentPage.verifyModalWindowOpenedWithRecentHistory();

		reportLog("18:Verify Add new note action is available, add new notice action is displayed");
		centralRatingAppointmentPage.verifyPresenceOfAddNoteButtonOnModalWindow();

		reportLog("19:Verify Tool tip 'Add Note' is displayed");
		centralRatingAppointmentPage.verifyTooTipTextOfAddNoteButton();

		// ** Verify Note's detail for Initiated subject status *//*

		reportLog("14:Verify Central Listing Screen is displayed");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("14.1:Verify List is sorted on the basis of study name and 'Initiated' status");
		centralRatingAssesmentListingPage.verifySortedListDisplayedForSelectedVisitStatus(studyNameToSort, initiatedStatus);

		reportLog("14.2:Select visit which is in 'Initiated' status");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(visitStatus, initiatedStatus);
		centralRatingAppointmentPage = centralRatingAssesmentListingPage.navigateToCentralRatingAppointmentPage();

		reportLog("14.3:Verify Appointment detail screen is displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("15:Verify Modal WIndow opened after clicking on Visit History Button");
		centralRatingAppointmentPage.verifyModalWindowOpenedWithNote();

		reportLog("16:Check that view notes indicate the date, time and person, which created particular note");
		centralRatingAppointmentPage.verifyDeatilsDisplayedOnNote();

		reportLog("17:Verify the most recent action is displayed on the top of the notes list");
		centralRatingAppointmentPage.verifyModalWindowOpenedWithRecentHistory();

		reportLog("18:Verify Add new note action is available, add new notice action is displayed");
		centralRatingAppointmentPage.verifyPresenceOfAddNoteButtonOnModalWindow();

		reportLog("19:Verify Tool tip 'Add Note' is displayed");
		centralRatingAppointmentPage.verifyTooTipTextOfAddNoteButton();

		reportLog("20.Logout from the application");
		loginPage.logoutApplication();

		reportLog("21.Verify logout page of the application");
		loginPage.verifyUserLogout();

		/**
		 * Login to portal as a user without claims to manage Central Ratings
		 * Appointment Pr#4 and Note Icon shouldn't displays
		 */

		reportLog("22.Login to portal as a user without claims to manage Central Ratings Appointment Pr#4");
		dashBoardPage = loginPage.loginInApplication(prodSiteUser, password);

		reportLog("23:Navigate to Central Ratings module");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("23.1:Verify Central Listing Screen is displayed");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		// Verify Note's detail for Initiated subject status

		reportLog("24:Verify List is sorted on the basis of study name and 'Initiated' status");
		centralRatingAssesmentListingPage.verifySortedListDisplayedForSelectedVisitStatus(studyToSortingForSiteUser,
				initiatedStatus);

		reportLog("24.1:Select visit which is in 'Initiated' status");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(visitStatus, initiatedStatus);
		subjectDetailPage = centralRatingAssesmentListingPage.navigateToNewSubjectDetailPage();

		reportLog("25:Verify Subject Details screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("26:Verify Note Button isn't displayed");
		subjectDetailPage.verifyIconForNoteNotDisplayed();

		// Verify Note's detail for Requested subject status

		reportLog("23.1:Verify Central Listing Screen is displayed");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("24:Verify List is sorted on the basis of study name and 'Requested' status");
		centralRatingAssesmentListingPage.verifySortedListDisplayedForSelectedVisitStatus(studyToSortingForSiteUser, requestStatus);

		reportLog("24.1:Select visit which is in 'Requested' status");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(visitStatus, requestStatus);
		subjectDetailPage = centralRatingAssesmentListingPage.navigateToNewSubjectDetailPage();

		reportLog("25:Verify Subject Details screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("26:Verify Note Button isn't displayed");
		subjectDetailPage.verifyIconForNoteNotDisplayed();

		reportLog("26.1: Logout from the Application");
		loginPage.logoutApplication();

		reportLog("26.2: Verify User Logout from the system");
		loginPage.verifyUserLogout();

	}
}
