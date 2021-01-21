/**
 *@author 
* @date 26/06/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-2656   Test Case Name: Certification Card - View completed Training- V18
*  pre-qualification :1. At least on Rater exists
                      2. At least one General training is completed by the Rater Pr#1. Training's assets previously were not
                       completed by the Rater. Training contains at least 2 events:
                      2.1 Event 1 contains an Asset
                      2.2 Event 2 contains Course with 2 asset
                      3. At least one Form based training is failed by the Rater Pr#1. Training's assets previously were not
                       completed by the Rater. Training contains at least 2 events:
                      3.1 Event 1 contains an Asset and it's passed
                      3.2 Event 2 contains Course with 2 asset and it's failed
                      4. At least one training is assigned to the Rater Pr#1 which is not started
* ======================================================================================================================================= 
*/package net.medavante.regression.testscripts.r4suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_2656_CertificationCardViewCompletedTraining_SIP extends BaseTest {

	private String trainingName="Training";
	
    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_2656_CertificationCardViewCompletedTraining_SIP(String browser) {
		super(browser);
	}

    @BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}
    
    @Test(description="Certification Card - View completed Training- V18")
    public void R4_FPTC_2656_CertificationCardViewCompletedTraining() throws InterruptedException
    { 
    	
    	reportLog("2.0: Log in to the Portal as User Pr.#1");
		dashBoardPage = loginPage.loginInApplication(RaterCertificationCardView, VU_AT_Password);

		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to user`s profile");
		raterProfilePage = dashBoardPage.navigateToRaterProfile(Constants.RaterCompleteName);
		
		reportLog("3.1: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();
		
		reportLog("3.2: Select the training Pr#2");
		raterProfilePage.selectTraining(Constants.Training_Name);
		
		reportLog("3.3: Training name is highlighted as a hyperlink");
		raterProfilePage.verifyTrainingIsDisplayedAsHyperlink(Constants.Training_Name);
		
		reportLog("4.0: Click on the Training name");
		raterProfilePage.clickToOpenOptionsForCompletedTraining();
		
		reportLog("4.1: View completed Training modal window is displayed with such fields:");
		reportLog("Dialog window header - [Training name] Pr#2");
		raterProfilePage.completedTrainingModalWindowIsDisplayed(Constants.Training_Name);

		reportLog("Name - [Training Display name] Pr#2");
		raterProfilePage.verifyRespectiveFieldIsDisplayed(Constants.fieldName,Constants.Training_Name);
		
		reportLog("Description - [Training Display Description] Pr#2 (if exists)");
		raterProfilePage.verifyRespectiveFieldIsDisplayed(Constants.fieldName_1,Constants.TrainingDescription);
	
		reportLog("Training materials to view:");
		raterProfilePage.verifyMessageDisplayed();
		
		reportLog("Event number");
		raterProfilePage.verifyTrainingEventNoIsDisplayed();
		
		reportLog("Training name");
		raterProfilePage.verifyTrainingNameIsDisplayed(Constants.Training_Name);
		
		reportLog("Option to view Training name tool-tip"); 
		raterProfilePage.verifyOptionToViewTrainingToolTipIsDisplayed(Constants.Training_Name);
		
		reportLog("Training Completed date"); 
		raterProfilePage.verifyCompletedDateIsDisplayed();
		
		reportLog("Training Result"); 
		raterProfilePage.verifyTrainingResultIsDisplayed();
		
		reportLog("List of assets completed by Rater in scope of this event - order by completed date desc");
		raterProfilePage.verifyListOfCompletedAssetIsDisplayed();
		
		reportLog("Asset name - hyperlink to view asset");
		raterProfilePage.verifyAssetAsHyperLinkDisplayed(Constants.Asset_Name);
		
		reportLog("5.0: Click on the option to view Training name tool-tip");
		raterProfilePage.clickOnTheTrainingToolTipOption(Constants.Training_Name);
		
		reportLog("5.1: Tool-tip is displayed with such information:"); 
		reportLog("Training Name"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Training_Name);
		
		reportLog("Language"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Language_Asset);

		reportLog("Attempts"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Attempts);

		reportLog("Result"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Completed_Status);

		reportLog("Result Date");
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.result_date);
		
		reportLog("6.0: Hover cursor to Asset name");
		raterProfilePage.hoverOverAssetToolTip(Constants.Asset_Name);
		
		reportLog("6.1: Asset name tool tip is displayed with such information:"); 
		reportLog("Asset Name"); 
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Asset_Name);

		reportLog("Language"); 
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Language_Asset);

		reportLog("Asset Version"); 
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.asset_version);

		reportLog("Result (Passed/Failed, Completed or Meets criteria)"); 
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Completed_Status);

		reportLog("Result Date");
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.result_date_1);
		
		reportLog("7.0: Click on the Asset name Pr#2.1");
		raterProfilePage.clickOntheAssetName(Constants.Asset_Name_1);
		
		reportLog("7.1: A new Browser window with Asset Pr#2.1 is opened");
		String parentwin = switchToChildWindow();
		switchParentWindowByClosingChild(parentwin);
		
		reportLog("8.0: Navigate to the Rater's profile");
		raterProfilePage.closeTrainnigCertificate();
		raterProfilePage.closeCertificationCard();
		
		reportLog("8.1: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();

		reportLog("8.2: Select the Training Pr#3");
		raterProfilePage.selectTraining(Constants.Training_Name_1);

		// Need to update TestCase For these below steps
		
		reportLog("8.3: Training name is highlighted as a hyperlink");
		raterProfilePage.verifyTrainingIsDisplayed(Constants.Training_Name_1);
		
		reportLog("9.0: Click on the Training name");
		raterProfilePage.clickToOpenOptionsForCompletedTraining();

		//  step no 8.3 & 9.0 needs to be updated
		
		reportLog("9.1: View completed Training modal window is displayed");
		raterProfilePage.completedTrainingModalWindowIsDisplayed(trainingName);

		reportLog("9.2: Training Result is passed for the event Pr#3.1");
		raterProfilePage.verifyTrainingResult(Constants.Asset_Name_2,Constants.Completed_Status);
		
		reportLog("9.3: Training Result is failed for the event Pr#3.2");
		raterProfilePage.verifyTrainingResult(Constants.Asset_Name_3,Constants.Status_Failed);

		reportLog("10.0: Select an option to view Training name tool-tip fro the first event Pr#3.1");
		raterProfilePage.hoverOverAssetToolTip(Constants.Asset_Name_2);

		reportLog("10.1: Tool-tip is displayed");
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Asset_Name_2);

		reportLog("10.2: Result is passed");
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Completed_Status);

		reportLog("11.0: Select an option to view Training name tool-tip for the second event Pr#3.2");
		raterProfilePage.hoverOverAssetToolTip(Constants.Asset_Name_3);

		reportLog("11.1: Tool-tip is displayed");
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Asset_Name_3);

		reportLog("11.2: Result is failed");
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Status_Failed);

		reportLog("12.0: Navigate to the Rater's profile");
		raterProfilePage.closeTrainnigCertificate();
		raterProfilePage.closeCertificationCard();
		
		reportLog("12.1: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();

		reportLog("12.2: Select the Training Pr#4");
		raterProfilePage.selectTraining(Constants.TrainingName);

		reportLog("12.3: Training name is not highlighted as a hyperlink");
		raterProfilePage.verifyTrainingIsDisplayed(Constants.TrainingName);
		raterProfilePage.closeCertificationCard();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
    }
}
