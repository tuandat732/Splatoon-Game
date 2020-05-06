package game.info;

import game.GameObject;
import game.Settings;
import game.scene.SceneManager;
import game.scene.game_over.ScoreScene;

import java.awt.*;

public class Time extends GameObject {
    int time;
    int phut;
    int giay;
    public Time(){
        time= Settings.time;
        phut=time/3600;
        giay=((time/60)%60)*60;
    }

    @Override
    public void run() {
        super.run();
        this.runtime();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.WHITE);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.F);
        newFont.getFamily();
        g.setFont(newFont);

        g.drawString("TIME: "+phut+":"+giay,1300,60);
    }
    int countfpt=60;
    private void runtime() {
        countfpt--;
        if (countfpt == 0) {
            countfpt=60;
            if (giay == 0) {
                phut--;
                giay = 60;
            }
            else if(giay>0){
                giay--;
            }
        }
        time--;
        if (time == 0) {
            SceneManager.signNewScene(new ScoreScene(Settings.mode));
        }
    }

}
