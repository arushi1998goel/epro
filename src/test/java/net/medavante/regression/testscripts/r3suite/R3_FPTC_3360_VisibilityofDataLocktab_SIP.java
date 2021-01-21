
/**
 *@author 
* @date 04/02/2020
* =========================================================================
*  Test Case Id: FP-TC-3360 || Test Case Name: Visibility of Data Lock tab
 * pre-qualification : 1. At least one Study is configured for the test
                       2. At least one User exists who can manage Studies cannot lock Assessments
                       3. At least one User exists who can manage Studies and lock Assessments
* ========================================================================= 
*/


package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3360_VisibilityofDataLocktab_SIP extends BaseTest {

	
	private String study;
	
	
	@Factory(dataProvider="Browsers",dataProviderClass=DataProviders.class)
	public R3_FPTC_3360_VisibilityofDataLocktab_SIP(String browser) {
		super(browser);
	}

	
	
	@BeforeMethod
	public void getTestData() throws Exception
	{
		System.setProperty("className",getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study=properties.getProperty("Study2831");
		
	}
	
	@Test(description="FP-TC-3360 ---Visibility Of Data Lock Tab")
	public void R3_FPTC_3360_visibilityOfdataLockTab()
	{
		reportLog("2.0:  Log in to the Portal as a User Pr.#2" );
		dashBoardPage=loginPage.loginInApplication(AT_PRODAdminViewOnly, AT_Password);
			
		reportLog("2.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0 Navigate to Administration");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.0.1 Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.0.2 Search " + study + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(study);
		
		reportLog("3.1: Study configuration is displayed" );
		adminstrationStudiesPage.verifyStudyConfigurationElementsAreDisplayed();
		
		reportLog("3.2: Data Lock tab is not displayed");
		adminstrationStudiesPage.verifyDataLockTabIsNotDisplayed();
		
		reportLog("3.2.0 LogOut Application");
		loginPage.logoutApplication();
	
		reportLog("4.0: Log in to the Portal as a User Pr.#3");
		loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("4.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("5.0 Navigate to Administration");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("5.0.1 Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("5.0.2 Search " + study + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(study);
		
		reportLog("5.1: Study configuration is displayed" );
		adminstrationStudiesPage.verifyStudyConfigurationElementsAreDisplayed();
		
		reportLog("5.2: Data Lock tab is Displayed");
		adminstrationStudiesPage.verifyDataLocktabIsDisplayed();
		
		reportLog("6.0: Select the Data Lock tab");
		adminstrationStudiesPage.clickOnDataLockTab();
		
		reportLog("6.2: Data Lock tab is opened");
		adminstrationStudiesPage.verifyDatLockTabpageIsOpened();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
	}
	
}
