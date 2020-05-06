package game.scene;

import game.Background;
import game.GameObject;
//import game.enemy.EnemySummoner;
import game.Settings;
import game.info.DienTich;
import game.info.LevelTable;
import game.info.Time;

import game.objects.createObjects;
import game.plane.Plane;
import game.plane.PlaneSummon;
import game.player.Player;
import game.player2.Player2;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class PlayScene extends Scene {
    Clip nhacnen;
    @Override
    public void init() {
        Settings.mode=1;
        nhacnen= AudioUtils.loadSound("assets/music/ngonnhat (online-audio-converter.com).wav");
        AudioUtils.loopForever(nhacnen);
        GameObject.recycle(LevelTable.class);
        GameObject.recycle(Background.class);
        GameObject.recycle(Player.class);
        GameObject.recycle(Player2.class);

        GameObject.recycle(Time.class);
        GameObject.recycle(DienTich.class);
        GameObject.recycle(createObjects.class);
        GameObject.recycle(PlaneSummon.class);
        Settings.vantoc2=2;
        Settings.vantoc1=2;
        Settings.size2=2;
        Settings.size1=2;
        Settings.long2=3;
        Settings.long1=3;
    }


    @Override
    public void clear() {
        AudioUtils.pause(nhacnen);
        GameObject.clearAll();
        GameObject.players.clear();
        GameObject.table.clear();
        GameObject.plane.clear();
    }
}
