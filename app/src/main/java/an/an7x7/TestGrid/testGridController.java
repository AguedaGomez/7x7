package an.an7x7.TestGrid;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.List;

import an.an7x7.framework.IGameController;
import an.an7x7.framework.TouchHandler;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class testGridController implements IGameController {

    public testGridController(float screenWidth, float screenHeight){
        Log.d("TEST"," Construyendo el controller. ");

    }

    @Override
    public void onUpdate(float deltaTime, List<TouchHandler.TouchEvent> touchEvents) {

    }

    @Override
    public Bitmap onDrawingRequested() {
        return null;
    }
}
