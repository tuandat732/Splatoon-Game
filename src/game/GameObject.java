package game;

import game.boss.Boss;
import game.boss.BossBullet;
import game.boss.Bosscon;
import game.info.*;
import game.objects.Barrier;
import game.physics.BoxCollider;
import game.plane.Item;
import game.plane.Plane;
import game.plane.PlaneSummon;
import game.player.MiniS;
import game.player.Player;
import game.player.PlayerBullet;
import game.player2.MiniS2;
import game.player2.Player2;
import game.player2.PlayerBullet2;
import game.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject /* extends Object */ {
    // quan li doi tuong (static)
    public static ArrayList<GameObject> objects = new ArrayList<>();
    public static ArrayList<GameObject> players= new ArrayList<>();
    public static ArrayList<GameObject> plane=new ArrayList<>();
    public static ArrayList<GameObject> boss=new ArrayList<>();
    public static ArrayList<GameObject> table=new ArrayList<>();

    public static <E extends GameObject> E recycle(Class<E> cls) {
        // 1. findInactive >> if found >> reset >> return
        // 2. if not found >> create new >> return
        E object = findInactive(cls);
        if(object != null) {
            object.reset();
            return object;
        }
        // cls = Player.class
        // cls.getConstructor().newInstance() ~ new Player()
        try {
            object = cls.getConstructor().newInstance();
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static <E extends GameObject> E findInactive(Class<E> cls) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if(cls.isAssignableFrom(object.getClass())
                && !object.active) {
                return (E) object;
            }
        }
        return null;
    }

    public static <E extends GameObject> E findIntersects(Class<E> cls
            , BoxCollider hitBox,int findindex) {
        // E ~ Enemy | Player | EnemyBullet ..
        // cls ~ Enemy.class | Player.class | EnemyBullet.class ..
        // for(GameObject object : objects)
        // object.active
        // object.hitBox != null
        // object.hitBox.intersects(hitBox)
        // object la 1 doi tuong thuoc class cls
        // return object
        // neu khong tim duoc object thoa man >> return null
        for (int i = objects.size()-1; i >=0; i--) {
            GameObject object = objects.get(i);
            if(object.active
                && object.hitBox != null
                && object.hitBox.intersects(hitBox)
                && cls.isAssignableFrom(object.getClass())
                && object.index!=findindex) { //moi sua
                return (E) object;
            }
        }
        return null;
    }

    public static <E extends GameObject> E findIntersectsx(Class<E> cls
            , BoxCollider hitBox,int findindex) {
        for (int i = objects.size()-1; i >=0; i--) {
            GameObject object = objects.get(i);
            if(object.active
                    && object.hitBox != null
                    && object.hitBox.intersectsx(hitBox)
                    && cls.isAssignableFrom(object.getClass())
                    && object.index!=findindex) { //moi sua
                return (E) object;
            }
        }
        return null;
    }

    public static void renderAll(Graphics g) {
//        System.out.println(objects.size());
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if(object.active&&object.notin==false) {
                object.render(g);
            }
        }
        for (int i=0;i<players.size();i++){
            if(players.get(i).active){
                players.get(i).render(g);
            }
        }
        for(int i=0;i<boss.size();i++){
            if(boss.get(i).active) {
                boss.get(i).render(g);
            }
        }
        for(int i=0;i<plane.size();i++){
            if(plane.get(i).active){
            plane.get(i).render(g);}
        }
        for (int i=0;i<table.size();i++){
            table.get(i).render(g);
        }
    }

    public static void runAll() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            // object ~ Background, Player, Enemy
            if(object.active) {
                object.run();
            }
        }
}

    public static void clearAll(){
        objects.clear();
    }


    // dinh nghia doi tuong
    public Renderer renderer; // = null
    public Vector2D position;
    public Vector2D velocity;
    public BoxCollider hitBox; // = null
    public boolean active;
    public Vector2D anchor;
    public int index;
    public boolean notin=false;

    public GameObject() {
        if(this.getClass().isAssignableFrom(Player.class)||this.getClass().isAssignableFrom(Player2.class)
        ||this.getClass().isAssignableFrom(PlayerBullet.class)
        ||this.getClass().isAssignableFrom(PlayerBullet2.class)) {
            players.add(this);
            this.notin=true;
        }
        else if(this.getClass().isAssignableFrom(Plane.class)||this.getClass().isAssignableFrom(PlaneSummon.class)
        ||this.getClass().isAssignableFrom(Item.class)) {
            plane.add(this);
            this.notin=true;
        }
        else if(this.getClass().isAssignableFrom(Boss.class)||this.getClass().isAssignableFrom(Bosscon.class)){
            boss.add(this);
            this.notin=true;
        }

        else if(this.getClass().isAssignableFrom(LevelTable.class)
        ||this.getClass().isAssignableFrom(DienTich.class)
        ||this.getClass().isAssignableFrom(Time.class)
        ||this.getClass().isAssignableFrom(Level.class)){
            table.add(this);
            this.notin=true;
        }
        objects.add(this);
        position = new Vector2D(); // (0, 0)
        velocity = new Vector2D(); // (0, 0)
        active = true;
        anchor = new Vector2D(0.5, 0.5);
        index=objects.size();
    }

    public void render(Graphics g) {
        if(renderer != null) {
            renderer.render(g, this);
        }
    }

    public void run() {
//        position.add(velocity.x, velocity.y);
         position.add(velocity);
    }

    public void deactive() {
        active = false;
    }

    public void reset() {
        active = true;
    }

//    public void checkmap(){
//        if(this.position.x<0||position.x>1200-hitBox.width||position.y<0||position.y>800-hitBox.height)
//            this.deactive();
//    }

    @Override
    public String toString() {
        return position.x + " " + position.y;
    }


}
