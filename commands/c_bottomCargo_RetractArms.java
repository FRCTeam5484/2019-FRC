
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.BottomCargo_SubSystem;

public class c_bottomCargo_RetractArms extends Command {
  public c_bottomCargo_RetractArms() {
    requires(new BottomCargo_SubSystem());
  }

  @Override
  protected void initialize() {
    Robot.bottomCargo.retractArms();
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
  }

  @Override
  protected void interrupted() {
  }
}
