package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class TurretState implements TelemetrySource {

    private double turretFlywheelSpeed;
    private boolean turretFlywheelStart;
    private boolean turretFlywheelStop;
    private boolean turretLaunching;
    private double turretRotation;
    private boolean turretUnjamming;

    @Override
    public Map<String, Double> getTelemetryData() {
        Map<String, Double> data = new HashMap<String, Double>();

        // Add all of the data represented by this class to the Map
        data.put("turretFlywheelSpeed", turretFlywheelSpeed);
        data.put("turretFlywheelStart", turretFlywheelStart ? 1.0 : 0.0);
        data.put("turretFlywheelStop", turretFlywheelStop ? 1.0 : 0.0);
        data.put("turretLaunching", turretLaunching? 1.0 : 0);
        data.put("turretRotation", turretRotation);
        data.put("turretUnjamming", turretUnjamming ? 1.0 : 0.0);

        return data;
    }

    public double getTurretFlywheelSpeed() {
        return turretFlywheelSpeed;
    }

    public void setTurretFlywheelSpeed(double turretFlywheelSpeed) {
        this.turretFlywheelSpeed = turretFlywheelSpeed;
    }

    public boolean isTurretFlywheelStart() {
        return turretFlywheelStart;
    }

    public void setTurretFlywheelStart(boolean turretFlywheelStart) {
        this.turretFlywheelStart = turretFlywheelStart;
    }

    public boolean isTurretFlywheelStop() {
        return turretFlywheelStop;
    }

    public void setTurretFlywheelStop(boolean turretFlywheelStop) {
        this.turretFlywheelStop = turretFlywheelStop;
    }

    public boolean isTurretLaunching() {
        return turretLaunching;
    }

    public void setTurretLaunching(boolean turretLaunching) {
        this.turretLaunching = turretLaunching;
    }

    public double getTurretRotation() {
        return turretRotation;
    }

    public void setTurretRotation(double turretRotation) {
        this.turretRotation = turretRotation;
    }

    public boolean isTurretUnjamming() {
        return turretUnjamming;
    }

    public void setTurretUnjamming(boolean turretUnjamming) {
        this.turretUnjamming = turretUnjamming;
    }

}
