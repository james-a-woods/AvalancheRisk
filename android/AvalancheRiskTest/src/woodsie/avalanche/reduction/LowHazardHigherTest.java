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

public class LowHazardHigherTest extends TestCase {

	private ReductionCalculator calculator;

	private ReductionParams params;

	@Override
	protected void setUp() throws Exception {
		calculator = new ReductionCalculator();
		params = getDefaultParams(Hazard.LOW, true);
	}

	public void test_default() {

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_1() {
		params.setSteepness(Steepness.VERY_VERY_STEEP);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_2() {
		params.setSteepness(Steepness.VERY_STEEP);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_steep_3() {
		params.setSteepness(Steepness.STEEP);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_steep_4() {
		params.setSteepness(Steepness.MODERATELY_STEEP);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_steep_5() {
		params.setSteepness(Steepness.NOT_STEEP);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_steep_avoid_north_sector() {
		params.setSteepness(Steepness.STEEP);
		params.setWhere(Where.AVOID_NORTH_SECTOR);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_steep_north_half() {
		params.setSteepness(Steepness.STEEP);
		params.setWhere(Where.AVOID_NORTH_HALF);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_very_very_steep_avoid_north_sector() {
		params.setSteepness(Steepness.VERY_VERY_STEEP);
		params.setWhere(Where.AVOID_NORTH_SECTOR);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_very_very_steep_avoid_north_half() {
		params.setSteepness(Steepness.VERY_VERY_STEEP);
		params.setWhere(Where.AVOID_NORTH_HALF);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_very_very_steep_avoid_north_half_tracked() {
		params.setSteepness(Steepness.VERY_VERY_STEEP);
		params.setWhere(Where.AVOID_NORTH_HALF);
		params.setTerrain(Terrain.TRACKED);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_very_very_steep_all_aspects_large_group() {
		params.setSteepness(Steepness.VERY_VERY_STEEP);
		params.setGroupSize(GroupSize.LARGE);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_very_steep_all_aspects_large_group() {
		params.setSteepness(Steepness.VERY_STEEP);
		params.setGroupSize(GroupSize.LARGE);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_very_very_steep_all_aspects_large_spaced_group() {
		params.setSteepness(Steepness.VERY_VERY_STEEP);
		params.setGroupSize(GroupSize.LARGE_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_very_very_steep_all_aspects_large_group_tracked() {
		params.setSteepness(Steepness.VERY_VERY_STEEP);
		params.setGroupSize(GroupSize.LARGE);
		params.setTerrain(Terrain.TRACKED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_very_very_steep_all_aspects_large_group_spaced_tracked() {
		params.setSteepness(Steepness.VERY_VERY_STEEP);
		params.setGroupSize(GroupSize.LARGE_SPACED);
		params.setTerrain(Terrain.TRACKED);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

}
