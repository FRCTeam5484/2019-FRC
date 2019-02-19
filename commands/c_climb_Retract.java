/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.subsystems.Climb_SubSystem.Position;

public class c_climb_Retract extends Command {
  static Position kPosition;
  
  public c_climb_Retract(Position position) {
    requires(Robot.climb);
    kPosition = position;
  }

  @Override
  protected void initialize() {
    Robot.climb.retractClimb(kPosition);
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
    Robot.climb.stopClimb(kPosition);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
