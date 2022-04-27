// package snakey.example.snakey2d;

public abstract class PowerUp {
    int color;
    int speedChange;
    int directionSwitch;
    int snakeChange;

    abstract void setPowerUp();
}

class SpeedChangePowerUp extends PowerUp{
    PowerUpFactory factory;

    public SpeedChangePowerUp(PowerUpFactory factory) {
        this.factory = factory;
    }

    void setPowerUp() {
        color = factory.setColor();
        speedChange = factory.setSpeedChange();
    }
}

class SwitchDirectionPowerUp extends PowerUp{
    PowerUpFactory factory;

    public SwitchDirectionPowerUp(PowerUpFactory factory) {
        this.factory = factory;
    }

    void setPowerUp() {
        color = factory.setColor();
        directionSwitch = factory.setDirectionSwitch();
    }
}

class SizeChangePowerUp extends PowerUp{
    PowerUpFactory factory;

    public SizeChangePowerUp(PowerUpFactory factory) {
        this.factory = factory;
    }

    void setPowerUp() {
        color = factory.setColor();
        snakeChange = factory.setSnakeChange();
    }
}

