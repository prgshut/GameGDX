package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Logo extends Sprite {
//    private Vector2 pos= new Vector2();// наша позиция
    private Vector2 drive = new Vector2();//движение
    private Vector2 direction = new Vector2();//направление
    private Vector2 touch = new Vector2();
    private Rect worldBounds;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds=worldBounds;
        setHeightProportion(0.1f);
//        pos.set(worldBounds.pos);
        setRight(worldBounds.getRight() - 0.05f);
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        move();
//        worldBounds.pos.set(touch);
//        System.out.println("pos.x "+pos.x+" pos.y "+pos.y);
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch= touch;
        direction.set((touch.cpy().sub(pos).setLength(0.01f)));
        return false;
    }

    private void move() {
        if(pos.dst2(newPos) > direction.len2()) {
            pos.add(direction);
        }
        else  {
            pos.set(newPos);
        }
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }
}
