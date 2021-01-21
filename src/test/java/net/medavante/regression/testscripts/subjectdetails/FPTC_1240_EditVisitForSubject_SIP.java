package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.pages.studynavigator.StudySubjectListingPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1240_EditVisitForSubject_SIP extends BaseTest {

	private String studyName;
	private String subjectName = "Subj_" + generateRandomString(3);
	protected StudySubjectListingPage studySubjectListingPageObj;
	protected String PROAss = "Auto_PROAssesment";
	protected String ObsROAss = "Auto_Obsro_NotCompleted";
	protected String ClinROAss = "Auto_VisitClinro";
	protected String EventAss = "Auto_NonCrVisit_Event";
	private String reasonForChange = "Technical difficulties";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1240_EditVisitForSubject_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("AutomationStudyName");

		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);		
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("1. Configure study for 1 PRO Assessment which marked as Not Completed exists");
		subjectDetailPage.clickOnVisitRow(PROAss);
		
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setCurrentDate();
		subjectDetailPage.selectReasonForChangeOption(reasonForChange);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("2. Configure study for 1 ObsRO Assessment which marked as Not Completed exists");
		subjectDetailPage.clickOnVisitRow(ObsROAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setCurrentDate();
		subjectDetailPage.selectReasonForChangeOption(reasonForChange);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("3. Configure study for 1 Event Assessment which marked as Not Completed exists");
		subjectDetailPage.clickOnVisitRow(EventAss);
		subjectDetailPage.clickOnAddVisitIcon();
				
		subjectDetailPage.verifyMarkAsCompletedLinkIsDisplayed();
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.setCurrentDate();
		subjectDetailPage.selectReasonForChangeOption(reasonForChange);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("4. Configure study for 1 ClinRO Assessment which marked as Not Administered exists");
		subjectDetailPage.clickOnVisitRow(ClinROAss);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	
	}

	/**
	 * =========================================================================
	 * Test Case Id:FP-TC-1240 Test Case Name: Edit visit for subject  
	 * =========================================================================
	 * ===========================================
	 */

	@Test(description = "FP-TC-1240: Subject Details - Edit visit for subject", groups = { "" })

	public void FPTC_1240_verifyEditVisitForSubject() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		
		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		
		reportLog("1.3: Select study and site");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.4: Navigate to the Subject details page of the subject and click on visit");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("1.5: Edit control is available");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("2.1: Click on 'Edit'");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("2.2: 'Cancel' control is available");
		subjectDetailPage.verifyEditSubjectCancelButtonIsDisplayed();
		subjectDetailPage.clickOnCancelBtn();

		reportLog("2.3: Forms ClinRo form are editable");
		subjectDetailPage.clickOnVisitRow(ClinROAss);
		subjectDetailPage.verifyVisitStatus(ClinROAss, Constants.Pending_Status);
		subjectDetailPage.verifyAddVisitIconIsNotDisplayed();		

		reportLog("2.3.1: Verify Name of the Other Rater is displayed in rater drop down list");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue("Me");
		
		reportLog("2.4: Forms ObsRo form are not editable");
		subjectDetailPage.clickOnVisitRow(ObsROAss);
		subjectDetailPage.verifyVisitStatus(ObsROAss, Constants.Complete_Status);
		subjectDetailPage.verifydeleteVisitIconIsNOtDisplayed();		

		reportLog("2.4.1: Submitted ObsROAss form displays following attributes:");
		subjectDetailPage.clickOnVisitRow(ObsROAss);
		subjectDetailPage.verifySubmittedVisitRaterName("");
		subjectDetailPage.verifySubmittedVisitDate("");		
		
		reportLog("2.5: Forms Pro form are not editable");
		subjectDetailPage.clickOnVisitRow(PROAss);
		subjectDetailPage.verifyVisitStatus(PROAss, Constants.Complete_Status);
		subjectDetailPage.verifydeleteVisitIconIsNOtDisplayed();	
		
		reportLog("2.5.1: Submitted PROAss form displays following attributes: ");
		subjectDetailPage.clickOnVisitRow(PROAss);
		subjectDetailPage.verifySubmittedVisitRaterName(Constants.ATAssignedRater_13);
		subjectDetailPage.verifySubmittedVisitDate(currentDate());
		
		reportLog("Logout from the application");
		loginPage.logoutApplication(); 
		
		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
