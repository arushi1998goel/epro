package net.medavante.regression.testscripts.r2suite;

import java.util.Arrays;

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

public class R2_FPTC_1446VerifyObserverListControlsAndAttributes_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1446VerifyObserverListControlsAndAttributes_SIP(String browser) {
		super(browser);
	}

	private String  studyName,nonObserverStudy, visitName1, visitName2, notCompletedAndNotAssignedObserver,
			notCompletedButAssignedObserver, completedButNotAssignedObserver;
	private String startedTime = "10:30", durationTime = "14:30";
	private String observerAlias = "Auto" + generateRandomString(3);

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties testData = Configuration.readTestData("RegressionTestData");
		studyName = testData.getProperty("AutomationStudyName");
		nonObserverStudy = testData.getProperty("AutomationNonObserverStudy");
		visitName1 = testData.getProperty("Auto_Paper_OBSRO_Visit1");
		visitName2 = testData.getProperty("Auto_Paper_OBSRO_Visit2");
		notCompletedAndNotAssignedObserver = testData.getProperty("Auto_Observer_Relation3");
		notCompletedButAssignedObserver = testData.getProperty("Auto_Observer_Relation2");
		completedButNotAssignedObserver = testData.getProperty("Auto_Observer_Relation1");

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODInvestigatorRater, AT_Password);
		
		reportLog("1.2: Verify user login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to study navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		//studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);

		reportLog("1.5: Click on add subject button to create new subject to configure the test prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);

		reportLog("1.6: Input " + screeningNum + " screening number to configure the test prerequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("1.7: Select " + studyLanguage
				+ " language from the configured study language options to configure the test prerequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("1.8: Click on save button to configure the test prerequiste and verify subject is created");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("1.9: Click on reported outcome button to add " + completedButNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("1.10: Click on add observer button to add " + completedButNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnAddObserverBtN();

		reportLog(
				"1.11: Enter " + completedButNotAssignedObserver + " in relation field to configure test pre requiste");
		subjectDetailPage.inputObserverRelationName(completedButNotAssignedObserver);

		reportLog("1.13: Enter " + observerAlias + " in alias field to to add " + completedButNotAssignedObserver
				+ " observer configure test pre requiste");
		subjectDetailPage.inputObserverAliasName(observerAlias);

		reportLog("1.13: Click on save button to to add " + completedButNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnObserverSaveBTN();

		reportLog("1.14: Click on add observer button to add " + notCompletedButAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnAddObserverBtN();

		reportLog(
				"1.15: Enter " + notCompletedButAssignedObserver + " in relation field to configure test pre requiste");
		subjectDetailPage.inputObserverRelationName(notCompletedButAssignedObserver);

		reportLog("1.16: Enter " + observerAlias + " in alias field to add " + notCompletedButAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.inputObserverAliasName(observerAlias);

		reportLog("1.17: Click on save button to add " + notCompletedButAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnObserverSaveBTN();

		reportLog("1.18: Click on add observer button to add " + notCompletedAndNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnAddObserverBtN();

		reportLog("1.19: Enter " + notCompletedAndNotAssignedObserver
				+ " in relation field to configure test pre requiste");
		subjectDetailPage.inputObserverRelationName(notCompletedAndNotAssignedObserver);

		reportLog("1.20: Enter " + observerAlias + " in alias field to add " + notCompletedAndNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.inputObserverAliasName(observerAlias);

		reportLog("1.21: Click on save button to add " + notCompletedAndNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnObserverSaveBTN();

		reportLog("1.22: Click on save button of reported outcomes to add " + notCompletedAndNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("1.23: Click on " + visitName1 + " visit to configure the test case prerequiste");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog("1.24: Click on Add visit button to configure the test case prerequiste");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("1.25: Select the option Enter Paper Transcription");
		subjectDetailPage.clickOnEnterPaperTranscription();

		reportLog("1.26: Provide a " + Constants.Visit_Reason_For_Change + " reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Visit_Reason_For_Change);

		reportLog("1.27: complete e-sign and save");
		assessmentDetailPage = subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODInvestigatorRater, AT_Password);
		
		reportLog("1.28: Select " + completedButNotAssignedObserver + " rater to make save button enabled");
		assessmentDetailPage.selectRater(completedButNotAssignedObserver);

		reportLog("1.29: Select started date to make save button enabled");
		assessmentDetailPage.setStartedDate();

		reportLog("1.30: set " + startedTime + " started time to make save button enabled");
		assessmentDetailPage.setStartedTime(startedTime, "AM");

		reportLog("1.31: Select " + durationTime + " duration time to make save button enabled");
		assessmentDetailPage.setDurationTime(durationTime);

		reportLog("1.32: Click on source button to upload a file so that save button enabled");
		assessmentDetailPage.clickOnSourceDocumentBtn();

		reportLog("1.33: Upload a " + Constants.PDFFileToUpload
				+ " paper transcription file so that save button enabled");	

		assessmentDetailPage.uploadFile(Constants.PDFFileToUpload);

		reportLog("1.34: Select the option to save");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("1.35: Click on Refresh button to update the page");
		assessmentDetailPage.clickOnRefreshBtn();

		reportLog("1.36: Edit the assessment scores");
		assessmentDetailPage.clickOnScoreTabEditBTN();

		reportLog("1.37: Change the assessment scores");
		assessmentDetailPage.changeTheScore(Constants.Score_Number);
		
		reportLog("1.38: click on save button to modified the assessment scores");
		assessmentDetailPage.clickOnSourceTabSaveBTN();

		reportLog("1.39: Esign to modified the assessment scores");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODInvestigatorRater, AT_Password);
		
		reportLog("1.40: Navigate back to Subject Details page by click on subject link");
		assessmentDetailPage.clickOnSubjectLink();

		reportLog("1.41: Click on " + visitName2
				+ " to configure test prerequiste to set as Observer is not completed but assigned to at least one assessment");
		subjectDetailPage.clickOnVisitRow(visitName2);

		reportLog(
				"1.42: Click on add visit icon to configure test prerequiste to set as Observer is not completed but assigned to at least one assessment");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog(
				"1.43: Select the option for Paper Transcription to set as Observer is not completed but assigned to at least one assessment");
		subjectDetailPage.clickOnEnterPaperTranscription();

		reportLog("1.44: Provide a " + Constants.Visit_Reason_For_Change + " reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Visit_Reason_For_Change);

		reportLog(
				"1.45: E-sign for paper transcription to set as Observer is not completed but assigned to at least one assessment");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODInvestigatorRater, AT_Password);

		reportLog("1.46: Select " + notCompletedButAssignedObserver
				+ " observer to set as Observer is not completed but assigned to at least one assessment");
		assessmentDetailPage.selectRater(notCompletedButAssignedObserver);

		reportLog(
				"1.47: Select started date to set as Observer is not completed but assigned to at least one assessment");
		assessmentDetailPage.setStartedDate();

		reportLog("1.48: set " + startedTime
				+ " started time to set as Observer is not completed but assigned to at least one assessment");
		assessmentDetailPage.setStartedTime(startedTime, "AM");

		reportLog("1.49: set " + durationTime
				+ " duration time to set as Observer is not completed but assigned to at least one assessment");
		assessmentDetailPage.setDurationTime(durationTime);

		reportLog(
				"1.50: Click on source button to upload the paper file to set as Observer is not completed but assigned to at least one assessment");
		assessmentDetailPage.clickOnSourceDocumentBtn();

		reportLog("1.51: Select " + Constants.PDFFileToUpload
				+ " paper file and upload the same to set as Observer is not completed but assigned to at least one assessment");
		assessmentDetailPage.uploadFile(Constants.PDFFileToUpload);

		reportLog(
				"1.52: Select the option to save to set as Observer is not completed but assigned to at least one assessment");
		assessmentDetailPage.clickOnSaveBTN();
		assessmentDetailPage.clickOnRefreshBtn();

		reportLog("1.53: Navigate back to subject detail page and verify subject detail page is open");
		assessmentDetailPage.clickOnSubjectLink();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
	}

	/**
	 * =========================================================================
	 * Test Case Id: FPTC_1446 Test Case Name: Observer list - Controls and
	 * attributes
	 * =========================================================================
	 * 
	 */

	@Test(description = "FPTC_1446_Observer list - Controls and attributes", groups = { "R2" })
	public void FPTC_1446_VerifyObserverListControlsAndAttributes() {

		reportLog("1.54: Click on reported outcome button to Open Reported Outcome Details screen");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("1.55: Verify observer section contains Relation, Alias, Deactivated, Deactivated Reason option");
		subjectDetailPage.verifyAddObserverTableHeaderAttributes(Arrays.asList("RELATION", "ALIAS", "DEACTIVATED", "DEACTIVATED REASON"));

		reportLog("1.56: Verify Observers section with list of available Observers for the selected Subject is displayed");
		subjectDetailPage.verifyObserverGridIsDisplayedWithListOfObserver();

		reportLog("1.57: Verify Control to Add new Observer is available");
		subjectDetailPage.verifyAddObserverBtnIsDisplayed();

		reportLog("2.1: Select the Observer which is not completed and not assigned to any assessments i.e. "
				+ notCompletedAndNotAssignedObserver);
		subjectDetailPage
				.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(notCompletedAndNotAssignedObserver);

		reportLog("3.1: Select the Observer which is not completed but assigned to at least one assessment i.e. "
				+ notCompletedButAssignedObserver);
		subjectDetailPage
				.selectObserverRelationAndVerifyEditBTNIsDisplayedAndDeleteBTNIsHidden(notCompletedButAssignedObserver);

		reportLog("4.1: Select the Observer which is completed but not assigned to any assessment i.e. "
				+ completedButNotAssignedObserver);
		subjectDetailPage
				.selectObserverRelationAndVerifyEditBTNIsDisplayedAndDeleteBTNIsHidden(completedButNotAssignedObserver);

		reportLog("4.2: Click on cross icon of reported outcome popup to close the popup");
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("5.1: Navigate to home page");
		dashBoardPage=subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog(
				"5.2: Navigate to study dashboard to select the study which is configured without forms of ObsRO type ");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("5.3: Select study without configured forms of ObsRO type");
		studyNavigatorDashBoardPage.selectStudy(nonObserverStudy,AT_PRODSiteCoordinatorUserName);
		
		reportLog("5.4: Create Subject which is configured without forms of ObsRO type");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("5.5: Input " + screeningNum + " screening number");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("5.6: Select " + studyLanguage
				+ " language from the configured study language options");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("5.7: Click on save button verify subject is created");
		studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("5.8: Click on reported outcome button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("5.9: Verify Observers section is hidden");
		subjectDetailPage.verifyObserverGridIsHidden();

		reportLog("5.10: Click on cancel button to close the reported outcome popup");
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("5.11: Logout from application");
		loginPage.logoutApplication();

		reportLog("5.12: Verify User Logout from application");
		loginPage.verifyUserLogout();
	}

}
