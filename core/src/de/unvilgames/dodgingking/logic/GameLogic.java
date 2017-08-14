package de.unvilgames.dodgingking.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import de.unvilgames.dodgingking.DodgingKing;
import de.unvilgames.dodgingking.graph.effects.EffectEngine;
import de.unvilgames.dodgingking.graph.effects.WarningEffect;
import de.unvilgames.dodgingking.logic.objects.Bonus;
import de.unvilgames.dodgingking.logic.objects.Enemy;
import de.unvilgames.dodgingking.logic.objects.Player;

/**
 * Created by timjk on 04.08.2017.
 */

public class GameLogic implements Enemy.EnemyAttackListener, WarningEffect.WarningEffectListener {

    public static final int MAX_BASE_X = 3;
    public static final int MAX_BASE_Y = 3;
    private static final int DEFAULT_PLAYER_LIVES = 3;
    private static final float BONUS_SPAWN_INTERWAL = 2.0f; //spawn bonus every 2 seconds
    private static final int MAX_BONUSES_ON_FIELD = 3;

    Player player;
    Enemy enemy;

    EffectEngine effectEngine;
    DodgingKing game;

    ArrayList<Bonus> bonuses;
    float gameTime;
    float lastBonusSpawn;


    public GameLogic(DodgingKing _game) {
        game = _game;
        player = new Player(MathUtils.random(MAX_BASE_X), MathUtils.random(MAX_BASE_Y), game.res, DEFAULT_PLAYER_LIVES); // 0..3
        enemy = new Enemy(game.res, this);
        effectEngine = new EffectEngine();

        bonuses = new ArrayList<Bonus>();
        gameTime = 0;
        lastBonusSpawn = 0;
    }

    public Player getPlayer() {
        return player;

    }

    public Enemy getEnemy() {
        return enemy;
    }

    private void SpawnRandomBonus() {
        int fx = 0;
        int fy = 0;
        boolean targetNonEmpty = true;
        do {
            fx = MathUtils.random(MAX_BASE_X);
            fy = MathUtils.random(MAX_BASE_Y);
            targetNonEmpty = player.getFieldX() == fx || fy == player.getFieldY();

            for (int i = 0; i < bonuses.size() && targetNonEmpty; i++) {
                if (bonuses.get(i).getFieldX() == fx && bonuses.get(i).getFieldY() == fy) {
                    targetNonEmpty = true;
                }
            }
        } while (targetNonEmpty);

        bonuses.add(Bonus.Create(fx, fy, MathUtils.random(3) == 0 ? Bonus.BONUS_TYPE_HEALTH : Bonus.BONUS_TYPE_ATTACK, game.res));
        lastBonusSpawn = gameTime;
    }

    public void update(float delta) {

        gameTime += delta;
        effectEngine.update(delta);
        enemy.update(delta);

        if (lastBonusSpawn + BONUS_SPAWN_INTERWAL < gameTime && bonuses.size()  < MAX_BONUSES_ON_FIELD) {
            SpawnRandomBonus();
        }
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
        }
    }

    public ArrayList<Bonus> getBonuses() {
        return bonuses;
    }

}
