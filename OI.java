package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.Climb_SubSystem.Position;

public class OI {
  // Driver One
  public XboxController driverOne = new XboxController(0); // Driver One Controller
  public JoystickButton b_toggleArms = new JoystickButton(driverOne, 2); // B Button
  public JoystickButton b_deployArm = new JoystickButton(driverOne, 6); // Right Bumper
  public JoystickButton b_gripPanel = new JoystickButton(driverOne, 5); // Left Bumper
  
  // Driver Two
  public XboxController driverTwo = new XboxController(1); // Driver Two Controller
  public JoystickButton b_extendClimb_Both = new JoystickButton(driverTwo, 3); // X Button
  public JoystickButton b_extendClimb_Front = new JoystickButton(driverTwo, 8); // Start Button
  public JoystickButton b_extendClimb_Back = new JoystickButton(driverTwo, 7); // Back Button
  // Top Cargo
  public JoystickButton b_topCargo_Grab = new JoystickButton(driverTwo, 6); // Right Bumper
  public JoystickButton b_topCargo_Eject = new JoystickButton(driverTwo, 5); // Left Bumper
  // Lift
  public JoystickButton b_liftHeightLow = new JoystickButton(driverTwo, 1); // A Button
  public JoystickButton b_liftHeightMid = new JoystickButton(driverTwo, 2); // B Button
  public JoystickButton b_liftHeightHigh = new JoystickButton(driverTwo, 4); // Y Button
  // public JoystickButton b_liftHeightLow_Modifier = new JoystickButton(driverTwo, 1); 
  // public JoystickButton b_liftHeightMid_Modifer = new JoystickButton(driverTwo, 2); 
  // public JoystickButton b_liftHeightHigh_Modifier = new JoystickButton(driverTwo, 4); 

  public OI() {
    //------ Driver One ------\\
    b_toggleArms.whenPressed(new c_bottomCargo_ToggleArms());  // Intake Arms
    b_deployArm.whenPressed(new c_hatchPanel_ToggleArm()); // Panel Arm
    b_gripPanel.whenPressed(new c_hatchPanel_ToggleGrip()); // Grip

    //------ Driver Two ------\\
    // Top Cargo
    b_topCargo_Grab.whenPressed(new c_topCargo_GrabBall()); // Grab
    b_topCargo_Eject.whenPressed(new c_topCargo_EjectBall()); // Eject
    // Lift
    b_liftHeightLow.whenPressed(new c_lift_MoveToPosition(0)); // Low
    b_liftHeightMid.whenPressed(new c_lift_MoveToPosition(0)); // Mid
    b_liftHeightHigh.whenPressed(new c_lift_MoveToPosition(0)); // High
    // b_liftHeightLow_Modifier.whenPressed(new c_lift_MoveToPosition(0)); // Low Modifer
    // b_liftHeightMid_Modifier.whenPressed(new c_lift_MoveToPosition(0)); // Mid Modifer
    // b_liftHeightHigh_Modifier.whenPressed(new c_lift_MoveToPosition(0)); // High Modier
    // Climb
    b_extendClimb_Both.whenPressed(new c_climb_Extend(Position.All)); // Both
    b_extendClimb_Front.whenPressed(new c_climb_Extend(Position.Front)); // Front
    b_extendClimb_Back.whenPressed(new c_climb_Extend(Position.Back)); // Back
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
