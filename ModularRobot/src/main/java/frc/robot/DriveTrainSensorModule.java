

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PascalCaseStrategy;
import java.util.ArrayList; // import the ArrayList class

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint.MinMax;
import com.kauailabs.navx.frc.AHRS;

public class DriveTrainSensorModule implements RobotModule {
        
        private double leftWheelSpeed;    
        private double rightWheelSpeed;
        private double wheelRadius;
        private double wheelBaseWidth;
        private TalonFX rightFrontWheel; // Front Right Motor
        private TalonFX rightBackWheel; // Back Right Motor
        private TalonFX leftBackWheel; // Back Left motor
        private TalonFX leftFrontWheel; // Front Left motor

        public DriveTrainSensorModule(){
                leftBackWheel = new TalonFX(HardwareConstants.leftBackWheel)
        }

        public Void update(){

        }       
        public Map<String, Double> getState(){

        } 
}
