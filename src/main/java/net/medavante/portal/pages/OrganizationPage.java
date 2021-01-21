package net.medavante.portal.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.medavante.portal.datamodel.OrganizationModel;
import net.medavante.portal.selenium.core.BasePage;

public class OrganizationPage extends BasePage {

	public OrganizationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Add']")
	private WebElement addOrganizationButton;

	@FindBy(xpath = "//div[@data-value='model.name']/div/div[2]/input")
	private WebElement orgNameTextBox;

	@FindBy(xpath = "//div[@class='scroll-wrapper']/div[contains(@class,'admin-grid-row ng')]//span[contains(@class,'item')]")
	private WebElement addedOrganization;

	@FindBy(xpath = "//div[@data-value='model.abbreviation']/div/div[2]/input")
	private WebElement orgAbbreviationTextBox;

	@FindBy(xpath = "//div[@data-value='model.ouTypeId']/div/div[2]")
	private WebElement orgTypeDropdown;

	@FindBy(xpath = "//ul[@class='dropdown-menu ng-scope']/li")
	private List<WebElement> orgDropdownOptions;

	@FindBy(xpath = "//div[@data-value='model.ouSubTypes']/div/div[2]")
	private WebElement orgSubtypeDropdown;

	@FindBy(xpath = "//div[@data-value='model.comments']/div/div[2]/textarea")
	private WebElement orgCommentsTextBox;

	@FindBy(xpath = "//span[@class='icon-small icon-save']")
	private WebElement saveIcon;

	@FindBy(name = "search")
	private WebElement searchTextbox;

	@FindBy(xpath = "//span[@class='icon-small icon-delete']")
	private WebElement deleteIcon;

	@FindBy(xpath = "//div[@class='btn btn-active' and text()='Yes']")
	private WebElement yesPopUpButton;

	@FindBy(xpath = "//div[@class='details-grid portal-grid row']")
	private WebElement gridOrganization;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Cancel' and disabled='disabled']")
	private String cancelIcon;

	@FindBy(xpath = "//span[@class='icon-small icon-menu']")
	private WebElement menu;

	@FindBy(xpath = "//a[@href='/Layout/LogOut']")
	private WebElement logout;

	/** ================== Organization Portal =============== */

	/**
	 * Function: Add new organization.
	 * 
	 * @param organizationModel
	 * @return
	 * @return
	 * @throws InterruptedException
	 */
	public <T> T addOrganization(final Class<T> className, OrganizationModel organizationModel)
			throws InterruptedException {
		waitForElementClickable(gridOrganization, 10);
		waitAndClick(addOrganizationButton);
		_normalWait(10000);
		inputText(orgNameTextBox, organizationModel.getOrgName());
		inputText(orgAbbreviationTextBox, organizationModel.getOrgAbbreviation());
		waitAndClick(orgTypeDropdown);
		selectDropdownOption(orgDropdownOptions, organizationModel.getOrgType());
		if (!StringUtils.isEmpty(organizationModel.getorgComments())
				|| !StringUtils.isBlank(organizationModel.getorgComments())) {
			inputText(orgCommentsTextBox, organizationModel.getorgComments());
		}
		waitAndClick(saveIcon);
		waitForSpinner(3);
		return PageFactory.initElements(driver, className);
	}

	/**
	 * Function: Delete organization.
	 * 
	 * @param organizationModel
	 * @return
	 * @throws InterruptedException
	 */
	public OrganizationPage deleteOrganization(OrganizationModel organizationModel) throws InterruptedException {
		waitForElementClickable(gridOrganization, 10);
		inputText(searchTextbox, organizationModel.getOrgName());
		_normalWait(3000);
		try {
		if (addedOrganization.isDisplayed()) {
			waitAndClick(addedOrganization);
			waitForElementClickable(deleteIcon, 5);
			_normalWait(2000);
			moveToElement(deleteIcon);
			waitAndClick(deleteIcon);
			waitAndClick(yesPopUpButton);
			waitUntillSpinnerToBecomeInvisible();
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return PageFactory.initElements(driver, OrganizationPage.class);
	}

	/**
	 * Function: Logout application
	 * 
	 * @return
	 */
	public LoginPage logoutApplication() {
		waitAndClick(menu);
		waitAndClick(logout);
		return PageFactory.initElements(driver, LoginPage.class);
	}
}
