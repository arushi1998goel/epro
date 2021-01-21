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

public class FPTC_1292_VerifyLayoutOfVisitDetailScreen_SIP extends BaseTest {
	
	private String studyName,subjectName,visitName;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1292_VerifyLayoutOfVisitDetailScreen_SIP(String browser) {
		super(browser);
			}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");	
		subjectName = properties.getProperty("SubjectName2144");
		visitName = properties.getProperty("MoveVisit1");
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1292 Test Case Name: Layout and interacting with Visit Detail
	 * ====================================================================================================================
	 * @throws InterruptedException 
	 */

	@Test(description = "FP-TC-1292 _Layout and interacting with Visit Detail", groups = { })
	public void FPTC_1292_VerifyLayoutOfVisitDetailScreen() throws InterruptedException {
		
		reportLog("1:Login to portal as a user with the claims");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.2: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		reportLog("2: Navigate to Visit list for the study identified in the Pr.#1");
		studyNavigatorDashBoardPage.navigateToVisitsListing();				
		
		reportLog("2.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();
		
		reportLog("3: Return back to Dashboard and click on 'Completed' filter");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.Complete_Status);
		
		reportLog("3.1: Visit list is opened displaying visits in status as Completed");
		studyNavigatorDashBoardPage.searchSubject(subjectName);    
		
		/*Need to update this method*/
		
		
		reportLog("3.2: Navigate to Visit Detail screen identified in the Pr.#2");
		visitDetaiLPage=studyNavigatorDashBoardPage.selectByFirstCell(VisitDetailsPage.class);
		
		reportLog("3.3: Visit Detail screen is opened");
		visitDetaiLPage.verifyVisitPageIsDisplayed();
		
		reportLog("3.4: Title of the Visit display");
		visitDetaiLPage.verifyVisitPageTitle(visitName);
		
		reportLog("3.5: 'Refresh' control is available");
		visitDetaiLPage.verifyRefreshIconIsDisplayed();
		
		reportLog("3.6: 'Actions' control with value: 'Move Visit' is available");
		visitDetaiLPage.verifyActionButtonIsDisplayed();
		
		reportLog("3.7: Visit Details is display");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("3.8: Subject Details");
		visitDetaiLPage.verifyVisitDetails(Constants.StudyDashBoard_columnName_Status, Constants.Complete_Status);
		
		reportLog("3.9: List of assessment forms");
		visitDetaiLPage.verifyAssessmentsTabIsDisplayed();
		
		reportLog("3.10: Attachments");
		//Not available
		
		reportLog("3.11:Logout application");
		loginPage.logoutApplication();

		reportLog("3.12:Verify user is logout");
		loginPage.verifyUserLogout();
				
		
	}

}
