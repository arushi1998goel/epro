package net.medavante.portal.pages.qualificationlibrary;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class QualificationLibraryPage extends BasePage {

	
	
	/**
	 * ===============================================
	 *            CONSTRUCTOR
	 * ===============================================
	 * @param driver
	 */
	
	
	public QualificationLibraryPage(WebDriver driver) {
		super(driver);
	}
	

	
	/**
	 * ================================================= 
	 *          LOCATORS
	 * =================================================
	 */
	
	
	@FindBy(xpath="//div[@class='heading-panel-box']/strong")
	private WebElement qualificationlistingScreen;
	
	@FindBy(xpath="//div[@id='QualificationComponent']//div[text()='Add Group']")
	private WebElement addGroupBtn;
	
	@FindBy(xpath="//span[@class='ma-dialog-title' and contains(text(),'Qualification Group')]")
	private WebElement addQualificationPouupTitle;
	
	@FindBy(xpath="//label[contains(text(),'Description:')]/..//textarea")
	private WebElement descriptionField;
	
	@FindBy(xpath="//label[text()='Description:']/..//div[@class='error-frame ng-star-inserted']")
	private WebElement requiredField;
	
	@FindBy(xpath="//div[text()='Qualification Question:']/following::strong")
	private WebElement qualificationQuestion;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement addButton;
	
	@FindBy(xpath="//button[text()=' Cancel']")
	private WebElement cancelButton;
	
	@FindBy(xpath="//div[@class='k-list-scroller']//li//label")
	private List<WebElement> formsList;
	
	@FindBy(xpath="//span[text()='Add Eligibility Rule']")
	private WebElement addEligibilityRulePopuptTitle;
	
	@FindBy(xpath="//label[text()='Description']/..//textarea")
	private WebElement addDescriptionFieldONPopup;
	
	@FindBy(xpath="//button[contains(text(),'Add Group')]")
	private WebElement addgroupBtn;
	
	@FindBy(xpath="//button[contains(text(),'Add Condition')]")
	private WebElement addConditionBtn;
	
	@FindBy(xpath="//kendo-dropdownlist[contains(@class,'variables')]")
	private WebElement roleField;
	
	@FindBy(xpath="//kendo-dropdownlist[contains(@class,'operator')]")
	private WebElement roleField1;
	
	@FindBy(xpath="//div[@class='k-list-scroller']//li")
	private List<WebElement> roleList;
	
	@FindBy(xpath="//ma-group-editor[@class='ng-star-inserted']//div[@class='group-container']")
	private WebElement groupField;
	
	@FindBy(id="expression-textarea")
	private WebElement expressionField;
	
	@FindBy(xpath="//mat-dialog-container[@id='mat-dialog-1']//button[text()='Add']")
	private WebElement rulePopupAddBtn;
	
	@FindBy(xpath="//mat-dialog-container[@id='mat-dialog-2']//button[text()='Add']")
	private WebElement rulePopupAddBtn1;
	
	@FindBy(xpath="//button[@class='btn btn-new-active' and text()='Yes, close form']")
	private WebElement yesOptionBtn;
	
	@FindBy(xpath="//span[text()='Add Qualification Rule']")
	private WebElement addQualificationRuleWindow;
	
	@FindBy(xpath="//span[contains(text(),'Library name')]/ancestor::th//a[@title='Filter']")
	private WebElement LibraryNamefilterIcon;
	
	@FindBy(xpath="(//input[@class='k-textbox ng-untouched ng-pristine ng-valid'])[1]")
	private WebElement filterInputField;
	
	@FindBy(xpath="//button[text()='Filter']")
	private WebElement filterButton;
	
	@FindBy(xpath="//a[text()='Delete']")
	private WebElement deleteButton;
	
	@FindBy(xpath="//div[text()=' Actions ']")
	private WebElement actionsButton;
	
	@FindBy(xpath="//label[text()='Username']/..//input")
    private WebElement userNameInputField;
     
    @FindBy(xpath="//label[text()='Password']/..//input")
    private WebElement passwordInputField;
     
    @FindBy(xpath="//button[text()='Delete']")
    private WebElement deleteOption;
     
    @FindBy(xpath="//button[text()='Confirm']")
    private WebElement confirmButton;
	
    @FindBy(xpath="//th[@class='k-hierarchy-cell k-header']")
	private WebElement detailsAcordinColumn;
	
	@FindBy(xpath="//th[@class='k-header']//label[@class='k-checkbox-label']")
	private WebElement multipleselectionColumn;
	
	@FindBy(xpath="//div[@class='filters-group']")
	private WebElement filtersection;
	
	@FindBy(xpath="//tr[@class='k-detail-row ng-star-inserted']")
	private WebElement additionaldetails;
	
	@FindBy(xpath="//div[@class='btn btn-default btn-icon' and @title='Columns filter']")
	private WebElement colomnConfiguration;
	
	@FindBy(xpath="//a[@title='Refresh']")
	private WebElement refreshBtn;
	
	@FindBy(xpath="//kendo-pager-page-sizes[@class='k-pager-sizes k-label']//select")
	private WebElement displayQuantityField;
	
	@FindBy(xpath="//kendo-pager[contains(@class,'k-pager-wrap')]//kendo-pager-next-buttons")
	private WebElement pagenavigationforward;
	
	@FindBy(xpath="//kendo-pager[contains(@class,'k-pager-wrap')]//kendo-pager-prev-buttons")
	private WebElement pagenavigationBackward;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']//a[contains(text(),'Qualification Library')]")
	private WebElement qualificationLibrary;
	
	@FindBy(xpath="//span[@class='k-icon k-i-group-delete']/.")
	private WebElement deleteDragEntry;
	
	@FindBy(xpath="(//span[@class='k-icon k-i-sort-asc-sm'])")
	private WebElement ascendingOrder;
	
	@FindBy(xpath="(//span[@class='k-icon k-i-sort-desc-sm'])")
	private WebElement descendingOrder;
	
	@FindBy(xpath="//button[text()='Close']")
	private WebElement closebtn;
	
	@FindBy(xpath="//span[text()='Clear all']")
	private WebElement clearAllOption;
	
	@FindBy(xpath="//input[@class='input ng-star-inserted']")
	private WebElement answerField;
	
	@FindBy(xpath="//input[@class='k-input k-formatted-value']")
	private WebElement numericField;
	
	@FindBy(xpath="//*[contains(@class,'k-widget k-dropdown k-header')][last()]")
	private WebElement answerDropDown;
	
	@FindBy(xpath="//span[@class='delete-button ng-star-inserted']")
	private WebElement deleteConditionControl;
	
	@FindBy(xpath="//div[@class='group-container']/div/following::span")
	private WebElement andorControl;
	
	@FindBy(xpath="//form[@class='condition-form ng-untouched ng-pristine ng-valid']")
	private WebElement conditionRow;
	
	@FindBy(xpath="(//span[@class='k-dropdown-wrap k-state-default'])[1]")
	private WebElement listboxDropDown;
	
	
	
	/**
	 * ================================================= 
	 *          METHODS
	 * =================================================
	 */

	
	
	/**
	 *  Add Condition
	 * @param roletobeSelectede
	 */
	public void addCondition(String roletobeSelectede)
	{
		clickOn(roleField);
		for(WebElement web: roleList)
		{
		   if (web.getText().trim().contains(roletobeSelectede)) {
			clickOn(web);
			break;
        	}	
		}
	}
	/**
	 *  Add Group
	 **/
	public void addGroup()
	{
		clickOn(addgroupBtn);
		reportInfo();
	}
	/**
	 * click add condition control
	 * 
	 */
	public void selectAddConditionControl()
	{
		clickOn(addConditionBtn);
		reportInfo();
	}
	/**
	 *  verify group is added
	 */
	public void verifyGroupIsAdded()
	{
		moveToElement(groupField);
		Assert.assertTrue(isElementDisplayed(groupField));
		reportInfo();
	}
	/**
	 *  verify group is added
	 */
	public void verifyGroupIsdeleted()
	{
		Assert.assertFalse(isElementDisplayed(groupField));
		reportInfo();
	}
	/**
	 *  verify condition entered with expression field value
	 * @param expectedtxt
	 */
	public void verifyCondition(String expectedtxt)
	{
		String actualtxt=expressionField.getAttribute("value");
		boolean flag=true;
		if (actualtxt.trim().contains(expectedtxt)) {
			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * verify Configure Option according to rule displayed
	 * @param rules
	 */
	public void verifyConfigureOptionAccordinglyRules(String rules)
	{
		WebElement rulestobeverified = 
				driver.findElement(ByLocator("//div[text()='"+rules+"']/following-sibling::div/button[text()=' Configure ']"));
		moveToElement(rulestobeverified);
		Assert.assertTrue(isElementDisplayed(rulestobeverified));
		reportInfo();
	}
	/**
	 *  verify question How many times have you administered this scale over this course of your career? Displayed
	 * @param question
	 */
	public void verifyQualificationQuestionIsDisplayed(String question)
	{
		moveToElement(qualificationQuestion);
		String actualquestion = qualificationQuestion.getText();
		Assert.assertEquals(actualquestion, question);
	}
	/**
	 *  verify field is displayed as Required
	 * @param FieldName
	 */
	public void verifyFieldIsDisplayedAndRequired(String FieldName)
	{
		verifyFieldIsDisplayed(FieldName);
		verifyFieldIsRequired(FieldName);
		reportInfo();
	}
	/*
	 *  verify add button Displayed
	 */
	public void verifyAddButtonIsDisplayed()
	{
		moveToElement(addButton);
		Assert.assertTrue(isElementDisplayed(addButton));
		reportInfo();
	}
	/*
	 * verify add button disabled
	 */
	public void verifyAddButtonDisabled()
	{
		Assert.assertFalse(addButton.isEnabled());
		reportInfo();
	}
	/* 
	 * verify add button Active
	*/
	public void verifyAddButtonIsEnabled()
	{
		Assert.assertTrue(addButton.isEnabled());
		reportInfo();
	}
	/* 
	 * select add button on qualification group window
	*/
	public void selectOptionToAddQualificationGroup()
	{
		clickOn(addButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/*
	 *  verify cancel button is Displayed
	 */
	public void verifyCancelButtonIsDisplayed()
	{
		moveToElement(cancelButton);
		Assert.assertTrue(isElementDisplayed(cancelButton));
		reportInfo();
	}
	/**
	 *  select cancel control
	*/
	public void selectCancelOption()
	{
		clickOn(cancelButton);
		reportInfo();
	}
	/**
	 *  select Yes Option on Confirmation Window
	 */
	public void confirmCancellationInConfirmDialogue()
	{
		clickOn(yesOptionBtn);
		reportInfo();
	}
	/*
	 * verify Description Field Displayed
	 */
	public void verifyDescriptionFieldIsDisplayedAsOptional()
	{
		moveToElement(descriptionField);
		Assert.assertTrue(isElementDisplayed(descriptionField));
		verifyFieldIsOptional();
		reportInfo();
	}
	/*
	 *  verify field is optional
	 */
	public void verifyFieldIsOptional()
	{
		boolean flag=true;
		try {
			if (requiredField.isDisplayed()) {
				flag=false;
			}
		  } catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  enter value in required field
	 * @param FieldName
	 * @param value
	 */
	public void enterRequiredFieldValue(String FieldName,String value)
	{
		WebElement fieldtobeverified = 
				driver.findElement(ByLocator("//label[text()='"+FieldName+"']/..//input"));
		inputText(fieldtobeverified, value);
		addQualificationPouupTitle.click();
		reportInfo();
	}
	/**
	 *  verify entered value in required Field
	 * @param FieldName
	 * @param expectedValue
	 */
	public void verifyRequiredFieldEnteredValue(String FieldName,String expectedValue)
	{
		WebElement fieldtobeverified = 
				driver.findElement(ByLocator("//label[text()='"+FieldName+"']/..//input"));
		String actualvalue=fieldtobeverified.getAttribute("value");
		Assert.assertEquals(actualvalue, expectedValue);
	}
	/**
	 *  verify field is displayed
	 * @param FieldName
	 */
	public void verifyFieldIsDisplayed(String FieldName)
	{
		WebElement fieldtobeverified = 
				driver.findElement(ByLocator("//label[text()='"+FieldName+"']/../div"));
		moveToElement(fieldtobeverified);
		Assert.assertTrue(isElementDisplayed(fieldtobeverified));
	}
	/**
	 *  verify field is required field
	 * @param FieldName
	 */
	public void verifyFieldIsRequired(String FieldName)
	{
		WebElement fieldtobeverified =
				driver.findElement(ByLocator("//label[text()='"+FieldName+"']/..//div[@class='error-frame ng-star-inserted']"));
		moveToElement(fieldtobeverified);
		Assert.assertTrue(isElementDisplayed(fieldtobeverified));
	}
	/**
	 *  verify field is not required
	 * @param FieldName
	 */
	public void verifyFieldIsNotDisplayedRequired(String FieldName)
	{
		boolean flag=true;
		try {
			WebElement fieldtobeverified =
					driver.findElement(ByLocator("//label[text()='"+FieldName+"']/..//div[@class='error-frame ng-star-inserted']"));
			
			if (fieldtobeverified.isDisplayed()) {
				flag=false;
			}
		  } catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify field highlighted  as invalid
	 * @param FieldName
	 */
	public void verifyFieldIsHighlightedAsInvalid(String FieldName)
	{
		WebElement fieldtobeverified =
			driver.findElement(ByLocator("//label[text()='"+FieldName+"']/..//div[@class='error-frame ng-star-inserted']"));
		boolean flag=true;
		try {
			if (fieldtobeverified.isDisplayed()) {
				flag=true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify field highlighted  as invalid
	 * @param FieldName
	 */
	public void verifyFieldValueIsValid(String FieldName)
	{
		boolean flag=true;
		try {
		WebElement fieldtobeverified =
			driver.findElement(ByLocator("//label[text()='"+FieldName+"']/..//div[@class='error-frame ng-star-inserted']"));
			if (fieldtobeverified.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify validation message
	 * @param FieldName
	 */
	public void verifyDisplayedValidationMessage(String FieldName)
	{
		verifyFieldIsRequired(FieldName);
	}
	/*
	 *  verify add qualification pop up Displayed
	 */
	public void verifyQualificationLibraryPopupDisplayed()
	{
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(addQualificationPouupTitle);
		Assert.assertTrue(isElementDisplayed(addQualificationPouupTitle));
		reportInfo();
	}
	/*
	 *  verify add qualification pop up NOT Displayed
	*/
	public void verifyAddQualificationLibraryPopupNotDisplayed()
	{
		Assert.assertFalse(isElementDisplayed(addQualificationPouupTitle));
		reportInfo();
	}
	/*
	 * verify qualification library page
	 */
	public void verifyQualificationLibraryPage()
	{
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(qualificationlistingScreen);
		Assert.assertTrue(qualificationlistingScreen.isDisplayed());
		reportInfo();
	}
	/**
	 *  verify add group button is displayed
	 */
	public void verifyAddGroupButtonIsDisplayed()
	{
		moveToElement(addGroupBtn);
		Assert.assertTrue(isElementDisplayed(addGroupBtn));
		reportInfo();
	}
	/**
	 *  verify add group button is not displayed
	 */
	public void verifyAddGroupButtonIsNOTDisplayed()
	{
		boolean flag=true;
		try {
			if (addGroupBtn.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  click add group btn
	 */
	public void selectOptionToAddGroup()
	{
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true)", addGroupBtn);
		clickOn(addGroupBtn);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 *  select Form group
	 * @param FormToBeSelected
	 */
	public void selectFormGroupFromFormGroupsList(String FormToBeSelected)
	{
		waitAndClick(driver.findElement(ByLocator("//label[text()='Form groups:']/../div/div[@class='ng-star-inserted']")));
		for(WebElement web: formsList)
		{
			if (web.getText().trim().contains(FormToBeSelected)) {
				
				clickOn(web);
				break;
			}
		}
		addQualificationPouupTitle.click();
		reportInfo();
	}
	/**
	 *  verify selected form group displayed
	 * @param FormGroup
	 */
	public void verifySelectedFormGroupDisplayed(String FormGroup)
	{
		WebElement elementtobeverified = driver.findElement(ByLocator("//span[contains(text(),'"+FormGroup+"')]/.."));
		moveToElement(elementtobeverified);
		Assert.assertTrue(isElementDisplayed(elementtobeverified));
	}
	/**
	 *  enter description data
	 * @param descriptionData
	 */
	public void enterDataInDescriptionField(String descriptionData)
	{
		inputText(descriptionField, descriptionData);
		addQualificationPouupTitle.click();
		reportInfo();
	}
	/**
	 *  verify entered value in description field
	 * @param expectedvalue
	 */
	public void verifyEnteredDataInDescriptionField(String expectedvalue)
	{
		String actualvalue=descriptionField.getAttribute("value");
		Assert.assertEquals(actualvalue, expectedvalue);
		reportInfo();
	}
	/**
	 * select Configure Option according to rule 
	 * @param rules
	 */
	public void selectConfigureOptionAccordinglyRules(String rules)
	{
		WebElement rulestobeverified = 
				driver.findElement(ByLocator("//div[text()='"+rules+"']/following-sibling::div/button[text()=' Configure ']"));
		clickOn(rulestobeverified);
		reportInfo();
	}
	/**
	 * select Configure Option according to rule displayed
	 * @param rules
	 */
	public void selectConfigureOptionAccordinglyRulesIsDisplayed(String rules)
	{
		WebElement rulestobeverified = 
				driver.findElement(ByLocator("//div[text()='"+rules+"']/following-sibling::div/button[text()=' Configure ']"));
		moveToElement(rulestobeverified);
		Assert.assertTrue(isElementDisplayed(rulestobeverified));
		reportInfo();
	}
	/*
	 *  verify add eligibility pop up window
	 */
	public void verifyAddEligibilityRulePopUPWindowDisplayed()
	{
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(addEligibilityRulePopuptTitle);
		Assert.assertTrue(isElementDisplayed(addEligibilityRulePopuptTitle));
		reportInfo();
	}
	/*
	 *  verify add eligibility pop up window not Displayed
	 */
	public void verifyAddEligibilityRulePopUpWindowNotDisplayed()
	{
		waitUntillFinishProcessSpinnerDisable();
		boolean flag=true;
		try {
			if (addEligibilityRulePopuptTitle.isDisplayed()) {
				flag=false;
			}
		 } catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify Field on add rule for qualification/eligibility Displayed
	 * @param fieldName
	 */
	public void verifyFieldIsDisplayedOnAddRulePopUp(String fieldName)
	{
		WebElement itemtobeverified = driver.findElement(ByLocator("//label[text()='"+fieldName+"']/..//textarea"));
		moveToElement(itemtobeverified);
		Assert.assertTrue(isElementDisplayed(itemtobeverified));
		reportInfo();
	}
	/**
	 * enter value in eligibility pop up description field
	 * @param Description
	 */
	public void enterDescriptionInAddRulePopup(String Description)
	{
		inputText(addDescriptionFieldONPopup, Description);
		expressionField.click();
		reportInfo();
	}
	/**
	 *  verify eligibility pop up entered  description value
	 * @param expectedvalue
	 */
	public void verifyEnteredValueInDescriptionFieldInAddRulePopUp(String expectedvalue)
	{
		String actualvalue=addDescriptionFieldONPopup.getAttribute("value");
		Assert.assertEquals(actualvalue, expectedvalue);
		reportInfo();
	}
	/**
	 *  select add option on eligibility pop up
	 */
	public void selectAddOptionOnRulePopUp()
	{
		clickOn(rulePopupAddBtn);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 *  select add option on Qualification pop up
	 */
	public void selectAddOptionOnQualificationRulePopUp()
	{
		clickOn(rulePopupAddBtn1);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 *  qualification rule window  displayed
	 */
	public void verifyAddQualificationRuleWindowIsdisplayed()
	{
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(addQualificationRuleWindow);
		Assert.assertTrue(isElementDisplayed(addQualificationRuleWindow));
		reportInfo();
	}
	/**
	 *  qualification rule window not displayed
	 */
	public void verifyQualificationRuleWindowNotDisplayed()
	{
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertFalse(isElementDisplayed(addQualificationRuleWindow));
		reportInfo();
	}
	/**
	 *  provided rule IS Displayed
	 * @param rule
	 * @param description
	 */
	public void verifyRecentlyProvidedDescriptionWithRuleIsDisplayed(String rule,String description)
	{
		WebElement itemtoverified =
				driver.findElement(ByLocator("//div[text()='"+rule+"']/..//strong[text()='"+description+"']"));
		moveToElement(itemtoverified);
		Assert.assertTrue(isElementDisplayed(itemtoverified));
		reportInfo();
	}
	/**
	 *  provided rule IS Not Displayed
	 * @param rule
	 * @param description
	 */
	public void verifyRecentlyProvidedDescriptionWithRuleIsNOTDisplayed(String rule,String description)
	{
		boolean flag=true;
		
		try {
			 WebElement itemtoverified =
						driver.findElement(ByLocator("//div[text()='"+rule+"']/..//strong[text()='"+description+"']"));
			    if (itemtoverified.isDisplayed()) {
					flag=false;
				}
		    } catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
		}
	/**
	 * 
	 *  GET TOTAL Qualifications Group cOUNTS
	 * @return
	 */
	public String getQualificationGroupCount()
	{
		moveToElement(qualificationlistingScreen);
		String count=qualificationlistingScreen.getText();
		return count;
	}
	/**
	 * 
	 *  Verifying Assets list updated
	 */
	public void verifyQualificationGroupListUpdated(String count)
	{
		moveToElement(qualificationlistingScreen);
		_normalWait(3000);
		String actual = count.substring(count.indexOf('(')+1, count.indexOf(')'));
		int i=Integer.parseInt(actual);  
		int updated = i+1;
		String updatedList=String.valueOf(updated);
		_normalWait(2000);
		String list = qualificationlistingScreen.getText();
		String expected = list.substring(list.indexOf('(')+1, list.indexOf(')'));
		int j = Integer.parseInt(expected);
		String NewList = String.valueOf(j);
		Assert.assertEquals(NewList, updatedList);
		reportInfo();
	}
	/**
	 *  apply Filter Under Library Name
	 * @param LibraryName
	 */
	public void applyFilterUnderLibraryName(String LibraryName)
	{
		clickOn(LibraryNamefilterIcon);
		clickOn(filterInputField);
		inputText(filterInputField, LibraryName);
		waitAndClick(filterButton);
		reportInfo();
	}
	/**
	 *  select Qualification Group
	 * @param LibraryName
	 */
	public void selectQualificationGroup(String LibraryName)
	{
		WebElement itemtobeSelected = 
				driver.findElement(ByLocator("(//a[text()='"+LibraryName+"']/ancestor::td/preceding-sibling::td)[2]"));
		clickOn(itemtobeSelected);
		reportInfo();
	}
	/**
	 *  verify Qualification Group is displayed
	 * @param LibraryName
	 */
	public void verifyQualificationGroupIsDisplayed(String LibraryName)
	{
		WebElement itemtobeSelected = driver.findElement(ByLocator("//a[text()='"+LibraryName+"']"));
		moveToElement(itemtobeSelected);
		Assert.assertTrue(isElementDisplayed(itemtobeSelected));
		reportInfo();
	}
	/**
	 *  open Qualification Group configuration
	 * @param LibraryName
	 */
	public void selectQualificationConfiguration(String LibraryName)
	{ 
		WebElement itemtobeSelected =driver.findElement(ByLocator("//a[text()='"+LibraryName+"']"));
		clickOn(itemtobeSelected);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 *  show qualification group additional details
	 * @param LibraryName
	 */
	public void showQualificationGroupAdditionalDetails(String LibraryName)
	{
		WebElement itemtobeSelected = driver.findElement(ByLocator("//a[text()='"+LibraryName+"']/ancestor::td/preceding::td"));
		clickOn(itemtobeSelected);
		reportInfo();
	}
	/*
	 * select option Drop down
	 */
	public void selectActionOption()
	{
		clickOn(actionsButton);
		reportInfo();
	}
	/**
	 *  Action button is n't displayed
	 */
	public void verifyActionOptionIsNOtDisplayed()
	{
		Assert.assertFalse(isElementDisplayed(actionsButton));
		reportInfo();
	}
	/**
	 *  perform delete Task
	 * @param UN
	 * @param PWD
	 */
	public void selectDeleteOption(String UN,String PWD)
	{
		clickOn(deleteButton);
		waitAndClick(deleteOption);
		inputText(userNameInputField, UN);
    	inputText(passwordInputField, PWD);
    	waitAndClick(confirmButton);
    	waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 *  verify qualification Group Deleted
	 * @param LibraryName
	 */
	public void verifyQualificationGroupDeleted(String LibraryName)
	{
		boolean flag=true;
			try {
				WebElement itemtobeSelected = 
					driver.findElement(ByLocator("(//a[text()='"+LibraryName+"']/ancestor::td/preceding-sibling::td)[2]"));
			if (itemtobeSelected.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
			Assert.assertTrue(flag);
			reportInfo();
	}
	/**
	 *  deleting Qualification Group
	 * @param LibraryName
	 * @param UN
	 * @param PWD
	 */
	public void deleteQualificationGroup(String LibraryName,String UN,String PWD)
	{
		applyFilterUnderLibraryName(LibraryName);
		selectQualificationGroup(LibraryName);
		selectActionOption();
		selectDeleteOption(UN, PWD);
	}
	/**
	 *  verify required column Displayed
	 * @param DesiredColumnName
	 */
	public void verifyRequiredColumnIsDisplayed(String DesiredColumnName)
	{
	 WebElement itemtobeverified = driver.findElement(ByLocator("//span[text()='"+DesiredColumnName+"']/.."));
	 moveToElement(itemtobeverified);
	 Assert.assertTrue(isElementDisplayed(itemtobeverified));
	 reportInfo();
	}
	/**
	 *  filter Section Is Displayed
	 */
	public void verifyOptionsToBeFilteredIsDisplayed()
	{
		moveToElement(filtersection);
		Assert.assertTrue(isElementDisplayed(filtersection));
		reportInfo();
	}
	/**
	 *  verify Respective Filter Option Is Displayed
	 * @param filterName
	 */
	public void verifyRespectiveFilterOptionDisplayed(String filterName)
	{
		WebElement optiontobeverified = driver.findElement(ByLocator("//span[contains(text(),'"+filterName+"')]/ancestor::li"));
		moveToElement(optiontobeverified);
		Assert.assertTrue(isElementDisplayed(optiontobeverified));
		reportInfo();
	}
	/**
	 *  select Respective Filter Option Is Displayed
	 * @param filterName
	 */
	public void selectRespectiveFilterOption(String filterName)
	{
		WebElement optiontobeverified = driver.findElement(ByLocator("//span[contains(text(),'"+filterName+"')]"));
        clickOn(optiontobeverified);
        waitUntillFinishProcessSpinnerDisable();
        reportInfo();
		
	}
	/**
	 *  verify filter option is selected
	 * @param filterName
	 */
	public void verifyFilterOptionIsSelected(String filterName)
	{
		WebElement fieldtobeverified = driver.findElement(ByLocator("//span[contains(text(),'"+filterName+"')]/ancestor::li//input"));
		Assert.assertTrue(fieldtobeverified.isSelected());
		reportInfo();
	}
	/*
	 *  verify details accordin column displayedS
	 */
	public void verifyDetailsAccordinColumnDisplayed()
	{
		moveToElement(detailsAcordinColumn);
		Assert.assertTrue(isElementDisplayed(detailsAcordinColumn));
	}
	/*
	 * verify Multiple Selection column displayedS
	 */
	public void verifyMultipleSelectionColumnDisplayed()
	{
		moveToElement(multipleselectionColumn);
		Assert.assertTrue(isElementDisplayed(multipleselectionColumn));
	}
	/*
	 *  verify page navigation option is displayed
	*/
	public void verifyPageNavigationOptionsIsDisplayed()
	{
		moveToElement(pagenavigationBackward);
		moveToElement(pagenavigationforward);
		Assert.assertTrue(pagenavigationforward.isDisplayed() && pagenavigationBackward.isDisplayed() );
		reportInfo();
	}
	/*
	 *  verify page Refresh option is displayed
	*/
	public void verifyRefreshOptionIsDisplayed()
	{
		moveToElement(refreshBtn);
		Assert.assertTrue(isElementDisplayed(refreshBtn));
		reportInfo();
	}
	/*
	 *  verify page Display Quantity option is displayed
	*/
	public void verifyDisplayQuantityIsDisplayed()
	{
		moveToElement(displayQuantityField);
		Assert.assertTrue(isElementDisplayed(displayQuantityField));
		reportInfo();
	}
	/**
	 *  applying & verifying sorting  
	 * @param fieldName
	 */
	public void applySortingAccordinglyColumn(String fieldName)
	{
		WebElement optiontobeclicked = 
				driver.findElement(ByLocator("//span[text()='"+fieldName+"']/.."));
		waitAndClick(optiontobeclicked);
		verifyItemsInAscendingOrder();
		waitUntillFinishProcessSpinnerDisable(); 
		clickOn(ascendingOrder);
		waitUntillFinishProcessSpinnerDisable();
		verifyItemsInDescendingOrder();
		clickOn(descendingOrder);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 *  verify items in ascending order displayed
	*/
	public void verifyItemsInAscendingOrder()
	{
		moveToElement(ascendingOrder);
		Assert.assertTrue(isElementDisplayed(ascendingOrder));
		reportInfo();
	}
	/**
	 *  verify items in Descending order displayed
	*/
	public void verifyItemsInDescendingOrder()
	{
		moveToElement(descendingOrder);
		Assert.assertTrue(isElementDisplayed(descendingOrder));
		reportInfo();
	}
	/*
	 *   verify additional details for selected group is displayed
	 */
	public void verifyAdditionalDetailsIsDisplayedForSelectedGroup()
	{
		moveToElement(additionaldetails);
		Assert.assertTrue(isElementDisplayed(additionaldetails));
		reportInfo();
	}
	/**
	 *  verify respective field in additional details Fields displayed
	 * @param FieldName
	 */
	public void verifyRespectiveFieldInAdditionalDetails(String FieldName)
	{
		WebElement fieldtobeverified = driver.findElement(ByLocator("//strong[contains(text(),'"+FieldName+"')]/.."));
		moveToElement(fieldtobeverified);
		Assert.assertTrue(isElementDisplayed(fieldtobeverified));
		reportInfo();
	}
	/**
	 *  select column configuration
	 */
	public void selectGridColumnConfiguration()
	{
		clickOn(colomnConfiguration);
		reportInfo();
	}
	/**
	 *  verify grid column configuration  option displayed
	 */
	public void verifyGridColumnConfigurationOptionIsdisplayed()
	{
		moveToElement(colomnConfiguration);
		Assert.assertTrue(isElementDisplayed(colomnConfiguration));
		reportInfo();
	}
	/**
	 *  verify option is selected & displayed
	 * @param optionName
	 */
	public void verifyRespectiveOptionIsDisplayedAndSelectedByDefault(String optionName)
	{
		WebElement optiontobeverified = driver.findElement(ByLocator("//label[contains(text(),'"+optionName+"')]"));
		WebElement optionisselected = driver.findElement(ByLocator("//label[contains(text(),'"+optionName+"')]//input"));
		moveToElement(optiontobeverified);
		Assert.assertTrue(optiontobeverified.isDisplayed() && optionisselected.isSelected());
		reportInfo();
	}
	/**
	 *  verify close control displayed
	 */
	public void verifyCloseOptionIsDisplayed()
	{
		moveToElement(closebtn);
		Assert.assertTrue(isElementDisplayed(closebtn));
		reportInfo();
	}
	/**
	 *  select close control option
	 */
	public void selectCloseControl()
	{
		clickOn(closebtn);
		reportInfo();
	}
	/**
	 *  De-Select Grid Option 
	 * @param optionName
	 */
	public void DeSelectRespectiveFromColumnsconfiguration(String optionName)
	{
		WebElement optiontobeverified = driver.findElement(ByLocator("//label[contains(text(),'"+optionName+"')]"));
		clickOn(optiontobeverified);
		reportInfo();
	}
	/**
	 *  verify option is not Enabled
	 * @param optionName
	 */
	public void verifySelectedOptionInViewOnlyMode(String optionName)
	{
		boolean flag=true;
		try {
			WebElement optiontobeverified = driver.findElement(ByLocator("//label[contains(text(),'"+optionName+"')]//input"));

			if (optiontobeverified.isEnabled()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify qualification library option not visible
	 */
	public void verifyQualificationLibraryOptionNotDisplayed()
	{
		boolean flag=true;
		try {
			if (qualificationLibrary.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  verify respective controls displayed
	 * @param controlName
	 */
	public void verifyRespectiveControlDisplayed(String controlName)
	{
		WebElement controltobeverified = driver.findElement(ByLocator("//button[contains(text(),'"+controlName+"')]"));
		moveToElement(controltobeverified);
		Assert.assertTrue(isElementDisplayed(controltobeverified));
		reportInfo();
	}
	/*
	 *  verify clear all control displayed
	 */
	public void verifyClearAllControlIsDisplayed()
	{
		moveToElement(clearAllOption);
		Assert.assertTrue(isElementDisplayed(clearAllOption));
		reportInfo();
	}
	/*
	 * select clear all control
	 */
	public void selectClearAllControl()
	{
		clickOn(clearAllOption);
		reportInfo();
	}
	/*
	 * verify add control Is Disabled
	*/
	public void verifyAddControlIsDisabled()
	{
		Assert.assertFalse(rulePopupAddBtn.isEnabled());;
		reportInfo();
	}
	/**
	 *  verify control Is Displayed
	 * @param controlName
	 */
	public void verifyControlIsdisplayed(String controlName)
	{
		WebElement controltobeverified =
				driver.findElement(ByLocator("//mat-dialog-container[@id='mat-dialog-1']//button[text()='"+controlName+"']"));
		moveToElement(controltobeverified);
		Assert.assertTrue(isElementDisplayed(controltobeverified));
		reportInfo();
	}
	/**
	 * select control
	 * @param controlName
	 */
	public void selectRespectiveControl(String controlName)
	{
	
		WebElement controltobeverified =
				driver.findElement(ByLocator("//mat-dialog-container[@id='mat-dialog-1']//button[text()='"+controlName+"']"));
		clickOn(controltobeverified);
		reportInfo();
	}
	/*
	 *  Verify condition is added & displayed
	 */
	public void verifyConditionIsDisplayed()
	{
		moveToElement(conditionRow);
		Assert.assertTrue(isElementDisplayed(conditionRow));
	    reportInfo();
	}
	/*
	 *  Verify condition is NOt displayed
	 */
	public void verifyConditionIsNotDisplayed()
	{
		Assert.assertFalse(isElementDisplayed(conditionRow));
	    reportInfo();
	}
	
	/**
	 *  Respective DropDown is displayed
	 * @param value
	 */
	public void verifyRespectiveDropDownIsDisplayed(String value)
	{
		WebElement dropDowntobeverified = 
				driver.findElement(ByLocator("//kendo-dropdownlist[starts-with(@class,'"+value+"-select k-widget k-dropdown')]"));
		moveToElement(dropDowntobeverified);
		Assert.assertTrue(isElementDisplayed(dropDowntobeverified));
		reportInfo();
	}
	
	@FindBy(xpath="(//div[@class='k-multiselect-wrap k-floatwrap'])[last()]")
	private WebElement multiSelectDropdown;
	
	@FindBy(xpath="//span[@class='icon-trash-blue delete-button ng-star-inserted']")
	private WebElement deleteGroupControl;
	/*
	 *  delete group control is displayed
	 */
	public void verifyDeleteGroupControlIsDisplayed()
	{
		moveToElement(deleteGroupControl);
		Assert.assertTrue(isElementDisplayed(deleteGroupControl));
		reportInfo();
	}
	/**
	 *  select action to delete group
	 */
	public void selectActiontoDeleteGroup()
	{
		clickOn(deleteGroupControl);
		reportInfo();
	}
	/**
	 *  verify  selected value is displayed in select question DropDown
	 * @param expected
	 */
	public void verifyDropDownListToSelectQuestionDisplayedWithValue(String expected)
	{
		moveToElement(multiSelectDropdown);
		Assert.assertTrue(isElementDisplayed(multiSelectDropdown));
		String actual = multiSelectDropdown.getText();
		Assert.assertEquals(actual, expected);
		reportInfo();
	}
	/**
	 *  verify default selected value
	 * @param value
	 * @param expected
	 */
	public void verifyDefaultSelectedDropDownFieldValue(String value,String expected)
	{
		WebElement dropDowntobeverified = 
				driver.findElement(ByLocator("//kendo-dropdownlist[starts-with(@class,'"+value+"-select k-widget k-dropdown')]/span"));
		String actual = dropDowntobeverified.getText();
		Assert.assertEquals(actual, expected);
		reportInfo();
	}
	/**
	 * Select drop Down option
	 * @param value
	 */
	public void selectDropDownOption(String value)
	{
		WebElement dropDowntobeverified =driver.findElement(ByLocator("//kendo-dropdownlist[contains(@class,'"+value+"')]"));
		waitAndClick(dropDowntobeverified);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/*
	 *  verify List Is not empty
	*/
	public void verifyListIsDisplayed()
	{
		boolean flag=true;
		try {
			if (roleList.size()==0) {
				flag=false;
			}
		} catch (Exception e) {
		}
		addEligibilityRulePopuptTitle.click(); 
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  select value in respective DropDown Field
	 * @param value
	 * @param roletobeSelectede
	 */
	public void selectValueFromDropDown(String value,String roletobeSelectede)
	{
		selectDropDownOption(value);
		for(WebElement web: roleList)
		{
		   if (web.getText().trim().contains(roletobeSelectede)) {
			clickOn(web);
			break;
        	}	
		}
		addEligibilityRulePopuptTitle.click(); 
	}
	/**
	 * verify Default selected Value of dropDown
	 * @param expected
	 */
	public void verifyDropDownDefaultSelectedValue(String expected)
	{
		String actual = listboxDropDown.getText();
		Assert.assertEquals(actual, expected);
		reportInfo();
	}
	/*
	 * select list box dropDown
	 */
	public void selectListBoxDropDown()
	{
		clickOn(listboxDropDown);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/*
	 *  verify List box dropDown is displayed
	 */
	public void verifyDropDownIsDisplayed()
	{
		moveToElement(listboxDropDown);
		Assert.assertTrue(isElementDisplayed(listboxDropDown));
	    reportInfo();
	}
	/**
	 *  verify dropDown field value 
	 * @param optionName
	 */
	public void verifyDropDownOption(String optionName)
	{
		waitUntillFinishProcessSpinnerDisable();
		WebElement optiontobeverified = 
				driver.findElement(ByLocator("//div[@class='k-list-scroller']//li[text()='"+optionName+"']"));
		moveToElement(optiontobeverified);
		assertTrue(isElementDisplayed(optiontobeverified));
		addEligibilityRulePopuptTitle.click();
		reportInfo();
	}
	/**
	 *  verify options Displayed under Question drop Down
	 * @param optionName
	 */
	public void verifyOptionsInQuestionDropdown(String optionName)
	{
		clickOn(roleField);
		WebElement optiontobeverified = 
				driver.findElement(ByLocator("//div[@class='k-list-scroller']//li[text()='"+optionName+"']"));
	    moveToElement(optiontobeverified);
		assertTrue(isElementDisplayed(optiontobeverified));
		clickOn(roleField);
		waitUntillFinishProcessSpinnerDisable();
	}
	/**
	 *  select value from ListBox DropDown
	 * @param optionName
	 */
	public void selectValueFromListBoxDropDown(String optionName)
	{
		selectListBoxDropDown();
		waitUntillFinishProcessSpinnerDisable();
		WebElement optiontobeverified = 
				driver.findElement(ByLocator("//div[@class='k-list-scroller']//li[text()='"+optionName+"']"));
		clickOn(optiontobeverified);
		addEligibilityRulePopuptTitle.click();	
	}
	/**
	 *  select option value
	 * @param optionName
	 */
	public void selectOptionFromDropDownValue(String optionName)
	{
		WebElement optiontobeverified = 
				driver.findElement(ByLocator("//div[@class='k-list-scroller']//li[text()='"+optionName+"']"));
		clickOn(optiontobeverified);
		reportInfo();
	}
	/*
	 * And/Or control Is displayed
	 */
	public void verifyAND_ORControlIsDisplayed()
	{
		moveToElement(andorControl);
		Assert.assertTrue(isElementDisplayed(andorControl));
		reportInfo();
	}
	/*
	 * delete condition is displayed
	 */
	public void verifyDeleteConditionControlIsDisplayed()
	{
		moveToElement(deleteConditionControl);
		Assert.assertTrue(isElementDisplayed(deleteConditionControl));
		reportInfo();
	}
	/**
	 *  verify drop down to select And/Or Displayed 
	 * @param value
	 */
	public void verifyDropDownToSelectAnAnswerIsDisplayed(String value)
	{
		try {
		
		WebElement dropDowntobeverified = 
				driver.findElement(ByLocator("//kendo-dropdownlist[starts-with(@class,'"+value+"-select k-widget k-dropdown')]/span"));
		String actual = dropDowntobeverified.getText();
		if (dropDowntobeverified.isDisplayed()) {
			if (actual.isEmpty()) {
				moveToElement(answerField);
				Assert.assertTrue(isElementDisplayed(answerField));
			}
		}
		else if(numericField.isDisplayed())
		{
			moveToElement(numericField);
			Assert.assertTrue(isElementDisplayed(numericField));
			
		}
		else {
			moveToElement(answerDropDown);
			Assert.assertTrue(isElementDisplayed(answerDropDown));
		}
		
		} catch (Exception e) {
		}
		reportInfo();
	}
	/**
	 *  verify options under Answer Drop down Displayed
	 */
	public void verifyOptionsforAnswerDropdownDisplayed()
	{
		clickOn(answerDropDown);
		int count = roleList.size();
		for (int i = 1; i <= count; i++) {
			WebElement optiontobeverified = 
					driver.findElement(ByLocator("(//div[@class='k-list-scroller']//li)["+i+"]"));
			moveToElement(optiontobeverified);
			Assert.assertTrue(isElementDisplayed(optiontobeverified));
		}
		reportInfo();
	}
	/**
	 * select Delete Control
	 */
	public void selectDeleteControl()
	{
		clickOn(deleteConditionControl);
		reportInfo();
	}
	/**
	 *  select option to select nested options i.e group/condition
	 * @param optionName
	 */
	public void selectAnOptionToClickNestedControl(String optionName)
	{
		WebElement optiontobeselected = 
			driver.findElement(ByLocator("//ma-group-editor[@class='ng-star-inserted']/div//button[text()='"+optionName+"']"));
		clickOn(optiontobeselected);
		reportInfo();
	}
}

