package net.medavante.smoke.testscripts;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.preqqualification.PreQualificationDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;



public class PreQualificationTest_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public PreQualificationTest_SIP(String browser) {
		super(browser);
	}


	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("TestDataFile");
		studyName = properties.getProperty("PREQUALIFICATIONStudy");
		
	}
	

	/**
	 * Test Case Id: TC-11 Test Case Name: Pre-Qualification Summary page displayed
	 * Expected Condition: Pre-Qualification Summary Should load and open
	 * successfully. Inputs: User Name, Password,Study Name
	 * 
	 */
	@Test(description = "Smoke TC-11--Pre-Qualification Summary page displayed", groups = { "smoke" })
	public void SFB_TC_2625_verifyPreQualificationSummary() {
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		
		reportLog("Click on Pre-Qualifications tile");
		preQualificationDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(PreQualificationDashBoardPage.class, Constants.NavigateText, Constants.QualificationText);
		
		reportLog("Select " + studyName + " from the study drop down");
		preQualificationDashBoardPage.selectStudy(studyName);
		
		reportLog("Verify Summary chart is opened for " + studyName);
		preQualificationDashBoardPage.verifySummaryChartIsOpened();
		
		reportLog("Click on mode view button to to view Pre-qualification as Rater Scale List");
		preQualificationDashBoardPage.changeViewMode();
		
		reportLog("Verify Pre Qualification Rater Scale List view Is Displayed");
		preQualificationDashBoardPage.verifyPreQualificationRaterScaleListIsDisplayed();
		
		reportLog("Verify total records is displayed");
		preQualificationDashBoardPage.verifyTotalPreQualificationRaterScaleIsDisplayed();
		
		reportLog("Logout application");
		loginPage.logoutApplication();
		
		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}
	
}
