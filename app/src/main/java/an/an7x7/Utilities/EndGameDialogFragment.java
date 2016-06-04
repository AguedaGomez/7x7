package an.an7x7.Utilities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by Ague on 04/06/2016.
 */
public class EndGameDialogFragment extends DialogFragment {

    public Dialog onCreateDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle("Game Over").setMessage("Score/n turn").setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Log.d("TEST","SE muestra");
        builder.show();
        return builder.create();
    }
               //.setIcon(R.drawable.icon7x7);
}
