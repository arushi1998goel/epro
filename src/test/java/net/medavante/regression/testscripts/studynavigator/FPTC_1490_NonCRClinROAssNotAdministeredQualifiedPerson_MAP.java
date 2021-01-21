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

public class FPTC_1490_NonCRClinROAssNotAdministeredQualifiedPerson_MAP
		extends BaseTest {
	
	private String visitName,subjectName="Auto2203"+generateRandomAlphanumericString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1490_NonCRClinROAssNotAdministeredQualifiedPerson_MAP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName=properties.getProperty("visitClinRoSubmitted");
		
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
	 * Test Case Id: FP-TC-1490 Test Case Name:Non-CR ClinRO assessment as 'Not
	 * Administered by Not Qualified person and a person who is NOT Assigned
	 * from Visit Details page
	 * =============================================================================================================================================
	 * @throws InterruptedException 
	 * 
	 */
	@Test(description = "FP-TC-1490_NonCRClinROAssessmentAsNotAdministeredByNotQualifiedPersonAndAPersonWhoIsNOTAssignedFromVisitDetailsPage", groups = {})
	public void FPTC_1490_VerifyNonCRClinROAssAsNotAdministeredQualifiedPerson() throws InterruptedException {

		reportLog("1.1:As logged in user from PR#1");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3:Navigat" + "e to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4:Study PR#1");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.5:Navigate To The Assesment Page");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("1.5:Assessment list is opened");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("2.1:	Open ClinRo form from PR#3");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitName, subjectName);

		reportLog("2.3:Assessment Details page is opened.");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("2.4:Form thumbnail is displayed with 'Pending' label. ");
		assessmentDetailPage.verifyPdfStatusTextPresent(Constants.Pending_Status);

		reportLog("2.5:Checkbox with Mark as 'Not Administered' label is displayed and editable. ");
		assessmentDetailPage.verifyCheckboxMarkAsNotCompletedAndAdminsiteredExistAndEditable(Constants.MarkAsNotAdministeredLabel);

		reportLog("2.6:'Started' , 'Duration' fields are empty.");
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_StartedField, "");
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_DurationField, "");
		
		reportLog("3.1:	Navigate to associated Visit Details page");
		visitDetaiLPage=assessmentDetailPage.clickOnVisitLink();

		reportLog("3.2:	Visit Details page is opened");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("3.3:'Not Assigned' assessment is displayed with form thumbnail.");
		visitDetaiLPage.verifyRaterAssignmentDropDownSelectedValueForConfiguredFormType(Constants.ClinRO_Form_Label,Constants.notAssignedText);
		visitDetaiLPage.VerifyFormThumbnailImageBeforeNotAdminsiteredIsPresent();
		
		reportLog("4.1:Press the form thumbnail");
		visitDetaiLPage.clickOnBeforeNotAdministeredThumbnailImage();
		
		reportLog("4.2:PDF preview of template is opened.");
		String ParrentWin = switchToChildWindow();
		visitDetaiLPage.verifyFormOpenInPDFtemplate();
		switchParentWindowByClosingChild(ParrentWin);
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("5.1:	Open the same assessment in the Assessment List");
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("5.2:Assessment details page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("6.1:Set checkbox Mark as 'Not Administered'");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		
		reportLog("6.2:	Checkbox is set. 'Confirm' control is appeared");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("7.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("7.2:	Confirmation message window appears. ");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog(
				"7.3:	The text of confirmation message for unassigned assessment is the following: Please ensure that this assessment is being marked as Not Administered because Site confirmed: ");
		assessmentDetailPage.verifyNotAdministeredConfirmationPopUpPresentWithWarningParagraph();
		
		reportLog("7.4::No Rater was assigned to this assessment");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(Constants.AssesmentDetail_ConfirmationPopUPNoRater_Warning);
		
		reportLog("7.5: No Paper source exists ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(Constants.AssesmentDetail_ConfirmationPopUPNoPaperSource_Warning);

		reportLog("7.6: It was not started offline on a tablet ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(Constants.AssesmentDetail_ConfirmationPopUPOfflineTablet_Warning);

		reportLog("7.7: Yes and No controls are displayed");
		assessmentDetailPage.verifyNotAdministeredControlsPresent();

		reportLog("8.1:	Press Yes control for confirmation ");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("8.2:Reason for Change window is displayed ");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("9.1:Select 'Screen failure', enter username and password and press OK control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);

		reportLog("9.2:	Assessment receives 'Complete' status. ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog(
				"10.1:Navigate to associated Subject details page using 'Subject' link in assessment details section");
		subjectDetailPage=assessmentDetailPage.clickOnSubjectLink();
		
		reportLog("10.2:Subject Details page is opened. ");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("10.3:'Not administered' and submitted assessment is displayed with 'Not administered' form thumbnail.  ");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.verifyAfterSubmissionNotAdministeredThumbnailImage();
	
		reportLog("10.4:Appropriate submission information with 'Not administered' label are displayed.");
		subjectDetailPage.verifyNotAdministeredLabelPresent();
		subjectDetailPage.navigateBackToPreviousPage();
		
		reportLog("11.1:Navigate to associated Visit Details page");
		visitDetaiLPage=assessmentDetailPage.clickOnVisitLink();

		reportLog("11.2:Visit Details page is opened Not administere and submitted assessment is displayed with 'Not administered' form thumbnail.");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		visitDetaiLPage.verifyNotAdministeredFormThumbnailImageIsPresent();

		reportLog("11.3:Appropriate submission information with 'Not administered' label are displayed.");
		visitDetaiLPage.verifyNotAdministeredLabelPresent();		
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
	}

}
