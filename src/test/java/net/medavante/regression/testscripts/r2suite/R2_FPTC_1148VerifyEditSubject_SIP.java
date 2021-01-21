package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;
import org.testng.annotations.*;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1148VerifyEditSubject_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1148VerifyEditSubject_SIP(String browser) {
		super(browser);
	}

	private String  studyName; 

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");


	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1148 Test Case Name: Edit Subject
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FPTC_1148 Edit Subject ", groups = { "R2" })
	public void FPTC_1148_verifyEditSubject() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Select " + studyName + " from study list");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("1.4: Create subject to configure the test case prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("1.5: Verify subject detail page is displayed with " + screeningNum + " number");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("2.1: Click on Edit Subject option");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("2.2: Verify subject editing popup is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("2.3: Verify Screening number is displayed and editable");
		subjectDetailPage.verifyScreeningNumIsDisplayedAndEditable();

		reportLog("2.4: Verify subject language field is displayed and editable");
		subjectDetailPage.verifyEditSubjectLanguageInputIsDisplayedAndEditable();

		reportLog("3.1: Update the screening num as " + screeningNum + " to modify the data");
		subjectDetailPage.inputScreeningNumber(screeningNum);

		reportLog("3.2: Verify data is entered");
		subjectDetailPage.verifyScreeningNumIsEntered(screeningNum);

		reportLog("3.3: Verify Options to save is available.");
		subjectDetailPage.verifyEditSubjectSaveButtonIsDisplayed();

		reportLog("3.4: Verify Options to cancel is available.");
		subjectDetailPage.verifyEditSubjectCancelButtonIsDisplayed();

		reportLog("4.1 Click on Cancel option");
		subjectDetailPage.clickOnCancelBtn();

		reportLog("4.2 Verify Edit screen is closed");
		subjectDetailPage.verifySubjectEdtingPopUpIsNotDisplayed();

		reportLog("4.3 Verify subject detail page is displayed without appying changes");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("5.1 Click on Edit Subject option again");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("5.2 Verify subject editing popup is displayed");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("5.3 Verify Screening number is displayed and editable");
		subjectDetailPage.verifyScreeningNumIsDisplayedAndEditable();

		reportLog("5.4 Verify subject language field is displayed and editable");
		subjectDetailPage.verifyEditSubjectLanguageInputIsDisplayedAndEditable();

		reportLog("6.1 Clear Screening# field");
		subjectDetailPage.clearScreeningInp();

		reportLog("6.2 verify Screening field is required");
		subjectDetailPage.verifyScreeningNumIsRequired();

		reportLog("6.3 verify save button is disabled");
		subjectDetailPage.verifyEditSubjectSaveButtonIsDisabled();

		reportLog("7.1 Input " + screeningNum + " screeningNum");
		subjectDetailPage.inputScreeningNumber(screeningNum);

		reportLog("7.2 click on save button to update and modify the changes");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("7.3 Select "+ Constants.Subject_Reason_For_Change+ " reason to modify the changes");
		subjectDetailPage.selectReasonForChangeOption(Constants.Subject_Reason_For_Change);

		reportLog("7.4 Esign to update the reason");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("7.5 Verify screeng field updated to "+ screeningNum);
		subjectDetailPage.verifyScreeningNumIsEntered(screeningNum);

		reportLog("7.6 Logout from the application");
		loginPage.logoutApplication();

		reportLog("7.7 Verify user is logout");
		loginPage.verifyUserLogout();

	}
	
	/*@AfterMethod
	public void updateEditedSubjectValue(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			Configuration.updatePropertyTestData("RegressionTestData", "AutomationSubjectName",screeningNum);
		}else {
			Log.error(screeningNum + " subject is not edited for " + studyName+ " study.");
		}
	}*/
}
