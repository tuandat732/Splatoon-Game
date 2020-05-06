package game.boss;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.player.MiniS;
import game.player2.MiniS2;
import game.renderer.Renderer;

public class MiniSBoss extends GameObject {
    public MiniSBoss() {
        //renderer = new Renderer("assets/images/player-bullets/blue/down/1.png");
        hitBox = new BoxCollider(this, 100, 100);
        anchor.set(0,0);
    }

    @Override
    public void run() {
        super.run();
//        this.checkMiniS2();
        this.checkMiniS();
        this.checkout();
    }

    private void checkout() {
        if(position.x> Settings.BACKGROUND_WIDTH
                ||position.x<0
                ||position.y>Settings.BACKGROUND_HEIGHT
                ||position.y<0)
            this.deactive();
    }

    private void checkMiniS() {
        MiniS miniS = GameObject.findIntersects(MiniS.class, hitBox,this.index);
        MiniS2 miniS2=GameObject.findIntersects(MiniS2.class,hitBox,this.index);
        if(miniS!=null && this.index<miniS.index){
            this.deactive();
        }
        else if(miniS2!=null && this.index<miniS2.index){
            this.deactive();
        }
    }
}
