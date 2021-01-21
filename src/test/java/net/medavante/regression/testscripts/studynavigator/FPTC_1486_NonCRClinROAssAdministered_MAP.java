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

public class FPTC_1486_NonCRClinROAssAdministered_MAP
		extends BaseTest {

	private String visitName, subjectName = "AutoSubject2202" + generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1486_NonCRClinROAssAdministered_MAP(
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
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_10);
		loginPage.logoutApplication();
		/* Subject Created Successfully */
	}

	/**
	 * =============================================================================================================================================
	 * Test Case Id: FP-TC-1486 Test Case Name: Non-CR ClinRO assessment as 'Not
	 * Administered for Qualified person and a person who is Assigned from
	 * Subject Details page
	 * =============================================================================================================================================
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test(description = "FP-TC-1486_NonCRClinROAssessmentAsNotAdministeredByNotQualifiedPersonAndAPersonWhoIsNOTAssignedFromVisitDetailsPage", groups = {})
	public void FPTC_1486_VerifyNonCRClinROAssAdministered()
			throws InterruptedException {

		reportLog("1.1:As logged in user from PR#1 And  PR#2 go to Dashboard and open the Assessment List");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("1.2:Assessment list is opened");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("2.1:	Open ClinRo form PR#3");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitName, subjectName);

		reportLog("2.2:Assessment details page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.3:Form thumbnail is displayed with 'Pending' label. ");
		assessmentDetailPage.verifyPdfStatusTextPresent(Constants.Pending_Status);

		reportLog("2.4:Checkbox with Mark as 'Not Administered' label is displayed and editable.");
		assessmentDetailPage.verifyCheckBoxIsPresent();

		reportLog("2.5:'Started' , 'Duration' fields are empty.");
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_StartedField, "");
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_DurationField, "");

		reportLog(
				"3.1:Navigate to associated Subject details screen using 'Subject' link in assessment details section");
		subjectDetailPage = assessmentDetailPage.clickOnSubjectLink();

		reportLog(
				"3.2:details section	Subject Details screen is opened. 'Assigned' assessment is displayed with form thumbnail.");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.VerifyFormThumbnailImageBeforeNotAdminsiteredIsPresent();

		reportLog("4.1:Press the form thumbnail");
		assessmentDetailPage = subjectDetailPage.clickOnBeforeNotAdministeredThumbnailImage();

		reportLog("4.2:Assessment details page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("5.1:Set checkbox Mark as Not Administered");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();

		reportLog("5.2:	Checkbox is set. Confirm control is appeared");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("6.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("6.2:	Confirmation message window appears. ");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog(
				"6.3:	The text of confirmation message for unassigned assessment is the following: Please ensure that this assessment is being marked as Not Administered because Site confirmed: ");
		assessmentDetailPage.verifyNotAdministeredConfirmationPopUpPresentWithWarningParagraph();

		reportLog("6.4: No Paper source exists ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(
				Constants.AssesmentDetail_ConfirmationPopUPNoPaperSource_Warning);

		reportLog("6.5: It was not started offline on a tablet ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(
				Constants.AssesmentDetail_ConfirmationPopUPOfflineTablet_Warning);

		reportLog("6.7: Yes and No controls are displayed");
		assessmentDetailPage.verifyNotAdministeredControlsPresent();

		reportLog("7.1:Press No Control for cancelling");
		assessmentDetailPage.clickOnNoButtonOfConfirmationWindow();

		reportLog("7.2:Confirmation message window is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("7.3:Mark as 'Not Administered' checkbox is set.");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("8.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("8.2:Confirmation message window appears.");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog("9.1:	Press Close icon");
		assessmentDetailPage.closeWitheNotAdministredCloseIcon();

		reportLog(
				"9.2:	Confirmation message window is closed. Mark as 'Not Administered' checkbox is set. Confirm' control is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("10.1:Press Yes control for confirmation");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("10.2:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("11.1:Select the reason for change - Enter username and password - Select the cancellation control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChangeWithOutConfirmation(AT_PRODProjectCoordinator, AT_Password);
		assessmentDetailPage.clickOnCancelIconReasonForChange();

		reportLog("11.2:Reason for Change window is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("11.3:Mark as Not Administered checkbox is set. ");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();

		reportLog("11.4:Confirm control is displayed");
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("12.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("12.2:Reason for Change window is opened again.");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("13.1:Select the reason for change -Enter username and password - Select the confirmation control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);

		reportLog(
				"13.2:Assessment receives Complete status. Assessment form thumbnail changed accordingly to Not administered option.");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.verifyAssesmentCheckBoxChangedAccordinglyNotAdministered();

		reportLog(
				"14.1:Navigate to associated Subject details screen using Subject link in assessment details section");
		subjectDetailPage = assessmentDetailPage.clickOnSubjectLink();

		reportLog("14.2:Subject Details screen is opened. ");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog(
				"14.3Not administered and submitted assessment is displayed with 'Not administered' form thumbnail. ");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.verifyAfterSubmissionNotAdministeredThumbnailImage();

		reportLog("14.4:Appropriate submission information with 'Not administered' label are displayed.");
		subjectDetailPage.verifyNotAdministeredLabelPresent();
		assessmentDetailPage = subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();

		reportLog("15.1:Navigate to associated Visit Details screen");
		visitDetaiLPage = assessmentDetailPage.clickOnVisitLink();

		reportLog("15.2:Visit Details screen is opened.");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog(
				"15.3: Not administered and submitted assessment is displayed with 'Not administered' form thumbnail. ");
		visitDetaiLPage.verifyNotAdministeredFormThumbnailImageIsPresent();

		reportLog("15.4:Appropriate submission information with Not administered label are displayed.");
		visitDetaiLPage.verifyNotAdministeredLabelPresent();

		reportLog("16.1:Press the form thumbnail, unset checkbox Mark as Not Administered and confirm it");
		assessmentDetailPage=visitDetaiLPage.clickOnAfterNotAdministeredThumbnailImage();

		reportLog("16.2:Checkbox is unset. Confirm control appears");
		assessmentDetailPage.uncheckNotAdministeredCheckBox();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("17.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("17.2:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("18.1:Select the reason for change -Enter username and password - Select the confirmation control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
//		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);

		reportLog(
				"18.2:Assessment stays in Completed status. 'Paper Transcription' checkbox is displayed and not set.");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.verifyPaperTranscriptionCheckBoxFlagNotSet();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
