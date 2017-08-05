package de.unvilgames.dodgingking.graph.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;

import de.unvilgames.dodgingking.graph.SizeEvaluator;

/**
 * Created by timjk on 04.08.2017.
 */

public abstract class Effect implements Pool.Poolable {

    protected  boolean isAlive;
    protected  float timeAlive;

    public Effect() {
        isAlive = false;
        timeAlive = 0;
    }

    @Override
    public void reset() {

    }

    public void init(EffectEngine parent) {
        isAlive = true;
        timeAlive = 0;
        parent.add(this);
    }

    public void update(float delta) {
        timeAlive += delta;
    }

    public abstract void draw(SpriteBatch batch, SizeEvaluator sizeEvaluator);

    public boolean isAlive() {
        return isAlive;
    }

    public abstract void release();
}
