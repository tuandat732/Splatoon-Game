package game.boss;

import game.GameObject;
import game.physics.BoxCollider;
import game.renderer.Renderer;

public class ExplosionBoss extends GameObject {
    public  ExplosionBoss(double x,double y){
        renderer=new Renderer("assets/images/boss/skillboss/skillboss2","stop",5);
        hitBox=new BoxCollider(this,200,200);
        anchor.set(0,0);
                for (int i = (int) y; i < (int) y + 200; i += 100) {
            for (int j = (int) x; j < (int) x + 200; j += 100) {
                MiniSBoss miniS = GameObject.recycle(MiniSBoss.class);
                miniS.position.set(j, i);
                miniS.index = this.index;
            }
 }
    }
//
//    @Override
//    public void checkmap() {
//        if(position.x<-200
//        ||position.y>1400
//        ||position.y<-500) this.deactive();
//    }
}
