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
    String terrain;
    private boolean isAccessible = true;
    /*
    0 represents null
    1 represents grass
    2 represents forests
    3 represents mountain
    4 represents desert
    5 represents water
    6 represents city
    */
    
    public Tile(String t){
        terrain = t;
        switch(t){
            case "Null":
                break;
            case "Grass":
                try {
                    animation = new Animation();
                    animation.frames = new Image[1];
                    animation.frames[0] = new Image(new FileInputStream("src/resources/images/tiles/grass0.png"));
                }catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Forest":
                try {
                    animation = new Animation();
                    animation.frames = new Image[1];
                    animation.frames[0] = new Image(new FileInputStream("src/resources/images/tiles/forest.png"));
                }catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Mountain":
                break;
            case "Desert":
                break;
            case "Water":
                //isAccessible = false;
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
            case "City":
                break;
            default:
                break;
        }
    }
    
    public boolean isAccessible(){
        return isAccessible;
    }
    
    public String getTerrain(){
        return terrain;
    }

}
