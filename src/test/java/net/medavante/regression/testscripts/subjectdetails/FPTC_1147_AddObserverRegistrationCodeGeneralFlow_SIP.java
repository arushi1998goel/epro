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

public class FPTC_1147_AddObserverRegistrationCodeGeneralFlow_SIP extends BaseTest {

	protected String observerName, observerAlias = "Auto" + generateRandomString(3),
			observerRelation1, observerNameInDropDown;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1147_AddObserverRegistrationCodeGeneralFlow_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("studyForEnrolledStatus");
		subjectName=properties.getProperty("Subject_2385");
		observerRelation1 = properties.getProperty("Auto_Observer_Relation1");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1147 Test Case Name:Add Observer - Registration
	 * Code: General flow
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1147_Add Observer - Registration Code:General flow", groups = { " " })

	public void FPTC_1147_verifyAddObserverRegistrationCodeGeneralFlow() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.1: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2.2: Verify Subject Listing Page is displayed");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("3.1:Select Subject Pr#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("3.2:Verify New Subject Detail Page");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.3:Action for Edit Reported Outcomes is displayed");
		subjectDetailPage.verifyReportedOutComeButtonIsDisplayed();

		reportLog("4.1:Click On Reported Outcome Edit Button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("4.2:Reported Outcome Details screen is displayed");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("4.3:Action for Add Observers is displayed");
		subjectDetailPage.verifyAddObserverBtnIsDisplayed();

		reportLog("5.1:Select action to add Observer");
		subjectDetailPage.clickOnAddObserverBtN();

		reportLog("5.2:New Observer entry screen is displayed");
		subjectDetailPage.verifyObserverRelationAnAliasFieldsAreRequired();
		
		reportLog("6.1:Enter required Observer data and click on 'Save' control");
		subjectDetailPage.inputObserverRelationName(observerRelation1);
		subjectDetailPage.inputObserverAliasName(observerAlias);
		subjectDetailPage.clickOnObserverSaveBTN();
		observerNameInDropDown = observerRelation1 + " " + "(" + observerAlias + ")";

		reportLog("6.2:New Observer record is created ");
		subjectDetailPage.verifyObserverInformation(observerRelation1, Constants.observerDeactiveText, "No");

		reportLog("6.3:Option to edit Observer details is available");
		subjectDetailPage.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(observerRelation1);

		reportLog("7.1:Set the Observer from Step#6 in Mobile PRO section ");
		subjectDetailPage.selectMobileProObserverEnabledOption();
 		subjectDetailPage.selectMobileProObserver(observerNameInDropDown);
 		
		reportLog("7.2:Save changes");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODSiteCoordinator, AT_Password);
		
		reportLog("7.3:Reported Outcome Details screen is closed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("7.4:QR code icon is displayed in Reported Outcomes section");
		subjectDetailPage.verifyQrMobileObserverIcon();

		reportLog(
				"8.1:At the Reported Outcomes section select action to open Observer Registration info -> Click on QR icon");
		subjectDetailPage.clickOnMobileObserverQrIcon();

		reportLog("8.2:Observer Registration pop-up is opened and contains the information");
		subjectDetailPage.verifyObserverRegistrationDialogPoUpIsOpened();
		subjectDetailPage.verifyInformationInObserverRegistratiOnPopUp();

		reportLog("9.1:Select action to close pop-up via Close control");
		subjectDetailPage.clickOnObserverRegistrationPopUpCloseButton();

		reportLog("9.2:Observer Registration pop-up is closed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("10.1:Click on QR icon for Observer again");
		subjectDetailPage.clickOnMobileObserverQrIcon();

		reportLog("10.2:Observer Registration pop-up is opened");
		subjectDetailPage.verifyObserverRegistrationDialogPoUpIsOpened();

		reportLog("11.1:Select action to close pop-up via cross 'X' control");
		subjectDetailPage.clickOnObserverResigtrationCrossControl();

		reportLog("11.2:Observer Registration pop-up is closed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("11.3: User logout from the application");
		loginPage.logoutApplication();

		reportLog("11.4: Verify User logout from the application");
		loginPage.verifyUserLogout();

	}
}
