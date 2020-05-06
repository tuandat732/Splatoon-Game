package game.info;

import game.GameObject;
import game.Settings;
import game.renderer.Renderer;

public class LevelTable extends GameObject {
    Level player1_size;
    Level player1_long;
    Level player1_speed;
    Level player2_size;
    Level player2_long;
    Level player2_speed;
    public LevelTable(){
        if(Settings.mode==1)
        renderer = new Renderer("assets/images/info/thanh_info.png");
        else if(Settings.mode==2)
            renderer=new Renderer("assets/images/info/thanhboss.png");
        position.set(1200,0);
        anchor.set(0,0);
        player1_size=new Level(1422,221,2);
        player1_long=new Level(1422,222+46,3);
        player1_speed=new Level(1422,222+46*2,2);
        player2_size=new Level(1422,465,2);
        player2_long=new Level(1422,465+46,3);
        player2_speed=new Level(1422,465+46*2,2);
    }

    @Override
    public void run() {
        super.run();
        if(Settings.long1==3) player1_long.levelnow=3;
        else if(Settings.long1==4) player1_long.levelnow=4;
        else if(Settings.long1==5) player1_long.levelnow=5;

        if(Settings.long2==3) player2_long.levelnow=3;
        else if (Settings.long2==4) player2_long.levelnow=4;
        else if (Settings.long2==5) player2_long.levelnow=5;

        if(Settings.size1==3) player1_size.levelnow=3;
        else if(Settings.size1==4) player1_size.levelnow=4;
        else if(Settings.size1==5) player1_size.levelnow=5;

        if(Settings.size2==3) player2_size.levelnow=3;
        else if(Settings.size2==4) player2_size.levelnow=4;
        else if(Settings.size2==5) player2_size.levelnow=5;

        if(Settings.vantoc1==3) player1_speed.levelnow=3;
        else if(Settings.vantoc1==4) player1_speed.levelnow=4;
        else if(Settings.vantoc1==5) player1_speed.levelnow=5;

        if(Settings.vantoc2==3) player2_speed.levelnow=3;
        else if(Settings.vantoc2==4) player2_speed.levelnow=4;
        else if(Settings.vantoc2==5) player2_speed.levelnow=5;
    }
}
