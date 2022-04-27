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


public class Snake {
    Color snakeColor;
    Boolean snakeAte;
    Boolean snakeReversed;
    Boolean snakeSpedUp;
    Boolean snakeSlowedDown;
    private Direction snakeDirection;
    private Boolean snakeIsAlive = true;
    private ArrayList<Node> snakeBodyStack = new ArrayList<Node>();
    

    void updateHead(Node node) 
    {
        //set color and type of new node
        node.setIsSnake(true);
        node.setColor(snakeColor);
        snakeBodyStack.add(0, node);

        //change old node and change color
        Node oldSnakNode = snakeBodyStack.get(snakeBodyStack.size()-1);
        oldSnakNode.setIsSnake(false);
        oldSnakNode.setColor(Color.BLACK);
        snakeBodyStack.remove(snakeBodyStack.size()-1);

    }
    Node getSnakeHead()
    {
        return snakeBodyStack.get(0);
    }
    Direction snakeDirection() 
    {
        return snakeDirection;
    }

    void changeDirection(Direction dir)
    {
        snakeDirection = dir;
    }

    void setToSnakeNode(int row, int col) 
    {

    }

    ArrayList<Node >getSnake() 
    {
        return snakeBodyStack;
    }

    void setSnakeAlive(boolean isAlive)
    {

    }

    Boolean getSnakeAlive() 
    {
        return snakeIsAlive;
    }

    void snakeTimeStep(Grid grid) 
    {

    }

    void addToSnake()
    {

    }

    void reverseSnakeDirection() 
    {

    }
    void createSnake(ArrayList<Node> snakeBody, Color color)
    {
        snakeBodyStack = snakeBody;
        snakeColor = color;
    }
}