package woodsie.avalanche.reduction.data;

public enum Terrain implements ReductionFactor {
    TRACKED(2),
    UNTRACKED(1);

    private final int reductionFactor;

    private Terrain(int reductionFactor) {
        this.reductionFactor = reductionFactor;
    }

    public int getReductionFactor() {
        return reductionFactor;
    }
}