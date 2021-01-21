package com.medavante.testscripts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;

public class TestLogin_PDFReporting extends BaseTest{

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
    public TestLogin_PDFReporting(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void getTestData() throws Exception {
        System.setProperty("className", getClass().getSimpleName());
    }

    @Test
    public void testLogin() {
        // login in to application
        reportLog("Login in to application");
        loginPage.loginInApplication(userName, password);
//        Assert.fail("Test Script failed for Demo");
        //captureScreenshot("Step_1");

        // Logout application.
        reportLog("Logout application");
        loginPage = loginPage.logoutApplication();
        //captureScreenshot("Step_2");
    }
	
	
}
