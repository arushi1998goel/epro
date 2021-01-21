package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;

import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;

import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1235ReportedOutcomesDetailsSiteBasedPRODisableParticipantObserve_SIP extends BaseTest {

	protected String  observerRelation1,
			observerAlias = "Alias" + generateRandomString(3), textToBeEnter = "Auto873" + generateRandomString(2);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1235ReportedOutcomesDetailsSiteBasedPRODisableParticipantObserve_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		observerRelation1 = properties.getProperty("Auto_Observer_Relation1");		
		visitName1 = properties.getProperty("ObserverAndProVisit");	
		visitName2 = properties.getProperty("Auto_ProObsroVisit2");
		
		//Creating subject 
		reportLog("Creating Subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName1);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1235 Test Case Name:Reported Outcomes Details:
	 * Site-based PRO - Disable Participant/Observer
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1235_ VerifyReportedOutcomesDetailsSiteBasedPRODisableParticipantObserve", groups = {
			"R2" })
	public void FPTC_1235_VerifyReportedOutcomesDetailsSiteBasedPRODisableParticipantObserve() {

		reportLog(
				"1.1  Login to the portal as the user exists having claim canManageAssessments i.e. with " + AT_PRODSiteCoordinatorCRSelfSchedule);
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.2: Verify User Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to Study Navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("2.1: Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
	
		reportLog("2.2: Select " + screeningNum + " subject from subject list");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		reportLog("2.3: Verify " + visitName1 + " visit is displayed");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visitName1);

		reportLog("2.4: Click on " + visitName1
				+ " to verify Assessment List for selected visit is displayed with the all forms, including PRO and ObsRO forms");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog("2.5: Open Reported Outcomes Details screen");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("2.6:Reported Outcomes Details screen is opened");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("2.7:Drop-downs for Disable Participant and Disable Observer are available");
		subjectDetailPage.verifyParticipantAndObserverSettingsAreAvailable();

		reportLog("3:Select Disable Participant and Disable Observer in corresponding drop-downs in Site-based PRO section");
		subjectDetailPage.selectDisabledSiteBasedProParticipant();
		subjectDetailPage.selectDisabledSiteBasedProObserver();

		reportLog(
				"3.1:Fields Reason for disabling Participant and Reason for disabling Observer appear and are highlighted");
		subjectDetailPage.verifyDisabledParticipantAndObserverSiteBasedInputReasonFieldAppearAndHighlighted();

		reportLog(
				"4:Attempt to select an action to Save without filling the fields Reason for disabling Participant and Reason for disabling Observer and Save is disabled");
		subjectDetailPage.verifyReportedOutcomeSaveButtonIsDisabled();

		reportLog(
				"5:Fill the fields Reason for disabling Participant and Reason for disabling Observer and select an action to cancel");
		subjectDetailPage.inputDisabledSiteBasedProParticipantReason(textToBeEnter);
		subjectDetailPage.inputDisabledSiteBasedProObserverReason(textToBeEnter);
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("6.1:Changes are not saved - Reported Outcomes Details screen is closed");
		subjectDetailPage.verifyChangesSavedIconsAreDisplayedParticipantObserverDisabled();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("6.2:Open Reported Outcomes Details screen again ");
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.verifyReportedOutComeIsDisplayed();
		
		reportLog("Setting Up The Pre-Requisite");
		subjectDetailPage.selectDisabledSiteBasedProParticipant();
		subjectDetailPage.selectEnableSiteBasedProParticipant();
		subjectDetailPage.selectEnableSiteBasedProObserver();
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog(
				"6.3:Select Disable Participant and Disable Observer Fill the fields Reason for disabling Participant and Reason for disabling Observer");
		subjectDetailPage.selectDisabledSiteBasedProParticipant();
		subjectDetailPage.inputDisabledSiteBasedProParticipantReason(textToBeEnter);
		subjectDetailPage.selectDisabledSiteBasedProObserver();
		subjectDetailPage.inputDisabledSiteBasedProObserverReason(textToBeEnter);

		reportLog("6.4:Select an action to Save changes");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog(
				"6.5:Changes Saved and Reported Outcomes Details screen is closed and Subject Details page is displayed  ");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyChangesSavedIconsAreDisplayedParticipantObserverDisabled();

		reportLog("6.6:Participant and Observer values are highlighted in red in Reported Outcomes section ");
		subjectDetailPage.verifySiteBasedDisabledParticipantAndObserversTextInRed();
	
		reportLog("6.7:Icons are displayed that Participant and Observer were disabled");
		subjectDetailPage.verifyChangesSavedIconsAreDisplayedParticipantObserverDisabled();

		reportLog(
				"7:Hover cursor to Participant value and verify Tooltip is displayed - Deactivation date and reason from Step#6 are displayed in the tooltip");
		subjectDetailPage.hoverCursorToParticipantValueAndVerifyToolTipTextForParticipant(textToBeEnter);

		reportLog(
				"8:Hover cursor to Observer value and verify Tooltip is displayed - Deactivation date and reason from Step#6 are displayed in the tooltip");
		subjectDetailPage.hoverCursorToObserverValueAndVerifyToolTipTextForObserver(textToBeEnter);

		reportLog("9:Select the Visit identified in Pr#2.3");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog(
				"9.1:Assessment List for selected visit is displayed with the all forms, including PRO and ObsRO forms");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);

		reportLog("10.1:Select an action to add visit identified in Pr#2.4");
		subjectDetailPage.clickOnVisitRow(visitName2);

		reportLog("10.2: Assessment List for selected visit is displayed without PRO and ObsRO forms");
		subjectDetailPage.verifyScaleTypeNotConfigured(Constants.ObsRo_Form_Label);
		subjectDetailPage.verifyScaleTypeNotConfigured(Constants.Pro_Form_Label);

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
