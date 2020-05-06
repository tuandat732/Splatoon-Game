package game.boss;

import game.GameObject;
import game.Settings;
import game.Vector2D;
import game.objects.Barrier;
import game.physics.BoxCollider;
import game.player.Explosion;
import game.player.Player;
import game.player.PlayerBullet;
import game.player2.Player2;
import game.player2.PlayerBullet2;
import game.renderer.Renderer;
import tklibs.AudioUtils;
import tklibs.Mathx;

public class BossBullet extends GameObject {
    public int type;
    int v;
    public BossBullet(double x,double y,int type){
        this.type=type;
        if(type==1) {
            v=Mathx.random(3,5);
            renderer = new Renderer("assets/images/boss/skillboss/skillboss2/0.png");
            //position.set(Mathx.random(600,1800),Mathx.random(-10,-50));
            hitBox = new BoxCollider(this, 240, 240);
//        position.set(Mathx.random(600,1100),-20);
            position.set(x, y);
            velocity.set(-v, v);
        }
        else if(type==2){
            renderer=new Renderer("assets/images/boss/skillboss/skillboss3","over",8);
            velocity.set(0,5);
            position.set(x,y);
            hitBox=new BoxCollider(this,45,45);
        }

    }

    @Override
    public void run() {
        super.run();

        if(this.type==1) {
            this.explosion();
        }
        this.checkPlayer();
        if(type==2){
            this.checkmap();
        }
    }

    private void checkmap() {
        if(position.x<-5
        ||position.x>Settings.BACKGROUND_WIDTH+5
        ||position.y<-5
        ||position.y>Settings.BACKGROUND_HEIGHT+5)
            this.deactive();
    }


    private void checkPlayer() {
        Player player=GameObject.findIntersects(Player.class,hitBox,this.index);
        Player2 player2=GameObject.findIntersects(Player2.class,hitBox,this.index);
        if(player!=null) player.immune=true;
        else if(player2!=null) player2.immune=true;
    }

    int count=0;
    private void explosion() {
        count++;
        if(count>Mathx.random(10,60)){
            ExplosionBoss explosionBoss=new ExplosionBoss(this.position.x-50,this.position.y-50);
            explosionBoss.position.set(this.position.x-150,this.position.y-100);
            this.deactive();
            count=0;
        }
    }
}
