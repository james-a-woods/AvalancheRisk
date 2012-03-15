package woodsie.avalanche.data;

public class ReductionParams {
    public Hazard hazardLevel;
    public Steepness steepness;
    public boolean allAspects;
    public boolean inverse;
    public Where where;
    public Terrain terrain;
    public GroupSize groupSize;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReductionParams hazardLevel=").append(hazardLevel);
        builder.append("\n\tsteepness=").append(steepness);
        builder.append("\n\tallAspects=").append(allAspects);
        builder.append("\n\tinverse=").append(inverse);
        builder.append("\n\twhere=").append(where);
        builder.append("\n\ttracked=").append(terrain);
        builder.append("\n\tgroupSize=").append(groupSize);
        return builder.toString();
    }

}
