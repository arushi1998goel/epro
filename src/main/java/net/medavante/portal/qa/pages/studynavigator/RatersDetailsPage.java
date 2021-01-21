package net.medavante.portal.qa.pages.studynavigator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class RatersDetailsPage extends BasePage {

	public RatersDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='page-title']/h1[contains(text(),'Rater')]")
	private WebElement raterDetailsPageTitle;

	@FindBy(xpath = "//h2[contains(text(),'Details')]/parent::div/following-sibling::div[1]//label[@class='ng-binding']")
	private WebElement raterName;
	
	@FindBy(xpath = "//h2[contains(text(),'Detail')]")
	private WebElement detailTitle;
	
	@FindBy(xpath = "//h2[contains(text(),'Qualifications')]")
	private WebElement qualificationTitle;
	
	@FindBy(xpath = "//h2[contains(text(),'Assessments')]")
	private WebElement assessmentTitle;
	
	@FindBy(xpath = "//a[@title='Raters']")
	private WebElement ratersLinkICN;

	@FindBy(xpath = "//a[@title='Show queries']")
	private WebElement queriesLinkICN;
	
	@FindBy(xpath = "//a[@title='Raters']/span[2]")
	private WebElement ratersCount;
	
	@FindBy(xpath = "//div[contains(@class,'ng-isolate-scope opened')]//h1[contains(text(),'Raters')]")
	private WebElement ratersProfileSliderPanelOpen;
	

	// ======================Methods ===============================

	public void verifyRaterDetailPageDisplayed() {
		waitForElement(raterDetailsPageTitle);
		Assert.assertTrue(raterDetailsPageTitle.isDisplayed());
		reportInfo();
	}

	public void verifyRaterName(String RaterNameToBeVerified) {
		Assert.assertTrue(raterDetailsPageTitle.isDisplayed());
		Assert.assertEquals(raterName.getText().trim(), RaterNameToBeVerified);
		reportInfo();
	}
	
	/* Verify Raters Panel Displayed */
	public void verifyRatersDetailsPanelIsOpened() {
		Assert.assertTrue(isElementPresent(ratersProfileSliderPanelOpen), "Raters Details Slider panel is displayed");
		reportInfo();

	}

}
