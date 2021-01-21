/**
 *@author 
* @date 18/06/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-4232   Test Case Name: Site rater - Launch training (Credit for asset)- V5
*  pre-qualification :1. Site rater exists for the test
                      2. Site rater has assigned multiple trainings in the scope of study and no study assigned:
                      2.1. Completed or In progress training with the asset in status Completed/Passed/Meets criteria
                      2.2. Not started training with event/course: the asset_1 from Pr.#2.1 + asset_2 (any other asset)
* ======================================================================================================================================= 
*/package net.medavante.regression.testscripts.r4suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_4232_SiteRaterLaunchTrainingCreditForAsset_SIP extends BaseTest {


    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_4232_SiteRaterLaunchTrainingCreditForAsset_SIP(String browser) {
		super(browser);
	}
    
    @BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}
	
	@Test(description="FP-TC-4232---Site rater - Launch training (Credit for asset)- V7")
	public void R4_FPTC_4232_SiteRaterLaunchTrainingCreditForAsset() throws InterruptedException
	{
		
		reportLog("2.0: Log in to the Portal as User Pr.#1");
		dashBoardPage = loginPage.loginInApplication(RaterAssetCredit_Test, VU_AT_Password);

		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.2: The home page is displayed");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("2.3: The Training Pr#2.2 is displayed on the Home page");
		dashBoardPage.verifyRespectiveTrainingIsDisplayed(Constants.TrainingName);
	 	
		reportLog("3.0: Select an action to start the Training Pr#2.2");
		dashBoardPage.selectActionToStartTraining(Constants.TrainingName);
		
		reportLog("3.1: Training is started from the asset_2");
		dashBoardPage.verifyTrainingIsStarted(Constants.TrainingName);	
		
		reportLog("3.2: Launch the training");
		reportLog("4.0: Close the asset_2");
		dashBoardPage.launchTrainingAndCloseAsset();
	
		reportLog("4.1: Asset_1 Pr#2.1 is displayed in the list of completed assets");
		dashBoardPage.verifyRespectiveTrainingIsDisplayed(Constants.TrainingName);
		
		reportLog("4.2: Credit label is displayed next to asset_1");
		dashBoardPage.verifyCreditLabelIsDisplayedNextToAssetName(Constants.assetName);
		dashBoardPage.closeAsset();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		
	}


}
