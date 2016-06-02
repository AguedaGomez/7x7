package an.an7x7.TestGrid;

import an.an7x7.Model.Square;

/**
 * Created by NachoLR on 10/05/2016.
 */
public class TestGridModel {

    public Square[][] allSquares;


    public TestGridModel(){

        allSquares = new Square[7][7];

    }

    private void startLevel() {
        for (int row = 0; row < 7; row++)
            for (int column = 0; column < 7; column++)
                allSquares[row][column] = new Square();

    }

    public void onTouch(int cB, int rB) {

    }

}
