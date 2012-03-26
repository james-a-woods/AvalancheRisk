package woodsie.avalanche.reduction;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(VeryHighHazardTest.class);
		suite.addTestSuite(VeryHighHazardHigherTest.class);
		suite.addTestSuite(HighHazardTest.class);
		suite.addTestSuite(HighHazardHigherTest.class);
		//$JUnit-END$
		return suite;
	}

}
