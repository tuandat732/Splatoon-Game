package game.player2;

import game.GameObject;
import game.physics.BoxCollider;
import game.player.Explosion;
import game.player.MiniS;
import game.renderer.Renderer;

public class Explosion2 extends GameObject {
    public Explosion2(String type,double x,double y) {
        renderer = new Renderer("assets/images/player-bullets/blue/blue_ex/"+type, "stop",6);
        hitBox=new BoxCollider(this,50,50);
        anchor.set(0,0);
//        for (int i=(int)y;i<(int)y+50;i+=25){
//            for (int j=(int)x;j<(int)x+50;j+=25){
//                MiniS2 miniS=GameObject.recycle(MiniS2.class);
//                miniS.position.set(j,i);
//                miniS.index=this.index;
//            }
//        }
        MiniS2 miniS=GameObject.recycle(MiniS2.class);
                miniS.position.set(x,y);
                miniS.index=this.index;
    }

    @Override
    public void run() {
        super.run();
        this.checkthis();
    }

    private void checkthis() {
        Explosion2 explosion2=GameObject.findIntersectsx(Explosion2.class,hitBox,index);
        Explosion explosion=GameObject.findIntersectsx(Explosion.class,hitBox,index);
        if(explosion!=null&&this.index<explosion.index){
//            this.deactive();
            objects.remove(this);
        }
        else if(explosion2!=null&&this.index<explosion2.index){
//            this.deactive();
            objects.remove(explosion2);
        }

    }
}
