/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyGame;


import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author johnwmarrs
 */
public class GameLoop extends AnimationTimer implements EventHandler{

    // Variables and player data
    Player up;
    //----------------------------------
    // Variables and AI DATA
    AI ai;
    //----------------------------------
    //Variables to record commands for player
    AudioController ac;
    Queue<String> commands = new LinkedList<String>();
    private String commandString = "";
    double initX;
    double initY;

    
    double centerX;
    double centerY;

    
    // Important variables for game flow
    boolean isPlayersTurn = false;
    double tLast = 0;
    String frameRate;
    GraphicsContext gc; // represents game content
    long startNanoTime;
    TileMap tm;
    // ---------------------------------
    public GameLoop(byte difficulty, GraphicsContext graphic) {
        gc = graphic;
        startNanoTime = System.nanoTime();
        tm = new TileMap(7,11);
        up = new Player(1,3);
        up.units.add(new Unit("Wizard","Water",2,1,false));
        ai = new AI(difficulty,9,3);
        ai.units.add(new Unit("Swordsman","Fire",4,2,true));
        centerX = gc.getCanvas().getWidth()/2;
        centerY = gc.getCanvas().getHeight()/2;
        ac = new AudioController();

    }
    
    
    @Override
    public void handle(long now) {

        double t = (now - startNanoTime)/1000000000.0;

        update(t);
        render(gc, t);
        frameRate = "" + Math.round(1/(t-tLast));
        tLast = t;
    

    }
    
    private void render(GraphicsContext gc, double t) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        
        gc.setFill(Color.RED);
        gc.fillText(frameRate, 16, 12);
        tm.render(gc,centerX,centerY, up, ai,t);

        
        //render the menus
        renderMenus(gc);
 
    }
    
    private void renderMenus(GraphicsContext gc) {
 

    }
    
    private void update(double time) {
        // need to check variables of units and everything involved
        if (commands.size() > 0) {
            process(commands.poll());
        }
       
    }
    


    @Override
    public void handle(Event event) {

        MouseEvent me;
        KeyEvent ke;
        ScrollEvent se;
        
        switch(event.getEventType().toString()){

            case "KEY_TYPED":
                ke = (KeyEvent)event;
                System.out.println("Key " + ke.getCharacter() + " pressed");
      
                break;
            case "MOUSE_CLICKED":
                me = (MouseEvent)event;
                
                int x = (int)Math.ceil((me.getX()-centerX+tm.getCenterX())/(100*tm.getScale()))-1;
                int y = (int)Math.ceil((me.getY()-centerY+tm.getCenterY())/(100*tm.getScale()))-1;
                
                
                if (me.getButton() == MouseButton.PRIMARY) {
                    if (commandString.equals("") && up.hasUnitAt(x,y)){
                        commandString += select(me.getX(),me.getY()) + "-";

                    }else if (commandString.contains("-")){
                        if(ai.hasUnitAt(x, y)){
                            commandString += select(me.getX(),me.getY()) + "-";
                            commands.offer(commandString);
                            commandString = "";
                        }else if(up.hasUnitAt(x, y)){
                            
                        }else if(tm.hasTileAt(x, y)){
                            commandString += select(me.getX(),me.getY());
                            commands.offer(commandString);
                            commandString = "";
                        }else {
                            
                        }
                    }
                    
                }else if (me.getButton() == MouseButton.SECONDARY) {
                    
                }
               

                break;
            case "MOUSE_PRESSED":
                me = (MouseEvent)event;
                initX = me.getX();
                initY = me.getY();
                break;
            case "MOUSE_RELEASED":
                me = (MouseEvent)event;
                //finX = me.getX();
                //finY = me.getY();
                //centerX -= initX - finX;
                //centerY -= initY - finY;
                break;
            case "MOUSE_DRAGGED":
                me = (MouseEvent)event;
                
                centerX -= initX - me.getX();
                centerY -= initY - me.getY();
                initX = me.getX();
                initY = me.getY();
                break;
            case "SCROLL":
                se = (ScrollEvent)event;
                tm.adjustScale((se.getDeltaY()/90)/10);
                break;
            default:
                break;
        }

    }
    
    private void process(String s) {
        String first;
        String last;

        System.out.println(s);
        if (s.contains("-")) {
            first = s.substring(0,s.indexOf("-"));
            last = s.substring(s.indexOf("-")+1);
            if (ai.isAt(getXComponent(last),getYComponent(last))){
                
            }else if (up.isAt(getXComponent(last),getYComponent(last))) {
                
            }else if(ai.hasUnitAt(getXComponent(last), getYComponent(last))) {
                int deltaX = getXComponent(last) - getXComponent(first);
                int deltaY = getYComponent(last) - getYComponent(first);
                if(1 <= up.getUnitAtLocation(getXComponent(first), getYComponent(first)).getMoves() && (Math.abs(deltaX) + Math.abs(deltaY)) <= up.getUnitAtLocation(getXComponent(first), getYComponent(first)).getRange()) {
                    up.getUnitAtLocation(getXComponent(first), getYComponent(first)).setMoves(up.getUnitAtLocation(getXComponent(first), getYComponent(first)).getMoves()-1);
                    //System.out.println(up.getUnitAtLocation(getXComponent(first), getYComponent(first)).getMoves());
                    up.getUnitAtLocation(getXComponent(first), getYComponent(first)).attack(ai.getUnitAtLocation(getXComponent(last), getYComponent(last)));
                    if (up.getUnitAtLocation(getXComponent(first), getYComponent(first)).getHealth() <= 0) {
                        up.units.remove(up.getUnitAtLocation(getXComponent(first), getYComponent(first)));
                    }
                    if (ai.getUnitAtLocation(getXComponent(last), getYComponent(last)).getHealth() <= 0) {
                        ai.units.remove(ai.getUnitAtLocation(getXComponent(last), getYComponent(last)));
                    }
                    
                }
                
            }else if(up.hasUnitAt(getXComponent(last), getYComponent(last))) {
                
            }else if(tm.hasTileAt(getXComponent(last), getYComponent(last))) {
                int deltaX = getXComponent(last) - getXComponent(first);
                int deltaY = getYComponent(last) - getYComponent(first);
                if(Math.abs(deltaX) + Math.abs(deltaY) <= up.getUnitAtLocation(getXComponent(first), getYComponent(first)).getMoves()) {
                    up.getUnitAtLocation(getXComponent(first), getYComponent(first)).setMoves(up.getUnitAtLocation(getXComponent(first), getYComponent(first)).getMoves()-(Math.abs(deltaX) + (Math.abs(deltaY))));
                    //System.out.println(up.getUnitAtLocation(getXComponent(first), getYComponent(first)).getMoves());
                    up.getUnitAtLocation(getXComponent(first), getYComponent(first)).translate(deltaX, deltaY);
                    
                }
            }else {
                
            }

        }
    }
    
    private String select(double x, double y) {
        int selectedColumn = (int)Math.ceil((x-centerX+tm.getCenterX())/(100*tm.getScale()))-1;
        int selectedRow = (int)Math.ceil((y-centerY+tm.getCenterY())/(100*tm.getScale()))-1;
        if (selectedRow >= tm.tile.length || selectedRow < 0 || selectedColumn >= tm.tile[0].length || selectedColumn < 0 ){
            return "(" + -1 + "," + -1 + ")";
        }
        return "(" + selectedColumn + "," + selectedRow+ ")";
    }
    
    private int getXComponent(String s){
        int temp = -1;
        s = s.substring(s.indexOf("(")+1,s.indexOf(","));
        try {
            temp = Integer.parseInt(s);
        }catch(Exception e) {
        }
        return temp;
    }
    private int getYComponent(String s){
        int temp = -1;
        s = s.substring(s.indexOf(",")+1,s.indexOf(")"));
        try {
            temp = Integer.parseInt(s);
        }catch(Exception e) {
        }
        return temp;
    }


    
}
