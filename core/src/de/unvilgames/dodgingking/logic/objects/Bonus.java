package de.unvilgames.dodgingking.logic.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;

import de.unvilgames.dodgingking.Resources;
import de.unvilgames.dodgingking.graph.SizeEvaluator;

/**
 * Created by timjk on 14.08.2017.
 */

public class Bonus extends Sprite implements Pool.Poolable {

    public static byte BONUS_TYPE_ATTACK = 0;
    public static byte BONUS_TYPE_HEALTH = 1;

    private int fieldX;
    private int fieldY;

    private byte bonusType;

    public Bonus() {

    }

    public void setup(int fx, int fy, byte bType, Resources res) {
        fieldX = fx;
        fieldX = fy;
        bonusType = bType;
        if (bType == BONUS_TYPE_ATTACK) {
            set(res.attackBonus);
        } else if (bType == BONUS_TYPE_HEALTH) {
            set(res.healthBonus);
        }
    }

    @Override
    public void reset() {

    }

    static final  Pool<Bonus> bonusPool = new Pool<Bonus>() {
        @Override
        protected Bonus newObject() {
            return new Bonus();
        }
    };

    public void release() {
        bonusPool.free(this);
    }

    public static Bonus Create(int fx, int fy, byte bType, Resources res) {
        Bonus bonus = bonusPool.obtain();
        bonus.setup(fx, fy, bType, res);
        return  bonus;
    }

    public void draw(SpriteBatch batch, SizeEvaluator sizeEvaluator) {
        setPosition(sizeEvaluator.getBaseScreenX(fieldX), sizeEvaluator.getBaseScreenY(fieldY));
        super.draw(batch);
    }

    public int getFieldX() {
        return  fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    public byte getBonusType() {
        return  bonusType;
    }

}
