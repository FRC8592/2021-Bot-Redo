///////////////////////////////////////////////////////////////////////////////////////////////////////
// Robot control scheme
//
// Joystick 0 = Driver
// Joystick 1 = Turret
//
// Array format = [Joystick, Button / Axis]
//////////////////////////////////////////////////////////////////////////////////////////////////////

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class robotControls {
    //
    // Define joystick names.  Each name must have an associated USB ID.
    //
    private static final int cDriver = 0;                    // Define USB IDs for joysticks
    private static final int cTurret = 1;                    // USB IDs must start with 0
    private static final int[] robotJoysticks = {cDriver, cTurret};

    //
    // Define the control scheme below.  Each named control is defined by a
    // two-element array containing the joystick ID and the button or axis ID
    //

    // Drivetrain control
    public static final int[] forwardRevAxis  = {cDriver,1};
    public static final int[] turnAxis        = {cDriver,2};

    // Collector control
    public static final int[] collectDownButton  = {cDriver,1};
    public static final int[] collectUpButton    = {cDriver,2};
    public static final int[] driverUnjamButton  = {cDriver,3};

    // Turret control
    public static final int[] flywheelStartButton = {cTurret,1};
    public static final int[] flywheelStopButton  = {cTurret,1};
    public static final int[] flywheelSpeedAxis   = {cTurret,3};   // Launch speed is adjustable
    public static final int[] launchButton        = {cTurret,1};
    public static final int[] turretUnjamButton   = {cTurret,2};
    public static final int[] turretSpinButton    = {cTurret,3};
    public static final int[] turretTurnAxis      = {cTurret,2};

    // Array to hold joystick objects
    private Joystick[] controllers = new Joystick[robotJoysticks.length];


    //
    // Create joystick objects from the definitions above
    //
    public robotControls() {
        for (int i = 0; i < robotJoysticks.length; i++) {
            controllers[robotJoysticks[i]] = new Joystick(robotJoysticks[i]);
        }
    }


    //
    // Get status for a button
    //
    public boolean getButton(int[] thisButton) {
        return controllers[thisButton[0]].getRawButton(thisButton[1]);
    }


    //
    // Get value for a joystick axis
    //
    public double getAxis(int[] thisAxis) {
        return controllers[thisAxis[0]].getRawAxis(thisAxis[1]);
    }

}
