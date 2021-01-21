package net.medavante.regression.testscripts.r4suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;


public class R4_FPTC_2154_AddSiteRaterFacilityCorrespondingToStudy_MAP extends BaseTest {

	private String study,pemail,email2,firstName,lastName,name;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
    public R4_FPTC_2154_AddSiteRaterFacilityCorrespondingToStudy_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
	
		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study=properties.getProperty("AutomationStudyName");
		studyName = properties.getProperty("AutomationStudyName");
		pemail= "jkj"+generateRandomString(3)+"@test.com";
		email2="KPK"+generateRandomString(3)+"@test.com";
		firstName="Automation"+generateRandomString(3);
		lastName="testing"+generateRandomString(3);
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-2154 Add Site Rater - Facility corresponding to study
 
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-2154 Add Site Rater - Facility corresponding to study", groups = { "R4" })
	public void R4_FPTC_2154_AddSiteRaterFacilityCorrespondingToStudy() throws Exception {
		
		reportLog("2: Log in to the Ma-Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager,SuperAdminPW);
	    		
		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();
	    
		reportLog("3: Navigate to Study Navigator ->Clinicians / Raters -> Study, Site Pr.#1");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.clinician_ratersText);
		ratersDetailsPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("4.1: Clinicians/Raters listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();		
		
		reportLog("5: Select an action to Add Site Rater");
		ratersDetailsPage.selectAddSiteControl();
				
		reportLog("5.1: A modal window for adding a new Site Rater is displayed");
		ratersDetailsPage.verifyAddSiteRaterWindowIsDisplayed();
				
		reportLog("6.1: Fill in all required fields");
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(pemail,firstName,lastName);
		
		reportLog("6.1.1: All required fields aren't highlighted");
		//studyNavigatorDashBoardPage.verifyRequiredFieldIsNotHighlightedAfterFillValidData(Constants.filledRequiredFieldBorderColorOnAddSitePopUp,pemail,siteRaterAccess,lastName);
		ratersDetailsPage.verifySiteRaterRequiredInformationFieldHighlighedOrNot(Constants.FilledrequiredColor);
		
		
		reportLog("6.2: Select Facilities and Studies 1 and 2 "
				+ "om Pr.#1");
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_SiteRater_PopUp);	
		
		//ratersDetailsPage.selectFacility(Constants.MedAvanteFacilityName, Constants.Assign_SiteRater_PopUp);	
		//ratersDetailsPage.selectStudyFromStudyDropDownOptions(studyName);
		ratersDetailsPage.searchAndSelectSite(Constants.ATAssignedRater_10, Constants.Add_SiteRater_PopUp);
		
		reportLog("6.2.1: Add control is enabled");
		ratersDetailsPage.verifyAddControlIsEnabledOnAddSitePopUp();
		
		reportLog("7: Remove Facility 1");
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_SiteRater_PopUp);
		
		reportLog("7.1: - Studies field is highlighted as invalid");
				
		reportLog("7.2: - The message is displayed saying that 'Provided Study is incorrect'");
		
		reportLog("7.3: - Add control is disabled");
		ratersDetailsPage.verifyAddButtonIsDisabledOnAddSitePopUp();
		
		reportLog("8: Select Facility 1");
		ratersDetailsPage.selectFacility(Constants.FacilityName, Constants.Add_SiteRater_PopUp);	
				
		reportLog("8.1: All required fields aren't highlighted");
		//studyNavigatorDashBoardPage.verifyRequiredFieldIsNotHighlightedAfterFillValidData(Constants.filledRequiredFieldBorderColorOnAddSitePopUp,pemail,siteRaterAccess,lastName);
		
		reportLog("8.2: The message is not displayed");
		
		reportLog("8.3: Add control is enabled");
		ratersDetailsPage.verifyAddControlIsEnabledOnAddSitePopUp();
		
		reportLog("9: Select option to Add Site Rater");
		ratersDetailsPage.clickOnYesCloseFormControlOnConfirmationPopUp();
		
		reportLog("9.1: New Site Rater is created");
		ratersDetailsPage.verifyNewlyAddedSiteRater(firstName);
		
		reportLog(" Logout application");
		loginPage.logoutApplication();

		reportLog(" Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
}
}
