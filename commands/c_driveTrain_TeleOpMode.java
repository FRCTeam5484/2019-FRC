package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class c_driveTrain_TeleOpMode extends Command {
  
  public c_driveTrain_TeleOpMode() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
    //Robot.vision.setLED(LEDState.Off);
  }

  @Override
  protected void execute() {
    Robot.driveTrain.mecanumDrive();
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
