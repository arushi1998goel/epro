package net.medavante.smoke.testscripts;


import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.selenium.core.BaseTest;

public class LoginTest_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public LoginTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
	}

	/**
	 * Test Case Id: TC -01 Test Case Name: User Login and Logout Expected
	 * Condition: User able login to site Portal and logout the application
	 * Inputs: User Name, Password
	 */
	@Test(description = "Smoke TC01--User Login and Logout", groups = "smoke")
	public void SFB_TC_2563_verifyUserLoginLogout() {

		reportLog("Login in to application");
		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("Verify user is login");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify User Logged out from application");
		loginPage.verifyUserLogout();

	}

}
