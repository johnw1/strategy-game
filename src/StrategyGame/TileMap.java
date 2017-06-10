/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author johnwmarrs
 */
public class TileMap implements Serializable {

    double scale = 1.5;
    Tile[][] tile;
    public TileMap(int x, int y){
        tile = new Tile[x][y];
        for (int r = 0; r < tile.length;r++) {
            for (int c = 0; c < tile[0].length;c++) {
                tile[r][c] = new Tile((byte)5);
            }
        }
    }
    public void saveFileMap(){
        
    }
    public void render(GraphicsContext gc, double offsetX, double offsetY, Player p, Player a, double t) {
        for (int r = 0; r < tile.length;r++) {
            for (int c = 0; c < tile[0].length;c++) {
                gc.drawImage(tile[r][c].animation.getFrame(t), 100*scale*c+offsetX-getCenterX(), 100*scale*r+offsetY-getCenterY(),100*scale,100*scale);
                //gc.fillText("" + c + "," + r, 100*scale*c+offsetX-getCenterX()+15, 100*scale*r+offsetY-getCenterY()+15);
            }
        }
        
        for (Unit u : p.units){
            gc.drawImage(u.animation.getFrame(t), 100*scale*u.getX()+offsetX-getCenterX(), 100*scale*u.getY()+offsetY-getCenterY(),100*scale,100*scale);
            gc.setFill(Color.RED);
            gc.fillRect(100*scale*u.getX()+offsetX-getCenterX() + 25*scale, 100*scale*u.getY()+offsetY-getCenterY() + 90*scale, 50*scale, 5*scale);
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(100*scale*u.getX()+offsetX-getCenterX() + 25*scale, 100*scale*u.getY()+offsetY-getCenterY() + 90*scale, u.getHealth()/100*50*scale, 5*scale);
        }
        for (Unit u : a.units){
            gc.drawImage(u.animation.getFrame(t), 100*scale*u.getX()+offsetX-getCenterX(), 100*scale*u.getY()+offsetY-getCenterY(),100*scale,100*scale);
            gc.setFill(Color.RED);
            gc.fillRect(100*scale*u.getX()+offsetX-getCenterX() + 25*scale, 100*scale*u.getY()+offsetY-getCenterY() + 90*scale, 50*scale, 5*scale);
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(100*scale*u.getX()+offsetX-getCenterX() + 25*scale, 100*scale*u.getY()+offsetY-getCenterY() + 90*scale, u.getHealth()/100*50*scale, 5*scale);
        }
        gc.drawImage(p.animation.getFrame(t), 100*scale*p.getX()+offsetX-getCenterX(), 100*scale*p.getY()+offsetY-getCenterY(),100*scale,100*scale);
        gc.drawImage(a.animation.getFrame(t), 100*scale*a.getX()+offsetX-getCenterX(), 100*scale*a.getY()+offsetY-getCenterY(),100*scale,100*scale);
        
        gc.setFill(Color.RED);
            gc.fillRect(100*scale*p.getX()+offsetX-getCenterX() + 25*scale, 100*scale*p.getY()+offsetY-getCenterY() + 90*scale, 50*scale, 5*scale);
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(100*scale*p.getX()+offsetX-getCenterX() + 25*scale, 100*scale*p.getY()+offsetY-getCenterY() + 90*scale, p.getHealth()/1000*50*scale, 5*scale);
            
            gc.setFill(Color.RED);
            gc.fillRect(100*scale*a.getX()+offsetX-getCenterX() + 25*scale, 100*scale*a.getY()+offsetY-getCenterY() + 90*scale, 50*scale, 5*scale);
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(100*scale*a.getX()+offsetX-getCenterX() + 25*scale, 100*scale*a.getY()+offsetY-getCenterY() + 90*scale, a.getHealth()/1000*50*scale, 5*scale);
        
        gc.setFill(Color.RED);
        
    }
    public void update(double time) {
        for (int r = 0; r < tile.length;r++) {
            for (int c = 0; c < tile[0].length;c++) {
            }
        }
    }
    
    public double getCenterX() {
        return (tile[0].length*100*scale)/2;
    }
    public double getCenterY() {
        return (tile.length*100*scale)/2;
    }
    
    public void adjustScale(double n) {
        if (n > 0 && scale < 1.25) {
            scale += n;
        }
        if (n < 0 && scale > .5) {
            scale += n;
        }
    }
    public double getScale() {
        return scale;
    }
    public boolean hasTileAt(int x, int y) {
        if(x >= 0 && x < tile.length && y >= 0 && y < tile[0].length) {
            return true;
        }
        return false;
    }
    
    public Tile getTile(double x, double y, double offsetX, double offsetY) {
        Tile Temp = null;
        try {
            Temp = tile[(int)((x-offsetX+getCenterX())/(100*scale))][(int)((y-offsetY+getCenterY())/(100*scale))];
        }catch(Exception e) {
            
        }
        Tile temp = null;
        
        
        return temp;
    }
    


    

    
    
}
