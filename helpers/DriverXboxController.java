
package frc.robot.helpers;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.helpers.DriverAxisButton.ThresholdType;

public class DriverXboxController extends Joystick {
    public DriverXboxController(int port){
        super(port);
    }
    public DriverXboxController(int port, double xDeadband, double yDeadband) {
		this(port);
		this.leftStick.setDeadband(xDeadband, yDeadband);
		this.rightStick.setDeadband(xDeadband, yDeadband);
	}
	public DriverButton xButton = new DriverButton(this, XboxButton.X);
	public DriverButton yButton = new DriverButton(this, XboxButton.Y);
	public DriverButton aButton = new DriverButton(this, XboxButton.A);
	public DriverButton bButton = new DriverButton(this, XboxButton.B);
	public DriverButton rightBumper = new DriverButton(this, XboxButton.RIGHT_BUMPER);
	public DriverButton leftBumper = new DriverButton(this, XboxButton.LEFT_BUMPER);
	public DriverButton startButton = new DriverButton(this, XboxButton.START);
	public DriverButton selectButton = new DriverButton(this, XboxButton.SELECT);
	public DriverButton leftStickButton = new DriverButton(this, XboxButton.LEFT_STICK);
	public DriverButton rightStickButton = new DriverButton(this, XboxButton.RIGHT_STICK);

	public DriverAxisButton leftTrigger = new DriverAxisButton(this, XboxAxis.LEFT_TRIGGER, .25, ThresholdType.GREATER_THAN);
	public DriverAxisButton rightTrigger = new DriverAxisButton(this, XboxAxis.RIGHT_TRIGGER, .25, ThresholdType.GREATER_THAN);
	public DriverDpad Dpad = new DriverDpad(this);

	public DriverThumbStick leftStick = new DriverThumbStick(this, XboxAxis.LEFT_X, XboxAxis.LEFT_Y);
	public DriverThumbStick rightStick = new DriverThumbStick(this, XboxAxis.RIGHT_X, XboxAxis.RIGHT_Y);

	public DriverTriggers triggers = new DriverTriggers(this);

	public void setRumble(double leftValue, double rightValue) {
		setRumble(RumbleType.kLeftRumble, leftValue);
		setRumble(RumbleType.kRightRumble, rightValue);
	}

	static enum XboxButton {
		A(1), B(2), X(3), Y(4), LEFT_BUMPER(5), RIGHT_BUMPER(6), SELECT(7), START(8), LEFT_STICK(9), RIGHT_STICK(10);
		final int value;
		XboxButton(int value) {
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
	}

	static enum XboxAxis {
		LEFT_X(0), LEFT_Y(1), LEFT_TRIGGER(2), RIGHT_TRIGGER(3), RIGHT_X(4), RIGHT_Y(5), DPAD(6);
		final int value;
		XboxAxis(int value) {
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
	}
	static enum XboxDpad {
		UNPRESSED(-1), UP(0), UP_RIGHT(45), RIGHT(90), DOWN_RIGHT(135), DOWN(180), DOWN_LEFT(225), LEFT(270), UP_LEFT(315);
		final int value;
		XboxDpad(int value) {
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
	}
}