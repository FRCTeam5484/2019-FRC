
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class c_hatchPanel_ToggleArm extends Command {
  public c_hatchPanel_ToggleArm() {
    requires(Robot.hatchPanel);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(RobotMap.panelArm.get() == true) {
       RobotMap.panelGrip.set(false);
    }
    RobotMap.panelArm.set(!RobotMap.panelArm.get());
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
  }
}
