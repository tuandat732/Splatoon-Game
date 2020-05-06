package game.physics;

import game.GameObject;
import game.Vector2D;

public class BoxCollider {
    public Vector2D position;
    public int width;
    public int height;
    public Vector2D anchor;
    public double tamx;
    public double tamy;


    public BoxCollider(GameObject master, int width, int height) {
        this.position = master.position;
        this.width = width;
        this.height = height;
        this.anchor = master.anchor;
        this.tamx=master.position.x+(width/2);
        this.tamy=master.position.y+(height/2);
    }

    public BoxCollider(Vector2D position, Vector2D anchor, int width, int height) {
        this.position = position;
        this.anchor = anchor;
        this.width = width;
        this.height = height;
    }

    public BoxCollider shift(double vx, double vy) {
        Vector2D position = this.position.clone();
        position.add(vx, vy);
        return new BoxCollider(position, this.anchor, this.width, this.height);
    }

    public double top() {
        return position.y - anchor.y * height;
    }

    public double bot() {
        return top() + height;
    }

    public double left() {
        return position.x - anchor.x * width;
    }

    public double right() {
        return left() + width;
    }

    public boolean intersects(BoxCollider other) {
        return other.right() >= this.left()
                && other.left() <= this.right()
                && other.bot() >= this.top()
                && other.top() <= this.bot();
    }

    public boolean intersectsx(BoxCollider other) {
        return other.right() == this.right()
                && other.left() == this.left()
                && other.bot() == this.bot()
                && other.top() == this.top();
    }

//    public String intersectsMap(BoxCollider other) {
//        if (other.left() == this.right()) {
//            return "left";
//        } else if (other.right() == this.left()) {
//            return "right";
//        } else if (other.bot() == this.top()) {
//            return "bot";
//        } else if (other.top() == this.bot()) {
//            return "top";
//        }
//        return "false";
//    }
}

