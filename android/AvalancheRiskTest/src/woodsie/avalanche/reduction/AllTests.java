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
		suite.addTestSuite(ConsiderableHazardTest.class);
		suite.addTestSuite(ConsiderableHazardHigherTest.class);
		suite.addTestSuite(ModerateHazardTest.class);
		suite.addTestSuite(ModerateHazardHigherTest.class);
		suite.addTestSuite(LowHazardTest.class);
		suite.addTestSuite(LowHazardHigherTest.class);
		suite.addTestSuite(AllAspectsTest.class);
		suite.addTestSuite(InverseTest.class);
		//$JUnit-END$
		return suite;
	}

}
