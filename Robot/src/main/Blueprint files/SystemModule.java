import java.util.HashMap;

/** the Systems Module holds all constants needed globally for the program still 
 * technically a module just so a state can be written out to smart dashboard */
public class HardwareConstants implements Module{


        /* hardware Module is acting a header or reference everything is public in this file I know this is not normal but all we are doing is assigning constants and pinouts
        everything is also public static as this will save time later on this module should only hold hardware constants. 
        start list here and PLEASE USE MEANINGFULL NAMES!!!!! if you don't this is unusable*/
        public static final Double driveJoystickPort = 0;

        @Override int update(){
            return 1;
        }
        //** important info like ports or states to this*/
        @Override Map<String, Double> getState(){
            Map map = new HashMap<String, Double>();
            return map;
        }
}