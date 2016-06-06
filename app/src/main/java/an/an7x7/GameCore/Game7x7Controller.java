package an.an7x7.GameCore;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import an.an7x7.Model.DrawNextSquares;
import an.an7x7.Utilities.EndGameDialogFragment;
import an.an7x7.framework.Graphics;
import an.an7x7.framework.IGameController;
import an.an7x7.framework.TouchHandler;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class Game7x7Controller implements IGameController  {

    private static final int SQUARE_PADDING = 2;
    private static final int BOARD_DIMENSION = 7;

    private final float width;
    private final float height;
    private final Context context;
    private final FragmentManager fm;
    private final float squareSide;

    private float levelTextXposition;
    private float levelTextYposition;
    private int levelTextFontSize;
    private float leftMarginText;
    private float xtext;
    private float ytext;
    private float yLine;
    private int mainTextFontSize;
    private float nextLevelLinesYposition;
    private float comboTextYposition;
    private int numericDataFontSize;


    private Graphics graphics;
    private Game7x7Model model;
    private boolean GameOverdialogNotCreated = true;
    private DrawNextSquares nextSquares;
    private float progress;
    private int lastColorDeleted = model.GRAY;
    private Bitmap icon;



    public Game7x7Controller(float screenWidth, float screenHeight, Context context, FragmentManager fm){

        this.width = screenWidth;
        this.height = screenHeight;
        this.context = context;
        this.fm = fm;
        squareSide = screenWidth/7 - SQUARE_PADDING;
        graphics = new Graphics((int) width, (int) height);
        model = new Game7x7Model();
        xtext = width - (3*(squareSide/1.35f) + (2-1)*SQUARE_PADDING*2 + 40);
        ytext = height * 14 /100;
        nextSquares = new DrawNextSquares(width,height,squareSide,SQUARE_PADDING,model,graphics);
        progress = width/model.LINES_NEXT_LEVEL;
        calculateGUIposition();
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

        if (model.state == Game7x7Model.State.END_GAME && GameOverdialogNotCreated){
            Log.d("TEST","GAME OVER");
            GameOverdialogNotCreated = false;
            Bundle args = new Bundle();
            args.putString("score",""+model.score);
            EndGameDialogFragment dialogFragment = new EndGameDialogFragment ();
            dialogFragment.setArguments(args);
            dialogFragment.show(fm,"Game Over");
        }

    }

    private void calculateGUIposition(){

        yLine = height * 7 / 100; // 7% de la pantalla desde arriba
        levelTextXposition = width * 20 /100;
        levelTextYposition = height * 5 / 100;
        levelTextFontSize = (int) yLine * 50/100; // es una fuente y tiene que ser int.
        leftMarginText = width * 3 / 100;
        mainTextFontSize = levelTextFontSize/2;
        nextLevelLinesYposition = height*10/100;
        comboTextYposition = ytext+2*squareSide/1.35f;
        numericDataFontSize = levelTextFontSize*2;

    }


    @Override
    public Bitmap onDrawingRequested() {

        graphics.clear(Color.WHITE);
        float x, y;
        int lines2nextLevel = model.LINES_NEXT_LEVEL - model.lines;

        graphics.drawLine(0,yLine,width, yLine, Color.LTGRAY); // línea de división
        graphics.drawRect(0, 0, progress * model.lines, yLine, lastColorDeleted); // progreso
        graphics.drawText(lines2nextLevel + " LINES TO NEXT LEVEL", leftMarginText,nextLevelLinesYposition ,mainTextFontSize , Color.LTGRAY); // texto lineas que faltan para el siguiente nivel

        switch (model.level) {
            case 3:
                graphics.drawText("LEVEL 1", levelTextXposition , levelTextYposition, levelTextFontSize, Color.BLACK);
                break;
            case 4:
                graphics.drawText("LEVEL 2", levelTextXposition, levelTextYposition, levelTextFontSize, Color.BLACK);
                break;
            case 5:
                graphics.drawText("LEVEL 3", levelTextXposition, levelTextYposition , levelTextFontSize, Color.BLACK);
                break;
            case 6:
                graphics.drawText("LEVEL 4", levelTextXposition, levelTextYposition , levelTextFontSize, Color.BLACK);
                break;
        }


        graphics.drawText("UP NEXT", xtext, ytext, mainTextFontSize, Color.BLACK);
        graphics.drawText("SCORE", leftMarginText, ytext, mainTextFontSize, Color.BLACK);
        graphics.drawText(model.score + "", leftMarginText, ytext+ numericDataFontSize , numericDataFontSize, Color.BLACK);
        graphics.drawText("COMBO", leftMarginText,comboTextYposition , mainTextFontSize, Color.BLACK);
        graphics.drawText(model.comboCounter + "X", leftMarginText, comboTextYposition + numericDataFontSize, numericDataFontSize, Color.BLACK);

        for (int i = 0; i < 2; i++ ) {
            for (int j = 3; j > 0; j--) {
                nextSquares.draw(i,j);
            }

        }

        for(int r = 0 ; r < BOARD_DIMENSION; r++) {
            for (int c = 0; c < BOARD_DIMENSION; c++) {
                x = column2xScreen(c);
                y = row2yScreen(r);

                if (model.state == Game7x7Model.State.SQUARE_SELECTED && model.selectRow == r && model.selectColumn == c) {
                    graphics.drawRect(x - SQUARE_PADDING * 2, y - SQUARE_PADDING * 2, squareSide + SQUARE_PADDING * 4, squareSide + SQUARE_PADDING * 4, model.allSquares[r][c].getColor());
                    graphics.drawRect(x - SQUARE_PADDING * 2, y- SQUARE_PADDING * 2, squareSide + SQUARE_PADDING * 4, squareSide + SQUARE_PADDING * 4, Color.argb(30, 0, 0, 0));
                }
                else if(model.state == Game7x7Model.State.SQUARE_SELECTED){
                    if (!model.allSquares[r][c].selectable && model.allSquares[r][c].getColor() == model.GRAY ){
                        graphics.drawRect(x, y, squareSide, squareSide, model.GRAY);
                        graphics.drawLine(x, y, x + squareSide, y + squareSide, Color.WHITE);
                        graphics.drawLine(x + squareSide, y, x, y + squareSide, Color.WHITE);
                    }
                    else{
                        graphics.drawRect(x, y, squareSide, squareSide, model.allSquares[r][c].getColor());

                    }
                }
                else if (model.state == Game7x7Model.State.SQUARES_APPEAR && model.selectRow == r && model.selectColumn == c &&  model.allSquares[r][c].getColor()!= model.GRAY){
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
                    lastColorDeleted = model.allSquares[r][c].getColor();
                }
                else {
                    graphics.drawRect(x, y, squareSide, squareSide, model.allSquares[r][c].getColor());
                }



            }
        }

        return graphics.getFrameBuffer();
    }

    public void restartGame(){
        GameOverdialogNotCreated = true;
        model.restartGame();
    }

}
