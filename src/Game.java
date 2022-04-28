import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public interface Game {
    void startNormalMode(Stage primaryStage);
    void startChallengeMode(Stage primaryStage);
    Scene draw(Stage primaryStage);
    void update(Stage primaryStage);
}

class SinglePlayerGame implements Game{

    //TODO: add a powerupset variable
    //TODO: add a Player variable
    Grid grid = new Grid(500 , 50, 5);
    int snakeStartingRow;
    int snakeStartingCol;
    int speedOfSnake = grid.speed;
    Direction snakeDir = Direction.left;

    //Starts normal mode
    public void startNormalMode(Stage primaryStage)
    {
        //create and draw grid
        grid.createGrid();
        draw(primaryStage);
        timeStep(primaryStage);
    }

    //Adds Game over and Main menu button
    public void gameOverScene(Stage primaryStage)
    {
        DropShadow ds = new DropShadow();

        //grabbed from https://www.tutorialspoint.com/javafx/javafx_text.htm
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text t = new Text();
        t.setEffect(ds);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.RED);
        t.setText("GAME OVER!!!");
        t.setFont(Font.font(null, FontWeight.BOLD, 32));

        Button bBack = new Button("Back to Main Menu");
        bBack.setDefaultButton(true);
        bBack.setStyle("-fx-background-color: #ff0000; ");
        bBack.setMinHeight(50);
        bBack.setMinWidth(400);

        EventHandler<ActionEvent> eventBack = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                MainMenu mainM = new MainMenu();
                mainM.showMenu(primaryStage);
            }
        };

        bBack.setOnAction(eventBack);

        VBox vbox = new VBox(25);
        vbox.setAlignment(Pos.CENTER);
        
        vbox.getChildren().add(t);
        vbox.getChildren().add(bBack);

        // create a scene
        Scene sc = new Scene(vbox, 500, 500);
        // set the scene
        primaryStage.setScene(sc);

        primaryStage.show();

    }
    
    //steps through each timestep of the game
    public void timeStep(Stage primaryStage)
    {
        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    return;
                }

                if (now - lastTick > 1000000000 / speedOfSnake) {
                    lastTick = now;
                    update(primaryStage);
                    if(grid.gameOver == true)
                    {
                        System.out.println("THE GAME is Over!!!");
                        stop();
                        gameOverScene(primaryStage);
                        return;
                    }
                }
                draw(primaryStage);
                Scene scene = draw(primaryStage);
                scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                    if (key.getCode() == KeyCode.UP) {
                        if (snakeDir != Direction.down)
                        {
                            snakeDir = Direction.up;
                        }
                    }
                    if (key.getCode() == KeyCode.LEFT) {
                        if (snakeDir != Direction.right)
                        {
                            snakeDir = Direction.left;
                        }
                    }
                    if (key.getCode() == KeyCode.DOWN) {
                        if (snakeDir != Direction.up)
                        {
                            snakeDir = Direction.down;
                        }
                    }
                    if (key.getCode() == KeyCode.RIGHT) {
                        if (snakeDir != Direction.left)
                        {
                            snakeDir = Direction.right;
                        }
                    }
                });
                primaryStage.setScene(scene);
            }

        }.start();
    }
    public void startChallengeMode(Stage primaryStage)
    {
        //set the snake to faster speed
        speedOfSnake = 20;
        grid.speed = 20;
        //create and draw grid
        grid.createGridChallenge();
        draw(primaryStage);
        timeStep(primaryStage);
    }
    public Scene draw(Stage primaryStage)
    {
        return grid.drawGrid(primaryStage);

    }

    public void update(Stage primaryStage)
    {
        grid.updateGrid(snakeDir);
        speedOfSnake = grid.speed;
    }

}
class MultiPlayerGame implements Game{

    //TODO: add a powerupset variable
    //TODO: add a Player variable
    Grid grid = new Grid(500 , 50, 8);
    int speedOfSnake= grid.speed;
    Direction snakeDir = Direction.left;
    Direction snakeDirSecond = Direction.right;

    //Starts normal mode
    public void startNormalMode(Stage primaryStage)
    {
        //create and draw grid
        grid.createGridMulti();
        draw(primaryStage);
        timeStep(primaryStage);
    }

    //Adds Game over and Main menu button
    public void gameOverScene(Stage primaryStage)
    {
        DropShadow ds = new DropShadow();

        //grabbed from https://www.tutorialspoint.com/javafx/javafx_text.htm
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text t = new Text();
        t.setEffect(ds);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.RED);
        t.setText("GAME OVER!!!");
        t.setFont(Font.font(null, FontWeight.BOLD, 32));

        Button bBack = new Button("Back to Main Menu");
        bBack.setDefaultButton(true);
        bBack.setStyle("-fx-background-color: #ff0000; ");
        bBack.setMinHeight(50);
        bBack.setMinWidth(400);

        EventHandler<ActionEvent> eventBack = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                MainMenu mainM = new MainMenu();
                mainM.showMenu(primaryStage);
            }
        };

        bBack.setOnAction(eventBack);

        VBox vbox = new VBox(25);
        vbox.setAlignment(Pos.CENTER);
        
        vbox.getChildren().add(t);
        vbox.getChildren().add(bBack);

        // create a scene
        Scene sc = new Scene(vbox, 500, 500);
        // set the scene
        primaryStage.setScene(sc);

        primaryStage.show();

    }
    
    //steps through each timestep of the game
    public void timeStep(Stage primaryStage)
    {
        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    return;
                }

                if (now - lastTick > 1000000000 / speedOfSnake) {
                    lastTick = now;
                    update(primaryStage);
                    if(grid.gameOver == true)
                    {
                        System.out.println("THE GAME is Over!!!");
                        stop();
                        gameOverScene(primaryStage);
                        return;
                    }
                }
                draw(primaryStage);
                Scene scene = draw(primaryStage);
                scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                    if (key.getCode() == KeyCode.UP) {
                        if (snakeDir != Direction.down)
                        {
                            snakeDir = Direction.up;
                        }
                    }
                    if (key.getCode() == KeyCode.LEFT) {
                        if (snakeDir != Direction.right)
                        {
                            snakeDir = Direction.left;
                        }
                    }
                    if (key.getCode() == KeyCode.DOWN) {
                        if (snakeDir != Direction.up)
                        {
                            snakeDir = Direction.down;
                        }
                    }
                    if (key.getCode() == KeyCode.RIGHT) {
                        if (snakeDir != Direction.left)
                        {
                            snakeDir = Direction.right;
                        }
                    }
                });
                scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                    if (key.getCode() == KeyCode.W) {
                        if (snakeDirSecond != Direction.down)
                        {
                            snakeDirSecond = Direction.up;
                        }
                    }
                    if (key.getCode() == KeyCode.A) {
                        if (snakeDirSecond != Direction.right)
                        {
                            snakeDirSecond = Direction.left;
                        }
                    }
                    if (key.getCode() == KeyCode.S) {
                        if (snakeDirSecond != Direction.up)
                        {
                            snakeDirSecond = Direction.down;
                        }
                    }
                    if (key.getCode() == KeyCode.D) {
                        if (snakeDirSecond != Direction.left)
                        {
                            snakeDirSecond = Direction.right;
                        }
                    }
                });
                primaryStage.setScene(scene);
            }

        }.start();
    }
    public Scene draw(Stage primaryStage)
    {
        return grid.drawGrid(primaryStage);

    }

    public void update(Stage primaryStage)
    {
        grid.updateGridMulti(snakeDir,snakeDirSecond);
        speedOfSnake = grid.speed;
    }

    @Override
    public void startChallengeMode(Stage primaryStage) {
        
    }

}