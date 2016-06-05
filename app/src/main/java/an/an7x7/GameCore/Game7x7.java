package an.an7x7.GameCore;

import android.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;

import an.an7x7.Utilities.EndGameDialogFragment;
import an.an7x7.framework.GameActivity;
import an.an7x7.framework.IGameController;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class Game7x7 extends GameActivity implements EndGameDialogFragment.NoticeDialogListener {
    private Game7x7Controller testController;

    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        testController = new Game7x7Controller( displayMetrics.widthPixels, displayMetrics.heightPixels,this,this.getFragmentManager());
        return testController;
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.d("TEST", "Se ha pulsado el TryAgain y Game7x7Activity lo ha captado");
        testController.restartGame();
        this.recreate();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.d("TEST", "Se ha pulsado el Exit y Game7x7Activity lo ha captado");
        finish();
    }
}
