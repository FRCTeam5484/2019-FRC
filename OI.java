package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.subsystems.Climb_SubSystem.Position;
import frc.robot.commands.*;

public class OI {
  // Driver One
  public XboxController driverOne = new XboxController(0); // Driver One Controller
  public JoystickButton b_toggleArms = new JoystickButton(driverOne, 2); // B Button
  public JoystickButton b_retractArm = new JoystickButton(driverOne, 6); // Right Bumper
  public JoystickButton b_gripPanel = new JoystickButton(driverOne, 5); // Left Bumper
  public JoystickButton b_autoLineUp = new JoystickButton(driverOne, 1); // A Button
  public JoystickButton b_resetNavX = new JoystickButton(driverOne, 4); // Y Button
  public JoystickButton b_endGame = new JoystickButton(driverOne, 8); // Start Button
  
  // Driver Two
  public XboxController driverTwo = new XboxController(1); // Driver Two Controller
  // Top Cargo
  public JoystickButton b_topCargo_Grab = new JoystickButton(driverTwo, 6); // Right Bumper
  public JoystickButton b_topCargo_Eject = new JoystickButton(driverTwo, 5); // Left Bumper
  // Lift
  public JoystickButton b_liftHeightLow = new JoystickButton(driverTwo, 1); // A Button
  public JoystickButton b_liftHeightMid = new JoystickButton(driverTwo, 2); // B Button
  public JoystickButton b_liftHeightHigh = new JoystickButton(driverTwo, 4); // Y Button
  // Climb
  
  // public JoystickButton b_liftHeightLow_Modifier = new JoystickButton(driverTwo, 1); 
  // public JoystickButton b_liftHeightMid_Modifer = new JoystickButton(driverTwo, 2); 
  // public JoystickButton b_liftHeightHigh_Modifier = new JoystickButton(driverTwo, 4); 

  public XboxController actionBox = new XboxController(2);
  public JoystickButton b_extendClimb_Both = new JoystickButton(actionBox, 1); // A Button
  public JoystickButton b_extendClimb_Front = new JoystickButton(actionBox, 8); // Start Button
  public JoystickButton b_extendClimb_Back = new JoystickButton(actionBox, 7); // Back Button
  public JoystickButton b_retractClimb_Front = new JoystickButton(actionBox, 6); // Right Bumper
  public JoystickButton b_retractClimb_Back = new JoystickButton(actionBox, 5); // Left Bumper
  public JoystickButton b_retractClimb_Both = new JoystickButton(actionBox, 2); // B Button
  public JoystickButton b_stopClimb = new JoystickButton(actionBox, 3); // X Button

  public OI() {
    //------ Driver One ------\\
    b_toggleArms.whenPressed(new c_bottomCargo_ToggleArms());  // Intake Arms
    b_retractArm.whileHeld(new c_hatchPanel_RetractArm()); // Panel Arm
    b_gripPanel.whenPressed(new c_hatchPanel_ToggleGrip()); // Grip
    b_autoLineUp.whileHeld(new c_driveTrain_DriveToTarget());
    b_resetNavX.whenPressed(new c_navX_Reset());
    b_endGame.toggleWhenPressed(new c_hatchPanel_HoldArmDown());

    //------ Driver Two ------\\
    // Top Cargo
    b_topCargo_Grab.whileHeld(new c_topCargo_GrabBall()); // Grab
    b_topCargo_Eject.whileHeld(new c_topCargo_EjectBall()); // Eject
    // Lift
    b_liftHeightLow.whenPressed(new c_lift_MoveToPosition(Robot.lift.BallLow)); // Low
    b_liftHeightMid.whenPressed(new c_lift_MoveToPosition(Robot.lift.BallMidHigh)); // Mid
    b_liftHeightHigh.whenPressed(new c_lift_MoveToPosition(Robot.lift.BallCargo)); // High

    //------ Climb Controller ------\\
    b_extendClimb_Both.whileHeld(new cExtendBoth()); // Both
    b_retractClimb_Both.whileHeld(new cRetractBoth());
    b_extendClimb_Front.whileHeld(new cfrontExtend()); // Front
    b_extendClimb_Back.whileHeld(new cbackExtend()); // Back
    b_retractClimb_Front.whileHeld(new cfrontRetract()); // Right Bumper
    b_retractClimb_Back.whileHeld(new cbackRetract()); // Left Bumper
    //b_stopClimb.whenPressed(new c_climb_Stop(Position.Both)); // X Button
  }

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
}
