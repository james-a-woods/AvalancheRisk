package woodsie.avalanche.section;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import woodsie.avalanche.R;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

@EqualsAndHashCode
public class CollapsibleSection {

	public static final String OPEN_SECTION = "openSection";

	public enum State {
		OPEN, CLOSED
	}

	@Getter
	private State state;

	@Getter(value = AccessLevel.PACKAGE)
	private final View heading;

	@Getter(value = AccessLevel.PACKAGE)
	private final ImageView arrowImage;

	private final View sectionLayout;

	private final Drawable arrowUp;

	private final Drawable arrowDown;

	public <T extends Activity & CollapsibleSectionParent> CollapsibleSection(T parent, int headingId, int arrowImageId, int sectionLayoutId) {
		heading = parent.findViewById(headingId);
		arrowImage = (ImageView) parent.findViewById(arrowImageId);
		sectionLayout = parent.findViewById(sectionLayoutId);

		arrowUp = parent.getResources().getDrawable(R.drawable.arrow_up);
		arrowDown = parent.getResources().getDrawable(R.drawable.arrow_down);

		state = sectionLayout.getVisibility() == VISIBLE ? State.OPEN : State.CLOSED;

		CollapsibleSectionListener sectionListener = CollapsibleSectionListener.getInstance(parent);
		heading.setOnClickListener(sectionListener);
		arrowImage.setOnClickListener(sectionListener);

	}

	public void open() {
		state = State.OPEN;
		arrowImage.setImageDrawable(arrowUp);
		sectionLayout.setVisibility(VISIBLE);
	}

	public void close() {
		state = State.CLOSED;
		arrowImage.setImageDrawable(arrowDown);
		sectionLayout.setVisibility(GONE);
	}

	public void toggle() {
		switch (state) {
		case OPEN:
			close();
			break;
		case CLOSED:
			open();
			break;
		}
	}
}
