package game.objects;

import game.GameObject;
import game.renderer.Renderer;

public class ObjectsMapBoss extends GameObject {
    public ObjectsMapBoss(){
        Barrier object1= new Barrier(
                0,
                0,
                300,
                50,
                "assets/images/barrier/tang_dai6_trai.png");
        Barrier object2= new Barrier(
                900,
                0,
                300,
                50,
                "assets/images/barrier/tang_dai6_phai.png");
        Barrier object3= new Barrier(
                950,
                50,
                250,
                50,
                "assets/images/barrier/tang_dai5_phai.png");
        Barrier object4= new Barrier(
                0,
                50,
                250,
                50,
                "assets/images/barrier/tang_dai5_trai.png");
        Barrier object5= new Barrier(
                0,
                750,
                250,
                50,
                "assets/images/barrier/tang_dai5_trai.png");
        Barrier object6= new Barrier(
                950,
                750,
                250,
                50,
                "assets/images/barrier/tang_dai5_phai.png");
        for (int i = 1; i <18 ; i++) {
            if(i==1 || i==17){
                Barrier object7= new Barrier(
                        i*50,
                        5*50,
                        300,
                        50,
                        "assets/images/barrier/tang_dai6_can_bang.png");
            }

        }
        for (int i = 0; i <24 ; i++) {
            if(i == 2 || i == 7 || i ==16 || i == 21){
                continue;
            }
            else{
                Barrier lava= new Barrier(
                        i*50,
                        8*50,
                        50,
                        50,
                        "");
//                lava.lava=true;
                lava.renderer=new Renderer("assets/images/barrier/lava_river","over",20);
            }


        }
        for (int i = 0; i <24 ; i++) {
            if(i == 4|| i == 19 ){
                continue;
            }
            else{
                Barrier object7= new Barrier(
                        i*50,
                        11*50,
                        50,
                        50,
                        "assets/images/barrier/tang_da.png");
            }




        }
        Barrier object7= new Barrier(
                4*50,
                11*50,
                2,
                100,
                "");
        object7.lava=true;
        Barrier object8 = new Barrier(
                4*50+48,
                11*50,
                2,
                100,
                ""
        );
        object8.lava=true;
        Barrier object9 = new Barrier(
                19*50,
                11*50,
                2,100,
                ""
        );
        Barrier object10 = new Barrier(
                19*50+48,
                11*50,
                2,
                100,
                ""
        );
        Barrier hole = new Barrier(
                10*50,
                3*50,
                220,200,
                "assets/images/barrier/hole_boss.png"
        );
        hole.lava=true;
        hole.hola=true;
    }
}
