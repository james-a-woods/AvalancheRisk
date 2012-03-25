package woodsie.avalanche.reduction;

import woodsie.avalanche.R;
import woodsie.avalanche.reduction.listener.ReductionListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class ReductionActivity extends Activity {
	private final ReductionListener listener = new ReductionListener(this);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reduction_method_screen);

		View topLevel = findViewById(R.id.reductionForm);
		setRecursiveOnClickListener(topLevel, listener);
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
}