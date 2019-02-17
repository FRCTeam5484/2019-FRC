package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.c_bottomCargo_ToggleArms;
import frc.robot.commands.c_climb_Extend;
import frc.robot.commands.c_hatchPanel_ToggleArm;
import frc.robot.commands.c_hatchPanel_ToggleGrip;
import frc.robot.subsystems.Climb_SubSystem.Position;

public class OI {
  // Driver One
  public XboxController driverOne = new XboxController(0);
  public JoystickButton b_toggleArms = new JoystickButton(driverOne, 2); // B Button
  public JoystickButton b_deployArm = new JoystickButton(driverOne, 6); // Right Bumper
  public JoystickButton b_gripPanel = new JoystickButton(driverOne, 5); // Left Bumper
  
  // Driver Two
  public XboxController driverTwo = new XboxController(1);
  public JoystickButton b_extendClimb_Both = new JoystickButton(driverTwo, 3); // X Button
  // Top Cargo
  public JoystickButton b_topCargo_Grab = new JoystickButton(driverTwo, 6); // Right Bumper
  public JoystickButton b_topCargo_Eject = new JoystickButton(driverTwo, 5); // Left Bumper
  // Lift
  public JoystickButton b_liftHeightLow = new JoystickButton(driverTwo, 1); // A Button
  public JoystickButton b_liftHeightMid = new JoystickButton(driverTwo, 2); // B Button
  public JoystickButton b_liftHeightHigh = new JoystickButton(driverTwo, 4); // Y Button
  
  // public Joystick driverSubSystems = new Joystick(2);
  // public JoystickButton forward = new JoystickButton(driverOne, 4);
  // public JoystickButton back = new JoystickButton(driverOne, 1);
  // public JoystickButton left = new JoystickButton(driverOne, 3);
  // public JoystickButton right = new JoystickButton(driverOne, 2);

  public OI() {
    // Driver One
    b_toggleArms.whenPressed(new c_bottomCargo_ToggleArms()); 
    b_extendClimb_Both.whenPressed(new c_climb_Extend(Position.Front, true));
    b_deployArm.whenPressed(new c_hatchPanel_ToggleArm());
    b_gripPanel.whenPressed(new c_hatchPanel_ToggleGrip());

    //Driver One
    
    // forward.whileHeld(new c_driveTrain_DriveStraight(new Vector2d(0, 1), 1));
    // back.whileHeld(new c_driveTrain_DriveStraight(new Vector2d(0, -1), 1));
    // left.whileHeld(new c_driveTrain_DriveStraight(new Vector2d(-1, 0), 1));
    // right.whileHeld(new c_driveTrain_DriveStraight(new Vector2d(1, 0), 1));
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
