package game.scene.chosemode;

import game.Background;
import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.renderer.Renderer;
import game.scene.BossScene;
import game.scene.PlayScene;
import game.scene.SceneManager;
import game.scene.welcome.WelcomeScene;

public class BackdgroundMode extends GameObject {
    Renderer mode1=new Renderer("assets/images/chosemode/mode1.png");
    Renderer mode2=new Renderer("assets/images/chosemode/mode2.png");
    Renderer mode3=new Renderer("assets/images/chosemode/mode3.png");
    Renderer mode4=new Renderer("assets/images/chosemode/mode4.png");
    public BackdgroundMode(){
        renderer=mode1;
        anchor.set(0,0);
        position.set(-40,0);
        Settings.MODE=1;
    }

    @Override
    public void run() {
        super.run();
        if(Settings.MODE==1) renderer=mode1;
        else if(Settings.MODE==2) renderer=mode2;
        else if(Settings.MODE==3) renderer=mode3;
        else if(Settings.MODE==4) renderer=mode4;

        if(Settings.MODE==1&& KeyEventPress.isEnterPress==true){
            SceneManager.signNewScene(new PlayScene());
            System.out.println(Settings.MODE);
        }
        else if(Settings.MODE==2&& KeyEventPress.isEnterPress==true){
            SceneManager.signNewScene(new BossScene());
            System.out.println(Settings.MODE);
        }
        if(Settings.MODE==4&& KeyEventPress.isEnterPress==true){
            SceneManager.signNewScene(new WelcomeScene());
            System.out.println(Settings.MODE);
        }

    }

}
