///////////////////////////////////////////////////////////////////////////////////////////////////////
// Manage the robot ball collector and associated pneumatics
//////////////////////////////////////////////////////////////////////////////////////////////////////

package frc.robot;

// Pneumatic control classes
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

// Motor control classes
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class collector {
    // Joystick controllers
    private robotControls myControllers;

    // Pneumatics
    private Compressor     robotCompressor;
    private DoubleSolenoid collectorSolenoid;

    // Intake spin motor
    private TalonSRX intakeSpin;

    // Collector position
    private boolean collectorInboard = true;


    //
    // Initialize collector
    //
    public collector(robotControls myControllers) {
        this.myControllers = myControllers;

        // Start Pneumatics
        robotCompressor    = new Compressor(config_hw.compressorCAN);
        collectorSolenoid  = new DoubleSolenoid(config_hw.compressorCAN,
                                                config_hw.intakeSolPortA, config_hw.intakeSolPortB);

        // *** DANGER : Closed loop control must be enabled to prevent overpressure ***
        robotCompressor.setClosedLoopControl(true);             // Cycle to control pressure
        robotCompressor.start();                                // Start compressor running
        collectorSolenoid.set(DoubleSolenoid.Value.kReverse);   // Move collector inboard
        collectorInboard = true;

        // Create motors
        intakeSpin = new TalonSRX(config_hw.intakeSpinCAN);
        intakeSpin.set(ControlMode.PercentOutput, 0);           // Ensure motor is stopped
    }


    //
    // Collector Controls
    //
    public void collectorPeriodic() {
        //
        // Move the collector inboard or outboard
        //
        if (myControllers.getButton(robotControls.collectDownButton)) {
            collectorSolenoid.set(DoubleSolenoid.Value.kForward);   // Move outboard
            collectorInboard = false;
        }
        else if (myControllers.getButton(robotControls.collectUpButton)) {
            collectorSolenoid.set(DoubleSolenoid.Value.kReverse);   // Move inboard
            collectorInboard = true;
        }
 
        //
        // The collector is automatically activated any time the collector is in the outboard
        // position.  The unjam button has priority in all situations
        //
        if (myControllers.getButton(robotControls.driverUnjamButton)) {
            intakeSpin.set(ControlMode.PercentOutput, -1);      // Reverse motor to unjam
        } else if (!collectorInboard) {
            intakeSpin.set(ControlMode.PercentOutput, 1);       // Run collector when outboard
        } else {
            intakeSpin.set(ControlMode.PercentOutput, 0);
        }

    }

}
