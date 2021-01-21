package net.medavante.tablet.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.selenium.core.BaseTest;

	public class LoginTestTablet_SIP extends BaseTest {
	
	private String uname, pWd;
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public LoginTestTablet_SIP(String browser) {
		super(browser);
	}

	@BeforeClass
	public void executionOn() {
		exceutionOn = "Mobile";
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		// appPath = Configuration.readApplicationFile("APPPATH");
		uname = "tp_tester1";
		pWd = "#2Pencil";
		studyName = "MedAvante - ProPhase - TabletApplication";

	}

	/**
	 * Test Case Id: Login to Tablet App Inputs: User Name, Password
	 * 
	 * @throws Exception
	 */
	@Test(description = "Login to tablet", groups = "")
	public void verifyTestLoginToTablet() throws Exception {
		
		/*reportLog("Login in to application");
		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("Verify user is login");
		dashBoardPage.verifyMedavantePortalPage();*/

		reportLog("Creating object for desktop app and launch the app.");
		tabletLoginPage = tabletDriver.tabletSetUp();
		
		reportLog("Enter Username in username field.");
		tabletLoginPage.enterUserName(uname);

		reportLog("Enter Username in username field.");
		tabletLoginPage.enterUserName(uname);
		
		reportLog("Enter Password in password field.");
		tabletLoginPage.enterPasswordName(pWd);

		reportLog("Click on Sign In button.");
		tabletLoginPage.clickOnSignInButton();

		//tabletLoginPage.clickOnStudyName(studyName);

		/*reportLog("Click on Virgil app");
		tabletLoginPage.closeTablet();		
				
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify User Logged out from application");
		loginPage.verifyUserLogout();*/
		
		reportLog("2: Select the Study “MA-PP Tablet Application");
		tabletLoginPage.clickOnStdyButton();
		
		reportLog("3: Select Subject 100-003 Visit 2");
		tabletLoginPage.enterSubject("100-005");
		
		reportLog("4: Access PGI-C_Screenshots Assessment");
		tabletLoginPage.clickOnSubject();
		
		reportLog("4.1: Confirmation message appears to hand over the tablet to the subject.");
		//tabletLoginPage.clickOnStdyButton();
		
		reportLog("5: Rater confirms Subject and hands over the tablet to subject");
		
		
		reportLog("5.1: PIN has expired message displayed.");
		
		reportLog("6: Answer the security questions with “1”");
		
		reportLog("6.1: PIN creation screen is displayed");
		
		reportLog("7: Create PIN “1234” ");
		
		reportLog("7.1: “Your last 5 PINs cannot be reused” Message is displayed");
		
		reportLog("8: Select Ok and Select Cancel");
		
		
		reportLog("8.1: “Please hand-over the tablet..” Message is displayed");
		
		
		reportLog("9: Log into Tablet, Close previous subject, Access Subject 100-102 Visit 1 and select PGI-C_Screenshots");
		
		
		reportLog("9.1: Confirmation message appears to hand over the tablet to the subject.");
		
		reportLog("10: Confirm the message");
		
		reportLog("10.1: PIN creation screen is displayed.");
		
		

	}
}
