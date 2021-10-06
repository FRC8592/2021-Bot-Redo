///////////////////////////////////////////////////////////////////////////////////////////////////////
// Manage the robot turret
//////////////////////////////////////////////////////////////////////////////////////////////////////

package frc.robot;
// Smart Dashboard classes
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Motor control classes
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import edu.wpi.first.wpilibj.Encoder;

// Limelight and network tables
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

// Math.  Like it's not important or anything.
import java.lang.Math;


public class turret {
    // Joystick controllers
    private robotControls myControllers;

    // Turret control motors
    WPI_TalonFX  turretLaunch; // Controls the motor that spins the flywheel to launch the ball
    WPI_TalonSRX ballProcess;  // Moves ball from intake area to ready to fire area
    WPI_TalonSRX ballTrigger;  // brings ball into contact of flywheel for firing
    WPI_TalonSRX turretRotate; // rotates turret assembly

    // Discrete motor encoders
    Encoder  turretRotateEncoder; // Encoder for turretRotate motor

    // Network table entries for the Limelight
    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry tv;

    //
    // Initialize turret
    //
    public turret(robotControls myControllers) {
        this.myControllers = myControllers;

        // Create turret control motors
        turretLaunch = new WPI_TalonFX(config_hw.turretLaunchCAN);      // High speed flywheel
        turretRotate = new WPI_TalonSRX(config_hw.turretRotateCAN);     // Turret rotation
        ballProcess  = new WPI_TalonSRX(config_hw.ballProcessCAN);      // Diagonal belt in ball storage area
        ballTrigger  = new WPI_TalonSRX(config_hw.ballTriggerCAN);      // Wheel under turret that loads balls into the flywheel shooter

        // Force motors to 0 RPM (probably unnecessary)WPI_
        turretLaunch.set(ControlMode.PercentOutput, 0);
        turretRotate.set(ControlMode.PercentOutput, 0);
        ballProcess.set(ControlMode.PercentOutput, 0);
        ballTrigger.set(ControlMode.PercentOutput, 0);

        // Additional configuration for the constant-speed flywheel motor 
        turretLaunch.configFactoryDefault();            // Start with a known configuration baseline
        turretLaunch.enableVoltageCompensation(true);   // Turn on voltage compensation
        turretLaunch.configVoltageCompSaturation(config.MAX_FLYWHEEL_VOLTS); // Set H-bridge voltage
        turretLaunch.setInverted(true);                 // As installed, motor runs backward to shoot
        turretLaunch.setSensorPhase(true);              // I think this is the default.  Possibly unnecessary
        turretLaunch.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, config.FLYWHEEL_PID_INDEX, 0);
        turretLaunch.config_kP(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_P);    // Flywheel PID control: Proportional
        turretLaunch.config_kI(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_I);    // Flywheel PID control: Integral
        turretLaunch.config_kD(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_D);    // Flywheel PID control: Derivative
        turretLaunch.config_kF(config.SHOOT_SLOT_INDEX, config.SHOOT_PID_F);    // Flywheel feedforward for closed loop
        turretLaunch.configClosedloopRamp(config.SHOOT_RAMP_TIME);              // Time to ramp from 0 to full power

        turretLaunch.set(ControlMode.Velocity, 0);      // Constant velocity mode at 0 rpm

        // Put flywheel RPM onto smart dashboard
        SmartDashboard.putNumber("Flywheel RPM", config.BALL_FLYWHEEL_RPM);

        // Configure network table interface for the limelight camera
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tv = table.getEntry("tv");
    }


    //
    // Turret controls
    //
    public void turretPeriodic() {
        double flywheelSpeedSet  = 0;
        double flywheelSpeedLast = 0;
        double realFlywheelSpeed = 0.0;
        //
        boolean targetValid;
        double  targetXAngle;
        double  targetYAngle;
        double  targetArea;
        double  targetRange;
        boolean enableShooter = false;
        boolean targetLocked  = false;
    
        //
        // Control the launch flywheel via smartDashboard
        //
        // Only update the turret speed settings when the SmartDashboard value actually changes
        //
        flywheelSpeedSet = SmartDashboard.getNumber("Flywheel RPM", config.BALL_FLYWHEEL_RPM);

        if (flywheelSpeedSet != flywheelSpeedLast) {
            // turretLaunch.set(ControlMode.Velocity, falconUtil.rpmToFalcon(flywheelSpeedSet));
            turretLaunch.set(ControlMode.Velocity, falconUtil.rpmToFalcon(0));
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
        // turretRotation = myControllers.getAxis(robotControls.turretTurnAxis) * config.TURRET_SCALE;
        // turretRotate.set(ControlMode.PercentOutput, turretRotation);

        //
        // Read the network tables values from the limelight camera
        //
        targetXAngle = tx.getDouble(0.0);
        targetYAngle = ty.getDouble(0.0);
        targetArea   = ta.getDouble(0.0);
        targetValid = (tv.getDouble(0.0) != 0); // Convert the float output to boolean

        //
        // Calculate range to the target.  We must convert degrees to radians
        //
        targetRange = config.HEIGHT_DELTA / Math.tan((config.CAMERA_ANGLE + targetYAngle) * Math.PI / 180);

        //
        // Post limelight values to Smart Dashboard.  This is mostly to assist debugging, but the boolean values
        //
        SmartDashboard.putNumber("LimelightX",    targetXAngle);
        SmartDashboard.putNumber("LimelightY",    targetYAngle);
        SmartDashboard.putNumber("LimelightArea", targetArea);
        SmartDashboard.putNumber("Target Range",  targetRange); 
        SmartDashboard.putBoolean("TargetValid",  targetValid);
        SmartDashboard.putBoolean("ShooterReady", enableShooter);



        //
        // Calculate turret power using a proportional constant (Kp) and the x angle error from the camera
        //
        double turretPower = Math.min(1, Math.abs(targetXAngle * config.TURRET_KP));

        //
        // If the turret is not centered within TURRET_AIM_TOLERANCE degrees, move the motor based on whether the
        // target is to the left or right of center
        //
        if (targetValid) {
            if (targetXAngle < -config.TURRET_AIM_TOLERANCE) {
                targetLocked = false;
                turretRotate.set(ControlMode.PercentOutput, -turretPower);
            }   
            else if (targetXAngle > config.TURRET_AIM_TOLERANCE) {
                targetLocked = false;
                turretRotate.set(ControlMode.PercentOutput, turretPower);
            }
            else {
                targetLocked = true;
                turretRotate.set(ControlMode.PercentOutput, 0);
            }
        }
        else {
            targetLocked = false;
            turretRotate.set(ControlMode.PercentOutput, 0);
        }

        SmartDashboard.putBoolean("TargetLocked", targetLocked);
    }
}
