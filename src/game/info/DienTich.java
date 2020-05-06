package game.info;

import game.GameObject;
import game.Settings;
import game.boss.MiniSBoss;
import game.player.MiniS;
import game.player2.MiniS2;

import java.awt.*;

public class DienTich extends GameObject {
    int mode;

    public DienTich() {
        this.mode = Settings.mode;
    }

    @Override
    public void run() {
        super.run();
        this.tinhS(this.mode);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.WHITE);


        g.drawString("S1:      " + (int) Settings.team1 + "      %", 1300, 165);
        if (this.mode == 1)
            g.drawString("S2:      " + (int) Settings.team2 + "      %", 1300, 410);
        else if (this.mode == 2) {
            g.drawString("S1:      " + (int) Settings.team1 + "      %", 1300, 410);
            g.drawString("S2:      " + (int) Settings.team2 + "      %", 1300, 645);
        }
    }
        private void tinhS ( int mode){
            if (mode == 1) {
                double count1 = 0;
                double count2 = 0;
                for (int i = 0; i < objects.size(); i++) {
                    if (objects.get(i).getClass().isAssignableFrom(MiniS.class) && objects.get(i).active == true) {
                        count1++;
                    } else if (objects.get(i).getClass().isAssignableFrom(MiniS2.class) && objects.get(i).active == true) {
                        count2++;
                    }
                }
                Settings.team1 = ((count1) * 1.2 / 355) * 100;
                Settings.team2 = ((count2) * 1.2 / 355) * 100;
            } else if (mode == 2) {
                double count1 = 0;
                double count2 = 0;
                for (int i = 0; i < objects.size(); i++) {
                    if ((objects.get(i).getClass().isAssignableFrom(MiniS.class) || objects.get(i).getClass().isAssignableFrom(MiniS2.class)) && objects.get(i).active == true) {
                        count1++;
                    } else if (objects.get(i).getClass().isAssignableFrom(MiniSBoss.class) && objects.get(i).active == true) {
                        count2++;
                    }
                }
                Settings.team1 = ((count1 / 1.2) / 448) * 100;
                Settings.team2 = (((count2+1) * 1.6) / 445) * 100;
            }
        }
    }
