/**
 * @author
 * @date 27/12/2019 
 * =========================================================================
 * Test Case Id: FP-TC-3620 
 * Test Case Name: Subject Details - Configure Year Of Birth for Subjects
 *  * pre-qualification : At least 1 Study and Site exists, Birth date options are not configured
                        2. At least 1 Subject exists for the study site Pr.#1
                        3. At least 1 user exists with appropriate claims to manage Studies
 * ========================================================================= 
 */

package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCustomPage;
import net.medavante.portal.pages.administration.AdministrationStudiesPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3620_ConfigureYearOfBirthforSubjects_SIP extends BaseTest

{

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3620_ConfigureYearOfBirthforSubjects_SIP(String browser) {
		super(browser);
	}
	
	protected String study,
	Subject = "Auto_Screen",
	message="There are unsaved changes. Do you want to continue and lose them?";
	
	@BeforeMethod
	public void getData() throws Exception
	{
		System.setProperty("className",getClass().getSimpleName());
		Properties prop=Configuration.readTestData("RegressionTestData");
		study=prop.getProperty("Study3620");
	}
	
	
	@Test(description = "FP-TC-3620 || Subject Details - Year of Birth", groups = { "R3" })
	public void R3_FPTC_3620_ConfigureYearOfBirthforSubjects()
	{
		reportLog("2.0: Login to Portal as a User Pr.#3.");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Verify user successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to Study setup Organization Page");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.0.1: Navigate to Study Page");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.0.2: Search Study: " + study);
		adminstrationStudiesPage.searchAndClickOnStudy(study);

		reportLog("3.0.3: Navigate to custom tab of study Page");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("3.0.3.1: Verify Custom Page");
		adminstrationStudiesCustomPage.verifyCustomPage();
		
		reportLog("3.0.4:  Edit mode");
		adminstrationStudiesCustomPage.customPageInEditMode();

		reportLog("3.1: Year Of Birth option is displayed in the Subject Field Visibility section");
		adminstrationStudiesCustomPage.verifyYearOfBirthCHKBOXDisplayed();

		reportLog("3.2: click on Custom Page and verify Custom Page in edit mode");
		adminstrationStudiesCustomPage.customPageInEditMode();

		reportLog("4.0: Click on year Of Birth CHKBOX to check the checkbox");
		adminstrationStudiesCustomPage.checkYearOfBirthCHKBOXCheckBox();

		reportLog("4.1: Verify year Of Birth CHKBOX Selected");
		adminstrationStudiesCustomPage.verifyYearOfBirthCHKBOXSelected();

		reportLog("4.2: verify Date Of Birth Check Box Unselected");
		adminstrationStudiesCustomPage.verifyDateOfBirthCHKBOXUnselected();
		
		reportLog("5.0: Select Date Of Birth option");
		adminstrationStudiesCustomPage.checkDateOfBirthCheckBox();
		
		reportLog("5.1: Verify Date Of Birth CHKBOX Selected");
		adminstrationStudiesCustomPage.verifyDateOfBirthCheckBoxIsSelected();
		
		reportLog("5.2: verify Year Of Birth Check Box Unselected");
		adminstrationStudiesCustomPage.verifyYearOfBirthCHKBOXUnselected();
				
		reportLog("6.0: Select Year Of Birth option again");
		adminstrationStudiesCustomPage.checkYearOfBirthCHKBOXCheckBox();
		
		reportLog("6.1: Verify year Of Birth CHKBOX Selected");
		adminstrationStudiesCustomPage.verifyYearOfBirthCHKBOXSelected();

		reportLog("7.0: Click on Cancel button to apply the changes- Сhanges are not saved");
		adminstrationStudiesCustomPage.clickOnCancelBtn();
		
		reportLog("7.1: Verify Year of Birth ChkBOX UNselected");
		adminstrationStudiesCustomPage.verifyYearOfBirthCHKBOXUnselected();
		
		reportLog("8.0: click on Custom Page and verify Custom Page in edit mode");
		adminstrationStudiesCustomPage.customPageInEditMode();

		reportLog("8.1: Click on year Of Birth CHKBOX to check the checkbox");
		adminstrationStudiesCustomPage.checkYearOfBirthCHKBOXCheckBox();

		reportLog("8.2: Navigate to study dashboard");
		adminstrationStudiesCustomPage.navigateToSubTabs(Constants.SubTabsLanguages, AdministrationStudiesPage.class);
		
		reportLog("8.3: Verifying Dialog Displayed Mesage --Confirm dialog displayed saying that 'There are unsaved changes. Do you want to continue and lose them?'");
		adminstrationStudiesCustomPage.verifyMessage(message);
		
		reportLog("9.0: Select Yes option" );
		adminstrationStudiesCustomPage.clickOnConfirmation("Yes");
		
		reportLog("9.1: Navigate to Custom Tab");
		adminstrationStudiesCustomPage.navigateToSubTabs(Constants.SubTabCustom, AdministrationStudiesCustomPage.class);
		
		reportLog("9.2: Verify Custom Page");
		adminstrationStudiesCustomPage.verifyCustomPage();
		
		reportLog("9.3: Year Of Birth CHKBOX UnSelected");
		adminstrationStudiesCustomPage.checkYearOfBirthCHKBOXCheckBox();
		
		reportLog("10.0: click on Custom Page and verify Custom Page in edit mode");
		adminstrationStudiesCustomPage.customPageInEditMode();

		reportLog("10.1: Click on year Of Birth CHKBOX to check the checkbox");
		adminstrationStudiesCustomPage.checkYearOfBirthCHKBOXCheckBox();
		
		reportLog("10.2: Click on Save Button");
		adminstrationStudiesCustomPage.clickOnSaveBtn();
		
		reportLog("10.3: Verifying Year Of birth CheckBOX Selected");
		adminstrationStudiesCustomPage.verifyYearOfBirthCHKBOXSelected();
		
		reportLog("11.0: Unselecting Year Of birth CheckNOx Again");
		adminstrationStudiesCustomPage.unselectYearOfBirthChkBx();
		adminstrationStudiesCustomPage.clickOnSaveBtn();
		
		reportLog("11.0 LogOut Application");
		loginPage.logoutApplication();
		
		reportLog("11.1 Verify LogOut ");
		loginPage.verifyUserLogout();
	}
	}


