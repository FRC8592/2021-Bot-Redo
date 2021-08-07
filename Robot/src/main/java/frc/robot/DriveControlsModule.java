package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Represents the the controls used to drive the robot. 
 * 
 * The hardware could be a joystick, or half of the buttons on a game controller,
 * or anything else. We can subclass this if we want to support multiple
 * kinds of hardware for experimentation.
 * 
 * We read the state of the module at the beginning of each robot cycle. Then we 
 * update it using a controller. Then we write the new, desired state back to the 
 * module. We also send the updated state as telemetry.
 */
public class DriveControlsModule {

	// We are chosing to use a joystick for the drive controls, for now.
	private Joystick joystick = new Joystick(0);

	/**
	 * Read the state of the hardware and return a DriveControlsState containing that information.
	 * 
	 * This should be called once per robot cycle, otherwise the control logic will have to evaluate
	 * from the hardware that could change multiple times during the cycle and the algorithm will be
	 * harder to write.
	 */
	public DriveControlsState readState() {
		DriveControlsState state = new DriveControlsState();

		state.setForwardBackward(joystick.getRawAxis(0));

		return state;
	}

}
