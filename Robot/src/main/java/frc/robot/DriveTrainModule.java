package frc.robot;

/**
 * Represents the motors used to drive the robot. We read the state of the module at the
 * beginning of each robot cycle. Then we update it using a controller. Then we write
 * the new, desired state back to the module. We also send the updated state as telemetry.
 */
public class DriveTrainModule {

	/**
	 * Read the current state from the hardware, like the current speed. This information
	 * will be used by the controller.
	 */
	public DriveTrainState readState() {
		return new DriveTrainState();
	}

	/**
	 * Write the new, desired state values. This will update things like PercentOutput on the motors.
	 * The vendor APIs for the hardware will not let us update some values, like current speed.
	 */
	public void writeState(DriveTrainState driveTrainState) {
		// TODO: set the target percent output of the Falcon motors
	}
    
}
