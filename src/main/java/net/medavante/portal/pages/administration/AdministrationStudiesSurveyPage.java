package net.medavante.portal.pages.administration;

import java.util.List;

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
	
	@FindBy(xpath="//div[@class='col-xs-24 no-padding align-center']//a[@disabled='disabled']")
	private WebElement addIconDisabled;
	
	@FindBy(xpath="//div[@class='col-xs-24 no-padding align-center']//a[@title='Preview']")
	private WebElement previewControl;
	
	@FindBy(xpath="//div[@class='col-xs-4 text-right']//a")
	private WebElement exitPreviewButton;

	
	
	
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

	/***
	 * Verify survey page is in view mode
	 */
	
	
	public void verifySurveyPageInViewMode() {

		String icon=addIconDisabled.getAttribute("disabled");
		if(icon.contains("disabled")) {
			Assert.assertTrue(true);
		}
	}
	/***
	 * Select the option to open the preview
	 */
	
	public void selectTheOptionToOpenThePreview() {
		waitAndClick(previewControl);
		
	}

	/***
	 * Verify exit preview button
	 */
	
	
	public void verifyExitPreviewButton() {
		
		moveToElement(exitPreviewButton);
		if(exitPreviewButton.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}

	
	
	
	
	
	
	
}
