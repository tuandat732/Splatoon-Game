package game.player2;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.Vector2D;
import game.objects.Barrier;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.Mathx;

import java.awt.*;

public class Player2 extends GameObject {
    public int hp;
    public boolean immune;
    Renderer topRenderer;Renderer leftRenderer;Renderer rightRenderer;Renderer downRenderer;
    Renderer paintdown;Renderer paintup;Renderer paintleft;Renderer paintright;
    Renderer btdown;Renderer btup;Renderer btright;Renderer btleft;

    public Player2() {
//        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        topRenderer = new Renderer("assets/images/players/player2/up","over",20);
        leftRenderer = new Renderer("assets/images/players/player2/left","over",20);
        rightRenderer = new Renderer("assets/images/players/player2/right","over",20);
        downRenderer= new Renderer("assets/images/players/player2/down","over",20);
        paintdown=new Renderer("assets/images/players/player2/painted/girl-xuong-dinh-son.png");
        paintleft=new Renderer("assets/images/players/player2/painted/girl-trai-dinh-son.png");
        paintright=new Renderer("assets/images/players/player2/painted/girl-phai-dinh-son.png");
        paintup=new Renderer("assets/images/players/player2/painted/girl-len-dinh-son.png");
        btup=new Renderer("assets/images/players/player2/up/girl_run_8.png");
        btdown=new Renderer("assets/images/players/player2/down/girl_run1.png");
        btright=new Renderer("assets/images/players/player2/right/girl_run_12.png");
        btleft=new Renderer("assets/images/players/player2/left/girl_run_4.png");
        renderer = btdown;
        position.set(800, 700);
        hitBox = new BoxCollider(this, 30, 45);
        hp = 3;
        immune = false;
//        leftSphere = new Sphere();
//        rightSphere = new Sphere();
    }

    @Override
    public void run() {
        super.run(); // position.add(velocity)
        this.move();
        this.limitPosition();
        this.fire();
        this.checkImmune();
        this.changeRenderer();
//        this.setSpherePosition();
    }


    private void move() {
        double vx = 0;
        double vy = 0;

        if(KeyEventPress.isUpPress2) {
            vy -= Settings.vantoc2;
        }
        else if(KeyEventPress.isRightPress2) {
            vx += Settings.vantoc2;
        }
        else if(KeyEventPress.isDownPress2) {
            vy += Settings.vantoc2;
        }
        else if(KeyEventPress.isLeftPress2) {
            vx -= Settings.vantoc2;
        }
        Barrier block = GameObject.findIntersects(Barrier.class, this.hitBox.shift(vx, vy),this.index);
        if(block != null) {
            Vector2D boxAheadPosition = block.position;
            Vector2D slideThroughVector = this.trySlideThrough(boxAheadPosition, vx, vy);
            if(slideThroughVector == null) {
                vx = 0;
                vy = 0;
            } else {
                vx = slideThroughVector.x;
                vy = slideThroughVector.y;
            }
        }

        velocity.set(vx, vy);
        velocity.setLength(Settings.vantoc2);
    }

    public Vector2D trySlideThrough(Vector2D boxAheadPosition, double vx, double vy) {
        double deviation = Math.abs(vx) > 0
                ? position.y - boxAheadPosition.y
                : position.x - boxAheadPosition.x;
        if(Math.abs(deviation) > 22) { // edit this number for sliding smoother
            Vector2D nextToBoxAheadPosition = boxAheadPosition.clone();
            if(Math.abs(vx) > 0) { // move horizontal, slide up/down
                nextToBoxAheadPosition.add(0, Math.signum(deviation) * 44);
            } else { // move vertical, slide left/right
                nextToBoxAheadPosition.add(Math.signum(deviation) * 44, 0);
            }
            BoxCollider nextToBoxAheadHitBox = new BoxCollider(nextToBoxAheadPosition, this.anchor, 42, 42);
//            if(GameObject.findIntersects(Wall.class, nextToBoxAheadHitBox) != null) {
//                return null;
//            }
            if(GameObject.findIntersects(Barrier.class, nextToBoxAheadHitBox,this.index) != null) {
                return null;
            }
            Vector2D slideVector = new Vector2D(vx, vy);
            if(Math.abs(vx) > 0) {
                slideVector.y = nextToBoxAheadPosition.y - this.position.y;
            } else {
                slideVector.x = nextToBoxAheadPosition.x - this.position.x;
            }
            return slideVector;
        }
        return null;
    }

    int count = 0;
    private void fire() {
        count++;
        if(KeyEventPress.isFirePress2 && count>20&&!immune) {
            if(renderer==rightRenderer||renderer==btright){
                PlayerBullet2 bullet= GameObject.recycle(PlayerBullet2.class);
                bullet.position.set(this.position.x,this.position.y);
                bullet.velocity.set(this.velocity.x+Settings.vantoc2,0);
                bullet.type="right";
            }
            else if(renderer==leftRenderer||renderer==btleft){
                PlayerBullet2 bullet= GameObject.recycle(PlayerBullet2.class);
                bullet.position.set(this.position.x,this.position.y);
                bullet.velocity.set(this.velocity.x-Settings.vantoc2,0);
                bullet.type="left";
            }
            else if(renderer==topRenderer||renderer==btup){
                PlayerBullet2 bullet= GameObject.recycle(PlayerBullet2.class);
                bullet.position.set(this.position.x,this.position.y);
                bullet.velocity.set(0,this.velocity.y-Settings.vantoc2);
                bullet.type="up";
            }
            else if(renderer==downRenderer||renderer==btdown){
                PlayerBullet2 bullet= GameObject.recycle(PlayerBullet2.class);
                bullet.position.set(this.position.x,this.position.y);
                bullet.velocity.set(0,this.velocity.y+Settings.vantoc2);
                bullet.type="down";
            }

            count = 0;
        }
    }

    //choang
    int immuneCount = 0;
    private void checkImmune() {
        if(immune) {
            velocity.set(0,0);
            immuneCount++;
            if(immuneCount > 120) {
                immune = false;
            }
        } else {
            immuneCount = 0;
        }
    }


    private void changeRenderer() {
        if(velocity.x < 0) {
            renderer = leftRenderer;}
        else if(velocity.x > 0) {
            renderer = rightRenderer;}
        else if(velocity.y<0) {
            renderer = topRenderer;
        }
        else if(velocity.y>0) renderer=downRenderer;
        if(immune){
            if(renderer==leftRenderer||renderer==btleft) renderer=paintleft;
            else if(renderer==rightRenderer||renderer==btright) renderer=paintright;
            else if(renderer==topRenderer||renderer==btup) renderer=paintup;
            else if(renderer==downRenderer||renderer==btdown) renderer=paintdown;
        }
        else if(!immune&&velocity.x==0&&velocity.y==0){
            if(renderer==leftRenderer||renderer==paintleft) renderer=btleft;
            else if(renderer==rightRenderer||renderer==paintright) renderer=btright;
            else if(renderer==topRenderer||renderer==paintup) renderer=btup;
            else if(renderer==downRenderer||renderer==paintdown) renderer=btdown;
        }
    }


    private void limitPosition() {
        // 384 ~ BACKGROUND_WITH
        position.x = Mathx.clamp(position.x, 0, Settings.BACKGROUND_WIDTH - Settings.PLAYER_WIDTH);
        position.y = Mathx.clamp(position.y, 0, Settings.GAME_HEIGHT - Settings.PLAYER_HEIGHT);
    }

}
