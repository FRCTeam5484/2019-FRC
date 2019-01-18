
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class subdrivetrain_DriveRightSideForward extends Command {
  public subdrivetrain_DriveRightSideForward() {
    
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.subDrive.MoveRightDrive(.5);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.subDrive.MoveRightDrive(0);
  }

  @Override
  protected void interrupted() {
  }
}
