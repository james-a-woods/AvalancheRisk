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

public class AllAspectsTest extends TestCase {

	private ReductionCalculator calculator;

	@Override
	protected void setUp() throws Exception {
		calculator = new ReductionCalculator();
	}

	public void test_considerable_high_moderately_steep_north_half() {
		ReductionParams params = getDefaultParams(Hazard.CONSIDERABLE, true);
		params.setSteepness(Steepness.MODERATELY_STEEP);
		params.setAllAspects(true);
		params.setWhere(Where.AVOID_NORTH_HALF);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_considerable_moderately_steep_avoid_north_half_tracked() {
		ReductionParams params = getDefaultParams(Hazard.CONSIDERABLE, false);
		params.setSteepness(Steepness.MODERATELY_STEEP);
		params.setAllAspects(true);
		params.setWhere(Where.AVOID_NORTH_HALF);
		params.setTerrain(Terrain.TRACKED);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_high_not_steep_avoid_critical() {
		ReductionParams params = getDefaultParams(Hazard.HIGH, false);
		params.setSteepness(Steepness.NOT_STEEP);
		params.setAllAspects(true);
		params.setWhere(Where.AVOID_CRITICAL);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_high_not_steep_avoid_north_half_tracked() {
		ReductionParams params = getDefaultParams(Hazard.HIGH, false);
		params.setSteepness(Steepness.NOT_STEEP);
		params.setAllAspects(true);
		params.setWhere(Where.AVOID_NORTH_HALF);
		params.setTerrain(Terrain.TRACKED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_high_not_steep_avoid_north_sector_large_spaced_group() {
		ReductionParams params = getDefaultParams(Hazard.HIGH, false);
		params.setSteepness(Steepness.NOT_STEEP);
		params.setAllAspects(true);
		params.setWhere(Where.AVOID_NORTH_HALF);
		params.setGroupSize(GroupSize.LARGE_SPACED);

		BigDecimal risk = calculator.process(params);
		assertIsNotSafe(risk);
	}

	public void test_very_steep_all_aspects_tracked_small_group() {
		ReductionParams params = getDefaultParams(Hazard.MODERATE, true);
		params.setSteepness(Steepness.VERY_STEEP);
		params.setAllAspects(true);
		params.setTerrain(Terrain.TRACKED);
		params.setGroupSize(GroupSize.SMALL);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}

	public void test_very_steep_all_aspects_tracked_small_spaced_group() {
		ReductionParams params = getDefaultParams(Hazard.LOW, true);
		params.setSteepness(Steepness.VERY_STEEP);
		params.setAllAspects(true);
		params.setTerrain(Terrain.TRACKED);
		params.setGroupSize(GroupSize.SMALL);

		BigDecimal risk = calculator.process(params);
		assertIsSafe(risk);
	}
}
