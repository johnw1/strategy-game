/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyGame;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author johnwmarrs
 */
public class AudioController {
    
  
    HashMap<String,Media> effects;
    Queue<String> bkgMusic = new LinkedList<String>();
    int num = 0;
    public AudioController() {
    
        bkgMusic.offer("src/resources/sounds/effects/click.mp3");
        bkgMusic.offer("src/resources/sounds/background/bkg0.mp3");
        bkgMusic.offer("src/resources/sounds/effects/click.mp3");
        effects = new HashMap<String, Media>();

        try {
            effects.put("click", new Media((new File("src/resources/sounds/effects/click.mp3")).toURI().toString()));
        }catch(Exception e) {
            
        }
        //playNext();
    }
    
    public void playSoundEffect(String s) {
        try {
            MediaPlayer mp = new MediaPlayer(effects.get(s));
            mp.play();
            mp.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    mp.dispose();
                }
                
            });
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    public void playNext() {
        bkgMusic.offer(bkgMusic.peek());
        System.out.println(bkgMusic.peek());
        System.out.println(bkgMusic.size());
        try {
            Media m = new Media(new File(bkgMusic.poll()).toURI().toString());
            MediaPlayer mp = new MediaPlayer(m);
            mp.play();
            mp.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    mp.dispose();
                    playNext();
                }
                
            });
        }catch(Exception e) {

        }
    }
    

}
