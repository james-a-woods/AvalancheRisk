package woodsie.avalanche.info;

import static woodsie.avalanche.section.CollapsibleSection.OPEN_SECTION;
import static woodsie.avalanche.section.CollapsibleSection.State.OPEN;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import woodsie.avalanche.R;
import woodsie.avalanche.section.CollapsibleSection;
import woodsie.avalanche.section.CollapsibleSectionParent;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

public class InfoActivity extends Activity implements CollapsibleSectionParent {

	@Getter
	private List<CollapsibleSection> sectionList;

	@Getter
	private ScrollView scroller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		sectionList = new ArrayList<CollapsibleSection>();
		scroller = (ScrollView) findViewById(R.id.infoScroller);

		sectionList.add(new CollapsibleSection(this, R.id.threex3Heading, R.id.threex3Arrow, R.id.threex3Layout));

		if (savedInstanceState != null && savedInstanceState.containsKey(OPEN_SECTION)) {
			sectionList.get(savedInstanceState.getInt(OPEN_SECTION)).open();
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		for (int i = 0; i < sectionList.size(); i++) {
			if (sectionList.get(i).getState() == OPEN)
				outState.putInt(OPEN_SECTION, i);
		}
	}

}
