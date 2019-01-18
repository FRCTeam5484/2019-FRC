

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class subdrivetrain_DriveLeftSideForward extends Command {
  public subdrivetrain_DriveLeftSideForward() {
   
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.subDrive.MoveLeftDrive(.5);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.subDrive.MoveLeftDrive(0);
  }

  @Override
  protected void interrupted() {
  }
}
