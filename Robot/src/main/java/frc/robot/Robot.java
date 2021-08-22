// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  private DriveControlsModule driveControlsModule = new DriveControlsModule();
  private DriveTrainModule driveTrainModule = new DriveTrainModule();
  private CollectorModule collectorModule = new CollectorModule();
  private TurretModule turretModule = new TurretModule();
  private Telemetry telemetry = new Telemetry();
  private TeleopController teleopController = new TeleopController();


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    driveTrainModule.init();
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
      RobotState robotState = new RobotState();
      robotState.setCollectorModuleState(collectorModule.readState());
      robotState.setDriveControlsState(driveControlsModule.readState());
      robotState.setDriveTrainState(driveTrainModule.readState());
      robotState.setTurretModuleState(turretModule.readState());

      // Use the logic in the controller to determine what the new state should be for each module.
      this.teleopController.control(robotState);

      // Write the new state of each module, i.e. actually send signals to the hardware.
      this.collectorModule.writeState(robotState.getCollectorModuleState());
      this.driveControlsModule.writeState(robotState.getDriveControlsState());
      this.driveTrainModule.writeState(robotState.getDriveTrainState());
      this.turretModule.writeState(robotState.getTurretModuleState());

      // Send telemetry.
      this.telemetry.send(robotState.getDriveControlsState(), robotState.getDriveTrainState());
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
