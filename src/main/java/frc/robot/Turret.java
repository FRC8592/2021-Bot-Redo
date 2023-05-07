package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.config_hw;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Turret{

  //Create the motor objects
  private WPI_TalonSRX ballProcess;
  private WPI_TalonSRX ballTrigger;
  private WPI_TalonFX turretLaunch;
  private WPI_TalonFX turretRotate;

  public Turret(){
    //Define & configure the belt motor
    ballProcess = new WPI_TalonSRX(config_hw.ballProcessCAN);
    ballProcess.configFactoryDefault();
    
    //Define & configure the ball trigger motor (feeds balls to the flywheel)
    ballTrigger = new WPI_TalonSRX(config_hw.ballTriggerCAN);
    ballTrigger.configFactoryDefault();
    
    //Define & configure the turret launch (flywheel) motor
    turretLaunch = new WPI_TalonFX(config_hw.turretLaunchCAN);
    turretLaunch.configFactoryDefault();
    turretLaunch.setInverted(true);
    
    //Define & configure the turret rotation motor
    turretRotate = new WPI_TalonFX(config_hw.turretRotateCAN);
    turretRotate.configFactoryDefault();
    turretRotate.setNeutralMode(NeutralMode.Brake);
    turretRotate.setSelectedSensorPosition(0);
    
  }
  
  /*
  * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  *
  * UP: Motor definitions and config
  * DOWN: Turret rotation functions
  *
  * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  */

  //Our old P-Controller turnTo function
  public void turnTo(double targetDegrees){ 
    double encoderValue=getRotMotorPos();
    double targetTicks=(degreesToTicks(targetDegrees)); //Convert the degrees input to ticks
    double speed = (targetTicks-encoderValue)/200; //How far the motor must turn to reach its destination, divided by 200
    if(speed>1){    //Limit
      speed = 1.0;  //the
    }               //motor
    if(speed<(-1)){ //speed
      speed = -1.0; //to
    }               //1.0
    speedRotate(speed);
  }

  //Control the turret with the bumpers. Note to devs: change this to use speedRotate when speedRotate is tested.
  public void buttonTurretControl(boolean leftButton, boolean rightButton){
    double encoderValue=getRotMotorPos();
    if(Math.abs(encoderValue)<Constants.turretRotationLimit){
      if((rightButton&&leftButton)||(!rightButton&&!leftButton)){ //If both buttons or neither button is pressed
        turretRotate.set(ControlMode.PercentOutput, 0);
      }
      else if(rightButton){
        turretRotate.set(ControlMode.PercentOutput, 0.5);
      }
      else{ //The only other case is ONLY the leftbutton being pressed
        turretRotate.set(ControlMode.PercentOutput, -0.5);
      }
    }
    else if(encoderValue>Constants.turretRotationLimit&&leftButton){ //If the motor turned too far right and the user told it to move back
      turretRotate.set(ControlMode.PercentOutput, -0.5);
    }
    else if(encoderValue<-Constants.turretRotationLimit&&rightButton){ //If the motor turned too far left and the user told it to move back
      turretRotate.set(ControlMode.PercentOutput, 0.5);
    }
    else{
      turretRotate.set(ControlMode.PercentOutput, 0);
    }
  }

  //Rotate based on a speed input
  public void speedRotate(double speed){
    double encoderValue=getRotMotorPos();
    if((Math.abs(encoderValue)<Constants.turretRotationLimit)||       //IF our encoder reads that we're within our limit OR
       (encoderValue>Constants.turretRotationLimit&&speed<0)||        //We're greater than our limit but the user wants to move backward OR
       (encoderValue<-Constants.turretRotationLimit&&speed>0)){       //We're lower than the negative of our limit but the user wants to move forward, THEN:
      turretRotate.set(ControlMode.PercentOutput, deadBand(speed));   //Allow the turret to rotate as the user asks
    }
    else{                                                             //OTHERWISE (the user wants to do something dangerous):
      stopRotation();                                                 //Lock the motor
    }
  }

  //This is mostly just a method for Robot.java to be able to stop the rotation motor
  public void stopRotation(){
    turretRotate.set(ControlMode.PercentOutput, 0);
  }

  /*
  * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  *
  * UP: Turret rotation control functions
  * DOWN: Shooting (belt, process, flywheel) functions
  *
  * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  */

  public void moveBall(double ballProcessSpeed, double ballTriggerSpeed){
    ballProcess.set(ControlMode.PercentOutput, ballProcessSpeed);
    ballTrigger.set(ControlMode.PercentOutput, ballTriggerSpeed);
  }
  public void stopBall(){
    ballProcess.set(ControlMode.PercentOutput, 0);
    ballTrigger.set(ControlMode.PercentOutput, 0);
  }
  public void runFlywheel(double speed){
    turretLaunch.set(ControlMode.PercentOutput, speed);
  }
  public void stopFlywheel(){
    turretLaunch.set(ControlMode.PercentOutput, 0);
  }


  /*
  * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  *
  * UP: Shooting control functions
  * DOWN: Accessor methods
  *
  * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  */

  public double getRotMotorPos(){
    return turretRotate.getSelectedSensorPosition();
  }

  /*
  * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  *
  * UP: Accessor methods
  * DOWN: Custom math functions
  *
  * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  */

  //Convert from degrees to ticks; used in turnTo, where we input degrees
  public double degreesToTicks(double degrees){
    return degrees*Constants.ticksPerDegree;
  }

  //Deadband method for the motors
  public double deadBand(double value){
    if(Math.abs(value) <= Constants.deadBandValue){
      return 0;
    }
    else{
      return value;
    }
  }


  
  public void setFlywheel(double speed){
    turretLaunch.set(ControlMode.PercentOutput, deadBand(speed));
  }
}