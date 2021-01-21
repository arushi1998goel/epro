package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1233LayoutAndInteractWithSubmittedClinRoPROAndObsROFormsOnPortalTest_SIP extends BaseTest {

	protected String subjectName = "Subject805"+generateRandomAlphanumericString(6); 
	String crSiteName, crStudyName, hourForAppointment = "03", minuteForAppointment = "30",timeMarker = "PM",
	hourForAppointment1 = "05", minuteForAppointment1 = "30",timeMarker1 = "PM",hourForAppointment2 = "07",
	minuteForAppointment2 = "30",timeMarker2 = "PM",commentReason = "Scheduling Adjustment", 
	commentTxt = "Test" + generateRandomString(3), ClinicianName, ClinicianNameSecond, dateTimeForScheduling, dateTimeForReScheduling;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1233LayoutAndInteractWithSubmittedClinRoPROAndObsROFormsOnPortalTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		crSiteName = prop.getProperty("siteName");
		crStudyName = prop.getProperty("AutomationStudyName");
		PROAss = prop.getProperty("PROAss");
		ObsROAss = prop.getProperty("ObsROAss");
		ClinROAss = prop.getProperty("ClinROAss");
		EventAss = prop.getProperty("EventAss");
		reportLog("Creating a subject from user and configure studies accordingly");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(crStudyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.waitSpinnerToBecomeInvisible();

		reportLog("1. Configure study for 1 PRO Assessment which marked as Not Completed exists");
		subjectDetailPage.clickOnVisitRow(PROAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);

		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN,SuperAdminPW);

		reportLog("2. Configure study for 1 ObsRO Assessment which marked as Not Completed exists");
		subjectDetailPage.clickOnVisitRow(ObsROAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN,SuperAdminPW);

		reportLog("3. Configure study for 1 Event Assessment which marked as Not Completed exists");
		subjectDetailPage.clickOnVisitRow(EventAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setStartedDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN,SuperAdminPW);

		reportLog("4. Configure study for 1 ClinRO Assessment which marked as Not Administered exists");
		subjectDetailPage.clickOnVisitRow(ClinROAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);

		loginPage.logoutApplication();

		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(crStudyName,Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(ClinROAss, subjectName);

		reportLog("Click on Assessment link");
		assessmentDetailPage.markAsNotAdministred(AT_PRODSiteCoordinator, AT_Password);
		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id:FPTC_1233 Test Case Name: Layout and interact with
	 * submitted ClinRo, PRO and ObsRO forms on Portal- V20
	 * 
	 * 
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FPTC_1233 Layout and interact with submitted ClinRo, PRO and ObsRO forms on Portal- V20", groups = {
			"R2" })

	public void FPTC_1233_verifyLayoutAndInteractWithSubmittedClinRoPROAndObsROFormsOnPortal()
			throws InterruptedException {

		reportLog(
				"1.1: Log in to Portal as the User of Pr#1 and navigate to the Study Dashboard and select the Study (Pr#2)");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);

		reportLog("1.2: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to Study Navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("1.4: Open Subject PR#3");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("1.5:Verify Subject DetailPage Opened");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2: Click on 'ClinRO' form thumbnail");
		subjectDetailPage.clickOnVisitRow(ClinROAss);

		reportLog("2.1: Visit is selected with labels");
		subjectDetailPage.verifyLabelForSelectedVisit(Constants.ClinRO_Form_Label);

		reportLog(
				"2.2: 'ClinRO' assessment displays text: 'Submitted by: full name of the person', Date (when the form was submitted) Date format: DD-MMM-YYYY.");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_10);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());

		reportLog("2.3: Assessment Details page is opened for 'ClinRO' assessment");
		assessmentDetailPage = subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.4: Go back to subject detail page and applied the filter for Visit");
		assessmentDetailPage.navigateBack();
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3: Click on 'ObsRO' form thumbnail");
		subjectDetailPage.clickOnVisitRow(ObsROAss);

		reportLog("3.1: Visit is selected with labels");
		subjectDetailPage.verifyLabelForSelectedVisit("ObsRO");

		reportLog(
				"3.2: 'ClinRO' assessment displays text: 'Submitted by: full name of the person', Date (when the form was submitted) Date format: DD-MMM-YYYY.");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_13);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());

		reportLog("3.3: Assessment Details page is opened for 'ObsRO' assessment");
		subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.4: Go back to subject detail page and applied the filter for Visit");
		assessmentDetailPage.navigateBack();
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("4: Click on 'PRO' form thumbnail");
		subjectDetailPage.clickOnVisitRow(PROAss);

		reportLog("4.1: Visit is selected with labels");
		subjectDetailPage.verifyLabelForSelectedVisit("PRO");

		reportLog(
				"4.2: 'PROAss' assessment displays text: 'Submitted by: full name of the person', Date (when the form was submitted) Date format: DD-MMM-YYYY.");
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_13);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());

		reportLog("4.3: Assessment Details page is opened for 'PRO' assessment");
		subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("4.4: Go back to subject detail page and applied the filter for Visit");
		assessmentDetailPage.navigateBack();
		assessmentDetailPage.waitSpinnerToBecomeInvisible();
		subjectDetailPage.verifyNewSubjectDetailPage();
	
		reportLog("4:5:Logout the user");
		loginPage.logoutApplication();

		reportLog("4.6:Verify user Logout");
		loginPage.verifyUserLogout();

	}

}
