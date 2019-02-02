package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class teleOp_Mode extends Command {
  public teleOp_Mode() {
    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
    RobotMap.navX.resetDisplacement();
  }

  @Override
  protected void execute() {
    Robot.drive.TeleOpDrive();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
