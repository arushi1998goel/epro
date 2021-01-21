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
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1605SiteViewOfScheduledCRVisits_SIP extends BaseTest {

	protected CentralRatingAssessmentsListingPage centralRatingAssessmentsListingPage;
	protected String subjectName = "SUBJ_" + generateRandomString(4);
	private String crStudyName, crVisitName,

			hourForAppointment = "05", minuteForAppointment = "45", timeMarker = "AM";
	String timeToSelect = hourForAppointment + ":" + minuteForAppointment + timeMarker;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1605SiteViewOfScheduledCRVisits_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());

		Properties userCredentials = Configuration.readTestData("userClaimsCredentials");
		userCredentials.getProperty("AT_PRODSystemAdministrator");
		userCredentials.getProperty("AT_PRODSiteCoordinator");
		Properties prop = Configuration.readTestData("RegressionTestData");
		crStudyName = prop.getProperty("studyName");
		crVisitName = prop.getProperty("visitName1");

		reportLog("Creating subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(crStudyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("Schedule visit for newly created subject");
		loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(crStudyName, Constants.ATAssignedRater_10);
		centralRatingAssesmentListingPage.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(subjectName);
		subjectDetailPage.clickOnVisitRow(crVisitName);
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);
		centralRatingAppointmentPage.clickOnPickClinician();
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, timeToSelect);
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1605 Test Case Name: Site view of scheduled CR
	 * visits
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1605_SiteViewOfScheduledCRVisits", groups = { "R1" })

	public void FPTC_1605_verifySiteViewOfScheduledCRVisits() throws InterruptedException {

		reportLog("1: Login to Portal as the user(PR#1)");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1: Navigate to CR Assessment page");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.2 Scheduled Date From is set as current date.");
		centralRatingAssesmentListingPage.verifyScheduleDateFrom();

		reportLog("2. CR assessment page is displayed. ");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("3. Click on Refresh icon");
		centralRatingAssesmentListingPage.clickRefreshIcon();

		reportLog("4. Click on Home button");
		dashBoardPage = centralRatingAssesmentListingPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,
				Constants.HomeNavText);

		reportLog("5.  Navigate to CR Assessment page");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("5.1 Click on Find Subject");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("5.2 Find Subject dialog is opened.");
		centralRatingAssesmentListingPage.verifyFindSubjectDialog();

		reportLog("5.3 Close the Find Subject pop up");
		centralRatingAssesmentListingPage.closeFindSubjectPopup();

		reportLog("6. Clear the dates in Form and To");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("6.1 Reset to Default button is enabled.");
		centralRatingAssesmentListingPage.clickResetToDefault();

		reportLog("6.2 CR assessment list is revised based on selected date");
		centralRatingAssesmentListingPage.searchByVisitStatus(Constants.scheduled_Status);

		reportLog("6.3. Verify schedule date is same as noted date for all revised records.");
		centralRatingAssesmentListingPage.verifyScheduleDateData();

		reportLog("6.4 Reset to Default button is enabled.");
		centralRatingAssesmentListingPage.verifyResetToDefaultButton();

		reportLog("7. Click on Reset to Default");
		centralRatingAssesmentListingPage.clickResetToDefault();

		reportLog("8. Click on Subject Number hyperlink for the Visit (PR#6.1 ) and make note for subject number");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();
		centralRatingAssesmentListingPage.searchByVisitStatus(Constants.scheduled_Status);
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);

		reportLog("8.1: Get the SVID value for this subject");
		String svidValue = centralRatingAssesmentListingPage.svidValue();
		centralRatingAssesmentListingPage.clickOnSearchSubjects(subjectName);

		reportLog("8.2 Take user to Subject details page Verify subject number in this page is same as noted number.");
		centralRatingAssesmentListingPage.verifyDataOnSubjcetGeneralSection(subjectName);

		reportLog("9. Go back to the CR Assessment page");
		centralRatingAssesmentListingPage.navigateBack();
		centralRatingAssesmentListingPage.waitSpinnerToBecomeInvisible();

		reportLog("9.1. Click on SVID hyperlink for PR#6.1 and note SVID");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();
		centralRatingAssesmentListingPage.searchByVisitStatus(Constants.scheduled_Status);
		centralRatingAssesmentListingPage.searchBySVID(svidValue);
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue("SVID", svidValue);

		reportLog("9.2 Verify SVID in this page is same as noted SVID");
		centralRatingAssesmentListingPage.verifyValueOnSubjcetDetailPage(svidValue);

		reportLog("10. Go back to the CR Assessment page");
		centralRatingAssesmentListingPage.navigateBack();

		reportLog("10.1 CR assessment page is displayed.");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("10.2 Applied filer as visit status");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();
		centralRatingAssesmentListingPage.searchByVisitStatus(Constants.scheduled_Status);

		reportLog("10.3 Click on a CR Visit (Pr#6.1) record and note Subject number, SVID");
		centralRatingAssesmentListingPage.searchBySVID(svidValue);
		centralRatingAssesmentListingPage.sortByVisitName(crVisitName);
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue("Visit Name", crVisitName);

		appointmentPage = centralRatingAssesmentListingPage.clickOnVisitRow();
		appointmentPage.verifyAppointmentPage();

		reportLog("10.4 Verify the Subject Number and SVID in this page matches with noted Subject Number and SVID");
		centralRatingAssesmentListingPage.verifyValueOnSubjcetDetailPage(svidValue);
		centralRatingAssesmentListingPage.verifyPageTitle(subjectName);

		reportLog("10.5 Go back to the CR Assessment page");
		centralRatingAssesmentListingPage.navigateBack();
		centralRatingAssesmentListingPage.waitSpinnerToBecomeInvisible();

		reportLog("11. Logout");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("11.1 login as the user (Pr#2) ");	
	    loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("11.2 navigate to CR assessment page");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("11.3 Scheduled Date From is set as current date.");
		centralRatingAssesmentListingPage.verifyScheduleDateFrom();

		reportLog("11.4 CR assessment page is displayed. ");
		centralRatingAssesmentListingPage.verifyCRAssessmentsPage();

		reportLog("12.1 Click on Refresh icon");
		centralRatingAssesmentListingPage.clickRefreshIcon();

		reportLog("12.2 Click on Home button");
		dashBoardPage = centralRatingAssesmentListingPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,
				Constants.HomeNavText);

		reportLog("12.3  Navigate to CR Assessment page");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("12.4 Clear the dates in Form and To");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("13.1 Reset to Default button is enabled.");
		centralRatingAssesmentListingPage.clickResetToDefault();

		reportLog("13.2 CR assessment list is revised based on selected date");
		centralRatingAssesmentListingPage.searchByVisitStatus("Scheduled");

		reportLog("13.3 Reset to Default button is enabled.");
		centralRatingAssesmentListingPage.verifyResetToDefaultButton();

		reportLog("13.4 Click on Reset to Default");
		centralRatingAssesmentListingPage.clickResetToDefault();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}

}
