package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1143_SubjectDetails_Subject_DeviceRegistrationHistory_MAP extends BaseTest {

	private String subjectRegistered, subjectUnRegistered;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1143_SubjectDetails_Subject_DeviceRegistrationHistory_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectRegistered = properties.getProperty("SubjectRegisteredWithDevice");
		subjectUnRegistered = properties.getProperty("SubjectNotRegisteredWithDevice");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1143 Test Case Name: Subject Details - Subject:
	 * Device Registration History
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1143 : Subject Details - Subject: Device Registration History ", groups = { "" })

	public void FPTC_1143_SubjectDetails_Subject_DeviceRegistrationHistory() throws Exception {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager, AT_Password);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("3.1:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectUnRegistered);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectUnRegistered);

		reportLog("3.2:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("4.1: Click on QR icon to open Subject Registration info");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();

		reportLog("4.2: Verify Registration dialog PopUp Displayed");
		subjectDetailPage.verifySubjectRegistrationDialogPoUpIsOpened();

		reportLog("4.3: Verify Information text that there is no device in use displayed");
		subjectDetailPage.verifySubjectNotRegisteredWithAnyDevice();

		reportLog("4.4: Click on Cancel button to close Subject Registration info");
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();

		reportLog("5.1: Navigate back to study navigator");
		subjectDetailPage.navigateBack();

		reportLog("5.2: Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectRegistered);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectRegistered);

		reportLog("5.3 :Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("6.1: Click on QR icon to open Subject Registration info");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();

		reportLog("6.2: Verify Registration dialog PopUp Displayed");
		subjectDetailPage.verifySubjectRegistrationDialogPoUpIsOpened();

		reportLog("6.3: Verify device is activated for registered Subject in device history");
		subjectDetailPage.verifyActivatedDeviceHistoryForRegisteredSubject();

		reportLog("6.4: Verify Date of the Activated device was registered in Format: DD-MMM-YYYY ");
		subjectDetailPage.verifyDateFormatOfActivatedDevice(Constants.Recipient_MessageModelWindow_Subject,
				Constants.datePattern_DDMMMYYYY);

		reportLog("6.5: Verify Date of the De-Activated device was registered in Format: DD-MMM-YYYY ");
		subjectDetailPage.verifyDateFormatOfDeActivatedDevice(Constants.StudyDashBoard_columnName_Subject,
				Constants.datePattern_DDMMMYYYY);

		reportLog("6.6: Verify Reason for change displayed under De-activated device history");
		subjectDetailPage.verifyDeActivatedDeviceHistoryWithReasonForChangeDisplayed(
				Constants.StudyDashBoard_columnName_Subject);

		reportLog("6.7: Verify e-signature displayed under De-activated device history");
		subjectDetailPage
				.verifyDeActivatedDeviceHistoryWithEsignatureDisplayed(Constants.StudyDashBoard_columnName_Subject);

		reportLog("6.8: Verify Device History displayed in descending order from last activated device");
		subjectDetailPage.verifyDeviceHistoryDisplayedInDescendingOrder(Constants.StudyDashBoard_columnName_Subject);

		reportLog("6.9: Verify option to deactivate the device is displayed");
		subjectDetailPage.verifyDeactivateDeviceButtonDisplayedForRegisteredSubject();

		reportLog("7.1: Click on option to deactivate the device");
		subjectDetailPage.clickOnDeactivateDeviceButtonForRegisteredSubject();

		reportLog("7.2: Verify Reason For Change E-Sign pop-up is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("7.3: Select Cancel button on Reason For Change E-Sign pop-up");
		subjectDetailPage.clickOnReasonForChangeCancelBTN();

		reportLog("7.4: Click on Cancel button to close Subject Registration info");
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();

		reportLog("7.5: Logout application");
		loginPage.logoutApplication();

		reportLog("7.6: Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
