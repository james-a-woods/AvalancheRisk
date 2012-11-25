package woodsie.avalanche.reduction;

import woodsie.avalanche.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class HazardFragment extends DialogFragment {

	public interface HazardDialogListener {
		public void OnDialogClick(HazardFragment dialog, int which);

		public int getInitialValue(HazardFragment dialog);
	}

	HazardDialogListener listener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.hazardLevel).setItems(R.array.hazardLevelValues, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				listener.OnDialogClick(HazardFragment.this, which);
				dismiss();
			}
		});
		return builder.create();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		listener = (HazardDialogListener) activity;
	}

}
