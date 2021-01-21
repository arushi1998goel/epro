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

public class FPTC_1517_CompletedAssessmentEditOnlyDuration_SIP extends BaseTest {

	private String visit_Pro, visit_Clinro, visit_Obsro, startedPreviousDateTime, durationPreviousTime,subjectName1;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1517_CompletedAssessmentEditOnlyDuration_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		subjectName1=properties.getProperty("SubjectnameTemp");
		subjectName = properties.getProperty("SubjectName2095");
		visit_Pro = properties.getProperty("Auto_Visit_ProForms");
		visit_Clinro = properties.getProperty("Visit_Screened_1682NonCR");
		visit_Obsro = properties.getProperty("Auto_Visit_ObsroForms");
	}

	/**
	 * 
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1517 || Test Case Name: Completed Assessment - Edit
	 * Only Duration - PDF File doesn't change
	 * ====================================================================================================================
	 * 
	 * @throws ParseException
	 */

	@Test(description = "FP-TC-1517_Completed Assessment - Edit Only Duration - PDF File doesn't change ", groups = {})
	public void FPTC_1517_CompletedAssessmentEditOnlyDuration() throws ParseException {

		reportLog("1.1: Log in to the Portal as User in Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.1: Navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("2.2: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("2.3: Search and click on assessment");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Complete_Status);
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormName, visit_Clinro, subjectName1);

		reportLog("2.4: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.5: Verify edit control is displayed and enabled");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("2.6: Verify Assessment image is displayed");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();

		reportLog("3.1: Get data available under Started and Duration field");
		startedPreviousDateTime = assessmentDetailPage.getStartedDateTime();
		durationPreviousTime = assessmentDetailPage.getDurationTime();

		reportLog("3.2: Open PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("3.3: Verify Date in the footer on the each page");
		assessmentDetailPage.verifyAssessmentDateIsPresentInFooterOnEachPageOfPDF(startedPreviousDateTime);

		reportLog("3.4: Verify Date on the PDF's cover page ");
		assessmentDetailPage.verifyAssessmentDateOnPDFsTitlePage(startedPreviousDateTime);

		reportLog("4.1: Close Assessment Source PDF");
		assessmentDetailPage.clickOnCancelButton();

		reportLog("4.2: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("4.3: Verify edit control is displayed and enabled");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("4.4: Verify Assessment image is displayed");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();

		reportLog("5.1. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("5.2: Verify Started fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("5.3: Verify Duration field become editable");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("5.4: Verify Save button is displayed and disabled");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();

		reportLog("5.5: Verify Cancel button is displayed and enabled");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("6.1:	Update Duration");
		assessmentDetailPage.updateDurationTime();

		reportLog("6.2: Select the save option");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("6.3: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("7.1: Verify the available reasons to choose from" + Constants.reasonChangeForAssesment);
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAssesment);

		reportLog("7.2: Select reason for change from the options" + Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));

		reportLog("7.3: Select the reason of Step#6 and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("7.4: Subject is updated with the new values of Step#4");
		assessmentDetailPage.verifyDurationTimeUpdated(durationPreviousTime);

		reportLog("8.1: Open PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("8.2: Verify Date in the footer on the each page");
		assessmentDetailPage.verifyAssessmentDateOnPDFsTitlePage(startedPreviousDateTime);

		reportLog("8.3: Verify Date on the PDF's cover page ");
		assessmentDetailPage.verifyAssessmentDateIsPresentInFooterOnEachPageOfPDF(startedPreviousDateTime);

		reportLog("9.1.1: Navigate to DashBoard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("9.1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
	

		reportLog("9.2.1: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("9.2.2: Search and click on assessment");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Complete_Status);
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_MMSEFormName, visit_Obsro, subjectName);

		reportLog("9.2.3: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("9.2.4: Verify edit control is displayed and enabled");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("9.2.5: Verify Assessment image is displayed");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();

		reportLog("9.3.1: Get data available under Started and Duration field");
		startedPreviousDateTime = assessmentDetailPage.getStartedDateTime();
		durationPreviousTime = assessmentDetailPage.getDurationTime();

		reportLog("9.3.2: Open PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("9.3.3: Verify Date in the footer on the each page");
		assessmentDetailPage.verifyAssessmentDateOnPDFsTitlePage(startedPreviousDateTime);

		reportLog("9.3.4: Verify Date on the PDF's cover page ");
		assessmentDetailPage.verifyAssessmentDateIsPresentInEachPageOfPDFofTypeWithSplittedAttributes(startedPreviousDateTime);

		reportLog("9.4.1: Close Assessment Source PDF");
		assessmentDetailPage.clickOnCancelButton();

		reportLog("9.4.2: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("9.4.3: Verify edit control is displayed and enabled");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("9.4.4: Verify Assessment image is displayed");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();

		reportLog("9.5.1. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("9.5.2: Verify Started fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("9.5.3: Verify Duration field become editable");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("9.5.4: Verify Save button is displayed and disabled");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();

		reportLog("9.5.5: Verify Cancel button is displayed and enabled");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("9.6.1:	Update Duration");
		assessmentDetailPage.updateDurationTime();

		reportLog("9.6.2: Select the save option");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("9.6.3: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("9.7.1: Verify the available reasons to choose from" + Constants.reasonChangeForAssesment);
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAssesment);

		reportLog("9.7.2: Select reason for change from the options" + Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));

		reportLog("9.7.3: Select the reason of Step#6 and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("9.7.4: Subject is updated with the new values of Step#4");
		assessmentDetailPage.verifyDurationTimeUpdated(durationPreviousTime);

		reportLog("9.8.1: Open PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("9.8.2: Verify Date in the footer on the each page");
		assessmentDetailPage.verifyAssessmentDateOnPDFsTitlePage(startedPreviousDateTime);

		reportLog("9.8.3: Verify Date on the PDF's cover page ");
		assessmentDetailPage.verifyAssessmentDateIsPresentInEachPageOfPDFofTypeWithSplittedAttributes(startedPreviousDateTime);

		reportLog("10.1.1: Navigate to DashBoard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("10.1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("10.2.1: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("10.2.2: Search and click on assessment");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Complete_Status);
		

		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_5DASCFormName, visit_Pro, subjectName);

		reportLog("10.2.3: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("10.2.4: Verify edit control is displayed and enabled");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("10.2.5: Verify Assessment image is displayed");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();

		reportLog("10.3.1: Get data available under Started and Duration field");
		startedPreviousDateTime = assessmentDetailPage.getStartedDateTime();
		durationPreviousTime = assessmentDetailPage.getDurationTime();

		reportLog("10.3.2: Open PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("10.3.3: Verify Corresponding fields should be updated: \n"
				+ "- The Date should be changed on the cover page \n" + "- Footer should be updated on each page \n"
				+ "- Audit History page should be updated");
		assessmentDetailPage.verifyAssessmentDateIsPresentInEachPageOfPDFofTypeWithSplittedAttributes(startedPreviousDateTime);

		reportLog("10.4.1: Close Assessment Source PDF");
		assessmentDetailPage.clickOnCancelButton();

		reportLog("10.4.2: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("10.4.3: Verify edit control is displayed and enabled");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("10.4.4: Verify Assessment image is displayed");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();

		reportLog("10.5.1. Select the option to edit Assessment Details");
		assessmentDetailPage.clickOnEditButton();

		reportLog("10.5.2: Verify Started fields become editable");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("10.5.3: Verify Duration field become editable");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("10.5.4: Verify Save button is displayed and disabled");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();

		reportLog("10.5.5: Verify Cancel button is displayed and enabled");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("10.6.1:	Update Duration");
		assessmentDetailPage.updateDurationTime();

		reportLog("10.6.2: Select the save option");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("10.6.3: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("10.7.1: Verify the available reasons to choose from" + Constants.reasonChangeForAssesment);
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAssesment);

		reportLog("10.7.2: Select reason for change from the options" + Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));

		reportLog("10.7.3: Select the reason of Step#6 and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("10.7.4: Subject is updated with the new values of Step#4");
		assessmentDetailPage.verifyDurationTimeUpdated(durationPreviousTime);

		reportLog("10.8.1: Open PDF file");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("10.8.2: Verify Corresponding fields should be updated: \n"
				+ "- The Date should be changed on the cover page \n" + "- Footer should be updated on each page \n"
				+ "- Audit History page should be updated");
		assessmentDetailPage.verifyAssessmentDateIsPresentInEachPageOfPDFofTypeWithSplittedAttributes(startedPreviousDateTime);

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	} 
}