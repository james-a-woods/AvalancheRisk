package woodsie.avalanche;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import java.util.Date;

import woodsie.avalanche.listener.ReductionListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class AvalancheRiskActivity extends Activity implements OnClickListener {
    private final ReductionListener listener = new ReductionListener(this);

    public static final long DISCLAIMER_TIMEOUT = 12 * 60 * 60 * 1000; // 12 hours

    Date lastWarningShown = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View topLevel = findViewById(R.id.reductionForm);
        setRecursiveOnClickListener(topLevel, listener);

        View continueButton = findViewById(R.id.frontContinueButton);
        continueButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScrollView scroll = (ScrollView) findViewById(R.id.reductionScroller);
        scroll.scrollTo(0, 0);

        listener.onClick(null);

        if (lastWarningShown == null || (new Date().getTime() - lastWarningShown.getTime()) > DISCLAIMER_TIMEOUT) {
            showDisclaimer();
        }
    }

    private void setRecursiveOnClickListener(View view, OnClickListener listener) {

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                setRecursiveOnClickListener(group.getChildAt(i), listener);
            }
        } else {
            view.setOnClickListener(listener);
        }
    }

    public void onClick(View v) {
        hideDisclaimer();
    }

    private void hideDisclaimer() {
        View page = findViewById(R.id.reductionMethodScreen);
        page.setVisibility(VISIBLE);

        page = findViewById(R.id.disclaimerScreen);
        page.setVisibility(INVISIBLE);

        lastWarningShown = new Date();
    }

    private void showDisclaimer() {
        View page = findViewById(R.id.disclaimerScreen);
        page.setVisibility(VISIBLE);

        page = findViewById(R.id.reductionMethodScreen);
        page.setVisibility(INVISIBLE);

        lastWarningShown = new Date();
    }
}