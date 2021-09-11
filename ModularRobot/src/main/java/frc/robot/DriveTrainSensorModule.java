
package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PascalCaseStrategy;
import java.util.ArrayList; // import the ArrayList class
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint.MinMax;
//import com.kauailabs.navx.frc.AHRS;

public class DriveTrainSensorModule implements RobotModule {
        
        private double rightWheelsVelocity;
        private double leftWheelsVelcoity;
        private TalonFX rightFrontWheel; // Front Right Motor
        private TalonFX rightBackWheel; // Back Right Motor
        private TalonFX leftBackWheel; // Back Left motor
        private TalonFX leftFrontWheel; // Front Left motor
        private double velocity;
        private double angularVelocity;
        private double wheelRadius;
        private double wheelBaseWidth;




        public DriveTrainSensorModule(){
                this.leftBackWheel = new TalonFX(HardwareConstants.CAN.LEFT_BACK_WHEEL);
                this.rightBackWheel = new TalonFX(HardwareConstants.CAN.RIGHT_BACK_WHEEL);
                this.rightFrontWheel = new TalonFX(HardwareConstants.CAN.RIGHT_FRONT_WHEEL);
                this.leftFrontWheel = new TalonFX(HardwareConstants.CAN.LEFT_FRONT_WHEEL);
                this.velocity = 0;
                this.wheelRadius = HardwareConstants.WHEEL_RADIUS_IN * HardwareConstants.IN_TO_M;
                this.wheelBaseWidth = HardwareConstants.WHEEL_BASE_IN * HardwareConstants.IN_TO_M;
                this.rightWheelsVelocity = 0;
                this.leftWheelsVelcoity = 0;   
                this.angularVelocity = 0;  
        }

        public void update(){
               updateVelocity();
               updateAngularVelocity();
        }       
        public Map<String, Double> getState(){
                Map<String, Double> map = new HashMap<String, Double>();
                map.put("drivetrain left velocity (m/s)", this.leftWheelsVelcoity);
                map.put("drivetrain right velocity (m/s)", this.rightWheelsVelocity);
                map.put("drivetrain velocity (m/s)", this.velocity);
                map.put("drivetrain angular velocity (deg/s", this.angularVelocity * HardwareConstants.RAD_TO_DEG);
                return map;
        } 

        private void updateVelocity() {
                double lfv = leftFrontWheel.getSensorCollection().getIntegratedSensorVelocity();
                double lbv = leftBackWheel.getSensorCollection().getIntegratedSensorVelocity();
                double rbv =  -1 * rightBackWheel.getSensorCollection().getIntegratedSensorVelocity();
                double rfv = -1 * rightFrontWheel.getSensorCollection().getIntegratedSensorVelocity();
                this.rightWheelsVelocity = (((((rbv + rfv)/2.0) * ((2 * Math.PI) / 2048.0) * this.wheelRadius) / .1) / 10.75); // speed in meters 
                this.leftWheelsVelcoity = (((((lbv + lfv)/2.0) * ((2 * Math.PI) / 2048.0) * this.wheelRadius) / .1) / 10.75); // speed in meters 
                this.velocity = (rightWheelsVelocity + leftWheelsVelcoity)/2;                                                                                         
            }

        private void updateAngularVelocity(){
                this.angularVelocity = (rightWheelsVelocity - leftWheelsVelcoity)/(wheelBaseWidth/2);   
        }
}
