package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.pool.EnemyPool;
import ru.geekbrains.stargame.pool.ExplosionPool;
import ru.geekbrains.stargame.sprite.Background;
import ru.geekbrains.stargame.sprite.MyShip;
import ru.geekbrains.stargame.sprite.Star;
import ru.geekbrains.stargame.utils.EnemyEmitter;


public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;
    private MyShip myShip;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;
    private BulletPool bulletPool;
    private Music music;
    private EnemyEmitter enemyEmitter;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        background = new Background(bg);
        stars = new Star[56];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas);
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds);
        myShip = new MyShip(atlas, bulletPool, explosionPool);
        enemyEmitter = new EnemyEmitter(atlas, enemyPool);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        free();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        myShip.resize(worldBounds);
        enemyEmitter.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        music.dispose();
        myShip.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        myShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        myShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        myShip.touchDown(touch,pointer,button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        myShip.touchUp(touch,pointer,button);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        myShip.touchDragged(touch,pointer);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        bulletPool.updateActiveSprites(delta);
        myShip.update(delta);
        enemyPool.updateActiveSprites(delta);
        explosionPool.updateActiveSprites(delta);
        enemyEmitter.generate(delta);
    }
    private void free() {
        bulletPool.freeAllDestroyed();
        enemyPool.freeAllDestroyed();
        explosionPool.freeAllDestroyed();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPool.drawActiveSprites(batch);
        myShip.draw(batch,0);
        enemyPool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);
        batch.end();
    }
}
