package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3660_EditVisitForASubjectWithAllLockedAssessment_SIP extends BaseTest {
	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3660_EditVisitForASubjectWithAllLockedAssessment_SIP(String Browser) {
		super(Browser);
	}

	@BeforeMethod
	public void getData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2831");
		subjectName = properties.getProperty("Subject2831");
		visitName= properties.getProperty("CompletedVisit2861");
	}
	
	/**
	 * Test Case Id: FP-TC-3660 -Edit Visit for a Subject with all locked Assessments  
	 * @throws Exception 
	 */
	@Test(description = "FP-TC-3660 -Edit Visit for a Subject with all locked Assessments", groups = { "R3" })
	public void R3_FPTC_3660_EditVisitForASubjectWithAllLockedAssessment() {		

			reportLog("1.1: Login to the application");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

			reportLog("1.2 :Verify site user is logged in");
			dashBoardPage.verifyMedavantePortalPage();

			reportLog("1.3:- Go to a Study Navigator -> Study Pr#1");
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage.verifyOptionToSelectStudyAndSiteIsDisplayed();
			studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
			
			reportLog("3.1: Navigate to the Subject Details page Pr#2");
			studyNavigatorDashBoardPage.navigateToSubjectsListing();		
			
			reportLog("3.2:The list of Subjcet are displayed ");
			studyNavigatorDashBoardPage.verifySubjectListIsOpened();

			reportLog("3.3:Select Subject Pr#3");
			studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
					subjectName);
			subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

			reportLog("3.4:Verify Subject Details Page");
			subjectDetailPage.verifyNewSubjectDetailPage();
			
			reportLog("3.5: Subject locked icon display");
			subjectDetailPage.verifySubjectLockedIconIsDisplayed();
						
			reportLog("4: Select the Visit from Pr#1 in the list");
			subjectDetailPage.clickOnVisitRow(visitName);
			
			reportLog("4.1: Visit status is Complete");
			subjectDetailPage.verifyVisitStatus(visitName, Constants.Complete_Status);			
			
			reportLog("4.2: The list of Assessments Pr.#1.1 is displayed");
			subjectDetailPage.verifyListOfAssessmentAlongWithVisit(Constants.ClinRO_Form_Label);
			
			reportLog("4.3: The Assessment Pr#1.1 cannot be assigned to a Rater");			
			subjectDetailPage.verifyRaterDropdownlinkIsNotDisplayed();
			
			reportLog("4.4: Lock icon displayed for all Assessments Pr.#1.1");
			subjectDetailPage.verifyLockedIconDisplayedForAssessment(Constants.ClinRO_Form_Label,"lock");
			
			reportLog("4.5: Edit action isn't displayed"); 
			subjectDetailPage.verifySubjectDetailsDisabledEditIconDisplayed();
			
			reportLog("Logout from the application");
			loginPage.logoutApplication();

			reportLog("Verify user is logout");
			loginPage.verifyUserLogout();	


	}
}
