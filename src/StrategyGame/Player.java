/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyGame;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author johnwmarrs
 */
public class Player {
    private int xLoc;
    private int yLoc;
    private int gold;
    private int moves = 2;
    private int maxMoves = 2;
    private double health = 1000;
    Animation animation;
    List<Unit> units = new ArrayList<Unit>();
    
   public Player(int x,int y) {
       xLoc = x;
       yLoc = y;
       try {
           animation = new Animation();
           animation.frames = new Image[1];
           animation.frames[0] = new Image(new FileInputStream("src/resources/images/units/playerCrown.png"));
       }catch(Exception e) {
           
       }
   }
   
   public void resetMoves() {
       moves = maxMoves;
       for (Unit u: units) {
           u.resetMoves();
       }
   }
   
   public Unit getUnitAtLocation(int x, int y) {
       for (Unit u: units) {
           if (u.getX() == x && u.getY() == y) {
               return u;
           }
       }
       System.out.println("None found");
       return null;
   }
   public boolean hasUnitAt(int x, int y) {
       for (Unit u: units) {
           if (u.getX() == x && u.getY() == y) {
               return true;
           }
       }
       return false;
   }
   
   public boolean isAt(int x, int y) {
       if (x == getX() && y == getY()) {
           return true;
       }
       return false;
   }
   
   public boolean hasMovesRemaining() {
       for (Unit u: units) {
           if(u.getMoves() > 0) {
               return true;
           }
       }
       if (moves > 0) {
           return true;
       }
       return false;
   }
   
   public int getMoves() {
       return moves;
   }
   public void setMoves(int m) {
       moves = m;
   }
   public int getX() {
       return xLoc;
   }
   public int getY() {
       return yLoc;
   }
   public void setX(int x) {
       xLoc = x;
   }
   public void setY(int y) {
       yLoc = y;
   }
   public void translate(int deltaX, int deltaY) {
       xLoc += deltaX;
       yLoc += deltaY;
   }
   public double getHealth() {
       return health;
   }
   public void setHealth(double healthValue) {
       health = healthValue;
   }
   
   
   

}
