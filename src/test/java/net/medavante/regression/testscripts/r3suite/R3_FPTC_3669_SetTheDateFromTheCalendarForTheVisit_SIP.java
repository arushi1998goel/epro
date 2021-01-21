/**
 * @author
 * @date 03/16/2020 
 * =========================================================================
 * Test Case Id: FP-TC-3669 
 * Test Case Name: Set The Date From The Calendar For The Visit
 *  * pre-qualification 1. At least one Study is configured for the test
                        2. At least one Subject with initiated Visit (in Pending state) exists
                        3. At least one Site User with access to Site Portal and can manage visits for a Study in Pr#1 exists
                        4. At least one MA User with access to MA Portal and can manage visits for a Study in Pr#1 exists
 * ========================================================================= 
 */
package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3669_SetTheDateFromTheCalendarForTheVisit_SIP extends BaseTest {
	String study,SubjectName1 = "SUBJ_" + generateRandomString(5),SubjectName2="SUBJ_" + generateRandomString(5);
	

	@Factory(dataProvider="Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3669_SetTheDateFromTheCalendarForTheVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod 
	public void getTestData() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		study =prop.getProperty("StudyName2645");
		
		reportLog("1.1: Creating pre-requisites for site portal");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog(" Creating a subject");
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(study, Constants.ATAssignedRater_10, SubjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Initiate Visit");
		subjectDetailPage.clikOnAllTab();
		subjectDetailPage.clickOnInitiateVisitIcon();
	    loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	    
	    reportLog("1.2: Creating pre-requisites for MA portal");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectCoordinator, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog(" Creating a subject");
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(study, Constants.ATAssignedRater_10, SubjectName2);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Initiate Visit");
		subjectDetailPage.clikOnAllTab();
		subjectDetailPage.clickOnInitiateVisitIcon();
	    loginPage.logoutApplication();
		loginPage.verifyUserLogout();		
		
}
	
	
	/***
	 * ====================================================================================================================
	 * Test Case Id: FPTC_3669 Test Case Name: Show that a User with an appropriate claim can set the date to a visit
	 *  
	 *  
	 *
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 *
	 */
	
	@Test(description = "FPTC_3669_SetTheDateFromTheCalendarForTheVisit", groups = { "R3" })
	public void FPTC_3669_verifySetTheDateFromTheCalendarForTheVisit() throws Exception {
		reportLog("2.1: Log in to the Site Portal as the User in Pr#3");
		dashBoardPage = loginPage.siteLogin(SuperAdminUN, SuperAdminPW);
		
		reportLog("2.2: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.1:  Navigate to the Subject Details screen of Pr#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.searchSubject(SubjectName1);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSubject(SubjectName1);
		subjectDetailPage.clikOnAllTab();
		
		reportLog("3.2: Select the Visit of Pr#2");
		subjectDetailPage.clickOnCalendarVisitRow(Constants.Auto_Visit_1);
		
		reportLog("3.3: Visit Details block is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		
		reportLog("3.4:  Calendar icon is displayed");
		subjectDetailPage.verifyCalendarIconIsDisplayed();
		
		reportLog("3.5: Notice that date isn't set is displayed");
		subjectDetailPage.noticeThatDateIsNotDisplayed();
		
		reportLog("4.1: Click on Calendar icon");
		subjectDetailPage.clickOnCalendarIcon();
		
		reportLog("4.2: Calendar date-picker is displayed");
		subjectDetailPage.verifyCalendarDatePickerDisplayed();
		
		reportLog("4.3: Current date selected by default");
		subjectDetailPage.verifyCurrentDateSelectedByDefault();
		
		reportLog("5.1: Select current date");
		subjectDetailPage.setCurrentDate();
		
		reportLog("5.2: Calendar date-picker field reflects the corresponding date");
		subjectDetailPage.verifyCorrespondingDateReflects();
		
		reportLog("5.3: 'Not Set' notice isn't displayed");
		subjectDetailPage.notSetIsNotDisplayed();
		
		reportLog("6.1: Select date in future");
		subjectDetailPage.clickOnCalendarIcon();
		subjectDetailPage.selectFutureDate();
		
		reportLog("6.2: Calendar date-picker field reflects the corresponding date");
		subjectDetailPage.verifyCorrespondingDateReflects();
		
		reportLog("6.3:  'Not Set' notice isn't displayed");
		subjectDetailPage.notSetIsNotDisplayed();

		reportLog("7.1: Select date in past");
		subjectDetailPage.clickOnCalendarIcon();
		subjectDetailPage.selectPastDate();

		reportLog("7.2: Calendar date-picker field reflects the corresponding date");
		subjectDetailPage.verifyCorrespondingDateReflects();

		reportLog("7.3:  'Not Set' notice isn't displayed");
		subjectDetailPage.notSetIsNotDisplayed();

		reportLog("8.1: Log out as a User Pr#3");
		loginPage.logoutApplication();
		
		reportLog("8.1.1: Verify user logged out successfully");
		loginPage.verifyUserLogout();

		
		reportLog("8.2: Log in to the MA Portal as the User in Pr#4 ");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectCoordinator, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Repeat the Steps#3-#7");
		reportLog("9.1:  Navigate to the Subject Details screen of Pr#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.searchSubject(SubjectName2);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSubject(SubjectName2);
		subjectDetailPage.clikOnAllTab();
		
		reportLog("9.2: Select the Visit of Pr#2");
		subjectDetailPage.clickOnCalendarVisitRow(Constants.Auto_Visit_1);

		
		reportLog("9.3: Visit Details block is displayed");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();

		reportLog("9.4:  Calendar icon is displayed");
		subjectDetailPage.verifyCalendarIconIsDisplayed();

		reportLog("9.5: Notice that date isn't set is displayed");
		subjectDetailPage.noticeThatDateIsNotDisplayed();

		reportLog("10.1: Click on Calendar icon");
		subjectDetailPage.clickOnCalendarIcon();

		reportLog("10.2: Calendar date-picker is displayed");
		subjectDetailPage.verifyCalendarDatePickerDisplayed();

		reportLog("10.3: Current date selected by default");
		subjectDetailPage.verifyCurrentDateSelectedByDefault();

		reportLog("11.1: Select current date");
		subjectDetailPage.setCurrentDate();

		reportLog("11.2: Calendar date-picker field reflects the corresponding date");
		subjectDetailPage.verifyCorrespondingDateReflects();

		reportLog("11.3: 'Not Set' notice isn't displayed");
		subjectDetailPage.notSetIsNotDisplayed();

		reportLog("12.1: Select date in future");
		subjectDetailPage.clickOnCalendarIcon();
		subjectDetailPage.selectFutureDate();
		
		reportLog("12.2: Calendar date-picker field reflects the corresponding date");
		subjectDetailPage.verifyCorrespondingDateReflects();

		reportLog("12.3:  'Not Set' notice isn't displayed");
		subjectDetailPage.notSetIsNotDisplayed();

		reportLog("13.1: Select date in past");
		subjectDetailPage.clickOnCalendarIcon();
		subjectDetailPage.selectPastDate();
		
		reportLog("13.2: Calendar date-picker field reflects the corresponding date");
		subjectDetailPage.verifyCorrespondingDateReflects();

		reportLog("13.3:  'Not Set' notice isn't displayed");
		subjectDetailPage.notSetIsNotDisplayed();

		reportLog("14.1: Log out as a User Pr#4");
		loginPage.logoutApplication();

		reportLog("14.1.1: Verify user logged out successfully");
		loginPage.verifyUserLogout();	
		
	}
	
}
