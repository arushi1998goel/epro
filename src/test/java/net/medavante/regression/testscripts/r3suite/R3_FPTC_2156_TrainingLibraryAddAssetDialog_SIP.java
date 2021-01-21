/**
 *@author 
* @date 17/03/2020
* =========================================================================
*  Test Case Id: FP-TC-2156 || Test Case Name: Training Library - Add Asset dialog
	 * pre-qualification :  1. At least one asset exists in the system
                            2. Two files (Scorm or PDF) exist for the test
                            3. A user with VU MA Admin role with the following claims canAccessTrainingLibrary, canManageTrainingLibrary exists	
* ========================================================================= 
*/package net.medavante.regression.testscripts.r3suite;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.traininglibrary.TrainingDetailsPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_2156_TrainingLibraryAddAssetDialog_SIP extends BaseTest {
	
	private String Displayname="Auto_display"+generateRandomString(120),Description="Auto_Description"+generateRandomString(500),
			NewLibraryName="Auto_Asset_New_" + generateRandomAlphanumericString(3);
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_2156_TrainingLibraryAddAssetDialog_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}
	
	
	@Test(description="FP-TC-2156---Training Library - Add Asset dialog")
	public void R3_FPTC_2156_TrainingLibraryAddAssetDialog() throws InterruptedException, AWTException
	{
		reportLog("1.0: Log in to the Portal as User Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.0.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.0.2: navigated to the Training Library -> Training Grid.");
		trainingDetailsPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				TrainingDetailsPage.class, Constants.ConfigureNavText, Constants.TrainingLibraryText);
		
		reportLog("1.0.3: navigated to  Assets grid");
		assetsDetailsPage=trainingDetailsPage.navigateToAssetsGrid();
		
		reportLog("1.0.3.1: Verify ASSETS Details Page");
		assetsDetailsPage.verifyAssetsDetailsPage();
		String AssetsCount = assetsDetailsPage.getAssetsCount();
		
		reportLog("2.0: Select an option to Add Asset");
		assetsDetailsPage.clickOnAddAssetButton();
		
		reportLog("2.1: Add Asset modal window is displayed with following fields:");
		reportLog("- Library name (required)");
		reportLog("Display name (required)");
		reportLog("Description (optional)");
		assetsDetailsPage.verifyAddCoursePopupWindowRequiredFieldsDisplayed();
		
		reportLog("2.2: Drop-down to configure outcome type as:");
		reportLog("Complete (default)");
		reportLog("Pass/Fail");
		reportLog("Requires Decision");
		assetsDetailsPage.verifyOutComeField();
		assetsDetailsPage.verifyOutComeList();
		
		reportLog("2.3:Option to upload Training package for default language (required)");
		assetsDetailsPage.verifyOptionToUploadForDefaultLanguageDisplayed();
		
		reportLog("2.4: Option to upload Training package for another language");
		assetsDetailsPage.verifyoptionTouploadTrainingPackage();
		
		reportLog("2.5: Add option (disabled by default)");
		assetsDetailsPage.verifyAddButtonDisabled();
		
		reportLog("2.6: Cancel option is enabled");
		assetsDetailsPage.verifyCancelButtonEnabled();
		
		reportLog("3.0: Provide more than 150 symbols for Library name");
		assetsDetailsPage.enterLibraryName(Constants.LibraryName);
		
		reportLog("3.1: Provided name is displayed in the field and highlighted as invalid");
		assetsDetailsPage.verifyLibraryNamefieldValue(Constants.LibraryName);
		assetsDetailsPage.verifyLibraryNameFieldHighlightedOrNot();
		
		reportLog("3.2: Validation message is displayed: 'Library Name is too long (max 150 characters)'");
		assetsDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("4.0: Provide more than 100 symbols for Display name");
		assetsDetailsPage.enterDisplayNameData(Displayname);
		
		reportLog("4.1: Provided name is displayed in the field and highlighted as invalid");
		assetsDetailsPage.verifyLibraryDisplayName(Displayname);
		assetsDetailsPage.verifyLibraryDisplayNameFieldHighlightedOrNot();
		
		reportLog("4.2: Validation message is displayed: 'Display Name is too long (max 100 characters)'");
		assetsDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("5.0: Provide more than 500 symbols for Description field");
		assetsDetailsPage.enterDescriptionData(Description);
		
		reportLog("5.1: Provided description is displayed in the field and highlighted as invalid");
		assetsDetailsPage.verifyDescriptionData(Description);
		assetsDetailsPage.verifyDescriptionFieldHighlightedOrNot();
		
		reportLog("5.2: Validation message is displayed: 'Description is too long (max 500 characters)'");
		assetsDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("6.0: Provide 500 or less symbols for Description field");
		assetsDetailsPage.enterDescriptionData(Constants.Description);

		reportLog("6.1: Provided description is displayed in the field and is not highlighted");
		assetsDetailsPage.verifyDescriptionData(Constants.Description);
		assetsDetailsPage.verifyDescriptionFieldHighlightedOrNot();
		
		reportLog("7.0: Select Complete option for outcome type drop-down");
		assetsDetailsPage.verifyOutComeField();
		assetsDetailsPage.clickOnOutComeListItem(Constants.OutCome_complete);
		
		reportLog("7.1: Option is selected for outcome type dropdown");
		assetsDetailsPage.verifyOutcomeFieldValueDisplayed(Constants.OutCome_complete);
		
		reportLog("8.0: Select an action to upload training package for default language");
		assetsDetailsPage.clickonUploadTrainingforDefaultLanguageOption();
		
		reportLog("8.1: Upload file from Pr.#2");
		assetsDetailsPage.uploadFileUsingRobotclass(Constants.fileName);
		
		reportLog("8.2: The file is uploaded and displayed as a link as a Current version for this language");
		assetsDetailsPage.verifyUploadedFileWithVersionDisplayed(Constants.fileName);
		
		reportLog("8.3: Dropdown to select Default language is required displayed");
		assetsDetailsPage.verifyLanguagesDropDownDisplayed();
		
		reportLog("8.4: Option to replace the file is displayed");
		assetsDetailsPage.verifyreplaceTrainingpackageoptionDisplayed();
		
		reportLog("9.0: Click on dropdown to select language");
		assetsDetailsPage.clickOnLanguageDropDown();
		
		reportLog("9.1: Select a language");
		assetsDetailsPage.selectLanguage(Constants.subjectLanguage);
		
		reportLog("9.2: Language is selected as a Default asset language");
		assetsDetailsPage.verifyLanguageFieldvalue(Constants.subjectLanguage);
		
		reportLog("10.0: Click on the uploaded file name to preview");
		assetsDetailsPage.clickOnUploadedFileforpreview(Constants.fileName);
		
		reportLog("10.1: Preview of the uploaded file is opened in a new browser window");
		assetsDetailsPage.verifyPreviewWindowDisplayed();
		
		reportLog("11.0: Provide non-unique Library name as in Pr.#1 and unique Display name and click Add");
		assetsDetailsPage.enterLibraryName(Constants.Libraryname);
		assetsDetailsPage.clickOnAddButton();

		reportLog("11.1: Provided Library name is displayed in the field and highlighted as invalid");
		assetsDetailsPage.verifyLibraryNamefieldValue(Constants.Libraryname);
		assetsDetailsPage.verifyLibraryNameFieldHighlightedOrNot();
		
		reportLog("11.2: Validation message is displayed: 'Display Name must be unique'");
		assetsDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("12.0: Provide non-unique Display name as in Pr.#1 and unique Library name and click Add");
		assetsDetailsPage.enterDisplayNameData(Constants.Libraryname);
		assetsDetailsPage.clickOnAddButton();
		
		reportLog("12.1: Provided Display name is displayed in the field and highlighted as invalid");
		assetsDetailsPage.verifyLibraryDisplayName(Constants.Libraryname);
		assetsDetailsPage.verifyLibraryDisplayNameFieldHighlightedOrNot();
		
		reportLog("12.2: Validation message is displayed: 'Display Name must be unique'");
		assetsDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("13.0: Provide unique Library name (length less or equal 150 symbols)");
		assetsDetailsPage.enterLibraryName(NewLibraryName);
		
		reportLog("13.1: Provided name is displayed in the field and is not highlighted");
		assetsDetailsPage.verifyLibraryNamefieldValue(NewLibraryName);
		assetsDetailsPage.verifyLibraryNameFieldHighlightedOrNot();
		
		reportLog("14.0: Provide unique Display name (length less or equal 100 symbols)");
		assetsDetailsPage.enterDisplayNameData(NewLibraryName);
		
		reportLog("14.1: Provided name is displayed in the field and is not highlighted");
		assetsDetailsPage.verifyLibraryDisplayName(NewLibraryName);
		assetsDetailsPage.verifyLibraryDisplayNameFieldHighlightedOrNot();
		
		reportLog("15.0: Click option to Replace the file");
		assetsDetailsPage.clickOnReplaceTrainingPackageButton();
		
		reportLog("15.1: Upload file from Pr.#2");
		assetsDetailsPage.uploadFileUsingRobotclass(Constants.fileName_2);
		
		reportLog("15.2: The file is uploaded and displayed as a link as a Current version for this language" );
		assetsDetailsPage.verifyUploadedFileWithVersionDisplayed(Constants.fileName_2);
		
		reportLog("16.0: Select an action to upload training package for another language");
		assetsDetailsPage.clickOnUploadTrainingPackageTranslation();
		
		reportLog("16.1: Upload file from Pr.#2");
		assetsDetailsPage.uploadFileUsingRobotclass(Constants.fileName);
		
		reportLog("16.2: The file is uploaded and displayed as a link as a Current version for this language");
		assetsDetailsPage.verifyUploadedFileWithVersionDisplayed(Constants.fileName);
		
		reportLog("16.3: Dropdown to select language is required Displayed");
		assetsDetailsPage.verifyTranslationFileLanguageFieldDisplayed();
		
		reportLog("16.4: Options to replace and to delete the file are displayed");
		assetsDetailsPage.verifyOptionToReplaceTrainingDisplayed(Constants.fileName);
		assetsDetailsPage.verifyOptionToDeleteFileDisplayed(Constants.fileName);
		
		/*=========================================================
		 * Step already Covered at 16.3 need update in jama
		 *=========================================================
		 */
		
		reportLog("16.5: One more option to Add language appears");
		//==========================================================
		
		reportLog("17.0: Click on dropdown to select language");
		assetsDetailsPage.clickOnTranslationTrainingUploadLanguageDropdown();
		
		reportLog("17.1: The list of languages is displayed");
		assetsDetailsPage.verifyLanguagesListDisplayed();
		
		reportLog("17.3: The language that was selected for the Default Asset language is not displayed in the list");
		assetsDetailsPage.verifyDefaultSelectedLanguageNOtDisplayed(Constants.subjectLanguage);
		
		reportLog("18.0: Select language" );
		assetsDetailsPage.selectLanguage(Constants.Language);
		
		reportLog("18.1: Language is selected" );
		assetsDetailsPage.verifyTranslationLanguageFieldValue(Constants.Language);
		
		reportLog("18.2: Add option becomes enable");
		assetsDetailsPage.verifyAddButtonEnabled();
		
		reportLog("19.0: Click on the uploaded file name to preview");
		assetsDetailsPage.clickOnUploadedFileforpreview(Constants.fileName);
		
		reportLog("19.1:Preview of the uploaded file is opened in a new browser window" );
		assetsDetailsPage.verifyPreviewWindowDisplayed();
		
		reportLog("20.0: Click option to Replace the file");
		assetsDetailsPage.ClickOnReplaceUploadForTrainingTypeTranslation(Constants.fileName);
		
		reportLog("20.1: Upload file from Pr.#2 (file type should be differs that used in default language)");
		assetsDetailsPage.uploadFileUsingRobotclass(Constants.fileName_3);
		
		reportLog("20.2: The file is uploaded and displayed as invalid with validation message: 'File type for translation should be the same as for default language'");
		assetsDetailsPage.verifyUploadedFileWithVersionDisplayed(Constants.fileName_3);
		assetsDetailsPage.verifyErrorWarningDisplayed();
		
		reportLog("21.0: Click option to delete the uploaded files for this language");
		assetsDetailsPage.clickOnDeleteUpload();
		
		reportLog("21.1: The file, highlighted as invalid, is deleted");
		assetsDetailsPage.verifyUploadedFileWithVersionNotDisplayed("FP-TC-2156");
		
		reportLog("21.2: An option to upload training package is displayed");
		assetsDetailsPage.verifyTranslationUploadOptionDisplayed();
		
		reportLog("22.0: Select an action to cancel adding asset");
		assetsDetailsPage.clickOnCancelButton();
		
		reportLog("22.1: A confirmation window is displayed");
		assetsDetailsPage.verifyCancelPopupWindowDisplayed();
		
		reportLog("23.0: Discard the cancellation");
		assetsDetailsPage.clickOnNoButton();
		
		reportLog("23.1: A confirmation window is closed");
		assetsDetailsPage.verifyCancelPopupWindowNotDisplayed();
		
		reportLog("23.2: The user is navigated back to the Add Asset modal window");
		assetsDetailsPage.verifyAddCoursePopupWindowRequiredFieldsDisplayed();
		
		reportLog("24.0: Select an action to cancel adding asset");
		assetsDetailsPage.clickOnCancelButton();
		
		reportLog("24.1: Confirm cancellation");
		assetsDetailsPage.confirmCancellation();
		
		reportLog("24.2: Add Asset modal window is closed");
		assetsDetailsPage.verifyAddAssetModalWindowNOtDisplayed();
		
		reportLog("24.3: The user is navigated to the Assets grid");
		assetsDetailsPage.verifyAssetsDetailsPage();
		
		reportLog("24.4: Asset was not added to the list");
		String NewList = assetsDetailsPage.getAssetsCount();
		Assert.assertEquals(NewList, AssetsCount);
		
        reportLog("25.0: Select an option to Add new Asset");
        assetsDetailsPage.clickOnAddAssetButton();
        
        reportLog("25.1: Provide valid Library and Display names");
        assetsDetailsPage.enterLibraryName(NewLibraryName);
        assetsDetailsPage.enterDisplayNameData(NewLibraryName);
        assetsDetailsPage.enterDescriptionData(Constants.Description);
            
        reportLog("25.2: Add few languages and upload file Pr.#2 for them");
        assetsDetailsPage.clickonUploadTrainingforDefaultLanguageOption();
        assetsDetailsPage.uploadFileUsingRobotclass(Constants.fileName);
        assetsDetailsPage.clickOnLanguageDropDown();
        assetsDetailsPage.selectLanguage(Constants.subjectLanguage);
        assetsDetailsPage.clickOnUploadTrainingPackageTranslation();
        assetsDetailsPage.uploadFileUsingRobotclass(Constants.fileName_1);
        assetsDetailsPage.clickOnTranslationTrainingUploadLanguageDropdown();
        assetsDetailsPage.selectLanguage(Constants.Language);
        
        reportLog("25.3: Click Add asset");
        assetsDetailsPage.clickOnAddButton();
        
        reportLog("25.4: Add Asset modal window is closed");
        assetsDetailsPage.verifyAddAssetModalWindowNOtDisplayed();
        
        reportLog("25.5: The user is navigated to the Assets grid");
        assetsDetailsPage.verifyAssetsDetailsPage();
        
        reportLog("25.6: The list of Assets is refreshed with the newly added asset");
        assetsDetailsPage.verifyAssetsListUpdated(AssetsCount);
        
        reportLog("26.0: Click on newly added asset");
        assetsDetailsPage.applyfilterToVerifyAsset(NewLibraryName);
        assetsDetailsPage.clickOnAsset(NewLibraryName);
        
        reportLog("26.1: Asset modal window is displayed");
		assetsDetailsPage.verifyAddCoursePopupWindowRequiredFieldsDisplayed();

		reportLog("26.2: All the provided data in step 25 is displayed");
		assetsDetailsPage.verifyLibraryNamefieldValue(NewLibraryName);
		assetsDetailsPage.verifyLibraryDisplayName(NewLibraryName);
		assetsDetailsPage.verifyDescriptionData(Constants.Description);
		
		reportLog("Restoring PreRequisite");
		assetsDetailsPage.clickOnCancelButton();
		assetsDetailsPage.deleteAsset(NewLibraryName, SuperAdminUN, AT_Password);
		
		reportLog(" LogOut Application");
		loginPage.logoutApplication();
		
		reportLog(" Verify LogOut ");
		loginPage.verifyUserLogout();
		
	}
	

}
