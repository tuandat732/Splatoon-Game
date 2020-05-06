package game.renderer;

import game.GameObject;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Renderer {
    public BufferedImage image;

    public ArrayList<BufferedImage> images;
    public int currentImageIndex;
    public int nextImageCount;
    public String type;
    public int nextframe;
    // isOnce == true >> onetime animation
    // isOnce == true >> loop animation

    public Renderer(String url) {
        // 1. Draw single image >> url is file url
        // 2. Draw animation >> url is directory url
        File source = new File(url);
        if(source.isFile()) { // 1.
            image = SpriteUtils.loadImage(url);
        }
        if(source.isDirectory()) { // 2.
            images = SpriteUtils.loadImages(url);
            currentImageIndex = 0;
            nextImageCount = 0;
        }
    }

    public Renderer(String url, String type,int nextframe) {
        this(url);
        this.type = type;
        this.nextframe=nextframe;
    }

    public void render(Graphics g, GameObject master) {
        if(image != null) { // 1.
            this.drawImage(g, master, image);
        }
        if(images != null) { // 2. draw animation
            BufferedImage currentImage = images.get(currentImageIndex);
            this.drawImage(g, master, currentImage);

            nextImageCount++;
            if(nextImageCount > nextframe) {
                currentImageIndex++;
                if(currentImageIndex >= images.size()) {
                    currentImageIndex = 0;
                    if(type=="stop") {
                        currentImageIndex=images.size()-1;
                    }
                    else if(type=="isOnce"){
                        master.deactive();
                    }
                }
                nextImageCount = 0;
            }
        }
    }

    private void drawImage(Graphics g, GameObject master, BufferedImage image) {
        g.drawImage(
                image,
                (int) (master.position.x - image.getWidth() * master.anchor.x),
                (int) (master.position.y - image.getHeight() * master.anchor.y),
                null
        );

        // logic for dev
//        if(master.hitBox != null){
//            // draw hit box for gameObjects
//            g.setColor(Color.CYAN);
//            g.drawRect((int)master.hitBox.left(), (int)master.hitBox.top(),
//                    master.hitBox.width, master.hitBox.height);
//            // draw position for gameObjects
//            g.setColor(Color.RED);
//            g.fillOval((int)master.position.x - 3, (int) master.position.y - 3,
//                    5, 5);
//        }
    }
}
