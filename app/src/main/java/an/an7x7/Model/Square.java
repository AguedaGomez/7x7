package an.an7x7.Model;

import android.graphics.Color;

/**
 * Created by Ague on 17/05/2016.
 */
public class Square {
    private int color;
    private boolean selectable = false;
    public boolean visited = false;

    public Square() {
        color = Color.LTGRAY;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
