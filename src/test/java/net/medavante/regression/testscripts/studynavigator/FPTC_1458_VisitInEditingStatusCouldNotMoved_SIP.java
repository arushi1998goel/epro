package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.pages.studynavigator.VisitDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1458_VisitInEditingStatusCouldNotMoved_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String ClinROAss="Auto_VisitClinro";

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1458_VisitInEditingStatusCouldNotMoved_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");	
		
		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("Select visit");
		subjectDetailPage.clickOnVisitRow(ClinROAss);
		subjectDetailPage.clickOnAddVisitIcon();		
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_11);
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText );
		loginPage.logoutApplication();
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1458 Test Case Name: Verify that Visit in Editing status couldn't be moved
	 * ====================================================================================================================
	 * @throws InterruptedException 
	 * 
	 */

	@Test(description = "SFB-TC-2147_Verify that Visit in Editing status couldn't be moved", groups = { })
	public void FPTC_1458_VerifyVisitInEditingStatusCouldNotBeMoved() throws InterruptedException {
		
		reportLog("1:Login to portal as a user with the claims");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.reSelectstudy(studyName);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.2: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		reportLog("2: Navigate to Visit list for the study identified in the Pr.#1");
		studyNavigatorDashBoardPage.navigateToVisitsListing();				
		
		reportLog("2.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();
		
		reportLog("3.1: Visit list is opened displaying visits in status as Completed");
		studyNavigatorDashBoardPage.searchSubject(subjectName);    
		studyNavigatorDashBoardPage.searchVisit(ClinROAss);	
		
		reportLog("3.2: Navigate to Visit Detail screen identified in the Pr.#2");
		visitDetaiLPage=studyNavigatorDashBoardPage.selectByFirstCell(VisitDetailsPage.class);
		
		reportLog("3.3: 'Action' control isn't displayed on Visit Detail screen");
		visitDetaiLPage.verifyingActionButtonIsNotDisplayed();
		
		reportLog("3.4:Logout application");
		loginPage.logoutApplication();

		reportLog("3.5:Verify user is logout");
		loginPage.verifyUserLogout();
				
		
	}

}
