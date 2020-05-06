package game.scene.welcome;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.renderer.Renderer;
import game.scene.PlayScene;
import game.scene.SceneManager;
import game.scene.chosemode.ModeScene;

public class BackgroundWelcome extends GameObject {
    public BackgroundWelcome(){
        renderer=new Renderer("assets/images/welcome","over",15);
        anchor.set(0,0);
        position.set(15,0);
    }
    @Override
    public void run(){
        super.run();
        this.checkChangeScene();
    }
    int count=0;
    private void checkChangeScene() {
        count++;
        if(KeyEventPress.isAnyKeyPress&&count>60){
            SceneManager.signNewScene(new ModeScene());
            count=0;
        }
    }
}
