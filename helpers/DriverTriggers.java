package frc.robot.helpers;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.helpers.DriverXboxController.XboxAxis;

public class DriverTriggers {
	Joystick controller;
	public DriverTriggers(Joystick controller) {
		this.controller = controller;
	}

	public double getLeft() {
		return this.controller.getRawAxis(XboxAxis.LEFT_TRIGGER.value);
	}

    public double getRight() {
		return this.controller.getRawAxis(XboxAxis.RIGHT_TRIGGER.value);
	}

	public double getTwist() {
		return -getLeft() + getRight();
	}
}