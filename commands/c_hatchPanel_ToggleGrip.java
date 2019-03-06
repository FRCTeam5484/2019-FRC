
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class c_hatchPanel_ToggleGrip extends Command {
  public c_hatchPanel_ToggleGrip() {
    requires(Robot.hatchPanel);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    RobotMap.panelGrip.set(!RobotMap.panelGrip.get());
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
