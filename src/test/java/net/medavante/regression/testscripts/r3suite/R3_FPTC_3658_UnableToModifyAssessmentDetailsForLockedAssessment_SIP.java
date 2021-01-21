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

public class R3_FPTC_3658_UnableToModifyAssessmentDetailsForLockedAssessment_SIP extends BaseTest {
		
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3658_UnableToModifyAssessmentDetailsForLockedAssessment_SIP(String Browser) {
		super(Browser);
	}

	@BeforeMethod
	public void getData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("Study2831");
		subjectName = properties.getProperty("Subject2831");
		visitName= properties.getProperty("NonCRCompletedVisit2193");
	}
	
	/**
	 * Test Case Id: FP-TC-2836 -Unable to modify Assessment Details for a locked Assessment 
	 * @throws Exception 
	 */
	@Test(description = "FP-TC-3658 -Unable to modify Assessment Details for a locked Assessment", groups = { "R3" })
	public void R3_FPTC_3658_UnableToModifyAssessmentDetailsForLockedAssessment() {
		
			reportLog("1.1: Login to the application");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

			reportLog("1.2 :Verify site user is logged in");
			dashBoardPage.verifyMedavantePortalPage();

			reportLog("1.3:- Go to a Study Navigator -> Study Pr#1");
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage.verifyOptionToSelectStudyAndSiteIsDisplayed();
			studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
			
			reportLog("3: Navigate to Assessment Details page Pr#1");
			studyNavigatorDashBoardPage.navigateToAssessmentsListing();			
			
			reportLog("3.2:The list of Assessment is displayed ");
			studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

			reportLog("3.3:Select the Assessment from Pr#3 in the list of Visits");
			assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assesment_AdasCog13List1FormName, visitName, subjectName);

			reportLog("3.4:Verify assessment detail page opened");
			assessmentDetailPage.verifyAssessmentDetailsDisplayed();
			
			reportLog("3.5: An indication that Assessment is locked is displayed");
			assessmentDetailPage.verifyLockedIconIsDisplayed();
			
			reportLog("4.2: Assessments Details are not editable");
			assessmentDetailPage.verifyDetailsDisabledEditIconDisplayed();
			
			reportLog("Logout from the application");
			loginPage.logoutApplication();

			reportLog("Verify user is logout");
			loginPage.verifyUserLogout();	


	}
}
