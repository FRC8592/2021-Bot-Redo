package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class TurretState implements TelemetrySource {

    private double flywheelSpeed;
    private boolean isLaunching;
    private boolean isUnjamming;
    private double turretRotation;
    private boolean isFlywheelStart;
    private boolean isFlywheelStop;

    @Override
    public Map<String, Double> getTelemetryData() {
        Map<String, Double> data = new HashMap<String, Double>();

        // Add all of the data represented by this class to the Map
        data.put("flywheelSpeed", getFlywheelSpeed());
        data.put("isLaunching", isLaunching() ? 1.0 : 0.0);
        data.put("isUnjamming", isUnjamming() ? 1.0 : 0.0);
        data.put("turretRotation", getTurretRotation());
        data.put("isFlywheelStart", isFlywheelStart() ? 1.0 : 0.0);
        data.put("isFlywheelStop", isFlywheelStop() ? 1.0 : 0.0);

        return data;
    }

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
