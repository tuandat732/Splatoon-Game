package game.objects;

import game.GameObject;
import game.physics.BoxCollider;
import game.renderer.Renderer;



public class Barrier extends GameObject {
    public boolean lava=false;
    public boolean hola=false;
    public Barrier(int x, int y, int width, int height , String url){
        hitBox = new BoxCollider(this,width,height);
        renderer = new Renderer(url);
        position.set(x,y);
        anchor.set(0,0);


    }
}
