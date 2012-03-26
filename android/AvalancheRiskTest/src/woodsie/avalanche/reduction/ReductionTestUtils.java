package woodsie.avalanche.reduction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.math.BigDecimal;

import woodsie.avalanche.reduction.data.GroupSize;
import woodsie.avalanche.reduction.data.Hazard;
import woodsie.avalanche.reduction.data.ReductionParams;
import woodsie.avalanche.reduction.data.Steepness;
import woodsie.avalanche.reduction.data.Terrain;
import woodsie.avalanche.reduction.data.Where;

public class ReductionTestUtils {

	public static ReductionParams getDefaultParams(Hazard hazardLevel, boolean higherHazard) {
		ReductionParams params = new ReductionParams();
		params.hazardLevel = hazardLevel;
		params.higherHazard = higherHazard;
		params.steepness = Steepness.VERY_VERY_STEEP;
		params.allAspects = false;
		params.inverse = false;
		params.where = Where.ALL_ASPECTS;
		params.terrain = Terrain.UNTRACKED;
		params.groupSize = GroupSize.LARGE;

		return params;
	}

	public static void assertIsSafe(BigDecimal risk) {
		assertThat(risk, is(lessThanOrEqualTo(BigDecimal.ONE)));
	}

	public static void assertIsNotSafe(BigDecimal risk) {
		assertThat(risk, is(greaterThan(BigDecimal.ONE)));
	}
}
