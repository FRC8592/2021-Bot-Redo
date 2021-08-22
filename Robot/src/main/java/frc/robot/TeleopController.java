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

        ControlsState driveControlsState = robotState.getDriveControlsState();
        DriveTrainState driveTrainState = robotState.getDriveTrainState();
        TurretState turretModuleState = robotState.getTurretModuleState();
        CollectorState collectorModuleState = robotState.getCollectorModuleState();

        controlDriveTrain(driveControlsState, driveTrainState);
        controlTurret(driveControlsState, turretModuleState);
        controlCollector(driveControlsState, collectorModuleState);
    }

    private void controlDriveTrain(ControlsState driveControlsState, DriveTrainState driveTrainState) {
        // Set the desired speed of the robot based on the forwardBackward input from
        // the controls
        double controlPosition = driveControlsState.getForwardBackward();
        double desiredSpeed = Math.pow(controlPosition, 2); // Square the input, so the controls are more forgiving.
                                                            // Half forward is .25 throttle. Full forward is still 1.0
                                                            // throttle.
        if (controlPosition < 0) {
            desiredSpeed = desiredSpeed * -1; // Makes it so that the negative inputs stay negative after being squared
        }
        driveTrainState.setDesiredSpeed(desiredSpeed);

        double desiredRotation = driveControlsState.getLeftRight();
        driveTrainState.setDesiredRotation(desiredRotation);
    }

    private void controlCollector(ControlsState driveControlsState, CollectorState collectorModuleState) {
        collectorModuleState.setDeploying(driveControlsState.getCollectorDown());
        collectorModuleState.setUneploying(driveControlsState.getCollectorUp());
        collectorModuleState.setUnjamming(driveControlsState.getCollectorUnjam());
    }

    private void controlTurret(ControlsState driveControlsState, TurretState turretModuleState) {
    }

}
