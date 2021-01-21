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

public class R3_FPTC_2658_ActionsOptionHiddenIfVisitContainsLockedAssessment_SIP extends BaseTest {
	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_2658_ActionsOptionHiddenIfVisitContainsLockedAssessment_SIP(String Browser) {
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
	 * Test Case Id: FP-TC-2658 -Actions option is hidden if a Visit contains a locked Assessment
	 * @throws Exception 
	 */
	@Test(description = "FP-TC-2658 -Actions option is hidden if a Visit contains a locked Assessment", groups = { "R3" })
	public void R3_FPTC_2658_ActionsOptionHiddenIfVisitContainsLockedAssessment() {
		

			reportLog("1.1: Login to the application");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

			reportLog("1.2 :Verify site user is logged in");
			dashBoardPage.verifyMedavantePortalPage();

			reportLog("1.3:- Go to a Study Navigator -> Study Pr#1");
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage.verifyOptionToSelectStudyAndSiteIsDisplayed();
			studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
			
			reportLog("3: Navigate to Visit Details from Pr#1");
			studyNavigatorDashBoardPage.navigateToVisitsListing();			
			
			reportLog("3.2:The list of Visits are displayed ");
			studyNavigatorDashBoardPage.verifyVisitListIsOpened();

			reportLog("4.1:Select the Visit from Pr#3 in the list of Visits");
			visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitName, subjectName);

			reportLog("4.2:Visit Details page is opened");
			visitDetaiLPage.verifyVisitDetailIsDisplayed();
			
			reportLog("4.4: Actions option is not displayed");
			visitDetaiLPage.verifyingActionButtonIsNotDisplayed();
						
			reportLog("Logout from the application");
			loginPage.logoutApplication();

			reportLog("Verify user is logout");
			loginPage.verifyUserLogout();	


	}
}
