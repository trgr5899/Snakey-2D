//package ooadfinal.snakey2d;

import java.util.ArrayList;
import javafx.scene.paint.Color;

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

    void addToSnake(Node node)
    {
        //set color and type of new node
        node.setIsSnake(true);
        node.setColor(snakeColor);
        node.isPowerUp = false;
        snakeBodyStack.add(0, node);

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