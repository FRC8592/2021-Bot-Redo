package frc.robot;

/**
 * Implements the control loop logic for teleop mode. Instantiate this class once per
 * robot cycle and call the control() method.
 */
public class TeleopController {

    private DriveControlsState driveControlsState;
    private DriveTrainState driveTrainState;
    
    /**
     * Constructor. Pass in all of the state variables that you want this controller to read or update.
     */
    public TeleopController(DriveControlsState driveControlsState, DriveTrainState driveTrainState) {
        this.driveControlsState = driveControlsState;
        this.driveTrainState = driveTrainState;
    }

    /**
     * Runs the control loop logic. This updates the "..State" varialbes that were passed to the constructor.
     */
    public void control() {
        // Control the robot by reading some state values, applying some logic to them, and setting other state values.

        // Set the desired speed of the robot based on the forwardBackward input from the controls
        double controlPosition = driveControlsState.getForwardBackward();
        double desiredSpeed = Math.pow(controlPosition, 2); // Square the input, so the controls are more forgiving. Half forward is .25 throttle. Full forward is still 1.0 throttle.
        if (controlPosition < 0){
            desiredSpeed = desiredSpeed * -1; //Makes it so that the negative inputs stay negative after being squared
        }
        driveTrainState.setDesiredSpeed(desiredSpeed);

        double desiredRotation = driveControlsState.getLeftRight();
        driveTrainState.setDesiredRotation(desiredRotation);
    }
    
}
