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

public class R2_FPTC_1236VerifyReportedOutcomesDetailsSitebasedPROEnableParticipantObserver_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1236VerifyReportedOutcomesDetailsSitebasedPROEnableParticipantObserver_SIP(String browser) {
		super(browser);
	}

	private String visitName1, studyName,site_based_PRO_Participant_INPTXT="Auto"+generateRandomString(2),site_based_PRO_Observer_INPTXT="Auto"+generateRandomString(2); 

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName1 = properties.getProperty("ObsroAndProConfiguredVisit");
		
		reportLog("1.1  Login to the portal as the user exists having claim canManageAssessments i.e. with " + SuperAdminUN);
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2: Verify User Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to Study Navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("2.2: Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);
	
		reportLog("1.4: Click on add subject button to create new subject to configure the test prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		
		reportLog("1.5: Input "+screeningNum+" Screening num to configure the test prerequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);
		
		reportLog("1.6: Select "+studyLanguage+" language from the configured study language options to configure the test prerequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		
		reportLog("2.7: Click on save button to configure the test prerequiste and verify subject is created");
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("2.8: Open Reported Outcomes Details screen");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("2.9: select disabled to configure the test and disabled Participant Site-based PRO settings");
		subjectDetailPage.selectDisabledSiteBasedProParticipant();
		
		reportLog("2.10: Enter reason for disabled Participant Site-based PRO settings");
		subjectDetailPage.inputDisabledSiteBasedProParticipantReason(site_based_PRO_Participant_INPTXT);
		
		reportLog("2.11: select disabled to configure the test and disabled Observer Site-based PRO settings");
		subjectDetailPage.selectDisabledSiteBasedProObserver();
		
		reportLog("2.12: Enter reason for disabled Observer Site-based PRO settings");
		subjectDetailPage.inputDisabledSiteBasedProObserverReason(site_based_PRO_Observer_INPTXT);
		
		reportLog("2.12: Click on save button to configure the test and disabled Participant and Observer Site-based PRO settings");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		
		reportLog("2.13: Select "+ Constants.Subject_Reason_For_Change + " as reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		
		reportLog("2.14: Esign and submit the same");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW); 
		
	}
	
	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1236 Test Case Name:Reported Outcomes Details: Site-based PRO - Enable Participant/Observer 
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1236_Verify Reported Outcomes Details: Site-based PRO - Enable Participant/Observer ", groups = {
			"R2" })
	public void FPTC_1236_verifyReportedOutcomesDetailsSitebasedPROEnableParticipantObserver() {
		
		reportLog("2.15: Open Reported Outcomes Details screen");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("2.16: Verify Reported Outcomes Details screen is opened ");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();
		
		reportLog("2.17: Verify Participant and Observer settings are available for enabling");
		subjectDetailPage.verifyParticipantAndObserverSettingsAreAvailable();
		
		reportLog("2.18: Verify  Reason for disabling Participant and Reason for disabling Observer are editable.");
		subjectDetailPage.verifyReasonForDisablingParticipantAndObserverInputFieldAreEditable();
		
		reportLog("3.1: Select Enable Participant in corresponding drop-downs");
		subjectDetailPage.selectEnableSiteBasedProParticipant();
		
		reportLog("3.2: Select Enable Observer in corresponding drop-downs");
		subjectDetailPage.selectEnableSiteBasedProObserver();
		
		reportLog("3.3: Verify Fields Reason for disabling Participant and Reason for disabling Observer disappear");
		subjectDetailPage.verifyReasonForDisablingParticipantAndObserverInputFieldIsNotDisplayed();
		
		reportLog("4.1: Select Disable Observer in corresponding drop-downs again within this editing session");
		subjectDetailPage.selectDisabledSiteBasedProParticipant();
		
		reportLog("4.2: Select Disable Observer in corresponding drop-downs again within this editing session");
		subjectDetailPage.selectDisabledSiteBasedProObserver();
		
		reportLog("4.3: Verify Fields Reason for disabling Participant appear with previously entered text.");
		subjectDetailPage.verifyDisabledReasonTextForSiteBasedPROParticipant(site_based_PRO_Participant_INPTXT);
		
		reportLog("4.4: Verify Fields Reason for disabling Observer appear with previously entered text.");
		subjectDetailPage.verifyDisabledReasonTextForSiteBasedPROObserver(site_based_PRO_Observer_INPTXT);
		
		reportLog("5.1: Select Enable Participant in corresponding drop-downs ");
		subjectDetailPage.selectEnableSiteBasedProParticipant();
		
		reportLog("5.2: Select Enable Observer in corresponding drop-downs ");
		subjectDetailPage.selectEnableSiteBasedProObserver();
		
		reportLog("5.3: Select an action to cancel changes");
		subjectDetailPage.closeReportedOutComePopup();
		
		reportLog("5.4: Verify Reported Outcomes details screen is closed ");
		subjectDetailPage.verifyReportedOutComeScreenIsNotDisplayed();
		
		reportLog("5.5: Verify Changes are not saved");
		subjectDetailPage.verifySiteBasedDisabledParticipantAndObserversTextInRed();
		
		reportLog("6.1: Select the "+visitName1 +" Visit identified in prerequiste");
		subjectDetailPage.clickOnVisitRow(visitName1);
		
		reportLog("6.2: Verify Assessment List for selected visit is displayed without PRO.");
		subjectDetailPage.verifyScaleTypeNotConfigured(Constants.Pro_Form_Label);
		
		reportLog("6.3: Verify Assessment List for selected visit is displayed without ObsRO forms.");
		subjectDetailPage.verifyScaleTypeNotConfigured(Constants.ObsRo_Form_Label);
		
		reportLog("7.1: Open Reported Outcomes Details screen ");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("7.2: Select Enable Participant in corresponding drop-downs ");
		subjectDetailPage.selectEnableSiteBasedProParticipant();
		
		reportLog("7.3: Select Enable Observer in corresponding drop-downs ");
		subjectDetailPage.selectEnableSiteBasedProObserver();
		
		reportLog("7.4: Save changes");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		
		reportLog("7.5: Select "+ Constants.Subject_Reason_For_Change + " as reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		
		reportLog("7.6: Esign and submit the same");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW); 

		reportLog("7.7: Verify Subject Details page is displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("7.8: Verify Participant and Observer values are not highlighted in red in Reported Outcomes section");
		subjectDetailPage.verifySiteBasedDisabledParticipantAndObserversTextIsNotRed();
		
		reportLog("7.9: Click on reported outcome button to configure Visit with PRO and ObsRO forms where a Visit is not scheduled");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("7.10: Select disable Site Based Pro Participant to configure Visit without PRO and ObsRO forms.");
		subjectDetailPage.selectDisabledSiteBasedProParticipant();
		
		reportLog("7.11: enter reason for disable Site Based Pro Participant to configure Visit without PRO and ObsRO forms.");
		subjectDetailPage.inputDisabledSiteBasedProParticipantReason(generateRandomString(4));
		
		reportLog("7.12: Select disable Site Based Pro Observer to configure Visit without PRO and ObsRO forms");
		subjectDetailPage.selectDisabledSiteBasedProObserver();
		
		reportLog("7.13: enter reason for disable Site Based Pro Observer to configure Visit without PRO and ObsRO forms.");
		subjectDetailPage.inputDisabledSiteBasedProObserverReason(generateRandomString(4));
		
		reportLog("7.14: click on save button to configure Visit with PRO and ObsRO forms where a Visit is not scheduled");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		
		reportLog("7.15: Select "+ Constants.Subject_Reason_For_Change + " as reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		
		reportLog("7.16: Esign and submit the same");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW); 
		
		reportLog("8.1: Select the Visit identified in Pr#2.2.1");
		subjectDetailPage.clickOnVisitRow(visitName1);
		
		reportLog("8.2: Verify Assessment List for selected visit is displayed without PRO forms.");
		subjectDetailPage.verifyScaleTypeNotConfigured(Constants.Pro_Form_Label);
		
		reportLog("8.3: Verify Assessment List for selected visit is displayed without ObsRO forms.");
		subjectDetailPage.verifyScaleTypeNotConfigured(Constants.ObsRo_Form_Label);
		
		reportLog("8.4: Click on reported outcome button to configure Visit with PRO and ObsRO forms where a Visit is not scheduled");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("8.5: Select enable Site Based Pro Participant to configure Visit with PRO and ObsRO forms where a Visit is not scheduled");
		subjectDetailPage.selectEnableSiteBasedProParticipant();
		
		reportLog("8.6: Select enable Site Based Pro Observer to configure Visit with PRO and ObsRO forms where a Visit is not scheduled");
		subjectDetailPage.selectEnableSiteBasedProObserver();
		
		reportLog("8.7: click on save button to configure Visit with PRO and ObsRO forms where a Visit is not scheduled");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		
		reportLog("8.8: Select "+ Constants.Subject_Reason_For_Change + " as reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		
		reportLog("8.9: Esign and submit the same");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW); 
		
		reportLog("9.1: Select an action to add the Visit");
		subjectDetailPage.clickOnAddVisitIcon(); 
		
		reportLog("9.2: Verify New visit is added");
		subjectDetailPage.verifyVisitStatus(visitName1, Constants.Pending_Status);
		
		reportLog("9.3: Verify Assessment List for the selected Visit is displayed with the all forms, including PRO forms");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);
		
		reportLog("9.4: Verify Assessment List for the selected Visit is displayed with the all forms, including ObsRO forms");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);
		
		reportLog("10.1: Logout the user");
		loginPage.logoutApplication();

		reportLog("10.2: Verify user Logout");
		loginPage.verifyUserLogout();
	}

}
