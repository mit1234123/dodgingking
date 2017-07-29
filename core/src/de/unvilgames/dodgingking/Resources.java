package de.unvilgames.dodgingking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by timjk on 29.07.2017.
 */

public class Resources {

    TextureAtlas gameSprites;

    public TextureRegion ground;
    public TextureRegion wall;

    public Resources() {
        gameSprites = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
        ground = gameSprites.findRegion("ground");
        wall = gameSprites.findRegion("wall");
    }

    public void dispose() {
        gameSprites.dispose();
    }

}
