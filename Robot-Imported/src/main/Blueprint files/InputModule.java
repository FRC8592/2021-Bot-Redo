import java.util.HashMap;
import java.util.Map;

public class InputModule implements RobotModule {

     private JoystickModule joystickModule;

    /*** This is the entry point int module use this module to read data from sensors or make calculations, or output commands 
     * to robot hardware if you do this write only on the update should be exposed for a module. all other methods shoud be private
     * with the exception of the getState method below. update will be called once per module by teleop Periodic
     */
    public InputModule(){
        joystickModule = new JoystickModule();
    }
    public void update(){

    }

    /** Coach Dillan Recomends state Obects or hashmaps are usefull and Coach Sam agrees With him states and hashmaps are useful this will be the function
    called to get this map from your object by the telemetry module you need to implement this for every Module you create*/
    public Map<String, Double> getState(){
        Map<K,V> inputMap = new HashMap<String, Double>();
        inputMap.putAll(joystickModule.getState());

        return inputMap;
    }
}