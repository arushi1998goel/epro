package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_3309StudyGeneralTabElements_SIP extends BaseTest {

	
	private String studyToBeSelected;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_3309StudyGeneralTabElements_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties testData = Configuration.readTestData("RegressionTestData");
		studyToBeSelected = testData.getProperty("AutomationStudyName");

	}
	
	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-3309 
	 * Test Case Name: Study General Tab - Elements
	 * =========================================================================
	 * 
	 */

	@Test(description = "FP-TC-3309_verifyStudyGeneralTabElements(", groups = { "R2" })
	public void FPTC_3309_verifyStudyGeneralTabElements(){
		
		reportLog("1:Login in to application");	
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps,AT_Password);
		
		reportLog("1.2: Verify User logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2:Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.1:Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("2.2:Select Study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyToBeSelected);
		
		reportLog("2.3:Verify general page is opened");
		adminstrationStudyGeneralPage = adminstrationStudiesPage.navigateToStudyGeneralTab();
		
		reportLog("3:Verify general page's grid elements are in read only mode");
		adminstrationStudyGeneralPage.verifyGenralTabElementsAreInReadOnlyMode();
		
		reportLog("3.1:Verify Save and Cancel button in disabled status");
		adminstrationStudyGeneralPage.verifySaveAndCancelControlStatus();
		
		reportLog("3.2:Verify Product Type elements");
		adminstrationStudyGeneralPage.verifyProductTypeElements();
		
		reportLog("4:Verify general page's grid elements are in edit mode");
		adminstrationStudyGeneralPage.verifyGenralTabElementsAreInEditMode();
		
		reportLog("4.1:Verify Save and Cancel button statuses");
		adminstrationStudyGeneralPage.verifySaveAndCancelControlStatus();
		
		reportLog("4.2:Verify Sponser and AWS Bucket Data is in read only mode");
		adminstrationStudyGeneralPage.verifySponsorAndAWSBucketDataStatus();
		
		reportLog("4.3:Verify Product Type elements");
		adminstrationStudyGeneralPage.verifyProductTypeElements();
		
		reportLog("5:Verify Study, Abbervieation and Phase fields are mandatory");
		adminstrationStudyGeneralPage.verifyRequiredFieldHighlightedAfterClearingData();
		
		reportLog("5.1:Verify Save and Cancel button statuses");
		adminstrationStudyGeneralPage.verifySaveAndCancelControlStatus();
		
		reportLog("6:Verify after clicking on Cancel Changes are not saved and elemnts become read only");
		adminstrationStudyGeneralPage.verifyChangesNotSavedAfterClickingOnCancel();
		
		reportLog("7:Verify for creating any new study some fields are required as well some fields value should be there");
		adminstrationStudyGeneralPage.fieldsVerificationForCreatingNewStudy();
		
		reportLog("8:Verify Sponser Dropdown should only contains Organization with Sponser Type ");
		adminstrationStudyGeneralPage.verifySponsorDropDownList();
		
		reportLog("9:Verify Status Dropdown should contains Statuses");
		adminstrationStudyGeneralPage.verifyStatusDropDownLIST();
		
		reportLog("10:Verify Indication Dropdown should contains Indications");
		adminstrationStudyGeneralPage.verifyIndicationDropDownLIST();
		
		reportLog("11.1:Logout from the application");
		loginPage.logoutApplication();

		reportLog("11.2:Verify user is logout");
		loginPage.verifyUserLogout();
	
	}

}
