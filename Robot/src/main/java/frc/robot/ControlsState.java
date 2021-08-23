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

    private double axisForwardReverse;
    private double axisLeftRight;
    private boolean buttonCollectorUp;
    private boolean buttonCollectorDown;
    private boolean buttonCollectorUnjam;
    private boolean buttonTurretFlywheelStart;
    private boolean buttonTurretFlywheelStop;
    private double axisTurretFlywheelSpeed;
    private boolean buttonTurretLaunch;
    private boolean buttonTurretUnjam;
    private double axisTurretRotation;

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
        data.put("axisForwardReverse", axisForwardReverse);
        data.put("axisLeftRight", axisLeftRight);
        data.put("buttonCollectorUp", buttonCollectorUp ? 1.0 : 0.0);
        data.put("buttonCollectorDown", buttonCollectorDown ? 1.0 : 0.0);
        data.put("buttonCollectorUnjam", buttonCollectorUnjam ? 1.0 : 0.0);
        data.put("buttonTurretFlywheelStart", buttonTurretFlywheelStart ? 1.0 : 0.0);
        data.put("buttonTurretFlywheelStop", buttonTurretFlywheelStop ? 1.0 : 0.0);
        data.put("axisTurretFlywheelSpeed", axisTurretFlywheelSpeed);
        data.put("buttonTurretLaunch", buttonTurretLaunch ? 1.0 : 0.0);
        data.put("buttonTurretUnjam", buttonTurretUnjam ? 1.0 : 0.0);
        data.put("axisTurretRotation", axisTurretRotation);

        return data;
    }

    public double getAxisForwardReverse() {
        return axisForwardReverse;
    }

    public void setAxisForwardReverse(double axisForwardReverse) {
        this.axisForwardReverse = axisForwardReverse;
    }

    public double getAxisLeftRight() {
        return axisLeftRight;
    }

    public void setAxisLeftRight(double axisLeftRight) {
        this.axisLeftRight = axisLeftRight;
    }

    public boolean isButtonCollectorUp() {
        return buttonCollectorUp;
    }

    public void setButtonCollectorUp(boolean buttonCollectorUp) {
        this.buttonCollectorUp = buttonCollectorUp;
    }

    public boolean isButtonCollectorDown() {
        return buttonCollectorDown;
    }

    public void setButtonCollectorDown(boolean buttonCollectorDown) {
        this.buttonCollectorDown = buttonCollectorDown;
    }

    public boolean isButtonCollectorUnjam() {
        return buttonCollectorUnjam;
    }

    public void setButtonCollectorUnjam(boolean buttonCollectorUnjam) {
        this.buttonCollectorUnjam = buttonCollectorUnjam;
    }

    public boolean isButtonTurretFlywheelStart() {
        return buttonTurretFlywheelStart;
    }

    public void setButtonTurretFlywheelStart(boolean buttonTurretFlywheelStart) {
        this.buttonTurretFlywheelStart = buttonTurretFlywheelStart;
    }

    public boolean isButtonTurretFlywheelStop() {
        return buttonTurretFlywheelStop;
    }

    public void setButtonTurretFlywheelStop(boolean buttonTurretFlywheelStop) {
        this.buttonTurretFlywheelStop = buttonTurretFlywheelStop;
    }

    public double getAxisTurretFlywheelSpeed() {
        return axisTurretFlywheelSpeed;
    }

    public void setAxisTurretFlywheelSpeed(double axisTurretFlywheelSpeed) {
        this.axisTurretFlywheelSpeed = axisTurretFlywheelSpeed;
    }

    public boolean isButtonTurretLaunch() {
        return buttonTurretLaunch;
    }

    public void setButtonTurretLaunch(boolean buttonTurretLaunch) {
        this.buttonTurretLaunch = buttonTurretLaunch;
    }

    public boolean isButtonTurretUnjam() {
        return buttonTurretUnjam;
    }

    public void setButtonTurretUnjam(boolean buttonTurretUnjam) {
        this.buttonTurretUnjam = buttonTurretUnjam;
    }

    public double getAxisTurretRotation() {
        return axisTurretRotation;
    }

    public void setAxisTurretRotation(double axisTurretRotation) {
        this.axisTurretRotation = axisTurretRotation;
    }

}
