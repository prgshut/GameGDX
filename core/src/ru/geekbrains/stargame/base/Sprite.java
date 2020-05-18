package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.math.Rect;

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame = 0;

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
    }
    public Sprite(TextureRegion region, int frame) {
        regions = new TextureRegion[frame];
        int width = region.getRegionWidth()/frame;
        for (int i = 0; i < regions.length; i++) {
            regions[i] = new TextureRegion(region,i*width,0,width,region.getRegionHeight() );
        }

    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void update(float delta) {

    }
    public void draw(SpriteBatch batch, int frame) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    public void resize(Rect worldBounds) {
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
