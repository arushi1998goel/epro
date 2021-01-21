package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1434_StudyNavigatorQuestionnairesList_SIP extends BaseTest {

	private String dateVar="13-AUG-2018 2:30AM",actualTime="0.8",ExpectedTime="5";
	

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1434_StudyNavigatorQuestionnairesList_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		subjectName = properties.getProperty("SubjectEventName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1434 Test Case Name:StudyNavigator Questionnaires List
	 * ====================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1434_StudyNavigator Questionnaires List", groups = {})
	public void FPTC_1434_VerifyStudyNavigatorQuestionnairesList() {

		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigat"
				+ "e to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("2.2:Study PR#1");
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);

		reportLog("2.3:	Study Navigator for Study PR#1 is displayed with expanded Left side panel");
		studyNavigatorDashBoardPage.verifyExtendedSidePanelIsDisplayedWithMenuIconAndTiles();

		reportLog("3.1:Select Questionnaires menu item");
		studyNavigatorDashBoardPage.selectStudyMenuItemAndNavigateToListing(Constants.StudyDashBoard_columnName_Questionnaires_MenuItem);

		reportLog("3.2:Selected menu item is displayed with its filter and list blocks");
		studyNavigatorDashBoardPage.verifySelectedMenuItemDisplayedWithFilter();
		
		reportLog("4.1:Check the 'Filter by' block");
		studyNavigatorDashBoardPage.verifyFilterByBlockDisplayed();

		reportLog("4.2:	'Filter by' block is displayed with the following items: Item title,");
		studyNavigatorDashBoardPage.verifyItemTitle(Constants.Dates_FilterBy);
		studyNavigatorDashBoardPage.verifyItemTitle(Constants.Other_FilterBy);

		reportLog("4.3:'All' filter with its value selected by default");
		studyNavigatorDashBoardPage.verifyMenuItemFilterSelected(Constants.CategoryFilter_All);
		
		reportLog("4.4:'Today' filter with its value ");
		studyNavigatorDashBoardPage.verifyMenuItemFiltersDisplayedWithValues(Constants.CategoryFilter_Today,"0");

		reportLog("4.5:'Past Week' filter with its value");
		studyNavigatorDashBoardPage.verifyMenuItemFiltersDisplayedWithValues(Constants.CategoryFilter_PastWeek,"0");

		reportLog("5.1:Select Today radio-button");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.CategoryFilter_Today);

		reportLog("5.2:'Today' radio-button is selected");
		studyNavigatorDashBoardPage.verifyMenuItemFilterSelected(Constants.CategoryFilter_Today);

		reportLog("5.3: List block is displayed according to the selected filter");
		studyNavigatorDashBoardPage.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_Today);
		
		reportLog("6.1:Select Past Week radio-button");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.CategoryFilter_PastWeek);

		reportLog("6.2:'Past Week' radio-button is selected");
		studyNavigatorDashBoardPage.verifyMenuItemFilterSelected(Constants.CategoryFilter_PastWeek);

		reportLog("6.3:List block is displayed according to the selected filter");
		studyNavigatorDashBoardPage.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_PastWeek);

		reportLog("7.1:	Check the lists block The following columns are displayed: Questionnaire (clickable link)");
		studyNavigatorDashBoardPage.clickOnRadioButtonForStatusOption(Constants.CategoryFilter_All);
		studyNavigatorDashBoardPage.searchQuestionnaire(subjectName,Constants.Mobile_Form,Constants.Compliance_TimeBlock);
		studyNavigatorDashBoardPage.verifyNameLinkIsClickable(Constants.Mobile_Form);

		reportLog("7.2:Date");
		studyNavigatorDashBoardPage.verifyQuestionnaireAndEventValuesDisplayedCorrectly(Constants.Mobile_Form,dateVar,"2");

		reportLog("7.3:	Subject (clickable link)");
		studyNavigatorDashBoardPage.verifySubjectLinkIsClickable(Constants.Mobile_Form);
		
		reportLog("7.4:Status");
		studyNavigatorDashBoardPage.verifyQuestionnaireAndEventValuesDisplayedCorrectly(Constants.Mobile_Form,Constants.Compliance_TimeBlock,"4");
	
		reportLog("7.5:	Duration");
		studyNavigatorDashBoardPage.verifyQuestionnaireAndEventValuesDisplayedCorrectly(Constants.Mobile_Form,actualTime,"5");
		studyNavigatorDashBoardPage.verifyQuestionnaireAndEventValuesDisplayedCorrectly(Constants.Mobile_Form,ExpectedTime,"6");
		
		reportLog("7.6:	Flag (empty or flag icon with 'Yes' icon)");
		studyNavigatorDashBoardPage.verifyQuestionnaireAndEventValuesDisplayedCorrectly(Constants.Mobile_Form,"","7");

		reportLog("7.7:	Site");
		studyNavigatorDashBoardPage.verifyQuestionnaireAndEventValuesDisplayedCorrectly(Constants.Mobile_Form,Constants.Site_forFilters,"8");

		reportLog("8.1:	Click to select any Questionnaire name");
		studyNavigatorDashBoardPage.clickOnQuestionnariesByQuestionnairesAndSubjectName(subjectName,Constants.Mobile_Form,Constants.Compliance_TimeBlock);
		
		reportLog("8.2: Questionnaire details screen is displayed");
		studyNavigatorDashBoardPage.verifyQuestionnairesPopUpDisplayed();
		studyNavigatorDashBoardPage.clickOnQuestionnairesCloseButton();
		
		reportLog("9.1:	Click to select any Subject");
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSubjectLink(Constants.Mobile_Form);
		subjectDetailPage.verifyNewSubjectDetailPage();
	
		reportLog("9.2:	Select Questionnaires from drop-down");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);

		
		reportLog("9.4 Click on All tab present in  questionnaires");
		subjectDetailPage.clikOnAllTab();
		
		
		
		reportLog("9.3:	Questionnaires list is displayed on Subject details screen for the selected Subject");
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();
		
		
		reportLog("10.1:Navigate back to the Study Navigator for Study PR#1");
		subjectDetailPage.navigateBack();
	
		reportLog("10.2:Questionnaires menu item is selected and displayed");
		studyNavigatorDashBoardPage.verifyMenuItemSelectedDisplayed(Constants.StudyDashBoard_columnName_Questionnaires_MenuItem);
	
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
