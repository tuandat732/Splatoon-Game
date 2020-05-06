package game.scene.game_over;

import game.GameObject;
import game.Settings;
import game.scene.Scene;

public class ScoreScene extends Scene {
    int type;
    public ScoreScene(int type){
        this.type=type;
    }
    @Override
    public void init() {
        Score score=new Score(type);
    }

    @Override
    public void clear() {
        GameObject.clearAll();
        GameObject.players.clear();
        GameObject.boss.clear();
        Settings.vantoc2=2;
        Settings.vantoc1=2;
        Settings.size2=2;
        Settings.size1=2;
        Settings.long2=3;
        Settings.long1=3;
    }
}
