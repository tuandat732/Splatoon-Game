package game;

//import game.enemy.EnemySummoner;
import game.player.Player;
import game.scene.PlayScene;
import game.scene.Scene;
import game.scene.SceneManager;
import game.scene.welcome.WelcomeScene;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    double fps;

    public GamePanel() {
        SceneManager.signNewScene(new WelcomeScene());
        fps = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // JPanel.paint()
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

//        background.render(g);
//        player.render(g);
//        enemy.render(g);
        GameObject.renderAll(g);

    }

    public void runAll() {
//        background.run();
//        enemy.run();
        GameObject.runAll();// bulletsRun()
    }

    public void gameLoop() {
        long lastTime = 0;
        long delay = 1000 / 70; // ~ 17
        while(true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastTime > delay) {
                fps = 1000 / (currentTime - lastTime);
                repaint(); // ~ call paint()
                runAll();
                lastTime = currentTime;
            }
        }
    }
}
