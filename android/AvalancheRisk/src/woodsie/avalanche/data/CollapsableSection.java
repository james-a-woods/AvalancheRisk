package woodsie.avalanche.data;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import lombok.Getter;
import woodsie.avalanche.R;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class CollapsableSection {

	public enum State {
		OPEN, CLOSED
	}

	@Getter
	private State state;

	private final Drawable arrowUp;

	private final Drawable arrowDown;

	private final ImageView arrowImage;

	private final View sectionLayout;

	public CollapsableSection(Activity activity, ImageView arrowImage, View sectionLayout) {
		state = sectionLayout.getVisibility() == VISIBLE ? State.OPEN : State.CLOSED;

		arrowUp = activity.getResources().getDrawable(R.drawable.arrow_up);
		arrowDown = activity.getResources().getDrawable(R.drawable.arrow_down);

		this.arrowImage = arrowImage;
		this.sectionLayout = sectionLayout;
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
