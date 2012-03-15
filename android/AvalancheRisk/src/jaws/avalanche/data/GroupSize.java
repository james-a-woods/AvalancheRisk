package woodsie.avalanche.data;

public enum GroupSize implements ReductionFactor {
    SMALL_SPACED(3),
    SMALL(2),
    LARGE_SPACED(2),
    LARGE(1);

    private final int reductionFactor;

    private GroupSize(int reductionFactor) {
        this.reductionFactor = reductionFactor;
    }

    public int getReductionFactor() {
        return reductionFactor;
    }
}