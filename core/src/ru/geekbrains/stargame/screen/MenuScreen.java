package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

import ru.geekbrains.stargame.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Vector2 pos;
    private Vector2 drive;
    private Vector2 direction;
    private Vector2 touch;
    @Override
    public void show() {
        super.show();
        img = new Texture("chel.jpg");
        pos= new Vector2();// наша позиция
        drive = new Vector2();//движение
        direction = new Vector2(); //направление
        touch=new Vector2();// полочаем место тыка
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        whereMove();
        batch.begin();
        batch.draw(img,pos.x,pos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight()-screenY);
        return false;
    }

    private void whereMove() {
        direction.x=touch.x;
        direction.y=touch.y;
        direction.sub(pos);
        direction.nor();
        drive=direction;// можно изменить скорость движения
        go();
    }

    private void go() {
        pos.add(drive);
        if ( Math.abs(pos.x-touch.x) <= Math.abs(direction.x)){
            pos.x=touch.x;
            drive.x=0;
        }
        if ( Math.abs(pos.y-touch.y) <= Math.abs(direction.y)){
            pos.y=touch.y;
            drive.y=0;
        }


    }
}
