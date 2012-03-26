package woodsie.avalanche.reduction;

import static woodsie.avalanche.reduction.ReductionTestUtils.assertIsNotSafe;
import static woodsie.avalanche.reduction.ReductionTestUtils.getDefaultParams;

import java.math.BigDecimal;

import junit.framework.TestCase;
import woodsie.avalanche.reduction.data.GroupSize;
import woodsie.avalanche.reduction.data.Hazard;
import woodsie.avalanche.reduction.data.ReductionParams;
import woodsie.avalanche.reduction.data.Steepness;
import woodsie.avalanche.reduction.data.Terrain;
import woodsie.avalanche.reduction.data.Where;

public class VeryHighHazardTest extends TestCase {

	private ReductionCalculator calculator;

	private ReductionParams params;

	@Override
	protected void setUp() throws Exception {
		calculator = new ReductionCalculator();
		params = getDefaultParams(Hazard.VERY_HIGH, false);
	}

	public void test_1() {

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_2() {
		params.steepness = Steepness.NOT_STEEP;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_3() {
		params.steepness = Steepness.NOT_STEEP;
		params.where = Where.AVOID_CRITICAL;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_4() {
		params.steepness = Steepness.NOT_STEEP;
		params.where = Where.AVOID_CRITICAL;
		params.terrain = Terrain.TRACKED;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_5() {
		params.steepness = Steepness.NOT_STEEP;
		params.where = Where.AVOID_CRITICAL;
		params.terrain = Terrain.TRACKED;
		params.groupSize = GroupSize.SMALL_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

}
