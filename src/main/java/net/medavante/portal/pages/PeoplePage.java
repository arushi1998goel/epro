package net.medavante.portal.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.medavante.portal.datamodel.PeopleModel;
import net.medavante.portal.selenium.core.BasePage;

public class PeoplePage extends BasePage {

    public PeoplePage(WebDriver driver) {
        super(driver);
    }

    /** ============ People portal ============================ */

    @FindBy(xpath = "//span[@class='ng-binding' and text()='People']")
    private WebElement peoplePortal;

    @FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Add']")
    private WebElement addPeopleButton;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameTextbox;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameTextbox;

    @FindBy(xpath = "//span[@class='ng-binding' and text()=' MBBS']/preceding-sibling::input")
    private WebElement degreeMBBSRadioButton;

    @FindBy(xpath = "//div[@title='Prefix']/button[@data-toggle='dropdown']")
    private WebElement prefixDropdown;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/span")
    private List<WebElement> peopleDropdownOptions;

    @FindBy(xpath = "//div[@title='User ID']/div/div/input")
    private WebElement userIdTextbox;

    @FindBy(xpath = "//div[@data-text-id='Models.people.comment']/div/div[2]/textarea")
    private WebElement peopleCommentTextBox;

    @FindBy(xpath = "//div[@class='details-grid portal-grid row']")
    private WebElement gridPeople;

    @FindBy(xpath = "//span[@class='icon-small icon-save']")
    private WebElement saveIcon;

    @FindBy(name = "search")
    private WebElement searchTextbox;

    @FindBy(xpath = "//span[@class='icon-small icon-delete']")
    private WebElement deleteIcon;

    @FindBy(xpath = "//div[@class='btn btn-active' and text()='Yes']")
    private WebElement yesPopUpButton;

    @FindBy(xpath = "//span[@class='icon-small icon-menu']")
    private WebElement menu;

    @FindBy(xpath = "//a[@href='/Layout/LogOut']")
    private WebElement logout;

    /** Funtion: Add new People in application
     * 
     * @param peopleModel
     * @return
     * @throws InterruptedException */
    public PeoplePage addPeople(PeopleModel peopleModel) throws InterruptedException {
        waitAndClick(peoplePortal);
        waitForElementClickable(gridPeople, 10);
        waitAndClick(addPeopleButton);
        inputText(firstNameTextbox, peopleModel.getFirstName());
        inputText(lastNameTextbox, peopleModel.getLastName());

        if (!StringUtils.isEmpty(peopleModel.getUseid()) || !StringUtils.isBlank(peopleModel.getUseid())) {
            inputText(userIdTextbox, peopleModel.getUseid());
        }

        if (!StringUtils.isEmpty(peopleModel.getDegree()) || !StringUtils.isBlank(peopleModel.getDegree())) {
            waitAndClick(degreeMBBSRadioButton);
        }

        if (!StringUtils.isEmpty(peopleModel.getNameAttributePrefix()) || !StringUtils.isBlank(peopleModel
                .getNameAttributePrefix())) {
            waitAndClick(prefixDropdown);
            selectDropdownOption(peopleDropdownOptions, peopleModel.getNameAttributePrefix());
        }
        if (!StringUtils.isEmpty(peopleModel.getComments()) || !StringUtils.isBlank(peopleModel.getComments())) {
            waitAndClick(peopleCommentTextBox);
            inputText(peopleCommentTextBox, peopleModel.getComments());
        }

        waitAndClick(saveIcon);
        waitForSpinner(3);
        return PageFactory.initElements(driver, PeoplePage.class);
    }

    /** Funtion: Delete people.
     * 
     * @param peopleModel
     * @return
     * @throws InterruptedException */
    public PeoplePage deletePeople(PeopleModel peopleModel) throws InterruptedException {
        inputText(searchTextbox, peopleModel.getFirstName() + " " + peopleModel.getLastName());
        waitForElementClickable(deleteIcon, 5);
        waitAndClick(deleteIcon);
        waitAndClick(yesPopUpButton);
        waitForSpinner(3);
        return PageFactory.initElements(driver, PeoplePage.class);
    }

    /** Function: Logout application
     * 
     * @return */
    public LoginPage logoutApplication() {
        waitAndClick(menu);
        waitAndClick(logout);
        return PageFactory.initElements(driver, LoginPage.class);
    }
}
