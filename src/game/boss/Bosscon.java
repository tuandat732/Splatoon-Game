package game.boss;

import game.GameObject;
import game.Settings;
import game.Vector2D;
import game.objects.Barrier;
import game.physics.BoxCollider;
import game.player.Player;
import game.player.PlayerBullet;
import game.player2.Player2;
import game.player2.PlayerBullet2;
import game.renderer.Renderer;
import tklibs.Mathx;

public class Bosscon extends GameObject {
    Renderer up;
    Renderer down;
    Renderer left;
    Renderer right;
    int type;
    public Bosscon(){
        down= new Renderer("assets/images/boss/bosscon2/quaivat 1_down.png");
        up=new Renderer("assets/images/boss/bosscon2/quaivat 1_up.png");
        right=new Renderer("assets/images/boss/bosscon2/quaivat 1_right.png");
        left=new Renderer("assets/images/boss/bosscon2/quaivat 1_left.png");
        renderer=down;
        hitBox=new BoxCollider(this,40,40);
    }

    @Override
    public void run() {
        super.run();
        this.choserun();
        this.checkBackground();
        this.checkPlayer();
        this.checkPlayerBullet();
    }

        private void checkPlayerBullet() {
            PlayerBullet playerBullet=GameObject.findIntersects(PlayerBullet.class,hitBox,this.index);
            PlayerBullet2 playerBullet2=GameObject.findIntersects(PlayerBullet2.class,hitBox,this.index);
            if(playerBullet!=null||playerBullet2!=null){
                this.deactive();
                Settings.maxbosscon--;
            }
        }

    private void checkPlayer() {
        Player player=GameObject.findIntersects(Player.class,hitBox,this.index);
        Player2 player2=GameObject.findIntersects(Player2.class,hitBox,this.index);
        if(player!=null) {
            player.immune=true;
        }
        else if(player2!=null){
            player2.immune=true;
        }
    }

    private void checkBackground() {
        if(this.position.x<=0 ){
            type = 4;
        }
        if(this.position.x > Settings.BACKGROUND_WIDTH-41){
            type = 3;
        }
        if(this.position.y <=0  ){
            type = 2;
        }
        if(this.position.y >Settings.GAME_HEIGHT-41){
            type = 1;
        }
    }

    int count=0;
    private void choserun() {
        count++;
        if(count>40) {
            type= Mathx.random(1,4);
            count=0;
        }

        switch (type){
            case 1:{ //top
                velocity.set(0,-4);
                renderer=up;
                break;
            }
            case 2:{ //down
                velocity.set(0,4);
                renderer=down;
                break;
            }
            case 3:{ //left
                velocity.set(-4,0);
                renderer=left;
                break;
            }
            case 4:{ //right
                velocity.set(4,0);
                renderer=right;
                break;
            }
        }
        Barrier block = GameObject.findIntersects(Barrier.class, this.hitBox.shift(velocity.x, velocity.y),this.index);
        if(block != null) {
            Vector2D boxAheadPosition = block.position;
            Vector2D slideThroughVector = this.trySlideThrough(boxAheadPosition, velocity.x, velocity.y);
            if(slideThroughVector == null) {
                velocity.x=0;
                velocity.y=0;
                type=Mathx.random(1,4);
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
}
