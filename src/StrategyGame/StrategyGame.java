/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyGame;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author johnwmarrs
 */
public class StrategyGame extends Application {
    

    
    GameLoop gl;
    Scene mainMenu, gameScene, mapCreatorScene;
    Canvas cv;
    GraphicsContext gc;
    private Object ConfirmBox;
    @Override
    public void start(Stage primaryStage) {
       
        VBox v = new VBox();
        mainMenu = new Scene(v,1080,720);
        

        Group root = new Group();
        gameScene = new Scene(root,1080,720);
        
        cv = new Canvas(1080,720);
        
        root.getChildren().add(cv);
        GraphicsContext gc = cv.getGraphicsContext2D();
        
        gl = new GameLoop((byte)1,gc);
        
        gameScene.setOnKeyTyped(gl);
        gameScene.setOnMouseClicked(gl);
        gameScene.setOnMouseMoved(gl);
        gameScene.setOnMousePressed(gl);
        gameScene.setOnMouseReleased(gl);
        gameScene.setOnMouseDragged(gl);
        gameScene.setOnScrollStarted(gl);
        gameScene.setOnScrollFinished(gl);
        gameScene.setOnScroll(gl);
        
        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> {
            System.out.println("start game");
            primaryStage.setScene(gameScene);
            gl.start();
        });
        
        Button createMapBtn = new Button("Create Map");
        Button quitGameBtn = new Button("Quit");
        v.getChildren().add(playBtn);
        v.getChildren().add(createMapBtn);
        v.getChildren().add(quitGameBtn);
        
        
        
        primaryStage.setTitle("Combat");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
  
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("Closing");
        });
        primaryStage.setResizable(false);
    }
    
    public void startGame() {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
