package woodsie.avalanche.data;

public enum Where implements ReductionFactor {
    AVOID_CRITICAL(4),
    AVOID_NORTH_HALF(3),
    AVOID_NORTH_SECTOR(2),
    ALL_ASPECTS(1);

    private final int reductionFactor;

    private Where(int reductionFactor) {
        this.reductionFactor = reductionFactor;
    }

    public int getReductionFactor() {
        return reductionFactor;
    }
}