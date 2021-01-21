package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1353_UserCanNavigateOnNextPredefinedVisitFilter_SIP extends BaseTest {

	private String studyName; 	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1353_UserCanNavigateOnNextPredefinedVisitFilter_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyCRStatus");	
		subjectName = properties.getProperty("SubjectNameCrStatus");
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1353 Test Case Name: Predefined filters - Visit Status 
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1353_Predefined filters - Visit Status", groups = { })
	public void FPTC_1353_VerifyUserCanNavigateOnNextPredefinedVisitFilter() {
		
		reportLog("1.1 :Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2 : Navigate to Study Dashboard of study (Pr #1)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.3: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		reportLog("2: Click on 'Visits filter");
		studyNavigatorDashBoardPage.navigateToVisitsListing();				
		
		reportLog("2.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.verifyVisitStatusOptions();
		
		reportLog("3: Return back to Dashboard and click on 'Pending' filter");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.Pending_Status);
		
		reportLog("3.1: Visit list is opened displaying visits in status as Requested ");
		studyNavigatorDashBoardPage.searchSubject(subjectName);    
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status, Constants.requested_Status);
		
		reportLog("3.2: Visit list is opened displaying visits in status as ​Scheduled ");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status, Constants.scheduled_Status);		
		
		reportLog("3.3: Visit list is opened displaying visits in status as ​Initiated");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status, Constants.initiated_Status);		
		
		reportLog("4: Return back to Dashboard and click on 'In Progress' filter");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.InProgress_Status);
		
		reportLog("4.1: Visit list is opened displaying visits in status");
		studyNavigatorDashBoardPage.searchSubject(subjectName);    
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status, Constants.InProgress_Status);		
		
		reportLog("5: Return back to Dashboard and click on 'Completed' filter");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.Complete_Status);
		
		reportLog("5.1: Visit list is opened displaying visits in status Compeletd");
		studyNavigatorDashBoardPage.searchSubject(subjectName);    
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status, Constants.Complete_Status);		
		
		reportLog("5.2: Visit list is opened displaying visits in status Editing");
		studyNavigatorDashBoardPage.searchSubject(subjectName);    
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status, Constants.Editing_Status);		
		
		reportLog("6: Return back to Dashboard and click on 'Editing' filter");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.Editing_Status);
		
		reportLog("6.1: Visit list is opened displaying visits in status Editing");
		studyNavigatorDashBoardPage.searchSubject(subjectName);    
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status, Constants.Editing_Status);		
		
		reportLog("7:Logout application");
		loginPage.logoutApplication();

		reportLog("7.1:Verify user is logout");
		loginPage.verifyUserLogout();
				
		
	}

}
