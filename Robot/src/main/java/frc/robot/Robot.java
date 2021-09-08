// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  private ControlsModule driveControlsModule = new ControlsModule();
  private DriveTrainModule driveTrainModule = new DriveTrainModule();
  private CollectorModule collectorModule = new CollectorModule();
  private TurretModule turretModule = new TurretModule();
  private Telemetry telemetry = new Telemetry();
  private TeleopController teleopController = new TeleopController();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    driveTrainModule.init();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    // Read the initial state of each relevant module, i.e. get data from the
    // sensors.
    RobotState robotState = new RobotState();
    readModuleStates(robotState);

    // Use the logic in the controller to determine what the new state should be for
    // each module.
    this.teleopController.control(robotState);

    // Write the new state of each module, i.e. actually send signals to the
    // hardware.
    writeModuleStates(robotState);

    // Send telemetry.
    this.telemetry.send(robotState.getTelemetrySources());
  }

  private void readModuleStates(RobotState robotState) {
    collectorModule.readState(robotState.getCollectorState());
    driveControlsModule.readState(robotState.getControlsState());
<<<<<<< HEAD
    //driveTrainModule.readState(robotState.getDriveTrainState());
=======
    driveTrainModule.readState(robotState.getDriveTrainState());
>>>>>>> e0defb3cc44f10f8b8db6996d043f0b35015441b
    turretModule.readState(robotState.getTurretState());
  }

  private void writeModuleStates(RobotState robotState) {
    this.collectorModule.writeState(robotState.getCollectorState());
    this.driveControlsModule.writeState(robotState.getControlsState());
    this.driveTrainModule.writeState(robotState.getDriveTrainState());
    this.turretModule.writeState(robotState.getTurretState());
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
