import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Node {
    private Rectangle boundRectangle = new Rectangle();
    private Color nodeColor;
    private int nodeRow;
    private int nodeCol;
    private boolean nodeIsSnake;
    private boolean nodeIsWall;

    Node(Color color, int row, int col, int height, boolean snake, boolean wall)
    {
        nodeColor = color;
        nodeRow = row;
        nodeCol = col;
        nodeIsSnake = snake;
        boundRectangle.setWidth(height);
        boundRectangle.setFill(nodeColor);

        //TODO: Add a powerUp variable
    }
    public int getRow()
    {
        return nodeRow;
    }
    public void setRow(int row)
    {
        nodeRow = row;
    }
    public int getCol()
    {
        return nodeCol;
    }
    public void setCol(int col)
    {
        nodeCol = col;
    }
    public boolean getIsSnake()
    {
        return nodeIsSnake;
    }
    public void setIsSnake(boolean snake)
    {
        nodeIsSnake = snake;
    }
    public boolean getIsWall()
    {
        return nodeIsWall;
    }
    public void setIsWall(boolean wall)
    {
        nodeIsWall = wall;
    }
    public Color getColor()
    {
        return nodeColor;
    }
    public void setColor(Color color)
    {
        nodeColor = color;
        boundRectangle.setFill(nodeColor);
    }
    public Rectangle getRect()
    {
        return boundRectangle;
    }

}
