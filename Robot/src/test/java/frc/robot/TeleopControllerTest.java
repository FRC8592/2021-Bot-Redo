package frc.robot;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TeleopControllerTest {

  @Test
  public void testSetsMaxSpeed() {

    // Given a TeleopController
    DriveControlsState driveControlsState = new DriveControlsState();
    DriveTrainState driveTrainState = new DriveTrainState();
    TeleopController teleopController = new TeleopController(driveControlsState, driveTrainState);

    // Given that the driver is using the drive controls to move the robot forward at maximum speed
    driveControlsState.setForwardBackward(1.0);

    // When I use the TeleopController to control the robot
    teleopController.control();

    // Then it should set the desired speed to maximum
    assertThat(driveTrainState.getDesiredSpeed(), equalTo(1.0));
  }

  @Test
  public void testSetsQuarterSpeed() {

    // Given a TeleopController
    DriveControlsState driveControlsState = new DriveControlsState();
    DriveTrainState driveTrainState = new DriveTrainState();
    TeleopController teleopController = new TeleopController(driveControlsState, driveTrainState);

    // Given that the driver has moved the forward/backward control half way forward
    driveControlsState.setForwardBackward(0.5);

    // When I use the TeleopController to control the robot
    teleopController.control();

    // Then it should set the desired speed to 0.25
    assertThat(driveTrainState.getDesiredSpeed(), equalTo(0.25));
  }

  @Test
  public void testNegativeForwardBackwardInput() {

    // Given a TeleopController
    DriveControlsState driveControlsState = new DriveControlsState();
    DriveTrainState driveTrainState = new DriveTrainState();
    TeleopController teleopController = new TeleopController(driveControlsState, driveTrainState);

    // Given that the driver has moved the forward/backward control all the way in the negative position
    driveControlsState.setForwardBackward(-1);

    // When I use the TeleopController to control the robot
    teleopController.control();

    // Then it should set the desired speed to -1
    assertThat(driveTrainState.getDesiredSpeed(), equalTo(-1));
  }
}
