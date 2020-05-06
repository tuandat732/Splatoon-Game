package game;

public class Settings {
    // game
    public static final int GAME_WIDTH = 1700;
    public static final int GAME_HEIGHT = 800;
    //time game
    public static int time=3*60*60;//fps
    //mode
    public static int MODE=0;
    public static int mode=0;
    //team
    public  static double team1=0;
    public static double team2=0;
    //bosscon
    public static int maxbosscon=5;
    // player
    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 48;
    //player1
    public static int vantoc1=2;
    public static int size1=2;
    public static int long1=3;
    //player2
    public static int vantoc2=2;
    public static int size2=2;
    public static int long2=3;
    // enemy

    // background
    public static final int BACKGROUND_WIDTH = 1200;
    public static final int BACKGROUND_HEIGHT = 800;
    //boss
    public static double bossStartAngle=0;
    public static double bossEndAngle=360;
    public static double bossStartX=20;
    public static double bossEndX=-20;
    public static int bossNumberBullet=12;
    public static double bossStepAngle=Math.abs(bossEndAngle-bossStartAngle)/(bossNumberBullet-1);
    public static double bossStepX=Math.abs(bossEndX-bossStartX)/(bossNumberBullet-1);
}
