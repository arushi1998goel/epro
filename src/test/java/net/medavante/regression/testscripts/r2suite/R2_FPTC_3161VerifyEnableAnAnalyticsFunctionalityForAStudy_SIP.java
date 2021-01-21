package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.*;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.StudyModuleConstants;

public class R2_FPTC_3161VerifyEnableAnAnalyticsFunctionalityForAStudy_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_3161VerifyEnableAnAnalyticsFunctionalityForAStudy_SIP(String browser) {
		super(browser);
	}


	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
				
		reportLog("1.1: Login in to application with "+ AT_ProdAdminOps);
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);
		
		reportLog("1.2: Verify User logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to Administration");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.2: Navigate to studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("2.3: Select the " + studyName + " from study list");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("2.4: Edit the study");
		adminstrationStudiesPage.editStudy();
		
		reportLog("2.5: Uncheck the Analytics CheckBox to configure the test prerequiste");
		adminstrationStudiesPage.unSelectAnalyticsCheckBox();
		
		reportLog("2.6: Click on save button to configure the test prerequiste");
		adminstrationStudiesPage.clickOnSaveBTN();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3161 Test Case Name:Enable an Analytics functionality
	 * for a Study
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-3161_Enable an Analytics functionality for a Study", groups = { "R2" })

	public void FPTC_3161_VerifyEnableAnAnalyticsFunctionalityForAStudy() {
		
		reportLog("2.7: Edit the study");
		adminstrationStudiesPage.editStudy();
		
		reportLog("2.8: Verify General tab is displayed in Edit mode for "+ studyName + " study");
		adminstrationStudiesPage.verifyGeneralTabIsInEditMode();

		reportLog("2.9: Verify Product Type section is displayed on the General tab");
		adminstrationStudiesPage.verifyProductTypeSectionIsDisplayed();

		reportLog("2.10: Verify Analytics setting checkbox is available on the General tab");
		adminstrationStudiesPage.verifyAnalyticsSettingIsDisplayed();

		reportLog("2.11: Verify The checkbox is not set for Analytics setting");
		adminstrationStudiesPage.verifyAnalyticsCheckBoxIsNotSelected();

		reportLog("3.1: Set the checkbox for Analytics setting");
		adminstrationStudiesPage.selectAnalyticsCheckBox();

		reportLog("3.2: Verify Checkbox is displayed ");
		adminstrationStudiesPage.verifyAnalyticsCheckBoxIsSelected();

		reportLog("3.3: Verify Save button is in active state");
		adminstrationStudiesPage.verifySaveButtonIsEnabled();

		reportLog("3.4: Verify Cancel button is in active state");
		adminstrationStudiesPage.verifyCancelButtonIsEnabled();

		reportLog("4.1: Click on Cancel button");
		adminstrationStudiesPage.clickOnCancelBTN();

		reportLog("4.2: Verify Analytics setting not configured");
		adminstrationStudiesPage.verifyProductTypeNotConfigured(StudyModuleConstants.analyticsTab);

		reportLog("5.1: Edit the study to Set the checkbox for Analytics setting");
		adminstrationStudiesPage.editStudy();

		reportLog("5.2: Set the checkbox for Analytics setting");
		adminstrationStudiesPage.selectAnalyticsCheckBox();

		reportLog("5.3: Click on Save button");
		adminstrationStudiesPage.clickOnSaveBTN();

		reportLog("5.4: Verify Changes are saved");
		adminstrationStudiesPage.verifyProductTypeConfigured(StudyModuleConstants.analyticsTab);
		
		reportLog("5.5: Verify Analytics tab is available for Study");
		adminstrationStudiesPage.verifyTabIsDisplayed(StudyModuleConstants.analyticsTab);
		
		reportLog("5.6: Logout from the application");
		loginPage.logoutApplication(); 
		
		reportLog("5.7: Verify user Logout from the application");
		loginPage.verifyUserLogout();

	}

}
