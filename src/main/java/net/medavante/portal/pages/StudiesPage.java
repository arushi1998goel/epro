package net.medavante.portal.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.medavante.portal.datamodel.StudyModel;
import net.medavante.portal.selenium.core.BasePage;

public class StudiesPage extends BasePage {

    public StudiesPage(WebDriver driver) {
        super(driver);
    }

    /** ============ Study portal ============================ */

    @FindBy(xpath = "//span[@class='ng-binding' and text()='Studies']")
    private WebElement studyPortal;

    @FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Add']")
    private WebElement addStudyButton;

    @FindBy(xpath = "//div[@class='details-grid portal-grid row']")
    private WebElement gridStudy;

    @FindBy(xpath = "//div[@data-text-id='Models.studies.name']/div/div[2]/input")
    private WebElement studyName;

    @FindBy(xpath = "//div[@data-text-id='Models.studies.abbreviation']/div/div[2]/input")
    private WebElement studyAbbreviation;

    @FindBy(xpath = "//div[@data-text-id='Models.studies.phase']/div/div[2]/input")
    private WebElement phase;

    @FindBy(xpath = "//div[@data-text-id='Models.studies.drugName']/div/div[2]/input")
    private WebElement drugName;

    @FindBy(xpath = "//div[@data-text-id='Models.studies.sponsor']//button[@data-toggle='dropdown']")
    private WebElement sponsorDropdown;

    @FindBy(xpath = "//span[@class='icon-small icon-save']")
    private WebElement saveIcon;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li")
    private List<WebElement> studyDropdownOptions;

    @FindBy(xpath = "//div[@class='collapsed-block open-grid']//div[@data-save='save()']//a[@class='btn circle-button btn-white' and @title='Save']")
    private WebElement savePeopleIcon;

    @FindBy(name = "search")
    private WebElement searchTextbox;

    @FindBy(xpath = "//span[@class='icon-small icon-delete']")
    private WebElement deleteIcon;

    @FindBy(xpath = "//div[@class='btn btn-active' and text()='Yes']")
    private WebElement yesPopUpButton;

    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li[2]/a")
    private WebElement languageSubmenu;

    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li[3]/a")
    private WebElement countrySubmenu;

    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li[9]/a")
    private WebElement sitesSubmenu;

    @FindBy(xpath = "//a[@data-ng-click='selectSubCategory(subCategory)' and text()='Qualifications']")
    private WebElement qualificationsSubmenu;

    @FindBy(xpath = "//a[@title='Edit']")
    private WebElement editIcon;

    @FindBy(xpath = "//a[@data-ng-click='getAvailableCountries()']/span")
    private WebElement addCountryButton;

    @FindBy(xpath = "//div[@data-value='country.targetSites']/div/div/input")
    private WebElement countryTragetValue;

    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li[9]/a")
    private WebElement sitesSubMenu;

    @FindBy(xpath = "//label[@class='ng-binding' and text()='Add Site']/preceding-sibling::a")
    private WebElement addSitesButton;

    @FindBy(xpath = "//div[@class='btn-group sites-tabs']/button[2]")
    private WebElement peopleSitesTab;

    @FindBy(xpath = "//div[@class='btn-group sites-tabs']/button[3]")
    private WebElement languagesSitesTab;

    @FindBy(xpath = "//button[@data-ng-click='changeSiteDetailsMode('closeout')']")
    private WebElement closeoutSitesTab;

    @FindBy(xpath = "//label[@class='ng-binding'and text()='Add People']/preceding-sibling::a")
    private WebElement addPeopleSitesSubMenuTab;

    @FindBy(xpath = "//label[@class='ng-binding'and text()='Add Facility']/preceding-sibling::a")
    private WebElement addFacilitySitesSubMenuIcon;

    @FindBy(xpath = "//div[@data-value='person.roleId']//button[@data-toggle='dropdown']")
    private WebElement systemRoleDropdown;

    @FindBy(xpath = "//div[@data-model='site']/div[2]/div/a")
    private WebElement dropdownArrow;

    @FindBy(xpath = "//a[@title='Add Language']")
    private WebElement addLanguageIcon;

    @FindBy(xpath = "//span[@class='icon-small icon-menu']")
    private WebElement menu;

    @FindBy(xpath = "//a[@href='/Layout/LogOut']")
    private WebElement logout;

    /** Funtion: Add new study in application
     * 
     * @param peopleModel
     * @return
     * @throws InterruptedException */
    public StudiesPage addNewStudy(StudyModel studyModel) throws InterruptedException {
        waitAndClick(studyPortal);
        waitForElementClickable(gridStudy, 10);
        waitAndClick(addStudyButton);
        inputText(studyName, studyModel.getStudyName());
        inputText(studyAbbreviation, studyModel.getAbbreviation());
        inputText(phase, studyModel.getPhase());
        if (!StringUtils.isEmpty(studyModel.getDrugName()) || !StringUtils.isBlank(studyModel.getDrugName())) {
            inputText(drugName, studyModel.getDrugName());
        }
        waitAndClick(sponsorDropdown);
        selectDropdownOption(studyDropdownOptions, studyModel.getSponsor());
        waitAndClick(saveIcon);
        waitForSpinner(3);
        return PageFactory.initElements(driver, StudiesPage.class);
    }

    /** Function: Search created study in side panel.
     * 
     * @param studyModel
     * @return
     * @throws InterruptedException */
    public StudiesPage searchStudy(StudyModel studyModel) throws InterruptedException {
        waitAndClick(studyPortal);
        waitForElementClickable(gridStudy, 10);
        inputText(searchTextbox, studyModel.getStudyName());
        return PageFactory.initElements(driver, StudiesPage.class);
    }

    /** Function: Select language in study.
     * 
     * @param languageName
     * @return
     * @throws InterruptedException */
    public StudiesPage selectLanguage(String languageName) throws InterruptedException {
        waitAndClick(languageSubmenu);
        waitForElement();
        waitAndClick(editIcon);
        String language = "//label[@class='ng-binding' and text()='" + languageName + "']/preceding-sibling::input";
        waitAndClick(language);
        waitAndClick(saveIcon);
        waitForSpinner(3);
        return PageFactory.initElements(driver, StudiesPage.class);
    }

    /** Function: Select country in study.
     * 
     * @param countryName
     * @param countryCode
     * @return
     * @throws InterruptedException */
    public StudiesPage selectCountry(String countryName, String countryCode) throws InterruptedException {
        waitAndClick(countrySubmenu);
        waitForElement();
        waitAndClick(addCountryButton);
        String country = "//label[@class='ng-binding' and text()='" + countryName + "']";
        waitAndClick(country);
        inputText(countryTragetValue, countryCode);
        waitAndClick(saveIcon);
        waitForSpinner(3);
        return PageFactory.initElements(driver, StudiesPage.class);
    }

    /** Function: Add site with facility, people and language.
     * 
     * @param site
     * @param facility
     * @param studyDropdown
     * @param language
     * @return
     * @throws InterruptedException */
    public StudiesPage enterSitesData(String site, String facility, String studyDropdown, String language)
            throws InterruptedException {
        waitAndClick(sitesSubmenu);
        waitForElement();
        waitAndClick(addSitesButton);
        String sites = "//label[@class='ng-binding' and text()='" + site + "']";
        waitAndClick(sites);
        waitAndClick(saveIcon);

        String siteLink = "//label[@class='orange ng-binding' and text()='" + site + "']";
        waitForElement();
        waitAndClick(siteLink);
        waitAndClick(addFacilitySitesSubMenuIcon);
        String facilities = "//label[@class='ng-binding' and text()='" + facility + "']";
        waitAndClick(facilities);

        waitForElement();
        waitAndClick(peopleSitesTab);
        siteLink = "//label[@class='orange ng-binding' and text()='" + site + "']";
        waitAndClick(siteLink);
        waitAndClick(addPeopleSitesSubMenuTab);
        String people = "//div[@data-ng-click='onAddStudyPeople(site.id, item)']/label";
        waitForElement();
        clickOn(people);
        waitAndClick(systemRoleDropdown);
        selectDropdownOption(studyDropdownOptions, studyDropdown);
        waitAndClick(savePeopleIcon);
        waitForElement();
        waitAndClick(languagesSitesTab);
        waitAndClick(dropdownArrow);
        String languageOption = "//div[@data-model='language']/div[2]/div//a[@class='a-color ng-binding' and text()='"
                + language + "']";
        waitAndClick(languageOption);
        waitAndClick(addLanguageIcon);
        waitForSpinner(3);
        return PageFactory.initElements(driver, StudiesPage.class);
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
