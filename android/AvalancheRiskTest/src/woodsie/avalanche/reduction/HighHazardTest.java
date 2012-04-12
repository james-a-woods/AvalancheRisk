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
		params.setSteepness(Steepness.VERY_VERY_STEEP);
		params.setWhere(Where.AVOID_CRITICAL);
		params.setTerrain(Terrain.TRACKED);
		params.setGroupSize(GroupSize.SMALL_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_2() {
		params.setSteepness(Steepness.VERY_STEEP);
		params.setWhere(Where.AVOID_CRITICAL);
		params.setTerrain(Terrain.TRACKED);
		params.setGroupSize(GroupSize.SMALL_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_3() {
		params.setSteepness(Steepness.STEEP);
		params.setWhere(Where.AVOID_CRITICAL);
		params.setTerrain(Terrain.TRACKED);
		params.setGroupSize(GroupSize.SMALL_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_4() {
		params.setSteepness(Steepness.MODERATELY_STEEP);
		params.setWhere(Where.AVOID_CRITICAL);
		params.setTerrain(Terrain.TRACKED);
		params.setGroupSize(GroupSize.SMALL_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_5() {
		params.setSteepness(Steepness.NOT_STEEP);
		params.setWhere(Where.AVOID_CRITICAL);
		params.setTerrain(Terrain.TRACKED);
		params.setGroupSize(GroupSize.SMALL_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_not_steep() {
		params.setSteepness(Steepness.NOT_STEEP);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_not_steep_avoid_north_half() {
		params.setSteepness(Steepness.NOT_STEEP);
		params.setWhere(Where.AVOID_NORTH_HALF);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_not_steep_avoid_critical() {
		params.setSteepness(Steepness.NOT_STEEP);
		params.setWhere(Where.AVOID_CRITICAL);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_not_steep_avoid_north_half_tracked() {
		params.setSteepness(Steepness.NOT_STEEP);
		params.setWhere(Where.AVOID_NORTH_HALF);
		params.setTerrain(Terrain.TRACKED);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_not_steep_avoid_north_sector_large_spaced_group() {
		params.setSteepness(Steepness.NOT_STEEP);
		params.setWhere(Where.AVOID_NORTH_HALF);
		params.setGroupSize(GroupSize.LARGE_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_not_steep_all_aspects_large_spaced_group() {
		params.setSteepness(Steepness.NOT_STEEP);
		params.setGroupSize(GroupSize.LARGE_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_not_steep_all_aspects_small_spaced_group() {
		params.setSteepness(Steepness.NOT_STEEP);
		params.setGroupSize(GroupSize.SMALL_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_not_steep_all_aspects_tracked_small_spaced_group() {
		params.setSteepness(Steepness.NOT_STEEP);
		params.setTerrain(Terrain.TRACKED);
		params.setGroupSize(GroupSize.SMALL_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

}
