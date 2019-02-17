/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climb_SubSystem.Position;

public class c_climb_Retract extends Command {
  static Position kPosition;
  static boolean kBoth;
  
  public c_climb_Retract(Position position, boolean both) {
    requires(Robot.climb);
    kBoth = both;
    kPosition = position;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climb.retractLift(kPosition, kBoth);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
