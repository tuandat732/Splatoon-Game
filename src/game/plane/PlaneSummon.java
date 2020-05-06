package game.plane;

import game.GameObject;
import game.Settings;
import game.player.Player;

public class PlaneSummon extends GameObject {
    int summondelay;
    public PlaneSummon(){
        summondelay=2800;
        velocity.set(1,0);
    }
    @Override
    public void run() {
        super.run();
        this.reuseplane();
    }
    int count=0;
    public void reuseplane() {
        count++;
        if(count>summondelay){
            Plane plane=new Plane();
            count=0;
        }
    }
}
