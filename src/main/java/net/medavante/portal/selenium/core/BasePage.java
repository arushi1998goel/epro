/*
  Class to initialize all page methods for the actions available
  under that page. All scripts must call the respective methods from the respective
  pages to achieve any action.

  @author 360Logica
 * @since 1.0
 *
 * @version 1.0
 */
package net.medavante.portal.selenium.core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import com.itextpdf.text.DocumentException;
import com.relevantcodes.extentreports.ExtentTest;

import net.medavante.portal.enums.TagName;
import net.medavante.portal.pages.RaterProfilePage;
import net.medavante.portal.pages.qualificationlibrary.QualificationLibraryPage;
import net.medavante.portal.report.MobileScreenRecorder;
import net.medavante.portal.utilities.DateCalendar;
import net.medavante.portal.utilities.Utilities;

public abstract class BasePage {

	protected static final int DEFAULT_WAIT_4_ELEMENT = 15;
	protected static final int DEFAULT_WAIT_4_PAGE = 10;



	protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
	protected static WebDriverWait ajaxWait;
	protected static WebDriver driver;
	protected String title;
	protected long timeout = 5;
	protected Robot robot;

	static String resultPath;
	public static ExtentTest test;
	public static BaseTest baseTest;
	protected DateCalendar dateCalendarRef = new DateCalendar();

	/** @Inject @Named("framework.implicitTimeout") protected long timeout; */

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public static String generateRandomString(int lettersNum) {
		StringBuilder finalString = new StringBuilder();

		int numberOfLetters = 25;
		long randomNumber;
		for (int i = 0; i < lettersNum; i++) {
			char letter = 97;
			randomNumber = Math.round(Math.random() * numberOfLetters);
			letter += randomNumber;
			finalString.append(String.valueOf(letter));
		}
		return finalString.toString();
	}

	/**
	 * Functin: Get random integer
	 * 
	 * @param aStart
	 * @param aEnd
	 * @return
	 */

	public int getRandomInteger(final long aStart, final long aEnd) {
		final Random aRandom = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		final long range = aEnd - aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		final long fraction = (long) (range * aRandom.nextDouble());
		final int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}

	/** Click action performed and then wait */

	public void waitAndClick(WebElement element) {
		// logger.info("Wait and Click");
		_normalWait(3000);
		waitForElement(element);
		moveToElement(element);
		element.click();
	}

	/**
	 * Move to Particular Element
	 * 
	 * @param element
	 */
	public void moveToElement(WebElement element) {
		Actions actn = new Actions(driver);
		actn.moveToElement(element).build().perform();
		javascripctHilightingElement(element);
		reportInfo();
		unhighLightElement();
	}

	public void clickOn(WebElement element) {
		// logger.info("Click");
		moveToElement(element);
		element.click();
	}

	private WebElement lastElem = null;

	public void normalClick(WebElement we) {
		we.click();
	}

	public static String exceutionOn = "";

	public static void reportInfo() {
		if (BaseTest.reportSwitch.equals("0") || BaseTest.reportSwitch.equals("2")
				&& !BaseTest.exceutionOn.equals("Mobile") && !BaseTest.exceutionOn.equals("MobileAndWebExecution")) {
			try {
				BaseTest.addPdfRow(System.getProperty("className"), System.getProperty("testcaseName"),
						BaseTest.getMessage(), BaseTest.captureScreenshots());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (BaseTest.reportSwitch.equals("1") && BaseTest.captureScreenshotSwitch.equalsIgnoreCase("true")) {
			try {
				BaseTest.captureScreenshots();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (BaseTest.exceutionOn.equals("Mobile") || BaseTest.exceutionOn.equals("MobileAndWebExecution")) {
			// MobileScreenRecorder.captureScreenBaseTestExtent();
			MobileScreenRecorder.captureScreenCasts();
			try {
				BaseTest.pdfMobileScreenShotWriter();
			} catch (DocumentException | IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * F:\NewProjectLatest\initialcode\src\test\resources\testdata\TestPDF.pdf
	 * Function: Highlight the WebElement and capture screenshot of the event
	 * 
	 * @param WebElement
	 * 
	 */

	public void javascripctHilightingElement(WebElement webElement) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 3px solid red;')", webElement);
		lastElem = webElement;
	}

	/**
	 * Function: UnHighlight the WebElement and capture screenshot of the event
	 * 
	 * @param WebElement
	 * 
	 */

	public void unhighLightElement() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 0px;');", lastElem);

	}

	/** Click on WebElement by using java script */
	public void javascriptButtonClick(WebElement webElement) {
		javascripctHilightingElement(webElement);
		reportInfo();
		unhighLightElement();
		waitForElement(webElement);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", webElement);
	}

	/** Click on element by string locator */
	public void waitAndClick(String locator) {
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :" + locator + " Not found");
		WebElement el = getDriver().findElement(ByLocator(locator));
		el.click();
	}

	/** Click on element by string locator */
	public void clickOn(String locator) {
		WebElement el = getDriver().findElement(ByLocator(locator));
		moveToElement(el);
		el.click();
	}

	public String returnTitle() {
		return title;
	}

	/** Scroll page down 250 pixel */
	public void scrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}

	/**
	 * Scroll page down pixel
	 *
	 * @param pixel pixel to scroll down
	 */
	public void scrollDown(String pixel) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + pixel + ")", "");
	}

	public void scrollToDocumentHeight() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToTopOfThePage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0 )");
	}

	/** Scroll page up 250 pixel */
	public void scrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(250, 0)", "");
	}

	/**
	 * Scroll page up pixel
	 *
	 * @param pixel pixel to scroll down
	 */
	public void scrollUp(String pixel) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + pixel + ", 0)", "");
	}

	/** Setting up implicit wait that will be used internally */
	private void setImplicitWait(int timeInSec) {
		logger.info("setImplicitWait, timeInSec={}", timeInSec);
		driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
	}

	/** Reset implicit wait */
	private void resetImplicitWait() {
		logger.info("resetImplicitWait");
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	/** Wait for element */
	public void waitFor(ExpectedCondition<Boolean> expectedCondition) {
		setImplicitWait(0);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(expectedCondition);
		resetImplicitWait();
	}

	/** Input text as string */
	public void inputText(WebElement element, String text) {
		// logger.info("inputText, text={}", text);
		waitForWebElementEnable(element, 10);
		element.clear();
		// waitForElement(element);
		element.sendKeys(text);
		// waitForElement(element);
	}

	/**
	 * Input the string in text box
	 * 
	 * @param element
	 * @param text
	 */
	public void inputText(String element, String text) {
		WebElement element1 = _getWebElementOnExistence(element);
		inputText(element1, text);
	}

	/**
	 * Pass the element to string and it will return the instance of WebElement
	 * 
	 * @param element
	 * @return
	 */
	private WebElement _getWebElementOnExistence(String element) {
		WebElement element1 = driver.findElement(ByLocator(element));
		return element1;
	}

	/** Wait for element to be present */
	public void waitForElement(WebElement element) {
		// logger.info("waitForElement");
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/** Wait for element by passing argument as string. */
	public void waitForElementToVisible(String locator, long timeOut) {
		// logger.info("waitForElement");
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ByLocator(locator)));
	}

	/** normal wait for thread. */
	public void _normalWait(long timeOut) {
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** Wait for JSLoad to load */
	public boolean _waitForJStoLoad() {
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = driver -> {
			try {
				return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
			} catch (Exception e) {
				return true;
			}
		};

		/** wait for JavaScript to load */
		ExpectedCondition<Boolean> jsLoad = driver -> {
			Object rsltJs = ((JavascriptExecutor) driver).executeScript("return document.readyState");
			if (rsltJs == null) {
				rsltJs = "";
			}
			return rsltJs.toString().equals("complete") || rsltJs.toString().equals("loaded");
		};

		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	/** Handle locator type */
	public By ByLocator(String locator) {
		By result;
		if (locator.startsWith("//") || locator.startsWith("(//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("#")) {
			result = By.id(locator.replace("#", ""));
		} else if (locator.startsWith("name=")) {
			result = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		} else {
			result = By.className(locator);
		}
		return result;
	}

	/** Verify the URL */
	public boolean verifyURL(String url) {
		boolean value = false;
		String currentUrl = driver.getCurrentUrl();
		return currentUrl.contains(url) || value;
	}

	/**
	 * Return current URL
	 * 
	 * @return
	 */
	public String getURL() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	/** Return driver instance */
	public WebDriver getDriver() {
		return driver;
	}

	/** Find webelement */
	public WebElement findElement(By by) {
		WebElement foundElement;

		if (driver instanceof ChromeDriver || driver instanceof InternetExplorerDriver) {
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (int millis = 0; millis < 3000; millis = millis + 200) {
			try {
				foundElement = driver.findElement(by);
				return foundElement;
			} catch (Exception e) {
				// Utils.hardWaitMilliSeconds(200);
			}
		}
		return null;
	}

	/** Use assert by page title */
	public void assertByPageTitle() {
		try {
			if (driver instanceof ChromeDriver || driver instanceof InternetExplorerDriver
					|| driver instanceof FirefoxDriver) {
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(returnTitle().equals(driver.getTitle()));
	}

	/** Find all links on current page */
	public List<String> findAllLinksOnPage() {
		List<String> links = new ArrayList<>();
		List<WebElement> linkElements = driver.findElements(By.tagName("a"));
		for (WebElement each : linkElements) {
			String link = each.getAttribute("href");
			if (link == null || link.contains("mailto") || link.contains("javascript")) {
				continue;
			}
			links.add(link);
		}
		return links;
	}

	/** Check the response of link */
	public boolean isResponseForLinkTwoHundredOrThreeOTwo(String link) {
		int code;
		Reporter.log("Link: " + link);

		try {

			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			code = connection.getResponseCode();
			Reporter.log("Code: " + code);
		} catch (Exception e) {
			Reporter.log(e.toString());
			return false;
		}
		return link.contains("pager") || code == 403 || code == 200 || code == 302;
	}

	/** Set wait for driver */
	public void setWaitTime(WebDriver driver, int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

	public void setWaitTimeToZero(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/** Condition to customize */
	public void customizableCondition(WebDriver driver, int waitTime, final Boolean condition) {
		// setWaitTimeToZero(driver);
		new WebDriverWait(driver, waitTime).until((ExpectedCondition<Boolean>) driver1 -> condition);
		// setWaitTime(driver, DEFAULT_WAIT_4_ELEMENT);
	}

	/** Wait for element to be clickable */

	public WebElement waitForElementClickable(WebElement webElement, int timeOutInSeconds) {
		WebElement element;
		try {
			// setWaitTimeToZero(driver);
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(webElement));

			// setWaitTime(driver, DEFAULT_WAIT_4_ELEMENT);
			return element;

		} catch (Exception e) {
		}
		return null;
	}

	/** Wait for element to be present */
	public WebElement waitForElementPresent(final By by, int timeOutInSeconds) {
		WebElement element;
		try {
			// setWaitTimeToZero(driver);
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

			// setWaitTime(driver, DEFAULT_WAIT_4_ELEMENT);
			return element;
		} catch (Exception e) {
		}
		return null;
	}

	/** Wait for element to be present by web element */
	public WebElement waitForElementPresent(WebElement webElement, int timeOutInSeconds) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.visibilityOf(webElement));
			return element;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * @param webElement
	 * @param text
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForTextPresentInElement(WebElement webElement, String text, int timeOutInSeconds) {
		boolean notVisible;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		notVisible = wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));

		return notVisible;
	}

	public boolean waitForTextPresentInElement(By by, String text, int timeOutInSeconds) {
		boolean notVisible;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		notVisible = wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));

		return notVisible;
	}

	/** Verify that element is present */
	public Boolean isElementPresent(WebElement element) {
		waitForElement(element);
		return element.isDisplayed();
	}

	/** Verify that element is not Visible */

	public Boolean isElementNotVisible(WebElement element) {
		Boolean result = false;
		try {
			if (!(element.isDisplayed()))
				;
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed())
				flag = true;
		} catch (Exception e) {
		}
		return flag;
	}

	/** Verify that element is present on web page */
	public Boolean isElementPresent(String locator) {
		Boolean result = false;
		try {
			getDriver().findElement(ByLocator(locator));
			result = true;
		} catch (Exception ex) {
		}
		return result;
	}

	/**
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public boolean WaitForElementNotPresent(WebElement locator) {
		boolean flag = false;
		int timeOut = 10;
		for (int i = 0; i < timeOut; i++) {
			if (!isElementPresent(locator)) {
				flag = true;
				break;

			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * @param locator
	 * @param timeout
	 */
	public void WaitForElementPresent(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param container
	 * @param element
	 * @return
	 */
	public int findNumberOfSpecificElementsInContainer(By container, By element) {
		WebElement mainDiv = driver.findElement(container);
		List<WebElement> divs = mainDiv.findElements(element);
		return divs.size();
	}

	/**
	 * @param List of WebElements
	 * @return size of list
	 * 
	 */
	public int sizeofTheList(List<WebElement> elementList) {
		int listSize = elementList.size();
		return listSize;
	}

	/**
	 * @param toBeHovered
	 * @param toBeClicked
	 * @return
	 */
	public WebDriver hoverOverElementAndClick(WebElement toBeHovered, WebElement toBeClicked) {
		Actions builder = new Actions(driver);
		builder.moveToElement(toBeHovered).build().perform();
		waitForElementPresent(toBeClicked, DEFAULT_WAIT_4_ELEMENT);
		toBeClicked.click();
		waitForPageLoaded();
		return driver;
	}

	/**
	 * Select element by visible text
	 *
	 * @param targetValue: visible text
	 */
	public void selectDropDownByText(WebElement element, String targetValue) {
		waitForElement(element);
		new Select(element).selectByVisibleText(targetValue);
	}

	/** Select element by Index */
	public void selectDropDownByIndex(WebElement element, int index) {
		waitForElement(element);
		new Select(element).selectByIndex(index);
	}

	/**
	 * Select element by value
	 *
	 * @param targetValue: value
	 */
	public void selectDropDownByValue(WebElement element, String targetValue) {
		// waitForElement(element);
		new Select(element).selectByValue(targetValue);
	}

	/**
	 * @param by
	 * @param driver
	 */
	public void waitForElementToBecomeVisible(By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_4_PAGE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * @param by
	 */
	public void waitForElementToBecomeInvisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 *
	 */
	public void waitForAjaxRequestsToComplete() {
		(new WebDriverWait(driver, DEFAULT_WAIT_4_PAGE)).until((ExpectedCondition<Boolean>) d -> {
			JavascriptExecutor js = (JavascriptExecutor) d;
			return (Boolean) js.executeScript("return jQuery.active == 0");
		});
	}
	
	/**
	 * wait for the angular to finish the processing
	 * @author siddharth
	 * @date 30/12/2019
	 * @return
	 */
	public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
            }
        };
    }

	/**
	 * @param driver
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete");
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait.until(expectation);
	}

	/**
	 * @param by
	 * @return
	 */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/** Verify that text is present on the page. */
	public boolean isTextPresentOnPage(String text) {
		return driver.findElement(By.tagName("body")).getText().contains(text);
	}

	/**
	 * Verify selected element is mandatory
	 * 
	 * @param element
	 */
	public void verifyInputIsMandatory(WebElement element) {

		Assert.assertEquals(element.getCssValue("color"), "#cc472c");
		Assert.assertEquals(element.getCssValue("background-color"), "rgba(255, 217, 217, 1)");

	}

	/**
	 * @param webElement
	 * @return
	 * @throws Exception
	 */
	public boolean isFileAvailableForDownload(WebElement webElement) throws Exception {
		int code;
		String downloadUrl = webElement.getAttribute("href");
		URL url = new URL(downloadUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		code = connection.getResponseCode();
		Reporter.log("The response code for download is " + code);
		return code == 200;
	}

	/** Store text from a locator */
	public String getText(WebElement element) {
		Assert.assertTrue(element.isDisplayed(), "Element Locator :" + element + " Not found");
		return element.getText();
	}

	public void takeRemoteWebDriverScreenShot(String fileName) {
		File screenshot = ((TakesScreenshot) new Augmenter().augment(driver)).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(fileName));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void waitForTextNotToBeVisible(String text, int timeoutInSeconds) {
		int startWait = 0;
		while (isTextPresentOnPage(text)) {
			startWait++;
			if (startWait == timeoutInSeconds) {
				throw new TimeoutException();
			}
		}
	}

	/**
	 * @param element
	 * @return visibility of the element
	 */
	public void waitForWebElementPresent(WebElement element) {
		WebDriverWait ajaxWait = new WebDriverWait(driver, 30);
		ajaxWait.until(ExpectedConditions.visibilityOf(element));

	}

	/** Perform Drag and drop */
	public void dragAndDrop(WebElement drag, WebElement drop) {
		Actions builder = new Actions(driver);
		builder.clickAndHold(drag).moveToElement(drop).pause(500).release(drop).build().perform();
	}
	
	/** Perform List of drag and drop operation*/
	public void listOfdragAndDrop(List<WebElement> drag, WebElement drop) {
		Actions builder = new Actions(driver);
		builder.clickAndHold((WebElement) drag).moveToElement(drop).pause(500).release(drop).build().perform();
	}
	

	/**
	 * 
	 */

	/** Switch to next tab */
	public void switchToTab() {

		// Switching between tabs using CTRL + tab keys.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		// Switch to current selected tab's content.
		driver.switchTo().defaultContent();
	}

	public void scrollPageThroughWebElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		_normalWait(1000);
	}

	public void scrollIntoView(String locator) {
		scrollIntoView(By.xpath(locator));
	}

	/* Scroll Into View By Element Or Xpath */
	public void scrollIntoView(Object byOrElement) {

		WebElement element;
		By by;
		if (byOrElement instanceof WebElement) {
			element = (WebElement) byOrElement;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new Object[] { element });
		} else if (byOrElement instanceof By) {
			by = (By) byOrElement;
			scrollIntoView(by);
		}

	}

	public void scrollIntoView(By by) {
		WebElement elem = getDriver().findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new Object[] { elem });
	}

	/**
	 * To load all the records by scrolling down the page
	 * 
	 * @param WebElement
	 */
	public void loadCompleteList(WebElement totalPages) {
		_normalWait(3000);
		if (totalPages.isDisplayed()) {
			String[] totalPagesCount = totalPages.getText().split("\\ /");
			int intialPagePos = 1;
			while (intialPagePos <= Integer.valueOf(totalPagesCount[1].replaceAll(" ", ""))) {
				scrollToDocumentHeight();
				_normalWait(3000);
				intialPagePos++;
			}
		}
	}

	/** Capturing screenshot once script is failed */
	public void captureScreenshotOfPages(String result) {
		try {
			String screenshotName = Utilities.getFileName(result);
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = Utilities.getPath();
			String screen = path + "/test-output/screenshots/" + screenshotName + ".png";
			File screenshotLocation = new File(screen);
			FileUtils.copyFile(screenshot, screenshotLocation);
			Thread.sleep(2000);
			Reporter.log(
					"<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Funtion: Select option from dropdown
	 * 
	 * @param options
	 * @param givenOption
	 */
	public void selectDropdownOption(List<WebElement> options, String givenOption) {

		for (WebElement e : options) {
			_normalWait(200);
			if (e.getText().equalsIgnoreCase(givenOption)) {
				waitAndClick(e);
				break;
			}
		}
	}

	/**
	 * Function: Get all drop down options list
	 * 
	 * @param selectDropDownWebElement
	 */
	public List<WebElement> getDropDownOptions(WebElement selectDropDownWebElement) {

		waitForElementPresent(selectDropDownWebElement, 10);
		Select dropdown = new Select(selectDropDownWebElement);
		return dropdown.getOptions();
	}

	/**
	 * Function: Get Selected drop down option
	 * 
	 * @return
	 */
	public WebElement getSelectedDropDownOption(WebElement selectDropDownWebElement) {
		waitForElementPresent(selectDropDownWebElement, 10);
		Select dropdown = new Select(selectDropDownWebElement);
		return dropdown.getFirstSelectedOption();
	}

	/**
	 * @function: Select accept alert.
	 * 
	 * @param time
	 */
	public void selectAlertPresent(final int time) {

		for (int i = 0; i < time; i++) {
			if (isAlertPresent()) {
				final Alert alert = driver.switchTo().alert();
				alert.accept();
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Funtion: Verify presence of alert popup
	 * 
	 * @return
	 */
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			logger.debug("Check if alert present");
			return true;
		} catch (final NoAlertPresentException e) {
			return false;
		}
	}

	public String currentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}

	public String currentOnlyDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("d");
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
	 * Function: Get Future date only
	 * 
	 * @return
	 */
	public String getFutureDate() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		// add one day to the date/calendar
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		// now get "tomorrow"
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("d");
		String strDate = dateFormat.format(tomorrow);
		return strDate;

	}

	/**
	 * Get Past week start and end dates
	 * 
	 * @return
	 */
	public List<String> pastWeekStartAndEndDates() {
		List<String> listToBeVerified = new ArrayList<String>();
		ZoneId US = ZoneId.of("America/New_York");
		final ZonedDateTime input = ZonedDateTime.now(US);
		final ZonedDateTime startOfLastWeek = input.minusWeeks(1).with(DayOfWeek.MONDAY);
		listToBeVerified.add(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(startOfLastWeek));
		final ZonedDateTime endOfLastWeek = startOfLastWeek.plusDays(6);
		listToBeVerified.add(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(endOfLastWeek));
		return listToBeVerified;
	}

	public WebElement waitForWebElementDisplayed(WebElement webElement, int timeOutInSeconds) {
		try {
			new FluentWait<WebElement>(webElement).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(ElementNotVisibleException.class).ignoring(ElementNotInteractableException.class)
					.ignoring(TimeoutException.class).ignoring(ElementNotFoundException.class)
					.until(new Function<WebElement, Boolean>() {
						@Override
						public Boolean apply(WebElement element) {
							return element.isDisplayed();
						}
					});

		} catch (Exception e) {

		}
		return null;
	}

	public WebElement waitForWebElementEnable(WebElement webElement, int timeOutInSeconds) {
		try {
			new FluentWait<WebElement>(webElement).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(ElementNotVisibleException.class).ignoring(ElementNotInteractableException.class)
					.ignoring(TimeoutException.class).ignoring(ElementNotFoundException.class)
					.until(new Function<WebElement, Boolean>() {
						@Override
						public Boolean apply(WebElement element) {
							return element.isEnabled();
						}
					});

		} catch (Exception e) {

		}
		return null;
	}

	public void waitForSpinner(int timeout) {
		waitForAjaxRequestsToComplete();
		String locator = "//div[@class='ng-isolate-scope ng-hide']";
		WebElement element = driver.findElement(By.xpath(locator));
		waitForWebElementEnable(element, timeout);
	}

	/** Select CheckBox From The List */
	public void clickCheckboxFromList(String xpathOfElement, String valueToSelect) {
		List<WebElement> element = driver.findElements(By.xpath(xpathOfElement));
		for (int i = 0; i < element.size(); i++) {
			List<WebElement> dr = element.get(i).findElements(By.tagName("input"));
			for (WebElement value : dr) {
				if (valueToSelect.equals(value.getText())) {
					value.click();
					break;
				}
			}
		}
	}

	/** CheckBox Checking **/
	public boolean checkboxStatus(WebElement checkbox) {
		boolean checkstatus = false;
		try {
			if (checkbox.isSelected())
				checkstatus = true;
		} catch (Exception e) {

		}
		return checkstatus;
	}

	/**
	 * Checkbox status
	 * 
	 * @param checkbox
	 * @return the element status i.e is WebElement Selected
	 */
	public boolean checkboxStatus(String checkbox) {
		WebElement element = driver.findElement(ByLocator(checkbox));
		boolean checkStatus = checkboxStatus(element);
		return checkStatus;
	}

	/** Navigate to previous page **/

	public void navigateBack() {
		driver.navigate().back();
	}

	/** Navigate to forward **/
	public void navigateForward() {
		driver.navigate().forward();
	}

	public void waitSpinnerToBecomeInvisible() {
	     waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("//div[@class='spinner']"));
		
	}

	public void waitUntillSpinnerToBecomeInvisible() {
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath(
				"//div[@class='modal fade modalshow in']//div[@class='ng-isolate-scope']//div[@class='spinner']"));
	}

	public void spinnerBecomeInvisible() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
	}
	
	public void waitForSpinnerBecomeInvisible(int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@data-ng-show,'isBusy') and @class='ng-isolate-scope']")));
	}

	public void waitSpinnerToBecomeVisible() {
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeVisible(By.xpath("//div[@class='spinner']"), driver);
	}
	

	public void waitForElement() throws InterruptedException {
		Thread.sleep(2000);
	}

	/**
	 * To Get The ToolTip Text
	 * 
	 * @param WebElement
	 */
	public String getToolTip(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
		String tootipMsg = element.getAttribute("title");
		return tootipMsg;
	}

	/**
	 * @function: Double click on the element
	 */
	public void doubleClickOnElement(WebElement element) {
		javascripctHilightingElement(element);
		reportInfo();
		unhighLightElement();
		Actions actions = new Actions(driver).doubleClick(element);
		actions.build().perform();
	}

	/**
	 * click On Element Through Mouse Hovering
	 */
	public void clickOnElementThroughMouseHovering(WebElement element) {
		javascripctHilightingElement(element);
		reportInfo();
		unhighLightElement();
		Actions actions = new Actions(driver).pause(500).click(element);
		actions.build().perform();
	}

	public void switchToFrame(WebElement element) {

		driver.switchTo().frame(element);

	}

	/**
	 * Mousehover on any element
	 */

	public void mouseHoverOnAnElement(WebElement webElement) {

		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).build().perform();

	}

	public String openApplicationInNewWindowTab(String appURL) {
		_normalWait(2000);
		String parent = null, child;
		try {
			Robot robot = new Robot();
			robot.setAutoDelay(3000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_T);
			_normalWait(1000);
			Set<String> windows = getDriver().getWindowHandles();
			Iterator<String> iterator = windows.iterator();
			parent = iterator.next();
			_normalWait(2000);
			child = iterator.next();
			getDriver().switchTo().window(child);
			getDriver().navigate().to(appURL);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		waitUntillFinishProcessSpinnerDisable();
		return parent;
	}

	/**
	 * @function: To verify element's enable or disable status
	 * @param Webelement
	 * @return
	 * 
	 */
	public boolean elementStatus(WebElement elementToBeCheck) {
		boolean status = false;
		if (elementToBeCheck.isEnabled()) {
			status = true;
		} else if (!(elementToBeCheck.isEnabled())) {
			status = false;
		}
		return status;
	}

	/**
	 * Get System current date time stamp in DD-MMM-YYYY format
	 * 
	 * @return
	 */
	public void verifyDateFormat(WebElement calenderWebelement) {

		boolean dateFormatFlag = false;

		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-uuuu");
		String systemFormatDateTime = currentTime.format(formatter);
		String dateStr = systemFormatDateTime.replace("-", "").replace(" ", "");

		String dateAndTimeSTR = calenderWebelement.getText();
		String formattedDate = dateAndTimeSTR.replace("-", "").replace(" ", "");

		/*
		 * int k = formattedDate.indexOf(" ", formattedDate.indexOf(" ") + 1); String
		 * dateFormat1 = formattedDate.substring(0, k);
		 * 
		 * int i = dateFormat1.indexOf(" ", dateFormat1.indexOf(" ")); String
		 * dateFormat3 = dateFormat1.substring(0, i);
		 */

		if (dateStr.length() <= formattedDate.length()) {
			dateFormatFlag = true;
			Assert.assertTrue(dateFormatFlag, "Date displayed in DDMMMYYYY Format");

		} else {
			Assert.assertFalse(dateFormatFlag);
		}
	}

	/**
	 * @function: Clear the previously filled input box
	 */
	public void clearTextBox(WebElement inputBoxWebElement) {
		javascripctHilightingElement(inputBoxWebElement);
		unhighLightElement();
		inputBoxWebElement.clear();
	}

	/**
	 * @return Background color of the element
	 * @function: Get background color of an element
	 * 
	 */
	public String getBackgroundColor(WebElement element) {

		String bgColor = element.getCssValue("color");

		return bgColor;
	}

	public String getTextColor(WebElement element) {

		String TextColor = element.getCssValue("color");
		return TextColor;
	}

	public void verifyTextColor(WebElement element, String color) {
		String textColor = getTextColor(element);
		Assert.assertTrue(StringUtils.containsIgnoreCase(color, textColor));
	}

	/**
	 * @return
	 * @function: Verify visibility of element on the page through list size
	 */
	public boolean isElementVisibleListSize(List<WebElement> weList) {
		boolean visibility = false;
		if (weList.size() < 0) {
			visibility = false;
		} else if (weList.size() > 0) {
			visibility = true;
		}
		return visibility;

	}

	/**
	 * @return
	 * @function: Get any attribute value of an element
	 * @param: Webelement and Attribute name
	 * 
	 */
	public String getAttributeValueOfElement(WebElement element, String attributeName) {
		isElementDisplayed(element);
		String attributeValue = element.getAttribute(attributeName);
		return attributeValue;
	}

	/**
	 * Return the attribute value of the given element
	 * 
	 * @param element
	 * @param attributeName
	 * @author siddharth
	 * @created on:05/09/2019
	 * @return {@link String}
	 */
	public String getAttributeValueOfElement(String element, String attributeName) {
		String attributeValue = this.getAttributeValueOfElement(driver.findElement(ByLocator(element)), attributeName);
		return attributeValue;
	}

	/* Refresh The Page */
	public void refreshPage() {
		driver.navigate().refresh();
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
	}

	/**
	 * Convert Sting value to Integer value
	 * 
	 */
	public int parseStringValueIntoInteger(String StringToBeEntered) {
		int result = Integer.parseInt(StringToBeEntered);
		return result;
	}
	/* Reach To End Of the Page */

	public void reachToEndOfThePage() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();

	}

	/*-------------------New Functionality methods--------------------------------------*/

	/* Select Upper Nav Menu Items */

	@SuppressWarnings("unchecked")
	public <T> T selectHorizontalUpperNavMenuItem(final Class className, String itemToBeSelect, String... subTabValue) {
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
		boolean flag = false;
		scrollToTopOfThePage();
		for (WebElement menuItem : driver
				.findElements(By.xpath("(//ul[contains(@class,'nav') and not(contains(@class,'drop'))])[1]/li/a"))) {
			try {
				if (getText(menuItem).trim().equalsIgnoreCase(itemToBeSelect)) {
					waitAndClick(menuItem);
					waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
					flag = true;
					break;
				}
			} catch (Exception e) {

				if (menuItem.findElement(By.xpath(".//span[@class='dropdown-text']")).getText()
						.equalsIgnoreCase(itemToBeSelect)) {
					waitAndClick(menuItem);
					waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
					flag = true;
					break;
				}

			}

		}
		_normalWait(2000);
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='header']//ul[@class='dropdown-menu']/parent::li[contains(@class,'open')]//following-sibling::ul/li/a")));

		} catch (Exception e) {

		}

		if (driver.findElements(By.xpath(
				"//*[@id='header']//ul[@class='dropdown-menu']/parent::li[contains(@class,'open')]//following-sibling::ul/li/a"))
				.size() > 0) {
			for (WebElement subTab : driver.findElements(By.xpath(
					"//*[@id='header']//ul[@class='dropdown-menu']/parent::li[contains(@class,'open')]//following-sibling::ul/li/a"))) {
				if (getText(subTab).trim().contains(subTabValue[0])) {
					waitAndClick(subTab);
					waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
					flag = true;
					break;
				}

			}
		}
		Assert.assertTrue(flag);
		waitUntillFinishProcessSpinnerDisable();

		reportInfo();
		return (T) PageFactory.initElements(driver, className);
	}


	/**
	 * navigating To subTabs;
	 * 
	 * @param tabName
	 * @param className
	 * @return
	 */
	public <T> T navigateToSubTabs(String tabName, Class<T> className) {

		List<WebElement> subtabName = driver.findElements(ByLocator("//ul[contains(@class,'nav-tabs')]//li"));
		boolean flag = false;
		for (WebElement we : subtabName) {

			if (we.getText().equalsIgnoreCase(tabName)) {
				flag = true;
				waitAndClick(we);
				Assert.assertTrue(true);
				break;
			}
		}
		//waitSpinnerToBecomeInvisible();
		waitForSpinnerBecomeInvisible(3000);
		Assert.assertTrue(flag);
		return PageFactory.initElements(driver, className);
	}

	/**
	 * Function: Verify Administration SubTabs are editable or not
	 * 
	 * @param className
	 * @param condition
	 * @param isdisplayed
	 * @param isdisabled
	 * @return
	 */
	public <T> T editAdministrationSubTabs(Class<T> className, String condition, boolean isdisplayed)

	{

		String editIcon = "(//span[@class='icon-small icon-edit'])[1]/..";
		String addEditIcon = "//div[@class='col-xs-15']//span[@class='icon-small icon-add']/..";
		String SaveBtn = "//span[@class='icon-small icon-save']/..";
		String SrvyBtnAdd = "(//div[@class='col-xs-24 no-padding align-center']//span)[1]//a";
		String value;
		if (condition.equalsIgnoreCase("EditIcon")) {
			if (isdisplayed) {
				value = getAttributeValueOfElement(editIcon, "enabled");
				Assert.assertFalse(StringUtils.isNotBlank(value));
			} else  {
				value = getAttributeValueOfElement(editIcon, "disabled");
				Assert.assertTrue(Boolean.valueOf(value));
			}
			
		}

		else if (condition.equalsIgnoreCase("AddEditIcon")) {

			if (isdisplayed) {
				value = getAttributeValueOfElement(addEditIcon, "enabled");
				Assert.assertFalse(StringUtils.isNotBlank(value));
			} else {
				value = getAttributeValueOfElement(addEditIcon, "disabled");
				Assert.assertTrue(Boolean.valueOf(value));
			}
		}

		else if (condition.equalsIgnoreCase("SaveIcon")) {

			if (isdisplayed) {
				value = getAttributeValueOfElement(SaveBtn, "enabled");
				Assert.assertFalse(StringUtils.isNotBlank(value));
			} else {
				value = getAttributeValueOfElement(SaveBtn, "disabled");
				Assert.assertTrue(Boolean.valueOf(value));
			}

		}

		else if (condition.equalsIgnoreCase("srvyBtnAdd")) {

			if (isdisplayed) {
				value = getAttributeValueOfElement(SrvyBtnAdd, "enabled");
				Assert.assertFalse(StringUtils.isNotBlank(value));
			} else {
				value = getAttributeValueOfElement(SrvyBtnAdd, "disabled");
				Assert.assertTrue(Boolean.valueOf(value));
			}
		}
		
		return PageFactory.initElements(driver, className);
	}


	/**
	 * Enter the keys to enter through keyboard(i.e robot)
	 * 
	 * @param keysToEnter
	 */
	public void RobotAction(int... keysToEnter) {
		int[] al = keysToEnter;
		try {
			robot = new Robot();
			for (int key : al) {
				robot.keyPress(key);
			}
			for (int i = al.length; i < 0; i--)
				robot.keyRelease(al[i]);
		} catch (Exception e) {
			Reporter.log("The key [" + keysToEnter + "] Entered is invalid Please Enter Correct key");
		}
	}

	/**
	 * Verify WebElement present inside dom pass the tag name and text to verify the
	 * WebElement
	 * 
	 * @param h2
	 * @param text
	 */
	public void verifytextPresentInTheTag(TagName tagName, String text) {
		String locator = "//" + tagName + "[contains(text(),'" + text + "')]";
		WaitForElementPresent(locator, DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(isElementPresent(locator));
	}


	/**
	 * Verify that WebElements are displayed or not
	 * <p>Pass The wait in calling method if method doesn't work"</p>
	 * @param elements
	 */
	public void verifyElementIsDisplayed(boolean status, WebElement... elements) {
		if (status) {
			for (WebElement element : elements) {
				Assert.assertTrue(isElementDisplayed(element));
			}
		} else {
			for (WebElement element : elements) {
				Assert.assertTrue(!isElementDisplayed(element));
			}
		}
	}

	/**
	 * Verify Element are displayed or not
	 * <p>Pass The wait in calling method if method doesn't work"</p>
	 * @param elements
	 */
	public void verifyElementIsDisplayed(boolean status, String... elements) {
		WebElement element1;
		if (status) {
			for (String element : elements) {
				element1 = _getWebElementOnExistence(element);
				verifyElementIsDisplayed(true, element1);
			}
		} else {
			for (String element : elements) {
				element1 = _getWebElementOnExistence(element);
				verifyElementIsDisplayed(false, element1);
			}
		}
	}

	/**
	 * Verify that WebElement present are enabled  
	 * <p>Pass The wait in calling method if method doesn't work"</p>
	 * @param elements
	 */
	public void verifyElementIsEnabledOrDisabled(boolean status, WebElement... elements) {
		if (status) {
			for (WebElement element : elements) {
				Assert.assertTrue(elementStatus(element));
			}
		}else {
			for (WebElement element : elements) {
				Assert.assertTrue(!elementStatus(element));
			}
		}
	}

	/**
	 * Verify Element are enabled or not
	 * <p>Pass The wait in calling method if method doesn't work"</p>
	 * @param elements
	 */
	public void verifyElementIsEnabledOrDisabled(boolean status, String... elements) {
		if (status) {
			for (String element : elements) {
				WebElement element1 = _getWebElementOnExistence(element);
				verifyElementIsEnabledOrDisabled(true,element1);
			}
		}else {
			for (String element : elements) {
				WebElement element1 = _getWebElementOnExistence(element);
				verifyElementIsEnabledOrDisabled(false,element1);
			}
		}
	}

	

	/**
	 * Verify that WebElement present are enabled
	 * @param elements
	 */
	public void verifyElementIsEnabled(WebElement... elements){
		for(WebElement element:elements) {
			boolean status=elementStatus(element);
			Assert.assertTrue(status);
		}
	}
	
	
	@FindBy(xpath="(//label[text()=' Failed to get training card info ']/following::div[@class='close-button-white'])[1]")
	private WebElement failedTraininginfoAlert;
	
	public void clickToCloseFailedTrainingInfoAlert()
	{
		boolean flag=true;
		try {
			if (failedTraininginfoAlert.isDisplayed()) {
				waitAndClick(failedTraininginfoAlert);
				flag = true;
			}
			
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
		
	}
	
	
	/**
     *  MA spinner
     * 
     */
    public void waitUntillFinishProcessSpinnerDisable()
    {
   	 waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("//div[@class='ma-spinner']"));
    }

	
	
    /*
     * This funnction creates a List and switches to window which is not current
     */

    public void switchToNewWindow() {

        String currentWindow = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();
        allHandles.remove(currentWindow);

        for (Iterator<String> it = allHandles.iterator(); ((Iterator) it).hasNext(); ) {
            String newHandle = it.next();
            driver.switchTo().window(newHandle);
        }
    }
    
	/**
	 * 
	 *  File uploading using Robot Class
	 * @param fileName
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void uploadFileUsingRobotclass(String fileName) throws InterruptedException, AWTException
	{
		String FilePath = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\testdata"
				+ File.separator + fileName;

		FilePath = FilePath.replace("\\", "\\");
		StringSelection sel = new StringSelection(FilePath);

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);

		Robot robot = new Robot();

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

		// Press CTRL+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Release CTRL+V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);
	}
	

	@FindBy(how=How.XPATH,using="//a[contains(text(),'Profile')]")
	private WebElement profileBtn;
	
	public RaterProfilePage navigateToRaterProfile(String raterName)
	{
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
		scrollToTopOfThePage();
		WebElement optionToBeSelected = driver.findElement(ByLocator("//span[contains(text(),'"+raterName+"')]/.."));
		clickOn(optionToBeSelected);
		waitAndClick(profileBtn);
		return PageFactory.initElements(driver, RaterProfilePage.class);
		
	}
	
	@FindBy(how=How.XPATH,using="//span[text()='CONFIGURE']/..")
	private WebElement configureOption;
	
	@FindBy(how=How.XPATH,using="//ul[@class='dropdown-menu']//a[contains(text(),'Qualification Library')]")
	private WebElement qualificationLibrary;
	
	public QualificationLibraryPage navigateToQualificationLibrary()
	{
		waitAndClick(configureOption);
		try {
			if (qualificationLibrary.isDisplayed()) {
				clickOn(qualificationLibrary);
			}
		} catch (Exception e) {
		}
		return PageFactory.initElements(driver, QualificationLibraryPage.class);
	}
	
	@FindBy(xpath="//div[@data-ng-show='messages']//button[contains(text(),'OK')]")
	private WebElement messageFieldokBtn;
	
	
	public void DismissAlertMessage()
	{
		boolean flag=true;
		try {
			if (messageFieldokBtn.isDisplayed()) {
				clickOn(messageFieldokBtn);
				flag=true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	@FindBy(xpath="//div[@id='cdk-overlay-3']//span[@class='ma-dialog-title']")
	private WebElement trainingpopup;
	
	@FindBy(xpath="//button[@class='btn btn-default ng-star-inserted'and text()=' Cancel']")
	private WebElement cancelTrainingBtn;
	
	@FindBy(xpath="//button[@class='btn btn-new-active ng-star-inserted' and text()=' Close']")
	private WebElement closebtn;
	
	@FindBy(xpath="//a[contains(text(),'HOME')]")
	private WebElement homeButton;
	
	@FindBy(xpath = "//div[@class='ma-dialog-header']//span[@class='ma-dialog-title' and @title='Security Confirmation']")
	private WebElement securityConfirmationPopUp;
	
	public void closeApplicationTraining()
	{
		boolean flag=true;
		try {
			if (trainingpopup.isDisplayed()) {
				clickOn(cancelTrainingBtn);
				waitUntillFinishProcessSpinnerDisable();
				clickOn(closebtn);
				waitUntillFinishProcessSpinnerDisable();
				clickOn(homeButton);
				waitUntillFinishProcessSpinnerDisable();
				DismissAlertMessage();
				flag=true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	/**
	 * Handle child windows
	 *
	 * @return: Parent window name
	 * @throws InterruptedException
	 */
	public String switchToChildWindow() throws InterruptedException {
		Thread.sleep(2000);
		Set<String> windows = getDriver().getWindowHandles();
		Iterator<String> iterator = windows.iterator();
		String parent = iterator.next();
		String child = iterator.next();
		getDriver().switchTo().window(child);
		return parent;
	}

	/** close child window */
	public void switchParentWindowByClosingChild(String Win) {
		driver.close();
		getDriver().switchTo().window(Win);
	}

	@FindBy(xpath="//div[contains(@class,'form-fields-box')]/button[text()='Launch Training']")
	private WebElement launchTrainingBtn;
	
	@FindBy(xpath="//div[contains(@id,'cdk-overlay')]//div[contains(@class,'form-fields-box')]/button")
	private WebElement continueTrainingBtn;
	
	@FindBy(xpath="//div[@class='done-button-container']/button[text()='Done']")
	private WebElement doneButton;
	
	@FindBy(xpath="//div[contains(@class,'form-fields-box')]/button[text()='E-Sign']")
	private List<WebElement> eSign;
	
	@FindBy(xpath="//div[contains(@class,'form-fields-box')]/button[text()='E-Sign']")
	private WebElement eSignButton;
	
	@FindBy(xpath="//input[@formcontrolname='userName']")
	private WebElement userNameField;
	
	@FindBy(xpath="//input[@formcontrolname='password']")
	private WebElement PasswordField;
	
	@FindBy(xpath="//button[text()='Confirm']")
	private WebElement confirmBtn;
	
	@FindBy(xpath = "//ul[@class='choice-list']//label[contains(text(),'US')]")
	private WebElement english_US_Option;
	
	@FindBy(xpath = "//ul[@class='choice-list']//label[contains(text(),'US')]/preceding::div[1]/input")
	private WebElement english_US_Checkbox;
	
	public void eSignature(String userName,String password)
	{
		//waitUntillFinishProcessSpinnerDisable();
		inputText(userNameField, userName);
        inputText(PasswordField, password);
        waitForElement(confirmBtn);
        waitAndClick(confirmBtn);
		waitUntillFinishProcessSpinnerDisable();
	}
	
	public void completeAllAssetsInTraining(String userName,String password)
	{
		boolean found = false,flag=true;
		_normalWait(2000);
		try {
			if (trainingpopup.isDisplayed()) {
				_normalWait(2000);
				moveToElement(trainingpopup);
				moveToElement(continueTrainingBtn);
				if(continueTrainingBtn.getText().equalsIgnoreCase("E-Sign")) {
					System.out.println("Training Asset is Completed");
				}else {
				
				do {
					if(isElementDisplayed(english_US_Checkbox)) {
						moveToElement(english_US_Option);
						javascriptButtonClick(english_US_Checkbox);
					}
					javascriptButtonClick(continueTrainingBtn);
					waitUntillFinishProcessSpinnerDisable();
					String parent = switchToChildWindow();
					clickOn(doneButton);
					getDriver().switchTo().window(parent);
					waitUntillFinishProcessSpinnerDisable();
					} while (!(eSign.size()==1));
				found = true;
				Assert.assertTrue(found, "Training Asset is Completed");
				}
				waitAndClick(eSignButton);
				waitForElementPresent(securityConfirmationPopUp, DEFAULT_WAIT_4_PAGE);
				Assert.assertTrue(securityConfirmationPopUp.isDisplayed());
				eSignature(userName, password);
                clickOn(closebtn);
				waitUntillFinishProcessSpinnerDisable();
				clickOn(homeButton);
				waitUntillFinishProcessSpinnerDisable();
				flag=true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	@FindBy(xpath = "//div[@class='cdk-overlay-container']//input[@placeholder='Search']")
	private WebElement searchStudy;

	@FindBy(xpath = "//p[contains(text(),'Study')]//parent::div//div[contains(@class,'item ng-s')]")
	private List<WebElement> studyList;

	@FindBy(xpath="//a[text()='HOME']/..")
	private WebElement homebutton;
	
	
	public void selectStudyWithAllSites(String studyName) {
		_normalWait(1000);
		try {
			if (driver.findElement(By.xpath("//div[@id='study-header']/button[1]")).isEnabled() == true) {
				driver.findElement(By.xpath("//div[@id='study-header']/button[1]")).click();
			}
		} catch (Exception e) {
		}
		if (driver.findElements(By.xpath("//*[contains(@id,'cdk-overlay')]")).size() > 0) {
			inputText(searchStudy, studyName);
			for (WebElement study : studyList) {
				if (study.getText().trim().contains(studyName)) {
					javascriptButtonClick(study);
					break;
				}
			}
		}
		WebElement sitestoBeSelected = driver.findElement(ByLocator("//div[text()=' All sites ']"));
		clickOn(sitestoBeSelected);
		WebElement selectBtn = driver.findElement(ByLocator("//button[text()='Select']"));
		waitAndClick(selectBtn);
		reportInfo();
	}
	
	public void navigateOnHomePage()
	{
		clickOn(homebutton);
		waitUntillFinishProcessSpinnerDisable();
	}

}

	


