package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1482_NonCRclinROAssessment_SIP
		extends BaseTest {

	
	private String visitName, subjectName = "AutoSubject2201" + generateRandomAlphanumericString(5);

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1482_NonCRclinROAssessment_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("NewVisit2201");

		/*Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();
		/* Subject Created Successfully */
	}

	/**
	 * =============================================================================================================================================
	 * Test Case Id: FP-TC-1482 Test Case Name:Non-CR ClinRO assessment as 'Not
	 * Administered by Not Qualified person and a person who is NOT Assigned
	 * from Subject Details page
	 * =============================================================================================================================================
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test(description = "FP-TC-1482_Non-CR ClinRO assessment as 'Not Administered by Not Qualified person and a person who is NOT Assigned from Subject Details page", groups = {})
	public void FPTC_1482_VerifyNonCRClinRAssessmentAsNotAdministeredByNotQualifiedPersonAndAPersonWhoIsNotAssignedFromSubjectDetailsPage()
			throws InterruptedException {

		reportLog("1.1:	Log in to the Portal as User from Pr#1 and PR#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);

		reportLog("1.2:	Login is successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Go to the Study Dashboard and open the Assessment Listing screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("2.2:	Assessment Listing screen is opened successfully");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("3.1:Open ClinRo form from PR#3");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameSecondType, visitName, subjectName);

		reportLog("3.2:Assessment details page is opened.");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3:Form thumbnail is displayed with 'Pending' label. ");
		assessmentDetailPage.verifyPdfStatusTextPresent(Constants.Pending_Status);

		reportLog("3.3:Checkbox with Mark as 'Not Administered' label is displayed and editable. ");
		assessmentDetailPage.verifyCheckBoxIsPresent();

		reportLog("3.4:	'Started' , 'Duration' fields are empty.");
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_StartedField, "");
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_DurationField, "");

		reportLog(
				"4.1:Navigate to associated Subject details screen using 'Subject' link in assessment details section");
		subjectDetailPage = assessmentDetailPage.clickOnSubjectLink();

		reportLog("4.2:	Subject Details page is opened. ");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("4.3:'Not Assigned' assessment is displayed with form thumbnail. ");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.VerifyFormThumbnailImageBeforeNotAdminsiteredIsPresent();

		reportLog("5.1:Press the form thumbnail");
		subjectDetailPage.clickOnBeforeNotAdministeredThumbnailImage();

		reportLog("5.2:PDF preview of template is opened.");
		String ParrentWin = switchToChildWindow();
		subjectDetailPage.verifyFormOpenInPDFtemplate();
		switchParentWindowByClosingChild(ParrentWin);
		subjectDetailPage.navigateToHomePage();

		reportLog("6.1:Open the same assessment in the Assessment Listing screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameSecondType, visitName, subjectName);

	
		reportLog("6.2:Assessment details page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("7.1:Set checkbox to Mark as 'Not Administered'");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();

		reportLog("7.2:	Checkbox is set. 'Confirm' control appears");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("8.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("8.2:	Confirmation message window appears. ");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog(
				"8.3:	The text of confirmation message for unassigned assessment is the following: Please ensure that this assessment is being marked as Not Administered because Site confirmed: ");
		assessmentDetailPage.verifyNotAdministeredConfirmationPopUpPresentWithWarningParagraph();

		reportLog("8.4: No Rater was assigned to this assessment. ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(Constants.AssesmentDetail_ConfirmationPopUPNoRater_Warning);

		reportLog("8.5: No Paper source exists ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(
				Constants.AssesmentDetail_ConfirmationPopUPNoPaperSource_Warning);

		reportLog("8.6: It was not started offline on a tablet ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(
				Constants.AssesmentDetail_ConfirmationPopUPOfflineTablet_Warning);

		reportLog("8.8: Yes and No controls are displayed");
		assessmentDetailPage.verifyNotAdministeredControlsPresent();

		reportLog("9.1:Press No Control for cancelling");
		assessmentDetailPage.clickOnNoButtonOfConfirmationWindow();

		reportLog("9.2:Confirmation message window is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("9.3:Mark as 'Not Administered' checkbox is set.");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("10.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("10.2:Confirmation message window appears.");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog("11.1:Press Close icon");
		assessmentDetailPage.closeWitheNotAdministredCloseIcon();

		reportLog("11.2:Confirmation message window is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("11.3:Mark as 'Not Administered' checkbox is set.");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("12.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("12.2:Confirmation message window appears.");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog("13.1:Press Yes control for confirmation");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("13.2:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("14.1:Select the reason for change - Enter username and password - Select the cancellation control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChangeWithOutConfirmation(AT_PRODProjectCoordinator, AT_Password);
		assessmentDetailPage.clickOnCancelIconReasonForChange();

		reportLog("14.2:Reason for Change window is closed. ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("14.3:Mark as Not Administered checkbox is set. ");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();

		reportLog("15.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("15.2:Press Yes control for confirmation");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("15.3:Reason for Change window is opened again.");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("16.1:Select the reason for change - Enter username and password - Select the confirmation contro");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);

		reportLog("16.2:Assessment receives 'Pending' status, then 'Complete' status.  ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("16.3:Assessment form thumbnail changed accordingly to 'Not administered' option. ");
		assessmentDetailPage.verifyAssesmentCheckBoxChangedAccordinglyNotAdministered();

		reportLog(
				"17.1:Navigate to associated Subject details screen using Subject link in assessment details section");
		subjectDetailPage = assessmentDetailPage.clickOnSubjectLink();

		reportLog("17.2:Subject Details screen is opened. ");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog(
				"17.3Not administered and submitted assessment is displayed with 'Not administered' form thumbnail. ");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.verifyAfterSubmissionNotAdministeredThumbnailImage();

		reportLog("17.4:Appropriate submission information with 'Not administered' label are displayed.");
		subjectDetailPage.verifyNotAdministeredLabelPresent();
		assessmentDetailPage = subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();

		reportLog("18.1:Navigate to associated Visit Details screen");
		visitDetaiLPage = assessmentDetailPage.clickOnVisitLink();

		reportLog("18.2:Visit Details screen is opened.");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog(
				"18.3: Not administered and submitted assessment is displayed with 'Not administered' form thumbnail. ");
		visitDetaiLPage.verifyNotAdministeredFormThumbnailImageIsPresent();

		reportLog("18.4:Appropriate submission information with Not administered label are displayed.");
		visitDetaiLPage.verifyNotAdministeredLabelPresent();

		reportLog("19.1:Navigate to the Assessment details screen");
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("19.2:Unset checkbox Mark as 'Not Administered' and confirm it");
		assessmentDetailPage.uncheckNotAdministeredCheckBox();	

		reportLog("19.3:Checkbox is unset. Reason for Change window is displayed");
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("20.1:Select the reason for change - Enter username and password -Select the confirmation control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);

		reportLog("20.2:Assessment receives 'Pending' status. ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Pending_Status);

		reportLog(
				"20.3:Mark as 'Not Administered' checkbox is not set. The state is reverted as it was before marking as 'Not Administered' ");
		assessmentDetailPage.verifyCheckBoxMarkAsNotAdministeredNotSet();
		assessmentDetailPage.verifyCheckBoxIsPresent();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		
	}

}
