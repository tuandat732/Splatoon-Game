package game.scene;

public class SceneManager {
    public static Scene currenScene;

    public static void signNewScene(Scene newScene){
        if(currenScene!=null){
            currenScene.clear();
        }
        newScene.init();
        currenScene=newScene;
    }
}
