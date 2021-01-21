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

public class FPTC_1282_SetClearFlagToAVisit_SIP extends BaseTest {
	private String visitName1, visitName2;


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1282_SetClearFlagToAVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		visitName1 = properties.getProperty("Visit12645");
		visitName2 = properties.getProperty("VisitSecond2645");
		
		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,AT_PRODSiteCoordinatorUserName,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitName2);
		subjectDetailPage.clickOnInitiateVisitIcon();
		subjectDetailPage.verifySetFlagIconDisplayed();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1282 Test Case Name:Set/Clear Flag To A Visit
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-1282_Set/Clear Flag To A Visit", groups = { "" })

	public void FPTC_1282_verifySetClearFlagToAVisit() throws Exception {

		reportLog("1.1:	Login to portal application as the Medavante User");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("2.1:Navigate to the Subject details page of Pr#4");
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.2: Select study and site on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.All_SiteText);

		reportLog("2.3: Select the subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum);

		reportLog("2.4:Verify Subject Details Displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("2.5:Select the Visit of Pr#5.1");
		subjectDetailPage.clickOnCalendarVisitRow(visitName1);

		reportLog("2.6:Visit Details block is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();

		reportLog("2.7:Control to set Flag is not displayed");
		subjectDetailPage.verifySetFlagIconIsNotDisplayed();		

		reportLog("3.1: Select the Visit of PR#5.2");
		subjectDetailPage.clickOnCalendarVisitRow(visitName2);
	
		reportLog("3.2:Visit Details block is displayed ");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();

		reportLog("3.3: Control to set Flag is displayed");
		subjectDetailPage.verifySetFlagIconDisplayed();

		reportLog("4.1:	Select an action to set Flag to a visit in Pr#5.2 ");
		subjectDetailPage.clickOnSetFlag();

		reportLog("4.2:Clear Flag control is displayed ");
		subjectDetailPage.verifyClearFlagIconDisplayed();
		
		reportLog("4.3:Flag icon is displayed in the visits list for a Visit of Pr#5.2");
		subjectDetailPage.verifyFlagIsSetForVisit(visitName2);
		
		reportLog("5.1:	Select an action to open Flagged filter in the Visit list");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_Flagged);

		reportLog("5.2:The Visit of Pr#5.2 is displayed in the list");
		subjectDetailPage.verifyVisitPresentInCalenderVisitRow(visitName2);

		reportLog("6.1:	Select an action to clear Flag to a visit of Pr#5.2");
		subjectDetailPage.clickOnClearFlag();
		
		reportLog("6.2:Set Flag control is displayed ");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitName2);
		subjectDetailPage.verifySetFlagIconDisplayed();

		reportLog("6.3:Flag icon is not displayed in the visits list for a Visit of Pr#5.2 ");
		subjectDetailPage.verifyFlagIsNotDisplayedForVisit(visitName2);

		reportLog("6.4:The Visit of Pr#5.2 is not displayed in the Flagged filter");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_Flagged);
		subjectDetailPage.verifyVisitNotPresentInCalenderVisitRow(visitName2);	
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("7.1:Initiate the Visit of Pr#5.1 ");
		subjectDetailPage.clickOnCalendarVisitRow(visitName1);
		subjectDetailPage.clickOnInitiateVisitIcon();

		reportLog("7.2:Select an action to set Flag to the Visit in Pr#5.1");
		subjectDetailPage.clickOnSetFlag();

		reportLog("7.3:Clear Flag control is displayed ");
		subjectDetailPage.verifyClearFlagIconDisplayed();
	
		reportLog("7.4:Flag icon is displayed in the visits list for a Visit of Pr#5.1 ");
		subjectDetailPage.verifyFlagIsSetForVisit(visitName1);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	// =========== Login Form User Not Having Claim To  Set/Clear Flag ===================================//

		reportLog("8.1:Log in to the Portal as User in Pr#3");
		dashBoardPage =loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);
		
		reportLog("8.2:	User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("9.1:Navigate to the Subject Details page of Pr#4 ");
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.All_SiteText);
		
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("9.2:	Select the Visit of Pr#5.1");
		subjectDetailPage.clickOnCalendarVisitRow(visitName1);
		
		reportLog("9.3:Visit Details block is displayed  ");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		
		reportLog("9.4:Control to clear Flag is not displayed ");
		subjectDetailPage.verifyClearFlagIconIsNotDisplayed();
		
		reportLog("10.1:Select the Visit of Pr#5.2");
		subjectDetailPage.clickOnCalendarVisitRow(visitName2);
		
		reportLog("10.2:Visit Details block is displayed ");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		
		reportLog("10.3:Control to set Flag is not displayed");
		subjectDetailPage.verifySetFlagIconIsNotDisplayed();
		
		reportLog("10.4:Logout from the application");
		loginPage.logoutApplication();

		reportLog("10.5:Verify user is logout");
		loginPage.verifyUserLogout();	

	}

}
