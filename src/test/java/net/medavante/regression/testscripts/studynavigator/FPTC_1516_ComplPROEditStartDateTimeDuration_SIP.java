package net.medavante.regression.testscripts.studynavigator;

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

public class FPTC_1516_ComplPROEditStartDateTimeDuration_SIP extends BaseTest {

	private String visit_Pro, startedPreviousDateTime, durationPreviousTime, startedUpdatedDateTime, startedUpdatedDate,
			startedTime = "10:30";
	private String subjectName = "Auto_"+generateRandomString(5);


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1516_ComplPROEditStartDateTimeDuration_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		//subjectName = properties.getProperty("SubjectName2095");
		visit_Pro = properties.getProperty("Auto_Visit_ProForms");
		
		//Create a new subject and compete the PROassessemnt
		reportLog("Creating a subject from user and configure visits for them");
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Completing one PRo Assessment For Configuring Pre-Requisite");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit_Pro);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setCurrentDate();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();		
		
		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_5DASCFormName,
				visit_Pro, subjectName);
	//	assessmentDetailPage.markAsNotAdministred(AT_PRODSiteCoordinator, AT_Password);
		assessmentDetailPage.navigateBackToDashBoard();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1516 || Test Case Name: Completed 'PRO' Assessment - Edit Start Date, Time and Duration
	 * ====================================================================================================================
	 * 
	 * @throws ParseException
	 */

	@Test(description = "FP-TC-1516_Completed 'PRO' Assessment - Edit Start Date, Time and Duration ", groups = {})
	public void FPTC_1516_FPTComplPROEditStartDateTimeDuration_SIP() throws ParseException {

		reportLog("1.1: Log in to the Portal as User in Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select study");
		studyNavigatorDashBoardPage.refreshPage();
		studyNavigatorDashBoardPage.reSelectstudy(studyName);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.1: Navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("2.2: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("2.3: Search and click on assessment");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Complete_Status);
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_5DASCFormName, visit_Pro, subjectName);

		reportLog("2.4: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.5: Verify edit control is displayed and enabled");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("2.6: Get data available under Started and Duration field");
		startedPreviousDateTime = assessmentDetailPage.getStartedDateTime();
		durationPreviousTime = assessmentDetailPage.getDurationTime();

		reportLog("3.1. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("3.2: Verify Started fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("3.3: Verify Duration field become editable");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("3.4: Verify Save button is displayed and disabled");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();

		reportLog("3.5: Verify Cancel button is displayed and enabled");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("4.1:	Update Date and time");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");

		reportLog("4.2:	Update Duration");
		assessmentDetailPage.updateDurationTime();

		reportLog("5.1: click details cancel button to discard the changes");
		assessmentDetailPage.clickOnCancelAssessmentChangesIcon();

		reportLog("5.2: Verify Changes are not applied");
		assessmentDetailPage.verifyStartedDateTimeNotChanged(startedPreviousDateTime);
		assessmentDetailPage.verifyDurationTimeNotChanged(durationPreviousTime);

		reportLog("6.1. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("6.2: Verify Started fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("6.3: Verify Duration field become editable");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("6.4: Verify Save button is displayed and disabled");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();

		reportLog("6.5: Verify Cancel button is displayed and enabled");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("7.1:	Update Date and time");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");

		reportLog("7.2:	Update Duration");
		assessmentDetailPage.updateDurationTime();

		reportLog("8.1: Select the save option");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("8.2: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("9.1: Verify the available reasons to choose from" + Constants.reasonChangeForAssesment);
//		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAssesment);

		reportLog("10.1: Select reason for change from the options" + Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));

		reportLog("10.2: Select the reason of Step#6 and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("10.3: Get updated data available under Started and Duration field");
		startedUpdatedDateTime = assessmentDetailPage.getStartedDateTime();
		startedUpdatedDate = assessmentDetailPage.getStartedDate();

		reportLog("10.4: Subject is updated with the new values of Step#4");
		assessmentDetailPage.verifyDurationTimeUpdated(durationPreviousTime);

		reportLog("10.5: Verify PDF preview image is available on the page");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();

		reportLog("11.1: Open PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("11.2: Verify Corresponding fields should be updated: \n"
				+ "- The Date should be changed on the cover page \n" + "- Footer should be updated on each page \n"
				+ "- Audit History page should be updated");
		//pdf issue for splitted attribute
		//assessmentDetailPage.verifyAssessmentDateIsPresentInEachPageOfPDFofTypeWithSplittedAttributes(startedUpdatedDate);

		reportLog("11.3: Close Assessment Source PDF");
		assessmentDetailPage.clickOnCancelButton();

		reportLog("12.1: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("12.2: Verify updated Started date avilable on Assessment Details page");
		assessmentDetailPage.verifyStartedDateTimeNotChanged(startedUpdatedDateTime);

		reportLog("12.3: Navigate to DashBoard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("12.4: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("12.5: Navigate to Visits Listing");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("12.6: Verify Visit List Is Displayed");
		studyNavigatorDashBoardPage.verifyVisitListIsOpened();

		reportLog("12.7: Search and click on visit");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Complete_Status);
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit_Pro, subjectName);

		reportLog("12.8: Verify Visit Detail page Is Displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("12.9: Verify updated Start Date on Visit Detail page");
		visitDetaiLPage.verifyVisitDetails(Constants.SubjectDeatil_VisitLabel, startedUpdatedDate);

		reportLog("12.10: Navigate to DashBoard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("12.11: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("12.12: Navigate to Subjects Listing");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("12.13: Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("12.14: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("12.15: Verify Visit start date");
		subjectDetailPage.verifyVisitStartDate(visit_Pro, startedUpdatedDate);

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}