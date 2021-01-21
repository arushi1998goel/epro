package net.medavante.portal.qa.pages.administration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.utilities.Constants;

public class AdministrationStudiesPage extends BasePage {

	@FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Add']")
	private WebElement addStudyBTN;

	@FindBy(xpath = "//div[@id='portal-grid-page-content']//div[@class='input-group']//input")
	private WebElement searchINP;

	@FindBy(xpath = "//span[@class='icon-small icon-delete']")
	private WebElement deleteBTN;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'General')]")
	private WebElement generalTAB;

	@FindBy(xpath = "//div[@data-model='model' and contains(@class,'edit')]")
	private WebElement generalTabEditMode;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']/li/a")
	private List<WebElement> studyTabLIST;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Languages')]")
	private WebElement languageTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Countries')]")
	private WebElement countriesTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Forms')]")
	private WebElement formsTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Visits')]")
	private WebElement visitsTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Custom')]")
	private WebElement customTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Analytics')]")
	private WebElement analyticsTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'People')]")
	private WebElement peopleTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Clinicians')]")
	private WebElement cliniciansTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Sites')]")
	private WebElement sitesTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Qualifications')]")
	private WebElement qualificationsTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Pre-qualifications')]")
	private WebElement preQualificationsTAB;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Survey')]")
	private WebElement surveyTAB;
	
	@FindBy(xpath = "//a[contains(text(),'Schedule')]")
	private WebElement scheduleTAB;
	
	@FindBy(xpath = "//a[contains(text(),'Applications')]")
	private WebElement applicationsTAB;
	
	@FindBy(xpath = "//a[contains(text(),'Identity')]")
	private WebElement identityTAB;
	
	@FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Add']")
	private WebElement addStudyButton;

	@FindBy(xpath = "//div[@class='scroll-wrapper']//div[@data-ng-click='selectItem(item)']//span[@class='item-name ng-binding']")
	private List<WebElement> studyList;

	@FindBy(xpath = "//*[@class='details-grid portal-grid row']/div/div[2]//div[@class='value col-xs-15']/label")
	private WebElement selectedStudyTXT;

	@FindBy(xpath = "//div[@class='details-grid portal-grid row']")
	private WebElement gridStudy;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.name']/div/div[2]/input")
	private WebElement studyNameINP;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.abbreviation']/div/div[2]/input")
	private WebElement studyAbbreviationINP;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.phase']/div/div[2]/input")
	private WebElement phaseINP;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.sponsor']//button[@data-toggle='dropdown']")
	private WebElement sponsorDRPDWN;

	@FindBy(xpath = "//ul[@class='dropdown-menu']/li/span")
	private List<WebElement> studyDRPDWNOptions;

	@FindBy(xpath = "//label[text()='Save']")
	private WebElement saveIcon;

	@FindBy(xpath = "//label[text()='Cancel']")
	private WebElement cancelIcon;

	@FindBy(xpath = "//input[contains(@ng-model,'SiteRating')]")
	private WebElement siteRatingCHKBOX;

	@FindBy(xpath = "//input[contains(@ng-model,'IndependentRating')]")
	private WebElement independentRatingCHKBOX;

	@FindBy(xpath = "//input[contains(@ng-model,'IndependentReview')]")
	private WebElement independentReviewCHKBOX;

	@FindBy(xpath = "//input[contains(@ng-model,'Analytics')]")
	private WebElement analyticsCHKBOX;

	@FindBy(xpath = "//div[@data-product-types='model.productTypes']")
	private WebElement productTypeSection;

	@FindBy(xpath = "//div[@data-product-types='model.productTypes']//span[contains(@class,'property-value')]")
	private WebElement savedProductTypeTEXT;

	@FindBy(xpath = "//div[contains(@class,'product-types')]/div[@class='nested-checkbox-group' or @class='checkbox-list-item value']//label")
	private List<WebElement> studyProductTypeLabelText;

	@FindBy(xpath = "//a[@class='home']")
	private WebElement homeICN;

	@FindBy(xpath = "//*[contains(@data-ng-repeat,'item in')]//span[@class='item-name ng-binding']")
	private List<WebElement> studyNameList;

	public AdministrationStudiesPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Function: Navigate to general tab of study.
	 * 
	 * @return AdministrationStudiesGeneralPage object
	 */
	public AdministrationStudiesGeneralPage navigateToStudyGeneralTab() {
		waitForElementClickable(generalTAB, 20);
		waitAndClick(generalTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesGeneralPage.class);
	}

	/**
	 * Function: Navigate to language tab of study.
	 * 
	 * @return AdministrationStudiesLanguagesPage object
	 */
	public AdministrationStudiesLanguagesPage navigateToStudyLanguageTab() {
		waitForElementClickable(languageTAB, 20);
		javascriptButtonClick(languageTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesLanguagesPage.class);

	}

	/**
	 * Function: Navigate to countries tab of study.
	 * 
	 * @return AdministrationStudiesCountriesPage object
	 */
	public AdministrationStudiesCountriesPage navigateToStudyCountriesTab() {
		waitAndClick(countriesTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesCountriesPage.class);

	}

	/**
	 * Function: Navigate to forms tab of study.
	 * 
	 * @return AdministrationStudiesFormsPage object
	 */
	public AdministrationStudiesFormsPage navigateToStudyFormsTab() {
		waitAndClick(formsTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesFormsPage.class);

	}

	/**
	 * Function: Navigate to visits tab of study.
	 * 
	 * @return AdministrationStudiesVisitsPage object
	 */
	public AdministrationStudiesVisitsPage navigateToStudyVisitsTab() {
		waitAndClick(visitsTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesVisitsPage.class);
	}

	/**
	 * Function: Navigate to custom tab of study.
	 * 
	 * @return AdministrationStudiesCustomPage object
	 */
	public AdministrationStudiesCustomPage navigateToStudyCustomTab() {
		waitAndClick(customTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesCustomPage.class);

	}

	/**
	 * Function: Navigate to Analytics tab of study.
	 * 
	 * @return AdministrationStudiesAnalyticsPage object
	 */
	public AdministrationStudiesAnalyticsPage navigateToStudyAnalyticsTab() {
		waitAndClick(analyticsTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesAnalyticsPage.class);

	}

	/**
	 * Function: Navigate to people tab of study.
	 * 
	 * @return AdministrationStudiesPeoplePage object
	 */
	public AdministrationStudiesPeoplePage navigateToStudyPeopleTab() {
		waitAndClick(peopleTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesPeoplePage.class);

	}

	/**
	 * Function: Navigate to Clinicians tab of study.
	 * 
	 * @return AdministrationStudiesCliniciansPage object
	 */
	public AdministrationStudiesCliniciansPage navigateToStudyCliniciansTab() {
		waitAndClick(cliniciansTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesCliniciansPage.class);

	}

	/**
	 * Function: Navigate to sites tab of study.
	 * 
	 * @return AdministrationStudiesSitesPage object
	 */
	public AdministrationStudiesSitesPage navigateToStudySitesTab() {
		waitAndClick(sitesTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesSitesPage.class);

	}

	/**
	 * Function: Navigate to Qualifications tab of study.
	 * 
	 * @return AdministrationStudiesQualificationsPage object
	 */
	public AdministrationStudiesQualificationsPage navigateToStudyQualificationsTab() {
		waitAndClick(qualificationsTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesQualificationsPage.class);

	}

	/**
	 * Function: Navigate to PreQualifications tab of study.
	 * 
	 * @return AdministrationStudiesPreQualificationsPage object
	 */
	public AdministrationStudiesPreQualificationsPage navigateToStudyPreQualificationsTab() {
		waitAndClick(preQualificationsTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesPreQualificationsPage.class);

	}

	/**
	 * Function: Navigate to Survey tab of study.
	 * 
	 * @return AdministrationStudiesSurveyPage object
	 */
	public AdministrationStudiesSurveyPage navigateToStudySurveyTab() {
		waitAndClick(surveyTAB);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesSurveyPage.class);
	}
	
	/**
	 * Function: Navigate to Schedule tab of study.
	 * 
	 * @return AdministrationStudiesSchedulePage object
	 */
	public AdministrationStudiesSchedulePage navigateToStudyScheduleTab() {
		waitAndClick(scheduleTAB);
		new WebDriverWait(driver, 25).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		return PageFactory.initElements(driver, AdministrationStudiesSchedulePage.class);
	}

	/**
	 * Function: Navigate to Application tab of study.
	 * 
	 * @return AdministrationStudiesApplicationPage object
	 */
	public AdministrationStudiesApplicationsPage navigateToStudyApplicationTab() {
		waitAndClick(applicationsTAB);
		new WebDriverWait(driver, 25).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		return PageFactory.initElements(driver, AdministrationStudiesApplicationsPage.class);
	}
	
	/**
	 * Function: Navigate to Identity tab of study.
	 * 
	 * @return AdministrationStudiesIdentityPage object
	 */
	public AdministrationStudiesIdentityPage navigateToStudyIdentityTab() {
		waitAndClick(identityTAB);
		_normalWait(2000);
		waitSpinnerToBecomeInvisible();
		return PageFactory.initElements(driver, AdministrationStudiesIdentityPage.class);
	}
	
	/** Verify Studies Page */

	public void verifyAdminstrationStudiesPageIsOpen() {
		Assert.assertTrue(isElementPresent(addStudyButton), "Studies Page is Open Sucessfully");
	}

	/** Search Study */

	public void searchAndClickOnStudy(String searchStudyName) {
		waitForElement(searchINP);
		inputText(searchINP, searchStudyName);
		for (WebElement studydisplay : studyList) {
			if (getText(studydisplay).contains(searchStudyName)) {
				waitForElementPresent(By.xpath("//div[@class='ng-isolate-scope' and @data-ng-show='ctx.isBusy']"), 10);
				javascriptButtonClick(
						studydisplay.findElement(By.xpath("//span[contains(text(),'" + searchStudyName + "')]")));
				waitSpinnerToBecomeInvisible();
				_normalWait(1000);
				break;
			}

		}

	}

	public void createStudy(String studyName, String studyAbbreviation, String studyPhase, String studySponsor) {
		waitForElementClickable(gridStudy, 10);
		waitAndClick(addStudyButton);

		inputText(studyNameINP, studyName);
		inputText(studyAbbreviationINP, studyAbbreviation);
		inputText(phaseINP, studyPhase);
		waitAndClick(sponsorDRPDWN);
		selectDropdownOption(studyDRPDWNOptions, studySponsor);
		clickOn(siteRatingCHKBOX);
		clickOn(independentRatingCHKBOX);
		clickOn(independentReviewCHKBOX);
		clickOn(analyticsCHKBOX);
		waitAndClick(saveIcon);
		waitForSpinner(3);
	}

	/**
	 * Verify general tab is displayed
	 * 
	 * @param studyNameAdmin
	 */
	public void verifyAdministrationStudiesGeneralPageIsOpen(String studyNameAdmin) {
		Assert.assertEquals(studyNameAdmin, selectedStudyTXT.getText());
		reportInfo();
	}

	/**
	 * Change the general mode to Edit mode
	 */
	public void editStudy() {
		clickOn(gridStudy);
	}

	/**
	 * Verify General tab is in edit mode
	 */
	public void verifyGeneralTabIsInEditMode() {
		Assert.assertTrue(generalTabEditMode.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify product type section is displayed
	 */
	public void verifyProductTypeSectionIsDisplayed() {
		Assert.assertTrue(productTypeSection.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Analytics setting is available on the General tab
	 */
	public void verifyAnalyticsSettingIsDisplayed() {
		Assert.assertTrue(analyticsCHKBOX.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Analytics checkbox is not selected
	 */
	public void verifyAnalyticsCheckBoxIsNotSelected() {
		Assert.assertFalse(analyticsCHKBOX.isSelected());
		reportInfo();
	}

	/**
	 * Verify Analytics checkbox is selected
	 */
	public void verifyAnalyticsCheckBoxIsSelected() {
		Assert.assertTrue(analyticsCHKBOX.isSelected());
		reportInfo();
	}

	/**
	 * Select Analytics checkbox
	 */
	public void selectAnalyticsCheckBox() {
		if (analyticsCHKBOX.isSelected() == false) {
			clickOn(analyticsCHKBOX);
		} else {
			logger.info("Analytics Checkbox is already selected");
		}
	}

	/**
	 * UnSelect Analytics checkbox
	 */
	public void unSelectAnalyticsCheckBox() {
		if (analyticsCHKBOX.isSelected() == true) {
			clickOn(analyticsCHKBOX);
		} else {
			logger.info("Analytics Checkbox is already un selected");
		}
	}

	/**
	 * Verify Save button is enabled
	 */
	public void verifySaveButtonIsEnabled() {
		Assert.assertTrue(saveIcon.isEnabled());
		reportInfo();
	}

	/**
	 * Verify cancel button is enabled
	 */
	public void verifyCancelButtonIsEnabled() {
		Assert.assertTrue(cancelIcon.isEnabled());
		reportInfo();
	}

	/**
	 * click on cancel button to discard the changes
	 */
	public void clickOnCancelBTN() {
		clickOn(cancelIcon);
	}

	/**
	 * click on save button to save the changes
	 */
	public void clickOnSaveBTN() {
		clickOn(saveIcon);
		new WebDriverWait(driver, 15).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='tab-content']/div[@class='ng-isolate-scope']")));
	}

	/**
	 * Verify product type is not configured
	 * 
	 * @param unSavedProductTypeToBeVerify
	 */
	public void verifyProductTypeNotConfigured(String unSavedProductTypeToBeVerify) {
		Assert.assertFalse(savedProductTypeTEXT.getText().contains(unSavedProductTypeToBeVerify));
		reportInfo();
	}

	/**
	 * Verify product type is configured
	 * 
	 * @param savedProductTypeToBeVerify
	 */
	public void verifyProductTypeConfigured(String savedProductTypeToBeVerify) {
		Assert.assertTrue(savedProductTypeTEXT.getText().contains(savedProductTypeToBeVerify));
		reportInfo();
	}

	/**
	 * Verify study tab is displayed
	 * 
	 * @param TabToBeVerified
	 */
	public void verifyTabIsDisplayed(String TabToBeVerified) {
		boolean flag = false;
		for (WebElement tabName : studyTabLIST) {
			if (tabName.getText().trim().toLowerCase().equals(TabToBeVerified.toLowerCase())) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select product type check box
	 * 
	 * @param studyProductType
	 */
	public void selectProductTypeCheckBox(String studyProductType) {
		for (WebElement productTypeText : studyProductTypeLabelText) {
			if (productTypeText.getText().equalsIgnoreCase(studyProductType)) {
				if (productTypeText.findElement(By.xpath("./preceding-sibling::input")).isSelected() == false) {
					clickOn(productTypeText.findElement(By.xpath("./preceding-sibling::input")));
					break;
				} else {
					logger.info(studyProductType + " Checkbox is already selected");
				}
			}
		}
	}

	/**
	 * Un Select product type check box
	 * 
	 * @param studyProductType
	 */
	public void unSelectProductTypeCheckBox(String studyProductType) {
		for (WebElement productTypeText : studyProductTypeLabelText) {
			if (productTypeText.getText().equalsIgnoreCase(studyProductType)) {
				if (productTypeText.findElement(By.xpath("./preceding-sibling::input")).isSelected() == true) {
					clickOn(productTypeText.findElement(By.xpath("./preceding-sibling::input")));
					break;
				} else {
					logger.info(studyProductType + " Checkbox is already not selected");
					reportInfo();
				}
			}
		}
	}

	/**
	 * Verify Product type is selected/Checked
	 * 
	 * @param studyProductType
	 */
	public void verifyProductTypeCheckBoxIsSelected(String studyProductType) {
		boolean flag = false;
		for (WebElement productTypeText : studyProductTypeLabelText) {
			if (productTypeText.getText().equalsIgnoreCase(studyProductType)) {
				flag = productTypeText.findElement(By.xpath("./preceding-sibling::input")).isSelected();
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Product type is not selected/UnChecked
	 * 
	 * @param studyProductType
	 */
	public void verifyProductTypeCheckBoxIsNotSelected(String studyProductType) {
		boolean flag = true;
		for (WebElement productTypeText : studyProductTypeLabelText) {
			if (productTypeText.getText().equalsIgnoreCase(studyProductType)) {
				flag = productTypeText.findElement(By.xpath("./preceding-sibling::input")).isSelected();
				break;
			}
		}
		Assert.assertFalse(flag);
		reportInfo();
	}

	/** Return Back to Home Page */
	public MedAvantePortalPage navigateToHomePage() {
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		clickOn(homeICN);
		return (PageFactory.initElements(driver, MedAvantePortalPage.class));
	}
	
	


	

	
}
