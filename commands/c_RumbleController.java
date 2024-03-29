
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class c_RumbleController extends Command {
  public c_RumbleController() {
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Timer time = new Timer();
    while(time.get() < 1) {
      Robot.oi.driverOne.setRumble(RumbleType.kLeftRumble, 0.75);
      Robot.oi.driverOne.setRumble(RumbleType.kRightRumble, 0.75);
    }
    Robot.oi.driverOne.setRumble(RumbleType.kLeftRumble, 0);
    Robot.oi.driverOne.setRumble(RumbleType.kRightRumble, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
