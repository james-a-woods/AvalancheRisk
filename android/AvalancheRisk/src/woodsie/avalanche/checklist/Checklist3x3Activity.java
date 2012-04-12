package woodsie.avalanche.checklist;

import static woodsie.avalanche.data.CollapsibleSection.State.OPEN;
import woodsie.avalanche.R;
import woodsie.avalanche.data.CollapsibleSection;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ScrollView;

public class Checklist3x3Activity extends Activity implements OnClickListener {

	private CollapsibleSection description;

	private CollapsibleSection regional;

	private CollapsibleSection local;

	private CollapsibleSection zonal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checklist_3x3);

		View heading = findViewById(R.id.descriptionHeading);
		ImageView arrow = (ImageView) findViewById(R.id.descriptionArrow);
		View layout = findViewById(R.id.descriptionLayout);
		if (savedInstanceState != null && savedInstanceState.getBoolean("description"))
			layout.setVisibility(View.VISIBLE);
		description = new CollapsibleSection(this, arrow, layout);
		heading.setOnClickListener(this);
		arrow.setOnClickListener(this);

		heading = findViewById(R.id.regionalHeading);
		arrow = (ImageView) findViewById(R.id.regionalArrow);
		layout = findViewById(R.id.regionalLayout);
		if (savedInstanceState != null && savedInstanceState.getBoolean("regional"))
			layout.setVisibility(View.VISIBLE);
		regional = new CollapsibleSection(this, arrow, layout);
		heading.setOnClickListener(this);
		arrow.setOnClickListener(this);

		heading = findViewById(R.id.localHeading);
		arrow = (ImageView) findViewById(R.id.localArrow);
		layout = findViewById(R.id.localLayout);
		if (savedInstanceState != null && savedInstanceState.getBoolean("local"))
			layout.setVisibility(View.VISIBLE);
		local = new CollapsibleSection(this, arrow, layout);
		heading.setOnClickListener(this);
		arrow.setOnClickListener(this);

		heading = findViewById(R.id.zonalHeading);
		arrow = (ImageView) findViewById(R.id.zonalArrow);
		layout = findViewById(R.id.zonalLayout);
		if (savedInstanceState != null && savedInstanceState.getBoolean("zonal"))
			layout.setVisibility(View.VISIBLE);
		zonal = new CollapsibleSection(this, arrow, layout);
		heading.setOnClickListener(this);
		arrow.setOnClickListener(this);

		View button = findViewById(R.id.resetTop);
		button.setOnClickListener(this);
		button = findViewById(R.id.resetBottom);
		button.setOnClickListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putBoolean("description", description.getState() == OPEN);
		outState.putBoolean("regional", regional.getState() == OPEN);
		outState.putBoolean("local", local.getState() == OPEN);
		outState.putBoolean("zonal", zonal.getState() == OPEN);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.descriptionHeading:
		case R.id.descriptionArrow:
			description.toggle();
			regional.close();
			local.close();
			zonal.close();
			break;

		case R.id.regionalHeading:
		case R.id.regionalArrow:
			description.close();
			regional.toggle();
			local.close();
			zonal.close();
			break;

		case R.id.localHeading:
		case R.id.localArrow:
			description.close();
			regional.close();
			local.toggle();
			zonal.close();
			break;

		case R.id.zonalHeading:
		case R.id.zonalArrow:
			description.close();
			regional.close();
			local.close();
			zonal.toggle();
			break;

		case R.id.resetTop:
		case R.id.resetBottom:
			description.close();
			regional.close();
			local.close();
			zonal.close();

			recursiveReset(findViewById(R.id.checklist3x3Form));
			break;
		}

		((ScrollView) findViewById(R.id.checklistScroll)).scrollTo(0, 0);
	}

	private void recursiveReset(View view) {

		if (view instanceof ViewGroup) {
			ViewGroup group = (ViewGroup) view;
			for (int i = 0; i < group.getChildCount(); i++) {
				recursiveReset(group.getChildAt(i));
			}
		} else if (view instanceof CheckBox) {
			((CheckBox) view).setChecked(false);
		}
	}

}
