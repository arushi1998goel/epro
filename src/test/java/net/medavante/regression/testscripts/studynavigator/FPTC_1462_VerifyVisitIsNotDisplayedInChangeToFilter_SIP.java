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

public class FPTC_1462_VerifyVisitIsNotDisplayedInChangeToFilter_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String mappedVisit, completedNonCRVisit;

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1462_VerifyVisitIsNotDisplayedInChangeToFilter_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");
		completedNonCRVisit = properties.getProperty("Auto_ClinRo");
		mappedVisit = properties.getProperty("MappedVisit");
		
		reportLog("Creating a subject from user and configure studies accordingly");
		
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
			
		subjectDetailPage.clickOnVisitRow(completedNonCRVisit);
		subjectDetailPage.clickOnAddVisitIcon();		
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		
		subjectDetailPage.clickOnVisitRow(mappedVisit);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		
		loginPage.logoutApplication();
		
		loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(completedNonCRVisit,subjectName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("Search the subject in assessment page");
		studyNavigatorDashBoardPage.selectByVisitAndSubjectName(mappedVisit, subjectName);
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		assessmentDetailPage.waitSpinnerToBecomeInvisible();

		loginPage.logoutApplication();
		
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1462 Test Case Name: Change Visit - Visit is not displayed in Change To filter in case when at least one scale that has form item mapping 
	 * ====================================================================================================================
	 * @throws InterruptedException 
	 */

	@Test(description = "FP-TC-1462_Change Visit - Visit is not displayed in Change To filter in case when at least one scale that has form item mapping", groups = { })
	public void FPTC_1462_VerifyVisitIsNotDisplayedInChangeToFilterInCaseWhenAtLeastOneScaleThatHasFormItemMapping() throws InterruptedException {
		
		reportLog("1: Login to portal as a user with the claims");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("2: Navigate to Visit Listing screen ");
		studyNavigatorDashBoardPage.navigateToVisitsListing();				
		
		reportLog("2.1: Select Visit from Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(mappedVisit,
				subjectName);

		reportLog("2.2: Visit Detail screen is displayed");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("3:'Action' option is displayed for Visit");
		visitDetaiLPage.verifyActionOptionIsDisplayed();
		
		reportLog("3.1: Select action to moving visit");
		visitDetaiLPage.selectActionToMoveVisit();
		visitDetaiLPage.verifyMoveVisitPopUpIsDisplayed();

		reportLog("3.2: Change subject");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName);
 		visitDetaiLPage.clickOnChangeToVisitDropDown();
		
		reportLog("4: Check that Visit Pr.#3 isn't displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.verifyVisitIsNotPresentInChangeToVisitDropDown(mappedVisit);
		visitDetaiLPage.clickOnCloseIconOfMoveVisitPopUp();
		
		reportLog("4.1: Logout application");
		loginPage.logoutApplication();

		reportLog("4.2: Verify user is logout");
		loginPage.verifyUserLogout();
				
		
	}

}
