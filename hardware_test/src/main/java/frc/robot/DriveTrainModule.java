package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Represents the motors used to drive the robot. We read the state of the module at the
 * beginning of each robot cycle. Then we update it using a controller. Then we write
 * the new, desired state back to the module. We also send the updated state as telemetry.
 */
public class DriveTrainModule {
	// Joystick controllers
	private robotControls myControllers;

	// Motor controllers
  	WPI_TalonFX leftFront;
  	WPI_TalonFX leftBack;
  	WPI_TalonFX rightFront;
	WPI_TalonFX rightBack;
	  
	// Motor groups
	SpeedControllerGroup leftDrive;
	SpeedControllerGroup rightDrive;
	  
	// Differential drive class
  	DifferentialDrive robotDrive;

	//
	// Initialize the drive control system
	//
	public DriveTrainModule(robotControls myControllers) {
		this.myControllers = myControllers;

		// Create motor objects
		leftFront = new WPI_TalonFX(config_hw.leftFrontCAN);
  		leftBack  = new WPI_TalonFX(config_hw.leftBackCAN);
  		rightFront= new WPI_TalonFX(config_hw.rightFrontCAN);
		rightBack = new WPI_TalonFX(config_hw.rightBackCAN);

		// Configure motor ramp time to smooth out acceleration
		leftFront.configOpenloopRamp(config.RAMP_TIME);
		leftBack.configOpenloopRamp(config.RAMP_TIME);
		rightFront.configOpenloopRamp(config.RAMP_TIME);
	  	rightBack.configOpenloopRamp(config.RAMP_TIME);
	
		// Pair up motors into control groups
		leftDrive  = new SpeedControllerGroup(leftFront, leftBack);
		rightDrive = new SpeedControllerGroup(rightFront, rightBack);
		  
		// Initialize drive system
  		robotDrive = new DifferentialDrive(rightDrive, leftDrive);
	}

	//
	// Update controls to the drive system
	//
	public void DriveTrainModulePeriodic() {
		// Read joystick values
		double forwardRev = myControllers.getAxis(robotControls.forwardRevAxis) * config.DRIVE_POWER;
		double turn       = myControllers.getAxis(robotControls.turnAxis) * config.TURN_POWER;

		// Send controls to the robot drive system
		robotDrive.arcadeDrive(forwardRev, turn, true);		// The final boolean value determines if expo is enabled
	}
	
}
