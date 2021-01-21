package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FPTC_2738_SubjectStatusFlowWithScreenFailedStatus_SIP extends BaseTest {

	private String studyName, visitScreened, visitEnrolled, visitCompleted, visitCompletedSecond, subjectNew,
			completedCustomStatus = "Auto2065Custom",
			subjectScreened = "Auto2065Screened" + generateRandomAlphanumericString(5),
			subjectCompleted2 = "Auto2065CompletedSecond" + generateRandomAlphanumericString(5),
			subjectEnrolled = "Auto2065Enrolled" + generateRandomAlphanumericString(5),
			subjectCompleted = "Auto2065Completed" + generateRandomAlphanumericString(5),
			SubjectCustom = "AutoSubjectCustom" + generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2738_SubjectStatusFlowWithScreenFailedStatus_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("tabletFormSettingStudy");
		subjectNew = properties.getProperty("SubjectNew2065");
		visitScreened = properties.getProperty("Auto_ScreenedStatusVisit");
		visitEnrolled = properties.getProperty("Auto_EnrolledStatusVisit");
		visitCompleted = properties.getProperty("Auto_CompletedStatusVisit");
		visitCompletedSecond = properties.getProperty("Auto_CompletedStatusVisit");

		/*--------------Configuring Screened Subject Status-------------*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectScreened);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitScreened,SuperAdminUN, SuperAdminPW);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		/*--------------Configuring Enrolled Subject Status-------------*/
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectEnrolled);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitEnrolled,SuperAdminUN, SuperAdminPW);

		/*--------------Configuring Complete Subject Status-------------*/
		subjectDetailPage.navigateBackToPreviousPage();
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectCompleted);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitCompleted,SuperAdminUN, SuperAdminPW);

		/*--------------Configuring Completed Second Subject Status-------------*/
		subjectDetailPage.navigateBackToPreviousPage();
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(subjectCompleted2);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitCompletedSecond,SuperAdminUN, SuperAdminPW);
	
		/*---------------Subject For Custom Status----------------*/
		subjectDetailPage.navigateBackToPreviousPage();
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(SubjectCustom);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitCompletedSecond,SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();

	}

	/**
	 * ==========================================================================
	 * Test Case Id: FP-TC-2738 Test Case Name: Subject Status - Flow with "Screen Failed" status
	 * ===========================================================================
	 * 
	 */

	@Test(description = "FP-TC-2738_Subject Status - Flow with Screen Failed status", groups = { "" })
	public void FPTC_2738_verifySubjectStatusFlowWithScreenFailedStatus() {

		reportLog("1.1:	Log in to Portal as a User from PR#1");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("1.2:	Home page displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog(
				"2.1:Navigate to Subject Detail screen and open subject with PR#3, click Edit Subject details control");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchSubject(subjectScreened);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectScreened);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("2.2:	Subject Details screen is in Edit mode");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("3.1:	Select Screen Failed status and Cancel changes");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnCancelBtn();

		reportLog("3.2:	No changes applied for the Subject Detail screen");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Screened);

		reportLog("4.1:	Select Screen Failed status and save");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("4.2:	Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("5.1:	Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("5.2:Screen Failed status is saved and displayed in Subject Details ");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_ScreenFailed);

		reportLog("6.1:	Open Status drop-down list, change status to Discontinued and Save changes");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Discontinued);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("6.2:Discontinued status is saved and displayed in Subject Details");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Discontinued);

		reportLog(
				"7.1:Navigate to Subject Detail screen and open subject with PR#5, Click Edit Subject Details control");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.searchSubject(subjectCompleted);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectCompleted);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("7.2:	Subject Details form is in the Edit mode");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("8.1:Select Screen Failed status and Cancel changes");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnCancelBtn();

		reportLog("8.2:No changes applied for the Subject Detail screen");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Completed);

		reportLog("9.1:	Select Screen Failed status and save");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("9.2:	Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("10.1:Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("10.2:Screen Failed status is saved and displayed in Subject Details ");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_ScreenFailed);

		reportLog("11.1:Open Status drop-down list, change status to Screened and Save changes");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Screened);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("11.2:Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("12.1:Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("12.2:“Screened” status is saved and displayed in Subject Details");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Screened);

		reportLog(
				"13.1:Navigate to Subject Detail screen and open subject with PR#4, Click Edit Subject details control");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.searchSubject(subjectEnrolled);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectEnrolled);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("13.2:Subject Details form is in the Edit mode");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("14.1:Select Screen Failed status and Cancel changes");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnCancelBtn();

		reportLog("14.2:No changes applied for the Subject Detail screen");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Screened);

		reportLog("15.1:Select Screen Failed status and save");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("15.2:Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("16.1:Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("16.2:Screen Failed status is saved and displayed in Subject Details");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_ScreenFailed);

		reportLog("17.1:Open the Status drop-down, change status to Enrolled and Save changes");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Enrolled);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("17.2:Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("18.1:Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("18.2:Enrolled status is saved and displayed in Subject Details");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Enrolled);

		reportLog(
				"19.1:Navigate to Subject Detail screen and open subject with PR#5, Click Edit Subject details control");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.searchSubject(subjectCompleted2);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectCompleted2);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("19.2:Subject Details form is in the Edit mode");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("20.1:Select Screen Failed status and Cancel changes");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnCancelBtn();

		reportLog("20.2:No changes applied for the Subject Detail screen");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Completed);

		reportLog("21.1:Select Screen Failed status and save");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("21.2:Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("22.1:Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("22.2:Screen Failed status is saved and displayed in Subject Details");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_ScreenFailed);

		reportLog("23.1:Open the Status drop-down, change status to Completed and Save changes");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Completed);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("23.2:Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("24.1:Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("24.2:Completed status is saved and displayed in Subject Details");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_Completed);

		reportLog("Configuring Subject With Custom Status");
		subjectDetailPage.navigateToHomePage();
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.editSubjectAlias(Constants.SubjectStatus_Completed,completedCustomStatus);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
	

		reportLog(
				"25.1:Navigate to Subject Detail screen and open subject with PR#6, click Edit Subject details control");
		studyNavigatorDashBoardPage.searchSubject(SubjectCustom);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(SubjectCustom);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("25.2:Subject Details form is in the Edit mode");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("26.1:Select Screen Failed status and Cancel changes");
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnCancelBtn();

		reportLog("26.2:No changes applied for the Subject Detail screen");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, "");

		reportLog("27.1:Select Screen Failed status and save");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus(Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("27.2:Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("28.1:Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("28.2:Screen Failed is saved and displayed in Subject Details");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status,
				Constants.SubjectStatus_ScreenFailed);

		reportLog("29.1:Open the Status drop-down, change status to Custom and Save changes");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.selectStatus("");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("29.2:Reason for Change window is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("30.1:Select the reason for change - Enter username and password - Select the confirmation control");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_ProdAdminOps, AT_Password);

		reportLog("30.2:Custom is saved and displayed in Subject Details");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, "");

		reportLog("31.1:Select any subject with status from PR#7 and go to Edit mode");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.searchSubject(subjectNew);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectNew);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("31.2:Status drop-down is disabled");
		subjectDetailPage.verifySubjectStatusEditButtonDisabled();

		reportLog("32.1:Press Cancel changes");
		subjectDetailPage.clickOnCancelBtn();

		reportLog("32.2:New subject is displayed for Subject Status");
		subjectDetailPage.verifyDetailStatus(Constants.StudyDashBoard_columnName_Status, Constants.SubjectStatus_New);

		reportLog("Reverting Subject With Custom Status");
		subjectDetailPage.navigateToHomePage();
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();
		adminstrationStudiesCustomPage.clearCustomSubjectStatus();
		adminstrationStudiesCustomPage.clickOnSaveBtn();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
