
package frc.robot.controllers;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.controllers.DriverXboxController.XboxAxis;

public class DriverAxisButton extends Button {

	private final Joystick joy;
	private final int axis;
	private double targetVal;
	private ThresholdType thresholdType;

    public DriverAxisButton(Joystick joystick, int axis, double threshold, ThresholdType thresholdType) {
		this.joy = joystick;
		this.axis = axis;
		this.targetVal = threshold;
		this.thresholdType = thresholdType;
	}
	public DriverAxisButton(Joystick joystick, XboxAxis axis, double threshold, ThresholdType thresholdType) {
		this(joystick, axis.value, threshold, thresholdType);
	}
	public boolean get() {
		switch (this.thresholdType) {
		case EXACT:
			System.out.println("axis value: " + joy.getRawAxis(this.axis));
			return joy.getRawAxis(this.axis) == this.targetVal;
		case LESS_THAN:
			return joy.getRawAxis(this.axis) < this.targetVal;
		case GREATER_THAN:
			return joy.getRawAxis(this.axis) > this.targetVal;
		case POV:
			return joy.getPOV() == this.targetVal;
		default:
			return false;
		}
	}
	static enum ThresholdType {
		LESS_THAN, GREATER_THAN, EXACT, POV;
	}

	public void configureThreshold(double threshold) {
		this.targetVal = threshold;
	}
}