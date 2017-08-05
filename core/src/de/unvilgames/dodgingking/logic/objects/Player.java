package de.unvilgames.dodgingking.logic.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.unvilgames.dodgingking.Resources;
import de.unvilgames.dodgingking.graph.SizeEvaluator;

/**
 * Created by timjk on 04.08.2017.
 */

public class Player extends Sprite {

    private int fieldX;
    private int fieldY;

    public Player(int fx, int fy, Resources res) {
        fieldX = fx;
        fieldY = fy;
        set(res.player);
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

    public void draw(SpriteBatch batch, SizeEvaluator sizeEvaluator) {
        setPosition(sizeEvaluator.getBaseScreenX(fieldX), sizeEvaluator.getBaseScreenY(fieldY));
        super.draw(batch);
    }

}
