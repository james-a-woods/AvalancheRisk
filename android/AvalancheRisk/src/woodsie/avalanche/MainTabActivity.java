package woodsie.avalanche;

import woodsie.avalanche.checklist.Checklist3x3Activity;
import woodsie.avalanche.info.InfoActivity;
import woodsie.avalanche.reduction.ReductionActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class MainTabActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);

		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, ReductionActivity.class);
		spec = tabHost.newTabSpec("reduction").setIndicator("Reduction").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, Checklist3x3Activity.class);
		spec = tabHost.newTabSpec("checklist3x3").setIndicator("3x3").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, InfoActivity.class);
		spec = tabHost.newTabSpec("info").setIndicator("Info").setContent(intent);
		tabHost.addTab(spec);

		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
		{
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			tv.setTextColor(getResources().getColor(R.color.white));
		}
	}

}
