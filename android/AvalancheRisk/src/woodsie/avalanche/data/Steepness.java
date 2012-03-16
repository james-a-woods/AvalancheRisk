package woodsie.avalanche.data;

public enum Steepness implements ReductionFactor {
	NOT_STEEP(4),
	MODERATELY_STEEP(4),
	STEEP(3),
	VERY_STEEP(2),
	VERY_VERY_STEEP(1);

	private final int reductionFactor;

	private Steepness(int reductionFactor) {
		this.reductionFactor = reductionFactor;
	}

	public int getReductionFactor() {
		return reductionFactor;
	}
}