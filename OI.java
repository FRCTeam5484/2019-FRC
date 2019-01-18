
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  public static JoystickButton driverOneButton_CargoInake;
  public static JoystickButton driverOneButton_CargoEject;
  public static JoystickButton driverOneButton_CargoSecure;

  public static JoystickButton driverTwoButton_HumanFeedPanel;
  public static JoystickButton driverTwoButton_HumanFeedCargo;
  public static JoystickButton driverTwoButton_CargoShipPanel;
  public static JoystickButton driverTwoButton_CargoShipCargo;
  public static JoystickButton driverTwoButton_StopLift;

  public static XboxController driverOne;
  public static Joystick driverTwo;

  public OI(){
    driverOne = new XboxController(0);
    driverTwo = new Joystick(1);
  }

  public double getDriverTwoStickValue(int joyStickAxis){
    return driverTwo.getRawAxis(joyStickAxis);
  }
}