package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1278_PossibilityToDeactivateDeviceForObserver_SIP extends BaseTest {

	private String subjectRegistered;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1278_PossibilityToDeactivateDeviceForObserver_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectRegistered = properties.getProperty("SubjectRegisteredWithDevice");

		// **************************************************
		// Need mobile interaction to activate mobile device for observer before
		// every run.
		// **************************************************

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1278 Test Case Name: Possibility to Deactivate
	 * Device for Observer
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1278 : Possibility to Deactivate Device for Observer ", groups = { "" })

	public void FPTC_1278_PossibilityToDeactivateDeviceForObserver() throws Exception {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("2.3:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectRegistered);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectRegistered);

		reportLog("2.4:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.5:Verify QR code icon is displayed for Observer in Reported Outcomes section");
		subjectDetailPage.verifyQrMobileObserverIcon();

		reportLog("3.1: Click on QR icon to open Observer Registration info");
		subjectDetailPage.clickOnMobileObserverQrIcon();

		reportLog("3.2: Verify Registration dialog PopUp Displayed");
		subjectDetailPage.verifyObserverRegistrationDialogPoUpIsOpened();

		reportLog("3.3: Verify device is activated for registered Observer in device history");
		subjectDetailPage.verifyActivatedDeviceHistoryForRegisteredObserver();

		reportLog("3.4: Verify option to deactivate the device is not displayed");
		subjectDetailPage.verifyDeactivateDeviceButtonNotDisplayedForRegisteredObserver();

		reportLog("4.1: Click on Cancel button to close Observer Registration info");
		subjectDetailPage.clickOnObserverResigtrationCrossControl();

		reportLog("4.2: Logout application");
		loginPage.logoutApplication();

		reportLog("4.3: Verify user is logout");
		loginPage.verifyUserLogout();

		reportLog("4.4: Login in to application");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectManager, AT_Password);

		reportLog("5.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("5.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("5.3:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectRegistered);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectRegistered);

		reportLog("5.4:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("5.5:Verify QR code icon is displayed for Observer in Reported Outcomes section");
		subjectDetailPage.verifyQrMobileObserverIcon();

		reportLog("6.1: Click on QR icon to open Observer Registration info");
		subjectDetailPage.clickOnMobileObserverQrIcon();

		reportLog("6.2: Verify Registration dialog PopUp Displayed");
		subjectDetailPage.verifyObserverRegistrationDialogPoUpIsOpened();

		reportLog("6.3: Verify device is activated for registered Observer in device history");
		subjectDetailPage.verifyActivatedDeviceHistoryForRegisteredObserver();

		reportLog("6.4: Verify option to deactivate the device is displayed");
		subjectDetailPage.verifyDeactivateDeviceButtonDisplayedForRegisteredObserver();

		reportLog("7.1: Click on option to deactivate the device");
		subjectDetailPage.clickOnDeactivateDeviceButtonForRegisteredObserver();

		reportLog("7.2: Verify Reason For Change E-Sign pop-up is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("7.3: Select Reason for change option");
		subjectDetailPage.selectReasonForChangeOption(Constants.reasonsForChangeDeactivateDevice.get(0));

		reportLog("7.4: Provide credentials and confirm");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODProjectManager, AT_Password);

		reportLog("7.5: Verify Device is deactivated");
		subjectDetailPage.verifyActivatedDeviceNotAvailableInDeviceHistory(Constants.ProductType_Observer);

		reportLog("7.6: Click on Cancel button to close Observer Registration info");
		subjectDetailPage.clickOnObserverResigtrationCrossControl();

		reportLog("7.7: Logout application");
		loginPage.logoutApplication();

		reportLog("7.8: Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
