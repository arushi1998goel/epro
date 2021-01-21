package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class SFBTC_606_AbilityToViewRaterProfileFromStudyDash_SIP extends BaseTest {

	private String studyName; 	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public SFBTC_606_AbilityToViewRaterProfileFromStudyDash_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");		
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: SFB-TC-606 Test Case Name: Verify the ability to view the Rater profile from Study Dashboard
	 * ====================================================================================================================
	 */

	@Test(description = "SFB-TC-606 _Verify the ability to view the Rater profile from Study Dashboard", groups = { })
	public void SFBTC_606_VerifyAbilityToViewRaterProfileFromStudyDash() {
		
		reportLog("1.1 :Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3 : Navigate to dashboard where at least one study is displayed");
		studyNavigatorDashBoardPage= dashBoardPage.navigateToStudyNavigator();
		//studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.4: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();
		
		reportLog("1.5: 'Raters' icon is displayed with count of available Raters in the Study");
		studyNavigatorDashBoardPage.verifyRatersOptionDisplay();
		String ratersCounts = studyNavigatorDashBoardPage.ratersCountValue();
		System.out.println(ratersCounts);
		
		reportLog("2: Click on Raters icon");
		studyNavigatorDashBoardPage.clickOnRatersIcon();
		
		reportLog("2.1: Raters slide panel is displayed with list of available raters");
		studyNavigatorDashBoardPage.verifyRatersDetailsPanelIsOpened();
		studyNavigatorDashBoardPage.verifyRatersListCount(ratersCounts);
		
		reportLog("2.2: Count of assessments in Scheduled, Completed and Edit states are displayed for each available rater.");		

		reportLog("3: Click on a Rater");
		ratersDetailsPage=studyNavigatorDashBoardPage.selectRater(Constants.RaterName_15);
		
		reportLog("3.1: Rater Details page is opened.");
		ratersDetailsPage.verifyRaterDetailPageDisplayed();
		ratersDetailsPage.verifyRaterName(Constants.RaterName_15);
		
		reportLog("3.2: Sliding panel remains active");
		ratersDetailsPage.verifyRatersDetailsPanelIsOpened();
		
		reportLog("3.3:Logout application");
		loginPage.logoutApplication();

		reportLog("3.4:Verify user is logout");
		loginPage.verifyUserLogout();
		
				
		
	}

}
