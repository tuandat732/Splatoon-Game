package game;

import game.player.Player;
import tklibs.Mathx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by huynq on 7/4/17.
 */

public class Program {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Xplatoon");
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                KeyEventPress.isAnyKeyPress=true;
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    KeyEventPress.isFirePress = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    KeyEventPress.isUpPress2 = true;
                    Settings.MODE=1;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    KeyEventPress.isLeftPress2 = true;
                    if(Settings.MODE<1) Settings.MODE=1;
                    else Settings.MODE--;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    KeyEventPress.isDownPress2 = true;
                    Settings.MODE=4;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    KeyEventPress.isRightPress2 = true;
                    if(Settings.MODE>3) Settings.MODE=3;
                    else Settings.MODE++;
                }
                if(e.getKeyCode() == KeyEvent.VK_ALT) {
                    KeyEventPress.isFirePress2 = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    KeyEventPress.isEnterPress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                KeyEventPress.isAnyKeyPress=false;
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    KeyEventPress.isFirePress = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    KeyEventPress.isUpPress2 = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    KeyEventPress.isLeftPress2 = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    KeyEventPress.isDownPress2 = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    KeyEventPress.isRightPress2 = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_ALT) {
                    KeyEventPress.isFirePress2 = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    KeyEventPress.isEnterPress = false;
                }
            }
        });

        GamePanel panel = new GamePanel();
        panel.setBackground(Color.CYAN);
        window.add(panel);
        panel.setPreferredSize(
                new Dimension(Settings.GAME_WIDTH, Settings.GAME_HEIGHT)
        );
        window.pack();

        window.setVisible(true);

        panel.gameLoop(); // start game

        // ctrl + ? // comment / uncomment code
        // alt + enter // fix code
        // (fn +) shift + f6 // rename
        // ctrl + alt + l // format code
    }
}
