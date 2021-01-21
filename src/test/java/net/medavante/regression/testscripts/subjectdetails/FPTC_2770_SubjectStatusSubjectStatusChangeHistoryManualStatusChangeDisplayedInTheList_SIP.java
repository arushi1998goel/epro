package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2770_SubjectStatusSubjectStatusChangeHistoryManualStatusChangeDisplayedInTheList_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5), discontinuedCustomStatus = "Auto2059Custom";
	protected String studyName, completedNonCRVisit;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2770_SubjectStatusSubjectStatusChangeHistoryManualStatusChangeDisplayedInTheList_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("AutomationStudyName");
		completedNonCRVisit = prop.getProperty("visitForRenameStatus");
		
		reportLog("create a new subject with completed visit ");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Select visit and assign rater");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		
		reportLog("Complete the Assessment");
		assessmentDetailPage=subjectDetailPage.clickOnBeforeNotAdministeredThumbnailImage();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();		

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-2770 Test Case Name: Verify that the manual status change displayed in history list with corresponding information.
	 * 
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-2770--Verify that the manual status change displayed in history list with corresponding information.", groups = { })
	public void FPTC_2770_SubjectStatusSubjectStatusChangeHistoryManualStatusChangeDisplayedInTheList() throws Exception {

		reportLog("1. Log in to the Portal as User in Pr#2 and navigate to Subject details page (Pr#4)");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		reportLog("Navigate to Subject Details screen with PR#4");
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSubject(subjectName);
		
		reportLog("1.1: Subject details page is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("1.2: Option to edit Subject details page is visible");
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();
		
		reportLog("2: Select the option to edit details page");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("2.1: Edit Subject modal window is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("3: Change Subject status manually to Screened. Save and E-sign the change.");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Screened);
		subjectDetailPage.clickOnSaveBtn();
		
		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);		
		
		reportLog("3.1: Status is successfully changed.");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,Constants.SubjectStatus_Screened);		
				
		reportLog("4: Open change status history modal window");
		subjectDetailPage.clickOnShowHistory();
		
		reportLog("4.1: Status Subject History modal window is opened ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();
		
		reportLog("4.2: The record is available on the top, that the status is changed to Screened and it was changed by the person who e-sign the change");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		
		reportLog("4.3: Change reason is displayed as the reason for change status ");
		subjectDetailPage.verifySubjectStatusHistoryContainsReasonForChangeStatus("Status changed");
		
		reportLog("4.4: Date and Time are displayed");
			subjectDetailPage.verifySubjectStatusHistoryContainsDateAndTime(currentDate().toUpperCase());
		
		reportLog("5: Close change status history modal window by close option");
		subjectDetailPage.clickOnCloseHistory();
		
		reportLog("5.1: The Subject details page is displayed after the modal window was closed");
		subjectDetailPage.verifyNewSubjectDetailPage();
				
		reportLog("6: Select the option to edit details page");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("6.1: Edit Subject modal window is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("7: Change Subject status manually to Enrolled. Save and E-sign the change.");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Enrolled);
		subjectDetailPage.clickOnSaveBtn();
		
		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);		
		
		reportLog("7.1: Status is successfully changed.");
		subjectDetailPage.verifyDetailStatus("Status",Constants.SubjectStatus_Enrolled);		
				
		reportLog("8: Open change status history modal window");
		subjectDetailPage.clickOnShowHistory();
		
		reportLog("8.1: Status Subject History modal window is opened ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();
		
		reportLog("8.2: The record is available on the top, that the status is changed to Screened and it was changed by the person who e-sign the change");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		
		reportLog("8.3: Change reason is displayed as the reason for change status ");
		subjectDetailPage.verifySubjectStatusHistoryContainsReasonForChangeStatus("Status changed");
		
		reportLog("8.4: Date and Time are displayed");
		subjectDetailPage.verifySubjectStatusHistoryContainsDateAndTime(currentDate().toUpperCase());
		
		reportLog("9: Close change status history modal window by close option");
		subjectDetailPage.clickOnCloseHistory();
		
		reportLog("9.1: The Subject details page is displayed after the modal window was closed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("10: Select the option to edit details page");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("10.1: Edit Subject modal window is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("11: Change Subject status manually to Completed. Save and E-sign the change.");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Completed);
		subjectDetailPage.clickOnSaveBtn();
		
		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);		
		
		reportLog("11.1: Status is successfully changed.");
		subjectDetailPage.verifyDetailStatus("Status",Constants.SubjectStatus_Completed);		
				
		reportLog("12: Open change status history modal window");
		subjectDetailPage.clickOnShowHistory();
		
		reportLog("12.1: Status Subject History modal window is opened ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();
		
		reportLog("12.2: The record is available on the top, that the status is changed to Screened and it was changed by the person who e-sign the change");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		
		reportLog("12.3: Change reason is displayed as the reason for change status ");
		subjectDetailPage.verifySubjectStatusHistoryContainsReasonForChangeStatus("Status changed");
		
		reportLog("12.4: Date and Time are displayed");
		subjectDetailPage.verifySubjectStatusHistoryContainsDateAndTime(currentDate().toUpperCase());
		
		reportLog("13: Close change status history modal window by close option");
		subjectDetailPage.clickOnCloseHistory();
		
		reportLog("13.1: The Subject details page is displayed after the modal window was closed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("14: Select the option to edit details page");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("14.1: Edit Subject modal window is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("15: Change Subject status manually to Discontinued. Save and E-sign the change.");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Discontinued);
		subjectDetailPage.clickOnSaveBtn();
		
		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);		
		
		reportLog("15.1: Status is successfully changed.");
		subjectDetailPage.verifyDetailStatus("Status",Constants.SubjectStatus_Discontinued);		
				
		reportLog("16: Open change status history modal window");
		subjectDetailPage.clickOnShowHistory();
		
		reportLog("17.1: Status Subject History modal window is opened ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();
		
		reportLog("17.2: The record is available on the top, that the status is changed to Screened and it was changed by the person who e-sign the change");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		
		reportLog("17.3: Change reason is displayed as the reason for change status ");
		subjectDetailPage.verifySubjectStatusHistoryContainsReasonForChangeStatus("Status changed");
		
		reportLog("17.4: Date and Time are displayed");
		subjectDetailPage.verifySubjectStatusHistoryContainsDateAndTime(currentDate().toUpperCase());
		
		reportLog("18: Close change status history modal window by close option");
		subjectDetailPage.clickOnCloseHistory();
		
		reportLog("18.1: The Subject details page is displayed after the modal window was closed");
		subjectDetailPage.verifyNewSubjectDetailPage();		
		subjectDetailPage.navigateBackToDashBoard();		
		
		reportLog("19: Navigate to Administration -> Studies -> Custom - change default Subject status to custom");
		adminstrationOrganizationPage = subjectDetailPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,
				Constants.ConfigureNavText, Constants.StudySetupText);	
		
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.editSubjectAlias(Constants.SubjectStatus_Discontinued, discontinuedCustomStatus);
	    		
		reportLog("19.1: Navigate back to Subject Detail page");
		adminstrationStudiesCustomPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		reportLog("19.1.1: Navigate to Subject Details screen with PR#4");
		studyNavigatorDashBoardPage.clickOnSubject(subjectName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();
		
		reportLog("19.2: Select the option to edit details page");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("19.2.1: Edit Subject modal window is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("20: Change Subject status manually to custom status (alias) Step#22. Save and E-sign the change.");
		subjectDetailPage.selectStatus(discontinuedCustomStatus);
		subjectDetailPage.inputNotesText("Test");
		subjectDetailPage.clickOnSaveBtn();
		
		subjectDetailPage.verifyReasonForChangePopUpOkAndCancelButtonisDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.inputCredentialsInReasonForChangePopUp(SuperAdminUN, SuperAdminPW);		
		
		reportLog("20.1: Status is successfully changed.");
		subjectDetailPage.verifyDetailStatus("Status",discontinuedCustomStatus);		
				
		reportLog("21: Open change status history modal window");
		subjectDetailPage.clickOnShowHistory();
		
		reportLog("21.1: Status Subject History modal window is opened ");
		subjectDetailPage.verifyHistoryPopUpDisplayed();
		
		reportLog("21.2: The record is available on the top, that the status is changed to Screened and it was changed by the person who e-sign the change");
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		
		reportLog("21.3: Change reason is displayed as the reason for change status ");
		subjectDetailPage.verifySubjectStatusHistoryContainsReasonForChangeStatus("Status changed");
		
		reportLog("21.4: Date and Time are displayed");
		subjectDetailPage.verifySubjectStatusHistoryContainsDateAndTime(currentDate().toUpperCase());
		
		reportLog("22: Close change status history modal window by close option");
		subjectDetailPage.clickOnCloseHistory();
		
		reportLog("22.1: The Subject details page is displayed after the modal window was closed");
		subjectDetailPage.verifyNewSubjectDetailPage();
						
		reportLog("Logout from the application");
		loginPage.logoutApplication(); 
		
		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
	
	
	@AfterMethod
	public void ResetValues() {
		
		reportLog("Reverting Custom Status");
		
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		adminstrationOrganizationPage=	dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,
				Constants.ConfigureNavText, Constants.StudySetupText);	
		adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.clearCustomSubjectStatus();
		adminstrationStudiesCustomPage.clickOnSaveBtn();
		loginPage.logoutApplication(); 
		loginPage.verifyUserLogout();
		
		
	}

}
