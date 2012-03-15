package woodsie.avalanche.data;

public enum Hazard {
    LOW(2),
    MODERATE(4),
    CONSIDERABLE(8),
    HIGH(16),
    VERY_HIGH(100);

    private final int dangerPotential;

    private Hazard(int dangerPotential) {
        this.dangerPotential = dangerPotential;
    }

    public int getDangerPotential() {
        return dangerPotential;
    }
}