//package ooadfinal.snakey2d;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score {
    private SimpleStringProperty userName;
    private SimpleIntegerProperty score;

    public Score(String userName, Integer score){
        this.userName = new SimpleStringProperty(userName);
        this.score = new SimpleIntegerProperty(score);
    }


    public void setUserName(String userName){
        this.userName.set(userName);
    }
    public String getUserName() {
        return userName.get();
    }
    public void setScore(Integer score){
        this.score.set(score);
    }
    public Integer getScore() {
        return score.get();
    }

}
