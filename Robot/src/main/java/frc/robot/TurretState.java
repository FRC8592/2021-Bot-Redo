package frc.robot;

public class TurretState {

    private double flywheelSpeed;
    private boolean isLaunching;
    private boolean isUnjamming;
    private double turretRotation;
    private boolean isFlywheelStart;
    private boolean isFlywheelStop;

    public double getFlywheelSpeed() {
        return flywheelSpeed;
    }

    public void setFlywheelSpeed(double flywheelSpeed) {
        this.flywheelSpeed = flywheelSpeed;
    }

    public boolean isLaunching() {
        return isLaunching;
    }

    public void setLaunching(boolean isLaunching) {
        this.isLaunching = isLaunching;
    }

    public boolean isUnjamming() {
        return isUnjamming;
    }

    public void setUnjamming(boolean isUnjamming) {
        this.isUnjamming = isUnjamming;
    }

    public double getTurretRotation() {
        return turretRotation;
    }

    public void setTurretRotation(double turretRotation) {
        this.turretRotation = turretRotation;
    }

    public boolean isFlywheelStart() {
        return isFlywheelStart;
    }

    public void setFlywheelStart(boolean isFlywheelStart) {
        this.isFlywheelStart = isFlywheelStart;
    }

    public boolean isFlywheelStop() {
        return isFlywheelStop;
    }

    public void setFlywheelStop(boolean isFlywheelStop) {
        this.isFlywheelStop = isFlywheelStop;
    }

}
