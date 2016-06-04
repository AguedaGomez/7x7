package an.an7x7.Model;

import android.graphics.Color;

/**
 * Created by Ague on 17/05/2016.
 */
public class Square {

    private int color;
    public boolean selectable = false;
    public boolean visited = false;
    public boolean erasable = false;
    public int myRow;
    public int myColumn;

    public Square(int r, int c) {
        color = Color.LTGRAY;
        myRow = r;
        myColumn = c;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
