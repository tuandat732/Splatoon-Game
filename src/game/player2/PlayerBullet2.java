package game.player2;

import game.GameObject;
import game.Settings;
import game.Vector2D;
import game.boss.Boss;
import game.objects.Barrier;
import game.physics.BoxCollider;
import game.player.Explosion;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;


public class PlayerBullet2 extends GameObject {
    public int damage;
    String type;

    public PlayerBullet2() {
//        velocity = new Vector2D(0,0);
        renderer = new Renderer("assets/images/player-bullets/blue/up");
        hitBox=new BoxCollider(this,25,25);
        damage=1;
    }


    @Override
    public void run() {
        super.run();
        this.fire();
        if(Settings.mode==1){
        this.checkPlayer();}
        this.checkout();
        this.checklava();
        this.checkBarrier();
    }
    private void checkBarrier() {
        Barrier block = GameObject.findIntersects(Barrier.class, this.hitBox.shift(velocity.x, velocity.y),this.index);
        if(block != null) {
            Vector2D boxAheadPosition = block.position;
            Vector2D slideThroughVector = this.trySlideThrough(boxAheadPosition, velocity.x, velocity.y);
            if(slideThroughVector == null) {
                velocity.x = 0;
                velocity.y = 0;
                this.deactive();
                Explosion2 explosion=new Explosion2(type,position.x-25,position.y-25);
                explosion.position.set(this.position.x-25,this.position.y-25);
                hitSound= AudioUtils.loadSound("assets/music/sfx/inkHit01.wav");
                AudioUtils.replay(hitSound);
                count=0;

            } else {
                velocity.x = slideThroughVector.x;
                velocity.y = slideThroughVector.y;
            }
        }
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

    private void checklava() {
        Barrier barrier=GameObject.findIntersects(Barrier.class,hitBox,this.index);
        if(barrier!=null && barrier.lava){
            this.deactive();
        }
    }

    private void checkout() {
        if(position.x> Settings.BACKGROUND_WIDTH
                ||position.x<0
                ||position.y>Settings.BACKGROUND_HEIGHT
                ||position.y<0)
            this.deactive();
    }

Clip hitSound;
    int count=0;
    public void fire(){
        renderer=new Renderer("assets/images/player-bullets/blue/"+type);
        int range=Settings.long2;
        count++;
        if( count>range*10){
            this.deactive();
            Explosion2 explosion=new Explosion2(type,position.x-25,position.y-25);
            explosion.position.set(this.position.x-25,this.position.y-25);
            hitSound= AudioUtils.loadSound("assets/music/sfx/inkHit01.wav");
            AudioUtils.replay(hitSound);
            count=0;
        }
    }

    private void checkPlayer() {
        Player player = GameObject.findIntersects(Player.class, hitBox,this.index);
        if(player != null && !player.immune) {
            player.immune=true;
            this.deactive();
        }
    }
}
