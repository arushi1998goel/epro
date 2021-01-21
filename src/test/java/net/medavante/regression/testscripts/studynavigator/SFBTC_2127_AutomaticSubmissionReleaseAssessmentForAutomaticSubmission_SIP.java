package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.AssessmentsDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class SFBTC_2127_AutomaticSubmissionReleaseAssessmentForAutomaticSubmission_SIP extends BaseTest {
	private String subjectAutomaticSubmissionName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public SFBTC_2127_AutomaticSubmissionReleaseAssessmentForAutomaticSubmission_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: SFB-TC-2127 Test Case Name:Automatic submission - Allow a
	 * ====================================================================================================================
	 * @throws Exception 
	 * 
	 * 
	 */

	@Test(description = "SFB-TC-2127_Verify Automatic submission - Release assessment for automatic submission ", groups = {})

	public void SFBTC_2127_VerifyAutomaticSubmissionReleaseAssessmentForAutomaticSubmission()
			throws Exception {

		/*-----------Login With  Site User without the claim to allow automatic submission----------*/
		
		reportLog("1.1: Log in to the Portal as User in Pr#3 and navigate to assessment details page in Pr#5");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.RaterName_20);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(
				Constants.StudyDashBoard_columnName_Assessment, Constants.Assesment_AdasCog14List2FormNameSecondType);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Pending_Status);
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(AssessmentsDetailsPage.class);

		reportLog("1.3:Assessment details page of Pr#5 is visible.");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("1.4: The Action dropdown is not displayed.");
		assessmentDetailPage.verifyActionButtonIsNotDisplayed();
		loginPage.logoutApplication();

		/*-----------Login With  Site User who has the claim to allow automatic submission----------*/

		reportLog("2.1:Login to the Portal as User in Pr#4 and navigate to assessment details page in Pr#5");
		dashBoardPage = loginPage.siteLogin(PRODSiteCoordinator, Password);

		reportLog("2.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.RaterName_20);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(
				Constants.StudyDashBoard_columnName_Assessment, Constants.Assesment_AdasCog14List2FormNameSecondType);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Pending_Status);
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(AssessmentsDetailsPage.class);

		reportLog("2.3:Assessment details page of Pr#5 is visible.");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.4: The icon is displayed on the Action drop-down to inform that some action is in pending. ");
		assessmentDetailPage.verifyActionButtonWithPendingOperationExists();

		reportLog("3.1:	Select the Action drop-down and confirm automatic submission");
		assessmentDetailPage.selectOptionInActionDropDown(Constants.Confirm_AutomaticSubmission);

		reportLog("3.2:	E-sign window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("4.1:	Select cancel in E-sign window");
		assessmentDetailPage.clickOnCancelIconReasonForChange();

		reportLog(
				"4.2: E-sing window is closed. -The icon is displayed on the Action drop-down to inform that some action is in pending. ");
		assessmentDetailPage.verifyActionButtonWithPendingOperationExists();

		reportLog("5.1:Select the Action drop-down and confirm automatic submission once again");
		assessmentDetailPage.selectOptionInActionDropDown(Constants.Confirm_AutomaticSubmission);

		reportLog("5.2:E-sign window appears.");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog(
				"6:Verify reason for change in E-sign window and Such reasons are available in the dropdown: - Rater not available.  Other.");
		assessmentDetailPage
				.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAutomaticSubmission);

		reportLog("7.1:	Confirm release automatic submission by entering the corresponding credentials ");
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAutomaticSubmission.get(0));
		assessmentDetailPage.eSignForReasonForChange(PRODSiteCoordinator, Password);
		
		reportLog("7.2:The message is displayed on the assessment details page with tablet instruction");
		assessmentDetailPage.verifyMeesageIsDisplayedWithTabletInstructions();

		reportLog("7.3:A new record is added to the Notes section with corresponding information.");
		assessmentDetailPage.verifyNotesSectionIsAppeared();

		reportLog("8.1: Check that the information was added to the Notes section  Such information is available: '- Submission date'");
		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.TabletRecord,
				Constants.Automatic_SubmissionConfimrationRecord,"");

		reportLog("8.2: Date and time when it was released. ");
		String startedDate =currentDate();
		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.dateRecord,startedDate,"");

		reportLog("8.3::The first and last name of the person who confirmed the release. ");
		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.TabletRecord,
				Constants.RaterName_21,"");
		
		reportLog("8.5:Most recent Tablet ID number on which the assessment resides.");
		String tbaletId=assessmentDetailPage.getTabletId();
		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.TabletRecord,tbaletId,"");
		loginPage.logoutApplication();

		/*-----------Login With  MA User with the claim to allow automatic submission----------*/
		reportLog("9.1:log in to Portal as User in Pr#2 and navigate to assessment details page in Pr#5");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectManager, AT_Password);

		reportLog("9.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.RaterName_20);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(
				Constants.StudyDashBoard_columnName_Assessment, Constants.Assesment_AdasCog14List2FormNameSecondType);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Pending_Status);
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(AssessmentsDetailsPage.class);

		reportLog("9.3:Assessment details page of Pr#5 is visible.");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("9.4: Action drop-down with option to remove automatic submission is not available");
		assessmentDetailPage.verifyActionButtonIsNotDisplayed();
		loginPage.logoutApplication();
		
		

	}
}
