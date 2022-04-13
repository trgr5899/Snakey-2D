package ooadfinal.snakey2d;

import java.util.ArrayList;
import java.time.Instant;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.IOException;

enum MessageID {
    ERR_DB_CONN, ERR_DB_CLOSE, ERR_DB_CREATE_TABLE, ERR_DB_INS_USER, ERR_DB_SEL_USER,

    ERR_DB_INS_SCORE, ERR_DB_SEL_SCORE
}

class Message {
    // Message data & metadata attributes
    private MessageID id;                  // Message ID (Observers match this ID with messages they care about)
    private Long unixTime;               // Time message was created in unix time (seconds)
    private ArrayList<Double> floatData = null; // Float data associated with Message ID type (Could be Null)
    private ArrayList<String> strData = null;   // String data associated with Message ID type (Could be Null)

    // Only set attributes through constructor
    public Message(MessageID id, ArrayList<Double> floatData, ArrayList<String> strData){
        this.id = id;                                   // Save message data
        this.floatData = floatData;
        this.strData = strData;
        this.unixTime = Instant.now().getEpochSecond(); // Get message creation unix time
    }

    // Getter methods
    public MessageID getId(){
        return id;
    }

    public Long getCreationTime(){
        return unixTime;
    }

    public ArrayList<Double> getFloatData(){
        return floatData;
    }

    public ArrayList<String> getStringData(){
        return strData;
    }
};

abstract interface Observer {
    public abstract void update(Message msg);
};

interface Subject {
    // List of observers observing subject
    public ArrayList<Observer> observers = new ArrayList<Observer>();

    // Register a new observer
    public default void registerObserver(Observer obs){
        observers.add(obs);
    }

    // Remove an observer
    public default void removeObserver(Observer obs){
        Iterator<Observer> iter = observers.iterator();

        while(iter.hasNext()){
            Observer curr_obs = (Observer) iter.next();
            if(curr_obs == obs){
                iter.remove();
                return;
            }
        }
    }

    // Notify all observers of subject w/ Message
    public default void notifyObservers(Message msg){
        for(Observer obs: observers){
            obs.update(msg);
        }
    }
};


// Single observer DebugLogger
// Logs debug message events to a file when opened and registered w/ subjects
class DebugLogger implements Observer {
    // Static reference to singleton
    private static DebugLogger dlogger_instance = new DebugLogger();
    // FileWriter to log file
    private FileWriter logFile = null;
    private Boolean logSTDOUT = false;

    // Empty Constructor
    private DebugLogger(){}

    // Get singleton instance
    public static DebugLogger getLogger(){
        return dlogger_instance;
    }

    // Open logfile
    public void openLogger(String fileName){
        // Set to log to STDOUT rather than a file
        if(fileName.equals("STDOUT")){
            logSTDOUT = true;
            return;
        }

        // Else open file
        try{
            logFile = new FileWriter(fileName);
        } catch (IOException e){
            System.out.println("Couldn't open log file");
            e.printStackTrace();
        }
    }

    // Update log file w/ message
    @Override
    public void update(Message msg){
        // Handle message IDs we care about here.
        String line = null;

        switch(msg.getId()){
            case ERR_DB_CONN:
            case ERR_DB_CLOSE:
            case ERR_DB_CREATE_TABLE:
            case ERR_DB_INS_USER:
            case ERR_DB_SEL_USER:
            case ERR_DB_INS_SCORE:
                line = msg.getStringData().get(0);
                break;
            default:
                break;
        }

        // If line is still null, message was not handled so do not log
        if(line != null){
            try{
                // Print to STDOUT it specified
                if(logSTDOUT){
                    System.out.println(line);
                }else if(logFile != null){
                    logFile.write(line + "\n");
                }
            }catch (IOException e){
                System.out.println("Couldn't open log file");
                e.printStackTrace();
            }
        }
    }

    // Close logfile
    public void closeLogger(){
        try{
            if(logFile != null && !logSTDOUT){
                logFile.close();
            }
        } catch(IOException e){

        }
    }
};
/*
class ScoreTracker implements Observer {

};
*/