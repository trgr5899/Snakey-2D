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
    int speed;
    Boolean FoodEaten = false;
    boolean SlowEaten = false;
    boolean SpeedEaten = false;
    Boolean gameOver = false;
    List<List<Node>> nodes = new ArrayList<List<Node>>();
    Snake snake = new Snake();
    Snake snakeSecond = new Snake();


    Grid(int sizeF, int NodesPerRow, int speedAdd)
    {
        sizeFrame = sizeF;
        numRows = NodesPerRow-1;
        numCols = NodesPerRow-1;
        height = sizeFrame/numRows;
        speed = speedAdd;
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
                    wall = false;
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
            snake.createSnake(snakeBody,Color.WHITE);
        }
        addFood();
    }
    public Node getRandomNode()
    {
        Random ran = new Random();
        int row = 0;
        int col = 0;
        while(true)
        {
            row = ran.nextInt(48) + 1 ;
            col = ran.nextInt(48) + 1 ;
            if((nodes.get(row).get(col).getIsWall() == false) && (nodes.get(row).get(col).getIsSnake() == false))
            {
                break;
            }
        }
        return nodes.get(row).get(col);
    }
    public void createGridChallenge() {

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
                else if((row == 10 && col == 15) || (row == 10 && col == 16) || (row == 10 && col == 17) || (row == 10 && col == 18)|| (row == 10 && col == 19)|| (row == 10 && col == 20))
                {
                    snakeBool = false;
                    wall = true;
                    Node node = new Node(Color.RED, row, col, height, snakeBool, wall);
                    tempList.add(node);
                }
                else if((row == 11 && col == 17) || (row == 12 && col == 17) || (row == 13 && col == 17) || (row == 14 && col == 17)|| (row == 15 && col == 17)|| (row == 16 && col == 17))
                {
                    snakeBool = false;
                    wall = true;
                    Node node = new Node(Color.RED, row, col, height, snakeBool, wall);
                    tempList.add(node);
                }
                else if((row == 36 && col == 40) || (row == 36 && col == 41) || (row == 36 && col == 42) || (row == 36 && col == 43)|| (row == 36 && col == 44)|| (row == 36 && col == 45))
                {
                    snakeBool = false;
                    wall = true;
                    Node node = new Node(Color.RED, row, col, height, snakeBool, wall);
                    tempList.add(node);
                }
                else if((row == 28 && col == 10) || (row == 29 && col == 11) || (row == 30 && col == 12) || (row == 31 && col == 13)|| (row == 32 && col == 14)|| (row == 33 && col == 15))
                {
                    snakeBool = false;
                    wall = true;
                    Node node = new Node(Color.RED, row, col, height, snakeBool, wall);
                    tempList.add(node);
                }
                else if((col == 28 && row == 10) || (col == 29 && row == 11) || (col == 30 && row == 12) || (col == 31 && row == 13)|| (col == 32 && row == 14)|| (col == 33 && row == 15))
                {
                    snakeBool = false;
                    wall = true;
                    Node node = new Node(Color.RED, row, col, height, snakeBool, wall);
                    tempList.add(node);
                }
                else if((row == 26 && col == 26) || (row == 26 && col == 27) || (row == 26 && col == 28) || (row == 26 && col == 29))
                {
                    snakeBool = true;
                    wall = false;
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
            snake.createSnake(snakeBody,Color.WHITE);
        }
        addFood();
        addSlowDown();
        addSpeedUp();
    }

    public void createGridMulti() {

        ArrayList<Node> snakeBody = new ArrayList<Node>();
        ArrayList<Node> snakeBodySecond = new ArrayList<Node>();
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
                    wall = false;
                    Node node = new Node(Color.WHITE, row, col, height, snakeBool, wall);
                    tempList.add(node);
                    snakeBody.add(node);
                }
                else if((row == 10 && col == 10) || (row == 11 && col == 10) || (row == 12 && col == 10) || (row == 13 && col == 10))
                {
                    snakeBool = true;
                    wall = false;
                    Node node = new Node(Color.GREEN, row, col, height, snakeBool, wall);
                    tempList.add(node);
                    snakeBodySecond.add(node);
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
            snake.createSnake(snakeBody,Color.WHITE);
            snakeSecond.createSnake(snakeBodySecond,Color.GREEN);
        }
        addFood();
        addSlowDown();
        addSpeedUp();
    }
    public void addFood()
    {
        Node newN = getRandomNode();
        newN.setColor(Color.AQUA);
        newN.isPowerUp = true;
        FoodEaten = false;
    }
    public void addSpeedUp()
    {
        Node newN = getRandomNode();
        newN.setColor(Color.DARKGOLDENROD);
        newN.isPowerUp = true;
        SpeedEaten = false;
    }
    public void addSlowDown()
    {
        Node newN = getRandomNode();
        newN.setColor(Color.VIOLET);
        newN.isPowerUp = true;
        SlowEaten = false;
    }
    public Scene drawGrid(Stage primaryStage)
    {

        if(FoodEaten)
        {
            addFood();
        }
        if(SpeedEaten)
        {
            addSpeedUp();
        }
        if(SlowEaten)
        {
            addSlowDown();
        }

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
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }
    public Node getNextHeadNode(Direction dir)
    {
        Node head = snake.getSnakeHead();
        int col = 0;
        int row = 0;

        //TODO: add edge cases
        if(dir == Direction.left)
        {
            col = head.getCol();
            row = head.getRow();
            col = col - 1; //move one column left
        }
        else if(dir == Direction.right)
        {
            col = head.getCol();
            row = head.getRow();
            col = col + 1; //move one column right
        }
        else if(dir == Direction.up)
        {
            col = head.getCol();
            row = head.getRow();
            row = row - 1; //move one row up
        }
        else //down
        {
            col = head.getCol();
            row = head.getRow();
            row = row + 1; //move one row down
        }

        return nodes.get(row).get(col);
    }
    public Node getNextHeadNodeSecondSnake(Direction dir)
    {
        Node head = snakeSecond.getSnakeHead();
        int col = 0;
        int row = 0;

        //TODO: add edge cases
        if(dir == Direction.left)
        {
            col = head.getCol();
            row = head.getRow();
            col = col - 1; //move one column left
        }
        else if(dir == Direction.right)
        {
            col = head.getCol();
            row = head.getRow();
            col = col + 1; //move one column right
        }
        else if(dir == Direction.up)
        {
            col = head.getCol();
            row = head.getRow();
            row = row - 1; //move one row up
        }
        else //down
        {
            col = head.getCol();
            row = head.getRow();
            row = row + 1; //move one row down
        }

        return nodes.get(row).get(col);
    }

    public void updateGrid(Direction dir)
    {
        //check for wall, snake node, empty node, food
        Node newHead = getNextHeadNode(dir);
        if (newHead.getIsWall() == true)
        {
            gameOver = true;
            System.out.println("Hit a wall");
        }
        else if(newHead.getIsSnake() == true)
        {
            gameOver = true;
            System.out.println("Hit the snake");
        }
        else if(newHead.getPowerUp() == true)
        {
            if(newHead.getColor() == Color.AQUA)
            {
                System.out.println("Got a food");
                FoodEaten = true;
                snake.addToSnake(newHead);
            }
            else if (newHead.getColor() == Color.DARKGOLDENROD)
            {
                System.out.println("Got a Speed");
                SpeedEaten = true;
                speed = speed + 2;
                snake.updateHead(newHead);
            }
            else
            {   
                System.out.println("Got a slow");
                SlowEaten = true;
                if(speed > 2)
                {
                    speed = speed - 2; 
                }
                snake.updateHead(newHead);
            }
        }
        else
        {
            snake.updateHead(newHead);
        }
    }
    public void updateGridMulti(Direction dir , Direction dirSecond)
    {
        //check for wall, snake node, empty node, food
        Node newHead = getNextHeadNode(dir);
        if (newHead.getIsWall() == true)
        {
            gameOver = true;
            System.out.println("Hit a wall");
        }
        else if(newHead.getIsSnake() == true)
        {
            gameOver = true;
            System.out.println("Hit the snake");
        }
        else if(newHead.getPowerUp() == true)
        {
            if(newHead.getColor() == Color.AQUA)
            {
                System.out.println("Got a food");
                FoodEaten = true;
                snake.addToSnake(newHead);
            }
            else if (newHead.getColor() == Color.DARKGOLDENROD)
            {
                System.out.println("Got a Speed");
                SpeedEaten = true;
                speed = speed + 2;
                snake.updateHead(newHead);
            }
            else
            {   
                System.out.println("Got a slow");
                SlowEaten = true;
                if(speed > 2)
                {
                    speed = speed - 2; 
                }
                snake.updateHead(newHead);
            }
        }
        else
        {
            snake.updateHead(newHead);
        }

        Node newHeadSecond = getNextHeadNodeSecondSnake(dirSecond);
        if (newHeadSecond.getIsWall() == true)
        {
            gameOver = true;
            System.out.println("Hit a wall");
        }
        else if(newHeadSecond.getIsSnake() == true)
        {
            gameOver = true;
            System.out.println("Hit the snake");
        }
        else if(newHeadSecond.getPowerUp() == true)
        {
            if(newHeadSecond.getColor() == Color.AQUA)
            {
                System.out.println("Got a food");
                FoodEaten = true;

                snakeSecond.addToSnake(newHeadSecond);
            }
            else if (newHeadSecond.getColor() == Color.DARKGOLDENROD)
            {
                System.out.println("Got a Speed");
                SpeedEaten = true;
                speed = speed + 2;
                snakeSecond.updateHead(newHeadSecond);
            }
            else
            {   
                System.out.println("Got a slow");
                SlowEaten = true;
                if(speed > 2)
                {
                    speed = speed - 2; 
                }
                snakeSecond.updateHead(newHeadSecond);
            }
        }
        else
        {
            snakeSecond.updateHead(newHeadSecond);
        }
    }
}
