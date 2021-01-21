package net.medavante.portal.qa.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesVisitsPage extends BasePage {

	@FindBy(xpath = "//a[@title='Add Visit']//span[@class='icon-small icon-add']")
	private WebElement addVisitBTN;

	@FindBy(xpath = "//div[@data-value='visit.name']//input")
	private WebElement visitNameINP;

	@FindBy(xpath = "//div[@data-value='visit.analyticalTypeId']")
	private WebElement analyticalTypeDRPDWN;

	@FindBy(xpath = "//div[@data-value='visit.analyticalTypeId']//ul//li//span")
	private List<WebElement> analyticalTypeDRPDWNOptn;

	@FindBy(xpath = "//div[@data-available-forms='::model.formsAvailable']//span[@class='icon-small icon-add']")
	private WebElement studyFormAddBTN;

	@FindBy(xpath = "//div[@data-available-forms='::model.formsAvailable']//div[@class='popup-scroll']//label")
	private List<WebElement> studyFormList;

	@FindBy(xpath = "//div[@data-available-forms='::model.formsAvailable']//button[@data-ng-disabled='onAddFormsDisabled()']")
	private WebElement addFormButton;

	@FindBy(xpath = "//div[contains(@class,'right-pane')]//span[@class='icon-save']")
	private WebElement saveVisitBTN;
	
	@FindBy(xpath="//div[@data-ng-show='isEditing()']/span")
	private List<WebElement> visitEditOption;
	
	@FindBy(xpath="//div[@data-text-id='Models.visits.subjectStatus']")
	private WebElement subjectStatusUnderVisitTab;
	
	@FindBy(xpath="(//div[@class='details-grid details portal-grid edit']//input)[1]")
    private WebElement screenedStatusSet;
	
	@FindBy(xpath="//div[@class='ordering-container']//div[@class='col-xs-3'][1]/label")
	private List<WebElement> visitListUnderStudies;

	@FindBy(xpath = "//a[@class='home']")
	private WebElement homeBTN;

	public AdministrationStudiesVisitsPage(WebDriver driver) {
		super(driver);
	}

	public void verifyVisitsPage() {
		Assert.assertTrue(isElementPresent(addVisitBTN));
		reportInfo();
	}

	public void addStudyVisit(String visitName, String analyticalType, String studyForm) {
		waitAndClick(addVisitBTN);
		waitSpinnerToBecomeInvisible();
		inputText(visitNameINP, visitName);
		waitAndClick(analyticalTypeDRPDWN);
		for (WebElement analyticalTypeOption : analyticalTypeDRPDWNOptn) {
			if (analyticalType.equalsIgnoreCase(analyticalTypeOption.getText().trim())) {
				clickOn(analyticalTypeOption);
				break;
			}
		}
		waitAndClick(studyFormAddBTN);
		for (WebElement studyFormOption : studyFormList) {
			if (studyFormOption.getText().trim().equalsIgnoreCase(studyForm)) {
				clickOn(studyFormOption.findElement(By.xpath(".//preceding-sibling::input")));
				moveToElement(addFormButton);
				clickOn(addFormButton);
				break;
			}
		}

		waitAndClick(saveVisitBTN);
		_normalWait(2000);
	}

	@FindBy(xpath = "//div[@data-value='visit.subjectStatusId']//div/label")
	private List<WebElement> visitSubjectStatusCheckBox;
	
	@FindBy(xpath="//div[@data-value='visit.scheduledTypeName']//button")
	private WebElement visitTypeDropDownBtn;
	
	@FindBy(xpath="//div[@data-value='visit.scheduledTypeName']//button/parent::div//ul[@class='dropdown-menu']/li/span")
	private List<WebElement> visitTypeList; 

	public void addMultiVisit(List<String> VisitName,String visitType,String visitSubjectStatus,String scaleToBeSelect) {
		for (String visitNameToBeSelect : VisitName) {
			waitAndClick(addVisitBTN);
			waitSpinnerToBecomeInvisible();
			inputText(visitNameINP, visitNameToBeSelect);
			waitAndClick(analyticalTypeDRPDWN);
			for (WebElement analyticalTypeOption : analyticalTypeDRPDWNOptn) {
				if ("Baseline".equalsIgnoreCase(analyticalTypeOption.getText().trim())) {
					clickOn(analyticalTypeOption);
					break;
				}
			}
			
			waitAndClick(visitTypeDropDownBtn);
			for(WebElement visitTypeText:visitTypeList){
				if(visitTypeText.getText().trim().equalsIgnoreCase(visitType)){
					waitAndClick(visitTypeText);
					break; 
				}
			}

			for (WebElement we : visitSubjectStatusCheckBox) {
				if (we.getText().trim().equalsIgnoreCase(visitSubjectStatus)
						&& (we.findElement(By.xpath(".//preceding-sibling::input")).isSelected() == false)) {
					clickOn(we.findElement(By.xpath(".//preceding-sibling::input")));
					break;
				}
			}

			waitAndClick(studyFormAddBTN);
			for (WebElement studyFormOption : studyFormList) {
				if (studyFormOption.getText().trim().equalsIgnoreCase(scaleToBeSelect)) {
					clickOn(studyFormOption.findElement(By.xpath(".//preceding-sibling::input")));
					moveToElement(addFormButton);
					clickOn(addFormButton);
					break;
				}
			}
			waitAndClick(saveVisitBTN);
			_normalWait(2000);
		}
	}


	/***verify visit Edit option is displaying or not.
	 * 
	 */

	public void verifyVisitEditSectionIsPresentOrNot(String optionToSelect) {
		boolean flag=false;
		for (WebElement editoption: visitEditOption) {
			if(editoption.getText().equalsIgnoreCase(optionToSelect)){
				flag=true;
				break;
			}	
		}
		Assert.assertTrue(flag);
		
	}
	
	/*******
	 * Subject statuses are displayed. Screened Subject status is set
	 */
	
	public void verifySubjectStatusesDisplayed() {
		boolean flag= false;
		if (isElementDisplayed(subjectStatusUnderVisitTab)) {
			flag=true;
		}
		Assert.assertTrue(flag);	
	}
	
	public void verifyScreenedSubjectStatusIsSet() {
		Assert.assertTrue(screenedStatusSet.isSelected());	
		moveToElement(screenedStatusSet);
	}
	
	/**
	 * Click on Visit to open
	 */
	public void selectVisitToEditVisitSetting(String visitToBeSelected) {	
		boolean flag=false;
		for(WebElement visit:visitListUnderStudies){
			if(visit.getText().equalsIgnoreCase(visitToBeSelected)){
				waitAndClick(visit.findElement(By.xpath("./ancestor::div[contains(@class,'row admin-grid-row ng-scope')]")));
				flag= true;
				break; 
			}
	}
	Assert.assertTrue(flag);	
	}
	
	
	/**
	 * Function: Navigate to home page
	 * 
	 * 
	 */
	public void navigateBackToDashBoard() {
		waitAndClick(homeBTN);
	}
}
