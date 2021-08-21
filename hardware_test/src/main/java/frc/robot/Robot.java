// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Default imports
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Control and basic infrastructure
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

// CTRE imports for Talon motor controllers and Falcon 500 motors (TalonFX)
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Encoder;

// Navx
import com.kauailabs.navx.frc.AHRS;


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

  // Robot teleop controllers
  robotControls myControllers;

  // Collector control
  collector robotCollector;

  // Turret motors
  TalonFX  turretLaunch; // Controls the motor that spins the flywheel to launch the ball
  TalonSRX ballProcess;  // Moves ball from intake area to ready to fire area
  TalonSRX ballTrigger;  // brings ball into contact of flywheel for firing
  TalonSRX turretRotate; // rotates turret assembly

  // Discrete motor encoders
  Encoder  turretRotateEncoder; // Encoder for turretRotate motor


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    // Initialize robot controllers
    myControllers = new robotControls();

    // Initialize collector control
    robotCollector = new collector(myControllers);

    // Create turret control motors
    turretLaunch = new TalonFX(config_hw.turretLaunchCAN);
    turretRotate = new TalonSRX(config_hw.turretRotateCAN);
    ballProcess  = new TalonSRX(config_hw.ballProcessCAN);
    ballTrigger  = new TalonSRX(config_hw.ballTriggerCAN);

    // Force all motors to 0 RPM
    turretLaunch.set(ControlMode.PercentOutput, 0);
    turretLaunch.setInverted(true);
    turretRotate.set(ControlMode.PercentOutput, 0);
    ballProcess.set(ControlMode.PercentOutput, 0);
    ballTrigger.set(ControlMode.PercentOutput, 0);
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
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double flywheelSpeed;

    // Update collector control
    robotCollector.collectorPeriodic();

    //
    // Control the launch flywheel.
    //
    flywheelSpeed = myControllers.getAxis(robotControls.flywheelSpeedAxis);                       // Modify (-1, 1) range to (0, 1) range
    flywheelSpeed = (-flywheelSpeed + 1 ) / 2;      

    SmartDashboard.putNumber("Flywheel", flywheelSpeed);
    turretLaunch.set(ControlMode.PercentOutput, flywheelSpeed);

    // Engage the ball feed mechanism
    if (myControllers.getButton(robotControls.launchButton)) {
      ballProcess.set(ControlMode.PercentOutput, config.ballProcessPower);
      ballTrigger.set(ControlMode.PercentOutput, config.ballTriggerPower);
    }
    else if (myControllers.getButton(robotControls.turretUnjamButton)) {
      ballProcess.set(ControlMode.PercentOutput, -config.ballProcessPower);
      ballTrigger.set(ControlMode.PercentOutput, -config.ballTriggerPower);
    }
    else {
      ballProcess.set(ControlMode.PercentOutput, 0);
      ballTrigger.set(ControlMode.PercentOutput, 0);    
    }

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
