package net.medavante.portal.report;


import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestCaseTrackingListners implements  ITestListener, ISuiteListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("==================" + " Execution of the "+ result.getName() +"  test starts now" +"==================");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("=================="+result.getName() + " Test Case run succesfully" +"==================");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("=================="+result.getName() + " Test Case has been failed" +"==================");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("=================="+result.getName() + " Test Case has been skipped" +"==================");		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("=================="+result.getName() + " Test Case failed With in Success Percentage"+"==================" );
		
	}

	@Override
	public void onStart(ISuite context) {
		System.out.println("=================="+context.getName() + " Suite has been started"+"==================" );
		
	}

	@Override
	public void onFinish(ISuite context) {
		System.out.println("=================="+context.getName() + " Suite has been finished" +"==================");
		
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {	
	}
	


}
