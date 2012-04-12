package woodsie.avalanche.reduction.data;

import lombok.Data;

@Data
public class ReductionParams {
	private Hazard hazardLevel;

	private boolean higherHazard;

	private Steepness steepness;

	private boolean allAspects;

	private boolean inverse;

	private Where where;

	private Terrain terrain;

	private GroupSize groupSize;

}
