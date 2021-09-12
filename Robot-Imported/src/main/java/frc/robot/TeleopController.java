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
        TurretState turretModuleState = robotState.getTurretState();
        CollectorState collectorModuleState = robotState.getCollectorState();

        controlDriveTrain(driveControlsState, driveTrainState);
        controlTurret(driveControlsState, turretModuleState);
        controlCollector(driveControlsState, collectorModuleState);
    }

    private void controlDriveTrain(ControlsState controlsState, DriveTrainState driveTrainState) {
        // Set the desired speed of the robot based on the forwardBackward input from
        // the controls. Square the input, so the controls are more forgiving. Half
        // forward is .25 throttle. Full forward is still 1.0 throttle.
        double controlPosition = controlsState.getAxisForwardReverse();
        double desiredSpeed = Math.pow(controlPosition, 2);
        if (controlPosition < 0) {
            desiredSpeed = desiredSpeed * -1; // Make the negative inputs stay negative after being squared
        }
        driveTrainState.setDriveSpeed(desiredSpeed);

        driveTrainState.setDriveRotation(controlsState.getAxisLeftRight());
    }

    private void controlCollector(ControlsState controlsState, CollectorState collectorState) {
        collectorState.setCollectorDeploying(controlsState.isButtonCollectorDown());
        collectorState.setCollectorUndeploying(controlsState.isButtonCollectorUp());
        collectorState.setCollectorUnjamming(controlsState.isButtonCollectorUnjam());
    }

    private void controlTurret(ControlsState controlsState, TurretState turretState) {
        turretState.setTurretFlywheelStart(controlsState.isButtonTurretFlywheelStart());
        turretState.setTurretFlywheelStop(controlsState.isButtonTurretFlywheelStop());
        turretState.setTurretFlywheelSpeed(controlsState.getAxisTurretFlywheelSpeed());
        turretState.setTurretLaunching(controlsState.isButtonTurretLaunch());
        turretState.setTurretUnjamming(controlsState.isButtonTurretUnjam());
        turretState.setTurretRotation(controlsState.getAxisTurretRotation());
    }
}
