// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;  
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Robot extends TimedRobot {

  private DriveControlsModule driveControlsModule = new DriveControlsModule();
  private DriveTrainModule driveTrainModule = new DriveTrainModule();
  private Telemetry telemetry = new Telemetry();
  
  WPI_TalonFX leftFront= new WPI_TalonFX(0); 
  WPI_TalonFX leftBack = new WPI_TalonFX(0);
  SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftFront, leftBack);
  WPI_TalonFX rightFront = new WPI_TalonFX(0);
  WPI_TalonFX rightBack = new WPI_TalonFX(0);
  SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightFront, rightBack);
  DifferentialDrive robotDrive = new DifferentialDrive(rightDrive, leftDrive);
  private final Joystick joystick = new Joystick(0);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
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

  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

      // Read the initial state of each relevant module, i.e. get data from the sensors.
      DriveControlsState driveControlsState = this.driveControlsModule.readState();
      DriveTrainState driveTrainState = this.driveTrainModule.readState();

      // Use the logic in the controller to determine what the new state should be for each module.
      TeleopController controller = new TeleopController(driveControlsState, driveTrainState);
      controller.control();

      // Write the new state of each module, i.e. actually send signals to the hardware.
      driveTrainModule.writeState(driveTrainState);

      // Send telemetry.
      this.telemetry.send(driveControlsState, driveTrainState);

      rightBack.setInverted(true);
      rightFront.setInverted(true);
      robotDrive.arcadeDrive(joystick.getRawAxis(0), joystick.getRawAxis(2));
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
