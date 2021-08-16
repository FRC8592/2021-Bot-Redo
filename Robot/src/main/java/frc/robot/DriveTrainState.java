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

    @Override
    public Map<String, Double> getTelemetryData() {
        return new HashMap<String, Double>();
    }

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
}
