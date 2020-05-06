package game.scene.game_over;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.objects.Barrier;
import game.renderer.Renderer;
import game.scene.SceneManager;
import game.scene.welcome.WelcomeScene;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;
import java.awt.*;

public class
Score extends GameObject {
    public int mode;
    public Score(int type){
        this.mode=type;
        System.out.println(mode);
        System.out.println(Settings.MODE);
        if(mode == 1 ){
            renderer = new Renderer("assets/images/background/score_background_pvp.png");
        }
        if(mode == 2){
            renderer = new Renderer("assets/images/background/score_background_boss.png");
        }
        position.set(20,0);
        anchor.set(0,0);

    }
    @Override
    public void render(Graphics g) {
        super.render(g);

        g.setColor(Color.DARK_GRAY);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 8.F);
        newFont.getFamily();
        g.setFont(newFont);
        if(mode == 1){

            if(Settings.team1 >Settings.team2){
                g.drawString("PLAYER 1 WIN !",430,670);
            }
            else if (Settings.team1 < Settings.team2){
                g.drawString("PLAYER 2 WIN !",430,670);
            }
            else{
                g.drawString("TIE !!!",620,670);
            }
        }
        else if(mode == 2){
            if(Settings.team1 > Settings.team2){
                g.drawString("PLAYERS WIN !",430,670);
            }
            else if (Settings.team1 <Settings.team2){
                g.drawString("BOSS WIN !" ,530,670);
            }
            else{
                g.drawString("TIE !!!",620,670);
            }
        }
        g.drawString((int)Settings.team1 + " % ",290,275);
        g.drawString((int)Settings.team2 + " % ",1050,275);
    }

    @Override
    public void run() {
        super.run();

        this.checkChangeScene();
    }
    int count=0;
    private void checkChangeScene() {
        count++;
        if(KeyEventPress.isAnyKeyPress&&count>120){
            SceneManager.signNewScene(new WelcomeScene());
            count=0;
        }
    }
}
