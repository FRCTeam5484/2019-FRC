
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.*;
import frc.robot.RobotMap;
import frc.robot.Robot;

public class SubDrive_SubSystem extends Subsystem {
  public TalonSRX subDriveLeft = RobotMap.subDriveLeft;
  public TalonSRX subDriveRight = RobotMap.subDriveRight;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new c_subDrive_TeleOp());
  }

  public void teleOpSubDrive() {
    subDriveLeft.set(ControlMode.PercentOutput, -Robot.oi.endGame.rightStick.getY());
    subDriveRight.set(ControlMode.PercentOutput, -Robot.oi.endGame.leftStick.getY());
  }
}