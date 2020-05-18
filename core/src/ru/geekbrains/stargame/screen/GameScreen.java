package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprite.Background;
import ru.geekbrains.stargame.sprite.Ship;
import ru.geekbrains.stargame.sprite.Star;


public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private TextureAtlas atlasGame;
    private Star[] stars;
    private Ship ship;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        atlas = new TextureAtlas(Gdx.files.internal("textures/menuAtlas.tpack"));
        atlasGame = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        background = new Background(bg);
        stars = new Star[56];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        ship = new Ship(atlasGame);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        ship.touchDown(touch,pointer,button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        ship.touchUp(touch,pointer,button);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        ship.touchDragged(touch,pointer);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        ship.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        ship.draw(batch,0);
        batch.end();
    }
}
