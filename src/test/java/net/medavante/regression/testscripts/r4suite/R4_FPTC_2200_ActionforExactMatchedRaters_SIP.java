/**
 *@author 
* @date 15/05/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-2200 || Test Case Name: Action for Exact Matched Raters: Global Review by Primary Email with >1 Site Raters- V34
*  pre-qualification : 1. At least 2 active Studies and Sites exists for the test
                       2. At least 2 Site Raters associated with the Study_1 and has Completed status with the same Primary email
                       3. At least 2 Site Raters associated with the Study_2 and have Global Review status.
                       3.1. 2 Site Raters have the same Primary e-mail as the Existing Site Rater Pr.#2 but different Study and Site
                       4. MA Admin User associated with both Studies PR#1 and has CanManageSiteRater claim
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

public class R4_FPTC_2200_ActionforExactMatchedRaters_SIP extends BaseTest {

	private String studyNew,phone=GenerateRandomNumber(5),phone_2=GenerateRandomNumber(5);
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_2200_ActionforExactMatchedRaters_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyNew = properties.getProperty("Study2180");
		
		reportLog("Creating Rater One For Prequisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudy(studyNew,Constants.ATAssignedRater_10);
		ratersDetailsPage.selectAddSiteControl();
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(Constants.email, Constants.FirstName,
				Constants.LastNAme);
		ratersDetailsPage.addAdditionalEmailAndPhoneDetails(Constants.additionalEmail, phone);
		ratersDetailsPage.selectFacility(Constants.facility, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectStudyFromStudyDropDownOptions(studyNew);
		ratersDetailsPage.searchAndSelectSite(Constants.ATAssignedRater_10, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();
		ratersDetailsPage.clickOnSkipReviewButton();
		
		reportLog("Creating Rater Two For Prequisite");

	    ratersDetailsPage.selectAddSiteControl();
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(Constants.email, Constants.FirstName,
					Constants.LastNAme_1);
		ratersDetailsPage.addAdditionalEmailAndPhoneDetails(Constants.additionalEmail_1, phone_2);
		ratersDetailsPage.selectFacility(Constants.facility, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectStudyFromStudyDropDownOptions(studyNew);
		ratersDetailsPage.searchAndSelectSite(Constants.ATAssignedRater_10, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();
		ratersDetailsPage.clickOnSkipReviewButton();
		ratersDetailsPage.navigateToOnBoardingMenu();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout(); 

	}
	
	@Test(description="FPTC_2200---Action for Exact Matched Raters: Global Review by Primary Email with >1 Site Raters- V34")
	public void R4_FPTC_2200_ActionforExactMatchedRaters() throws Exception
	{
		
		reportLog("2.0: Log in to MA Portal as User PR#4");
		dashBoardPage = loginPage.maLogin(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.2:  Select Navigate -> University -> Study_2 -> On-boarding grid -> All");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudy(studyNew,Constants.ATAssignedRater_10);
        ratersDetailsPage.navigateToOnBoardingMenu();
        
		reportLog("2.3: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingGridIsDisplayed();
		
		reportLog("3.0: Select row for Site Rater_1 PR#3");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_1);
		ratersDetailsPage.selectRater(Constants.RaterForReview_1);
		
		reportLog("3.1: Site Rater_1 PR#3 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.RaterForReview_1);

		reportLog("3.2: Actions control is displayed");
		ratersDetailsPage.verifyActionControlDropDownButton();
		
		reportLog("4.0: Select Actions drop-down menu");
		ratersDetailsPage.selectActionControl();

		reportLog("4.1: Select Match Review option");
		ratersDetailsPage.selectMatchReviewOption();
		
		reportLog("4.2: Users Match Review modal window is displayed with:");
		ratersDetailsPage.userMatchReviewWindowIsDisplayed();
		
		reportLog("4.2.1:  Information message "
		+ "'We have found users with the same Email(s) in our system. Please review them and choose the action to perform.' is displayed");
		ratersDetailsPage.InformationMessageIsDisplayed();
		
		reportLog("4.2.2: Following details are provided about User Under Review");
		reportLog("User Under Review title");
		reportLog("Primary Email");
		reportLog("Additional Emails");
		reportLog("First Name");
		reportLog("Last Name");
		reportLog("Phone");
		reportLog("Facilities - list of Facilities available for user");
		reportLog("Studies - list of Studies available for user");
		reportLog("Sites - comma-separated list");
		reportLog("Person Type");
		ratersDetailsPage.verifyUserUnderReviewPopupTitleAndRaterDetails();
		
		reportLog("4.2.3: Following details are provided about existing user");
		reportLog("Existing Users title");
		reportLog("Radio-button");
		reportLog("Primary Email");
		reportLog("Additional Emails");
		reportLog("First Name");
		reportLog("Last Name");
		reportLog("Phone");
		reportLog("Facilities - list of Facilities available for user");
		reportLog("Studies - list of Studies available for user");
		reportLog("Sites - comma-separated list");
		reportLog("Person Type - comma-separated list");
		reportLog("Status - Active");
		ratersDetailsPage.verifyExistingUserTitleRadioButton();
		ratersDetailsPage.verifyExistingUserDetails();
		
		reportLog("4.3: The following options are available:");
		reportLog("Merge Users with 'Choose the existing user from the list below to merge with:' info");
		ratersDetailsPage.verifyOptionToMergeUserWithFromListedUserIsDisplayed();
		
		reportLog("Discard User with 'Discard user under review and do not add anything to the system.' info");
		ratersDetailsPage.verifyOptionToDiscardUserUnderReviewWithDetailsDisplayed();
		
		reportLog("4.4: Option to close the dialog");
		ratersDetailsPage.verifyCloseOptionDisplayed();
		
		reportLog("4.5: Cancel option (displayed by default)");
		ratersDetailsPage.verifyCancelButtonDisplayedOnUserMatchReviewPopoup();
		
		reportLog("4.6: Site Rater PR#2 is displayed in the Existing Users section with highlighted matched users details");
		ratersDetailsPage.verifyfieldHighlightedForExistingUser(Constants.email, Constants.HighlightedColor);
		ratersDetailsPage.verifyExistingUserHighlightedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);

		reportLog("4.7: Site Rater_1 PR#3 is displayed in the User under review section with highlighted matched users details");
		ratersDetailsPage.verifyFieldHighlightedForUnderReviewUser(Constants.email,Constants.HighlightedColor);
		ratersDetailsPage.verifyUserUnderReviewFieldDisplayedHighlighgtedForMatchedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
		
		reportLog("5.0: Select Cancel option");
		ratersDetailsPage.selectCancelOptionINUserReviewPopup();
		
		reportLog("5.1: Modal window is closed");
		ratersDetailsPage.verifyUserMatchReviewPopupNotdisplayed();
		
		reportLog("5.2: Site Rater_1 PR#3 is displayed in the On-boarding grid without changes");
		ratersDetailsPage.verifyOnBoardingGridIsDisplayed();
		ratersDetailsPage.verifySiteRaterIsDisplayed(Constants.RaterForReview_1);
			
		reportLog("6.0: Select row for the Site Rater_1 PR#3");
		ratersDetailsPage.verifyRaterIsSelected(Constants.RaterForReview_1);

		reportLog("6.1: Select Match Review option in the Actions drop-down menu");
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.selectMatchReviewOption();

		reportLog("6.2: Users Match Review modal window is displayed");
		ratersDetailsPage.userMatchReviewWindowIsDisplayed();
		
		reportLog("7.0: Select Discard User control");
		ratersDetailsPage.selectDiscardUser();
		
		reportLog("7.1: Site Rater_1 PR#3 is deleted and don't added to the System");
		ratersDetailsPage.verifyRaterIsDeletedOrNotDisplayed(Constants.RaterForReview_1);
		
		reportLog("8.0: Select row for Site Rater_2 PR#3");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_2);
		ratersDetailsPage.selectRater(Constants.RaterForReview_2);
		
		reportLog("8.1: Site Rater_2 PR#3 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.RaterForReview_2);
		
		reportLog("8.2: Select Match Review option in the Actions drop-down menu");
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.selectMatchReviewOption();
		
		reportLog("8.3:  Users Match Review modal window is displayed with:");
		reportLog("Site Rater PR#2 is displayed in the Existing Users section with highlighted matched users details");
		reportLog("Site Rater_2 PR#3 is displayed in the User under review section with highlighted matched users details");
		ratersDetailsPage.verifyfieldHighlightedForExistingUser(Constants.email, Constants.HighlightedColor);
		ratersDetailsPage.verifyExistingUserHighlightedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
		ratersDetailsPage.verifyFieldHighlightedForUnderReviewUser(Constants.email,Constants.HighlightedColor);
		ratersDetailsPage.verifyUserUnderReviewFieldDisplayedHighlighgtedForMatchedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
		
		reportLog("9.0: Select Merge Users option");
		ratersDetailsPage.selectMergeUserOption();
		
		reportLog("9.1:  Merge Users option is selected");
		ratersDetailsPage.verifyMergeUserRadioBtnIsSelected();
		
		reportLog("9.2: Merge Users control is displayed (disabled)");
		ratersDetailsPage.verifyMergeUsercontrolbuttonIsDisplayedAndDisabled();
		
		reportLog("10.0: Select row for Site Rater PR#2");
		ratersDetailsPage.selectUserIntoNewUserMerge(Constants.userName_1);
		
		reportLog("10.1: Site Rater PR#2 is selected with blue font");
		ratersDetailsPage.verifyRaterIsSelectedWithBlueFont(Constants.ExpectedColor);
		
		reportLog("10.2: Merge Users control is enabled");
		ratersDetailsPage.verifyMergeUserControlIsEnabled();
		
		reportLog("11.0: Try to select >1 row at once");
		ratersDetailsPage.selectUserIntoNewUserMerge(Constants.userName);

		reportLog("11.1: >1 row at once cannot be selected");
		ratersDetailsPage.verifyMoreThanOneRowCantBeSelected();

		reportLog("12.0: Select Merge Users control");
		ratersDetailsPage.selectMergeUserControlButton();
		
		reportLog("12.1: Site Rater_2 PR#3 is merged with Site Rater PR#2");
		reportLog("Site Rater_2 PR#3 is not displayed in the grid");
		ratersDetailsPage.verifyRaterIsDeletedOrNotDisplayed(Constants.RaterForReview_2);

		reportLog("13.0: Navigate to Site Rater PR#2 profile");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_3);
		ratersDetailsPage.NavigateToRaterProfile(Constants.RaterForReview_3);
		
		reportLog("13.1: Updated details for Site Rater PR#2 are displayed in the profile");
		ratersDetailsPage.verifyUpdatedDetailsShowingInUserProfile(Constants.study,Constants.facility);
		ratersDetailsPage.selectBackButton();
		
		reportLog("14.0: Navigate to Site Rater PR#2 view history page");
		ratersDetailsPage.selectRaterWithCompletedStatus(Constants.RaterForReview_3);
		ratersDetailsPage.selectActionControl();
        ratersDetailsPage.clickOnViewHistoryLink();
        
		reportLog("14.1: Appropriate rows are displayed in the View History page for Site Rater"
				+ " PR#2 (Add to Facility/ Add to Study/Add to Site)");
		ratersDetailsPage.verifyNewEntryDisplayed();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout(); 
		
		
	}

}
