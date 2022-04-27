// package snakey.example.snakey2d;

public interface PowerUpFactory{
    public int setColor();
    public int setSpeedChange();
    public int setDirectionSwitch();
    public int setSnakeChange();
}

class SpeedUpPowerUpFactory implements PowerUpFactory{
    public int setColor() {
        return 0;
    }
    public int setSpeedChange() {
        return 1;
    }
    public int setDirectionSwitch() {
        return 0;
    }
    public int setSnakeChange() {
        return 0;
    }
}

class SlowDownPowerUpFactory implements PowerUpFactory{
    public int setColor() {
        return 1;
    }
    public int setSpeedChange() {
        return -1;
    }
    public int setDirectionSwitch() {
        return 0;
    }
    public int setSnakeChange() {
        return 0;
    }
}

class SwitchDirectionPowerUpFactory implements PowerUpFactory{
    public int setColor() {
        return 2;
    }
    public int setSpeedChange() {
        return 0;
    }
    public int setDirectionSwitch() {
        return 1;
    }
    public int setSnakeChange() {
        return 0;
    }
}

class GrowPowerUpFactory implements PowerUpFactory{
    public int setColor() {
        return 3;
    }
    public int setSpeedChange() {
        return 0;
    }
    public int setDirectionSwitch() {
        return 0;
    }
    public int setSnakeChange() {
        return 1;
    }
}

class ShrinkPowerUpFactory implements PowerUpFactory{
    public int setColor() {
        return 0;
    }
    public int setSpeedChange() {
        return 0;
    }
    public int setDirectionSwitch() {
        return 0;
    }
    public int setSnakeChange() {
        return -1;
    }
}