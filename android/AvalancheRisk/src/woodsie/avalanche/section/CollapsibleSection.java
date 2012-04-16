package woodsie.avalanche.section;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import lombok.Getter;
import woodsie.avalanche.R;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class CollapsibleSection {

	public enum State {
		OPEN, CLOSED
	}

	@Getter
	private State state;

	@Getter
	private final View heading;

	@Getter
	private final ImageView arrowImage;

	@Getter
	private final View sectionLayout;

	private final Drawable arrowUp;

	private final Drawable arrowDown;

	public CollapsibleSection(Activity activity, int headingId, int arrowImageId, int sectionLayoutId) {
		heading = activity.findViewById(headingId);
		arrowImage = (ImageView) activity.findViewById(arrowImageId);
		sectionLayout = activity.findViewById(sectionLayoutId);

		state = sectionLayout.getVisibility() == VISIBLE ? State.OPEN : State.CLOSED;

		arrowUp = activity.getResources().getDrawable(R.drawable.arrow_up);
		arrowDown = activity.getResources().getDrawable(R.drawable.arrow_down);

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
