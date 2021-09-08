import edu.wpi.first.wpilibj.Joystick;
import frc.robot.HardwareIds;
/**
 * @author Alex Shcherbina
 * Joystick input module
 */
public class JoystickModule implements RobotModule{
    private Joystick driveStick;   
    private Joystick turretStick;

    private boolean collectorIntake;
    private boolean collectorUp;
    private boolean collectorDown;
    private boolean collectorUnjam;

    private double forwardAxis;
    private double turnAxis;

    private boolean turretLaunch;
    private boolean stageTwoCollection;
    private double turretSpeedControl;

    public JoystickModule(){
        this.driveStick = new Joystick(HardwareConstants.DRIVE_STICK);
        this.turretStick = new Joystick(HardwareConstants.TURRET_STICK);
       
    }

     public void updateBasicMovement(){
        this.forwardAxis = driveStick.getRawAxis(HardwareConstants.FORWARD_AXIS);
        this.turnAxis = driveStick.getRawAxis(HardwareConstants.TURN_AXIS);
       
     }

     public void updateCollectorControls(){
        this.collectorIntake = driveStick.getRawButton(HardwareConstants.COLLECTOR_INTAKE);
        this.collectorUp = driveStick.getRawButton(HardwareConstants.COLLECTOR_UP);
        this.collectorDown = driveStick.getRawButton(HardwareConstants.COLLECTOR_DOWN);
        this.collectorUnjam = driveStick.getRawButton(HardwareConstants.COLLECTOR_UNJAM);
     }

     public void updateTurretControls(){
        this.turretLaunch = turretStick.getRawButton(HardwareConstants.TURRET_LAUNCH);
        this.turretSpeedControl = turretStick.getRawAxis(HardwareConstants.FLYWHEEL_SPEED_CONTROL);
        this.stageTwoCollection = turretStick.getRawButton(HardwareConstants.STAGE_2_COLLECTION);
     }
    

     /*** This is the entry point int module use this module to read data from sensors or make calculations, or output commands 
     * to robot hardware if you do this write only on the update should be exposed for a module. all other methods shoud be private
     * with the exception of the getState method below. update will be called once per module by teleop Periodic
     */
    public void update(){
      if(this.driveStick != null && this.driveStick != null){
         updateBasicMovement();
         updateCollectorControls();
         updateTurretControls();
      }
    }
    public boolean getCollectorIntake(){
         return this.collectorIntake;
    }

    /** Coach Dillan Recomends state Obects or hashmaps are usefull and Coach Sam agrees With him states and hashmaps are useful this will be the function
    called to get this map from your object by the telemetry module you need to implement this for every Module you create*/
    public Map<String, Double> getState(){
         Map joystickMap = new HashMap()
    }
}
