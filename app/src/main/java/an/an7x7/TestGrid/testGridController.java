package an.an7x7.TestGrid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

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

        this.width = screenWidth;
        this.height = screenHeight;
        this.context = context;
        squareSide = screenWidth/7 - SQUARE_PADDING;
        graphics = new Graphics((int) width, (int) height);
    }

    private int xScreen2column(float xScreen){
        return (int)((xScreen - SQUARE_PADDING)/(SQUARE_PADDING + squareSide)); //pasarlo a entero o hacer división entera
    }

    private int yScreen2row(float yScreen) {
        return (int)((height - yScreen - SQUARE_PADDING + 7*(SQUARE_PADDING + squareSide))/(SQUARE_PADDING + squareSide));
    }

    private float column2xScreen(int column) {
        return SQUARE_PADDING + column * (SQUARE_PADDING + squareSide);
    }

    private float row2yScreen(int row) {
        return height - SQUARE_PADDING - row *(SQUARE_PADDING + squareSide);
    }

    @Override
    public void onUpdate(float deltaTime, List<TouchHandler.TouchEvent> touchEvents) {

    }

    @Override
    public Bitmap onDrawingRequested() {
        graphics.clear(Color.WHITE);
        float x,y;
        for(int r = 0 ; r <= 7; r++) {
            for (int c = 0; c < 7; c++) {
                x = column2xScreen(c);
                y = row2yScreen(r);
                graphics.drawRect(x, y,  squareSide, squareSide, Color.GRAY);
            }
        }

        return graphics.getFrameBuffer();
    }
}
