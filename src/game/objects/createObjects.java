package game.objects;

import game.GameObject;

public class createObjects extends GameObject{
    public createObjects(){
        for (int y = 0; y <=150 ;y+=50 ) {
            for (int x = 0; x <=150-y; x=x+50) {

                    Barrier object1= new Barrier(
                            0+x,
                            0+y,
                            50,
                            50,
                            "assets/images/barrier/tree1.png");
                    Barrier object2 = new Barrier(
                            1150-x,
                            0+y,
                            50,
                            50,
                            "assets/images/barrier/tree1.png");
                    Barrier object3 = new Barrier(
                            0+x,
                            750-y,
                            50,
                            50,
                            "assets/images/barrier/tree1.png");
                    Barrier object4 = new Barrier(
                            1150-x,
                            750-y,
                            50,
                            50,
                            "assets/images/barrier/tree1.png");




            }
            
        }
        for (int y = 4; y <7 ; y++) {

            Barrier object5= new Barrier(
                    4*50,
                    y*50,
                    50,
                    50,
                    "assets/images/barrier/wood.png");

            Barrier object7= new Barrier(
                    19*50,
                    y*50,
                    50,
                    50,
                    "assets/images/barrier/wood.png");
        }
        for (int y = 9;y<12;y++){

            Barrier object6= new Barrier(
                    4*50,
                    y*50,
                    50,
                    50,
                    "assets/images/barrier/wood.png");

            Barrier object8= new Barrier(
                    19*50,
                    y*50,
                    50,
                    50,
                    "assets/images/barrier/wood.png");
        }
        for (int x = 7; x <16 ; x++) {
            Barrier object9= new Barrier(
                    x*50+25,
                    7*50+25,
                    50,
                    50,
                    "assets/images/barrier/wood.png");
        }
        for (int x = 9; x <=14 ; x++) {
            Barrier object8= new Barrier(
                    x*50,
                    3*50,
                    50,
                    50,
                    "assets/images/barrier/rock.png");
            Barrier object9= new Barrier(
                    x*50,
                    12*50,
                    50,
                    50,
                    "assets/images/barrier/rock.png");
        }
    }
}
