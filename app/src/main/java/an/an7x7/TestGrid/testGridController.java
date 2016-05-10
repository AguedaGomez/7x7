package an.an7x7.TestGrid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.util.List;

import an.an7x7.framework.Graphics;
import an.an7x7.framework.IGameController;
import an.an7x7.framework.TouchHandler;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class TestGridController implements IGameController {

    private static final float SQUARE_PADDING = 2;

    private final float width;
    private final float height;
    private final Context context;
    private final float squareSide;

    private Graphics graphics;

    public TestGridController(float screenWidth, float screenHeight, Context context){
        Log.d("TEST"," Construyendo el controller. ");
        this.width = screenWidth;
        this.height = screenHeight;
        this.context = context;
        squareSide = screenWidth/7 - SQUARE_PADDING * 8;
    }

    private float xScreen2column(float xScreen){
        return (xScreen-SQUARE_PADDING)/(SQUARE_PADDING+squareSide);
    }

    @Override
    public void onUpdate(float deltaTime, List<TouchHandler.TouchEvent> touchEvents) {

    }

    @Override
    public Bitmap onDrawingRequested() {

        graphics.clear(Color.WHITE);

        for(int r = 0 ; )

        return null;
    }
}
