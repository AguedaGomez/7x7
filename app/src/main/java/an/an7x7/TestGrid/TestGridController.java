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
    private static final int BOARD_DIMENSION = 7;

    private final float width;
    private final float height;
    private final Context context;
    private final float squareSide;

    private Graphics graphics;
    private TestGridModel model;

    public TestGridController(float screenWidth, float screenHeight, Context context){

        this.width = screenWidth;
        this.height = screenHeight;
        this.context = context;
        squareSide = screenWidth/7 - SQUARE_PADDING;
        Log.d("TEST", "Alto: " + height + " Cuadrado: " + squareSide);
        graphics = new Graphics((int) width, (int) height);
        model = new TestGridModel();
    }

    private int xScreen2column(float xScreen){
        return (int)((xScreen - SQUARE_PADDING)/(SQUARE_PADDING + squareSide));
    }

    private int yScreen2row(float yScreen) {
        return (int)(7-((height - yScreen)/(squareSide + SQUARE_PADDING)));
    }

    private float column2xScreen(int column) {
        return SQUARE_PADDING + column * (SQUARE_PADDING + squareSide);
    }

    private float row2yScreen(int row) {
        return height - (7-row) * (squareSide + SQUARE_PADDING);
    }

    @Override
    public void onUpdate(float deltaTime, List<TouchHandler.TouchEvent> touchEvents) {
        for (TouchHandler.TouchEvent touchEvent: touchEvents)
            if (touchEvent.type == TouchHandler.TouchType.TOUCH_UP) {
                int columnBoard = xScreen2column(touchEvent.x), rowBoard = yScreen2row(touchEvent.y);
                //Log.d("TEST", "y: " + touchEvent.y );
                //Log.d("TEST", "Fila: " + rowBoard + " Columna: " + columnBoard );
                model.onTouch(columnBoard, rowBoard);
            }

    }



    @Override
    public Bitmap onDrawingRequested() {
        graphics.clear(Color.WHITE);
        float x,y;
        for(int r = 0 ; r < BOARD_DIMENSION; r++) {
            for (int c = 0; c < BOARD_DIMENSION; c++) {
                x = column2xScreen(c);
                y = row2yScreen(r);
                if (model.state == "selected" && model.selectRow == r && model.selectColumn == c) {
                    graphics.drawRect(x - SQUARE_PADDING * 2, y - SQUARE_PADDING * 2, squareSide + SQUARE_PADDING * 4, squareSide + SQUARE_PADDING * 4, model.allSquares[r][c].getColor());
                }else {
                    if (!model.allSquares[r][c].selectable && model.allSquares[r][c].getColor() == Color.LTGRAY ){
                        graphics.drawRect(x, y, squareSide, squareSide, Color.BLACK);
                    }
                    graphics.drawRect(x, y, squareSide, squareSide, model.allSquares[r][c].getColor());
                }
            }
        }

        return graphics.getFrameBuffer();
    }
}
