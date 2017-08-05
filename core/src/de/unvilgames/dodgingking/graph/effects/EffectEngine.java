package de.unvilgames.dodgingking.graph.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import de.unvilgames.dodgingking.graph.SizeEvaluator;

/**
 * Created by timjk on 04.08.2017.
 */

public class EffectEngine {

    List<Effect> effects;
    public EffectEngine() {
        effects = new ArrayList<Effect>();
    }

    public void add(Effect effect) {
        effects.add(effect);
    }

    public void update(float delta) {
        int i = 0;
        while (i < effects.size()) {
            effects.get(i).update(delta);
            if(effects.get(i).isAlive) {
                i++;
            } else {
                effects.get(i).release();
                effects.remove(i);
            }
        }
    }

    public void draw(SpriteBatch batch, SizeEvaluator sizeEvaluator) {
        for(int i = 0; i < effects.size(); i++) {
            effects.get(i).draw(batch, sizeEvaluator);
        }
    }

    public void clear() {
        while(effects.size() > 0) {
            effects.get(0).release();
            effects.remove(0);
        }
    }

}
