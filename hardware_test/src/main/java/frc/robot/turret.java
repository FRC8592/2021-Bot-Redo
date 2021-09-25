///////////////////////////////////////////////////////////////////////////////////////////////////////
// Manage the robot turret
//////////////////////////////////////////////////////////////////////////////////////////////////////

package frc.robot;
// Smart Dashboard classes
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.lang.model.util.ElementScanner6;

// Motor control classes
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Encoder;

import java.lang.Math;


public class turret {
    // Joystick controllers
    private robotControls myControllers;

    // Turret control motors
    TalonFX  turretLaunch; // Controls the motor that spins the flywheel to launch the ball
    TalonSRX ballProcess;  // Moves ball from intake area to ready to fire area
    TalonSRX ballTrigger;  // brings ball into contact of flywheel for firing
    TalonSRX turretRotate; // rotates turret assembly

    // Discrete motor encoders
    Encoder  turretRotateEncoder; // Encoder for turretRotate motor


    //
    // Initialize turret
    //
    public turret(robotControls myControllers) {
        this.myControllers = myControllers;

        // Create turret control motors
        turretLaunch = new TalonFX(config_hw.turretLaunchCAN);
        turretRotate = new TalonSRX(config_hw.turretRotateCAN);
        ballProcess  = new TalonSRX(config_hw.ballProcessCAN);
        ballTrigger  = new TalonSRX(config_hw.ballTriggerCAN);

        // Force motors to 0 RPM
        turretLaunch.set(ControlMode.PercentOutput, 0);
        turretRotate.set(ControlMode.PercentOutput, 0);
        ballProcess.set(ControlMode.PercentOutput, 0);
        ballTrigger.set(ControlMode.PercentOutput, 0);

        // Additional configuration for the constant-speed flywheel motor 
        turretLaunch.configFactoryDefault();            // Start with a known baseline
        //turretLaunch.enableVoltageCompensation(true);   // Turn on voltage compensation
        //turretLaunch.configVoltageCompSaturation(config.MAX_FLYWHEEL_VOLTS);
        turretLaunch.setInverted(true);                 // As installed, motor runs backward to shoot
        turretLaunch.setSensorPhase(true);
        turretLaunch.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, config.FLYWHEEL_PID_INDEX, 0);
        turretLaunch.config_kP(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_P);    // PID and FF constants
        turretLaunch.config_kI(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_I);
        turretLaunch.config_kD(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_D);
        turretLaunch.config_kF(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_F);
        turretLaunch.configClosedloopRamp(config.SHOOT_RAMP_TIME);

        turretLaunch.set(ControlMode.Velocity, 0);  // Constant velocity mode at 0 rpm

        // Set turret controls on Smart Dashboard
        SmartDashboard.putNumber("Flywheel RPM", config.BALL_FLYWHEEL_RPM);
    }


    //
    // Turret controls
    //
    public void turretPeriodic() {
        double flywheelSpeedSet  = 0;
        double flywheelSpeedLast = 0;
        double realFlywheelSpeed = 0.0;
        double turretRotation    = 0.0;
        boolean enableShooter    = false;

        //
        // Control the launch flywheel via smartDashboard
        //
        // Only update the turret speed settings when the SmartDashboard value actually changes
        //
        flywheelSpeedSet = SmartDashboard.getNumber("Flywheel RPM", config.BALL_FLYWHEEL_RPM);

        if (flywheelSpeedSet != flywheelSpeedLast) {
            turretLaunch.set(ControlMode.Velocity, falconUtil.rpmToFalcon(flywheelSpeedSet));
            flywheelSpeedLast = flywheelSpeedSet;
        }

        // Read actual speed from the flywheel and display it
        realFlywheelSpeed = falconUtil.falconToRpm(turretLaunch.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Real Speed", realFlywheelSpeed);

        // Disable the ball feed mechanism if the flywheel speed is incorrect
        if (Math.abs(realFlywheelSpeed - flywheelSpeedSet) <= config.MAX_RPM_DEVIATION) {
            enableShooter = true;
        }
        else {
            enableShooter = false;
        }
        
        // Engage the ball feed mechanism.  Unjam always has priority
        if (myControllers.getButton(robotControls.turretUnjamButton)) {                        // If unjam pressed, reverse trigger and process motors
            ballProcess.set(ControlMode.PercentOutput, -config.BALL_PROCESS_POWER);
            ballTrigger.set(ControlMode.PercentOutput, -config.BALL_TRIGGER_POWER);
        } else if (myControllers.getButton(robotControls.launchButton) & enableShooter) {  // elsif launch button pressed, drive both motors forward
            ballProcess.set(ControlMode.PercentOutput, config.BALL_PROCESS_POWER);
            ballTrigger.set(ControlMode.PercentOutput, config.BALL_TRIGGER_POWER);
        }
        else {
            ballProcess.set(ControlMode.PercentOutput, 0);                                   // else stop both motors
            ballTrigger.set(ControlMode.PercentOutput, 0);    
        }

        // Rotate the turret
        turretRotation = myControllers.getAxis(robotControls.turretTurnAxis) * config.TURRET_SCALE;
        turretRotate.set(ControlMode.PercentOutput, turretRotation);
    }

}
