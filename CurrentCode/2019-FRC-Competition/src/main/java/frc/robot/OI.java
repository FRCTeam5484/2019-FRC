
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.driveForward;
import frc.robot.commands.driveToTarget;
import frc.robot.commands.resetNavX;

public class OI {
  public static JoystickButton driverOneButton_CargoInake;
  public static JoystickButton driverOneButton_CargoEject;
  public static JoystickButton driverOneButton_CargoSecure;

  public static JoystickButton driverTwoButton_HumanFeedPanel;
  public static JoystickButton driverTwoButton_HumanFeedCargo;
  public static JoystickButton driverTwoButton_CargoShipPanel;
  public static JoystickButton driverTwoButton_CargoShipCargo;
  public static JoystickButton driverTwoButton_StopLift;

  public static JoystickButton driverOneButton_VisionTest;
  public static JoystickButton driverOneButton_ResetNavX;
  public static JoystickButton driverOneButton_driveForward;
  public static JoystickButton driverOneButton_driveBackward;
  public static JoystickButton driverOneButton_turnRight;
  public static JoystickButton driverOneButton_turnLeft;

  public static XboxController driverOne;
  public static Joystick driverTwo;

  public OI(){
    driverOne = new XboxController(0);
    driverTwo = new Joystick(1);
    
    driverOneButton_VisionTest = new JoystickButton(new Joystick(0), 1);
    driverOneButton_VisionTest.whileHeld(new driveToTarget());

    driverOneButton_ResetNavX = new JoystickButton(new Joystick(0), 3);
    driverOneButton_ResetNavX.whileHeld(new resetNavX());

    driverOneButton_driveForward = new JoystickButton(new Joystick(0), 4);
    driverOneButton_driveForward.whileHeld(new driveForward());
  }

  public double getDriverTwoStickValue(int joyStickAxis){
    return driverTwo.getRawAxis(joyStickAxis);
  }
}