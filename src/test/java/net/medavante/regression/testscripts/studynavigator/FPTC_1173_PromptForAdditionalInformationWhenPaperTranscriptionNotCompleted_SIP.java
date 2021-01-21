package net.medavante.regression.testscripts.studynavigator;

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

public class FPTC_1173_PromptForAdditionalInformationWhenPaperTranscriptionNotCompleted_SIP
		extends BaseTest {

	private String studyName2, subjectNameWithIndependentRating,
			subjectWithNonIrVisitName = "Auto1678Subject" + generateRandomAlphanumericString(3), visitIrCompleted,
			visitNonIrWithThreeForms;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1173_PromptForAdditionalInformationWhenPaperTranscriptionNotCompleted_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Test741Study");
		studyName2 = properties.getProperty("StudyName2645");
		subjectNameWithIndependentRating = properties.getProperty("Subject1678IndepedentRating");
		visitIrCompleted = properties.getProperty("Visit1678IRCompleted");
		visitNonIrWithThreeForms = properties.getProperty("Visit2146NotCompletedVisit");

		/*-----------------Creating Subject For NonIr Visit Configuring Pre-Requisite---------------*/
	
		reportLog("1:Login To Application And Setting Up The Pre-Requisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.1: Verify medavante portal page");
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10 ,subjectWithNonIrVisitName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		//------------------Subject Created Successfully--------------------------------------

		//------------------Configure the Pending Clinro,Pro,Obsro Forms For Visit ------------

		subjectDetailPage.clickOnVisitRow(visitNonIrWithThreeForms);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDownForMultipleConfiguredFormType(Constants.ClinRO_Form_Label,Constants.ATAssignedRater_10);
		subjectDetailPage.navigateBack();

		}

	/**
	 * =======================================================================================================================================
	 * Test Case Id: FP-TC-1173 Test Case Name: In Portal - prompt for
	 * additional information when 'Paper Transcription'/'Not Completed'/'Not
	 * Administered' is selected
	 * 
	 * ========================================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1173_InPortalPromptForAdditionalInformationWhenPaperTranscriptionNotCompletedNotAdministeredIsSelected", groups = {
			"" })
	public void FPTC_1173_VerifyInPortalPromptForAdditionalInformationWhenPaperTranscriptionNotCompletedNotAdministeredIsSelected() {

		/*----------------Login With The User Having Claim Can Do Paper Transcription---------------------*/


		reportLog("1.3:Go to Study Navigator -> Assessment details screen of assessment from Pr#7");
		studyNavigatorDashBoardPage.selectStudy(studyName2,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType,visitIrCompleted, subjectNameWithIndependentRating);

		reportLog("1.4:Assessment details screen is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.1:	Mark flag 'Paper Transcription' and confirm the update");
		assessmentDetailPage.clickOnPaperTrnscriptionCheckBox();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("2.2:The Reason For Change pop-up appears with Reason's List:");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.ReasonsForChangePaperTranscription);

		reportLog("3.1:	Select the reason and proceed to save by e-signing");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonsForChangePaperTranscription.get(2));
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("3.2:Assessment detail screen is updated with set flag value");
		assessmentDetailPage.verifyPaperTranscriptionCheckBoxFlagSet();

		reportLog("4.1:	Unmark flag 'Paper Transcription' and confirm the update");
		assessmentDetailPage.unmarkFlagPaperTranscription();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("4.2:The Reason For Change pop-up appears with Reason's List:");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage
				.verifyReasonForChangeOptionsPresentInDropDown(Constants.ReasonsForChangePaperTranscription);

		reportLog("5.1:Select the reason and proceed to save by e-signing");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonsForChangePaperTranscription.get(2));
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("5.2:	Assessment detail screen is updated with not set flag value");
		assessmentDetailPage.verifyPaperTranscriptionCheckBoxFlagNotSet();

		reportLog("6.1:Log out from the Portal. ");
		loginPage.logoutApplication();

		reportLog("6.2:User from Pr#4 is successfully logged out ");
		loginPage.verifyUserLogout();

		/*----------------Login With The User Having Claim  Manage Completed Assessment Flags-----------------------------------------*/

		reportLog("6.3:Log in to the Portal as User from Pr#3. ");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("6.4:User from Pr#3 is successfully logged in ");
		dashBoardPage.verifyMedavantePortalPage();

		/*------------------Performing Steps For ClinRO assessment in "Pending" status and not marked for "Not Administered"-----------*/
		reportLog("6.5:Go to Study Navigator -> Visit details screen of Visit from Pr#6");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitNonIrWithThreeForms,
				subjectWithNonIrVisitName);

		reportLog("6.6:Visit details screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		visitDetaiLPage.navigateBack();

		reportLog("7.1:	Mark assessment from Pr#6.1 as 'Not Administered' and confirm the update");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitNonIrWithThreeForms, subjectWithNonIrVisitName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();
	
		reportLog("7.2:	The Reason For Change pop-up appears with Reason's List:");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.ReasonChangeNotAdministered);

		reportLog("8.1:	Select the reason and proceed to save by e-signing");
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(2));
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("8.2:	Assessment detail screen is updated with set flag value");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.verifyPdfStatusTextAndClosedThumbnailImageIsDisplayed(Constants.Complete_Status);
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();

		reportLog("9.1: Unmark flag 'Not Administered' and confirm the update");
		assessmentDetailPage.uncheckNotAdministeredCheckBox();
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("9.2:	The Reason For Change pop-up appears with Reason's List:");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.ReasonChangeNotAdministered);

		reportLog("10.1:Select the reason and proceed to save by e-signing");
		assessmentDetailPage.selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(2));
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("10.2:Assessment detail screen is updated with not set flag value");
		assessmentDetailPage.verifyPaperTranscriptionCheckBoxFlagNotSet();
		assessmentDetailPage.closeOnFailedassessment();
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
		/*------------------Performing Steps For PRO assessment in "Pending" status and not marked for "Not Complete"/"Paper Transcription"-----------*/

		reportLog("11.1 :Go to Study Navigator -> Visit details screen of visit from Pr#6.2");
	
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToVisitsListing();
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitNonIrWithThreeForms,
				subjectWithNonIrVisitName);

		reportLog("11.2:Visit details screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		visitDetaiLPage.navigateBack();

		reportLog("12.1:Mark assessment from Pr#6.2 as 'Not Completed' and confirm the update");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_MMSEFormName, visitNonIrWithThreeForms, subjectWithNonIrVisitName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("12.2:The Reason For Change pop-up appears with Reason's List:");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeValuesList);

		reportLog("13.1:Select the reason and proceed to save by e-signing");
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeValuesList.get(1));
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("13.2:Assessment detail screen is updated with set flag value");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.verifyPdfStatusTextAndClosedThumbnailImageIsDisplayed(Constants.Complete_Status);

		/*------------------Performing Steps For ObsRO assessment in "Pending" status and not marked for "Not Complete"/"Paper Transcription"-----------*/

		reportLog("14.1:Repeat Step#11 to Step#13 for Assessment from Pr#6.3");
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_CdrFormName, visitNonIrWithThreeForms, subjectWithNonIrVisitName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("14.2:Same as Step#11 to Step#13");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeValuesList);
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeValuesList.get(1));
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.verifyPdfStatusTextAndClosedThumbnailImageIsDisplayed(Constants.Complete_Status);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
