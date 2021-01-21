package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1140_AddSubjectRegistrationCodeGeneraFlow_SIP extends BaseTest {

	 protected String noteText="AUTO2385"+generateRandomString(4),screeningNumber="Subject2385"+generateRandomString(3),visitName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1140_AddSubjectRegistrationCodeGeneraFlow_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyCRStatus");
		visitName=properties.getProperty("InProgressVisit");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1140 Test Case Name: Add Subject - Registration Code:
	 * General flow
	 *  
	 * ====================================================================================================================
	 */

	
	@Test(description = "FP-TC-1140_Add Subject - Registration Code:General flow", groups = { " " })
	public void FPTC_1140_verifyAddSubjectRegistrationCodeGeneralFlow() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("2.2: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);
		//studyNavigatorDashBoardPage.selectSite(AT_PRODSiteCoordinatorUserName);

		reportLog("2.3: Option to add subject is available");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("3.1:Click on add Subject by selecting the site of Pr#1");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("3.2:New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("3.3:Required fields are highlighted​");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("4.1 Enter not all required subject data​");
		studyNavigatorDashBoardPage.inputNotes(noteText);
		
		reportLog("4.2:Data are entered ");
		studyNavigatorDashBoardPage.verifyNotesTextIsEntered(noteText);
		
		reportLog("4.3 Not filled required fields stay highlighted ");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("4.4:Save option is not enabled");
		studyNavigatorDashBoardPage.verifySaveButtonIsDisabled();

		reportLog("5.1:Enter required subject data and click on 'Save'");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNumber);
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("5.2:Data entered into subject entry form are validated ");
		studyNavigatorDashBoardPage.verifyScreeningNumIsEntered(screeningNumber);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("5.3: New subject record is created ");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNumber);

		reportLog("5.4:List of available visits are displayed along with option to add subject visit ");
		subjectDetailPage.verifyVisitGridDisplayed();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.verifyAddVisitIconIsDisplayed();

		reportLog("5.5:Option to edit subject details is available");
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();

		reportLog("6.2:QR icon is displayed in Reported Outcomes section");
		subjectDetailPage.verifyReportedOutComeQRCodeForMobileSubjectRegistrationDialogIsDisplayed();
		
		reportLog("6.1:At the Reported Outcomes section select action to open Registration info -> Click on QR icon");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();

		reportLog(
				"6.3: Values is Non Editable and Subject Registration pop-up is opened and contains the following information:Registration Code,Control to print Code,Device History enabled,Activate/Deactivate controls,Values are non-editable ");
		subjectDetailPage.verifySubjectRegistrationDialogPoUpIsOpened();
		subjectDetailPage.verifyInformationInSubjectRegistratiOnPopUp();
		subjectDetailPage.checkValuesNonEditableInSubjectRegistrationPopUp();
		
		reportLog("7.1:	Select action to close pop-up via Close control");
		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();
		
		reportLog("7.2:	Subject Registration pop-up is closed");
		subjectDetailPage.verifyNewSubjectDetailPage();
			
		reportLog("8.1:	Click on QR icon");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();

		reportLog("8.2:	Subject Registration pop-up is opened");
		subjectDetailPage.verifySubjectRegistrationDialogPoUpIsOpened();

		reportLog("9.1:Select action to close pop-up via cross 'X' control");
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();
		
		reportLog("9.2:Subject Registration pop-up is closed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("9.3: User Logout the application");
		loginPage.logoutApplication();

		reportLog("9.4: Verify User Logout the application");
		loginPage.verifyUserLogout();

	}
}