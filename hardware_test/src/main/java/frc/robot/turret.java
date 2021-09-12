///////////////////////////////////////////////////////////////////////////////////////////////////////
// Manage the robot turret
//////////////////////////////////////////////////////////////////////////////////////////////////////

package frc.robot;
// Smart Dashboard classes
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Motor control classes
import com.ctre.phoenix.motorcontrol.ControlMode;
<<<<<<< HEAD
=======
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
>>>>>>> c257334faf76a783080ba850e81649d116bc6a83
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Encoder;


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

<<<<<<< HEAD
        // Force all motors to 0 RPM
        turretLaunch.set(ControlMode.PercentOutput, 0);
        turretLaunch.setInverted(true);
        turretRotate.set(ControlMode.PercentOutput, 0);
        ballProcess.set(ControlMode.PercentOutput, 0);
        ballTrigger.set(ControlMode.PercentOutput, 0);
=======
        // Force motors to 0 RPM
        turretLaunch.set(ControlMode.PercentOutput, 0);
        turretRotate.set(ControlMode.PercentOutput, 0);
        ballProcess.set(ControlMode.PercentOutput, 0);
        ballTrigger.set(ControlMode.PercentOutput, 0);

        // Additional configuration for the constant-speed flywheel motor 
        turretLaunch.configFactoryDefault();    // Start with a known baseline
        turretLaunch.setInverted(true);         // As installed, motor runs backward to shoot
        turretLaunch.setSensorPhase(true);
        turretLaunch.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, config.FLYWHEEL_PID_INDEX, 0);
        turretLaunch.config_kP(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_P);    // PID and FF constants
        turretLaunch.config_kI(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_I);
        turretLaunch.config_kD(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_D);
        turretLaunch.config_kF(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_F);
        turretLaunch.configClosedloopRamp(config.SHOOT_RAMP_TIME);

        turretLaunch.set(ControlMode.Velocity, 0);  // Constant velocity mode at 0 rpm
>>>>>>> c257334faf76a783080ba850e81649d116bc6a83
    }


    //
    // Turret controls
    //
    public void turretPeriodic() {
        double flywheelSpeed;
        double turretRotation;

        //
        // Control the launch flywheel.
        //
        // I used to transform the joystick throttle such that the range of (-1, 1) was mapped to (0,1).
        // This made the bottom position of the throttle = 0 and allow us to use the whole range of
        // motion to make adjustments.  However, this meant that when the joystick wasn't plugged in,
        // the axis would default to a value of 0, which would be mapped to 0.5, causing the flywheel
        // to spin.  This is annoying and possibly dangerous, so I now just use the (0, 1) portion of
        // the throttle control and map any negative numbers to zero
        //
        flywheelSpeed = myControllers.getAxis(robotControls.flywheelSpeedAxis);
        if (flywheelSpeed  < 0) {
            flywheelSpeed = 0;
        }
        
<<<<<<< HEAD
        SmartDashboard.putNumber("Flywheel", flywheelSpeed);
        turretLaunch.set(ControlMode.PercentOutput, flywheelSpeed);
    
        // Engage the ball feed mechanism.  Unjam always has priority
        if (myControllers.getButton(robotControls.turretUnjamButton)) {
            ballProcess.set(ControlMode.PercentOutput, -config.ballProcessPower);
            ballTrigger.set(ControlMode.PercentOutput, -config.ballTriggerPower);
        } else if (myControllers.getButton(robotControls.launchButton)) {
            ballProcess.set(ControlMode.PercentOutput, config.ballProcessPower);
            ballTrigger.set(ControlMode.PercentOutput, config.ballTriggerPower);
        }
        else {
            ballProcess.set(ControlMode.PercentOutput, 0);
=======
        //SmartDashboard.putNumber("Flywheel", flywheelSpeed);
        //turretLaunch.set(ControlMode.PercentOutput, flywheelSpeed * config.BALL_FLYWHEEL_POWER);

        turretLaunch.set(ControlMode.Velocity, falconUtil.rpmToFalcon(config.BALL_FLYWHEEL_RPM));  // Constant velocity mode at 0 rpm5
    
        // Engage the ball feed mechanism.  Unjam always has priority
        if (myControllers.getButton(robotControls.turretUnjamButton)) {             // If unjam pressed, reverse trigger and process motors
            ballProcess.set(ControlMode.PercentOutput, -config.BALL_PROCESS_POWER);
            ballTrigger.set(ControlMode.PercentOutput, -config.BALL_TRIGGER_POWER);
        } else if (myControllers.getButton(robotControls.launchButton)) {           // elsif launch button pressed, drive both motors forward
            ballProcess.set(ControlMode.PercentOutput, config.BALL_PROCESS_POWER);
            ballTrigger.set(ControlMode.PercentOutput, config.BALL_TRIGGER_POWER);
        }
        else {
            ballProcess.set(ControlMode.PercentOutput, 0);                          // else stop both motors
>>>>>>> c257334faf76a783080ba850e81649d116bc6a83
            ballTrigger.set(ControlMode.PercentOutput, 0);    
        }

        // Rotate the turret
        turretRotation = myControllers.getAxis(robotControls.turretTurnAxis) * config.TURRET_SCALE;
        turretRotate.set(ControlMode.PercentOutput, turretRotation);
    }

}
