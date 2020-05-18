package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.ScaledButton;
import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Ship extends Sprite {
    private TextureRegion[] region;
    private Vector2 speed, touch, temp;
    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"),2);
        speed = new Vector2(0.01f,0f);
        touch = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        move();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.15f);
        setBottom(worldBounds.getBottom());
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        temp.set(touch);
        temp.sub(pos);
        temp.nor();
        temp.scl(speed);

        return false;
    }

    private void move() {
        if (touch.sub(pos).len()>temp.len()){
            System.out.println("temp.len "+ temp.len()+ " touch.sub(pos).len "+touch.sub(pos).len());
            pos.add(temp);
            System.out.println("pos.add.x " +pos.x+" pos.add.y "+pos.y);
        }else{
            pos.set(touch);
        }
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        this.touch.set(touch);
        return super.touchDragged(touch, pointer);
    }
}
