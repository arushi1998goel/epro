package net.medavante.portal.pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationScaleActivationPage extends BasePage


{

	@FindBy(xpath="//div[@class='col-xs-2 btn-arrows text-center']//a")
	private WebElement expandicon;
	
	@FindBy(xpath="//div[@class='pull-right col-xs-9 search-block']")
	private WebElement searchFieldForScaleActivation;
	
	@FindBy(xpath="(//div[@class='collapsed-block open-grid' and @data-ng-click='(isSelectedItemLocked() && !canOverrideConfigLock()) ? null : selectRow()'])[1]")
	private WebElement scaleActivationDisabledTab;
	
	@FindBy(xpath="//div[@id='f3729c07-c988-4b75-b47d-36f7800155af']")
	private WebElement scaleActivationDetails;
	
	
	
	
	
	public AdministrationScaleActivationPage(WebDriver driver) {
		super(driver);
		
	}
	
	/***
	 * @param Deactivating rater
	 * @param raterName
	 * @param raterSiteName
	 */
	public void deactivateRater(String raterName,String raterSiteName)
	{
		
	String SelDeselectarrow="//label[@class='a-color ng-binding'][contains(text(),'"+raterName+"')]/ancestor::div[2]"
			+ "//span[@class='icon-flip-arrows']";
	WebElement raterSelectArrow = driver.findElement(ByLocator(SelDeselectarrow));
	waitAndClick(raterSelectArrow);
	WebElement RaterSite = driver.findElement(ByLocator("//label[contains(text(),'"+raterName+"')]/ancestor::div[4]"
			+ "//div[@class='col-xs-4']//label[contains(text(),'"+raterSiteName+"')]"));
	waitAndClick(RaterSite);
	WebElement selectDate = driver.findElement(ByLocator("//label[contains(text(),'"+raterName+"')]/ancestor::div[4]//div[@id='datepicker']/a[@class='add-on icon-calendar datepickerbutton']"));
	waitAndClick(selectDate);	
	WebElement deactiveDate = driver.findElement(By.xpath("(//div[contains(@style,'display: block')]//td[@class='day active today'])[last()]"));
	waitAndClick(deactiveDate);
	String save="//label[contains(text(),'"+raterName+"')]/ancestor::div[4]//div[@id='datepicker']/ancestor::div[@class='row grid-row item-row']//span[@class='icon-small icon-save']";
	WebElement savebutn = driver.findElement(ByLocator(save));
	waitAndClick(savebutn);
	Assert.assertTrue(true);
	
	
	}
	
	/***
	 * 
	 * @param Activating Rater
	 * @param className
	 * @param raterName
	 * @param raterSiteName
	 * @return
	 */
	
	public <T> T activateRater(Class<T> className,String raterName,String raterSiteName)
	{
		

		WebElement RaterSite = driver.findElement(ByLocator("//label[contains(text(),'"+raterName+"')]/ancestor::div[4]"
				+ "//div[@class='col-xs-4']//label[contains(text(),'"+raterSiteName+"')]"));
		waitAndClick(RaterSite);
		WebElement cancelDeactivatebtn = driver.findElement(ByLocator("//label[text()='"+raterSiteName+"']/ancestor::div//label[contains(text(),'Deactivated:')]/../following-sibling::div//a[@class='add-on icon-cancel']"));
		waitAndClick(cancelDeactivatebtn);
		String save="//label[contains(text(),'"+raterName+"')]/ancestor::div[4]//div[@id='datepicker']/ancestor::div[@class='row grid-row item-row']//span[@class='icon-small icon-save']";
		WebElement savebutn = driver.findElement(ByLocator(save));
		waitAndClick(savebutn);
		Assert.assertTrue(true);
		return PageFactory.initElements(driver, className);
	}

	/***
	 * Select expand icon
	 */
	
	public void selectExpandIcon() {

 
		waitAndClick(expandicon);
	}
	
	/***
	 * Scale activation tab is displayed in view mode
	 */

	
	public void scaleActivationTabDisplayedInViewMode() {
		moveToElement(scaleActivationDisabledTab);
		String value=scaleActivationDisabledTab.getAttribute("data-ng-click");
		if(value.contains("ItemLocked")) {
			Assert.assertTrue(true);
		}
	}

	/***
	 * Search field available for scale activation
	 */
	
	
	public void searchFieldAvailableForScaleActivation() {

		moveToElement(searchFieldForScaleActivation);
		if(searchFieldForScaleActivation.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 * Scale activation details are displayed
	 */
	
	
	public void scaleActivationDetailsAreDisplayed() {
		moveToElement(scaleActivationDetails);
		if(scaleActivationDetails.isDisplayed()) {
			Assert.assertTrue(true);
		}
	}

	
	
	

}
