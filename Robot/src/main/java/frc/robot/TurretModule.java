package frc.robot;

// Motor control classes
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Manages the turret of the robot
 */
public class TurretModule {

    // Turret control motors
    TalonFX turretLaunch; // Controls the motor that spins the flywheel to launch the ball
    TalonSRX ballProcess; // Moves ball from intake area to ready to fire area
    TalonSRX ballTrigger; // brings ball into contact of flywheel for firing
    TalonSRX turretRotate; // rotates turret assembly

    // Discrete motor encoders
    Encoder turretRotateEncoder; // Encoder for turretRotate motor

    /**
     * Initialize turret
     */
    public TurretModule() {

        // Create turret control motors
        turretLaunch = new TalonFX(HardwareIds.turretLaunchCAN);
        turretRotate = new TalonSRX(HardwareIds.turretRotateCAN);
        ballProcess = new TalonSRX(HardwareIds.ballProcessCAN);
        ballTrigger = new TalonSRX(HardwareIds.ballTriggerCAN);

        // Force all motors to 0 RPM
        turretLaunch.set(ControlMode.PercentOutput, 0);
        turretLaunch.setInverted(true);
        turretRotate.set(ControlMode.PercentOutput, 0);
        ballProcess.set(ControlMode.PercentOutput, 0);
        ballTrigger.set(ControlMode.PercentOutput, 0);
    }

    public void readState(TurretState state) {
        // Do nothing. This is a write-only module.
    }

    public void writeState(TurretState state) {

        // Control the launch flywheel.
        //
        // I used to transform the joystick throttle such that the range of (-1, 1) was
        // mapped to (0,1).
        // This made the bottom position of the throttle = 0 and allow us to use the
        // whole range of
        // motion to make adjustments. However, this meant that when the joystick wasn't
        // plugged in,
        // the axis would default to a value of 0, which would be mapped to 0.5, causing
        // the flywheel
        // to spin. This is annoying and possibly dangerous, so I now just use the (0,
        // 1) portion of
        // the throttle control and map any negative numbers to zero
        //

        double flywheelSpeed = state.getTurretFlywheelSpeed();
        if (flywheelSpeed < 0) {
            flywheelSpeed = 0;
        }

        turretLaunch.set(ControlMode.PercentOutput, flywheelSpeed);

        // Engage the ball feed mechanism. Unjam always has priority
        if (state.isTurretUnjamming()) {
            ballProcess.set(ControlMode.PercentOutput, -Config.ballProcessPower);
            ballTrigger.set(ControlMode.PercentOutput, -Config.ballTriggerPower);
        } else if (state.isTurretLaunching()) {
            ballProcess.set(ControlMode.PercentOutput, Config.ballProcessPower);
            ballTrigger.set(ControlMode.PercentOutput, Config.ballTriggerPower);
        } else {
            ballProcess.set(ControlMode.PercentOutput, 0);
            ballTrigger.set(ControlMode.PercentOutput, 0);
        }

        // Rotate the turret
        turretRotate.set(ControlMode.PercentOutput, state.getTurretRotation());
    }

}
