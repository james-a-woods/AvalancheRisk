package woodsie.avalanche.section;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;

class CollapsibleSectionListener implements OnClickListener {

	private static final Map<CollapsibleSectionParent, CollapsibleSectionListener> INSTANCE_MAP =
	        new HashMap<CollapsibleSectionParent, CollapsibleSectionListener>();

	private final Collection<CollapsibleSection> sectionList;

	private final ScrollView scroller;

	private CollapsibleSectionListener(CollapsibleSectionParent parent) {
		sectionList = parent.getSectionList();
		scroller = parent.getScroller();
	}

	static CollapsibleSectionListener getInstance(CollapsibleSectionParent parent) {
		if (!INSTANCE_MAP.containsKey(parent)) {
			synchronized (INSTANCE_MAP) {
				if (!INSTANCE_MAP.containsKey(parent)) {
					INSTANCE_MAP.put(parent, new CollapsibleSectionListener(parent));
				}
			}
		}

		return INSTANCE_MAP.get(parent);
	}

	public void onClick(View view) {

		for (CollapsibleSection section : sectionList) {
			if (view == section.getHeading() || view == section.getArrowImage()) {
				section.toggle();
			} else {
				section.close();
			}
		}

		scroller.scrollTo(0, 0);
	}

}
