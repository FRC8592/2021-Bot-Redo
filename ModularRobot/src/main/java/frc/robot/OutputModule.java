
public class OutputModule implements RobotModule{

    //Private Variables

    //initialize all hardware in constructor;
    public OutputModule(){

    }
    //still including this incase you want to make changes to hardware like motor speed in the update frame
    void Update(){

    }
    //Report values of each private Variable set by end of frame.
    public Map<String, Double> getState(){
        Map map = new HashMap<String, Double>();
            return map;
    }

    // Public Setter Methods go here you could also update Hardware elements in setter methods its up to you but be constistant. 

}