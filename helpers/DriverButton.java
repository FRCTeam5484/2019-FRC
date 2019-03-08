package frc.robot.helpers;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.helpers.DriverXboxController.XboxButton;

public class DriverButton extends JoystickButton {
	public DriverButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);
	}
	public DriverButton(XboxController joystick, XboxButton button) {
		super(joystick, button.value);
	}
	public DriverButton(DriverXboxController joystick, XboxButton button) {
		super(joystick, button.value);
	}
}