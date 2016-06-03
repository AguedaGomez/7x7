package an.an7x7.TestGrid;

import android.graphics.Color;

import java.util.Random;

import an.an7x7.Model.Square;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class TestGridModel {

    private static final int PURPLE = Color.rgb(153,51,255);
    private static final int BLUE = Color.rgb(51,204,255);
    private static final int YELLOW = Color.rgb(255,204,0);
    private static final int GREEN = Color.rgb(128,255,0);
    private static final int RED = Color.rgb(255,0,0);

    public Square[][] allSquares;
    public String state;
    public int selectRow;
    public int selectColumn;

    private Random randomRow;
    private Random randomColumn;
    private Random randomColor;
    private int[] colors  = {PURPLE, BLUE, YELLOW, GREEN, RED};



    public TestGridModel(){

        allSquares = new Square[7][7];
        randomColumn = new Random();
        randomRow = new Random();
        randomColor = new Random();
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

        if (rB <0) { // si fila es menor que cero, es que esta fuera (en la parte de arrriba de la pantalla).

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
                    findTargetableLocations();
                }

            }
            else {
                if (state == "selected") {
                    int currentColor = allSquares[selectRow][selectColumn].getColor();
                    allSquares[selectRow][selectColumn].setColor(Color.LTGRAY);
                    allSquares[rB][cB].setColor(currentColor);
                    state = "";
                    for (int i = 0; i<3; i++)
                        createSquareRandom();

                }
            }

        }

    }

    public void findTargetableLocations(){
        for (int row = 0; row < 7; row++)
            for (int column = 0; column < 7; column++){
                allSquares[row][column].selectable = false;
                allSquares[row][column].visited = false;
            }
        visitNeighbours(selectRow, selectColumn);
    }

    private void visitNeighbours(int sRow, int sColumn) {

        //Marco como visitado
        allSquares[sRow][sColumn].visited = true;

        //compruebo el cuadrado superior
        if(sRow > 0 ){ // si es == 0 no tiene superior
            if(!allSquares[sRow - 1][sColumn].visited) { // si el superior aun no ha sido visitado
                if (allSquares[sRow - 1][sColumn].getColor() == Color.LTGRAY) { // si el superior está vacío
                    allSquares[sRow - 1][sColumn].selectable = true;
                    visitNeighbours(sRow-1,sColumn); //llamada "recursiva"
                } else {
                    allSquares[sRow - 1][sColumn].selectable = false;
                }
            }
        }

        //compruebo el cuadrado inferior
        if(sRow < 6 ){ // si es == 6 no tiene inferior
            if(!allSquares[sRow + 1][sColumn].visited) { // si el inferior aun no ha sido visitado
                if (allSquares[sRow + 1][sColumn].getColor() == Color.LTGRAY) { // si el inferior está vacío
                    allSquares[sRow + 1][sColumn].selectable = true;
                    visitNeighbours(sRow + 1 ,sColumn); //llamada "recursiva"
                } else {
                    allSquares[sRow + 1][sColumn].selectable = false;
                }
            }
        }

        //compruebo el cuadrado derecho
        if(sColumn  < 6 ){ // si es == 6 no tiene cuadrado a su derecha
            if(!allSquares[sRow][sColumn + 1].visited) { // si el derecho aun no ha sido visitado
                if (allSquares[sRow][sColumn + 1].getColor() == Color.LTGRAY) { // si el derecho está vacío
                    allSquares[sRow ][sColumn + 1].selectable = true;
                    visitNeighbours(sRow  ,sColumn + 1); //llamada "recursiva"
                } else {
                    allSquares[sRow ][sColumn + 1].selectable = false;
                }
            }
        }

        //compruebo el cuadrado izquierdo
        if(sColumn  > 0 ){ // si es == 0 no tiene cuadrado a su izquierda
            if(!allSquares[sRow][sColumn - 1].visited) { // si el izquierdo aun no ha sido visitado
                if (allSquares[sRow][sColumn - 1].getColor() == Color.LTGRAY) { // si el izquierdo está vacío
                    allSquares[sRow ][sColumn - 1].selectable = true;
                    visitNeighbours(sRow  ,sColumn - 1); //llamada "recursiva"
                } else {
                    allSquares[sRow ][sColumn - 1].selectable = false;
                }
            }
        }
    }


    private void createSquareRandom() {
        int row = randomRow.nextInt(7-0)+0;
        int column = randomRow.nextInt(7-0)+0;
        allSquares[row][column].setColor(colors[randomColor.nextInt(4 - 0)]);
    }


}
