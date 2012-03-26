package woodsie.avalanche.reduction;

import static woodsie.avalanche.reduction.ReductionTestUtils.assertIsNotSafe;
import static woodsie.avalanche.reduction.ReductionTestUtils.assertIsSafe;
import static woodsie.avalanche.reduction.ReductionTestUtils.getDefaultParams;

import java.math.BigDecimal;

import junit.framework.TestCase;
import woodsie.avalanche.reduction.data.GroupSize;
import woodsie.avalanche.reduction.data.Hazard;
import woodsie.avalanche.reduction.data.ReductionParams;
import woodsie.avalanche.reduction.data.Steepness;
import woodsie.avalanche.reduction.data.Terrain;
import woodsie.avalanche.reduction.data.Where;

public class HighHazardTest extends TestCase {

	private ReductionCalculator calculator;

	private ReductionParams params;

	@Override
	protected void setUp() throws Exception {
		calculator = new ReductionCalculator();
		params = getDefaultParams(Hazard.HIGH, false);
	}

	public void test_default() {

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_1() {
		params.steepness = Steepness.VERY_STEEP;
		params.where = Where.AVOID_CRITICAL;
		params.terrain = Terrain.TRACKED;
		params.groupSize = GroupSize.SMALL_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_2() {
		params.steepness = Steepness.STEEP;
		params.where = Where.AVOID_CRITICAL;
		params.terrain = Terrain.TRACKED;
		params.groupSize = GroupSize.SMALL_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_3() {
		params.steepness = Steepness.MODERATELY_STEEP;
		params.where = Where.AVOID_CRITICAL;
		params.terrain = Terrain.TRACKED;
		params.groupSize = GroupSize.SMALL_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_4() {
		params.steepness = Steepness.NOT_STEEP;
		params.where = Where.AVOID_CRITICAL;
		params.terrain = Terrain.TRACKED;
		params.groupSize = GroupSize.SMALL_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_not_steep() {
		params.steepness = Steepness.NOT_STEEP;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_not_steep_avoid_north_half() {
		params.steepness = Steepness.NOT_STEEP;
		params.where = Where.AVOID_NORTH_HALF;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_not_steep_avoid_critical() {
		params.steepness = Steepness.NOT_STEEP;
		params.where = Where.AVOID_CRITICAL;

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_not_steep_avoid_north_half_tracked() {
		params.steepness = Steepness.NOT_STEEP;
		params.where = Where.AVOID_NORTH_HALF;
		params.terrain = Terrain.TRACKED;

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_not_steep_avoid_north_sector_large_spaced_group() {
		params.steepness = Steepness.NOT_STEEP;
		params.where = Where.AVOID_NORTH_HALF;
		params.groupSize = GroupSize.LARGE_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_not_steep_all_aspects_large_spaced_group() {
		params.steepness = Steepness.NOT_STEEP;
		params.groupSize = GroupSize.LARGE_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_not_steep_all_aspects_small_spaced_group() {
		params.steepness = Steepness.NOT_STEEP;
		params.groupSize = GroupSize.SMALL_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_not_steep_all_aspects_tracked_small_spaced_group() {
		params.steepness = Steepness.NOT_STEEP;
		params.terrain = Terrain.TRACKED;
		params.groupSize = GroupSize.SMALL_SPACED;

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

}
