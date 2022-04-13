//package ooadfinal.snakey2d;

import java.sql.*;
import java.util.ArrayList;

public class GameDB implements Subject{
    // GameDB Singleton instance
    private static final GameDB gameDB_instance = new GameDB();

    // GameDB state attributes
    private Boolean connected = false;
    private String path       = null;
    private Connection dbConn = null;

    // Static SQL Queries
    private static final String create_table_users =     "CREATE TABLE IF NOT EXISTS users (UserID INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
                                                   "username TEXT NOT NULL UNIQUE, " +
                                                   "password TEXT NOT NULL)";
    private static final String create_table_NM_scores = "CREATE TABLE IF NOT EXISTS nmscores (ScoreID INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
                                                    "UserID INTEGER NOT NULL, " +
                                                    "username TEXT NOT NULL, " +
                                                    "Score INTEGER NOT NULL)";
    private static final String create_table_CM_scores = "CREATE TABLE IF NOT EXISTS cmscores (ScoreID INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
                                                    "UserID INTEGER NOT NULL, " +
                                                    "username TEXT NOT NULL, " +
                                                    "Score INTEGER NOT NULL)";
    private static final String create_table_MM_scores = "CREATE TABLE IF NOT EXISTS mmscores (ScoreID INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
                                                    "UserID INTEGER NOT NULL, " +
                                                    "username TEXT NOT NULL, " +
                                                    "Score INTEGER NOT NULL)";
    private static final String insert_user_row        = "INSERT INTO users(UserID, username, password) VALUES(null,?,?)";
    private static final String select_user_row        = "SELECT * FROM users WHERE username=? AND password=?";

    // Constructor
    private GameDB(){}

    // Retrieve singleton
    public static GameDB get_instance(){
        return gameDB_instance;
    }

    // Set path to DB
    public void setDBPath(String path){
        this.path = path;
    }

    // Setup tables for new DB
    public Boolean setupDB(){
        // Connect & Verify
        if(!connect()){
            return false;
        }

        // Success flag
        boolean succ = true;

        // Create needed tables
        succ &= createTable(create_table_users);
        succ &= succ & createTable(create_table_NM_scores);
        succ &= succ & createTable(create_table_CM_scores);
        succ &= succ & createTable(create_table_MM_scores);

        return succ;
    }

    private boolean createTable(String query){
        // Connect & Verify
        if(!connect()){
            return false;
        }

        // Success flag
        boolean succ = true;

        // Attempt to execute statement (Update based on parameter flag)
        try {
            Statement exec_statement = dbConn.createStatement();

            exec_statement.executeUpdate(query);
            exec_statement.close();

        } catch(SQLException e){
            // Notify observers of exception
            String errStr = "Could not create table: " + query + ". Exception: " + e.getMessage();
            ArrayList<String> msgData = new ArrayList<>();
            msgData.add(errStr);

            Message errMsg = new Message(MessageID.ERR_DB_CREATE_TABLE, null, msgData);
            notifyObservers(errMsg);

            succ = false;
        } finally {
            // Disconnect and return status
            disconnect();
        }
        return succ;
    }

    // sqlite-jdbc reference: https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
    private Boolean connect(){
        // Return true if already connected
        if(dbConn != null && connected){
            return true;
        }

        connected = false;

        // Return false if no path is set
        if(path == null){
            return false;
        }

        // Attempt connection
        try {
            try{
                Class.forName("org.sqlite.JDBC");
            } catch(Exception E){
                System.out.println("this failed.");
            }
            dbConn = DriverManager.getConnection(path);
        } catch(SQLException e){
            // Notify observers of exception
            String errStr = "Could not initiate connection to db: " + path + ". Exception: " + e.getMessage();
            ArrayList<String> msgData = new ArrayList<>();
            msgData.add(errStr);

            Message errMsg = new Message(MessageID.ERR_DB_CONN, null, msgData);
            notifyObservers(errMsg);
            return false;
        }

        // Verify state for return value
        connected = dbConn != null;
        return connected;
    }

    private void disconnect(){
        // Attempt to close connection if non-null
        try {
            if(dbConn != null) {
                dbConn.close();
                dbConn = null;
            }
        } catch(SQLException e){
            // Notify observers of exception
            String errStr = "Could not close connection to db: " + path + ". Exception: " + e.getMessage();
            ArrayList<String> msgData = new ArrayList<>();
            msgData.add(errStr);

            Message errMsg = new Message(MessageID.ERR_DB_CLOSE, null, msgData);
            notifyObservers(errMsg);
        }
    }

    // Gamemode: 0 = NM 1 = CM, 2 = MM
    public ArrayList<String> getscores(Integer gamemode){
        String query = "SELECT * FROM ";

        switch(gamemode){
            case 0:
                query += "nmscores";
                break;
            case 1:
                query += "cmscores";
                break;
            case 2:
                query += "mmscores";
                break;
            default:
                // Notify observers of default case
                String errStr = "Default case in GameDB Get Scores (Unknown gamemode/leaderboard)";
                ArrayList<String> msgData = new ArrayList<>();
                msgData.add(errStr);

                Message errMsg = new Message(MessageID.ERR_DB_SEL_SCORE, null, msgData);
                notifyObservers(errMsg);
                return null;
        }

        // Connect & Verify
        if(!connect()){
            return null;
        }
        // Success flag
        ArrayList<String> scores = new ArrayList<>();

        // Attempt select
        try {
            Statement select_scores_stmt = dbConn.createStatement();

            ResultSet res = select_scores_stmt.executeQuery(query);

            while(res.next()){
                scores.add(res.getString("username"));
                scores.add(String.valueOf(res.getInt("UserID")));
                scores.add(String.valueOf(res.getInt("Score")));
            }

            res.close();
            select_scores_stmt.close();

        } catch(SQLException e){
            // Notify observers of exception
            String errStr = "Could not select scores from gamemode: " + gamemode + ". Exception: " + e.getMessage();
            ArrayList<String> msgData = new ArrayList<>();
            msgData.add(errStr);

            Message errMsg = new Message(MessageID.ERR_DB_SEL_SCORE, null, msgData);
            notifyObservers(errMsg);

            scores = null;
        } finally {
            // Disconnect & return scores
            disconnect();
        }
        return scores;
    }

    // Gamemode: 0 = NM 1 = CM, 2 = MM
    public Boolean writeScore(Integer gamemode, Integer userID, String username, Integer score){
        String query = "INSERT INTO ";

        switch(gamemode){
            case 0:
                query += "nmscores";
                break;
            case 1:
                query += "cmscores";
                break;
            case 2:
                query += "mmscores";
                break;
            default:
                // Notify observers of default case
                String errStr = "Default case in GameDB Write Score (Unknown gamemode/leaderboard)";
                ArrayList<String> msgData = new ArrayList<>();
                msgData.add(errStr);

                Message errMsg = new Message(MessageID.ERR_DB_INS_SCORE, null, msgData);
                notifyObservers(errMsg);
                return false;
        }

        query += "(ScoreID, username, UserID, Score) VALUES(null, ?, ?, ?)";


        // Connect & Verify
        if(!connect()){
            return false;
        }
        // Success flag
        boolean succ = true;

        // Attempt insert
        try {
            PreparedStatement insert_score_stmt = dbConn.prepareStatement(query);

            // Insert parameters into static query & execute
            insert_score_stmt.setString(1, username);
            insert_score_stmt.setInt(2, userID);
            insert_score_stmt.setInt(3, score);

            insert_score_stmt.executeUpdate();
            insert_score_stmt.close();

        } catch(SQLException e){
            // Notify observers of exception
            String errStr = "Could not write score w/ username " + username + " and id: " + userID + ". Exception: " + e.getMessage();
            ArrayList<String> msgData = new ArrayList<>();
            msgData.add(errStr);

            Message errMsg = new Message(MessageID.ERR_DB_INS_USER, null, msgData);
            notifyObservers(errMsg);

            succ = false;
        } finally {
            // Disconnect & return status
            disconnect();
        }
        return succ;
    }

    public Boolean writeUser(String username, String pw){
        // Connect & Verify
        if(!connect()){
            return false;
        }
        // Success flag
        boolean succ = true;

        // Attempt insert
        try {
            PreparedStatement insert_user_stmt = dbConn.prepareStatement(insert_user_row);

            // Insert parameters into static query & execute
            insert_user_stmt.setString(1, username);
            insert_user_stmt.setString(2, pw);

            insert_user_stmt.executeUpdate();
            insert_user_stmt.close();

        } catch(SQLException e){
            // Notify observers of exception
            String errStr = "Could not write user w/ username " + username + " and pw: " + pw + ". Exception: " + e.getMessage();
            ArrayList<String> msgData = new ArrayList<>();
            msgData.add(errStr);

            Message errMsg = new Message(MessageID.ERR_DB_INS_USER, null, msgData);
            notifyObservers(errMsg);

            succ = false;
        } finally {
            // Disconnect & return status
            disconnect();
        }
        return succ;
    }

    // IF return is NULL, user does not exist
    // ELSE return userID
    public Integer checkUser(String username, String pw){
        // Connect & Verify
        if(!connect()){
            return null;
        }

        // result data
        Integer userID = null;

        // Attempt SELECT
        try {
            PreparedStatement select_user_stmt = dbConn.prepareStatement(select_user_row);

            // Insert parameters into static query & execute
            select_user_stmt.setString(1, username);
            select_user_stmt.setString(2, pw);

            ResultSet res = select_user_stmt.executeQuery();

            if(res.next()){
                userID = res.getInt("userID");
            }
            res.close();
            select_user_stmt.close();

        } catch(SQLException e){
            // Notify observers of exception
            String errStr = "Could select for user w/ username " + username + " and pw: " + pw + ". Exception: " + e.getMessage();
            ArrayList<String> msgData = new ArrayList<>();
            msgData.add(errStr);

            Message errMsg = new Message(MessageID.ERR_DB_SEL_USER, null, msgData);
            notifyObservers(errMsg);

            userID = null;
        } finally {
            // Disconnect & return userID
            disconnect();
        }
        return userID;
    }

    public static void db_test(){
        DebugLogger logger = DebugLogger.getLogger();
        logger.openLogger("STDOUT");

        GameDB db = GameDB.get_instance();
        db.registerObserver(logger);

        db.setDBPath("jdbc:sqlite:snake.db");
        Boolean res1 = db.setupDB();

        Boolean res2 = db.writeUser("Connor", "abcd");

        Boolean res3 = db.writeUser("Trevor", "1234");

        Integer uid1 = db.checkUser("Connor", "abcd");
        Integer uid2 = db.checkUser("Trevor", "1234");
        Integer uid3 = db.checkUser("Doesntexist", "password");

        logger.closeLogger();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);

        System.out.println(uid1);
        System.out.println(uid2);
        System.out.println(uid3);
        System.out.println("========================================");


        Boolean res = db.writeScore(0, uid1, "Connor", 400);
        System.out.println(res);
        res = db.writeScore(0, uid2, "Trevor", 500);
        System.out.println(res);
        res = db.writeScore(1, uid1, "Connor", 600);
        System.out.println(res);
        res = db.writeScore(1, uid1, "Connor", 700);
        System.out.println(res);

        ArrayList<String> scores = db.getscores(0);

        for(String s: scores){
            System.out.println(s);
        }
        System.out.println("========================================");
        scores = db.getscores(1);

        for(String s: scores){
            System.out.println(s);
        }
        System.out.println("========================================");
        scores = db.getscores(2);

        for(String s: scores){
            System.out.println(s);
        }
    }
}