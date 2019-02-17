/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class c_lift_MoveToPosition extends Command {
  public double desiredPosition;

  public c_lift_MoveToPosition(double position) {
    requires(Robot.lift);
    desiredPosition = position;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.lift.moveToPosition(desiredPosition);
  }

  @Override
  protected boolean isFinished() {
    return Robot.lift.onTarget();
  }

  @Override
  protected void end() {
    Robot.lift.stopLift();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
