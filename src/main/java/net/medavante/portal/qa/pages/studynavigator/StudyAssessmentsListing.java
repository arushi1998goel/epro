package net.medavante.portal.qa.pages.studynavigator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class StudyAssessmentsListing extends BasePage {

	/**
	 * ============ Assessment Listing Page Header Locators =====================
	 * 
	 */

	@FindBy(xpath = "//div[@class='col-xs-24']/div//div/label")
	private List<WebElement> assesmentListingHeaderTitles;

	@FindBy(xpath = "//a[@title='Study Profile']")
	private WebElement studyProfileICON;

	@FindBy(xpath = "//a[@title='Raters']")
	private WebElement ratersICON;

	@FindBy(xpath = "//a[@title='Show queries']")
	private WebElement showQueriesICON;

	@FindBy(xpath = "//a[@class='home']")
	private WebElement homeICON;

	@FindBy(xpath = "//a[@class='refresh-page-content circle-button btn btn-white']")
	private WebElement refreshPageContentICON;

	/**
	 * ============ Assessment Listing Page List Section =====================
	 */

	@FindBy(xpath = "//div[@class='content ng-scope']/div//div[@class=' list-text']")
	private List<WebElement> assessmentListAfterSorting;

	@FindBy(xpath = "//div[@class='row grid-row ng-scope']")
	private List<WebElement> recordRowLIST;

	public StudyAssessmentsListing(WebDriver driver) {
		super(driver);
	}

	/**
	 * @function: Select the given Assessment from the loaded and sorted list
	 * 
	 * 
	 */

	public void verifySelectionOfAssessmentFromList() {

		for (WebElement assessmentHeaderList : assesmentListingHeaderTitles) {
			if (assessmentHeaderList.getText().contains("Status")) {
				clickOn(assessmentHeaderList);
				break;
			}
		}
		for (int i = 0; i <= assessmentListAfterSorting.size() - 1; i++) {
			if (assessmentListAfterSorting.get(0).isEnabled()) {
				clickOn((assessmentListAfterSorting.get(0)));
				break;
			}
		}
	}

	public AssessmentsDetailsPage navigateToAssessmentsDetailPage() {

		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);

	}

	/**
	 * Click on row on the basis of column name
	 * 
	 * @param columnName
	 * @param rowValue
	 */
	public AssessmentsDetailsPage clickOnAssesmentBySubjectName(String columnName, String rowValue) {
		for (int columnNameIndex = 0; columnNameIndex < assesmentListingHeaderTitles.size(); columnNameIndex++) {
			if (getText(assesmentListingHeaderTitles.get(columnNameIndex)).trim().equalsIgnoreCase(columnName)) {
				int columnNameIndex1 = columnNameIndex + 1;
				for (int recordRow = 0; recordRow < recordRowLIST.size(); recordRow++) {
					if (getText(recordRowLIST.get(recordRow)
							.findElement(By.xpath("./div[" + columnNameIndex1 + "]//label"))).trim()
									.equalsIgnoreCase(rowValue)) {

						clickOn(recordRowLIST.get(recordRow)
								.findElement(By.xpath("./div[" + columnNameIndex1 + "]//label")));
						break;
					}
				}
				break;
			}
		}
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	public AssessmentsDetailsPage clickOnAssesmentByVisitAndSubjectName(String visitName, String subjectName) {
		WebElement visitSubjectRow = null;
		boolean flag = false;
		do {
			try {

				visitSubjectRow = driver
						.findElement(By.xpath("//div[@id='portal-grid-page-content']//div[div[7]//text() = '"
								+ visitName + "' and div[10]//text()='" + subjectName + "']"));
				scrollDown("100");
				moveToElement(visitSubjectRow);
				clickOn(visitSubjectRow);
				new WebDriverWait(driver, 15).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
				flag = true;
				break;
			} catch (Throwable e) {
				scrollDown("100");
				try {
					Thread.sleep(600);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		} while ((flag == false)
				&& driver.findElements(By.xpath("//div[@class='row text-center text-muted']")).size() > 0);
		Assert.assertTrue(flag, "Subject Should be present in list to click");
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

}
