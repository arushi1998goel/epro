package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.aspectj.apache.bcel.classfile.Constant;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.AssessmentsDetailsPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1249_TemporaryIDAppearsAsASubjectIdentifierWhileAddingASubject_SIP extends BaseTest {

	private String siteRater1LoginUN, studyNameNonCR, SubjectNumber, visitName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1249_TemporaryIDAppearsAsASubjectIdentifierWhileAddingASubject_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyNameNonCR = properties.getProperty("AutomationStudyNameNonCR");
		visitName = properties.getProperty("Auto_Visit1781");
		studyLanguage = properties.getProperty("studyLanguage");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1249 Test Case Name: Temporary ID appears as a
	 * subject identifier while adding a subject
	 * =========================================================================
	 * 
	 * @throws Exception
	 * 
	 */

	@Test(description = "FP-TC-1249_Temporary ID appears as a subject identifier while adding a subject", groups = {
			"" })
	public void FPTC_1249_TemporaryIDAppearsAsSubjectIdentifierWhileAddingSubject() throws Exception {

		reportLog("1.2: Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.2: select " + studyNameNonCR + " study.");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ATAssignedRater_10);
		
		reportLog("2.3: Add new Subject by control 'Add New Subject'");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
	
		reportLog("2.4: Verify Add Subject Popup appear");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog(
				"3-4: Verify Screening Number and TemporaryID fields are highlighted by entering values in both fields");
		SubjectNumber = studyNavigatorDashBoardPage.verifyScreenAndTempFieldHighlighted(screeningNum);

		reportLog("5.1: Select a language​");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("5.2: Save the changes");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("6.1: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("6.2: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("6.3: Click on add visit icon");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("6.4: Verify Visit is added");
		subjectDetailPage.verifyVisitStatus(visitName, Constants.Pending_Status);

		reportLog("6.5: Verify Forms for assessments are listed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("6.6: Verify Name of the Other Rater is displayed in rater drop down list");
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_12);

		reportLog("6.7: Logout from the application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("6.8: Login to The Application from pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("6.9: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("6.10: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("6.11: Select Study");
		studyNavigatorDashBoardPage.selectStudy(studyNameNonCR,Constants.ATAssignedRater_10);
		
		reportLog("6.12 Navigate To Assesment Listing Page");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("6.13: Navigate To Assesment Details Page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visitName,
				SubjectNumber);

		reportLog("6.14: Verify Assesment Detail Page Displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("6.15: Set CheckBox As Not Administered");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();

		reportLog("6.16: Verify CheckBox Is Set");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();

		reportLog("6.17: Confirm Button Appears");
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("6.18: Click On Confirm Button");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("6.19: Confirmation Window Appears");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog("6.20: Press Yes Control From Confirmation");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("6.21:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("6.22: Select 'Reason for Change', enter username and password and press OK control");
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog(
				"6.23: Verify Assessment receives 'Complete' status  Assessment form thumbnail changed accordingly to 'Not administered' option");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("6.24: Verify CheckBox Span Text Changed As Not Administered");
		assessmentDetailPage.verifyAssesmentCheckBoxChangedAccordinglyNotAdministered();
		assessmentDetailPage.clickToCloseFailedTrainingInfoAlert();

		reportLog("7.1: Navigate to Home page");
		assessmentDetailPage.navigateOnHomePage();
		
		reportLog("7.2: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("7.3: Navigate to Visits List");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("8.1: Navigate To Visit Details Page");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitName, SubjectNumber);

		reportLog("8.2: Verify Subject Number");
		visitDetaiLPage.verifyVisitDetailsUnderSubjectSection("Subject", SubjectNumber);

		reportLog("9.1: Navigate to Home page");
		studyNavigatorDashBoardPage.navigateToHomePage();

		reportLog("9.2: Navigate to Study Navigator");
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("9.3: Navigate to Subjects Listing");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("9.4: Verify Language and Subject");
		studyNavigatorDashBoardPage.searchBySubjectNameAndVerifyLanguage(SubjectNumber, Constants.subjectLanguage);

		reportLog("10.1 Navigate To Assesment Listing Page");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("10.2: Navigate To Assesment Details Page");
		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visitName, SubjectNumber);

		reportLog("10.3: Logout application");
		loginPage.logoutApplication();

		reportLog("10.4: Verify user is Logout from application");
		loginPage.verifyUserLogout();

	}
}
