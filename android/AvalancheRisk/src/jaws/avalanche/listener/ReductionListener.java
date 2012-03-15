package jaws.avalanche.listener;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static jaws.avalanche.data.GroupSize.LARGE;
import static jaws.avalanche.data.GroupSize.LARGE_SPACED;
import static jaws.avalanche.data.GroupSize.SMALL;
import static jaws.avalanche.data.GroupSize.SMALL_SPACED;
import static jaws.avalanche.data.Hazard.CONSIDERABLE;
import static jaws.avalanche.data.Hazard.HIGH;
import static jaws.avalanche.data.Hazard.LOW;
import static jaws.avalanche.data.Hazard.MODERATE;
import static jaws.avalanche.data.Hazard.VERY_HIGH;
import static jaws.avalanche.data.Steepness.MODERATELY_STEEP;
import static jaws.avalanche.data.Steepness.STEEP;
import static jaws.avalanche.data.Steepness.VERY_STEEP;
import static jaws.avalanche.data.Steepness.VERY_VERY_STEEP;
import static jaws.avalanche.data.Terrain.TRACKED;
import static jaws.avalanche.data.Terrain.UNTRACKED;
import static jaws.avalanche.data.Where.ALL_ASPECTS;
import static jaws.avalanche.data.Where.AVOID_CRITICAL;
import static jaws.avalanche.data.Where.AVOID_NORTH_SECTOR;

import java.math.BigDecimal;
import java.math.RoundingMode;


import woodsie.avalanche.R;
import jaws.avalanche.data.ReductionParams;
import jaws.avalanche.listener.processor.ReductionProcessor;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ReductionListener implements OnClickListener {
    private final ReductionProcessor processor = new ReductionProcessor();

    private final Activity activity;

    public ReductionListener(Activity activity) {
        this.activity = activity;
    }

    public void onClick(View view) {
        ReductionParams params = new ReductionParams();

        RadioGroup hazard = (RadioGroup) activity.findViewById(R.id.hazard);
        switch (hazard.getCheckedRadioButtonId()) {
        case R.id.low:
            params.hazardLevel = LOW;
            break;
        case R.id.moderate:
            params.hazardLevel = MODERATE;
            break;
        case R.id.considerable:
            params.hazardLevel = CONSIDERABLE;
            break;
        case R.id.high:
            params.hazardLevel = HIGH;
            break;
        case R.id.veryHigh:
        default:
            params.hazardLevel = VERY_HIGH;
            break;
        }

        RadioGroup steepness = (RadioGroup) activity.findViewById(R.id.steepness);
        switch (steepness.getCheckedRadioButtonId()) {
        case R.id.moderatelySteep:
            params.steepness = MODERATELY_STEEP;
            break;
        case R.id.steep:
            params.steepness = STEEP;
            break;
        case R.id.verySteep:
            params.steepness = VERY_STEEP;
            break;
        case R.id.veryVerySteep:
        default:
            params.steepness = VERY_VERY_STEEP;
            break;
        }

        CheckBox allAspects = (CheckBox) activity.findViewById(R.id.allAspectsDanger);
        params.allAspects = allAspects.isChecked();

        CheckBox inverse = (CheckBox) activity.findViewById(R.id.inverse);
        params.inverse = inverse.isChecked();

        RadioGroup where = (RadioGroup) activity.findViewById(R.id.where);
        switch (where.getCheckedRadioButtonId()) {
        case R.id.allAspects:
            params.where = ALL_ASPECTS;
            break;
        case R.id.avoidNorthSector:
            params.where = AVOID_NORTH_SECTOR;
            break;
        case R.id.avoidNorthHalf:
            params.where = AVOID_NORTH_SECTOR;
            break;
        case R.id.avoidCritical:
        default:
            params.where = AVOID_CRITICAL;
            break;
        }

        CheckBox tracked = (CheckBox) activity.findViewById(R.id.tracked);
        params.terrain = tracked.isChecked() ? TRACKED : UNTRACKED;

        RadioGroup groupSize = (RadioGroup) activity.findViewById(R.id.groupSize);
        switch (groupSize.getCheckedRadioButtonId()) {
        case R.id.smallGroupSpaced:
            params.groupSize = SMALL_SPACED;
            break;
        case R.id.smallGroup:
            params.groupSize = SMALL;
            break;
        case R.id.largeGroupSpaced:
            params.groupSize = LARGE_SPACED;
            break;
        case R.id.largeGroup:
        default:
            params.groupSize = LARGE;
            break;
        }

        RadioButton avoidNorthSector = (RadioButton) activity.findViewById(R.id.avoidNorthSector);
        RadioButton avoidNorthHalf = (RadioButton) activity.findViewById(R.id.avoidNorthHalf);

        if (params.allAspects) {
            avoidNorthSector.setVisibility(VISIBLE);
            avoidNorthHalf.setVisibility(VISIBLE);

            where.setVisibility(GONE);
            tracked.setVisibility(GONE);

        } else if (params.inverse) {
            where.setVisibility(VISIBLE);
            tracked.setVisibility(VISIBLE);

            avoidNorthSector.setVisibility(GONE);
            avoidNorthHalf.setVisibility(GONE);

        } else {
            where.setVisibility(VISIBLE);
            avoidNorthSector.setVisibility(VISIBLE);
            avoidNorthHalf.setVisibility(VISIBLE);
            tracked.setVisibility(VISIBLE);
        }

        TextView text = (TextView) activity.findViewById(R.id.dangerLevelText);

        BigDecimal risk = processor.Process(params);
        String riskStr = activity.getResources().getString(R.string.dangerLevel);
        riskStr = riskStr.replace("%", risk.setScale(3, RoundingMode.CEILING).toString());

        String message;
        if (risk.compareTo(BigDecimal.ONE) <= 0) {
            text.setBackgroundColor(activity.getResources().getColor(R.color.green));
            message = activity.getResources().getString(R.string.riskSafe);
        } else {
            text.setBackgroundColor(activity.getResources().getColor(R.color.red));
            if (risk.equals(ReductionProcessor.EXTREME)) {
                message = activity.getResources().getString(R.string.riskExtreme);
            } else {
                message = activity.getResources().getString(R.string.riskDangerous);
            }

        }

        message = riskStr + "\n" + message;
        text.setText(message);
    }

}
