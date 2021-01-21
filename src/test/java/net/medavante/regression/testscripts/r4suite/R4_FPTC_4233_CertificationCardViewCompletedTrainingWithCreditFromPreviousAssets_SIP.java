/**
 *@author 
* @date 30/06/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-4233   
*  Test Case Name: Certification Card - View completed Training with credit from previous Assets- V14
*  pre-qualification :
1. At least one Rater/Clinician exists
2. At least one Training is completed by the Site Rater Pr#1 in a scope of Study. Training contains at least 2 events:
2.1 Event 1 contains an Asset. A credit was given for the Asset
2.1.1 The Asset was previously completed by the Rater in another training.
2.2 Event 2 contains Course with 2 asset.
A credit was given for one of the assets in the course
2.2.1. The Asset was previously completed by the Rater in another training
3. The user exists with the claim canAccessMedavantePortal
4. The user exists without the claim canAccessMedavantePortal (siteperson)	
* ======================================================================================================================================= 
*/package net.medavante.regression.testscripts.r4suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_4233_CertificationCardViewCompletedTrainingWithCreditFromPreviousAssets_SIP extends BaseTest {

	private String study;
	
    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
    public R4_FPTC_4233_CertificationCardViewCompletedTrainingWithCreditFromPreviousAssets_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study=properties.getProperty("Study1089");
		}
	
	@Test(description="FP-TC-4233--Certification Card - View completed Training with credit from previous Assets- V14")
	public void R4_FPTC_4233_CertificationCardViewCompletedTrainingWithCreditFromPreviousAssets() throws Exception
	{
		reportLog("2.0: Log in to the Portal as User Pr#1");
		dashBoardPage = loginPage.loginInApplication(RaterCertificationCardView, VU_AT_Password);

		reportLog("2.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to the User profile");
		raterProfilePage = dashBoardPage.navigateToRaterProfile(Constants.RaterCompleteName);

		reportLog("3.1: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();

		reportLog("3.2: Select the training Pr#2");
		raterProfilePage.selectTraining(Constants.Trainingname);

		reportLog("3.3: Training name is highlighted as a hyperlink");
		raterProfilePage.verifyTrainingIsDisplayedAsHyperlink(Constants.Trainingname);

		reportLog("4.0: Click on the Training name");
		raterProfilePage.clickToOpenOptionsForCompletedTraining();
		
		reportLog("4.1: View completed Training modal window is displayed with such fields:");
		reportLog("Dialog window header - [Training name] Pr#2");
		raterProfilePage.completedTrainingModalWindowIsDisplayed(Constants.Trainingname);

		reportLog("Name - [Training Display name] Pr#2");
		raterProfilePage.verifyRespectiveFieldIsDisplayed(Constants.fieldName,Constants.Trainingname);
		
		reportLog("Description - [Training Display Description] Pr#2 (if exists)");
		raterProfilePage.verifyRespectiveFieldIsDisplayed(Constants.fieldName_1,Constants.Trainingdescription);
	
		reportLog("Training materials to view:");
		raterProfilePage.verifyMessageDisplayed();
		
		reportLog("Event number");
		raterProfilePage.verifyTrainingEventNoIsDisplayed();
		
		reportLog("Training name");
		raterProfilePage.verifyTrainingNameIsDisplayed(Constants.Trainingname);
		
		reportLog("Option to view Training name tool-tip"); 
		raterProfilePage.verifyOptionToViewTrainingToolTipIsDisplayed(Constants.Trainingname);
		
		reportLog("Training Completed date"); 
		raterProfilePage.verifyCompletedDateIsDisplayed();
		
		reportLog("Training Result"); 
		raterProfilePage.verifyTrainingResultIsDisplayed();
		
		reportLog("List of assets completed by Rater in scope of this event - order by completed date desc");
		raterProfilePage.verifyListOfCompletedAssetIsDisplayed();
		
		reportLog("Asset name - hyperlink to view asset");
		raterProfilePage.verifyAssetAsHyperLinkDisplayed(Constants.Asset_Name_1);
		
		reportLog("4.2: Asset Pr#2.1 has a Credit label with icon");
		raterProfilePage.verifyAssetWithCreditLabel(Constants.Asset_name);
		
		reportLog("4.3: One of the Asset Pr#2.2 has a Credit label");
		raterProfilePage.verifyAssetWithCreditLabel(Constants.Asset_Name_1);
		
		reportLog("5.0: Click on the option to view Training name tool-tip");
		raterProfilePage.clickOnTheTrainingToolTipOption(Constants.Trainingname);
		
		reportLog("5.1: Tool-tip is displayed with such information:"); 
		reportLog("Training Name"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Trainingname);
		
		reportLog("Language"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Language_Asset);

		reportLog("Attempts"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Attempts);

		reportLog("Result"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Completed_Status);

		reportLog("Result Date");
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.result_date_2);

		reportLog("6.0: Hover cursor to Asset name Pr#2.1");
		raterProfilePage.hoverOverAssetToolTip(Constants.Asset_Name_1);
		
		reportLog("6.1: Asset name tooltip is displayed with such information (data from the previously completed asset):"); 
		reportLog("Asset Name"); 
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Asset_Name_1);

		reportLog("Language"); 
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Language_Asset);

		reportLog("Asset Version"); 
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.asset_version);

		reportLog("Result (Passed/Failed, Completed or Meets criteria)"); 
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.Completed_Status);

		reportLog("Result Date");
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.result_date_1);

		reportLog("7.0: Hover cursor over the icon near the Сredit label");
		raterProfilePage.hoverCursorOverRespectiveToolTip(Constants.Asset_Name_1);
		
		reportLog("7.1: Tool-tip is displayed with such information:"); 
		reportLog("Training name Pr.#2.1.1 or 2.2.1"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Training_Name);

		reportLog("Result date");
		raterProfilePage.verifyRespectiveOptionOnAssetToolTip(Constants.result_date_1);

		reportLog("8.0: Click on the Asset name Pr#2.1");
		raterProfilePage.clickOntheAssetName(Constants.Asset_Name_1);

		reportLog("8.1: A new Browser window with Asset Pr#2.1 is opened");
		String parentwin = switchToChildWindow();
		switchParentWindowByClosingChild(parentwin);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();

		reportLog("9.0: Log in to the MA Portal as User Pr#3");
		dashBoardPage = loginPage.maLogin(SuperAdminUN, SuperAdminPW);

		reportLog("9.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("10.0: Navigate to the rater/clinician profile page Pr#1");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
					Constants.NavigateText, Constants.SubTabUniversity);
        ratersDetailsPage.selectStudyWithAllSites(study);
        ratersDetailsPage.navigateToOnBoardingTab();
        ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterCompleteName);
        ratersDetailsPage.NavigateToRaterProfile(Constants.RaterCompleteName);
		 
        reportLog("10.1: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();
		
		reportLog("10.2: Select the training Pr#2");
		raterProfilePage.selectTraining(Constants.Trainingname);

		reportLog("10.3: Training name is highlighted as a hyperlink");
		raterProfilePage.verifyTrainingIsDisplayedAsHyperlink(Constants.Trainingname);

		reportLog("11.0: Click on the Training name");
		raterProfilePage.clickToOpenOptionsForCompletedTraining();
		
		reportLog("11.1: Hover cursor over the icon near the Сredit label");
		raterProfilePage.hoverCursorOverRespectiveToolTip(Constants.Asset_name);
		
		reportLog("11.2: Tool-tip is displayed with such information:"); 
		reportLog("Training name Pr.#2.1.1 or 2.2.1"); 
		raterProfilePage.verifyRespectiveOptionOnTrainingToolTip(Constants.Training_Name);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();

		reportLog("12.0: Log in to the Portal as User Pr#4");
		dashBoardPage = loginPage.siteLogin(RaterViewOnly, VU_AT_Password);

		reportLog("12.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("13.0: Navigate to the rater/clinician profile page Pr#1");
		//dashBoardPage.selectStudyWithAllSites(study);
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
					Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudyWithAllSites(study);
        ratersDetailsPage.navigateToOnBoardingTab();
        ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterCompleteName);
        ratersDetailsPage.NavigateToRaterProfile(Constants.RaterCompleteName);
		 
        reportLog("13.1: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();
		
		reportLog("13.2: Select the training Pr#2");
		raterProfilePage.selectTraining(Constants.Trainingname);

		reportLog("13.3: Training name is highlighted as a hyperlink");
		raterProfilePage.verifyTrainingIsDisplayedAsHyperlink(Constants.Trainingname);

		reportLog("14.0:Click on the Training name");
		raterProfilePage.clickToOpenOptionsForCompletedTraining();
		
		reportLog("14.1: The icon is NOT displayed near the Credit label");
		raterProfilePage.verifyToolTipIconIsNoTDisplayedNearCreditLabel(Constants.Asset_name);
		
		reportLog("14.2: Tool-tip is NOT displayed for credit label");
        raterProfilePage.verifyToolTipNearCreditLabelNotDisplayed();
        
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
	}

}
