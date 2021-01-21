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
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class FPTC_1229_SystemDoesntAllowCreatingDuplicateSubjectNumber_SIP extends BaseTest {

	private String subjectWithRandomNumber, subjectWithScreeningNumber, subjectWithTempId;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public  FPTC_1229_SystemDoesntAllowCreatingDuplicateSubjectNumber_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyConfiguredWithBirth");
		subjectWithRandomNumber = properties.getProperty("SubjectWithRandomNumber");
		subjectWithScreeningNumber = properties.getProperty("SubjectWithScreeningNumber");
		subjectWithTempId = properties.getProperty("SubjectWithTempId");

	}

	
	/**
	 * ==========================================================================================================================
	 * Test Case Id: FP-TC-1229 Test Case Name: System doesn't allow creating
	 * duplicate subject number using the same attribute
	 * 
	 * ==========================================================================================================================
	 */

	@Test(description = "FP-TC-1229_System doesn't allow creating duplicate subject number using the same attribute", groups = {
			" " })

	public void FPTC_1229_verifySystemDoesntAllowCreatingDuplicateSubjectNumberUsingTheSameAttribute() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.4:Subject List page is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("1.5:Option to add new subject is visible ");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("1.6:Pr#3, Pr#4 and Pr#5 are visible");
		studyNavigatorDashBoardPage.verifySubjectIsDisplayedInSubjectList(subjectWithScreeningNumber);
		studyNavigatorDashBoardPage.verifySubjectIsDisplayedInSubjectList(subjectWithRandomNumber);
		studyNavigatorDashBoardPage.verifySubjectIsDisplayedInSubjectList(subjectWithTempId);

		reportLog("2.1:Edit the Subject of Pr#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,subjectWithScreeningNumber);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectWithScreeningNumber);
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("2.3:Add Randomization# = Randomization# of Pr#4");
		subjectDetailPage.inputRandomNumber(subjectWithRandomNumber);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("2.4:Subject can't be updated System displays message indicating that the subject with same name already exists");
		subjectDetailPage.verifyErrorMessage(SubjectsModuleConstants.editDuplicaterandomizationErrorMessage);
		subjectDetailPage.closeErrorMessage();
		subjectDetailPage.clickOnCancelBtn();
		subjectDetailPage.navigateBack();

		reportLog("3.1:Edit the Subject of Pr#4");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,subjectWithRandomNumber);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectWithRandomNumber);
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("3.2:Add Screening# = Screening# of Pr#3");
		subjectDetailPage.clearScreeningInp();
		subjectDetailPage.inputScreeningNumber(Constants.ScreeningNumber);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("3.3:Subject can't be updated System displays message indicating that the subject with same name already exists");
		subjectDetailPage.verifyErrorMessage(SubjectsModuleConstants.editDuplicateScreeningErrorMessage);
		subjectDetailPage.closeErrorMessage();
		subjectDetailPage.clickOnCancelBtn();
		
		reportLog("4.1:Edit the Subject of Pr#4 and add Temporary ID = Temporary ID of Pr#5");
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.clearScreeningInp();
		subjectDetailPage.inputTemporaryID(Constants.TempIDNumber);
		subjectDetailPage.clickOnSaveBtn();
		
		reportLog("4.2:Subject can't be updated System displays message indicating that the subject with same name already exists");
		subjectDetailPage.verifyErrorMessage(SubjectsModuleConstants.editDuplicateTemporaryIDErrorMessage);
		subjectDetailPage.closeErrorMessage();
		subjectDetailPage.clickOnCancelBtn();
		subjectDetailPage.navigateBack();

		reportLog("5.1:Edit the Subject of Pr#5 and add Screening# = Screening# of Pr#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,subjectWithTempId);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectWithTempId);
		subjectDetailPage.clickOnSubjectEdtingIcon();
		subjectDetailPage.inputScreeningNumber(Constants.ScreeningNumber);
		subjectDetailPage.clickOnSaveBtn();

		reportLog("5.2:Subject can't be updated System displays message indicating that the subject with same name already exists");
		subjectDetailPage.verifyErrorMessage(SubjectsModuleConstants.editDuplicateScreeningErrorMessage);
		subjectDetailPage.closeErrorMessage();
		subjectDetailPage.clickOnCancelBtn();
		subjectDetailPage.navigateBack();

		reportLog("6.1:Select the option to add new subject");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("6.2:Add new subject screen is visible");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("6.3:Screening#, Randomization# and TemporaryID fields are visible");
		studyNavigatorDashBoardPage.verifyScreeningNumberRandomizationTempIdIsDisplayed();

		reportLog("7.1:	Try to create the subject with : - Temporary ID = Temporary ID of Pr#5");
		studyNavigatorDashBoardPage.inputTemporaryID(Constants.TempIDNumber);
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("7.2:Subject can't be updated System displays message indicating that the subject with same name already exists");
		studyNavigatorDashBoardPage.verifyHeaderErrorText(SubjectsModuleConstants.newDuplicateTemporaryIdErrorMessage);
		studyNavigatorDashBoardPage.closeHeaderErrorMessage();
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("8.1:	Repeat Step#7 with Temporary ID = Screening Number of Pr#3");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		studyNavigatorDashBoardPage.inputTemporaryID(Constants.ScreeningNumber);
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("8.2:Subject can be created");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Setting up The Pre-Requisite");
		subjectDetailPage.deleteSubject();

		reportLog("9.1:	Repeat Step#7 with Screening Number = Screening Number of Pr#3");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		studyNavigatorDashBoardPage.inputScreeningNum(Constants.ScreeningNumber);
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("9.2:Subject can't be created - System displays message indicating that the subject with same name already exists");
		studyNavigatorDashBoardPage.verifyHeaderErrorText(SubjectsModuleConstants.newDuplicateScreeningErrorMessage);
		studyNavigatorDashBoardPage.closeHeaderErrorMessage();
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("10.1:Repeat Step#7 with Screening Number = Temporary ID of Pr#5");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		studyNavigatorDashBoardPage.inputScreeningNum(Constants.TempIDNumber);
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("10.2:Subject can be created");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Setting up The Pre-Requisite");
		subjectDetailPage.deleteSubject();

		reportLog("11.1:Repeat Step#7 with Randomization Number = Randomization Number of Pr#4");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		studyNavigatorDashBoardPage.inputScreeningNum("675");
		studyNavigatorDashBoardPage.inputRandomizationNum(subjectWithRandomNumber);
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("11.2:Subject can't be created - System displays message indicating that the subject with same name already exists");
		studyNavigatorDashBoardPage.verifyHeaderErrorText(SubjectsModuleConstants.newDuplicaterandomizationErrorMessage);
		studyNavigatorDashBoardPage.closeHeaderErrorMessage();
 		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("12.1:Repeat Step#7 with Randomization Number = Screening Number of Pr#5");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);
		studyNavigatorDashBoardPage.inputRandomizationNum(subjectWithScreeningNumber);
		studyNavigatorDashBoardPage.inputScreeningNum("890");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("12.2:Subject can be created");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Setting up The Pre-Requisite");
		subjectDetailPage.deleteSubject();

		reportLog("12.3: Logout from the application");
		loginPage.logoutApplication();

		reportLog("12.4: Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
