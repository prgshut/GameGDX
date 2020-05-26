package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {

    private final List<T> activeObjects = new ArrayList<>();
    private final List<T> freeObjects = new ArrayList<>();

    protected abstract T newObject();

    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        System.out.println(getClass().getName() + " active/free: " + activeObjects.size() + "/" + freeObjects.size());
        return object;
    }

    public void updateActiveSprites(float delta) {
        for (Sprite sprite : activeObjects) {
            if (sprite.isDestroyed()) {
                continue;
            }
            sprite.update(delta);
        }
    }

    public void drawActiveSprites(SpriteBatch batch) {
        for (Sprite sprite : activeObjects) {
            if (sprite.isDestroyed()) {
                continue;
            }
            sprite.draw(batch);
        }
    }

    public void freeAllDestroyed() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T object = activeObjects.get(i);
            if (object.isDestroyed()) {
                free(object);
                i--;
            }
        }
    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }

    public void dispose() {
        activeObjects.clear();
        freeObjects.clear();
    }

    private void free(T object) {
        object.flushDestroy();
        if (activeObjects.remove(object)) {
            freeObjects.add(object);
        }
        System.out.println(getClass().getName() + " active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }

}
