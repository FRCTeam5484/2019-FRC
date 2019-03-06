package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class c_topCargo_EjectBall extends Command {
  public c_topCargo_EjectBall() {
    requires(Robot.topCargo);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.topCargo.ejectBall();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.topCargo.stopMotors();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
