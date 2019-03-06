package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class c_subDrive_TeleOp extends Command {
  public c_subDrive_TeleOp() {
    requires(Robot.subDrive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      Robot.subDrive.teleOpSubDrive();
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
    end();
  }
}
