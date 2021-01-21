package net.medavante.regression.testscripts.studynavigator;

import java.text.ParseException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1478_EditNotCompletedAssessment_SIP extends BaseTest {

	private String visit, startedPreviousDateTime, durationPreviousTime, startedTime = "10:30";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1478_EditNotCompletedAssessment_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		subjectName = properties.getProperty("SubjectName918");
		visit = properties.getProperty("Auto_Visit_ProForms");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1478 || Test Case Name: Edit "Not Completed"
	 * Assessment
	 * ====================================================================================================================
	 * 
	 * @throws ParseException
	 */

	@Test(description = "FP-TC-1478_Edit 'Not Completed' Assessment ", groups = {})
	public void FPTC_1478_EditNotCompletedAssessment() throws ParseException {

		reportLog("1.1: Log in to the Portal as User in Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);


		reportLog("1.4: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.5: Navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("1.6: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("1.7: Search and click on assessment");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Complete_Status);
		assessmentDetailPage = studyNavigatorDashBoardPage
				.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_5DASCFormName, visit, subjectName);

		reportLog("1.8: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("1.9: Verify edit control is displayed and enabled");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("1.10: Get data available under Started and Duration field");
		startedPreviousDateTime = assessmentDetailPage.getStartedDateTime();
		durationPreviousTime = assessmentDetailPage.getDurationTime();

		reportLog("2.1. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("2.2: Verify Started fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("2.3: Verify Save button is displayed and disabled");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();

		reportLog("2.4: Verify Cancel button is displayed and enabled");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("2.4: Verify 'Datepicker' and 'timepicker' controls are displayed for 'Started' field.");
		assessmentDetailPage.verifyFieldsAreEditableForCompletedAssesment();

		reportLog("3.1:	Update 'Started' and 'Duration' info.");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");
		assessmentDetailPage.updateDurationTime();

		reportLog("3.2: click details cancel button to discard the changes");
		assessmentDetailPage.clickOnCancelAssessmentChangesIcon();

		reportLog("3.3: Verify Changes are not applied");
		assessmentDetailPage.verifyStartedDateTimeNotChanged(startedPreviousDateTime);
		assessmentDetailPage.verifyDurationTimeNotChanged(durationPreviousTime);

		reportLog("3.4: Verify field is switched to view mode");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedInViewMode();

		reportLog("4.1. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("4.2: Verify Started fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("4.3: Verify 'Datepicker' and 'timepicker' controls are displayed for 'Started' field.");
		assessmentDetailPage.verifyFieldsAreEditableForCompletedAssesment();

		reportLog("4.4:	Update 'Started' and 'Duration' info.");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");
		assessmentDetailPage.updateDurationTime();

		reportLog("4.5: Select the save option");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("4.6: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("4.7: Select reason for change from the options"+Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));

		reportLog("4.8: Select the reason of Step#6 and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("4.9:	Subject is updated with the new values of Step#4");
		assessmentDetailPage.verifyDurationTimeUpdated(durationPreviousTime);

		reportLog("4.10: Verify field is switched to view mode");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedInViewMode();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
