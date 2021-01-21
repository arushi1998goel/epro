/**
 *@author 
* @date 21/05/2020
* =======================================================================================================================================
*  Test Case Id: FP-TC-2195 || Test Case Name: Action for Partial Matched Raters: Global Review- V24
*  pre-qualification :1. At least 2 active Studies and Site exist for the test
                      2. At least 2 Site Raters (Site Rater_1,2) associated with Study_1 exist
                      3. At least 3 Site Raters associated with the Study_2 PR#1 and have Global Review status:
                       - Site Rater_3 has the same First name + Last name as Site Rater_1
                       - Site Rater_4 has the same additional email as Site Rater_2
                       - Site Rater_5 has the primary email which match with additional email of Site Rater_1
                      4. User with claim to allow Review User Match for Rater\Clinician exists
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

public class R4_FPTC_2195_ActionForPartialMatchedRatersGlobalReview_SIP extends BaseTest {

	private String study,primaryEmail1,primaryEmail2,additionalEmail1,additionalEmail3;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R4_FPTC_2195_ActionForPartialMatchedRatersGlobalReview_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("Study2180");
		primaryEmail1 = "Rater"+generateRandomString(3) +"@test.com";
		primaryEmail2 = "RaterPrimary"+generateRandomString(3) +"@test.com";
		additionalEmail1 = "TestRater"+ generateRandomString(4) + "@auto.com";
		additionalEmail3= "Testrater"+generateRandomString(4) + "@addit.com";
		
		/***********************************************/
		reportLog("Creating Rater One For Prequisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudy(study,Constants.ATAssignedRater_10);
		ratersDetailsPage.selectAddSiteControl();
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(primaryEmail1, Constants.SameFirstName,
				Constants.SameLastName);
		ratersDetailsPage.addAdditionalEmailAndPhoneDetails(additionalEmail1,Constants.Phonenumber_1);
		ratersDetailsPage.selectFacility(Constants.facility, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.clickOnAddSiteRaterWindowTitle();
		ratersDetailsPage.selectStudyFromStudyDropDownOptions(study);
		ratersDetailsPage.searchAndSelectSite(Constants.ATAssignedRater_10, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectUserRoleOnAddStaffPopUp();
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();
		ratersDetailsPage.clickOnSkipReviewButton();
		
		/***********************************************/
		reportLog("Creating Rater Two For Prequisite");

	    ratersDetailsPage.selectAddSiteControl();
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(primaryEmail2, Constants.FirstName_1,
					Constants.SecondName_2);
		ratersDetailsPage.addAdditionalEmailAndPhoneDetails(Constants.sameAdditionalEmail,Constants.Phonenumber_2);
		ratersDetailsPage.selectFacility(Constants.facility, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectStudyFromStudyDropDownOptions(study);
		ratersDetailsPage.searchAndSelectSite(Constants.ATAssignedRater_10, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectUserRoleOnAddStaffPopUp();
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();
		ratersDetailsPage.clickOnSkipReviewButton();
		
		/***********************************************/
		reportLog("Creating Rater Three For Prequisite");

	    ratersDetailsPage.selectAddSiteControl();
		ratersDetailsPage.enterRequiredFieldDataOnSiteRaterWindow(Constants.samePrimaryEmail, Constants.FirstName_3,
					Constants.SecondName_3);
		ratersDetailsPage.addAdditionalEmailAndPhoneDetails(additionalEmail3,Constants.Phonenumber_3);
		ratersDetailsPage.selectFacility(Constants.facility, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectStudyFromStudyDropDownOptions(study);
		ratersDetailsPage.searchAndSelectSite(Constants.ATAssignedRater_10, Constants.Add_SiteRater_PopUp);
		ratersDetailsPage.selectUserRoleOnAddStaffPopUp();
		ratersDetailsPage.clickOnAddControlOnSiteRaterWindow();
		ratersDetailsPage.clickOnSkipReviewButton();
		ratersDetailsPage.navigateToOnBoardingMenu();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout(); 
	}
	
	@Test(description="FP-TC-2195--Action for Partial Matched Raters: Global Review- V24")
	public void R4_FPTC_2195_ActionForPartialMatchedRatersGlobalReview() throws Exception
	{
		
		
		reportLog("2.0: Log in to MA Portal as User PR#4");
		dashBoardPage = loginPage.maLogin(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.2:  Select Navigate -> University -> Study_2 -> On-boarding grid");
		ratersDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(RatersDetailsPage.class,
				Constants.NavigateText, Constants.SubTabUniversity);
		ratersDetailsPage.selectStudy(study,Constants.ATAssignedRater_10);
        ratersDetailsPage.navigateToOnBoardingMenu();
        
		reportLog("2.3: On-boarding grid is displayed");
		ratersDetailsPage.verifyOnBoardingGridIsDisplayed();
		
		reportLog("3.0: Select row for Site User_3");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_3);
		ratersDetailsPage.applyFilterUnderStatusTab(Constants.status);
		ratersDetailsPage.selectRater(Constants.RaterForReview_3);
		
		reportLog("3.1: Select Match Review option in the Actions drop-down menu");
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.selectMatchReviewOption();
		
		reportLog("3.2: Users Match Review modal window is displayed");
		ratersDetailsPage.userMatchReviewWindowIsDisplayed();
		
		reportLog("3.2.1:  Information message"
				+ " 'We have found similar profiles(s) existing in our system. Please review them and choose the action to"
				+ " perform.' is displayed");
		ratersDetailsPage.InformationMessageIsDisplayed();
				
		reportLog("3.2.2: Following details are provided about User Under Review");
		reportLog("Primary Email");
		reportLog("Additional Emails");
		reportLog("First Name");
		reportLog("Last Name");
		reportLog("Phone (if exists)");
		reportLog("Facilities - comma-separated list");
		reportLog("Study");
		reportLog("Sites - comma-separated list");
		reportLog("System Role,");
		reportLog("Person Type(empty)");
		reportLog("Identity");
		ratersDetailsPage.verifyUserUnderReviewPopupTitleAndRaterDetails();
				
		reportLog("3.2.3: Following details are provided about existing user");
		reportLog("Radio-button/Information icon");
		reportLog("Primary Email");
		reportLog("Additional Emails (if exists)");
		reportLog("First Name");
		reportLog("Last Name");
		reportLog("Phone (if exists)");
		reportLog("Facilities - comma-separated list");
		reportLog("Studies - comma-separated list");
		reportLog("Sites - comma-separated list");
		reportLog("System Role");
		reportLog("Person Type ");
		reportLog("Identity");
		reportLog("Status - Active");
		ratersDetailsPage.verifyExistingUserTitleRadioButton();
		ratersDetailsPage.verifyExistingUserDetails();
				
		reportLog("3.3: The following options are available:");
		reportLog("Add user as new with 'Add the user under review to the system.' info");
		ratersDetailsPage.verifyOptionToAddUserUnderReviewAsNewUserIsDisplayed();
		
		reportLog("Merge Users with 'Choose the existing user from the list below to merge with:' info");
		ratersDetailsPage.verifyOptionToMergeUserWithFromListedUserIsDisplayed();
		
		reportLog("Discard User with 'Discard user under review and do not add anything to the system.' info");
		ratersDetailsPage.verifyOptionToDiscardUserUnderReviewWithDetailsDisplayed();
		
		reportLog("3.4: Option to close the dialog");
		ratersDetailsPage.verifyCloseOptionDisplayed();
		
		reportLog("3.5: Cancel option (displayed by default)");
		ratersDetailsPage.verifyCancelButtonDisplayedOnUserMatchReviewPopoup();
		
		reportLog("3.6: Site Rater_3 is displayed in the User under review section with highlighted matched users details");
		ratersDetailsPage.verifyUserUnderReviewFieldDisplayedHighlighgtedForSameFirstLastName(Constants.SameFirstName,
				Constants.SameLastName,Constants.HighlightedColor);
		ratersDetailsPage.verifyUserUnderReviewFieldDisplayedHighlighgtedForMatchedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
		
		reportLog("3.7: Site Rater_1 is displayed in the Existing Users section with highlighted matched users details");
		ratersDetailsPage.verifyFirstLastNameFieldHighlightedForExistingUser(Constants.SameFirstName,
				Constants.SameLastName,Constants.HighlightedColor);
		ratersDetailsPage.verifyExistingUserHighlightedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
		
		reportLog("4.0: Select Cancel option");
		ratersDetailsPage.selectCancelOptionINUserReviewPopup();

		reportLog("4.1: Modal window is closed");
		ratersDetailsPage.verifyUserMatchReviewPopupNotdisplayed();

		reportLog("4.2: Site Rater_3 is displayed in the On-boarding grid");
		ratersDetailsPage.verifyOnBoardingGridIsDisplayed();
		ratersDetailsPage.verifySiteRaterIsDisplayed(Constants.RaterForReview_3);
		
		reportLog("5.0: Select row for the Site Rater_3");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_3);
		ratersDetailsPage.applyFilterUnderStatusTab(Constants.status);
		ratersDetailsPage.selectRater(Constants.RaterForReview_3);
		
		reportLog("5.1:  Select Match Review option in the Actions drop-down menu");
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.selectMatchReviewOption();
				
		reportLog("5.2: Users Match Review modal window is displayed");
		ratersDetailsPage.userMatchReviewWindowIsDisplayed();

		reportLog("5.3: Discard User control is displayed");
		ratersDetailsPage.verifyOptionToDiscardUserUnderReviewWithDetailsDisplayed();
		
		reportLog("5.4: Select option to Discard User");
		ratersDetailsPage.selectDiscardOption();
		
		reportLog("5.5:  Discard User option is selected with red font");
		ratersDetailsPage.verifyDiscardTextIsDisplayedWithRedFont(Constants.ColorCode_Red);
		
		reportLog("6.0: Select Discard User control");
		ratersDetailsPage.selectDiscardControlButton();
		
		reportLog("6.1: Site Rater_3 is not displayed in the grid");
		ratersDetailsPage.verifyRaterIsDeletedOrNotDisplayed(Constants.RaterForReview_3);

		reportLog("7.0: Select row for Site Rater_4");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_4);
		ratersDetailsPage.removeStatusTabfilter();
		ratersDetailsPage.selectRater(Constants.RaterForReview_4);
		
		reportLog("7.1: Site Rater_4 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.RaterForReview_4);

		reportLog("7.2: Select Match Review option in the Actions drop-down menu");
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.selectMatchReviewOption();
		
		reportLog("7.3: Users Match Review modal window is displayed with:");
		ratersDetailsPage.userMatchReviewWindowIsDisplayed();

		reportLog("Site Rater_4 is displayed in the User under review section with highlighted matched users details");
		ratersDetailsPage.verifyUserUnderReviewWithHighlightedAndDisplayed(Constants.sameAdditionalEmail,Constants.HighlightedColor);
		ratersDetailsPage.verifyUserUnderReviewFieldDisplayedHighlighgtedForMatchedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
		
		reportLog("Site Rater_2 is displayed in the Existing Users section with highlighted matched users details");
		ratersDetailsPage.verifyExistingUsersectionWithHighlightedDetails(Constants.sameAdditionalEmail, Constants.HighlightedColor);
		ratersDetailsPage.verifyExistingUserHighlightedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
   
		reportLog("7.4: The following options are available:");
		reportLog("Add user as new with 'Add the user under review to the system.' info");
		ratersDetailsPage.verifyOptionToAddUserUnderReviewAsNewUserIsDisplayed();
		
		reportLog("Discard User with 'Discard user under review and do not add anything to the system.' info");
		ratersDetailsPage.verifyOptionToDiscardUserUnderReviewWithDetailsDisplayed();

		reportLog("Merge Users with 'Choose the existing user from the list below to merge with:' info");
		ratersDetailsPage.verifyOptionToMergeUserWithFromListedUserIsDisplayed();

		reportLog("7.5: Option to close the dialog");
		ratersDetailsPage.verifyCloseOptionDisplayed();

		reportLog("7.6: Cancel option (displayed by default)");
		ratersDetailsPage.verifyCancelButtonDisplayedOnUserMatchReviewPopoup();
		
		reportLog("8.0: Select option to Add user as new");
		ratersDetailsPage.selectOptionToAddUserAsNew();
		
		reportLog("8.1: Add user as new is selected with greed font");
		ratersDetailsPage.verifyAddUserAsNewOptionIsSelected();
		
		reportLog("8.2: User cannot select any existing Site Rater");
		ratersDetailsPage.verifyUserCanNotSelectAnyExistingUser(Constants.userName_1);
		
		reportLog("8.3: Add as new control is displayed");
        ratersDetailsPage.verifyAddAsNewControlBtnISDisplayed();
        
        reportLog("9.0: Select Add as new control");
        ratersDetailsPage.clickOnAddAsNewBtn();
        
        reportLog("9.1: Status Invited is applied for Site Rater_4");
        ratersDetailsPage.verifyRaterStatusIsDisplayed(Constants.RaterForReview_4,Constants.Status);
        
        reportLog("10.0: Navigate to Site Rater_4 view history page");
        ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_4);
		ratersDetailsPage.selectRater(Constants.RaterForReview_4);
		ratersDetailsPage.selectActionControl();
		ratersDetailsPage.clickOnViewHistoryLink();
		
        reportLog("10.1: Appropriate actions are displayed in the View History page for Site Rater_4");
        ratersDetailsPage.verifyNewEntryDisplayed();
		ratersDetailsPage.selectBackButton();
        
        reportLog("11.0: Select row for Site Rater_5");
        ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_5);
		ratersDetailsPage.selectRater(Constants.RaterForReview_5);
		
        reportLog("11.1: Site Rater_5 is selected");
		ratersDetailsPage.verifyRaterIsSelected(Constants.RaterForReview_5);

        reportLog("11.2: Select Match Review option in the Actions drop-down menu");
        ratersDetailsPage.selectActionControl();
		ratersDetailsPage.selectMatchReviewOption();

        reportLog("11.3: Users Match Review modal window is displayed with:");
		ratersDetailsPage.userMatchReviewWindowIsDisplayed();

        reportLog("11.4: Site Rater_5 is displayed in the User under review section with highlighted matched users details");
        ratersDetailsPage.verifyFieldHighlightedForUnderReviewUser(Constants.samePrimaryEmail,Constants.HighlightedColor);
		ratersDetailsPage.verifyUserUnderReviewFieldDisplayedHighlighgtedForMatchedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
		
        reportLog("11.5: Site Rater_1 is displayed in the Existing Users section with highlighted matched users details");
        ratersDetailsPage.verifyExistingUsersectionWithHighlightedDetails(Constants.samePrimaryEmail, Constants.HighlightedColor);
		ratersDetailsPage.verifyExistingUserHighlightedFields(Constants.facility,Constants.study, 
				Constants.site,Constants.personType,Constants.HighlightedColor);
		
        reportLog("11.6: The following options are available:");
        reportLog(" Add user as new with 'Add the user under review to the system.' info");
		ratersDetailsPage.verifyOptionToAddUserUnderReviewAsNewUserIsDisplayed();

        reportLog("Discard User with 'Discard user under review and do not add anything to the system.' info");
		ratersDetailsPage.verifyOptionToDiscardUserUnderReviewWithDetailsDisplayed();

        reportLog(" Merge Users with 'Choose the existing user from the list below to merge with:' info");
		ratersDetailsPage.verifyOptionToMergeUserWithFromListedUserIsDisplayed();

        reportLog("11.7: Option to close the dialog");
		ratersDetailsPage.verifyCloseOptionDisplayed();

        reportLog("11.8: Cancel option (displayed by default)");
		ratersDetailsPage.verifyCancelButtonDisplayedOnUserMatchReviewPopoup();
		
		reportLog("12.0: Select Merge Users option");
		ratersDetailsPage.selectMergeUserOption();

		reportLog("12.1: Merge Users option is selected");
		ratersDetailsPage.verifyMergeUserRadioBtnIsSelected();

		reportLog("12.2: Merge Users control is displayed disabled");
		ratersDetailsPage.verifyMergeUsercontrolbuttonIsDisplayedAndDisabled();

		reportLog("13.0: Select row for Site Rater_1");
		ratersDetailsPage.selectUserIntoNewUserMerge(Constants.userName);

		reportLog("13.1: Site Rater_1 is selected");
		ratersDetailsPage.verifyRaterIsSelectedWithBlueFont(Constants.ExpectedColor);

		reportLog("13.2: Merge Users control is enabled");
		ratersDetailsPage.verifyMergeUserControlIsEnabled();

		reportLog("14.0: Select Merge Users control");
		ratersDetailsPage.selectMergeUserControlButton();

		reportLog("14.1: Site Rater_5 is merged with Site Rater_1 and not displayed in the grid");
		ratersDetailsPage.verifyRaterIsDeletedOrNotDisplayed(Constants.RaterForReview_5);

		reportLog("15.0: Navigate to Site Rater_1 profile");
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.Rater_1);
		ratersDetailsPage.NavigateToRaterProfile(Constants.Rater_1);
		
		reportLog("15.1: Updated details for Site Rater_1 are displayed in the profile");
		ratersDetailsPage.verifyUpdatedDetailsShowingInUserProfile(Constants.study,Constants.facility);
		ratersDetailsPage.selectBackButton();
		
		reportLog("16.0: Navigate to Site Rater_1 view history page");
		ratersDetailsPage.selectRaterWithCompletedStatus(Constants.Rater_1);
		ratersDetailsPage.selectActionControl();
        ratersDetailsPage.clickOnViewHistoryLink();
        
		reportLog("16.1: Appropriate actions are displayed in the View History page for Site Rater_1"
				+ " (Add to Facility/ Add to Study/Add to Site)");
		ratersDetailsPage.verifyNewEntryDisplayed();
		
		reportLog("Deleting SiteRater_4 to Restore prequisite" );
		ratersDetailsPage.selectBackButton();
		ratersDetailsPage.applyNameFilterUnderTrackingTab(Constants.RaterForReview_4);
	    ratersDetailsPage.deleteraterInformation(Constants.RaterForReview_4,SuperAdminUN, SuperAdminPW);
	    ratersDetailsPage.verifyDeleteRaterWindowIsNotdisplayed();
	       
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout(); 

	}
}
