package woodsie.avalanche.section;

import java.util.Collection;

import android.widget.ScrollView;

public interface CollapsibleSectionParent {

	public abstract Collection<CollapsibleSection> getSectionList();

	public abstract ScrollView getScroller();

}
