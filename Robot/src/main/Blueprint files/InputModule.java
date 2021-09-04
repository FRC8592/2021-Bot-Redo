
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.DriveTrainModule;

public class InputModule implements RobotModule{

    private JoystickModule joystickModule;
    private DriveTrainSensorModule driveSensorModule;

    public InputModule(){
        joystickModule = new JoystickModule();
        driveSensorModule = new DriveTrainSensorModule();

    }

    public void update(){
        joystickModule.update();
        driveSensorModule.update();
    }

    public Map writeState(){
        DriveTrainModule.writeState(joystickModule.axis[0]);
    }
    
    public JoystickModule getJoystickModule(){
        return this.joystickModule;
    }

    public DriveTrainSensorModule getDriveTrainSensorModule(){
        return this.driveSensorModule;
    }
}
