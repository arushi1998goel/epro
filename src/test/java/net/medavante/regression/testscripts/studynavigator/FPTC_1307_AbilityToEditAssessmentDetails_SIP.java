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

public class FPTC_1307_AbilityToEditAssessmentDetails_SIP extends BaseTest{
	
	private String visit1,visit2,visit3,startedPreviousDateTime, durationPreviousTime, startedUpdatedDateTime, startedUpdatedDate,
	startedTime = "10:30";
	private String subjectName = "Auto2145"+generateRandomString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
    public FPTC_1307_AbilityToEditAssessmentDetails_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		visit1 = properties.getProperty("Auto_Visit_ClinROForms");
		visit2=properties.getProperty("Auto_Visit_ObsroForms");
		visit3=properties.getProperty("Auto_Visit_ProForms");
		
		
		reportLog("Creating a subject from user and configure visits for them");
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Completing one ClinRo Assessment For Configuring Pre-Requisite ");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visit1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleFormOfSameName(Constants.ClinRO_Form_Label, Constants.ATAssignedRater_10);
		assessmentDetailPage=subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnNotAdminsteredPopUpYesButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		assessmentDetailPage.verifyReasonForChangeAuthenticationText(Constants.ReasonForChange_AuthenticationMessage);

		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
        assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.verifyAssesmentCheckBoxChangedAccordinglyNotAdministered();
		subjectDetailPage=assessmentDetailPage.clickOnSubjectLink();
		
		reportLog("Completing one OBSRO Assessment For Configuring Pre-Requisite 2");
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visit2, SuperAdminUN, SuperAdminPW);
		
		reportLog("Completing one PRO Assessment For Configuring Pre-Requisite 3");
        subjectDetailPage.completingVisitByMarkAsNotCompleting(visit3, SuperAdminUN, SuperAdminPW);
			
	}
	
	
	@Test(description = "FPTC_1307_ability To Edit Assessment Details", groups = {})
	public void FPTC_1307_abilityToEditAssessmentDetails() throws ParseException {
  
		reportLog("1.1: Navigate to Assessment details page form from prerequosite#1");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage
				.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormName, visit1, subjectName);
		
		reportLog("1.2: Edit control is available");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();
		
		reportLog("1.3: Get data available under Started and Duration field");
		startedPreviousDateTime = assessmentDetailPage.getStartedDateTime();
		durationPreviousTime = assessmentDetailPage.getDurationTime();
		
		reportLog("2.1: Click on edit control");
		assessmentDetailPage.clickOnEditButton();
		
		reportLog("2.2: 'Started' field is moved to edit mode");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();
		
		reportLog("2.3: 'Save' and 'Cancel' icons are appeared");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();
		
		reportLog("3.1: Make some changes in 'Started' field and click 'Cancel'");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");	
		assessmentDetailPage.clickOnCancelAssessmentChangesIcon();
		
		reportLog("3.2: Changes are not applied");
		assessmentDetailPage.verifyStartedDateTimeNotChanged(startedPreviousDateTime);
		
		reportLog("3.3: Field is switched to view mode");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedInViewMode();
		
		reportLog("4.1: Click Edit control");
		assessmentDetailPage.clickOnEditButton();
		
		reportLog("4.2: make some changes in 'Started' and Click 'Save'");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");
		assessmentDetailPage.clickOnSaveBTN();
		
		reportLog("4.3: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("4.4: Verify the available reasons to choose from" + Constants.reasonChangeForAssesment);
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAssesment);

		reportLog("4.5: Select reason for change from the options" + Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));

		reportLog("4.6: Select the reason  and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		
		reportLog("4.7: Changes are applied");
		startedUpdatedDateTime = assessmentDetailPage.getStartedDateTime();
		startedUpdatedDate = assessmentDetailPage.getStartedDate();
		assessmentDetailPage.verifyStartedDateTimeUpdated(startedPreviousDateTime);
		
		reportLog("4.8: Field is switched to view mode");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedInViewMode();
		
		reportLog("5.1: Navigate to Assessment details page form from prerequosite#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		

		assessmentDetailPage = studyNavigatorDashBoardPage
				.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_MMSEFormName, visit2, subjectName);
		
		reportLog("5.2: Edit control is available");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();
		
		reportLog("6.1: Click Edit control");
		assessmentDetailPage.clickOnEditButton();
		
		reportLog("6.2: 'Started' field is moved to edit mode");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();

		reportLog("6.3: 'Save' and 'Cancel' icons are appeared");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();
		
		reportLog("7.1: Edit 'Started' field and cancel changes");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");	
		assessmentDetailPage.clickOnCancelAssessmentChangesIcon();
		
		reportLog("7.2: Changes are not applied");
		assessmentDetailPage.verifyStartedDateTimeNotChanged(startedPreviousDateTime);

		reportLog("8.1: Edit 'Started' field and save changes");
		assessmentDetailPage.clickOnEditButton();
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");
		assessmentDetailPage.clickOnSaveBTN();
		
		reportLog("8.2: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("8.3: Verify the available reasons to choose from" + Constants.reasonChangeForAssesment);
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAssesment);

		reportLog("8.4: Select reason for change from the options" + Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));

		reportLog("8.5: Select the reason  and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		
		reportLog("8.6: Get updated data available under Started and Duration field");
		startedUpdatedDateTime = assessmentDetailPage.getStartedDateTime();
		startedUpdatedDate = assessmentDetailPage.getStartedDate();
		
		reportLog("8.7: Changes are applied");
		startedUpdatedDateTime = assessmentDetailPage.getStartedDateTime();
		startedUpdatedDate = assessmentDetailPage.getStartedDate();
		assessmentDetailPage.verifyStartedDateTimeUpdated(startedPreviousDateTime);
		
		
		reportLog("9.1: Navigate to Assessment details form from prerequosite#3");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Complete_Status);
		assessmentDetailPage = studyNavigatorDashBoardPage
				.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_5DASCFormName, visit3, subjectName);
		
		reportLog("9.2: Edit control is available");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();
		
		
		reportLog("10.1: Click Edit control");
		assessmentDetailPage.clickOnEditButton();
		
		
		reportLog("10.2: 'Started' field is moved to edit mode");
		assessmentDetailPage.verifyDateAndTimeFieldDisplayedEditable();
		
		reportLog("10.3: Duration field is moved to edit mode");
		assessmentDetailPage.verifyDurationFieldsDisplayedEnabled();

		reportLog("10.4: 'Save' and 'Cancel' icons are appeared");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();
		

		reportLog("11.1: Edit 'Started' field and save changes");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");
		assessmentDetailPage.clickOnSaveBTN();
		
		reportLog("11.2: The 'reason for change' window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("11.3: Verify the available reasons to choose from" + Constants.reasonChangeForAssesment);
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAssesment);

		reportLog("11.4: Select reason for change from the options" + Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));

		reportLog("11.5: Select the reason  and proceed to save by E-signing");
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		
		reportLog("11.6: Changes are applied ");
		startedUpdatedDateTime = assessmentDetailPage.getStartedDateTime();
		startedUpdatedDate = assessmentDetailPage.getStartedDate();
		assessmentDetailPage.verifyStartedDateTimeUpdated(startedPreviousDateTime);
		
		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

		
		


		
		
		
		
	}
	
	

}
