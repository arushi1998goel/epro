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

public class FPTC_1483_NonCRClinROAssessAsNotAdministeredbyNotQualified_SIP extends BaseTest
{
	
	private String visitName, subjectName = "AutoSubject2206" + generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1483_NonCRClinROAssessAsNotAdministeredbyNotQualified_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1483 Test Case NameNon-CR ClinRO assessment as 'Not Administered
	 *  by Not Qualified person and a person who is NOT Assigned from Assessment Details page
	 * 
	 * =============================================================================================================================================
	 * 
	 * 
	 * 
	 */
	
	@Test(description = "FP-TC-1487_NonCRClinROAssessmentAsNotAdministeredForNotQualifiedPersonAndAPersonWhoIsNotAssignedFromAssessmentDetailsPage", groups = {})
	public void FPTC_1483_NonCRClinROAssessAsNotAdministeredbyNotQualified()
	{
		reportLog("1.1:Log in to the Portal as User from Pr#1");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);

		reportLog("1.2:	Login is successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog(
				"2.1:Go to the Study Dashboard > Assessment Listing screen and open the Assessment in Pending status for Assigned person");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitName, subjectName);

		reportLog("2.2:Assessment details page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.1:Set checkbox to Mark as Not Administered");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();

		reportLog("3.2:Checkbox is set. 'Confirm' control appears");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("4.1:	Press Confirm control");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("4.2:	Confirmation message window appears. ");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog(
				"4.3:	The text of confirmation message for unassigned assessment is the following: Please ensure that this assessment is being marked as Not Administered because Site confirmed: ");
		assessmentDetailPage.verifyNotAdministeredConfirmationPopUpPresentWithWarningParagraph();

		reportLog("4.4: No Paper source exists ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(
				Constants.AssesmentDetail_ConfirmationPopUPNoPaperSource_Warning);

		reportLog("4.5: It was not started offline on a tablet ");
		assessmentDetailPage.verifyConfirmationMessagePrsentWithText(
				Constants.AssesmentDetail_ConfirmationPopUPOfflineTablet_Warning);

		reportLog("4.6: Yes and No controls are displayed");
		assessmentDetailPage.verifyNotAdministeredControlsPresent();

		reportLog("5.1:Press Yes Control for confirmation");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("5.2:	Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("6.1:Select the reason for change -Enter username and password - Select the confirmation control");
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODProjectCoordinator, AT_Password);

		reportLog("6.2:Assessment receives 'Complete' status. ");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog(
				"7.1:Navigate to associated Subject details screen using 'Subject' link in assessment details section");
		subjectDetailPage = assessmentDetailPage.clickOnSubjectLink();

		reportLog("7.2:Subject Details screen is opened. ");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.VerifyFormThumbnailImageBeforeNotAdminsiteredIsPresent();

		reportLog("7.3Not administered and submitted assessment is displayed with 'Not administered' form thumbnail. ");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.verifyAfterSubmissionNotAdministeredThumbnailImage();

		reportLog("7.4:Appropriate submission information with 'Not administered' label are displayed.");
		subjectDetailPage.verifyNotAdministeredLabelPresent();
		assessmentDetailPage = subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();

		reportLog("8.1:Navigate to associated Visit Details screen");
		visitDetaiLPage = assessmentDetailPage.clickOnVisitLink();

		reportLog("8.2:Visit Details screen is opened.");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog(
				"8.3: Not administered and submitted assessment is displayed with 'Not administered' form thumbnail. ");
		visitDetaiLPage.verifyNotAdministeredFormThumbnailImageIsPresent();

		reportLog("8.4:Appropriate submission information with Not administered label are displayed.");
		visitDetaiLPage.verifyNotAdministeredLabelPresent();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
	}
}
