// package snakey.example.snakey2d;

public abstract class PowerUpSet {
    abstract PowerUp createPowerUp();

    public PowerUp returnPowerUp() {
        PowerUp powerUp = createPowerUp();
        return powerUp;
    }
}

class SpeedUpPowerUpSet extends PowerUpSet {
    protected PowerUp createPowerUp(){
        PowerUpFactory factory = new SpeedUpPowerUpFactory();
        PowerUp powerUp = new SpeedChangePowerUp(factory);
        return powerUp;
    }
}

class SlowDownPowerUpSet extends PowerUpSet {
    protected PowerUp createPowerUp(){
        PowerUpFactory factory = new SlowDownPowerUpFactory();
        PowerUp powerUp = new SpeedChangePowerUp(factory);
        return powerUp;
    }
}

class SwitchDirectionPowerUpSet extends PowerUpSet {
    protected PowerUp createPowerUp(){
        PowerUpFactory factory = new SwitchDirectionPowerUpFactory();
        PowerUp powerUp = new SwitchDirectionPowerUp(factory);
        return powerUp;
    }
}

class GrowPowerUpSet extends PowerUpSet {
    protected PowerUp createPowerUp(){
        PowerUpFactory factory = new GrowPowerUpFactory();
        PowerUp powerUp = new SizeChangePowerUp(factory);
        return powerUp;
    }
}

class ShrinkPowerUpSet extends PowerUpSet {
    protected PowerUp createPowerUp(){
        PowerUpFactory factory = new ShrinkPowerUpFactory();
        PowerUp powerUp = new SizeChangePowerUp(factory);
        return powerUp;
    }
}