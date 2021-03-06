package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Represents the the controls used to drive the robot.
 * 
 * The hardware could be a joystick, or half of the buttons on a game
 * controller, or anything else. We can subclass this if we want to support
 * multiple kinds of hardware for experimentation.
 * 
 * We read the state of the module at the beginning of each robot cycle. Then we
 * update it using a controller. Then we write the new, desired state back to
 * the module. We also send the updated state as telemetry.
 */
public class ControlsModule {

    // Define joystick names. Each name must have an associated USB ID.
    private static final int DRIVE_STICK = 0; // Define USB IDs for joysticks
    private static final int TURRET_STICK = 1; // USB IDs must start with 0
    private static final int[] JOYSTICKS = { DRIVE_STICK, TURRET_STICK };

    //
    // Define the control scheme below. Each named control is defined by a
    // two-element array containing the joystick ID and the button or axis ID
    //

    // Drivetrain control
    private static final int[] FORWARD_REV_AXIS = { DRIVE_STICK, 1 };
    private static final int[] TURN_AXIS = { DRIVE_STICK, 2 };

    // Collector control
    private static final int[] COLLECT_DOWN_BUTTON = { DRIVE_STICK, 1 };
    private static final int[] COLLECT_UP_BUTTON = { DRIVE_STICK, 2 };
    private static final int[] COLLECT_UNJAM_BUTTON = { DRIVE_STICK, 3 };

    // Turret control
    private static final int[] FLYWHEEL_START_BUTTON = { TURRET_STICK, 1 };
    private static final int[] FLYWHEEL_STOP_BUTTON = { TURRET_STICK, 2 };
    private static final int[] FLYWHEEL_SPEED_AXIS = { TURRET_STICK, 3 }; // Launch speed is adjustable
    private static final int[] LAUNCH_BUTTON = { TURRET_STICK, 3 };
    private static final int[] TURRET_UNJAM_BUTTON = { TURRET_STICK, 4 };
    private static final int[] TURRET_AXIS = { TURRET_STICK, 2 };

    // Array to hold joystick objects
    private Joystick[] joysticks = new Joystick[JOYSTICKS.length];

    public ControlsModule() {
        // Create joystick objects from the definitions above
        for (int i = 0; i < JOYSTICKS.length; i++) {
            joysticks[JOYSTICKS[i]] = new Joystick(JOYSTICKS[i]);
        }
    }

    /**
     * Check whether a button is pressed - true means it is pressed
     */
    private boolean getButton(int[] buttonConstant) {
        return joysticks[buttonConstant[0]].getRawButton(buttonConstant[1]);
    }

    /**
     * Get value for a joystick axis
     */
    private double getAxis(int[] axisConstant) {
        return joysticks[axisConstant[0]].getRawAxis(axisConstant[1]);
    }

    /**
     * Read the state of the hardware and return a DriveControlsState containing
     * that information.
     * 
     * This should be called once per robot cycle, otherwise the control logic will
     * have to evaluate from the hardware that could change multiple times during
     * the cycle and the algorithm will be harder to write.
     */
    public ControlsState readState(ControlsState state) {

        // Drive train controls
        state.setAxisForwardReverse(getAxis(FORWARD_REV_AXIS));
        state.setAxisLeftRight(getAxis(TURN_AXIS));

        // Collector controls
        state.setButtonCollectorDown(getButton(COLLECT_DOWN_BUTTON));
        state.setButtonCollectorUp(getButton(COLLECT_UP_BUTTON));
        state.setButtonCollectorUnjam(getButton(COLLECT_UNJAM_BUTTON));

        // Turret controls
        state.setButtonTurretFlywheelStart(getButton(FLYWHEEL_START_BUTTON));
        state.setButtonTurretFlywheelStop(getButton(FLYWHEEL_STOP_BUTTON));
        state.setAxisTurretFlywheelSpeed(getAxis(FLYWHEEL_SPEED_AXIS));
        state.setButtonTurretLaunch(getButton(LAUNCH_BUTTON));
        state.setButtonTurretUnjam(getButton(TURRET_UNJAM_BUTTON));
        state.setAxisTurretRotation(getAxis(TURRET_AXIS));

        return state;
    }

    public void writeState(ControlsState driveControlsState) {
    }

}
