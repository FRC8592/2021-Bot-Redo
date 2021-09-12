package frc.robot;


import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.Joystick;
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
        this.driveStick = new Joystick(HardwareConstants.JOYSTICK.DRIVE_STICK);
        this.turretStick = new Joystick(HardwareConstants.JOYSTICK.TURRET_STICK);
       
    }

     public void updateBasicMovement(){
        this.forwardAxis = driveStick.getRawAxis(HardwareConstants.JOYSTICK.FORWARD_AXIS);
        this.turnAxis = driveStick.getRawAxis(HardwareConstants.JOYSTICK.TURN_AXIS);
       
     }

     public void updateCollectorControls(){
        this.collectorIntake = driveStick.getRawButton(HardwareConstants.JOYSTICK.COLLECTOR_INTAKE);
        this.collectorUp = driveStick.getRawButton(HardwareConstants.JOYSTICK.COLLECTOR_UP);
        this.collectorDown = driveStick.getRawButton(HardwareConstants.JOYSTICK.COLLECTOR_DOWN);
        this.collectorUnjam = driveStick.getRawButton(HardwareConstants.JOYSTICK.COLLECTOR_UNJAM);
     }

     public void updateTurretControls(){
        this.turretLaunch = turretStick.getRawButton(HardwareConstants.JOYSTICK.TURRET_LAUNCH);
        this.turretSpeedControl = turretStick.getRawAxis(HardwareConstants.JOYSTICK.FLYWHEEL_SPEED_CONTROL);
        this.stageTwoCollection = turretStick.getRawButton(HardwareConstants.JOYSTICK.STAGE_2_COLLECTION);
     }
    

     /*** This is the entry point int module use this module to read data from sensors or make calculations, or output commands 
     * to robot hardware if you do this write only on the update should be exposed for a module. all other methods shoud be private
     * with the exception of the getState method below. update will be called once per module by teleop Periodic
     */
    public void update(){
      if(this.driveStick != null && this.turretStick != null){
         updateBasicMovement();
         updateCollectorControls();
         updateTurretControls();
      }
    }

    public boolean getCollectorIntake(){
         return this.collectorIntake;
    }

    public boolean getCollectorUp(){
         return this.collectorUp;  
    }

    public boolean getCollectorDown(){
         return this.collectorDown;
    }

    public boolean getCollectorUnjam(){
      return this.collectorUnjam;
    }

    public double getForwardAxis(){
      return this.forwardAxis;
    }

    public double getTurnAxis(){
      return this.turnAxis;
    }

    public boolean getTurretLaunch(){
       return this.turretLaunch;
    }

    public boolean getStageTwoCollection(){
      return this.stageTwoCollection;
    }

    public double getTurretSpeedControl(){
      return this.turretSpeedControl;
    }

    /** Coach Dillan Recomends state Obects or hashmaps are usefull and Coach Sam agrees With him states and hashmaps are useful this will be the function
    called to get this map from your object by the telemetry module you need to implement this for every Module you create*/
    public Map<String, Double> getState(){
         HashMap<String, Double> joystickMap = new HashMap<String, Double>();
         joystickMap.put("Joystick driveStick", HardwareConstants.JOYSTICK.DRIVE_STICK + 0.0);
         joystickMap.put("Joystick turretStick", HardwareConstants.JOYSTICK.TURRET_STICK + 0.0);
         joystickMap.put("Joystick ForwardAxis", this.forwardAxis);
         joystickMap.put("Joystick TurnAxis", this.turnAxis);
         joystickMap.put("JoyStick collectorIntake", this.collectorIntake? 1.0:0.0);
         joystickMap.put("JoyStick collectorDown", this.collectorDown? 1.0:0.0);
         joystickMap.put("JoyStick collectorUnjam", this.collectorUnjam? 1.0:0.0);
         joystickMap.put("JoyStick turretLaunch", this.turretLaunch? 1.0:0.0);
         joystickMap.put("JoyStick stageTwoCollection", this.stageTwoCollection? 1.0:0.0);
         joystickMap.put("JoyStick turretSpeedControl", this.turretSpeedControl);
       return joystickMap;
  }
}
