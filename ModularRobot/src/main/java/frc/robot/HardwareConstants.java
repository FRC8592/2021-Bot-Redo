package frc.robot;

import java.util.HashMap;
import java.util.Map;


/** the Systems Module holds all constants needed globally for the program still 
 * technically a module just so a state can be written out to smart dashboard */
public class  HardwareConstants implements RobotModule{

    //UnitConversionConstants
    public static final double IN_TO_M = 0.0254;  
    public static final double FT_TO_M = 0.3048;
    public static final double DEG_TO_RAD = 0.0174533;
    public static class JOYSTICK {
    // JoyStick Constants
    public static final int DRIVE_STICK = 0;
    public static final int TURRET_STICK = 1;
    public static final int COLLECTOR_INTAKE = 1;
    public static final int COLLECTOR_UP = 2;
    public static final int COLLECTOR_DOWN = 3;
    public static final int COLLECTOR_UNJAM = 4;
    public static final int TURN_AXIS = 2;
    public static final int FORWARD_AXIS = 0;
    public static final int FLYWHEEL_SPEED_CONTROL = 3;
    public static final int TURRET_LAUNCH = 6;
    public static final int STAGE_2_COLLECTION = 5;
    }
    //Drive Train
    public static final int LEFT_MOTOR_INTS = 0;
    public static class CAN {
     // Drivetrain CAN bus IDs
     public static final int LEFT_FRONT_WHEEL = 1;
     public static final int LEFT_BACK_WHEEL   = 3;
     public static final int RIGHT_FRONT_WHEEL = 4;
     public static final int RIGHT_BACK_WHEEL  = 2;
 
     // Collector CAN bus IDs
     public static final int INTAKE_SPIN = 16;     // Spins collector wheels on intake
     public static final int PNUEMATIC_COMPRESSOR = 20;
     public static final int INTAKE_SOLINOID_PORT_A = 2;
     public static final int INTAKE_SOLINIOD_PORT_B  = 3;
 
     // Turret CAN bus IDs
     public static final int TURRET_LAUNCH = 7;      // High speed shooter flywheel
     public static final int TURRET_ROTATE = 12;     // Control rotation of turret
     public static final int BALL_PROCESS  = 13;     // Controls belt driving balls towards turret
     public static final int BALL_TRIGGER  = 11;     // Feed balls to flywheel (5k RPM max!  Be careful!)
    }
    /* hardware Module is acting a header or reference everything is public in this file I know this is not normal but all we are doing is assigning constants and pinouts
        everything is also public static as this will save time later on this module should only hold hardware constants. 
        start list here and PLEASE USE MEANINGFULL NAMES!!!!! if you don't this is unusable these are sometimes written in all caps*/

        @Override
         public void update(){

        }
        //** important info like ports or states to this*/
        @Override
         public Map<String, Double> getState(){
            HashMap<String, Double> map = new HashMap<String, Double>();
            return map;
        }
}
