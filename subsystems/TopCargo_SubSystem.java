
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.c_topCargo_TeleOp;

public class TopCargo_SubSystem extends Subsystem {

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new c_topCargo_TeleOp());
  }

  public void grabBall() {
    RobotMap.topIntakeMotor.set(ControlMode.PercentOutput, 0.5);
  }
  public void ejectBall() {
    RobotMap.topIntakeMotor.set(ControlMode.PercentOutput, -0.5);
  }
  public void stopMotors() {
    RobotMap.topIntakeMotor.set(ControlMode.PercentOutput, 0);
  }
  public void teleOpIntake() {
    /*if(Robot.oi.driverTwo.getTriggerAxis(Hand.kRight) > 0.1)
    {
      RobotMap.topIntakeMotor.set(ControlMode.PercentOutput, Robot.oi.driverTwo.getTriggerAxis(Hand.kRight));
    }
    else if(Robot.oi.driverTwo.getTriggerAxis(Hand.kLeft) > 0.1)
    {
      RobotMap.topIntakeMotor.set(ControlMode.PercentOutput, -Robot.oi.driverTwo.getTriggerAxis(Hand.kLeft));
    }
    else {
      stopMotors();
    }*/
  }
}