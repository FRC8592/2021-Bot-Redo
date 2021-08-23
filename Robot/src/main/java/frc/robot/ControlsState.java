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
public class ControlsState implements TelemetrySource {

    private double forwardBackward;
    private double leftRight;
    private boolean collectorUp;
    private boolean collectorDown;
    private boolean collectorUnjam;
    private boolean flywheelStartButton;
    private boolean flywheelStopButton;
    private double flywheelSpeedAxis;
    private boolean launchButton;
    private boolean turretUnjamButton;
    private double turretAxis;

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

    public boolean getCollectorUp() {
        return collectorUp;
    }

    public void setCollectorUp(boolean collectorUp) {
        this.collectorUp = collectorUp;
    }

    public boolean getCollectorDown() {
        return collectorDown;
    }

    public void setCollectorDown(boolean collectorDown) {
        this.collectorDown = collectorDown;
    }

    public boolean getCollectorUnjam() {
        return collectorUnjam;
    }

    public void setCollectorUnjam(boolean collectorUnjam) {
        this.collectorUnjam = collectorUnjam;
    }

    public boolean isFlywheelStartButton() {
        return flywheelStartButton;
    }

    public void setFlywheelStartButton(boolean flywheelStartButton) {
        this.flywheelStartButton = flywheelStartButton;
    }

    public boolean isFlywheelStopButton() {
        return flywheelStopButton;
    }

    public void setFlywheelStopButton(boolean flywheelStopButton) {
        this.flywheelStopButton = flywheelStopButton;
    }

    public double getFlywheelSpeedAxis() {
        return flywheelSpeedAxis;
    }

    public void setFlywheelSpeedAxis(double flywheelSpeedAxis) {
        this.flywheelSpeedAxis = flywheelSpeedAxis;
    }

    public boolean isLaunchButton() {
        return launchButton;
    }

    public void setLaunchButton(boolean launchButton) {
        this.launchButton = launchButton;
    }

    public boolean isTurretUnjamButton() {
        return turretUnjamButton;
    }

    public void setTurretUnjamButton(boolean turretUnjamButton) {
        this.turretUnjamButton = turretUnjamButton;
    }

    public double getTurretAxis() {
        return turretAxis;
    }

    public void setTurretAxis(double turretAxis) {
        this.turretAxis = turretAxis;
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
        data.put("collectorUp", getLeftRight());
        data.put("collectorDown", getLeftRight());
        data.put("collectorUnjam", getLeftRight());
        data.put("flywheelStartButton", getLeftRight());
        data.put("flywheelStopButton", getLeftRight());
        data.put("flywheelSpeedAxis", getLeftRight());
        data.put("launchButton", getLeftRight());
        data.put("turretUnjamButton", getLeftRight());
        data.put("turretAxis", getLeftRight());

        return data;
    }
}
