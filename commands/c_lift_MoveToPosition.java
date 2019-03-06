
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class c_lift_MoveToPosition extends Command {
  public double desiredPosition;

  public c_lift_MoveToPosition(double position) {
    requires(Robot.lift);
    desiredPosition = position;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.lift.enable();
    Robot.lift.moveToPosition(desiredPosition);
  }

  @Override
  protected boolean isFinished() {
    return Robot.lift.onTarget();
  }

  @Override
  protected void end() {
    Robot.lift.stopLift();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
