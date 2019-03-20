package frc.robot.controllers;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllers.DriverAxisButton.ThresholdType;
import frc.robot.controllers.DriverXboxController.XboxAxis;
import frc.robot.controllers.DriverXboxController.XboxDpad;

public class DriverDpad {
	public final Joystick joy;
	public DriverAxisButton Up;
	public DriverAxisButton Down;
	public DriverAxisButton Left;
	public DriverAxisButton Right;
	public DriverAxisButton UpLeft;
	public DriverAxisButton UpRight;
	public DriverAxisButton DownLeft;
	public DriverAxisButton DownRight;

    public DriverDpad(Joystick joystick) {
		this.joy = joystick;
		this.Up = new DriverAxisButton(joy, XboxAxis.DPAD, XboxDpad.UP.value, ThresholdType.POV);
		this.Down = new DriverAxisButton(joy, XboxAxis.DPAD, XboxDpad.DOWN.value, ThresholdType.POV);
		this.Left = new DriverAxisButton(joy, XboxAxis.DPAD, XboxDpad.LEFT.value, ThresholdType.POV);
		this.Right = new DriverAxisButton(joy, XboxAxis.DPAD, XboxDpad.RIGHT.value, ThresholdType.POV);
		this.UpLeft = new DriverAxisButton(joy, XboxAxis.DPAD, XboxDpad.UP_LEFT.value, ThresholdType.POV);
		this.UpRight = new DriverAxisButton(joy, XboxAxis.DPAD, XboxDpad.UP_RIGHT.value, ThresholdType.POV);
		this.DownLeft = new DriverAxisButton(joy, XboxAxis.DPAD, XboxDpad.DOWN_LEFT.value, ThresholdType.POV);
		this.DownRight = new DriverAxisButton(joy, XboxAxis.DPAD, XboxDpad.DOWN_RIGHT.value, ThresholdType.POV);
	}

	public double getValue() {
		return joy.getRawAxis(XboxAxis.DPAD.value);
	}
}