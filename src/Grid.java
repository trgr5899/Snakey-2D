//package ooadfinal.snakey2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Grid {
    int numRows;
    int numCols;
    int sizeFrame;
    int height;
    List<List<Node>> nodes = new ArrayList<List<Node>>();
    Snake snake = new Snake();
    Grid(int sizeF, int NodesPerRow)
    {
        sizeFrame = sizeF;
        numRows = NodesPerRow-1;
        numCols = NodesPerRow-1;
        height = sizeFrame/numRows;
    }

    public void createGrid() {

        ArrayList<Node> snakeBody = new ArrayList<Node>();
        Boolean snakeBool = false;
        Boolean wall;
        for (int row = 0; row < numRows; row++) {
            List<Node> tempList = new ArrayList<Node>(); 
            for (int col = 0; col < numCols; col++) {
                if(row == 0 || row == numRows-1 || col == 0 || col == numCols-1)
                {
                    snakeBool = false;
                    wall = true;
                    Node node = new Node(Color.RED, row, col, height, snakeBool, wall);
                    tempList.add(node);
                }
                else if((row == 26 && col == 26) || (row == 26 && col == 27) || (row == 26 && col == 28) || (row == 26 && col == 29))
                {
                    snakeBool = true;
                    wall = true;
                    Node node = new Node(Color.WHITE, row, col, height, snakeBool, wall);
                    tempList.add(node);
                    snakeBody.add(node);
                }
                else
                {
                    snakeBool = false;
                    wall = false;
                    Node node = new Node(Color.BLACK, row, col, height, snakeBool, wall);
                    tempList.add(node);
                }
            }
            nodes.add(tempList);
            snake.createSnake(snakeBody);
        }
    }

    public void drawGrid(Stage primaryStage)
    {
        GridPane grid = new GridPane();
        for(int i = 0; i < nodes.size(); i++)
        {
            for(int j = 0; j < nodes.size(); j++)
            {
                Rectangle rec = new Rectangle();
                rec.setWidth(height);
                rec.setHeight(height);
                rec.setFill(nodes.get(i).get(j).getColor());
                GridPane.setRowIndex(rec, nodes.get(i).get(j).getRow());
                GridPane.setColumnIndex(rec, nodes.get(i).get(j).getCol());
                grid.getChildren().addAll(rec);
            }
        }
    
        Scene scene = new Scene(grid, sizeFrame, sizeFrame);
    
        primaryStage.setTitle("Challenge Mode");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
