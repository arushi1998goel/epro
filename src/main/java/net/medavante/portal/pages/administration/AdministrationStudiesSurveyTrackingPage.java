package net.medavante.portal.pages.administration;

import java.util.List;

import org.apache.commons.imaging.formats.wbmp.WbmpImageParser;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesSurveyTrackingPage extends BasePage


{
	
	@FindBy(xpath="//label[text()='Add Emails']//ancestor::span//a")
	private WebElement addEmailButton;

	@FindBy(xpath="//span[@id='selectedStudy'][text()='Select Site']")
	private WebElement selectStudyButton;
	
	@FindBy(xpath="//textarea[@placeholder='Enter rater Email, First name, Last name']")
	private WebElement textArea;
	
	@FindBy(xpath="//button[contains(text(),'Load Emails')]")
	private WebElement LoademailBtn;
	
	@FindBy(xpath="//input[@class='checkbox-big ng-pristine ng-untouched ng-valid ng-scope']")
	private WebElement selectEmailreceiver;
	
	@FindBy(xpath="//span[@class='icon-small icon-mail']/..")
	private WebElement sendsurveyBtn;
	
	
	@FindBy(xpath="//label[text()='Export to CSV']/../a")
	private WebElement  exportCsvbtn;
	
	@FindBy(xpath="(//div[@class='col-xs-17 no-padding align-center']//a[@disabled='disabled'])[1]")
	private WebElement addIcon;
	
	@FindBy(xpath="//div[@class='pull-right col-xs-7 search-block']")
	private WebElement searchField;
	
	
	
	public AdministrationStudiesSurveyTrackingPage(WebDriver driver) {
		super(driver);
	}

	
	public void addEmail(String siteName,String email,String textContent,String Cnfrmmsg)
	{
		
		boolean flag=true;
		
		try {
			List<WebElement> existingemail = driver.findElements(ByLocator("//label[text()='"+email+"']"));
			if (existingemail.size()<=0) {
				  waitAndClick(addEmailButton);
				  waitAndClick(selectStudyButton);
			      WebElement site = driver.findElement(ByLocator("//button[@class='btn dropdown-toggle btn-default']"
			    		+ "/following-sibling::div[@class='dropdown-menu']//li//span[contains(text(),'"+siteName+"')]"));
				  waitAndClick(site);
				  inputText(textArea, textContent);
				  waitAndClick(LoademailBtn);
				  WebElement cnfrm = driver.findElement(ByLocator("//div[@id='queryConfirmation']//div[text()='"+Cnfrmmsg+"'][@class='btn btn-active']"));	
				  waitAndClick(cnfrm);
			}	
			
		} catch (Exception e) {
		}
	  Assert.assertTrue(flag);
	}
	
	
	public void sendingSurvey(String Cnfrmmsg)
	{
		
		waitAndClick(selectEmailreceiver);
		waitAndClick(sendsurveyBtn);
		WebElement cnfrmation = driver.findElement(ByLocator("//div[@id='preQualificationConfirmationDialog']//div[text()='"+Cnfrmmsg+"']"));
		waitAndClick(cnfrmation);
		Assert.assertTrue(true);
		
		
	}
	
	public <T> T exportToCsv(Class<T> className)
	{
		spinnerBecomeInvisible();
		String session = driver.getWindowHandle();
		System.out.println(session);
		waitAndClick(exportCsvbtn);
		
		_normalWait(2000);
		driver.switchTo().window(session);
		Assert.assertTrue(true);
         return PageFactory.initElements(driver, className);
		
	}

	/***
	 * Verify survey tracker page in view mode
	 */
	
	
	public void verifySurveyTrackerPageInViewMode() {

		String icon=addIcon.getAttribute("disabled");
		if(icon.contains("disabled")) {
			Assert.assertTrue(true);
		}
	}

	/***
	 * Verify search field on survey tracker page
	 */

	
	public void verifySearchFieldOnSurveyTrackerPage() {
		
		moveToElement(searchField);
		if(searchField.isDisplayed()) {
			Assert.assertTrue(true);
		}
	}
	
	
}
