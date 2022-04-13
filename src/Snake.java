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
    Color snakeColor = Color.WHITE;
    Boolean snakeAte;
    Boolean snakeReversed;
    Boolean snakeSpedUp;
    Boolean snakeSlowedDown;
    private Direction snakeDirection = Direction.left;
    private Boolean snakeIsAlive = true;
    private ArrayList<Node> snakeBodyStack = new ArrayList<Node>();
    
    void checkAlive(Grid grid) 
    {

    }

    void updateHead(Node node) 
    {

    }

    Direction snakeDirection() 
    {
        return snakeDirection;
    }

    void changeDirection(Direction dir)
    {
        
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
    void createSnake(ArrayList<Node> snakeBody)
    {
        snakeBodyStack = snakeBody;
    }
}