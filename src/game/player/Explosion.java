package game.player;

import game.GameObject;
import game.boss.Boss;
import game.physics.BoxCollider;
import game.player2.Explosion2;
import game.renderer.Renderer;

public class Explosion extends GameObject {
    public Explosion(String type, double x, double y) {
        renderer = new Renderer("assets/images/player-bullets/pink/pink-ex/" + type, "stop",6);
        hitBox = new BoxCollider(this, 50, 50);
        anchor.set(0,0);
//        for (int i = (int) y; i < (int) y + 50; i += 25) {
//            for (int j = (int) x; j < (int) x + 50; j += 25) {
//                MiniS miniS = GameObject.recycle(MiniS.class);
//                miniS.position.set(j, i);
//                miniS.index = this.index;
//            }
// }
        MiniS miniS=GameObject.recycle(MiniS.class);
        miniS.position.set(x,y);
        miniS.index=this.index;
    }

    @Override
    public void run() {
        super.run();
        this.checkthis();
        this.checkboss();//check no tu va cham vao no
    }

    private void checkboss() {
        Boss boss=GameObject.findIntersects(Boss.class,hitBox,this.index);
        if(boss!=null)
            this.deactive();
    }

    private void checkthis() {
        Explosion explosion=GameObject.findIntersectsx(Explosion.class,hitBox,this.index);
        Explosion2 explosion2=GameObject.findIntersectsx(Explosion2.class,hitBox,this.index);
        if(explosion!=null && this.index<explosion.index){
            objects.remove(explosion);
        }
        else if(explosion2!=null&&this.index<explosion2.index){
            objects.remove(this);
        }
    }
}



