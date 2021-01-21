package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class SFBTC_2124AutomaticSubmissionAllow_MAP extends BaseTest {

	private String subjectAutomaticSubmissionName, visitAllowAutomaticSubmissionFirst,
			visitAllowAutomaticSubmissionSecond;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public SFBTC_2124AutomaticSubmissionAllow_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		subjectAutomaticSubmissionName = properties.getProperty("SubjectAutomaticSubmission");
		visitAllowAutomaticSubmissionFirst = properties.getProperty("visitClinRoSubmitted");
		visitAllowAutomaticSubmissionSecond = properties.getProperty("VisitAutomaticSubmission");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: SFB-TC-2124 Test Case Name: Automatic submission - Allow
	 * automatic submission
	 * ====================================================================================================================
	 * 
	 * 
	 */

	@Test(description = "SFB-TC-2124_Verify Automatic submission - Allow automatic submission ", groups = {})
	public void SFBTC_2124_VerifyAutomaticSubmissionAllow() {
		
		/*-----------Login With  MA User who has the claim to allow automatic submission----------*/

		reportLog("1: Log in to the Portal as User in Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.1:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:navigate to assessment details page in Pr#4.");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("1.3:Option to authorize automatic submission is available In the Action drop-down");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(Constants.Authorize_AutomaticSubmission);

		reportLog("2.1:In the Action drop-down select the option to authorize automatic submission");
		assessmentDetailPage.selectOptionInActionDropDown(Constants.Authorize_AutomaticSubmission);

		reportLog("2.2:Confirmation message is appeared ");
		assessmentDetailPage.verifyAllowAutomaticConfirmationPopUpDisplayed();

		reportLog("2.3:Options to confirm and cancel are available");
		assessmentDetailPage.verifyOptionsToConfirmCancelAvailable();

		reportLog("3.1:	Select the cancel option in the confirmation message");
		assessmentDetailPage.clickOnConfirmationOfAllowAutomationSubmissionNoButton();

		reportLog("3.2:Confirmation message is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3:Authorize automatic submission is cancelled.");
		reportLog("3.4:Option to authorize automatic submission is available In the Action drop-down");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(Constants.Authorize_AutomaticSubmission);

		reportLog("4.1:	In the Action drop-down select the option to authorize automatic submission once again");
		assessmentDetailPage.selectOptionInActionDropDown(Constants.Authorize_AutomaticSubmission);

		reportLog("4.2:	Confirmation message is appeared ");
		assessmentDetailPage.verifyAllowAutomaticConfirmationPopUpDisplayed();

		reportLog("4.3:	Options to confirm and cancel are available");
		assessmentDetailPage.verifyOptionsToConfirmCancelAvailable();

		reportLog("5.1:	Select the option to confirm the authorize automatic submission");
		assessmentDetailPage.clickOnConfirmationOfAllowAutomationSubmissionYesButton();

		reportLog("5.2:Confirmation message is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog(
				"5.3:Notes section is appeared. A record is displayed that assessment was marked for automatic submission.  ");
		assessmentDetailPage.verifyNotesSectionIsAppeared();
		
		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.TabletRecord,
				Constants.Automatic_SubmissionConfimrationRecord,"");

		reportLog("5.4:Option to deactivate (remove automatic submission) is available In the Action drop-down ");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(Constants.Remove_AutomaticSubmission);

		reportLog("6.1:In the Action drop-down select the option to deactivate (remove automatic submission)");
		assessmentDetailPage.selectOptionInActionDropDown(Constants.Remove_AutomaticSubmission);

		reportLog("6.2:The confirmation message appears. ");
		assessmentDetailPage.verifyAllowAutomaticConfirmationPopUpDisplayed();

		reportLog("6.3:Options to confirm and cancel are available. ");
		assessmentDetailPage.verifyOptionsToConfirmCancelAvailable();

		reportLog("7.1:Select the Cancel option in the confirmation message");
		assessmentDetailPage.clickOnConfirmationOfAllowAutomationSubmissionNoButton();

		reportLog("7.2:Confirmation message is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("7.3:Automatic submission deactivation is canceled.");
		reportLog("7.4:Option to deactivate (remove automatic submission) is available In the Action drop-down");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(Constants.Remove_AutomaticSubmission);
		reportLog(
				"8.1:In the Action drop-down select the option to deactivate (remove automatic submission) once again");
		assessmentDetailPage.selectOptionInActionDropDown(Constants.Remove_AutomaticSubmission);

		reportLog("8.2:The confirmation message appears. ");
		assessmentDetailPage.verifyAllowAutomaticConfirmationPopUpDisplayed();

		reportLog("8.3:Options to confirm and cancel are available.");
		assessmentDetailPage.verifyOptionsToConfirmCancelAvailable();

		reportLog("9.1:Select the option to confirm removing automatic submission");
		assessmentDetailPage.clickOnConfirmationOfAllowAutomationSubmissionYesButton();

		reportLog("9.2:The confirmation message is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("9.3:Notes section is updated. A new record is added that automatic submission was removed. ");
		assessmentDetailPage.verifyNotesSectionIsAppeared();
		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.TabletRecord,
				Constants.Automatic_SubmissionCanceledRecord,"");

		reportLog("10.1:Navigate to assessment details page in Pr#5 ");
		assessmentDetailPage.navigateBack();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormName, visitAllowAutomaticSubmissionSecond,
				subjectAutomaticSubmissionName);

		reportLog("10.2:Assessment details page of Pr#5 is visible.  ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("10.3:Option to activate automatic submission is available on the page  ");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(Constants.Authorize_AutomaticSubmission);

		reportLog("11.1:In the Action drop-down select the option to activate automatic submission and confirm it.  ");
		assessmentDetailPage.selectOptionInActionDropDown(Constants.Authorize_AutomaticSubmission);
		assessmentDetailPage.clickOnConfirmationOfAllowAutomationSubmissionYesButton();

		reportLog("11.2:The confirmation message is closed.   ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog(
				"11.3:Notes section is appeared. A record is displayed that assessment was marked for automatic submission ");

		assessmentDetailPage.verifyNotesSectionIsAppeared();
		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.TabletRecord,
				Constants.Automatic_SubmissionConfimrationRecord,"");

		reportLog("11.3:Option to deactivate (remove automatic submission) is available. ");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(Constants.Remove_AutomaticSubmission);
				
		reportLog("Setting up the Pre-requisite");
		assessmentDetailPage.selectOptionInActionDropDown(Constants.Remove_AutomaticSubmission);
		assessmentDetailPage.clickOnConfirmationOfAllowAutomationSubmissionYesButton();
		assessmentDetailPage.navigateBackToDashBoard();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		
		/*-----------Login With MA User without the claim to allow automatic submission----------*/

		reportLog("12.1:	Login to the Portal as User in Pr#3 and  ");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);

		reportLog("12.2: Login is successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("12.3:Navigate to assessment details page in Pr#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);

		reportLog("12.3: Assessment details page of Pr#4 is visible.  ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("12.4:Option to authorize automatic submission is not displayed ");
		assessmentDetailPage.verifyOptionToAuthorizeAutomatiSubmissionIsNotDisplayed();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
