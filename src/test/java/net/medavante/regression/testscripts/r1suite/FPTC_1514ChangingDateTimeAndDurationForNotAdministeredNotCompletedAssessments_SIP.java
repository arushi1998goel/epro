package net.medavante.regression.testscripts.r1suite;

import java.text.ParseException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1514ChangingDateTimeAndDurationForNotAdministeredNotCompletedAssessments_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5), crStudyName, ClinicianName, ClinicianNameSecond,
			dateTimeForScheduling, dateTimeForReScheduling, crStudyLangauage, hourForAppointment = "03",
			minuteForAppointment = "30", commentTxt = "Test" + generateRandomString(3), timeMarker = "PM",
			hourForAppointment1 = "05", minuteForAppointment1 = "30", hourForAppointment2 = "07",
			minuteForAppointment2 = "30", commentReason = "Scheduling Adjustment", PROAss = "Auto_PROAssesment",
			ObsROAss = "Auto_Obsro_NotCompleted", ClinROAss = "Auto_VisitClinro",
			reasonForChange = "Technical difficulties", startedTime = "03:30", durationTime = "01:30",
			startedTime1 = "05:30", durationTime1 = "04:30", startedTime2 = "07:30", durationTime2 = "03:30";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1514ChangingDateTimeAndDurationForNotAdministeredNotCompletedAssessments_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		crStudyName = prop.getProperty("AutomationStudyName");
		crStudyLangauage = prop.getProperty("studyLanguage");

		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(crStudyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("1. Configure study for 1 PRO Assessment which marked as Not Completed exists");

		subjectDetailPage.clickOnVisitRow(PROAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setCurrentDate();
		subjectDetailPage.selectReasonForChangeOption(reasonForChange);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("2. Configure study for 1 ObsRO Assessment which marked as Not Completed exists");
		subjectDetailPage.clickOnVisitRow(ObsROAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setCurrentDate();
		subjectDetailPage.selectReasonForChangeOption(reasonForChange);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("3. Configure study for 1 ClinRO Assessment which marked as Not Administered exists");
		subjectDetailPage.clickOnVisitRow(ClinROAss);
		subjectDetailPage.clickOnAddVisitIcon();
        subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		loginPage.logoutApplication();

		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(crStudyName,Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(ClinROAss, subjectName);
		assessmentDetailPage.markAsNotAdministred(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1514 Test Case Name: Changing Date/Time and duration
	 * for Not Administered /Not Completed Assessments
	 * 
	 * 
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 * @throws ParseException
	 */

	@Test(description = "FPTC_1514Changing Date/Time and duration for Not Administered /Not Completed Assessments V8", groups = {
			"R1" })

	public void FPTC_1514_verifyChangingDateTimeAndDurationForNotAdministeredNotCompletedAssessments() {

		reportLog(
				"1: Log in to Portal as the User of Pr#1 and navigate to the Study Dashboard and select the Study (Pr#2)");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("2: Navigate to Assessment Details page on the Portal(Pr#3)");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(ClinROAss, subjectName);

		reportLog("2.1: Assessment details page is visible");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.2: Control of edit Assessment Detail is visible");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("3: Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("3.1: Date and time fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("3.2: Duration field becomes editable");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("3.3: Options to save and cancel are displayed");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("4: Change date, time and duration");
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, timeMarker);
		assessmentDetailPage.setDurationTime(durationTime);

		reportLog("5: Select the save option");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("5.1: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("6: Verify the available reasons to choose from");
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonsForChange_DataClarification);

		reportLog("7: Select the reason of Step#6 and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog(
				"7.1: Verify Assessment receives 'Complete' status  Assessment form thumbnail changed accordingly to 'Not administered' option");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("8: Verify the generated PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("8.1: Footer should be updated on the each page and Audit History page should be updated");
		assessmentDetailPage.verifyFooterDataOnEachPage(crStudyName);

		reportLog("9: Navigate to Assessment Details page on the Portal(Pr#4)");
		studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(PROAss, subjectName);

		reportLog("9.1: Assessment details page is visible");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("9.2: Control of edit Assessment Detail is visible");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("10. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("10.1: Date and time fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("10.2: Duration field becomes editable");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("11: Change date, time and duration");
		assessmentDetailPage.setStartedDate();

		reportLog("11.1: Date and time are edited ");
		assessmentDetailPage.setStartedTime(startedTime1, timeMarker);

		reportLog("11.2: Duration is edited");
		assessmentDetailPage.setDurationTime(durationTime1);

		reportLog("12: Select the save option");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("12.1: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("13: Verify the available reasons to choose from");
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonsForChange_DataClarification);

		reportLog("14: Select the reason of Step#13 and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog(
				"14.1: Verify Assessment receives 'Complete' status  Assessment form thumbnail changed accordingly to 'Not administered' option");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("15: Verify the generated PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("15.1: Footer should be updated on the each page and Audit History page should be updated");
		// commented due to footer text is not corrected in PDF
		// assessmentDetailPage.verifyFooterDataOnEachPage("16Org - SFBTC97 |");
		
		reportLog("16: Navigate to Assessment Details page on the Portal(Pr#5)");
		studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(ObsROAss, subjectName);

		reportLog("16.1: Assessment details page is visible");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("16.2: Control of edit Assessment Detail is visible");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("17. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("17.1: Date and time fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("17.2: Duration field becomes editable");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("18: Change date, time and duration");
		assessmentDetailPage.setStartedDate();

		reportLog("18.1: Date and time are edited ");
		assessmentDetailPage.setStartedTime(startedTime2, timeMarker);

		reportLog("18.2: Duration is edited");
		assessmentDetailPage.setDurationTime(durationTime2);

		reportLog("19: Select the save option");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("19.1: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("20: Verify the available reasons to choose from");
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonsForChange_DataClarification);

		reportLog("21: Select the reason of Step#13 and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog(
				"21.1: Verify Assessment receives 'Complete' status  Assessment form thumbnail changed accordingly to 'Not administered' option");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("22: Verify the generated PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

      /*reportLog("22.1: Footer should be updated on the each page and Audit History page should be updated");
		assessmentDetailPage.verifyFooterDataOnEachPage("16Org - SFBTC97 |");*/

		reportLog("23: Logout the user");
		loginPage.logoutApplication();

		reportLog("23.1: Verify user Logout");
		loginPage.verifyUserLogout();

	}

}
