

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climb_SubSystem.Position;

public class c_climb_Extend extends Command {
  static Position kPosition;

  public c_climb_Extend(Position position) {
    requires(Robot.climb);
    kPosition = position;
  }

  @Override
  protected void initialize() {
    Robot.climb.extendClimb(kPosition);
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
    Robot.climb.stopClimb(kPosition);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
