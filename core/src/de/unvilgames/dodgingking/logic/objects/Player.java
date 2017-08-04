package de.unvilgames.dodgingking.logic.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by timjk on 04.08.2017.
 */

public class Player extends Sprite {

    private int fieldX;
    private int fieldY;

    public Player(int fx, int fy) {
        fieldX = fx;
        fieldY = fy;
    }

    public int getFieldX() {
        return fieldX;
    }

    public void setFieldX(int fx) {
        fieldX = fx;
    }

    public int getFieldY() {
        return fieldY;
    }

    public void setFieldY(int fy) {
        fieldY = fy;
    }

}
