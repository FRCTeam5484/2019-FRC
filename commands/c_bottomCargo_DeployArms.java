package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class c_bottomCargo_DeployArms extends Command {
  public c_bottomCargo_DeployArms() {
    requires(Robot.bottomCargo);
  }

  @Override
  protected void initialize() {
    Robot.bottomCargo.deployArms();
  }

  @Override
  protected void execute() {
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