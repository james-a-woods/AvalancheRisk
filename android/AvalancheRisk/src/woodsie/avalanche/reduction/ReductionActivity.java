package woodsie.avalanche.reduction;

import woodsie.avalanche.AbstractPersistedStateActivity;
import woodsie.avalanche.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.ScrollView;

public class ReductionActivity extends AbstractPersistedStateActivity {
	private final ReductionListener listener = new ReductionListener(this);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reduction_method_screen);

		restoreStateFromFile();

		View topLevel = findViewById(R.id.reductionForm);
		setRecursiveOnClickListener(topLevel, listener);

		findViewById(R.id.reductionResetTop).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				reset();
			}
		});
		findViewById(R.id.reductionResetBottom).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				reset();
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		saveStateToFile(findViewById(R.id.reductionForm));
	}

	@Override
	protected void onResume() {
		super.onResume();

		listener.onClick(null);
	}

	private void setRecursiveOnClickListener(View view, OnClickListener listener) {

		if (view instanceof ViewGroup) {
			ViewGroup group = (ViewGroup) view;
			for (int i = 0; i < group.getChildCount(); i++) {
				setRecursiveOnClickListener(group.getChildAt(i), listener);
			}
		} else {
			view.setOnClickListener(listener);
		}
	}

	public void reset() {
		reset(findViewById(R.id.reductionForm));

		((ScrollView) findViewById(R.id.reductionScroller)).scrollTo(0, 0);

	}

	private void reset(View view) {

		((RadioButton) findViewById(R.id.veryHigh)).setChecked(true);
		((RadioButton) findViewById(R.id.veryVerySteep)).setChecked(true);
		((RadioButton) findViewById(R.id.allAspects)).setChecked(true);
		((RadioButton) findViewById(R.id.largeGroup)).setChecked(true);

		((CheckBox) findViewById(R.id.higherHazard)).setChecked(false);
		((CheckBox) findViewById(R.id.allAspectsDanger)).setChecked(false);
		((CheckBox) findViewById(R.id.inverse)).setChecked(false);
		((CheckBox) findViewById(R.id.tracked)).setChecked(false);

		listener.onClick(null);
	}

}