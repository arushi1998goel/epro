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

public class R3_FPTC_1961_UnlockStudy_SIP extends BaseTest {

	 @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
		public R3_FPTC_1961_UnlockStudy_SIP(String browser) {
			super(browser);
			
		}
	    
	    @BeforeMethod
		public void getTestData() throws Exception {
			siteRaterAccess="SiteRater"+generateRandomString(6);
			System.setProperty("className", getClass().getSimpleName());
			Properties properties = Configuration.readTestData("RegressionTestData");
			studyName = properties.getProperty("Study_FPTC_1961");
			}
	    
	    
	    /***
		 * ====================================================================================================================
		 * Test Case Id: FPTC_1961 Test Case Name: To show the ability to unlock administration configuration of the Study
		 *
		 * ====================================================================================================================
		 * 
		 * @throws InterruptedException
		 *
		 */
	    
	    @Test(description = "R3_FPTC_1961_UnlockStudy", groups = { "R3" })
		public void FPTC_1961_verifyUnlockStudy() throws Exception{
	    	
	    	reportLog("2.1: Log in to Portal with PR#1.");
	    	dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
			dashBoardPage.verifyMedavantePortalPage();

	    	reportLog("2.2: Navigate to Administration -> Studies.");
	    	adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
					AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

	    	reportLog("2.3: Select a Study from the PR#2 ");
	    	adminstrationStudiesPage=adminstrationOrganizationPage.navigateToStudies();
	    	adminstrationStudiesPage.searchAndClickOnStudy(studyName);
	    	
	    	reportLog("2.4: General tab displayed for a Study from the PR#2 with active Unlock control");
	    	adminstrationStudiesPage.verifyTabIsDisplayed(Constants.GeneralTab);
	        adminstrationStudiesPage.verifyUnlockControlIsActive();
	    	
	    	reportLog("3.1: Click the Unlock control");
	    	adminstrationStudiesPage.clickOnUnlockControlOrLockControl();
 
	    	reportLog("3.2: A pop-up confirmation displayed with the text : Are you sure you want to unlock study configuration?");
	    	adminstrationStudiesPage.verifyPopUpWhenClickOnUnlockControl(Constants.popUpTitle, Constants.confirmationText);
	    	
	    	reportLog("4.1: Select No option");
	    	adminstrationStudiesPage.clickOnNoOnConfirmationPopupOfUnlockControl();
	    	
	    	reportLog("4.2: No changes applied for the General tab and Study");
	    	adminstrationStudiesPage.verifyNoChangeWhenClickOnNoButtonOfConfirmationPopupOfUnlockControl();
	    	
	    	reportLog("5.1: Click the Unlock control and select Yes option");
	    	adminstrationStudiesPage.clickOnUnlockControlOrLockControl();
	    	adminstrationStudiesPage.clickOnYesOnConfirmationPopupOfUnlockControl();
	    	adminstrationStudiesPage.refreshPage();

	    	reportLog("5.2: System unlocks the Study configuration.");
	    	adminstrationStudiesPage.verifySystemAfterUnlockTheStudyConfiguration();
	    	
	    	reportLog("5.3: Lock icon disappears from the Study row in the Study list.");
	    	adminstrationStudiesPage.searchAndClickOnStudy(studyName);
	    	adminstrationStudiesPage.verifyLockIconDisappearsFromStudyRowInTheStudyList();
	    	
	    	/*Lock control is not hiding on UI*/
	    	reportLog("5.4: Lock control is hidden from the General Study tab for user from PR#1");
	    	
	    	reportLog(" Unlocked study is locked again.");
	    	adminstrationStudiesPage.verifyUnlockedStudyIsLocked();
	    	
	    	reportLog(" Logout application");
	 		loginPage.logoutApplication();

	 		reportLog(" Verify user is logout");
	 		loginPage.verifyUserLogout();
	    }

}
