package frc.robot.helpers;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.helpers.DriverXboxController.XboxAxis;

public class DriverThumbStick {
	Joystick controller;
	XboxAxis xAxis;
	XboxAxis yAxis;
	double yDeadband = 0.0;
	double xDeadband = 0.0;

	public DriverThumbStick(Joystick controller, XboxAxis xAxis, XboxAxis yAxis) {
		this.controller = controller;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public DriverThumbStick(Joystick controller, XboxAxis xAxis, XboxAxis yAxis, double yDeadband, double xDeadband) {
		this(controller, xAxis, yAxis);
		this.yDeadband = Math.abs(yDeadband);
		this.xDeadband = Math.abs(xDeadband);
	}

	public double getX() {
		double value = this.controller.getRawAxis(xAxis.value);
		return handleDeadband(value, xDeadband);
	}

	public double getY() {
		double value = this.controller.getRawAxis(yAxis.value);
		return handleDeadband(value, yDeadband);
	}

	public void setXDeadband(double deadband) {
		this.xDeadband = deadband;
	}

	public void setYDeadband(double deadband) {
		this.yDeadband = deadband;
	}

	public void setDeadband(double xDeadband, double yDeadband) {
		setXDeadband(xDeadband);
		setYDeadband(yDeadband);
	}

	public double handleDeadband(double input, double deadband) {
		if (input > -deadband && input < deadband) {
			return 0;
		} else {
			return input;
		}
	}
}