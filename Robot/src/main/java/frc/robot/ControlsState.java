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

    private double forwardBackwardAxis;
    private double leftRightAxis;
    private boolean collectorUpButton;
    private boolean collectorDownButton;
    private boolean collectorUnjamButton;
    private boolean flywheelStartButton;
    private boolean flywheelStopButton;
    private double flywheelSpeedAxis;
    private boolean launchButton;
    private boolean turretUnjamButton;
    private double turretAxis;

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
        data.put("forwardBackwardAxis", this.getForwardBackwardAxis());
        data.put("leftRightAxis", this.getLeftRightAxis());
        data.put("collectorUpButton", this.isCollectorUpButton() ? 1.0 : 0.0);
        data.put("collectorDownButton", this.isCollectorDownButton() ? 1.0 : 0.0);
        data.put("collectorUnjamButton", this.isCollectorUnjamButton() ? 1.0 : 0.0);
        data.put("flywheelStartButton", this.isFlywheelStartButton() ? 1.0 : 0.0);
        data.put("flywheelStopButton", this.isFlywheelStopButton() ? 1.0 : 0.0);
        data.put("flywheelSpeedAxis", this.getFlywheelSpeedAxis());
        data.put("launchButton", this.isLaunchButton() ? 1.0 : 0.0);
        data.put("turretUnjamButton", this.isTurretUnjamButton() ? 1.0 : 0.0);
        data.put("turretAxis", this.getTurretAxis());

        return data;
    }

    public double getForwardBackwardAxis() {
        return forwardBackwardAxis;
    }

    public void setForwardBackwardAxis(double forwardBackwardAxis) {
        this.forwardBackwardAxis = forwardBackwardAxis;
    }

    public double getLeftRightAxis() {
        return leftRightAxis;
    }

    public void setLeftRightAxis(double leftRightAxis) {
        this.leftRightAxis = leftRightAxis;
    }

    public boolean isCollectorUpButton() {
        return collectorUpButton;
    }

    public void setCollectorUpButton(boolean collectorUpButton) {
        this.collectorUpButton = collectorUpButton;
    }

    public boolean isCollectorDownButton() {
        return collectorDownButton;
    }

    public void setCollectorDownButton(boolean collectorDownButton) {
        this.collectorDownButton = collectorDownButton;
    }

    public boolean isCollectorUnjamButton() {
        return collectorUnjamButton;
    }

    public void setCollectorUnjamButton(boolean collectorUnjamButton) {
        this.collectorUnjamButton = collectorUnjamButton;
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
}
