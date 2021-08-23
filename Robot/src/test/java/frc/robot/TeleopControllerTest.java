package frc.robot;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TeleopControllerTest {

  @Test
  public void testSetsMaxSpeed() {

    // Given a TeleopController
    RobotState robotState = new RobotState();
    TeleopController teleopController = new TeleopController();

    // Given that the driver is using the drive controls to move the robot forward at maximum speed
    robotState.getControlsState().setForwardBackward(1.0);

    // When I use the TeleopController to control the robot
    teleopController.control(robotState);

    // Then it should set the desired speed to maximum
    assertThat(robotState.getDriveTrainState().getDesiredSpeed(), equalTo(1.0));
  }

  @Test
  public void testSetsQuarterSpeed() {

    // Given a TeleopController
    RobotState robotState = new RobotState();
    TeleopController teleopController = new TeleopController();

    // Given that the driver has moved the forward/backward control half way forward
    robotState.getControlsState().setForwardBackward(0.5);

    // When I use the TeleopController to control the robot
    teleopController.control(robotState);

    // Then it should set the desired speed to 0.25
    assertThat(robotState.getDriveTrainState().getDesiredSpeed(), equalTo(0.25));
  }

  @Test
  public void testNegativeForwardBackwardInput() {

    // Given a TeleopController
    RobotState robotState = new RobotState();
    TeleopController teleopController = new TeleopController();

    // Given that the driver has moved the forward/backward control all the way in the negative position
    robotState.getControlsState().setForwardBackward(-1);

    // When I use the TeleopController to control the robot
    teleopController.control(robotState);

    // Then it should set the desired speed to -1
    assertThat(robotState.getDriveTrainState().getDesiredSpeed(), equalTo(-1.0));
  }
}
