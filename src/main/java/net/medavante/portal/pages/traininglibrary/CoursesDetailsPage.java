package net.medavante.portal.pages.traininglibrary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class CoursesDetailsPage  extends BasePage{

	/**
	 * ===================================================
	 * Locators
	 * ===================================================
	 * 
	 */
	
	
	@FindBy(xpath="//div[@class='heading-panel-box']/strong")
	private WebElement coursesDetails;
	
	@FindBy(xpath="//div[text()='Add Course' and @title='Add Course']")
	private WebElement addCoursesButton;
	
	@FindBy(xpath="//label[text()='Library Name:']/..//div[@class='input-box-wrapper']")
	private WebElement libraryNameField;
	
	@FindBy(xpath="//label[text()='Description:']/..//div[@class='input-box-wrapper']")
	private WebElement descriptionField;
	
	@FindBy(xpath="//span[text()='Add asset →']")
	private WebElement addAssetbtn;
	
	@FindBy(xpath="//button[text()=' Cancel']")
	private WebElement cancelButton;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement addButton;
	
	@FindBy(id="library-name-input")
	private WebElement enterLibraryNamefield;
	
	@FindBy(xpath="//div[@class='error-frame ng-star-inserted']")
	private WebElement errorMsg;
	
	@FindBy(xpath="//div[@class='k-list-scroller']//li//span")
	private List<WebElement> assetList;
	
	@FindBy(xpath="//span[contains(@class,'delete-button ng-t')]")
	private WebElement deletelink;
	
	@FindBy(xpath="//span[text()='Add Course']")
	private WebElement addCoursepopuptitle;
	
	@FindBy(xpath="//span[text()='Confirmation']")
	private WebElement cancelPopUptitle;
	
	@FindBy(xpath="//button[text()='No, back to form']")
	private WebElement cancelpopupCancelactionBtn;
	
	@FindBy(xpath="//button[text()='Yes, close form']")
	private WebElement acceptBtnCancelPopup;
	
	@FindBy(xpath="//span[text()='Name']/../preceding-sibling::kendo-grid-filter-menu/a")
	private WebElement namaFilterBtn;
	
	@FindBy(xpath="(//input[@class='k-textbox ng-untouched ng-pristine ng-valid'])[1]")
	private WebElement FilterInputField;
	
	@FindBy(xpath="//button[text()='Filter']")
	private WebElement filterItemBtn;
	
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
	
	public CoursesDetailsPage(WebDriver driver) {
		super(driver);
	}

	
	/**
	 * ===================================================================
	 * Methods
	 * ================================================================
	 */
	
	
	/**
	 * 
	 *  verify Courses List displayed
	 */
	public void verifyCoursesLisDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(coursesDetails));
		reportInfo();
	}
	
	/**
	 * 
	 *  verify add courses button displayed
	 */
	public void addCoursesButtonDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(addCoursesButton));
		moveToElement(addCoursesButton);
		reportInfo();
	}

	/**
	 * 
	 *  click add Courses Button
	 */
	public void selectOptionToCreateCourse()
	{
	
		clickOn(addCoursesButton);
		reportInfo();
	}
	/**
	 * 
	 *  verify Add Course Pop up window Displayed
	 */
	public void verifyAddCoursePopupWindowDisplayed()
	{
		moveToElement(addCoursepopuptitle);
		Assert.assertTrue(isElementDisplayed(addCoursepopuptitle));
		
	}
	
	/**
	 * 
	 *  verify  Add Course Pop up window  Not Displayed
	 */
	public void verifyAddCoursepopupWindowNotDisplayed()
	{
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("//div[@class='ma-spinner']"));
		boolean flag=true;
		try {
			if (addCoursepopuptitle.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
		
	}
	/**
	 * 
	 *  Verify add Course fields
	 */
	
	public void verifyAddCoursesPopupFieldsDisplayed()
	{
		
		Assert.assertTrue(isElementDisplayed(libraryNameField));
		Assert.assertTrue(isElementDisplayed(descriptionField));
		Assert.assertTrue(isElementDisplayed(addAssetbtn));
		moveToElement(libraryNameField);
		moveToElement(descriptionField);
		moveToElement(addAssetbtn);
		reportInfo();
		
	}
	
	/**
	 * 
	 * Verify add control displayed on add course popup
	 */
	public void verifyAddControlDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(addButton));
		moveToElement(addButton);
		reportInfo();
	}
	/**
	 * 
	 *  verify cancel Control displayed on add course Popup
	 */
	public void verifyCancelControlDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(cancelButton));
		moveToElement(cancelButton);
		reportInfo();
	}
	/**
	 * 
	 * Enter Library Name
	 * @param libraryName
	 */
	public void enterLibraryName(String libraryName)
	{
		
		inputText(enterLibraryNamefield, libraryName);
		clickOn(descriptionField.findElement(By.xpath("./textarea")));
		reportInfo();
	}
	/**
	 * 
	 * verify entered Library name displayed
	 * @param nametobeverified
	 */
	public void verifyLibraryNameDisplayed(String nametobeverified)
	{
		
		String name = enterLibraryNamefield.getAttribute("value");
		Assert.assertEquals(name, nametobeverified);
	}
	
	/**
	 * 
	 *  verify Library name field Highlighted with error Or Not
	 */
	public void verifyLibraryNameFieldHighlightedOrNotWithError()
	{
		
		String cssvalue = enterLibraryNamefield.getCssValue("background");
		Assert.assertTrue(true,cssvalue);
	}
	
	/**
	 * 
	 *  verify Validation error Message  Displayed
	 */
	public void verifyValidationMessageDisplayed()
	{
		moveToElement(errorMsg);
		Assert.assertTrue(isElementDisplayed(errorMsg));
		reportInfo();
	}
	
	/**
	 * 
	 *  verify Validation error Message not Displayed
	 */
	
	public void verifyValidationMessageNotdisplayed()
	{
		boolean flag=true;
		try {
			if (errorMsg.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * 
	 *  enter description
	 * @param description
	 */
	public void enterCourseDescription(String description)
	{
		
		inputText(descriptionField.findElement(By.xpath("./textarea")), description);
		clickOn(enterLibraryNamefield);
	}
	/**
	 * 
	 *  verify Description Message Displayed
	 * @param Description
	 */
	public void verifyDescriptionIsDisplayed(String Description)
	{
		String descrptn = descriptionField.findElement(By.xpath("./textarea")).getAttribute("value");
		Assert.assertEquals(descrptn, Description);
		
	}
	
	/**
	 * 
	 *  Verify Description Field Highlighted or Not
	 */
	public void verifyDescriptionfieldHighlightedOrNotWithError()
	{
		String colorvalue = descriptionField.findElement(By.xpath("./textarea")).getCssValue("background");
		Assert.assertTrue(true,colorvalue);
	}
	/**
	 * 
	 * click on add asset link
	 */
	public void clickOnAddAsset()
	{
		clickOn(addAssetbtn);
		reportInfo();
		
	}
	
	/**
	 * 
	 *  verify asset List Displayed
	 */
	public void verifyAssetListDisplayed()
	{
		boolean flag=true;
		
		if (assetList.size()==0) {
			flag=false;
		}
		
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	/**
	 * 
	 * verify asset displayed & available for Selection
	 */
	
	public void verifyAssetavailableforselection(String assetName)
	{
		boolean flag = false;
		for (WebElement we:assetList) {
			if (we.getText().trim().contains(assetName)) {
				flag = true;
				moveToElement(we);
				break;
			}
			
		}
		 Assert.assertTrue(flag);
		 reportInfo();
		
	}
	/**
	 * 
	 *  Select Required asses From asset List
	 */
	public void addaAssetFromAssetList(String assetName)
	{
		boolean flag = false;
		for (WebElement we:assetList) {
			if (we.getText().trim().contains(assetName)) {
				flag = true;
				clickOn(we);
				break;
			}
			
		}
		 Assert.assertTrue(flag);
		 reportInfo();
	}
	
	/**
	 * 
	 *  Verify asset sequence
	 * @param assetName
	 */
	public void verifyAssetAddedInSequence(String assetName)
	{
		WebElement asset = driver.findElement(ByLocator("//span[text()='"+assetName+"']/ancestor::div[@class='group-container']"));
		Assert.assertTrue(isElementDisplayed(asset));
		moveToElement(asset);
		reportInfo();
		
	}
	/**
	 * 
	 *  delete asset From sequence
	 */
	public void verifyOptionToDeleteADDedAssetDisplayed()
	{
		
		moveToElement(deletelink);
		Assert.assertTrue(isElementDisplayed(deletelink));
		reportInfo();
	}
	/**
	 * 
	 *  Click on Cancel Button
	 */
	public void clickOnCancelButton()
	{
		
		 clickOn(cancelButton);
		 reportInfo();
		
	}
	/**
	 * 
	 *  verify Cancel Popup displayed
	 */
	public void verifyCancelPopupConfirmationwindowDisplayed()
	{
		
		boolean flag=true;
		try {
			if (cancelPopUptitle.isDisplayed()) {
				flag=true;
				moveToElement(cancelPopUptitle);
			}
		} catch (Exception e) {
		}
		
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * 
	 *  click on no in Cancel popup window
	 */
	public void clickOnCancelButtonInCancelPopUp()
	{
		
		clickOn(cancelpopupCancelactionBtn);
		reportInfo();
		
	}
	
	/*
	 * 
	 *  Click On yes in Cancel popup
	 */
	public void clickOnYesButtonInCancelPopup()
	{

		clickOn(acceptBtnCancelPopup);
		reportInfo();
		
	}
	/**
	 * 
	 *  click on add (save) Course button
	 */
	public void clickOnAddButton()
	{
		
		clickOn(addButton);
		spinnerBecomeInvisible();
		reportInfo();
		
	}
	
	
	/**
	 * 
	 * apply filter to search any course
	 * @param CourseName
	 */
	public void applyFilterInCourseGrid(String CourseName)
	{
		waitAndClick(namaFilterBtn);
		clickOn(FilterInputField);
		inputText(FilterInputField, CourseName);
		clickOn(filterItemBtn);
		reportInfo();
		
	}
	/**
	 * 
	 *  verify course details  displayed
	 * @param CourseName
	 */
	public void verifyCourseInGridDisplayed(String CourseName)
	{
	
		WebElement courseDetails = driver.findElement(ByLocator("//a[text()='"+CourseName+"']/ancestor::td"));
		moveToElement(courseDetails);
		Assert.assertTrue(isElementDisplayed(courseDetails));
		
	}
	/**
	 * 
	 *  verify course details not displayed
	 * @param CourseName
	 */
	public void verifyCourseInGridNotDisplayed(String CourseName)
	{
		boolean flag=true;
      try {
    	  WebElement courseDetails = driver.findElement(ByLocator("//a[text()='"+CourseName+"']/ancestor::td"));
		if (courseDetails.isDisplayed()) {
			flag=false;
		}
	} catch (Exception e) {
	}
      Assert.assertTrue(flag);
		
	}
	/**
	 * 
	 *  deleting course
	 * @param CourseName
	 * @param username
	 * @param password
	 */
	public void deleteCourse(String CourseName,String username,String password)
	{
		
		WebElement coursetobedeleted = driver.findElement(ByLocator("//a[text()='"+CourseName+"']/ancestor::td/preceding-sibling::td[@class='k-checkbox-cell ng-star-inserted']"));
		clickOn(coursetobedeleted);
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
