/**
 *@author 
* @date 13/05/2020
* ===============================================================================================================================
*  Test Case Id: FP-TC-2796 || Test Case Name: Site Rater's Profile - e-Learning Brief- V22
*  pre-qualification : 1. At least 1 Study/Site exists for test with enabled and configured e-Learning Brief training
                       2. At least 1 Site Rater exists for test and has 'Qualified' status for 1 Qualification Form group in Study Pr.#1
                       3. e-Learning Brief training exists and is associated with Study Pr.#1
                       4. At least 2 Trainings are associated with Study Pr.#1

* ================================================================================================================================ 
*/package net.medavante.regression.testscripts.r4suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_2796_SiteRatersProfileELearningBrief_SIP extends BaseTest {
	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_2796_SiteRatersProfileELearningBrief_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());

	}
	
	@Test(description="FP-TC-2796--Site Rater's Profile - e-Learning Brief- V22")
	public void R4_FPTC_2796_SiteRatersProfileELearningBrief()
	{
		
		reportLog("2.0:  Log in to the Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(RaterForELearning, VU_AT_Password);

		reportLog("2.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("2.2: Navigate to user`s profile");
		raterProfilePage = dashBoardPage.navigateToRaterProfile(Constants.RaterName_1);
		
		reportLog("2.3: Site Rater's profile is displayed");
		raterProfilePage.verifyRaterProfileIsDisplayed();
		
		reportLog("2.4: Only e-Learning Brief training Pr.#3 for Study Pr.#1 is displayed in Certification section");
		raterProfilePage.verifyELearningTrainingDisplayed(Constants.RaterTrainingName);
		
		reportLog("3.0: Select the e-Learning Brief training Pr.#3");
		raterProfilePage.selectTraining(Constants.RaterTrainingName);
		
		reportLog("3.1: Open the training and Start");
		raterProfilePage.proceedToStartTraining();
		
		reportLog("3.2: Modal window to start training is displayed with:");
		reportLog("Training display name");
		reportLog("Description");
	    reportLog("Option to select language (if more than 1 language configured)");
		reportLog("Option to Launch Training");
		reportLog("Options to Close the modal window");
		reportLog("Language is selected");
		
		reportLog("3.3: Select any language (in case of multiple languages)");

		reportLog("4.0: Select to Launch Training");
		
		reportLog("4.1: Training is launched");
		reportLog("4.2: Asset is opened in the new tab");
		
		
		reportLog("5.0: Close Asset without completion");
		reportLog("5.1: Training has Continue control");
		reportLog("5.2: Close modal window");
		
		
		/***
		 * ================================================
		 * 
		 * Remain Incomplete 
		 *  Out Of Scope(reason updated in sheet)
		 * =================================================
		 */
		
		
		
		
	}
}
