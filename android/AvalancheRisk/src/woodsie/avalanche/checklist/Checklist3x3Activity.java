package woodsie.avalanche.checklist;

import static woodsie.avalanche.section.CollapsibleSection.OPEN_SECTION;
import static woodsie.avalanche.section.CollapsibleSection.State.OPEN;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import woodsie.avalanche.AbstractPersistentActivity;
import woodsie.avalanche.R;
import woodsie.avalanche.section.CollapsibleSection;
import woodsie.avalanche.section.CollapsibleSectionParent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ScrollView;

public class Checklist3x3Activity extends AbstractPersistentActivity implements CollapsibleSectionParent, OnClickListener {

	@Getter
	private List<CollapsibleSection> sectionList;

	@Getter
	private ScrollView scroller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checklist_3x3);

		sectionList = new ArrayList<CollapsibleSection>();
		scroller = (ScrollView) findViewById(R.id.checklistScroller);

		sectionList.add(new CollapsibleSection(this, R.id.regionalHeading, R.id.regionalArrow, R.id.regionalLayout));
		sectionList.add(new CollapsibleSection(this, R.id.localHeading, R.id.localArrow, R.id.localLayout));
		sectionList.add(new CollapsibleSection(this, R.id.zonalHeading, R.id.zonalArrow, R.id.zonalLayout));

		if (savedInstanceState != null && savedInstanceState.containsKey(OPEN_SECTION)) {
			sectionList.get(savedInstanceState.getInt(OPEN_SECTION)).open();
		}

		findViewById(R.id.checklist3x3ResetTop).setOnClickListener(this);
		findViewById(R.id.checklist3x3ResetBottom).setOnClickListener(this);
	}

	@Override
	protected void updateSaveInstanceState(Bundle outState) {
		for (int i = 0; i < sectionList.size(); i++) {
			if (sectionList.get(i).getState() == OPEN)
				outState.putInt(OPEN_SECTION, i);
		}
	}

	public void onClick(View view) {

		for (CollapsibleSection section : sectionList) {
			section.close();
		}

		recursiveReset(findViewById(R.id.checklist3x3Form));

		((ScrollView) findViewById(R.id.checklistScroller)).scrollTo(0, 0);
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
