package woodsie.avalanche;

import woodsie.avalanche.checklist.Checklist3x3Activity;
import woodsie.avalanche.info.InfoActivity;
import woodsie.avalanche.reduction.ReductionActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainTabActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);

		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, ReductionActivity.class);
		spec = tabHost.newTabSpec("reduction").setIndicator("Reduction", null).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, Checklist3x3Activity.class);
		spec = tabHost.newTabSpec("checklist3x3").setIndicator("3x3", null).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, InfoActivity.class);
		spec = tabHost.newTabSpec("info").setIndicator("Info", null).setContent(intent);
		tabHost.addTab(spec);

	}

}
