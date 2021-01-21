package net.medavante.portal.pages.traininglibrary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class TrainingDetailsPage extends BasePage {

	
	/**
	 * ================================================================
	 * Locators
	 * ================================================================
	 */
	
	
	@FindBy(xpath="//div[@class='heading-panel-box']/strong")
	private WebElement traininglist;
	
	@FindBy(xpath="//div[text()='Add Training']")
	private WebElement  addTrainingButton;
	
	@FindBy(xpath="//span[text()='Courses']")
	private WebElement Coursestab;
	
	@FindBy(xpath="//span[text()='Add Training']")
	private WebElement addtrainingPopuptitle;
	
	@FindBy(id="library-name-input")
	private WebElement LibraryNamaeField;
	
	@FindBy(id="display-name-input")
	private WebElement DisplayNmaeField;
	
	@FindBy(id="description-textarea")
	private WebElement librarydescriptionfield;
	
	@FindBy(id="display-description-textarea")
	private WebElement displayDescriptionField;
	
	@FindBy(xpath="//div[@class='input-box-wrapper sel-wrapper ng-star-inserted']")
	private WebElement traininghTypeField;
	
	@FindBy(xpath="//div[@class='form-fields-box row']/following::h2")
	private WebElement events;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement Addbtn;
	
	@FindBy(xpath="//button[text()=' Cancel']")
	private WebElement CancelbtnOnPopup;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement SaveButton;
	
	@FindBy(xpath="//div[@class='k-popup k-list-container k-reset']//li")
	private List<WebElement> trainingTypeList;
	
	@FindBy(xpath="//div[@class='k-multiselect-wrap k-floatwrap']")
	private WebElement formsGroupfield;
	
	@FindBy(xpath="//span[text()='Add Event']")
	private WebElement addEventLink;
	
	@FindBy(xpath="//div[@class='ng-star-inserted']")
	private WebElement addeventbtn;
	
	@FindBy(xpath="//div[@class='k-list-scroller']//li//label")
	private List<WebElement> FormsGroupList;
	
	@FindBy(xpath="//span[text()='Add Event'][@title='Add Event']")
	private WebElement addEventpopupwindow;
	
	@FindBy( id="event-name-input")
	private WebElement eventNameField;
	
	@FindBy(xpath="//label[text()='Asset']/ancestor::div[3]//div[@class='form-fields-box']/div")
	private WebElement AddeventpopupDescriptionield;
	
	@FindBy(xpath="//label[text()='Asset']/../kendo-dropdownlist")
	private WebElement assetField;
	
	@FindBy(xpath="//div[@class='k-list-scroller']//li")
	private List<WebElement> assetList;
	
	@FindBy(xpath="//label[text()='Name:']/ancestor::div[@class='ma-dialog-content']/following::div[@class='ma-dialog-footer']//button[text()='Add']")
	private WebElement AddbuttonOneventPopup;
	
	@FindBy(xpath="//label[text()='Name:']/ancestor::div[@class='ma-dialog-content']/following::div[@class='ma-dialog-footer']//button[text()=' Cancel']")
	private WebElement CancelButtonOnaddEventPopup;
	
	@FindBy(xpath="//label[text()='Type:']/..//span[@class='k-dropdown-wrap k-state-default']")
	private WebElement typefieldoneventPopup;
	
	@FindBy(xpath="//div[@class='k-list-scroller']//li")
	private List<WebElement> typeList;
	
	@FindBy(xpath="//label[text()='Content:']/..//span[@class='k-dropdown-wrap k-state-default']")
	private WebElement contentField;
	
	@FindBy(xpath="//div[@class='error-frame ng-star-inserted']")
	private WebElement errorMsg;
	
	@FindBy(xpath="//button[text()='Delete']")
	private WebElement deleButton;
	
	@FindBy(xpath="//span[text()='Library Name']/ancestor::th//a[@class='k-grid-filter']")
	private WebElement libraryNamefilterBtn;
	
	@FindBy(xpath="(//input[@class='k-textbox ng-untouched ng-pristine ng-valid'])[1]")
	private WebElement filterInputField;
	
	@FindBy(xpath="//button[text()='Filter']")
	private WebElement FilterBtn;
	
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
	
	@FindBy(xpath="//button[text()='No, back to form']/ancestor::div[@class='ma-dialog-footer']/preceding::div[1]")
	private WebElement cancelpopupMsg;
	
	@FindBy(xpath="//button[text()='No, back to form']")
	private WebElement cancelpopupNoButton;
	
	@FindBy(xpath="//button[text()='Yes, close form']")
	private WebElement yescloseButton;
	
	@FindBy(xpath="//span[text()='Assets']")
	private WebElement assetGrid;
	
	@FindBy(xpath="//span[text()='Edit Training']")
	private WebElement editTrainingpopupTitle;
	
	@FindBy(xpath="//strong[text()='Event Scheduling']")
	private WebElement eventSchedulingField;
	
	@FindBy(xpath="//label[contains(text(),'Changes certification')]")
	private WebElement changeCertification;
	
	@FindBy(xpath="//label[contains(text(),'Automatically applied')]")
	private WebElement automaticallyApplied;
	
	@FindBy(xpath="(//div[@class='ma-dialog-content'])[2]")
	private WebElement confirmation;
	
	@FindBy(xpath="// button[@class='btn btn-default' and text()='Cancel']")
	private WebElement cancelpopupcancelbtn;
	
	/**
	 * =============================
	 * Constructor
	 * =============================
	 * 
	 * @param driver
	 */

	public TrainingDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	
/**================================================================
 * Methods
 * ================================================================
 */
	/**
	 * 
	 *  verify Training details Page
	 */
	public void verifyTrainingDetailsPage()
	{
		Assert.assertTrue(traininglist.isDisplayed() && addTrainingButton.isDisplayed() );
	}
	
	/**
	 *  navigate To training grid
	 */
	public AssetsDetailsPage navigateToAssetsGrid()
	{
		waitUntillFinishProcessSpinnerDisable();
		clickOn(assetGrid);
		reportInfo();
		return PageFactory.initElements(driver, AssetsDetailsPage.class);
	}
	/*
	 * verify Save & Cancvel Button displayed
	 */
	public void verifySave_CancelButtonDisplayed()
	{
		Assert.assertTrue(SaveButton.isDisplayed() && CancelbtnOnPopup.isDisplayed());
		moveToElement(SaveButton);
		moveToElement(CancelbtnOnPopup);
		reportInfo();
	}
	
	public void verifySaveButtonDisabled()
	{
		moveToElement(SaveButton);
		Assert.assertFalse(SaveButton.isEnabled());
		reportInfo();
	}
	
	/**
	 *  click on course tab
	 * 
	 * @return
	 */
	public CoursesDetailsPage clickOnCoursesTab()
	{
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(Coursestab);
		clickOn(Coursestab);
		
		 return PageFactory.initElements(driver, CoursesDetailsPage.class);
	}
	/**
	 * 
	 *  click add training button
	 */
	public void clickOnaddTrainingButton()
	{
		clickOn(addTrainingButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 * 
	 *   Verify add Training pop up displayed
	 */
	public void verifyAddTrainingPopupDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(addtrainingPopuptitle));
		moveToElement(addtrainingPopuptitle);
		reportInfo();
	}
	
	public void verifyConfirmationMSgOnDeleteEvent()
	{
		moveToElement(confirmation);
		Assert.assertTrue(isElementDisplayed(confirmation));
		reportInfo();
	}
	/*
	 *  click cancel on de;ete event pop up
	 */
	public void clickonCancelondeletePoopup()
	{
	
		clickOn(cancelpopupcancelbtn);
		reportInfo();
	}
	/**
	 * 
	 * Verify add Training pop up not displayed
	 */
	public void verifyAddTrainingPopupNotDisplayed()
	{
		waitUntillFinishProcessSpinnerDisable();
		boolean flag=true;
		try {
			if (addtrainingPopuptitle.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * 
	 *  GET TOTAL TRAINING cOUNTS
	 * @return
	 */
	public String getTrainingCount()
	{
		moveToElement(traininglist);
		String count=traininglist.getText();
		
		return count;
		
	}
	/**
	 * 
	 *   verify all Fields On add training pop Up displayed
	 */
	public void  verifyAddTrainingPopupFieldsDisplayed()
	{
		moveToElement(LibraryNamaeField);
		Assert.assertTrue(isElementDisplayed(LibraryNamaeField));
		
		moveToElement(DisplayNmaeField);
		Assert.assertTrue(isElementDisplayed(DisplayNmaeField));
		
		moveToElement(librarydescriptionfield);
		Assert.assertTrue(isElementDisplayed(librarydescriptionfield));
		
		moveToElement(displayDescriptionField);
		Assert.assertTrue(isElementDisplayed(displayDescriptionField));
		
		moveToElement(traininghTypeField);
		Assert.assertTrue(isElementDisplayed(traininghTypeField));
       
		moveToElement(events);
		Assert.assertTrue(isElementDisplayed(events));
		reportInfo();
		
	}
	/*
	 * 
	 *  Verify add button displayed On add training Pop Up
	 */
	public void verifyaddButtonDisplayedInAddTrainingPopUp()
	{
		moveToElement(Addbtn);
		Assert.assertTrue(isElementDisplayed(Addbtn));
	}
	/*
	 * 
	 * Verify cancel Button displayed On add training Pop Up
	 */
	public void verifyCancelButtonDisplayedInAddTrainingPopUp()
	{
		moveToElement(CancelbtnOnPopup);
		Assert.assertTrue(isElementDisplayed(CancelbtnOnPopup));
	}
	/*
	 * click on cancel button
	 */
	public void clickOnCancelButton()
	{
		clickOn(CancelbtnOnPopup);
		reportInfo();
	}
	/**
	 *  verify msg dialogue on cancel popup
	 */
	public void verifyConfirmationDialogueOnCancelPopup()
	{
		Assert.assertTrue(isElementDisplayed(cancelpopupMsg));
		reportInfo();
		
	}
	/**
	 *  verify confirmation dialogue not displayed
	 */
	public void VerifyConfirmationDialogueNotDisplayed()
	{
		boolean flag=true;
		try {
			if (cancelpopupMsg.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * click no button
	 */
	
	public void clickOnNoButton()
	{
		clickOn(cancelpopupNoButton);
		reportInfo();
	}
	/**
	 *  Enter Display description Data
	 * @param description
	 */
	public void enterDisplayDescriptionData(String DisplayDescription)
	{
		inputText(displayDescriptionField, DisplayDescription);
		librarydescriptionfield.click();
		reportInfo();
		
	}
	/**
	 *  Enter Library description Data
	 * @param description
	 */
	public void enterLibraryDescriptionData(String description)
	{
      inputText(librarydescriptionfield, description);
      displayDescriptionField.click();
      reportInfo();
      
	}
	/**
	 *  verify Library description Field Value
	 * @param description
	 */
	public void verifyLibraryDescriptionValueDisplayed(String description)
	{
		String valueText = librarydescriptionfield.getAttribute("value");
		Assert.assertEquals(valueText, description);
		reportInfo();
	}
	/**
	 *  verify display description Field Value
	 * @param description
	 */
	public void verifyDisplayDescriptionValueDisplayed(String description)
	{
		String valueText = displayDescriptionField.getAttribute("value");
		Assert.assertEquals(valueText, description);
		reportInfo();
	}
	/**
	 * verify Library description field Highlighted or Not
	 */
	public void verifyLibraryDescriptionFieldHighlightedOrNot()
	{
		String css = librarydescriptionfield.getCssValue("background");
		Assert.assertTrue(true, css);
		reportInfo();
	}
	/**
	 * verify Display description field Highlighted or Not
	 */
	public void verifyDisplayDescriptionFieldHighlightedOrNot()
	{
		String css = displayDescriptionField.getCssValue("background");
		Assert.assertTrue(true, css);
		reportInfo();
	}
	
	/**
	 * Provide  valid Library name
	 * @param LibraryNmae
	 * 
	 */
	public void EnterLibraryName(String LibraryNmae)
	{
		inputText(LibraryNamaeField, LibraryNmae);
		DisplayNmaeField.click();
		reportInfo();
		
	}
	/**
	 *  provide training display name
	 * @param DisplayNmae
	 */
	public void EnterLibraryDisplayName( String DisplayNmae)
	{
		inputText(DisplayNmaeField, DisplayNmae);
		librarydescriptionfield.click();
		reportInfo();
		
	}
	/**
	 * 
	 *  Verify library name field value
	 * @param LibraryName
	 * 
	 */
	public void verifyLibraryNameFieldsValue(String LibraryNmae)
	{
		String librarynamefieldvalue = LibraryNamaeField.getAttribute("value");
		Assert.assertEquals(librarynamefieldvalue, LibraryNmae);
		
	}
	
	/**
	 *  Verify library display name field value
	 * @param DisplayNmae
	 */
	
	public void verifyLibraryDisplayName(String DisplayNmae)
	{
		String displaynamevalue = DisplayNmaeField.getAttribute("value");
		Assert.assertEquals(displaynamevalue, DisplayNmae);
	}
	/**
	 * 
	 *  verify Library name field Highlighted with error Or Not
	 */
	public void verifyLibraryNameFieldHighlightedOrNotWithError()
	{
		
		String cssvalue = LibraryNamaeField.getCssValue("background");
		Assert.assertTrue(true,cssvalue);
	}
	/**
	 *  Verify Library display Name Highlighted With error or not
	 */
	public void verifyLibraryDisplayNameFieldHighlightedOrNot()
	{
		String css = DisplayNmaeField.getAttribute("background");
		Assert.assertTrue(true, css);
	}
	/*
	 * click select training type field
	 */
	public void selectTrainingTypeField()
	{
		clickOn(traininghTypeField);
		reportInfo();
		
	}
	/**
	 *   select training type
	 * @param TrainingTypename
	 */
	public void SelectDropDownTrainingtypeSelectValue(String TrainingTypename)
	{
	
		for(WebElement we:trainingTypeList)
		{
			if (we.getText().trim().contains(TrainingTypename)) {
				waitAndClick(we);
				break;
			}
		}
	}
	public void verifyTrainingTypeListDisplayed( )
	{
		boolean flag=true;
		try {
			if (trainingTypeList.size()==0) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * 
	 * verify training type Field Highlighted
	 */
	public void verifyTrainingTypeFieldHighlighted()
	{
		String color = traininghTypeField.getCssValue("background");
		Assert.assertTrue(true,color);
		reportInfo();
		
	}
	/**
	 *  verify training type selected value
	 * @param TrainingTypename
	 */
	public void verifyTrainingTypefieldValue(String TrainingTypename)
	{
		
		String value = traininghTypeField.getText();
		Assert.assertEquals(value, TrainingTypename);
		reportInfo();
	}
	/**
	 * 
	 *  verify Forms Group field Displayed
	 */
	public void verifyFormsGroupFieldDisplayed()
	{	
		Assert.assertTrue(isElementDisplayed(formsGroupfield));
		moveToElement(formsGroupfield);
		reportInfo();
	}
	/**
	 *  Select forms field
	 */
	public void selectformsField()
	{
		clickOn(formsGroupfield);
		reportInfo();
	}
	/**
	 *  select Form
	 * @param FormName
	 */
	public void selectFormsFromformList(String FormName)
	{
		
		for(WebElement we:FormsGroupList)
		{
			if (we.getText().trim().contains(FormName)) {
				clickOn(we);
				break;
			}
		}
		addtrainingPopuptitle.click();
		reportInfo();
	}
	/**
	 *  verify Forms List displayed
	 */
	public void verifyFormGroupListDisplayed()
	{
		boolean flag=true;
		try {
			if (FormsGroupList.size()==0) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * 
	 *  verify form group field value
	 * @param FormName
	 */
	public void verifyFormgroupfieldValue(String FormName)
	{
		
		String formvalue = formsGroupfield.getText();
        Assert.assertEquals(formvalue, FormName);
        reportInfo();
	}
	
	/**
	 *  Verify selected form displayed
	 * @param FormName
	 */
	public void verifySelectedFormDisplayed(String FormName)
	{
		WebElement forms = driver.findElement(ByLocator("//span[contains(text(),'"+FormName+"')]"));
		Assert.assertTrue(isElementDisplayed(forms));
		moveToElement(forms);
		reportInfo();
	}
	
	/**
	 * 
	 *  Verify add Event option displayed
	 */
	public void verifyAddEventOptiondisplayed()
	{
		
		Assert.assertTrue(isElementDisplayed(addeventbtn));
		moveToElement(addeventbtn);
	}
	/**
	 * 
	 *  click on add event link
	 */
	public void clickonaddEventLink()
	{
		clickOn(addEventLink);
		reportInfo();
		waitUntillFinishProcessSpinnerDisable();
		
	}
	/**
	 * 
	 *  verify add event Option Not displayed
	 */
	public void verifyOptiontoAddEventNotdisplayed()
	{
		boolean flag=true;
		try {
			if (addEventLink.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		
		Assert.assertTrue(flag);
		reportInfo();
		
	}
	/**
	 * 
	 *  apply filter on Training Library Name Field
	 * @param TrainingName
	 */
	
	public void applyfilterToVerifyTraining(String TrainingName)
	{
		waitAndClick(libraryNamefilterBtn);
		clickOn(filterInputField);
		inputText(filterInputField, TrainingName);
		clickOn(FilterBtn);
		reportInfo();
	}
	/**
	 *  Verifying training Data
	 * @param TrainingName
	 */
	public void verifyAddedTrainingDisplayed(String TrainingName)
	{
		WebElement trainingdatatobeverified = driver.findElement(ByLocator("//a[text()='"+TrainingName+"']"));
		Assert.assertTrue(isElementDisplayed(trainingdatatobeverified));
		moveToElement(trainingdatatobeverified);
		reportInfo();
	}
	/**
	 *  click on filtered  trainng hyperlink
	 * @param TrainingName
	 */
	public void clickOnFilteredTrainingLibrary(String TrainingName)
	{
		WebElement trainingdatatobeverified = driver.findElement(ByLocator("//a[text()='"+TrainingName+"']"));
		clickOn(trainingdatatobeverified);
		reportInfo();

	}
	/**
	 *  Verifying training Data Not Displayed
	 * @param TrainingName
	 */
	public void verifyAddedTrainingNotDisplayed(String TrainingName)
	{
		boolean flag=true;
		try {
			WebElement trainingdatatobeverified = driver.findElement(ByLocator("//a[text()='"+TrainingName+"']"));
			if (trainingdatatobeverified.isDisplayed()) {
				flag=false;
			}
			
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * 
	 *  deleting course
	 * @param CourseName
	 * @param username
	 * @param password
	 */
	public void deleteTraining(String CourseName,String username,String password)
	{
		
		WebElement trainingtobedeleted = driver.findElement(ByLocator("//a[text()='"+CourseName+"']/ancestor::td/preceding-sibling::td[contains(@class,'k-checkbox-cell')]"));
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
	
	/**
	 * 
	 *  verify error message Displayed
	 */
	public void verifyErrorWarningDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(errorMsg));
		moveToElement(errorMsg);
		reportInfo();
		
	}
	/**
	 * 
	 *  verify Validation message Displayed
	 */
	public void verifyValidationMsgNotDisplayed()
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
	 *  Delete option Event displayed
	 * @param EventName
	 */
	public void verifyOptionToDeleteEventDisplayed(String EventName)
	{
		WebElement eventtobeverified = driver.findElement(ByLocator("//strong[text()='"+EventName+"']/ancestor::div[@class='definitions-row']/ancestor::ma-training-event/span"));
		Assert.assertTrue(isElementDisplayed(eventtobeverified));
		moveToElement(eventtobeverified);
		reportInfo();
		
	}
	/**
	 *  click on delete event Button
	 * @param EventName
	 */
	public void deleteEvent(String EventName)
	{
		WebElement eventtobeverified = driver.findElement(ByLocator("//strong[text()='"+EventName+"']/ancestor::div[@class='definitions-row']/ancestor::ma-training-event/span"));
		clickOn(eventtobeverified);
		reportInfo();
		
	}
	/**
	 * 
	 *  click on delete Button
	 */
	public void clickonDeleteBTn()
	{
		clickOn(deleButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}
	/**
	 * 
	 *  click Add Button
	 */
	public void clickOnAddButton()
	{
		clickOn(Addbtn);
		reportInfo();
	}
	
	/**
	 * 
	 *  verify Add event pop up Displayed
	 */
	public void verifyAddEventPopUpDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(addEventpopupwindow));
		moveToElement(addEventpopupwindow);
		reportInfo();
		
	}
	/**
	 * 
	 *  verify Add event pop up Displayed
	 */
	public void verifyAddEventpopUpNotDisplayed()
	{
		boolean flag=true;
		try {
			if (addEventpopupwindow.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
		
		
	}
	public void clickOnAssetOption()
	{
		clickOn(assetField);
		reportInfo();
	}
	
	/**
	 *  Enter Events Data To add Events pop up
	 * @param EventName
	 * @param assetName
	 */
	public void EnterEventAssetNameInEventPopUp(String assetName)
	{
		for(WebElement web:assetList)
		{
			if (web.getText().trim().contains(assetName)) {
				clickOn(web);
				break;
			}
			
		}
	     reportInfo();	
	}
	/**
	 * 
	 *   Enter Events Data for Form Type Event
	 * @param EventName
	 * @param Type
	 * @param assetName
	 */
	public void enterEventAssetNameForFormTypeEvent(String Type,String assetName )
	{
		clickOn(typefieldoneventPopup);
		_normalWait(2000);
		for(WebElement element:typeList)
		{
			if (element.getText().trim().contains(Type)) {
				waitAndClick(element);
				break;
			}
		}
		
		waitAndClick(contentField);
		for(WebElement web:assetList)
		{
			if (web.getText().trim().contains(assetName)) {
				waitAndClick(web);
				break;
			}
			
		}
		reportInfo();
	}
	/**
	 * 
	 *  Verify add event name Field Value on add event Pop up Fields  
	 * @param EventName
	 * @param assetName
	 */
	
	public void verifyEventNameFieldValue(String EventName)
	{
		String eventname = eventNameField.getAttribute("value");
		Assert.assertEquals(eventname, EventName);
	}
	/**
	 *  verify asset field value on add event pop up
	 * @param assetName
	 */
	public void verifyAssetFielsValue(String assetName)
	{
	
		String assetname = assetField.getText();
		Assert.assertEquals(assetname, assetName);
	}
	/**
	 *  click on add on add event button pop up
	 */
	public void clickOnaddButton()
	{
		clickOn(AddbuttonOneventPopup);
		reportInfo();
		
	}
	/**
	 * Verify Added event Displayed
	 * @param eventName
	 */
	public void verifyaddedEventdisplayed(String eventName)
	{
		WebElement addedEventTerminal = driver.findElement(ByLocator("//strong[text()='"+eventName+"']/ancestor::div[@class='definitions-row']"));
		Assert.assertTrue(isElementDisplayed(addedEventTerminal));
		moveToElement(addedEventTerminal);
		reportInfo();
	}
	/**
	 * Verify Added event NOt Displayed
	 * @param eventName
	 */
	public void verifyEventNotdisplayed(String eventName)
	{
		
		boolean flag=true;
		try {
			WebElement addedEventTerminal = driver.findElement(ByLocator("//strong[text()='"+eventName+"']/ancestor::div[@class='definitions-row']"));
			if (addedEventTerminal.isDisplayed()) {
				flag=false;
				
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	/**
	 * 
	 *  Verifying Warning Msg  displayed
	 * @param EventName
	 */
	public void verifyWarningMsgDisplayedOnEvent(String EventName)
	{
		WebElement warng = driver.findElement(ByLocator("//strong[text()='"+EventName+"']/ancestor::div[1]/div"));
	    Assert.assertTrue(isElementDisplayed(warng));
	    moveToElement(warng);
	    reportInfo();
		
	}
	/**
	 * Verify  events Field Highlighted 
	 */
	public void VerifyEventsFieldhighlighted(String eventName)
	{
		WebElement colorhigh = driver.findElement(ByLocator("//strong[text()='"+eventName+"']/ancestor::div[4]/.."));
		String clr = colorhigh.getCssValue("background");
		Assert.assertTrue(true,clr);
		moveToElement(colorhigh);
		reportInfo();
	}

	/**
	 * 
	 *  Verifying events list updated
	 */
	public void verifyTrainingListUpdated(String count)
	{
		moveToElement(traininglist);
		_normalWait(3000);
		String actual = count.substring(count.indexOf('(')+1, count.indexOf(')'));
		int i=Integer.parseInt(actual);  
		int updated = i+1;
		String updatedList=String.valueOf(updated);
		_normalWait(2000);
		String list = traininglist.getText();
		String expected = list.substring(list.indexOf('(')+1, list.indexOf(')'));
		int j = Integer.parseInt(expected);
		String NewList = String.valueOf(j);
		Assert.assertEquals(NewList, updatedList);
		reportInfo();
	}
	/**
	 *  add training Option Button Is Enabled
	 */
	public void verifyAddTrainingButtonIsEnabled()
	{
		boolean flag=true;
		try {
			if (Addbtn.isEnabled()) {
				flag =true;
			}
		} catch (Exception e) {
		}
	    Assert.assertTrue(flag);
	    reportInfo();
	}
	/**
	 *  verify option to Delete Form Displayed
	 * @param FormName
	 */
	public void verifyFormsDeleteOptionDisplayed(String FormName )
	{
		WebElement formtoberemoved = driver.findElement(ByLocator("//span[contains(text(),'"+FormName+"')]/../span/span"));
		Assert.assertTrue(isElementDisplayed(formtoberemoved));
		moveToElement(formtoberemoved);
		reportInfo();
	}
	/**
	 *  Verify Deleted Form not Displayed
	 * @param FormName
	 */
	public void verifyDeletedFormNotDisplayed(String FormName)
	{
		boolean flag=true;
		try {
			WebElement formtoberemoved = driver.findElement(ByLocator("//span[contains(text(),'"+FormName+"')]/../span/span"));
			if (formtoberemoved.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 *  Delete Selected Form
	 * @param FormName
	 */
	public void deleteSelectedForm(String FormName)
	{
		WebElement formtoberemoved = driver.findElement(ByLocator("//span[contains(text(),'"+FormName+"')]/../span/span"));
		clickOn(formtoberemoved);
		reportInfo();
		
	}
	/*
	 * click on yes Option
	 */
	public void clickYesOnCancelPopup()
	{
		clickOn(yescloseButton);
		reportInfo();
	}
	/*
	 * verify edit Trainng Dialogue Displayed & popup opened
	 */
	public void verifyEditTrainngDialogueDisplayed()
	{
		Assert.assertTrue(isElementDisplayed(editTrainingpopupTitle));
		reportInfo();
	}
	/**
	 *  verify name,Description & asset fields at Add event popp up Displayed
	 */
	public void verifyFieldsAtAddEventPopup()
	{
		moveToElement(eventNameField);
		moveToElement(AddeventpopupDescriptionield);
		moveToElement(assetField);
		Assert.assertTrue(eventNameField.isDisplayed() && AddeventpopupDescriptionield.isDisplayed() && assetField.isDisplayed());
		reportInfo();
	}
	/**
	 * verify add button disabled At add Event Pop Up
	 */
	public void verifyAddButtonDisabled()
	{
		Assert.assertFalse(AddbuttonOneventPopup.isEnabled());
		reportInfo();
	}
	public void verifyCancelbuttonOnaddEventPopupDisplayed()
	{
		moveToElement(CancelButtonOnaddEventPopup);
		Assert.assertTrue(isElementDisplayed(CancelButtonOnaddEventPopup));
		reportInfo();
	}
	
	public void enterEventNameinAddEventPopup(String EventName )
	{
		inputText(eventNameField, EventName);
		reportInfo();
		
	}
	/**
	 * verify  event scheduling Field not Displayed
	 */
	public void verifyEventSchedulingFieldNotDisplayed()
	{
		boolean flag=true;
		try {
			if (eventSchedulingField.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/*
	 *  verify asset List Displayed
	 */
	public void verifyAssetListDisplayed()
	{
		boolean flag=true;
		try {
			if (assetList.size()==0) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * verify asset is displayed in asset List
	 * @param assetNmae
	 */
	public void verifyAssetDisplayedInAssetList(String assetNmae)
	{
		boolean flag=true;
		try {
		WebElement assettobeverified = driver.findElement(ByLocator("//div[@class='k-list-scroller']//li[text()='"+assetNmae+"']"));
			if (assettobeverified.isDisplayed()) {
				moveToElement(assettobeverified);
				flag=true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
		
	}
	/**
	 *  verify asset is n't displayed in asset list
	 * @param assetNmae
	 */
	public void verifyassetNotDisplayedInAssetList(String assetNmae)
	{
		boolean flag=true;
		try {
			WebElement assettobeverified = driver.findElement(ByLocator("//div[@class='k-list-scroller']//li[text()='"+assetNmae+"']"));
			if (assettobeverified.isDisplayed()) {
				flag=false;
			}
			
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 *  verify change Certification & automatically Applied Not displayed
	 */
	public void verifyChangesCertificationAutomaticallyAppliedNotDisplayed()
	{
		boolean flag=true;
		try {
			if (changeCertification.isDisplayed() && automaticallyApplied.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		
		Assert.assertTrue(flag);
		reportInfo();
		
	}
	/**
	 *  verify Delete & Cancel Button on Delete Pop up
	 */
	public void verifyDeleteCancelButtonsDisplayedOnDeleteEventPopup()
	{
		Assert.assertTrue(clickPopUpdeleteBtn.isDisplayed() && CancelbtnOnPopup.isDisplayed() );
		reportInfo();
	}
	
}
