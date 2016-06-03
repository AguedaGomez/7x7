package an.an7x7.TestGrid;

import android.graphics.Color;

import java.util.Random;

import an.an7x7.Model.Square;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class TestGridModel {

    public Square[][] allSquares;
    public String state;
    public int selectRow;
    public int selectColumn;

    private Random randomRow;
    private Random randomColumn;



    public TestGridModel(){

        allSquares = new Square[7][7];
        randomColumn = new Random();
        randomRow = new Random();
        state = "";
        startLevel();

    }

    private void startLevel() {
        for (int row = 0; row < 7; row++)
            for (int column = 0; column < 7; column++)
                allSquares[row][column] = new Square();

    }

    public void onTouch(int cB, int rB) {

        int row, column;

        if (rB <0) {

            row = randomRow.nextInt(7-0)+0;
            column = randomRow.nextInt(7-0)+0;
            allSquares[row][column].setColor(Color.RED);

        }
        else {
            if (allSquares[rB][cB].getColor() != Color.LTGRAY) {
                if (state == "selected") {
                    state = "";
                }
                else {

                    state = "selected";
                    selectRow = rB;
                    selectColumn = cB;
                   // Log.d("TEST", "SE HA SELECCIONADO EL CUADRADO: " + rB + " " + cB);
                }

            }
            else {
                if (state == "selected") {
                    allSquares[selectRow][selectColumn].setColor(Color.LTGRAY);
                    allSquares[rB][cB].setColor(Color.RED);
                    state = "";

                }
            }

        }

    }

    private void findTargetLocation(){
        
    }

}
