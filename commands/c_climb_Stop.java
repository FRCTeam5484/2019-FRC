package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climb_SubSystem.Position;

public class c_climb_Stop extends Command {
  private String kPosition;

  public c_climb_Stop(String position) {
    requires(Robot.climb);
    kPosition = position;
  }

  @Override
  protected void initialize() {
    Robot.climb.stopClimb(kPosition);
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
