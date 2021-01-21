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

public class FPTC_1490_NonCRClinROAssesAsNotAdministeredForQualifiedPerson_SIP
		extends BaseTest {
	private String visitName, subjectName = "AutoSubject2205" + generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1490_NonCRClinROAssesAsNotAdministeredForQualifiedPerson_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("visitClinRoSubmitted");

		reportLog("1: Log in to the Portal as User in Pr#2");
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
	 * Test Case Id: FP-TC-1490 Test Case Name:Non-CR ClinRO assessment as 'Not
	 * Administered for Qualified person and a person who is Assigned from Visit
	 * Details page
	 * 
	 * =============================================================================================================================================
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test(description = "FPTC_1490_NonCRClinROAssessmentAsNotAdministeredForQualifiedPersonAndAPersonWhoIsAssignedFromVisitDetailsPage", groups = {})
	public void FPTC_1490_VerifyNonCRClinROAssessmentAsNotAdministeredForQualifiedPersonAndAPersonWhoIsAssignedFromVisitDetailsPage()
			throws InterruptedException {

		reportLog(
				"1.1:	As logged in user from PR#1 go to the Study Dashboard > Visit and open Visit details for Assigned person");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		studyNavigatorDashBoardPage.verifyVisitListIsOpened();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitName, subjectName);

		reportLog("1.2:	Visit Details screen is opened successfully.");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("2.1:Open ClinRo form from PR#3");
		assessmentDetailPage=visitDetaiLPage.clickOnAssignedRaterThumbnailImage();
		
		reportLog("2.2:Form thumbnail is displayed with 'Pending' label. ");
		assessmentDetailPage.verifyPdfStatusTextPresent(Constants.Pending_Status);
		
		reportLog("2.3:Checkbox with Mark as 'Not Administered' label is displayed and editable. ");
		assessmentDetailPage.verifyCheckBoxIsPresent();
		
		reportLog("2.4:'Started', 'Duration' fields are empty.");
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_StartedField, "");
		assessmentDetailPage.verifyAssessmentDetails(Constants.AssesmentDetail_DurationField, "");
			
		reportLog("3.1:Navigate to associated Visit Details page using 'Visit' link in assessment details section");
		visitDetaiLPage = assessmentDetailPage.clickOnVisitLink();
		
		reportLog("3.2:Visit Details page is opened");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("3.3: 'Assigned' assessment is displayed with form thumbnail.");
		visitDetaiLPage.verifyAssignedRaterNameNextToThumbnail(Constants.ATAssignedRater_10);
		visitDetaiLPage.VerifyFormThumbnailImageBeforeNotAdminsiteredIsPresent();
		
		reportLog("4.1:Press the form thumbnail");
		assessmentDetailPage=visitDetaiLPage.clickOnAssignedRaterThumbnailImage();
		
		reportLog("4.2:Assessment details page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("5.1:Set checkbox to Mark as 'Not Administered'");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		
		reportLog("5.2:Checkbox is set ");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		
		reportLog("5.4:Confirmation control appears");
		assessmentDetailPage.conifrmButtonAppears();
		
		reportLog("6.1:Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();
		
		reportLog(
				"6.2:	The text of confirmation message for unassigned assessment is the following: Please ensure that this assessment is being marked as Not Administered because Site confirmed: ");
		assessmentDetailPage.verifyNotAdministeredConfirmationPopUpPresentWithWarningParagraph();

		reportLog("6.3: No Paper source exists ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(
				Constants.AssesmentDetail_ConfirmationPopUPNoPaperSource_Warning);

		reportLog("6.4: It was not started offline on a tablet ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(
				Constants.AssesmentDetail_ConfirmationPopUPOfflineTablet_Warning);

		reportLog("6.5: Yes and No controls are displayed");
		assessmentDetailPage.verifyNotAdministeredControlsPresent();

		reportLog("7.1:Press Yes control for confirmation");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
		
		reportLog("7.2:	Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("8.1:Select the reason for change - Enter username and password - Select the confirmation control");
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);
		
		reportLog("8.2:Assessment receives Complete status. ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("9.1:Navigate to associated Subject details page using 'Subject' link in assessment details section");
		subjectDetailPage = assessmentDetailPage.clickOnSubjectLink();
		
		reportLog("9.2:Subject Details page is opened.");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("9.3:'Not administered' and submitted assessment is displayed with 'Not administered' form thumbnail. ");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.verifyAfterSubmissionNotAdministeredThumbnailImage();
		
		reportLog("9.4:Appropriate submission information with 'Not administered' label is displayed.");
		subjectDetailPage.verifyNotAdministeredLabelPresent();
		assessmentDetailPage = subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();
		
		reportLog("10.1:Navigate to associated Visit Details page  via Assessment page");
		visitDetaiLPage = assessmentDetailPage.clickOnVisitLink();
		
		reportLog("10.2:Visit Details page is opened.");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("10.3:'Not administered' and submitted assessment is displayed with 'Not administered' form thumbnail");
		visitDetaiLPage.verifyNotAdministeredFormThumbnailImageIsPresent();
		
		reportLog("10.4:Appropriate submission information with 'Not administered' label is displayed.");
		visitDetaiLPage.verifyNotAdministeredLabelPresent();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
