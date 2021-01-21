package net.medavante.regression.testscripts.studynavigator;

import java.text.ParseException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1174_TrackChangesToCompAssesDurationDateTimer_SIP
		extends BaseTest {

	private String studyName2, subjectNameWithIndependentRating, subjectWithNonIrVisitName,
			visitNonIrCompletedPaperTranscription, visitIrCompleted, startedTime = "10:30",
			 durationPreviousTime;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1174_TrackChangesToCompAssesDurationDateTimer_SIP(
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
		subjectWithNonIrVisitName = properties.getProperty("SubjectNameNonIrCompletedPaperTranscription");
		visitIrCompleted = properties.getProperty("Visit1678IRCompleted");
		visitNonIrCompletedPaperTranscription = properties.getProperty("VisitPaperCompleted_1");

	}   

	/**
	 * =======================================================================================================================================
	 * Test Case Id: FP-TC-1174 Test Case Name: In Portal - track changes to a
	 * completed assessment duration and datetime (Paper Transcription)
	 * 
	 * ========================================================================================================================================
	 * 
	 * @throws ParseException
	 * 
	 */
	@Test(description = "FP-TC-1174_InPortalTrackChangesToACompletedAssessmentDurationAndDateTimerPaperTranscription", groups = {
			"" })

	public void FPTC_1174_VerifyInPortalTrackChangesToACompletedAssessmentDurationAndDateTimerPaperTranscription()
			throws ParseException {

		/*-----------------Login With User Having Claim To Manage IR Assessments-----------------------*/

		reportLog("1.1:	Log in to the portal as the User from Pr.#3");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User successful logged in");
		dashBoardPage.verifyMedavantePortalPage();

		/*-----------------Steps For IR Completed Paper Transcription Assessment-----------------------*/

		reportLog("2.1:Go to the assessment details page of the assessment of Pr.#5.1");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName2,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitIrCompleted, subjectNameWithIndependentRating);

		reportLog("2.2:Selected assessment details page is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.getStartedDateTime();
		durationPreviousTime = assessmentDetailPage.getDurationTime();

		reportLog("2.3:Option to edit the assessment is available");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("3.1:	Select the option to edit");
		assessmentDetailPage.clickOnEditButton();

		reportLog("3.2:Assessment info appear as editable");
		assessmentDetailPage.verifyFieldsAreEditableForCompletedAssesment();

		reportLog("4.1:	Update 'Started' and 'Duration' info and select to save");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");
		assessmentDetailPage.updateDurationTime();

		reportLog("3.3: Option to save is available");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndEnabled();
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("4.2:	The  reason for change pop-up appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("5.1:Verify the available reasons to choose from,Reasons include:Data Entry Error,Others");
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.changeofreason);

		reportLog("6.1:	Select 'Data Entry Error' reason and proceed to save by e-signing");
		assessmentDetailPage.selectReasonForChangeOption(Constants.changeofreason.get(0));
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("6.2:	Subject is updated with the new values of Step#4");
		assessmentDetailPage.verifyDurationTimeUpdated(durationPreviousTime);
		loginPage.logoutApplication();

		/*-------------- Login With User Having Claim Can Manage Assessment--------------------------------*/

		reportLog("7.1:	Repeat Step#1 to Step#6 for the User from Pr.#4 and assessment from Pr.#6");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_S_STS, visitNonIrCompletedPaperTranscription,
		subjectWithNonIrVisitName);

		/*------Repeating The Steps For Non IR Completed PaperTranscription Assessment----------------------*/

		reportLog("7.2:	Same as Step#1 to Step#6");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.getStartedDateTime();
		durationPreviousTime = assessmentDetailPage.getDurationTime();

		reportLog("7.3:Option to edit the assessment is available");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("7.4:	Select the option to edit");
		assessmentDetailPage.clickOnEditButton();

		reportLog("7.5:Assessment info appear as editable");
		assessmentDetailPage.verifyFieldsAreEditableForCompletedAssesment();

		reportLog("7.6:	Update 'Started' and 'Duration' info and select to save");
		assessmentDetailPage.updateStartedDate();
		assessmentDetailPage.setStartedTime(startedTime, "AM");
		assessmentDetailPage.updateDurationTime();

		reportLog("7.7: Option to save is available");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndEnabled();
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("7.8:	The  reason for change pop-up appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("7.9:Verify the available reasons to choose from,Reasons include:Data Entry Error,Others");
		assessmentDetailPage.verifyReasonForChangeOptionsPresentInDropDown(Constants.reasonChangeForAssesment);

		reportLog("7.10:	Select 'Data Entry Error' reason and proceed to save by e-signing");
		assessmentDetailPage.selectReasonForChangeOption(Constants.reasonChangeForAssesment.get(0));
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinator, AT_Password);

		reportLog("7.11:	Subject is updated with the new values of Step#4");
		assessmentDetailPage.verifyDurationTimeUpdated(durationPreviousTime);
		loginPage.logoutApplication();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
