package net.medavante.regression.testscripts.r2suite;

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

public class R2_FPTC_1154InPortalTrackReasonForChangeOfExisitingSubjectInformation_SIP extends BaseTest {

	private String  studyNameCR, studyNameNonCR, Notes_Text, screeningNum_NonCR_New,
			screeningNum_NonCR_Screened, screeningNum_NonCR_Discontinued, Visit_Screened, Visit_Discontinued,
			screeningNum_CR_New;
			

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1154InPortalTrackReasonForChangeOfExisitingSubjectInformation_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		Configuration.updatePropertyTestData("RegressionTestData", "AutoSubjectStatus_New_1682CR",
				"Auto_Screening_" + generateRandomString(3) + getRandomInteger(0, 9));
		Configuration.updatePropertyTestData("RegressionTestData", "AutoSubjectStatus_New_1682NonCR",
				"Auto_Screening_" + generateRandomString(3) + getRandomInteger(0, 9));
		Configuration.updatePropertyTestData("RegressionTestData", "AutoSubjectStatus_Screened_1682NonCR",
				"Auto_Screening_" + generateRandomString(3) + getRandomInteger(0, 9));
		Configuration.updatePropertyTestData("RegressionTestData", "AutoSubjectStatus_Discontinued_1682NonCR",
				"Auto_Screening_" + generateRandomString(3) + getRandomInteger(0, 9));

		Properties properties = Configuration.readTestData("RegressionTestData");


		System.setProperty("className", getClass().getSimpleName());

		studyNameCR = properties.getProperty("AutomationStudyNameCR");
		studyNameNonCR = properties.getProperty("AutomationStudyNameNonCR");
		screeningNum_NonCR_New = properties.getProperty("AutoSubjectStatus_New_1682NonCR");
		screeningNum_NonCR_Screened = properties.getProperty("AutoSubjectStatus_Screened_1682NonCR");
		screeningNum_NonCR_Discontinued = properties.getProperty("AutoSubjectStatus_Discontinued_1682NonCR");
		screeningNum_CR_New = properties.getProperty("AutoSubjectStatus_New_1682CR");
		Notes_Text = "Edit Subject Notes " + generateRandomString(3);
		Visit_Screened = properties.getProperty("Visit_Screened_1682NonCR");
		Visit_Discontinued = properties.getProperty("Visit_Discontinued_1682NonCR");

		/** Create Subject on MA portal */

		reportLog("Creating CR subjects with status 'New' on MA portal");
		dashBoardPage = loginPage.maLogin(SuperAdminUN, SuperAdminPW);

		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyNameCR, AT_PRODSiteCoordinatorUserName, screeningNum_CR_New);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum_CR_New);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		/** Create Subject on Site portal */

		reportLog("Creating Non-CR subjects with status 'New' on Site portal");

		dashBoardPage = loginPage.siteLogin(SuperAdminUN, SuperAdminPW);

		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyNameNonCR, Constants.ATAssignedRater_10, screeningNum_NonCR_New);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum_NonCR_New);

		reportLog("Creating Non-CR subjects with status 'Screened' on Site portal");

		subjectDetailPage.navigateBack();
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(screeningNum_NonCR_Screened);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyVisitGridDisplayed();
		subjectDetailPage.clickOnVisitRow(Visit_Screened);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);

		reportLog("Creating Non-CR subjects with status 'Discontinued' on Site portal");

		dashBoardPage=studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.createSubjectWithExistingStudy(screeningNum_NonCR_Discontinued);

		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum_NonCR_Discontinued);
		subjectDetailPage.verifyVisitGridDisplayed();
		subjectDetailPage.clickOnVisitRow(Visit_Screened);
		subjectDetailPage.clickOnAddVisitIcon();
//		subjectDetailPage.selectRaterFromDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label,Constants.ATAssignedRater_10);
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();
		subjectDetailPage.selectUnscheduledVisit(Visit_Discontinued);
//		subjectDetailPage.selectRaterFromDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label,Constants.ATAssignedRater_10);
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);
		assessmentDetailPage = subjectDetailPage.selectThumbnailIMGFromMultipleConfiguredFormType(Constants.ClinRO_Form_Label);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * =========================================================================
	 * Test Case Id: FPTC_1154 Test Case Name: In Portal - track reason for
	 * change of existing subject information
	 * =========================================================================
	 * 
	 * @throws Exception
	 * 
	 */

	@Test(description = "FPTC_1154_In Portal - track reason for change of exisiting subject information ", groups = {
			"R2" })
	public void FPTC_1154_TrackReasonForChangeOfExisitingSubjectInformation() throws Exception {

		/**
		 * =========================================================================
		 * Test case: TC#1 Portal: Administration Study Type: CR Subject Type:
		 * New
		 * =========================================================================
		 */

		

		reportLog("1.1.1 :Login to Administration Portal");
		 dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2.1 : Navigate to study dashboard");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.2.2 : select " + studyNameCR + " study.");
		studyNavigatorDashBoardPage.selectStudy(studyNameCR,Constants.ATAssignedRater_10);

		reportLog("1.2.3: Select " + screeningNum_CR_New + " from subject list");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum_CR_New);

		reportLog("1.2.4: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum_CR_New);

		reportLog("1.2.5: Verify Subject Status should be: " + Constants.SubjectStatus_New);
		subjectDetailPage.verifyDetailStatus("Status", Constants.SubjectStatus_New);

		reportLog("1.2.6: Verify Subject Edit Btn is enable");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("1.3.1: Click on Subject details edit icon");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("1.3.2: Verify Edit subject Save Button is displayed");
		subjectDetailPage.verifyEditSubjectSaveButtonIsDisplayed();

		reportLog("1.4.1: Update Screening no in Edit subject modal window");
		screeningNum_CR_New = subjectDetailPage.updateScreeningInpWithNw("AutoSubjectStatus_New_1682CR");

		reportLog("1.4.2: Update Notes in Edit subject modal window: " + Notes_Text);
		subjectDetailPage.inputNotesText(Notes_Text);

		reportLog("1.4.3: Click Edit subject Save Button");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("1.4.4: Verify Reason for Change modal window appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("1.5.1: Verify expected reasons are available in Reason for Change drop down");
		subjectDetailPage.verifyReasonForChangeOption(Constants.ReasonsForChange);

		reportLog("1.6.1: Select reason : " + Constants.ReasonsForChange.get(0) + " as ReasonForChange");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(0));

		reportLog("1.6.2: Proceed to save by providing e-signing");
		 subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("1.6.3: Verify Screening Number is updated");
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(screeningNum_CR_New);

		reportLog("1.6.4: Verify Note text is updated");
		subjectDetailPage.verifyNoteTextIsSaved(Notes_Text);

		reportLog("1.6.5: Logout application");
		loginPage.logoutApplication();

		reportLog("1.6.6: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

		

		/**
		 * =========================================================================
		 * Test case: TC#3 Portal: Site Study Type: non-CR Subject Type: New
		 * =========================================================================
		 */

		reportLog("3.1.1: Navigate to Site Portal");
	

		reportLog("3.1.2: Login to Site Portal");
		dashBoardPage = loginPage.siteLogin(SuperAdminUN, SuperAdminPW);

		reportLog("3.1.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.2.1: Navigate to study dashboard");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("3.2.2: select " + studyNameNonCR + " study.");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ATAssignedRater_10);

		reportLog("3.2.3: Select " + screeningNum_NonCR_New + " from subject list");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum_NonCR_New);

		reportLog("3.2.4: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum_NonCR_New);

		reportLog("3.2.5: Verify Subject Status: " + Constants.SubjectStatus_New);
		subjectDetailPage.verifyDetailStatus("Status", Constants.SubjectStatus_New);

		reportLog("3.2.6: Verify Subject Edit Btn is enable");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("3.3.1: Click on Subject details edit icon");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("3.3.2: Verify Edit subject Save Button is displayed");
		subjectDetailPage.verifyEditSubjectSaveButtonIsDisplayed();

		reportLog("3.4.1: Update Screening no in Edit subject modal window");
		screeningNum_NonCR_New = subjectDetailPage.updateScreeningInpWithNw("AutoSubjectStatus_New_1682NonCR");

		reportLog("3.4.2: Update Notes in Edit subject modal window: " + Notes_Text);
		subjectDetailPage.inputNotesText(Notes_Text);

		reportLog("3.4.3: Click Edit subject Save Button");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("3.4.4: Verify Reason for Change modal window appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("3.5.1: Verify expected reasons are available in Reason for Change drop down");
		subjectDetailPage.verifyReasonForChangeOption(Constants.ReasonsForChange);

		reportLog("3.6.1: Select reason : " + Constants.ReasonsForChange.get(2) + " as ReasonForChange");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(2));

		reportLog("3.6.2: Proceed to save by providing e-signing");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("3.6.3: Verify Screening Number is updated");
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(screeningNum_NonCR_New);

		reportLog("3.6.4: Verify Note text is updated");
		subjectDetailPage.verifyNoteTextIsSaved(Notes_Text);

		reportLog("3.6.5: Logout application");
		loginPage.logoutApplication();

		reportLog("3.6.6: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

		/**
		 * =========================================================================
		 * Test case: TC#4 Portal: Site Study Type: non-CR Subject Type:
		 * Screened
		 * =========================================================================
		 */

		reportLog("4.1.1 :Login to Site Portal");
		loginPage.siteLogin(SuperAdminUN, SuperAdminPW);

		reportLog("4.1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("4.2.1 : Navigate to study dashboard");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("4.2.2 : select " + studyNameNonCR + " study.");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ATAssignedRater_10);

		reportLog("4.2.3: Select " + screeningNum_NonCR_Screened + " from subject list");
		studyNavigatorDashBoardPage.clickOnSubject(screeningNum_NonCR_Screened);

		reportLog("4.2.4: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum_NonCR_Screened);

		reportLog("4.2.5: Verify Subject Status: " + Constants.SubjectStatus_Screened);
		subjectDetailPage.verifyDetailStatus("Status", Constants.SubjectStatus_Screened);

		reportLog("4.2.6: Verify Subject Edit Btn is enable");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("4.3.1: Click on Subject details edit icon");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("4.3.2: Verify Edit subject Save Button is displayed");
		subjectDetailPage.verifyEditSubjectSaveButtonIsDisplayed();

		reportLog("4.4.1: Update Screening no in Edit subject modal window");
		screeningNum_NonCR_Screened = subjectDetailPage
				.updateScreeningInpWithNw("AutoSubjectStatus_Screened_1682NonCR");

		reportLog("4.4.2: Update Notes in Edit subject modal window: " + Notes_Text);
		subjectDetailPage.inputNotesText(Notes_Text);

		reportLog("4.4.3: Click Edit subject Save Button");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("4.4.4: Verify Reason for Change modal window appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("4.5.1: Verify expected reasons are available in Reason for Change drop down");
		subjectDetailPage.verifyReasonForChangeOption(Constants.ReasonsForChange);

		reportLog("4.6.1: Select reason : " + Constants.ReasonsForChange.get(3) + " as ReasonForChange");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(3));

		reportLog("4.6.2: Proceed to save by providing e-signing");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("4.6.3: Verify Screening Number is updated");
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(screeningNum_NonCR_Screened);

		reportLog("4.6.4: Verify Note text is updated");
		subjectDetailPage.verifyNoteTextIsSaved(Notes_Text);

		reportLog("4.6.5: Logout application");
		loginPage.logoutApplication();

		reportLog("4.6.6: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

		/**
		 * =========================================================================
		 * Test case: TC#5 Portal: Site Study Type: non-CR Subject Type:
		 * Discontinued
		 * =========================================================================
		 */

		reportLog("5.1.1 :Login to Site Portal");
		loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("5.1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("5.2.1 : Navigate to study dashboard");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("5.2.2 : select " + studyNameNonCR + " study.");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ATAssignedRater_10);

		reportLog("5.2.3: Select " + screeningNum_NonCR_Discontinued + " from subject list");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum_NonCR_Discontinued);

		reportLog("5.2.4: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum_NonCR_Discontinued);

		reportLog("5.2.5: Verify Subject Status: " + Constants.SubjectStatus_Discontinued);
		subjectDetailPage.verifyDetailStatus("Status", Constants.SubjectStatus_Discontinued);

		reportLog("5.2.6: Verify Subject Edit Btn is enable");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("5.3.1: Click on Subject details edit icon");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("5.3.2: Verify Edit subject Save Button is displayed");
		subjectDetailPage.verifyEditSubjectSaveButtonIsDisplayed();

		reportLog("5.4.1: Update Screening no in Edit subject modal window");
		screeningNum_NonCR_Discontinued = subjectDetailPage
				.updateScreeningInpWithNw("AutoSubjectStatus_Discontinued_1682NonCR");

		reportLog("5.4.2: Update Notes in Edit subject modal window: " + Notes_Text);
		subjectDetailPage.inputNotesText(Notes_Text);

		reportLog("5.4.3: Click Edit subject Save Button");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("5.4.4: Verify Reason for Change modal window appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("5.5.1: Verify expected reasons are available in Reason for Change drop down");
		subjectDetailPage.verifyReasonForChangeOption(Constants.ReasonsForChange);

		reportLog("5.6.1: Select reason : " + Constants.ReasonsForChange.get(4) + " as ReasonForChange");
		subjectDetailPage.selectReasonForChangeOption(Constants.ReasonsForChange.get(4));

		reportLog("5.6.2: Enter comment for Reason For Change: " + Constants.Other_Reason_For_Change);
		subjectDetailPage.inputReasonForChangeComment(Constants.Other_Reason_For_Change);

		reportLog("5.6.3: Proceed to save by providing e-signing");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("5.6.4: Verify Screening Number is updated");
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(screeningNum_NonCR_Discontinued);

		reportLog("5.6.5: Verify Note text is updated");
		subjectDetailPage.verifyNoteTextIsSaved(Notes_Text);

		reportLog("5.6.6: Logout application");
		loginPage.logoutApplication();

		reportLog("5.6.7: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

	}

}
