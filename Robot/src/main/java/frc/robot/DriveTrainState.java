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

    private double m_desiredRotation = 0;
    private double m_desiredSpeed = 0;

    public double getDesiredRotation() {
        return m_desiredRotation;
    }

    public void setDesiredRotation(double desiredRotation) {
        m_desiredRotation = desiredRotation;
    }

    public double getDesiredSpeed() {
        return m_desiredSpeed;
    }

    public void setDesiredSpeed(double desiredSpeed) {
        m_desiredSpeed = desiredSpeed;
    }

    @Override
    public Map<String, Double> getTelemetryData() {
        Map<String, Double> data = new HashMap<String, Double>();

        // Add all of the data represented by this class to the Map
        data.put("getDesiredRotation", getDesiredRotation());
        data.put("getDesiredSpeed", getDesiredSpeed());

        return data;
    }
}
