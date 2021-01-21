package net.medavante.mobile.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;

public class FPTC_839_VerifyShowTabPagesBasedOnStudyConfigParticipant_SIP extends BaseTest {

	
	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_839_VerifyShowTabPagesBasedOnStudyConfigParticipant_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-839 Test Case Name: Show tab pages based on study config_participant  
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-839_Show tab pages based on study config_participant ", groups = { "Mobile" })
	public void FPTC_839_VerifyShowTabPagesBasedOnStudyConfigParticipant() throws Exception {

		reportLog("1: MobileView Logged in user is the Participant");
		mobileLoginPage = androidSetUp();

		reportLog("2.1: MobileView: Signin page shall be displayed with an instruction message.");
		mobileLoginPage.verifySignInScreenDisplayed();
		
		reportLog("2.2: Enter valid pin and then click on submit button.");
		dashborad = mobileLoginPage.loginUser(mobilePin);
		
		reportLog("2.3: Home page shall be displayed.");
		dashborad.verifyHomePageDisplay();		
		
		reportLog("3: check the top menu tabs");
		dashborad.verifyTopMenuOptions();
		
		reportLog("3.1: For the logged in participant, following tabs shall be displayed Home, Questionnaire ,Messages and log an Event.");
		dashborad.clickOnHomeTab();
		dashborad.clickOnQuestionnairesTab();
		dashborad.clickOnMessageTab();
		dashborad.clickOnLogAnEventTab();		
				
		reportLog("4: Click on side menu in the top left corner of the home page.");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		
		reportLog("4.1: Exit application.");
		dashborad.clickOnExitApplication();
		
		
		
	}

}
