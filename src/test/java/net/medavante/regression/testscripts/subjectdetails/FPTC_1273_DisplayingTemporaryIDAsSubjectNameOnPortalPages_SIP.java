package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1273_DisplayingTemporaryIDAsSubjectNameOnPortalPages_SIP extends BaseTest {

	private String studyNameNonCR, SubjectNumber1, SubjectNumber2, CRVisit, visit_ObsroForms,
			hourForAppointment = "08", minuteForAppointment = "00", timeMarker = "AM",
			time = hourForAppointment + ":" + minuteForAppointment;
	String timeToSelect = hourForAppointment + ":" + minuteForAppointment + timeMarker;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1273_DisplayingTemporaryIDAsSubjectNameOnPortalPages_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyNameNonCR = properties.getProperty("AutomationStudyNameNonCR");
		CRVisit = properties.getProperty("Auto_Visit_CR");
		visit_ObsroForms = properties.getProperty("Auto_Visit_ObsroForms");

		/** Creating a subject from user and configure Visits accordingly. */

		reportLog("PR3.1: Login to Site Portal");
		 dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("PR3.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("PR3.3: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("PR3.4: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ATAssignedRater_10);

		reportLog("PR3.3: Add new Subject by control 'Add New Subject'");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("PR3.4: Verify Add Subject Popup appear");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("PR3.5: Generate Auto Temporary ID");
		SubjectNumber1 = studyNavigatorDashBoardPage.generateAutoTemporaryID();

		reportLog("PR3.6: Select a language​");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("PR3.7: Save the changes");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("PR3.8: Verify Subject details page is opened");
		subjectDetailPage.verifyTempIdIsDisplayed(SubjectNumber1);

		reportLog("PR3.9: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("PR3.10: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_ObsroForms);

		reportLog("PR3.11: Click on add visit icon");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("PR4.1: Navigate back to study dashboard");
		subjectDetailPage.navigateBack();

		reportLog("PR4.2: Add new Subject by control 'Add New Subject'");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);

		reportLog("PR4.3: Verify Add Subject Popup appear");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("PR4.4: Generate Auto Temporary ID");
		SubjectNumber2 = studyNavigatorDashBoardPage.generateAutoTemporaryID();

		reportLog("PR4.5: Select a language​");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("PR4.6: Save the changes");
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("PR4.7: Verify Subject details page is opened");
		subjectDetailPage.verifyTempIdIsDisplayed(SubjectNumber2);

		reportLog("PR4.8: Verify CR visit exists in visit list");
		subjectDetailPage.verifyVisitAddedIsPresentInList(CRVisit);

		reportLog("PR4.9: Logout application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1273 Test Case Name: Displaying the Temporary ID as
	 * a Subject name on the Portal pages
	 * =========================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1273_Displaying the Temporary ID as a Subject name on the Portal pages ", groups = {
			"" })
	public void FPTC_1273_DisplayingTemporaryIDAsSubjectNameOnPortalPages() throws InterruptedException {

		reportLog("1.1: Login to Site Portal");
		 loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);

		reportLog("1.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);


		reportLog("1.4: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ChooseSite_ATTester10);

		reportLog("1.5:Verify existance of subject name#1");
		studyNavigatorDashBoardPage.verifyExistingOfSubjectNo(SubjectNumber1);

		reportLog("2.1:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				SubjectNumber1);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(SubjectNumber1);

		reportLog("2.2:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.3:Verify TemporaryID is displayed as a Subject name for a Subject");
		subjectDetailPage.verifysubjectNoInTitle(SubjectNumber1);

		reportLog("3.1: Navigate to Visits List view");
		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("4.1: Open Visit Details page for Subject");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit_ObsroForms, SubjectNumber1);

		reportLog("4.2: Verify Visit Details Page is opened");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("4.3: Verify TemporaryID is displayed as a Subject name for a Subject");
		visitDetaiLPage.verifyVisitDetailsUnderSubjectSection(Constants.StudyDashBoard_columnName_Subject,
				SubjectNumber1);

		reportLog("5.1: Navigate to Assessments List view");
		visitDetaiLPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("6.1: Open Assessment Details page for a Subject");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visit_ObsroForms, SubjectNumber1);

		reportLog("6.2: Verify Assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("6.3: Verify TemporaryID is displayed as a Subject name for a Subject");
		assessmentDetailPage.verifyDisplayedSubject(SubjectNumber1);

		reportLog("7.1: Navigate to Subject List view");
		assessmentDetailPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("7.2: Search and click on Subject #2");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				SubjectNumber2);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(SubjectNumber2);

		reportLog("7.3: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("7.4: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("7.5: Select visit row ");
		subjectDetailPage.clickOnVisitRow(CRVisit);

		reportLog("7.6: Click on add visit icon");
		centralRatingAppointmentPage = subjectDetailPage.clickOnAddVisitIcon();

		reportLog("7.7:Verify Appointment Page is displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("7.8:Set Appointment  Date ");
		centralRatingAppointmentPage.setAppointmentDate();

		reportLog("7.9:Set Appointment Time ");
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("7.10:Verify Appointment Date And Time Has been Set");
		centralRatingAppointmentPage.verifyAppointmentDateTimeSet(time);

		reportLog("7.11: Select To View Qualified Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("7.12: Verify clinician With Calender Is Displayed");
		centralRatingAppointmentPage.verifyClinicianWithCalenderIsDisplayed();

		reportLog("7.13: Select Clinician Without Schedule Appointment");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, timeToSelect);

		reportLog("7.14: Click On Schedule Appointment Button");
		centralRatingAppointmentPage.clickOnScheduleAppointment();

		reportLog("7.15: Verify Confirmation Pop Up Message");
		String middleMessageText = centralRatingAppointmentPage.getMiddleMessage();
		centralRatingAppointmentPage.verifyPopUPConfirmationMessageForAppointment(
				ApplicationVerificationMessage.scheduleCentralRatingAssesmentMessage);

		reportLog("7.16: Confirm Confirmation Of PopUp Message");
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("7.17: Verify Visit Status");
		centralRatingAppointmentPage.verifyVisitStatus(Constants.scheduled_Status);

		reportLog("7.18: Logout application");
		loginPage.logoutApplication();

		reportLog("7.19: Verify user is logout");
		loginPage.verifyUserLogout();

		reportLog("8.1: Login to Site Portal");
		loginPage.loginInApplication(AT_PRODITSupport, AT_Password);

		reportLog("8.2: Navigate to study dashboard");
		centralRatingAssesmentListingPage = dashBoardPage.navigateToCentralRatings();

		reportLog("8.3: Verify TemporaryID is displayed as a Subject name in the Subject Number column");
		centralRatingAssesmentListingPage.verifyExistanceOfSubjectUnderSubjectNumber(SubjectNumber2);

		reportLog("9.1: click on Find Subject control on the CR visits page");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("9.2: Select Study and Site under find Subject control");
		centralRatingAssesmentListingPage.selectStudyAndSiteFromDropDown(studyNameNonCR, Constants.RaterName_21);

		reportLog("9.2: Verify and click on TemporaryID is displayed as a Subject name");
		centralRatingAssesmentListingPage.selectSubjectNameAfterSelectingStudyAndSiteFromDropDown(SubjectNumber2);

		reportLog("9.3:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("9.4: Logout application");
		loginPage.logoutApplication();

		reportLog("9.5: Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
