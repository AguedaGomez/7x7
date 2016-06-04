package an.an7x7.Utilities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import an.an7x7.R;

/**
 * Created by Ague on 04/06/2016.
 */
public class EndGameDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        Log.d("TEST","Se muestra el EndGameDialog");
        return new AlertDialog.Builder(getActivity())
                .setTitle("Game Over")
                .setMessage("Score/n turn")
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
                }
                        //.setIcon(R.drawable.icon7x7);
    }
