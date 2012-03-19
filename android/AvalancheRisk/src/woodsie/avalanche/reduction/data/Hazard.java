package woodsie.avalanche.reduction.data;

public enum Hazard {
    LOW(2, 3),
    MODERATE(4, 6),
    CONSIDERABLE(8, 12),
    HIGH(16, 100),
    VERY_HIGH(100, 100);

    private final int dangerPotential;

    private final int higherDangerPotential;

    private Hazard(int dangerPotential, int higherDangerPotential) {
        this.dangerPotential = dangerPotential;
        this.higherDangerPotential = higherDangerPotential;
    }

    public int getDangerPotential(boolean higherHazard) {
        return higherHazard ? higherDangerPotential : dangerPotential;
    }
}