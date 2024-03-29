
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.Robot;

public class c_driveTrain_DriveStraight extends Command {
  public Vector2d kDirection;
  public double kSpeed;

  public c_driveTrain_DriveStraight(Vector2d direction, double speed) {
    requires(Robot.driveTrain);
    kDirection = direction;
    kSpeed = speed;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.driveTrain.driveStraight(kDirection, kSpeed);
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
