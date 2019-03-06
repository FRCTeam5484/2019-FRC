package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.controllers.DriverXboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.Climb_SubSystem.Position;

public class OI {
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
    driverTwo.yButton.whenPressed(new c_lift_MoveToPosition(Robot.lift.BallCargo));
    driverTwo.xButton.whenPressed(new c_lift_MoveToPosition(Robot.lift.BallHuman));
    driverTwo.bButton.whenPressed(new c_lift_MoveToPosition(Robot.lift.BallMidHigh));
    driverTwo.aButton.whenPressed(new c_lift_MoveToPosition(Robot.lift.BallLow));
    driverTwo.Dpad.Up.whenPressed(new c_lift_MoveToPosition(Robot.lift.PanelHigh));
    driverTwo.Dpad.Right.whenPressed(new c_lift_MoveToPosition(Robot.lift.PanelMid));
    driverTwo.Dpad.Down.whenPressed(new c_lift_MoveToPosition(Robot.lift.Ground));

    //------ End Game ------\\
    endGame.xButton.whenPressed(new c_climb_Stop(Position.Both));
    endGame.bButton.whileHeld(new c_climb_Retract(Position.Both));
    endGame.aButton.whileHeld(new c_climb_Extend(Position.Both));
    endGame.selectButton.whileHeld(new c_climb_Extend(Position.Back));
    endGame.startButton.whileHeld(new c_climb_Extend(Position.Front));
    endGame.leftBumper.whileHeld(new c_climb_Retract(Position.Back));
    endGame.rightBumper.whileHeld(new c_climb_Retract(Position.Front));
  }

  /*
  public double getDriverOneStickValue(int axis) {
    if(Math.abs(driverOne.getRawAxis(axis)) < 0.05) { 
      return 0; 
    }
    else { 
      return driverOne.getRawAxis(axis); 
    }
  }

  public double getDriverTwoStickValue(int axis) {
    if(Math.abs(driverTwo.getRawAxis(axis)) < 0.05) { 
      return 0; 
    }
    else { 
      return driverTwo.getRawAxis(axis);
    }
  }

  public double getStickValue (Joystick joystick, int axis) {
    return joystick.getRawAxis(axis);
  }

  public double getStickValue (Joystick joystick, int axis, double deadZone) {
    if(Math.abs(joystick.getRawAxis(axis)) < deadZone) { 
      return 0;
    }
    else { 
      return joystick.getRawAxis(axis); 
    }
  }
  */
}
