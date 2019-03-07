
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TopCargo_SubSystem extends Subsystem {

  public TalonSRX topIntakeMotor = RobotMap.topIntakeMotor;

  @Override
  public void initDefaultCommand() {
  }

  public void grabBall() {
    topIntakeMotor.set(ControlMode.PercentOutput, -1);
  }
  public void ejectBall() {
    topIntakeMotor.set(ControlMode.PercentOutput, 1);
  }
  public void stopMotors() {
    topIntakeMotor.set(ControlMode.PercentOutput, 0);
  }
}