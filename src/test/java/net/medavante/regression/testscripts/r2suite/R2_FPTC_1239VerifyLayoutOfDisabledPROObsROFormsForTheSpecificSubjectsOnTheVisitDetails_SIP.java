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

public class R2_FPTC_1239VerifyLayoutOfDisabledPROObsROFormsForTheSpecificSubjectsOnTheVisitDetails_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1239VerifyLayoutOfDisabledPROObsROFormsForTheSpecificSubjectsOnTheVisitDetails_SIP(String browser) {
		super(browser);
	}

	private String visitName1, studyName;

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

		reportLog("1.3: Navigate to Study Navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("1.5: Click on add subject button to create new subject to configure the test prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		
		reportLog("1.6: Input "+screeningNum+" Screening num to configure the test prerequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);
		
		reportLog("1.7: Select "+studyLanguage+" language from the configured study language options to configure the test prerequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		
		reportLog("1.8: Click on save button to save the subject to configure the test prerequiste");
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN(); 
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("1.9: Verify " + visitName1 + " visit is displayed");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visitName1);

		reportLog("1.10: Click on " + visitName1 + " to verify Assessment List for selected visit is displayed with the all forms, including PRO and ObsRO forms");
		subjectDetailPage.clickOnVisitRow(visitName1);
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1239 Test Case Name: Layout of disabled PRO/ObsRO forms for the specific Subjects on the Visit Details  
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1239_Verify Layout of disabled PRO/ObsRO forms for the specific Subjects on the Visit Details", groups = {
			"R2" })
	public void FPTC_1239_VerifyLayoutOfDisabledPROObsROFormsForTheSpecificSubjectsOnTheVisitDetails() {
		
		reportLog("1.11:  Verify Assessment List for selected visit is displayed with the forms, including ObsRO forms");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ObsRo_Form_Label);
		
		reportLog("1.12: Verify Assessment List for selected visit is displayed with the forms, including Pro forms");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.Pro_Form_Label);
		
		reportLog("2.1: Click on reported outcome button to configure the test by disable Participant and Observer Site-based PRO settings");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("2.2: select disabled to configure the test by disable Participant Site-based PRO settings");
		subjectDetailPage.selectDisabledSiteBasedProParticipant();
		
		reportLog("2.3: Enter reason to disable Participant Site-based PRO settings");
		subjectDetailPage.inputDisabledSiteBasedProParticipantReason("Auto"+generateRandomString(5));
		
		reportLog("2.4: select disabled to configure the test by disable Observer Site-based PRO settings");
		subjectDetailPage.selectDisabledSiteBasedProObserver();
		
		reportLog("2.5: Enter reason for disable Observer Site-based PRO settings");
		subjectDetailPage.inputDisabledSiteBasedProObserverReason("Auto"+generateRandomString(5));
		
		reportLog("2.6: Click on save button to configure the test by disable Participant and Observer Site-based PRO settings");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		
		reportLog("2.7: Select "+ Constants.Subject_Reason_For_Change + " as reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		
		reportLog("2.8: Esign and submit the same");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW); 
		
		reportLog("2.9: Click on " + visitName1 + " to verify PRO and ObsRO forms are not listed within the Assesment List of the selected visit");
		subjectDetailPage.clickOnVisitRow(visitName1);
		
		reportLog("2.10: Verify Assessment List for selected visit not listed obsro form");
		subjectDetailPage.verifyScaleTypeNotConfigured(Constants.ObsRo_Form_Label);
		
		reportLog("2.11: Verify Assessment List for selected visit not listed Pro form");
		subjectDetailPage.verifyScaleTypeNotConfigured(Constants.Pro_Form_Label);
		
		reportLog("3.1: User logout from the application");
		loginPage.logoutApplication(); 
		
		reportLog("3.2: Verify User logout from the application");
		loginPage.verifyUserLogout();
	}
}
