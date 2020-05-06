package game.boss;

import game.GameObject;
import game.Settings;
import game.Vector2D;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.AudioUtils;
import tklibs.Mathx;

import javax.sound.sampled.Clip;

public class Boss extends GameObject {
    Renderer bt = new Renderer("assets/images/boss/boss to", "over", 15);

    int type;
    int hp;

    public Boss() {
        renderer = bt;
        position.set(600, 260);
        hitBox = new BoxCollider(this, 150, 150);
        hp = 1;
        for (int i = 0; i <Settings.maxbosscon ; i++) {
            Bosscon bosscon = GameObject.recycle(Bosscon.class);
            bosscon.position.set(this.position.x, this.position.y-200);
        }
    }


    @Override
    public void run() {
        super.run();
        this.skill();
        this.makebosscon();
    }

//    int count1 = 0;

    private void makebosscon() {
//        count1++;
        if (Settings.maxbosscon==0) {
            Settings.maxbosscon=5;
            for (int i = 0; i <Settings.maxbosscon ; i++) {
                Bosscon bosscon = GameObject.recycle(Bosscon.class);
                bosscon.position.set(this.position.x, this.position.y-200);
            }
//            count1 = 0;
        }
    }

    int count = 0;
    Clip gam;
    private void skill() {
        count++;
        if (count > 580) {
            type = Mathx.random(1, 3);
            count = 0;
            switch (type) {
                case 1: {
                    gam = AudioUtils.loadSound("assets/music/sfx/gam.wav");
                    AudioUtils.replay(gam);
                    for (int i = 0; i < 6; i++) {
                        BossBullet bossBullet = new BossBullet(Mathx.random(200, 1800), Mathx.random(0, 600), 1);
                    }
                    break;
                }
                case 2: {
                    for (int i = 0; i <= Settings.bossNumberBullet; i++) {
                        BossBullet bullet = new BossBullet(this.position.x, this.position.y, 2);
                        bullet.position.set(this.position.x + 10, this.position.y + 40);
                        bullet.velocity.setAngle(Math.toRadians(Settings.bossStartAngle + i * Settings.bossStepAngle));
                    }
                    break;
                }
                case 3:{
                    for (int i = 0; i <= Settings.bossNumberBullet; i++) {
                        BossBullet bullet = new BossBullet(this.position.x, this.position.y, 2);
                        bullet.position.set(this.position.x + 10, this.position.y + 40);
                        bullet.velocity.setAngle(Math.toRadians(Settings.bossStartAngle + i * Settings.bossStepAngle));
                    }
                }
            }
        }
    }
}
