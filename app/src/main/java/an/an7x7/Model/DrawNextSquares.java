package an.an7x7.Model;

import an.an7x7.GameCore.Game7x7Model;
import an.an7x7.framework.Graphics;

/**
 * Created by Ague on 05/06/2016.
 */
public class DrawNextSquares {

    float width, squareSide;
    int padding;
    Game7x7Model model;
    Graphics graphics;

    public DrawNextSquares (float width, float squareSide, int padding, Game7x7Model model, Graphics graphics) {
        this.width = width;
        this.squareSide = squareSide;
        this.padding = padding;
        this.model = model;
        this.graphics = graphics;
    }

    public void draw (int i, int j) {
       float x = width - (j*(squareSide/1.35f) + (j-1)*padding*2 + 40);
        float y = 150 + i * (squareSide/1.35f + padding*2);
        switch (model.level) {
            case 3:
                if (i == 0 && j==3)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[0]);
                else if (i==0 && j==2)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[1]);
                else if (i==0 && j==1)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[2]);
                else
                    graphics.drawRectStroke(x, y, squareSide / 1.35f, squareSide / 1.35f, model.GRAY);
                break;

            case 4:
                if (i == 0 && j==3)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[0]);
                else if (i==0 && j==2)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[1]);
                else if (i==0 && j==1)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[2]);
                else if (i==1 && j == 3)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[3]);
                else
                    graphics.drawRectStroke(x, y, squareSide / 1.35f, squareSide / 1.35f, model.GRAY);
                break;

            case 5:
                if (i == 0 && j==3)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[0]);
                else if (i==0 && j==2)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[1]);
                else if (i==0 && j==1)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[2]);
                else if (i==1 && j == 3)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[3]);
                else if (i==1 && j == 2)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[4]);
                else
                    graphics.drawRectStroke(x, y, squareSide / 1.35f, squareSide / 1.35f, model.GRAY);
                break;

            case 6:
                if (i == 0 && j==3)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[0]);
                else if (i==0 && j==2)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[1]);
                else if (i==0 && j==1)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[2]);
                else if (i==1 && j == 3)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[3]);
                else if (i==1 && j == 2)
                    graphics.drawRect(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[4]);
                else
                    graphics.drawRectStroke(x, y, squareSide / 1.35f, squareSide / 1.35f, model.squaresPreview[5]);
                break;
        }

    }
}
