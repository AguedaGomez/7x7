package an.an7x7.Utilities;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import an.an7x7.R;


/**
 * Created by Ague on 04/06/2016.
 */
public class EndGameDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        Log.d("TEST","Se muestra el EndGameDialog");
        Bundle mArgs = getArguments();
        String score = mArgs.getString("score");
        return new AlertDialog.Builder(getActivity())
                .setTitle("Game Over")
                .setMessage(" Score: " + score)
                .setIcon(R.drawable.icon_xhdpi)
                .setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        listener.onDialogPositiveClick(EndGameDialogFragment.this);
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(EndGameDialogFragment.this);

                    }
                }).create();
                }
                        //.setIcon(R.drawable.icon7x7);
    }
