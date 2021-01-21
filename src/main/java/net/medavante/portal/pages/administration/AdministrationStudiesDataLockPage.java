package net.medavante.portal.pages.administration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesDataLockPage extends BasePage{
	
	@FindBy(xpath="//div[@class='data-lock-row']/div/label[contains(text(),'Lock Date')]")
	private WebElement LockDatetab;
	
	@FindBy(xpath="//span[text()='Lock']/..")
	private WebElement LockControlButton;
	
	@FindBy(xpath="//span[text()='Analyze']/..")
	private WebElement AnalyzecontrolButton;
	
	@FindBy(xpath="//div[@class='data-lock-row']/following::div[@class='data-lock-section data-lock-row']/following::div[1]")
	private WebElement DetailsList;
	
    @FindBy(xpath="//span[@class='icon-small icon-calendar']")
    private WebElement selectDatebutton;
    
    @FindBy(xpath="//td[contains(@class,'today')]/following::td[2]")
    private WebElement FutureDate;
    
    @FindBy(xpath="//td[contains(@class,'today')]/preceding::td[2]")
    private WebElement PastDate;
    
    @FindBy(xpath="//td[contains(@class,'today')]")
    private WebElement currentDate; 

	@FindBy(xpath="//div[@class='modal-header has-action']")
	private WebElement analyzecontrolModalwindow;
	
	@FindBy(xpath="//div[@id='side-settings-dialog']//div[@class='data-lock-grid-body']")
	private List<WebElement> warningListGridDta;

	@FindBy(xpath="//span[@class='icon-small icon-export-file']/..")
	private WebElement exportToCSVButton;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-without-icon pull-right']")
	private WebElement closeButton;
	
	
	
	/****************************************************Methods****************************************************************/
	
	
	
	public AdministrationStudiesDataLockPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void verifyAdministrationStudiesDataLockPage() {
		Assert.assertTrue(isElementDisplayed(LockDatetab) && isElementDisplayed(LockControlButton));
	}
	
	
	
	/**
	 * 
	 *  verify Lock Tab Lock Date is Displayed 
	 */
	public void verifyDataLockTabLockDateFielddisplayed()
	{
		moveToElement(LockDatetab);
		Assert.assertTrue(isElementDisplayed(LockDatetab));
		
	}
	/**
	 *  verify Analyze Control is displayed
	 * 
	 */
	public void verifyAnalyzeControlIsDisplayed()
	{
		moveToElement(AnalyzecontrolButton);
		Assert.assertTrue(isElementDisplayed(AnalyzecontrolButton));
		
	}
	
	/**
	 * 
	 *  verify lock control is displayed
	 */
	
	public void verifyLockControlIsDisplayed()
	{
		moveToElement(LockControlButton);
		Assert.assertTrue(isElementDisplayed(LockControlButton));
	}
	
	/***
	 * 
	 *  verify details List is Displayed in Lock tab
	 */
	public void verifyDetailsListIsDisplayed()
	{
		
		boolean flag=true;
		try {
			if (DetailsList.isDisplayed()) {
				flag=true;
			}
			
		} catch (Exception e) {
		}
		
		Assert.assertTrue(flag);
		reportInfo();
	}
/**
 * 
 *  click on date selection button
 */
	
	public void clickToSelectDateButton()
	{
		moveToElement(selectDatebutton);
		waitAndClick(selectDatebutton);
		reportInfo();
		
	}
	/**
	 * 
	 * Select future date
	 */
	public void selectFutureDate()
	{
		moveToElement(FutureDate);
		waitAndClick(FutureDate);
		reportInfo();
	}
	/**
	 * 
	 * Verify Future Date is Disable and can't be selected
	 * 
	 */
	public void verifyselectFutureDateIsDisabled()
	{
		boolean flag=false;
		try {
			if (FutureDate.isEnabled()) {
				moveToElement(FutureDate);
				waitAndClick(FutureDate);
				flag=true;
				}
			
		} catch (Exception e) {
		}
	Assert.assertTrue(flag);
	reportInfo();
	}
	/**
	 * 
	 *  select Past Date
	 */
	public void selectPastDate()
	{
		moveToElement(PastDate);
		waitAndClick(PastDate);
		reportInfo();
	}
	/**
	 *  
	 * Verify Past Date is enable and can be selected
	 * 
	 */
	public void verifySelectPastDateIsEnabled()
	{
		clickToSelectDateButton();
		boolean flag=true;
		try {
			if (PastDate.isEnabled()) {
				moveToElement(PastDate);
				waitAndClick(PastDate);
			}
			
		} catch (Exception e) {
		}
	 Assert.assertTrue(flag);
	 reportInfo();
	}
	
	/**
	 * 
	 *  select current date
	 */
	
	public void selectCurrentDate()
	{
		waitAndClick(currentDate);
		reportInfo();
	}
	
	/**
	 * 
	 *  verify Current Day Selection Is Enabled
	 */
	
	public void verifyCurrentDateSelectionIsenabled()
	{
		clickToSelectDateButton();
		boolean flag=true;
		try {
			if (currentDate.isEnabled()) {
				moveToElement(currentDate);
				waitAndClick(currentDate);
				flag=true;
			}
			
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	/**
	 * 
	 * click on analyze control button;
	 */
	public void clickOnAnalyzeControlButton()
	{
		
		moveToElement(AnalyzecontrolButton);
		waitAndClick(AnalyzecontrolButton);
		reportInfo();
	}
	/**
	 * 
	 *  verify Modal window Displayed
	 */
	public void verifyModalWindowISDisplayed()
	{
		
		boolean flag=true;
		try {
			if (analyzecontrolModalwindow.isDisplayed()) {
				moveToElement(analyzecontrolModalwindow);
				flag=true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/**
	 * 
	 *  verify warning List Grid Data Displayed
	 */
	public void verifywarningListDataISDisplayed()
	{
		boolean flag=true;
		try {
			if (warningListGridDta.size()>0) {
			flag=true;	
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
		
	}
	/**
	 * 
	 *  Warnings for Pr#2.2 - Pr#2.5 are displayed in the list
	 * 
	 */
	public void  verifyRequisiteWarningDataIsDisplayed(String Warning)
	{
		boolean flag=true;
		try {
			WebElement Inprogressvisit = driver.findElement(ByLocator("//div[text()='"+Warning+"']/.."));
			if (Inprogressvisit.isDisplayed()) {
				moveToElement(Inprogressvisit);
				flag=true;
			}
		} catch (Exception e) {
		}
	Assert.assertTrue(flag);
	reportInfo();
   }
	
    /***
	 * 
	 *  verify export To CSV Displayed
	 */
	public void verifyExportToCSVControlIsDisplayed()
	{
		
		moveToElement(exportToCSVButton);
		Assert.assertTrue(isElementDisplayed(exportToCSVButton));
	}
	/**
	 * 
	 *  click on Export To CSV Button
	 */
	public void clickOnExportToCSVButton()
	{
		
		moveToElement(exportToCSVButton);
		waitAndClick(exportToCSVButton);
		reportInfo();
		
	}
	/**
	 * 
	 *  close Modal window
	 */
	public void closeModalWindow()
	{
		
		moveToElement(closeButton);
		waitAndClick(closeButton);
        reportInfo();
	}
	
	/**
	 * 
	 *  Click on Lock Control Button
	 */
	public void clickOnLockcontrolButton()
	{
		waitAndClick(LockControlButton);
		reportInfo();
	}
	/**
	 * 
	 *  click on confirmation Action popup 
	 * @param Response
	 */
	
	public void clickOnConfirmAction(String Response)
	{
		
		WebElement action = driver.findElement(ByLocator("//label[contains(text(),'lock study data?')]/ancestor::div[@class='modal-body']/following::div[1]/div[text()='"+Response+"']"));
		waitAndClick(action);
		reportInfo();
		
	}
	/**
	 * 
	 *  Verify Lock Data Grid view status
	 * @param EntryStatus
	 */
	public void verifyNewLockEntryStatus(String EntryStatus)
	{
		refreshPage();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = formatter.format(date);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String datatoBeVerified="//div[text()='"+strDate+"']/parent::div/div[text()='"+EntryStatus+"']/..";
		WebElement Status = driver.findElement(ByLocator(datatoBeVerified));
		moveToElement(Status);
		Assert.assertTrue(isElementDisplayed(Status));
	}
}
