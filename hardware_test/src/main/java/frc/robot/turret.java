///////////////////////////////////////////////////////////////////////////////////////////////////////
// Manage the robot turret
//////////////////////////////////////////////////////////////////////////////////////////////////////

package frc.robot;
// Smart Dashboard classes
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Motor control classes
import com.ctre.phoenix.motorcontrol.ControlMode;
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

        // Force all motors to 0 RPM
        turretLaunch.set(ControlMode.PercentOutput, 0);
        turretLaunch.setInverted(true);
        turretRotate.set(ControlMode.PercentOutput, 0);
        ballProcess.set(ControlMode.PercentOutput, 0);
        ballTrigger.set(ControlMode.PercentOutput, 0);
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
        
        SmartDashboard.putNumber("Flywheel", flywheelSpeed);
        turretLaunch.set(ControlMode.PercentOutput, flywheelSpeed * ballFlywheelPower);
    
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
            ballTrigger.set(ControlMode.PercentOutput, 0);    
        }

        // Rotate the turret
        turretRotation = myControllers.getAxis(robotControls.turretTurnAxis) * config.TURRET_SCALE;
        turretRotate.set(ControlMode.PercentOutput, turretRotation);
    }

}
