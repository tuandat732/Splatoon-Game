package game.plane;

import game.GameObject;
import game.renderer.Renderer;
import tklibs.Mathx;

public class Plane extends GameObject {

    public Plane() {
        renderer = new Renderer("assets/images/plane","over",20);
        position.set(1500, Mathx.random(10, 600));
        velocity.set(-5, 0);
    }

    @Override
    public void run() {
        super.run();
        this.chose();
        this.move();
    }

    public void move() {
        if(this.position.x<-200) {
            objects.remove(this);
            plane.remove(this);
        }
    }

    int count = 0;

    public void chose() {
        count++;
        if (count > Mathx.random(60, 120)&&this.active) {
            Item item = GameObject.recycle(Item.class);
            item.position.set(this.position.x, this.position.y+Mathx.random(-100, 200));
            count = 0;
        }
    }
}

