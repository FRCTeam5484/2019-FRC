package frc.robot;

import frc.robot.helpers.DriverXboxController;
import frc.robot.commands.*;

public class OI {
  double Ground = 0.98;
  double PanelMid = 0.64;
  double PanelHigh = 0.45;
  double BallHuman = 0.93;
  double BallLow = 0.71;
	double BallMidHigh = 0.44;
  double BallCargo = 0.58;


  // Driver One
  public DriverXboxController driverOne = new DriverXboxController(0);
  public DriverXboxController driverTwo = new DriverXboxController(1);
  public DriverXboxController endGame = new DriverXboxController(2);

  public OI() {
    //------ Driver One ------\\
    driverOne.yButton.whenPressed(new c_navX_Reset());
    driverOne.bButton.whenPressed(new c_bottomCargo_ToggleArms());
    driverOne.aButton.whileHeld(new c_driveTrain_DriveToTarget());
    driverOne.rightBumper.whileHeld(new c_hatchPanel_RetractArm());
    driverOne.leftBumper.whenPressed(new c_hatchPanel_ToggleGrip());
    driverOne.startButton.toggleWhenPressed(new c_hatchPanel_HoldArmDown());

    //------ Driver Two ------\\
    driverTwo.rightBumper.whileHeld(new c_topCargo_GrabBall());
    driverTwo.leftBumper.whileHeld(new c_topCargo_EjectBall());
    driverTwo.yButton.whenPressed(new c_lift_MoveToPosition(BallCargo));
    driverTwo.xButton.whenPressed(new c_lift_MoveToPosition(BallHuman));
    driverTwo.bButton.whenPressed(new c_lift_MoveToPosition(BallMidHigh));
    driverTwo.aButton.whenPressed(new c_lift_MoveToPosition(BallLow));
    driverTwo.Dpad.Up.whenPressed(new c_lift_MoveToPosition(PanelHigh));
    driverTwo.Dpad.Right.whenPressed(new c_lift_MoveToPosition(PanelMid));
    driverTwo.Dpad.Down.whenPressed(new c_lift_MoveToPosition(Ground));

    //------ End Game ------\\
    endGame.xButton.whenPressed(new c_climb_Stop("FB"));
    endGame.bButton.whileHeld(new c_climb_Retract("FB"));
    endGame.aButton.whileHeld(new c_climb_Extend("FB"));
    endGame.selectButton.whileHeld(new c_climb_Extend("B"));
    endGame.startButton.whileHeld(new c_climb_Extend("F"));
    endGame.leftBumper.whileHeld(new c_climb_Retract("B"));
    endGame.rightBumper.whileHeld(new c_climb_Retract("F"));
  }
}
