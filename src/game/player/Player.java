package game.player;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.Vector2D;
import game.objects.Barrier;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.Mathx;

import java.awt.*;

public class Player extends GameObject {
    public int hp;
    public boolean immune;

    Renderer topRenderer;Renderer leftRenderer;Renderer rightRenderer;Renderer downRenderer;
    Renderer paintdown;Renderer paintup;Renderer paintleft;Renderer paintright;
    Renderer btdown;Renderer btup;Renderer btright;Renderer btleft;
    public Player() {
//        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        topRenderer = new Renderer("assets/images/players/player1/up","over",20);
        leftRenderer = new Renderer("assets/images/players/player1/left","over",20);
        rightRenderer = new Renderer("assets/images/players/player1/right","over",20);
        downRenderer= new Renderer("assets/images/players/player1/down","over",20);
        paintdown=new Renderer("assets/images/players/player1/painted/boy-xuong-dinh-son.png");
        paintleft=new Renderer("assets/images/players/player1/painted/boy-trai-dinh-son.png");
        paintright=new Renderer("assets/images/players/player1/painted/boy-phai-dinh-son.png");
        paintup=new Renderer("assets/images/players/player1/painted/boy-len-dinh-son.png");
        btup=new Renderer("assets/images/players/player1/up/boy13.png");
        btdown=new Renderer("assets/images/players/player1/down/boy1.png");
        btright=new Renderer("assets/images/players/player1/right/boy9.png");
        btleft=new Renderer("assets/images/players/player1/left/boy5.png");
        renderer = btdown;
        position.set(300, 700);
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
        this.changeRenderer();
        this.checkImmune();
        //this.checkObject();
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

//    private void checkObject() {
//        Barrier barrier=GameObject.findIntersects(Barrier.class,hitBox,this.index);
//        if(barrier!=null){
//            check=true;
//            if(right) right=false;
//            else if(left) left=false;
//            else if(down) down=false;
//            else if(up) up=false;
//        }
//    }

    private void move() {
        double vx = 0;
        double vy = 0;

        if(KeyEventPress.isUpPress) {
            vy -= Settings.vantoc1;
        }
        else if(KeyEventPress.isRightPress) {
            vx += Settings.vantoc1;
        }
        else if(KeyEventPress.isDownPress) {
            vy += Settings.vantoc1;
        }
        else if(KeyEventPress.isLeftPress) {
            vx -= Settings.vantoc1;
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
        velocity.setLength(Settings.vantoc1);
    }

    int count = 0;
    private void fire() {
        count++;
        if(KeyEventPress.isFirePress && count>20&&!immune) {
            if(renderer==rightRenderer||renderer==btright){
                PlayerBullet bullet= GameObject.recycle(PlayerBullet.class);
                bullet.position.set(this.position.x,this.position.y);
                bullet.velocity.set(this.velocity.x+Settings.vantoc1,0);
                bullet.type="right";
            }
            else if(renderer==leftRenderer||renderer==btleft){
                PlayerBullet bullet= GameObject.recycle(PlayerBullet.class);
                bullet.position.set(this.position.x,this.position.y);
                bullet.velocity.set(this.velocity.x-Settings.vantoc1,0);
                bullet.type="left";
            }
            else if(renderer==topRenderer||renderer==btup){
                PlayerBullet bullet= GameObject.recycle(PlayerBullet.class);
                bullet.position.set(this.position.x,this.position.y);
                bullet.velocity.set(0,this.velocity.y-Settings.vantoc1);
                bullet.type="up";
            }
            else if(renderer==downRenderer||renderer==btdown){
                PlayerBullet bullet= GameObject.recycle(PlayerBullet.class);
                bullet.position.set(this.position.x,this.position.y);
                bullet.velocity.set(0,this.velocity.y+Settings.vantoc1);
                bullet.type="down";
            }

            count = 0;
        }
    }


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
