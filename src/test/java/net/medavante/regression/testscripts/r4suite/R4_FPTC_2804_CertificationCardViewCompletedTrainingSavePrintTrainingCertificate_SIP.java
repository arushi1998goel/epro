/**
 *@author 
* @date 30/04/2020
* ===============================================================================================================================
*  Test Case Id: FP-TC-2804 || Test Case Name:Certification card - View completed Training - Save/Print Training Certificate- V5
*  	 * pre-qualification :  1. At least one Rater exists
                            2. At least one training is completed by Rater Pr#1 with at least two assets
                            3. At least one training is passed by Rater Pr#1
                            4. At least one training is failed by Rater Pr#1	
* ================================================================================================================================ 
*/package net.medavante.regression.testscripts.r4suite;

import java.awt.AWTException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R4_FPTC_2804_CertificationCardViewCompletedTrainingSavePrintTrainingCertificate_SIP extends BaseTest {
	
	private String value;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_2804_CertificationCardViewCompletedTrainingSavePrintTrainingCertificate_SIP(String browser) {
		super(browser);
	}

	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}
	 
	@Test(description="FP-TC-2804---Certification card - View completed Training - Save/Print Training Certificate- V5")
	public void R4_FPTC_2804_CertificationCardViewCompletedTraining() throws InterruptedException, AWTException
	{ 
		reportLog("2.0:  Log in as a User Pr.#1");
		dashBoardPage = loginPage.loginInApplication(RaterForCertificationCard, CertificationRaterPWD_Test);

		reportLog("2.0.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("3.0: Navigate to user`s profile");
		raterProfilePage = dashBoardPage.navigateToRaterProfile(Constants.RaterName);
		
		reportLog("3.1: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();
		
		reportLog("3.2: Select the training Pr#2");
		raterProfilePage.selectTrainingWithTwoAssets();
		
		reportLog("3.3: Option to print certificate is displayed and available");
		raterProfilePage.clickToOpenOptionsForCompletedTraining();
		raterProfilePage.verifyOptionToPrintCertificateDisplayedAndActive();
		
		reportLog("4.0: Select option to print certificate");
		raterProfilePage.selectrOptiontoPrintCertificate();
		
		reportLog("4.1: Training certificate is displayed with frame across all page borders within appropriate application\r\n" + 
				"Following info is displayed:");
		reportLog("Rater/Clinician First Name");
		reportLog("Rater/Clinician Last Name"); 
		reportLog("Training Name");
		reportLog("Training Assets");
		reportLog("Completion Date");
		value = switchToChildWindow();
		raterProfilePage.cancelPrintOperation();
		raterProfilePage.verifyFrameAcrossPagesDisplayed();
		raterProfilePage.verifyRaterDetailsDisplayed(Constants.RaterName,Constants.Rater_Training,Constants.Training_completionDate);
		
		reportLog("5.0: Close Training certificate");
		switchParentWindowByClosingChild(value);
		raterProfilePage.closeTrainnigCertificate();

		reportLog("5.1: Close Certification card");
		raterProfilePage.closeCertificationCard();
		
		reportLog("5.2: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();
		
		reportLog("5.3: Select Training Pr#3");
		raterProfilePage.selectTraining(Constants.Training_Two);
		
		reportLog("5.4: Option to print certificate is displayed and available");
		raterProfilePage.clickToOpenOptionsForCompletedTraining();
		raterProfilePage.verifyOptionToPrintCertificateDisplayedAndActive();

		reportLog("6.0: Close Training certificate");
		raterProfilePage.closeTrainnigCertificate();

		reportLog("6.1: Close Certification card");
		raterProfilePage.closeCertificationCard();

		reportLog("6.2: Certification card is displayed");
		raterProfilePage.verifyCertificationCardDisplayed();
		
		reportLog("6.3: Select Training Pr#3");
		raterProfilePage.selectTraining(Constants.Training_Three);

		reportLog("6.4: Option to print certificate is not displayed");
		raterProfilePage.clickToOpenOptionsForCompletedTraining();
        raterProfilePage.verifyoptionToPrintCertificateNotDisplayed();
        
        reportLog("close all Configurations");
		raterProfilePage.closeTrainnigCertificate();
		raterProfilePage.closeCertificationCard();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout(); 
	}

}
