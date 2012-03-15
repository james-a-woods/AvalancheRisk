package jaws.avalanche.listener.processor;

import java.math.BigDecimal;
import java.math.MathContext;

import jaws.avalanche.data.Hazard;
import jaws.avalanche.data.ReductionParams;
import jaws.avalanche.data.Where;



public class ReductionProcessor {
    public static final BigDecimal EXTREME = BigDecimal.valueOf(100);

    public BigDecimal Process(ReductionParams params) {
        if (params.hazardLevel == Hazard.VERY_HIGH) {
            return EXTREME;
        }

        BigDecimal dangerPotential = new BigDecimal(params.hazardLevel.getDangerPotential(), MathContext.DECIMAL32);

        int reductionFactor = 1;

        if (params.hazardLevel.ordinal() < Hazard.CONSIDERABLE.ordinal()
                || params.steepness.getReductionFactor() > 1) {
            reductionFactor *= params.steepness.getReductionFactor();

            if (!params.allAspects) {

                if (!params.inverse
                        || (params.inverse && params.where == Where.AVOID_CRITICAL)) {
                    reductionFactor *= params.where.getReductionFactor();
                }

                reductionFactor *= params.terrain.getReductionFactor();
            }

            reductionFactor *= params.groupSize.getReductionFactor();
        }

        return dangerPotential.divide(new BigDecimal(reductionFactor), MathContext.DECIMAL32);
    }
}
