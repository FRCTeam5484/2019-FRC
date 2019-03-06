
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class c_bottomCargo_ToggleArms extends Command {

  public c_bottomCargo_ToggleArms() {
    requires(Robot.bottomCargo);   
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    RobotMap.intakeArms.set(!RobotMap.intakeArms.get());
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