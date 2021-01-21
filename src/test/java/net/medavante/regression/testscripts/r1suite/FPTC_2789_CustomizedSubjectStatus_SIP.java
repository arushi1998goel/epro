package net.medavante.regression.testscripts.r1suite;

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

public class FPTC_2789_CustomizedSubjectStatus_SIP extends BaseTest {
	
	private String studyName;
	private String subjectName="Subj_"+generateRandomString(5);
	protected String ClinROAss="Auto_Visit1";
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2789_CustomizedSubjectStatus_SIP(String browser) {
		super(browser);
				
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");	
		studyName = prop.getProperty("AutomationStudyName");
		
		reportLog("Create new subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10, subjectName);
	    subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
        subjectDetailPage.clickOnVisitRow(ClinROAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
	    loginPage.logoutApplication();
	    loginPage.verifyUserLogout();
		
		reportLog("Navigate to the assessment listing page");
		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);			
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		
		reportLog("Search the subjcet in assessment page");
		studyNavigatorDashBoardPage.searchSubject(subjectName);
		studyNavigatorDashBoardPage.searchVisit(ClinROAss);

		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.clickOnAssessmentFilterLink();
		assessmentDetailPage.markAsNotAdministred(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		loginPage.logoutApplication();
	    loginPage.verifyUserLogout();
	}

	/**
	 * ===================================================================================================
	 * Test Case Id : FP-TC-2789 Test Case Name :Customized Subject Status, Screen Failed - Manually set. 
	 * From Enrolled to Screen Failed 
	 * 
	 * ===================================================================================================
	 * 
	 * 
	 */

	@Test(description = " Customized Subject Status, “Screen Failed” - Manually set. From Enrolled to Screen Failed ", groups = { "R1" })
	public void FPTC_2789verifyCustomizedSubjectStatus_SIP() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to a Subject Detail screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		
		reportLog("2.2: Verify Study Navigator Dashboard Page is opened");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		reportLog("2.3 Select the enrolled subject");
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);		
		
		reportLog("3.1 Validate the status before making any change");	
		subjectDetailPage.verifyDetailStatus("Status",Constants.SubjectStatus_Enrolled);

		reportLog("3.2 Click on  subject editing icon");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		
		reportLog("3.3 Available Subject Status options displayed with a customized “Screen Failed” status");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();
		
		reportLog("4 Select a customized “Screen Failed” status and cancel changes");
		subjectDetailPage.selectStatus("Screen Failed");		
		subjectDetailPage.clickOnCancelBtn();
		
		reportLog("4.1 No changes applied for the Subject detail screen");
		subjectDetailPage.verifyDetailStatus("Status",Constants.SubjectStatus_Enrolled);		
		
		reportLog("5 Select a customized “Screen Failed” status and save");		
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();		
		
		subjectDetailPage.selectStatus("Screen Failed");		
		subjectDetailPage.clickOnSaveBtn();
		
		reportLog("5.1 A customized “Screen Failed” status is saved and displayed in Subject Details");
		subjectDetailPage.selectReasonForChangeOption("Subject status change");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		
		reportLog("5.2 Validate the applied changes");
		subjectDetailPage.verifyDetailStatus("Status","Screen Failed");
		
		reportLog("6. Navigate to a Subjects Listing screen");
		studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);	
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		//studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("Select subject");
		studyNavigatorDashBoardPage.clickOnSubject(subjectName);
				
		reportLog("6.1 A customized “Screen Failed” status is displayed for a Subject identified in the PR#3");
		subjectDetailPage.verifyDetailStatus("Status","Screen Failed");
		
		reportLog("7. Navigate to a Subject Detail screen");
		studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		
		reportLog("7.1 Identified in the PR #3");
		studyNavigatorDashBoardPage.clickOnSubject(subjectName);
		
		reportLog("7.2 change the 'Screen failed' status to previous 'Enrolled' and Save changes");
		subjectDetailPage.verifyDetailStatus("Status","Screen Failed");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();		
		subjectDetailPage.selectStatus(Constants.SubjectStatus_Enrolled);		
		subjectDetailPage.clickOnSaveBtn();
		subjectDetailPage.selectReasonForChangeOption("Subject status change");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		
		reportLog("7.3 Previous 'Enrolled' status is saved and displayed in Subject Details");
		subjectDetailPage.verifyDetailStatus("Status",Constants.SubjectStatus_Enrolled);
				
		reportLog("8 Logout the user");
		loginPage.logoutApplication();
		
		reportLog("8.1 Verify user Logout");
		loginPage.verifyUserLogout();
		
	}

}
