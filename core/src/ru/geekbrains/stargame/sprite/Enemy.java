package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Ship;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.pool.ExplosionPool;


public class Enemy extends Ship {
    private static final Vector2 STAR_V= new Vector2(0,-0.5f);
    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound sound) {
        super(bulletPool, explosionPool, worldBounds, sound);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getTop()<=worldBounds.getTop()){
            v.set(v0);
            autoShoot(delta);
        } else {
            v.set(STAR_V);
        }
        if (getBottom() <= worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int damage,
            float reloadInterval,
            int hp,
            float height
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        this.reloadTimer = reloadInterval;
        this.hp = hp;
        setHeightProportion(height);
//        this.v.set(v0);
    }
}
