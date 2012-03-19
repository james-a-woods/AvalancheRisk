package woodsie.avalanche;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AvalancheRiskActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		View continueButton = findViewById(R.id.frontContinueButton);
		continueButton.setOnClickListener(this);

	}

//	@Override
//	protected void onRestoreInstanceState(Bundle savedInstanceState) {
//		super.onRestoreInstanceState(savedInstanceState);
//
//		lastWarningShown = new Date(savedInstanceState.getLong("lastWarningShown"));
//	}
//
//	@Override
//	protected void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
//
//		if (lastWarningShown != null)
//			outState.putLong("lastWarningShown", lastWarningShown.getTime());
//	}

	public void onClick(View v) {
		Intent intent = new Intent().setClass(this, MainTabActivity.class);
		startActivity(intent);
	}

}