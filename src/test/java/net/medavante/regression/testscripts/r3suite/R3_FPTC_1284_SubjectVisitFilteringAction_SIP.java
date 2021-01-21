/**
 *@author 
* @date 20/07/2020
* ==========================================================================================================
*  Test Case Id: FP-TC-1284 || Test Case Name: Subject Visit: Filtering action- V1
*  
	 * pre-qualification :1. Study is configured for the test and has at least one Visit in the Calendar
                          2. At least one Subject for the Study in Pr#1 exists 
                          3. At least one Visit based on duration exists and associated with the Subject Pr.#2:
                          3.1 Now
                          3.2 Future
                          3.3 Past
                          4. At least one User who can manage visits in the Study of Pr#1 exists
 	
* ========================================================================================================== 
*/package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1284_SubjectVisitFilteringAction_SIP extends BaseTest {
			
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1284_SubjectVisitFilteringAction_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
	}
	
	@Test(description="FP-TC-1284--Subject Visit: Filtering action- V1")
	public void R3_FPTC_1284_SubjectVisitFilteringAction()
	{
		reportLog("1.1:	Log in to the Portal as User of Pr#4");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:	User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:	 Navigate to Subject Details page of PR#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				Constants.SubjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(Constants.SubjectName);
		
		reportLog("2.2: Subject detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("2.3: Select the Visit of Pr#3 from category list");
		subjectDetailPage.selectVisitByName(Constants.visitForNow);
		
		reportLog("2.4: New version of Visit list is displayed");
		subjectDetailPage.verifyViSitListIsDisplayed();
		
		reportLog("2.5: 'Now' filter Pr.#3.1 displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.VisitCategoryFilter_Now);
		
		reportLog("3.0: Select 'All' filter");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("3.1: All Visits are displayed (Pr.#3.1 - Pr.#3.3)");
        subjectDetailPage.verifyVisitIsDisplayed(Constants.visitForNow);
        subjectDetailPage.verifyVisitIsDisplayed(Constants.visitForPast);
        subjectDetailPage.verifyVisitIsDisplayed(Constants.visitForFuture);
        
        reportLog("4.0: Select 'Future' filter");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_Future);

        reportLog("4.1: Visits Pr.#3.2 that would be conducted into future (tomorrow and onward) are displayed");
        subjectDetailPage.verifyVisitIsDisplayed(Constants.visitForFuture);

        reportLog("5.0: Select 'Past' filter");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_Past);

        reportLog("5.1: Visits Pr.#3.3 that was conducted in past (including completed and skipped) are displayed");
        subjectDetailPage.verifyVisitIsDisplayed(Constants.visitForPast);

        reportLog("LogOut Application");
		loginPage.logoutApplication();
		
		reportLog("Verify LogOut ");
		loginPage.verifyUserLogout();



	}
}
