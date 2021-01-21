package net.medavante.portal.pages.administration;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationPeoplePage extends BasePage {

	@FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Add']")
	private WebElement addPeopleBTN;

	@FindBy(xpath = "//div[@id='portal-grid-page-content']//input[@name='search']")
	private WebElement searchINP;

	@FindBy(xpath = "//span[@class='icon-small icon-delete']")
	private WebElement deleteBTN;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'General')]")
	private WebElement generalTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Contact Information')]")
	private WebElement contactInfoTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Organizations')]")
	private WebElement organizationsTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Forms Activation')]")
	private WebElement formsActivationTAB;

	@FindBy(xpath = "//div[@class='details-grid search-block']//input[@name='search']")
	private WebElement peopleSearchInputTextBox;

	@FindBy(xpath = "(//div[@class='details-grid search-block']//span[@class='icon-small icon-search'])[1]")
	private WebElement searchPeopleIcon;

	@FindBy(xpath = "//div[@class='scroll-wrapper']/div[2]/span[@class='item-name ng-binding']")
	private WebElement clickOnSearchPeople;

	@FindBy(xpath = "//div[@class='scroll-wrapper']//div[@data-ng-click='selectItem(item)']//span[@class='item-name ng-binding']")
	private List<WebElement> personList;
	
	@FindBy(xpath="//span[@class='icon-small icon-cancel']/..")
	private WebElement cancel;
	
	@FindBy(xpath="//span[@class='icon-small icon-save']//parent::a")
	private WebElement saveIcon;

	public AdministrationPeoplePage(WebDriver driver) {
		super(driver);
	}

	public AdministrationPeopleGeneralPage navigateToPeopleGeneralTab() {
		waitForElementClickable(generalTAB, 10);
		waitAndClick(generalTAB);
		return PageFactory.initElements(driver, AdministrationPeopleGeneralPage.class);
	}

	public AdministrationPeopleContactInformationPage navigateToPeopleContactInforrmationTab() {
		waitForElementClickable(contactInfoTAB, 10);
		waitAndClick(contactInfoTAB);
		return PageFactory.initElements(driver, AdministrationPeopleContactInformationPage.class);
	}

	public AdministrationPeopleOrganizationsPage navigateToPeopleOrganizationsTab() {
		waitForElementClickable(organizationsTAB, 10);
		_normalWait(2000);
		waitAndClick(organizationsTAB);
		return PageFactory.initElements(driver, AdministrationPeopleOrganizationsPage.class);
	}

	public AdministrationPeopleQualificationsPage navigateToPeopleQualificationsTab() {

		waitForElementClickable(formsActivationTAB, 5);
		javascriptButtonClick(formsActivationTAB);
		return PageFactory.initElements(driver, AdministrationPeopleQualificationsPage.class);
	}

	public void searchPerson(String searchPersonName) {
		_normalWait(2000);
		inputText(peopleSearchInputTextBox, searchPersonName);
		_normalWait(2000);
		List<WebElement> newPeopleList = personList;
		waitSpinnerToBecomeInvisible();
		for (WebElement displayPeople : newPeopleList) {
			_normalWait(1000);
			if(displayPeople.getText().contains(searchPersonName)){
			waitAndClick(displayPeople);
			waitSpinnerToBecomeInvisible();
			break;
			}
		}
	}

	public void verifyAllTabsPresentUnderPeopleTab() {
		Assert.assertTrue(isElementPresent(generalTAB) && isElementPresent(contactInfoTAB)
				&& isElementPresent(organizationsTAB) && isElementPresent(formsActivationTAB));
	}

	public void verifyPeoplePageWithAddPeopleIcon(){
		
		Assert.assertTrue(isElementDisplayed(addPeopleBTN));
	}

     /**
      * This method navigates user to people sub tab passed in the parameter
     * @date 17/09/2019
     * @version 1.0
	 * @param tabName
	 * @param classname
	 * @return
	 */
	  public <T> T NavigateToPeopleSubTab(String tabName,Class<T> classname) {
		  waitSpinnerToBecomeInvisible();
	    	String peopleTab="//ul[@class='nav nav-tabs']/li/a[contains(text(),'"+tabName+"')]";                                                          
	    	waitForElementToVisible(peopleTab, 10);
	    	clickOn(peopleTab);
	    	return PageFactory.initElements(driver, classname);
	    }
	  
	  /**
	   * Verify the personType passed in the parameter's are present
	   * @param personType
	   */
	  public void verifyPersonTypePresent(String... personType) {
		 for(String person:personType) {
			 String personLocator="//label[contains(text(),'"+person+"') and @class='ng-binding']";
			 Assert.assertTrue(isElementPresent(personLocator));
		 }
	  }
	  
	  /**
	   * click on button to activate the general mode
	   */
	  public void clickToEditTheGeneralTab() {
		  waitAndClick("//label[contains(text(),'Person Type')]/..");
	  }
	  
	  /**
	   * verify the edit mode is activated
	   */
	  public void verifyEditModeIsActivated(boolean isEditable) {
		  if(isEditable) {
			  Assert.assertTrue(Objects.isNull(getAttributeValueOfElement(cancel, "disabled")));
		  }else {
			  Assert.assertTrue(Boolean.valueOf(getAttributeValueOfElement(cancel, "disabled")));
		  }
	  }
	  
	  /**
	   * Select Person type as passed in parameter On People page
	   * @param personType
	   */
	  public void selectPersonType(String personType) {
		  String person="//label[contains(text(),'"+personType+"')]/../following-sibling::div/child::input";
		  	  if(!checkboxStatus(person))
			  clickOn(person);
		  }
	  
	  /**
	   * Deselect the person type 
	   * @param personType
	   */
	  public void deselectPersonType(String personType) {
		  String person="//label[contains(text(),'"+personType+"')]/../following-sibling::div/child::input";
		  if(checkboxStatus(person)) {
			  clickOn(person);
		  }
	  }
	  
	  /**
	   * click on save icon
	   */
	  public void clickOnSaveIcon() {
		  if(!Boolean.valueOf(saveIcon.getAttribute("disabled")))
		  waitAndClick(saveIcon);
	  }
	  
	  /**
	   * verify if editbale mode is active 
	   */
	  private void removeTheEditableMode() {
		  if(StringUtils.isAllBlank(cancel.getAttribute("disabled"))) 
			  waitAndClick(cancel);
	  }
	  
	  /**
	   * verify Person Type is set 
	   * @param personType
	   */
	  public void verifyPersonTypeIsSetTo(String personType) {
		 
		  try {
		  removeTheEditableMode();
		  String person="//label[contains(text(),'"+personType+"')]/../following-sibling::div/child::input";
		  Assert.assertTrue(getAttributeValueOfElement(person, "class").contains("ng-untouched"));
		  }catch (Exception e) {
			Reporter.log("The person Type checkbox is not selected");
		}
	  }

}