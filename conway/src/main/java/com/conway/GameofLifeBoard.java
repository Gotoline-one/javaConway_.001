package com.conway;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameofLifeBoard  {
    private static int WIDTH = 50;
    private static int HEIGHT = 50;
    private static int CELL_SIZE = 20;

    private Rectangle[][] rectangles;// = new Rectangle[HEIGHT][WIDTH];
    private GameofLife game; 
    private GridPane grid; 

    private static final int TICK_RATE = 20; // ms per tick (50Hz)
    private ArrayList<Integer> frameCountList;
    private ArrayList<Long> nanoTimeList;
    private long lastFpsTime = 0;
    private int frameCount = 0;
    private long startNano; 

    private long startMili; 


    // provides calling app Node to set into larger Java FX app
    protected GridPane getView() {
        return grid;
    }

    // Draw current board state
    private void drawBoard() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
               rectangles[row][col].setFill(game.getCell(row,col) ? Color.BLACK : Color.WHITE);
            }
        }
    }
    
    public GameofLifeBoard() {
        frameCountList = new ArrayList<Integer>();
        nanoTimeList = new ArrayList<Long>();
        startNano = System.nanoTime();
        startMili = System.currentTimeMillis();

        // Create the model
        game = new GameofLife(HEIGHT, WIDTH);
        
        // Random initialization
        game.randomizeBoard();   
        
        // Set up the grid layout for the board
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        
        // Instanciate the rectangles 2D array
        rectangles = new Rectangle[HEIGHT][WIDTH];

        // Initialize rectangles (view)
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                rectangles[row][col] = new Rectangle(CELL_SIZE, CELL_SIZE);
                rectangles[row][col].setStroke(Color.LIGHTGRAY);
                // Initially set based on the model's state
                rectangles[row][col].setFill(game.getCell(row,col) ? Color.BLACK : Color.WHITE);
                grid.add(rectangles[row][col], col, row);
            }
        }
        
        // Set up the game loop (controller updating the model and view)
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(TICK_RATE), 
                e -> {  game.updateBoard(); drawBoard(); }
            )
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        // FPS counter using AnimationTimer (actual frame rate)
        AnimationTimer fpsCounter = new AnimationTimer() {
            @Override
            public void handle(long now) {
                frameCount++;
                if (lastFpsTime == 0) {
                    lastFpsTime = now;
                } else if (now - lastFpsTime >= 1_000_000_000) { // 1 second
                    // System.out.println("fps: " + frameCount);
                    frameCountList.add(frameCount);
                    frameCount = 0;
                    lastFpsTime = now;
                    //save time
                    nanoTimeList.add(System.nanoTime());
                }
            }
        };
        fpsCounter.start();
        
    }


  // Methods to provide dimensions for the Scene

    public int getWidth() {
        return WIDTH * CELL_SIZE;
    }

    public int getHeight() {
        return HEIGHT * CELL_SIZE;
    }
    private void outputJson() {
        System.out.println("{");
        System.out.println("  \"tick_rate_ms\": " + TICK_RATE + ",");
        System.out.println("  \"start_time_ms\": " + startMili + ",");
        System.out.println("  \"data\": [");
    
        for (int i = 0; i < frameCountList.size(); i++) {
            long elapsedNano = nanoTimeList.get(i) - startNano;
            double elapsedMs = elapsedNano / 1_000_000.0;
    
            System.out.print("    { ");
            System.out.print("\"index\": " + i + ", ");
            System.out.print("\"frame_count\": " + frameCountList.get(i) + ", ");
            System.out.print("\"nano_time\": " + nanoTimeList.get(i) + ", ");
            System.out.printf("\"elapsed_ms\": %.3f", elapsedMs);
            System.out.print(" }");
    
            if (i < frameCountList.size() - 1) System.out.println(",");
            else System.out.println();
        }
    
        System.out.println("  ]");
        System.out.println("}");
    }

    private void outputCsv() {
        System.out.println("index,frame_count,nano_time,elapsed_ms");
    
        for (int i = 0; i < frameCountList.size(); i++) {
            long elapsedNano = nanoTimeList.get(i) - startNano;
            double elapsedMs = elapsedNano / 1_000_000.0;
    
            System.out.printf("%d,%d,%d,%.3f%n",
                    i,
                    frameCountList.get(i),
                    nanoTimeList.get(i),
                    elapsedMs
            );
        }
    
        // Optionally, add summary info at end:
        long totalElapsedNano = nanoTimeList.get(nanoTimeList.size() - 1) - startNano;
        double totalElapsedMs = totalElapsedNano / 1_000_000.0;
        System.out.printf("Summary: TICK_RATE=%dms, Total Frames=%d, Total Time=%.3fms%n",
                TICK_RATE,
                frameCountList.size(),
                totalElapsedMs
        );
    }

} 