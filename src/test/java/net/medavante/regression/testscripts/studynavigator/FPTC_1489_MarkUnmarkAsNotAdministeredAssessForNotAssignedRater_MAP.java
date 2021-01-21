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

public class FPTC_1489_MarkUnmarkAsNotAdministeredAssessForNotAssignedRater_MAP
		extends BaseTest {

	private String visitName,subjectName="Auto2216"+generateRandomAlphanumericString(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1489_MarkUnmarkAsNotAdministeredAssessForNotAssignedRater_MAP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("visitClinRoSubmitted");	
		
		/* Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();
		/* Subject Created Successfully */

	}

	/**
	 * =============================================================================================================================================
	 * Test Case Id: FP-TC-1489 Test Case Name:Mark and unmark as 'Not
	 * Administered' Assessment for Not Assigned rater changes the status of
	 * assessment
	 * =============================================================================================================================================
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test(description = "FP-TC-1489_MarkAndUnmarkAsNotAdministeredAssessmentForNotAssignedRaterChangesTheStatusOfAssessment", groups = {})
	public void FPTC_1489_VerifyMarkAndUnmarkAsNotAdministeredAssessmentForNotAssignedRaterChangesTheStatusOfAssessment()
			throws InterruptedException {

		reportLog(
				"1.1:As user from PR#2 navigate to Study Dashboard > Assessments Listing screen and open Assessment in Pending status from Pr#3");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitName, subjectName);

		reportLog("1.2:Assessment Details screen is opened successfully.");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.1:Set checkbox Mark as 'Not Administered'");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();

		reportLog("2.2:The checkbox is set. 'Confirm' control appears");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("3.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("3.2:	Confirmation message window appears. ");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog("4.1:Press Yes Control for confirmation");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("4.2:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("5.1:Select 'Reason for Change', enter username and password and press OK control");
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);

		reportLog("5.2:Assessment goes to 'Complete' status. ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("5.3:The Submitted date shows up on the portal.");
		String submissionDateTime =assessmentDetailPage.getCurrentDateAccordingToAssessmentCompletionDateFormat();
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_StartedField, submissionDateTime);
		
		reportLog("6.1:	Unset checkbox Mark as 'Not Administered'. ");
		assessmentDetailPage.uncheckNotAdministeredCheckBox();
		
		reportLog("6.2:The checkbox is unset. 'Confirm' control appears. Option to mark checkbox again is available.");
		assessmentDetailPage.verifyNotAdministeredCheckBoxFlagNotSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("7.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("7.2:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("8.1:Select 'Reason for Change', enter username and password and press OK control");
//		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);

		reportLog("8.2:	Assessment goes to 'Pending' status");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Pending_Status);

		reportLog("8.3: Mark as 'Not Administered' checkbox is not set");
		assessmentDetailPage.verifyCheckBoxMarkAsNotAdministeredNotSet();

		reportLog("8.4:The state is reverted as it was before marking as 'Not Administered'");
		assessmentDetailPage.verifyCheckBoxIsPresent();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
