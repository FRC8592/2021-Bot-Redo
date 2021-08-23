package frc.robot;

/**
 * Implements the control loop logic for teleop mode. Instantiate this class
 * once per robot cycle and call the control() method.
 */
public class TeleopController {

    /**
     * Runs the control loop logic. This updates the "..State" variables that were
     * passed to the constructor.
     */
    public void control(RobotState robotState) {
        // Control the robot by reading some state values, applying some logic to them,
        // and setting other state values.

        ControlsState driveControlsState = robotState.getControlsState();
        DriveTrainState driveTrainState = robotState.getDriveTrainState();
        TurretState turretModuleState = robotState.getTurretModuleState();
        CollectorState collectorModuleState = robotState.getCollectorModuleState();

        controlDriveTrain(driveControlsState, driveTrainState);
        controlTurret(driveControlsState, turretModuleState);
        controlCollector(driveControlsState, collectorModuleState);
    }

    private void controlDriveTrain(ControlsState driveControlsState, DriveTrainState driveTrainState) {
        // Set the desired speed of the robot based on the forwardBackward input from
        // the controls. Square the input, so the controls are more forgiving. Half
        // forward is .25 throttle. Full forward is still 1.0 throttle.
        double controlPosition = driveControlsState.getForwardBackward();
        double desiredSpeed = Math.pow(controlPosition, 2);
        if (controlPosition < 0) {
            desiredSpeed = desiredSpeed * -1; // Make the negative inputs stay negative after being squared
        }
        driveTrainState.setDesiredSpeed(desiredSpeed);

        double desiredRotation = driveControlsState.getLeftRight();
        driveTrainState.setDesiredRotation(desiredRotation);
    }

    private void controlCollector(ControlsState controlsState, CollectorState collectorState) {
        collectorState.setDeploying(controlsState.isCollectorDownButton());
        collectorState.setUndeploying(controlsState.isCollectorUpButton());
        collectorState.setUnjamming(controlsState.isCollectorUnjamButton());
    }

    private void controlTurret(ControlsState controlsState, TurretState turretState) {
        turretState.setFlywheelStart(controlsState.isFlywheelStartButton());
        turretState.setFlywheelStop(controlsState.isFlywheelStopButton());
        turretState.setFlywheelSpeed(controlsState.getFlywheelSpeedAxis());
        turretState.setLaunching(controlsState.isLaunchButton());
        turretState.setUnjamming(controlsState.isTurretUnjamButton());
        turretState.setTurretRotation(controlsState.getTurretAxis());
    }
}
