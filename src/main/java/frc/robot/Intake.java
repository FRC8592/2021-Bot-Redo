///////////////////////////////////////////////////////////////////////////////////////////////////////
// Manage the robot ball collector and associated pneumatics
//////////////////////////////////////////////////////////////////////////////////////////////////////

package frc.robot;

// Pneumatic control classes
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

// Motor control classes
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {

    // Pneumatics
    private Compressor robotCompressor;
    private DoubleSolenoid collectorSolenoid;

    // Intake spin motor
    private TalonSRX intakeSpin;

    // Collector position
    private boolean isDeployed = true; // is the collector in the outboard position?
    
    public enum IntakePosition {
        STOWED,
        DEPLOYED,
    }



    public IntakePosition currentPosition = IntakePosition.STOWED;
    public boolean isDeploying = false;
    public boolean isStowing = false;

    // Initialize collector
    public Intake() {
        
        // Start Pneumatics
        robotCompressor = new Compressor(PneumaticsModuleType.CTREPCM);
        
        collectorSolenoid = new DoubleSolenoid(Constants.compressorCAN, PneumaticsModuleType.CTREPCM, Constants.intakeSolPortA,
                Constants.intakeSolPortB);

        // *** DANGER : Closed loop control must be enabled to prevent overpressure ***
        robotCompressor.enableAnalog(100, 120);; // Cycle to control pressure
        // robotCompressor.start(); // Start compressor running
        collectorSolenoid.set(DoubleSolenoid.Value.kReverse); // Move collector inboard (undeploy it)
        this.isDeployed = false;

        // Create motors
        intakeSpin = new TalonSRX(Constants.intakeSpinCAN);
        intakeSpin.set(ControlMode.PercentOutput, 0); // Ensure motor is stopped
    }



    
    
    public void setIntakePosition(IntakePosition desiredIntakePosition) {

      
        switch (desiredIntakePosition) {
            case STOWED:
                this.isDeployed = false;
                break;
        
            case DEPLOYED:
                this.isDeployed = true;
                break;
        }


 
        // Otherwise don't change the state

        //
        // Move the collector inboard or outboard
        //
        if (this.isDeployed) {
            collectorSolenoid.set(DoubleSolenoid.Value.kForward); // Move outboard
            currentPosition = IntakePosition.DEPLOYED;

        } else if (!this.isDeployed) {
            collectorSolenoid.set(DoubleSolenoid.Value.kReverse); // Move inboard
            currentPosition = IntakePosition.STOWED;
        }
        // Otherwise don't move it

        // //
        // // The collector is automatically activated any time the collector is in the
        // // outboard position (deployed). The unjam button has priority in all situations
        // //
        // if (isUnjamming) {
        //     intakeSpin.set(ControlMode.PercentOutput, -1); // Reverse motor to unjam
        // } else if (this.isDeployed) {
        //     intakeSpin.set(ControlMode.PercentOutput, 1); // Run collector when outboard
        // } else {
        //     intakeSpin.set(ControlMode.PercentOutput, 0); // Stop the collector
        // }

    }


    /**
     * Read state into an existing variable to reduce object churn
     */
    public void readState(IntakeState state) {
        // Do nothing. This module is write-only.

    }

    public boolean isIntakeDeployed(){
        return currentPosition == IntakePosition.DEPLOYED;
    }

}