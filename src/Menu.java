// package snakey.example.snakey2d;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;


public interface Menu {
    public Stage showMenu(Stage primaryStage);
    // void buildMenu();
    // void handlebutton(int nuttonNum);
    // void getText();

}
class MainMenu implements Menu{
    Stage primStage = new Stage();
    public Stage showMenu(Stage primaryStage)
    {

        // set title for the stage
        DropShadow ds = new DropShadow();

        //grabbed from https://www.tutorialspoint.com/javafx/javafx_text.htm
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text t = new Text();
        t.setEffect(ds);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.BLACK);
        t.setText("Main Menu");
        t.setFont(Font.font(null, FontWeight.BOLD, 32));

        //set regular button
        Button bRegular = new Button("Regular");
        bRegular.setDefaultButton(true);
        bRegular.setStyle("-fx-background-color: #00ff00; ");
        bRegular.setMinHeight(50);
        bRegular.setMinWidth(400);

        // set challenge button
        Button bChallenge = new Button("Challenge");
        bChallenge.setDefaultButton(true);
        bChallenge.setStyle("-fx-background-color: #00ffd7; ");
        bChallenge.setMinHeight(50);
        bChallenge.setMinWidth(400);

        // set Multiplayer button
        Button bMultiplayer = new Button("Multiplayer");
        bMultiplayer.setDefaultButton(true);
        bMultiplayer.setStyle("-fx-background-color: #0072f4; ");
        bMultiplayer.setMinHeight(50);
        bMultiplayer.setMinWidth(400);

        // set leaderboard button
        Button bLeaderboard = new Button("Leaderboard");
        bLeaderboard.setDefaultButton(true);
        bLeaderboard.setStyle("-fx-background-color: #7bbee7; ");
        bLeaderboard.setMinHeight(50);
        bLeaderboard.setMinWidth(300);

        // set logout button
        Button bLogout = new Button("Logout");
        bLogout.setDefaultButton(true);
        bLogout.setStyle("-fx-background-color: #a2a2a2; ");

        // set vertical box for buttons
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        // when button is pressed
        // action event
        EventHandler<ActionEvent> eventRegular = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                oldGame oldgame = new oldGame();
                oldgame.start(primaryStage);
            }
        };

        EventHandler<ActionEvent> eventChallenge = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Grid grid = new Grid(500 , 50); //Passed in 500 size and 50 nodes
                grid.createGrid();
                grid.drawGrid(primaryStage);
            }
        };
        EventHandler<ActionEvent> eventMultiplayer = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {

            }
        };
        EventHandler<ActionEvent> eventLeaderboard = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {

            }
        };
        EventHandler<ActionEvent> eventLogout = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                LoginMenu logMenu = new LoginMenu();
                primStage = logMenu.showMenu(primaryStage);
            }
        };

        //set the actions
        bRegular.setOnAction(eventRegular);
        bChallenge.setOnAction(eventChallenge);
        bMultiplayer.setOnAction(eventMultiplayer);
        bLeaderboard.setOnAction(eventLeaderboard);
        bLogout.setOnAction(eventLogout);

        // add button
        vbox.getChildren().add(t);
        vbox.getChildren().add(bRegular);
        vbox.getChildren().add(bChallenge);
        vbox.getChildren().add(bMultiplayer);
        vbox.getChildren().add(bLeaderboard);
        vbox.getChildren().add(bLogout);

        // create a scene
        Scene sc = new Scene(vbox, 500, 500);
        // set the scene
        primaryStage.setScene(sc);

        primaryStage.show();

        return primaryStage;
    }

}

class RegisterMenu implements Menu {
    Stage primStage = new Stage();
    public Stage showMenu(Stage primaryStage) {
        primStage = primaryStage;

        // set title for the stage
        DropShadow ds = new DropShadow();

        //grabbed from https://www.tutorialspoint.com/javafx/javafx_text.htm
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text t = new Text();
        t.setEffect(ds);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.BLACK);
        t.setText("Register New Account");
        t.setFont(Font.font(null, FontWeight.BOLD, 32));

        //set username text box
        TextField username = new TextField ();
        username.setPromptText("Enter your username: ");
        username.setMaxWidth(400);

        // set password text box
        PasswordField password = new PasswordField();
        password.setPromptText("Enter your password:");
        password.setMaxWidth(400);

        //Set confirm password box
        PasswordField password2 = new PasswordField();
        password2.setPromptText("Confirm your password:");
        password2.setMaxWidth(400);

        // set logout button
        Button bSubmit = new Button("Submit");
        bSubmit.setDefaultButton(true);
        bSubmit.setStyle("-fx-background-color: #7bbee7; ");

        // set vertical box for buttons
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        // when button is pressed
        // action event
        EventHandler<ActionEvent> eventSubmit = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                String user = username.getText();
                String pass = password.getText();
                String pass2 = password2.getText();
                if (user.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
                    Text noInput = new Text();
                    noInput.setText("Please fill out all fields");
                    vbox.getChildren().add(noInput);
                }
                else if(!pass.equals(pass2)) {
                    Text passMatch = new Text();
                    passMatch.setText("Passwords don't match");
                    vbox.getChildren().add(passMatch);
                }
                else {
                    MainMenu main_menu = new MainMenu();
                    primStage = main_menu.showMenu(primaryStage);
                }
            }
        };

        //set the actions
        bSubmit.setOnAction(eventSubmit);

        vbox.getChildren().add(t);
        vbox.getChildren().add(username);
        vbox.getChildren().add(password);
        vbox.getChildren().add(password2);
        vbox.getChildren().add(bSubmit);

        // create a scene
        Scene sc = new Scene(vbox, 500, 500);
        // set the scene
        primaryStage.setScene(sc);

        primaryStage.show();

        return primaryStage;
    }
}

class LoginMenu implements Menu {
    Stage primStage = new Stage();
    public Stage showMenu(Stage primaryStage) {
        primStage = primaryStage;

        // set title for the stage
        DropShadow ds = new DropShadow();

        //grabbed from https://www.tutorialspoint.com/javafx/javafx_text.htm
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text t = new Text();
        t.setEffect(ds);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.BLACK);
        t.setText("Login");
        t.setFont(Font.font(null, FontWeight.BOLD, 32));

        //set username text box
        TextField username = new TextField ();
        username.setPromptText("Enter your username: ");
        username.setMaxWidth(400);

        // set password text box
        PasswordField password = new PasswordField();
        password.setPromptText("Enter your password:");
        password.setMaxWidth(400);

        // set logout button
        Button bLogin = new Button("Login");
        bLogin.setDefaultButton(true);
        bLogin.setStyle("-fx-background-color: #7bbee7; ");

        Button bRegister = new Button("Don't have an account yet?");
        bRegister.setDefaultButton(true);
        bRegister.setStyle("-fx-background-color: #a2a2a2; ");

        // set vertical box for buttons
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        HBox hbox = new HBox();
        hbox.setMinHeight(50);

        // when button is pressed
        // action event
        EventHandler<ActionEvent> eventLogin = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                String user = username.getText();
                String pass = password.getText();
                if (user.isEmpty() || pass.isEmpty()) {
                    Text noInput = new Text();
                    noInput.setText("Please fill out all fields");
                    vbox.getChildren().add(noInput);
                }
                else if (true) {
                    MainMenu main_menu = new MainMenu();
                    primStage = main_menu.showMenu(primaryStage);
                }
                else {
                    Text incorrect = new Text();
                    incorrect.setText("Incorrect login");
                    vbox.getChildren().add(incorrect);
                }
            }
        };

        EventHandler<ActionEvent> eventRegister = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                RegisterMenu regMenu = new RegisterMenu();
                primStage = regMenu.showMenu(primaryStage);
            }
        };

        //set the actions
        bLogin.setOnAction(eventLogin);
        bRegister.setOnAction(eventRegister);

        vbox.getChildren().add(t);
        vbox.getChildren().add(username);
        vbox.getChildren().add(password);
        vbox.getChildren().add(bLogin);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(bRegister);

        // create a scene
        Scene sc = new Scene(vbox, 500, 500);
        // set the scene
        primaryStage.setScene(sc);

        primaryStage.show();

        return primaryStage;
    }
}

