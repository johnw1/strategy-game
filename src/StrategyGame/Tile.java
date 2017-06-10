/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyGame;

import java.io.FileInputStream;
import javafx.scene.image.Image;

/**
 *
 * @author johnwmarrs
 */
public class Tile {
    Animation animation;
    byte terrain;
    boolean isFilled = false;
    /*
    0 represents null
    1 represents grass
    2 represents forests
    3 represents mountain
    4 represents desert
    5 represents water
    6 represents city
    */
    
    public Tile(byte t){
        terrain = t;
        switch(t){
            case 0:
                break;
            case 1:
                try {
                    animation = new Animation();
                    animation.frames = new Image[1];
                    animation.frames[0] = new Image(new FileInputStream("src/resources/images/tiles/grass0.png"));
                }catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    animation = new Animation();
                    animation.frames = new Image[1];
                    animation.frames[0] = new Image(new FileInputStream("src/resources/images/tiles/forest.png"));
                }catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                try {
                    animation = new Animation();
                    animation.frames = new Image[4];
                    
                    for (int i = 0; i < 3; i++) {
                        animation.frames[i] = new Image(new FileInputStream("src/resources/images/tiles/water" + i + ".png"));
                    }
                    animation.frames[3] = animation.frames[1];
                    animation.setDuration(.3);
                    
                }catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                break;
            default:
                break;
        }
    }

}
