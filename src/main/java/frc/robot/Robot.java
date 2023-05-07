// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.lang.Math;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.DriveTrain;
import frc.robot.Turret;
import frc.robot.Intake.IntakePosition;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private XboxController gamePad;
  private DriveTrain driveTrain;
  private Turret turret;
  private double currentFlyWheelPower;
  private boolean flyWheelEnabled;
  private Intake intake;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //Define joystick object
    gamePad = new XboxController(0);
    driveTrain = new DriveTrain();
    turret = new Turret();
    currentFlyWheelPower = 0.0;
    intake = new Intake();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    //Add the SmartDashboard numbers that may and/or will be used for inputting data 
    SmartDashboard.putNumber("Target Turret Angle", 0.0);
    SmartDashboard.putNumber("DriveTrain enabled?", 0);
    SmartDashboard.putNumber("Turret Rotation Mode (0=Bumpers, 1=Turn-To, 2=Joystick)", 0);
    flyWheelEnabled = false;

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() { 
    //Variables for input from the gamePad
    double leftStickY = gamePad.getLeftY();
    double leftStickX = gamePad.getLeftX();
    double rightStickY = gamePad.getRightY();
    double rightStickX = gamePad.getRightX();

    double rightTrigger = gamePad.getRightTriggerAxis();
    double leftTrigger = gamePad.getLeftTriggerAxis();

    boolean rightButton = gamePad.getRightBumper();
    boolean leftButton = gamePad.getLeftBumper();

    boolean XButton = gamePad.getXButton();
    boolean YButton = gamePad.getYButton();
    boolean AButton = gamePad.getAButton();
    boolean BButton = gamePad.getBButton();


    /*
    * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    *
    * UP: Variable definitions for gamePad input
    * DOWN: SmartDashboard-related code
    *
    * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    */

    //Push data to smartdashboard for the Left and Right joystick values, plus the turret rotation motor's encoder value

    SmartDashboard.putNumber("Left Joystick", leftStickY);
    SmartDashboard.putNumber("Right Joystick", rightStickY);
    SmartDashboard.putNumber("Encoder Value", turret.getRotMotorPos());

    //Get user data for the drive mode, turret turn mode, and target turret angle (for turn-to).
    double driveMode = SmartDashboard.getNumber("DriveTrain enabled?", 1.0);
    double turnMode = SmartDashboard.getNumber("Turret Rotation Mode (0=Bumpers, 1=Turn-To, 2=Joystick)", 0.0);
    double targetTurretAngle = SmartDashboard.getNumber("Target Turret Angle", 0.0);

    /*
    * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    *
    * UP: SmartDashboard-related code
    * DOWN: Robot control
    *
    * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    */

    //Operate the drive train
  
    driveTrain.oneJoystickDrive(leftStickX, leftStickY);
    
    

    //Operate the turret rotation motor
    if(turnMode==0){
      turret.buttonTurretControl(gamePad.getLeftTriggerAxis() >= 0.1, gamePad.getRightTriggerAxis() >= 0.1);
    }
    else if(turnMode==1){
      turret.turnTo(targetTurretAngle);
    }
    else if(turnMode==2&&driveMode!=1){
      turret.speedRotate(rightStickX);
    }
    else{
      turret.stopRotation();
    }

    //Operate the belt and loading motors
    if (rightButton){
      turret.moveBall(Constants.ballProcessSpeed, Constants.ballTriggerSpeed);
    }
    else if(XButton){
      turret.moveBall(-Constants.ballProcessSpeed, -Constants.ballTriggerSpeed);
    }
    else{
      turret.stopBall();
    }


    if (gamePad.getLeftBumperPressed()){
      flyWheelEnabled = !flyWheelEnabled;
    }


    //Operate the flywheel
    // if(leftTrigger>0.1){
    //   turret.runFlywheel(constants.turretLaunchSpeed);
    // }
    // else{
    //   turret.stopFlywheel();
    // }
    if (flyWheelEnabled){ 
      turret.setFlywheel(0.25);
    }else {
      turret.setFlywheel(0.0);
    }   
    
    if (gamePad.getYButtonPressed()){
      System.out.println("STOWED BUTTON");
      intake.setIntakePosition(IntakePosition.STOWED);
    }else if (gamePad.getAButtonPressed()){
      System.out.println("DEPLOYED BUTTON");
      intake.setIntakePosition(IntakePosition.DEPLOYED);
    }
    /*
    * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    *
    * UP: Robot control
    * DOWN: Other Robot functions from the default code
    *
    * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    */

   

    SmartDashboard.putBoolean("FLYWHEEL ENABLED", flyWheelEnabled);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}


