package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1276_ReasonsForChangeForDeactivatingObserverDevice_MAP extends BaseTest {

	private String subjectRegistered;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1276_ReasonsForChangeForDeactivatingObserverDevice_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectRegistered = properties.getProperty("SubjectRegisteredWithDevice");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1276 Test Case Name: Reasons for change for
	 * deactivating Observer's device
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1276 : Reasons for change for deactivating Observer's device ", groups = { "" })

	public void FPTC_1276_ReasonsForChangeForDeactivatingObserverDevice() throws Exception {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager, AT_Password);

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

		reportLog("3.4: Verify option to deactivate the device is displayed");
		subjectDetailPage.verifyDeactivateDeviceButtonDisplayedForRegisteredObserver();

		reportLog("4.1: Click on option to deactivate the device");
		subjectDetailPage.clickOnDeactivateDeviceButtonForRegisteredObserver();

		reportLog("4.2: Verify Reason For Change E-Sign pop-up is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("4.3: Verify list of reasons are available -" + Constants.reasonsForChangeDeactivateDevice);
		subjectDetailPage.verifyReasonForChangeOption(Constants.reasonsForChangeDeactivateDevice);

		reportLog("4.4: Select Cancel button on Reason For Change E-Sign pop-up");
		subjectDetailPage.clickOnReasonForChangeCancelBTN();

		reportLog("4.5: Click on Cancel button to close Observer Registration info");
		subjectDetailPage.clickOnObserverResigtrationCrossControl();

		reportLog("4.6: Logout application");
		loginPage.logoutApplication();

		reportLog("4.7: Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
