package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class OutputModule implements RobotModule{


    //public variables
    public DrivetrainModule driveTrainModule;

    //Private Variables
    

    //initialize all hardware in constructor;
    public OutputModule(){
        this.driveTrainModule = new DrivetrainModule();
    }
    //still including this incase you want to make changes to hardware like motor speed in the update frame
    @Override
    public void update(){
        driveTrainModule.update();
    }
    //Report values of each private Variable set by end of frame.
    public Map<String, Double> getState(){
        HashMap<String, Double> map = new HashMap<String, Double>();
        map.putAll(driveTrainModule.getState());
            return map;
    }

    // Public Setter Methods go here you could also update Hardware elements in setter methods its up to you but be constistant. 

}