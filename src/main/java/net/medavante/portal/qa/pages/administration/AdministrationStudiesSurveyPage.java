package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesSurveyPage extends BasePage {
	
	@FindBy(xpath="//div[@class='right-pane-wrapper right-content-scroll portal-grid details-grid col-xs-24']//span/label[contains(text(),'Add Scale')]")
	private WebElement scaleLabelSurvey;
	
	@FindBy(xpath="//div[@class='right-pane-wrapper right-content-scroll portal-grid details-grid col-xs-24']//span/label[contains(text(),'Preview')]")
	private WebElement previewLabelSurvey;
	
	@FindBy(xpath="//div[@class='col-xs-24 no-padding']//a[@title='Add Scale']")
	private WebElement addScaleICN;
	
	
	
	public AdministrationStudiesSurveyPage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationStudiesSurveyPage() {
		Assert.assertTrue(isElementPresent(scaleLabelSurvey) && isElementPresent(previewLabelSurvey));
	}
	
	public void surveyFilledForParentScale(String parentScaleName)
	{
	  clickOn(addScaleICN);
	  String scale="//label[@class='ng-binding' and text()='" + parentScaleName + "']/preceding-sibling::input";
	  waitAndClick(scale);
	
	}
	
	
	
}
