package woodsie.avalanche.reduction;

import java.math.BigDecimal;
import java.math.MathContext;

import woodsie.avalanche.reduction.data.Hazard;
import woodsie.avalanche.reduction.data.ReductionParams;
import woodsie.avalanche.reduction.data.Steepness;
import woodsie.avalanche.reduction.data.Where;

public class ReductionCalculator {
	public static final BigDecimal EXTREME = BigDecimal.valueOf(100);

	public BigDecimal process(ReductionParams params) {
		if (params.getHazardLevel() == Hazard.VERY_HIGH) {
			return EXTREME;
		}

		BigDecimal dangerPotential = new BigDecimal(params.getHazardLevel().getDangerPotential(params.isHigherHazard()), MathContext.DECIMAL32);

		int reductionFactor = 1;

		if ((params.getHazardLevel().getDangerPotential(params.isHigherHazard()) < Hazard.CONSIDERABLE.getDangerPotential(false))
		        || (params.getHazardLevel() == Hazard.HIGH && params.getSteepness() == Steepness.NOT_STEEP)
		        || (params.getHazardLevel() == Hazard.CONSIDERABLE && params.getSteepness().getReductionFactor() > 1)) {
			reductionFactor *= params.getSteepness().getReductionFactor();

			if (!params.isAllAspects()) {

				if (!params.isInverse()
				        || (params.isInverse() && params.getWhere() == Where.AVOID_CRITICAL)) {
					reductionFactor *= params.getWhere().getReductionFactor();
				}

			}

			reductionFactor *= params.getTerrain().getReductionFactor();
			reductionFactor *= params.getGroupSize().getReductionFactor();
		}

		return dangerPotential.divide(new BigDecimal(reductionFactor), MathContext.DECIMAL32);
	}
}
