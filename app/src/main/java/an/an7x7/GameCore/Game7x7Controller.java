package an.an7x7.GameCore;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.List;

import an.an7x7.Utilities.EndGameDialogFragment;
import an.an7x7.framework.Graphics;
import an.an7x7.framework.IGameController;
import an.an7x7.framework.TouchHandler;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class Game7x7Controller implements IGameController {

    private static final float SQUARE_PADDING = 2;
    private static final int BOARD_DIMENSION = 7;

    private final float width;
    private final float height;
    private final Context context;
    private final FragmentManager fm;
    private final float squareSide;

    private Graphics graphics;
    private Game7x7Model model;
    private boolean dialogNotCreated = true;

    public Game7x7Controller(float screenWidth, float screenHeight, Context context, FragmentManager fm){

        this.width = screenWidth;
        this.height = screenHeight;
        this.context = context;
        this.fm = fm;
        squareSide = screenWidth/7 - SQUARE_PADDING;
        graphics = new Graphics((int) width, (int) height);
        model = new Game7x7Model();
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
                model.onTouch(columnBoard, rowBoard);
            }
        model.update(deltaTime);

        if (model.state == Game7x7Model.State.END_GAME && dialogNotCreated){
            dialogNotCreated = false;
            EndGameDialogFragment dialogFragment = new EndGameDialogFragment ();
            dialogFragment.show(fm, "Sample Fragment");
        }

    }



    @Override
    public Bitmap onDrawingRequested() {

        graphics.clear(Color.WHITE);
        float x, y, xprev, yprev;

        for (int i = 0; i < 2; i++ ) {
            for (int j = 3; j > 0; j--) {
                xprev = width - (j*(squareSide/1.5f) + (j-1)*SQUARE_PADDING*2 + 40);
                yprev = 200 + i * (squareSide/1.5f + SQUARE_PADDING*2);
                graphics.drawRectStroke(xprev, yprev, squareSide/1.5f, squareSide/1.5f, Color.LTGRAY);
            }
        }
        for(int r = 0 ; r < BOARD_DIMENSION; r++) {
            for (int c = 0; c < BOARD_DIMENSION; c++) {
                x = column2xScreen(c);
                y = row2yScreen(r);

                if (model.state == Game7x7Model.State.SQUARE_SELECTED && model.selectRow == r && model.selectColumn == c) {
                    graphics.drawRect(x - SQUARE_PADDING * 2, y - SQUARE_PADDING * 2, squareSide + SQUARE_PADDING * 4, squareSide + SQUARE_PADDING * 4, model.allSquares[r][c].getColor());
                }
                else if(model.state == Game7x7Model.State.SQUARE_SELECTED){
                    if (!model.allSquares[r][c].selectable && model.allSquares[r][c].getColor() == Color.LTGRAY ){
                        graphics.drawRect(x, y, squareSide, squareSide, Color.LTGRAY);
                        graphics.drawLine(x, y, x + squareSide, y + squareSide, Color.WHITE);
                        graphics.drawLine(x + squareSide, y, x, y + squareSide, Color.WHITE);
                    }
                    else{
                        graphics.drawRect(x, y, squareSide, squareSide, model.allSquares[r][c].getColor());
                    }
                }
                else if (model.state == Game7x7Model.State.SQUARES_APPEAR && model.selectRow == r && model.selectColumn == c){
                    float coordx = x - SQUARE_PADDING * model.differencePositionBigSquare;
                    float coordy = y - SQUARE_PADDING * model.differencePositionBigSquare;
                    float side =  squareSide + SQUARE_PADDING * model.differenceSideBigSquare;
                    graphics.drawRect(coordx,coordy, side, side, model.allSquares[r][c].getColor());
                }
                else if (model.state == Game7x7Model.State.SQUARES_DESAPPEAR && model.allSquares[r][c].erasable) {
                    float coordx = x-SQUARE_PADDING * model.difPosBigTransparentSquare;
                    float coordy = y-SQUARE_PADDING * model.difPosBigTransparentSquare;
                    float side = squareSide+ SQUARE_PADDING * model.difSideBigTransparentSquare;
                    graphics.drawRect(coordx ,coordy ,side , side, model.allSquares[r][c].getColor());
                }
                else {
                    graphics.drawRect(x, y, squareSide, squareSide, model.allSquares[r][c].getColor());
                }
            }
        }

        return graphics.getFrameBuffer();
    }
}
