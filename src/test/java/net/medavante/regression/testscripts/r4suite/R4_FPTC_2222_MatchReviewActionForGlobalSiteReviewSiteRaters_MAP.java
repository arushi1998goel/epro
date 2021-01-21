/**
 *@author 
* @date 29/05/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-2222 
*  Test Case Name:Match Review action for Global/Site Review Site Raters: action availability by roles- V11
*  pre-qualification :
1. At least two Studies and Sites exist for the test:
1.1 Study_1 with On-boarding configured by MA-PP and Sites.
1.2 Study_2 with On-boarding configured by MA-PP only.
2. At least two Raters associated with the Study/Site Pr. #1.1 and has the following statuses:
2.1 Rater_1 with Global Review status
2.2 Rater_2 with Site Review status
3. At least two Raters associated with the Study/Site Pr. #1.2 and has the following statuses:
3.1 Rater_3 with Global Review status
3.2 Rater_4 with Site Review status
4. MA User associated with the Study/Site Pr. #1 and has claim to allow Review User Match exists (canManageSiteRaters)
5. Site Manager user associated with the Studies/Sites Pr. #1 and has claim to allow Review User Match exists (canManageStudySiteRaters)
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

public class R4_FPTC_2222_MatchReviewActionForGlobalSiteReviewSiteRaters_MAP extends BaseTest {

	private String study,studyNew;

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_2222_MatchReviewActionForGlobalSiteReviewSiteRaters_MAP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("Study1089");
		studyNew=properties.getProperty("Study2650");
	}

	@Test(description="Match Review action for Global/Site Review Site Raters: action availability by roles- V11")
	public void R4_FPTC_2222_MatchReviewActionForGlobalSiteReviewSiteRaters() throws Exception
	{
		
		reportLog("2.0: Log in to MA Portal as User PR#4");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);

		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to University grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		
		reportLog("3.1: Select Study and Site Pr. #1.1");
		ratersDetailsPage.selectStudy(study,Constants.ATAssignedRater_10);

		reportLog("3.2: Site Raters/Clinicians listing screen is displayed");
		ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

		reportLog("4.0: From the Filter select: On-boarding grid -> All");
        ratersDetailsPage.navigateToOnBoardingMenu();

		reportLog("4.1: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingGridIsDisplayed();
		
		reportLog("5.0: Select row for Rater Pr. #2.1");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.GlobalReview_1);
		ratersDetailsPage.selectRater(Constants.GlobalReview_1);
		
		reportLog("5.1: Rater Pr. #2.1 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.GlobalReview_1);

		reportLog("5.2: Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();

		reportLog("6.0: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

		reportLog("6.1: Match Review option is displayed");
		ratersDetailsPage.verifyMatchReviewOptionIsDisplayed();
		
		reportLog("7.0: Deselect Rater");
		ratersDetailsPage.DeSelectRater(Constants.GlobalReview_1);

		reportLog("7.1: Select row for Rater Pr. #2.2");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.SiteReview_1);
		ratersDetailsPage.selectRater(Constants.SiteReview_1);
		
		reportLog("7.2: Rater Pr. #2.2 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.SiteReview_1);
		
		reportLog("7.3: Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();

		reportLog("8.0: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

		reportLog("8.1: Match Review option is displayed");
		ratersDetailsPage.verifyMatchReviewOptionIsDisplayed();

		reportLog("9.0: Select Study and Site Pr. #1.2");
		ratersDetailsPage.reSelectstudy(study);
		ratersDetailsPage.selectStudy(studyNew, Constants.ATAssignedRater_10);

		reportLog("9.1: Select row for Rater Pr.# 3.1");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.GlobalReview_2);
		ratersDetailsPage.selectRater(Constants.GlobalReview_2);

		reportLog("9.2: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

		reportLog("9.3: Match Review option is displayed");
		ratersDetailsPage.verifyMatchReviewOptionIsDisplayed();

		reportLog("10.0: Deselect Rater");
		ratersDetailsPage.DeSelectRater(Constants.GlobalReview_2);

		reportLog("10.1: Select row for Rater Pr.# 3.2");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.SiteReview_2);
		ratersDetailsPage.selectRater(Constants.SiteReview_2);

		reportLog("10.2: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

		reportLog("10.3: Match Review option is displayed");
		ratersDetailsPage.verifyMatchReviewOptionIsDisplayed();
		
		reportLog("Logout application");
		ratersDetailsPage.DismissAlertMessage();
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		reportLog("11.0: Log in to Site Portal as User Pr. #5");
		dashBoardPage = loginPage.siteLogin(AT_PRODProjectManager, AT_Password);

		reportLog("11.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("12.0: Navigate to University grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		
		reportLog("12.1: Select Study and Site Pr. #1 .1");
		ratersDetailsPage.selectStudy(study,Constants.ATAssignedRater_10);

		reportLog("12.2: Site Raters/Clinicians listing screen is displayed");
        ratersDetailsPage.verifyClinicianRatersListingScreenDisplayed();

        reportLog("13.0: From the Filter select: On-boarding grid -> All");
        ratersDetailsPage.navigateToOnBoardingMenu();

		reportLog("13.1: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingGridIsDisplayed();
		
		reportLog("14.0: Select row for Rater Pr. #2.1");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.GlobalReview_1);
		ratersDetailsPage.selectRater(Constants.GlobalReview_1);
		
		reportLog("14.1: Rater Pr. #2.1 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.GlobalReview_1);
        
        reportLog("14.2:  Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();

        reportLog("14.3: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

        reportLog("14.4:  Match Review option is NOT displayed");
        ratersDetailsPage.verifyMatchReviewOptionNotDisplayed();
        
        reportLog("15.0: Deselect Rate");
		ratersDetailsPage.DeSelectRater(Constants.GlobalReview_1);

        reportLog("15.1: Select row for the Rater Pr.#2.2");
        ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.SiteReview_1);
		ratersDetailsPage.selectRater(Constants.SiteReview_1);
		
        reportLog("15.2: Rater Pr. #2.2 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.SiteReview_1);

        reportLog("15.3: Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();

        reportLog("15.4: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

        reportLog("15.5: Match Review option is displayed");
        ratersDetailsPage.verifyMatchReviewOptionIsDisplayed();
        
        reportLog("16.0: Select Study and Site Pr. #1.2");
        ratersDetailsPage.reSelectstudy(study);
		ratersDetailsPage.selectStudy(studyNew, Constants.ATAssignedRater_10);

        reportLog("16.1: Select row for Rater Pr.# 3.1");
        ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.GlobalReview_2);
		ratersDetailsPage.selectRater(Constants.GlobalReview_2);
		
        reportLog("16.2: Rater Pr. #3.1 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.GlobalReview_2);

        reportLog("16.3: Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();

        reportLog("16.4: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

        reportLog("16.5: Match Review option is NOT displayed");
        ratersDetailsPage.verifyMatchReviewOptionNotDisplayed();

        reportLog("17.0: Deselect Rater");
		ratersDetailsPage.DeSelectRater(Constants.GlobalReview_2);

        reportLog("17.1: Select row for the Rater Pr.#3.2");
        ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.SiteReview_2);
		ratersDetailsPage.selectRater(Constants.SiteReview_2);
		
        reportLog("17.2: Rater Pr. #3.2 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.SiteReview_2);

        reportLog("17.3: Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();

        reportLog("17.4: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

        reportLog("17.5: Match Review option is NOT displayed");
        ratersDetailsPage.verifyMatchReviewOptionNotDisplayed();
        
        reportLog("Logout application");
		ratersDetailsPage.DismissAlertMessage();
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();
	}
}
