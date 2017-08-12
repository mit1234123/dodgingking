package de.unvilgames.dodgingking.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import de.unvilgames.dodgingking.DodgingKing;
import de.unvilgames.dodgingking.graph.effects.EffectEngine;
import de.unvilgames.dodgingking.graph.effects.WarningEffect;
import de.unvilgames.dodgingking.logic.objects.Enemy;
import de.unvilgames.dodgingking.logic.objects.Player;

/**
 * Created by timjk on 04.08.2017.
 */

public class GameLogic implements Enemy.EnemyAttackListener, WarningEffect.WarningEffectListener {

    public static final int MAX_BASE_X = 3;
    public static final int MAX_BASE_Y = 3;
    private static final int DEFAULT_PLAYER_LIVES = 3;

    Player player;
    Enemy enemy;
    EffectEngine effectEngine;
    DodgingKing game;

    public GameLogic(DodgingKing _game) {
        game = _game;
        player = new Player(MathUtils.random(MAX_BASE_X), MathUtils.random(MAX_BASE_Y), game.res, DEFAULT_PLAYER_LIVES); // 0..3
        enemy = new Enemy(game.res, this);
        effectEngine = new EffectEngine();
    }

    public Player getPlayer() {
        return player;

    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void update(float delta) {
        effectEngine.update(delta);
        enemy.update(delta);
    }

    public boolean CanMove(int fx, int fy) {
        return (fx >= 0 && fx <= MAX_BASE_X) && (fy >= 0 && fy <= MAX_BASE_Y);
    }

    public void AssignPlayerPosition(int fx, int fy) {
        player.setFieldX(fx);
        player.setFieldY(fy);
    }

    public EffectEngine getEffectEngine() {
        return  effectEngine;
    }

    @Override
    public void OnAttack(boolean[][] tiles) {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                if (tiles[x][y]) {
                    WarningEffect.Create(x, y, effectEngine, game.res, this);
                }
            }
        }
    }

    @Override
    public void OnEffectOver(WarningEffect effect) {
        if (effect.getFieldX() == player.getFieldX() && effect.getFieldY() == player.getFieldY()) {
            player.takeDamage(1);
            if (player.getLives() == 0) {
                Gdx.app.exit();
            }
        }
    }
}
