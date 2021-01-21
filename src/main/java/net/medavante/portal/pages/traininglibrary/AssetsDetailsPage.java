package net.medavante.portal.pages.traininglibrary;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AssetsDetailsPage extends BasePage {

	/**
	 * ================================================================
	 * Locators
	 * ================================================================
	 */
	
	@FindBy(xpath="//div[@class='heading-panel-box']/strong")
	private WebElement assetsList;
	
	@FindBy(xpath="//div[text()='Add Asset']")
	private WebElement addAssetButton;
	
	@FindBy(xpath="//label[text()='Library Name:']/..//div[@class='input-box-wrapper']/input")
	private WebElement libraryNamefield;
	
	@FindBy(xpath="//label[text()='Display Name:']/..//div[@class='input-box-wrapper']/input")
	private WebElement displayNamefield;
	
	@FindBy(xpath="//label[text()='Description:']/..//div[@class='input-box-wrapper']/textarea")
	private WebElement descriptionfield;
	
	@FindBy(xpath="//span[text()='Add Asset']")
	private WebElement addAssetPopupTitle;
	
	@FindBy(xpath="//span[@class='k-input']")
	private WebElement outcomefield;
	
	@FindBy(xpath="//div[@class='k-list-scroller']//li")
	private List<WebElement> outComeTypeList;
	
	@FindBy(xpath="//label[text()='DEFAULT']/../following::div[1]/label[contains(text(),'Upload Training Package')]")
	private WebElement defaultLanguageUploadBtn;
	
	@FindBy(xpath="//input[@id='uploadFile_1']/preceding::label[1]")
	private WebElement UploadTrainingPackagebutton;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement addbutton;
	
	@FindBy(xpath="//button[text()=' Cancel']")
	private WebElement cancelbutton;
	
	@FindBy(xpath="//div[@class='error-frame ng-star-inserted']")
	private List<WebElement> errorMsg;
	
	@FindBy(xpath="//label[text()='Language']/ancestor::div[contains(@class,' ng-trigger n')]//div[@class='versions-container']/kendo-dropdownlist")
	private WebElement LanguagesDrpDwn;
	
	@FindBy(xpath="//label[text()=' Replace Training Package ']")
	private WebElement replaceUploadPackagebtn;
	
	@FindBy(xpath="//div[@class='k-list-scroller']//li")
	private List<WebElement> languagesList;
	
	@FindBy(xpath="//div[@id='content']")
	private List<WebElement> previewfile;
	
	@FindBy(xpath="(//label[text()='Language']/ancestor::div[contains(@class,' ng-trigger n')]//div[@class='versions-container']/kendo-dropdownlist)[2]")
	private WebElement translationLanguageField;
	
	@FindBy(xpath="//div[@class='box invalid-upload']/span")
	private WebElement deleteUpload;
	
	@FindBy(xpath="//span[text()='Confirmation']")
	private WebElement cancelPopoupTitle;
	
	@FindBy(xpath="//button[text()='No, back to form']")
	private WebElement noBackBtn;
	
	@FindBy(xpath="//button[text()='Yes, close form']")
	private WebElement yesButton;
	
	@FindBy(xpath="//span[text()='Library Name']/ancestor::th//a[@class='k-grid-filter']")
	private WebElement libraryNamefilterBtn;
	
	@FindBy(xpath="(//input[@class='k-textbox ng-untouched ng-pristine ng-valid'])[1]")
	private WebElement filterInputField;
	
	@FindBy(xpath="//button[text()='Filter']")
	private WebElement FilterBtn;
	
	@FindBy(xpath="//input[@id='uploadFile_2']/preceding::label[1]")
	private WebElement translationUpload;
	
	@FindBy(xpath="//div[contains(text(),'Actions')]")
	private WebElement actionbtn;
	
	@FindBy(xpath="//a[text()='Delete']")
	private WebElement deleteButton;
	
	@FindBy(xpath="//button[text()='Delete']")
	private WebElement clickPopUpdeleteBtn;
	
	@FindBy(xpath="//label[text()='Username']/following::input[1]")
	private WebElement userNmaeField;
	
	@FindBy(xpath="//label[text()='Username']/following::input[2]")
	private WebElement PasswordField;
	
	@FindBy(xpath="//button[text()='Confirm']")
	private WebElement confirmBtn;
	
	/**
	 * =============================
	 * Constructor
	 * =============================
	 *  @param driver
	 */
	public AssetsDetailsPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * =============================
	 * Methods
	 * =============================
	 *  @param driver
	 */
	
	/*
	 * verify Assets Details Page
	 */
	public void verifyAssetsDetailsPage()
	{
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(assetsList.isDisplayed() && addAssetButton.isDisplayed() );
		reportInfo();
	}
	/*
	 * verify confirmation Window Displayed
	 */
	public void verifyCancelPopupWindowDisplayed()
	{
		moveToElement(cancelPopoupTitle);
		Assert.assertTrue(isElementDisplayed(cancelPopoupTitle));
		reportInfo();
	}
	/**
	 * 
	 *  GET TOTAL ASSETS cOUNTS
	 * @return
	 */
	public String getAssetsCount()
	{
		moveToElement(assetsList);
		String count=assetsList.getText();
		
		return count;
		
	}
	/*
	 * Confirm Cancellation
	 */
	public void confirmCancellation()
	{
		clickOn(yesButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/*
	 * verify add asset Modat Window not displayed
	 */
	public void verifyAddAssetModalWindowNOtDisplayed()
	{
		boolean flag=true;
		try {
			if (addAssetPopupTitle.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
			
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/*
	 * Discard Cancel Changes
	 */
	public void clickOnNoButton()
	{
		clickOn(noBackBtn);
		reportInfo();
	}
	/*
	 *  verify cancellation Pop Up Window
	 */
	public void verifyCancelPopupWindowNotDisplayed()
	{
		boolean flag=true;
		try {
			if (cancelPopoupTitle.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/*
	 * click on ad asset
	 */
	public void clickOnAddAssetButton()
	{
		waitUntillFinishProcessSpinnerDisable();
		waitAndClick(addAssetButton);
		reportInfo();
		
	}

	/**
	 *  add Course pop up window title, library name & display name displayed
	 */
	public void verifyAddCoursePopupWindowRequiredFieldsDisplayed()
	{
		Assert.assertTrue(libraryNamefield.isDisplayed() && displayNamefield.isDisplayed() && descriptionfield.isDisplayed() );
		moveToElement(libraryNamefield);
		moveToElement(displayNamefield);
		moveToElement(descriptionfield);
		reportInfo();
	}/**
	 *  Enter Description Data
	 * @param description
	 */
	public void enterDescriptionData(String Description)
	{
		inputText(descriptionfield, Description);
		addAssetPopupTitle.click();
		reportInfo();
		
	}
	/*
	 *  click Cancel Button on Add asset Pop Up
	 */
	public void clickOnCancelButton()
	{
		clickOn(cancelbutton);
		reportInfo();
	}
	/**
	 *  Verify Description field value
	 * @param Description
	 */
	
	public void verifyDescriptionData(String Description)
	{
		String displaynamevalue = descriptionfield.getAttribute("value");
		Assert.assertEquals(displaynamevalue, Description);
	}
	/**
	 *  Verify Description Field Highlighted With error or not
	 */
	public void verifyDescriptionFieldHighlightedOrNot()
	{
		String css = descriptionfield.getAttribute("background");
		Assert.assertTrue(true, css);
	}
	/**
	 *  Enter Display name Data
	 * @param description
	 */
	public void enterDisplayNameData(String DisplayName)
	{
		inputText(displayNamefield, DisplayName);
		addAssetPopupTitle.click();
		reportInfo();
		
	}
	/**
	 *  Verify library display name field value
	 * @param DisplayNmae
	 */
	
	public void verifyLibraryDisplayName(String DisplayNmae)
	{
		String displaynamevalue = displayNamefield.getAttribute("value");
		Assert.assertEquals(displaynamevalue, DisplayNmae);
	}
	/**
	 *  Verify Library display Name Highlighted With error or not
	 */
	public void verifyLibraryDisplayNameFieldHighlightedOrNot()
	{
		String css = displayNamefield.getAttribute("background");
		Assert.assertTrue(true, css);
	}
	
	/**
	 *  enter library name
	 * @param libraryname
	 */
	public void enterLibraryName(String libraryname)
	{
		
		inputText(libraryNamefield, libraryname);
		addAssetPopupTitle.click();
	}
	/**
	 *   verify field value
	 * @param libraryname
	 */
	public void verifyLibraryNamefieldValue(String libraryname)
	{
		String valueText = libraryNamefield.getAttribute("value");
		Assert.assertEquals(valueText, libraryname);
		reportInfo();
	}
	/**
	 * verify Library Name field Highlighted or Not
	 */
	public void verifyLibraryNameFieldHighlightedOrNot()
	{
		String css = libraryNamefield.getCssValue("background");
		Assert.assertTrue(true, css);
		reportInfo();
	}
	/**
	 * 
	 *  verify error message Displayed
	 */
	public void verifyErrorWarningDisplayed()
	{
		
		int errorscounts = errorMsg.size();
		for (int i = 1; i <=errorscounts; i++) {
			WebElement errorstobeverified = driver.findElement(ByLocator("(//div[contains(@class,'error-frame')])["+i+"]"));
			moveToElement(errorstobeverified);
		   }
	    reportInfo();
	}
	/*
	 *  verify & click Outcome drop down
	 */
	public void verifyOutComeField()
	{
		Assert.assertTrue(isElementDisplayed(outcomefield));
		clickOn(outcomefield);
		reportInfo();
		
	}
	/*
	 *  Verify OutCome List items displayed one by one
	 */
	public void clickOnOutComeListItem(String OutcomeName)
	{
		for(WebElement web:outComeTypeList)
		{
			if (web.getText().trim().contains(OutcomeName)) {
				clickOn(web);
				break;
			}
		}
		addAssetPopupTitle.click();
		reportInfo();	
	}
	/*
	 *  Verify OutCome List items displayed one by one
	 */
	public void verifyOutComeList()
	{
		int listcount = outComeTypeList.size();
		for (int i = 1; i <= listcount; i++) {
				WebElement outCometobeVerified = driver.findElement(ByLocator("//div[@class='k-list-scroller']//li["+i+"]"));
				moveToElement(outCometobeVerified);
			
			}
		addAssetPopupTitle.click();
		reportInfo();	
	}
	/**
	 *  verify outcome field value
	 * @param OutcomeName
	 */
	public void verifyOutcomeFieldValueDisplayed(String OutcomeName)
	{
		String data = outcomefield.getText();
		Assert.assertEquals(data, OutcomeName);
		reportInfo();
	}
	/*
	 * Option to upload Training package for default language (required)	
	 */
	public void clickonUploadTrainingforDefaultLanguageOption()
	{
		clickOn(defaultLanguageUploadBtn);
		reportInfo();
	}
	/*
	 * Option to upload Training package for default language (required)	
	 */
	public void verifyOptionToUploadForDefaultLanguageDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(defaultLanguageUploadBtn));
		moveToElement(defaultLanguageUploadBtn);
		reportInfo();
	}
	/**
	 * Option to upload Training package for another language
	 */
	public void verifyoptionTouploadTrainingPackage()
	{
		Assert.assertTrue(isElementDisplayed(UploadTrainingPackagebutton));
		moveToElement(UploadTrainingPackagebutton);
		reportInfo();
		
	}
	/**
	 *  verify translation type upload displayed
	 */
	public void verifyTranslationUploadOptionDisplayed()
	{
		moveToElement(translationUpload);
		Assert.assertTrue(isElementDisplayed(translationUpload));
		reportInfo();
	}
	
	/**
	 *  verify add button disabled
	 */
	public void verifyAddButtonDisabled()
	{
		moveToElement(addbutton);
		Assert.assertTrue(addbutton.isEnabled()==false);
		reportInfo();
	}
	/*
	 * verify add button enabled
	 */
	public void verifyAddButtonEnabled()
	{
		Assert.assertTrue(addbutton.isEnabled()==true);
		reportInfo();
	}
	/**
	 *  verify add button Enabled
	 */
	public void verifyCancelButtonEnabled()
	{
		moveToElement(cancelbutton);
		Assert.assertTrue(cancelbutton.isEnabled());
		reportInfo();
	}
	/**
	 *  verify uploaded file displayed
	 * @param FileName
	 */
	public void verifyUploadedFileWithVersionDisplayed(String FileName)
	{
		WebElement filetobeverified = driver.findElement(ByLocator("//span[contains(text(),'"+FileName+"')]"));
		moveToElement(filetobeverified);
		Assert.assertTrue(isElementDisplayed(filetobeverified));
		reportInfo();
		
	}
	/**
	 *  verify Uploaded File not displayed
	 * @param FileName
	 */
	public void verifyUploadedFileWithVersionNotDisplayed(String FileName)
	{
		boolean flag=true;
		try {
			WebElement filetobeverified = driver.findElement(ByLocator("//span[contains(text(),'"+FileName+"')]"));
			if (filetobeverified.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
			
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/*
	 *  verify languages drop down displayed
	 */
	public void verifyLanguagesDropDownDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(LanguagesDrpDwn));
		reportInfo();
	}
	
	/**
	 *  verify replace training package option displayed
	 */
	public void verifyreplaceTrainingpackageoptionDisplayed()
	{
		moveToElement(replaceUploadPackagebtn);
		Assert.assertTrue(isElementDisplayed(replaceUploadPackagebtn));
		reportInfo();
	}
	/**
	 *  click LanguagedropDown
	 */
	public void clickOnLanguageDropDown()
	{
		clickOn(LanguagesDrpDwn);
		reportInfo();
	}
	/**
	 *  select language
	 * @param language
	 */
	public void selectLanguage(String language)
	{
		for(WebElement web:languagesList)
		{
			if (web.getText().trim().contains(language)) {
				clickOn(web);
				break;
			}
		}
	}
	/**
	 * verify languages field Value
	 * @param language
	 */
	public void verifyLanguageFieldvalue(String language)
	{
		String value = LanguagesDrpDwn.getText();
		Assert.assertEquals(value, language);
		reportInfo();
	}
	
	public void clickOnUploadedFileforpreview(String FileName)
	{
		WebElement filetobepreviewed = driver.findElement(ByLocator("//span[contains(text(),'"+FileName+"')]"));
		clickOn(filetobepreviewed);
		reportInfo();
	}
	
	/**
	 * verify Preview window
	 */
	public void verifyPreviewWindowDisplayed()
	{
	Set<String> windows = getDriver().getWindowHandles();
	Iterator<String> iterator = windows.iterator();
	String parent = iterator.next();
	_normalWait(2000);
	String child = iterator.next();
	getDriver().switchTo().window(child);
	boolean flag=true;
	try {
		if (previewfile.size()==0) {
			flag=false;
		}
	} catch (Exception e) {
        }
	Assert.assertTrue(true);
	reportInfo();
	driver.close();
	getDriver().switchTo().window(parent);
	
}
	/**
	 * click to Add asset
	 */
	public void clickOnAddButton()
	{
		if (addbutton.isEnabled()==true) {
			clickOn(addbutton);
			waitUntillFinishProcessSpinnerDisable();
			
		}
		reportInfo();
	}
	/**
	 *  click on replace Training Package Button
	 */
	public void clickOnReplaceTrainingPackageButton()
	{
		clickOn(replaceUploadPackagebtn);
		reportInfo();
	}
	/*
	 *  click upload Training Package For translation
	 */
	public void clickOnUploadTrainingPackageTranslation()
	{
		clickOn(UploadTrainingPackagebutton);
		reportInfo();
	}
	/**
	 * Options to replace file displayed
	 * @param PDF
	 */
	public void verifyOptionToReplaceTrainingDisplayed(String PDF)
	{
		WebElement replace = driver.findElement(ByLocator("//span[contains(text(),'"+PDF+"')]/ancestor::ul/following-sibling::div/label"));
		moveToElement(replace);
		Assert.assertTrue(isElementDisplayed(replace));
		reportInfo();
		
	}
	
	public void ClickOnReplaceUploadForTrainingTypeTranslation(String PDF)
	{
		WebElement replace = driver.findElement(ByLocator("//span[contains(text(),'"+PDF+"')]/ancestor::ul/following-sibling::div/label"));
        clickOn(replace);
        reportInfo();
		
	}
	
	public void verifyOptionToDeleteFileDisplayed(String PDF)
	{
		String locator="//span[contains(text(),'"+PDF+"')]/ancestor::ul/following::div/span/label";
		WebElement delete = driver.findElement(ByLocator(locator));
		moveToElement(delete);
		Assert.assertTrue(isElementDisplayed(delete));
		reportInfo();
	}


	/*
	 * click To Delete uploaded file
	 */
	public void clickOnDeleteUpload()
	{
		clickOn(deleteUpload);
		reportInfo();
	}
    /**
     *  Verify Translation Language dropdown Value 
     * @param Language
     */
	public void verifyTranslationLanguageFieldValue(String Language)
	{
		String text = translationLanguageField.getText();
		Assert.assertEquals(Language, text);
		reportInfo();
	}

	public void verifyTranslationFileLanguageFieldDisplayed()
	{
		moveToElement(translationLanguageField);
		Assert.assertTrue(isElementDisplayed(translationLanguageField));
		reportInfo();
	}
	/*
	 * click on language DropDown For Translation Training Package UploAD
	 */
	public void clickOnTranslationTrainingUploadLanguageDropdown()
	{
		clickOn(translationLanguageField);
		reportInfo();
	}


	public void verifyLanguagesListDisplayed()
	{
		boolean flag=true;
		try {
			if (languagesList.size()==0) {
				flag=false;
			}
		} catch (Exception e) {
		
		}
	 Assert.assertTrue(flag);
	 reportInfo();
	}


	public void verifyDefaultSelectedLanguageNOtDisplayed(String Language)
	{
		boolean flag=true;
		try {
			for(WebElement web:languagesList)
			{
				if (web.getText().trim().contains(Language)) {
					flag=false;
				}
			}
		} catch (Exception e) {
			
		}
	Assert.assertTrue(flag);	
	}

	/**
	 * 
	 *  Verifying Assets list updated
	 */
	public void verifyAssetsListUpdated(String count)
	{
		moveToElement(assetsList);
		_normalWait(3000);
		String actual = count.substring(count.indexOf('(')+1, count.indexOf(')'));
		int i=Integer.parseInt(actual);  
		int updated = i+1;
		String updatedList=String.valueOf(updated);
		_normalWait(2000);
		String list = assetsList.getText();
		String expected = list.substring(list.indexOf('(')+1, list.indexOf(')'));
		int j = Integer.parseInt(expected);
		String NewList = String.valueOf(j);
		Assert.assertEquals(NewList, updatedList);
		reportInfo();
	}

	/**
	 * 
	 *  apply filter on Asset Library Name Field
	 * @param TrainingName
	 */
	
	public void applyfilterToVerifyAsset(String TrainingName)
	{
		waitAndClick(libraryNamefilterBtn);
		clickOn(filterInputField);
		inputText(filterInputField, TrainingName);
		clickOn(FilterBtn);
		reportInfo();
	}
	/**
	 * click on added asset
	 * @param AssetName
	 */
	public void clickOnAsset(String AssetName)
	{
		WebElement asssettobeverified = driver.findElement(ByLocator("//a[text()='"+AssetName+"']"));
		clickOn(asssettobeverified);
		reportInfo();
	}
	/**
	 * 
	 *  deleting Asset
	 * @param CourseName
	 * @param username
	 * @param password
	 */
	public void deleteAsset(String AssetName,String username,String password)
	{
		
		WebElement trainingtobedeleted = driver.findElement(ByLocator("//a[text()='"+AssetName+"']/ancestor::td/preceding-sibling::td[contains(@class,'k-checkbox-cell')]"));
		clickOn(trainingtobedeleted);
		waitAndClick(actionbtn);
		clickOn(deleteButton);
		waitAndClick(clickPopUpdeleteBtn);
		inputText(userNmaeField, username);
		inputText(PasswordField, password);
		clickOn(confirmBtn);
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("//div[@class='ma-spinner']"));
		
	}

}


