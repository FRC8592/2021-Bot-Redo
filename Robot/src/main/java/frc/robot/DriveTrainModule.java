package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Represents the motors used to drive the robot. We read the state of the module at the
 * beginning of each robot cycle. Then we update it using a controller. Then we write
 * the new, desired state back to the module. We also send the updated state as telemetry.
 */
public class DriveTrainModule {

	// TODO: replace these with the correct CAN bus numbers.
  WPI_TalonFX leftFront= new WPI_TalonFX(0); 
  WPI_TalonFX leftBack = new WPI_TalonFX(1);
  WPI_TalonFX rightFront= new WPI_TalonFX(2); 
	WPI_TalonFX rightBack = new WPI_TalonFX(3);

  SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftFront, leftBack);
  SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightFront, rightBack);
  DifferentialDrive robotDrive = new DifferentialDrive(rightDrive, leftDrive);

	/**
	 * Read the current state from the hardware, like the current speed. This information
	 * will be used by the controller.
	 */
	public DriveTrainState readState() {
		return new DriveTrainState();
	}

	/**
	 * Write the new, desired state values. This will update things like PercentOutput on the motors.
	 * The vendor APIs for the hardware will not let us update some values, like current speed.
	 */
	public void writeState(DriveTrainState driveTrainState) {
		double desiredSpeed = driveTrainState.getDesiredSpeed();
		double desiredRotation = driveTrainState.getDesiredRotation();
    robotDrive.arcadeDrive(desiredSpeed, desiredRotation);
	}
    
}
