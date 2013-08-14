package woodsie.avalanche.reduction.dialog;

import woodsie.avalanche.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

public class SelectionDialog extends DialogFragment {

	private SelectionDialogListener listener;

	private int values;

	public void show(FragmentManager manager, String tag, int values) {
		super.show(manager, tag);

		this.values = values;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.hazardLevel).setItems(values, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String choice = getResources().getStringArray(values)[which];
				listener.OnDialogClick(SelectionDialog.this, getTag(), choice);
				dismiss();
			}
		});

		return builder.create();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		listener = (SelectionDialogListener) activity;
	}

}
