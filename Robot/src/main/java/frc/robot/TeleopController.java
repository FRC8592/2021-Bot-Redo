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
    }
    
}
