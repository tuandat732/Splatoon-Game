package game.scene.chosemode;

import game.GameObject;
import game.Settings;
import game.scene.Scene;

public class ModeScene extends Scene {
    @Override
    public void init() {
        BackdgroundMode background =new BackdgroundMode();
//        ArrowMode arrowMode=new ArrowMode();
    }

    @Override
    public void clear() {
        GameObject.clearAll();
    }

}
