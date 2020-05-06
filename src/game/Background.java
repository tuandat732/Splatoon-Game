package game;

import game.info.LevelTable;
import game.plane.Plane;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.util.Set;

public class Background extends GameObject {

    public Background() {
        if(Settings.MODE==1) {
//        image = SpriteUtils.loadImage("assets/images/background/0.png");
            renderer = new Renderer("assets/images/background/map_background.png");
            position.set(0, 0);
        }
        else if(Settings.MODE==2) {
            renderer = new Renderer("assets/images/background/map_background_boss.png");
            position.set(-7, 0);
        }

        anchor.set(0, 0);


    }
}
