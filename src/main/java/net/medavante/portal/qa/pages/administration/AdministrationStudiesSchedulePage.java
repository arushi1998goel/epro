package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesSchedulePage extends BasePage{

	@FindBy(xpath="//button[contains(text(),'Calendar')]")
	private WebElement calendarSchedule;
	
	@FindBy(xpath="//button[contains(text(),'Visit Templates')]")
	private WebElement visitTemplateSchedule;
	
	@FindBy(xpath="//button[contains(text(),'Questionnaire Templates')]")
	private WebElement questionnaireTemplatesSchedule;
	
	@FindBy(xpath="//span[@class='icon-edit']")
	private WebElement editIconSchedule;
	
	
	public AdministrationStudiesSchedulePage(WebDriver driver) {
		super(driver);
	}

	/*
	 * @function: Verification of Schedule Page
	 *
	 */
	public void verifyAdministrationStudiesSchedulePage() {
		Assert.assertTrue(isElementPresent(calendarSchedule) && isElementPresent(visitTemplateSchedule));
		Assert.assertTrue(isElementPresent(questionnaireTemplatesSchedule) && isElementPresent(editIconSchedule));
	}
}
