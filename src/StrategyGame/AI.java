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
public class AI extends Player {
    // holds the difficulty  
    byte d;
    /*
    0 - easy
    1 - medium
    2 - hard
    3 - impossible
    */
    
    // holds the strategy
    byte strategy;
    /*
    0 - balanced
    1 - aggressive
    2 - defensive
    3 - impossible
    */
    
    public AI(byte difficulty,int x, int y) {
        super(x,y);
        d = difficulty;
        goldPerTurn = 4+3*d;
        try {
           animation = new Animation();
           animation.frames = new Image[1];
           animation.frames[0] = new Image(new FileInputStream("src/resources/images/units/aiCrown.png"));
       }catch(Exception e) {
           System.out.println("failed somewhere");
       }
    }
    
    public String makeMove(Player p) {
        // THE STRING RETURNED IS THE COMMAND
        /*-----------------------------------
        A few comments on commands
        -------------------------------------
        To move or attack use the following notation
            AI(unitLocationX,unitLocationY)-(enemyLocationX,enemyLocationY)
        To purchase an item, use the following notation
            AIb<unitName>(locationX,locationY)
        
        
        
        
        -------------------------------------
        */
        String command = "";
        if (this.hasMovesRemaining()) {
            switch (d) {
                case 0:
                    switch(strategy) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                    break;
                case 1:
                    switch(strategy) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        
                    }
                    break;
                case 2:
                    switch(strategy) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                    break;
            }
        }else {
            
        }
        
        
   
        
        command = "AI end";
        return command;
    }
}
