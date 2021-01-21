/*
  Class to initialize all application page objects and manage WebDriver browser
  object. Each and every test script class must extend this. This class does
  not use any of the Selenium APIs directly, and adds support to make this
  framework tool independent.

  @author 360Logica
 * @since 1.0
 *
 * @version 1.0
 */
package net.medavante.portal.selenium.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.winium.WiniumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import net.medavante.mobile.appium.core.MobileDriver;
import net.medavante.mobile.core.TabletDriver;
import net.medavante.portal.enums.DriverType;
import net.medavante.portal.pages.FormLibraryPage;
import net.medavante.portal.pages.LoginPage;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.RaterProfilePage;
import net.medavante.portal.pages.administration.AdministrationFormsLibraryPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationAddressesPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationGeneralPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationOrganizationPeoplePage;
import net.medavante.portal.pages.administration.AdministrationPeopleContactInformationPage;
import net.medavante.portal.pages.administration.AdministrationPeopleGeneralPage;
import net.medavante.portal.pages.administration.AdministrationPeopleOrganizationsPage;
import net.medavante.portal.pages.administration.AdministrationPeoplePage;
import net.medavante.portal.pages.administration.AdministrationPeopleQualificationsPage;
import net.medavante.portal.pages.administration.AdministrationScaleActivationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesAnalyticsPage;
import net.medavante.portal.pages.administration.AdministrationStudiesApplicationsPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCliniciansPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCountriesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesCustomPage;
import net.medavante.portal.pages.administration.AdministrationStudiesDataLockPage;
import net.medavante.portal.pages.administration.AdministrationStudiesFormsPage;
import net.medavante.portal.pages.administration.AdministrationStudiesGeneralPage;
import net.medavante.portal.pages.administration.AdministrationStudiesIdentityPage;
import net.medavante.portal.pages.administration.AdministrationStudiesLanguagesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesPeoplePage;
import net.medavante.portal.pages.administration.AdministrationStudiesPreQualificationsPage;
import net.medavante.portal.pages.administration.AdministrationStudiesQualificationsPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSchedulePage;
import net.medavante.portal.pages.administration.AdministrationStudiesSitesPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSurveyPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSurveyTrackingPage;
import net.medavante.portal.pages.administration.AdministrationStudiesVirgilUniversityPage;
import net.medavante.portal.pages.administration.AdministrationStudiesVisitsPage;
import net.medavante.portal.pages.administration.AdministrationSystemPage;
import net.medavante.portal.pages.analytics.AnalyticsDashBoardPage;
import net.medavante.portal.pages.centralrating.CentralRatingAppointmentPage;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.pages.formsLibrary.FormManagerPage;
import net.medavante.portal.pages.preqqualification.PreQualificationDashBoardPage;
import net.medavante.portal.pages.qualificationlibrary.QualificationLibraryPage;
import net.medavante.portal.pages.studynavigator.AssessmentsDetailsPage;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.RatersDetailsPage;
import net.medavante.portal.pages.studynavigator.StudyAssessmentsListing;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.pages.studynavigator.StudySubjectListingPage;
import net.medavante.portal.pages.studynavigator.VisitDetailsPage;
import net.medavante.portal.pages.traininglibrary.AssetsDetailsPage;
import net.medavante.portal.pages.traininglibrary.CoursesDetailsPage;
import net.medavante.portal.pages.traininglibrary.TrainingDetailsPage;
import net.medavante.portal.report.MobileScreenRecorder;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.MobileConstants;
import net.medavante.portal.utilities.UserClaims;
import net.medavante.portal.utilities.Utilities;

public abstract class BaseTest extends MobileDriver
		implements CentralRatingModuleConstants, ApplicationVerificationMessage, UserClaims, MobileConstants  {

	public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static final String BREAK_LINE = "\n";

	public static ExtentTest test;
	public static ExtentReports extent;

	// page object initialization
	protected TabletDriver tabletDriver = new TabletDriver();
	protected LoginPage loginPage = new LoginPage(driver);
	protected RaterProfilePage raterProfilePage=new RaterProfilePage(driver);
	protected MedAvantePortalPage dashBoardPage = new MedAvantePortalPage(driver);
	protected AdministrationOrganizationPage adminstrationOrganizationPage = new AdministrationOrganizationPage(driver);
	protected AdministrationSystemPage adminstrationSystemPage = new AdministrationSystemPage(driver);
	protected AdministrationStudiesPage adminstrationStudiesPage = new AdministrationStudiesPage(driver);
	protected AdministrationStudiesGeneralPage adminstrationStudyGeneralPage = new AdministrationStudiesGeneralPage(
			driver);
	protected AdministrationStudiesCustomPage adminstrationStudiesCustomPage = new AdministrationStudiesCustomPage(
			driver);
	protected AdministrationStudiesSitesPage administrationStudiesSitePage = new AdministrationStudiesSitesPage(driver);
	protected AdministrationFormsLibraryPage adminstrationFormLibraryPage = new AdministrationFormsLibraryPage(driver);
	protected AdministrationStudiesCliniciansPage administrationStudiesCliniciansPage = new AdministrationStudiesCliniciansPage(
			driver);
	protected AdministrationStudiesPreQualificationsPage prequalificationPage = new AdministrationStudiesPreQualificationsPage(
			driver);
	protected AdministrationStudiesFormsPage adminstrationFormsPage = new AdministrationStudiesFormsPage(driver);
	protected AdministrationStudiesAnalyticsPage adminstrationStudiesAnalytics = new AdministrationStudiesAnalyticsPage(
			driver);

	protected AdministrationOrganizationGeneralPage adminstrationOrganizationGeneralPage = new AdministrationOrganizationGeneralPage(
			driver);
	protected AdministrationOrganizationAddressesPage adminstrationOrganizationAddressPage = new AdministrationOrganizationAddressesPage(
			driver);
	protected AdministrationOrganizationPeoplePage adminstrationOrganizationPeoplePage = new AdministrationOrganizationPeoplePage(
			driver);
	protected StudyDashBoardPage studyNavigatorDashBoardPage = new StudyDashBoardPage(driver);
	protected NewSubjectDetailPage subjectDetailPage = new NewSubjectDetailPage(driver);
	protected CentralRatingAssessmentsListingPage centralRatingAssesmentListingPage = new CentralRatingAssessmentsListingPage(
			driver);
	protected CentralRatingAppointmentPage appointmentPage = new CentralRatingAppointmentPage(driver);
	protected AssessmentsDetailsPage assessmentDetailPage = new AssessmentsDetailsPage(driver);
	protected StudyAssessmentsListing assessmentListingPage = new StudyAssessmentsListing(driver);
	protected PreQualificationDashBoardPage preQualificationDashBoardPage = new PreQualificationDashBoardPage(driver);
	protected CentralRatingAppointmentPage centralRatingAppointmentPage = new CentralRatingAppointmentPage(driver);
	protected StudySubjectListingPage studySubjectListing = new StudySubjectListingPage(driver);
	protected VisitDetailsPage visitDetaiLPage = new VisitDetailsPage(driver);
	protected AnalyticsDashBoardPage analyticsPage = new AnalyticsDashBoardPage(driver);
	protected RatersDetailsPage ratersDetailsPage = new RatersDetailsPage(driver);
	protected AdministrationStudiesLanguagesPage adminLanguagePage = new AdministrationStudiesLanguagesPage(driver);
	protected AdministrationStudiesCountriesPage adminCountriesPage = new AdministrationStudiesCountriesPage(driver);
	protected AdministrationStudiesVisitsPage adminVisitsPage = new AdministrationStudiesVisitsPage(driver);
	protected AdministrationStudiesPeoplePage adminPeoplePage = new AdministrationStudiesPeoplePage(driver);
	protected AdministrationStudiesSitesPage adminSitesPage = new AdministrationStudiesSitesPage(driver);
	protected AdministrationStudiesDataLockPage admindataLockTabPage =new AdministrationStudiesDataLockPage(driver);

	protected AdministrationStudiesQualificationsPage adminStudQualificationPage = new AdministrationStudiesQualificationsPage(
			driver);
	protected AdministrationStudiesSurveyPage adminStudiesSurveyPage = new AdministrationStudiesSurveyPage(driver);
	protected AdministrationStudiesSchedulePage adminStudiesSchedulePage = new AdministrationStudiesSchedulePage(
	
			driver);
	protected AdministrationScaleActivationPage adminScaleActivationPage=new AdministrationScaleActivationPage(driver);
	protected AdministrationStudiesSurveyTrackingPage adminSurveyTrackingPage= new AdministrationStudiesSurveyTrackingPage(driver);
	protected AdministrationStudiesApplicationsPage adminStudiesApplicationPage = new AdministrationStudiesApplicationsPage(
			driver);
	protected AdministrationStudiesIdentityPage adminStudiesIdentityPage = new AdministrationStudiesIdentityPage(
			driver);
	protected AdministrationStudiesVirgilUniversityPage adminStudiesVirgilUniversityPage = new AdministrationStudiesVirgilUniversityPage(
			driver);

	protected AdministrationPeoplePage adminstrationPeoplePage = new AdministrationPeoplePage(driver);
	protected AdministrationPeopleGeneralPage adminstrationPeopleGeneralPage = new AdministrationPeopleGeneralPage(
			driver);
	protected AdministrationPeopleContactInformationPage adminstrationPeopleContactInformationPage = new AdministrationPeopleContactInformationPage(
			driver);
	protected AdministrationPeopleOrganizationsPage adminstrationPeopleOrganizationPage = new AdministrationPeopleOrganizationsPage(
			driver);
	protected AdministrationPeopleQualificationsPage adminstrationPeopleQualificationPage = new AdministrationPeopleQualificationsPage(
			driver);
	protected FormLibraryPage formLibraryPage=new FormLibraryPage(driver);
	
	protected QualificationLibraryPage qualificationlibrary=new QualificationLibraryPage(driver);
	
	protected TrainingDetailsPage trainingDetailsPage=new TrainingDetailsPage(driver);
	protected CoursesDetailsPage coursesDetailsPage=new CoursesDetailsPage(driver);
	protected AssetsDetailsPage assetsDetailsPage=new AssetsDetailsPage(driver);
	protected FormManagerPage formManagerPage = new FormManagerPage(driver);
	

	private static String browserType, applicationUrl, machineForRun, setEnvironment, quitBrowser;
	private static WebDriver driver;
	private static AppiumDriver<MobileElement> appiumDriver;
	private static WiniumDriver windriver;

	protected String superAdminUN, superAdminPW, userName, maAdmin1UserName, sponsorUserName, maUser, password,
			studyName, studyNameCR, orgName, orgType, personName, formName,formNameMobile1,formNameMobile2,formNameTablet1,formNameTablet2, subjectName, studyAbbrevation,
			registrationCodeParticipant, registrationCodeObserver, studyLanguage, adminStudyName, visitName1,
			visitName2, abbrevationOrg, studyPhase, abbrevationStudy, orgNameForDataCreation, siteCount, studyCountry,
			visitName, userWithoutRightClaim, dashBoard1, dashBoard2, investigatorRoleAccess, sponsorUserType1Access,
			siteRaterAccess, MedUserType1Access, dashBoardVersion, dashBoardName, visitSubmitted, visitNotAssigned,email,
			PROAss, ObsROAss, ClinROAss, EventAss, crVisitName, subjectNameNew, subjectNameScreened,queryReply,
			subjectNameEnrolled, subjectNameDiscontinued, subjectNameCompleted, visitBaseLine, visitTreatmentPhase,
			studyForAssessmentListing, Subject01, Subject02, registrationIncorrectCode, registrationIncompleteCode,
			observerregistrationCode, visitScreenedName, visitEnrolled, visitCompleted, visitDiscontinued, newVisitName,
			screenedVisitName, enrolledVisitName, completedVisitName, discontinuedVisitName, pendingNonIRVisit, queryName,
			formTitle,formAbbreviation,footerData, subjectWithScreennedStatusName = "SUBJScreened_" + generateRandomString(5),
			subjectWithEnrolledStatusName = "SUBJEnrolled_" + generateRandomString(5),
			subjectCompletedStatusName = "SUBJCompleted_" + generateRandomString(5),
			subjectDisconutinedStatusName = "SUBJDiscontinued_" + generateRandomString(5),
			subjectScreenFailedName = "SUBJScreenFailed_" + generateRandomString(5);

	protected String displayName = "Auto_DashBorad" + generateRandomAlphanumericString(4),
			systemName = "Auto_System" + generateRandomAlphanumericString(4);
	protected String screeningNum = "Automation_" + generateRandomString(6);
	protected String randomizationNum = "AutoRandomNum_" + generateRandomString(4);

	public static String resultPath;
	protected HashMap<String,String> required;
	
	

	public static Document document = null;
	static Image img;

	public static final String[] IMAGES = { ".\\screenshots\\DummyImage.png" };
	public static final String DEST = ".\\PdfReport\\R1\\";
	static String reportSwitch = "";
	static String captureScreenshotSwitch = "";
	public static String exceutionOn = "";

	@SuppressWarnings("static-access")
	public BaseTest(String browser) {
		this.browserType = browser;
	}

	@BeforeSuite
	public void before() throws Exception {
		// Create Result repository for report.
		String timeStamp = getFormattedTimeStamp().replace("-", "").replace(":", "").replace(".", "");
		String path = Utilities.getPath();
		resultPath = path + "/Result/Suite_" + timeStamp;
		new File(resultPath).mkdirs();
		extent = new ExtentReports(resultPath + "/CustomReport.html", true);
		reportSwitch = Configuration.readApplicationFile("reportSwitch");
		machineForRun = Configuration.readApplicationFile("RunOnLocalMachine");
		setEnvironment = Configuration.readApplicationFile("SetEnvironment");
		quitBrowser = Configuration.readApplicationFile("closeAndQuitBrowser");
		captureScreenshotSwitch = Configuration.readApplicationFile("takeScreenShot");
	}

	@SuppressWarnings("static-access")
	@BeforeMethod
	public void setUp(Method method) throws Exception {
		if (exceutionOn.equals("Mobile")) {
			setReportTest(method);
			System.setProperty("testcaseName", method.getName().toString());
		} else if (exceutionOn.equals("") || exceutionOn.equals("MobileAndWebExecution")) {
			if (browserType == null) {
				browserType = Configuration.readApplicationFile("Chrome");
			}

			@SuppressWarnings("rawtypes")
			Class className = this.getClass();

			if (machineForRun.equals("true")) {
				if (setEnvironment.equals("stg")) {
					if (className.toString().contains("MAP")) {
						this.applicationUrl = Configuration.readApplicationFile("maStgURL");
					} else if (className.toString().contains("SIP")) {
						this.applicationUrl = Configuration.readApplicationFile("siteStgURL");
					} else if (className.toString().contains("SPP")) {
						this.applicationUrl = Configuration.readApplicationFile("sponsorStgURL");
					}
					
				} else if (setEnvironment.equals("test")) {
					if (className.toString().contains("MAP")) {
						this.applicationUrl = Configuration.readApplicationFile("maTestURL");
					} else if (className.toString().contains("SIP")) {
						this.applicationUrl = Configuration.readApplicationFile("siteTestURL");
					} else if (className.toString().contains("SPP")) {
						this.applicationUrl = Configuration.readApplicationFile("sponsorTestURL");
					}
				} else if (setEnvironment.equals("qa")) {
					if (className.toString().contains("MAP")) {
						this.applicationUrl = Configuration.readApplicationFile("maQaURL");
					} else if (className.toString().contains("SIP")) {
						this.applicationUrl = Configuration.readApplicationFile("siteQaURL");
					} else if (className.toString().contains("SPP")) {
						this.applicationUrl = Configuration.readApplicationFile("sponserQaURL");
					}
				}
				else if (setEnvironment.equals("form")) {
					if (className.toString().contains("MAP")) {
						this.applicationUrl = Configuration.readApplicationFile("maQaURL");
					} else if (className.toString().contains("SIP")) {
						this.applicationUrl = Configuration.readApplicationFile("formURL");
					} else if (className.toString().contains("SPP")) {
						this.applicationUrl = Configuration.readApplicationFile("sponserQaURL");
					}
				}
			} else {

				this.applicationUrl = System.getProperty("environmentUrl");
				this.applicationUrl = Configuration.readApplicationFile(this.applicationUrl);
			}

			if (DriverType.Firefox.toString().toLowerCase().equals(browserType.toLowerCase())) {
				System.setProperty("webdriver.gecko.driver",
						Utilities.getPath() + "//src//test//resources//webdriver/geckodriver.exe");
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setCapability("marionette", true);
				driver = new FirefoxDriver(firefoxOptions);

			} else if (DriverType.IE.toString().toLowerCase().equals(browserType.toLowerCase())) {
				System.setProperty("webdriver.ie.driver",
						Utilities.getPath() + "//src//test//resources//webdriver/IEDriverServer.exe");
				InternetExplorerOptions ieOptions = new InternetExplorerOptions();
				ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				ieOptions.setCapability("nativeEvents", false);
				driver = new InternetExplorerDriver(ieOptions);
			} else if (DriverType.Chrome.toString().toLowerCase().equals(browserType.toLowerCase())) {
				System.setProperty("webdriver.chrome.driver", ".//src//test//resources//webdriver/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);
			} else {
				throw new Exception("Please pass a valid browser type value");
			}

			/**
			 * Maximize windoW
			 */
			driver.manage().window().maximize();

			/**
			 * Delete cookies and set timeout
			 */
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			/**
			 * Open application URL
			 */
			getWebDriver().navigate().to(applicationUrl);

			/**
			 * Initialize the reporting object
			 */
			setReportTest(method);
			loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
			userName = Configuration.readApplicationFile("UserName");
			superAdminPW = Configuration.readApplicationFile("SuperAdminPW");
			maAdmin1UserName = Configuration.readApplicationFile("MAAdmin1");
			sponsorUserName = Configuration.readApplicationFile("sponsorUserName");
			maUser = Configuration.readApplicationFile("maUser");
			password = Configuration.readApplicationFile("Password");

			Properties properties = Configuration.readTestData("TestDataFile");
			studyName = properties.getProperty("AutomationStudyName");
			orgType = properties.getProperty("AutomationOrgType");
			orgName = properties.getProperty("AutomationOrgName");
			personName = properties.getProperty("AutomationSitePeople1");
			formName = properties.getProperty("AutomationScale");
			subjectName = properties.getProperty("subjectName");
			adminStudyName = properties.getProperty("adminStudyName");
			studyAbbrevation = properties.getProperty("studyAbbrevation");
			studyLanguage = properties.getProperty("AutomationStudyLanguage");
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMainMethod(ITestResult result) throws IOException, InterruptedException {
		if (exceutionOn.equals("Mobile")) {
			document.close();
			extent.endTest(test);
			extent.flush();
		} else if (exceutionOn.equals("") || exceutionOn.equals("MobileAndWebExecution")) {

			if (reportSwitch.equals("0") || reportSwitch.equals("2")) {
				if (result.getStatus() == ITestResult.FAILURE) {
					try {
						test.log(LogStatus.FAIL, "Failed test step is: " + result.getName() + " => " + getMessage());
						test.log(LogStatus.FAIL, result.getThrowable());
						captureScreenshot(result);
					} catch (Exception e) {
						e.printStackTrace();
					}
					document.close();
					extent.endTest(test);
					extent.flush();

				}

				else if (!(result.getStatus() == (ITestResult.SUCCESS)
						|| result.getStatus() == (ITestResult.FAILURE))) {
					try {
						test.log(LogStatus.SKIP, "Skipped Test Case is: " + result.getName() + " => " + getMessage());
						captureScreenshot(result);
					} catch (Exception e) {
						e.printStackTrace();
					}
					document.close();
					extent.endTest(test);
					extent.flush();
				}
			}

			if (reportSwitch.equals("1")) {

				if (result.getStatus() == ITestResult.FAILURE) {
					try {
						test.log(LogStatus.FAIL, "Failed test step is: " + result.getInstanceName());
						test.log(LogStatus.FAIL, result.getThrowable());
						captureScreenshot(result);
					} catch (Exception e) {
						e.printStackTrace();
					}
					extent.endTest(test);
					extent.flush();
				}

				else if (!(result.getStatus() == (ITestResult.SUCCESS)
						|| result.getStatus() == (ITestResult.FAILURE))) {

					try {
						test.log(LogStatus.SKIP, "Skipped Test Case is: " + result.getName() + " => " + getMessage());
						// test.log(LogStatus.SKIP, result.getThrowable());
						captureScreenshot(result);
					} catch (Exception e) {
						e.printStackTrace();
					}
					extent.endTest(test);
					extent.flush();
				}
			}
		}

		if (reportSwitch.equals("1"))

		{
			extent.endTest(test);
			extent.flush();
		} else if (reportSwitch.equals("0") || reportSwitch.equals("2")) {
			document.close();
			extent.endTest(test);
			extent.flush();
		}

		if (exceutionOn.equals("Mobile") && quitBrowser.equals("true")) {
			appiumDriver.quit();
		}

		if (exceutionOn.equals("") && quitBrowser.equals("true")) {
			driver.quit();
		}

		if (exceutionOn.equals("MobileAndWebExecution") && quitBrowser.equals("true")) {
			driver.quit();
		}

		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	/*
	 * @AfterTest public void cleanup(){ try { finalize(); } catch (Throwable e) {
	 * e.printStackTrace(); } Runtime.getRuntime().gc(); System.gc();
	 * 
	 * }
	 */

	@SuppressWarnings("unused")
	@Override
	protected void finalize() throws Throwable {

		WeakReference<LoginPage> logSofRef = new WeakReference<>(loginPage);
		loginPage = null;
		WeakReference<MedAvantePortalPage> dasSofRef = new WeakReference<>(dashBoardPage);
		dashBoardPage = null;
		WeakReference<AdministrationOrganizationPage> adminSofRef = new WeakReference<>(adminstrationOrganizationPage);
		adminstrationOrganizationPage = null;
		WeakReference<AdministrationSystemPage> adSySofRef = new WeakReference<>(adminstrationSystemPage);
		adminstrationSystemPage = null;
		WeakReference<AdministrationStudiesPage> adStSofRef = new WeakReference<>(adminstrationStudiesPage);
		adminstrationStudiesPage = null;
		WeakReference<AdministrationStudiesGeneralPage> adStGeSofRef = new WeakReference<>(
				adminstrationStudyGeneralPage);
		adminstrationStudyGeneralPage = null;
		WeakReference<AdministrationStudiesCustomPage> adStCuuSofRef = new WeakReference<>(
				adminstrationStudiesCustomPage);
		adminstrationStudiesCustomPage = null;
		WeakReference<AdministrationStudiesSitesPage> adStSiSofRef = new WeakReference<>(administrationStudiesSitePage);
		administrationStudiesSitePage = null;
		WeakReference<AdministrationFormsLibraryPage> adStFoLSofRef = new WeakReference<>(adminstrationFormLibraryPage);
		adminstrationFormLibraryPage = null;
		WeakReference<AdministrationStudiesCliniciansPage> adStClSofRef = new WeakReference<>(
				administrationStudiesCliniciansPage);
		administrationStudiesCliniciansPage = null;
		WeakReference<AdministrationStudiesPreQualificationsPage> adStPQSofRef = new WeakReference<>(
				prequalificationPage);
		prequalificationPage = null;
		WeakReference<AdministrationStudiesFormsPage> adStFoSofRef = new WeakReference<>(adminstrationFormsPage);
		adminstrationFormsPage = null;
		WeakReference<AdministrationStudiesAnalyticsPage> adStAnaSofRef = new WeakReference<>(
				adminstrationStudiesAnalytics);
		adminstrationStudiesAnalytics = null;
		WeakReference<StudyDashBoardPage> adStDBSofRef = new WeakReference<>(studyNavigatorDashBoardPage);
		studyNavigatorDashBoardPage = null;
		WeakReference<NewSubjectDetailPage> adSubSofRef = new WeakReference<>(subjectDetailPage);
		subjectDetailPage = null;
		WeakReference<CentralRatingAssessmentsListingPage> cRSofRef = new WeakReference<>(
				centralRatingAssesmentListingPage);
		centralRatingAssesmentListingPage = null;
		WeakReference<CentralRatingAppointmentPage> cRASofRef = new WeakReference<>(appointmentPage);
		appointmentPage = null;
		WeakReference<AssessmentsDetailsPage> asseDPSofRef = new WeakReference<>(assessmentDetailPage);
		assessmentDetailPage = null;
		WeakReference<StudyAssessmentsListing> stAsseSofRef = new WeakReference<>(assessmentListingPage);
		assessmentListingPage = null;
		WeakReference<PreQualificationDashBoardPage> pQDBSofRef = new WeakReference<>(preQualificationDashBoardPage);
		preQualificationDashBoardPage = null;
		WeakReference<CentralRatingAppointmentPage> cRAppSofRef = new WeakReference<>(centralRatingAppointmentPage);
		centralRatingAppointmentPage = null;
		WeakReference<StudySubjectListingPage> stSubSofRef = new WeakReference<>(studySubjectListing);
		studySubjectListing = null;
		WeakReference<VisitDetailsPage> viDPSofRef = new WeakReference<>(visitDetaiLPage);
		visitDetaiLPage = null;
		WeakReference<AnalyticsDashBoardPage> anaDBSofRef = new WeakReference<>(analyticsPage);
		analyticsPage = null;
		WeakReference<RatersDetailsPage> rDPSofRef = new WeakReference<>(ratersDetailsPage);
		ratersDetailsPage = null;
		WeakReference<AdministrationOrganizationGeneralPage> adOGSofRef = new WeakReference<>(
				adminstrationOrganizationGeneralPage);
		adminstrationOrganizationGeneralPage = null;
		WeakReference<AdministrationOrganizationAddressesPage> adOASofRef = new WeakReference<>(
				adminstrationOrganizationAddressPage);
		adminstrationOrganizationAddressPage = null;
		WeakReference<AdministrationOrganizationPeoplePage> adOPSofRef = new WeakReference<>(
				adminstrationOrganizationPeoplePage);
		adminstrationOrganizationPeoplePage = null;
		WeakReference<AdministrationStudiesLanguagesPage> adSLSofRef = new WeakReference<>(adminLanguagePage);
		adminLanguagePage = null;
		WeakReference<AdministrationStudiesCountriesPage> adSCoSofRef = new WeakReference<>(adminCountriesPage);
		adminCountriesPage = null;
		WeakReference<AdministrationStudiesVisitsPage> adSCVSofRef = new WeakReference<>(adminVisitsPage);
		adminVisitsPage = null;
		WeakReference<AdministrationStudiesPeoplePage> adSPSofRef = new WeakReference<>(adminPeoplePage);
		adminPeoplePage = null;
		WeakReference<AdministrationStudiesSitesPage> adSSiSofRef = new WeakReference<>(adminSitesPage);
		adminSitesPage = null;
		WeakReference<AdministrationStudiesQualificationsPage> adStQualRef = new WeakReference<>(
				adminStudQualificationPage);
		adminStudQualificationPage = null;

		WeakReference<AdministrationPeoplePage> adPeoRef = new WeakReference<>(adminstrationPeoplePage);
		adminstrationPeoplePage = null;
		WeakReference<AdministrationPeopleGeneralPage> adPeoGeRef = new WeakReference<>(adminstrationPeopleGeneralPage);
		adminstrationPeopleGeneralPage = null;
		WeakReference<AdministrationPeopleContactInformationPage> adPeoCIRef = new WeakReference<>(
				adminstrationPeopleContactInformationPage);
		adminstrationPeopleContactInformationPage = null;
		WeakReference<AdministrationPeopleOrganizationsPage> adPeoOrgRef = new WeakReference<>(
				adminstrationPeopleOrganizationPage);
		adminstrationPeopleOrganizationPage = null;
		WeakReference<AdministrationPeopleQualificationsPage> adPeoQualRef = new WeakReference<>(
				adminstrationPeopleQualificationPage);
		adminstrationPeopleQualificationPage = null;
		WeakReference<FormManagerPage> fomaRef = new WeakReference<>(
				formManagerPage);
		formManagerPage = null;

		test = null;

		Runtime.getRuntime().runFinalization();
		Runtime.getRuntime().gc();

	}

	public void setReportTest(Method method) {
		System.setProperty("testcaseName", method.getName().toString());
		if (reportSwitch.equals("1") || reportSwitch.equals("2") && !(exceutionOn.equals("Mobile"))) {
			test = extent.startTest(method.getName(), this.getClass().getName());
			test.assignCategory(this.getClass().getSimpleName());
		}
		if (reportSwitch.equals("0") || reportSwitch.equals("2") && !(exceutionOn.equals("Mobile"))) {
			try {
				img = Image.getInstance(IMAGES[0]);
				document = new Document(img);
				PdfWriter.getInstance(document,
						new FileOutputStream(DEST + Utilities.getFileName(method.getName()) + ".pdf"));
				document.open();
			} catch (Exception e) {
			}
		}
		if (reportSwitch.equals("0") || reportSwitch.equals("2") && exceutionOn.equals("Mobile")) {
			test = extent.startTest(method.getName(), this.getClass().getName());
			test.assignCategory(this.getClass().getSimpleName());
			try {
				img = Image.getInstance(IMAGES[0]);
				document = new Document(img);
				PdfWriter.getInstance(document,
						new FileOutputStream(DEST + Utilities.getFileName(method.getName()) + ".pdf"));
				document.open();
			} catch (Exception e) {
			}
		}
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownSuite() throws IOException {
		boolean direExists = false;
		boolean deskdirExists = false;

		File dest = new File(Utilities.getPath() + "/target/");
		File resultSourceFolder = new File(Utilities.getPath() + "/Result");

		if (reportSwitch.equals("1")) {
			extent.flush();
			// Copy report to target directory for CI
			File source = new File(resultPath + "/CustomReport.html");
			if (dest.exists()) {
				try {
					FileUtils.cleanDirectory(dest);
				} catch (Exception e) {

				}

			}

			File ExtentReportsource = new File(resultPath);
			File ExtentReportDest = new File(Utilities.getPath() + "/target/ExtentReports/");
			if (ExtentReportDest.exists()) {
				ExtentReportDest.deleteOnExit();
			}
			if (!(ExtentReportDest.exists())) {
				ExtentReportDest.mkdir();
				direExists = true;
				if (direExists == true) {

					try {
						FileUtils.copyDirectory(ExtentReportsource, ExtentReportDest);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

			/*
			 * try { FileUtils.copyFileToDirectory(source, dest); } catch (IOException e) {
			 * e.printStackTrace(); } try { FileUtils.cleanDirectory(resultSourceFolder); }
			 * catch (Exception e) {
			 * 
			 * }
			 */

		} else if (reportSwitch.equals("0")) {
			document.close();
			File pdfReportsource = new File(DEST);
			File pdfReportDest = new File(Utilities.getPath() + "/target/PDFReports/");
			if (dest.exists()) {
				try {
					FileUtils.cleanDirectory(dest);
				} catch (Exception e) {

				}
			}
			if (pdfReportDest.exists()) {
				pdfReportDest.deleteOnExit();
			}
			if (!(pdfReportDest.exists())) {
				pdfReportDest.mkdir();
				direExists = true;
				if (direExists == true) {
					FileUtils.copyDirectory(pdfReportsource, pdfReportDest);
					FileUtils.cleanDirectory(pdfReportsource);
				}
			}

		} else if (reportSwitch.equals("2")) {
			document.close();
			extent.flush();
			// Copy report to target directory for CI
			File source = new File(resultPath + "/CustomReport.html");

			if (dest.exists()) {
				try {
					FileUtils.cleanDirectory(dest);
				} catch (Exception e) {

				}
			}

			File extentReportsource = new File(resultPath);
			File extentReportDest = new File(Utilities.getPath() + "/target/ExtentReports/");
			if (extentReportDest.exists()) {
				extentReportDest.deleteOnExit();
			}
			if (!(extentReportDest.exists())) {
				extentReportDest.mkdir();
				direExists = true;
				if (direExists == true) {

					try {
						FileUtils.copyDirectory(extentReportsource, extentReportDest);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

			/*
			 * try { FileUtils.copyFileToDirectory(source, dest); //
			 * FileUtils.cleanDirectory(pdfReportsource); } catch (IOException e) {
			 * 
			 * }
			 */

			File pdfReportsource = new File(DEST);
			File pdfReportDest = new File(Utilities.getPath() + "/target/PDFReports/");
			if (pdfReportDest.exists()) {
				pdfReportDest.deleteOnExit();
			}
			if (!(pdfReportDest.exists())) {
				pdfReportDest.mkdir();
				direExists = true;
				if (direExists == true) {
					FileUtils.copyDirectory(pdfReportsource, pdfReportDest);
					FileUtils.cleanDirectory(pdfReportsource);
				}
			}
		}
		String timeStamp = getFormattedTimeStamp().replace("-", "").replace(":", "").replace(".", "").replaceAll(" ",
				"");
		String sourcePath = Utilities.getPath() + "/target/";
		String archivedReportFolder = "/ArchiveReports";
		String archivedReportPath = "/ArchiveReports/ArchivedReports_" + timeStamp;

		File finalReportDest = new File(Utilities.getPath() + archivedReportPath);
		File archiveReportFolder = new File(Utilities.getPath() + archivedReportFolder);
		File sourceDir = new File(sourcePath + "/surefire/");
		try {
			FileUtils.deleteDirectory(sourceDir);
		} catch (Exception e) {

		}

		if (archiveReportFolder.exists())
			try {
				FileUtils.cleanDirectory(archiveReportFolder);
			} catch (Exception e) {

			}

		if (!(finalReportDest.exists())) {
			finalReportDest.mkdirs();
			direExists = true;
			String zipFilePath = Utilities.getPath() + archivedReportPath;
			if (direExists == true) {
				try {
					zipDir(sourcePath, zipFilePath + "/Combined_Reports.zip");
					// FileUtils.cleanDirectory(resultSourceFolder);
				} catch (IOException e2) {

				}
			}

			// Copy Archive Reports on Desktop
			File reportPath_Dekstop = new File(
					System.getProperty("user.home") + "/Desktop/ArchiveReports/MedAvante_Reports/");
			if (!(reportPath_Dekstop.exists())) {
				reportPath_Dekstop.mkdirs();
			}
			try {
				FileUtils.copyDirectory(archiveReportFolder, reportPath_Dekstop);
				// FileUtils.cleanDirectory(ReportPath_Dekstop);
			} catch (IOException e2) {

			}

		}
	}

	public WebDriver getWebDriver() {
		return driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * Handle child windows
	 *
	 * @return: Parent window name
	 * @throws InterruptedException
	 */
	public String switchToChildWindow() throws InterruptedException {
		Thread.sleep(2000);
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iterator = windows.iterator();
		String parent = iterator.next();
		String child = iterator.next();
		getWebDriver().switchTo().window(child);
		return parent;
	}

	/** close child window */
	public void switchParentWindowByClosingChild(String Win) {
		driver.close();
		getWebDriver().switchTo().window(Win);
	}

	/**
	 * Switch Parent window
	 * 
	 * @throws InterruptedException
	 */
	public void switchParentWindow(String Win) throws InterruptedException {
		Thread.sleep(2000);
		getWebDriver().switchTo().window(Win);
	}
	

	/**
	 * Get absolute path
	 *
	 * @return: Absolute path
	 */
	public String getPathUpload() {
		String path;
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("/", "//");
		return path;
	}

	/**
	 * Capturing screenshot once script is failed
	 * 
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws BadElementException
	 */
	public static Image captureScreenshot(ITestResult result)
			throws BadElementException, MalformedURLException, IOException {
		String fileName = System.getProperty("className");
		String screen = "";
		try {
			String screenshotName = Utilities.getFileName(fileName);
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = resultPath + "/" + fileName;
			new File(path).mkdirs();
			screen = path + "/" + "Failed_" + screenshotName + ".png";
			File screenshotLocation = new File(screen);
			FileUtils.copyFile(screenshot, screenshotLocation);
			Thread.sleep(1500);
			InputStream is = new FileInputStream(screenshotLocation);
			byte[] imageBytes = IOUtils.toByteArray(is);
			Thread.sleep(2000);
			String base64 = Base64.getEncoder().encodeToString(imageBytes);

			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(LogStatus.FAIL, "Failed_" + fileName + " \n Snapshot below: "
						+ test.addBase64ScreenShot("data:image/png;base64," + base64));
				test.addScreenCapture(screen);
			}
			if (result.getStatus() == ITestResult.SKIP) {
				test.log(LogStatus.SKIP, "Skipped_" + fileName + " \n Snapshot below: "
						+ test.addBase64ScreenShot("data:image/png;base64," + base64));
				test.addScreenCapture(screen);
			}
			if (!(result.getStatus() == (ITestResult.SUCCESS) || result.getStatus() == (ITestResult.FAILURE))) {
				test.log(LogStatus.SKIP, "Skipped_" + fileName + " \n Snapshot below: "
						+ test.addBase64ScreenShot("data:image/png;base64," + base64));
				test.addScreenCapture(screen);
			}
			imageBytes = null;
			Runtime.getRuntime().gc();
			System.gc();
			base64 = null;
			Runtime.getRuntime().gc();
			System.gc();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return Image.getInstance(screen);

	}

	/**
	 * Capturing screenshot after every step.
	 * 
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws BadElementException
	 */
	public static Image captureScreenshots() throws BadElementException, MalformedURLException, IOException {
		String fileName = System.getProperty("className");
		String screen = "";
		try {
			String screenshotName = Utilities.getFileName(fileName);
			// File screenshot = ((TakesScreenshot)
			// driver).getScreenshotAs(OutputType.FILE);
			// String screen = null;
			String path = resultPath + "/" + fileName;
			new File(path).mkdirs();
			screen = path + "/" + screenshotName + ".png";
			// File screenshotLocation = new File(screen);
			// FileUtils.copyFile(screenshot, screenshotLocation);
			Thread.sleep(500);
			/*
			 * InputStream is = new FileInputStream(screenshotLocation); byte[] imageBytes =
			 * IOUtils.toByteArray(is); Thread.sleep(2000); String base64 =
			 * Base64.getEncoder().encodeToString(imageBytes); test.log(LogStatus.PASS,
			 * fileName + "_" + "\n Snapshot below: " +
			 * test.addBase64ScreenShot("data:image/png;base64," + base64));
			 * test.addScreenCapture(screen); Reporter.log( "<a href= '" + screen +
			 * "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
			 */

			// test.log(LogStatus.PASS,
			// screen= test.addScreenCapture(fileName + "/" + screenshotName +
			// ".png"));

			// FileUtils.cleanDirectory(screenshotLocation);

			// Capture whole screen function
			screen = MobileScreenRecorder.captureScreenCasts();
			Runtime.getRuntime().gc();
			System.gc();
		} catch (Exception e) {
		}
		return Image.getInstance(screen);
	}

	/**
	 * Report logs
	 *
	 * @param :
	 *            message
	 * @throws DocumentException
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws BadElementException
	 */
	public void reportLog(String msg) {
		message = msg;
		if (reportSwitch.equals("1") || reportSwitch.equals("2") || exceutionOn.equals("Mobile")
				|| exceutionOn.equals("MobileAndWebExecution") || exceutionOn.equals("")) {
			/*
			 * if (captureScreenshotSwitch.equalsIgnoreCase("true")) { try { } catch
			 * (Exception e) { eprintStackTrace(); } }
			 */
			test.log(LogStatus.INFO, message);
		}
		message = BREAK_LINE + message;
		logger.info("Message: " + message);
		Reporter.log(message);
	}

	static String message = "";

	public static String getMessage() {
		return message;
	}

	/**
	 * @function : PDF reporting function
	 * @param :
	 *            moduleName, testCseName, step, imagePath
	 * 
	 */

	public static void addPdfRow(String moduleName, String testCaseName, String step, Image imgPath)
			throws IOException, DocumentException {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 3 });
		table.addCell(createTextCell("ModuleName: " + moduleName + "\nTestName:" + testCaseName + "\nTest Step: " + step
				+ "\nTime Stamp: " + timestamp));
		table.addCell(createImageCell(imgPath));
		document.add(table);

	}

	public static void pdfMobileScreenShotWriter()
			throws DocumentException, IOException, MalformedURLException, java.io.IOException, InterruptedException {
		String moduleName = System.getProperty("className");
		/* String testcaseName = System.getProperty("moduleName"); */
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 3 });
		table.addCell(createTextCell("ModuleName: " + moduleName + "\nTestName:" + System.getProperty("testcaseName")
				+ "\nTest Step: " + getMessage() + "\nTime Stamp: " + timestamp));
		table.addCell(createImageCell(MobileScreenRecorder.imgPath));
		document.add(table);
	}
	
	public static void pdfMobileScreenShotWriter(String Scrrenshotpath)
			throws DocumentException, IOException, MalformedURLException, java.io.IOException, InterruptedException {
		String moduleName = System.getProperty("className");
		/* String testcaseName = System.getProperty("moduleName"); */
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 3 });
		table.addCell(createTextCell("ModuleName: " + moduleName + "\nTestName:" + System.getProperty("testcaseName")
				+ "\nTest Step: " + getMessage() + "\nTime Stamp: " + timestamp));
		//table.addCell("   ");
		table.addCell(createImageCell(Scrrenshotpath));
		document.add(table);
	}

	/**
	 * function : Fetch the System's current date with time
	 * 
	 */
	public String getTimeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime()).toString().replace(" ", "");
	}

	/**
	 * @return
	 * @function: Get formatted Time stamp
	 * 
	 */
	public String getFormattedTimeStamp() {

		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-uuuu HH:mm:ss");
		String formatDateTime = currentTime.format(formatter);
		return formatDateTime;

	}

	/**
	 * @author Mrinalm
	 * @return
	 * @throws ParseException
	 * @function: Conversion Of Date Format from yyyy/MM/d to dd-MMM-yyyy
	 * 
	 */
	public String conversionOfDateFormat(String sDate1) throws ParseException {
		SimpleDateFormat fr = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
		Date d = fr.parse(sDate1);
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		String formatDateTime = format.format(d);
		return formatDateTime;
	}

	/**
	 * function : Creating Image Cell into the PDF files
	 * 
	 */

	public static PdfPCell createImageCell(Image image) throws DocumentException, IOException {
		return new PdfPCell(Image.getInstance(image), true);
	}

	public static PdfPCell createImageCell(String string)
			throws DocumentException, IOException, MalformedURLException, java.io.IOException {
		return new PdfPCell(Image.getInstance(string), true);
	}

	/**
	 * function : Creating cell for text to be logged into the PDF file
	 * 
	 */

	public static PdfPCell createTextCell(String text) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph(text);
		p.getFont().setSize(20);
		p.setAlignment(Element.ALIGN_LEFT);
		cell.addElement(p);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(Rectangle.BOX);
		return cell;
	}

	/**
	 * Capturing image with hilighting the locators
	 * 
	 */

	/**
	 * Function: Get current date.
	 * 
	 * @return
	 */
	public String currentDate() {
		final DateFormat format = new SimpleDateFormat("dd-MMM-YYYY");
		Date date = new Date();
		final String currentDate = format.format(date);
		return currentDate;
	}
	
	public String currentDateWithMonthName() {
		String currentdate=currentDate();
		return currentdate.toUpperCase();
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
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
		String strDate = dateFormat.format(tomorrow);
		return strDate;

	}

	/**
	 * Generate Random Numeric String of length
	 */
	public static String GenerateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0
				: new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
						+ (int) Math.pow(10, charLength - 1));
	}

	/**
	 * Function: Get random string
	 * 
	 * @param lettersNum
	 * @return
	 */
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
	 * Generate AlphanumericString of length
	 */
	public String generateRandomAlphanumericString(int lenthOfString) {
		String generatedString = RandomStringUtils.randomAlphanumeric(lenthOfString);
		return generatedString;
	}

	/**
	 * Function: Get random integer
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

	/**
	 * Compressing Reports folder in a compressed Zip file for archiving reports
	 * 
	 */

	public void zipDir(String dirName, String nameZipFile) throws IOException {
		ZipOutputStream zip = null;
		FileOutputStream fW = null;
		fW = new FileOutputStream(nameZipFile);
		zip = new ZipOutputStream(fW);
		addFolderToZip("", dirName, zip);
		zip.close();
		fW.close();
	}

	public void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws IOException {
		File folder = new File(srcFolder);
		if (folder.list().length == 0) {
			addFileToZip(path, srcFolder, zip, true);
		} else {
			for (String fileName : folder.list()) {
				if (path.equals("")) {
					addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip, false);
				} else {
					addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip, false);
				}
			}
		}
	}

	/**
	 * Get Past week start and end dates
	 */
	public ArrayList<String> pastWeekStartAndEndDates() {
		ArrayList<String> listToBeVerified = new ArrayList<>();
		ZoneId US = ZoneId.of("America/New_York");
		final ZonedDateTime input = ZonedDateTime.now(US);
		final ZonedDateTime startOfLastWeek = input.minusWeeks(1).with(DayOfWeek.MONDAY);
		listToBeVerified.add(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(startOfLastWeek));
		final ZonedDateTime endOfLastWeek = startOfLastWeek.plusDays(6);
		listToBeVerified.add(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(endOfLastWeek));
		return listToBeVerified;
	}

	public void addFileToZip(String path, String srcFile, ZipOutputStream zip, boolean flag) throws IOException {
		File folder = new File(srcFile);
		if (flag) {
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName() + "/"));
		} else {
			if (folder.isDirectory()) {
				addFolderToZip(path, srcFile, zip);
			} else {
				byte[] buf = new byte[1024];
				int len;
				FileInputStream in = new FileInputStream(srcFile);
				zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
				while ((len = in.read(buf)) > 0) {
					zip.write(buf, 0, len);
				}
			}
		}
	}

}