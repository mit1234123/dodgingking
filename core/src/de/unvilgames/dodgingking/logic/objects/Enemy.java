package de.unvilgames.dodgingking.logic.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import de.unvilgames.dodgingking.Resources;
import de.unvilgames.dodgingking.graph.SizeEvaluator;
import de.unvilgames.dodgingking.logic.GameLogic;

/**
 * Created by timjk on 04.08.2017.
 */

public class Enemy extends Sprite {

    private static  final float BASE_ATTACK_TIME = 3.0f;

    private float timeSinceAttack;
    private float nextAttackTime;

    private boolean targetTiles[][];

    public interface EnemyAttackListener {
        void OnAttack(boolean[][] tiles);
    }

    private EnemyAttackListener attackListener;

    public Enemy(Resources res, EnemyAttackListener listener) {
        set(res.enemy);
        resetAttackTime();
        attackListener = listener;

        targetTiles = new boolean[GameLogic.MAX_BASE_X +1][];
        for (int i = 0; i < GameLogic.MAX_BASE_X +1; i++) {
            targetTiles[i] = new boolean[GameLogic.MAX_BASE_Y + 1];
        }
    }

    public void resetAttackTime() {
        timeSinceAttack = 0;
        nextAttackTime = BASE_ATTACK_TIME + MathUtils.random(2);
    }

    public void draw(SpriteBatch batch, SizeEvaluator sizeEvaluator) {
        setPosition(sizeEvaluator.getEnemyX(this), sizeEvaluator.getEnemyY(this));
        super.draw(batch);
    }

    public void update(float delta) {
        timeSinceAttack += delta;
        if (timeSinceAttack > nextAttackTime) {
            int col1 = MathUtils.random(GameLogic.MAX_BASE_X);
            int col2 = 0;
            do {
                col2 = MathUtils.random(GameLogic.MAX_BASE_X);
            } while (col1 == col2);

            for (int x = 0; x < GameLogic.MAX_BASE_X + 1; x++) {
                for (int y = 0; y < GameLogic.MAX_BASE_Y + 1; y++) {
                    targetTiles[x][y] = (x == col1 || x == col2);
                }
            }

            attackListener.OnAttack(targetTiles);
            resetAttackTime();
        }
    }

}
