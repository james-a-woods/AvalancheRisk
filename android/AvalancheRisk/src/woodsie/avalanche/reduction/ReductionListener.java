package woodsie.avalanche.reduction;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static woodsie.avalanche.reduction.data.GroupSize.LARGE;
import static woodsie.avalanche.reduction.data.GroupSize.LARGE_SPACED;
import static woodsie.avalanche.reduction.data.GroupSize.SMALL;
import static woodsie.avalanche.reduction.data.GroupSize.SMALL_SPACED;
import static woodsie.avalanche.reduction.data.Hazard.CONSIDERABLE;
import static woodsie.avalanche.reduction.data.Hazard.HIGH;
import static woodsie.avalanche.reduction.data.Hazard.LOW;
import static woodsie.avalanche.reduction.data.Hazard.MODERATE;
import static woodsie.avalanche.reduction.data.Hazard.VERY_HIGH;
import static woodsie.avalanche.reduction.data.Steepness.MODERATELY_STEEP;
import static woodsie.avalanche.reduction.data.Steepness.NOT_STEEP;
import static woodsie.avalanche.reduction.data.Steepness.STEEP;
import static woodsie.avalanche.reduction.data.Steepness.VERY_STEEP;
import static woodsie.avalanche.reduction.data.Steepness.VERY_VERY_STEEP;
import static woodsie.avalanche.reduction.data.Terrain.TRACKED;
import static woodsie.avalanche.reduction.data.Terrain.UNTRACKED;
import static woodsie.avalanche.reduction.data.Where.ALL_ASPECTS;
import static woodsie.avalanche.reduction.data.Where.AVOID_CRITICAL;
import static woodsie.avalanche.reduction.data.Where.AVOID_NORTH_SECTOR;

import java.math.BigDecimal;
import java.math.RoundingMode;

import woodsie.avalanche.R;
import woodsie.avalanche.reduction.data.ReductionParams;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ReductionListener implements OnClickListener {
	private final ReductionCalculator processor = new ReductionCalculator();

	private final Activity activity;

	public ReductionListener(Activity activity) {
		this.activity = activity;
	}

	public void onClick(View view) {
		ReductionParams params = getParamsFromScreen();

		showOrHideInputFields(params);

		BigDecimal risk = processor.process(params);

		displayRisk(risk);
	}

	public ReductionParams getParamsFromScreen() {
		ReductionParams params = new ReductionParams();

		// Hazard level
		RadioGroup hazard = (RadioGroup) activity.findViewById(R.id.hazard);
		switch (hazard.getCheckedRadioButtonId()) {
		case R.id.low:
			params.setHazardLevel(LOW);
			break;
		case R.id.moderate:
			params.setHazardLevel(MODERATE);
			break;
		case R.id.considerable:
			params.setHazardLevel(CONSIDERABLE);
			break;
		case R.id.high:
			params.setHazardLevel(HIGH);
			break;
		case R.id.veryHigh:
		default:
			params.setHazardLevel(VERY_HIGH);
			break;
		}

		CheckBox higherHazard = (CheckBox) activity.findViewById(R.id.higherHazard);
		params.setHigherHazard(higherHazard.isChecked());

		// First class
		RadioGroup steepness = (RadioGroup) activity.findViewById(R.id.steepness);
		switch (steepness.getCheckedRadioButtonId()) {
		case R.id.notSteep:
			params.setSteepness(NOT_STEEP);
			break;
		case R.id.moderatelySteep:
			params.setSteepness(MODERATELY_STEEP);
			break;
		case R.id.steep:
			params.setSteepness(STEEP);
			break;
		case R.id.verySteep:
			params.setSteepness(VERY_STEEP);
			break;
		case R.id.veryVerySteep:
		default:
			params.setSteepness(VERY_VERY_STEEP);
			break;
		}

		// Second class
		CheckBox allAspects = (CheckBox) activity.findViewById(R.id.allAspectsDanger);
		params.setAllAspects(allAspects.isChecked());

		CheckBox inverse = (CheckBox) activity.findViewById(R.id.inverse);
		params.setInverse(inverse.isChecked());

		RadioGroup where = (RadioGroup) activity.findViewById(R.id.where);
		switch (where.getCheckedRadioButtonId()) {
		case R.id.allAspects:
			params.setWhere(ALL_ASPECTS);
			break;
		case R.id.avoidNorthSector:
			params.setWhere(AVOID_NORTH_SECTOR);
			break;
		case R.id.avoidNorthHalf:
			params.setWhere(AVOID_NORTH_SECTOR);
			break;
		case R.id.avoidCritical:
		default:
			params.setWhere(AVOID_CRITICAL);
			break;
		}

		CheckBox tracked = (CheckBox) activity.findViewById(R.id.tracked);
		params.setTerrain(tracked.isChecked() ? TRACKED : UNTRACKED);

		// Third class
		RadioGroup groupSize = (RadioGroup) activity.findViewById(R.id.groupSize);
		switch (groupSize.getCheckedRadioButtonId()) {
		case R.id.smallGroupSpaced:
			params.setGroupSize(SMALL_SPACED);
			break;
		case R.id.smallGroup:
			params.setGroupSize(SMALL);
			break;
		case R.id.largeGroupSpaced:
			params.setGroupSize(LARGE_SPACED);
			break;
		case R.id.largeGroup:
		default:
			params.setGroupSize(LARGE);
			break;
		}

		return params;
	}

	private void showOrHideInputFields(ReductionParams params) {
		RadioGroup where = (RadioGroup) activity.findViewById(R.id.where);
		RadioButton avoidNorthSector = (RadioButton) activity.findViewById(R.id.avoidNorthSector);
		RadioButton avoidNorthHalf = (RadioButton) activity.findViewById(R.id.avoidNorthHalf);

		if (params.isAllAspects()) {
			avoidNorthSector.setVisibility(VISIBLE);
			avoidNorthHalf.setVisibility(VISIBLE);

			where.setVisibility(GONE);

		} else if (params.isInverse()) {
			where.setVisibility(VISIBLE);

			avoidNorthSector.setVisibility(GONE);
			avoidNorthHalf.setVisibility(GONE);

		} else {
			where.setVisibility(VISIBLE);
			avoidNorthSector.setVisibility(VISIBLE);
			avoidNorthHalf.setVisibility(VISIBLE);
		}

	}

	private void displayRisk(BigDecimal risk) {
		String riskStr = activity.getResources().getString(R.string.dangerLevel);
		riskStr = riskStr.replace("%", risk.setScale(3, RoundingMode.CEILING).toString());

		TextView text = (TextView) activity.findViewById(R.id.dangerLevelText);

		String message;
		if (risk.compareTo(BigDecimal.ONE) <= 0) {
			text.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.cyan_hgradient));
			message = activity.getResources().getString(R.string.riskSafe);
		} else {
			text.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.red_hgradient));
			if (risk.equals(ReductionCalculator.EXTREME)) {
				message = activity.getResources().getString(R.string.riskExtreme);
			} else {
				message = activity.getResources().getString(R.string.riskDangerous);
			}

		}

		message = riskStr + "\n" + message;
		text.setText(message);

	}
}
