package game.info;

import game.GameObject;
import game.renderer.Renderer;

public class Level extends GameObject {
    public int levelnow;
    Renderer level2;
    Renderer level3;
    Renderer level2_to_3;
    Renderer level3_to_4;
    Renderer level4_to_5;
    public Level(double x,double y,int level){
        level2=new Renderer("assets/images/info/thanh-level/2/2.png");
        level3=new Renderer("assets/images/info/thanh-level/3/3.png");
        level2_to_3=new Renderer("assets/images/info/thanh-level/2to3","stop",7);
        level3_to_4=new Renderer("assets/images/info/thanh-level/3to4","stop",7);
        level4_to_5=new Renderer("assets/images/info/thanh-level/4to5","stop",7);
        if(level==2) renderer=level2;
        else if(level==3) renderer=level3;
        position.set(x,y);
    }

    @Override
    public void run() {
        super.run();
        this.upto();
    }

    private void upto() {
        if(levelnow==3) renderer=level2_to_3;
        else if(levelnow==4) renderer=level3_to_4;
        else if(levelnow==5) renderer=level4_to_5;
        else if(levelnow==2) renderer=level2;
    }
}
