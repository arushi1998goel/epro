package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.pages.studynavigator.VisitDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1473_ChangeVisitNotAppearHasOneScaleFormMapping_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5),mapVisit;

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1473_ChangeVisitNotAppearHasOneScaleFormMapping_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");	
		mapVisit = properties.getProperty("Mapped2232VisitName");
		
		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();	
		subjectDetailPage.clickOnVisitRow(mapVisit);
		subjectDetailPage.clickOnAddVisitIcon();		
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		
		reportLog("Search the subjcet in assessment page");
		assessmentDetailPage=studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog14List1FormNameThirdType, mapVisit, subjectName);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		loginPage.logoutApplication();
				
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1473 Test Case Name: Change Visit - option to change Visit doesn't appear in case when Visit has at least one scale with form item mapping
	 * ====================================================================================================================
	 * @throws InterruptedException 
	 */

	@Test(description = "FP-TC-1473_Change Visit - option to change Visit doesn't appear in case when Visit has at least one scale with form item mapping", groups = { })
	public void FPTC_1473_VerifyChangeVisitNotAppearHasOneScaleFormMapping() throws InterruptedException {
		
		reportLog("1: Login to portal as a user with the claims");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		reportLog("1.2: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		reportLog("2: Navigate to Visit list for the study identified in the Pr.#1");
		studyNavigatorDashBoardPage.navigateToVisitsListing();				
		
		reportLog("2.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();
		
		reportLog("3.1: Visit list is opened displaying visits in status as Completed");
		studyNavigatorDashBoardPage.searchSubject(subjectName);    
		studyNavigatorDashBoardPage.searchVisit(mapVisit);	
		
		reportLog("3.2: Navigate to Visit Detail screen identified in the Pr.#2");
		visitDetaiLPage=studyNavigatorDashBoardPage.selectByFirstCell(VisitDetailsPage.class);
		
		reportLog("3.3: 'Action' control isn't displayed on Visit Detail screen");
		visitDetaiLPage.verifyingActionButtonIsNotDisplayed();
		
		reportLog("3.4: Logout application");
		loginPage.logoutApplication();

		reportLog("3.5: Verify user is logout");
		loginPage.verifyUserLogout();
				
		
	}

}
