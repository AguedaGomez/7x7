package an.an7x7.TestGrid;

import android.util.DisplayMetrics;

import an.an7x7.framework.GameActivity;
import an.an7x7.framework.IGameController;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class TestGrid extends GameActivity {
    private TestGridController testController;

    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        testController = new TestGridController( displayMetrics.widthPixels, displayMetrics.heightPixels,this);
        return testController;
    }
}
