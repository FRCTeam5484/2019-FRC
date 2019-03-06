
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class c_hatchPanel_RetractArm extends Command {
  public c_hatchPanel_RetractArm() {
    requires(Robot.hatchPanel);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.hatchPanel.retractArm();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
