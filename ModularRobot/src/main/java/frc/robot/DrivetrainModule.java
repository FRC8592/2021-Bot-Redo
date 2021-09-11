package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
/**
 * @author Alex Shcherbina
 * Drivetrain module
 */
public class DrivetrainModule implements RobotModule{
    private TalonFX leftFront;
    private TalonFX rightFront;
    private TalonFX leftBack;
    private TalonFX rightBack;
    private double leftPower;
    private double rightPower;
    private SpeedControllerGroup leftDrive; 
	private SpeedControllerGroup rightDrive;
    private DifferentialDrive robotDrive; 

    public DrivetrainModule(){
        this.leftDrive = = new SpeedControllerGroup(leftFront, leftBack);
        this.rightDrive; = new SpeedControllerGroup(rightFront, rightBack);
        this.robotDrive = new DifferentialDrive(rightDrive, leftDrive);
    }
    public void updatePowerRightMotors(){
        rightFront.set(ControlMode.PercentOutput, rightPower);
        rightBack.set(ControlMode.PercentOutput, rightPower);
    }

    public void updatePowerLeftMotors(){
        leftFront.set(ControlMode.PercentOutput, leftPower);
        leftBack.set(ControlMode.PercentOutput, leftPower);
    }

    public void setRightMotorPower(double motorPower){
        this.rightPower = motorPower;
    }
    public void setLeftMotorPower(double motorPower){
        this.leftPower = motorPower;
    }
    
      /*** This is the entry point int module use this module to read data from sensors or make calculations, or output commands 
     * to robot hardware if you do this write only on the update should be exposed for a module. all other methods shoud be private
     * with the exception of the getState method below. update will be called once per module by teleop Periodic
     */
    public void update(){
        //updatePowerLeftMotors();
        //updatePowerRightMotors();
        updateDifferentialDrive();
    }
    private void updateDifferentialDrive(){
            robotDrive.arcadeDrive(rightPower, leftPower);
    }

    /** Coach Dillan Recomends state Obects or hashmaps are usefull and Coach Sam agrees With him states and hashmaps are useful this will be the function
    called to get this map from your object by the telemetry module you need to implement this for every Module you create*/
    public Map<String, Double> getState(){
        HashMap<String, Double> driveTrainPowerMap = new HashMap<String, Double>();
        driveTrainPowerMap.put("Drivetrain rightPower",leftPower);
        driveTrainPowerMap.put("Drivetrain leftPower",rightPower);
        return driveTrainPowerMap;
    } 
}
