/**
 *@author 
* @date 04/03/2020
* =========================================================================
*  Test Case Id: FP-TC-2157 || Test Case Name: Training Library - Add Course dialog
	 * pre-qualification :1. At least one User with the specific claim to manage Training Library exists
                          2. At least one asset is uploaded to the Training Library	
* ========================================================================= 
*/package net.medavante.regression.testscripts.r3suite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.traininglibrary.TrainingDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_2157_TrainingLibraryAddCoursedialog_SIP extends BaseTest
{
	
	private String LibraryNameValid="Auto_Library"+generateRandomString(4),Description="Auto_description"+generateRandomAlphanumericString(600);
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_2157_TrainingLibraryAddCoursedialog_SIP(String browser) {
		super(browser);
	}
	
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	
	}
	
	
	@Test(description="FP-TC-2157 --Training Library - Add Course dialog")
	public void R3_FPTC_2157_TrainingLibraryAddCoursedialog()
	{
		
		
		reportLog("2.0: Log in to the Portal as User Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("2.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to Training Library ->");
		trainingDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				TrainingDetailsPage.class, Constants.ConfigureNavText, Constants.TrainingLibraryText);
		
		reportLog("3.0.1: Navigating to Courses grid");
		coursesDetailsPage=trainingDetailsPage.clickOnCoursesTab();
		
		reportLog("3.1: The list of Courses is displayed");
		coursesDetailsPage.verifyCoursesLisDisplayed();
		
		reportLog("3.2: An option to add a new Course is displayed");
		coursesDetailsPage.addCoursesButtonDisplayed();
		
		reportLog("4.0: Select an action to add a new Course");
		coursesDetailsPage.selectOptionToCreateCourse();
		
		reportLog("4.1: Add Course dialog is displayed with the following fields:");
		reportLog("- Library name field (required)");
		reportLog("- Description field (optional)");
		reportLog("- Option to Add asset");
		reportLog("- Option to add");
		reportLog("Option to cancel");
		coursesDetailsPage.verifyAddCoursePopupWindowDisplayed();
		coursesDetailsPage.verifyAddCoursesPopupFieldsDisplayed();
		coursesDetailsPage.verifyAddControlDisplayed();
		coursesDetailsPage.verifyCancelControlDisplayed();
		
		reportLog("5.0: Provide more than 150 symbols for the Library name");
		coursesDetailsPage.enterLibraryName(Constants.LibraryName);
		
		reportLog("5.1: - Provided name is displayed in the field");
		coursesDetailsPage.verifyLibraryNameDisplayed(Constants.LibraryName);
		
		reportLog("5.2: - Name is highlighted as invalid");
		coursesDetailsPage.verifyLibraryNameFieldHighlightedOrNotWithError();
		
		reportLog("5.3: - Validation message is displayed");
		coursesDetailsPage.verifyValidationMessageDisplayed();
		
		reportLog("6.0: Provide unique Library name (length less than 150 symbols)");
		coursesDetailsPage.enterLibraryName(LibraryNameValid);
		
		reportLog("6.1: Provided name is displayed in the field as valid");
		coursesDetailsPage.verifyLibraryNameDisplayed(LibraryNameValid);
		coursesDetailsPage.verifyLibraryNameFieldHighlightedOrNotWithError();
		coursesDetailsPage.verifyValidationMessageNotdisplayed();
		
		reportLog("7.0: Provide more than 500 symbols for the Description");
		coursesDetailsPage.enterCourseDescription(Description);
		
		reportLog("7.1: Provided text is displayed in the field");
		coursesDetailsPage.verifyDescriptionIsDisplayed(Description);
		
		reportLog("7.2:Description is highlighted as invalid");
		coursesDetailsPage.verifyDescriptionfieldHighlightedOrNotWithError();
		
		reportLog("7.3: Validation message is displayed");
		coursesDetailsPage.verifyValidationMessageDisplayed();
		
		reportLog("8.0: Provide less than 500 symbols for Description");
		coursesDetailsPage.enterCourseDescription(Constants.Description);
		
		reportLog("8.1: Provided text is displayed in the field as valid");
		coursesDetailsPage.verifyDescriptionIsDisplayed(Constants.Description);
		coursesDetailsPage.verifyDescriptionfieldHighlightedOrNotWithError();
		coursesDetailsPage.verifyValidationMessageNotdisplayed();
		
		reportLog("9.0 :Select an action to choose the asset");
		coursesDetailsPage.clickOnAddAsset();
		
		reportLog("9.1: The list of assets is displayed");
		coursesDetailsPage.verifyAssetListDisplayed();
		
		reportLog("9.2: The Asset Pr#2 is available in the list");
		coursesDetailsPage.verifyAssetavailableforselection(Constants.assetName);
		
		reportLog("10.0: Select the Asset Pr#2");
		coursesDetailsPage.addaAssetFromAssetList(Constants.assetName);
		
		reportLog("10.1:The Asset is added to the sequence");
		coursesDetailsPage.verifyAssetAddedInSequence(Constants.assetName);
		
		reportLog("10.2:  An option to delete the Asset is displayed");
		coursesDetailsPage.verifyOptionToDeleteADDedAssetDisplayed();
		
		reportLog("11.0: Select an action to cancel");
		coursesDetailsPage.clickOnCancelButton();
		
		reportLog("11.1: Confirmation window is displayed");
		coursesDetailsPage.verifyCancelPopupConfirmationwindowDisplayed();
		
		reportLog("12.0: Select an action to cancel in the confirmation window");
		coursesDetailsPage.clickOnCancelButtonInCancelPopUp();
		
		reportLog("12.1: - Select an action to add");
		coursesDetailsPage.clickOnAddButton();
		
		reportLog("12.2: The Course is saved");
		coursesDetailsPage.verifyAddCoursepopupWindowNotDisplayed();
		
		reportLog("12.3: The Course is displayed in the Courses grid");
		coursesDetailsPage.applyFilterInCourseGrid(LibraryNameValid);
		coursesDetailsPage.verifyCourseInGridDisplayed(LibraryNameValid);
		
		reportLog("13.0: Select an action to add a new Course");
		coursesDetailsPage.selectOptionToCreateCourse();
		
		reportLog("13.1: Add Course dialog is displayed");
		coursesDetailsPage.verifyAddCoursesPopupFieldsDisplayed();
		
		reportLog("13.2: Provide non-unique Library name");
		coursesDetailsPage.enterLibraryName(LibraryNameValid);
		coursesDetailsPage.enterCourseDescription(Constants.Description);
		coursesDetailsPage.clickOnAddAsset();
		coursesDetailsPage.addaAssetFromAssetList(Constants.assetName);
		coursesDetailsPage.clickOnAddButton();
		
		reportLog("13.3: Validation message is displayed that Library name is non-unique");
		coursesDetailsPage.verifyValidationMessageDisplayed();
		
		reportLog("14.0: Select an action to cancel");
		coursesDetailsPage.clickOnCancelButton();
		
		reportLog("14.1:  Confirm cancellation in the Confirmation window");
		coursesDetailsPage.clickOnYesButtonInCancelPopup();
		
		reportLog("Delete added Course");
		coursesDetailsPage.deleteCourse(LibraryNameValid, SuperAdminUN, SuperAdminPW);
		
		reportLog("14.2: Add Course dialog is closed without saving");
		coursesDetailsPage.verifyAddCoursepopupWindowNotDisplayed();
		coursesDetailsPage.applyFilterInCourseGrid(LibraryNameValid);
		coursesDetailsPage.verifyCourseInGridNotDisplayed(LibraryNameValid);
		
		
		reportLog(" LogOut Application");
		loginPage.logoutApplication();
		
		reportLog(" Verify LogOut ");
		loginPage.verifyUserLogout();
		
		
		
	}

}
