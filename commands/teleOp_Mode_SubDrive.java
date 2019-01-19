package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class teleOp_Mode_SubDrive extends Command {
  public teleOp_Mode_SubDrive() {
    requires(Robot.subDrive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.subDrive.drive();
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
