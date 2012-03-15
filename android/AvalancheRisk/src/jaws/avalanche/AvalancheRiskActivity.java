package jaws.avalanche;

import woodsie.avalanche.R;
import jaws.avalanche.listener.ReductionListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class AvalancheRiskActivity extends Activity {
    private final ReductionListener listener = new ReductionListener(this);

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View topLevel = findViewById(R.id.reductionForm);
        setOnClickListener(topLevel, listener);

        listener.onClick(null);
    }

    // @Override
    // protected void onResume() {
    // super.onResume();
    //
    // ScrollView scroll = (ScrollView) findViewById(R.id.reductionScroller);
    // scroll.scrollTo(0, 0);
    //
    // listener.onClick(null);
    // }

    private void setOnClickListener(View view, OnClickListener listener) {

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                setOnClickListener(group.getChildAt(i), listener);
            }
        } else {
            view.setOnClickListener(listener);
        }
    }
}