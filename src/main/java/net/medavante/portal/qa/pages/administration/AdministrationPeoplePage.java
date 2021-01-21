package net.medavante.portal.qa.pages.administration;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[contains(text(),'Qualifications')]")
	private WebElement qualificationsTAB;

	@FindBy(xpath = "//div[@class='details-grid search-block']//input[@name='search']")
	private WebElement peopleSearchInputTextBox;

	@FindBy(xpath = "(//div[@class='details-grid search-block']//span[@class='icon-small icon-search'])[1]")
	private WebElement searchPeopleIcon;

	@FindBy(xpath = "//div[@class='scroll-wrapper']/div[2]/span[@class='item-name ng-binding']")
	private WebElement clickOnSearchPeople;

	@FindBy(xpath = "//div[@class='scroll-wrapper']//div[@data-ng-click='selectItem(item)']//span[@class='item-name ng-binding']")
	private List<WebElement> personList;

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

		waitForElementClickable(qualificationsTAB, 5);
		javascriptButtonClick(qualificationsTAB);
		return PageFactory.initElements(driver, AdministrationPeopleQualificationsPage.class);
	}

	public void searchPerson(String searchPersonName) {
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
				&& isElementPresent(organizationsTAB) && isElementPresent(qualificationsTAB));
	}

}