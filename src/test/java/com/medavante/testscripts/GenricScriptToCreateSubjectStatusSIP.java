package com.medavante.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class GenricScriptToCreateSubjectStatusSIP extends BaseTest{
	
	private String pendingVisit="Auto_Paper_Transcription_ObsRO_Visit1"; 
	private String scheduledVisit="Auto_CR_Visit1";
	private String requestedVisit="Automation_CR_Visit2";
	private String cancelVisit="Automation_CR_Visit3";
	private String userName,password;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public GenricScriptToCreateSubjectStatusSIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties userCredentials = Configuration.readTestData("userClaimsCredentials");
		userName=userCredentials.getProperty("SuperAdminUN");
		password=userCredentials.getProperty("SuperAdminPW");
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		
	}
	
	
	@Test
	public  void subjectStatusCreationScript() {
		dashBoardPage = loginPage.loginInApplication(userName, password);
		
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		studyNavigatorDashBoardPage.selectStudy(studyName);		
		
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.RaterName_21);
	
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();		
		
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);
		
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		subjectDetailPage.clickOnVisitRow(pendingVisit);
		
		subjectDetailPage.clickOnAddVisitIcon();
		
		subjectDetailPage.clickOnVisitRow(scheduledVisit);
		
		centralRatingAppointmentPage=subjectDetailPage.clickOnAddVisitIcon();
		
		centralRatingAppointmentPage.verifyAppointmentPage();
		
		centralRatingAppointmentPage.setAppointmentDate();
		
		centralRatingAppointmentPage.setStartedTime("01", "30", "PM");
		
		centralRatingAppointmentPage.clickOnSave();
		
		subjectDetailPage=centralRatingAppointmentPage.navigateToSubjectDetailsPage();
		
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(screeningNum);
		
		subjectDetailPage.clickOnVisitRow(requestedVisit);
		
		centralRatingAppointmentPage=subjectDetailPage.clickOnAddVisitIcon();
		
		centralRatingAppointmentPage.setAppointmentDate();
		
		centralRatingAppointmentPage.setStartedTime("10", "30", "AM");
		
		centralRatingAppointmentPage.clickOnPickClinician();
		
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Available", "Fully", "time-slot");
		
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		
		subjectDetailPage=centralRatingAppointmentPage.navigateToSubjectDetailsPage();
		
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(screeningNum);
		
		subjectDetailPage.clickOnVisitRow(cancelVisit);
		
		centralRatingAppointmentPage=subjectDetailPage.clickOnAddVisitIcon(); 
		
		centralRatingAppointmentPage.setAppointmentDate();
		
		centralRatingAppointmentPage.setStartedTime("11", "30", "AM");
		
		centralRatingAppointmentPage.clickOnPickClinician();
		
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Available", "Fully", "time-slot");
		
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		
		centralRatingAppointmentPage.confirmationOfPopUpMessage();
		
		centralRatingAppointmentPage.verifyAppointmentPage();
		
		centralRatingAppointmentPage.clickOnCancelAppointment();
		
		centralRatingAppointmentPage.selectAppointmentReasonAndComment(Constants.Cancel_Appointment_Scheduling_Adjusment_Reason, generateRandomString(3));
		
		centralRatingAppointmentPage.confirmConfirmationOfReasonPopUp();
		
		subjectDetailPage=centralRatingAppointmentPage.navigateToSubjectDetailsPage();
		
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(screeningNum);
	}
}