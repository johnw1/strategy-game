/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyGame;

import javafx.scene.image.Image;

/**
 *
 * @author johnwmarrs
 */
public class Animation {
    public Image[] frames;
    public double duration;

    public void setDuration(double t) {
        duration = t;
    }
    public Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration))/duration);
        return frames[index];
    }
}
