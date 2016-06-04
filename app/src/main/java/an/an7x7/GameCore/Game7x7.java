package an.an7x7.GameCore;

import android.util.DisplayMetrics;

import an.an7x7.framework.GameActivity;
import an.an7x7.framework.IGameController;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class Game7x7 extends GameActivity {
    private Game7x7Controller testController;

    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        testController = new Game7x7Controller( displayMetrics.widthPixels, displayMetrics.heightPixels,this,this.getFragmentManager());
        return testController;
    }

    public void aversifunciona(){


    }
}
