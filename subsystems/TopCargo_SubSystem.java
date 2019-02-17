
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class TopCargo_SubSystem extends Subsystem {

  public TalonSRX topIntakeMotor = RobotMap.topIntakeMotor;

  @Override
  public void initDefaultCommand() {
  }

  public void grabBall() {
    topIntakeMotor.set(ControlMode.PercentOutput, 0.8);
  }
  public void ejectBall() {
    topIntakeMotor.set(ControlMode.PercentOutput, -0.5);
  }
  public void stopMotors() {
    topIntakeMotor.set(ControlMode.PercentOutput, 0);
  }
}