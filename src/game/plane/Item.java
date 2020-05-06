package game.plane;

import game.GameObject;
import game.Settings;
import game.objects.Barrier;
import game.physics.BoxCollider;
import game.player.Explosion;
import game.player.Player;
import game.player.PlayerBullet;
import game.player2.Explosion2;
import game.player2.Player2;
import game.player2.PlayerBullet2;
import game.renderer.Renderer;
import tklibs.Mathx;

public class Item extends GameObject {
    String[] types=new String[3];
    String type;
    public Item(){
        types[0]="speed";
        types[1]="size";
        types[2]="long";
        type=types[Mathx.random(0,2)];
        if(type=="speed") renderer=new Renderer("assets/images/items/giay.png");
        else if(type=="size") renderer=new Renderer("assets/images/items/nuoc.png");
        else if(type=="long") renderer=new Renderer("assets/images/items/sung.png");
        hitBox=new BoxCollider(this,30,30);
//        this.type=type;
    }

    @Override
    public void run() {
        super.run();
        this.checkBarrier();
        this.checkPlayer();
        this.checkplayerBullet();
        this.stopSetting();
    }

    private void stopSetting() {
        if(Settings.long1>5) Settings.long1=5;
        else if(Settings.long2>5) Settings.long2=5;
        else if(Settings.size1>5) Settings.size1=5;
        else if(Settings.size2>5) Settings.size2=5;
        else if(Settings.vantoc1>5) Settings.vantoc1=5;
        else if(Settings.vantoc2>5) Settings.vantoc2=5;
    }

    private void checkplayerBullet() {
        PlayerBullet playerBullet=GameObject.findIntersects(PlayerBullet.class,hitBox,this.index);
        PlayerBullet2 playerBullet2=GameObject.findIntersects(PlayerBullet2.class,hitBox,this.index);
        Explosion explosion=GameObject.findIntersectsx(Explosion.class,hitBox,this.index);
        Explosion2 explosion2=GameObject.findIntersects(Explosion2.class,hitBox,this.index);
        if((playerBullet!=null&&this.index<playerBullet.index)
        ||(playerBullet2!=null&&this.index<playerBullet2.index)
        ||(explosion!=null&&this.index<explosion.index)
        ||(explosion2!=null&&this.index<explosion2.index)){
            this.deactive();
        }

    }

    private void checkPlayer() {
        Player player=GameObject.findIntersects(Player.class,hitBox,this.index);
        Player2 player2=GameObject.findIntersects(Player2.class,hitBox,this.index);
        if(player!=null){
            if(this.type=="speed"){
                Settings.vantoc1+=1;
                this.deactive();}
            else if(this.type=="size"){
                Settings.size1+=1;
                this.deactive();
            }
            else if(type=="long"){
                Settings.long1+=1;
                this.deactive();
            }
        }
        if(player2!=null){
            if(this.type=="speed"){
                Settings.vantoc2+=1;
                this.deactive();}
            else if(this.type=="size"){
                Settings.size2+=1;
                this.deactive();
            }
            else if(type=="long"){
                Settings.long2+=1;
                this.deactive();
            }
        }
    }

    private void checkBarrier() {
        Barrier barrier=GameObject.findIntersects(Barrier.class,hitBox,this.index);
        if(barrier!=null){
            this.deactive();
        }
    }


}
