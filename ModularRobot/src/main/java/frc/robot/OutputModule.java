package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class OutputModule implements RobotModule{

    //Private Variables

    //initialize all hardware in constructor;
    public OutputModule(){

    }
    //still including this incase you want to make changes to hardware like motor speed in the update frame
    @Override
    public void update(){

    }
    //Report values of each private Variable set by end of frame.
    public Map<String, Double> getState(){
        HashMap<String, Double> map = new HashMap<String, Double>();
            return map;
    }

    // Public Setter Methods go here you could also update Hardware elements in setter methods its up to you but be constistant. 

}