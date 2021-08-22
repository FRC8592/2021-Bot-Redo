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

public class CollectorModule {

    // Pneumatics
    private Compressor robotCompressor;
    private DoubleSolenoid collectorSolenoid;

    // Intake spin motor
    private TalonSRX intakeSpin;

    // Collector position
    private boolean isDeployed = true; // is the collector in the outboard position?

    // Initialize collector
    public CollectorModule() {

        // Start Pneumatics
        robotCompressor = new Compressor(HardwareIds.compressorCAN);
        collectorSolenoid = new DoubleSolenoid(HardwareIds.compressorCAN, HardwareIds.intakeSolPortA,
                HardwareIds.intakeSolPortB);

        // *** DANGER : Closed loop control must be enabled to prevent overpressure ***
        robotCompressor.setClosedLoopControl(true); // Cycle to control pressure
        robotCompressor.start(); // Start compressor running
        collectorSolenoid.set(DoubleSolenoid.Value.kReverse); // Move collector inboard (undeploy it)
        this.isDeployed = false;

        // Create motors
        intakeSpin = new TalonSRX(HardwareIds.intakeSpinCAN);
        intakeSpin.set(ControlMode.PercentOutput, 0); // Ensure motor is stopped
    }

    //
    // Collector Controls
    //
    public void writeState(CollectorState state) {

        boolean isDeploying = state.isDeploying();
        boolean isUndeploying = state.isUndeploying();
        boolean isUnjamming = state.isUnjamming();

        //
        // Update whether the module considers the collector "deployed"
        //
        if (isDeploying) {
            this.isDeployed = true;
        } else if (isUndeploying) {
            this.isDeployed = false;
        }
        // Otherwise don't change the state

        //
        // Move the collector inboard or outboard
        //
        if (isDeploying) {
            collectorSolenoid.set(DoubleSolenoid.Value.kForward); // Move outboard
        } else if (isUndeploying) {
            collectorSolenoid.set(DoubleSolenoid.Value.kReverse); // Move inboard
        }
        // Otherwise don't move it

        //
        // The collector is automatically activated any time the collector is in the
        // outboard position (deployed). The unjam button has priority in all situations
        //
        if (isUnjamming) {
            intakeSpin.set(ControlMode.PercentOutput, -1); // Reverse motor to unjam
        } else if (this.isDeployed) {
            intakeSpin.set(ControlMode.PercentOutput, 1); // Run collector when outboard
        } else {
            intakeSpin.set(ControlMode.PercentOutput, 0); // Stop the collector
        }

    }

    public CollectorState readState() {
        return null;
    }

}
