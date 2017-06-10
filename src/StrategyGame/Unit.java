/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyGame;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Random;
import javafx.scene.image.Image;
/**
 *
 * @author johnwmarrs
 */
public class Unit implements Serializable {
    
    Animation animation;
    private int yLoc;
    private int xLoc;
    
    private String name = "";
    private String type = "";
    double health = 100;
    private int defense;
    private int attack;
    private int price;
    private int range = 1;
    
    private int moves = 2;
    private int maxMoves = moves;
    
    boolean isRanged = false;
    
    public void attack(Unit eu) {
        Random rand = new Random();
        if (!isRanged) {
            this.defend(eu);
        }
        eu.setHealth(eu.getHealth() - (int)((this.getAttack() * rand.nextFloat())*(1+calculateBonus(this.getType(),eu.getType()))));
        System.out.println("Attacker " + this.getHealth());
    }
    
    public void defend(Unit eu) {
        Random rand = new Random();
        this.setHealth(eu.getHealth() - (int)((eu.getDefense() * rand.nextFloat())*(1+calculateBonus(eu.getType(),this.getType()))));
        System.out.println(eu.getHealth());
    }
    
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public double getHealth() {
        return health;
    }
    public void setHealth(double h) {
        health = h;
    }
    public int getDefense() {
        return defense;
    }
    public int getAttack() {
        return attack;
    }
    public int getPrice() {
        return price;
    }
    public int getX() {
        return xLoc;
    }
    public int getY() {
        return yLoc;
    }
    public void setX(int xi) {
        xLoc = xi;
    }
    public void setY(int yi) {
        yLoc = yi;
    }
    public int getMoves() {
        return moves;
    }
    public void setMoves(int m) {
        moves = m;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int r) {
        range = r;
    }
    public void translate(int deltaX, int deltaY) {
       xLoc += deltaX;
       yLoc += deltaY;
   }
    
    public double calculateBonus(String attackerType, String defenderType) {
        double bonus = 0;
        if (attackerType.equals("Normal") && defenderType.equals("Shadow")){
            bonus = -.1;
        }else if (attackerType.equals("Shadow") && defenderType.equals("Normal")) {
            bonus = .1;
        }else if (attackerType.equals("Fire") && defenderType.equals("Water")){
            bonus = -.2;
        }else if (attackerType.equals("Water") && defenderType.equals("Fire")) {
            bonus = .2;
        }else if (attackerType.equals("Dark") && defenderType.equals("Light")) {
            bonus = -.1;
        }else if (attackerType.equals("Light") && defenderType.equals("Dark")){
            bonus = .25;
        }else if (attackerType.equals("Water") && defenderType.equals("Earth")) {
            bonus = .15;
        }else if (attackerType.equals("Earth") && defenderType.equals("Normal")) {
            bonus = .1;
        }else if (defenderType.equals("Air")) {
            bonus = -.25;
        }
            
        return bonus;
    }
     
    public void resetMoves() {
        moves = maxMoves;
    }
    
    public Unit(String n, String t, int x, int y, boolean isAI) {
        xLoc = x;
        yLoc = y;
        name = n;
        type = t;
        moves = 2;
        switch(name) {
            case "Archer":
                attack = 30;
                defense = 30;
                isRanged = true;
                range = 3;
                price = 7;
                try {
                    if (!isAI) {
                        animation = new Animation();
                        animation.frames = new Image[1];
                        animation.frames[0] = new Image(new FileInputStream("src/resources/images/units/playerArcher.png"));
                    }else {
                        animation = new Animation();
                        animation.frames = new Image[1];
                        animation.frames[0] = new Image(new FileInputStream("src/resources/images/units/aiArcher.png"));
                    }
                }catch (Exception e) {
                    
                }
                break;
            case "Swordsman":
                attack = 50;
                defense = 50;
                price = 8;
                try {
                    if (!isAI) {
                        animation = new Animation();
                        animation.frames = new Image[1];
                        animation.frames[0] = new Image(new FileInputStream("src/resources/images/units/playerSwordsman.png"));
                    }else {
                        animation = new Animation();
                        animation.frames = new Image[1];
                        animation.frames[0] = new Image(new FileInputStream("src/resources/images/units/enemySwordsman.png"));
                    }
                }catch (Exception e) {
                    
                }
                
                break;
            case "Knight":
                attack = 60;
                defense = 50;
                price = 11;
                break;
            case "Guard":
                attack = 25;
                defense = 85;
                price = 10;
                moves = 1;
                maxMoves = 1;
                break;
            case "Goblin":
                attack = 25;
                defense = 25;
                price = 3;
                break;
            case "Ninja":
                attack = 55;
                defense = 30;
                price = 8;
                moves = 3;
                maxMoves = 3;
                break;
            case "Wizard":
                attack = 80;
                defense = 40;
                isRanged = true;
                range = 2;
                price = 15;
                try {
                    if (!isAI) {
                        animation = new Animation();
                        animation.frames = new Image[1];
                        animation.frames[0] = new Image(new FileInputStream("src/resources/images/units/playerWizard.png"));
                    }else {
                        animation = new Animation();
                        animation.frames = new Image[1];
                        animation.frames[0] = new Image(new FileInputStream("src/resources/images/units/enemyWizard.png"));
                    }
                }catch (Exception e) {
                    
                }
                break;
            case "Prince":
                attack = 50;
                defense = 50;
                price = 10;
                break;

            case "Dragon":
                attack = 100;
                defense = 100;
                price = 25;
                moves = 3;
                maxMoves = 3;
                break;
            default:
                break;
        }
    }
    
}
