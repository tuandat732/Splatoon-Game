package game.scene;

import game.Background;
import game.GameObject;
import game.Settings;
import game.boss.Boss;
import game.info.DienTich;
import game.info.LevelTable;
import game.info.Time;

import game.objects.ObjectsMapBoss;
import game.plane.PlaneSummon;
import game.player.Player;
import game.player2.Player2;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class BossScene extends Scene {
    Clip boss;
        @Override
        public void init() {
            Settings.mode=2;
            Settings.maxbosscon=5;
            boss= AudioUtils.loadSound("assets/music/ChocoboRacingCidsTestCourse-HoaTau-3316605 (online-audio-converter.com).wav");
            AudioUtils.loopForever(boss);
            GameObject.recycle(LevelTable.class);
            GameObject.recycle(Background.class);
            GameObject.recycle(Player.class);
            GameObject.recycle(Player2.class);


            GameObject.recycle(Time.class);
            GameObject.recycle(DienTich.class);
            GameObject.recycle(ObjectsMapBoss.class);
            GameObject.recycle(Boss.class);
            GameObject.recycle(PlaneSummon.class);
        }

        @Override
        public void clear() {
            AudioUtils.pause(boss);
            GameObject.clearAll();
            GameObject.players.clear();
            GameObject.boss.clear();
            GameObject.table.clear();
            GameObject.plane.clear();
        }

    }


