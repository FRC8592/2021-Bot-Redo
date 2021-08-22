package frc.robot;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the current state of the hardware used to drive the robot. The
 * hardware could be joysticks, a game controller, keyboard, or anything we
 * choose. We read a new instance of this class from DriveControlsModule at the
 * beginning of every robot cycle. After the controller updates some values on
 * that instance, we write it back to the module, which updates the desired
 * output of the motors. The updated instance is also sent as telemetry.
 */
public class DriveControlsState implements TelemetrySource {

    private double forwardBackward;
    private double leftRight;
    private boolean collectorUp;
    private boolean collectorDown;
    private boolean collectorUnjam;

    public double getForwardBackward() {
        return this.forwardBackward;
    }

    public void setForwardBackward(double forwardBackwardInput) {
        this.forwardBackward = forwardBackwardInput;
    }

    public double getLeftRight() {
        return this.leftRight;
    }

    public void setLeftRight(double leftRightInput) {
        this.leftRight = leftRightInput;
    }

    /**
     * Store convert all data represented by this class into a HashMap so that the
     * Telemetry class can more easily consume data from any module.
     * 
     * @see frc.robot.Telemetry
     * @see frc.robot.TelemetrySource
     */
    @Override
    public Map<String, Double> getTelemetryData() {
        Map<String, Double> data = new HashMap<String, Double>();

        // Add all of the data represented by this class to the Map
        data.put("forwardBackward", getForwardBackward());
        data.put("leftRight", getLeftRight());

        return data;
    }

    public boolean isCollectorUp() {
        return collectorUp;
    }

    public void setCollectorUp(boolean collectorUp) {
        this.collectorUp = collectorUp;
    }

    public boolean isCollectorDown() {
        return collectorDown;
    }

    public void setCollectorDown(boolean collectorDown) {
        this.collectorDown = collectorDown;
    }

    public boolean isCollectorUnjam() {
        return collectorUnjam;
    }

    public void setCollectorUnjam(boolean collectorUnjam) {
        this.collectorUnjam = collectorUnjam;
    }
}
