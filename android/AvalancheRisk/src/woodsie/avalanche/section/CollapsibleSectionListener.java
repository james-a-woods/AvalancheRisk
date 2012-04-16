package woodsie.avalanche.section;

import java.util.Map;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;

public class CollapsibleSectionListener implements OnClickListener {

	private final Map<String, CollapsibleSection> sectionMap;

	private final ScrollView scrollView;

	public CollapsibleSectionListener(Map<String, CollapsibleSection> sectionMap, ScrollView scrollView) {
		this.sectionMap = sectionMap;
		this.scrollView = scrollView;
	}

	public void onClick(View view) {

		for (CollapsibleSection section : sectionMap.values()) {
			if (view.equals(section.getHeading()) || view.equals(section.getArrowImage())) {
				section.toggle();
			} else {
				section.close();
			}
		}

		scrollView.scrollTo(0, 0);
	}

}
