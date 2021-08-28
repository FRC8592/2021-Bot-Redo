package frc.robot;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the current state of the motors that drive the robot. We read a
 * new instance of this class from DriveTrainRobot at the beginning of every
 * robot cycle. After the controller updates some values on that instance, we
 * write it back to the module, which updates the desired output of the motors.
 * The updated instance is also sent as telemetry.
 */
public class DriveTrainState implements TelemetrySource {

    private double driveRotation = 0;
    private double driveSpeed = 0;

    @Override
    public Map<String, Double> getTelemetryData() {
        Map<String, Double> data = new HashMap<String, Double>();

        // Add all of the data represented by this class to the Map
        data.put("driveRotation", driveRotation);
        data.put("driveSpeed", driveSpeed);

        return data;
    }

    public double getDriveRotation() {
        return driveRotation;
    }

    public void setDriveRotation(double driveRotation) {
        this.driveRotation = driveRotation;
    }

    public double getDriveSpeed() {
        return driveSpeed;
    }

    public void setDriveSpeed(double driveSpeed) {
        this.driveSpeed = driveSpeed;
    }

}
