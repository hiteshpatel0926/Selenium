package QA.Tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 extends TestBase {

	@BeforeTest
	public void Test1MethodBT1() {
		System.out.println("Inside Before Test Method Test");
	}
	
	@Test
	public void Test1Method1() {
		
		test.pass("STEP1");
		System.out.println("Inside Test1Method1");
		test.pass("STEP2");
		test.info("STEP3");
	}
	
	@Test
	public void Test1Method2() {
		test.pass("STEP1");
		test.info("STEP2");
		System.out.println("Inside Test1Method2");
		test.fail("STEP3");
	}
	
	@AfterTest
	public void Test1MethodAT1() {
		System.out.println("Inside After Test Method Test1");
	}

}
