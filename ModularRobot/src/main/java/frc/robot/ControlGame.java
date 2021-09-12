package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import java.util.HashMap;
import java.util.Map;


public class ControlGame extends JoystickModule {

    // this file is for getting the robot to work with a game controller
    // joystick axis 1 is our forward back stick (also down is positive up is
    // negative)
    // joystick axis 4 is our left right stick
    // the left and right triggers are axis's 2 and 3 respectively (goes from 0 to 1
    // as you press it down)
    // module writen by Zolton (probably)

    /*
     * todo list: make forward back work with the joystick make left right work with
     * the joystick make left right work with the left and right triggers idk this
     * is a short list stab Zolton
     */

    private Joystick driveStick;

    private double forwardAxis;
    private double turnAxis;

    private double triggerL;
    private double triggerR;
    private double turningForce;

    public ControlGame(){
        this.driveStick = new Joystick(HardwareConstants.JOYSTICK.DRIVE_STICK);
       
    }

    public void updateBasicMovement() {
        this.forwardAxis = driveStick.getRawAxis(HardwareConstants.JOYSTICK.FORWARD_AXIS_G); //had to add new harwareconstants
        this.turnAxis=driveStick.getRawAxis(HardwareConstants.JOYSTICK.TURN_AXIS_G);
        this.triggerL=driveStick.getRawAxis(HardwareConstants.JOYSTICK.LEFT_TRIGGER_PULL);
        this.triggerR=driveStick.getRawAxis(HardwareConstants.JOYSTICK.RIGHT_TRIGGER_PULL);
        getTurningForce();
        // I cant find where this is being written to the robot or called by any class in general -Zolton
    }


    public double getTurningForce() {
        this.turningForce = (-1.0*triggerL) + triggerR + turnAxis;
        if(turningForce > 1.0){
            turningForce = 1.0;
        }
        return turningForce;
    }

    public void update(){
        updateBasicMovement();
    }

    public Map<String, Double> getState(){
        HashMap<String, Double> joystickMap = new HashMap<String, Double>();
        joystickMap.put("Joystick driveStick", HardwareConstants.JOYSTICK.DRIVE_STICK + 0.0);
        joystickMap.put("Joystick turretStick", HardwareConstants.JOYSTICK.TURRET_STICK + 0.0);
        joystickMap.put("Joystick ForwardAxis", this.forwardAxis);
        joystickMap.put("Joystick TurnAxis", this.turnAxis);
        joystickMap.put("Joystick LeftTriggerPull", this.triggerL);
        joystickMap.put("Joystick RightTriggerPull", this.triggerR);
      return joystickMap;
 }

    public double getTriggerR() {
        return triggerR;
    }

    public double getTriggerL() {
        return triggerL;
    }

    public double getTurnAxis() {
        return turnAxis;
    }

    public double getForwardAxis() {
        return forwardAxis;
    }

}